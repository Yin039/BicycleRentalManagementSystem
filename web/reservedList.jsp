<%@page import="java.time.ZoneId"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Date"%>
<%@page import="com.dao.userDao"%>
<%@page import="com.model.bicycle"%>
<%@page import="com.dao.bicycleDao"%>
<%@page import="java.util.List"%>
<%@page import="com.model.reservation"%>
<%@page import="com.dao.reservationDao"%>
<%@page import="com.model.user"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body>
        <jsp:include page="navBar.jsp"/>
        <section class="right">
            <h1>Bicycle Rental Management System</h1>
        </section>
        <div class="center1">
            <h3>Reserved Bicycle List</h3>
            <small style="color: red;">${reservedError}</small>
            <div class="position1">
                <table class='table2' border='1' width='95%'>
                    <%
                        Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
                        
                        user user = (user) session.getAttribute("login");

                        List<reservation> reservedByCus = reservationDao.getReservedListByCusID(user.getCusID());
                    %>
                    <tr>
                        <th>Bicycle ID</th>
                        <th>Bicycle Name</th>
                        <th>Bicycle Type</th>
                        <th>Rental Price per Day</th>
                        <th>Reserved Rental Date</th>
                        <th>Action</th>
                    </tr>
                    <%
                        for (reservation e : reservedByCus) {
                            bicycle bic = bicycleDao.getBicById(e.getBic_ID());
                    %>
                    <tr>
                        <td><%=bic.getBicID()%></td>
                        <td><%=bic.getBicName()%></td>
                        <td><%=bic.getBicType()%></td>
                        <td>RM <%=String.format("%.2f", bic.getBicRentPrice())%></td>
                        <%if (e.getRes_date().before(today)) {%>
                        <td><a href="reservationServlet?id=<%=e.getRes_ID()%>&action=TOUPDATE" style="color: red;"><%=e.getRes_date()%></a></td>
                            <%} else {%>
                        <td><a href="reservationServlet?id=<%=e.getRes_ID()%>&action=TOUPDATE"><%=e.getRes_date()%></a></td>
                            <%}%>
                        <td><a href="reservationServlet?id=<%=e.getRes_ID()%>&action=DELETE"><img class="icon_size" src="img/Flat_cross_icon.svg.png"></a></td>
                    </tr>
                    <%}%>
                </table>
            </div>
        </div>
    </body>
</html>
