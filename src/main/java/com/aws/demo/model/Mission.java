package com.aws.demo.model;

public class Mission {
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
