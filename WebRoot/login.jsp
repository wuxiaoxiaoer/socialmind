<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <title>登陆 - socialmind大平台</title>
    <!-- Bootstrap -->
    <link href="foreground/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="foreground/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <link href="foreground/assets/styles.css" rel="stylesheet" media="screen">
     <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script src="foreground/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>
  </head>
  <body id="login">
    <div class="container">
      <form class="form-signin" action="login">
        <h2 class="form-signin-heading">请登录</h2>
        <input type="text" name="username" class="input-block-level" placeholder="用户名">
        <input type="password" name="userpwd" class="input-block-level" placeholder="密码">
		<div class="control-group">
            <div class="controls">
                <select id="select01" name="userType" class="input-block-level">
                    <option value="individual">个人用户</option>
                    <option value="company">企业用户</option>
                    <option value="government">政府用户</option>
                    <option value="institution">事业单位用户</option>
                </select>
            </div>
        </div>

        <label class="checkbox">
          <input type="checkbox" value="remember-me"> 记住用户
        </label>
        <button class="btn btn-large btn-primary">登录</button>
      </form>

    </div> <!-- /container -->
    <script src="foreground/vendors/jquery-1.9.1.min.js"></script>
    <script src="foreground/bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>