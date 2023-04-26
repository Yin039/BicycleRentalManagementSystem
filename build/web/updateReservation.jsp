<%@page import="com.model.user"%>
<%@page import="com.model.reservation"%>
<%@page import="com.model.bicycle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body>
        <jsp:include page="navBar.jsp"/>
        <%
            bicycle bic = (bicycle) session.getAttribute("bicSelected");
            reservation selected = (reservation) session.getAttribute("reservation");
            user currentUser = (user) session.getAttribute("login");
        %>
        <section class="right">
            <h1>Bicycle Rental Management System</h1>
        </section>
        <div class="center1">
            <h3>Bicycle Rental Reservation</h3>
            <div class="pos1">
                <div class="pos1-1">
                    <form name="createFrom" method="post" action="reservationServlet?action=UPDATE">
                        <table class="table1-1">
                            <tr>
                                <td>Bicycle ID:</td>
                                <td><%=bic.getBicID()%></td>
                            </tr>
                            <tr>
                                <td>Bicycle Name:</td>
                                <td><%=bic.getBicName()%></td>
                            </tr>
                            <tr>
                                <td>Bicycle Type:</td>
                                <td><%=bic.getBicType()%></td>
                            </tr>
                            <tr>
                                <td>Reserved Rental Date:</td>
                                <td><input type="date" name="reservedDate" min="<%=java.time.LocalDate.now()%>" value="<%=selected.getRes_date()%>"></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <button type="submit" name="submitbtn">Done</button>
                                    <%if (currentUser.getUserType().equals("Customer")) {%>
                                    <button type="reset" name="btnSubmit" value="Cancel"><a href="cusmain.jsp">Cancel</a></button>
                                    <%} else if (currentUser.getUserType().equals("Employee")) {%>
                                    <button type="reset" name="btnSubmit" value="Cancel"><a href="bicList.jsp?status=Reserved">Cancel</a></button>
                                    <%}%>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
