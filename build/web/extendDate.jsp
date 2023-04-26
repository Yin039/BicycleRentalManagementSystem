<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.model.bicycle"%>
<%@page import="com.model.payment"%>
<%@page import="com.model.rentalinfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body>
        <%
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            rentalinfo extendRent = (rentalinfo) session.getAttribute("extendRent");
            bicycle bicExtend = (bicycle) session.getAttribute("bicExtend");

        %>
        <section class="right-1">
            <h1>Bicycle Rental Management System</h1>
        </section>
        <div class="center-1">
            <h3>Extend Return Date</h3>
            <div class="pos2-1">
                <form name="createFrom" method="post" action="rentalServlet?action=CONFIRMREXTEND">
                    <table class="table-1">
                        <tr>
                            <td colspan="2"><h4>Bicycle Information</h4></td>
                        </tr>
                        <tr>
                            <td>Bicycle ID:</td>
                            <td><%=bicExtend.getBicID()%></td>
                        </tr>
                        <tr>
                            <td>Bicycle Name:</td>
                            <td><%=bicExtend.getBicName()%></td>
                        </tr>
                        <tr>
                            <td>Bicycle Type:</td>
                            <td><%=bicExtend.getBicType()%></td>
                        </tr>
                        <tr>
                            <td colspan="2"><h4>Rental Information</h4></td>
                        </tr>
                        <tr>
                            <td>Rental ID:</td>
                            <td><%=extendRent.getBicID()%></td>
                        </tr>
                        <tr>
                            <td>Rental date:</td>
                            <td><%=sdf.format(extendRent.getRental_date())%></td>
                        </tr>
                        <tr>
                            <td>Return date:</td>
                            <td><input type="date" name="returnDate" min="<%=java.time.LocalDate.now()%>"  value="<%=extendRent.getReturn_date()%>" required></td>
                        </tr>
                        <tr>
                            <td><i class="fab fa-cc-stripe"></i>&nbsp;Payment Type:</td>
                            <td>
                                <select name="paymentType">
                                    <option>Cash</option>
                                    <option>Card</option>
                                    <option>E-Wallet</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <button type="submit" name="submitbtn">Next</button>
                                <button type="reset" name="btnSubmit"><a href="bicList.jsp?status=<%=bicExtend.getBicStatus()%>">Cancel</a></button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
