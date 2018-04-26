<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>User information list</title>
<link  type="text/css" href="css/bootstrap.min.css" rel="stylesheet"/>
<link type="text/css" href="css/model.css" rel="stylesheet"/>
</head>

<body>
<input type="hidden" id="role" value="<%=session.getAttribute("role") %>" />
<div class="head">User information list</div>
<form>
<table class="table table-hover">
<thead>
<tr>
<th>UserName</th>
<th>Email</th>
<th>State</th>
<th>City</th>
<th>Street Address</th>
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
	var role = $("#role").val();
	$.post('user_list2.do',
	function(data){
		$('#tb1').empty();
		if(role=="0"){
			for(var i=0;i<data.length;i++){
				var c=data[i];
				$('#tb1').append('<tr><td>'+c.username+'</td><td>'+c.email+'</td><td>'+c.state+'</td><td>'+c.city+'</td><td>'+c.streetaddress+'</td><td>'+c.role+'</td><td><button type="button" class="btn btn-info" onclick="del(this)">delete</button></td></tr>');
			}
		}else{
			for(var i=0;i<data.length;i++){
				var c=data[i];
				$('#tb1').append('<tr><td>'+c.username+'</td><td>'+c.email+'</td><td>'+c.state+'</td><td>'+c.city+'</td><td>'+c.streetaddress+'</td><td>'+c.role+'</td></tr>');
			}
		}
		
	},'json');
	
				
});	

function del(a){
	var $btn=$(a);
	var b=$btn.parent().parent().children(':eq(0)').html();
	var c=$btn.parent().parent().children(':eq(1)').html();

	$.get('user_del.do',
	{username:b,email:c},
	function(data){
	if(data=="Success"){
		alert("User deletion successful");
		window.parent.$('.J_iframe').attr('src','user_list.jsp');
	}else{
		alert("User deletion failed");
		window.parent.$('.J_iframe').attr('src','user_list.jsp');
	}
	});
	}



</script>

</body>
</html>
    