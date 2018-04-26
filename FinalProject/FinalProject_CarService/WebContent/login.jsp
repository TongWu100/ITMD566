<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<title>Car Service</title>
  <link rel="stylesheet" href="css/style.css">
  <!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>
<body>
<div style="margin-top:150px;text-align:center;font-size:40px;font-weight: bold;color:green;">Automotive management system</div>
  <section class="container">
    <div class="login">
      <h1>User Login</h1>
     
        <p><input id="username" type="text" name="username" value="" placeholder="帐号"></p>
        <p><input id="password" type="password" name="password" value="" placeholder="密码"></p>

        <p class="remember_me">
          <label>
            <input type="checkbox" name="remember_me" id="remember_me" checked="checked">
            Remember
          </label>
        </p>
        <p class="submit">
        	<input type="submit" id="commit" name="commit" value="login"/>
        	<input type="submit" id="regist" name="regist" value="register"/>
        </p>
     
    </div>
  </section>
<div style="text-align:center;">

</div>


<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript">


 $(function(){
	var n = $("#username").val().trim();
	var p = $("#password").val().trim();

	if($('#remember_me').is(':checked')){
		$('#remember_me').attr("checked",true);
		$.cookie('username', n, {expires:7});
		$.cookie('password', p, {expires:7});
		//如果能匹配到说明是记住密码，将复选框设置为选中状态
	}else{
		$.cookie('username', "");
		$.cookie('password', "");
	}
	
	$('#commit').click(lg);
	$('#username,#password').keyup(function(even){
		if(even.keyCode==13){
			lg();
		}
	});
	$('#regist').click(function(){
		location.href="${pageContext.request.contextPath }/Registraion.jsp";
	});
}); 
 function lg(){		
		$.post('webLogin.do',{
			username:$('#username').val(),
			password:$('#password').val()
		},function(data){
			if(data=="Fail")
			{
				alert("User name or password error");
			}
			else if(data=="Success")
			{
			location.href="${pageContext.request.contextPath }/interface.jsp";
			}
		});
	} 
</script>


</body>
</html>

