<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html lang="en">

<head>
<title>SocialMind底层配置管理平台</title><meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="admin/css/bootstrap.min.css" />
<link rel="stylesheet" href="admin/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="admin/css/fullcalendar.css" />
<link rel="stylesheet" href="admin/css/maruti-style.css" />
<link rel="stylesheet" href="admin/css/maruti-media.css" class="skin-color" />
</head>
<body>

<!-- 引入后台头模板-->
<jsp:include page="/static/admin_header.jsp"></jsp:include>
<!--close-top-Header-menu-->

<div id="content">
  <div id="content-header">
    <div id="breadcrumb"> <a href="index.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
	<h1>增加配置</h1>
  </div>

 <div class="row-fluid">
     <div class="span8" style="padding-left: 25%">
         <div class="widget-box">
             <div class="widget-title">
                 <span class="icon">
                     <i class="icon-info-sign"></i>
                 </span>
                 <h5>增加配置</h5>
             </div>
             <div class="widget-content nopadding">
                 <form class="form-horizontal" method="post" action="addConfig" name="basic_validate" id="basic_validate" novalidate="novalidate">

                     <input type="text"  style="display: none;" name="sConfigId" id="sConfigId">
                     <div class="control-group">
                         <label class="control-label">配置名称</label>
                         <div class="controls">
                             <input type="text" name="configName" id="configName">
                         </div>
                     </div>






                    <c:forEach items="${configItemList}" var="v">
                        <c:if test="${v.type eq 'int'}">
                            <div class="control-group">
                             <label class="control-label">${v.displayName}</label>
                             <div class="controls">
                                 <input type="text" value="" name="${v.configItemId}" >

                             </div>
                         </div>
                        </c:if>
                        <c:if test="${v.type eq 'boolean'}">
                        <div class="control-group">
                            <label class="control-label">${v.displayName}</label>
                            <div class="controls">
                            <select  name="${v.configItemId}" >
                                <option  value="true">是</option>
                                <option  value="false">否</option>
                            </select>
                            </div>
                        </div>
                        </c:if>

                    </c:forEach>





                     <div class="form-actions">
                         <button type="submit" class="btn btn-success">保存</button>
                     </div>
                 </form>
             </div>
         </div>
     </div>
 </div>

</div>

<!-- 引入后台尾部模板 -->
<jsp:include page="/static/admin_footer.jsp"></jsp:include>

<script src="admin/js/excanvas.min.js"></script>
<script src="admin/js/jquery.min.js"></script>
<script src="admin/js/jquery.ui.custom.js"></script>
<script src="admin/js/bootstrap.min.js"></script>
<script src="admin/js/jquery.flot.min.js"></script>
<script src="admin/js/jquery.flot.resize.min.js"></script>
<script src="admin/js/jquery.peity.min.js"></script>
<script src="admin/js/fullcalendar.min.js"></script>
<script src="admin/js/maruti.js"></script>
<script src="admin/js/maruti.dashboard.js"></script>
<script src="admin/js/maruti.chat.js"></script>

</body>

</html>
