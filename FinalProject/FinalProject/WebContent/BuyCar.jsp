<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
 body{
     background-image:url('D:/eclipse ee/workstation/FinalProject/car.jpg');
     background-repeat:no-repeat;
     background-attachment:fixed;
     background-position:center;
 }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buy Car</title>
</head>
<body>
   <h2 align="center">Add a Car</h2>
   <form name="Addcar" action="" method="post">
   <table align="center">
   <tr>
   <td>Car ID</td>
   <td><input type="text" name="carid" value=""></td>
   </tr>
   <tr>
   <td>Car Name</td>
   <td><input type="text" name="carname" value=""></td>
   </tr>
   <tr>
   <td>Car type</td>
   <td><input type="text" name="cartype" value=""></td>
   </tr>
   <tr>
   <td>Buy Price</td>
   <td><input type="text" name="buyprice" value=""></td>
   </tr>
    <tr>
   <td>Rent Price</td>
   <td><input type="text" name="rentprice" value=""></td>
   </tr>
    <tr>
   <td colspan="2" align="center"><input type="submit" value="Add"></td>
   </tr>
   </table>
   </form>
</body>
</html>