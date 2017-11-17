<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html lang="en">
<head>
<title>日志管理</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="admin/css/bootstrap.min.css" />
<link rel="stylesheet" href="admin/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="admin/css/uniform.css" />
<link rel="stylesheet" href="admin/css/select2.css" />
<link rel="stylesheet" href="admin/css/maruti-style.css" />
<link rel="stylesheet" href="admin/css/maruti-media.css" class="skin-color" />
</head>
<body>

<!-- 引入后台头模板-->
<jsp:include page="/static/admin_header.jsp"></jsp:include>

<!--close-top-Header-menu-->

<div id="content">
  <div id="content-header">
    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">Tables</a> </div>
    <h1>操作日志</h1>
  </div>
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="widget-box">
          <div class="widget-title">
             <span class="icon"><i class="icon-th"></i></span> 
            <h5>Data table</h5>
          </div>
          <div class="widget-content nopadding">
            <table class="table table-bordered data-table">
              <thead>
                <tr>
                  <th>角色</th>               
                  <th>用户名</th>
                  <th>Ip</th>
				  <th>操作时间</th>
				  <th>方法逻辑名</th>
				  <th>方法名</th>
				  <th>参数</th>
                  <th>上一页</th>
                  <th>下一页</th>
                  <th>停留时间</th>


                </tr>
              </thead>
              <tbody>
                <c:forEach items="${logList}" var="v">
                <tr class="gradeX">
                    <td>${v.userId}</td>
                    <td>${v.userId}</td>
                    <td>${v.ip}</td>
                    <td>${v.logTime}</td>
                    <td>${v.methodLogicName}</td>
                    <td>${v.method}</td>
                    <td>${v.param}</td>
                    <td>${v.sourcePage}</td>
                    <td>${v.targetPage}</td>
                    <td>${v.stayTime}</td>
                </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
 <!-- 引入后台尾部模板 -->
 <jsp:include page="/static/admin_footer.jsp"></jsp:include>

<script src="admin/js/jquery.min.js"></script>
<script src="admin/js/jquery.ui.custom.js"></script>
<script src="admin/js/bootstrap.min.js"></script>
<script src="admin/js/jquery.uniform.js"></script>
<script src="admin/js/select2.min.js"></script>
<script src="admin/js/jquery.dataTables.min.js"></script>
<script src="admin/js/maruti.js"></script>
<script src="admin/js/maruti.tables.js"></script>
</body>
</html>
