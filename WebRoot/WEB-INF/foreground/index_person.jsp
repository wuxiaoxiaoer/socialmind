<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/10/30
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页-个人</title>
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

        <!--/span-->
        <div class="span9" id="content">

            <div class="row-fluid">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left">统计</div>
                        <!--<div class="pull-right"><span class="badge badge-warning">View More</span></div> -->
                    </div>
                    <div class="block-content collapse in">
                        <div class="span3">
                            <div class="chart" data-percent="73">73%</div>
                            <div class="chart-bottom-heading"><span class="label label-info">新增热门事件数</span>

                            </div>
                        </div>
                        <div class="span3">
                            <div class="chart" data-percent="53">53%</div>
                            <div class="chart-bottom-heading"><span class="label label-info">我关注的领域新增信息数</span>

                            </div>
                        </div>
                        <div class="span3">
                            <div class="chart" data-percent="83">83%</div>
                            <div class="chart-bottom-heading"><span class="label label-info">我关注的事件有更新的个数</span>

                            </div>
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
                            <div class="muted pull-left">热门事件排行</div>
                            <div class="pull-right"><span class="badge badge-info" style="background-color:#f89406">更多...</span></div>
                            <div class="pull-right"><span class="badge badge-info">最新</span></div>
                            <div class="pull-right"><span class="badge badge-info">最热</span></div>
                        </div>
                        <div class="block-content collapse in">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>事件名称</th>
                                    <th>热度</th>
                                    <th>时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>十九大召开</td>
                                    <td>219</td>
                                    <td>2017-10-19 08:00</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Jacob</td>
                                    <td>Thornton</td>
                                    <td>@fat</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Vincent</td>
                                    <td>Gabriel</td>
                                    <td>@gabrielva</td>
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
                            <div class="muted pull-left">关注的事件</div>
                            <div class="pull-right"><span class="badge badge-info" style="background-color:#f89406">更多...</span></div>
                            <div class="pull-right"><span class="badge badge-info">最新</span></div>
                            <div class="pull-right"><span class="badge badge-info">增长最快</span></div>
                        </div>
                        <div class="block-content collapse in">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>事件名称</th>
                                    <th>热度</th>
                                    <th>时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>敏感事件</td>
                                    <td>219</td>
                                    <td>2017-10-19 08:00</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Jacket</td>
                                    <td>01/02/2013</td>
                                    <td>$335.00</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Shoes</td>
                                    <td>01/02/2013</td>
                                    <td>$29.99</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- /block -->
                </div>
            </div>
            <div class="row-fluid">
                <div class="row-fluid">
                    <div class="span6">
                        <!-- block -->
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">关心领域动态</div>
                                <div class="pull-right"><span class="badge badge-info" style="background-color:#f89406">更多...</span></div>
                                <div class="pull-right"><a href="manageTopics.html" style="text-decoration:none; color:#333;"><span class="badge badge-info" style="background-color: #1b561b;">
									+管理</span></a></div>
                                <div class="pull-right"><span class="badge badge-info">领域1</span></div>
                                <div class="pull-right"><span class="badge badge-info">领域2</span></div>
                            </div>
                            <div class="block-content collapse in">
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>事件名称</th>
                                        <th>热度</th>
                                        <th>领域</th>
                                        <th>时间</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>十九大召开</td>
                                        <td>25.12</td>
                                        <td>政治</td>
                                        <td>2017-10-24 12:12:22</td>
                                    </tr>
                                    <tr>
                                        <td>1</td>
                                        <td>十九大召开</td>
                                        <td>25.12</td>
                                        <td>政治</td>
                                        <td>2017-10-24 12:12:22</td>
                                    </tr>
                                    <tr>
                                        <td>1</td>
                                        <td>十九大召开</td>
                                        <td>25.12</td>
                                        <td>政治</td>
                                        <td>2017-10-24 12:12:22</td>
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
                                <div class="muted pull-left">日志中事件动态</div>
                                <div class="pull-right"><span class="badge badge-info" style="background-color:#f89406">更多...</span></div>
                                <div class="pull-right"><span class="badge badge-info">浏览</span></div>
                                <div class="pull-right"><span class="badge badge-info">留言</span></div>
                                <div class="pull-right"><span class="badge badge-info">点赞</span></div>
                                <div class="pull-right"><span class="badge badge-info">判别真假</span></div>
                                <div class="pull-right"><span class="badge badge-info">全部</span></div>
                            </div>
                            <div class="block-content collapse in">
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>事件名称</th>
                                        <th>热度</th>
                                        <th>操作名称</th>
                                        <th>时间</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>十九大召开</td>
                                        <td>25.12</td>
                                        <td>点赞</td>
                                        <td>2017-10-24 11:00:00</td>
                                    </tr>
                                    <tr>
                                        <td>1</td>
                                        <td>十九大召开</td>
                                        <td>25.12</td>
                                        <td>点赞</td>
                                        <td>2017-10-24 11:00:00</td>
                                    </tr>
                                    <tr>
                                        <td>1</td>
                                        <td>十九大召开</td>
                                        <td>25.12</td>
                                        <td>点赞</td>
                                        <td>2017-10-24 11:00:00</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- /block -->
                    </div>
                </div>

            </div>
            <div class="row-fluid">

            </div>
        </div>
    </div>
    <hr>
    <footer>
        <p>&copy; Vincent Gabriel 2013</p>
    </footer>
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
