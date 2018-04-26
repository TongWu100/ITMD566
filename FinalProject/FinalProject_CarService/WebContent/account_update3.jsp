<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%  
	
    String userName=session.getAttribute("userName").toString();
    if(userName==null){
    	response.sendRedirect("login.jsp");
    return;
    }
    String username = request.getParameter("username").toString();//直接传递
    String email = request.getParameter("email").toString();
    String password = request.getParameter("password").toString();
    String account = request.getParameter("account").toString();
    String role = request.getParameter("role").toString();
    String city = request.getParameter("city").toString();
    String streetaddress = request.getParameter("streetaddress").toString();
%>

<html>
  <head>
    <title>Modify user account information</title>
<link  type="text/css" href="css/bootstrap.min.css" rel="stylesheet"/>
<link type="text/css" href="css/insert_model.css" rel="stylesheet"/>
<style type="text/css">
div{
dispaly:line-block;
}
</style>
</head>

<body>

<div class="head">Modify user account information</div>
<form  id="formtale">


<div class="form-group">
<span>UserName：</span>
<input style="width:200px" type="text" class="form-control" id="username" name="username" maxlength="10" disabled="true" value="<%= request.getParameter("username").toString()%>"/>
</div> 

<div class="form-group">
<span>Email：</span>
<input style="width:200px" type="text" class="form-control" id="email" name="email" maxlength="16"   disabled="true" value="<%= request.getParameter("email").toString()%>"/>
</div> 

<div class="form-group">
<span>Password：</span>
<input style="width:200px" type="password" class="form-control" id="password" name="password" maxlength="6" 
 onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"  disabled="true" value="<%=request.getParameter("password").toString()%>"/>
</div> 

<div class="form-group">
<span>Confirm Password：</span>
<input style="width:200px" type="password" class="form-control" id="repassword" name="repassword" maxlength="6" 
 onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"  disabled="true" value="<%=request.getParameter("password").toString()%>"/>
</div> 


<div class="form-group">
<span>Account：</span>
<input style="width:200px" type="text" class="form-control" id="account"  value="<%=request.getParameter("account").toString()%>"/>
</div> 
 
<div class="form-group">
<span>Role：</span>
<input style="width:200px" type="text" class="form-control" id="rights_id" disabled="true"  maxlength="3" value="<%=request.getParameter("role").toString()%>"/>
</div>  

<div class="form-group">
<span>State：</span>
<input style="width:200px" type="text" class="form-control" id="state"  maxlength="3" value="<%=request.getParameter("state").toString()%>"/>
</div> 

<div class="form-group">
<span>City：</span>
<input style="width:200px" type="text" class="form-control" id="city"  maxlength="3" value="<%=request.getParameter("city").toString()%>"/>
</div> 

<div class="form-group">
<span>StreetAddress：</span>
<input style="width:200px" type="text" class="form-control" id="streetAddress"  maxlength="3" value="<%=request.getParameter("streetaddress").toString()%>"/>
</div>

</form>
<div>
<span ></span>
<button type="button" class="btn btn-info" id="submit">submit</button>

<button style="margin-left:60px" type="button" class="btn btn-info" id="cancle">cancle</button>
</div>

<script src="js/jquery-2.1.0.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){
	
	$('#submit').click(function(){
		
		if($('#account').val()==''||$('#state').val()==''||$('#city').val()==''||$('#streetAddress').val()==''){
			alert('The parameter cannot be empty');
			return;
		}
		
		if($('#password').val()!==$('#repassword').val()){
			alert('The password input is not consistent 2 times. Please re-enter it');
			return;
		}
		
		$.post('user_account_update.do',
		{   email:$('#email').val(),
			account:$('#account').val(),
			state:$('#state').val(),
			city:$('#city').val(),
			streetAddress:$('#streetAddress').val()
		},
			function(data){
				if(data=='Success'){
					alert("User modification success");
					window.parent.$('.J_iframe').attr('src','account_list.jsp');
					}
					else{
					alert("The input information is incorrect. Please re-enter it");
					}
			});
	});
});


$(function(){
	$('#cancle').click(function(){
	window.parent.$('.J_iframe').attr('src','account_list.jsp');
	});
	});


</script>
</body>
</html>
    