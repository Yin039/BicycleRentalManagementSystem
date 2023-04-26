package com.controller;

import com.dao.userDao;
import com.model.user;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePassServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("ownermain.jsp");

        HttpSession session = request.getSession();
        user oldUser = (user) session.getAttribute("login");

        user user = new user();
        user.setUserID(oldUser.getUserID());
        user.setUsername(oldUser.getUsername());
        user.setPassword(oldUser.getPassword());
        user.setUserType(oldUser.getUserType());

        String oldPass = request.getParameter("oldPass");
        String newPass = request.getParameter("newPass");
        String conPass = request.getParameter("conPass");

        if (oldPass.equals(user.getPassword())) {

            if (user.isValidPassword(newPass) == true) {

                if (conPass.equals(newPass)) {
                    user.setPassword(newPass);

                    if (user.getUserType().equals("Employee")) {
                        rd = request.getRequestDispatcher("employeemain.jsp");

                        user.setName(oldUser.getName());
                        user.setEmpID(oldUser.getEmpID());
                        user.setIc(oldUser.getIc());
                        user.setAddress(oldUser.getAddress());
                        user.setHp(oldUser.getHp());

                    } else if (user.getUserType().equals("Customer")) {
                        rd = request.getRequestDispatcher("cusmain.jsp");

                        user.setName(oldUser.getName());
                        user.setCusID(oldUser.getCusID());
                        user.setIc(oldUser.getIc());
                        user.setAddress(oldUser.getAddress());
                        user.setHp(oldUser.getHp());
                    }

                    int status = userDao.update(user);

                    if (status > 0) {
                        session.removeAttribute("login");
                        session.setAttribute("login", user);
                        rd.include(request, response);
                    } else {
                        request.setAttribute("updateError", "Unable to change password..!");
                        request.getRequestDispatcher("changePass.jsp").include(request, response);
                    }
                } else {
                    request.setAttribute("confirmPassError", "New password and confirm password are different");
                    request.getRequestDispatcher("changePass.jsp").include(request, response);
                }

            } else {
                request.setAttribute("passwordError", "Must contains 8-20 charcters, a lowercase, an uppercase, a special symbol..!");
                request.getRequestDispatcher("changePass.jsp").include(request, response);
            }
        } else {
            request.setAttribute("passInvalid", "This is not your old password..!");
            request.getRequestDispatcher("changePass.jsp").include(request, response);
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
