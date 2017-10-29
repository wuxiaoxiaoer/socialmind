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
    <title>搜索 - 微博舆情分析</title>
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
        
        <div style="padding:50px 80px;margin:auto 0;width:100%;height:50px;">
           	<form action="so/1" method="post">
           		<input type="hidden" name="token" value="${token}"/>
            	<span><input id="kw_name" name="kw_name" type="text" value="${kw_name}" style="border-radius:0px;height:38px;width:60%;float:left;" class="form-control" id="inputEmail" placeholder="输入关键词"></span>
            	<span><button type="submit" style="border-radius:0px;" class="templatemo-blue-button">搜 索</button></span>
            	
				<div class="col-lg-12 form-group" style="margin-top:5px;">                   
                    <div class="margin-right-15 templatemo-inline-block">
                      <input type="checkbox" name="server" id="c3" value="" onclick="getProvince();">
                      <label for="c3" class="font-weight-400"><span style="width:21px;height:21px;"></span>地区</label>
                      <label for="c3" class="font-weight-400">
					  	<select name="kw_province" id="select_province" class="form-control" style="width:80px;height:30px;display:none; border-radius:0; font-size: 10px;">
		                    <option value="">默认</option>
		                    <option value="北京">北京</option>
                            <option value="浙江">浙江</option>
                            <option value="天津">天津</option>
                            <option value="安徽">安徽</option>
                            <option value="上海">上海</option>
                            <option value="福建">福建</option>
                            <option value="重庆">重庆</option>
                            <option value="江西">江西</option>
                            <option value="山东">山东</option>
                            <option value="河南">河南</option>
                            <option value="湖北">湖北</option>
                            <option value="湖南">湖南</option>
                            <option value="广东">广东</option>
                            <option value="海南">海南</option>
                            <option value="山西">山西</option>
                            <option value="青海">青海</option>
                            <option value="江苏">江苏</option>
                            <option value="辽宁">辽宁</option>
                            <option value="吉林">吉林</option>
                            <option value="台湾">台湾</option>
                            <option value="河北">河北</option>
                            <option value="贵州">贵州</option>
                            <option value="四川">四川</option>
                            <option value="云南">云南</option>
                            <option value="陕西">陕西</option>
                            <option value="甘肃">甘肃</option>
                            <option value="黑龙江">黑龙江</option>
                            <option value="香港">香港</option>
                            <option value="澳门">澳门</option>
                            <option value="广西">广西</option>
                            <option value="宁夏">宁夏</option>
                            <option value="新疆">新疆</option>
                            <option value="内蒙古">内蒙古</option>
                            <option value="西藏">西藏</option>                      
		                </select>
					  </label>
                    </div>
                    <div class="margin-right-15 templatemo-inline-block">                      
                      <input type="checkbox" name="member" id="c4" value="" onclick="getDate();">
                      <label for="c4" class="font-weight-400"><span style="width:21px;height:21px;"></span>时间</label>
                       <span id="dateID" style="display: none">
                       	 <input name="kw_DateBegin" type="text" style="width:82px;height:30px;" value="" id="datepicker">
	                     <label>-</label>
	                     <input name="kw_DateEnd" type="text" style="width:82px;height:30px;" value="" id="datepicker2">
	                   </span>
                    </div>
                </div>
           	</form>
        </div>
        <script type="text/javascript">
        	function getProvince(){
        		var checkbox = document.getElementById("c3");
        		if(!checkbox.checked){
        			document.getElementById("select_province").style.display="none";
        		}else{
        			document.getElementById("select_province").style.display="";
        		}
        	}
        	function getDate(){
        		var checkbox = document.getElementById("c4");
        		if(!checkbox.checked){
        			document.getElementById("dateID").style.display="none";
        		}else{
        			document.getElementById("dateID").style.display="";
        		}
        	}
        </script>
           
	<script type="text/javascript" src="js/moment.min.js"></script> 
	<script type="text/javascript" src="js/pikaday.js"></script>
        <script>
		   var picker = new Pikaday(
		    {
		        field: document.getElementById('datepicker'),
		        firstDay: 1,
		        minDate: new Date('2000-01-01'),
		        maxDate: new Date('2020-12-31'),
		        yearRange: [2000,2020],
		        onSelect: function() {
		            var date = document.createTextNode(this.getMoment().format('Do MMMM YYYY') + ' ');
		        }
		    });
	    	
	    	var picker2 = new Pikaday(
		    {
		        field: document.getElementById('datepicker2'),
		        firstDay: 1,
		        minDate: new Date('2000-01-01'),
		        maxDate: new Date('2020-12-31'),
		        yearRange: [2000,2020],
		        onSelect: function() {
		            var date = document.createTextNode(this.getMoment().format('Do MMMM YYYY') + ' ');
		        }
		    });
	    	picker.setMoment(moment().dayOfYear(366));
		    picker2.setMoment(moment().dayOfYear(366));
	    </script>
        
        
        <div class="templatemo-content-container">
                   
          <div class="templatemo-flex-row flex-content-row">
            <div class="col-1">
              <div class="panel panel-default margin-10">
              </div>              
            </div>                      
          </div> <!-- Second row ends -->
          <c:forEach items="${page.result}" var = "w" varStatus="s">
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
          
          <%-- <div id="getMoreWeiboContent">
          
          <div class="templatemo-flex-row flex-content-row">
          
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
            
          </div>
          
          </div> --%>
          <!-- <button type="button" onclick="loadMoreInfo()" style="border-radius:0px;width:100%;" class="templatemo-blue-button">加载更多...</button> -->
          
          <script type="text/javascript">
          /* 	var flag = 0;
          	function loadMoreInfo(){
          		var kw_province = $("#select_province").val();
          		var kw_DateBegin = $("#datepicker").val();
          		var kw_DateEnd = $("#datepicker2").val();
          		var kw_name = $("#kw_name").val();
          		if(kw_province == ''){
          			kw_province = "%";
          		}
          		alert(flag+":"+kw_province+":"+kw_DateBegin+":"+kw_DateEnd+":"+kw_name);
          		$.ajax({
          			type : 'post',
          			url : 'somore/'+flag+'?kw_province='+kw_province+'&kw_DateBegin='+kw_DateBegin+'&kw_DateEnd='+kw_DateEnd+'&kw_name='+kw_name,
          			dataType : 'json',
          			success : function(msg){
          				$.each(msg ,function(index, item){
          				alert(index);
          					if(index % 5 == 0){
          						$("#getMoreWeiboContent").append('<div><div class="templatemo-flex-row flex-content-row">'+index);
          					}
          					
          					$("#getMoreWeiboContent").append('<div name="unit" class="templatemo-content-widget white-bg col-1 text-center templatemo-position-relative">'
              						+'<i class="fa fa-times"></i>'
             						+'<img src="images/demo/rmrb.jpg" alt="Bicycle" class="img-circle img-thumbnail margin-bottom-30">'
              						+'<h2 class="text-uppercase blue-text margin-bottom-5">'+item.wb_createdAt+'</h2>'
              						+'<h3 style="height:auto;font-size: 12px;">'+item.wb_content+'</h3>'
              						+'<h4 style="float:right;height:40px;font-size: 12px;"><a target="_blank" href="'+item.wb_originalLink+'"><i style="background-image:url("images/icon-link.png");width:16px;height:16px;display: inline-block"></i><b>原文链接</b></a></h4>'
             						+'<div class="templatemo-social-icons-container">'
                					+'<div class="social-icon-wrap">'
                  					+'<i>阅读量'+item.wb_readNum+'</i>'
                					+'</div>'
                					+'<div class="social-icon-wrap">'
                  					+'<i>转载量 '+item.wb_forwardNum+'</i>'  
                					+'</div>'
                					+'<div class="social-icon-wrap">'
                  					+'<i>评论量 '+item.wb_commentsNum+'</i>'  
                					+'</div>'
                					+'<div class="social-icon-wrap">'
                  					+'<i>点赞量 '+item.wb_likeNum+'</i>'  
                					+'</div>'            
              						+'</div>');
            						
            				if(index % 5 == 4 || index == 9){
          						$("#getMoreWeiboContent").append(index+'</div></div>');
          					}         					
          				});
          			}
          		});
          		flag ++;
          	} */
          
          </script>
          
          <input type="hidden" id="hid_kw_name" value="${kw_name}"/>
          <input type="hidden" id="kw_DateBegin" value="${kw_DateBegin}"/>
          <input type="hidden" id="kw_DateEnd" value="${kw_DateEnd}"/>
          <input type="hidden" id="kw_province" value="${kw_province}"/>
          
           <div id="unit_pageno" name="unit" class="pagination-wrap">
            <ul class="pagination">
              <li><a href="javascript:void(0)" onclick="skipPage(1)">1</a></li>
              <li><a href="javascript:void(0)" onclick="skipPage(2)">2</a></li>
              <li class="active"><a href="javascript:void(0)" onclick="skipPage(3)">3 <span class="sr-only">(current)</span></a></li>
              <li><a href="javascript:void(0)" onclick="skipPage(4)">4</a></li>
              <li><a href="javascript:void(0)" onclick="skipPage(5)">5</a></li>
              <li>
                <a href="javascript:void(0)" onclick="skipPage(${page.pageNo+1})" aria-label="Next">
                  <span aria-hidden="true"><i class="fa fa-play"></i></span>
                </a>
              </li>
            </ul>            
          </div> 
          <script type="text/javascript">
          
          function skipPage(pageNo){
          
          		var kw_name = $("#hid_kw_name").val();
          		var kw_DateBegin = $("#kw_DateBegin").val();
          		var kw_DateEnd = $("#kw_DateEnd").val();
          		var kw_province = $("#kw_province").val();
          		
          		window.location.href = "<%=path%>/so/"+pageNo+"?kw_name="+kw_name+"&kw_province="+kw_province+"&kw_DateBegin="+kw_DateBegin+"&kw_DateEnd="+kw_DateEnd;
          }
          
          
          	<%-- function nextPage(){
          		var str = window.location.href;
          		var arr = str.split('=');
          		var pno = parseInt(arr[1]);
          		window.location.href = "<%=path%>/searchsPage?pageNo="+(pno+1);		
          	} --%>
          </script>
          <jsp:include page="/jsp/statics/foot.jsp"/>
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