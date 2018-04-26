<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%  
	
    
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
</head>

<body>
<form id="loginForm" action="${pageContext.request.contextPath}/BusinessServlet/test.do" method="post" >
<input type="button" value="submit">
</form>
<script src="js/jquery-2.1.0.js"></script>
<script type="text/javascript">

$(function(){
	$("#loginForm").submit();
});
	



</script>

</body>
</html>
    