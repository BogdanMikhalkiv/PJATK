package com.example.diplom;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {

    static Connection dbConnection;
    protected static String dbHost = "localhost";
    protected static String dbPort = "3306" ;
    protected static String dbUser = "root";
    protected static String dbPass = "73torrent1";
    protected static String dbName = "DBdyploma" ;

    public static Connection getDbConnection ()
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

}
