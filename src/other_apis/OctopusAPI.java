package other_apis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Base64;

public class OctopusAPI {

    private String returnDataString(HttpURLConnection connection) { //Establishes a connection with the endpoint and forms the response using an input stream. This data is then returned and it will be of JSON format and returned as a String. 
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
            new Error("There was a problem reading data from Octopus API");
            return null;
        }
    }

    private HttpURLConnection GETsetup(HttpURLConnection connection) { //Sets up a GET API request and returns the HttpURLConnection object
        try {
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(10000);
            return connection;
        } catch (ProtocolException ex) {
            new Error("There was a problem reading data from Octopus API");
            return null;
        }
    }

    public String GETAgilePrices(String periodStart, String periodEnd) { //Uses the Octopus API to get the agile prices for a given time period and returns a string representation of a JSON response
        try {
            HttpURLConnection connection;
            URL url = new URL("https://api.octopus.energy/v1/products/AGILE-18-02-21/electricity-tariffs/E-1R-AGILE-18-02-21-H/standard-unit-rates/?period_from=" + periodStart + "&period_to=" + periodEnd);
            connection = (HttpURLConnection) url.openConnection();
            connection = GETsetup(connection);
            String response = returnDataString(connection);
            return response;
        } catch (IOException ex) {
            new Error("There was a problem reading data from Octopus API");
            return null;
        }
    }

    public String GETAccounts() {
        try {
            HttpURLConnection connection;
            String encoding = Base64.getEncoder().encodeToString("<api key>".getBytes("UTF-8"));
            URL url = new URL("https://api.octopus.energy/v1/accounts/<account number>/");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", "Basic " + encoding);
            connection = GETsetup(connection);
            String response = returnDataString(connection);
            return response;
        } catch (IOException ex) {
            new Error("There was a problem reading data from Octopus API");
        }
        return null;
    }
}
