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
     background-position:bottom;
 }
</style>
<jsp:useBean id="regUser" class="Bean.User" scope="session"/>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&key=AIzaSyCTozB-5_cry7INYuEljhQ04LSFv1ibjS8 "></script>
<script type="text/javascript">
var geocoder = new google.maps.Geocoder();
var address = "<jsp:getProperty property="streetaddress" name="regUser"/>";
geocoder.geocode( { 'address': address}, function(results, status) {

if (status == google.maps.GeocoderStatus.OK) {
	var latitude = results[0].geometry.location.lat();
	var longitude = results[0].geometry.location.lng();
	var table = document.getElementById("table1");
	var tr=document.createElement("tr");
	var td1 = document.createElement("td");
	td1.innerHTML = latitude;
	var td2 = document.createElement("td");
	td2.innerHTML = longitude;
	var p = document.createElement("td");
	p.innerHTML = "Latitude";
	var p1 = document.createElement("td");
	p1.innerHTML = "Longitude";
	table.append(tr);
	table.append(p);
	table.append(td1);
	table.append(tr);
	table.append(p1);
	table.append(td2);
    } 

}); 
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Process</title>
</head>
<body>
<h1 align="center">login success</h1>
<h2 align="center">Thank you for using WebService</h2>

<table id ="table1" align="center" width="400" cellpadding="10" cellspacing="0" border="1">
<tr>
   <td>Username</td>
   <td><jsp:getProperty property="username" name="regUser"/></td>
</tr>
<tr>
   <td>SSN</td>
   <td><jsp:getProperty property="ssn" name="regUser"/></td>
</tr>
<tr>
   <td>ZipCode</td>
   <td><jsp:getProperty property="zipcode" name="regUser"/></td>
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
</body>
</html>