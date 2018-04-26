<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Auto parts information list</title>
<link  type="text/css" href="css/bootstrap.min.css" rel="stylesheet"/>
<link type="text/css" href="css/model.css" rel="stylesheet"/>
</head>

<body>
<input type="hidden" id="userName" value="<%=String.valueOf(session.getAttribute("userName")) %>" />
<input type="hidden" id="email" value="<%=String.valueOf(session.getAttribute("email")) %>" />
<input type="hidden" id="account" value="<%=String.valueOf(session.getAttribute("account")) %>" />
<input type="hidden" id="role" value="<%=session.getAttribute("role") %>" />
<div class="head">Auto parts information list</div>
<form>
<table class="table table-hover">
<thead>
<tr>
<th>Serial</th>
<th>Parts img</th>
<th>Parts img detail</th>
<th>Parts name</th>
<th>Buy price</th>

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
	$.post('carpart_list.do',
	function(data){
	$('#tb1').empty();
	if(role=="0"){
		for(var i=0;i<data.length;i++){
			var c=data[i];
			$('#tb1').append('<tr><td>'+c.id+'</td><td><img id="imgs" src='+c.imgpath+' style="width:100px;height:40px;"/></td><td><button type="button" class="btn btn-info" onclick="show()">show</button></td><td>'+c.name+'</td><td>'+c.buyprice+'</td><td><button type="button" class="btn btn-info" onclick="buy(this)">buy</button></td><td><button type="button" class="btn btn-info" onclick="del(this)">delete</button></td></tr>');
						
			}
	}else{
		for(var i=0;i<data.length;i++){
			var c=data[i];
			$('#tb1').append('<tr><td>'+c.id+'</td><td><img id="imgs" src='+c.imgpath+' style="width:100px;height:40px;"/></td><td><button type="button" class="btn btn-info" onclick="show()">show</button></td><td>'+c.name+'</td><td>'+c.buyprice+'</td><td><button type="button" class="btn btn-info" onclick="buy(this)">buy</button></td></tr>');
						
			}
	}
	},'json');
	
	 /* $("#imgs").click(function(){
		alert($("#imgs")[0].src);
		//window.open(s,"newwindow","width="+(screen.availWidth-10)+",height="+(screen.availHeight-50)+"menubar=no,location=no,scrollbars=yes,resizable=no,top=0,left=800");
	});  */
	
});	

function show(){
	//alert($("#imgs")[0].src);
	var s = $("#imgs")[0].src;
	window.open(s,"newwindow","width="+(screen.availWidth-10)+",height="+(screen.availHeight-50)+"menubar=no,location=no,scrollbars=yes,resizable=no,top=0,left=400");
}

function buy(a){
	var $btn=$(a);
	var b=$btn.parent().parent().children(':eq(3)').html();
	var c=$btn.parent().parent().children(':eq(4)').html();
	var email = $("#email").val();
	var username = $("#userName").val();
	var account = 0;
	$.ajax({  
        url:"account_get.do",  
        data: {email:email},  //关键性步骤,后台只要写String username = String.valueOf(request.getParameter("username"));等就能取到username、password、verifyCode值,而不是写data:{arr,arr},这样子后台只能取到arr这个字符串，具体字段的值还要通过切割截取获取
        type:"get",
        cache:false,
        async:false,
        success:function(data){  
        	account = data; 
        },  
        error:function(){  
            alert("Server exception!"); 
        }  
    });
	
	//var account = $("#account").val();
	if(parseInt(account)<parseInt(c)){
		alert("Sorry, the balance in your account is insufficient, please contact the administrator to recharge!");
		return;
	}
	var resu = parseInt(account)-parseInt(c);
	$.get('car_buy.do',
	{username:username,email:email,resu:resu,name:b,type:"Buy car parts"},
	function(data){
		if(data=="Success"){
			alert("Auto parts purchase success");
			window.parent.$('.J_iframe').attr('src','carpart_list.jsp');
		}else{
			alert("Auto parts purchase failure");
			window.parent.$('.J_iframe').attr('src','carpart_list.jsp');
		}
	});
	}

function del(a){
	var $btn=$(a);
	var b=$btn.parent().parent().children(':eq(0)').html();

	$.get('carpart_del.do',
	{id:b},
	function(data){
		if(data=="Success"){
			alert("Delete information success");
			window.parent.$('.J_iframe').attr('src','carpart_list.jsp');
		}else{
			alert("Delete information failure");
			window.parent.$('.J_iframe').attr('src','carpart_list.jsp');
		}
	});
	}



</script>

</body>
</html>
    