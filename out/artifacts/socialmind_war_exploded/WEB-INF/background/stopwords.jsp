<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>微博管理 - 微博舆情分析系统</title>

<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/templatemo-style.css" rel="stylesheet">
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/style.css" rel="stylesheet" />
<script   type="text/javascript">
function show(tag){
    var light=document.getElementById(tag);
   // var fade=document.getElementById('fade');
    light.style.display='block';
   // fade.style.display='block';
}
function hide(tag){
    var light=document.getElementById(tag);
    //var fade=document.getElementById('fade');
    light.style.display='none';
    //fade.style.display='none';
}
</script>
</head>
<body>
	<!-- Left column -->
	<div class="templatemo-flex-row">
		<div class="templatemo-sidebar">
			<jsp:include page="/jsp/back_static/left.jsp" />
		</div>
		<!-- Main content -->
		<div class="templatemo-content col-1 light-gray-bg">
			<div class="templatemo-top-nav-container">
				<jsp:include page="/jsp/statics/header.jsp" />
			</div>
			<div class="templatemo-content-container">
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive"
						style="width:100%;">
						<div class="templatemo-content-widget white-bg">
						<table
							class="table table-striped table-bordered templatemo-user-table"
							style="width:100%">
							<thead style="width:100%">
								<tr style="width:100%">
									<td style="width:20%">No. 
									
									</td>
									<td style="width:30%">停用词
									
									</td>
									<td style="width:25%">修改
									</td>
									<td style="width:25%">删除</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.result}" var="cs" varStatus="vs">
									<tr>
										<td>${vs.index+1}</td>
										<td>${cs.csd_word }</td>
										<td><a href="javascript:void(0)"
											onclick="show('light${vs.index+1}')" class="templatemo-edit-btn">edit</a> <%--<td><button onclick="ck();" class="templatemo-edit-btn">Delete</button></td> --%>
										<td><a
											href="background/deleteStopwords?csd_id=${cs.csd_id}"
											class="templatemo-edit-btn">Delete</a>
										</td>
									</tr>
									<div>
										<form action="background/editStopwords" method="post">
											<div id="light${vs.index+1}" class="white_content"
												style="border:0px;height:180px;">
												<div class="close">
													<a href="javascript:void(0)"
														onclick="hide('light${vs.index+1}')"> <img
														title="关闭" src="images/close.png"
														style="padding: 0px; margin: 0px; border: 0px;"> </a>
												</div>
												<div class="con" >
													<center>
														<h3 style="margin-left:-40px">修改停用词信息</h3>
													</center>
													<input type="hidden" name="pageNo" value="1">
													<input type="hidden" name="csd_id" value="${cs.csd_id}" /> 
													<input
														id="csd_word" name="csd_word" type="text"
														value="${cs.csd_word}" onblur="return checkUser()"
														class="form-control"
														style="margin-top: 10px;width:220px;margin-left:20px"
														placeholder="关键词名称"> 
													<button type="submit"
														class="templatemo-blue-button width-100"
														style="margin-top:15px;width:100px;margin-left:60px">修
														改</button>
												</div>
											</div>
										</form>
									</div>
								</c:forEach>
								</table>
								
							</tbody>
							
					</div>
					
					<div class="pagination-wrap">
									<ul class="pagination">
										<!-- 前一页 -->
										<%-- <a href="background/stopwords/${page.prePage}"
											aria-label="Next"> <span aria-hidden="true"><i
												class="fa fa-play"></i>
										</span> </a> --%>
										<li><a href="background/stopwords/1">1</a>
										</li>
										<li><a href="background/stopwords/2">2</a>
										</li>
										<li><a href="background/stopwords/3">3
												<span class="sr-only">(current)</span>
										</a>
										</li>
										<li><a href="background/stopwords/4">4</a>
										</li>
										<li><a href="background/stopwords/5">5</a>
										</li>
										<li><a href="background/stopwords/${page.nextPage}"
											aria-label="Next"> <span aria-hidden="true"><i
													class="fa fa-play"></i>
											</span> </a></li>
									</ul>
								</div>
					
					</div>
				</div>

				<div style="padding:50px;width:100%;height:10%;float:right;">
					<jsp:include page="/jsp/statics/foot.jsp"></jsp:include>
				</div>
				
			</div>
		</div>
	</div>

	<!-- JS -->
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	<!-- jQuery -->
	<script type="text/javascript" src="js/templatemo-script.js"></script>
	<!-- Templatemo Script -->
	<script type="text/javascript"
		src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script>
      $(document).ready(function(){
        // Content widget with background image
        var imageUrl = $('img.content-bg-img').attr('src');
        $('.templatemo-content-img-bg').css('background-image', 'url(' + imageUrl + ')');
        $('img.content-bg-img').hide();        
      });
    </script>
</body>
</html>