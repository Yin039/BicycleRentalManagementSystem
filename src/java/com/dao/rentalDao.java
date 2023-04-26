package com.dao;

import com.model.rentalinfo;
import com.util.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class rentalDao {

    public static int save(rentalinfo e) {
        int status = 0;

        Date rentD = new Date(e.getRental_date().getTime());
        Date returnD = new Date(e.getReturn_date().getTime());

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into rentalinfo(bic_ID, cus_ID, emp_ID, rental_date, return_date, trade) values (?,?,?,?,?,?)");
            ps.setInt(1, e.getBicID());
            ps.setInt(2, e.getCusID());
            ps.setInt(3, e.getEmpID());
            ps.setDate(4, rentD);
            ps.setDate(5, returnD);
            ps.setString(6, e.getTrade());

            status = ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    }
    
    public static int update(rentalinfo e){
        int status = 0;
        
        Date rentD = new Date(e.getRental_date().getTime());
        Date returnD = new Date(e.getReturn_date().getTime());
        
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("update rentalinfo set bic_ID=?, cus_ID=?, emp_ID=?, rental_date=?, return_date=?, trade=? where rent_ID=?");
            ps.setInt(1, e.getBicID());
            ps.setInt(2, e.getCusID());
            ps.setInt(3, e.getEmpID());
            ps.setDate(4, rentD);
            ps.setDate(5, returnD);
            ps.setString(6, e.getTrade());
            ps.setInt(7, e.getRentID());
            
            status = ps.executeUpdate();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return status;
    }

    public static int getRentalID(int id, Date rentD) {
        int rent_ID = 0;

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from rentalinfo where bic_ID=? and rental_date=?");
            ps.setInt(1, id);
            ps.setDate(2, rentD);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                rent_ID = rs.getInt("rent_ID");
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return rent_ID;
    }

    public static rentalinfo getRentalInfoByID(int id) {
        rentalinfo rentinfo = new rentalinfo();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from rentalinfo where rent_ID = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                rentinfo.setRentID(rs.getInt("rent_ID"));
                rentinfo.setBicID(rs.getInt("bic_ID"));
                rentinfo.setCusID(rs.getInt("cus_ID"));
                rentinfo.setEmpID(rs.getInt("emp_ID"));
                rentinfo.setRental_date(rs.getDate("rental_date"));
                rentinfo.setReturn_date(rs.getDate("return_date"));
                rentinfo.setTrade(rs.getString("trade"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return rentinfo;
    }
    
    public static List<rentalinfo> getAllRentalInfo() {
        List<rentalinfo> list = new ArrayList<rentalinfo>();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("Select * from rentalinfo");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rentalinfo rentinfo = new rentalinfo();
                rentinfo.setRentID(rs.getInt("rent_ID"));
                rentinfo.setBicID(rs.getInt("bic_ID"));
                rentinfo.setCusID(rs.getInt("cus_ID"));
                rentinfo.setEmpID(rs.getInt("emp_ID"));
                rentinfo.setRental_date(rs.getDate("rental_date"));
                rentinfo.setReturn_date(rs.getDate("return_date"));
                rentinfo.setTrade(rs.getString("trade"));

                list.add(rentinfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static List<rentalinfo> getRentalInfoByMonthYear(int year, int month){
        List<rentalinfo> list = new ArrayList<rentalinfo>();
        
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("Select * from rentalinfo where YEAR(rental_date)=? and MONTH(rental_date)=?");
            ps.setInt(1, year);
            ps.setInt(2, month);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rentalinfo rentinfo = new rentalinfo();
                rentinfo.setRentID(rs.getInt("rent_ID"));
                rentinfo.setBicID(rs.getInt("bic_ID"));
                rentinfo.setCusID(rs.getInt("cus_ID"));
                rentinfo.setEmpID(rs.getInt("emp_ID"));
                rentinfo.setRental_date(rs.getDate("rental_date"));
                rentinfo.setReturn_date(rs.getDate("return_date"));
                rentinfo.setTrade(rs.getString("trade"));

                list.add(rentinfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
