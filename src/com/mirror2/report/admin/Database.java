package com.mirror2.report.admin;

/**
 * Created by ayatullah213574 on 29/07/2015.
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    public static void main(String[] args) {
        System.out.println(getConnection());
    }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/erp_db","root", "");
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->" + ex.getMessage());
            return null;
        }
    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }
}
