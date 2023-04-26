package com.controller;

import com.dao.bicycleDao;
import com.dao.reservationDao;
import com.dao.userDao;
import com.model.bicycle;
import com.model.reservation;
import com.model.user;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class updateBicServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String action = request.getParameter("action");

        String newC = request.getParameter("condition");
        String newS = request.getParameter("status");

        if (action.equals("GET")) {
            int id = Integer.parseInt(request.getParameter("id"));
            bicycle bic = bicycleDao.getBicById(id);

            session.setAttribute("bicycle", bic);

            response.sendRedirect("updateBicycle.jsp");

        } else if (action.equals("UPDATE")) {
            int id = Integer.parseInt(request.getParameter("id"));
            bicycle bic = bicycleDao.getBicById(id);

            bicycle newB = new bicycle(id, bic.getBicName(), bic.getBicType(), bic.getBicCondition(), bic.getBicStatus(), bic.getBicRentPrice());

            String newN = request.getParameter("bicycleName");
            String newT = request.getParameter("bicycleType");
            double newP = Double.parseDouble(request.getParameter("bicRentPrice"));

            if (!newN.isEmpty()) {
                newB.setBicName(newN);
            }

            if (!newT.isEmpty()) {
                newB.setBicType(newT);
            }

            if (newP != bic.getBicRentPrice()) {
                newB.setBicRentPrice(newP);
            }

            if (!newC.isEmpty()) {
                newB.setBicCondition(newC);
            }

            if (!newS.isEmpty()) {
                newB.setBicStatus(newS);
            }

            int status = bicycleDao.update(newB);

            if (status > 0) {
                request.getRequestDispatcher("ownermain.jsp").include(request, response);
            } else {
                request.setAttribute("updateError", "Unable to update Bicycle Information");
                request.getRequestDispatcher("updateBicycle.jsp").include(request, response);
            }

        } else if (action.equals("CONDITION")) {
            int id = Integer.parseInt(request.getParameter("id"));
            bicycle bic = bicycleDao.getBicById(id);

            session.setAttribute("bicycle", bic);

            response.sendRedirect("upCondition.jsp");

        } else if (action.equals("UPCON")) {
            bicycle bic = (bicycle) session.getAttribute("bicycle");;

            bicycle newB = new bicycle(bic.getBicID(), bic.getBicName(), bic.getBicType(), bic.getBicCondition(), bic.getBicStatus(), bic.getBicRentPrice());

            if (!newC.isEmpty()) {
                newB.setBicCondition(newC);
            }

            if (!newS.isEmpty()) {
                newB.setBicStatus(newS);
            }

            int status = bicycleDao.update(newB);

            if (status > 0) {
                session.removeAttribute("bicycle");
                request.getRequestDispatcher("bicList.jsp?status=" + bic.getBicStatus()).include(request, response);
            } else {
                request.setAttribute("updateError", "Unable to update Bicycle Condition");
                request.getRequestDispatcher("upCondition.jsp").include(request, response);
            }
        } else if (action.equals("GETUNRENTED")) {
            int id = Integer.parseInt(request.getParameter("id"));
            bicycle bic = bicycleDao.getBicById(id);

            session.setAttribute("bicycle", bic);

            response.sendRedirect("rentBic.jsp");

        } else if (action.equals("GETRESERVED")) {
            int id = Integer.parseInt(request.getParameter("id"));
            bicycle bic = bicycleDao.getBicById(id);

            int resID = Integer.parseInt(request.getParameter("resID"));
            reservation reserved = reservationDao.getReservedByID(resID);

            int cusID = reserved.getCus_ID();
            user cus = userDao.getByCusID(cusID);

            session.setAttribute("reservedBy", cus);
            session.setAttribute("bicycle", bic);
            session.setAttribute("reservation", reserved);

            response.sendRedirect("rentBic.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
