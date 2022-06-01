package com.aws.demo.controller;

import com.aws.demo.GsonTester;
import com.aws.demo.model.Account;
import com.aws.demo.model.Mission;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class MissionController {

    ArrayList<Mission> list = new ArrayList<Mission>();
    ArrayList<Mission> list_task = new ArrayList<Mission>();
    ArrayList<Mission> list_unfinished = new ArrayList<Mission>();
    ArrayList<Mission> list_running = new ArrayList<Mission>();
    ArrayList<Mission> list_history = new ArrayList<Mission>();

    public void mission() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        list = new ArrayList<Mission>();
        list_task = new ArrayList<Mission>();
        list_unfinished = new ArrayList<Mission>();
        list_running = new ArrayList<Mission>();
        list_history = new ArrayList<Mission>();
        connect_db();
        Gson gson = new Gson();
        for(int counter = 0; counter < list.size(); counter++) {
            if(list.get(counter).getApp_situation().equals("Unfinished")){
                list_unfinished.add(list.get(counter));
            }else if (list.get(counter).getApp_situation().equals("Running")){
                list_running.add(list.get(counter));
            }else{
                list_history.add(list.get(counter));
            }
        }
        list_task.addAll(list_unfinished);
        list_task.addAll(list_running);
    }

    @RequestMapping("/mission/task")
    public String task() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        mission();
        Gson gson = new Gson();
        StringBuilder sb = new StringBuilder();
        for(int counter = 0; counter < list_task.size(); counter++) {
            sb.append(gson.toJson(list_task.get(counter)));
            if (counter !=  list_task.size() - 1){
                sb.append(",");
            }
        }
        list_task = new ArrayList<Mission>();
        return "[" + sb.toString() + "]";
    }

    @RequestMapping("/mission/history")
    public String history() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        mission();
        Gson gson = new Gson();
        StringBuilder sb = new StringBuilder();
        for(int counter = 0; counter < list_history.size(); counter++) {
            sb.append(gson.toJson(list_history.get(counter)));
            if (counter !=  list_history.size() - 1){
                sb.append(",");
            }
        }
        list_history = new ArrayList<Mission>();
        return "[" + sb.toString() + "]";
    }

    @RequestMapping("/mission/change")
    public String ChangeStatus(@RequestBody Mission m) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        change_db();
        Gson gson = new Gson();

        StringBuilder sb = new StringBuilder();
        sb.append(gson.toJson(m));
        return "[" + sb.toString() + "]";
    }

    public void change_db() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://localhost:3316/aws";
        String db_username = "test";
        String db_password = "12345";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, db_username, db_password);
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect the database!", e);
        }
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM `mission_manage`");//搜尋哪個資料表


        while (rs.next() == true){

        }
        connection.close();
    }

    public void connect_db() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://localhost:3316/aws";
        String db_username = "test";
        String db_password = "12345";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, db_username, db_password);
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect the database!", e);
        }
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM `mission_manage`");//搜尋哪個資料表


        while (rs.next() == true){
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