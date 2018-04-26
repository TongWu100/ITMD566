<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%  
	
    String userName=session.getAttribute("userName").toString();
    if(userName==null){
    	//response.sendRedirect("login.jsp");
    	response.sendRedirect(request.getContextPath() + "/login.jsp");  
    return;
    }
%>

<html>
  <head>
    <title>User account information list</title>
<link  type="text/css" href="css/bootstrap.min.css" rel="stylesheet"/>
<link type="text/css" href="css/model.css" rel="stylesheet"/>
</head>

<body>
<div class="head">User account information list</div>
<form>
<table class="table table-hover">
<thead>
<tr>
<th>UserName</th>
<th>Email</th>
<th>State</th>
<th>City</th>
<th>StreetAddress</th>
<th>Account</th>
<th>Role</th>

</tr>
</thead>
<tbody id="tb1">


</tbody>
</table>
</form>
<script src="js/jquery-2.1.0.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">

$(function(){
	$.post('account_list.do',
	function(data){
	$('#tb1').empty();
	for(var i=0;i<data.length;i++){
	var c=data[i];
	console.log(c);
				$('#tb1').append('<tr><td>'+c.username+'</td><td>'+c.email+'</td><td>'+c.state+'</td><td>'+c.city+'</td><td>'+c.streetaddress+'</td><td>'+c.account+'</td><td>'+c.role+'</td></tr>');}},
				'json');
});	

</script>

</body>
</html>
    