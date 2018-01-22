<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sicdlib.entity.UserEntity" %>
<%@ page import="com.sicdlib.entity.RoleEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    UserEntity user = (UserEntity) session.getAttribute("commonUser");
    RoleEntity role = null;
    String roleName = null;
    if (user != null){
        role = user.getRole();
        roleName = role.getRoleName();
    }
%>

<!DOCTYPE html>
<html class="no-js">
    <head>
        <base href="<%=basePath%>foreground/"/>
        <title>首页 - Socialmind大平台</title>
        <!-- Bootstrap -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
        <link href="vendors/easypiechart/jquery.easy-pie-chart.css" rel="stylesheet" media="screen">
        <link href="assets/styles.css" rel="stylesheet" media="screen">
        <!--[if lt IE 9]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <script src="vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
        <script src="vendors/jquery-1.9.1.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                //通过调用新浪IP地址库接口查询用户当前所在国家、省份、城市、运营商信息
                $.getScript('http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js',function(){
                    $(".country").html(remote_ip_info.country);
                    $(".province").html(remote_ip_info.province);
                    $(".city").html(remote_ip_info.city);
                    $(".isp").html(remote_ip_info.isp);
                });
            });
        </script>
    </head>
    
    <body>

    <!-- 引入头模板 -->
    <jsp:include page="/static/fore_header.jsp"/>

        <div class="container-fluid" style="margin-top: 4%;">
            <div class="row-fluid">
                <div class="span3" id="sidebar">
                    <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                        <li class="active">
                            <a href="index.html"><i class="icon-chevron-right"></i> 统计</a>
                        </li>
                        <li>
                            <a href="calendar.html"><i class="icon-chevron-right"></i> 该单位相关事件排行</a>
                        </li>
                        <li>
                            <a href="stats.html"><i class="icon-chevron-right"></i> 单位内部事件挖掘</a>
                        </li>
                        <li>
                            <a href="form.html"><i class="icon-chevron-right"></i> 主要领导动态</a>
                        </li>
                        <li>
                            <a href="tables.html"><i class="icon-chevron-right"></i> 所在领域动态</a>
                        </li>
                        <li>
                            <a href="buttons.html"><i class="icon-chevron-right"></i>日志中事件动态</a>
                        </li>
                    </ul>
                </div>
                
                <!--/span-->
                <div class="span9" id="content">
                    <div class="row-fluid">
                        <div class="alert alert-success">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
                            <h4>欢迎您！</h4>
                        	<c:out value="${sessionScope.commomUser.role.roleName}"/>用户, 当前您所处的位置: <span class="country"></span>-<span class="province"></span>-<span class="city"></span></div>
                            <div></div>
                            <div class="navbar">
                                    <div class="navbar-inner">
                                        <ul class="breadcrumb">
                                            <i class="icon-chevron-left hide-sidebar"><a href='#' title="Hide Sidebar" rel='tooltip'>&nbsp;</a></i>
                                            <i class="icon-chevron-right show-sidebar" style="display:none;"><a href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a></i>
                                            <li>
                                                <a href="#">Dashboard</a> <span class="divider">/</span>
                                            </li>
                                            <li>
                                                <a href="#">Settings</a> <span class="divider">/</span>
                                            </li>
                                            <li class="active">Tools</li>
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
                                    <div class="chart-bottom-heading"><span class="label label-info">新增该单位相关事件数</span>
                                    </div>
                                </div>
                                <div class="span3">
                                    <div class="chart" data-percent="53">53%</div>
                                    <div class="chart-bottom-heading"><span class="label label-info">新增该系统内部事件数</span>

                                    </div>
                                </div>
                                <div class="span3">
                                    <div class="chart" data-percent="83">83%</div>
                                    <div class="chart-bottom-heading"><span class="label label-info">新增同行事件数</span>

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
                        <%
                            if (roleName.equals("政府")){
                        %>
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
                                    <div class="pull-right"><a href="<%=basePath%>object/managePersons?flag=leader" style="text-decoration:none; color:#333;"><span class="badge badge-info" style="background-color: #1b561b;">
									+管理</span></a></div>
                                </div>
                                <div class="block-content collapse in">
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th>序号</th>
                                            <th>人物名称</th>
                                            <th>浏览量</th>
                                            <th>新动态个数</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${objectUsersLeader}" var="ou" varStatus="sts">
                                            <tr>
                                                <td>${sts.index + 1}</td>
                                                <td>${ou.objectEntity.name}</td>
                                                <td>${ou.objectEntity.scanNumber}</td>
                                                <td>${ou.objectEntity.addTime}</td>
                                            </tr>
                                        </c:forEach>
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
                                    <div class="pull-right"><a href="<%=basePath%>object/managePersons?flag=department" style="text-decoration:none; color:#333;"><span class="badge badge-info" style="background-color: #1b561b;">
									+管理</span></a></div>
                                </div>
                                <div class="block-content collapse in">
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th>序号</th>
                                            <th>机构名称</th>
                                            <th>浏览量</th>
                                            <th>时间</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${objectUsersDepartment}" var="ou" varStatus="sts">
                                            <tr>
                                                <td>${sts.index + 1}</td>
                                                <td>${ou.objectEntity.name}</td>
                                                <td>${ou.objectEntity.scanNumber}</td>
                                                <td>${ou.objectEntity.addTime}</td>
                                            </tr>
                                        </c:forEach>
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
                                        <div class="pull-right"><a href="<%=basePath%>object/managePersons?flag=field" style="text-decoration:none; color:#333;"><span class="badge badge-info" style="background-color: #1b561b;">
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
                                                <th>浏览量</th>
                                                <th>时间</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${objectUsersField}" var="ou" varStatus="sts">
                                                <tr>
                                                    <td>${sts.index + 1}</td>
                                                    <td>${ou.objectEntity.name}</td>
                                                    <td>${ou.objectEntity.scanNumber}</td>
                                                    <td>${ou.objectEntity.addTime}</td>
                                                </tr>
                                            </c:forEach>
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
                        <%}%>

                </div>
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