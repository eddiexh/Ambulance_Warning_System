package com.aws.demo.controller;

import com.aws.demo.model.DBTitle;
import com.aws.demo.model.Mission;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.sql.*;
import java.util.ArrayList;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/mission")
@RestController
public class MissionController {
    Gson gson = new Gson();
    ArrayList<Mission> list;
    ArrayList<Mission> list_task;
    ArrayList<Mission> list_unfinished;
    ArrayList<Mission> list_running;
    ArrayList<Mission> list_history;

    @RequestMapping("/start/{id}")
    public String StartMission(@PathVariable("id") Integer id) throws SQLException, ClassNotFoundException {
        Mission m = new Mission();
        String json = gson.toJson(m.Start(id));
        return "[" + json + "]";
    }
    @RequestMapping("/cancel/{id}")
    public String CancelMission(@PathVariable("id") Integer id) throws SQLException, ClassNotFoundException {
        Mission m = new Mission();
        String json = gson.toJson(m.Cancel(id));
        return "[" + json + "]";
    }

    @RequestMapping("/complete/{id}")
    public String CompleteMission(@PathVariable("id") Integer id) throws SQLException, ClassNotFoundException {
        Mission m = new Mission();
        String json = gson.toJson(m.Complete(id));
        return "[" + json + "]";
    }

    @RequestMapping("/task")
    public String task() throws SQLException, ClassNotFoundException {
        GetMissionInfo();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list_task.size(); i++) {
            sb.append(gson.toJson(list_task.get(i)));
            if (i !=  list_task.size() - 1){
                sb.append(",");
            }
        }
        list_task = new ArrayList<>();
        return "[" + sb + "]";
    }

    @RequestMapping("/history")
    public String history() throws SQLException, ClassNotFoundException {
        GetMissionInfo();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list_history.size(); i++) {
            sb.append(gson.toJson(list_history.get(i)));
            if (i !=  list_history.size() - 1){
                sb.append(",");
            }
        }
        list_history = new ArrayList<>();
        return "[" + sb + "]";
    }

    public void GetMissionInfo() throws SQLException, ClassNotFoundException {
        DBTitle t = new DBTitle();
        list = new ArrayList<>();
        list_task = new ArrayList<>();
        list_unfinished = new ArrayList<>();
        list_running = new ArrayList<>();
        list_history = new ArrayList<>();

        ResultSet rs = DatabaseManager.view_table("mission_manage");

        while (rs.next()){
            Mission m = new Mission();
            m.setId(rs.getInt(t.m1));
            m.setCategory(rs.getString((t.m2)));
            m.setLocation(rs.getString((t.m3)));
            m.setCar_id(rs.getString((t.m4)));
            m.setApp_situation(rs.getString((t.m5)));
            m.setNote(rs.getString((t.m6)));
            list.add(m);
        }

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
}