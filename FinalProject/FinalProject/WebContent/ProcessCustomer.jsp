<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Model.*"%>
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
<title>ProcessCustomer</title>
</head>
<body>
<jsp:useBean id="regUser" class="Model.User" scope="session"/>
<h1 align="center">Registration success</h1>
<h2 align="center">Thank you for using WebService</h2>
<%
      UserDao ud = new UserDao();
      ud.addUser(regUser);
%>
<table id ="table1" align="center" width="400" cellpadding="10" cellspacing="0" border="1">
<tr>
   <td>UserId</td>
   <td><jsp:getProperty property="id" name="regUser"/></td>
</tr>
<tr>
   <td>PassWord</td>
   <td><jsp:getProperty property="password" name="regUser"/></td>
</tr>
<tr>
   <td>FullName</td>
   <td><jsp:getProperty property="fullname" name="regUser"/></td>
</tr>
<tr>
   <td>Email</td>
   <td><jsp:getProperty property="email" name="regUser"/></td>
</tr>
<tr>
   <td>StreetAddress</td>
   <td><jsp:getProperty property="streetaddress" name="regUser"/></td>
</tr>
<tr>
   <td>City</td>
   <td><jsp:getProperty property="city" name="regUser"/></td>
</tr>
<tr>
   <td>State</td>
   <td><jsp:getProperty property="state" name="regUser"/></td>
</tr>
</table>
<br/>
<p align="center">
<a href="UserLogin.jsp">Please Login</a>
</p>
</body>
</html>