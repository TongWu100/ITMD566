<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.regex.*" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
 body{
     background-image:url('car.jpg');
     background-repeat:no-repeat;
     background-attachment:fixed;
     background-position:center;
 }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Failure</title>
</head>
<body>
<jsp:useBean id="regUser" class="Model.User" scope="session"/>
<p align="center">LoginFaliure
<% 
    String reg1 = "[a-zA-Z_0-9]+@[a-zA-Z_0-9]+\\.[a-zA-Z_0-9]{2,3}";
    String reg2 = "[a-zA-Z]{2}";
    Pattern p1 = Pattern.compile(reg1);
    Matcher m1 = p1.matcher(regUser.getEmail());
    Pattern p2 = Pattern.compile(reg2);
    Matcher m2 = p2.matcher(regUser.getState());
    if(!(regUser.getUsername().length()>=4&&regUser.getUsername().length()<=50)){
 	   out.print("inputing Username is wrong");
     }else if(!(regUser.getPassword().length()>=5)){
     	out.print("inputing Password is wrong");
     }else if(!(regUser.getEmail().length()>=7&&m1.matches())){
     	out.println("inputing email address is wrong");
     }else if(!(regUser.getStreetaddress().length()>=4&&regUser.getStreetaddress().length()<=50)){
     	out.println("inputing streetaddress is wrong");
     }else if(regUser.getCity().length()>=3&&regUser.getCity().length()<=50){
     	out.println("inputing city is wrong");
     }else if(!m2.matches()){
     	out.println("inputing state is wrong ");
     }
%>
<br/>
<a href="Registraion.jsp">please Registration again</a>
</p>
</body>
</html>