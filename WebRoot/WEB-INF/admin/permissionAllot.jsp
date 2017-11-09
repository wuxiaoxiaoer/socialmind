
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


<div id="content">
  <div id="content-header">
    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">Tables</a> </div>
    <h1>权限分配</h1>
  </div>
    <form action="permissionAllot" method="post" name="permissionAllot" class="form-horizontal" role="form">
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="widget-box">

          <div class="widget-title">
             <span class="icon"><i class="icon-th"></i></span> 
            角色：<select id="roleId" name="roleId"  placeholder="请选择角色" onchange="permissionByrole()">
              <c:forEach items="${roleList}" var="v">
                  <option value="${v.roleId}">${v.roleName}</option>
              </c:forEach>
              </select>

          </div>

          <div class="widget-content nopadding">
            <table class="table table-bordered data-table">
              <thead>
                <tr>
                  <th width=30% style="display: none;" >权限ID</th>
				  <th >权限名称</th>
                  <th >物理名</th>
                  <th ><input type="checkbox"  id="controlAll" onclick="selectAll()"></th>
                </tr>
              </thead>
                <tbody id="body">
                <c:forEach items="${permissionList}" var="v">
                    <tr>
                        <td style="display: none;">${v.permissionId}</td>
                        <td>${v.permissionName}</td>
                        <td>${v.action}</td>
                        <c:choose>
                            <c:when test="${permissionMap[v.permissionId] eq 't' }">
                                <td><input type="checkbox" name="permissionId" checked="checked" value="${v.permissionId}"></td>
                            </c:when>

                            <c:otherwise>
                                <td><input type="checkbox" name="permissionId" value="${v.permissionId}"></td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
                </tbody>


            </table>
			<button type="submit">提交</button>
          </div>

       </div>
       
        
      </div>
    </div>
  </div>
    </form>
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

    /*全选全不选*/
    function selectAll(){
        var checklist = document.getElementsByName ("permissionId");
        if(document.getElementById("controlAll").checked)
        {
            for(var i=0;i<checklist.length;i++)
            {
                checklist[i].setAttribute("checked","checked");
            }
        }else{
            for(var j=0;j<checklist.length;j++)
            {
                checklist[j].removeAttribute("checked");

            }
        }
    }


    //按角色显示权限
    function permissionByrole(){
        var roleId = $("#roleId").val();

        $.ajax({
            async : false,//将async设置为false,才能使得return 返回true/false有效
            type : 'post',
            url : 'permissionByrole?roleId=' + roleId,
            success : function(data) {
                var permissionList = eval('(' + data + ')').permissionList; //获取后台list
                var permissionMap = eval('(' + data + ')').permissionMap;

                if (permissionList.length == 0) {
                    $("#body").html("");

                }
                else {
                    var permissionHtml = "";
                    for (var i = 0; i < permissionList.length; i++) {
                        permissionHtml = permissionHtml
                            + "<tr><td width=30% style='display: none;'>"
                            + permissionList[i].permissionId
                            + "</td><td>"
                            + permissionList[i].permissionName
                            + "</td><td>"
                            + permissionList[i].action
                        if (permissionMap[permissionList[i].permissionId] == 't') {

                            permissionHtml = permissionHtml
                                + "<td><input type='checkbox' name='permissionId' checked='true' value=' "
                                +permissionList[i].permissionId
                                +"' ></td></tr>";
                        }
                        else {
                            permissionHtml = permissionHtml
                                + "<td><input type='checkbox' name='permissionId' value='"
                                +permissionList[i].permissionId
                                +" ' ></td></tr>";
                        }

                    }

                    $("#body")[0].innerHTML = permissionHtml;
                }

            }
        });
    }





</script>


</body>
</html>
