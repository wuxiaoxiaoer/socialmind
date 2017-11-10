<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>全网事件分析 - socialmind大平台</title>
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
<body>
<!-- 引入头模板 -->
<jsp:include page="/static/fore_header.jsp"/>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span3" id="sidebar">
            <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                <li>
                    <a href=""><i class="icon-chevron-right"></i> 推荐事件</a>
                </li>
                <li class="active">
                    <a href=""><i class="icon-chevron-right"></i>全网事件分析</a>
                </li>

            </ul>
        </div>



        <!--/span-->
        <div class="span9" id="content">
            <!--title-->
            <h3 align="center">全网事件分析</h3>
            <div align="center"><input type="text" name="name" data-required="1" class="span5 m-wrap" placeholder="实体、事件关键词">
                <a href="" style="padding-top:-4px;"><button class="btn btn-success" style="margin:-11px 0 0 20px;">搜索</button></a>
            </div>
            
            <!--map-->
            <div class="row-fluid">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left">热点关注事件</div>
                        <div class="pull-right"><span class="badge badge-warning">刷新</span>
                        </div>
                    </div>
                    <div class="block-content collapse in">
                        <table style="table-layout: fixed;font-size: 12px;" width="15%" align="left">
                        <c:forEach items="${eventList}" var="e" varStatus="sts">
                            <tbody>

                            <tr style="height: 40%">
                                <td width="30%" height="40%"><img src="foreground/images/67be458fjw8exz23yekkwj2050050t8q.jpg" class="img-responsive img-circle"/></td>
                                <td style="color: grey"><strong>${e.name}</strong></td>
                            </tr>
                            <tr style="width: 20%">
                                <td colspan="2"><div style="width:100%;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;">摘要:${e.introduction}</div></td>
                            </tr>
                            <tr>
                                <td style="color: #00b7ee">开始时间:<br>结束时间:</td>
                                <td>${e.eventBeginTime}<br>${e.eventEndTime}</td>
                            </tr>
                            </tbody>
                        </table>
                        </c:forEach>
                    </div>

                    <div class="block-content collapse in">
                        <a href=""><img src="foreground/images/event1.png" height="200px" width="300px"></a>
                        <a href=""><img src="foreground/images/event2.png" height="200px" width="300px"></a>
                        <a href=""><img src="foreground/images/event3.png" height="200px" width="300px"></a>
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

    <script src="foreground/vendors/jquery-1.9.1.js"></script>
    <script src="foreground/bootstrap/js/bootstrap.min.js"></script>
    <script src="foreground/vendors/datatables/js/jquery.dataTables.min.js"></script>


    <script src="foreground/assets/scripts.js"></script>
    <script src="foreground/assets/DT_bootstrap.js"></script>
    <script>
        $(function() {

        });
    </script>
</body>

</html>
