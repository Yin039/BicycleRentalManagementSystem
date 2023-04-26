package com.controller;

import com.dao.userDao;
import com.model.rentalinfo;
import com.model.user;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddAccountServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;");
        RequestDispatcher rd;
        int status;

        String action = request.getParameter("action");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String ic = request.getParameter("ic");
        String address = request.getParameter("address");
        String hp = request.getParameter("Hp");
        String type = request.getParameter("type");

        switch (type) {
            case "Employee":
                rd = request.getRequestDispatcher("addEmployee.jsp");
                if (user.isValidPassword(password) == false) {
                    request.setAttribute("passwordError", "Must contains 8-20 charcters, a lowercase, an uppercase, a special symbol..!");
                    rd.forward(request, response);
                }
                if (user.isValidIcNo(ic) == false) {
                    request.setAttribute("icError", "Must in format xxxxxx-xx-xxxx");
                    rd.forward(request, response);
                }
                if (user.isValidPhnNo(hp) == false) {
                    request.setAttribute("hpError", "Must not contain '-'");
                    rd.forward(request, response);
                }
                if (user.isValidIcNo(ic) == true && user.isValidPhnNo(hp) == true && user.isValidPassword(password) == true) {
                    user e = new user();
                    e.setUsername(username);
                    e.setPassword(password);
                    e.setUserType(type);
                    e.setName(name);
                    e.setIc(ic);
                    e.setAddress(address);
                    e.setHp(hp);

                    status = userDao.save(e);

                    if (status > 0) {
                        request.getRequestDispatcher("manageAccount.jsp?type=Employee").include(request, response);
                    } else {
                        request.setAttribute("createError", "User Had Available");
                        rd.include(request, response);
                    }
                }
                break;
            case "Owner":
                rd = request.getRequestDispatcher("addOwner.jsp");
                if (user.isValidPassword(password) == false) {
                    request.setAttribute("passwordError", "Must contains 8-20 charcters, a lowercase, an uppercase, a special symbol..!");
                    rd.include(request, response);
                } else {
                    user e = new user();
                    e.setUsername(username);
                    e.setPassword(password);
                    e.setUserType(type);

                    status = userDao.save(e);

                    if (status > 0) {
                        request.getRequestDispatcher("manageAccount.jsp?type=Owner").include(request, response);
                    } else {
                        request.setAttribute("createError", "User Had Available");
                        rd.include(request, response);
                    }
                }
                break;
            case "Customer":
                user e = new user();

                if (action.equals("REGISTER")) {
                    rd = request.getRequestDispatcher("registration.jsp");

                    if (user.isValidIcNo(ic) == false) {
                        request.setAttribute("icError", "Must in format xxxxxx-xx-xxxx");
                        rd.forward(request, response);
                    }
                    if (user.isValidPhnNo(hp) == false) {
                        request.setAttribute("hpError", "Must not contain '-'");
                        rd.forward(request, response);
                    }

                    if (user.isValidPassword(password) == false) {
                        request.setAttribute("passwordError", "Must contains 8-20 charcters, a lowercase, an uppercase, a special symbol..!");
                        rd.forward(request, response);
                    }

                    if (user.isValidIcNo(ic) == true && user.isValidPhnNo(hp) == true && user.isValidPassword(password) == true) {
                        e.setUsername(username);
                        e.setPassword(password);
                        e.setUserType(type);
                        e.setName(name);
                        e.setIc(ic);
                        e.setAddress(address);
                        e.setHp(hp);

                        user availableCus = userDao.checkAvailable(name, ic);
                        if (availableCus == null) {
                            status = userDao.save(e);
                        } else {
                            e.setCusID(availableCus.getCusID());
                            status = userDao.addCusAcc(e);
                        }

                        if (status > 0) {
                            HttpSession session = request.getSession();
                            session.setAttribute("customer", e);

                            request.getRequestDispatcher("login.jsp").include(request, response);
                        } else {
                            request.setAttribute("createError", "Unable to create account");
                            rd.include(request, response);
                        }
                    }
                } else if (action.equals("ADD")) {
                    rd = request.getRequestDispatcher("addCustomer.jsp");
                    
                    if (user.isValidIcNo(ic) == false) {
                        request.setAttribute("icError", "Must in format xxxxxx-xx-xxxx");
                        rd.forward(request, response);
                    }
                    if (user.isValidPhnNo(hp) == false) {
                        request.setAttribute("hpError", "Must not contain '-'");
                        rd.forward(request, response);
                    }

                    if (user.isValidIcNo(ic) == true && user.isValidPhnNo(hp) == true) {
                        e.setName(name);
                        e.setIc(ic);
                        e.setAddress(address);
                        e.setHp(hp);

                        status = userDao.addCus(e);

                        if (status > 0) {
                            HttpSession session = request.getSession();
                            session.removeAttribute("customer");

                            e.setCusID(userDao.getCusID(name, ic));

                            session.setAttribute("customer", e);

                            rentalinfo rentinfo = (rentalinfo) session.getAttribute("rentinfo");
                            rentalinfo newInfo = new rentalinfo();
                            newInfo.setRental_date(rentinfo.getRental_date());
                            newInfo.setReturn_date(rentinfo.getReturn_date());
                            newInfo.setCusID(e.getCusID());

                            session.removeAttribute("rentinfo");
                            session.setAttribute("rentinfo", newInfo);

                            request.getRequestDispatcher("rentalConfirmation.jsp").include(request, response);
                        } else {
                            request.setAttribute("addCusError", "Unable to Add Customer Information");
                            rd.include(request, response);
                        }
                    }
                }
                break;
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
        } catch (SQLException ex) {
            Logger.getLogger(AddAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(AddAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
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
