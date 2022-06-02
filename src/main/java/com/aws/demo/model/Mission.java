package com.aws.demo.model;

import com.aws.demo.controller.ArduinoController;
import com.aws.demo.controller.DatabaseManager;

import java.sql.*;

public class Mission {
    Mission m;
    public Mission Start(Integer id) throws SQLException, ClassNotFoundException {
        DatabaseManager.update_id("mission_manage","app_situation","date_mission",id, "Running");
        ResultSet rs = DatabaseManager.view_id("mission_manage",id);

        m = new Mission();
        while (rs.next()){
            m.setId(rs.getInt("date_mission"));
            m.setCategory(rs.getString("category"));
            m.setLocation(rs.getString("location"));
            m.setCar_id(rs.getString("car_id"));
            m.setApp_situation(rs.getString("app_situation"));
            m.setNote(rs.getString("note"));
        }
        ArduinoController client = new ArduinoController();
        client.run();
        return m;
    }

    public Mission Cancel(Integer id) throws SQLException, ClassNotFoundException {
        DatabaseManager.update_id("mission_manage","app_situation","date_mission",id, "Cancel");
        ResultSet rs = DatabaseManager.view_id("mission_manage",id);

        m = new Mission();
        while (rs.next()){
            m.setId(rs.getInt("date_mission"));
            m.setCategory(rs.getString("category"));
            m.setLocation(rs.getString("location"));
            m.setCar_id(rs.getString("car_id"));
            m.setApp_situation(rs.getString("app_situation"));
            m.setNote(rs.getString("note"));
        }
        return m;
    }

    public Mission Complete(Integer id) throws SQLException, ClassNotFoundException {
        DatabaseManager.update_id("mission_manage","app_situation","date_mission",id, "Complete");
        ResultSet rs = DatabaseManager.view_id("mission_manage",id);

        m = new Mission();
        while (rs.next()){
            m.setId(rs.getInt("date_mission"));
            m.setCategory(rs.getString("category"));
            m.setLocation(rs.getString("location"));
            m.setCar_id(rs.getString("car_id"));
            m.setApp_situation(rs.getString("app_situation"));
            m.setNote(rs.getString("note"));
        }
        return m;
    }

    private Integer id;
    private String date = "2022/06/10";
    private String category;
    private String location;
    private String car_id;
    private String app_situation;
    private String note;

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategory() {
        return category;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocation() {
        return location;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }
    public String getCar_id() {
        return car_id;
    }

    public void setApp_situation(String app_situation) {
        this.app_situation = app_situation;
    }
    public String getApp_situation() {
        return app_situation;
    }

    public void setNote(String note) {
        this.note = note;
    }
    public String getNote() {
        return note;
    }
}
