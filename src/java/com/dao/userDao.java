package com.dao;

import com.model.user;
import com.util.DBConnection;
import java.util.*;
import java.sql.*;

public class userDao {

    public static int save(user e) {
        int status = 0;

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into useracc(username, password, user_type) values (?,?,?)");
            ps.setString(1, e.getUsername());
            ps.setString(2, e.getPassword());
            ps.setString(3, e.getUserType());

            status = ps.executeUpdate();
            ps = con.prepareStatement("select user_ID from useracc where username = ?");
            ps.setString(1, e.getUsername());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                if (e.getUserType().equals("Employee")) {
                    ps = con.prepareStatement("insert into employee(user_ID, emp_name, emp_IC, emp_address, emp_Hp) values (?,?,?,?,?)");
                    ps.setInt(1, rs.getInt("user_ID"));
                    ps.setString(2, e.getName());
                    ps.setString(3, e.getIc());
                    ps.setString(4, e.getAddress());
                    ps.setString(5, e.getHp());

                    status = ps.executeUpdate();
                } else if (e.getUserType().equals("Customer")) {
                    ps = con.prepareStatement("insert into customer(user_ID, cus_name, cus_IC, cus_address, cus_Hp) values (?,?,?,?,?)");
                    ps.setInt(1, rs.getInt("user_ID"));
                    ps.setString(2, e.getName());
                    ps.setString(3, e.getIc());
                    ps.setString(4, e.getAddress());
                    ps.setString(5, e.getHp());

                    status = ps.executeUpdate();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }

    public static user login(String username, String password) {
        user usr = null;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from useracc where username=? and password=?");
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usr = new user();
                usr.setUserID(rs.getInt("user_ID"));
                usr.setUsername(rs.getString("username"));
                usr.setPassword(rs.getString("password"));
                usr.setUserType(rs.getString("user_type"));

                if (rs.getString("user_type").equals("Employee")) {
                    ps = con.prepareStatement("select * from employee where user_ID = ?");
                    ps.setInt(1, rs.getInt("user_ID"));

                    rs = ps.executeQuery();
                    if (rs.next()) {
                        usr.setEmpID(rs.getInt("emp_ID"));
                        usr.setName(rs.getString("emp_name"));
                        usr.setIc(rs.getString("emp_IC"));
                        usr.setAddress(rs.getString("emp_address"));
                        usr.setHp(rs.getString("emp_Hp"));
                    }

                } else if (rs.getString("user_type").equals("Customer")) {
                    ps = con.prepareStatement("select * from customer where user_ID = ?");
                    ps.setInt(1, rs.getInt("user_ID"));

                    rs = ps.executeQuery();
                    if (rs.next()) {
                        usr.setCusID(rs.getInt("cus_ID"));
                        usr.setName(rs.getString("cus_name"));
                        usr.setIc(rs.getString("cus_IC"));
                        usr.setAddress(rs.getString("cus_address"));
                        usr.setHp(rs.getString("cus_Hp"));
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return usr;
    }

    public static int update(user e) {
        int status = 0;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("update useracc set username=?, password=?, user_type=? where user_ID=?");
            ps.setString(1, e.getUsername());
            ps.setString(2, e.getPassword());
            ps.setString(3, e.getUserType());
            ps.setInt(4, e.getUserID());

            status = ps.executeUpdate();

            if (e.getUserType().equals("Employee")) {
                ps = con.prepareStatement("update employee set user_ID=?, emp_name=?, emp_IC=?, emp_address=?, emp_Hp=? where emp_ID =?");
                ps.setInt(1, e.getUserID());
                ps.setString(2, e.getName());
                ps.setString(3, e.getIc());
                ps.setString(4, e.getAddress());
                ps.setString(5, e.getHp());
                ps.setInt(6, e.getEmpID());

                status = ps.executeUpdate();

            } else if (e.getUserType().equals("Customer")) {
                ps = con.prepareStatement("update customer set user_ID=?, cus_name=?, cus_IC=?, cus_address=?, cus_Hp=? where cus_ID =?");
                ps.setInt(1, e.getUserID());
                ps.setString(2, e.getName());
                ps.setString(3, e.getIc());
                ps.setString(4, e.getAddress());
                ps.setString(5, e.getHp());
                ps.setInt(6, e.getCusID());

                status = ps.executeUpdate();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    }

    public static int delete(int id) {
        int status = 0;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from useracc where user_ID=?");
            ps.setInt(1, id);

            status = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;

    }

    public static user getUserById(int id) {
        user usr = new user();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from useracc where user_ID=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getString("user_type").equals("Employee")) {
                    ps = con.prepareStatement("select * from employee where user_ID = ?");
                    ps.setInt(1, id);

                    rs = ps.executeQuery();
                    if (rs.next()) {
                        usr.setEmpID(rs.getInt("emp_ID"));
                        usr.setName(rs.getString("emp_name"));
                        usr.setIc(rs.getString("emp_IC"));
                        usr.setAddress(rs.getString("emp_address"));
                        usr.setHp(rs.getString("emp_Hp"));
                    }

                } else if (rs.getString("user_type").equals("Customer")) {
                    ps = con.prepareStatement("select * from customer where user_ID = ?");
                    ps.setInt(1, id);

                    rs = ps.executeQuery();
                    if (rs.next()) {
                        usr.setCusID(rs.getInt("cus_ID"));
                        usr.setName(rs.getString("cus_name"));
                        usr.setIc(rs.getString("cus_IC"));
                        usr.setAddress(rs.getString("cus_address"));
                        usr.setHp(rs.getString("cus_Hp"));
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return usr;
    }

    public static List<user> getAllUser(String userType) {
        List<user> list = new ArrayList<user>();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps;
            ResultSet rs;

            if (userType.equals("Employee")) {
                ps = con.prepareStatement("Select * from useracc LEFT JOIN employee USING (user_ID) WHERE user_type = 'employee'");
                rs = ps.executeQuery();

                while (rs.next()) {
                    user e = new user();
                    e.setUserID(rs.getInt("user_ID"));
                    e.setEmpID(rs.getInt("emp_ID"));
                    e.setName(rs.getString("emp_name"));
                    e.setIc(rs.getString("emp_IC"));
                    e.setAddress(rs.getString("emp_address"));
                    e.setHp(rs.getString("emp_Hp"));

                    list.add(e);
                }
            } else if (userType.equals("Owner")) {
                ps = con.prepareStatement("Select * from useracc where user_type = 'owner'");
                rs = ps.executeQuery();

                while (rs.next()) {
                    user e = new user();
                    e.setUserID(rs.getInt("user_ID"));
                    e.setUsername(rs.getString("username"));

                    list.add(e);
                }
            } else if (userType.equals("Customer")) {
                ps = con.prepareStatement("Select * from useracc LEFT JOIN customer USING (user_ID) WHERE user_type = 'customer'");
                rs = ps.executeQuery();
                
                while(rs.next()){
                    user e = new user();
                    e.setUserID(rs.getInt("user_ID"));
                    e.setCusID(rs.getInt("cus_ID"));
                    e.setName(rs.getString("cus_name"));
                    e.setIc(rs.getString("cus_IC"));
                    e.setAddress(rs.getString("cus_address"));
                    e.setHp(rs.getString("cus_Hp"));
                    
                    list.add(e);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static user checkAvailable(String cusName, String cusIC) {
        user cus = null;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from customer where cus_name=? and cus_IC=?");
            ps.setString(1, cusName);
            ps.setString(2, cusIC);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cus = new user();
                cus.setCusID(rs.getInt("cus_ID"));
                cus.setName(rs.getString("cus_name"));
                cus.setIc(rs.getString("cus_IC"));
                cus.setAddress(rs.getString("cus_address"));
                cus.setHp(rs.getString("cus_Hp"));
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return cus;
    }

    public static int addCus(user e) {
        int status = 0;

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into customer(cus_name, cus_IC, cus_address, cus_Hp) values (?,?,?,?)");
            ps.setString(1, e.getName());
            ps.setString(2, e.getIc());
            ps.setString(3, e.getAddress());
            ps.setString(4, e.getHp());

            status = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return status;
    }

    public static int addCusAcc(user e) {
        int status = 0;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into useracc(username, password, user_type) values (?,?,?)");
            ps.setString(1, e.getUsername());
            ps.setString(2, e.getPassword());
            ps.setString(3, e.getUserType());

            status = ps.executeUpdate();
            ps = con.prepareStatement("select user_ID from useracc where username = ?");
            ps.setString(1, e.getUsername());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ps = con.prepareStatement("update customer set user_ID=?, cus_name=?, cus_IC=?, cus_address=?, cus_Hp=? where cus_ID =?");
                ps.setInt(1, rs.getInt("user_ID"));
                ps.setString(2, e.getName());
                ps.setString(3, e.getIc());
                ps.setString(4, e.getAddress());
                ps.setString(5, e.getHp());
                ps.setInt(6, e.getCusID());

                status = ps.executeUpdate();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    }

    public static int getCusID(String cusName, String cusIC) {
        int cus_ID = 0;

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from customer where cus_name=? and cus_IC=?");
            ps.setString(1, cusName);
            ps.setString(2, cusIC);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cus_ID = rs.getInt("cus_ID");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return cus_ID;
    }

    public static user getByCusID(int id) {
        user usr = new user();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from customer where cus_ID = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usr.setUserID(rs.getInt("user_ID"));
                usr.setCusID(rs.getInt("cus_ID"));
                usr.setName(rs.getString("cus_name"));
                usr.setIc(rs.getString("cus_IC"));
                usr.setAddress(rs.getString("cus_address"));
                usr.setHp(rs.getString("cus_Hp"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return usr;
    }
}
