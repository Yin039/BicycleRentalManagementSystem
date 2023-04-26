<%@page import="com.dao.paymentDao"%>
<%@page import="com.dao.bicycleDao"%>
<%@page import="java.util.List"%>
<%@page import="com.model.bicycle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <script>
        window.onload = function () {
        <%
            String type = "All";
            List<bicycle> list = bicycleDao.getAllBicycle();

            if (request.getParameter("type") != null) {
                type = request.getParameter("type");
                if (type.equals("All")) {
                    list = bicycleDao.getAllBicycle();
                } else {
                    list = bicycleDao.getBicycleByType(type);
                }
            }
        %>
        };
    </script>
    <body>
        <jsp:include page="navBar.jsp"/>
        <section class="right">
            <h1>Bicycle Rental Management System</h1>
        </section>
        <div class="center1">
            <h3>Bicycle Information</h3>
            <small style="color: red;">${deleteError}</small>
            <div class="position1">
                <div class="position2">
                    <select name="catogery" onchange="window.location = 'ownermain.jsp?type=' + this.value">

                        <option value="<%=type%>" selected style="display:none;"><%=type%></option>
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
                        <th>Condition</th>
                        <th>Status</th>
                        <th colspan="2">Action</th>
                    </tr>

                    <%for (bicycle e : list) {%>
                    <tr>
                        <td><%=e.getBicID()%></td>
                        <td><%=e.getBicName()%></td>
                        <td><%=e.getBicType()%></td>
                        <td>RM <%=String.format("%.2f", e.getBicRentPrice())%></td>
                        <td><%=e.getBicCondition()%></td>
                        <td><%=e.getBicStatus()%></td>

                        <%if (!e.getBicStatus().equals("Rented")) {%>
                        <td><a href='updateBicServlet?id=<%=e.getBicID()%>&action=GET'><img class="icon_size" src="img/edit.png"></a></td>
                        <td><a href="DeleteBicServlet?id=<%=e.getBicID()%>"><img class="icon_size" src="img/Flat_cross_icon.svg.png"></a></td>
                                <%} else {%>
                        <td colspan="2" style="color:rgba(170, 177, 180, 0.8);"><i>Not Available</i></td>
                        <%}%>
                    </tr>
                    <%}%>
                </table>
                <a href="addbicycle.jsp"><button>Add Bicycle</button></a>
            </div>
        </div>
    </body>
</html>
