package com.model;

import java.util.Date;

public class reservation {
    
    int res_ID, cus_ID, bic_ID;
    Date res_date;
    String res_state;

    public int getRes_ID() {
        return res_ID;
    }

    public void setRes_ID(int res_ID) {
        this.res_ID = res_ID;
    }

    public int getCus_ID() {
        return cus_ID;
    }

    public void setCus_ID(int cus_ID) {
        this.cus_ID = cus_ID;
    }

    public int getBic_ID() {
        return bic_ID;
    }

    public void setBic_ID(int bic_ID) {
        this.bic_ID = bic_ID;
    }

    public Date getRes_date() {
        return res_date;
    }

    public void setRes_date(Date res_date) {
        this.res_date = res_date;
    }

    public String getRes_state() {
        return res_state;
    }

    public void setRes_state(String res_state) {
        this.res_state = res_state;
    }
}
