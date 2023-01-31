package databases;

import java.sql.Connection;

public class DBCredentials {

    private final String dbName; //String object that holds the DB name

    private final String username; //String object that holds the DB username

    private final String password; //String object that holds the DB password

    private Connection connection; //Connection object that holds the connection to the remote database

    DBCredentials(String dbName, String username, String password) { //initialises final values when object is created
        this.dbName = dbName;
        this.username = username;
        this.password = password;
    }

    public Connection getConnnection() { //A getter for the Connection object
        return this.connection;
    }

    public String getDBName() { //A getter for the database name
        return this.dbName;
    }

    public String getUsername() { //A getter for the database username
        return this.username;
    }

    public String getPassword() { //A getter for the database password
        return this.password;
    }

    public void setConnnection(Connection connection) { //A setter for the Connection object
        this.connection = connection;
    }
}
