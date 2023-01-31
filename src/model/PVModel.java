package model;

import databases.DBpvModel;
import givenergy_apis.ChartsGivEnergyAPI;
import other_apis.ForecastAPI;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import solarproject.DateStrings;
import solarproject.Files;

public final class PVModel {

    private final PanelProperties PANELPROPERITES = new PanelProperties(); //An object that holds variables about the orientation and location of the solar panels

    private final DateStrings DATESTRS = new DateStrings(); //An object that holds variables that represent a year, month and date

    public PVModel() {
        updateParams();
        LocalDate ld = LocalDate.now();
        DATESTRS.setYear(Integer.toString(ld.getYear()));
        DATESTRS.setMonth(Integer.toString(ld.getMonthValue()));
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(new Date());
        DATESTRS.setDate(date);
    }

    public PanelProperties getProperties() {
        return this.PANELPROPERITES;
    }

    public void updateParams() { //After the user changes settings in the GUI such as the location coordinates of the solar panels or tilt of the panels, this method can update the attributes in the class that are holding information
        try {
            Files files = new Files();//reads from appSettings.txt file to get panel info
            String settings = files.read();
            JSONObject jo = new JSONObject(settings);
            PANELPROPERITES.setPanelAzimuth(jo.getInt("azimuth"));
            PANELPROPERITES.setPanelTilt(jo.getInt("tilt"));
            PANELPROPERITES.setLongitude(jo.getDouble("longitude"));
            PANELPROPERITES.setLatitude(jo.getDouble("latitude"));
        } catch (JSONException ex) {
            new Error("There was a problem reading data");
        }
    }

    public double[] getGHIData() { //Returns all the solar radiation data that is stored in the database that is being used by the complex mathematical model
        DBpvModel db = new DBpvModel();
        db.getModelData();
        return db.getGHIdata();//gets all GHI datapoints from database
    }

    public double[] getPPVData() { //Returns all the Solar Panel power output data that is stored in the database that is being used by the complex mathematical model
        DBpvModel db = new DBpvModel();
        db.getModelData();
        return db.getPVoutputdata();//gets all PPV datapoints from database
    }

    public void updateModel() { //A key function that will update and refine the complex mathematical model with new data that has been collected over the last day.
        DBpvModel db = new DBpvModel();
        ChartsGivEnergyAPI cgeAPI = new ChartsGivEnergyAPI(DATESTRS.getYear(), DATESTRS.getMonth(), DATESTRS.getDate());
        ForecastAPI fa = new ForecastAPI();
        String actuals = fa.estimatedActuals();//array of estimated actual solar radiation values over the last day
        String[][] radData = fa.calcTiltedPlaneRadiation(48, PANELPROPERITES.getPanelAzimuth(), PANELPROPERITES.getPanelTilt(), actuals, false); //recalculated array of solar raditaion values taking panel orientation into account
        String[][] ppv = cgeAPI.GETPVpowerByDay(DATESTRS.getDate()); //array of the actual power output of the solar panels
        double[] newppv = format(radData, ppv);
        double[] newRadData = removeSecondItem(radData);
        db.addToPVModelData(newRadData, newppv);//adds the new collected data to the data table in the database
        db.getModelData();
        PolynomialRegression pr = new PolynomialRegression(db.getGHIdata(), db.getPVoutputdata(), 5, true); //re-evaluates the complex mathematical model with all the newly collected data
        double[] coeff = pr.getCoeff();
        db.addToPVModel(coeff[5], coeff[4], coeff[3], coeff[2], coeff[1], coeff[0]); //adds the newly calculated model coefficients to the database
    }

    public double useModel(double ghi) { //From an inputted value of GHI (solar radiation) in the parameter it will return a value for the expected power output of the solar panels based on the mathematical model
        DBpvModel db = new DBpvModel();
        double[] model = db.getModel(); // fetches mathematical model coefficients from DB
        model = reverseArr(model);
        PolynomialRegression nlr = new PolynomialRegression(db.getGHIdata(), db.getPVoutputdata(), 5, false);
        nlr.setCoeff(model); //sets coefficients as model has already been calculated and fetched from storage in DB
        double predictedpv = nlr.curveFunction(ghi);
        return predictedpv;
    }

    public double[] useModel(double[] ghi) { //From an inputted array of values of GHI (solar radiation) in the parameter it will return an array of values for the expected power output of the solar panels based on the mathematical model
        DBpvModel db = new DBpvModel();
        db.getModelData();
        double[] model = db.getModel(); //fetches mathematical model coefficients from DB
        model = reverseArr(model);
        double[] ppv = new double[ghi.length];
        PolynomialRegression nlr = new PolynomialRegression(db.getGHIdata(), db.getPVoutputdata(), 5, false);
        nlr.setCoeff(model); //sets coefficients as model has already been calculated and fetched from storage in DB
        double predictedPV;
        for (int i = 0; i < ppv.length; i++) { //iterates through array of inputs to produce array of outputs
            predictedPV = nlr.curveFunction(ghi[i]);
            ppv[i] = predictedPV;
        }
        return ppv;
    }

    private double[] format(String[][] ghiEstimatedValues, String[][] ppv) {
        /*
        Formats data that is collected from temporary storage in database and data that is collected from weather forecast API. 
        It calculated average power outputs over half hour periods of the forecasted time frame according to the times returned from the API.
         */
        double[] newppv = new double[ghiEstimatedValues.length];
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        for (int i = 0; i < ghiEstimatedValues.length; i++) {
            String periodEnd = ghiEstimatedValues[i][1];
            LocalTime periodEndlt = LocalTime.parse(periodEnd, dtf); //end of the half hour period
            LocalTime periodStartlt = LocalTime.parse(periodEnd, dtf).minusMinutes(30); //start of the half hour period
            double avrPower, totalPower = 0;
            int count = 0;
            for (String[] ppv1 : ppv) {
                String ppvTime = ppv1[1];
                LocalTime ppvTimelt = LocalTime.parse(ppvTime.substring(11, 19), dtf); //the time the power value was recorded
                boolean inRange = ppvTimelt.isAfter(periodStartlt) & ppvTimelt.isBefore(periodEndlt); //calculates whether the LocalTime object is within two other times and sets boolean values accordingly
                if (inRange) {
                    totalPower += Double.parseDouble(ppv1[0]); //if in range then start adding up values in order to be able to calculate average later
                    count++;
                }
            }
            avrPower = totalPower / count; //average value in time period calcualted by dividing sum by the count
            if (Double.isNaN(avrPower)) {
                avrPower = 0;
            }
            BigDecimal bd = new BigDecimal(avrPower);
            avrPower = Double.parseDouble(bd.setScale(1, RoundingMode.HALF_UP).toString()); //rounds the value to remove unnessicary decimal places
            newppv[i] = avrPower; //adds average power value for that period to array
        }
        return newppv;
    }

    private double[] removeSecondItem(String[][] twoDarray) {
        double[] oneDArray = new double[twoDarray.length];
        for (int i = 0; i < oneDArray.length; i++) {
            oneDArray[i] = Double.parseDouble(twoDarray[i][0]);
        }
        return oneDArray;
    }

    private double[] reverseArr(double[] arr) {
        int length = arr.length;
        double[] newArr = new double[length];
        for (int i = 0; i < length; i++) {
            newArr[i] = arr[length - 1 - i];
        }
        return newArr;
    }
}
