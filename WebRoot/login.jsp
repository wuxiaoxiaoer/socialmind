<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@page import="java.net.URLDecoder"%>
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
<html>
  <head>
    <title>登陆 - socialmind大平台</title>
      <base href="<%=basePath%>"/>
    <!-- Bootstrap -->
    <link href="foreground/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="foreground/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <link href="foreground/assets/styles.css" rel="stylesheet" media="screen">
     <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
      <style type="text/css">
          .bg {
              background: url('foreground/images/bg.jpg');
              background-repeat: no-repeat;
              background-position: center;
              background-size: cover;
              text-align: center;
              background-size:100% 100%;
              overflow:hidden;
              background-color: #464646;
              background-size: cover;
              /*设置图片全屏*/
              background-attachment: fixed;
              background-position: center center;
          }
          body
          {
              width:auto; margin-top:12px;
              float:right; font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
              font-size:11px;
              color:#999999;
              line-height:25px;
              letter-spacing:1px
          }
      </style>
  </head>
  <body id="login" class="bg">
    <div class="container">
        <div style="margin-top:15%">
            <h1 style="color: #FFF;">Socialmind大平台</h1>

        </div>
      <form class="form-signin" action="user/login" method="post" style="opacity:0.90; margin-top:1.9%;">
        <h2 class="form-signin-heading">Login</h2>
        <input type="text"  value="<%=u_name%>" name="username" id="username" class="input-block-level" onblur="usernameIsString();" />
          <label id="lab_username" style="color:red"></label>
        <input type="password" value="<%=u_pwd%>" name="userpwd" id="password"  class="input-block-level" onblur="pwdIsString();"/>
          <label id="lab_pwd" style="color:red"></label>
		<%--<div class="control-group">
            <div class="controls">
                <select id="select01" name="userType" class="input-block-level">
                    <option value="individual">个人用户</option>
                    <option value="company">企业用户</option>
                    <option value="government">政府用户</option>
                    <option value="institution">事业单位用户</option>
                </select>
            </div>
        </div>--%>
        <label class="checkbox" style="width:20%">
          <input type="checkbox" <%=checked%> id="c1" name="cc"> 记住用户
        </label>
          <label id="prompt" style="color: red;float:right; margin-top:-10%;"></label>

          <div style="float:right; margin-top: -10%;">
            <button class="btn btn-large btn-success">登录</button>
          </div>

          <div class="templatemo-content-widget templatemo-login-widget templatemo-register-widget white-bg" style="width:100%; margin:10% 0 0 0;">
              没有账户? <strong><a href="register.jsp" class="blue-text">立即注册</a></strong>&nbsp;&nbsp;</p>
          </div>
      </form>

    </div>

    <!-- /container -->
    <script src="foreground/vendors/jquery-1.9.1.min.js"></script>
    <script src="foreground/bootstrap/js/bootstrap.min.js"></script>
  </body>
  <script type="text/javascript">
      function login(){
          var u_name = $.trim($("#username").val());
          var u_pwd = $.trim($("#password").val());
          var isRemPwd = $("#c1").is(':checked');
          $.ajax({
              type:'post',
              url:'user/login?username='+u_name+'&userpwd='+u_pwd+'&isRemPwd='+isRemPwd,
              success:function(msg){
                  if('admin' == msg){
                      window.location.href= "<%=basePath%>admin/index";
                  }else if('failure' == msg){
                      $("#prompt").html("用户名或密码错误！");
                  }
              }
          });
          return false;
      }
  </script>
  <script src="foreground/js/validationForms.js"></script>
  <script>
      //用户名验证
      function usernameIsString(){
          var value = $("#username").val();
          var isStr = IsString(value);
          if(!isStr){
              $("#lab_username").text("必须为数字和字母组合，且至少6位");
          }else{
              $("#lab_username").text("");
          }
      }

      //密码验证
      function pwdIsString(){
          var value = $("#password").val();
          var isStr = IsString(value);
          if(!isStr){
              $("#lab_pwd").text("必须为数字和字母组合，且至少6位");
          }else{
              $("#lab_pwd").text("");
          }
      }

      //验证用户名密码
      function validate_username_pwd() {
          var username = $("#username").val();
          var password = $("#password").val();
          var isTrue = false;
          $.ajax({
              type : "POST",
              async:false,
              url:"validate_login?username="+username+"&password="+password,
              success:function (data){
                  if(data == 'success'){
                      $("#lab_user_pwd").text("");
                      isTrue = true;
                  }else{
                      $("#lab_user_pwd").text("用户名或密码不正确");
                      isTrue = false;
                  }
              }
          });
          return isTrue;
      }

      //验证邮箱格式、该邮箱下用户是否存在
      function valid_email() {
          var value = $("#id_email").val();
          var isStr = validation_email(value);
          var isTure = false;
          if(!isStr){
              $("#lab_email").text("邮箱格式不正确");
              return false;
          }else{
              $("#lab_email").text("");
              $.ajax({
                  type : "post",
                  async : false,
                  url : "getUserByEmail?email="+value,
                  success : function (data) {
                      if(data == 'success'){
                          isTure = true;
                      }else{
                          $("#lab_email").text("该邮箱未注册该系统");
                          isTure = false;
                      }
                  }
              });
              return isTure;
          }
      }


      //忘记密码发送邮件
      function findPassword_sendMail(){
          var email = $("#id_email").val();
          if(valid_email() == true){
              $.ajax({
                  type : "post",
                  url : "findPassword_sendMail?email="+email,
                  success : function (data) {
                      if(data == 'success'){
                          $("#lab_send").text("已成功发送，请登录邮箱验证...");
                      }
                  }
              });
          }
      }

  </script>


</html>