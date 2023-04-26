<%@page import="java.util.List"%>
<%@page import="com.dao.bicycleDao"%>
<%@page import="com.model.bicycle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <script>
        window.onload = function () {
        <%
            String type = null;
            List<bicycle> list = bicycleDao.getBicycleByStatus("Unrented");

            if (request.getParameter("type") != null) {
                type = request.getParameter("type");
                if (type.equals("All")) {
                    list = bicycleDao.getBicycleByStatus("Unrented");
                } else {
                    list = bicycleDao.getBicycleToResByType(type);
                }
            }
        %>
        }
    </script>
    <body>
        <jsp:include page="navBar.jsp"/>
        <section class="right">
            <h1>Bicycle Rental Management System</h1>
        </section>
        <div class="center1">
            <h3>Bicycle Information</h3>
            <small style="color: red;">${reservedError}</small>
            <div class="position1">
                <div class="position2">
                    <select name="catogery" onchange="window.location = 'cusmain.jsp?type=' + this.value">
                        <%if (type != null) {%>
                        <option value="<%=type%>" selected style="display:none;"><%=type%></option>
                        <%}%>
                        <option value="All">All</option>
                        <option value="Road Bike">Road Bike</option>
                        <option value="Mountain Bike">Mountain Bike</option>
                        <option value="Touring Bike">Touring Bike</option>
                        <option value="Folding Bike">Folding Bike</option>
                        <option value="BMX">BMX</option>
                        <option value="Electric Bike">Electric Bike</option>
                    </select>
                </div>

                <table class='table2' border='1' width='95%'>
                    <tr>
                        <th>Bicycle ID</th>
                        <th>Bicycle Name</th>
                        <th>Bicycle Type</th>
                        <th>Rental Price per Day</th>
                        <th></th>
                    </tr>

                    <%for (bicycle e : list) {%>
                    <tr>
                        <td><%=e.getBicID()%></td>
                        <td><%=e.getBicName()%></td>
                        <td><%=e.getBicType()%></td>
                        <td>RM <%=String.format("%.2f", e.getBicRentPrice())%></td>
                        <td><a href="reservationServlet?id=<%=e.getBicID()%>&action=SELECT">To Reserve</a></td>
                    </tr>
                    <%}%>
                </table>
            </div>
        </div>
    </body>
</html>
