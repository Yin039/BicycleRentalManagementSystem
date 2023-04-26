package com.dao;

import com.model.reservation;
import com.util.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class reservationDao {

    public static int save(reservation e) {
        int status = 0;

        Date reserveD = new Date(e.getRes_date().getTime());

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into reservation(cus_ID, bic_ID, res_date) values (?,?,?)");
            ps.setInt(1, e.getCus_ID());
            ps.setInt(2, e.getBic_ID());
            ps.setDate(3, reserveD);

            status = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    }

    public static int update(reservation e) {
        int status = 0;

        Date returnD = new Date(e.getRes_date().getTime());

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("update reservation set cus_ID=?, bic_ID=?, res_date=? where res_ID=?");
            ps.setInt(1, e.getCus_ID());
            ps.setInt(2, e.getBic_ID());
            ps.setDate(3, returnD);
            ps.setInt(4, e.getRes_ID());

            status = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    }

    public static reservation getReservedByID(int id) {
        reservation e = new reservation();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from reservation where res_ID=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                e.setRes_ID(rs.getInt("res_ID"));
                e.setCus_ID(rs.getInt("cus_ID"));
                e.setBic_ID(rs.getInt("bic_ID"));
                e.setRes_date(rs.getDate("res_date"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return e;
    }
    
    public static List<reservation> getReservedList() {
        List<reservation> list = new ArrayList<reservation>();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from reservation");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                reservation e = new reservation();
                e.setRes_ID(rs.getInt("res_ID"));
                e.setCus_ID(rs.getInt("cus_ID"));
                e.setBic_ID(rs.getInt("bic_ID"));
                e.setRes_date(rs.getDate("res_date"));
                
                list.add(e);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static List<reservation> getReservedListByCusID(int id) {
        List<reservation> list = new ArrayList<reservation>();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from reservation where cus_ID=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                reservation e = new reservation();
                e.setRes_ID(rs.getInt("res_ID"));
                e.setCus_ID(rs.getInt("cus_ID"));
                e.setBic_ID(rs.getInt("bic_ID"));
                e.setRes_date(rs.getDate("res_date"));

                list.add(e);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static int delete(int id) {
        int status = 0;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from reservation where res_ID=?");
            ps.setInt(1, id);

            status = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
