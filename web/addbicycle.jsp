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
                <h3>Add bicycle</h3>
                <small style="color: red;">${createError}</small>
                <div class="pos2-1">
                    <form method="post" action="AddBicServlet">
                        <table class="table-1">
                            <tr>
                                <td>Bicycle Name:</td>
                                <td><input type="text" name="bicycleName"></td>
                            </tr>
                            <tr>
                                <td>Bicycle Type:</td>
                                <td>
                                    <select name="bicycleType">
                                        <option value="Road Bike">Road Bike</option>
                                        <option value="Mountain Bike">Mountain Bike</option>
                                        <option value="Touring Bike">Touring Bike</option>
                                        <option value="Folding Bike">Folding Bike</option>
                                        <option value="BMX">BMX</option>
                                        <option value="Electric Bike">Electric Bike</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Rental Price per Days: </td>
                                <td>RM <input type="number" name="bicRentPrice"></td>
                            </tr>
                            <tr>
                                <td><input type="hidden" name="condition" value="good"></td>
                                <td><input type="hidden" name="status" value="Unrented"></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <button type="submit" name="submitbtn">Save</button>
                                    <a href="ownermain.jsp?type=All"><button type="button" name="submitbtn">Cancel</button></a>
                                </td>
                            </tr>
                        </table>

                    </form>
                </div>
            </div>  
        </div>
    </body>
</html>

