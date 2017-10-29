<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String[] color_pro_arr =null;
if(request.getAttribute("colorArray")!=null){
	color_pro_arr = (String[])request.getAttribute("colorArray");
}
%>
<!DOCTYPE html>
<html lang="en">
  <head>
  	<base href="<%=basePath%>">
    <meta charset="utf-8">
    <title>特定关键词地图</title>
	<link href="css/opensans.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="jqvmap/jqvmap.css" media="screen" rel="stylesheet" type="text/css" /> 
    <link href="css/templatemo-style.css" rel="stylesheet">
    <script type="text/javascript" src="http://api.map.baidu.com/api?key=3102CDD11B20B0B2D16C9E96995B98CE72DD22B4&v=1.1&services=true"></script> 
<script type="text/javascript">
	var datas = new Array();
</script>
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
        <div class="templatemo-content-container">
        	<div class="templatemo-content-widget white-bg">
	        	<form action="keywords" method="post">
	            	<span><input name="kw_name" type="text" style="border-radius:0px;height:38px;width:60%;float:left;" class="form-control" id="inputEmail" placeholder="输入关键词"></span>
	            	<span><button type="submit" style="border-radius:0px;" class="templatemo-blue-button">搜 索</button></span>
	            </form>
            	<p class="margin-bottom-0"> 关于本关键词在中国34个省市自治区(包括香港、澳门、台湾)中的热度排行.
        		</p>
        	
            </div>
          <div class="templatemo-flex-row flex-content-row">
            <div class="col-1">              
              <div class="panel panel-default margin-10">
                <div class="panel-heading"><h2>特定关键词地图</h2></div>
                <div class="panel-body">
                   <div id="container" style="height:600px;" class="vmap"></div>
                    <script type="text/javascript"> 
					    if (typeof console == "undefined"){   
					         window.console = {log: function(){}}; 
					      } 
					     var map = new BMap.Map("container"); 
					     map.centerAndZoom(new BMap.Point(114.403765, 37.914850), 5); 
					     var stdMapCtrl = new BMap.NavigationControl({type:BMAP_NAVIGATION_CONTROL_SMALL}) ;
					      map.addControl(stdMapCtrl); 
					      map.enableScrollWheelZoom();  
					      map.enableContinuousZoom();  
					
					      function getBoundary(data,HotWords){    
								var bdary = new BMap.Boundary();   
								bdary.get(data.split("-")[0], function(rs){      
								console.log(rs);        
								var bounds;      
								var maxNum = -1, maxPly;   
								var count = rs.boundaries.length;
								var	color=data.split("-")[1];
								var name=data.split("-")[0];
								var opts = { 
					                width : 200,     // 信息窗口宽度 
					                height: 100,     // 信息窗口高度 
					                title : "微博热搜词"  // 信息窗口标题 
					            }
								for(var i = 0; i < count; i++){          
									var ply = new BMap.Polygon(rs.boundaries[i], {strokeWeight: 1, strokeOpacity:0.5,fillColor:data.split("-")[1],strokeColor: "#000000"});   
									map.addOverlay(ply);  
									ply.addEventListener("click",function(e){
										var latlng=e.point;
								
										var infoWindow = new BMap.InfoWindow("World", opts);  // 创建信息窗口对象 
										map.openInfoWindow(infoWindow,latlng);
										//var info=new BMap.InfoWindow("省份："+name+" "+latlng.lat+","+latlng.lng,opts);
										//"<a href='#'>"+"1.土耳其政变"+"</a></br>"+"<a href='#'>"+"2.人民日报谈2.5天休假"+"</a>"+"<a href='#'>"+"3."+"</a>"		
										var info=new BMap.InfoWindow(HotWords);	 	
										map.openInfoWindow(info,latlng);
									
										//高亮闪烁显示鼠标点击的省
										delay = 0;
										for (flashTimes = 0; flashTimes < 3; flashTimes++) {
									        delay += 200;
									        setTimeout(function () {
									            ply.setFillColor("#FFFF00");
									        }, delay);
										delay += 200;
										setTimeout(function () {
									            ply.setFillColor(color);
									        }, delay);
										}
									});
								var arrPts = ply.getPoints();        
									if(arrPts.length > maxNum){            
										maxNum = arrPts.length;             
										maxPly = ply;           
									}      
								}		        
						         
					  }); 
					 }
					 map.clearOverlays();
					 if(${fn:length(colorArray)}==0){
					 	datas = new Array("湖北-#F8D007","湖南-#FFD700","江西-#F1C90E","重庆-#EAC215","贵州-#E3BB1C","广西-#DCB423","广东-#D5AD2A","云南-#CEA62A","福建-#C79F2A","浙江-#C0982A","安徽-#B9912A","河南-#B28A2A","江苏-#AB832A","四川-#A57C2A","海南省-#A5752A","山东-#A56E2A","辽宁-#A5672A","新疆-#A5602A","西藏-#A5602A","陕西-#A5592A","河北-#A5522A","黑龙江-#A54B2A","宁夏-#A5442A","内蒙古自治区-#A53D2A","青海-#A5362A","甘肃-#A52F2A","山西-#A52A2A","吉林省-#A52A2A","北京-#A52A2A","天津-#A52A2A","澳门-#A52A2A","上海-#A52A2A","香港-#A52A2A","台湾-#A52A2A"); 
					  }else{
					 	<%
					 		if(color_pro_arr!=null){
					 			for(int i = 0; i<color_pro_arr.length;i++){ 
					 	%>
					 			datas[<%=i%>] = "<%=color_pro_arr[i]%>";
					 	<%}}%>
					 } 
					 /* if(${fn:length(colorArray)}==0){
					 	datas = new Array("湖北-#F8D007","湖南-#FFD700","江西-#F1C90E","重庆-#EAC215","贵州-#E3BB1C","广西-#DCB423","广东-#D5AD2A","云南-#CEA62A","福建-#C79F2A","浙江-#C0982A","安徽-#B9912A","河南-#B28A2A","江苏-#AB832A","四川-#A57C2A","海南省-#A5752A","山东-#A56E2A","辽宁-#A5672A","新疆-#A5602A","西藏-#A5602A","陕西-#A5592A","河北-#A5522A","黑龙江-#A54B2A","宁夏-#A5442A","内蒙古自治区-#A53D2A","青海-#A5362A","甘肃-#A52F2A","山西-#A52A2A","吉林省-#A52A2A","北京-#A52A2A","天津-#A52A2A","澳门-#A52A2A","上海-#A52A2A","香港-#A52A2A","台湾-#A52A2A"); 
					 }else{
						 for(var i = 0;i < ${fn:length(colorArray)};i++){
						 	datas[i] = '${colorArray[i]}';
						 }				 	
					 } */
					 var dataHotWords = new Array(
					 "<c:forEach items='${keywords}' var='k'>"
					 +"${k.kw_province}:${k.kw_name}<br/>"+"热度:${k.kw_hotDegree}<br>相关微博:<br/><a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${fn:length(k.weibos)}条</a>",
					 +"</c:forEach>"
					 //"湖南：中国不接受不承认所谓南海仲裁</br>"+"热度：45</br>"+"<a href='#'>"+"相关微博:3455条"+"</a>"
					 
					 /* "湖北-#ff5500",
					 "江西-#ffff00","重庆-#00ff00",
					 "贵州-#00ff55","广西-#C8C1E3",
					 "广东-#FBC5DC","云南-#DBEDC7",
					 "福建-#FEFCBF","浙江-#C8C1E3",
					 "安徽-#FBC5DC","河南-#DBECC8",
					 "江苏-#DBECC8","四川-#FCFBBB",
					 "海南省-#FCFBBB","山东-#FCFBBB",
					 "辽宁-#FCFBBB","新疆-#FCFBBB",
					 "西藏-#E7CCAF","陕西-#E7CCAF",
					 "河北-#E7CCAF","黑龙江-#E7CCAF",
					 "宁夏-#FBC5DC","内蒙古自治区-#DBEDC7",
					 "青海-#DBEDC7","甘肃-#C8C1E3",
					 "山西-#FBC5DC","吉林省-#C8C1E3",
					 "北京-#FBC5DC","天津-#C8C1E3",
					 "澳门-#78090","上海-#FCFBBB",
					 "香港-#C8C1E3","台湾-#C8C1E3" */
					 );
					 for(var i=0;i<datas.length;i++){   
						getBoundary(datas[i],dataHotWords[i]);
					 }
					  
					 function showInfo(e){
					 var latlng = e.point;
					    var info = new BMap.InfoWindow(" " + latlng.lat + "," + latlng.lng, {width:220});
					    map.openInfoWindow(info, latlng);      
					}
					//map.addEventListener("click", showInfo);
					map.disableDoubleClickZoom();
					</script>
                </div>
              </div>
            </div>
          </div>
          <jsp:include page="/jsp/statics/foot.jsp"></jsp:include> 		  			
          </div>           
        </div>
      </div>
    
  </body>
</html>