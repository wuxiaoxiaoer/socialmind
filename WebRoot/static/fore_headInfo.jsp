<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/11/9
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<head>
    <title>socialmind大平台</title>
    <!-- Bootstrap -->
    <link href="foreground/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="foreground/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <link href="foreground/assets/styles.css" rel="stylesheet" media="screen">
    <link href="foreground/assets/DT_bootstrap.css" rel="stylesheet" media="screen">
    <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="foreground/vendors/flot/excanvas.min.js"></script><![endif]-->
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script src="foreground/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
</head>