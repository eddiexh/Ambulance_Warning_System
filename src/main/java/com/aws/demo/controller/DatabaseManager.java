package com.aws.demo.controller;

import java.sql.*;

public class DatabaseManager {
    public static ResultSet View_id(String table, String title, Integer id) throws SQLException, ClassNotFoundException {
        Statement stmt = Connect_db();
        return stmt.executeQuery("SELECT * FROM `" + table + "` where " + title + " = " + id);//搜尋哪個資料表;
    }

    public static ResultSet View_table(String table) throws SQLException, ClassNotFoundException {
        Statement stmt = Connect_db();
        return stmt.executeQuery("SELECT * FROM `" + table + "`");//搜尋哪個資料表
    }

    public static ResultSet Update_id(String table, String title, String title2, Integer id, String s) throws ClassNotFoundException, SQLException {
        Statement stmt = Connect_db();
        stmt.execute("update `" + table + "` set " + title +"='" + s + "' where " + title2 + " = " + id);
        return stmt.executeQuery("SELECT * FROM `" + table + "` where " + title2 + " = " + id);//搜尋哪個資料表;
    }

    public static Statement Connect_db() throws ClassNotFoundException, SQLException {
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
}

