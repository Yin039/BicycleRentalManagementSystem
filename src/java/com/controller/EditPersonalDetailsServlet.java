package com.controller;

import com.dao.userDao;
import com.model.user;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditPersonalDetailsServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        user oldUser = (user) session.getAttribute("login");
        
        user user = new user();
        user.setUserID(oldUser.getUserID());
        user.setUsername(oldUser.getUsername());
        user.setPassword(oldUser.getPassword());
        user.setUserType(oldUser.getUserType());
        user.setName(oldUser.getName());
        user.setIc(oldUser.getIc());
        user.setAddress(oldUser.getAddress());
        user.setHp(oldUser.getHp());
        
        if (oldUser.getUserType().equals("Employee")) {
            user.setEmpID(oldUser.getEmpID());
        } else if (oldUser.getUserType().equals("Customer")) {
            user.setCusID(oldUser.getCusID());
        }
        
        String newAdd = request.getParameter("address");
        String newHP = request.getParameter("Hp");
        
        if (!newAdd.equals(oldUser.getAddress())) {
            user.setAddress(newAdd);
        }
        
        if (!newHP.equals(oldUser.getHp())) {
            if (user.isValidPhnNo(newHP) == false) {
                session.setAttribute("hpError", "Must not contain '-'");
                request.getRequestDispatcher("upPersonalDetails.jsp").include(request, response);
            } else {
                user.setHp(newHP);
            }
        }
        
        if (user.isValidPhnNo(newHP) == true || !newAdd.isEmpty()) {
            int status = userDao.update(user);
            
            if (status > 0) {
                session.removeAttribute("login");
                session.setAttribute("login", user);
                request.getRequestDispatcher("personalDetails.jsp").include(request, response);
            } else {
                request.setAttribute("updateError", "Unable to update information..!");
                request.getRequestDispatcher("personalDetails.jsp").include(request, response);
            }
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
