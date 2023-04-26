package com.model;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class rentalinfo {
    private int rentID, bicID, cusID, empID;
    private Date rental_date, return_date;
    private String trade;

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

    public int getCusID() {
        return cusID;
    }

    public void setCusID(int cusID) {
        this.cusID = cusID;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
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

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }
    
    public int dayDiff(){
        int dayDiff = 0;
        
        long timeDiff = Math.abs(this.return_date.getTime() - this.rental_date.getTime());
        long day = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
        
        dayDiff = (int) (day + 1);
        
        return dayDiff;
    }
}
