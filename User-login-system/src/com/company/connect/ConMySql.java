package com.company.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConMySql {
    private Connection connection;
    public ConMySql(){
        String jdbcURL="jdbc:mysql://localhost:3306/user_management";
        String sqlusername="root";
        String sqlpass="123456";
        try {
            connection = DriverManager.getConnection(jdbcURL,sqlusername,sqlpass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }


}
