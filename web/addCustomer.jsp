<%@page import="com.model.user"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body>
        <%
            user cus = (user) session.getAttribute("customer");
        %>
        <section class="right-1">
            <h1>Bicycle Rental Management System</h1>
        </section>

        <div class="center2">
            <h3>Add Customer</h3>
            <small style="color: red;">${addCusError}</small>
            <div class="pos3">
                <form method="post" action="AddAccountServlet?action=ADD">
                    <table class="table1">
                        <tr>
                            <td>Name:</td>
                            <td><input type="text" name="name" size="50" value="<%=cus.getName()%>"></td>
                        </tr>
                        <tr>
                            <td>IC:</td>
                            <td><input type="text" name="ic" value="<%=cus.getIc()%>" placeholder="Exp:xxxxxx-xx-xxxx"></td>
                        </tr>
                        <tr>
                            <td colspan="2"><small style="color: red;">${icError}</small></td>
                        </tr>
                        <tr>
                            <td>Address: </td>
                            <td><input type="text" size="100" name="address" required></td>
                        </tr>
                        <tr>
                            <td>Phone Number: </td>
                            <td><input type="text" name="Hp" required placeholder="Not contain '-'"></td>
                        </tr>
                        <tr>
                            <td colspan="2"><small style="color: red;">${hpError}</small></td>
                        </tr>
                        <tr>
                            <td><input type="hidden" name="type" value="Customer"/></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <button type="submit" name="submitbtn">Save</button>
                                <button type="reset" name="btnCancel"><a href="rentBic.jsp">Cancel</a></button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
