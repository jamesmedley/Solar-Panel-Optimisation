package givenergy_apis;

import org.json.JSONException;
import org.json.JSONObject;
import solarproject.Error;

public class BatteryGivEnergyAPI extends GivEnergyAPI {

    private final JSONObject BATTERYDATAJO; //Holds a JSON data response that contains all the battery data from the system after an API response has been receive 

    public BatteryGivEnergyAPI() {
        /*
        Uses its super class to get a API response JSON. This is stored in the batteryDataJO attribute of this class.
         */
        String batteryData = super.GETAllBatteryData();
        BATTERYDATAJO = GETJSON(batteryData);
    }

    private JSONObject GETJSON(String data) { //Converts a String of data into a JSONObject of data in JSON format that can be more easily accessed
        try {
            JSONObject response = new JSONObject(data);
            return response;
        } catch (JSONException ex) {
            Error err = new Error();
            err.sendError("There was a problem reading data");
            return null;
        }
    }

    public int GETMode() {
        try {
            int mode = BATTERYDATAJO.getInt("mode");
            return mode;
        } catch (JSONException ex) {
            Error err = new Error();
            err.sendError("There was a problem reading data");
            return -1;
        }
    }

    public int GETSelfConsumptionMode() {
        try {
            int selfConsumptionMode = BATTERYDATAJO.getInt("selfConsumptionMode");
            return selfConsumptionMode;
        } catch (JSONException ex) {
            Error err = new Error();
            err.sendError("There was a problem reading data");
            return -1;
        }
    }

    public int GETshallowCharge() {
        try {
            int shallowCharge = BATTERYDATAJO.getInt("shallowCharge");
            return shallowCharge;
        } catch (JSONException ex) {
            Error err = new Error();
            err.sendError("There was a problem reading data");
            return -1;
        }
    }

    public int GETdischargeScheduleStart() {
        try {
            int dischargeStart = BATTERYDATAJO.getInt("dischargeScheduleStart");
            return dischargeStart;
        } catch (JSONException ex) {
            Error err = new Error();
            err.sendError("There was a problem reading data");
            return -1;
        }
    }

    public int GETdischargeScheduleEnd() {
        try {
            int dischargeEnd = BATTERYDATAJO.getInt("dischargeScheduleEnd");
            return dischargeEnd;
        } catch (JSONException ex) {
            Error err = new Error();
            err.sendError("There was a problem reading data");
            return -1;
        }
    }

    public int GETchargeScheduleStart() {
        try {
            int chargeStart = BATTERYDATAJO.getInt("chargeScheduleStart");
            return chargeStart;
        } catch (JSONException ex) {
            Error err = new Error();
            err.sendError("There was a problem reading data");
            return -1;
        }
    }

    public int GETchargeScheduleEnd() {
        try {
            int chargeEnd = BATTERYDATAJO.getInt("chargeScheduleEnd");
            return chargeEnd;
        } catch (JSONException ex) {
            Error err = new Error();
            err.sendError("There was a problem reading data");
            return -1;
        }
    }

    public int GETdischargeDownTo() {
        try {
            int dischargeTo = BATTERYDATAJO.getInt("dischargeDownTo");
            return dischargeTo;
        } catch (JSONException ex) {
            Error err = new Error();
            err.sendError("There was a problem reading data");
            return -1;
        }
    }

    public int GETchargeUpTo() {
        try {
            int chargeTo = BATTERYDATAJO.getInt("chargeUpTo");
            return chargeTo;
        } catch (JSONException ex) {
            Error err = new Error();
            err.sendError("There was a problem reading data");
            return -1;
        }
    }

    public void modeChange(int mode) {
        POSTmode(mode);
    }

    public void charge(int start, int end, int chargeto) {
        POSTchargeBattey(start, end, chargeto);
    }

    public void discharge(int start, int end, int dischargeto, boolean enable) {
        POSTdischargeBattey(enable, start, end, dischargeto);
    }

    public void dischargeflag(int flag) {
        POSTdischargeFlag(flag);
    }

    public void chargeflag(int flag) {
        POSTchargeFlag(flag);
    }

    public void shallowCharge(int charge) {
        POSTshallowCharge(charge);
    }
}
