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
<title>User Login</title>
</head>
<body>
<h2 align="center">User Login</h2>
<form action="login" method="post">
     <table align="center">
     <tr>
        <td>Email:</td>
        <td><input type="text" name="email"/></td>
     </tr>
     <tr>
        <td>PassWord:</td>
        <td><input type="password" name="password"/></td>
     </tr>
     <tr>
      <td colspan="2" align="center"><input type="submit" value="Submit"></td>
     </tr>
     </table>
</form>
</body>
</html>