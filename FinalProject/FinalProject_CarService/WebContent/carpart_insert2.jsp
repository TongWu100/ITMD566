<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
  <head>
    <title>Add auto parts</title>
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

<div class="head">Add auto parts</div>

<div>
<form name="loginForm" id="loginForm" action="${pageContext.request.contextPath}/BusinessServlet/img_upload3.do" method="post" enctype="multipart/form-data" target="hiddenFrame">
	
	<div class="form-group">
	<span>Parts name：</span>
	<input style="width:200px" type="text" class="form-control" id="name" name="name" maxlength="16" placeholder="例：轮胎"/>
	</div> 

	<div class="form-group">
	<span>Buy price：</span>
	<input style="width:200px" type="text" class="form-control" id="buyprice" name="buyprice" maxlength="6" 
	 onchange="if(/\D/.test(this.value)){alert('购买价格栏中只能输入数字');this.value='';}" placeholder="例：1300(单位为元)">
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
<button style="margin-left:25px" type="button" class='btn btn-info' id="cancle">cancle</button><!-- class="btn btn-default" -->
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
		var arr = $('#loginForm').serialize(); 
		arr = decodeURIComponent(arr,true);//解码
		$.ajax({  
            url:"img_upload2.do",  
            data: arr,  //关键性步骤,后台只要写String username = String.valueOf(request.getParameter("username"));等就能取到username、password、verifyCode值,而不是写data:{arr,arr},这样子后台只能取到arr这个字符串，具体字段的值还要通过切割截取获取
            type:"post",  
            dataType:"json",  
            success:function(data){  
            	
            },  
            error:function(){  
                alert("服务器异常!"); 
            }  
        })
	});
	
	
	$('#sub').click(function(){
		
		if($('#name').val()==''||$('#buyprice').val()==''||$('#textfield').val()==''){
			alert('参数不能为空');
			return;
		}
		
		/* $("#loginForm").submit(function(data){
			console.log(data);
		}); */
		
		$("#loginForm").submit();
		
		/* $.post('carpart_add.do',
		{   name:$('#name').val(),
			buyprice:$('#buyprice').val(),
		},
			function(data){
				if(data=='Success'){
					alert("添加成功");
					window.parent.$('.J_iframe').attr('src','carpart_list.jsp');
					}
					else{
					alert("输入信息有误，请重新输入");
					}
			}); */
	});
	
	/* $("#loginForm").submit(function(){
		if($('#name').val()==''||$('#buyprice').val()==''||$('#textfield').val()==''){
			alert('参数不能为空');
			return false;
		}
		return true;
	}); */
});


$(function(){
	$('#back').click(function(){
		window.parent.$('.J_iframe').attr('src','carpart_list.jsp');
	});
	$('#cancle').click(function(){
		window.parent.$('.J_iframe').attr('src','home.jsp');
	});
});

</script>
</body>
</html>
    