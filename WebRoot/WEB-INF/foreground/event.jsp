<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>

<jsp:include page="/static/fore_headInfo.jsp"/>
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
        <hr>
        <!-- 引入尾部模板 -->
        <jsp:include page="/static/fore_footer.jsp"></jsp:include>
    </div>
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
