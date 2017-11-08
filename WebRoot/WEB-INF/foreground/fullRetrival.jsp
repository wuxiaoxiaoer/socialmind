<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//UserEntity user = (UserEntity) session.getAttribute("user");
%>

<html>
    <head>
        <title>全文检索 - socialmind大平台</title>
        <!-- Bootstrap -->
        <link href="../../foreground/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="../../foreground/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
        <link href="../../foreground/vendors/easypiechart/jquery.easy-pie-chart.css" rel="stylesheet" media="screen">
        <link href="../../foreground/assets/styles.css" rel="stylesheet" media="screen">
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <script src="../../foreground/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
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
                                        <a tabindex="-1" href="../../foreground/person_info.html">个人中心</a>
                                    </li>
									<li>
                                        <a tabindex="-1" href="../../foreground/notification.html">通知  <font color="blue">1</font></a>
                                    </li>
                                    <li>
                                        <a tabindex="-1" href="../../foreground/login.html">Logout</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                        <ul class="nav">
							<li class="">
                                <a href="../../foreground/index_gov.html">主页</a>
                            </li>
                            <li class="">
                                <a href="../../foreground/heatIndex.html">热度指标</a>
                            </li>
                            <li class="">
                                <a href="../../foreground/emotionMap.html">情绪地图</a>
                            </li>
                            <li class="">
                                <a href="../../foreground/info_dectection.html">信息监测</a>
                            </li>
							<li class="">
                                <a href="fullRetrival.html">全文检索</a>
                            </li>
							<li class="">
                                <a href="../../foreground/event.html">全网事件分析</a>
                            </li>
							<li class="">
                                <a href="../../foreground/makeReporter_1.html">简报制作</a>
                            </li>
                        </ul>
                    </div>
                    <!--/.nav-collapse -->
                </div>
            </div>
        </div>

                <div class="span3" id="sidebar">
                    <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                        <li>
                            <a href="index.html"><i class="icon-chevron-right"></i> 全文检索</a>
                        </li>
                        <li>
                            <a href="calendar.html"><i class="icon-chevron-right"></i> 热门事件</a>
                        </li>
                        <li>
                            <a href="stats.html"><i class="icon-chevron-right"></i> 本单位/企业/个人/政府相关事件</a>
                        </li>

                    </ul>
                </div>
        <div class="container-fluid">
            <div class="row-fluid">
                
                <!--/span-->
                <div class="span9" id="content">
                    <div class="row-fluid">
                        
                    <div class="row-fluid">
                        <!-- block -->
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">全文检索</div>
                            </div>
                            <div class="block-content collapse in">
                            <div align="center"><input type="text" name="name" data-required="1" class="span5 m-wrap" placeholder="实体、事件关键词全文检索"> 
								<a href="../../foreground/fullRetrivalResults.html"><button class="btn btn-success" style="margin: -10px 0 0 10px;">搜索</button></a>
							</div>
							</div>
                        </div>
                        <!-- /block -->
                    </div>
                        <!-- block -->
                        <div class="control-group" align="left">
  								<label class="control-label">热门事件（政府用户）</label>
  								<div class="controls">
					<div class="row-fluid">
                        <div class="span6">
                            <!-- block -->
                            <div class="block">
                                <div class="navbar navbar-inner block-header">
                                    <div class="muted pull-left">敏感事件</div>
                                    <div class="pull-right"><span class="badge badge-info">1,234</span>

                                    </div>
                                </div>
                                <div class="block-content collapse in">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>事件</th>
                                                <th>数量</th>
                                                <th>涉及最多网站</th>
                                                <th>发生时间</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>事件1</td>
                                                <td>10000</td>
                                                <td>微博</td>
                                                <td>2017-5-5</td>
                                            </tr><tr>
                                                <td>事件1</td>
                                                <td>10000</td>
                                                <td>微博</td>
                                                <td>2017-5-5</td>
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
                                    <div class="muted pull-left">重大热门事件（政府用户）</div>
                                    <div class="pull-right"><span class="badge badge-info">752</span>

                                    </div>
                                </div>
                                <div class="block-content collapse in">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>事件</th>
                                                <th>数量</th>
                                                <th>涉及最多网站</th>
                                                <th>发生时间</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>事件1</td>
                                                <td>10000</td>
                                                <td>微博</td>
                                                <td>2017-5-5</td>
                                            </tr><tr>
                                                <td>事件1</td>
                                                <td>10000</td>
                                                <td>微博</td>
                                                <td>2017-5-5</td>
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
                                    <div class="muted pull-left">本单位相关事件(事业单位)</div>
                                    <div class="pull-right"><span class="badge badge-info">1,234</span>

                                    </div>
                                </div>
                                <div class="block-content collapse in">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>事件</th>
                                                <th>数量</th>
                                                <th>涉及最多网站</th>
                                                <th>发生时间</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>事件1</td>
                                                <td>10000</td>
                                                <td>微博</td>
                                                <td>2017-5-5</td>
                                            </tr><tr>
                                                <td>事件1</td>
                                                <td>10000</td>
                                                <td>微博</td>
                                                <td>2017-5-5</td>
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
                                    <div class="muted pull-left">相关单位事件（事业单位用户）</div>
                                    <div class="pull-right"><span class="badge badge-info">752</span>

                                    </div>
                                </div>
                                <div class="block-content collapse in">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>事件</th>
                                                <th>数量</th>
                                                <th>涉及最多网站</th>
                                                <th>发生时间</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>事件1</td>
                                                <td>10000</td>
                                                <td>微博</td>
                                                <td>2017-5-5</td>
                                            </tr><tr>
                                                <td>事件1</td>
                                                <td>10000</td>
                                                <td>微博</td>
                                                <td>2017-5-5</td>
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
                                    <div class="muted pull-left">本企业相关事件(企业单位)</div>
                                    <div class="pull-right"><span class="badge badge-info">1,234</span>

                                    </div>
                                </div>
                                <div class="block-content collapse in">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>事件</th>
                                                <th>数量</th>
                                                <th>涉及最多网站</th>
                                                <th>发生时间</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>事件1</td>
                                                <td>10000</td>
                                                <td>微博</td>
                                                <td>2017-5-5</td>
                                            </tr><tr>
                                                <td>事件1</td>
                                                <td>10000</td>
                                                <td>微博</td>
                                                <td>2017-5-5</td>
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
                                    <div class="muted pull-left">相关企业事件（企业单位用户）</div>
                                    <div class="pull-right"><span class="badge badge-info">752</span>

                                    </div>
                                </div>
                                <div class="block-content collapse in">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>事件</th>
                                                <th>数量</th>
                                                <th>涉及最多网站</th>
                                                <th>发生时间</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>事件1</td>
                                                <td>10000</td>
                                                <td>微博</td>
                                                <td>2017-5-5</td>
                                            </tr><tr>
                                                <td>事件1</td>
                                                <td>10000</td>
                                                <td>微博</td>
                                                <td>2017-5-5</td>
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
                                    <div class="muted pull-left">本企业相关事件(企业单位)</div>
                                    <div class="pull-right"><span class="badge badge-info">1,234</span>

                                    </div>
                                </div>
                                <div class="block-content collapse in">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>事件</th>
                                                <th>数量</th>
                                                <th>涉及最多网站</th>
                                                <th>发生时间</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>事件1</td>
                                                <td>10000</td>
                                                <td>微博</td>
                                                <td>2017-5-5</td>
                                            </tr><tr>
                                                <td>事件1</td>
                                                <td>10000</td>
                                                <td>微博</td>
                                                <td>2017-5-5</td>
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
                                    <div class="muted pull-left">相关企业事件（企业单位用户）</div>
                                    <div class="pull-right"><span class="badge badge-info">752</span>

                                    </div>
                                </div>
                                <div class="block-content collapse in">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>事件</th>
                                                <th>数量</th>
                                                <th>涉及最多网站</th>
                                                <th>发生时间</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>事件1</td>
                                                <td>10000</td>
                                                <td>微博</td>
                                                <td>2017-5-5</td>
                                            </tr><tr>
                                                <td>事件1</td>
                                                <td>10000</td>
                                                <td>微博</td>
                                                <td>2017-5-5</td>
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
                                    <div class="muted pull-left">兴趣偏好（个人用户）</div>
                                    <div class="pull-right"><span class="badge badge-info">752</span>

                                    </div>
                                </div>
                                <div class="block-content collapse in">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>事件</th>
                                                <th>数量</th>
                                                <th>涉及最多网站</th>
                                                <th>发生时间</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>事件1</td>
                                                <td>10000</td>
                                                <td>微博</td>
                                                <td>2017-5-5</td>
                                            </tr><tr>
                                                <td>事件1</td>
                                                <td>10000</td>
                                                <td>微博</td>
                                                <td>2017-5-5</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <!-- /block -->
                        </div>
						
								
  					</div>
					
  							</div>
                        <!-- /block -->
                    </div>
                </div>
            </div>
            <hr>
            <footer>
                <p></p>
            </footer>
        </div>
        <!--/.fluid-container-->
        <script src="../../foreground/vendors/jquery-1.9.1.min.js"></script>
        <script src="../../foreground/bootstrap/js/bootstrap.min.js"></script>
        <script src="../../foreground/vendors/easypiechart/jquery.easy-pie-chart.js"></script>
        <script src="../../foreground/assets/scripts.js"></script>
        <script>
        $(function() {
            // Easy pie charts
            $('.chart').easyPieChart({animate: 1000});
        });
        </script>
    

</body></html>