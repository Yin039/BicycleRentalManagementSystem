package com.model;

import java.util.Date;

public class rented {

    private int rentID, bicID;
    private Date rental_date, return_date;
    private String bicName, bicType, bicStatus, name, hp;

    public int getRentID() {
        return rentID;
    }

    public void setRentID(int rentID) {
        this.rentID = rentID;
    }

    public int getBicID() {
        return bicID;
    }

    public void setBicID(int bicID) {
        this.bicID = bicID;
    }

    public Date getRental_date() {
        return rental_date;
    }

    public void setRental_date(Date rental_date) {
        this.rental_date = rental_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
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

    public String getBicStatus() {
        return bicStatus;
    }

    public void setBicStatus(String bicStatus) {
        this.bicStatus = bicStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }
    

}
