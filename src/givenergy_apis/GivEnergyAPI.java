package givenergy_apis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import solarproject.Error;

public class GivEnergyAPI {

    private String jSessionID; //String representation of the session cookie used by the GivEnergyAPI used to retain the connection to the API

    public String getSessionID() {
        return this.jSessionID;
    }

    private String returnDataString(HttpURLConnection connection) { //Uses an HttpURLConnection object to get the API response from any GivEnergy API endpoint and returns it as a string.
        try {
            BufferedReader reader;
            String line;
            StringBuilder responseContent = new StringBuilder();
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
            String response = responseContent.toString();
            connection.disconnect();
            return response;
        } catch (IOException ex) {
            Error err = new Error();
            err.sendError("There was a problem with the connection to GivEnergy.");
            return null;
        }
    }

    public void login() { //Method that is called when the program needs to connect to the GivEnergy API. It also sets a value to the jsessionid attribute so the same connection can be retained
        try {
            HttpURLConnection connection;
            URL url = new URL("https://www.givenergy.cloud/GivManage/api/login?account=<account>&password=<password>"); //uses login endpoint to login to the API 
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            jSessionID = findCookies(connection); //sets the cookie attribute so that the session can be retained
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
        } catch (IOException ex) {
            Error err = new Error();
            err.sendError("There was a problem with the connection to GivEnergy.");
        }
    }

    private String findCookies(HttpURLConnection connection) { //Gets the cookie string from the API connection so that the program can remain logged into the API with cookie retention
        java.net.CookieManager msCookieManager = new java.net.CookieManager();
        Map<String, List<String>> headerFields = connection.getHeaderFields();
        List<String> cookiesHeader = headerFields.get("Set-Cookie");
        if (cookiesHeader != null) {
            for (String cookie : cookiesHeader) {
                msCookieManager.getCookieStore().add(null, HttpCookie.parse(cookie).get(0));
            }
        }
        String cookie = (cookiesHeader.toString().subSequence(12, 44)).toString();
        return cookie;
    }

    public String GETInvData(String date) {
        try {
            HttpURLConnection connection;
            URL url = new URL("https://www.givenergy.cloud/GivManage/invData/<inverter number>/" + "" + date + "" + "+?page=1&rows=1000"); //uses inverter data endpoint to get data about inverter
            connection = (HttpURLConnection) url.openConnection();
            connection = GETsetup(connection);
            String response = returnDataString(connection);
            return response;
        } catch (IOException ex) {
            Error err = new Error();
            err.sendError("There was a problem with the connection to GivEnergy.");
            return null;
        }
    }

    public String GETPlantSummary() {
        try {
            HttpURLConnection connection;
            URL url = new URL("https://www.givenergy.cloud/GivManage/api/plant/getPlantSummary?plantId=<plant id>"); //uses plant summary endpoint to get data about the plant (whole solar panel system)
            connection = (HttpURLConnection) url.openConnection();
            connection = GETsetup(connection);
            String response = returnDataString(connection);
            return response;
        } catch (IOException ex) {
            Error err = new Error();
            err.sendError("There was a problem with the connection to GivEnergy.");
            return null;
        }
    }

    public String GETPlantChartByMonth(String year, String month) {
        try {
            HttpURLConnection connection;
            URL url = new URL("https://www.givenergy.cloud/GivManage/api/plantChart/monthColumn?plantId=<plant id>&year=" + year + "&month=" + month); //uses plantChart endpoint to get data about the plant by month
            connection = (HttpURLConnection) url.openConnection();
            connection = GETsetup(connection);
            String response = returnDataString(connection);
            return response;
        } catch (IOException ex) {
            Error err = new Error();
            err.sendError("There was a problem with the connection to GivEnergy.");
            return null;
        }
    }

    public String GETPlantChartByYear(String year) {
        try {
            HttpURLConnection connection;
            URL url = new URL("https://www.givenergy.cloud/GivManage/api//plantChart/yearColumn?plantId=<plant id>&year=" + year); //uses plantChart endpoint to get data about the plant by year
            connection = (HttpURLConnection) url.openConnection();
            connection = GETsetup(connection);
            String response = returnDataString(connection);
            return response;
        } catch (IOException ex) {
            Error err = new Error();
            err.sendError("There was a problem with the connection to GivEnergy.");
            return null;
        }
    }

    public String GETInverterChartByDay(String date) {
        try {
            HttpURLConnection connection;
            URL url = new URL("https://www.givenergy.cloud/GivManage/api/invChart/dayMultiLine?serialNum=<serial number>&dateText=" + date); //uses inverter data endpoint to get data about inverter by day
            connection = (HttpURLConnection) url.openConnection();
            connection = GETsetup(connection);
            String response = returnDataString(connection);
            return response;
        } catch (IOException ex) {
            Error err = new Error();
            err.sendError("There was a problem with the connection to GivEnergy.");
            return null;
        }
    }

    public String GETAllBatteryData() {
        try {
            HttpURLConnection connection;
            URL url = new URL("https://api.givenergy.cloud/batteryData/all");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("Authorization", "<api key>"); //uses the battery data endpoint to get data about battery
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            String response = returnDataString(connection);
            return response;
        } catch (IOException ex) {
            Error err = new Error();
            err.sendError("There was a problem with the connection to GivEnergy.");
            return null;
        }
    }

    public String POSTchargeBattey(int start, int end, int chargeto) {
        try {
            HttpURLConnection connection;
            String json = "{\r\n    \"enable\": true,\r\n    \"start\": \"" + start + "\",\r\n    \"finish\": \"" + end + "\",\r\n    \"chargeToPercent\": \"" + chargeto + "\"\r\n}"; //json formed to pass into POST API request
            URL url = new URL("https://api.givenergy.cloud/chargeBattery");
            connection = (HttpURLConnection) url.openConnection();
            connection = POSTsetup(connection, json);
            String response = returnDataString(connection);
            return response;
        } catch (IOException ex) {
            Error err = new Error();
            err.sendError("There was a problem with the connection to GivEnergy.");
            return null;
        }
    }

    public String POSTdischargeBattey(boolean enable, int start, int end, int dischargeto) {
        try {
            HttpURLConnection connection;
            String json = "{\r\n    \"" + enable + "\": true,\r\n    \"start\": \"" + start + "\",\r\n    \"finish\": \"" + end + "\",\r\n    \"dischargeToPercent\": \"" + dischargeto + "\"\r\n}"; //json formed to pass into POST API request
            URL url = new URL("https://api.givenergy.cloud/dischargeBattery");
            connection = (HttpURLConnection) url.openConnection();
            connection = POSTsetup(connection, json);
            String response = returnDataString(connection);
            return response;
        } catch (IOException ex) {
            Error err = new Error();
            err.sendError("There was a problem with the connection to GivEnergy.");
            return null;
        }
    }

    public String POSTmode(int mode) {
        try {
            HttpURLConnection connection;
            String json = "{\r\n    \"mode\": \"" + mode + "\"\r\n}"; //json formed to pass into POST API request
            URL url = new URL("https://api.givenergy.cloud/mode");
            connection = (HttpURLConnection) url.openConnection();
            connection = POSTsetup(connection, json);
            String response = returnDataString(connection);
            return response;
        } catch (IOException ex) {
            Error err = new Error();
            err.sendError("There was a problem with the connection to GivEnergy.");
            return null;
        }
    }

    public String POSTchargeFlag(int flag) {
        try {
            HttpURLConnection connection;
            String json = "{\r\n    \"value\": \"" + flag + "\"\r\n}"; //json formed to pass into POST API request
            URL url = new URL("https://api.givenergy.cloud/registers/chargeFlag");
            connection = (HttpURLConnection) url.openConnection();
            connection = POSTsetup(connection, json);
            String response = returnDataString(connection);
            return response;
        } catch (IOException ex) {
            Error err = new Error();
            err.sendError("There was a problem with the connection to GivEnergy.");
            return null;
        }
    }

    public String POSTdischargeFlag(int flag) {
        try {
            HttpURLConnection connection;
            String json = "{\r\n    \"value\": \"" + flag + "\"\r\n}"; //json formed to pass into POST API request
            URL url = new URL("https://api.givenergy.cloud/registers/dischargeFlag");
            connection = (HttpURLConnection) url.openConnection();
            connection = POSTsetup(connection, json);
            String response = returnDataString(connection);
            return response;
        } catch (IOException ex) {
            Error err = new Error();
            err.sendError("There was a problem with the connection to GivEnergy.");
            return null;
        }
    }

    public String POSTshallowCharge(int charge) {
        try {
            HttpURLConnection connection;
            String json = "{\r\n    \"value\": \"" + charge + "\"\r\n}"; //json formed to pass into POST API request
            URL url = new URL("https://api.givenergy.cloud/registers/shallowValue");
            connection = (HttpURLConnection) url.openConnection();
            connection = POSTsetup(connection, json);
            String response = returnDataString(connection);
            return response;
        } catch (IOException ex) {
            Error err = new Error();
            err.sendError("There was a problem with the connection to GivEnergy.");
            return null;
        }
    }

    private HttpURLConnection POSTsetup(HttpURLConnection connection, String json) { //Method that sets up a connection that can send data in JSON format to a POST endpoint so that the program is able to send data to the GivEnergy system.
        try {
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.addRequestProperty("Authorization", "<api key"); //sets authorization so POST request is accepted
            connection.addRequestProperty("Accept", "application/json"); //sets data format
            connection.addRequestProperty("Content-Type", "application/json; charset=UTF-8");
            OutputStream os = connection.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();
            return connection;
        } catch (UnsupportedEncodingException ex) {
            Error err = new Error();
            err.sendError("There was a problem with the connection to GivEnergy.");
            return null;
        } catch (IOException ex) {
            Error err = new Error();
            err.sendError("There was a problem with the connection to GivEnergy.");
            return null;
        }
    }

    private HttpURLConnection GETsetup(HttpURLConnection connection) { //Method that sets up the connection to a GET request
        try {
            connection.setRequestMethod("POST");
            connection.addRequestProperty("Cookie", "JSESSIONID=" + jSessionID);
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            return connection;
        } catch (ProtocolException ex) {
            Error err = new Error();
            err.sendError("There was a problem with the connection to GivEnergy.");
            return null;
        }
    }
}
