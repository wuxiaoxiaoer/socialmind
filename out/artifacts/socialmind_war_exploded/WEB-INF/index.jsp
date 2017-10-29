<%@ page language="java" import="java.util.*" import="com.sicdlib.entity.KeyWords" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

List<KeyWords> keywords = (List<KeyWords>) request.getAttribute("topkeywords");
%>

<!DOCTYPE html>
<html lang="en">

  <head>
    <meta charset="utf-8">
    <title>微博舆情分析 - Home</title>
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/templatemo-style.css" rel="stylesheet">
	<script src="js/jquery-1.11.2.min.js"></script>
<!-- 动画css开始 -->
<!-- 以下信息与演示无关，可不必理会 -->
<style>
.nav {width:10%; margin: 0 auto 20px; text-align: center; font-size: 0;
font-family: "Microsoft YaHei", "Microsoft Yahei", "微软雅黑";
float:right;}
.nav li {margin: 0 5px; padding: 10px 20px; font-size: 14px; color: #333; background-color: #ccc;}
.nav .active { color: #fff; background-color: #21b384;}
.sucaihuo { width: 900px; font-size: 0;float:left;}
.filtr-item { width: 40%;}
.filtr-item img {width: 100%; height: 40%;}

.filtr-item {position: relative;}
.words{
	font-size: 36px;  
	font-family: "Microsoft YaHei", "Microsoft Yahei", "微软雅黑", "", "Courier New", "Helvetica Neue", Courier, monospace;
    letter-spacing: 4px;  
    position: absolute;  
    top:4%;  
    left: 40%;
    color:white;
    padding-top:20%;
    margin-left:-6%;
   }
   
.filtr-item img:hover{ opacity:0.5;filter(alpha=100); background-color: black;}
.words hover{ opacity:0.5;filter(alpha=100); background-color: black;}

</style>

<!-- 动画css结束 -->
  </head>
  <body style="width:100%;height:100%">  
  
    <!-- Left column -->
    <div class="templatemo-flex-row">
      <jsp:include page="/jsp/statics/left.jsp"/> 
      <!-- Main content --> 
      <div class="templatemo-content col-1 light-gray-bg">
        <div class="templatemo-top-nav-container">
          <jsp:include page="/jsp/statics/header.jsp"/>
        </div>
        <div class="templatemo-content-container" style="padding:0">
		<script type="text/javascript">
			/* $(document).ready(function(e){
				$(document).on('mouseover',function(e){
					$(".words").css("font-size","40px");
				});
			}); */
			function mouseover1(){
					$("#word1").css("font-size","48px");
			} 
			function mouseout1(){
					$("#word1").css("font-size","30px");
			} 
			
			function getRandomNum1(){
				$.ajax({
					type : 'post',
					dataType : 'json',
					contentType : 'application/json',
					url : 'getRandomKeyWords',
					success : function(json){
						//$("#init_keywords").css("display","none");
						$("#init_keywords").html("");
						var str = "";
						for(i in json){
							//alert(i+":"+json[i].kw_name);
							str ="<div id='pic_1' onmouseover='mouseover1()' onmouseout='mouseout1()' style='position:absolute; opacity: 1; transform: scale(1) translate3d(750px, 0px, 0px); perspective: 1000px; transition: all 0.5s ease-out 0ms;' class='filtr-item' data-category='2'>"
							+"<a href='#'><img src='images/demo/"+1+".jpg'><div id='word1' class='words'><br>"
							+json[i].kw_name+"</div></a></div></div>";
							$("#init_keywords").append(str);
						}
						$("#init_keywords").append("<ul class='nav'>"
						+"<li id='next_btn' onclick='getRandomNum()' class='shuffle-btn' data-shuffle=''>下一批</li>"
						+"</ul></div>");
					}
				});
			}
			
			function getRandomNum11(){
				$('.sucaihuo').filterizr();
				if($("#init_keywords1").css("display")=='block'){
					$("#init_keywords1").css("display","none");
					$("#init_keywords2").css("display","block");
				}else{
					$("#init_keywords1").css("display","block");
					$("#init_keywords2").css("display","none");
				}
			}
			
			$(function() {
				$('.sucaihuo').filterizr();
			
				$('.nav li').on('click', function() {
					$(this).toggleClass('active').siblings().removeClass('active');
				});
			});
			/* function myfun(){
				$("#sucaihuo_1").filterizr();
				$("#sucaihuo_1").css("display","");
				$("#sucaihuo_2").css("display","none");
			}			
			window.onload=myfun; */
		</script>
		<div id="init_keywords1" style="padding: 0px; position: relative; height: 453px;" class="sucaihuo">
			<!-- 第一次 -->
			<c:forEach var="tk" items="${topkeywords}" varStatus="s">
				<c:if test="${s.index<9}">
					<div id="pic_1" onmouseover="mouseover1()" onmouseout="mouseout1()" style="position:relative; opacity: 1; transform: scale(1) translate3d(750px, 0px, 0px); perspective: 1000px; transition: all 0.5s ease-out 0ms;" class="filtr-item" data-category="2">
		                <a href="<%=path%>/weibolists?kwid=${tk.kw_id}">
		                    <img src="images/demo/${s.index+1}.jpg" alt="" title="${s.index+1}">
		                    <div id="word1" class="words">
						　　　　<br>${tk.kw_name}
						　　	</div>
		                </a> 
		            </div>
				</c:if>		           			
			</c:forEach>
			
			<div style="padding-left:119.45%;">
				<ul class="nav">
					<li id="next_btn" style="width:56px;" class="shuffle-btn" data-shuffle="">下一批</li>
				</ul>   
			</div> 
       </div>
      <%--   
		<div id="init_keywords2" style="display:none;padding: 0px; position: relative; height: 453px;" class="sucaihuo">
			<!-- 第一次 -->
			<c:forEach var="tk" items="${topkeywords}" varStatus="s">
				<c:if test="${s.index >2&&s.index<12}">
					<div id="pic_1" onmouseover="mouseover1()" onmouseout="mouseout1()" style="position:relative; opacity: 1; transform: scale(1) translate3d(750px, 0px, 0px); perspective: 1000px; transition: all 0.5s ease-out 0ms;" class="filtr-item" data-category="2">
		                <a href="#">
		                    <img src="images/demo/${s.index+1}.jpg" alt="" title="${s.index+1}">
		                    <div id="word1" class="words">
						　　　　<br>${tk.kw_name}
						　　	</div>
		                </a> 
		            </div>
				</c:if>		           			
			</c:forEach>
			<ul class="nav">
				<li id="next_btn" onclick="getRandomNum()" class="shuffle-btn" data-shuffle="">下一批</li>
			</ul>    
       </div> --%>
<script>

/* $(function() {
	$('.sucaihuo').filterizr();

	$('.nav li').on('click', function() {
		$(this).toggleClass('active').siblings().removeClass('active');
	});
}); */

</script> 
       	<div style="padding:50px;width:100%;height:10%;float:right;">
         	<jsp:include page="/jsp/statics/foot.jsp"/> 
       	</div> 
      </div> 
  </div>

	<!-- JS -->
	<script src="js/jquery.filterizr.js"></script>
	<!-- jQuery -->
	<script src="js/jquery-migrate-1.2.1.min.js"></script>
	<!--  jQuery Migrate Plugin -->
	
	<script type="text/javascript" src="js/templatemo-script.js"></script>
	<!-- Templatemo Script -->
</body>
</html>