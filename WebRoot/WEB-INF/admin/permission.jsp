<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html lang="en">
<head>
<title>Maruti Admin</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="admin/css/bootstrap.min.css" />
<link rel="stylesheet" href="admin/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="admin/css/uniform.css" />
<link rel="stylesheet" href="admin/css/select2.css" />
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
        <h1>权限管理</h1>
    </div>

    <div class="widget-box" style="width:50%; align: center;">
        <div class="widget-title">
								<span class="icon">
									<i class="icon-eye-open"></i>
								</span>
            <h5>权限列表</h5>
        </div>
        <div class="widget-content nopadding">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th style="display: none;">权限ID</th>
                    <th>权限逻辑名</th>
                    <th>权限物理名</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${permissionList}" var="v">
                    <tr>
                        <td style="display: none;">${v.permissionId}</td>
                        <td>${v.permissionName}</td>
                        <td>${v.action}</td>

                        <td align="center">
                            <a onclick="popModel('${v.permissionId}','${v.permissionName}','${v.action}')"><i class="icon-edit"></i></a>
                            <a onclick="deletePermission('${v.permissionId}');"><i class="icon-remove"></i></a>
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
            <h5>增加权限</h5>
        </div>
        <div class="widget-content nopadding">
            权限逻辑名：<input type="text" id="permissionName">
            权限物理名：<input type="text" id="action">
            <button class="btn btn-primary"  onclick="addPermission()">新增权限</button>
        </div>
    </div>


</div>




<!-- Modal -->
<div class="modal hide fade" id="myModal" tabindex="-1" role="dialog">
    <div class="modal-header"><button class="close" type="button" data-dismiss="modal">×</button>
        <h3 id="myModalLabel">修改权限</h3>
    </div>
    <div class="modal-body">

        <input type="text"  style="display: none;" id="id" ><br/>
        权限逻辑名：<input type="text" id="permissionNewName" ><br/>
        权限物理名：<input type="text" id="newAction" >
    </div>

    <div class="modal-footer">
        <a href="#" class="btn btn-primary"  onclick="updatePermission()" >保存</a>
        <a href="#" class="btn">取消</a>
    </div>
</div>

<!-- 引入后台尾部模板 -->
<jsp:include page="/static/admin_footer.jsp"></jsp:include>


<script src="admin/js/jquery.min.js"></script>
<script src="admin/js/jquery.ui.custom.js"></script>
<script src="admin/js/bootstrap.min.js"></script>
<script src="admin/js/jquery.uniform.js"></script>
<script src="admin/js/select2.min.js"></script>
<script src="admin/js/jquery.dataTables.min.js"></script>
<script src="admin/js/maruti.js"></script>
<script src="admin/js/maruti.tables.js"></script>


<script>
    /*增加权限*/
    function addPermission(){
        var permissionName = $("#permissionName").val();
        var action = $("#action").val();
        var result = false;
        $.ajax({
            async : false,//将async设置为false,才能使得return 返回true/false有效
            type : 'post',
            url : 'addPermission?permissionName='+permissionName+'&action='+action,
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
    function popModel(permissionId,permissionName,action){

        document.getElementById("id").value=permissionId;
        document.getElementById("permissionNewName").value=permissionName;
        document.getElementById("newAction").value=action;
        $('#myModal').modal('show');
    }

    /*弹出修改角色*/
    function updatePermission(){
        var id = $("#id").val();
        var permissionNewName = $("#permissionNewName").val();
        var newAction = $("#newAction").val();

        var result = false;
        $.ajax({
            async : false,//将async设置为false,才能使得return 返回true/false有效
            type : 'post',
            url : 'updatePermission?permissionName='+permissionNewName+'&action='+newAction+'&id='+ id,
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
    function deletePermission(permissionId) {

        var result = false;
        if (confirm("确定删除吗?") == false) {
            return;
        }
        $.ajax({
            async : false,
            type : 'post',
            url : 'deletePermission?permissionId=' + permissionId,
            success : function(msg) {
                if (msg == 'success') {
                    window.location.reload();
                    alert("删除成功");
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
