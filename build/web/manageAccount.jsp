<%@page import="com.dao.userDao"%>
<%@page import="java.util.List"%>
<%@page import="com.model.user"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <script>
        window.onload = function () {
        <%
            String type = "Employee";
            List<user> list = userDao.getAllUser(type);
            user user = (user) session.getAttribute("login");
            int currentUser = user.getUserID();

            if (request.getParameter("type") != null) {
                type = request.getParameter("type");
                list = userDao.getAllUser(type);
            }
        %>
        }
    </script>
    <body>
        <jsp:include page="navBar.jsp"/>
        <section class="right">
            <h1>Bicycle Rental Management System</h1>
        </section>
        <div class="center1">
            <h3><%=type%> Account</h3>
            <small style="color: red;">${deleteError}</small>
            <div class="position1">
                <div class="position2">
                    <select name="catogery" onchange="window.location = 'manageAccount.jsp?type=' + this.value">

                        <option value="<%=type%>" selected style="display:none;"><%=type%></option>
                        <option value="Owner">Owner</option>
                        <option value="Employee">Employee</option>
                        <option value="Customer">Customer</option>
                    </select>
                </div>

                <%
                    if (type.equals("Customer")) {
                %>  
                <table class='table2' border='1' width='95%'>
                    <tr>
                        <th>Customer ID</th>
                        <th>Customer Name</th>
                        <th>IC No</th>
                        <th>Address</th>
                        <th>Phone Number</th>
                        <th>Action</th>
                    </tr>
                    <%for (user e : list) {%>
                    <tr>
                        <td><%=e.getCusID()%></td>
                        <td><%=e.getName()%></td>
                        <td><%=e.getIc()%></td>
                        <td><%=e.getAddress()%></td>
                        <td><%=e.getHp()%></td>
                        <td><a href="DeleteAccServlet?id=<%=e.getUserID()%>&type=Customer"><img class="icon_size" src="img/Flat_cross_icon.svg.png"></a></td>
                    </tr>
                    <%}%>
                </table>
                <%} else if (type.equals("Owner")) {%>
                <table class='table2' border='1' width='95%'>
                    <tr>
                        <th>User ID</th>
                        <th>Username</th>
                        <th>Action</th>
                    </tr>
                    <%
                        for (user e : list) {
                            if (e.getUserID() != currentUser) {
                    %>
                    <tr>
                        <td><%=e.getUserID()%></td>
                        <td><%=e.getUsername()%></td>
                        <td><a href="DeleteAccServlet?id=<%=e.getUserID()%>&type=Owner"><img class="icon_size" src="img/Flat_cross_icon.svg.png"></a></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
                <a href="addOwner.jsp"><button>Add Owner</button></a>
                <%} else if (type.equals("Employee")) {%>
                <table class='table2' border='1' width='95%'>
                    <tr>
                        <th>Employee ID</th>
                        <th>Employee Name</th>
                        <th>IC No</th>
                        <th>Address</th>
                        <th>Phone Number</th>
                        <th>Action</th>
                    </tr>
                    <%for (user e : list) {%>
                    <tr>
                        <td><%=e.getEmpID()%></td>
                        <td><%=e.getName()%></td>
                        <td><%=e.getIc()%></td>
                        <td><%=e.getAddress()%></td>
                        <td><%=e.getHp()%></td>
                        <td><a href="DeleteAccServlet?id=<%=e.getUserID()%>&type=Employee"><img class="icon_size" src="img/Flat_cross_icon.svg.png"></a></td>
                    </tr>
                    <%}%>
                </table>
                <br>
                <a href="addEmployee.jsp"><button>Add Employee</button></a>
                <%}%>
            </div>
        </div>
    </body>
</html>