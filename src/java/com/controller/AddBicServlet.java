package com.controller;

import com.dao.bicycleDao;
import com.model.bicycle;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddBicServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String name = request.getParameter("bicycleName");
        String type = request.getParameter("bicycleType");
        double bicRentPrice = Double.parseDouble(request.getParameter("bicRentPrice"));
        String condition = request.getParameter("condition");
        String bic_status = request.getParameter("status");

        bicycle e = new bicycle();
        e.setBicName(name);
        e.setBicType(type);
        e.setBicRentPrice(bicRentPrice);
        e.setBicCondition(condition);
        e.setBicStatus(bic_status);

        int status = bicycleDao.save(e);
        
        if (status > 0) {
            request.getRequestDispatcher("ownermain.jsp?type=All").include(request, response);
        } else {
            request.setAttribute("createError", "Unable To Add Bicycle");
            request.getRequestDispatcher("addbicycle.jsp").include(request, response);
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
