
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
    <base href="<%=basePath%>foreground/"/>
    <title>制作简报-5</title>
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

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span3" id="sidebar">
            <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                <li class="active">
                    <a href="index.html"><i class="icon-chevron-right"></i> 曾制作过的简报</a>
                </li>

            </ul>
        </div>

        <!--/span-->
        <div class="span9" id="content">
            <div class="row-fluid">
                <div class="alert alert-success">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <h4>Success</h4>
                    简报成功制作完成</div>
                <div class="navbar">
                    <div class="navbar-inner">
                        <ul class="breadcrumb">
                            <i class="icon-chevron-left hide-sidebar"><a href="#" title="Hide Sidebar" rel="tooltip">&nbsp;</a></i>
                            <i class="icon-chevron-right show-sidebar" style="display:none;"><a href="#" title="Show Sidebar" rel="tooltip">&nbsp;</a></i>
                            <li>
                                返回主页 <span class="divider">/</span>
                            </li>
                            <li class="active"><a href="#">下载查看...</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- block -->
            <div class="row-fluid">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left">曾制作过的简报...</div>
                    </div>
                    <div class="block-content collapse in">
                        <div class="span12">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>简报名称</th>
                                    <th>简报简介</th>
                                    <th>生成时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr class="success">
                                    <td>1</td>
                                    <td>十九大召开</td>
                                    <td>十九大的热度指标/情绪地图</td>
                                    <td>2017-10-10 10:10</td>
                                </tr>
                                <tr class="error">
                                    <td>1</td>
                                    <td>十九大召开</td>
                                    <td>十九大的信息监控</td>
                                    <td>2017-10-10 10:10</td>
                                </tr>
                                <tr class="info">
                                    <td>1</td>
                                    <td>十九大召开</td>
                                    <td>十九大的热度度量</td>
                                    <td>2017-10-10 10:10</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- /block -->
            </div>
            <!-- /block -->
        </div>
    </div>
</div>
<hr>

<!-- 引入尾部模板 -->
<jsp:include page="/static/fore_footer.jsp"></jsp:include>

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
