<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>admin/"/>
<title>舆情对象管理 - Socialmind大平台</title>
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
	<div id="breadcrumb">
		<a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
		<a href="#" class="tip-bottom">Form elements</a>
		<a href="#" class="current">Common elements</a>
	</div>
	<h1>舆情对象管理（对舆情对象的增、删、展示）</h1>
	<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-align-justify"></i>									
								</span>
								<h5>新增舆情对象</h5>
							</div>
							<div class="widget-content nopadding">
								<form action="similarity" method="post" class="form-horizontal">
									<%--<div class="control-group">
										<label class="control-label">主题名称：</label>
										<div class="controls ">
											<div class="select2-container" id="s2id_autogen1">
											<div class="select2-drop select2-offscreen">   <div class="select2-search">       <input type="text" autocomplete="off" class="select2-input" tabindex="0">   </div>   <ul class="select2-results">   </ul></div></div><select style="display: none;">
												<option>请选择：</option>
												<option>科技</option>
												<option>社会</option>
												<option>民生</option>
												<option>军事</option>
												<option>...</option>
												<option>Seventh option</option>
												<option>Eighth option</option>
											</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">网站名称：</label>
										<div class="controls">
											<select>
												<option>全部</option>
												<option>豆瓣</option>
												<option>微博</option>
											</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">舆情对象类型：</label>
										<div class="controls">
											<select>
												<option>事件</option>
												<option>实体</option>
											</select>
										</div>
									</div>--%>
                                    <div class="control-group">
										<label class="control-label">舆情对象名称：</label>
										<div class="controls"><input type="text" name="eventKeywords" class="span20" placeholder="Last name" /></div>
									</div>
									
									<div class="control-group">
										<label class="control-label">舆情对象描述：</label>
										<div class="controls">
											<textarea name="eventName" class="span20">为更好的抽取舆情对象相关的文章....</textarea>
										</div>
									</div>
									<div class="form-actions">
										<button type="submit" class="btn btn-success">创建舆情对象，一键舆情对象分析</button>
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
									<i class="icon-align-justify"></i>									
								</span>
								<h5>舆情对象自动生成</h5>
							</div>
							<div class="widget-content nopadding">
								<form action="#" method="get" class="form-horizontal">
									<div class="control-group">
										<label class="control-label">主题名称：</label>
										<div class="controls ">
											<select style="display: none;">
												<option>请选择：</option>
												<option>科技</option>
												<option>社会</option>
												<option>民生</option>
												<option>军事</option>
												<option>...</option>
											</select>
										</div>
										
										<label class="control-label">网站名称：</label>
										<div class="controls ">
											<select style="display: none;">
												<option>请选择：</option>
												<option>全部</option>
												<option>豆瓣</option>
												<option>微博</option>
											</select>
										</div>
										
										<label class="control-label">舆情对象类型：</label>
										<div class="controls ">
											<select style="display: none;">
												<option>请选择：</option>
												<option>全部</option>
												<option>豆瓣</option>
												<option>微博</option>
											</select>
										</div>
									</div>											
									<div class="form-actions">
										<button type="submit" class="btn btn-success">舆情对象自动生成，一键舆情对象分析</button>
									</div>                                    
								</form>
							</div>
						</div>						
					</div>
				</div>
	</div>

  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="widget-box">
          <div class="widget-title">
             <span class="icon"><i class="icon-th"></i></span> 
            <h5>舆情对象展示</h5>
          </div>
          <div class="widget-content nopadding">
            <table class="table table-bordered data-table">
              <thead>
                <tr>
                  <th>序号</th>
                  <th>舆情对象类型</th>
                  <th>对象名称</th>
                  <th>对象生成时间</th>
                  <th>发生地</th>
                  <th>开始时间</th>
                  <th>结束时间</th>
                  <th>摘 要</th>
                  <th>总 结</th>
                </tr>
              </thead>
              <tbody>
                  <c:forEach items="${dynamicObjects}" var="d" varStatus="sts">
                      <tr class="gradeX">
                          <td>${sts.index + 1}</td>
                          <td>${d.object.objectType}</td>
                          <td>${d.object.name}</td>
                          <td>${d.object.addTime}</td>
                          <td class="center">${d.object.place}</td>
                          <td class="center">${d.event.eventBeginTime}</td>
                          <td class="center">${d.event.eventBeginTime}</td>
                          <td class="center">${d.event.introduction}</td>
                          <td class="center">${d.event.eventSummary}</td>
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
