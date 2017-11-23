<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sicdlib.entity.UserEntity" %>
<%@ page import="com.sicdlib.entity.AdminEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <h1>用户敏感评论管理（审、删、展示）</h1>
  </div>
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="widget-box">
            <form action="<%=basePath%>multiDeleteComments">
              <div class="widget-title">
                 <span class="icon"><i class="icon-th"></i></span>
                <h5>最新敏感信息展示</h5>
                <div class="fr" style="margin: 0.5% 10% 0 0;">
                    <input type="submit" value="批量删除" class="btn btn-primary btn-mini"/>
                    <a href="<%=basePath%>sensitive" class="btn btn-primary btn-mini">敏感评论</a>
                </div>
              </div>
              <div class="widget-content nopadding">
                <table class="table table-bordered data-table">
                  <thead>
                    <tr>
                      <th><input type="checkbox" />&nbsp&nbsp是否选中</th>
                      <th>用户类型</th>
                      <th>用户名</th>
                      <th>舆情对象名</th>
                      <th>评论内容</th>
                      <th>评论时间</th>
                      <th>操作</th>
                    </tr>
                  </thead>
                  <tbody>
                      <c:forEach items="${sensitiveComments}" var="s" varStatus="sts">
                          <c:if test="${s.comment.fathercommentId == null}">
                            <tr class="gradeX">
                              <td><input name="commentId" value="${s.comment.commentId}" type="checkbox"/></td>
                              <td>${s.comment.userEntity.role.roleName}</td>
                              <td>${s.comment.userEntity.userName}</td>
                              <td>${s.comment.objectEntity.name}</td>
                              <td class="center"><b>${s.comment.commentContent}</b>&nbsp;&nbsp;&nbsp;<span style="float: right">包含敏感类型为<code>${s.sensitiveType}</code>的<span style="color: red"><code>${s.sensitiveWord}</code></span>敏感词</span></td>
                              <td class="center">${s.comment.commentTime}</td>
                                <td class="taskOptions">
                                    <a href="<%=basePath%>commentsManager?flag=delete&commentId=${s.comment.commentId}" class="tip-top" data-original-title="Delete">
                                        <i class="icon-remove"></i>
                                    </a>
                                </td>
                            </tr>
                          </c:if>
                          <c:forEach items="${page.result}" var="c2" varStatus="sts2">
                          <c:if test="${s.comment.commentId == c2.fathercommentId}">
                              <tr>
                                  <td><input type="checkbox" /></td>
                                  <td>${c2.userEntity.role.roleName}</td>
                                  <td>${c2.userEntity.userName}</td>
                                  <td>${c2.objectEntity.name}</td>
                                  <td>回复: ${c2.commentContent}
                                      <c:forEach items="${sensitiveComments}" var="sctemp">
                                          <c:if test="${sctemp.comment.commentId == c2.commentId}">
                                            <span style="float: right">包含敏感类型为<code>${sctemp.sensitiveType}</code>的<span style="color: red"><code>${sctemp.sensitiveWord}</code></span>敏感词</span>
                                          </c:if>
                                      </c:forEach>
                                  </td>
                                  <td class="center">${c2.commentTime}</td>
                                  <td class="taskOptions">
                                      <a href="<%=basePath%>commentsManager?flag=delete&commentId=${c2.commentId}" class="tip-top" data-original-title="Delete">
                                          <i class="icon-remove"></i>
                                      </a>
                                  </td>
                              </tr>
                          </c:if>
                          </c:forEach>
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
                      <h5>用户评论管理</h5>
                      <div class="fr" style="margin: 0.5% 10% 0 0;">
                          <input type="hidden" name="flag" value="multidelete"/>
                          <input type="submit" value="批量删除" class="btn btn-primary btn-mini"></input>
                      </div>
                  </div>
                  <div class="widget-content nopadding">
                      <table class="table table-bordered data-table">
                          <thead>
                          <tr>
                              <th><input type="checkbox" />&nbsp&nbsp是否选中</th>
                              <th>用户类型</th>
                              <th>用户名</th>
                              <th>舆情对象名</th>
                              <th>评论内容</th>
                              <th>评论时间</th>
                              <th>操作</th>
                          </tr>
                          </thead>
                          <tbody>
                          <c:forEach items="${page.result}" var="c" varStatus="sts">
                              <c:if test="${c.fathercommentId == null}">
                                  <tr class="gradeX">
                                      <td><input name="commentId" value="${c.commentId}" type="checkbox"/></td>
                                      <td>${c.userEntity.role.roleName}</td>
                                      <td>${c.userEntity.userName}</td>
                                      <td>${c.objectEntity.name}</td>
                                      <td class="center"><b>${c.commentContent}</b></td>
                                      <td class="center">${c.commentTime}</td>
                                      <td class="taskOptions">
                                          <a href="<%=basePath%>commentsManager?flag=delete&commentId=${c.commentId}" class="tip-top" data-original-title="Delete">
                                              <i class="icon-remove"></i>
                                          </a>
                                      </td>
                                  </tr>
                              </c:if>
                              <c:forEach items="${page.result}" var="c2" varStatus="sts2">
                                  <c:if test="${c.commentId == c2.fathercommentId}">
                                      <tr>
                                          <td><input type="checkbox" /></td>
                                          <td>${c2.userEntity.role.roleName}</td>
                                          <td>${c2.userEntity.userName}</td>
                                          <td>${c2.objectEntity.name}</td>
                                          <td>回复: ${c2.commentContent}</td>
                                          <td class="center">${c2.commentTime}</td>
                                          <td class="taskOptions">
                                              <a href="<%=basePath%>commentsManager?flag=delete&commentId=${c2.commentId}" class="tip-top" data-original-title="Delete">
                                                  <i class="icon-remove"></i>
                                              </a>
                                          </td>
                                      </tr>
                                  </c:if>
                              </c:forEach>
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
