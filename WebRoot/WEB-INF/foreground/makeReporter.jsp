<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>制作简报首页 - socialmind大平台</title>
    <base href="<%=basePath%>foreground/"/>
    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
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

<div class="container-fluid"  style="margin-top: 4%;">
    <div class="row-fluid">
        <div class="span3" id="sidebar">
            <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                <li>
                    <a href="index.html"><i class="icon-chevron-right"></i> 制作简报</a>
                </li>
                <li>

            </ul>
        </div>
        <!--/span-->
        <div class="span9" id="content">
            <div class="row-fluid">
                <div class="span12">
                    <!-- block -->
                    <div class="block">
                        <div class="navbar navbar-inner block-header">
                            <div class="muted pull-left">制作简报</div>
                        </div>
                        <div class="block-content collapse in">
                            <div class="span12">
                                <h4>制作简报</h4>
                                <img src="images/makeSummarize.png">
                                <p>
                                    <button class="btn btn-warning btn-large" style="margin-left:40%; margin-top: 20px; padding:10px;" onclick="javascript:window.location.href='<%=basePath%>reporterList'">新建简报 &nbsp>></button>
                                </p>

                            </div>
                        </div>
                    </div>
                    <!-- /block -->
                </div>
                <!-- block -->

                <!-- /block -->
            </div>
        </div>

    </div>
</div>
<hr>

<!-- 引入尾部模板 -->
<jsp:include page="/static/fore_footer.jsp"></jsp:include>

<!--/.fluid-container-->
<script src="vendors/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="assets/scripts.js"></script>
<script>
    $(function() {

    });
</script>


</body>
</html>
