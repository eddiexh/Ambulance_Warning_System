package com.aws.demo.model;

import com.aws.demo.controller.DatabaseManager;

import java.sql.*;

public class Mission {
    DBTitle t = new DBTitle();
    private Integer id;
    private final String date = "2022/06/10";
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

    public Mission Start(Integer id) throws SQLException, ClassNotFoundException {
        ResultSet rs = DatabaseManager.Update_id("mission_manage","app_situation","date_mission",id, "Running");

        TrafficLight tl = new TrafficLight();
        //tl.ChangeLight(Return(rs,t), tl, true);
        tl.Run();
        return Return(rs,t);
    }

    public Mission Cancel(Integer id) throws SQLException, ClassNotFoundException {
        ResultSet rs = DatabaseManager.Update_id("mission_manage","app_situation","date_mission",id, "Cancel");
        return Return(rs,t);
    }

    public Mission Complete(Integer id) throws SQLException, ClassNotFoundException {
        ResultSet rs = DatabaseManager.Update_id("mission_manage","app_situation","date_mission",id, "Complete");
        return Return(rs,t);
    }

    public Mission Return(ResultSet rs, DBTitle t) throws SQLException {
        Mission m = new Mission();
        while (rs.next()){
            m.setId(rs.getInt(t.m1));
            m.setCategory(rs.getString((t.m2)));
            m.setLocation(rs.getString((t.m3)));
            m.setCar_id(rs.getString((t.m4)));
            m.setApp_situation(rs.getString((t.m5)));
            m.setNote(rs.getString((t.m6)));
        }
        return m;
    }
}
