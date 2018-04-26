<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
  <head>
    <title>Increase car information</title>
<link  type="text/css" href="css/bootstrap.min.css" rel="stylesheet"/>
<link type="text/css" href="css/insert_model.css" rel="stylesheet"/>
<link type="text/css" href="css/fileupload.css" rel="stylesheet"/>
<style type="text/css">
div{
dispaly:line-block;
}
</style>
<script src="js/jquery-2.1.0.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
</head>

<body>

<div class="head">Increase car information</div>
<div>

<form name="loginForm" id="loginForm" action="${pageContext.request.contextPath}/BusinessServlet/img_upload4.do" method="post" enctype="multipart/form-data" target="hiddenFrame">
	
	<div class="form-group">
	<span>Car name：</span>
	<input style="width:200px" type="text" class="form-control" id="name" name="name" maxlength="16" placeholder="例：BMW"/>
	</div> 
	
	<div class="form-group">
	<span>Car type：</span>
	<input style="width:200px" type="text" class="form-control" id="type" name="type" maxlength="16" placeholder="例：小型车">
	</div> 
	
	<div class="form-group">
	<span>Buy price：</span>
	<input style="width:200px" type="text" class="form-control" id="buyPrice" name="buyPrice" maxlength="6" 
	 onchange="if(/\D/.test(this.value)){alert('购买价格栏中只能输入数字');this.value='';}" placeholder="例：13000(单位为元)">
	</div> 
	
	<div class="form-group">
	<span>Lease price：</span>
	<input style="width:200px" type="text" class="form-control" id="rentPrice" name="rentPrice" maxlength="6" 
	 onchange="if(/\D/.test(this.value)){alert('租赁价格栏中只能输入数字');this.value='';}" placeholder="例：200(单位为元)">
	</div>
	 
	 <div style="width:600px;margin-left:20%;"  class="file-box">
	 <input type='text' name='textfield' id='textfield' class='txt' />  
	 <input type='button' class='btn btn-info' value='浏览' />
	 <input type="file" name="file" class="file" id="file" size="28" onchange="limit(this)"  multiple="multiple" />
	 <!-- <input type="submit" id="subm" name="subm" class='btn btn-info' value="配件图导入"/> -->
	</div>
</form>
 

</div>

<div>
<span style="font-size:18px;color:red;margin-left:-15px;">(Do not resubmit)</span><button style="margin-left:25px" type="button" class='btn btn-info' id="sub">submit</button>
<button style="margin-left:25px" type="button" class='btn btn-info' id="back">back</button>
<button style="margin-left:25px" type="button" class='btn btn-info' id="cancle">cancle</button>
</div>

<div>
	<iframe height="0" width="0" name="hiddenFrame" style="display:none;"></iframe>
	<div  id="loading" class="overlay" style="visibility:hidden;">
		<table width="100%" height="100%"> 
			 <tr align="center" valign="middle">
		         <td align="center" valign="middle">
		              <img style="width: 32px;height: 32px;" src="image/loading.gif">
		              <br><font color="#ffffff">正在执行,请稍候. . 若响应时间较长请按F5刷新</font>
		     	 </td> 
			 </tr> 
		 </table>
	</div>	
</div>
<script type="text/javascript">
function limit(obj)	
{
  var filenames = "";
  var filearray = obj.files;
  
  for (var i = 0; i < filearray.length;i++)
  {
	  var file = filearray[i];
	  var filename = file.name;
	  
	  if (filename.indexOf(".jpg") == -1 && filename.indexOf(".png") == -1 && filename.indexOf(".jpeg") == -1 && filename.indexOf(".bmp") == -1)
	  {
		  alert("请选择jpg、png、jpeg或bmp结尾的图片");
		  return;
	  }
	  
	  filenames += filename + ",";
  
  }
  document.getElementById('textfield').value=filenames;
}
function uploadFile() {
	var filevalue =  document.getElementById('textfield').value;
	//loading();
	//unloading();
	/* $.ajaxFileUpload
    (
        {
            url: 'img_upload.do', //用于文件上传的服务器端请求地址
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: 'file', //文件上传域的ID
            dataType: 'json', //返回值类型 一般设置为json
            data:{},//一同上传的数据 
            success: function (data, status)  //服务器成功响应处理函数
            {
            	unloading();
            },
            error: function (data, status, e)//服务器响应失败处理函数
            {
            	alert("服务器出错!");
                unloading();
            }
        }
    ) 
    return false; */
    
}
$(function(){
	
	$('#submit').click(function(){
		
		if($('#name').val()==''||$('#type').val()==''||$('#buyPrice').val()==''||$('#rentPrice').val()==''){
			alert('The parameter cannot be empty');
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
					alert("Add car success");
					window.parent.$('.J_iframe').attr('src','car_list.jsp');
					}
					else{
					alert("The input information is incorrect. Please re-enter it");
					}
			});
	});
	
	$('#sub').click(function(){
		
		if($('#name').val()==''||$('#type').val()==''||$('#buyPrice').val()==''||$('#rentPrice').val()==''||$('#textfield').val()==''){
			alert('The parameter cannot be empty');
			return;
		}
		
		$("#loginForm").submit();
	});
	
});


$(function(){
	$('#back').click(function(){
		window.parent.$('.J_iframe').attr('src','car_list.jsp');
	});
	$('#cancle').click(function(){
		window.parent.$('.J_iframe').attr('src','home.jsp');
	});
	});


</script>
</body>
</html>
    