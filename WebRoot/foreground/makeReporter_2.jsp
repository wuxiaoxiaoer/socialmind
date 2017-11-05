<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/10/30
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>制作简报-2</title>
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
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#">social mind舆情分析平台</a>
            <div class="nav-collapse collapse">
                <ul class="nav pull-right">
                    <li class="dropdown">
                        <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user"></i> 吴连伟 <i class="caret"></i>

                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a tabindex="-1" href="person_info.html">个人中心</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a tabindex="-1" href="login.html">Logout</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav">
                    <li class="">
                        <a href="heatIndex.html">热度指标</a>
                    </li>
                    <li class="">
                        <a href="emotionMap.html">情绪地图</a>
                    </li>
                    <li class="">
                        <a href="info_dectection.html">信息监测</a>
                    </li>
                    <li class="">
                        <a href="fullRetrival.html">全文检索</a>
                    </li>
                    <li class="">
                        <a href="event.html">全网事件分析</a>
                    </li>
                    <li class="">
                        <a href="makeReporter_1.html">简报制作</a>
                    </li>
                </ul>
            </div>
            <!--/.nav-collapse -->
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span3" id="sidebar">
            <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                <li class="active">
                    <a href="index.html"><i class="icon-chevron-right"></i> 选择样式</a>
                </li>

            </ul>
        </div>

        <!--/span-->
        <div class="span9" id="content">

            <div class="row-fluid">
                <div class="row-fluid">
                    <!-- block -->
                    <div class="block">
                        <div class="navbar navbar-inner block-header">
                            <div class="muted pull-left">选择样式</div>
                            <div class="pull-right"><span class="badge badge-info">4套</span>

                            </div>
                        </div>
                        <div class="block-content collapse in">
                            <div class="row-fluid padd-bottom">
                                <div class="span3">
                                    <a href="#" class="thumbnail">
                                        <img data-src="holder.js" alt="简报样式1" style="width: 260px; height: 180px;" src="images/summarizeStyle.jpg">
                                    </a>
                                </div>
                                <div class="span3">
                                    <a href="#" class="thumbnail">
                                        <img data-src="holder.js" alt="简报样式1" style="width: 260px; height: 180px;" src="images/summarizeStyle.jpg">
                                    </a>
                                </div>
                                <div class="span3">
                                    <a href="#" class="thumbnail">
                                        <img data-src="holder.js" alt="简报样式1" style="width: 260px; height: 180px;" src="images/summarizeStyle.jpg">
                                    </a>
                                </div>
                                <div class="span3">
                                    <a href="#" class="thumbnail">
                                        <img data-src="holder.js" alt="简报样式1" style="width: 260px; height: 180px;" src="images/summarizeStyle.jpg">
                                    </a>
                                </div>
                            </div>

                        </div>
                    </div>
                    <button class="btn btn-warning btn-large" style="margin-left:40%; margin-top: 20px; padding:10px;"
                            onclick="javascript:window.location.href='makeReporter_3.html'">选定样式&nbsp>></button>
                    <!-- /block -->
                </div>
            </div>
        </div>
        <hr>
        <footer>

        </footer>
    </div>
</div>
    <!--/.fluid-container-->
    <script src="vendors/jquery-1.9.1.min.js"></script>
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