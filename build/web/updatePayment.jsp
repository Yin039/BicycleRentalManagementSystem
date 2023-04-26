<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.model.rentalinfo"%>
<%@page import="com.model.payment"%>
<%@page import="com.model.bicycle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body>
        <%
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            payment oldPym = (payment) session.getAttribute("extendPay");

            bicycle bic = (bicycle) session.getAttribute("bicExtend");
            payment pym = (payment) session.getAttribute("newPay");
            rentalinfo rentinfo = (rentalinfo) session.getAttribute("extendRent");

            pym.calculateTotal(rentinfo.getRental_date(), rentinfo.getReturn_date(), bic.getBicRentPrice());

            double addon = pym.getAmount() - oldPym.getAmount();
        %>
        <section class="right-1">
            <h1>Bicycle Rental Management System</h1>
        </section>
        <div class="center-1">
            <h3>Payment Information - Extend Return Date</h3>
            <small style="color: red;">${addPayError}</small>
            <div class="pos2-1">
                <form name="createFrom" method="post" action="paymentServlet?action=ADDON">
                    <table class="table-1">
                        <tr>
                            <td>Rent Date: </td>
                            <td><%=sdf.format(rentinfo.getRental_date())%></td>
                        </tr>
                        <tr>
                            <td>Return Date: </td>
                            <td><%=sdf.format(rentinfo.getReturn_date())%></td>
                        </tr>
                        <tr>
                            <td>Total Amount: </td>
                            <td>RM <%=String.format("%.2f", pym.getAmount())%></td>
                        </tr>
                        <tr>
                            <td>Amount to Pay: </td>
                            <td>RM <%=String.format("%.2f", addon)%></td>
                        </tr>
                        <tr>
                            <td>Payment Type: </td>
                            <td><%=pym.getPayment_type()%></td>
                        </tr>

                        <%if (pym.getPayment_type().equals("Card")) {%>
                        <tr>
                            <td>Name on Card</td>
                            <td><input type="text" name="cardHolder" value="<%=pym.getCardName()%>" required></td>
                        </tr>
                        <tr>
                            <td>Card Number</td>
                            <td><input type="number" name="cardNumber" value="<%=pym.getCardNum()%>" required></td>
                        </tr>
                        <tr>
                            <td>Exp</td>
                            <td><input type="month" name="exp" value="<%=pym.getCardExp()%>" required></td>
                        </tr>
                        <tr>
                            <td>CVV</td>
                            <td><input type="number" name="cvv" value="<%=pym.getCardCVV()%>" required></td>
                        </tr>

                        <%} else if (pym.getPayment_type().equals("E-Wallet")) {%>
                        <div class="card">
                            <img src="img/qr-code.jpeg" alt="">
                            <p>Scan the qr code to pay</p>
                        </div>
                        <%}%>

                        <tr>
                            <td colspan="2">
                                <button type="submit" name="submitbtn">Next</button>
                                <button type="reset" name="btnSubmit" value="Cancel"><a href="extendDate.jsp">Cancel</a></button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
