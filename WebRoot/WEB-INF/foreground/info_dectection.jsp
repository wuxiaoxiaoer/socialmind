<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    
    <head>
        <base href="<%=basePath%>"/>
        <title>信息监测 - socialmind大平台</title>
        <!-- Bootstrap -->
        <link href="foreground/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="foreground/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
        <link href="foreground/assets/styles.css" rel="stylesheet" media="screen">
        <link href="foreground/assets/DT_bootstrap.css" rel="stylesheet" media="screen">
        <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="foreground/vendors/flot/excanvas.min.js"></script><![endif]-->
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <script src="foreground/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    </head>
    
    <body>

    <!-- 引入头模板 -->
    <jsp:include page="/static/fore_header.jsp"/>

        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span3" id="sidebar">
                    <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                        <li>
                            <a href="index.html"><i class="icon-chevron-right"></i> 搜索</a>
                        </li>
                        <li>
                            <a href=""><i class="icon-chevron-right"></i> 政府：敏感事件</a>
                        </li>

                    </ul>
                </div>
				

				
                <!--/span-->
                <div class="span9" id="content">
                   	<!--title-->
					<h3 align="center">信息监测</h3>
                    <form action="infodetection/search">
                        <div align="center">
                            <input type="text" name="name" data-required="1" class="span5 m-wrap" placeholder="信息检测关键词">
                            <button class="btn btn-success" style="margin:-11px 0 0 20px;" type="submit">搜索</button>
                        </div>
                    </form>
					<!--推荐事件-->
				
					<!--map-->
					<div class="row-fluid">
                        <!-- block -->
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">政府：敏感事件</div>
                                <div class="pull-right"><span class="badge badge-warning">刷新</span>
                                </div>
                            </div>
							<div class="block-content collapse in">
								<a href="info_dectection_info_text.jsp"><img src="foreground/images/event1.png" height="200px" width="300px"></a>
                                <a href="info_dectection_info_text.jsp"><img src="foreground/images/event1.png" height="200px" width="300px"></a>
                                <a href="info_dectection_info_text.jsp"><img src="foreground/images/event1.png" height="200px" width="300px"></a>
							</div>
                            
                        </div>
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">事业单位：内部事件</div>
                                <div class="pull-right"><span class="badge badge-warning">刷新</span>
                                </div>
                            </div>
							<div class="block-content collapse in" id="map">
								<a href="info_dectection_info_text.jsp"><img src="foreground/images/event2.png" height="200px" width="300px"></a>
                                <a href="info_dectection_info_text.jsp"><img src="foreground/images/event2.png" height="200px" width="300px"></a>
                                <a href="info_dectection_info_text.jsp"><img src="foreground/images/event2.png" height="200px" width="300px"></a>
							</div>
                            
                        </div>
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">企业：负面舆情</div>
                                <div class="pull-right"><span class="badge badge-warning">刷新</span>
                                </div>
                            </div>
							<div class="block-content collapse in" id="map">
								<a href="info_dectection_info_text.jsp"><img src="foreground/images/event3.png" height="200px" width="300px"></a>
                                <a href="info_dectection_info_text.jsp"><img src="foreground/images/event3.png" height="200px" width="300px"></a>
                                <a href="info_dectection_info_text.jsp"><img src="foreground/images/event3.png" height="200px" width="300px"></a>
							</div
                            
                        </div>
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">个人：热门关注</div>
                                <div class="pull-right"><span class="badge badge-warning">刷新</span>
                                </div>
                            </div>
							<div class="block-content collapse in" id="map">
								<a href="info_dectection_info_text.jsp"><img src="foreground/images/event1.png" height="200px" width="300px"></a>
                                <a href="info_dectection_info_text.jsp"><img src="foreground/images/event2.png" height="200px" width="300px"></a>
                                <a href="info_dectection_info_text.jsp"><img src="foreground/images/event3.png" height="200px" width="300px"></a>
							</div>
                        </div>
                </div>

                </div>
            </div>

            <!-- 引入尾部模板 -->
            <jsp:include page="/static/fore_footer.jsp"></jsp:include>

        </div>
        <!--/.fluid-container-->

        <script src="foreground/vendors/jquery-1.9.1.js"></script>
        <script src="foreground/bootstrap/js/bootstrap.min.js"></script>
        <script src="foreground/vendors/datatables/js/jquery.dataTables.min.js"></script>


        <script src="foreground/assets/scripts.js"></script>
        <script src="foreground/assets/DT_bootstrap.js"></script>

    </body>


</html>