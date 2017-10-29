<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
  <head>
  	<base href="<%=basePath%>">
    <meta charset="utf-8">
  </head>
  <body>  
    <!-- Left column -->
    <div class="templatemo-flex-row">
      <div class="templatemo-sidebar">
        <header class="templatemo-site-header" style="height:56px;">
          <div class="square"></div>
          <h1 style="font-family: 华文行楷;font-size: 22px;">微博舆情分析系统</h1>
        </header>
        
        <div class="profile-photo-container">
          <img src="images/profile-photo.jpg" alt="Profile Photo" class="img-responsive">  
          <div class="profile-photo-overlay"></div>
        </div>
        <div class="mobile-menu-icon">
            <i class="fa fa-bars"></i>
        </div>
        <nav class="templatemo-left-nav">
          <ul id="nav">
            <li><a href="<%=basePath%>index" class="active"><i class="fa fa-home fa-fw"></i>今日热点</a></li>
            <li><a href="<%=basePath%>latest"><i class="fa fa-bar-chart fa-fw"></i>最近要闻</a></li>
            <li><a href="<%=basePath%>maps"><i class="fa fa-database fa-fw"></i>地域分布图</a></li>
            <li><a href="<%=basePath%>perkeymaps"><i class="fa fa-database fa-fw"></i>特定关键词地图</a></li>
            <li><a href="<%=basePath%>search"><i class="fa fa-users fa-fw"></i>搜 索</a></li>
            <li><a href="<%=basePath%>analyser"><i class="fa fa-users fa-fw"></i>微分析</a></li>
          </ul> 
        </nav>
      </div>
    </div>    
  </body>
	<script src="js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript">
	   $(function(){
	   $("a").click(function(){
	        $("a").removeClass("active");
	        $(this).addClass("active");
		   });
		});
	</script>
</html>