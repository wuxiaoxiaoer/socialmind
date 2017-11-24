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
                <h5>最新用户操作展示</h5>
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
                      <c:forEach items="${userOperasPage.result}" var="o" varStatus="sts">
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

          <div class="widget-box">
              <form action="<%=basePath%>commentsManager">
                  <div class="widget-title">
                      <span class="icon"><i class="icon-th"></i></span>
                      <h5>舆情对象的用户操作展示</h5>
                  </div>
                  <div class="widget-content nopadding">
                      <table class="table table-bordered data-table">
                          <thead>
                          <tr>
                              <th>舆情对象名</th>
                              <th>评论量</th>
                              <th>点赞量</th>
                              <th>收藏量</th>
                              <th>判真量</th>
                              <th>判假量</th>
                          </tr>
                          </thead>
                          <tbody>
                          <c:forEach items="${objectPage.result}" var="o" varStatus="sts">
                              <tr class="gradeX">
                                  <td>${o.name}</td>
                                  <!-- 评论量 -->
                                  <td><a href="<%=basePath%>systemCommentsShow?objId=${o.objectId}" target="_blank">${fn:length(o.comments)}</a></td>
                                  <c:set var="support" scope="page" value="0"/>
                                  <c:set var="collect" scope="page" value="0"/>
                                  <c:set var="isTrue" scope="page" value="0"/>
                                  <c:set var="isFalse" scope="page" value="0"/>
                                  <c:forEach items="${o.userOperas}" var="ou">
                                      <!-- 字典：5 点赞 -->
                                      <c:if test="${ou.operaType == '5'}">
                                          <c:set var="support" scope="page" value="${support + 1}"/>
                                      </c:if>
                                      <!-- 字典：8 收藏 -->
                                      <c:if test="${ou.operaType == '5'}">
                                          <c:set var="collect" scope="page" value="${collect + 1}"/>
                                      </c:if>
                                      <!-- 字典：6 判真 -->
                                      <c:if test="${ou.operaType == '6'}">
                                          <c:set var="isTrue" scope="page" value="${isTrue + 1}"/>
                                      </c:if>
                                      <!-- 字典：7 判假 -->
                                      <c:if test="${ou.operaType == '7'}">
                                          <c:set var="isFalse" scope="page" value="${isFalse + 1}"/>
                                      </c:if>
                                  </c:forEach>
                                  <td><a href="<%=basePath%>userOperaDetail?objId=${o.objectId}&op=5" target="_blank">${support}</a></td>
                                  <td><a href="<%=basePath%>userOperaDetail?objId=${o.objectId}&op=8" target="_blank">${collect}</a></td>
                                  <td><a href="<%=basePath%>userOperaDetail?objId=${o.objectId}&op=6" target="_blank">${isTrue}</a></td>
                                  <td><a href="<%=basePath%>userOperaDetail?objId=${o.objectId}&op=7" target="_blank">${isFalse}</a></td>
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
