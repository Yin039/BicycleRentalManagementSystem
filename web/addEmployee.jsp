<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body>
        <section class="right-1">
            <h1>Bicycle Rental Management System</h1>
        </section>

        <div class="center2">
            <h3>Add Employee</h3>
            <small style="color: red;">${createError}</small>
            <div class="pos3">
                <form method="post" action="AddAccountServlet">
                    <table class="table1">
                        <tr>
                            <td>Username</td>
                            <td><input type="text" name="username" required></td>
                        </tr>
                        <tr>
                            <td>Password: </td>
                            <td><input type="text" name="password" maxlength="26" placeholder="8-20 charcters, at least 1 lowercase, uppercase, special symbol"></td>
                        </tr>
                        <tr>
                            <td colspan="2"><small style="color: red;">${passwordError}</small></td>
                        </tr>
                        <tr>
                            <td>Name:</td>
                            <td><input type="text" name="name" required></td>
                        </tr>
                        <tr>
                            <td>IC:</td>
                            <td><input type="text" name="ic" required placeholder="Exp:xxxxxx-xx-xxxx"></td>
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
                            <td><input type="text" name="Hp" placeholder="Not contain '-'"></td>
                        </tr>
                        <tr>
                            <td colspan="2"><small style="color: red;">${hpError}</small></td>
                        </tr>
                        <tr>
                            <td><input type="hidden" name="type" value="Employee"/></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <button type="submit" name="submitbtn">Save</button>
                                <button type="reset" name="btnCancel"><a href="manageAccount.jsp">Cancel</a></button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>
