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

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;");

        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        user user = userDao.login(username, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("login", user);

            if (user.getUserType().equals("Owner")) {
                response.sendRedirect("ownermain.jsp");
            } else if (user.getUserType().equals("Employee")) {
                response.sendRedirect("employeemain.jsp");
            } else if (user.getUserType().equals("Customer")) {
                response.sendRedirect("cusmain.jsp");
            }

        } else {
            request.setAttribute("loginError", "Invalid email or password..!");
            rd.include(request, response);
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
    }// </editor-fold>  
}
