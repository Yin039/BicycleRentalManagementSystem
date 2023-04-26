<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.model.rentalinfo"%>
<%@page import="com.model.payment"%>
<%@page import="com.model.user"%>
<%@page import="com.model.bicycle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body>
        <%
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            bicycle bic = (bicycle) session.getAttribute("bicycle");
            payment pym = (payment) session.getAttribute("pym");
            rentalinfo rentinfo = (rentalinfo) session.getAttribute("rentinfo");
            user cus = (user) session.getAttribute("customer");

            pym.calculateTotal(rentinfo.getRental_date(), rentinfo.getReturn_date(), bic.getBicRentPrice());
        %>
        <section class="right-1">
            <h1>Bicycle Rental Management System</h1>
        </section>
        <div class="center-1">
            <h3>Rental Confirmation</h3>
            <div class="pos2-1">
                <table class="table-1">
                    <tr>
                        <td colspan="2"><h4>Bicycle Information</h4></td>
                    </tr>
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
                        <td colspan="2"><h4>Customer Information</h4></td>
                    </tr>
                    <tr>
                        <td>Customer Name:</td>
                        <td><%=cus.getName()%></td>
                    </tr>
                    <tr>
                        <td>Customer IC:</td>
                        <td><%=cus.getIc()%></td>
                    </tr>
                    <tr>
                        <td>Customer Address:</td>
                        <td><%=cus.getAddress()%></td>
                    </tr>
                    <tr>
                        <td>Customer Phone Number:</td>
                        <td><%=cus.getHp()%></td>
                    </tr>

                    <tr>
                        <td colspan="2"><h4>Rental Information</h4></td>
                    </tr>
                    <tr>
                        <td>Rental Date:</td>
                        <td><%=sdf.format(rentinfo.getRental_date())%></td>
                    </tr>
                    <tr>
                        <td>Return Date:</td>
                        <td><%=sdf.format(rentinfo.getReturn_date())%></td>
                    </tr>
                    <tr>
                        <td><i class="fab fa-cc-stripe"></i>&nbsp;Payment Type:</td>
                        <td><%=pym.getPayment_type()%></td>
                    </tr>
                    <tr>
                        <td>Total Amount:</td>
                        <td>RM <%=String.format("%.2f", pym.getAmount())%></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <a href="addPayment.jsp"><button name="submitbtn">Next</button></a>
                            <a href="rentBic.jsp"><button name="btnSubmit">Back</button></a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </body>
</html>