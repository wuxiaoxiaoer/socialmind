
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>foreground/"/>
    <title>制作简报列表 - socialmind大平台</title>
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

<div class="container-fluid" style="margin-top: 4%;">
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
                            <div class="pull-right"><span class="badge badge-info">3套</span>

                            </div>
                        </div>
                        <div class="block-content collapse in">
                            <div class="row-fluid padd-bottom">
                                <div class="span4">
                                    <a href="javascript:void(0);" class="thumbnail" onclick="chooseReporterStyle('1');">
                                        <img data-src="holder.js" alt="简报样式1" src="images/style1_demo.png">
                                    </a>
                                </div>
                                <div class="span4">
                                    <a href="javascript:void(0);" class="thumbnail" onclick="chooseReporterStyle('2');">
                                        <img data-src="holder.js" alt="简报样式1" src="images/style2_demo.png">
                                    </a>
                                </div>
                                <div class="span4">
                                    <a href="javascript:void(0);" class="thumbnail" onclick="chooseReporterStyle('3');">
                                        <img data-src="holder.js" alt="简报样式1" style="width: 260px; height: 180px;" src="images/summarizeStyle.jpg">
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function chooseReporterStyle(index) {
        window.location.href = "<%=basePath%>ReporterMaterial?style="+index;
    }

</script>

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