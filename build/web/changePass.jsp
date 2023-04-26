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

        <div class="center-1">
            <h3>Change Password</h3>
            <small style="color: red;">${updateError}</small>
            <div class="pos2-1">
                <form method="post" action="ChangePassServlet?userType=<%=user.getUserType()%>">
                    <table class="table-1">
                        <tr>
                            <td>Username</td>
                            <td><input type="text" name="username" value="<%=user.getUsername()%>"></td>
                        </tr>
                        <tr>
                            <td>Old Password: </td>
                            <td><input type="password" placeholder="Key in old password" name="oldPass" maxlength="26"></td>
                        </tr>
                        <tr>
                            <td colspan="2"><small style="color: red;">${passInvalid}</small></td>
                        </tr>
                        <tr>
                            <td>New Password: </td>
                            <td><input type="password" placeholder="Key in new password" name="newPass" maxlength="26"></td>
                        </tr>
                        <tr>
                            <td colspan="2"><small style="color: red;">${passwordError}</small></td>
                        </tr>
                        <tr>
                            <td>Confirm Password: </td>
                            <td><input type="password" placeholder="Key in new password" name="conPass" maxlength="26"></td>
                        </tr>
                        <tr>
                            <td colspan="2"><small style="color: red;">${confirmPassError}</small></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <button type="submit" name="submitbtn">Save</button>

                                <%if (user.getUserType().equals("Owner")) {%>
                                <button type="reset" name="btnCancel"><a href="ownermain.jsp">Cancel</a></button>

                                <%} else if (user.getUserType().equals("Employee")) {%>
                                <button type="reset" name="btnCancel"><a href="employeemain.jsp">Cancel</a></button>

                                <%} else if (user.getUserType().equals("Customer")) {%>
                                <button type="reset" name="btnCancel"><a href="cusmain.jsp">Cancel</a></button>

                                <%}%>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
