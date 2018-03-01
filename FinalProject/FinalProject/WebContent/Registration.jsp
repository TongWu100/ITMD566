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
<title>User Registration</title>
</head>
<body>
   <h2 align="center">User Registration</h2>
   <form name="Registration" action="UserServlet" method="post">
   <table align="center">
   <tr>
   <td>UserID</td>
   <td><input type="text" name="userid" value=""></td>
   </tr>
   <tr>
   <td>Password</td>
   <td><input type="text" name="password" value=""></td>
   </tr>
   <tr>
   <td>FullName</td>
   <td><input type="text" name="fullname" value=""></td>
   </tr>
   <tr>
   <td>Email</td>
   <td><input type="text" name="email" value=""></td>
   </tr>
    <tr>
   <td>Street Address</td>
   <td><input type="text" name="streetaddress" value=""></td>
   </tr>
    <tr>
   <td>City</td>
   <td><input type="text" name="city" value=""></td>
   </tr>
    <tr>
   <td>State</td>
   <td><input type="text" name="state" value=""></td>
   </tr>
    <tr>
   <td colspan="2" align="center"><input type="submit" value="Registration"></td>
   </tr>
   </table>
   </form>
</body>
</html>