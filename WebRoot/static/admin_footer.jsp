<%--
  Created by IntelliJ IDEA.
  User: DeMH
  Date: 2017/11/2
  Time: 9:15
  系统后台 - 尾部模板.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="row-fluid">
    <div id="footer" class="span12"> 2017 &copy; sicdpLab Admin. 社会智能与复杂数据处理实验室</div>
</div>
