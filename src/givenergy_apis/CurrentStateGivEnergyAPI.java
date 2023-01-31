package givenergy_apis;

import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public class CurrentStateGivEnergyAPI extends GivEnergyAPI {

    private final JSONObject PLANTSUMMARYJO; //Holds a JSONObject object of an API response that is used in the rest of the class

    public CurrentStateGivEnergyAPI() throws IOException, JSONException {//method is called from within a try-catch so throws is acceptable
        /*
        Uses its super class to get an API response for the current live values of the system. 
        Also ensures that the program is logged into the API and if not uses the login() method to do so.
         */
        if (super.getSessionID() == null) {
            super.login();
        }
        String plantSummary = super.GETPlantSummary();
        this.PLANTSUMMARYJO = GETJSON(plantSummary);
    }

    private JSONObject GETJSON(String data) { //Converts a String of data into a JSONObject of data in JSON format that can be more easily accessed
        try {
            JSONObject response = new JSONObject(data);
            return response;
        } catch (JSONException ex) {
            new Error("There was a problem reading data.");
            return null;
        }
    }

    public int GETBatterySOC() {
        try {
            int soc = PLANTSUMMARYJO.getInt("soc");
            return soc;
        } catch (JSONException ex) {
            new Error("There was a problem reading data");
            return -1;
        }
    }

    public String GETBatteryPower() {
        try {
            String batPower = PLANTSUMMARYJO.getString("batPower");
            return batPower;
        } catch (JSONException ex) {
            new Error("There was a problem reading data");
            return null;
        }
    }

    public String GETGridPower() {
        try {
            String gridpower = PLANTSUMMARYJO.getString("gridPower");
            return gridpower;
        } catch (JSONException ex) {
            new Error("There was a problem reading data");
            return null;
        }
    }

    public String GETPVPower() {
        try {
            String pvpower = PLANTSUMMARYJO.getString("pvPower");
            return pvpower;
        } catch (JSONException ex) {
            new Error("There was a problem reading data");
            return null;
        }
    }

    public int GETLoadPower() {
        try {
            int load = PLANTSUMMARYJO.getInt("loadPower");
            return load;
        } catch (JSONException ex) {
            new Error("There was a problem reading data");
            return -1;
        }
    }

    public String GETCO2Today() {
        try {
            String co2 = PLANTSUMMARYJO.getString("todayCo2Saved");
            return co2;
        } catch (JSONException ex) {
            new Error("There was a problem reading data");
            return null;
        }
    }

    public String GETCO2Month() {
        try {
            String co2 = PLANTSUMMARYJO.getString("monthCo2Saved");
            return co2;
        } catch (JSONException ex) {
            new Error("There was a problem reading data");
            return null;
        }
    }

    public String GETCO2total() {
        try {
            String co2 = PLANTSUMMARYJO.getString("totalCo2Saved");
            return co2;
        } catch (JSONException ex) {
            new Error("There was a problem reading data");
            return null;
        }
    }

    public int GETPowerInv() {
        try {
            int powerInv = PLANTSUMMARYJO.getInt("pInv");
            return powerInv;
        } catch (JSONException ex) {
            new Error("There was a problem reading data");
            return -1;
        }
    }
}
