package databases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import solarproject.Error;

public class DBpvModel extends DBConnection {

    private double[] ghiData; //Array that contains all the forecasted GHI data stored in the database

    private double[] pvOutputData; //Array that contains all the power output data from the solar panels that is stored in the database

    public DBpvModel() {
        DBCredentials dbCreds = new DBCredentials(<db name>, <db uesr>, <db password>); //Uses the DBCredentials class to add the passwords and usernames and other infomation that needs to be uesd in the DBConnection class
        super.setDBCredentials(dbCreds);
        super.connect();
    }

    public double[] getGHIdata() {
        return this.ghiData;
    }

    public double[] getPVoutputdata() {
        return this.pvOutputData;
    }

    public void addToPVModelData(double[] forecastGHIdata, double[] pvOutputdata) { //Inserts new data that can be used in the model into a database table
        LocalDate date = LocalDate.now();
        String query;
        for (int i = 0; i < forecastGHIdata.length; i++) {
            if ((pvOutputdata[i] == 0) | (forecastGHIdata[i] == 0)) {
                continue;
            }
            query = "INSERT INTO pvModelData(forecastGHI,pvOutput,FK_pvModel)" + "VALUES (" + forecastGHIdata[i] + "," + pvOutputdata[i] + ",'" + date + "')"; //SQL query to insert a record into the pvModelData table  
            perform(query);
        }
    }

    private void deleteByID(String id) { //Can delete a set of data points that have a specific primary key date
        String query = "DELETE FROM pvModelData WHERE pvModel.PK_pvModel='" + id + "'"; //SQL query to delete every item with a specific foriegn key 
        perform(query);
    }

    public void getModelData() { //Gets all the model data points from the database and stores them into arrays that can be used elsewhere in the program
        try {
            int count = getCountpvModelData();
            ghiData = new double[count];
            pvOutputData = new double[count];
            ResultSet results;
            String query = "SELECT * FROM pvModelData"; //SQL query to select everything from pvModelData table
            results = getResponse(query);
            int i = 0;
            while (results.next()) {
                ghiData[i] = results.getDouble("forecastGHI");
                pvOutputData[i] = results.getDouble("pvOutput");
                i++;
            }
        } catch (SQLException ex) {
            Error err = new Error();
            err.sendError("Database Error");
        }
    }

    private int getCountpvModelData() { //Performs SQL statement to find how many records are in the model data table
        try {
            String query = "SELECT COUNT(*) FROM pvModelData"; //SQL query to count the number of records in the pvModelData table
            ResultSet results;
            results = getResponse(query);
            results.next();
            int count = results.getInt(1);
            return count;
        } catch (SQLException ex) {
            Error err = new Error();
            err.sendError("Database Error");
            return -1;
        }
    }

    public void addToPVModel(double order5, double order4, double order3, double order2, double order1, double order0) { //Adds calculated model parameters to a table in the database
        LocalDate date = LocalDate.now();
        String query = "INSERT INTO pvModel(Order5,Order4,Order3,Order2,Order1,Order0,PK_pvModel)" + "VALUES (" + order5 + "," + order4 + "," + order3 + "," + order2 + "," + order1 + "," + order0 + ",'" + date + "')";
        perform(query);
    }

    public double[] getModel() { //Looks in the model table to get the model parameters that can be used to make forecast predictions
        try {
            String query = "SELECT * FROM pvModel ORDER BY date(PK_pvModel) DESC"; //SQL query to select every value in pvModel table and order them by the date in the primary key of that table descending
            ResultSet rs = getResponse(query);
            double[] model = new double[6];
            rs.next();
            model[0] = rs.getDouble("Order5");
            model[1] = rs.getDouble("Order4");
            model[2] = rs.getDouble("Order3");
            model[3] = rs.getDouble("Order2");
            model[4] = rs.getDouble("Order1");
            model[5] = rs.getDouble("Order0");
            return model;
        } catch (SQLException ex) {
            Error err = new Error();
            err.sendError("Database Error");
            return null;
        }
    }

    public String[] getChargingPeriods() { //Returns a list of previously calculated charging and discharging periods to display in the GUI that are stored overnight in a table.
        try {
            String[] charging = new String[4];
            String query = "SELECT * FROM SystemSettings"; //SQL query to select everything from SystemSettings table
            ResultSet results;
            results = getResponse(query);
            results.next();
            charging[0] = results.getString("NightChargingTime");
            charging[1] = results.getString("DayChargingTime");
            charging[2] = results.getString("DischargePeriod1");
            charging[3] = results.getString("DischargePeriod2");
            return charging;
        } catch (SQLException ex) {
            Error err = new Error();
            err.sendError("Database Error");
            return null;
        }
    }
}
