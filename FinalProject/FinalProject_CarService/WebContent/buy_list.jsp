<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Buy a list of records</title>
<link  type="text/css" href="css/bootstrap.min.css" rel="stylesheet"/>
<link type="text/css" href="css/model.css" rel="stylesheet"/>
</head>

<body>
<div class="head">Buy a list of records</div>
<form>
<table class="table table-hover">
<thead>
<tr>
<!-- <th>序号</th> -->
<th>Name</th>
<th>Buy num</th>
<th>Buy time</th>
<th>Buy type</th>
<th>Buy user</th>

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
	$.post('buy_list.do',
	function(data){
	$('#tb1').empty();
	for(var i=0;i<data.length;i++){
	var c=data[i];
	console.log(c);
				$('#tb1').append('<tr><td>'+c.name+'</td><td>'+c.buynum+'</td><td>'+c.buytime+'</td><td>'+c.buytype+'</td><td>'+c.buyuser+'</td></tr>');}},
				'json');
});	


</script>

</body>
</html>
    