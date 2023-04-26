package com.controller;

import com.dao.bicycleDao;
import com.dao.paymentDao;
import com.dao.rentalDao;
import com.model.bicycle;
import com.model.payment;
import com.model.rentalinfo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class paymentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        bicycle bic = (bicycle) session.getAttribute("bicycle");

        switch (action) {
            case "ADD": {
                payment pym = (payment) session.getAttribute("pym");

                String cardHolder = request.getParameter("cardHolder");
                String cardNumber = request.getParameter("cardNumber");
                String exp = request.getParameter("exp");
                String cvv = request.getParameter("cvv");

                payment payment = new payment();
                payment.setAmount(pym.getAmount());
                payment.setPayment_type(pym.getPayment_type());

                if (pym.getPayment_type().equals("Card")) {
                    payment.setCardName(cardHolder);
                    payment.setCardNum(cardNumber);
                    payment.setCardExp(exp);
                    payment.setCardCVV(Integer.parseInt(cvv));
                }
                session.removeAttribute("pym");
                session.setAttribute("pym", payment);
                request.getRequestDispatcher("paymentConfirmation.jsp").include(request, response);
                break;
            }
            case "CONFIRM": {
                rentalinfo rentinfo = (rentalinfo) session.getAttribute("rentinfo");
                java.sql.Date sqlD = new java.sql.Date(rentinfo.getRental_date().getTime());
                int rent_ID = rentalDao.getRentalID(bic.getBicID(), sqlD);

                payment pym = (payment) session.getAttribute("pym");

                payment nPym = new payment();
                nPym.setRentID(rent_ID);
                nPym.setAmount(pym.getAmount());
                nPym.setPayment_type(pym.getPayment_type());
                nPym.setCardName(pym.getCardName());
                nPym.setCardNum(pym.getCardNum());
                nPym.setCardExp(pym.getCardExp());
                nPym.setCardCVV(pym.getCardCVV());

                int status = paymentDao.save(nPym);

                if (status > 0) {
                    int state = bicycleDao.update(bic);

                    if (state > 0) {
                        session.removeAttribute("pym");
                        session.removeAttribute("payment");
                        session.removeAttribute("bicycle");
                        session.removeAttribute("rentinfo");
                        session.removeAttribute("customer");
                        request.getRequestDispatcher("bicList.jsp?status=Rented").include(request, response);
                    } else {
                        request.setAttribute("rentalError", "Unable to Rent");
                        request.getRequestDispatcher("bicList.jsp?status=Unrented").include(request, response);
                    }
                } else {
                    request.setAttribute("addPayError", "Unable to Create Payment");
                    request.getRequestDispatcher("addPayment.jsp").include(request, response);
                }
                break;
            }
            case "ADDON": {
                payment extendPay = (payment) session.getAttribute("newPay");
                rentalinfo extendRent = (rentalinfo) session.getAttribute("extendRent");
                String cardHolder = request.getParameter("cardHolder");
                String cardNumber = request.getParameter("cardNumber");
                String exp = request.getParameter("exp");
                String cvv = request.getParameter("cvv");
                
                payment upPay = new payment();
                upPay.setPaymentID(extendPay.getPaymentID());
                upPay.setRentID(extendRent.getRentID());
                upPay.setPayment_type(extendPay.getPayment_type());
                upPay.setAmount(extendPay.getAmount());
                upPay.setCardName(extendPay.getCardName());
                upPay.setCardNum(extendPay.getCardNum());
                upPay.setCardExp(extendPay.getCardExp());
                upPay.setCardCVV(extendPay.getCardCVV());
                
                if (extendPay.getPayment_type().equals("Card") && !extendPay.getCardNum().equals(cardNumber) && cardNumber != null) {
                    upPay.setCardName(cardHolder);
                    upPay.setCardNum(cardNumber);
                    upPay.setCardExp(exp);
                    upPay.setCardCVV(Integer.parseInt(cvv));
                }
                
                int status = paymentDao.update(upPay);
                if (status > 0) {
                    status = rentalDao.update(extendRent);
                    if (status > 0) {
                        session.removeAttribute("extendRent");
                        session.removeAttribute("extendPay");
                        session.removeAttribute("newPay");
                        session.removeAttribute("bicExtend");
                        request.getRequestDispatcher("bicList.jsp?status=Rented").include(request, response);
                    } else {
                        session.setAttribute("extendError", "Fail to extend return date");
                        request.getRequestDispatcher("bicList.jsp?status=Rented").include(request, response);
                    }
                } else {
                    session.setAttribute("extendError", "Fail to update Payment");
                    request.getRequestDispatcher("bicList.jsp?status=Rented").include(request, response);
                }
                break;
            }
            default:
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
