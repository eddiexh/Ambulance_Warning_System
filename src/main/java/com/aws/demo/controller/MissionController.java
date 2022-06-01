package com.aws.demo.controller;

import com.aws.demo.model.Mission;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.sql.*;
import java.util.ArrayList;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class MissionController {
    Mission m = new Mission();
    Gson gson = new Gson();
    ArrayList<Mission> list = new ArrayList<>();
    ArrayList<Mission> list_task = new ArrayList<>();
    ArrayList<Mission> list_unfinished = new ArrayList<>();
    ArrayList<Mission> list_running = new ArrayList<>();
    ArrayList<Mission> list_history = new ArrayList<>();

    public void mission() throws SQLException, ClassNotFoundException {
        list = new ArrayList<>();
        list_task = new ArrayList<>();
        list_unfinished = new ArrayList<>();
        list_running = new ArrayList<>();
        list_history = new ArrayList<>();
        connect_db();
        for (Mission mission : list) {
            if (mission.getApp_situation().equals("Unfinished")) {
                list_unfinished.add(mission);
            } else if (mission.getApp_situation().equals("Running")) {
                list_running.add(mission);
            } else {
                list_history.add(mission);
            }
        }
        list_task.addAll(list_unfinished);
        list_task.addAll(list_running);
    }

    @RequestMapping("/mission/task")
    public String task() throws SQLException, ClassNotFoundException {
        mission();
        Gson gson = new Gson();
        StringBuilder sb = new StringBuilder();
        for(int counter = 0; counter < list_task.size(); counter++) {
            sb.append(gson.toJson(list_task.get(counter)));
            if (counter !=  list_task.size() - 1){
                sb.append(",");
            }
        }
        list_task = new ArrayList<>();
        return "[" + sb + "]";
    }

    @RequestMapping("/mission/history")
    public String history() throws SQLException, ClassNotFoundException {
        mission();
        Gson gson = new Gson();
        StringBuilder sb = new StringBuilder();
        for(int counter = 0; counter < list_history.size(); counter++) {
            sb.append(gson.toJson(list_history.get(counter)));
            if (counter !=  list_history.size() - 1){
                sb.append(",");
            }
        }
        list_history = new ArrayList<>();
        return "[" + sb + "]";
    }

    @RequestMapping("/mission/start/{id}")
    public String StartMission(@PathVariable("id") Integer id) throws SQLException, ClassNotFoundException {
        String json = gson.toJson(this.m.Start(id));
        return "[" + json + "]";
    }
    @RequestMapping("/mission/cancel/{id}")
    public String CancelMission(@PathVariable("id") Integer id) throws SQLException, ClassNotFoundException {
        String json = gson.toJson(this.m.Cancel(id));
        return "[" + json + "]";
    }

    @RequestMapping("/mission/complete/{id}")
    public String CompleteMission(@PathVariable("id") Integer id) throws SQLException, ClassNotFoundException {
        String json = gson.toJson(this.m.Complete(id));
        return "[" + json + "]";
    }



    public void connect_db() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/aws";
        String db_username = "test";
        String db_password = "12345";
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, db_username, db_password);
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect the database!", e);
        }
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM `mission_manage`");//搜尋哪個資料表


        while (rs.next()){
            Mission m = new Mission();
            m.setId(rs.getInt("date_mission"));
            m.setCategory(rs.getString("category"));
            m.setLocation(rs.getString("location"));
            m.setCar_id(rs.getString("car_id"));
            m.setApp_situation(rs.getString("app_situation"));
            m.setNote(rs.getString("note"));
            list.add(m);
        }
        connection.close();
    }
}