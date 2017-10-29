<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
  <head>
  	<base href="<%=basePath%>"/>
    <meta charset="utf-8">
  </head>
  <body>  
      
    <footer style="text-align: center;" >
      <p style="font-family:Microsoft yahei;">版权所有 &copy; 2016 社会智能与复杂数据处理实验室 
      | Designed by <a style="font-family:Microsoft yahei;" href="#" target="_parent">15社会计算组</a>&nbsp;&nbsp;&nbsp;<a href="adminlogin" style="font-size:8px;color:grey;">我是管理员</a></p>
    </footer>  
  </body>
</html>