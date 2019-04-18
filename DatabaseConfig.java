package com.company;
import java.sql.*;

public class DatabaseConfig {
    public static void connectTODatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://35.230.107.13/SalesOffice",
                    "yiffy",
                    "poopy");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * From Customer");
            while (rs.next()) {
                System.out.println(rs.getString(1) + "," + rs.getString(2));
            }
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}