<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	
<head>
	<base href="<%=basePath%>admin/"/>
	<title>Maruti Admin</title><meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="css/bootstrap.min.css" />
	<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
	<link rel="stylesheet" href="css/uniform.css" />
	<link rel="stylesheet" href="css/select2.css" />
	<link rel="stylesheet" href="css/maruti-style.css" />
	<link rel="stylesheet" href="css/maruti-media.css" class="skin-color" />
	<script>src="js/etljs/jquery-3.2.1.js"</script>
	<script src="js/etljs/jquery-3.2.1.min.js"></script>
	</head>
	<body>
	<%--<!-- 引入后台头模板-->--%>
	<jsp:include page="/static/admin_header.jsp"></jsp:include>

		<div id="content">
			<div id="content-header">
				<div id="breadcrumb">
				<a href="index.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
				<a href="#">Form elements</a>
				<a href="#" class="current">Validation</a>
				</div>
                <h1>数据清洗日志管理</h1>
			</div>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-info-sign"></i>									
								</span>
								<h5>数据清洗日志</h5>
							</div>
							<div class="widget-content nopadding" >
								<form class="form-horizontal" method="post" action="#" name="basic_validate" id="basic_validate" novalidate="novalidate">
									<div style="margin-left:3%;">
                                        <input type="text" name="required" id="required" placeholder="请输入用户名...">
										<input type="submit" value="搜索" class="btn btn-success">                                  
                                    </div>
									<div style="margin-left:3%; margin-top:1%">
                                        操作时间：                                   
                                        从<input type="text" id="fromDate" class="form-control dpform" placeholder="2017-10-10" style="width:120px;"/>&nbsp&nbsp&nbsp到&nbsp&nbsp&nbsp<input type="text" id="toDate"class="form-control dpform"  placeholder="2017-10-10" style="width:120px;"/>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <input type="submit" value="今天" class="btn btn-success"><input type="submit" value="近七天" class="btn btn-success"><input type="submit" value="近一个月" class="btn btn-success"><input type="submit" value="近三个月" class="btn btn-success">               										
                                    </div>
                                    <div style="margin-left:3%; margin-top:1%">                                                                               
                                          操作类型：<input id="wlmsinput1" name="maktMaktx" list="wlmslist" placeholder="修改"/>
										<datalist id="wlmslist">
											<c:forEach var="wl" items="${wuliaocp}">
												<option value="${wl.maktx }">修改</option>
												<option value="${wl.maktx }">删除</option>
												<option value="${wl.maktx }">添加</option>
											</c:forEach>
										</datalist> 
										  操作表：<input id="wlmsinput" name="maktMaktx" list="wlmslist2" placeholder="全部表"/>
										<datalist id="wlmslist2">
											<c:forEach var="wl" items="${wuliaocp}">
												<option value="${wl.maktx }">bbs_china_author</option>
												<option value="${wl.maktx }">bbs_china_post</option>
												<option value="${wl.maktx }">bbs_china_comment</option>
												<option value="${wl.maktx }">bbs_mop_author</option>
												<option value="${wl.maktx }">bbs_mop_post</option>
												<option value="${wl.maktx }">bbs_mop_comment</option>
											</c:forEach>
										</datalist>                                 
                                    </div>
                                    
                                    <div class="form-actions">
									<div class="widget-box">
								  <div class="widget-title"> <span class="icon"> <i class="icon-th"></i> </span>
									<h5>Static table</h5>
								  </div>
								  <div class="widget-content nopadding">
									<table class="table table-bordered table-striped">
									  <thead>
										<tr>
											<th>日志ID</th>
										    <th>管理员ID</th>
										    <th>表名</th>
										    <th>字段名</th>
										    <th>填入旧值</th>
										    <th>填入新值</th>
										    <th>清洗时间</th>
											<th>清洗策略ID</th>
											<th>清洗结果</th>
											<th>响应时间</th>
											<th>管理员IP</th>
											<th>错误代码</th>
											<th>错误信息</th>
										</tr>
									  </thead>
									  <tbody>
											<c:forEach  items="${cleanLogList}" var="v">
												<tr class="odd gradeX">
													<td>${v.cleanLogId}</td>
													<td>${v.userId}</td>
													<td>${v.tableName}</td>
													<td>${v.culumnName}</td>
													<td>${v.userDefineOldValue}</td>
													<td>${v.userDefineNewValue}</td>
													<td>${v.cleanTime}</td>
													<td>${v.cleanStategyId}</td>
													<td>${v.resultState}</td>
													<td>${v.responseTime}</td>
													<td>${v.IP}</td>
													<td>${v.errorCode}</td>
													<td>${v.errorMessage}</td>
												</tr>
											</c:forEach>
									  </tbody>
									</table>
								  </div>
								</div>
                                        <input type="submit" value="前一页" class="btn btn-success"><input type="submit" value="1" class="btn btn-success"><input type="submit" value="下一页" class="btn btn-success">
                                    </div>
                                </form>
							</div>
						</div>			
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-info-sign"></i>									
								</span>
								<h5>数据清洗前后对照</h5>
							</div>							
								管理员：Manager1 操作类型：修改 操作表：博客中国作者表(bbs_China_author) 操作时间：2017-10-23 2:43
									<table class="table table-bordered table-striped">
									  <thead>
										<tr>
										  <th>字段</th>
										  <th>名称</th>
										  <th>旧值</th>
										  <th>新值</th>
										</tr>
									  </thead>
									  <tbody>
										<tr class="odd gradeX">
										  <td>age</td>
										  <td>年龄</td>
										  <td>null</td>
										  <td class="center"> 20</td>
										</tr>
										<tr class="odd gradeX">
										  <td>birthday</td>
										  <td>生日</td>
										  <td>null</td>
										  <td class="center"> 1995-10-14</td>
										</tr>
									  </tbody>
									  </table>
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
            <script src="js/jquery.validate.js"></script>
            <script src="js/maruti.js"></script>
            <script src="js/maruti.form_validation.js"></script>
	</body>

</html>
