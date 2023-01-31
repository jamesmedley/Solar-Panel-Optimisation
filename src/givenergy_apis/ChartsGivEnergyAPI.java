package givenergy_apis;

import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;
import solarproject.DateStrings;
import solarproject.Dictionary;

public class ChartsGivEnergyAPI extends GivEnergyAPI {

    private final DateStrings DATESTRS = new DateStrings(); //An object that holds variables of dates such as year, month and a date

    private String[] timeVals; //An array that holds String representations of times that are used when generating graphs of power or energy against time.

    public ChartsGivEnergyAPI(String year, String month, String date) {
        if (super.getSessionID() == null) {
            super.login();
        }
        DATESTRS.setYear(year);
        DATESTRS.setMonth(month);
        DATESTRS.setDate(date);
    }

    public DateStrings getDateStrs() {
        return this.DATESTRS;
    }

    public String[] getTimevals() {
        return this.timeVals;
    }

    private JSONObject GETJSON(String data) {
        JSONObject response;
        try {
            response = new JSONObject(data); //turns a string representation of JSON into a JSONObject
            return response;
        } catch (JSONException ex) {
            new Error("There was a problem reading data.");
            return null;
        }
    }

    public double[][] GETEnergyByMonth(String year, String month) { //Uses a GivEnergyAPI response to return a set of data representing different energy uses such as generation and demand over the course of a month during that year.
        String response = GETPlantChartByMonth(year, month);
        JSONObject chartJO = GETJSON(response); //turns response into JSONObject
        String[] names = new String[]{"day", "exportEnergy", "DischargeEnergyToday", "ChargeEnergyToday", "importEnergy", "consumptionEnergy", "InvImportEnergy", "InvExportEnergy", "pvEnergy"};
        double[][] chartData = new double[9][31];
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 31; i++) {
                try {
                    chartData[j][i] = chartJO.getJSONArray("data").getJSONObject(i).getDouble(names[j]); //fetches the correct value of data from the API response from chartJO using the data keys stored in names
                } catch (JSONException ex) {
                    new Error("There was a problem reading data.");
                }
            }
        }
        return chartData;
    }

    public String[][] GETPVpowerByDay(String date) { //Uses a GivEnergy API response to return a set of data about the power generation by the solar panels for a specific date.
        try {
            String response = GETInverterChartByDay(date);
            JSONObject chartJO = GETJSON(response);
            int length = chartJO.getJSONArray("data").length();
            String[][] chartData = new String[length][2];
            for (int i = 0; i < length; i++) {
                chartData[i][0] = Double.toString(chartJO.getJSONArray("data").getJSONObject(i).getDouble("ppv"));
                chartData[i][1] = chartJO.getJSONArray("data").getJSONObject(i).getString("time");
            }
            return chartData;
        } catch (JSONException ex) {
            new Error("There was a problem reading data.");
            return null;
        }
    }

    public double[][] GETEnergyByYear(String year) { //Uses a GivEnergyAPI response to return sets of data representing various energy uses such as generation and demand over the course of each month over the given year.
        String response = GETPlantChartByYear(year);
        JSONObject chartJO = GETJSON(response);
        String[] names = new String[]{"month", "exportEnergy", "DischargeEnergyToday", "ChargeEnergyToday", "importEnergy", "consumptionEnergy", "InvImportEnergy", "InvExportEnergy", "pvEnergy"};
        double[][] chartData = new double[9][12];
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 12; i++) {
                try {
                    chartData[j][i] = chartJO.getJSONArray("data").getJSONObject(i).getDouble(names[j]); //fetches the correct value of data from the API response from chartJO using the data keys stored in names
                } catch (JSONException ex) {
                    new Error("There was a problem reading data.");
                }
            }
        }
        return chartData;
    }

    public Dictionary inverterValuesDictionary(String date) { //Uses the Dictionary class to create a new Dictionary and store array objects against a key. This is an effective way to return a single object that holds data sets used in graphs that show multiple sets of data on the same axis.
        try {
            Dictionary dict = new Dictionary();
            String response = GETInverterChartByDay(date);
            JSONObject chartJO = GETJSON(response);
            timeVals = new String[chartJO.getJSONArray("data").length()];
            ArrayList pacExportList = new ArrayList(), pacImportList = new ArrayList(), ppvList = new ArrayList(), loadPowerList = new ArrayList(), batPowerActualList = new ArrayList();
            for (int j = 0; j < chartJO.getJSONArray("data").length(); j++) {
                timeVals[j] = chartJO.getJSONArray("data").getJSONObject(j).getString("time");
                pacExportList.add(chartJO.getJSONArray("data").getJSONObject(j).getDouble("pacExport"));
                pacImportList.add(chartJO.getJSONArray("data").getJSONObject(j).getDouble("pacImport"));
                ppvList.add(chartJO.getJSONArray("data").getJSONObject(j).getDouble("ppv"));
                loadPowerList.add(chartJO.getJSONArray("data").getJSONObject(j).getDouble("loadPower"));
                batPowerActualList.add(chartJO.getJSONArray("data").getJSONObject(j).getDouble("batPowerActual"));
            }
            dict.add("pacExport", Arrays.stream(pacExportList.toArray()).mapToDouble(num -> Double.parseDouble(num.toString())).toArray());
            dict.add("pacImport", Arrays.stream(pacImportList.toArray()).mapToDouble(num -> Double.parseDouble(num.toString())).toArray());
            dict.add("ppv", Arrays.stream(ppvList.toArray()).mapToDouble(num -> Double.parseDouble(num.toString())).toArray());
            dict.add("loadpower", Arrays.stream(loadPowerList.toArray()).mapToDouble(num -> Double.parseDouble(num.toString())).toArray());
            dict.add("batpoweractual", Arrays.stream(batPowerActualList.toArray()).mapToDouble(num -> Double.parseDouble(num.toString())).toArray());
            return dict;
        } catch (JSONException ex) {
            new Error("There was a problem reading data.");
            return null;
        }
    }
}
