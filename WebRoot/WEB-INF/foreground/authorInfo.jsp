<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    
    <head>
		<base href="<%=basePath%>foreground/"/>
        <title>文章作者个人中心</title>
        <!-- Bootstrap -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
        <link href="assets/styles.css" rel="stylesheet" media="screen">
        <link href="vendors/jGrowl/jquery.jgrowl.css" rel="stylesheet" media="screen">
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <script src="js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    </head>
    
    <body>

	<!-- 引入头模板 -->
	<jsp:include page="/static/fore_header.jsp"/>

	<div class="container-fluid" style="margin-top: 4%;">
            <div class="row-fluid">
                <div class="span3" id="sidebar">
                    <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                        <li>
                            <a href="index.html"><i class="icon-chevron-right"></i> Dashboard</a>
                        </li>
                        <li>
                            <a href="calendar.html"><i class="icon-chevron-right"></i> Calendar</a>
                        </li>
                    </ul>
                </div>
                <!--/span-->
                <div class="span9" id="content">
                    <div class="row-fluid">
                        <div class="span12">
                            <!-- block -->
                            <div class="block">
                                <div class="navbar navbar-inner block-header">
                                    <div class="muted pull-left">个人中心</div>
                                </div>

                                <div class="block-content collapse in">
                                    <div class="span12">
										<h4>基本信息</h4>
										<table class="table table-bordered table-striped">
											<thead>
											  <tr>
												<th>属性</th>
												<th>值</th>
												<th>属性</th>
												<th>值</th>
												<th>属性</th>
												<th>值</th>
											  </tr>
											</thead>
											<tbody>
											  <tr>
												<td>
												  <span class="label">姓名</span>
												</td>
												<td>
												  <code style="font-size: 18px;">
													  <b>${authorEntity.name} </b>
													  <c:forEach items="${authorEntity.articles}" var="a" varStatus="sts">
														<c:if test="${sts.index == 0}">(${a.websiteEntity.websiteName})</c:if>
													  </c:forEach>
												  </code>
												</td>

												  <td>
													  <span class="label label-success">性别</span>
												  </td>
												  <td>
													  <code>${authorEntity.sex}</code>
												  </td>
												  <td>
													  <span class="label label-warning">生日</span>
												  </td>
												  <td>
													  <code>${authorEntity.birthday}</code>
												  </td>
											  </tr>
											  <tr>
												<td>
												  <span class="label">地址</span>
												</td>
												<td style="">
												  <code>${authorEntity.address}</code>
												</td>
												  <td>
													  <span class="label label-success">简介</span>
												  </td>
												  <td>
													  <code>${authorEntity.introduction}</code>
												  </td>
												  <td>
													  <span class="label label-warning">注册时间</span>
												  </td>
												  <td>
													  <code>${authorEntity.registrationTime}</code>
												  </td>
											  </tr>
											  <tr>
												  <td>
													  <span class="label">教育背景</span>
												  </td>
												  <td>
													  <code>${authorEntity.educationBackground}</code>
												  </td>
												  <td>
													  <span class="label label-success">职业</span>
												  </td>
												  <td>
													  <code>${authorEntity.career}</code>
												  </td>
												  <td>
													  <span class="label label-warning">在线时长</span>
												  </td>
												  <td>
													  <code>${authorEntity.onlineTime}</code>
												  </td>
											  </tr>

											  <tr>
												  <td>
													  <span class="label">是否认证</span>
												  </td>
												  <td>
													  <code>${authorEntity.isCertification}</code>
												  </td>
												  <td>
													  <span class="label label-success">是否大V</span>
												  </td>
												  <td>
													  <code>${authorEntity.isBigV}</code>
												  </td>
												  <td>
													  <span class="label label-warning">真实姓名</span>
												  </td>
												  <td>
													  <code>${authorEntity.realName}</code>
												  </td>
											  </tr>
											  <tr>
												  <td>
													  <span class="label">性取向</span>
												  </td>
												  <td>
													  <code>${authorEntity.sexualOrientation}</code>
												  </td>
												  <td>
													  <span class="label label-success">邮箱</span>
												  </td>
												  <td>
													  <code>${authorEntity.email}</code>
												  </td>
												  <td>
													  <span class="label label-warning">QQ</span>
												  </td>
												  <td>
													  <code>${authorEntity.qq}</code>
												  </td>
											  </tr>
											  <tr>
												  <td>
													  <span class="label">血型</span>
												  </td>
												  <td style="border-bottom: 1px solid #ddd">
													  <code>${authorEntity.bloodType}</code>
												  </td>

												  <td style="border-bottom: 1px solid #ddd">
													  <span class="label label-important"></span>
												  </td>
												  <td style="border-bottom: 1px solid #ddd">
													  <code></code>
												  </td>
												  <td style="border-bottom: 1px solid #ddd">
													  <span class="label label-important"></span>
												  </td>
												  <td style="border-bottom: 1px solid #ddd">
													  <code></code>
												  </td>
											  </tr>

											  <tr>
												<td>
												  <span class="label label-info">关注度:</span>
													<code>${authorEntity.focusNumber}</code>&nbsp;&nbsp;
													<span class="label label-info">粉丝量:</span>
													<code>${authorEntity.fansNumber}</code>&nbsp;&nbsp;
													<span class="label label-info">朋友量:</span>
													<code>${authorEntity.friendNumber}</code>&nbsp;&nbsp;
													<span class="label label-info">回复量:</span>
													<code>${authorEntity.replayNumber}</code>&nbsp;&nbsp;
													<span class="label label-info">点击量:</span>
													<code>${authorEntity.clickNumber}</code>&nbsp;&nbsp;
													<span class="label label-info">登陆次数:</span>
													<code>${authorEntity.loginNumber}</code>&nbsp;&nbsp;
													<span class="label label-info">精华文章数:</span>
													<code>${authorEntity.elitePostNumber}</code>&nbsp;&nbsp;
													<span class="label label-info">积分:</span>
													<code>${authorEntity.score}</code>&nbsp;&nbsp;
													<span class="label label-info">声望度:</span>
													<code>${authorEntity.fameDegree}</code>&nbsp;&nbsp;
													<span class="label label-info">获得赞赏数:</span>
													<code>${authorEntity.getReward}</code>&nbsp;&nbsp;
													<span class="label label-info">送出赞赏数:</span>
													<code>${authorEntity.giveReward}</code>&nbsp;&nbsp;
												</td>
											  </tr>
											</tbody>
										  </table>
											<h4>文章</h4>
										<div class="row-fluid">
											<!-- block -->
											<div class="block">
												<div class="navbar navbar-inner block-header">
													<div class="muted pull-left">检索结果</div>
												</div>
												<div class="block-content collapse in">
													<div class="span12">
														<div id="example_wrapper" class="dataTables_wrapper form-inline" role="grid">
															<div class="row"><div class="span6"><div id="example_length" class="dataTables_length">
																<label>
																	<select size="1" name="example_length" aria-controls="example"  style="margin-left:40px; width: 60px;">
																		<option value="10" selected="selected">10</option>
																		<option value="25">25</option><option value="50">50</option>
																		<option value="100">100</option></select> records per page</label>
															</div>
															</div>
																<div class="span6">

																	<div class="dataTables_filter" id="example_filter">
																		<label>Search: <input type="text" aria-controls="example"></label>
																	</div>
																</div>
															</div>
															<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered dataTable" id="example" aria-describedby="example_info">
																<thead>
																<tr role="row">
																	<th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Rendering engine: activate to sort column descending" style="width: 206px;">
																		标题</th>
																</tr>
																</thead>

																<tbody role="alert" aria-live="polite" aria-relevant="all">
																<c:forEach items="${authorEntity.articles}" var="a" varStatus="sts">
																	<tr class="gradeA odd">
																		<td class="">
																			<b><a href="<%=basePath%>authorInfo?authorId=${a.authorEntity.authorId}" target="_blank">${a.authorEntity.name}</a></b><br>
																			<b><a href="${a.newsResource}" target="_blank">${a.title}</a></b>
																			<div class="pull-right">
																				<span class="badge badge-info" style="background-color: #f89406">${a.similarDegree}</span>
																			</div>
																			<div id="content${sts.index}" class="text" style="">${a.content}</div>
																			<br/>
																				${a.postTime}
																			<div style="float: right">
																				来自于:${a.websiteEntity.websiteName}
																			</div>
																			<br/>
																			<span style="color: blue;" onclick="commentShow(${sts.index});">评论量:${a.commentNumber}</span>
																			浏览量:${a.scanNumber}
																			参与量:${a.participationNumber}
																			喜欢量:${a.likeNumber}
																			推荐量:${a.recommendNumber}
																			收藏量:${a.collectNumber}
																		</td>
																	</tr>
																	<tr style="background-color: #bce8f1">
																		<td>
																			<div id="comment${sts.index}" style="display: none">
																				<c:forEach items="${a.articleComments}" var="c">
																					<c:if test="${c.fatherCommentId == null}">
																						<a href="<%=basePath%>authorInfo?authorId=${c.authorEntity.authorId}" class="badge badge-info"><b>${c.authorEntity.name}</b></a>  <br/>
																						${c.content} <br/>
																						${c.commentTime}<br/>
																					</c:if>
																					<c:forEach items="${a.articleComments}" var="c2">
																						<c:if test="${c2.fatherCommentId == c.articleCommentId}">
																							<div style="margin-left: 3%">
																								<a href="<%=basePath%>authorInfo?authorId=${c2.authorEntity.authorId}" class="badge badge-info"><b>${c2.authorEntity.name}</b></a> <br/>
																								<span style="color: #1a1aa4;;">@${c.authorEntity.name}</span>
																									${c2.content} <br/>
																									${c2.commentTime}<br/>
																							</div>
																						</c:if>
																					</c:forEach>
																				</c:forEach>
																			</div>
																		</td>
																	</tr>
																</c:forEach>
																</tbody>
															</table>
															<div class="row"><div class="span6"></div><div class="span6"><div class="dataTables_paginate paging_bootstrap pagination"><ul><li class="prev disabled"><a href="#">← Previous</a></li><li class="active"><a href="#">1</a></li><li><a href="#">2</a></li><li><a href="#">3</a></li><li><a href="#">4</a></li><li><a href="#">5</a></li><li class="next"><a href="#">Next → </a></li></ul></div></div></div></div>
													</div>
												</div>
											</div>
											<!-- /block -->
										</div>

										<h4>评论</h4>
										<div class="row-fluid">
											<!-- block -->
											<div class="block">
												<div class="navbar navbar-inner block-header">
													<div class="muted pull-left">检索结果</div>
												</div>
												<div class="block-content collapse in">
													<div class="span12">
														<div id="example_wrapper" class="dataTables_wrapper form-inline" role="grid">
															<div class="row"><div class="span6"><div id="example_length" class="dataTables_length">
																<label>
																	<select size="1" name="example_length" aria-controls="example"  style="margin-left:40px; width: 60px;">
																		<option value="10" selected="selected">10</option>
																		<option value="25">25</option><option value="50">50</option>
																		<option value="100">100</option></select> records per page</label>
															</div>
															</div>
																<div class="span6">

																	<div class="dataTables_filter" id="example_filter">
																		<label>Search: <input type="text" aria-controls="example"></label>
																	</div>
																</div>
															</div>
															<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered dataTable" id="example" aria-describedby="example_info">
																<thead>
																<tr role="row">
																	<th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Rendering engine: activate to sort column descending" style="width: 206px;">
																		标题</th>
																</tr>
																</thead>

																<tbody role="alert" aria-live="polite" aria-relevant="all">
																<c:forEach items="${authorEntity.articleComments}" var="c" varStatus="sts">
																	<tr class="gradeA odd">
																		<td class="">
																			<b><a href="<%=basePath%>authorInfo?authorId=${c.authorEntity.authorId}" target="_blank">${c.authorEntity.name}</a></b><br>
																			<b><a href="${c.articleEntity.newsResource}" target="_blank">${c.articleEntity.title}</a></b>
																			<br/>
																				${c.articleEntity.postTime}
																			<div style="float: right">
																				来自于:${c.articleEntity.websiteEntity.websiteName}
																			</div>
																			<br/>
																			<span style="color: blue;" onclick="commentShow(${sts.index});">评论量:${c.articleEntity.commentNumber}</span>
																			浏览量:${c.articleEntity.scanNumber}
																			参与量:${c.articleEntity.participationNumber}
																			喜欢量:${c.articleEntity.likeNumber}
																			推荐量:${c.articleEntity.recommendNumber}
																			收藏量:${c.articleEntity.collectNumber}
																		</td>
																	</tr>
																	<tr>
																		<td>
																			<div id="comment${sts.index}"style="margin-left:40px;">
																				<b>${c.authorEntity.name}</b>  <br/>
																				${c.content} <br/>
																				<span>点赞量：${c.likeNumber}</span>
																				<span style="margin-left: 10px;">回复量：${c.likeNumber}</span>
																				<span style="margin-left: 10px;">${c.commentTime}</span><br/>
																			</div>
																		</td>
																	</tr>
																</c:forEach>
																</tbody>
															</table>
															<div class="row"><div class="span6"></div><div class="span6"><div class="dataTables_paginate paging_bootstrap pagination"><ul><li class="prev disabled"><a href="#">← Previous</a></li><li class="active"><a href="#">1</a></li><li><a href="#">2</a></li><li><a href="#">3</a></li><li><a href="#">4</a></li><li><a href="#">5</a></li><li class="next"><a href="#">Next → </a></li></ul></div></div></div></div>
													</div>
												</div>
											</div>
											<!-- /block -->
										</div>

										<script>
											function commentShow(index) {
												var css = $("#comment"+index).css("display");
												if (css == 'none'){
													$("#comment"+index).css("display", "");
												}else{
													$("#comment"+index).css("display", "none");
												}
											}
										</script>
                                    </div>
                                </div>
                            </div>
                            <!-- /block -->
                        </div>
                    </div>

                </div>
            </div>
            <hr>

		<!-- 引入尾部模板 -->
		<jsp:include page="/static/fore_footer.jsp"></jsp:include>


	</div>
        <!--/.fluid-container-->
        <script src="vendors/jquery-1.9.1.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="vendors/jGrowl/jquery.jgrowl.js"></script>
        <script src="assets/scripts.js"></script>
        <script>
        $(function() {
            $('.tooltip').tooltip();	
			$('.tooltip-left').tooltip({ placement: 'left' });	
			$('.tooltip-right').tooltip({ placement: 'right' });	
			$('.tooltip-top').tooltip({ placement: 'top' });	
			$('.tooltip-bottom').tooltip({ placement: 'bottom' });

			$('.popover-left').popover({placement: 'left', trigger: 'hover'});
			$('.popover-right').popover({placement: 'right', trigger: 'hover'});
			$('.popover-top').popover({placement: 'top', trigger: 'hover'});
			$('.popover-bottom').popover({placement: 'bottom', trigger: 'hover'});

			$('.notification').click(function() {
				var $id = $(this).attr('id');
				switch($id) {
					case 'notification-sticky':
						$.jGrowl("Stick this!", { sticky: true });
					break;

					case 'notification-header':
						$.jGrowl("A message with a header", { header: 'Important' });
					break;

					default:
						$.jGrowl("Hello world!");
					break;
				}
			});
        });
        </script>
    </body>

</html>