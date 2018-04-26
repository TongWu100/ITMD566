<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery.min.js"></script>
<style type="text/css">
 body{
     background-image:url('car.jpg');
     background-repeat:no-repeat;
     background-attachment:fixed;
     background-position:center ; 
 }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Registration</title>
</head>
<body>
<div style="margin-top:120px;margin-bottom:80px;text-align:center;font-size:40px;font-weight: bold;color:green;">Automotive management system</div>
<div style="text-align:center;">
   <h2 align="center">User Registration</h2>
   <table align="center">
   <tr>
   <td>UserName</td>
   <td><input type="text" id="username" value=""><span id="sp1" style="color:red;"></span></td>
   </tr>
   <tr>
   <td>Password</td>
   <td><input type="password" id="password" value=""></td>
   </tr>
   <tr>
   <td>Email</td>
   <td><input type="text" id="email" value=""></td>
   </tr>
   <tr>
   <td>State</td>
   <td><input type="text" id="state" value=""></td>
   </tr>
    <tr>
   <td>City</td>
   <td><input type="text" id="city" value=""></td>
   </tr>
    <tr>
   <td>Street Address</td>
   <td><input type="text" id="streetaddress" value=""></td>
   </tr>
    <tr>
   <td colspan="2" align="center"><input type="button" id="Registration" value="Registration"></td>
   </tr>
   </table>
  </div>
<script type="text/javascript">
$(function(){
	$("#Registration").click(function(){
		$.post('webRegist.do',{
			username:$('#username').val(),
			password:$('#password').val(),
			email:$('#email').val(),
			state:$('#state').val(),
			city:$('#city').val(),
			streetaddress:$('#streetaddress').val()
		},function(data){
			data = data.trim();
			if(data=="File")
			{
				alert("User name or password error");
			}
			else if(data=="Success")
			{
				if(window.confirm('Have you registered successfully and are you going to login?')){
					location.href="${pageContext.request.contextPath }/login.jsp";
	             }else{
	            	 location.href="${pageContext.request.contextPath }/Registraion.jsp";
	             }
				
			}
			else if(data=="File1")
			{
				alert("inputing Username is wrong");
				//$("#sp1").html("inputing Username is wrong");
			}
			else if(data=="File2")
			{
				alert("inputing Password is wrong");
			}
			else if(data=="File3")
			{
				alert("inputing email address is wrong");
			}
			else if(data=="File4")
			{
				alert("inputing state is wrong");
			}
			else if(data=="File5")
			{
				alert("inputing city is wrong");
			}
			else if(data=="File6")
			{
				alert("inputing streetaddress is wrong");
			}
		});
	});
}); 
</script>
</body>
</html>