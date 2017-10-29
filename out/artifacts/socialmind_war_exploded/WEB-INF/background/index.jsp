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
    <title>首页 - 微博舆情分析系统</title>
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/templatemo-style.css" rel="stylesheet">

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
                  
          <div class="templatemo-flex-row flex-content-row">
            
             
            <div class="col-1">              
                      
              <div class="templatemo-content-widget blue-bg">
                
                <h1 class="text-uppercase margin-bottom-10" align="center">MANAGE SELECT</h1>
                <p class="margin-bottom-0"></p>                
              </div>            
            </div>                       
          </div> <!-- Second row ends -->
          <div class="templatemo-flex-row flex-content-row">

            <div class="templatemo-content-widget white-bg col-2">
             
              <div class="media margin-bottom-30">
                <div class="media-left padding-right-25">
                  <a href="/weibomonitor/background/manageusers">
                    <img class="media-object img-circle templatemo-img-bordered" src="images/user.png" alt="Sunset">
                  </a>
                </div>
                <div class="media-body">
                  <h2 class="media-heading text-uppercase blue-text">User</h2>
                  
                </div>        
              </div>
              <div class="table-responsive">
                <table class="table">
                  <tbody>
                    <tr>
                      <td><div class="circle green-bg"></div></td>
                      <td>The Number Of User</td>
                      <td>02</td>                    
                    </tr> 
                                               
                  </tbody>
                </table>
              </div>   
            </div>


         
            
			    <div class="templatemo-content-widget white-bg col-2">
            
              <div class="media margin-bottom-30">
                <div class="media-left padding-right-25">
                  <a href="/weibomonitor/background/manager">
                    <img class="media-object img-circle templatemo-img-bordered" src="images/manager.png" alt="Sunset" >
                  </a>
                </div>
                <div class="media-body">
                  <h2 class="media-heading text-uppercase blue-text">Manager</h2>
                  
                </div>        
              </div>
              <div class="table-responsive">
                <table class="table">
                  <tbody>
                    <tr>
                      <td><div class="circle green-bg"></div></td>
                      <td>The Number Of Manager</td>
                      <td>02</td>                    
                    </tr> 
                                                       
                  </tbody>
                </table>
              </div>   
            </div>


          </div>
             <jsp:include page="/jsp/statics/foot.jsp"/>
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