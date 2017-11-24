<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sicdlib.entity.UserEntity" %>
<%@ page import="com.sicdlib.entity.AdminEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>评论管理 - socialmind后台</title>
    <base href="<%=basePath%>admin/"/>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
    <link rel="stylesheet" href="css/uniform.css" />
    <link rel="stylesheet" href="css/select2.css" />
    <link rel="stylesheet" href="css/maruti-style.css" />
    <link rel="stylesheet" href="css/maruti-media.css" class="skin-color" />
    </head>
<body>

<!-- 引入后台头模板-->
<jsp:include page="/static/admin_header.jsp"></jsp:include>

<div id="content">
  <div id="content-header">
    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">Tables</a> </div>
    <h1>用户操作展示</h1>
  </div>
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="widget-box">
            <form action="<%=basePath%>multiDeleteComments">
              <div class="widget-title">
                 <span class="icon"><i class="icon-th"></i></span>
                <h5>操作内容</h5>
              </div>
              <div class="widget-content nopadding">
                <table class="table table-bordered data-table">
                  <thead>
                    <tr>
                      <th>用户类型</th>
                      <th>用户名</th>
                      <th>舆情对象名</th>
                      <th>操作内容</th>
                      <th>操作时间</th>
                    </tr>
                  </thead>
                  <tbody>
                      <c:forEach items="${userOperas}" var="o" varStatus="sts">
                        <tr class="gradeX">
                          <td>${o.userEntity.role.roleName}</td>
                          <td>${o.userEntity.userName}</td>
                          <td>${o.objectEntity.name}</td>
                            <c:forEach items="${operas}" var="op">
                                <c:if test="${op.dataDictionaryId == o.operaType}">
                                    <td class="center"><b>${op.note}</b></td>
                                </c:if>
                            </c:forEach>
                          <td class="center">${o.operaTime}</td>
                        </tr>
                      </c:forEach>
                  </tbody>
                </table>
              </div>
            </form>
        </div>

      </div>
    </div>
  </div>
</div>
<div class="row-fluid">
  <div id="footer" class="span12"> 2012 &copy; Marutii Admin. More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a> </div>
</div>
<script src="js/jquery.min.js"></script> 
<script src="js/jquery.ui.custom.js"></script> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/jquery.uniform.js"></script> 
<script src="js/select2.min.js"></script> 
<script src="js/jquery.dataTables.min.js"></script> 
<script src="js/maruti.js"></script> 
<script src="js/maruti.tables.js"></script>
</body>
</html>
