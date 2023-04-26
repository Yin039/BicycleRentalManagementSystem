<%@page import="com.model.user"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <%
            user user = (user) session.getAttribute("login");

            if (user.getUserType().equals("Owner")) {
        %>
        <div>
            <div class="left">
                <div class="navbar">
                    <div class="subnav">
                        <button class="subnavbtn"><%=user.getUsername()%> &nbsp;<i class="fa fa-caret-down"></i></button>
                        <div class="subnav-content">
                            <a href="changePass.jsp">Change Account Details</a>
                            <a href="logout.jsp">Sign Out</a>
                        </div>
                    </div>
                    <a href="ownermain.jsp">Bicycle Information</a>

                    <div class="subnav">
                            <a href="manageAccount.jsp">Manage Account</a>
                    </div> 

                    <a href="report.jsp">View Report</a>
                </div>
            </div>
        </div>
        <%} else if (user.getUserType().equals("Employee")) {%>
        <div>
            <div class="left">
                <div class="navbar">
                    <div class="subnav">
                        <button class="subnavbtn"><%=user.getName()%> &nbsp;<i class="fa fa-caret-down"></i></button>
                        <div class="subnav-content">
                            <a href="changePass.jsp">Change Account Details</a>
                            <a href="logout.jsp">Sign Out</a>
                        </div>
                    </div>

                    <div class="subnav">
                        <button class="subnavbtn">Bicycle Information &nbsp;<i class="fa fa-caret-down"></i></button>
                        <div class="subnav-content">
                            <a href="employeemain.jsp">Bicycle List</a>
                            <a href="bicList.jsp">Manage Bicycle</a>
                        </div>
                    </div>

                    <a href="personalDetails.jsp">Personal Details</a>
                </div>
            </div>
        </div>
        <%} else if (user.getUserType().equals("Customer")) {%>
        <div>
            <div class="left">
                <div class="navbar">
                    <div class="subnav">
                        <button class="subnavbtn"><%=user.getName()%> &nbsp;<i class="fa fa-caret-down"></i></button>
                        <div class="subnav-content">
                            <a href="changePass.jsp">Change Account Details</a>
                            <a href="logout.jsp">Sign Out</a>
                        </div>
                    </div>
                        
                    <div class="subnav">
                        <button class="subnavbtn">Bicycle List &nbsp;<i class="fa fa-caret-down"></i></button>
                        <div class="subnav-content">
                            <a href="cusmain.jsp">Make Reservation</a>
                            <a href="reservedList.jsp">Your Reservation</a>
                        </div>
                    </div>
                        
                    <a href="personalDetails.jsp">Personal Details</a>
                </div>
            </div>
        </div>
        <%}%>
    </body>
</html>
