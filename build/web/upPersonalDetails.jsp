<%@page import="com.model.user"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body>
        <%
            user user = (user) session.getAttribute("login");
        %>
        <section class="right-1">
            <h1>Bicycle Rental Management System</h1>
        </section>

        <div class="center2">
            <h3>Edit Personal Details</h3>
            <small style="color: red;">${updateError}</small>
            <div class="pos3">
                <form method="post" action="EditPersonalDetailsServlet">
                    <table class="table1">
                        <tr>
                            <td>Name:</td>
                            <td><input type="text" name="name" value="<%=user.getName()%>" readonly="readonly"></td>
                        </tr>
                        <tr>
                            <td>IC:</td>
                            <td><input type="text" name="ic" value="<%=user.getIc()%>" readonly="readonly"></td>
                        </tr>
                        <tr>
                            <td>Address: </td>
                            <td><input type="text" size="100" name="address" value="<%=user.getAddress()%>"></td>
                        </tr>
                        <tr>
                            <td>Phone Number: </td>
                            <td><input type="text" name="Hp" value="<%=user.getHp()%>" placeholder="Not contain '-'"></td>
                        </tr>
                        <tr>
                            <td colspan="2"><small style="color: red;">${hpError}</small></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <button type="submit" name="submitbtn">Save</button>
                                <button type="reset" name="btnCancel"><a href="personalDetails.jsp">Cancel</a></button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
