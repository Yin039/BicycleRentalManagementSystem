<%@page import="com.model.user"%>
<%@page import="com.model.bicycle"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body>
        <%
            bicycle bic = (bicycle) session.getAttribute("bicycle");

            user cus = (user) session.getAttribute("reservedBy");
        %>
        <section class="right-1">
            <h1>Bicycle Rental Management System</h1>
        </section>
        <div class="center2">
            <h3>Rented Bicycle Information</h3>
            <div class="pos3">
                <form name="createFrom" method="post" action="rentalServlet?action=RENT">
                    <table class="table1">
                        <tr>
                            <td>Bicycle ID:</td>
                            <td><input type="text" name="bicycleID" value="<%=bic.getBicID()%>" readonly="readonly"></td>
                        </tr>
                        <tr>
                            <td>Customer Name:</td>
                            <td><input type="text" name="cusName" 
                                <%
                                    if (cus != null) {
                                        out.print("value='" + cus.getName() + "' readonly='readonly'");
                                    }
                                %>
                                required>
                            </td>
                        </tr>
                        <tr>
                            <td>Customer IC:</td>
                            <td><input type="text" name="cusIc" 
                                <%
                                    if (cus != null) {
                                        out.print("value='" + cus.getIc() + "' readonly='readonly'");
                                    }
                                %>
                                required placeholder="Exp:xxxxxx-xx-xxxx">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><small style="color: red;">${icError}</small></td>
                        </tr>
                        <tr>
                            <td>Rental date:</td>
                            <td><input type="date" name="rentDate" value="<%=java.time.LocalDate.now()%>" readonly="readonly"></td>
                        </tr>
                        <tr>
                            <td>Return date:</td>
                            <td><input type="date" name="returnDate" min="<%=java.time.LocalDate.now()%>" required></td>
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
                            <td><input type="hidden" name="status" value="Rented"></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <button type="submit" name="submitbtn">Next</button>
                                <button type="reset" name="btnSubmit" value="Cancel"><a href="bicList.jsp?status=<%=bic.getBicStatus()%>">Cancel</a></button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
