<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html lang="en">
	
<head>
	<base href="<%=basePath%>">
	<title>信息可信度标注</title><meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="admin/css/bootstrap.min.css" />
	<link rel="stylesheet" href="admin/css/bootstrap-responsive.min.css" />
	<link rel="stylesheet" href="admin/css/maruti-style.css" />
	<link rel="stylesheet" href="admin/css/maruti-media.css" class="skin-color" />
</head>
<body>

<!-- 引入后台头模板-->
<jsp:include page="/static/admin_header.jsp"></jsp:include>
<!--close-top-Header-menu-->
		<div id="content" style="width:100%;height:100%;" align="center">
			<div id="content-header">
			<div id="breadcrumb">
				<a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
				<a href="#" class="current">Widgets</a>
			</div>
                <h1>信息可信度标注</h1>
			</div>
			
			<div class="widget-box" style="width:50%; align: center;">
				<div class="widget-title">
					<span class="icon">
						<i class="icon-eye-open"></i>
					</span>
					<h5>事件列表</h5>
				</div>
				<div class="widget-content nopadding">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>序号</th>
								<th>事件名称</th>
								<th>事件简介</th>
								<th>可信度</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${objects}" var="o" varStatus="sts">
							<tr>
								<td>${sts.index + 1}</td>
								<td><a href="<%=basePath%>label/essaysList?objectId=${o.objectId}" style="color: #0000FF;">${o.name}</a></td>
								<td>${o.introduction}</td>
								<td>${o.credibility}</td>
							</tr>
						</c:forEach>

						</tbody>
					</table>

				</div>
			</div>

			<div class="widget-box" style="width:50%; align: center;">
				<div class="widget-title">
								<span class="icon">
									<i class="icon-eye-open"></i>
								</span>
					<h5>事件库全局各网站信息</h5>
				</div>
				<div class="widget-content nopadding">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th style="">序号</th>
								<th>网站名</th>
								<th>事件数量</th>
								<th>非谣言事件数量</th>
								<th>文章数量</th>
							</tr>
						</thead>

						<tbody>

						</tbody>
					</table>
				</div>
			</div>
		</div>




<!-- Modal -->
<div class="modal hide fade" id="myModal" tabindex="-1" role="dialog">
	<div class="modal-header"><button class="close" type="button" data-dismiss="modal">×</button>
		<h3 id="myModalLabel">修改角色</h3>
	</div>
	<div class="modal-body">

		<input type="text"  style="display: none;" id="id" ><br/>
		角色名：<input type="text" id="roleNewName" ><br/>
		描  述：<input type="text" id="roleNewDesc" >
	</div>

	<div class="modal-footer">

		<a href="#" class="btn btn-primary"  onclick="updateRole()" >保存</a>
		<a href="#" class="btn">取消</a>

	</div>
</div>


<!-- 引入后台尾部模板 -->
<jsp:include page="/static/admin_footer.jsp"></jsp:include>

			
<script src="admin/js/jquery.min.js"></script>
<script src="admin/js/jquery.ui.custom.js"></script>
<script src="admin/js/bootstrap.min.js"></script>
<script src="admin/js/maruti.js"></script>

<script>
	/*增加角色*/
    function addRole(){
        var roleName = $("#roleName").val();
        var roleDesc = $("#roleDesc").val();

        var result = false;
        $.ajax({
            async : false,//将async设置为false,才能使得return 返回true/false有效
            type : 'post',
            url : 'addRole?roleName='+roleName+'&roleDesc='+roleDesc,
            success : function(str){
                if('success'==str) {
                    location.reload(true);//重新加载
				} else {
                    alert("失败！！！");
				}
            }
        });
    }

	/*弹出修改角色*/
    function popModel(roleId,roelName,roleDesc){

        document.getElementById("id").value=roleId;
        document.getElementById("roleNewName").value=roelName;
        document.getElementById("roleNewDesc").value=roleDesc;

        $('#myModal').modal('show');

    }

	/*弹出修改角色*/
    function updateRole(){
        var id = $("#id").val();
        var roleNewName = $("#roleNewName").val();
        var roleNewDesc = $("#roleNewDesc").val();

        var result = false;
        $.ajax({
            async : false,//将async设置为false,才能使得return 返回true/false有效
            type : 'post',
            url : 'updateRole?roleNewName='+roleNewName+'&roleNewDesc='+roleNewDesc+'&id='+ id,
            success : function(str){
                if('success'==str) {
                    location.reload(true);//重新加载
                } else {
                    alert("失败！！！");
                }
            }
        });

    }

	/*删除角色*/
    function deleteRole(roleId) {

        var result = false;
        if (confirm("确定删除吗 ?") == false) {
            return;
        }

        $.ajax({
            async : false,
            type : 'post',
            url : 'deleteRole?roleId=' + roleId,
            success : function(msg) {
                if (msg == 'success') {
                    alert("删除成功");
                    window.location.reload();
                    result = true;
                } else {
                    alert("删除失败");
                    result = false;
                }
            }
        });
        return result;
    }












</script>





</body>

</html>
