<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.model.reservation"%>
<%@page import="com.model.bicycle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body>
        <%
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            bicycle bic = (bicycle) session.getAttribute("bicSelected");
            reservation reserved = (reservation) session.getAttribute("reservation");
        %>
        <section class="right-1">
            <h1>Bicycle Rental Management System</h1>
        </section>
        <div class="center-1">
            <h3>Bicycle Rental Reservation</h3>
            <div class="pos2-1">
                <form name="createFrom" method="post" action="reservationServlet?action=CONFIRM">
                    <table class="table-1">
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
                            <td><%=sdf.format(reserved.getRes_date())%></td>
                        </tr>
                        <tr>
                                <td colspan="2">
                                    <button type="submit" name="submitbtn">Confirm</button>
                                    <button type="reset" name="btnSubmit" value="Cancel"><a href="reservation.jsp">Back</a></button>
                                </td>
                            </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
