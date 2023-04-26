package com.controller;

import com.dao.bicycleDao;
import com.dao.reservationDao;
import com.model.bicycle;
import com.model.reservation;
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

public class reservationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        user currentUser = (user) session.getAttribute("login");
        int id;

        switch (action) {
            case "SELECT":
                id = Integer.parseInt(request.getParameter("id"));
                bicycle bicSelected = bicycleDao.getBicById(id);
                session.setAttribute("bicSelected", bicSelected);

                request.getRequestDispatcher("reservation.jsp").include(request, response);

                break;
            case "RESERVATION": {
                String reservedDate = request.getParameter("reservedDate");
                long l = sdf.parse(reservedDate).getTime();
                Date res_date = new Date(l);

                user cus = (user) session.getAttribute("login");
                bicycle bic = (bicycle) session.getAttribute("bicSelected");

                reservation reserved = new reservation();
                reserved.setBic_ID(bic.getBicID());
                reserved.setCus_ID(cus.getCusID());
                reserved.setRes_date(res_date);

                bicycle reservedBic = new bicycle();
                reservedBic.setBicID(bic.getBicID());
                reservedBic.setBicName(bic.getBicName());
                reservedBic.setBicType(bic.getBicType());
                reservedBic.setBicCondition(bic.getBicCondition());
                reservedBic.setBicStatus("Reserved");
                reservedBic.setBicRentPrice(bic.getBicRentPrice());

                session.removeAttribute("bicSelected");
                session.setAttribute("bicSelected", reservedBic);
                session.setAttribute("reservation", reserved);

                request.getRequestDispatcher("reservationConfirmation.jsp").include(request, response);
                break;
            }
            case "CONFIRM": {
                bicycle bic = (bicycle) session.getAttribute("bicSelected");
                reservation reserved = (reservation) session.getAttribute("reservation");

                int status = reservationDao.save(reserved);

                if (status > 0) {
                    status = bicycleDao.update(bic);

                    session.removeAttribute("bicSelected");
                    session.removeAttribute("reservation");
                    if (status > 0) {

                        response.sendRedirect("reservedList.jsp");
                    } else {
                        request.setAttribute("reservedError", "Unable To change Bicycle Status");
                        request.getRequestDispatcher("cusmain.jsp").forward(request, response);
                    }
                } else {
                    session.removeAttribute("bicSelected");
                    session.removeAttribute("reservation");
                    request.setAttribute("reservedError", "Unable to Make Reservation");
                    request.getRequestDispatcher("cusmain.jsp").forward(request, response);
                }
                break;
            }
            case "TOUPDATE": {
                id = Integer.parseInt(request.getParameter("id"));
                reservation selected = reservationDao.getReservedByID(id);
                bicycle bic = bicycleDao.getBicById(selected.getBic_ID());

                session.setAttribute("reservation", selected);
                session.setAttribute("bicSelected", bic);
                request.getRequestDispatcher("updateReservation.jsp").include(request, response);

                break;
            }
            case "UPDATE": {
                String reservedDate = request.getParameter("reservedDate");
                long l = sdf.parse(reservedDate).getTime();
                Date res_date = new Date(l);

                reservation upReserved = (reservation) session.getAttribute("reservation");
                reservation newR = new reservation();
                newR.setRes_ID(upReserved.getRes_ID());
                newR.setCus_ID(upReserved.getCus_ID());
                newR.setBic_ID(upReserved.getBic_ID());
                newR.setRes_date(res_date);
                newR.setRes_state(upReserved.getRes_state());

                int status = reservationDao.update(newR);
                if (status > 0) {
                    session.removeAttribute("reservation");
                    session.removeAttribute("bicSelected");
                    if (currentUser.getUserType().equals("Customer")) {
                        response.sendRedirect("reservedList.jsp");
                    } else if (currentUser.getUserType().equals("Employee")) {
                        response.sendRedirect("bicList.jsp?status=Reserved");
                    }
                } else {
                    session.removeAttribute("reservation");
                    session.removeAttribute("bicSelected");
                    request.setAttribute("upReservedError", "Unable to change reservation information");

                    if (currentUser.getUserType().equals("Customer")) {
                        request.getRequestDispatcher("reservedList.jsp").forward(request, response);
                    } else if (currentUser.getUserType().equals("Employee")) {
                        request.getRequestDispatcher("bicList.jsp?status=Reserved").forward(request, response);
                    }
                }

                break;
            }
            case "DELETE": {
                id = Integer.parseInt(request.getParameter("id"));
                reservation selected = reservationDao.getReservedByID(id);

                int bic_ID = selected.getBic_ID();
                bicycle bic = bicycleDao.getBicById(bic_ID);

                bicycle newB = new bicycle();
                newB.setBicID(bic.getBicID());
                newB.setBicName(bic.getBicName());
                newB.setBicRentPrice(bic.getBicRentPrice());
                newB.setBicStatus("Unrented");
                newB.setBicType(bic.getBicType());
                newB.setBicCondition(bic.getBicCondition());

                int status = reservationDao.delete(id);

                if (status > 0) {
                    status = bicycleDao.update(newB);

                    if (status > 0) {
                        if (currentUser.getUserType().equals("Customer")) {
                            response.sendRedirect("reservedList.jsp");
                        } else if (currentUser.getUserType().equals("Employee")) {
                            response.sendRedirect("bicList.jsp?status=Reserved");
                        }
                    }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(reservationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(reservationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
