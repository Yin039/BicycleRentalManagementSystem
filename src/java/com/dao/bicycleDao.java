package com.dao;

import com.util.DBConnection;
import com.model.bicycle;
import java.util.*;
import java.sql.*;

public class bicycleDao {

    public static int save(bicycle e) {
        int status = 0;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into bicycle(bic_name, bic_type, bic_rentPrice, bic_condition, bic_status) values (?,?,?,?,?)");
            ps.setString(1, e.getBicName());
            ps.setString(2, e.getBicType());
            ps.setDouble(3, e.getBicRentPrice());
            ps.setString(4, e.getBicCondition());
            ps.setString(5, e.getBicStatus());

            status = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    }

    public static int update(bicycle e) {
        int status = 0;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("update bicycle set bic_name=?, bic_type=?, bic_rentPrice=?, bic_condition=?, bic_status=? where bic_ID =?");
            ps.setString(1, e.getBicName());
            ps.setString(2, e.getBicType());
            ps.setDouble(3, e.getBicRentPrice());
            ps.setString(4, e.getBicCondition());
            ps.setString(5, e.getBicStatus());
            ps.setInt(6, e.getBicID());

            status = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;

    }

    public static int delete(int id) {
        int status = 0;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from bicycle where bic_ID=?");
            ps.setInt(1, id);

            status = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;

    }

    public static bicycle getBicById(int id) {
        bicycle e = new bicycle();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from bicycle where bic_ID=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                e.setBicID(rs.getInt("bic_ID"));
                e.setBicName(rs.getString("bic_name"));
                e.setBicType(rs.getString("bic_type"));
                e.setBicRentPrice(rs.getDouble("bic_rentPrice"));
                e.setBicCondition(rs.getString("bic_condition"));
                e.setBicStatus(rs.getString("bic_status"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return e;
    }

    public static List<bicycle> getAllBicycle() {
        List<bicycle> list = new ArrayList<bicycle>();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("Select * from bicycle");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                bicycle e = new bicycle();
                e.setBicID(rs.getInt("bic_ID"));
                e.setBicName(rs.getString("bic_name"));
                e.setBicType(rs.getString("bic_type"));
                e.setBicRentPrice(rs.getDouble("bic_rentPrice"));
                e.setBicCondition(rs.getString("bic_condition"));
                e.setBicStatus(rs.getString("bic_status"));

                list.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<bicycle> getBicycleByStatus(String status) {
        List<bicycle> list = new ArrayList<bicycle>();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("Select * from bicycle where bic_status = ?");
            ps.setString(1, status);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                bicycle e = new bicycle();
                e.setBicID(rs.getInt("bic_ID"));
                e.setBicName(rs.getString("bic_name"));
                e.setBicType(rs.getString("bic_type"));
                e.setBicRentPrice(rs.getDouble("bic_rentPrice"));
                e.setBicCondition(rs.getString("bic_condition"));
                e.setBicStatus(rs.getString("bic_status"));

                list.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<bicycle> getBicycleToResByType(String type) {
        List<bicycle> list = new ArrayList<bicycle>();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("Select * from bicycle where bic_status = 'Unrented' and bic_type=?");
            ps.setString(1, type);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                bicycle e = new bicycle();
                e.setBicID(rs.getInt("bic_ID"));
                e.setBicName(rs.getString("bic_name"));
                e.setBicType(rs.getString("bic_type"));
                e.setBicRentPrice(rs.getDouble("bic_rentPrice"));
                e.setBicCondition(rs.getString("bic_condition"));
                e.setBicStatus(rs.getString("bic_status"));

                list.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static List<bicycle> getBicycleByType(String type) {
        List<bicycle> list = new ArrayList<bicycle>();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("Select * from bicycle where bic_type=?");
            ps.setString(1, type);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                bicycle e = new bicycle();
                e.setBicID(rs.getInt("bic_ID"));
                e.setBicName(rs.getString("bic_name"));
                e.setBicType(rs.getString("bic_type"));
                e.setBicRentPrice(rs.getDouble("bic_rentPrice"));
                e.setBicCondition(rs.getString("bic_condition"));
                e.setBicStatus(rs.getString("bic_status"));

                list.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
