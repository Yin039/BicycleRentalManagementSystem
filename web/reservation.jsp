<%@page import="com.model.bicycle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body>
        <%
            bicycle bic = (bicycle) session.getAttribute("bicSelected");
        %>
        <section class="right-1">
            <h1>Bicycle Rental Management System</h1>
        </section>
        <div class="center-1">
            <h3>Bicycle Rental Reservation</h3>
            <div class="pos2-1">
                <form name="createFrom" method="post" action="reservationServlet?action=RESERVATION">
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
                            <td><input type="date" name="reservedDate" min="<%=java.time.LocalDate.now()%>"></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <button type="submit" name="submitbtn">Next</button>
                                <button type="reset" name="btnSubmit" value="Cancel"><a href="cusmain.jsp">Cancel</a></button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
