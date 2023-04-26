<%@page import="com.model.user"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body>
        <jsp:include page="navBar.jsp"/>
        <%
            user user = (user) session.getAttribute("login");
        %>
        <section class="right">
            <h1>Bicycle Rental Management System</h1>
        </section>

        <div class="center1">
            <h3>Personal Details</h3>
            <small style="color: red;">${updateError}</small>
            <div class="position1">
                <form method="post" action="#">
                    <table class="table2-1">
                        <tr>
                            <td>Name:</td>
                            <td><%=user.getName()%></td>
                        </tr>
                        <tr>
                            <td>IC:</td>
                            <td><%=user.getIc()%></td>
                        </tr>
                        <tr>
                            <td>Address: </td>
                            <td><%=user.getAddress()%></td>
                        </tr>
                        <tr>
                            <td>Phone Number: </td>
                            <td><%=user.getHp()%></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <button type="submit" name="editbtn"><a href="upPersonalDetails.jsp">Edit</a></button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
