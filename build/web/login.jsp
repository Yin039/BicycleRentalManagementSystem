<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body>
        <div>
            <section class="right-1">
                <h1>Bicycle Rental Management System</h1>
            </section>
            <div class="center-1">
                <h3>Login</h3>

                <div class="pos2-1">
                    <form name="login" method="post" action="LoginServlet">
                        <table class="table-1" cellpadding="2" cellspacing="2">
                            <tr>
                                <td><i class="fas fa-user-alt" ></i></td>
                                <td><input type="text" name="username" placeholder="Username"></td>
                            </tr>
                            <tr>
                                <td><i class="fa fa-lock" ></i></td>
                                <td><input type="password" name="password" placeholder="Password"></td>
                            </tr>
                            <tr>
                                <td colspan="2"><small style="color: red;">${loginError}</small></td>
                            </tr>
                            <tr>
                                <td colspan="2"><button type="submit" name="btnSubmit" value="Submit">Login</button></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <hr>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2"><button class="createNewUser"><a href="registration.jsp">Create Customer Account</a></button></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
