<%--
  Created by IntelliJ IDEA.
  User: DeMH
  Date: 2017/11/2
  Time: 9:11
  系统前台 - 头模板.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<hr>
<footer style="position: fixed;bottom: 0;align-content: center" >
    <p>&copy; sicdpLab 2017 社会智能与复杂数据处理实验室 </p>
</footer>




