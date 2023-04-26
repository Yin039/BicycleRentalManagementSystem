<%@page import="java.time.LocalDate"%>
<%@page import="com.util.DBConnection"%>
<%@ page import="java.util.*,java.sql.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    int year = LocalDate.now().getYear();

    if (request.getParameter("year") != null) {
        year = Integer.parseInt(request.getParameter("year"));
    }

    Gson gsonObj = new Gson();
    Map<Object, Object> map = null;
    List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
    String dataPoints = null;

    try {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT MONTH(rental_date) AS MONTH, SUM(amount) AS amount FROM payment JOIN rentalinfo USING (rent_ID) WHERE YEAR(rental_date)=? GROUP BY MONTH");
        ps.setInt(1, year);

        double xVal, yVal;

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            xVal = rs.getInt("MONTH");
            yVal = rs.getDouble("amount");

            String month = null;
            if (xVal == 1) {
                month = "January";
            } else if (xVal == 2) {
                month = "February";
            } else if (xVal == 3) {
                month = "March";
            } else if (xVal == 4) {
                month = "April";
            } else if (xVal == 5) {
                month = "May";
            } else if (xVal == 6) {
                month = "June";
            } else if (xVal == 7) {
                month = "July";
            } else if (xVal == 8) {
                month = "August";
            } else if (xVal == 9) {
                month = "September";
            } else if (xVal == 10) {
                month = "October";
            } else if (xVal == 11) {
                month = "November";
            } else if (xVal == 12) {
                month = "December";
            }

            map = new HashMap<Object, Object>();
            map.put("label", month);
            map.put("y", yVal);
            list.add(map);
            dataPoints = gsonObj.toJson(list);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="header.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">
            window.onload = function () {
            <% if (dataPoints != null) {%>
                var chart = new CanvasJS.Chart("chartContainer", {
                    animationEnabled: true,
                    exportEnabled: true,
                    title: {
                        text: "Yearly Report <%out.print(year);%>"
                    },
                    axisY: {
                        title: "Total Amount (RM)"
                    },
                    axisX: {
                        title: "Month"
                    },
                    data: [{
                            type: "line",
                            yValueFormatString: "RM#,##0.0#",
                            click: onClick,
                            dataPoints: <%out.print(dataPoints);%>
                        }]
                });
                chart.render();

                function onClick(e) {
                    window.location = 'reportTable.jsp?year=' + <%=year%> + '&month=' + e.dataPoint.x;
                }
            <%}%>
            }
        </script>
        <style>
            .position1{
                position: relative;
                float:left; 
                margin: auto; 
                left:35%; 
                top:60%;
            }
            .position2{
                margin-top: 5px;
                padding-top: 5px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="navBar.jsp"/>
        <div class="position2">
            <select name="year" onchange="window.location = 'report.jsp?year=' + this.value">
                <option value="<%=year%>" selected style="display:none;"><%=year%></option>
                <%int nyear = 0;
                    for (nyear = LocalDate.now().getYear() - 5; nyear < (LocalDate.now().getYear() + 1); nyear++) {%>
                <option value="<%=nyear%>"><%=nyear%></option>
                <%}
                    for (nyear = LocalDate.now().getYear() + 1; nyear < (LocalDate.now().getYear() + 6); nyear++) {%>
                <option value="<%=nyear%>"><%=nyear%></option>
                <%}%>
            </select>
        </div>
        <div id="chartContainer" style=" width: 80%; height:370px; padding-top: 100px;">
            <div class="position1">
                <h1>No Record Saved for <%=year%></h1>
            </div>
        </div>
        <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
    </body>
</html> 

