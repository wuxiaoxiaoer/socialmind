<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html lang="en">
	
<head>
		<title>事件统计分析</title><meta charset="UTF-8" />
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
		<div id="content" style="width:100%;height:100%;">
			<div id="content-header">
			<div id="breadcrumb">
				<a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
				<a href="#" class="current">Widgets</a>
			</div>
                <h1>事件信息统计</h1>
			</div>
			
			<div class="widget-box" style="width:50%; align: center;">
				<div class="widget-title">
					<span class="icon">
						<i class="icon-eye-open"></i>
					</span>
					<h5>事件库总体信息</h5>
				</div>
				<div class="widget-content nopadding">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>总事件数量</th>
								<th>非谣言事件数量</th>
								<th>谣言事件数量</th>
								<th>文章数量</th>
								<th>作者数量</th>
								<th>评论数量</th>
							</tr>
						</thead>

						<tbody>
							<tr>
								<td onclick="getAll();">${eventSize}</td>
								<td>${trueEventSize}</td>
								<td>${falseEventSize}</td>
								<td>${articlesSize}</td>
								<td>${authorsSize}</td>
								<td>${commentsSize}</td>
							</tr>
						</tbody>
					</table>

				</div>
			</div>
			<script>
				function getAll() {
					$.ajax({
						type:"post",
						url : "<%=basePath%>/eventStaticsticInfo?flag=all",
						success : function (data) {
							alert(data);
                        }
					});
                }
			</script>
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
							<c:forEach items="${webstatistics}" var="ws" varStatus="sts">
								<tr>
									<td style="">${sts.index + 1}</td>
									<td>${ws.key}</td>
									<td>${ws.value[0]}</td>
									<td>
										<c:if test="${ws.value[1] == null}">0</c:if>
										<c:if test="${ws.value[1] != null}">${ws.value[1]}</c:if>
									</td>
									<td>${ws.value[2]}</td>
								</tr>
							</c:forEach>
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
