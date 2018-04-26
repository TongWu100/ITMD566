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
<%-- <input type="hidden" id="username" value="<%=request.getAttribute("username") %>" />
<input type="hidden" id="email" value="<%=request.getAttribute("email") %>" />
<input type="hidden" id="password" value="<%=request.getAttribute("password") %>" />
<input type="hidden" id="account" value="<%=request.getAttribute("account") %>" />
<input type="hidden" id="role" value="<%=request.getAttribute("role") %>" />
<input type="hidden" id="city" value="<%=request.getAttribute("city") %>" />
<input type="hidden" id="streetaddress" value="<%=request.getAttribute("streetaddress") %>" /> --%>
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
	$('#tb1').append('<tr><td>'+c.username+'</td><td>'+c.email+'</td><td>'+c.state+'</td><td>'+c.city+'</td><td>'+c.streetaddress+'</td><td>'+c.account+'</td><td>'+c.role+'</td><td><button type="button" class="btn btn-info" onclick="update(this)">update</button></td></tr>');}},
	'json');
});	

function update(a){
	var $btn=$(a);
	var b=$btn.parent().parent().children(':eq(1)').html();

	$.ajax({  
        url:"account_update.do",  
        data: {email:b},  //关键性步骤,后台只要写String username = String.valueOf(request.getParameter("username"));等就能取到username、password、verifyCode值,而不是写data:{arr,arr},这样子后台只能取到arr这个字符串，具体字段的值还要通过切割截取获取
        type:"post", 
        dataType:"json",  
        success:function(data){  
        	if(data!="File"){
    			<%-- window.parent.$('.J_iframe').attr('src','account_update2.jsp?username=<%=request.getAttribute("username").toString()%>'); --%>
	   			for(var i=0;i<data.length;i++){
	   				var c=data[i];
	   				window.parent.$('.J_iframe').attr('src','account_update3.jsp?username='+c.username+'&email='+c.email+'&password='+c.password+'&account='+c.account+'&role='+c.role+'&state='+c.state+'&city='+c.city+'&streetaddress='+c.streetaddress);
	   			}	
        	}else{
    			window.parent.$('.J_iframe').attr('src','account_list.jsp');
    		}
        },  
        error:function(){  
            alert("Server exception!"); 
        }  
    })
	}

</script>

</body>
</html>
    