<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sicdlib.entity.UserEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //UserEntity user = (UserEntity) session.getAttribute("user");
%>

<html>
<head>
    <title>主页-政府</title>
    <base href="<%=basePath%>foreground/"/>
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

        <!--/span-->
        <div class="span9" id="content" style="margin-top: 4%;">
            <div class="row-fluid">
                <div class="alert alert-success">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <h4>欢迎您！</h4>
                    政府用户</div>
                <div class="navbar">
                    <div class="navbar-inner">
                        <ul class="breadcrumb">
                            <i class="icon-chevron-right show-sidebar" style="display:none; float: right;width: 140px; background-image: none;"><div class="pull-right"><span class="badge badge-info">点击进行&nbsp;留言&nbsp;点赞&nbsp;判别真假</span></div></i>
                            <li>
                                <a href="#">系统操作</a> <span class="divider">/</span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
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
                            <div class="chart-bottom-heading"><span class="label label-info">新增敏感信息数</span>

                            </div>
                        </div>
                        <div class="span3">
                            <div class="chart" data-percent="83">83%</div>
                            <div class="chart-bottom-heading"><span class="label label-info">新增领导动态数</span>

                            </div>
                        </div>
                        <div class="span3">
                            <div class="chart" data-percent="13">13%</div>
                            <div class="chart-bottom-heading"><span class="label label-info">新增下属动态数</span>

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
                            <div class="muted pull-left">当地热门事件排行</div>
                            <div class="pull-right"><span class="badge badge-info" style="background-color:#f89406">更多...</span></div>
                            <div class="pull-right"><span class="badge badge-info">最新</span></div>
                            <div class="pull-right"><span class="badge badge-info">最热</span></div>
                        </div>
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
                                    <c:forEach items="${top5DynamicObjIndexs}" var="d" varStatus="sts">
                                        <tr>
                                            <td>${sts.index + 1}</td>
                                            <td><a>${d.object.name}</a></td>
                                            <td>${d.hotValue}</td>
                                            <td>${d.object.addTime}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- /block -->
                </div>
                <div class="span6">
                    <!-- block -->
                    <div class="block">
                        <div class="navbar navbar-inner block-header">
                            <div class="muted pull-left">敏感、苗头事件初探</div>
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
                <div class="span6">
                    <!-- block -->
                    <div class="block">
                        <div class="navbar navbar-inner block-header">
                            <div class="muted pull-left">领导动态</div>
                            <div class="pull-right"><span class="badge badge-info" style="background-color:#f89406">更多...</span></div>&nbsp
                            <div class="pull-right"><a href="managePersons.html" style="text-decoration:none; color:#333;"><span class="badge badge-info" style="background-color: #1b561b;">
									+管理</span></a></div>
                        </div>
                        <div class="block-content collapse in">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>人物名称</th>
                                    <th>热度</th>
                                    <th>新动态个数</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>系统上级领导</td>
                                    <td>100</td>
                                    <td>20</td>
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
                            <div class="muted pull-left">下属机关部门动态</div>
                            <div class="pull-right"><span class="badge badge-info" style="background-color:#f89406">更多...</span></div>
                            <div class="pull-right"><a href="managePeerInstitutions.html" style="text-decoration:none; color:#333;"><span class="badge badge-info" style="background-color: #1b561b;">
									+管理</span></a></div>
                        </div>
                        <div class="block-content collapse in">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>机构名称</th>
                                    <th>热度</th>
                                    <th>时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>机构1</td>
                                    <td>25.12</td>
                                    <td>2017-10-24 11:10:11</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>机构1</td>
                                    <td>25.12</td>
                                    <td>2017-10-24 11:10:11</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>机构1</td>
                                    <td>25.12</td>
                                    <td>2017-10-24 11:10:11</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- /block -->
                </div>
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
