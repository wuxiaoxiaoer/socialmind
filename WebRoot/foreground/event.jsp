<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/10/30
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>全网事件分析</title>
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

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span3" id="sidebar">
            <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                <li>
                    <a href="index.html"><i class="icon-chevron-right"></i> 推荐事件</a>
                </li>
                <li class="active">
                    <a href="tables.html"><i class="icon-chevron-right"></i>全网事件分析</a>
                </li>

            </ul>
        </div>



        <!--/span-->
        <div class="span9" id="content">
            <!--title-->
            <h3 align="center">全网事件分析</h3>
            <div align="center"><input type="text" name="name" data-required="1" class="span5 m-wrap" placeholder="实体、事件关键词">
                <a href="eventInfo.html" style="padding-top:-4px;"><button class="btn btn-success" style="margin:-11px 0 0 20px;">搜索</button></a>
            </div>

            <!--推荐事件-->
            <div class="control-group">
                <label class="control-label">推荐事件<span class="required">*</span></label>

            </div>
            <!--map-->
            <div class="row-fluid">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left">政府：热点事件</div>
                        <div class="pull-right"><span class="badge badge-warning">刷新</span>
                        </div>
                    </div>
                    <div class="block-content collapse in">
                        <a href="eventInfo.html"><img src="./images/event1.png" height="200px" width="300px"></a>
                        <a href="eventInfo.html"><img src="./images/event1.png" height="200px" width="300px"></a>
                        <a href="eventInfo.html"><img src="./images/event1.png" height="200px" width="300px"></a>
                    </div>

                </div>
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left">事业单位：相关热点事件</div>
                        <div class="pull-right"><span class="badge badge-warning">刷新</span>
                        </div>
                    </div>
                    <div class="block-content collapse in" >
                        <a href="eventInfo.html"><img src="./images/event2.png" height="200px" width="300px"></a>
                        <a href="eventInfo.html"><img src="./images/event2.png" height="200px" width="300px"></a>
                        <a href="eventInfo.html"><img src="./images/event2.png" height="200px" width="300px"></a>
                    </div>

                </div>
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left">企业：所属领域热点事件</div>
                        <div class="pull-right"><span class="badge badge-warning">刷新</span>
                        </div>
                    </div>
                    <div class="block-content collapse in" >
                        <a href="eventInfo.html"><img src="./images/event3.png" height="200px" width="300px"></a>
                        <a href="eventInfo.html"><img src="./images/event3.png" height="200px" width="300px"></a>
                        <a href="eventInfo.html"><img src="./images/event3.png" height="200px" width="300px"></a>
                    </div>

                </div>
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left">个人：热点关注事件</div>
                        <div class="pull-right"><span class="badge badge-warning">刷新</span>
                        </div>
                    </div>
                    <div class="block-content collapse in">
                        <a href="eventInfo.html"><img src="./images/event1.png" height="200px" width="300px"></a>
                        <a href="eventInfo.html"><img src="./images/event2.png" height="200px" width="300px"></a>
                        <a href="eventInfo.html"><img src="./images/event3.png" height="200px" width="300px"></a>
                    </div>

                </div>

            </div>

            <div class="row-fluid">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left">全部事件</div>
                        <div class="pull-right"><span class="badge badge-warning">刷新</span>
                        </div>
                    </div>
                    <div class="block-content collapse in">
                        <img src="./images/event1.png" height="200px" width="300px">
                        <img src="./images/event1.png" height="200px" width="300px">
                        <img src="./images/event1.png" height="200px" width="300px">
                    </div>
                    <div class="block-content collapse in" >
                        <img src="./images/event1.png" height="200px" width="300px">
                        <img src="./images/event1.png" height="200px" width="300px">
                        <img src="./images/event1.png" height="200px" width="300px">
                    </div><div class="block-content collapse in">
                    <img src="./images/event1.png" height="200px" width="300px">
                    <img src="./images/event1.png" height="200px" width="300px">
                    <img src="./images/event1.png" height="200px" width="300px">
                </div>
                    <div class="block-content collapse in">
                        <img src="./images/event1.png" height="200px" width="300px">
                        <img src="./images/event1.png" height="200px" width="300px">
                        <img src="./images/event1.png" height="200px" width="300px">
                    </div>
                    <div class="block-content collapse in">
                        <img src="./images/event1.png" height="200px" width="300px">
                        <img src="./images/event1.png" height="200px" width="300px">
                        <img src="./images/event1.png" height="200px" width="300px">
                    </div>
                    <div class="block-content collapse in" id="map">
                        <img src="./images/event1.png" height="200px" width="300px">
                        <img src="./images/event1.png" height="200px" width="300px">
                        <img src="./images/event1.png" height="200px" width="300px">
                    </div>

                </div>
            </div>
            </div>
        </div>
        <hr>
        <!-- 引入尾部模板 -->
        <jsp:include page="/static/fore_footer.jsp"></jsp:include>
    </div>

    <!--/.fluid-container-->

    <script src="foreground/vendors/jquery-1.9.1.js"></script>
    <script src="foreground/bootstrap/js/bootstrap.min.js"></script>
    <script src="foreground/vendors/datatables/js/jquery.dataTables.min.js"></script>


    <script src="foreground/assets/scripts.js"></script>
    <script src="foreground/assets/DT_bootstrap.js"></script>
    <script>
        $(function() {

        });
    </script>
</body>

</html>
