<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>领导配置</title>
    <base href="<%=basePath%>foreground/"/>
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

<div class="container-fluid" style="margin-top: 4%;">
    <div class="row-fluid">
        <div class="span3" id="sidebar">
            <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                <li>
                    <a href="#"><i class="icon-chevron-right"></i> 领导动态管理</a>
                </li>

            </ul>
        </div>
        <!--/span-->
        <div class="span9" id="content">

            <div class="row-fluid">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left">动态管理</div>
                    </div>
                    <div class="block-content collapse in">
                        <div class="span12">
                            <div class="table-toolbar">
                                <div class="btn-group">
                                    <a onclick="showAddInfo()"><button class="btn btn-success">新增 <i class="icon-plus icon-white"></i></button></a>
                                </div>
                                <div class="btn-group pull-right">
                                    <button data-toggle="dropdown" class="btn dropdown-toggle">Tools <span class="caret"></span></button>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Print</a></li>
                                        <li><a href="#">Save as PDF</a></li>
                                        <li><a href="#">Export to Excel</a></li>
                                    </ul>
                                </div>
                            </div>

                            <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example2">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>名称</th>
                                    <th>简介</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${objectUsers}" var="ou" varStatus="sts">
                                        <tr class="odd gradeX">
                                            <td>${sts.index}</td>
                                            <td>${ou.objectEntity.name}</td>
                                            <td>${ou.objectEntity.introduction}</td>
                                            <td class="center">
                                                <div class="pull-right"><a href="<%=basePath%>object/managePersons?flag=deleteObjUser&ouId=${ou.id}"><span class="badge badge-info" style="background-color: #f89406;">删除</span></a></div>
                                                <div class="pull-right"><span class="badge badge-info">显示</span></div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- /block -->
                <script>
                    function showAddInfo() {
                        $("#showLeader").css("display", "");
                    }
                </script>
                <div id="showLeader" class="row-fluid">
                    <!-- block -->
                    <div class="block">
                        <div class="navbar navbar-inner block-header">
                            <div class="muted pull-left">新增</div>
                        </div>
                        <div class="block-content collapse in">
                            <div class="span12">
                                <form id="formAdd" action="<%=basePath%>object/managePersons" class="form-horizontal">
                                    <!-- 增加领导 -->
                                    <input type="hidden" name="flag" value="addLeader"/>
                                    <fieldset>
                                        <legend>新增</legend>
                                        <div class="control-group">
                                            <label class="control-label">名称</label>
                                            <div class="controls">
                                                <input name="name" id="name" class="input-xlarge focused" type="text">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label">简介</label>
                                            <div class="controls">
                                                <textarea name="introduction" id="introduction" rows="8" style="width: 270px;"></textarea>
                                            </div>
                                        </div>
                                        <%--<div class="control-group">
                                            <label class="control-label" for="focusedInput">人物关键词</label>
                                            <div class="controls">
                                                <input name="keywords" id="keywords" class="input-xlarge focused" type="text" placeholder="以英文逗号(,)分割">
                                            </div>
                                        </div>--%>
                                        <div class="form-actions">
                                            <button type="button" onclick="formSubmit();" class="btn btn-primary">下一步>></button>
                                            <button type="reset" class="btn">取消</button>
                                        </div>

                                    </fieldset>
                                </form>

                            </div>
                        </div>
                    </div>
                    <script>
                        function formSubmit() {
//                            $("#formAdd").submit(function (){
//                                $("#showLeader").css("display", "");
//                            });
                            document.getElementById("formAdd").submit();
                            $("#showLeader").css("display", "");
                        }
                    </script>
                    <div id="objectList">
                        <div class="navbar navbar-inner block-header">
                            <div class="muted pull-left">推荐新增</div>
                        </div>
                        <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example2">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>名称</th>
                                <th>简介</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${searchObjects}" var="o" varStatus="sts">
                                <tr class="odd gradeX">
                                    <td>${sts.index}</td>
                                    <td>${o.name}</td>
                                    <td>${o.introduction}</td>
                                    <td class="center">
                                        <div class="pull-right"><a href="<%=basePath%>object/managePersons?flag=chooseObject&chooseId=${o.objectId}&oType=leader"><span class="badge badge-info" style="background-color: #f89406;">选定</span></a></div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                    </div>


                    <%--<div class="block">
                        <div class="navbar navbar-inner block-header">
                            <div class="muted pull-left">推荐关键词</div>
                        </div>
                        <div class="block-content collapse in">
                            <div class="span12">
                                <form class="form-horizontal">
                                    <fieldset>
                                        <legend>推荐关键词</legend>
                                        <div class="control-group">
                                            <label class="control-label" for="focusedInput">推荐关键词</label>
                                            <div class="controls">
                                                <input class="input-xlarge focused" id="focusedInput" type="text" value="关键词1, 关键词2...为了实体消歧">
                                            </div>
                                        </div>

                                    </fieldset>
                                    <div class="form-actions">
                                        <button type="submit" class="btn btn-primary">确认+</button>
                                        <button type="reset" class="btn">取消</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>--%>
                    <!-- /block -->
                </div>

            </div>
        </div>
    </div>
    <hr>
    <footer>
        <p>&copy; Vincent Gabriel 2013</p>
    </footer>
</div>
<!--/.fluid-container-->

<script src="vendors/jquery-1.9.1.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="vendors/datatables/js/jquery.dataTables.min.js"></script>


<script src="assets/scripts.js"></script>
<script src="assets/DT_bootstrap.js"></script>
<script>
    $(function() {

    });
</script>
</body>

</html>