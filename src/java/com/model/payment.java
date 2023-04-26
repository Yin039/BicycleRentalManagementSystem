package com.model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class payment {

    private int paymentID, rentID, cardCVV;
    private double amount;
    private String payment_type, cardName, cardNum, cardExp;

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getRentID() {
        return rentID;
    }

    public void setRentID(int rentID) {
        this.rentID = rentID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public int getCardCVV() {
        return cardCVV;
    }

    public void setCardCVV(int cardCVV) {
        this.cardCVV = cardCVV;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardExp() {
        return cardExp;
    }

    public void setCardExp(String cardExp) {
        this.cardExp = cardExp;
    }

    public void calculateTotal(Date rentDate, Date returnDate, double amount) {
        double total;

        long timeDiff = Math.abs(returnDate.getTime() - rentDate.getTime());
        long day = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);

        total = amount * (day + 1);
        this.amount = total;
    }
}
