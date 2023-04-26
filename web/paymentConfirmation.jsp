<%@page import="com.model.payment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body>
        <%
            payment pym = (payment) session.getAttribute("pym");
        %>
        <section class="right-1">
            <h1>Bicycle Rental Management System</h1>
        </section>
        <div class="center-1">
            <h3>Payment Confirmation</h3>
            <div class="pos2-1">
                <form name="createFrom" method="post" action="rentalServlet?action=CONFIRM">
                    <table class="table-1">
                        <tr>
                            <td>Total Amount: </td>
                            <td>RM <%=String.format("%.2f", pym.getAmount())%></td>
                        </tr>

                        <tr>
                            <td>Payment Type: </td>
                            <td><%=pym.getPayment_type()%></td>
                        </tr>

                        <%if (pym.getPayment_type().equals("Card")) {%>
                        <tr>
                            <td>Name on Card: </td>
                            <td><%=pym.getCardName()%></td>
                        </tr>
                        <tr>
                            <td>Card Number: </td>
                            <td><%=pym.getCardNum()%></td>
                        </tr>
                        <tr>
                            <td>Exp: </td>
                            <td><%=pym.getCardExp()%></td>
                        </tr>
                        <tr>
                            <td>CVV: </td>
                            <td><%=pym.getCardCVV()%></td>
                        </tr>
                        <%}%>

                        <tr>
                            <td colspan="2">
                                <button type="submit" name="submitbtn">Confirm</button>
                                <button type="reset" name="btnSubmit" value="Cancel"><a href="rentBic.jsp">Cancel</a></button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div> 
        </div>
    </body>
</html>
