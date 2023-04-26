package com.dao;

import com.model.payment;
import com.model.rentalinfo;
import com.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class paymentDao {

    public static int save(payment e) {
        int status = 0;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into payment(rent_ID, amount, payment_type, card_name, card_num, card_exp, card_cvv) values (?,?,?,?,?,?,?)");
            ps.setInt(1, e.getRentID());
            ps.setDouble(2, e.getAmount());
            ps.setString(3, e.getPayment_type());
            ps.setString(4, e.getCardName());
            ps.setString(5, e.getCardNum());
            ps.setString(6, e.getCardExp());
            ps.setInt(7, e.getCardCVV());

            status = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    }

    public static int update(payment e) {
        int status = 0;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("update payment set amount=?, payment_type=?, card_name=?, card_num=?, card_exp=?, card_cvv=? where payment_ID=? and rent_ID=?");
            ps.setDouble(1, e.getAmount());
            ps.setString(2, e.getPayment_type());
            ps.setString(3, e.getCardName());
            ps.setString(4, e.getCardNum());
            ps.setString(5, e.getCardExp());
            ps.setInt(6, e.getCardCVV());
            ps.setInt(7, e.getPaymentID());
            ps.setInt(8, e.getRentID());

            status = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    }

    public static payment getPaymentByID(int id) {
        payment pym = new payment();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from payment where rent_ID = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pym.setPaymentID(rs.getInt("payment_ID"));
                pym.setRentID(rs.getInt("rent_ID"));
                pym.setAmount(rs.getDouble("amount"));
                pym.setPayment_type(rs.getString("payment_type"));
                pym.setCardName(rs.getString("card_name"));
                pym.setCardNum(rs.getString("card_num"));
                pym.setCardExp(rs.getString("card_exp"));
                pym.setCardCVV(rs.getInt("card_cvv"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return pym;
    }
    
    public static double[][] getTotalAmountByMonth (){
        double[][] month = null;
        int i=0;
        
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT MONTH(rental_date) AS MONTH, SUM(amount) AS amount FROM payment JOIN rentalinfo USING (rent_ID) GROUP BY MONTH");

            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                month[i][0] = rs.getDouble("MONTH");
                month[i][1] = rs.getDouble("amount");
                i++;
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return month;
    }
}