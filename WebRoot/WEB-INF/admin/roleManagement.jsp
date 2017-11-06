<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html lang="en">
	
<head>
		<title>角色管理</title><meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" href="css/maruti-style.css" />
		<link rel="stylesheet" href="css/maruti-media.css" class="skin-color" />	
</head>
<body>

<!-- 引入后台头模板-->
<jsp:include page="/static/admin_header.jsp"></jsp:include>

<!--close-top-Header-menu-->


		
		<div id="content" style="width:100%;height:100%;">
			<div id="content-header">
			<div id="breadcrumb">
				<a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
				<a href="#" class="current">Widgets</a>
			</div>
                <h1>角色管理</h1>
			</div>
			
						<div class="widget-box" style="width:50%; align: center;">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-eye-open"></i>
								</span>
								<h5>角色展示</h5>
							</div>
							<div class="widget-content nopadding">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th style="display: none;">角色ID</th>
											<th>角色名</th>
											<th>角色描述</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>

                                    <c:forEach items="${roleList}" var="v">
										<tr>
											 <td style="display: none;">${v.roleId}</td>
											 <td>${v.roleName}</td>
											 <td>${v.description}</td>

											 <td align="center">
												<i class="icon-edit"></i>
												<i class="icon-remove"></i>
											 </td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
								
							</div>
						</div>
	
						<div class="widget-box" style="width:50%; align: center;">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-arrow-right"></i>
								</span>
								<h5>增加角色</h5>
							</div>
							<div class="widget-content nopadding">
								新角色名：<input type="text" id="roleName">
								角色描述：<input type="text" id="roleDesc">
								<button class="btn btn-primary"  onclick="addRole()">新建角色</button>
							</div>
						</div>


		</div>
			
<script src="js/jquery.min.js"></script>
<script src="js/jquery.ui.custom.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/maruti.js"></script>

<script>

	/*增加角色*/
    function addRole(){
        var roleName = $("#roleName").val();
        var roleDesc = $("#roleDesc").val();

        var result = false;
        $.ajax({
            async : false,//将async设置为false,才能使得return 返回true/false有效
            type : 'post',
            url : 'addRole?roleName='+roleName,
            success : function(str){
                if('success'==str) {
                    location.reload(true);//重新加载
				} else {
                    alert("失败！！！");
				}


            }
        });
    }


</script>













</body>









</html>
