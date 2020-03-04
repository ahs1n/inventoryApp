package com.example.tabletsinventory.ui;

public class InventoryUpdate {
    int id;
    long imei;
    String tag_number;
    String date;
    String project_name;
    String accessories;
    String handedoverby;
    String received_from;
    String lastlocation;
    String currentlocation;
    String remarks;
    String device;

    public InventoryUpdate() {
    }

    public InventoryUpdate(int id, long imei, String tag_number, String date, String project_name, String accessories, String handedoverby,
                           String received_from, String lastlocation, String currentlocation, String remarks) {
        this.id = id;
        this.imei = imei;
        this.tag_number = tag_number;
        this.date = date;
        this.project_name = project_name;
        this.accessories = accessories;
        this.handedoverby = handedoverby;
        this.received_from = received_from;
        this.lastlocation = lastlocation;
        this.currentlocation = currentlocation;
        this.remarks = remarks;
        this.device = device;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getImei() {
        return imei;
    }

    public void setImei(long imei) {
        this.imei = imei;
    }

    public String getTag_number() {
        return tag_number;
    }

    public void setTag_number(String tag_number) {
        this.tag_number = tag_number;
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

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public String getHandedoverby() {
        return handedoverby;
    }

    public void setHandedoverby(String handedoverby) {
        this.handedoverby = handedoverby;
    }

    public String getReceived_from() {
        return received_from;
    }

    public void setReceived_from(String received_from) {
        this.received_from = received_from;
    }

    public String getLastlocation() {
        return lastlocation;
    }

    public void setLastlocation(String lastlocation) {
        this.lastlocation = lastlocation;
    }

    public String getCurrentlocation() {
        return currentlocation;
    }

    public void setCurrentlocation(String currentlocation) {
        this.currentlocation = currentlocation;
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