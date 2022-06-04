package com.aws.demo.controller;

import java.sql.*;

public class DatabaseManager {
    public static Statement connect_db() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/aws";
        String db_username = "test";
        String db_password = "12345";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, db_username, db_password);
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect the database!", e);
        }
        return connection.createStatement();
    }

    public static ResultSet view_id(String table, Integer id) throws SQLException, ClassNotFoundException {
        Statement stmt = connect_db();
        return stmt.executeQuery("SELECT * FROM `" + table + "`where date_mission =" + id);//搜尋哪個資料表;
    }

    public static ResultSet view_table(String table) throws SQLException, ClassNotFoundException {
        Statement stmt = connect_db();
        return stmt.executeQuery("SELECT * FROM `" + table + "`");//搜尋哪個資料表
    }

    public static void update_id(String table,String title,String title2, Integer id, String s) throws ClassNotFoundException, SQLException {
        Statement stmt = connect_db();
        stmt.execute("update `" + table + "` set " + title +"='" + s + "' where " + title2 + " =" + id);
    }
}

