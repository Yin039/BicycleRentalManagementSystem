<%@page import="com.model.payment"%>
<%@page import="com.model.bicycle"%>
<%@page import="com.dao.bicycleDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body>
        <jsp:include page="navBar.jsp"/>
        <section class="right">
            <h1>Bicycle Rental Management System</h1>
        </section>
        <div class="center1">
            <h3>Bicycle Information</h3>
            <div class="position1 ">
                <%
                    payment payment = new payment();
                    List<bicycle> list = bicycleDao.getAllBicycle();
                %>
                <table class='table2' border='1' width='95%'>
                    <tr>
                        <th>Bicycle ID</th>
                        <th>Bicycle Name</th>
                        <th>Bicycle Type</th>
                        <th>Rental Price per Day</th>
                        <th>Bicycle Condition</th>
                        <th>Bicycle Status</th>
                    </tr>

                    <%for (bicycle e : list) {%>
                    <tr>
                        <td><%=e.getBicID()%></td>
                        <td><%=e.getBicName()%></td>
                        <td><%=e.getBicType()%></td>
                        <td>RM <%=String.format("%.2f", e.getBicRentPrice())%></td>
                        <td><%=e.getBicCondition()%></td>
                        <td><%=e.getBicStatus()%></td>
                    </tr>
                    <%}%>

                </table>
            </div>
        </div>
    </body>
</html>
