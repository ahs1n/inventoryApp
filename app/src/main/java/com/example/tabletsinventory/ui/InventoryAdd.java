package com.example.tabletsinventory.ui;

public class InventoryAdd {
    int id;
    String imei;
    String serial;
    String tag_number;
    String brand;
    String model;
    String date;
    String project_name;
    String received_from;
    String location;
    String remarks;
    String device;

    public InventoryAdd() {
    }

    public InventoryAdd(int id, String imei, String serial, String tag_number, String brand, String model, String date, String project_name, String received_from, String location, String remarks, String device) {
        this.id = id;
        this.imei = imei;
        this.serial = serial;
        this.tag_number = tag_number;
        this.brand = brand;
        this.model = model;
        this.date = date;
        this.project_name = project_name;
        this.received_from = received_from;
        this.location = location;
        this.remarks = remarks;
        this.device = device;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getTag_number() {
        return tag_number;
    }

    public void setTag_number(String tag_number) {
        this.tag_number = tag_number;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getReceived_from() {
        return received_from;
    }

    public void setReceived_from(String received_from) {
        this.received_from = received_from;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}