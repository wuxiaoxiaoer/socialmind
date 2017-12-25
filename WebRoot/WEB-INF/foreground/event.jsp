<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>foreground/"/>
    <title>全网事件分析 - socialmind大平台</title>
    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <link href="assets/styles.css" rel="stylesheet" media="screen">
    <link href="assets/DT_bootstrap.css" rel="stylesheet" media="screen">
    <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="vendors/flot/excanvas.min.js"></script><![endif]-->
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script src="vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
</head>
<body>
<!-- 引入头模板 -->
<jsp:include page="/static/fore_header.jsp"/>



<div class="container-fluid" style="margin-top: 4%;">
    <div class="row-fluid">
        <div class="span3" id="sidebar">
            <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                <li>
                    <a><i class="icon-chevron-right"></i> 推荐事件</a>
                </li>
            </ul>
        </div>



        <!--/span-->
        <div class="span9" id="content">
            <!--title-->
            <h3 align="center">全网事件分析</h3>
            <form action="<%=basePath%>event">
                <input type="hidden" name="type" value="input"/>
                <div align="center"><input type="text" name="objectId" data-required="1" class="span5 m-wrap" placeholder="实体、事件关键词">
                    <a style="padding-top:-4px;"><button class="btn btn-success" style="margin:-11px 0 0 20px;">搜索</button></a>
                </div>
            </form>
            <!--map-->
            <div class="row-fluid">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left">热点关注事件</div>
                    </div>
                    <div class="block-content collapse in">
                        <c:forEach items="${event}" var="e" varStatus="sts">
                        <table style="font-size: 13px;" width="25%" height="20%" align="left">
                            <tbody>
                            <tr style="height: 30%">
                                <td width="25%" height="20%"><img src=" images/logo.jpg" class="img-responsive img-circle" width="70%"/></td>
                                <td style="color: grey" align="left"><strong><a href="<%=basePath%>event/eventInfo?type=view&&objectId=${e.object.objectId}">${e.object.name}</a></strong></td>
                            </tr>
                            <tr>
                                <%----%>
                                <td colspan="2"><div style="width:80%;overflow:hidden;line-height: 20px"><strong>摘要：</strong>${e.event.introduction}......</div></td>
                            </tr>
                            <tr>
                                <td style="color:#17d0ff">开始时间:<br>结束时间:</td>
                                <td>${e.event.eventBeginTime}<br>${e.event.eventEndTime}</td>
                            </tr>

                            </tbody>
                        </table>
                        </c:forEach>
                    </div>

                </div>
                <!-- block -->
            </div>


            </div>
        </div>
        <hr>
        <!-- 引入尾部模板 -->
        <jsp:include page="/static/fore_footer.jsp"></jsp:include>
    </div>

    <!--/.fluid-container-->

    <script src=" vendors/jquery-1.9.1.js"></script>
    <script src=" bootstrap/js/bootstrap.min.js"></script>
    <script src=" vendors/datatables/js/jquery.dataTables.min.js"></script>


    <script src=" assets/scripts.js"></script>
    <script src=" assets/DT_bootstrap.js"></script>
    <script>
        $(function() {

        });
    </script>
</body>

</html>
