package com.aws.demo.model;

import com.aws.demo.controller.DatabaseManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;

//extend green light
//change rad light

//recover light

public class TrafficLight {
    private Integer id;
    private double x;
    private double y;
    private String Status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }


    public void run() {
            try {
                int serverPort = 8888;
                InetAddress host = InetAddress.getByName("172.20.10.7");
                System.out.println("Connecting to server on port " + serverPort);

                Socket socket = new Socket(host,serverPort);
                //Socket socket = new Socket("127.0.0.1", serverPort);
                if(socket.isConnected()) {
                    System.out.println("Just connected to " + socket.getRemoteSocketAddress());
                    PrintWriter toServer =
                            new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader fromServer =
                            new BufferedReader(
                                    new InputStreamReader(socket.getInputStream()));
                    toServer.println("Hello from " + socket.getLocalSocketAddress());
                }
            }
            catch(UnknownHostException ex) {
                ex.printStackTrace();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }


    public double getDistance() {
        Double distance = 500.00;
        for(int i = 0;i<5;i++){
            distance += i;
        }
        return distance;
    }

    private int buffertime;
    private double X;
    private double Y;

    private String pre_status;

    public String getPre_status() {
        return pre_status;
    }

    public void setPre_status(String pre_status) {
        this.pre_status = pre_status;
    }


    public int getBuffertime() {
        return buffertime;
    }

    public void setBuffertime(int buffertime) {
        this.buffertime = buffertime;
    }


    public TrafficLight getTrafficLightInfo(DBTitle t) throws SQLException, ClassNotFoundException {
        ResultSet rs =  DatabaseManager.view_id("traffic_light",1);
        TrafficLight tl = new TrafficLight();
        while (rs.next()){
            tl.setId(rs.getInt(t.t1));
            tl.setX(rs.getInt(t.t2));
            tl.setY(rs.getInt(t.t3));
            tl.setStatus(rs.getString(t.t4));
        }
        return tl;
    }

    public void ChangeLight(Mission m, TrafficLight tl, boolean on_main_lane) {
        while (m.getApp_situation() == "start") {
            if (tl.getDistance() <= 1000 && on_main_lane) {
                tl.setStatus("green");
                this.addSpecialSign();
                //wait ambulance
                buffer(m, tl);
            } else {
                tl.setStatus("red");
                //wait ambulance
                buffer(m, tl);
            }
        }
    }

    public void buffer(Mission m,TrafficLight tl) {
        tl.setBuffertime(10);
        if (tl.getDistance() == 0 && m.getApp_situation() != "cancel") {
            for (int i = tl.getBuffertime(); i > 0; i--) {
                tl.setStatus("yellow");
            }
        }else{
            for (int i = tl.getBuffertime(); i > 0; i--) {
                tl.setStatus("red");
            }
        }
        RecoverLight(m,tl);
    }
    public void addSpecialSign(){
        System.out.println("+");
    }

    public void RecoverLight(Mission m ,TrafficLight tl){
        switch (tl.getPre_status()) {
            case "green":
                tl.setStatus("green");
                break;
            case "yellow":
                tl.setStatus("yellow");
                break;
            case "red":
                tl.setStatus("red");
                break;
        }
    }
}