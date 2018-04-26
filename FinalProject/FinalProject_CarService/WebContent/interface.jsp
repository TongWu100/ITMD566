<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%  
    String userName=String.valueOf(session.getAttribute("userName"));
    String role=String.valueOf(session.getAttribute("role"));
    String email=String.valueOf(session.getAttribute("email"));
    if(userName==null){
    	response.sendRedirect("login.jsp");
    return;
    }
%>

<html>
  <head>
   <title>Automotive management system</title>
<link  type="text/css" href="css/houtai.css" rel="stylesheet"/>
<link  type="text/css" href="css/public.css" rel="stylesheet"/>
<link  type="text/css" href="css/smartMenu.css"  rel="stylesheet"/>

</head>

<body>
<div id="admin"><!--整个界面div  --> 
        <div class="ad-menu" id="ad-menu"><!-- 菜单栏div -->
        <div class="ad-logo"><img src="image/m-logo.png" height="103" width="130"></div><!--头像div  -->
        <div class="ad-list"><!--列表div-->
        <ul>
        <!-- 用户管理-->
        <li id="user_power"></li>
        
        <!-- 汽车管理-->
        <li id="car_power"></li>
        
         <!-- 配件管理-->
        <li id="car_part_power">
        </li>
        
        <!-- 账户管理-->
        <li id="account_power">
        </li>
        
        <!-- 购买记录管理-->
        <li id="buy_power">
        </li>
        
        </li>
        
        </ul>
        </div>
        </div><!-- 菜单栏div -->
        <!-- 右上角以及搜索功能模块div -->
        <div class="ad-comment-box" id="ad-comment">
         <div class="ad-top-comment">
          <div class="ad-message"> <!-- 左边右边加在一起 -->
            <!-- 左边 -->
           <div class="ad-top-left">
               <div class="ad-change-btn"><a id="ad-list" href="javascript:;" class="scm ad-list-btn"></a></div>
               <div class="ad-search-box clear">
                                
              </div>
          </div>
          <!-- 右边 -->
          <div class="ad-top-right">
              <div class="ad-notice" id="addnotice"> <!-- 消息提示 -->
                                
                </div>
                <div class="ad-welcom">
                                <div class="ad-wel-img"><img src="image/min_logo.png" height="36" width="36"></div>
                                <div class="ad-wel-text">
                                    <div class="font-wel"><strong>Welcome！<%=userName %></strong></div>
                                    <div class="font-wel"><a href="javascript:" id="exit" onclick="exit()"><strong>【exit】</strong></a></div>
                                </div>
                </div>      
          </div>
          <!-- 右边 -->
          </div>
          
          <!-- 下面一块区域 -->
          <div class="ad-main-nav-box">
              <ul id="breadcrumbs-three">
                            <li title="首页"><a href="javascript:;" class="dot">home page</a></li>
              </ul>
              <div class="ad-main-wraper .J_menuItems">
                            <ul class="ad-main-list" id="ad-main">
                            </ul>
              </div>
                 
          </div>
          
          <!-- 再下面的一块区域 -->
          <div class="ad-sub-nav-box content-tabs">
            
             <div class="ad-sub-wraper page-tabs J_menuTabs">
                            <ul class="ad-sub-list page-tabs-content">
                                <li class="active J_menuTab" data-id="index_v0.html">
                                <a href="javascript:" id="homepage" onclick="skip('gohome')">home page</a>
                                </li>
                            </ul>
             </div>
             <a href="javascript:;" class="scm jian-a next j_tabBg J_tabRight"></a>
          </div>
          <!-- 再下面的一块区域 -->
         </div>  <!-- 除了显示区域外的所有区域 -->
         
         <!-- 点击按钮后显示区域 -->
          <div class="ad-main-comment J_mainContent" id="ad-iframe">
                    <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="home.jsp" frameborder="0" data-id="home.jsp" seamless></iframe>
          </div>
         <!-- 点击按钮后显示区域 -->
         </div> <!-- 所有右边的界面 -->
         <div id="backuserid" ><%= userName%></div>
        </div><!--整个界面div  --> 

<script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/do.js"></script>
        <script type="text/javascript" src="js/contabs.js"></script>
        <script type="text/javascript" src="js/maintabs.js"></script>
        <script type="text/javascript" src="js/jquery-smartMenu-min.js"></script>
        <script type="text/javascript" src="js/jquery.nicescroll.min.js"></script>
        <script type="text/javascript">
       
        function exit(){
        	window.location.href="exit.do";
        }
        
        $(function(){
        	$('#backuserid').css('display','none');
        });
        //查权限表，获取显示出来的列表，动态添加
        $(function(){
        	$.post('listshow.do',
        			{rightid:$('#backuserid').html()},
        			function(data){
						 /* if(data[0].user_power==1){$('#user_power').append("<div class='li-item'><em class='scm li-ico ic1'></em>用户管理<span class='scm arrow'></span></div><dl><dd><a href='javascript:' class='dd-item' onclick='skip(3)'>查看用户信息<span class='scm dd-ar'></span></a></dd><dd><a href='javascript:' class='dd-item' onclick='skip(4)'>增加用户信息<span class='scm dd-ar'></span></a></dd></dl>");}       				
						 if(data[0].car_power==1){$('#car_power').append("<div class='li-item'><em class='scm li-ico ic1'></em>汽车管理<span class='scm arrow'></span></div><dl><dd><a href='javascript:' class='dd-item' onclick='skip(1)'>查找汽车信息<span class='scm dd-ar'></span></a></dd><dd><a href='javascript:' class='dd-item' onclick='skip(2)'>增加汽车信息<span class='scm dd-ar'></span></a></dd></dl>");}
						 if(data[0].car_part_power==1){$('#car_part_power').append("<div class='li-item'><em class='scm li-ico ic1'></em>配件管理<span class='scm arrow'></span></div><dl><dd><a href='javascript:' class='dd-item' onclick='skip(5)'>查看配件信息<span class='scm dd-ar'></span></a></dd><dd><a href='javascript:' class='dd-item' onclick='skip(6)'>增加配件信息<span class='scm dd-ar'></span></a></dd></dl>");}
						 if(data[0].user_account_power==1){$('#account_power').append("<div class='li-item'><em class='scm li-ico ic1'></em>账户管理<span class='scm arrow'></span></div><dl><dd><a href='javascript:' class='dd-item' onclick='skip(7)'>查看用户账户<span class='scm dd-ar'></span></a></dd></dl>");}
						 if(data[0].user_power==1){$('#buy_power').append("<div class='li-item'><em class='scm li-ico ic1'></em>购买管理<span class='scm arrow'></span></div><dl><dd><a href='javascript:' class='dd-item' onclick='skip(9)'>查看购买记录<span class='scm dd-ar'></span></a></dd></dl>");}
						 
						 if(data[0].user_power==2){$('#user_power').append("<div class='li-item'><em class='scm li-ico ic1'></em>个人信息<span class='scm arrow'></span></div><dl><dd><a href='javascript:' class='dd-item' onclick='skip(3)'>查看用户信息<span class='scm dd-ar'></span></a></dd><dd></dd></dl>");}       				
						 if(data[0].car_power==2){$('#car_power').append("<div class='li-item'><em class='scm li-ico ic1'></em>汽车管理<span class='scm arrow'></span></div><dl><dd><a href='javascript:' class='dd-item' onclick='skip(1)'>查找汽车列表<span class='scm dd-ar'></span></a></dd></dl>");}
						 if(data[0].car_part_power==2){$('#car_part_power').append("<div class='li-item'><em class='scm li-ico ic1'></em>配件管理<span class='scm arrow'></span></div><dl><dd><a href='javascript:' class='dd-item' onclick='skip(5)'>查看配件列表<span class='scm dd-ar'></span></a></dd></dl>");}
						 if(data[0].user_account_power==2){$('#account_power').append("<div class='li-item'><em class='scm li-ico ic1'></em>账户管理<span class='scm arrow'></span></div><dl><dd><a href='javascript:' class='dd-item' onclick='skip(8)'>查看用户账户<span class='scm dd-ar'></span></a></dd></dl>");}
						 if(data[0].user_power==2){$('#buy_power').append("<div class='li-item'><em class='scm li-ico ic1'></em>购买管理<span class='scm arrow'></span></div><dl><dd><a href='javascript:' class='dd-item' onclick='skip(9)'>查看购买记录<span class='scm dd-ar'></span></a></dd></dl>");} */
        				 
						 if(data[0].user_power==1){$('#user_power').append("<div class='li-item'><em class='scm li-ico ic1'></em><span style='font-size:14px;'>User management</span><span class='scm arrow'></span></div><dl><dd><a href='javascript:' class='dd-item' onclick='skip(3)'>View user information<span class='scm dd-ar'></span></a></dd><dd><a href='javascript:' class='dd-item' onclick='skip(4)'>Add user information<span class='scm dd-ar'></span></a></dd></dl>");}       				
						 if(data[0].car_power==1){$('#car_power').append("<div class='li-item'><em class='scm li-ico ic1'></em><span style='font-size:12px;'>Vehicle management</span><span class='scm arrow'></span></div><dl><dd><a href='javascript:' class='dd-item' onclick='skip(1)'>Find car information<span class='scm dd-ar'></span></a></dd><dd><a href='javascript:' class='dd-item' onclick='skip(2)'>Add car information<span class='scm dd-ar'></span></a></dd></dl>");}
						 if(data[0].car_part_power==1){$('#car_part_power').append("<div class='li-item'><em class='scm li-ico ic1'></em><span style='font-size:10px;'>Spareparts management</span><span class='scm arrow'></span></div><dl><dd><a href='javascript:' class='dd-item' onclick='skip(5)'><span style='font-size:12px;'>View accessory information</span><span class='scm dd-ar'></span></a></dd><dd><a href='javascript:' class='dd-item' onclick='skip(6)'><span style='font-size:12px;'>Add accessories information</span><span class='scm dd-ar'></span></a></dd></dl>");}
						 if(data[0].user_account_power==1){$('#account_power').append("<div class='li-item'><em class='scm li-ico ic1'></em><span style='font-size:12px;'>Account management</span><span class='scm arrow'></span></div><dl><dd><a href='javascript:' class='dd-item' onclick='skip(7)'>View user accounts<span class='scm dd-ar'></span></a></dd></dl>");}
						 if(data[0].user_power==1){$('#buy_power').append("<div class='li-item'><em class='scm li-ico ic1'></em><span style='font-size:10px;'>Purchase management</span><span class='scm arrow'></span></div><dl><dd><a href='javascript:' class='dd-item' onclick='skip(9)'>View purchase records<span class='scm dd-ar'></span></a></dd></dl>");}
						 
						 if(data[0].user_power==2){$('#user_power').append("<div class='li-item'><em class='scm li-ico ic1'></em><span style='font-size:14px;'>User management</span><span class='scm arrow'></span></div><dl><dd><a href='javascript:' class='dd-item' onclick='skip(10)'>View user information<span class='scm dd-ar'></span></a></dd><dd></dd></dl>");}       				
						 if(data[0].car_power==2){$('#car_power').append("<div class='li-item'><em class='scm li-ico ic1'></em>Vehicle management<span class='scm arrow'></span></div><dl><dd><a href='javascript:' class='dd-item' onclick='skip(1)'>Find car information<span class='scm dd-ar'></span></a></dd></dl>");}
						 if(data[0].car_part_power==2){$('#car_part_power').append("<div class='li-item'><em class='scm li-ico ic1'></em><span style='font-size:10px;'>Spareparts management</span><span class='scm arrow'></span></div><dl><dd><a href='javascript:' class='dd-item' onclick='skip(5)'><span style='font-size:12px;'>View accessory information</span><span class='scm dd-ar'></span></a></dd></dl>");}
						 if(data[0].user_account_power==2){$('#account_power').append("<div class='li-item'><em class='scm li-ico ic1'></em><span style='font-size:12px;'>Account management</span><span class='scm arrow'></span></div><dl><dd><a href='javascript:' class='dd-item' onclick='skip(8)'>View user accounts<span class='scm dd-ar'></span></a></dd></dl>");}
						 if(data[0].user_power==2){$('#buy_power').append("<div class='li-item'><em class='scm li-ico ic1'></em><span style='font-size:10px;'>Purchase management</span><span class='scm arrow'></span></div><dl><dd><a href='javascript:' class='dd-item' onclick='skip(9)'>View purchase records<span class='scm dd-ar'></span></a></dd></dl>");}
        			
        			},'json'
			        	);
			        });
            $(function(){
                $(".ad-menu").niceScroll({cursorborder:"0 none",cursorcolor:"#1a1a19",cursoropacitymin:"0",boxzoom:false});
            })
        </script>
</body>
</html>
