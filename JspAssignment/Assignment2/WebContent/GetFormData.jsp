<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
 body{
     background-image:url('map.jpg');
     background-repeat:no-repeat;
     background-attachment:fixed;
     background-position:center;
 }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
   
   <h2 align="center">User Login</h2>
   <form name="Login" action="/Assignment2/UserServlet" method="post">
   <table align="center">
   <tr>
   <td>Username</td>
   <td><input type="text" name="username" value=""></td>
   </tr>
   <tr>
   <td>SSN</td>
   <td><input type="text" name="ssn" value=""></td>
   </tr>
   <tr>
   <td>ZipCode</td>
   <td><input type="text" name="zipcode" value=""></td>
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
   <td colspan="2" align="center"><input type="submit" value="Login"></td>
   </tr>
   </table>
   </form>
</body>
</html>