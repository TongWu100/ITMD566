<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%  String backuserid=session.getAttribute("backuserid").toString();
String backusername=session.getAttribute("backusername").toString();
String schoolid=session.getAttribute("schoolid").toString();
%>



<html>
  <head>
   <title>学员信息</title>
<link  type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="css/model.css" rel="stylesheet"/>
<link type="text/css" href="jquery-ui-1.9.2.custom/css/base/jquery-ui-1.9.2.custom.min.css" rel="stylesheet"/>
</head>

<body>

<div id="dialog_simple">
<form  >

<div class="form-group">
<span>姓名：</span>
<input style="width:200px" type="text" class="form-control" id="name" name="name" maxlength="5"
onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" onbeforepaste="clipboardData.setData
('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5]/g,''))"
placeholder="例：张三">
</div> 
<div >
<span>性别：</span>
<p><input type="radio" value="男" name="sex" id="nan">男</p> 
<p><input type="radio" value="女" name="sex" id="nv">女</p>
</div>
<br />

<div class="form-group">
<span>身份证：</span>
<input style="width:200px" type="text" class="form-control" id="idcard" name="idcard" maxlength="18" 
onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}"  placeholder="例：350000000000000000"><div id="id_length"></div>
</div> 


<div class="form-group">
<span>年龄：</span>
<input style="width:200px" type="text" class="form-control" id="age" maxlength="3" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}"
placeholder="例：20">
</div> 
<div class="form-group">
<span>籍贯：</span>
<input style="width:200px" type="text" class="form-control" id="palce" maxlength="6"
onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" onbeforepaste="clipboardData.setData
('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5]/g,''))"
placeholder="例：福建福州">
</div> 

<div class="form-group">
<span>生日：</span>
<input style="width:200px" type="text" class="form-control" id="birth" maxlength="8" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}"
placeholder="例：19001001">
</div> 

<div >
<span>驾照类型：</span>
<select id="cartype"><option>A1</option><option>B1</option>
<option>C1</option><option>C2</option><option>C3</option></select>
</div>
<br /> 
<br /> 
<div class="form-group">
<span>驾校ID：</span>
<input style="width:200px" type="text" class="form-control" id="scid"  maxlength="3" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}"
placeholder="例：1" disabled="true" value="<%=schoolid %>">
</div> 

<div class="form-group">
<span>报名时间：</span>
<input style="width:200px" type="text" class="form-control" id="time"  maxlength="8" onkeyup="if(/\D/.test(this.value)){alert('只能输入数字');this.value='';}"
placeholder="例：20060101">
</div> 
<span>照片：</span><br />
<div class="form-group"> <input type="file" id="inputfile">
</div>

 <input type="hidden" id="stuid">

</form>


</div>
<div class="head">学员信息</div>
<form>
<table class="table table-hover" id="table1">
<thead>
<tr>
<th>学生ID</th>
<th>姓名</th>
<th>年龄</th>
<th>性别</th>
<th>驾照类型</th>
<th>身份证号</th>
<th>报考时间</th>
</tr>
</thead>
<tbody id="tb1">

</tbody>
</table>
</form>

		<div id="TableTail" align="center">
		当前${requestScope.pageBean.currentPage }/${requestScope.pageBean.totalPage }页     &nbsp;&nbsp;
		<a href="">首页</a>
		<a href="">上一页 </a>
		<a href="">下一页 </a>
		<a href="">末页</a>
		<div id="schoolid" ><%= schoolid%></div>
		</div>
<script src="js/jquery-2.1.0.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="jquery-ui-1.9.2.custom/js/jquery-ui-1.9.2.custom.min.js"></script>
<script type="text/javascript">

$(function(){
	$('#schoolid').css('display','none');
});

$(function(){
	
	$('#dialog_simple').dialog({
	    autoOpen: false,
	    width:500,
	    height:500,
	    buttons: {
	        "修改": function () {
	             $.post('update_stu2.do',
	            		 {
	            	     stuid:$('#stuid').val(),
	            	     name:$('#name').val(),
	            		 sex:$('input:radio[name="sex"]:checked').val(),
	            		 idcard:$('#idcard').val(),
	            		 age:$('#age').val(),
	            		 place:$('#palce').val(),
	            		 birth:$('#birth').val(),
	            		 cartype:$('#cartype').val(),
	            		 scid:$('#scid').val(),
	            		 time:$('#time').val(),
	            		 inputfile:$('#inputfile').val()
	            		 },
	            function(data){
	            	if(data=="Success"){
	            		alert("修改成功");
	            		window.parent.$('.J_iframe').attr('src','stu_select.jsp');
	            	}else{
	            		alert("输入有误，请重新修改");
	            	}		
	            			 
	            		 });
	             
	             $(this).dialog("close");
	             
	        },
	        "取消": function () {
	            $(this).dialog("close");
	        }
	    }
	    
	});	
	
$.post('stu_select.do',
		{schoolid:$('#schoolid').html()},
function(data){
$('#tb1').empty();
for(var i=0;i<data.length;i++){
var c=data[i];
			$('#tb1').append('<tr><td>'+c.stuid+'</td><td>'+c.name+'</td><td>'+c.age+'</td><td>'+c.sex+'</td><td>'+c.type+'</td><td>'
			+c.idCard+'</td><td>'+c.applyTime +'</td><td><button type="button" class="btn btn-default" onclick="update(this)">修改</button><button type="button" class="btn btn-default" onclick="del(this)">删除</button></td></tr>');}},
			'json');
});
function del(a){

var $btn=$(a);
var b=$btn.parent().parent().children(':eq(0)').html();
$.get('del_stu.do',
{stuid:b},
function(data){
if(data=="Success"){
alert("删除信息成功");
window.parent.$('.J_iframe').attr('src','stu_select.jsp');
}
});
}
 
function update(b){
	$('#dialog_simple').dialog('open');
var $btn=$(b);
var s=$btn.parent().parent().children(':eq(0)').html();
$.post('update_stu.do',
		{stuid:s},
		function(data){
		var d= data[0];
		$('#name').val(d.name);
		$('input:radio[name="sex"]:checked').val(d.sex);
		$('#idcard').val(d.idCard);
		$('#age').val(d.age);
		$('#palce').val(d.stu_address);
		$('#birth').val(d.brith);
		$('#cartype').val(d.type);
		$('#scid').val(d.schoolId);
		$('#time').val(d.applyTime);
		$('#inputfile').val(d.inputfile); 
		$('#stuid').val(d.stuid);
		if(d.sex=="男"){
			$('#nan').prop('checked',true);
		}else{
			$('#nv').prop('checked',true);
		}
		
		
		},'json'); 
}

</script>
</body>
</html>
