<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
  <head>
  	<base href="<%=basePath%>"/>
    <meta charset="utf-8"> 
    <title>Visual Admin Dashboard - Manage Users</title>
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/templatemo-style.css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet"/>
    <link href="css/style.css" rel="stylesheet"/>
	<script text="text/javascript">
			function show(tag){
	            var light=document.getElementById(tag);
	            var fade=document.getElementById('fade');
	            light.style.display='block';
	            fade.style.display='block';
	        }
	        function hide(tag){
	            var light=document.getElementById(tag);
	            var fade=document.getElementById('fade');
	            light.style.display='none';
	            fade.style.display='none';
	        }
	        window.onload=function(){
	        	var flag =document.getElementsByName("u_sex");
	        	if(flag=='true'){
	        		document.getElementsByName("u_sex")[1].checked='checked';
	        	}
	        	else{
	        		document.getElementsByName("u_sex")[0].checked='checked';
	        	} 
	        	
	        }
		</script>
  </head>
  <body> 
    <!-- Left column -->
    <div class="templatemo-flex-row">
      <jsp:include page="/jsp/back_static/left.jsp"/>
      <!-- Main content --> 
      <div class="templatemo-content col-1 light-gray-bg">
        <div class="templatemo-top-nav-container">
          <jsp:include page="/jsp/statics/header.jsp"/>
        </div>
        <div class="templatemo-content-container">
          <div class="templatemo-content-widget no-padding">
            <div class="panel panel-default table-responsive">
               <div class="templatemo-content-widget white-bg">
               <form action="background/userSearch" method="post">
                  <span><input name="search_userKey" type="text" style="border-radius:0px;height:38px;width:60%;float:left;" class="form-control" id="inputEmail" placeholder="输入关键词"></span>
                   <span><button type="submit" style="border-radius:0px;" class="templatemo-blue-button">搜 索</button></span>
                </form>
                </div>  
              <table class="table table-striped table-bordered templatemo-user-table">
              <thead>
                  <tr>
                    <td><a href="" class="white-text templatemo-sort-by">no. <span class="caret"></span></a></td>
                    <td><a href="" class="white-text templatemo-sort-by">name<span class="caret"></span></a></td>
					<td><a href="" class="white-text templatemo-sort-by">telphone<span class="caret"></span></a></td>
                    <td><a href="" class="white-text templatemo-sort-by">mail<span class="caret"></span></a></td>
                    <td><a href="" class="white-text templatemo-sort-by">sex<span class="caret"></span></a></td>
                    <td colspan="2">Manage</td>
                  </tr>
                  </thead>
                  <tbody>
                  
	       <c:forEach items="${page.result}" var="c" varStatus="status">
                <tr>
                <td>${status.index+1}</td>
                <td>${c.u_name}</td>
                <td>${c.u_telphone}</td>
                <td>${c.u_mail}</td>
                <td>
                <c:if test="${c.u_sex==true }">女</c:if>
                <c:if test="${c.u_sex==false }">男</c:if>
                </td>
                <td><a href="javascript:void(0)" onclick="show('light${status.index+1}')" class="templatemo-edit-btn">edit</a></td>
                <td><a href="<%=path%>/background/deleteUser?id=${c.u_id}" class="templatemo-edit-btn">delete</a></td>
                </tr>
<form action="background/editUser" method="post">   

<input type="hidden" name="pageNo" value="1"/>   
  
<div id="light${status.index+1}" class="white_content" style="border:0px;height:420px;">
    <div class="close"><a href="javascript:void(0)" onclick="hide('light${status.index+1}')"> <img title="关闭" src="images/close.png" style="padding: 0px; margin: 0px; border: 0px;"></a></div>
    <div class="con">
    	<center><h3>修改用户信息</h3></center>
    	<input type="hidden" name="u_id" value="${c.u_id}"/>
        <div class="form-group">
      		<div class="input-group">
       		<div class="input-group-addon" style="padding:6px 15px;"><i class="fa fa-user fa-fw" style="float:left;margin-left:-30px;"></i></div>	        		
             	<input id="u_name" name="u_name" type="text" value="${c.u_name}" onblur="return checkUser()" class="form-control" placeholder="username">     
         	</div>
      	</div>
      	 <div class="form-group">
      		<div class="input-group">
       		<div class="input-group-addon" style="padding:6px 15px;"><i class="fa fa-key fa-fw" style="float:left;margin-left:-30px;"></i></div>	        		
             	<input id="u_pwd" name="u_pwd" type="password" value="${c.u_pwd}" onblur="return checkUser()" class="form-control" placeholder="****">     
         	</div>
      	</div>      	
      	<div class="form-group">
      		<div class="input-group">
       		<div class="input-group-addon" style="padding:6px 15px;"><i class="fa fa-telphone fa-fw" style="float:left;margin-left:-30px;"></i></div>	        		
             	<input id="u_telphone" name="u_telphone" type="text" value="${c.u_telphone}" onblur="return checkUser()" class="form-control" placeholder="telephone">     
         	</div>
      	</div>
      	<div class="form-group">
      		<div class="input-group">
       		<div class="input-group-addon" style="padding:6px 15px;"><i class="fa fa-mail fa-fw" style="float:left;margin-left:-30px;"></i></div>	        		
             	<input id="u_mail" name="u_mail" type="text" value="${c.u_mail}" onblur="return checkUser()" class="form-control" placeholder="email">     
         	</div>
      	</div>
      	
         <div class="margin-right-15 templatemo-inline-block">
           <input id="female${status.index+1}" type="radio" name="u_sex" value="true" checked="">
           <label for="female${status.index+1}" class="font-weight-400"><span></span>女</label>
         </div>
         <div class="margin-right-15 templatemo-inline-block">
            <input id="male${status.index+1}" type="radio" name="u_sex" value="false" checked="">
            <label for="male${status.index+1}" class="font-weight-400"><span></span>男</label>
          </div>
          
        <button type="submit" class="templatemo-blue-button width-100">修 改</button>
    </div>
</div>
</form>
      </c:forEach>   
      </tbody>  
<div id="fade" class="black_overlay" style=""></div>
          <!-- Second row ends -->        
        </div>
      </div>
    </div>
    </table> 
       <div class="pagination-wrap">
            <ul class="pagination">
              <li><a href="#">1</a></li>
              <li><a href="#">2</a></li>
              <li class="active"><a href="#">3 <span class="sr-only">(current)</span></a></li>
              <li><a href="#">4</a></li>
              <li><a href="#">5</a></li>
              <li>
                <a href="#" aria-label="Next">
                  <span aria-hidden="true"><i class="fa fa-play"></i></span>
                </a>
              </li>
            </ul>
          </div>          
         <jsp:include page="/jsp/statics/foot.jsp"/>
    <!-- JS -->
    <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
    <script type="text/javascript" src="js/templatemo-script.js"></script>      <!-- Templatemo Script -->
    <script type="text/javascript" src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
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