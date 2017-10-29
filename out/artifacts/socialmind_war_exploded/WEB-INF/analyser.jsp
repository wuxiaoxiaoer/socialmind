<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html>
<html lang="en">
  <head>
  	<base href="<%=basePath%>" />
    <meta charset="utf-8">
    <title>微分析 - 微博舆情分析</title>
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
        <div class="templatemo-content-widget white-bg" style="margin:50px;">
        	<h2 class="margin-bottom-10" style="font-family: '黑体'">微分析</h2>
        	<h2 class="margin-bottom-10">Micro Analysis</h2>
        	<p>输入一篇新闻，点击 ‘<span style="color:red;font-weight: bold;">生成摘要</span>’ 形成一篇摘要，然后点击  ‘<span style="color:red;font-weight: bold;">查找相关微博</span>’ ，将输出相关微博。</p>
	        <div>
	           	<form id="form1" method="post">
	            	<span><textarea id="news" style="border-radius:24px;" class="form-control" id="inputNote" rows="10" placeholder="输入一篇新闻"></textarea></span>
	            	<span><button type="button" onclick="javascript:geneSummari_submit();" style="border-radius:44px;" class="templatemo-blue-button">生成摘要</button></span>
	           	</form>
	            	<div id="summarize" style="padding-top:4%;display:none;">
	            		<div class="panel-heading border-radius-10" style="background-color: #39ADB4;border-radius:0px;color:white;margin-top:20px;">
			                <h2 style="font-family: '黑体'">形成摘要</h2>
			            </div>
		            	<span><textarea id="summar" style="border-radius:0px;" class="form-control" id="inputNote" rows="3" placeholder="生成摘要"></textarea></span>
		            	<span><button type="button" style="border-radius:44px;" onclick="relativeWeibo();" class="templatemo-blue-button">查找相关微博</button></span>
	            	</div>
					<div class="col-lg-12 form-group" style="margin-top:5px;">
	                    
	                </div>
	                
			            <hr/>
	                <div id="weiboBody" style="display:none">
		                <div class="panel-heading border-radius-10" style="background-color: #39ADB4;border-radius:0px;color:white;margin-top:20px;">
			                <h2 style="font-family: '黑体'">相关微博</h2>
			            </div>
		                <div id="weibocontent" style="padding:0px;">
				        </div>
	                </div>
	                
	        </div>
	        
	        <script type="text/javascript">
	        	function geneSummari_submit(){
	        		$("#summarize").css("display","");
	        		var news = encodeURI($("#news").val());
	        		$.ajax({
	        			type : 'post',
	        			url : 'genesummarize?news='+news,
	        			scriptCharset : 'utf-8',
	        			success : function (msg){
	        				$("#summar").html(msg);
	        			}
	        		});
	        	}
	        	
	        	function relativeWeibo(){
	        		$("#weiboBody").css("display", "");
	        		var summ = $("#summar").val();
	        		$.ajax({
	        			type:'post',
	        			url : 'relativeSummarWeibo?summar='+summ,
	        			beforeSend : loadFunction,
	        			error : errorFunction,
	        			success : successFunction
	        		});
	        		
	        		function loadFunction(){
	        			$("#weibocontent").html("加载中...");
	        		}
	        		
	        		function errorFunction(){
	        			$("#weibocontent").html("加载失败,稍后重试...");
	        		}
	        		
	        		function successFunction(msg){
	        			$("#weibocontent").html(" ");
	        			var json = eval(msg);
	        			var divFir = "";
	        			var divEnd = "";
	        			$.each(json, function(index, item){
	        				
	        				$("#weibocontent").append(
	        				"<div style='float:left;width:106.7%;padding:0px;margin-left:-40px;margin-right:-40px;' class='templatemo-flex-row flex-content-row'>"
	        				+"<div name='unit' class='templatemo-content-widget white-bg col-1 text-center templatemo-position-relative'>"
	        				+"<i class='fa fa-times' style='background-color:#F58110;margin-right:20px;padding:9px 20px;'>simi:"+item.simiDegree+"</i>"
	        				+"<img src='images/demo/rmrb.jpg' alt='Bicycle' class='img-circle img-thumbnail margin-bottom-30'>"
	        				+"<h2 class='text-uppercase blue-text margin-bottom-5'>${w.wbuser.wbu_name}</h2>"
	        				+"<h3 style='height:auto;font-size: 12px;'>"+item.twoText+"</h3>"
	        				+"<h4 style='float:right;height:40px;font-size: 12px;'><a target='_blank' href='${w.wb_originalLink}'><i style='background-image:url('images/icon-link.png');width:16px;height:16px;display: inline-block'></i><b>原文链接</b></a></h4>"
	        				+"<div class='templatemo-social-icons-container'>"
	        				+"<div class='social-icon-wrap'>"
	        				+"<i>阅读量</i>"
	        				+"</div>"
	        				+"<div class='social-icon-wrap'>"
	        				+"<i>转载量 </i>"
	        				+"</div>"
	        				+"<div class='social-icon-wrap'>"
	        				+"<i>评论量 </i>"
	        				+"</div>"
	        				+"<div class='social-icon-wrap'>"
	        				+"<i>点赞量 </i>"
	        				+"</div>"
	        				+"</div>"
	        				+"</div>"
	        				+"</div>"
	        				);
	        			});
	        		}
	        	}
	        </script>
	        
	        <%-- <div class="templatemo-content-container" style="margin-top:100%;">
	          <jsp:include page="/jsp/statics/foot.jsp"></jsp:include>
	        </div>--%>      
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