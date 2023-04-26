package com.controller;

import com.dao.bicycleDao;
import com.dao.paymentDao;
import com.dao.rentalDao;
import com.dao.userDao;
import com.model.bicycle;
import com.model.payment;
import com.model.rentalinfo;
import com.model.user;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class rentalServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        HttpSession session = request.getSession();
        user emp = (user) session.getAttribute("login");
        bicycle bic = (bicycle) session.getAttribute("bicycle");

        String action = request.getParameter("action");

        user cus;
        user newC = new user();
        rentalinfo rentinfo = new rentalinfo();
        payment pym = new payment();

        switch (action) {
            case "RENT": {
                int bicID = Integer.parseInt(request.getParameter("bicycleID"));
                String status = request.getParameter("status");
                bicycle bicSelected = (bicycle) bicycleDao.getBicById(bicID);
                bicSelected.setBicStatus(status);

                String cusName = request.getParameter("cusName");
                String cusIC = request.getParameter("cusIc");
                String rentD = request.getParameter("rentDate");
                String returnD = request.getParameter("returnDate");

                long l = sdf.parse(rentD).getTime();
                Date rentDate = new Date(l);
                long lo = sdf.parse(returnD).getTime();
                Date returnDate = new Date(lo);

                String pymType = request.getParameter("paymentType");

                if (user.isValidIcNo(cusIC) == false) {
                    request.setAttribute("icError", "Must in format xxxxxx-xx-xxxx");
                    request.getRequestDispatcher("rentBic.jsp").forward(request, response);
                }

                cus = userDao.checkAvailable(cusName, cusIC);
                newC.setName(cusName);
                newC.setIc(cusIC);

                session.removeAttribute("bicycle");
                session.setAttribute("bicycle", bicSelected);

                rentinfo.setRental_date(rentDate);
                rentinfo.setReturn_date(returnDate);

                pym.setPayment_type(pymType);
                session.setAttribute("pym", pym);
                
                
                if (cus != null) {
                    rentinfo.setCusID(cus.getCusID());
                    session.setAttribute("rentinfo", rentinfo);
                    session.setAttribute("customer", cus);
                    request.getRequestDispatcher("rentalConfirmation.jsp").include(request, response);
                } else {
                    session.setAttribute("rentinfo", rentinfo);
                    session.setAttribute("customer", newC);
                    request.getRequestDispatcher("addCustomer.jsp").include(request, response);
                }
                break;
            }
            case "CONFIRM": {
                rentalinfo rentalinfo = (rentalinfo) session.getAttribute("rentinfo");
                cus = (user) session.getAttribute("customer");

                rentinfo.setBicID(bic.getBicID());
                rentinfo.setCusID(cus.getCusID());
                rentinfo.setEmpID(emp.getEmpID());
                rentinfo.setRental_date(rentalinfo.getRental_date());
                rentinfo.setReturn_date(rentalinfo.getReturn_date());
                rentinfo.setTrade("Undergoing");

                int state = rentalDao.save(rentinfo);
                
                if (state > 0) {
                    session.removeAttribute("rentinfo");
                    session.setAttribute("rentinfo", rentinfo);
                    request.getRequestDispatcher("paymentServlet?action=CONFIRM").forward(request, response);
                } else {
                    session.setAttribute("rentalError", "Unable to rent");
                    request.getRequestDispatcher("bicList.jsp?status=Unrented").include(request, response);
                }
                break;
            }
            case "RETURN": {
                int rentID = Integer.parseInt(request.getParameter("rentID"));
                rentinfo = rentalDao.getRentalInfoByID(rentID);

                rentalinfo upRentInfo = new rentalinfo();
                upRentInfo.setRentID(rentID);
                upRentInfo.setBicID(rentinfo.getBicID());
                upRentInfo.setCusID(rentinfo.getCusID());
                upRentInfo.setEmpID(rentinfo.getEmpID());
                upRentInfo.setRental_date(rentinfo.getRental_date());
                upRentInfo.setReturn_date(rentinfo.getReturn_date());
                upRentInfo.setTrade("Complete");

                rentalDao.update(upRentInfo);

                int bicID = rentinfo.getBicID();
                bicycle bicReturn = bicycleDao.getBicById(bicID);

                bicycle bReturned = new bicycle();
                bReturned.setBicID(bicID);
                bReturned.setBicName(bicReturn.getBicName());
                bReturned.setBicRentPrice(bicReturn.getBicRentPrice());
                bReturned.setBicType(bicReturn.getBicType());
                bReturned.setBicCondition(bicReturn.getBicCondition());
                bReturned.setBicStatus("Unrented");

                int status = bicycleDao.update(bReturned);

                if (status > 0) {
                    request.getRequestDispatcher("bicList.jsp?status=Unrented").include(request, response);
                } else {
                    request.setAttribute("returnError", "Unable to return the bicycle");
                    request.getRequestDispatcher("bicList.jsp?status=Rented.jsp").include(request, response);
                }
                break;
            }
            case "EXTEND":
                int rentID = Integer.parseInt(request.getParameter("rentID"));
                rentinfo = rentalDao.getRentalInfoByID(rentID);
                session.setAttribute("extendRent", rentinfo);

                payment payment = paymentDao.getPaymentByID(rentID);
                session.setAttribute("extendPay", payment);

                bicycle bicExtend = bicycleDao.getBicById(rentinfo.getBicID());
                session.setAttribute("bicExtend", bicExtend);

                request.getRequestDispatcher("extendDate.jsp").forward(request, response);

                break;
            case "CONFIRMREXTEND":
                String extendReturn = request.getParameter("returnDate");
                long lo = sdf.parse(extendReturn).getTime();
                Date extendD = new Date(lo);

                rentalinfo extendRent = (rentalinfo) session.getAttribute("extendRent");
                rentalinfo upRent = new rentalinfo();
                upRent.setRentID(extendRent.getRentID());
                upRent.setBicID(extendRent.getBicID());
                upRent.setCusID(extendRent.getCusID());
                upRent.setEmpID(emp.getEmpID());
                upRent.setRental_date(extendRent.getRental_date());
                upRent.setReturn_date(extendD);
                upRent.setTrade(extendRent.getTrade());

                session.removeAttribute("extendRent");
                session.setAttribute("extendRent", upRent);
                
                String pymType = request.getParameter("paymentType");
                payment extendPay = (payment) session.getAttribute("extendPay");
                payment upPay = new payment();
                upPay.setPaymentID(extendPay.getPaymentID());
                upPay.setRentID(extendPay.getRentID());
                upPay.setPayment_type(pymType);
                upPay.setAmount(extendPay.getAmount());
                upPay.setCardName(extendPay.getCardName());
                upPay.setCardNum(extendPay.getCardNum());
                upPay.setCardExp(extendPay.getCardExp());
                upPay.setCardCVV(extendPay.getCardCVV());
                
                session.setAttribute("newPay", upPay);
                
                request.getRequestDispatcher("updatePayment.jsp").forward(request, response);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(rentalServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(rentalServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
