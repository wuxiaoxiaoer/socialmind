<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//要删除
session.setAttribute("valication_mail_code", 1234);
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
	<body class="light-gray-bg" onload="back_timer();" id="body_back" class="light-gray-bg" style="background: url('images/fore_background.jpg') no-repeat;background-size:cover;background-size: 100%;overflow: hidden;">
		<div class="templatemo-content-widget templatemo-login-widget white-bg">
			<header class="text-center">
	          <div class="square"></div>
	          <h1>注册 - 微博舆情分析系统</h1>
	        </header>
	        <form action="user/reg" onsubmit="return registerCheck();" method="post" class="templatemo-login-form">
	        	<div class="form-group">
	        		<div class="input-group">
		        		<div class="input-group-addon"><i class="fa fa-user fa-fw"></i></div>	        		
		              	<input id="u_name" name="u_name" type="text" onblur="return checkUser()" class="form-control" placeholder="username">     
		          	</div>
		          		<label id="u_name_prompt" style="color:red; font-size: 10px;font-style: normal; display: none;"></label>
	        	</div>
	        	<div class="form-group">
	        		<div class="input-group">
		        		<div class="input-group-addon"><i class="fa fa-key fa-fw"></i></div>	        		
		              	<input id="u_pwd" name="u_pwd" type="password" onblur="return checkPwd()" class="form-control" placeholder="******">           
		          	</div>
		          	<label id="u_pwd_prompt" style="color:red; font-size: 10px;font-style: normal; display: none;"></label>	
	        	</div>
	        	<div class="form-group">
	        		<div class="input-group">
		        		<div class="input-group-addon"><i class="fa fa-key fa-fw"></i></div>	        		
		              	<input id="u_r_pwd" name="u_r_pwd" onblur="return check_R_Pwd()" type="password" class="form-control" placeholder="******repeat password">           
		          	</div>
		          	<label id="u_r_pwd_prompt" style="color:red; font-size: 10px;font-style: normal; display: none;"></label>	
	        	</div>
	        	
	        	<div class="form-group">
	        		<div class="input-group">
		        		<div class="input-group-addon"><i class="fa fa-mail fa-fw"></i></div>	        		
		              	<input id="u_mail" name="u_mail" type="text" class="form-control" onblur="return check_u_mail()" placeholder="name@mail.com">
		          	</div>
		          	<label id="u_mail_prompt" style="color:red; font-size: 10px;font-style: normal; display: none;"></label>
		          	<div>
		          		<input id="btn_send_code" type="button" value="发送邮箱验证" onclick="send_verfication_code()" class="templatemo-blue-button" style="margin-top:4px;display: none;line-height:inherit"/>
		          		<input id="txt_verfication_code" type="text" value="" onblur="valid_mail_code()" class="form-control" style="width:160px;display: none;"/>
		          	</div>
		          	<label id="u_mail_verfication_prompt" style="color:red; font-size: 10px;font-style: normal; display: none;"></label>
	        	</div>
	        	<div class="form-group">
	        		<div class="input-group">
		        		<div class="input-group-addon"><i class="fa fa-telphone fa-fw"></i></div>	        		
		              	<input id="u_telphone" name="u_telphone" type="text" class="form-control" onblur="return check_u_telphone()" placeholder="telphone">           
		          	</div>
		          	<label id="u_telphone_prompt" style="color:red; font-size: 10px;font-style: normal; display: none;"></label>	
	        		<!-- <div>
		          		<input id="btn_send_telphone_code" type="button" value="发送手机验证" onclick="send_verfication_telphone_code()" class="templatemo-blue-button" style="margin-top:4px;display: none;line-height:inherit"/>
		          		<input id="txt_verfication_telphone_code" type="text" value="" onclick="" class="form-control" style="width:160px;display: none;"/>
		          	</div> -->
	        	</div>
	        	<div class="form-group">
	        		<div class="input-group">
		        		<div class="input-group-addon"><i class="fa fa-heart-o fa-fw"></i></div>	        		
		              	<select name="usertype_id" class="form-control">
		              		<!-- <option value="1">管理员</option> -->
		              		<option value="2">普通用户</option>
		              	</select>
		          	</div>	
	        	</div>
	        	<div class="col-lg-12 form-group">                   
                    <div class="margin-right-15 templatemo-inline-block">
                      <input type="radio" name="u_sex" id="r4" value="true" checked="">
                      <label for="r4" class="font-weight-400"><span></span>男</label>
                    </div>
                    <div class="margin-right-15 templatemo-inline-block">
                      <input type="radio" name="u_sex" id="r5" value="false">
                      <label for="r5" class="font-weight-400"><span></span>女</label>
                    </div>
                </div>
				<div class="form-group">
					<button type="submit" class="templatemo-blue-button width-100">注册</button>
				</div>
	        </form>
		</div>
		<div class="templatemo-content-widget templatemo-login-widget templatemo-register-widget white-bg">
			<p>已有账户? <strong><a href="<%=basePath%>" class="blue-text">返回登录!</a></strong></p>
		</div>
		
		
		<div style="padding:20px;width:100%;height:10%;bottom:0;position:absolute;">
         	<jsp:include page="/jsp/statics/foot.jsp"/> 
       	</div> 
		
		<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
	    <script type="text/javascript" src="js/templatemo-script.js"></script>      <!-- Templatemo Script -->
	    <script type="text/javascript" src="js/register.js"></script>      <!-- register Script -->
		<script type="text/javascript">
			function valid_mail_code(){
				var txt_verfication_code =  $("#txt_verfication_code").val();
				var valication_mail_code = "<%=session.getAttribute("valication_mail_code")%>";
				if(txt_verfication_code == valication_mail_code){
					$("#u_mail_verfication_prompt").html("验证正确");
					$("#u_mail_verfication_prompt").css("display","");
					$("#u_mail_verfication_prompt").css("color","green");
					return true;
				}else{
					$("#u_mail_verfication_prompt").html("验证失败");
					$("#u_mail_verfication_prompt").css("display","");
					return false;
				}
			}
		</script>
	</body>
</html>