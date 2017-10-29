<%@ page language="java" import="java.util.*" import="com.sicdlib.entity.User" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)session.getAttribute("user");
String user_name = "";
String user_email = "";
String user_telephone = "";
boolean user_sex = true;
if(user != null){
	user_name = user.getU_name();
	user_email = user.getU_mail();
	user_telephone = user.getU_telphone();
	user_sex = user.isU_sex();
}
%>

<!DOCTYPE html>
<html lang="en">
  <head>
  	<base href="<%=basePath%>">
    <meta charset="utf-8">
    <link href="css/openwindow.css" rel="stylesheet" type="text/css" />
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/templatemo-style.css" rel="stylesheet">
	<style type="text/css">
		.input-group{
			margin-top:30px;
			margin-left:40px;
			margin-right:40px;
		}
		.form-group{
			height:40px;
		}
	</style>
    <script src="js/openwindow_js.js" type="text/javascript"></script>
    <script src="js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="js/register.js"></script>      <!-- register Script -->
<script type="text/javascript">
overall_user_name = "";
window.onload = function(){
	Alert('#but', {
		content : '<form action="user/modMyInfo" method="post"><center><h2 style="background-color:#39ADB4;color:white;padding:10px;margin-buttom:5px;width:101.1%;margin-left:-4px;">个人信息</h2></center><div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-user fa-fw"></i></div><input id="u_name" name="u_name" onblur="modInfo_checkUser();" type="text" value="<%=user_name%>" class="form-control" placeholder="username"></div><label id="u_name_prompt" style="color:red; font-size: 10px;font-style: normal; display: none;"></label></div>'
		+'<div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-key fa-fw"></i></div><input id="u_pwd" name="u_pwd" type="password" onblur="return modInfo_checkPwd()" class="form-control" value="***" placeholder="******"></div><label id="u_pwd_prompt" style="color:red; font-size: 10px;font-style: normal; display: none;"></label></div>'
		+'<div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-key fa-fw"></i></div><input id="u_r_pwd" name="u_r_pwd" onblur="return check_R_Pwd()" type="password" class="form-control" value="***" placeholder="******repeat password"></div><label id="u_r_pwd_prompt" style="color:red; font-size: 10px;font-style: normal; display: none;"></label></div>'
		+'<div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-mail fa-fw"></i></div><input id="u_mail" name="u_mail" value="<%=user_email%>" type="text" class="form-control" onblur="return modInfo_check_u_mail(<%=user_email%>)" placeholder="name@mail.com"></div><label id="u_mail_prompt" style="color:red; font-size: 10px;font-style: normal; display: none;"></label><div><input id="btn_send_code" type="button" value="发送邮箱验证" onclick="send_verfication_code()" class="templatemo-blue-button" style="margin-top:4px;display: none;line-height:inherit"/><input id="txt_verfication_code" type="text" value="" onblur="valid_mail_code()" class="form-control" style="width:160px;display: none;"/></div><label id="u_mail_verfication_prompt" style="color:red; font-size: 10px;font-style: normal; display: none;"></label></div>'
		+'<div class="form-group"><div class="input-group"><div class="input-group-addon"><i class="fa fa-telphone fa-fw"></i></div><input id="u_telphone" name="u_telphone" type="text" value="<%=user_telephone%>" class="form-control" onblur="return check_u_telphone()" placeholder="telphone"></div><label id="u_telphone_prompt" style="color:red; font-size: 10px;font-style: normal; display: none;"></label></div>'
		+'<div class="col-lg-12 form-group"><div class="margin-right-15 templatemo-inline-block">性别：<input type="radio" name="u_sex" id="r4" value="true" checked=""><label for="r4" class="font-weight-400"><span></span>男</label></div><div class="margin-right-15 templatemo-inline-block"><input type="radio" name="u_sex" id="r5" value="false"><label for="r5" class="font-weight-400"><span></span>女</label></div></div>'
        +'<div class="form-group"><button type="submit" class="templatemo-blue-button width-100">修改个人信息</button></div></form>',
		width : '',
		height : '',
		top : '',
		left : '',
		sidebar : '',
		traction : '',
		fixed : 'fixed',
		close : 'close'
	});
};
//修改个人信息
function modInfo_checkUser(){
	var u_name = $.trim($("#u_name").val());
	//为全局变量赋值
	
	alert(<%=user_name%> +":"+ u_name);
	if(<%=user_name%> != u_name){
		if(u_name==""||u_name.length<6){
			$("#u_name_prompt").html("请输入6位数密码");
			$("#u_name_prompt").css("display","");
			return false;
		}else {
			$.ajax({
				type: 'post',
				url: 'user/isExistUser?u_name='+u_name,
				success:function(msg){
					if('success' == msg){
						$("#u_name_prompt").html("该用户名已被使用");
						$("#u_name_prompt").css("display","");
						return false;
					}
				}
			});
			$("#u_name_prompt").html("");
			$("#u_name_prompt").css("display","none");
			return true;
		}
	}
}
				
			
</script> 
  </head>
  <body>  
      <!-- header --> 
       <div class="row">
         <nav class="templatemo-top-nav col-lg-12 col-md-12">
           <ul class="text-uppercase">
             <li><a class="active">微博,绽放你的每一秒</a></li>
             <li><a class="active">网上微博,网罗生活</a></li>
             <li><a class="active">微观风云,博览天下</a></li>
             <li><a class="active">微小世界，广博天空</a></li>
             <li><a class="active">尽在本平台</a></li>
           </ul>  
         </nav>
          <div class="templatemo-content-widget white-bg" style="border-radius:0px;box-shadow:0 0 0 0 ;border:0px;width:200px;height:80px;float:right;margin-top:-60px;margin-right:-10px;">
                <div class="media">
                  <div class="media-left" id="but">
                    <a>
                      <img class="media-object img-circle" src="images/sunset.jpg" alt="Sunset">
                    </a>
                  </div>
                  <div class="media-body">
					<h2 class="media-heading text-uppercase"><%=user_name%></h2>
					<a class="media-heading text-uppercase" href="user/logout">注 销</a>
                  </div>
                </div>                
              </div>
              
       </div>
  </body>
</html>