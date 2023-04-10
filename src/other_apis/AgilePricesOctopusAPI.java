package other_apis;

import optimisation.Prices;
import org.json.JSONException;
import org.json.JSONObject;
import solarproject.Error;

public class AgilePricesOctopusAPI extends OctopusAPI {

    private JSONObject GETJSON(String data) {
        try {
            JSONObject response = new JSONObject(data);
            return response;
        } catch (JSONException ex) {
            Error err = new Error();
            err.sendError("There was a problem collecting Agile Prices");
            return null;
        }
    }

    public String[][] GETPrices(String start, String end) { //Uses the Octopus API methods from the OctopusAPI class it inherits from to get the agile prices for an inputted day.
        try {
            String data = GETAgilePrices(start, end);
            JSONObject jo = GETJSON(data);//turns a String of data into JSONObject
            int count = jo.getInt("count");
            String[][] prices = new String[count][2];
            for (int i = 0; i < count; i++) { //loop formats each item and adds to an array that will be returned from the method
                prices[i][0] = Double.toString(jo.getJSONArray("results").getJSONObject(i).getDouble("value_inc_vat"));
                prices[i][1] = jo.getJSONArray("results").getJSONObject(i).getString("valid_from");
            }
            Prices p = new Prices(prices);
            p.findPrices(true, 5);
            return prices;
        } catch (JSONException ex) {
            Error err = new Error();
            err.sendError("There was a problem collecting forecasts");
            return null;
        }
    }
}
