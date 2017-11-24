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
                <li class="active">
                    <a href="index.html"><i class="icon-chevron-right"></i> 政府热门事件</a>
                </li>
                <li>
                    <a href="calendar.html"><i class="icon-chevron-right"></i> 事业单位热门事件</a>
                </li>
                <li>
                    <a href="stats.html"><i class="icon-chevron-right"></i> 同类事业单位热门事件 (Charts)</a>
                </li>
                <li>
                    <a href="form.html"><i class="icon-chevron-right"></i> 企业热门事件</a>
                </li>
                <li>
                    <a href="tables.html"><i class="icon-chevron-right"></i> 相关企业热门事件</a>
                </li>
                <li>
                    <a href="buttons.html"><i class="icon-chevron-right"></i> 个人热门事件</a>
                </li>


            </ul>
        </div>

        <!--/span-->
        <div class="span9" id="content">


            <h2 align="center">热度指数 </h2>



            <div class="block-content collapse in">
                <div align="center"><input type="text" name="name" data-required="1" class="span5 m-wrap" placeholder="实体、事件关键词">
                    <a href="<%=basePath%>heatGraph" style="padding-top:-4px;"><button class="btn btn-success" style="margin:-11px 0 0 20px;">搜索</button></a>
                </div>
            </div>

            <div class="row-fluid">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left">政府热门事件</div>
                    </div>
                    <div class="block-content collapse in">
                        <div class="span12">

                            <table cellpadding="0" cellspacing="0" border="0" class="table table-striped">
                                <thead>
                                <tr>
                                    <th width="10%">排名</th>
                                    <th>事件名称</th>
                                    <th>热度指数</th>

                                </tr>
                                </thead>
                                <tbody>
                                <tr class="odd gradeX">
                                    <td>1</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>
                                <tr class="even gradeC">
                                    <td>2</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>
                                <tr class="odd gradeA">
                                    <td>3</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>
                                <tr class="even gradeA">
                                    <td>4</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>

                                <tr class="even gradeA">
                                    <td>5</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>

                                </tbody>
                            </table>
                        </div>
                    </div>


                </div>
                <!-- /block -->

            </div>





            <div class="row-fluid">
                <div class="span6">
                    <!-- block -->
                    <div class="block">
                        <div class="navbar navbar-inner block-header">
                            <div class="muted pull-left">事业单位热门事件</div>
                            <div class="pull-right"><span class="badge badge-info">更多</span>

                            </div>
                        </div>
                        <div class="block-content collapse in">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th width="10%">排名</th>
                                    <th>事件名称</th>
                                    <th>热度指数</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr class="odd gradeX">
                                    <td>1</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>
                                <tr class="even gradeC">
                                    <td>2</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>
                                <tr class="odd gradeA">
                                    <td>3</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>
                                <tr class="even gradeA">
                                    <td>4</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>

                                <tr class="even gradeA">
                                    <td>5</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>

                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- /block -->
                </div>
                <div class="span6">
                    <!-- block -->
                    <div class="block">
                        <div class="navbar navbar-inner block-header">
                            <div class="muted pull-left">同类事业单位热门事件</div>
                            <div class="pull-right"><span class="badge badge-info">更多</span>

                            </div>
                        </div>
                        <div class="block-content collapse in">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th width="10%">排名</th>
                                    <th>事件名称</th>
                                    <th>热度指数</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr class="odd gradeX">
                                    <td>1</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>
                                <tr class="even gradeC">
                                    <td>2</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>
                                <tr class="odd gradeA">
                                    <td>3</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>
                                <tr class="even gradeA">
                                    <td>4</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>

                                <tr class="even gradeA">
                                    <td>5</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>

                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- /block -->
                </div>
            </div>






            <div class="row-fluid">
                <div class="span6">
                    <!-- block -->
                    <div class="block">
                        <div class="navbar navbar-inner block-header">
                            <div class="muted pull-left">企业热门事件</div>
                            <div class="pull-right"><span class="badge badge-info">更多</span>

                            </div>
                        </div>
                        <div class="block-content collapse in">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th width="10%">排名</th>
                                    <th>事件名称</th>
                                    <th>热度指数</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr class="odd gradeX">
                                    <td>1</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>
                                <tr class="even gradeC">
                                    <td>2</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>
                                <tr class="odd gradeA">
                                    <td>3</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>
                                <tr class="even gradeA">
                                    <td>4</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>

                                <tr class="even gradeA">
                                    <td>5</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>

                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- /block -->
                </div>
                <div class="span6">
                    <!-- block -->
                    <div class="block">
                        <div class="navbar navbar-inner block-header">
                            <div class="muted pull-left">相关企业热门事件</div>
                            <div class="pull-right"><span class="badge badge-info">更多</span>

                            </div>
                        </div>
                        <div class="block-content collapse in">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th width="10%">排名</th>
                                    <th>事件名称</th>
                                    <th>热度指数</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr class="odd gradeX">
                                    <td>1</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>
                                <tr class="even gradeC">
                                    <td>2</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>
                                <tr class="odd gradeA">
                                    <td>3</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>
                                <tr class="even gradeA">
                                    <td>4</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>

                                <tr class="even gradeA">
                                    <td>5</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>

                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- /block -->
                </div>
            </div>



            <div class="row-fluid">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left">个人热门事件</div>
                    </div>
                    <div class="block-content collapse in">
                        <div class="span12">

                            <table cellpadding="0" cellspacing="0" border="0" class="table table-striped" id="example">
                                <thead>
                                <tr>
                                    <th width="10%">排名</th>
                                    <th>事件名称</th>
                                    <th>热度指数</th>

                                </tr>
                                </thead>
                                <tbody>
                                <tr class="odd gradeX">
                                    <td>1</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>
                                <tr class="even gradeC">
                                    <td>2</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>
                                <tr class="odd gradeA">
                                    <td>3</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>
                                <tr class="even gradeA">
                                    <td>4</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>

                                <tr class="even gradeA">
                                    <td>5</td>

                                    <td class="center">事件1</td>
                                    <td class="center">3</td>
                                </tr>

                                </tbody>
                            </table>
                        </div>
                    </div>


                </div>
                <!-- /block -->

            </div>



        </div>
        <hr>
        <!-- 引入尾部模板 -->
        <jsp:include page="/static/fore_footer.jsp"></jsp:include>
</div>
    <!--/.fluid-container-->
    <script src="vendors/jquery-1.9.1.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="vendors/datatables/js/jquery.dataTables.min.js"></script>


    <script src="assets/scripts.js"></script>
    <script src="assets/DT_bootstrap.js"></script>
    <script>
        $(function() {
            // Easy pie charts
            $('.chart').easyPieChart({animate: 1000});
        });
    </script>
</body>

</html>
