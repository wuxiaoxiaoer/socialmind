<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<% 
	String u_name = "";
	String u_pwd = "";
	String checked = "";
	Cookie[] cookies = request.getCookies();
	if(cookies!=null&&cookies.length>0){
		for(int i = 0;i<cookies.length;i++){
			Cookie cookie = cookies[i];
			if("u_name".equals(cookie.getName())){
				u_name = URLDecoder.decode(cookie.getValue(),"utf-8");
				checked = "checked";
			}
			if("u_pwd".equals(cookie.getName())){
				u_pwd = cookie.getValue();
			}
		}
	} 
%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>"/>
		<meta charset="utf-8">
	    <title>微博舆情分析系统</title>
	    <link href="css/font-awesome.min.css" rel="stylesheet">
	    <link href="css/bootstrap.min.css" rel="stylesheet">
	    <link href="css/templatemo-style.css" rel="stylesheet">
	    <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	</head>
	<script type="text/javascript">
		var flag = 0;
		function slideOfBackground(flag){
			/* var random_num = Math.random()*10;
			random_num = Math.floor(random_num);
			alert(random_num % 3); */
			var flag_temp = flag;
			if(flag_temp == 0){
				$("#body_back").css("background","url('images/fore_background2.jpg') no-repeat");
				$("#body_back").css("background-size","cover");
				$("#body_back").css("overflow","hidden");				
			}
			else if(flag_temp == 1){
				$("#body_back").css("background","url('images/fore_background.jpg') no-repeat");
				$("#body_back").css("background-size","cover");
				$("#body_back").css("overflow","hidden");
			}
			else if(flag_temp == 2){
				$("#body_back").css("background","url('images/background.jpg') no-repeat");
				$("#body_back").css("background-size","cover");
				$("#body_back").css("overflow","hidden");				
			}
		}
	
		function back_timer(){
			flag++;
			slideOfBackground(flag%3);
			setTimeout('back_timer()', 2000);
		}
	</script>
	<body onload="back_timer();" id="body_back" class="light-gray-bg" style="background: url('images/fore_background.jpg') no-repeat;background-size:cover;background-size: 100%;overflow: hidden;">
		<div class="templatemo-content-widget templatemo-login-widget white-bg">
			<header class="text-center">
	          <div class="square"></div>
	          <h1 style="font-family: '华文行楷';font-size: 34px;">微博舆情分析系统</h1>
	        </header>
	       <!--   <form action="user/login" method="post" onsubmit="return login()" class="templatemo-login-form">-->
	       	<form action="user/login" method="post" onsubmit="return login()" class="templatemo-login-form">
	        	<div class="form-group">
	        		<div class="input-group">
		        		<div class="input-group-addon"><i class="fa fa-user fa-fw"></i></div>	        		
		              	<input name="u_name" id="u_name" type="text" value="<%=u_name%>" class="form-control" placeholder="username">           
		          	</div>	
	        	</div>
	        	<div class="form-group">
	        		<div class="input-group">
		        		<div class="input-group-addon"><i class="fa fa-key fa-fw"></i></div>	        		
		              	<input name="u_pwd" id="u_pwd" type="password" value="<%=u_pwd%>" class="form-control" placeholder="******">           
		          	</div>	
	        	</div>
	        	<!-- <div class="form-group">
	        		<div class="input-group">
		        		<div class="input-group-addon"><i class="fa fa-heart-o fa-fw"></i></div>	        		
		              	<select name="usertype_id" class="form-control">
		              		<option value="1">管理员</option>
		              		<option value="2">普通用户</option>
		              	</select>           
		          	</div>
	        	</div> -->
	          	<div class="form-group">
				    <div class="checkbox squaredTwo">
				        <input type="checkbox" id="c1" name="cc" <%=checked%>/>
						<label for="c1"><span></span>记住密码</label>
						<label id="prompt" style="color: red;float:right"></label>
				    </div>				    
				</div>
				<div class="form-group">
					<button type="submit" class="templatemo-blue-button width-100">登 录</button>
				</div>
	        </form>
		</div>
		<div class="templatemo-content-widget templatemo-login-widget templatemo-register-widget white-bg">
			没有账户? <strong><a href="user/register/0" class="blue-text">立即注册</a></strong>&nbsp;&nbsp;</p>
			
		</div>
		
		<div style="padding:20px;width:100%;height:10%;bottom:0;position:absolute;">
         	<jsp:include page="/jsp/statics/foot.jsp"/> 
       	</div> 
		
	</body>
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
    <script type="text/javascript" src="js/templatemo-script.js"></script>      <!-- Templatemo Script -->  
	<script type="text/javascript">
		function login(){
			var u_name = $.trim($("#u_name").val());
			var u_pwd = $.trim($("#u_pwd").val());
			var isRemPwd = $("#c1").is(':checked');
			$.ajax({
				type:'post',
				url:'user/login?u_name='+u_name+'&u_pwd='+u_pwd+'&isRemPwd='+isRemPwd,
				success:function(msg){
					if('success' == msg){
						location.href= "<%=basePath%>index";
					}else if('failure' == msg){
						$("#prompt").html("用户名或密码错误！");
					}
				}
			});
			return false;
		}
	</script>
</html>