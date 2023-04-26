<%@page import="com.model.payment"%>
<%@page import="com.dao.paymentDao"%>
<%@page import="com.dao.bicycleDao"%>
<%@page import="com.model.bicycle"%>
<%@page import="java.util.List"%>
<%@page import="com.model.rentalinfo"%>
<%@page import="com.dao.rentalDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    int year = Integer.parseInt(request.getParameter("year"));
    int month = Integer.parseInt(request.getParameter("month")) + 1;

    List<rentalinfo> list = rentalDao.getRentalInfoByMonthYear(year, month);
%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="header.jsp"/>
    </head>
    <body>
        <jsp:include page="navBar.jsp"/>
        <section class="right">
            <h1>Bicycle Rental Management System</h1>
        </section>
        <div class="center1">
            <h3>Bicycle Rental Report <%=month%>/<%=year%></h3>
            <div class="position1">
                <table class='table2' border='1' width='95%'>
                    <tr>
                        <th>Rental ID</th>
                        <th>Bicycle Name</th>
                        <th>Bicycle Type</th>
                        <th>Rental Date</th>
                        <th>Return Date</th>
                        <th>Days Rented</th>
                        <th>Amount (RM)</th>
                    </tr>
                    <%
                        for (rentalinfo e : list) {
                            bicycle bic = bicycleDao.getBicById(e.getBicID());
                            payment pym = paymentDao.getPaymentByID(e.getRentID());
                    %>
                    <tr>
                        <td><%=e.getRentID()%></td>
                        <td><%=bic.getBicName()%></td>
                        <td><%=bic.getBicType()%></td>
                        <td><%=e.getRental_date()%></td>
                        <td><%=e.getReturn_date()%></td>
                        <td><%=e.dayDiff()%></td>
                        <td><%=String.format("%.2f", pym.getAmount())%></td>
                    </tr>
                    <%}%>
                </table>
                <a href="report.jsp?year=<%=year%>"><button>Back</button></a>
            </div>
        </div>
    </body>
</html>
