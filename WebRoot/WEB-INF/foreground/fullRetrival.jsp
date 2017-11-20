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
        <div class="container-fluid" style="margin-top: 4%;">
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
                            <form action="<%=basePath%>retrivalResults">
                                <!-- 判别全文检索 -->
                                <input type="hidden" name="flag" value="all"/>
                                <div class="block-content collapse in">
                                <div align="center"><input type="text" name="name" data-required="1" class="span5 m-wrap" placeholder="实体、事件关键词全文检索">
                                    <button type="submit" class="btn btn-success" style="margin: -10px 0 0 10px;">搜索</button>
                                </div>
                                </div>
                            </form>
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