<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

session.setAttribute("pageNo", 1);
%>
<!DOCTYPE html>
<html lang="en">
  <head>
  	<base href="<%=basePath%>" />
    <meta charset="utf-8">
    <title>微博列表 - 微博舆情分析</title>
	<link href="css/opensans.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/templatemo-style.css" rel="stylesheet">
	<link rel="stylesheet" href="css/pikaday.css">
	<link rel="stylesheet" href="css/site.css">
	<style type="text/css">
		i{
			font-size: 10px;
			font-style: normal;
			color:#7f7f7f;
		}
		.img-circle img-thumbnail margin-bottom-30{
			max-width:30%;
		}
	</style>  
  </head>
  <body>  
    <!-- Left column -->
    <div class="templatemo-flex-row">
      <jsp:include page="/jsp/statics/left.jsp"></jsp:include>
      <!-- Main content --> 
      <div class="templatemo-content col-1 light-gray-bg">
        <div class="templatemo-top-nav-container">
          <jsp:include page="/jsp/statics/header.jsp"></jsp:include>
        </div>
        
        <div style="padding:20px 30px;margin:auto 0;width:100%;height:50px;">
           		<input type="hidden" name="token" value="${token}"/>
            	
				<div class="col-lg-12 form-group" style="margin-top:5px;">                   
                    <div class="templatemo-content-widget white-bg">
				           <h2 class="margin-bottom-10" style="font-family:Arial,Helvetica,sans-serif,SimSun;width:94%;float:left;">关键热词：<b>${keyword.kw_name}</b></h2>
				           <h1 style="display:inline;background-color: #5F6262;height:20px;"><a style="color:#FFFFFF; font-size:30px; font-family: 'SimHei'" onclick="history.go(-1);">返回</a></h1>
				           <p class="margin-bottom-0">关键热词热度：${keyword.kw_hotDegree}&nbsp&nbsp&nbsp 
				           		相关微博数：${weiboSize}条&nbsp&nbsp&nbsp 关键热词时间：${keyword.kw_Date}</p>
			        </div>
                </div>
        </div>
        
        <div class="templatemo-content-container" style="float:left;padding:50px 45px;">
                   
          <div class="templatemo-flex-row flex-content-row">
            <div class="col-1">
              <div class="panel panel-default margin-10">
              </div>
            </div>                      
          </div> <!-- Second row ends -->
          <c:forEach items="${weibos}" var = "w" varStatus="s">
       		<c:if test="${s.index % 5 == 0}">
       			<div class="templatemo-flex-row flex-content-row">
       		</c:if>
          	<div name="unit" class="templatemo-content-widget white-bg col-1 text-center templatemo-position-relative">
              <i class="fa fa-times"></i>
              <img src="${w.wbuser.wbu_portrait}" alt="Bicycle" class="img-circle img-thumbnail margin-bottom-30">
              <h2 class="text-uppercase blue-text margin-bottom-5">${w.wbuser.wbu_name}</h2>
              <h3 style="height:auto;font-size: 12px;">${w.wb_content}</h3>
              <h4 style="float:right;height:40px;font-size: 12px;"><a target="_blank" href="${w.wb_originalLink}"><i style="background-image:url('images/icon-link.png');width:16px;height:16px;display: inline-block"></i><b>原文链接</b></a></h4>
              <div class="templatemo-social-icons-container">
                <div class="social-icon-wrap">
                  <i>阅读量${w.wb_readNum}</i>  
                </div>
                <div class="social-icon-wrap">
                  <i>转载量 ${w.wb_forwardNum}</i>  
                </div>
                <div class="social-icon-wrap">
                  <i>评论量 ${w.wb_commentsNum}</i>  
                </div>
                <div class="social-icon-wrap">
                  <i>点赞量 ${w.wb_likeNum}</i>  
                </div>            
              </div>
            </div>
          	<c:if test="${s.index%5 == 4 || s.last}">
       			</div>
       		</c:if>
          		
          </c:forEach>
         
         <%--  <div name="unit" class="pagination-wrap">
            <ul class="pagination">
              <li><a href="<%=path%>/searchsPage?pageNo=1">1</a></li>
              <li><a href="<%=path%>/searchsPage?pageNo=2">2</a></li>
              <li class="active"><a href="<%=path%>/searchsPage?pageNo=3">3 <span class="sr-only">(current)</span></a></li>
              <li><a href="<%=path%>/searchsPage?pageNo=4">4</a></li>
              <li><a href="<%=path%>/searchsPage?pageNo=5">5</a></li>
              <li>
                <a href="javascript:nextPage();" aria-label="Next">
                  <span aria-hidden="true"><i class="fa fa-play"></i></span>
                </a>
              </li>
            </ul>            
          </div>  --%>
          <script type="text/javascript">
          	function nextPage(){
          		var str = window.location.href;
          		var arr = str.split('=');
          		var pno = parseInt(arr[1]);
          		window.location.href = "<%=path%>/searchsPage?pageNo="+(pno+1);		
          	}
          </script>
          <jsp:include page="/jsp/statics/foot.jsp"></jsp:include>
        </div>      
      </div>
    </div>
    
    <!-- JS -->
    
    <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
    <script type="text/javascript" src="js/templatemo-script.js"></script>      <!-- Templatemo Script -->  
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