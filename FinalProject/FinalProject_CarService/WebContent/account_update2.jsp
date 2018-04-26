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
    <title>修改用户账户信息</title>
<link  type="text/css" href="css/bootstrap.min.css" rel="stylesheet"/>
<link type="text/css" href="css/insert_model.css" rel="stylesheet"/>
<style type="text/css">
div{
dispaly:line-block;
}
</style>
</head>

<body>

<div class="head">修改用户账户信息</div>
<form  id="formtale">


<div class="form-group">
<span>用户名称：</span>
<input style="width:200px" type="text" class="form-control" id="username" name="username" maxlength="10" disabled="true" value="<%username%>"/>
</div> 

<div class="form-group">
<span>邮箱：</span>
<input style="width:200px" type="text" class="form-control" id="email" name="email" maxlength="16"   disabled="true" value="<%email%>"/>
</div> 

<div class="form-group">
<span>密码：</span>
<input style="width:200px" type="password" class="form-control" id="password" name="password" maxlength="6" 
 onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"  disabled="true" value="<%password%>"/>
</div> 

<div class="form-group">
<span>确认密码：</span>
<input style="width:200px" type="password" class="form-control" id="repassword" name="repassword" maxlength="6" 
 onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"  disabled="true" value="<%password%>"/>
</div> 


<div class="form-group">
<span>用户余额：</span>
<input style="width:200px" type="text" class="form-control" id="rights_id"  maxlength="3" value="<%account%>"/>
</div> 
 
<div class="form-group">
<span>权限等级：</span>
<input style="width:200px" type="text" class="form-control" id="rights_id"  maxlength="3" value="<%role%>"/>
</div>  

<div class="form-group">
<span>城市：</span>
<input style="width:200px" type="text" class="form-control" id="city"  maxlength="3" value="<%city%>"/>
</div> 

<div class="form-group">
<span>地区：</span>
<input style="width:200px" type="text" class="form-control" id="streetAddress"  maxlength="3" value="<%streetaddress%>"/>
</div>

</form>
<div>
<span ></span>
<button type="button" class="btn btn-default" id="submit">提交</button>

<button style="margin-left:60px" type="button" class="btn btn-default" id="cancle">取消</button>
</div>

<script src="js/jquery-2.1.0.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){
	
	$('#submit').click(function(){
		
		if($('#username').val()==''||$('#email').val()==''||$('#password').val()==''||$('#repassword').val()==''||$('#city').val()==''||$('#streetAddress').val()==''){
			alert('参数不能为空');
			return;
		}
		
		if($('#password').val()!==$('#repassword').val()){
			alert('2次密码输入不一致，请重新输入');
			return;
		}
		
		$.post('user_add.do',
		{   username:$('#username').val(),
			email:$('#email').val(),
			password:$('#password').val(),
			repassword:$('#repassword').val(),
			rights_id:$('#rights_id').val(),
			city:$('#city').val(),
			streetAddress:$('#streetAddress').val()
		},
			function(data){
				if(data=='Success'){
					alert("添加成功");
					window.parent.$('.J_iframe').attr('src','user_list.jsp');
					}
					else{
					alert("输入信息有误，请重新输入");
					}
			});
	});
});


$(function(){
	$('#cancle').click(function(){
	window.parent.$('.J_iframe').attr('src','home.jsp');
	});
	});


</script>
</body>
</html>
    