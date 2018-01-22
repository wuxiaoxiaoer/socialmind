<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//UserEntity user = (UserEntity) session.getAttribute("user");
%>

<html>
<head>
    <title>信息监测 - socialmind大平台</title>
    <base href="<%=basePath%>foreground/" />
    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <link href="vendors/easypiechart/jquery.easy-pie-chart.css" rel="stylesheet" media="screen">
    <link href="assets/styles.css" rel="stylesheet" media="screen">
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script src="vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>

</head>

<body>

<!-- 引入头模板 -->
<jsp:include page="/static/fore_header.jsp"/>

<div class="span3" id="sidebar">
    <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
        <li>
            <a></i> 当前信息监测类型为事件</a>
        </li>
    </ul>
</div>
<div class="container-fluid" style="margin-top: 4%;">
    <div class="row-fluid">

        <!--/span-->
        <div class="span9" id="content">
            <div class="row-fluid">

                <div class="row-fluid">
                    <!-- block -->
                    <div class="block">
                        <div class="navbar navbar-inner block-header">
                            <div class="muted pull-left">信息监测</div>
                        </div>
                        <form action="<%=basePath%>infodetection/detection">
                            <!-- 判别全文检索 -->
                            <div class="block-content collapse in">
                                <div align="center"><input type="text" name="object" data-required="1" class="span5 m-wrap" placeholder="请输入关键词">
                                    <button type="submit" class="btn btn-success" style="margin: -10px 0 0 10px;">搜索</button>
                                </div>
                                <%--<div align="center">
                                    <h3>可选择以下标签:</h3>
                                <p>
                                    <button class="btn btn-danger">热点</button>
                                    <button class="btn">政治</button>
                                    <button class="btn btn-primary">科技</button>
                                    <button class="btn btn-info">娱乐</button>
                                    <button class="btn btn-success">教育</button>
                                    <button class="btn btn-warning">金融</button>
                                    <button class="btn btn-inverse">Button</button>
                                </p>
                                </div>--%>
                            </div>
                        </form>
                    </div>
                    <!-- /block -->
                </div>
            </div>

            <c:if test="${event!=null}">
            <div class="row-fluid">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">

                    </div>
                    <div class="block-content collapse in">
                        <c:forEach items="${event}" var="e" varStatus="sts">
                            <table style="font-size: 13px;" width="25%" height="20%" align="left">
                                <tbody>
                                <tr style="height: 30%">
                                    <td width="25%" height="20%"><img src=" images/logo.jpg" class="img-responsive img-circle" width="70%"/></td>
                                    <td style="color: grey" align="left"><strong><a href="<%=basePath%>infodetection/graph?objectId=${e.object.objectId}">${e.object.name}</a></strong></td>
                                </tr>
                                <tr>
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
            </c:if>

        </div>
    </div>
    <!-- 引入尾部模板 -->
    <jsp:include page="/static/fore_footer.jsp"></jsp:include>

    <!--/.fluid-container-->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="vendors/easypiechart/jquery.easy-pie-chart.js"></script>
    <script src="assets/scripts.js"></script>
    <script>
        $(function() {
            // Easy pie charts
            $('.chart').easyPieChart({animate: 1000});
        });
    </script>

</body>
</html>