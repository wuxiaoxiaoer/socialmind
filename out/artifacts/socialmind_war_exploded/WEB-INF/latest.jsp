<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>最近要闻 - 微博舆情分析系统</title>
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/templatemo-style.css" rel="stylesheet">
<script src="js/jquery-1.11.2.min.js"></script>
<!-- 动画css开始 -->
<!-- 以下信息与演示无关，可不必理会 -->
<style>
.nav .active {
	color: #fff;
	background-color: #21b384;
}
.nav {margin: 0 auto 20px; text-align: center; font-size: 0;
font-family: "Microsoft YaHei", "Microsoft Yahei", "微软雅黑";
float:right;}
.nav li {margin: 0 5px; padding: 10px 20px; font-size: 14px; color: #333; background-color: #ccc;}
.nav .active { color: #fff; background-color: #21b384;}
.sucaihuo { width: 900px; font-size: 0;float:left;}

.filtr-item {
	display: inline-block;
	width: 230px;
}

.filtr-item img {width: 100%; height: 40%;}
.filtr-item {position: relative;width: 40%;}
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
		<jsp:include page="/jsp/statics/left.jsp"></jsp:include>
		<!-- Main content -->
		<div class="templatemo-content col-1 light-gray-bg">
			<div class="templatemo-top-nav-container">
				<jsp:include page="/jsp/statics/header.jsp"></jsp:include>
			</div>
			<div class="templatemo-content-container" style="padding:0px;">

				<div id="sucaihuo_1" style="padding: 0px; position: relative; height: 453px;"
					class="sucaihuo">
					<!-- <div
						style="position:relative;opacity: 1; transform: scale(1) translate3d(750px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="2">
						<a href="#"> 
						<img src="images/demo/1.jpg" alt="">
						<div id="word1" class="words">
					　　　　<br>西安大雨
					　　	</div>
						</a>
					</div> -->
					
					<c:forEach items="${td0_topkeywords}" var="td0" varStatus="s">
						<c:if test="${s.index<9 }">
						<div
							style="position:relative;opacity: 1; transform: scale(1) translate3d(750px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
							class="filtr-item" data-category="2">
							<a href="<%=path%>/weibolists?kwid=${td0.kw_id}"> 
							<img src="images/demo/${s.index+1}.jpg" alt="">
							<div id="word1" class="words">
						　　　　<br>${td0.kw_name}
						　　	</div>
							</a>
						</div>
						</c:if>
					</c:forEach>
					
					<!-- <div
						style="opacity: 1; transform: scale(1) translate3d(500px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="1">
						<a href="#"> 
							<img src="images/demo/2.jpg" alt="">
						</a>
					</div>
					<div
						style="opacity: 1; transform: scale(1) translate3d(0px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="1">
						<a href="#"> 
							<img src="images/demo/3.jpg" alt="">
						</a>
					</div>
					<div
						style="opacity: 1; transform: scale(1) translate3d(250px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="3">
						<a href="#"> 
							<img src="images/demo/4.jpg" alt="">
						</a>
					</div>
					<div
						style="opacity: 1; transform: scale(1) translate3d(500px, 151px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="1">
						<a href="#"> 
							<img src="images/demo/5.jpg" alt="">
						</a>
					</div>					
					<div
						style="opacity: 1; transform: scale(1) translate3d(250px, 151px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="1">
						<a href="#"> 
							<img src="images/demo/6.jpg" alt="">
						</a>
					</div>
					<div
						style="opacity: 1; transform: scale(1) translate3d(0px, 151px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="2">
						<a href="#"> 
							<img src="images/demo/7.jpg" alt="">
						</a>
					</div>
					<div
						style="opacity: 1; transform: scale(1) translate3d(750px, 151px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="2">
						<a href="#"> 
							<img src="images/demo/8.jpg" alt="">
						</a>
					</div>
					<div
						style="opacity: 1; transform: scale(1) translate3d(750px, 151px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="2">
						<a href="#"> 
							<img src="images/demo/9.jpg" alt="">
						</a>
					</div> -->					
				</div>
				
				<div id="sucaihuo_2" style="display:none; padding: 0px; position: relative; height: 453px;"
					class="sucaihuo">
					<%-- <div
						style="opacity: 1; transform: scale(1) translate3d(750px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="2">
						<a href="<%=path%>/weibolists?kwid=${td0.kw_id}"> <img src="images/demo/2.jpg" alt="">
						</a>
					</div> --%>
					<c:forEach items="${td1_topkeywords}" var="td0" varStatus="s">
					<c:if test="${s.index<9 }">
						<div
							style="position:relative;opacity: 1; transform: scale(1) translate3d(750px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
							class="filtr-item" data-category="2">
							<a href="<%=path%>/weibolists?kwid=${td0.kw_id}"> 
							<img src="images/demo/${s.index+1}.jpg" alt="">
							<div id="word1" class="words">
						　　　　<br>${td0.kw_name}
						　　	</div>
							</a>
						</div>
						</c:if>
					</c:forEach>
					<!-- <div
						style="opacity: 1; transform: scale(1) translate3d(500px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="1">
						<a href="#"> <img src="img/city_2.jpg" alt="">
						</a>
					</div>
					<div
						style="opacity: 1; transform: scale(1) translate3d(0px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="1">
						<a href="#"> <img src="img/city_3.jpg" alt="">
						</a>
					</div>
					<div
						style="opacity: 1; transform: scale(1) translate3d(250px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="3">
						<a href="#"> <img src="img/industrial_1.jpg" alt="">
						</a>
					</div>
					<div
						style="opacity: 1; transform: scale(1) translate3d(500px, 151px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="1">
						<a href="#"> <img src="img/industrial_2.jpg" alt="">
						</a>
					</div>					
					<div
						style="opacity: 1; transform: scale(1) translate3d(250px, 151px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="1">
						<a href="#"> <img src="img/nature_1.jpg" alt="">
						</a>
					</div>
					<div
						style="opacity: 1; transform: scale(1) translate3d(0px, 151px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="2">
						<a href="#"> <img src="img/nature_2.jpg" alt="">
						</a>
					</div>
					<div
						style="opacity: 1; transform: scale(1) translate3d(750px, 151px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="2">
						<a href="#"> <img src="img/nature_3.jpg" alt="">
						</a>
					</div> -->			
				</div>
				
				<div id="sucaihuo_3" style="display:none; padding: 0px; position: relative; height: 453px;"
					class="sucaihuo">
					<%-- <div
						style="opacity: 1; transform: scale(1) translate3d(750px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="2">
						<a href="<%=path%>/weibolists?kwid=${td0.kw_id}"> <img src="images/demo/3.jpg" alt="">
						</a>
					</div> --%>
					<c:forEach items="${td2_topkeywords}" var="td0" varStatus="s">
					<c:if test="${s.index<9 }">
						<div
							style="position:relative;opacity: 1; transform: scale(1) translate3d(750px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
							class="filtr-item" data-category="2">
							<a href="<%=path%>/weibolists?kwid=${td0.kw_id}"> 
							<img src="images/demo/${s.index+1}.jpg" alt="">
							<div id="word1" class="words">
						　　　　<br>${td0.kw_name}
						　　	</div>
							</a>
						</div>
						</c:if>
					</c:forEach>
					<!-- <div
						style="opacity: 1; transform: scale(1) translate3d(500px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="1">
						<a href="#"> <img src="img/city_2.jpg" alt="">
						</a>
					</div>
					<div
						style="opacity: 1; transform: scale(1) translate3d(0px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="1">
						<a href="#"> <img src="img/city_3.jpg" alt="">
						</a>
					</div>
					<div
						style="opacity: 1; transform: scale(1) translate3d(250px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="3">
						<a href="#"> <img src="img/industrial_1.jpg" alt="">
						</a>
					</div>
					<div
						style="opacity: 1; transform: scale(1) translate3d(500px, 151px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="1">
						<a href="#"> <img src="img/industrial_2.jpg" alt="">
						</a>
					</div>					
					<div
						style="opacity: 1; transform: scale(1) translate3d(250px, 151px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="1">
						<a href="#"> <img src="img/nature_1.jpg" alt="">
						</a>
					</div>
					<div
						style="opacity: 1; transform: scale(1) translate3d(0px, 151px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="2">
						<a href="#"> <img src="img/nature_2.jpg" alt="">
						</a>
					</div>
					<div
						style="opacity: 1; transform: scale(1) translate3d(750px, 151px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="2">
						<a href="#"> <img src="img/nature_3.jpg" alt="">
						</a>
					</div> -->					
				</div>
				
				<div id="sucaihuo_4" style="display:none; padding: 0px; position: relative; height: 453px;"
					class="sucaihuo">
					<%-- <div
						style="opacity: 1; transform: scale(1) translate3d(750px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="2">
						<a href="<%=path%>/weibolists?kwid=${td0.kw_id}"> <img src="images/demo/4.jpg" alt="">
						</a>
					</div> --%>
					<c:forEach items="${td3_topkeywords}" var="td0" varStatus="s">
					<c:if test="${s.index<9 }">
						<div
							style="position:relative;opacity: 1; transform: scale(1) translate3d(750px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
							class="filtr-item" data-category="2">
							<a href="<%=path%>/weibolists?kwid=${td0.kw_id}"> 
							<img src="images/demo/${s.index+1}.jpg" alt="">
							<div id="word1" class="words">
						　　　　<br>${td0.kw_name}
						　　	</div>
							</a>
						</div>
						</c:if>
					</c:forEach>
					<!-- <div
						style="opacity: 1; transform: scale(1) translate3d(500px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="1">
						<a href="#"> <img src="img/city_2.jpg" alt="">
						</a>
					</div>
					<div
						style="opacity: 1; transform: scale(1) translate3d(0px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="1">
						<a href="#"> <img src="img/city_3.jpg" alt="">
						</a>
					</div>
					<div
						style="opacity: 1; transform: scale(1) translate3d(250px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="3">
						<a href="#"> <img src="img/industrial_1.jpg" alt="">
						</a>
					</div>
					<div
						style="opacity: 1; transform: scale(1) translate3d(500px, 151px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="1">
						<a href="#"> <img src="img/industrial_2.jpg" alt="">
						</a>
					</div>					
					<div
						style="opacity: 1; transform: scale(1) translate3d(250px, 151px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="1">
						<a href="#"> <img src="img/nature_1.jpg" alt="">
						</a>
					</div>
					<div
						style="opacity: 1; transform: scale(1) translate3d(0px, 151px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="2">
						<a href="#"> <img src="img/nature_2.jpg" alt="">
						</a>
					</div>
					<div
						style="opacity: 1; transform: scale(1) translate3d(750px, 151px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="2">
						<a href="#"> <img src="img/nature_3.jpg" alt="">
						</a>
					</div>	 -->					
				</div>
				
				<div id="sucaihuo_5" style="display:none; padding: 0px; position: relative; height: 453px;"
					class="sucaihuo">
					<%-- <div
						style="opacity: 1; transform: scale(1) translate3d(750px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="2">
						<a href="<%=path%>/weibolists?kwid=${td0.kw_id}"> <img src="images/demo/5.jpg" alt="">
						</a>
					</div> --%>
					<c:forEach items="${td4_topkeywords}" var="td0" varStatus="s">
					<c:if test="${s.index<9 }">
						<div
							style="position:relative;opacity: 1; transform: scale(1) translate3d(750px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
							class="filtr-item" data-category="2">
							<a href="<%=path%>/weibolists?kwid=${td0.kw_id}"> 
							<img src="images/demo/${s.index+1}.jpg" alt="">
							<div id="word1" class="words">
						　　　　<br>${td0.kw_name}
						　　	</div>
							</a>
						</div>
						</c:if>
					</c:forEach>
					<!-- <div
						style="opacity: 1; transform: scale(1) translate3d(500px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="1">
						<a href="#"> <img src="img/city_2.jpg" alt="">
						</a>
					</div>
					<div
						style="opacity: 1; transform: scale(1) translate3d(0px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="1">
						<a href="#"> <img src="img/city_3.jpg" alt="">
						</a>
					</div>

					<div
						style="opacity: 1; transform: scale(1) translate3d(250px, 0px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="3">
						<a href="#"> <img src="img/industrial_1.jpg" alt="">
						</a>
					</div>
					<div
						style="opacity: 1; transform: scale(1) translate3d(500px, 151px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="1">
						<a href="#"> <img src="img/industrial_2.jpg" alt="">
						</a>
					</div>
					
					<div
						style="opacity: 1; transform: scale(1) translate3d(250px, 151px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="1">
						<a href="#"> <img src="img/nature_1.jpg" alt="">
						</a>
					</div>
					<div
						style="opacity: 1; transform: scale(1) translate3d(0px, 151px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="2">
						<a href="#"> <img src="img/nature_2.jpg" alt="">
						</a>
					</div>
					<div
						style=" opacity: 1; transform: scale(1) translate3d(750px, 151px, 0px); perspective: 1000px; position: absolute; transition: all 0.5s ease-out 0ms;"
						class="filtr-item" data-category="2">
						<a href="#"> <img src="img/nature_3.jpg" alt="">
						</a>
					</div>	 -->					
				</div>
				
				<script>
					function click_1(){
						$("#sucaihuo_1").filterizr();
						$("#sucaihuo_1").css("display","");
						$("#sucaihuo_2").css("display","none");
						$("#sucaihuo_3").css("display","none");
						$("#sucaihuo_4").css("display","none");
						$("#sucaihuo_5").css("display","none");
					}
					
					function click_2(){
						$("#sucaihuo_2").filterizr();
						$("#sucaihuo_2").css("display","");
						$("#sucaihuo_1").css("display","none");
						$("#sucaihuo_3").css("display","none");
						$("#sucaihuo_4").css("display","none");
						$("#sucaihuo_5").css("display","none");
					}
					
					function click_3(){
						$("#sucaihuo_3").filterizr();
						$("#sucaihuo_3").css("display","");
						$("#sucaihuo_1").css("display","none");
						$("#sucaihuo_2").css("display","none");
						$("#sucaihuo_4").css("display","none");
						$("#sucaihuo_5").css("display","none");
					}
					function click_4(){
						$("#sucaihuo_4").filterizr();
						$("#sucaihuo_4").css("display","");
						$("#sucaihuo_1").css("display","none");
						$("#sucaihuo_3").css("display","none");
						$("#sucaihuo_2").css("display","none");
						$("#sucaihuo_5").css("display","none");
					}
					function click_5(){
						$("#sucaihuo_5").filterizr();
						$("#sucaihuo_5").css("display","");
						$("#sucaihuo_1").css("display","none");
						$("#sucaihuo_3").css("display","none");
						$("#sucaihuo_4").css("display","none");
						$("#sucaihuo_2").css("display","none");
					}
					$(function() {
						//$('.sucaihuo').filterizr();
					
						$('.nav li').on('click', function() {
							$(this).toggleClass('active').siblings().removeClass('active');
						});
					});
					
					function myfun()
					{
						$("#sucaihuo_1").filterizr();
						$("#sucaihuo_1").css("display","");
						$("#sucaihuo_2").css("display","none");
						$("#sucaihuo_3").css("display","none");
						$("#sucaihuo_4").css("display","none");
						$("#sucaihuo_5").css("display","none");
					}
					
					window.onload=myfun;
				</script>
	</div>
			<div style="width:20%;height:100%;float:left;">
				<ul class="nav">
					<li onclick="click_1()" class="shuffle-btn" data-shuffle="">7月24号</li>
					<li onclick="click_2()" class="shuffle-btn" data-shuffle="">7月23号</li>
					<li onclick="click_3()" class="shuffle-btn" data-shuffle="">7月22号</li>
					<li onclick="click_4()" class="shuffle-btn" data-shuffle="">7月21号</li>
					<li onclick="click_5()" class="shuffle-btn" data-shuffle="">7月20号</li>
					<!-- <li class="sort-btn" data-sortasc="">顺序</li>-->
					<!-- <li class="sort-btn" data-sortdesc="">倒序</li> -->
				</ul>
			</div>	
	<%-- <div style="bottom:0;">
		<jsp:include page="/jsp/statics/foot.jsp"></jsp:include>
	</div> --%>
	<!-- JS -->
	<script src="js/jquery.filterizr.js"></script>
	<!-- jQuery -->
	<script src="js/jquery-migrate-1.2.1.min.js"></script>
</body>
</html>