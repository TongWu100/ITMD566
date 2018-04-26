<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
  <head>
    <title>Add user information</title>
<link  type="text/css" href="css/bootstrap.min.css" rel="stylesheet"/>
<link type="text/css" href="css/insert_model.css" rel="stylesheet"/>
<style type="text/css">
div{
dispaly:line-block;
}
</style>
</head>

<body>

<div class="head">Add user information</div>
<form  id="formtale">


<div class="form-group">
<span>UserName：</span>
<input style="width:200px" type="text" class="form-control" id="username" name="username" maxlength="10" placeholder="example：test"/>
</div> 

<div class="form-group">
<span>Password：</span>
<input style="width:200px" type="password" class="form-control" id="password" name="password" maxlength="6" 
 onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" placeholder="example：123456">
</div> 

<div class="form-group">
<span>Confirm Password：</span>
<input style="width:200px" type="password" class="form-control" id="repassword" name="repassword" maxlength="6" 
 onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" placeholder="example：123456">
</div> 

<div class="form-group">
<span>Email：</span>
<input style="width:200px" type="text" class="form-control" id="email" name="email" maxlength="16"

placeholder="example：123456789@qq.com">
</div> 

</div> 
<div class="form-group">
<span>Role：</span>
<input style="width:200px" type="text" class="form-control" id="rights_id"  maxlength="3" 
  disabled="true" value="Average user">
</div> 

<div class="form-group">
<span>State：</span>
<input style="width:200px" type="text" class="form-control" id="state"  maxlength="3" 
placeholder="example：chaina">
</div>  

<div class="form-group">
<span>City：</span>
<input style="width:200px" type="text" class="form-control" id="city"  maxlength="3" 
placeholder="example：beijin">
</div> 

<div class="form-group">
<span>Street Address：</span>
<input style="width:200px" type="text" class="form-control" id="streetAddress"  maxlength="3" 
placeholder="example：dongchengqu">
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
		
		if($('#username').val()==''||$('#email').val()==''||$('#password').val()==''||$('#repassword').val()==''||$('#state').val()==''||$('#city').val()==''||$('#streetAddress').val()==''){
			alert('The parameter cannot be empty');
			return;
		}
		
		if($('#password').val()!==$('#repassword').val()){
			alert('The password input is not consistent 2 times. Please re-enter it');
			return;
		}
		
		$.post('user_add.do',
		{   username:$('#username').val(),
			email:$('#email').val(),
			password:$('#password').val(),
			repassword:$('#repassword').val(),
			rights_id:$('#rights_id').val(),
			state:$('#state').val(),
			city:$('#city').val(),
			streetAddress:$('#streetAddress').val()
		},
			function(data){
				if(data=='Success'){
					alert("User added success");
					window.parent.$('.J_iframe').attr('src','user_list.jsp');
					}
					else{
					alert("The input information is incorrect. Please re-enter it");
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
    