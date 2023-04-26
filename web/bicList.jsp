<%@page import="com.model.user"%>
<%@page import="com.dao.userDao"%>
<%@page import="com.dao.reservationDao"%>
<%@page import="com.model.reservation"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.util.DBConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.model.rented"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.ZoneId"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Date"%>
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
            Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

            String status = "Unrented";
            List<bicycle> list = bicycleDao.getBicycleByStatus(status);

            if (request.getParameter("status") != null) {
                status = request.getParameter("status");
                list = bicycleDao.getBicycleByStatus(status);
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
            <h3><%=status%> Bicycle Information</h3>
            <small style="color: red;">${rentalError}</small>
            <div class="position1">
                <div class="position2">
                    <select name="catogery" onchange="window.location = 'bicList.jsp?status=' + this.value">
                        <option value="<%=status%>" selected style="display:none;"><%=status%></option>
                        <option value="Unrented">Unrented</option>
                        <option value="Rented">Rented</option>
                        <option value="Repairing">Repairing</option>
                        <option value="Reserved">Reserved</option>
                    </select>
                </div>

                <%if (status.equals("Unrented")) {%>        
                <table class='table2' border='1' width='95%'>
                    <tr>
                        <th>Bicycle ID</th>
                        <th>Bicycle Name</th>
                        <th>Bicycle Type</th>
                        <th>Rental Price per Day</th>
                        <th>Condition</th>
                        <th>Status</th>
                    </tr>

                    <%for (bicycle e : list) {%>
                    <tr>
                        <td><%=e.getBicID()%></td>
                        <td><%=e.getBicName()%></td>
                        <td><%=e.getBicType()%></td>
                        <td>RM <%=String.format("%.2f", e.getBicRentPrice())%></td>
                        <td><a href='updateBicServlet?id=<%=e.getBicID()%>&action=CONDITION'><%=e.getBicCondition()%></a></td>
                        <td><a href="updateBicServlet?id=<%=e.getBicID()%>&action=GETUNRENTED"><%=e.getBicStatus()%></a></td>
                    </tr>
                    <%}%>
                </table>

                <%} else if (status.equals("Rented")) {

                    List<rented> rentedList = new ArrayList<rented>();

                    Connection con = DBConnection.getConnection();
                    PreparedStatement ps = con.prepareStatement(
                            "Select A.rent_ID,  B.bic_ID, B.bic_name, B.bic_type, C.cus_name, C.cus_Hp, A.rental_date, A.return_date, B.bic_status "
                            + "from rentalinfo as A, bicycle as B, customer as C "
                            + "where A.bic_ID = B.bic_ID and A.cus_ID = C.cus_ID and B.bic_status = 'Rented' and A.trade = 'Undergoing'"
                    );

                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        rented rentalinfo = new rented();
                        rentalinfo.setRentID(rs.getInt("rent_ID"));
                        rentalinfo.setBicID(rs.getInt("bic_ID"));
                        rentalinfo.setRental_date(rs.getDate("rental_date"));
                        rentalinfo.setReturn_date(rs.getDate("return_date"));
                        rentalinfo.setBicName(rs.getString("bic_name"));
                        rentalinfo.setBicType(rs.getString("bic_type"));
                        rentalinfo.setBicStatus(rs.getString("bic_status"));
                        rentalinfo.setName(rs.getString("cus_name"));
                        rentalinfo.setHp(rs.getString("cus_Hp"));

                        rentedList.add(rentalinfo);
                    }
                %>
                <table class='table2' border='1' width='95%'>
                    <tr>
                        <th>Rental ID</th>
                        <th>Bicycle ID</th>
                        <th>Bicycle Name</th>
                        <th>Bicycle Type</th>
                        <th>Customer Name</th>
                        <th>Customer HP</th>
                        <th>Rental Date</th>
                        <th>Return Date</th>
                        <th>Status</th>
                    </tr>

                    <%for (rented a : rentedList) {%>
                    <tr>
                        <td><%=a.getRentID()%></td>
                        <td><%=a.getBicID()%></td>
                        <td><%=a.getBicName()%></td>
                        <td><%=a.getBicType()%></td>
                        <td><%=a.getName()%></td>
                        <td><%=a.getHp()%></td>
                        <td><%=a.getRental_date()%></td>

                        <%if (a.getReturn_date().before(today)) {%>
                        <td><a href="rentalServlet?rentID=<%=a.getRentID()%>&action=EXTEND" style="color: red;"><%=a.getReturn_date()%></a></td>
                            <%} else {%>
                        <td><a href="rentalServlet?rentID=<%=a.getRentID()%>&action=EXTEND"><%=a.getReturn_date()%></a></td>
                            <%}%>
                        <td><a href="rentalServlet?rentID=<%=a.getRentID()%>&action=RETURN"><%=a.getBicStatus()%></a></td>
                    </tr>
                    <%}%>
                </table>
                <%} else if (status.equals("Repairing")) {%>
                <table class='table2' border='1' width='95%'>
                    <tr>
                        <th>Bicycle ID</th>
                        <th>Bicycle Name</th>
                        <th>Bicycle Type</th>
                        <th>Rental Price per Day</th>
                        <th>Condition</th>
                        <th>Status</th>
                    </tr>

                    <%for (bicycle e : list) {%>
                    <tr>
                        <td><%=e.getBicID()%></td>
                        <td><%=e.getBicName()%></td>
                        <td><%=e.getBicType()%></td>
                        <td>RM <%=String.format("%.2f", e.getBicRentPrice())%></td>
                        <td><a href='updateBicServlet?id=<%=e.getBicID()%>&action=CONDITION'><%=e.getBicCondition()%></a></td>
                        <td><%=e.getBicStatus()%></a></td>
                    </tr>
                    <%}%>
                </table>
                <%
                } else if (status.equals("Reserved")) {
                    List<reservation> reserved = reservationDao.getReservedList();
                %>
                <table class='table2' border='1' width='95%'>
                    <tr>
                        <th>Bicycle ID</th>
                        <th>Bicycle Name</th>
                        <th>Bicycle Type</th>
                        <th>Bicycle Condition</th>
                        <th>Customer Name</th>
                        <th>Customer HP</th>
                        <th>Reserved Date</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                    <%
                        for (reservation e : reserved) {
                            bicycle bic = bicycleDao.getBicById(e.getBic_ID());
                            user cus = userDao.getByCusID(e.getCus_ID());
                    %>
                    <tr>
                        <td><%=bic.getBicID()%></td>
                        <td><%=bic.getBicName()%></td>
                        <td><%=bic.getBicType()%></td>
                        <td><a href='updateBicServlet?id=<%=bic.getBicID()%>&action=CONDITION'><%=bic.getBicCondition()%></a></td>
                        <td><%=cus.getName()%></td>
                        <td><%=cus.getHp()%></td>
                        <%if (e.getRes_date().before(today)) {%>
                        <td><a href="reservationServlet?id=<%=e.getRes_ID()%>&action=TOUPDATE" style="color: red;"><%=e.getRes_date()%></a></td>
                            <%} else {%>
                        <td><a href="reservationServlet?id=<%=e.getRes_ID()%>&action=TOUPDATE"><%=e.getRes_date()%></a></td>
                            <%}%>

                        <td><a href="updateBicServlet?id=<%=bic.getBicID()%>&action=GETRESERVED&resID=<%=e.getRes_ID()%>"><%=bic.getBicStatus()%></a></td>
                        <td><a href="reservationServlet?id=<%=e.getRes_ID()%>&action=DELETE"><img class="icon_size" src="img/Flat_cross_icon.svg.png"></a></td>
                    </tr>
                    <%}%>
                </table>
                <%}%>
            </div>
        </div>
    </body>
</html>
