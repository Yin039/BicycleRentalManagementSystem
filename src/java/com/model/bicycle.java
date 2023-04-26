package com.model;

public class bicycle {
    private int bicID;
    private String bicName, bicType, bicCondition, bicStatus;
    private Double bicRentPrice;


    public bicycle() {
    }

    public bicycle(int bicID, String bicName, String bicType, String bicCondition, String bicStatus, Double bicRentPrice) {
        this.bicID = bicID;
        this.bicName = bicName;
        this.bicType = bicType;
        this.bicCondition = bicCondition;
        this.bicStatus = bicStatus;
        this.bicRentPrice = bicRentPrice;
    }

    public int getBicID() {
        return bicID;
    }

    public void setBicID(int bicID) {
        this.bicID = bicID;
    }

    public String getBicName() {
        return bicName;
    }

    public void setBicName(String bicName) {
        this.bicName = bicName;
    }

    public String getBicType() {
        return bicType;
    }

    public void setBicType(String bicType) {
        this.bicType = bicType;
    }

    public String getBicCondition() {
        return bicCondition;
    }

    public void setBicCondition(String bicCondition) {
        this.bicCondition = bicCondition;
    }

    public String getBicStatus() {
        return bicStatus;
    }

    public void setBicStatus(String bicStatus) {
        this.bicStatus = bicStatus;
    }
    
    public Double getBicRentPrice() {
        return bicRentPrice;
    }

    public void setBicRentPrice(Double bicRentPrice) {
        this.bicRentPrice = bicRentPrice;
    }
}
