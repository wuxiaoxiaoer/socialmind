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
  <div id="content-header" >
    <div id="breadcrumb"> <a href="index.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
	<h1>增加爬虫</h1>
  </div>

 		<div class="row-fluid">
			<div class="span8" style="padding-left: 200px">
				<div class="widget-box">
					<div class="widget-title">
						<span class="icon">
							<i class="icon-info-sign"></i>
						</span>
						<h5>增加爬虫</h5>
					</div>
					<div class="widget-content nopadding">
					<form class="form-horizontal" method="post" action="addSpider" name="basic_validate" id="basic_validate" novalidate="novalidate">
						<div class="control-group">
							<label class="control-label">爬虫名</label>
							<div class="controls">
								<input type="text" name="spiderName" id="spiderName">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">网站名</label>
							<div class="controls">
								<select id="websiteName" name="websiteId"  placeholder="请选择网站">
									<c:forEach items="${websiteList}" var="v">
										<option value="${v.websiteId}">${v.websiteName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">爬虫文件</label>
							<div class="controls">
								<input type="file" name="fileName" id="fileName" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">爬虫配置</label>
							<div class="controls">
								<select id="spiderConfigId" name="spiderConfigId"  placeholder="请选择配置">
									<c:forEach items="${configList}" var="v">
										<option value="${v.spiderConfigId}">${v.configName}</option>
									</c:forEach>
								</select>
							</div>
						</div>

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
