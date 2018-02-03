<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/1/23
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
    <title>舆情事件分析</title><meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="admin/css/bootstrap.min.css" />
    <link rel="stylesheet" href="admin/css/bootstrap-responsive.min.css" />
    <link rel="stylesheet" href="admin/css/maruti-style.css" />
    <link rel="stylesheet" href="admin/css/maruti-media.css" class="skin-color" />
    <script src="foreground/js/echarts.min.js"></script>
    <script src="foreground/js/jquery.min.js"></script>
</head>
<body>

<div>
    <!-- 引入后台头模板-->
    <jsp:include page="/static/admin_header.jsp"></jsp:include>

    <!--close-top-Header-menu-->

    <div id="content">
        <div id="content-header">
            <div id="breadcrumb">
                <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
                <a href="" class="current"></a>
            </div>
            <br>
            <div style=" margin-left: 5%;">

                <a onclick="document.getElementById('001').scrollIntoView();" class="btn btn-primary btn-large">全部事件列表</a>
                <a onclick="document.getElementById('002').scrollIntoView();" class="btn btn-primary btn-large">全部实体列表</a>
                <a onclick="document.getElementById('003').scrollIntoView();" class="btn btn-primary btn-large">事件分类列表</a>
            </div>
        </div>

        <div class="container-fluid">

            <div class="row-fluid" style="height:70%">
                <div class="span4">
                    <div class="widget-box" style="height: 250px">
                        <div class="widget-title">
								<span class="icon">
									<i class="icon-eye-open"></i>
								</span>
                            <h5>事件列表</h5>
                        </div>
                        <div class="widget-content nopadding">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th></th>
                                    <th>事件名称</th>

                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${eventList}" var="e" varStatus="sts">
                                    <tr class="gradeX">
                                        <input type="hidden" value="${e.objectID}">
                                        <td>${sts.index + 1}</td>
                                        <td><a href="<%=basePath%>event/eventInfo?type=view&&objectId=${e.objectID}">${e.name}</a></td>
                                    </tr>
                                </c:forEach>


                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="span4">
                    <div class="widget-box"  style="height: 250px">
                        <div class="widget-title">
								<span class="icon">
									<i class="icon-arrow-right"></i>
								</span>
                            <h5>实体列表</h5>
                        </div>
                        <div class="widget-content nopadding">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th></th>
                                    <th>实体名称</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${entityList}" var="el" varStatus="sts">
                                <tr>
                                    <td>${sts.index + 1}</td>
                                    <td>${el}</td>
                                </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="span4">
                    <div class="widget-box" style="height: 250px">
                        <div class="widget-title">
								<span class="icon">
									<i class="icon-file"></i>
								</span>
                            <h5>不同类型的事件列表</h5>
                        </div>
                        <div class="widget-content nopadding">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th></th>
                                    <th>类型</th>
                                    <th>事件名称</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${eventOfType}" var="et" varStatus="sts">
                                    <tr>
                                        <td>${sts.index + 1}</td>
                                        <td>${et.type}</td>
                                        <td><a href="<%=basePath%>event/eventInfo?type=view&&objectId=${et.objectID}">${et.eventName}</a></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>


            </div>
        </div>
    </div>

    <!-- 引入后台尾部模板 -->
    <jsp:include page="/static/admin_footer.jsp"></jsp:include>

</body>

<script src="admin/js/jquery.min.js"></script>
<script src="admin/js/jquery.ui.custom.js"></script>
<script src="admin/js/bootstrap.min.js"></script>
<script src="admin/js/maruti.js"></script>

</html>

