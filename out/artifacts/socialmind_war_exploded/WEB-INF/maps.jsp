<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8"> 
    <title>地域分布图</title>
	<link href="css/opensans.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="jqvmap/jqvmap.css" media="screen" rel="stylesheet" type="text/css" /> 
    <link href="css/templatemo-style.css" rel="stylesheet">
    <script type="text/javascript" src="http://api.map.baidu.com/api?key=3102CDD11B20B0B2D16C9E96995B98CE72DD22B4&v=1.1&services=true"></script> 

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
            <h2 class="margin-bottom-10">全国各省/市微博热词分布图</h2>
            <p class="margin-bottom-0">包括了中国34个省市自治区(包括香港、澳门、台湾)的微博热词信息，显示每个省市自治区排名前五的热词，点击每个省市自治区可查看相关热词，点击每个热词可擦看相关的微博列表。</p>              
          </div>
          <div class="templatemo-flex-row flex-content-row">
            <div class="col-1">              
              <div class="panel panel-default margin-10">
                <div class="panel-heading"><h2>热词区域分布图</h2></div>
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
					 var datas = new Array("湖南-#ff0000","湖北-#ff5500","江西-#ffff00","重庆-#00ff00","贵州-#00ff55","广西-#C8C1E3","广东-#FBC5DC","云南-#DBEDC7","福建-#FEFCBF","浙江-#C8C1E3","安徽-#FBC5DC","河南-#DBECC8","江苏-#DBECC8","四川-#FCFBBB","海南省-#FCFBBB","山东-#FCFBBB","辽宁-#FCFBBB","新疆-#FCFBBB","西藏-#E7CCAF","陕西-#E7CCAF","河北-#E7CCAF","黑龙江-#E7CCAF","宁夏-#FBC5DC","内蒙古自治区-#DBEDC7","青海-#DBEDC7","甘肃-#C8C1E3","山西-#FBC5DC","吉林省-#C8C1E3","北京-#FBC5DC","天津-#C8C1E3","澳门-#78090","上海-#FCFBBB","香港-#C8C1E3","台湾-#C8C1E3"); 
					 var dataHotWords = new Array(
					 "<center><b>湖南 热词排行</b></center>"
					 +"<c:forEach items='${keywords_hubei}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 
					 "<center><b>湖北 热词排行</b></center>"
					 +"<c:forEach items='${keywords_hubei}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 
					 "<center><b>江西 热词排行</b></center>"
					 +"<c:forEach items='${keywords_jiangxi}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 
					 "<center><b>重庆 热词排行</b></center>"
					 +"<c:forEach items='${keywords_chongqing}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 
					 "<center><b>贵州 热词排行</b></center>"
					 +"<c:forEach items='${keywords_guizhou}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>广西 热词排行</b></center>"
					 +"<c:forEach items='${keywords_guangxi}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>广东 热词排行</b></center>"
					 +"<c:forEach items='${keywords_guangdong}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>云南 热词排行</b></center>"
					 +"<c:forEach items='${keywords_yunnan}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>福建 热词排行</b></center>"
					 +"<c:forEach items='${keywords_fujian}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>浙江 热词排行</b></center>"
					 +"<c:forEach items='${keywords_zhejiang}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>安徽 热词排行</b></center>"
					 +"<c:forEach items='${keywords_anhui}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>河南 热词排行</b></center>"
					 +"<c:forEach items='${keywords_henan}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>江苏 热词排行</b></center>"
					 +"<c:forEach items='${keywords_jiangsu}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>四川 热词排行</b></center>"
					 +"<c:forEach items='${keywords_sichuan}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>海南 热词排行</b></center>"
					 +"<c:forEach items='${keywords_hainan}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>山东 热词排行</b></center>"
					 +"<c:forEach items='${keywords_shandong}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>辽宁 热词排行</b></center>"
					 +"<c:forEach items='${keywords_liaoning}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>新疆 热词排行</b></center>"
					 +"<c:forEach items='${keywords_xinjiang}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>西藏 热词排行</b></center>"
					 +"<c:forEach items='${keywords_xizang}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>陕西 热词排行</b></center>"
					 +"<c:forEach items='${keywords_shananxi}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>河北 热词排行</b></center>"
					 +"<c:forEach items='${keywords_hebei}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>黑龙江 热词排行</b></center>"
					 +"<c:forEach items='${keywords_heilongjiang}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>宁夏 热词排行</b></center>"
					 +"<c:forEach items='${keywords_ningxia}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>内蒙古 热词排行</b></center>"
					 +"<c:forEach items='${keywords_neimenggu}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>青海 热词排行</b></center>"
					 +"<c:forEach items='${keywords_qinghai}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>甘肃 热词排行</b></center>"
					 +"<c:forEach items='${keywords_gansu}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>山西 热词排行</b></center>"
					 +"<c:forEach items='${keywords_shanxi}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>吉林 热词排行</b></center>"
					 +"<c:forEach items='${keywords_jilin}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>北京 热词排行</b></center>"
					 +"<c:forEach items='${keywords_beijing}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>天津 热词排行</b></center>"
					 +"<c:forEach items='${keywords_tianjin}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>澳门 热词排行</b></center>"
					 +"<c:forEach items='${keywords_aomen}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>上海 热词排行</b></center>"
					 +"<c:forEach items='${keywords_shanghai}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>香港 热词排行</b></center>"
					 +"<c:forEach items='${keywords_xianggang}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>",
					 "<center><b>台湾 热词排行</b></center>"
					 +"<c:forEach items='${keywords_taiwan}' var='k' varStatus='s'>"
					 +"<a href='<%=path%>/weibolists?kwid=${k.kw_id}'>${s.index+1}.${k.kw_name}\t热度:${k.kw_hotDegree}</a></br></c:forEach>");
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