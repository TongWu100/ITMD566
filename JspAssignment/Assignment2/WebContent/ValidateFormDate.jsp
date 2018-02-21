<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@ page import="java.util.regex.*" %>
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
<title>Validate</title>
</head>   
<body>
<h1>User info</h1>
<jsp:useBean id="regUser" class="Bean.User" scope="session"/>
<%  
    String reg1 = "[\\d]{3}\\-[\\d]{2}\\-[\\d]{4}";
    String reg2 = "[\\d]{5}\\-[\\d]{4}";
    String reg3 = "[a-zA-Z_0-9]+@[a-zA-Z_0-9]+\\.[a-zA-Z_0-9]{2,3}";
    String reg4 = "[a-zA-Z]{2}";
    Pattern p1 = Pattern.compile(reg1);
    Matcher m1 = p1.matcher(regUser.getSsn());
    Pattern p2 = Pattern.compile(reg2);
    Matcher m2 = p2.matcher(regUser.getZipcode());
    Pattern p3 = Pattern.compile(reg3);
    Matcher m3 = p3.matcher(regUser.getEmail());
    Pattern p4 = Pattern.compile(reg4);
    Matcher m4 = p4.matcher(regUser.getState());

    if(regUser.getUsername().length()>=4&&regUser.getUsername().length()<=50&&m1.matches()&&m2.matches()&&regUser.getEmail().length()>=7
    		&&m3.matches()&&regUser.getStreetaddress().length()>=4&&regUser.getStreetaddress().length()<=50&&regUser.getCity().length()>=3&&
    		regUser.getCity().length()<=50&&m4.matches()){
    	request.getRequestDispatcher("/ProcessCustomerDataRequest.jsp").forward(request, response);
    }else{
    	response.sendRedirect("LoginFaliure.jsp");    	
    }
%>
</body>
</html>