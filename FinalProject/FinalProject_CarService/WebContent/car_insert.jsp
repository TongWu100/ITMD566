<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
  <head>
    <title>Increase car information</title>
<link  type="text/css" href="css/bootstrap.min.css" rel="stylesheet"/>
<link type="text/css" href="css/insert_model.css" rel="stylesheet"/>
<style type="text/css">
div{
dispaly:line-block;
}
</style>
</head>

<body>

<div class="head">Increase car information</div>
<form  id="formtale">


<div class="form-group">
<span>Car name：</span>
<input style="width:200px" type="text" class="form-control" id="name" name="name" maxlength="16" placeholder="例：BMW"/>
</div> 

<div class="form-group">
<span>Car type：</span>
<input style="width:200px" type="text" class="form-control" id="type" name="type" maxlength="16"

placeholder="例：小型车">
</div> 

<div class="form-group">
<span>Buy price：</span>
<input style="width:200px" type="text" class="form-control" id="buyPrice" name="buyPrice" maxlength="6" 
 onchange="if(/\D/.test(this.value)){alert('购买价格栏中只能输入数字');this.value='';}" placeholder="例：13000(单位为元)">
</div> 

<div class="form-group">
<span>Lease price：</span>
<input style="width:200px" type="text" class="form-control" id="rentPrice" name="rentPrice" maxlength="6" 
 onchange="if(/\D/.test(this.value)){alert('租赁价格栏中只能输入数字');this.value='';}" placeholder="例：200(以天为参考点单位为块)">
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
		
		if($('#name').val()==''||$('#type').val()==''||$('#buyPrice').val()==''||$('#rentPrice').val()==''){
			alert('参数不能为空');
			return;
		}
		
		
		$.post('car_add.do',
		{   name:$('#name').val(),
			type:$('#type').val(),
			buyPrice:$('#buyPrice').val(),
			rentPrice:$('#rentPrice').val(),
		},
			function(data){
				if(data=='Success'){
					alert("添加成功");
					window.parent.$('.J_iframe').attr('src','car_list.jsp');
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
    