package databases;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private DBCredentials dbCreds; //DBCredentials object that holds all the values needed for the connection to the remote MySQL database

    public void setDBCredentials(DBCredentials dbCreds) {
        this.dbCreds = dbCreds;
    }

    public void connect() { //Connects to the remote DB using the credentials a
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println(dbCreds.getDBName()+" "+ dbCreds.getUsername()+" "+ dbCreds.getPassword());
            dbCreds.setConnnection(DriverManager.getConnection(<db> + dbCreds.getDBName(), dbCreds.getUsername(), dbCreds.getPassword()));
        } catch (Exception e) {
            new Error("Could not connect to database: " + e);
        }
    }

    public void disconnect() { //Closes the connection to the database
        try {
            dbCreds.getConnnection().close();
        } catch (SQLException ex) {
            new Error("Database Error");
        }
    }

    public void perform(String sqlquery) { //Executes an SQL query on the database 
        try {
            String query = sqlquery;
            if (dbCreds.getConnnection().isClosed()) { //Removes chance that error could occur if the connection happened to be closed
                connect();
            }
            PreparedStatement ps = dbCreds.getConnnection().prepareStatement(query);
            ps.executeUpdate();
            disconnect(); //closes the connection to ensure that there is never more than one connection to the database
        } catch (SQLException ex) {
            new Error("Database Error");
        }
    }

    public ResultSet getResponse(String query) { //If the query is something that will return data then this method is used to return a ResultSet object where the data can be found
        try {
            if (dbCreds.getConnnection().isClosed()) { //Removes chance that error could occur if the connection happened to be closed
                connect();
            }
            Statement statement;
            ResultSet results;
            String queryStr = query;
            statement = dbCreds.getConnnection().createStatement();
            results = statement.executeQuery(queryStr);
            disconnect(); //Removes chance that error could occur if the connection happened to be closed
            return results;
        } catch (SQLException ex) {
            new Error("Database Error");
            return null;
        }
    }
}
