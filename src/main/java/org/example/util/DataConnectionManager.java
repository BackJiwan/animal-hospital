package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnectionManager {
    private static DataConnectionManager instance;
    private Connection connection;
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String username = "testuser";
    private String password = "test1234";

    private DataConnectionManager() {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Database Connection Creation Failed : " + e.getMessage());
        }
    }

    public static DataConnectionManager getInstance() {
        if (instance == null) {
            instance = new DataConnectionManager();
        } else {
            try {
                if (instance.getConnection().isClosed()) {
                    instance = new DataConnectionManager();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}