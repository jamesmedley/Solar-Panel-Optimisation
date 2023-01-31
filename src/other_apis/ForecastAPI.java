package other_apis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import model.PVModel;
import model.PanelProperties;

public class ForecastAPI {

    private HttpURLConnection connection; //Holds the HttpURLConnection that is used when using the API

    private String GETResponse(boolean forecast) { //Uses the Solcast API to get a weather forecast for the next 48 hours. Returns data as a string representation of a JSON response 
        try {
            String response;
            PVModel pvm = new PVModel();
            PanelProperties pp = pvm.getProperties();
            double longitude = pp.getLongitude();
            double latitude = pp.getLatitude();
            BufferedReader reader;
            String line;
            StringBuilder responseContent = new StringBuilder();
            URL url = null;
            if (forecast) { //if forecast data is wanted use this endpoint
                try {
                    url = new URL("https://api.solcast.com.au/world_radiation/forecasts?latitude=" + latitude + "&longitude=" + longitude + "&format=json&%E2%80%8B&api_key=<api key>");
                } catch (MalformedURLException ex) {
                    new Error("There was a problem collecting forecasts");
                }
            } else { // if estimated actuals are wanted use this endpoint
                try {
                    url = new URL("https://api.solcast.com.au/world_radiation/estimated_actuals?latitude=" + latitude + "&longitude=" + longitude + "&format=json&%E2%80%8B&api_key=<api key>");
                } catch (MalformedURLException ex) {
                    new Error("There was a problem collecting forecasts");
                }
            }
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int status = connection.getResponseCode();
            if (status != 200) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            response = responseContent.toString();
            connection.disconnect();
            return response;
        } catch (IOException ex) {
            new Error("There was a problem collecting forecasts");
            return null;
        }
    }

    public String forecast() {
        return GETResponse(true);
    }

    public String estimatedActuals() {
        return GETResponse(false);
    }

    public double[] GETGHI(String forecast, int limit) {
        try {
            double[] ghiData = new double[limit];
            JSONObject response = new JSONObject(forecast);
            for (int i = 0; i < limit; i++) {
                ghiData[i] = response.getJSONArray("forecasts").getJSONObject(i).getDouble("ghi"); //collects GHI data from json
            }
            return ghiData;
        } catch (JSONException ex) {
            new Error("There was a problem collecting forecasts");
            return null;
        }
    }

    public double[] GETGHI90(String forecast) {
        try {
            double[] ghi90Data = new double[96];
            JSONObject response = new JSONObject(forecast);
            for (int i = 0; i < 96; i++) {
                ghi90Data[i] = response.getJSONArray("forecasts").getJSONObject(i).getDouble("ghi90"); //collects GHI90 data from json
            }
            return ghi90Data;
        } catch (JSONException ex) {
            new Error("There was a problem collecting forecasts");
            return null;
        }
    }

    public double[] GETGHI10(String forecast) {
        try {
            double[] ghi10Data = new double[96];
            JSONObject response = new JSONObject(forecast);
            for (int i = 0; i < 96; i++) {
                ghi10Data[i] = response.getJSONArray("forecasts").getJSONObject(i).getDouble("ghi10"); //collects GHI10 data from json
            }
            return ghi10Data;
        } catch (JSONException ex) {
            new Error("There was a problem collecting forecasts");
            return null;
        }
    }

    public String[][] calcTiltedPlaneRadiation(int limit, double panelAzimuth, double panelTilt, String radiationData, boolean forecast) {
        /*
        Performs calculations on the solar radiation GHI data from the forecast to calculate the actual intensity of light that is incident to the solar panels (the API only returns the solar radiation intensity for horizontal ground). 
        It used values of panel tilt angle and azimuth and location coordinates to perform these calculations.
         */
        try {
            String[][] solarRadiation = new String[limit][3];
            JSONObject response = new JSONObject(radiationData);//JSONObject that holds API response
            int dayOfYear;
            double hour;
            String arrayName, date;
            if (forecast) {
                arrayName = "forecasts";
            } else {
                arrayName = "estimated_actuals";
            }
            for (int i = 0; i < limit; i++) {
                try {
                    date = response.getJSONArray(arrayName).getJSONObject(i).getString("period_end");//gets time and date from json response (needed for calculations)
                    dayOfYear = LocalDate.of(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)), Integer.parseInt(date.substring(8, 10))).getDayOfYear(); // uses above date to work out day of year
                    hour = Double.parseDouble(date.substring(11, 13)) + (Double.parseDouble(date.substring(14, 16)) / 60); // uses above date to work hour of day with a decimal to represent minutes
                    double elevation = calcElevation(dayOfYear, hour); //uses clacElevation method to work out the elevation of the sun at each time 
                    double azimuth = calcAzimuth(dayOfYear, hour); //uses clacAzimuth method to work out the azimuth of the sun at each time 
                    double ghi = response.getJSONArray(arrayName).getJSONObject(i).getDouble("ghi"); //gets GHI value fron API response
                    double incidentRad = ghi / Math.sin(toRadians(90 - elevation)); //calcuates the radiation intensity in the direction of the direct path from the sun to a point on the ground
                    double moduleRad = incidentRad * ((Math.cos(toRadians(90 - elevation)) * Math.sin(toRadians(panelTilt)) * Math.cos(toRadians(panelAzimuth - azimuth))) + (Math.sin(toRadians(90 - elevation)) * Math.cos(toRadians(panelTilt)))); //calculates the radiation intensity that is incident to the plane of the solar panels
                    if ((moduleRad == Double.NEGATIVE_INFINITY) | (moduleRad == Double.POSITIVE_INFINITY) | (Double.isNaN(moduleRad))) { //if the calculated value tends to a very large value due to the complex maths then the module radiation is set to 0 as that is what it would actually be
                        moduleRad = 0.0;
                    } else if (moduleRad < 0) {
                        moduleRad = moduleRad * -1;
                    }
                    BigDecimal bd = new BigDecimal(moduleRad);
                    moduleRad = Double.parseDouble(bd.setScale(1, RoundingMode.HALF_UP).toString());
                    solarRadiation[i][0] = Double.toString(moduleRad);
                    String time = response.getJSONArray(arrayName).getJSONObject(i).getString("period_end").substring(11, 19);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalTime lTime = LocalTime.parse(time, dtf).plusSeconds(1);
                    LocalTime lTime2 = LocalTime.parse(lTime.toString(), dtf).minusMinutes(30);
                    solarRadiation[i][1] = lTime.toString();
                    solarRadiation[i][2] = lTime2.toString();
                } catch (JSONException ex) {
                    new Error("There was a problem collecting forecasts");
                    return null;
                }
            }
            return solarRadiation;
        } catch (JSONException ex) {
            new Error("There was a problem collecting forecasts");
            return null;
        }
    }

    private double[] calcSolarParams(int days, double hour) { //Calculates the current hour angle and declination based upon the current date and time and location
        PVModel pvm = new PVModel();
        PanelProperties pp = pvm.getProperties();
        double longitude = pp.getLongitude();
        double x = toRadians((360 / 365) * (days - 81));
        double equationOfTime = 9.87 * (Math.sin(2 * x)) - 7.53 * (Math.cos(x)) - 1.5 * (Math.sin(x)); //calculates EoT
        double timeCorrection = 4 * (longitude) + equationOfTime; //calculates timeCorrection from EoT
        double localSolarTime = hour + (timeCorrection / 60); //calculates the local solar time
        double hourAngle = 15 * (localSolarTime - 12);  //calculates the hour angle
        double declination = toRadians(3.45 * Math.sin(x));//calculates the declination of the sun
        return new double[]{declination, hourAngle}; //returns the hour angle and declination
        //maths sourced from: https://www.pveducation.org/pvcdrom/properties-of-sunlight/the-suns-position
    }

    private double calcElevation(int days, double hour) { //Uses the calcSolarParams method and trigonometry to calculate the elevation of the sun above the horizon at a location and time
        PVModel pvm = new PVModel();
        PanelProperties pp = pvm.getProperties();
        double[] solarParams = calcSolarParams(days, hour);
        double declination = solarParams[0];
        double hourAngle = solarParams[1];
        double latitude = pp.getLatitude();
        double elevation = toDegrees(Math.asin(Math.sin(declination) * Math.sin(toRadians(latitude)) + Math.cos(declination) * Math.cos(toRadians(latitude)) * Math.cos(toRadians(hourAngle)))); //calculates angle of sun in degrees above horizon
        return elevation;
    }

    private double calcAzimuth(int days, double hour) { //Uses the calcSolarParams method and trigonometry to calculate the azimuth of the sun at a location and time
        PVModel pvm = new PVModel();
        PanelProperties pp = pvm.getProperties();
        double[] solarParams = calcSolarParams(days, hour);
        double declination = solarParams[0];
        double hourAngle = solarParams[1];
        double latitude = pp.getLatitude();
        double azimuth = toDegrees(Math.acos(((Math.sin(declination) * Math.sin(toRadians(latitude))) - (Math.cos(declination) * Math.sin(toRadians(latitude)) * Math.cos(toRadians(hourAngle)))) / (Math.cos(toRadians(calcElevation(days, hour)))))); //calculates azimuth of sun in degrees
        return azimuth;
    }

    public double[] GETPPV(String forecast) { //Uses the forecast response and mathematical model to return an array of predicted power output from the solar panels
        PVModel pvm = new PVModel();
        PanelProperties pp = pvm.getProperties();
        String[][] tiltedRadiation = calcTiltedPlaneRadiation(96, pp.getPanelAzimuth(), pp.getPanelTilt(), forecast, true);
        double[] ppv = new double[96];
        double[] radiation = new double[96];
        for (int i = 0; i < ppv.length; i++) {
            radiation[i] = Double.parseDouble(tiltedRadiation[i][0]);
        }
        ppv = pvm.useModel(radiation);
        return ppv;
    }

    public String[][] PPVForDayRecommendations(String forecast) { //Uses the mathematical model to return an array of predicted power output and time
        PVModel pvm = new PVModel();
        PanelProperties pp = pvm.getProperties();
        String[][] tiltedRadiation = calcTiltedPlaneRadiation(96, pp.getPanelAzimuth(), pp.getPanelTilt(), forecast, true);//array of forecasted radiation intensity on the plane of the panels
        double[] ppv = new double[96];
        double[] radiation = new double[96];
        for (int i = 0; i < ppv.length; i++) { //converts string array to double array
            radiation[i] = Double.parseDouble(tiltedRadiation[i][0]);
        }
        ppv = pvm.useModel(radiation); //converts solar radiation array to predicted power output array using model
        ArrayList<String[]> values = new ArrayList();
        boolean day = false;
        for (int i = 0; i < ppv.length; i++) {//loops that breaks full day of data into 3 parts according to time boundaries at 8:00 and 15:30 
            if (((!"08:00".equals(tiltedRadiation[i][2].substring(0, 5))) & day == false)) {
            } else {
                day = true;
                if ("15:30".equals(tiltedRadiation[i][2].substring(0, 5))) {
                    values.add(new String[]{Double.toString(ppv[i]), tiltedRadiation[i][1], tiltedRadiation[i][2]});
                    break;
                }
                values.add(new String[]{Double.toString(ppv[i]), tiltedRadiation[i][1], tiltedRadiation[i][2]});
            }
        }
        String[][] powerPeriods = new String[values.size()][3];
        for (int i = 0; i < powerPeriods.length; i++) {//arraylist items added to array to be returned
            powerPeriods[i] = values.get(i);
        }
        powerPeriods = reverseArr(powerPeriods);
        return powerPeriods;
    }

    private String[][] reverseArr(String[][] arr) {
        int length = arr.length;
        int sublen = arr[0].length;
        String[][] newarr = new String[length][sublen];
        for (int i = 0; i < length; i++) {
            newarr[i] = arr[length - 1 - i];
        }
        return newarr;
    }

    private double toRadians(double degrees) {
        double radians = degrees * (Math.PI / 180);
        return radians;
    }

    private double toDegrees(double radians) {
        double degrees = radians / (Math.PI / 180);
        return degrees;
    }

    public double[] GETCloudOpacity(String forecast) {
        try {
            double[] cloudData = new double[96];
            JSONObject response = new JSONObject(forecast);
            for (int i = 0; i < 96; i++) {
                cloudData[i] = response.getJSONArray("forecasts").getJSONObject(i).getDouble("cloud_opacity");//collects cloud opacity data from json
            }
            return cloudData;
        } catch (JSONException ex) {
            new Error("There was a problem collecting forecasts");
            return null;
        }
    }

    public double[] GETZenith(String forecast) {
        try {
            double[] zenithData = new double[96];
            JSONObject response = new JSONObject(forecast);
            for (int i = 0; i < 96; i++) {
                zenithData[i] = response.getJSONArray("forecasts").getJSONObject(i).getDouble("zenith");//collects zenith data from json
            }
            return zenithData;
        } catch (JSONException ex) {
            new Error("There was a problem collecting forecasts");
            return null;
        }
    }

    public double[] GETAzimuth(String forecast) {
        try {
            double[] azimuthData = new double[96];
            JSONObject response = new JSONObject(forecast);
            for (int i = 0; i < 96; i++) {
                azimuthData[i] = response.getJSONArray("forecasts").getJSONObject(i).getDouble("azimuth");//collects azimuth data from json
            }
            return azimuthData;
        } catch (JSONException ex) {
            new Error("There was a problem collecting forecasts");
            return null;
        }
    }

    public double[] GETAirTemp(String forecast) {
        try {
            double[] tempData = new double[96];
            JSONObject response = new JSONObject(forecast);
            for (int i = 0; i < 96; i++) {
                tempData[i] = response.getJSONArray("forecasts").getJSONObject(i).getDouble("air_temp");//collects air temperature data from json
            }
            return tempData;
        } catch (JSONException ex) {
            new Error("There was a problem collecting forecasts");
            return null;
        }
    }
}
