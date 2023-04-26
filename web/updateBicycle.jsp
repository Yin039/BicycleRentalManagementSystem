<%@page import="com.model.bicycle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="header.jsp"/>
    <body>
        <div>
            <%
                bicycle bic = (bicycle) session.getAttribute("bicycle");
                String[] bicType = {"Road Bike", "Mountain Bike", "Touring Bike", "Folding Bike", "BMX", "Electric Bike"};
                String[] bicStatus = {"Unrented", "Repairing", "Broken"};
            %>
            <section class="right-1">
                <h1>Bicycle Rental Management System</h1>
            </section>

            <div class="center-1">
                <h3>Update bicycle</h3>
                <small style="color: red;">${updateError}</small>
                <div class="pos2-1">
                    <form method="post" action="updateBicServlet?action=UPDATE">
                        <table class="table-1">
                            <tr>
                                <td>Bicycle ID:</td>
                                <td><input type="text" name="id" value="<%=bic.getBicID()%>" readonly="readonly"></td>
                            </tr>
                            <tr>
                                <td>Bicycle Name:</td>
                                <td><input type="text" name="bicycleName" value="<%=bic.getBicName()%>"></td>
                            </tr>
                            <tr>
                                <td>Bicycle Type:</td>
                                <td>
                                    <select name="bicycleType">
                                        <option selected="selected"><%=bic.getBicType()%></option>
                                        <%
                                            for (int count = 0; count < bicType.length; count++) {
                                                if (!bicType[count].equals(bic.getBicType())) {
                                                    out.print("<option>" + bicType[count] + "</option>");
                                                }
                                            }
                                        %>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Rental Price per Days: </td>
                                <td>RM <input type="number" name="bicRentPrice" value="<%=bic.getBicRentPrice()%>"></td>
                            </tr>
                            <tr>
                                <td>Bicycle Condition:</td>
                                <td><input type="text" name="condition" value="<%=bic.getBicCondition()%>" size="100"></td>
                            </tr>
                            <tr>
                                <td>Bicycle Status:</td>
                                <td>
                                    <select name="status">
                                        <option selected="selected"><%=bic.getBicStatus()%></option>
                                        <%
                                            for (int count = 0; count < bicStatus.length; count++) {
                                                if (!bicStatus[count].equals(bic.getBicStatus())) {
                                                    out.print("<option>" + bicStatus[count] + "</option>");
                                                }
                                            }
                                        %>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <button type="submit" name="submitbtn">Save</button>
                                    <button type="reset" name="btnCancel"><a href="ownermain.jsp">Cancel</a></button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>  
        </div>
    </body>
</html>
