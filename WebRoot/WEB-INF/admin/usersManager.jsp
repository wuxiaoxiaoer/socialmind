<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=basePath%>admin/"/>
<title>Maruti Admin</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/uniform.css" />
<link rel="stylesheet" href="css/select2.css" />
<link rel="stylesheet" href="css/maruti-style.css" />
<link rel="stylesheet" href="css/maruti-media.css" class="skin-color" />
<script type="text/javascript" src="<%=basePath%>foreground/js/jsAddress.js"></script>
</head>
<body>

<!-- 引入后台头模板-->
<jsp:include page="/static/admin_header.jsp"></jsp:include>


<div id="content">
  <div id="content-header">
    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">Tables</a> </div>
    <h1>用户管理（审、删、展示）</h1>
  </div>
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="widget-box">
          
		<div class="span12">
            <div class="widget-box">
              <div class="widget-title">
                <ul class="nav nav-tabs">
                  <li class="active"><a data-toggle="tab" href="#tab1">个人用户</a></li>
                  <li class=""><a data-toggle="tab" href="#tab2">企业用户</a></li>
                  <li class=""><a data-toggle="tab" href="#tab3">事业单位用户</a></li>
				  <li class=""><a data-toggle="tab" href="#tab4">政府用户</a></li>
                </ul>
              </div>
              <div class="widget-content tab-content">
                <div id="tab1" class="tab-pane active">
				<div class="widget-box">
			<form id="formPerson" action="<%=basePath%>usersManager" method="post">
				<input type="hidden" name="op" value="userMultiVerify"/>
				<input type="hidden" name="isisAuthenticated" value="1"/>
			  <div class="widget-title">
				 <span class="icon"><i class="icon-th"></i></span>
				<h5>待审核个人用户</h5>
				<div class="fr" style="margin: 0.5% 20% 0 0;">
					<input type="submit" class="btn btn-success btn-mini" value="批量审核通过"/>
					<a href="#" class="btn btn-success btn-mini"></a>
					<a onclick="userMultiVerify();" class="btn btn-danger btn-mini">批量审核不通过</a>
				</div>
			  </div>
          <div class="widget-content nopadding">
            <table class="table table-bordered data-table">
              <thead>
                <tr>
				<th><input type="checkbox" />&nbsp&nbsp是否选中</th>
				  <th>序号</th>
                  <th>用户名</th>
				  <th>角色</th>
				  <th>邮箱</th>
				  <th>身份证ID</th>
				  <th>地址</th>
				  <th>状态</th>
				  <th>性别</th>
				  <th>生日</th>
				  <th>电话</th>
				  <th>爱好</th>
				  <th>职业</th>
				  <th>真实姓名</th>
				  <th>审核</th>
                </tr>
              </thead>
              <tbody>
			  <c:forEach items="${persons}" var="p" varStatus="sts">
				  <tr class="gradeX">
					  <td><input type="checkbox" name="userId" value="${p.userId}"/></td>
					  <td>${sts.index + 1}</td>
					  <c:forEach items="${page.result}" var="pa">
						  <c:if test="${p.userId == pa.userId}">
							  <td><a href="#" style="color:blue;">${pa.userName}</a></td>
							  <td>${pa.role.roleName}</td>
							  <td>${pa.email}</td>
							  <td>${pa.registrantId}</td>
							  <td>${pa.address}</td>
							  <td>
								  <c:if test="${pa.isAuthenticated == '0'}">未审核</c:if>
								  <c:if test="${pa.isAuthenticated == '1'}">审核通过</c:if>
								  <c:if test="${pa.isAuthenticated == '-1'}">审核不通过</c:if>
								  <c:if test="${pa.isAuthenticated == 'no'}">用户已被删除</c:if>
							  </td>
						  </c:if>
					  </c:forEach>
					  <td>
						  <c:if test="${p.sex == '1'}">男</c:if>
						  <c:if test="${p.sex == '0'}">女</c:if>
					  </td>
					  <td>${p.birthday}</td>
					  <td>${p.phoneNumber}</td>
					  <td>${p.hobby}</td>
					  <td>${p.career}</td>
					  <td>${p.realName}</td>
					  <td class="taskOptions">
						  <a href="<%=basePath%>usersManager?userId=${p.userId}&isisAuthenticated=1&op=userVerify" class="tip-top" data-original-title="Update">
							  <i class="icon-ok"></i>
						  </a>
						  <a  href="<%=basePath%>usersManager?userId=${p.userId}&isisAuthenticated=-1&op=userVerify">
							  <span class="label label-inverse">No</span>
						  </a>
					  </td>
                </tr>
			  </c:forEach>
              </tbody>
            </table>
          </div>
		</form>
		<script>
			function userMultiVerify() {
				$("input[name='isisAuthenticated']").val("-1");
				$("#formPerson").submit();
			}

		</script>
		<div class="widget-title" style="margin-top:30px;">
			<span class="icon"><i class="icon-th"></i></span>
			<h5>全部个人用户 共230条</h5>
			<div class="fr" style="margin: 0.5% 10% 0 0;">
				<a href="#" class="btn btn-success btn-mini">批量删除</a>
			</div>
		</div>
	  <div class="widget-content nopadding">
		  <table class="table table-bordered data-table">
			  <thead>
			  <tr>
				  <th>序号</th>
				  <th>用户名</th>
				  <th>角色</th>
				  <th>邮箱</th>
				  <th>身份证ID</th>
				  <th>地址</th>
				  <th>状态</th>
				  <th>性别</th>
				  <th>生日</th>
				  <th>电话</th>
				  <th>爱好</th>
				  <th>职业</th>
				  <th>真实姓名</th>
				  <th>审核</th>
				  <th>操作</th>
			  </tr>
			  </thead>
			  <tbody>
			  <c:forEach items="${pageAllPersons}" var="p" varStatus="sts">
				  <tr class="gradeX">
					  <td>${sts.index + 1}</td>
					  <c:forEach items="${pageAllUsers.result}" var="pa">
						  <c:if test="${p.userId == pa.userId}">
							  <td><a href="#" style="color:blue;">${pa.userName}</a></td>
							  <td>${pa.role.roleName}</td>
							  <td>${pa.email}</td>
							  <td>${pa.registrantId}</td>
							  <td>${pa.address}</td>
							  <td>
								  <c:if test="${pa.isAuthenticated == '0'}">未审核</c:if>
								  <c:if test="${pa.isAuthenticated == '1'}">审核通过</c:if>
								  <c:if test="${pa.isAuthenticated == '-1'}">审核不通过</c:if>
								  <c:if test="${pa.isAuthenticated == 'no'}">用户已被删除</c:if>
							  </td>
						  </c:if>
					  </c:forEach>
					  <td>
						  <c:if test="${p.sex == '1'}">男</c:if>
						  <c:if test="${p.sex == '0'}">女</c:if>
					  </td>
					  <td>${p.birthday}</td>
					  <td>${p.phoneNumber}</td>
					  <td>${p.hobby}</td>
					  <td>${p.career}</td>
					  <td>${p.realName}</td>
					  <td class="taskOptions">
						  <a href="<%=basePath%>usersManager?userId=${p.userId}&isisAuthenticated=1&op=userVerify" class="tip-top" data-original-title="Update">
							  <i class="icon-ok"></i>
						  </a>
						  <a  href="<%=basePath%>usersManager?userId=${p.userId}&isisAuthenticated=-1&op=userVerify">
							  <span class="label label-inverse">No</span>
						  </a>
					  </td>
					  <td class="center"><a href="<%=basePath%>usersManager?userId=${p.userId}&isisAuthenticated=no&op=userVerify" class="tip-top" data-original-title="Delete"><i class="icon-remove"></i></a></td>
				  </tr>
			  </c:forEach>
			  </tbody>
		  </table>
	  </div>
	</div>
	</div>


	  <div id="tab2" class="tab-pane">
	  <div class="widget-box">
          <div class="widget-title">
             <span class="icon"><i class="icon-th"></i></span> 
            <h5>待审核企业用户</h5>
          </div>
          <div class="widget-content nopadding">
            <table class="table table-bordered data-table">
              <thead>
                <tr>
				  <th>序号</th>
					<th>用户名</th>
					<th>角色</th>
					<th>邮箱</th>
					<th>地区</th>
					<th>状态</th>
					<th>身份证ID</th>
					<th>法人身份证正面</th>
					<th>法人身份证背面</th>
                  <th>法人姓名</th>
				  <th>企业名称</th>
				  <th>企业类型</th>
                  <th>营业执照</th>
                  <th>营业执照图片</th>
				  <th>审核</th>
                </tr>
              </thead>
              <tbody>
				  <c:forEach items="${unVerifyCompanyUsers}" var="c" varStatus="sts">
					<tr class="gradeX">
					  <td>${sts.index + 1}</td>
						<c:forEach items="${page.result}" var="pa">
							<c:if test="${c.userId == pa.userId}">
								<td><a href="#" style="color:blue;">${pa.userName}</a></td>
								<td>${pa.role.roleName}</td>
								<td>${pa.email}</td>
								<td>${pa.address}</td>
								<td>
									<c:if test="${pa.isAuthenticated == '0'}">未审核</c:if>
									<c:if test="${pa.isAuthenticated == '1'}">审核通过</c:if>
									<c:if test="${pa.isAuthenticated == '-1'}">审核不通过</c:if>
									<c:if test="${pa.isAuthenticated == 'no'}">用户已被删除</c:if>
								</td>
								<td>${pa.registrantId}</td>
							</c:if>
						</c:forEach>
						<td><li class="span2">
							<a class="thumbnail lightbox_trigger" href="<%=basePath%>${c.idFrontUrl}">
								<img src="<%=basePath%>${c.idFrontUrl}" alt="">
							</a>
						</li></td>
						<td><li class="span2">
							<a class="thumbnail lightbox_trigger" href="<%=basePath%>${c.idBackUrl}">
								<img src="<%=basePath%>${c.idBackUrl}" alt="">
							</a>
						</li></td>
					  <td>${c.bossName}</td>
					  <td>${c.companyName}</td>
					  <td>${c.type}</td>
					  <td>${c.businessLicenceId}</td>
					  <td><li class="span2">
						<a class="thumbnail lightbox_trigger" href="<%=basePath%>${c.businessLicenceUrl}">
							<img src="<%=basePath%>${c.businessLicenceUrl}" alt="">
						</a>
					</li></td>
					<td class="taskOptions">
						<a href="<%=basePath%>usersManager?userId=${c.userId}&isisAuthenticated=1&op=userVerify" class="tip-top" data-original-title="Update">
							<i class="icon-ok"></i>
						</a>
						<a href="<%=basePath%>usersManager?userId=${c.userId}&isisAuthenticated=-1&op=userVerify">
							<span class="label label-inverse">No</span>
						</a>
					</td>
					</tr>
				  </c:forEach>
              </tbody>
            </table>
          </div>
			

		<div class="widget-title" style="margin-top:30px;">
             <span class="icon"><i class="icon-th"></i></span> 
            <h5>全部企业用户 </h5>
          </div>
		  <table class="table table-bordered data-table">
              <thead>
                <tr>
					<th>序号</th>
					<th>用户名</th>
					<th>角色</th>
					<th>邮箱</th>
					<th>地区</th>
					<th>状态</th>
					<th>身份证ID</th>
					<th>法人身份证正面</th>
					<th>法人身份证背面</th>
					<th>法人姓名</th>
					<th>企业名称</th>
					<th>企业类型</th>
					<th>营业执照</th>
					<th>营业执照图片</th>
					<th>审核</th>
				  <th>操作</th>
                </tr>
              </thead>
              <tbody>
				  <c:forEach items="${pageAllCompanyUsers}" var="c" varStatus="sts">
					  <tr class="gradeX">
						  <td>${sts.index + 1}</td>
						  <c:forEach items="${pageAllUsers.result}" var="pa">
							  <c:if test="${c.userId == pa.userId}">
								  <td><a href="#" style="color:blue;">${pa.userName}</a></td>
								  <td>${pa.role.roleName}</td>
								  <td>${pa.email}</td>
								  <td>${pa.address}</td>
								  <td>
									  <c:if test="${pa.isAuthenticated == '0'}">未审核</c:if>
									  <c:if test="${pa.isAuthenticated == '1'}">审核通过</c:if>
									  <c:if test="${pa.isAuthenticated == '-1'}">审核不通过</c:if>
									  <c:if test="${pa.isAuthenticated == 'no'}">用户已被删除</c:if>
								  </td>
								  <td>${pa.registrantId}</td>
							  </c:if>
						  </c:forEach>
						  <td><li class="span2">
							  <a class="thumbnail lightbox_trigger" href="<%=basePath%>${c.idFrontUrl}">
								  <img src="<%=basePath%>${c.idFrontUrl}" alt="">
							  </a>
						  </li></td>
						  <td><li class="span2">
							  <a class="thumbnail lightbox_trigger" href="<%=basePath%>${c.idBackUrl}">
								  <img src="<%=basePath%>${c.idBackUrl}" alt="">
							  </a>
						  </li></td>
						  <td>${c.bossName}</td>
						  <td>${c.companyName}</td>
						  <td>${c.type}</td>
						  <td>${c.businessLicenceId}</td>
						  <td><li class="span2">
							  <a class="thumbnail lightbox_trigger" href="<%=basePath%>${c.businessLicenceUrl}">
								  <img src="<%=basePath%>${c.businessLicenceUrl}" alt="">
							  </a>
						  </li></td>
						  <td class="taskOptions">
							  <a href="<%=basePath%>usersManager?userId=${c.userId}&isisAuthenticated=1&op=userVerify" class="tip-top" data-original-title="Update">
								  <i class="icon-ok"></i>
							  </a>
							  <a href="<%=basePath%>usersManager?userId=${c.userId}&isisAuthenticated=-1&op=userVerify">
								  <span class="label label-inverse">No</span>
							  </a>
						  </td>

					  </tr>
				  </c:forEach>
              </tbody>
            </table>
        </div>
		
		</div>
                <div id="tab3" class="tab-pane">
                  <div class="widget-box">
          <div class="widget-title">
             <span class="icon"><i class="icon-th"></i></span> 
            <h5>待审核事业单位用户 共25条</h5>
          </div>
          <div class="widget-content nopadding">
            <table class="table table-bordered data-table">
              <thead>
                <tr>
				  <th>序号</th>
					<th>用户名</th>
					<th>角色</th>
					<th>邮箱</th>
					<th>地区</th>
					<th>状态</th>
					<th>身份证ID</th>
					<th>法人身份证正面</th>
					<th>法人身份证背面</th>
                  <th>法人姓名</th>
				  <th>事业单位名称</th>
				  <th>事业单位类型</th>
                  <th>事业单位编号</th>
                  <th>事业单位登记图片</th>
				  <th>审核</th>
                </tr>
              </thead>
              <tbody>
				  <c:forEach items="${unVerifyInstitUsers}" var="i" varStatus="sts">
					  <tr class="gradeX">
						  <td>${sts.index + 1}</td>
						  <c:forEach items="${page.result}" var="pa">
							  <c:if test="${i.userId == pa.userId}">
								  <td><a href="#" style="color:blue;">${pa.userName}</a></td>
								  <td>${pa.role.roleName}</td>
								  <td>${pa.email}</td>
								  <td>${pa.address}</td>
								  <td>
									  <c:if test="${pa.isAuthenticated == '0'}">未审核</c:if>
									  <c:if test="${pa.isAuthenticated == '1'}">审核通过</c:if>
									  <c:if test="${pa.isAuthenticated == '-1'}">审核不通过</c:if>
									  <c:if test="${pa.isAuthenticated == 'no'}">用户已被删除</c:if>
								  </td>
								  <td>${pa.registrantId}</td>
							  </c:if>
						  </c:forEach>
						  <td><li class="span2">
							  <a class="thumbnail lightbox_trigger" href="<%=basePath%>${i.idFrontUrl}">
								  <img src="<%=basePath%>${i.idFrontUrl}" alt="">
							  </a>
						  </li></td>
						  <td><li class="span2">
							  <a class="thumbnail lightbox_trigger" href="<%=basePath%>${i.idBackUrl}">
								  <img src="<%=basePath%>${i.idBackUrl}" alt="">
							  </a>
						  </li></td>
						  <td>${i.bossName}</td>
						  <td>${i.institutionName}</td>
						  <td>${i.type}</td>
						  <td>${i.institudeCode}</td>
						  <td><li class="span2">
							  <a class="thumbnail lightbox_trigger" href="<%=basePath%>${c.institudeCodeUrl}">
								  <img src="<%=basePath%>${c.institudeCodeUrl}" alt="">
							  </a>
						  </li></td>
						  <td class="taskOptions">
							  <a href="<%=basePath%>usersManager?userId=${i.userId}&isisAuthenticated=1&op=userVerify" class="tip-top" data-original-title="Update">
								  <i class="icon-ok"></i>
							  </a>
							  <a href="<%=basePath%>usersManager?userId=${i.userId}&isisAuthenticated=-1&op=userVerify">
								  <span class="label label-inverse">No</span>
							  </a>
						  </td>
					  </tr>
				  </c:forEach>
              </tbody>
            </table>
          </div>
			

		<div class="widget-title" style="margin-top:30px;">
             <span class="icon"><i class="icon-th"></i></span> 
            <h5>全部事业单位用户 共230条</h5>
          </div>
		  <table class="table table-bordered data-table">
              <thead>
                <tr>
				  <th>序号</th>
					<th>用户名</th>
					<th>角色</th>
					<th>邮箱</th>
					<th>地区</th>
					<th>状态</th>
					<th>身份证ID</th>
					<th>法人身份证正面</th>
					<th>法人身份证背面</th>
                  <th>法人姓名</th>
				  <th>事业单位名称</th>
				  <th>事业单位类型</th>
                  <th>事业单位编号</th>
                  <th>事业单位登记图片</th>
				  <th>状态</th>
				  <th>操作</th>
                </tr>
              </thead>
              <tbody>
			  <c:forEach items="${pageAllInstitUsers}" var="i" varStatus="sts">
				  <tr class="gradeX">
					  <td>${sts.index + 1}</td>
					  <c:forEach items="${pageAllUsers.result}" var="pa">
						  <c:if test="${i.userId == pa.userId}">
							  <td><a href="#" style="color:blue;">${pa.userName}</a></td>
							  <td>${pa.role.roleName}</td>
							  <td>${pa.email}</td>
							  <td>${pa.address}</td>
							  <td>
								  <c:if test="${pa.isAuthenticated == '0'}">未审核</c:if>
								  <c:if test="${pa.isAuthenticated == '1'}">审核通过</c:if>
								  <c:if test="${pa.isAuthenticated == '-1'}">审核不通过</c:if>
								  <c:if test="${pa.isAuthenticated == 'no'}">用户已被删除</c:if>
							  </td>
							  <td>${pa.registrantId}</td>
						  </c:if>
					  </c:forEach>
					  <td><li class="span2">
						  <a class="thumbnail lightbox_trigger" href="<%=basePath%>${i.idFrontUrl}">
							  <img src="<%=basePath%>${i.idFrontUrl}" alt="">
						  </a>
					  </li></td>
					  <td><li class="span2">
						  <a class="thumbnail lightbox_trigger" href="<%=basePath%>${i.idBackUrl}">
							  <img src="<%=basePath%>${i.idBackUrl}" alt="">
						  </a>
					  </li></td>
					  <td>${i.bossName}</td>
					  <td>${i.institutionName}</td>
					  <td>${i.type}</td>
					  <td>${i.institudeCode}</td>
					  <td><li class="span2">
						  <a class="thumbnail lightbox_trigger" href="<%=basePath%>${c.institudeCodeUrl}">
							  <img src="<%=basePath%>${c.institudeCodeUrl}" alt="">
						  </a>
					  </li></td>
					  <td class="taskOptions">
						  <a href="<%=basePath%>usersManager?userId=${i.userId}&isisAuthenticated=1&op=userVerify" class="tip-top" data-original-title="Update">
							  <i class="icon-ok"></i>
						  </a>
						  <a href="<%=basePath%>usersManager?userId=${i.userId}&isisAuthenticated=-1&op=userVerify">
							  <span class="label label-inverse">No</span>
						  </a>
					  </td>
					  <td class="center"><a href="<%=basePath%>usersManager?userId=${i.userId}&isisAuthenticated=no&op=userVerify" class="tip-top" data-original-title="Delete"><i class="icon-remove"></i></a></td>
				  </tr>
			  </c:forEach>
              </tbody>
            </table>
        </div>
		</div>
		
		<div id="tab4" class="tab-pane">
                  <div class="widget-box">
          <div class="widget-title">
             <span class="icon"><i class="icon-th"></i></span> 
            <h5>待审核政府单位用户 共25条</h5>
          </div>
          <div class="widget-content nopadding">
            <table class="table table-bordered data-table">
              <thead>
                <tr>
				  <th>序号</th>
					<th>用户名</th>
					<th>角色</th>
					<th>邮箱</th>
					<th>地区</th>
					<th>状态</th>
					<th>身份证ID</th>
					<th>法人身份证正面</th>
					<th>法人身份证背面</th>
                  <th>法人姓名</th>
				  <th>政府名称</th>
				  <th>政府类型</th>
                  <th>组织机构代码</th>
                  <th>组织机构图片</th>
				  <th>审核</th>
                </tr>
              </thead>
              <tbody>
				  <c:forEach items="${unVerifyGovUsers}" var="g" varStatus="sts">
					  <tr class="gradeX">
						  <td>${sts.index + 1}</td>
						  <c:forEach items="${page.result}" var="pa">
							  <c:if test="${g.userId == pa.userId}">
								  <td><a href="#" style="color:blue;">${pa.userName}</a></td>
								  <td>${pa.role.roleName}</td>
								  <td>${pa.email}</td>
								  <td>${pa.address}</td>
								  <td>
									  <c:if test="${pa.isAuthenticated == '0'}">未审核</c:if>
									  <c:if test="${pa.isAuthenticated == '1'}">审核通过</c:if>
									  <c:if test="${pa.isAuthenticated == '-1'}">审核不通过</c:if>
									  <c:if test="${pa.isAuthenticated == 'no'}">用户已被删除</c:if>
								  </td>
								  <td>${pa.registrantId}</td>
							  </c:if>
						  </c:forEach>
						  <td><li class="span2">
							  <a class="thumbnail lightbox_trigger" href="<%=basePath%>${g.idFrontUrl}">
								  <img src="<%=basePath%>${g.idFrontUrl}" alt="">
							  </a>
						  </li></td>
						  <td><li class="span2">
							  <a class="thumbnail lightbox_trigger" href="<%=basePath%>${g.idBackUrl}">
								  <img src="<%=basePath%>${g.idBackUrl}" alt="">
							  </a>
						  </li></td>
						  <td>${g.bossName}</td>
						  <td>${g.govName}</td>
						  <td>${g.type}</td>
						  <td>${g.govCode}</td>
						  <td><li class="span2">
							  <a class="thumbnail lightbox_trigger" href="<%=basePath%>${g.govCodeUrl}">
								  <img src="<%=basePath%>${g.govCodeUrl}" alt="">
							  </a>
						  </li></td>
						  <td class="taskOptions">
							  <a href="<%=basePath%>usersManager?userId=${g.userId}&isisAuthenticated=1&op=userVerify" class="tip-top" data-original-title="Update">
								  <i class="icon-ok"></i>
							  </a>
							  <a href="<%=basePath%>usersManager?userId=${g.userId}&isisAuthenticated=-1&op=userVerify">
								  <span class="label label-inverse">No</span>
							  </a>
						  </td>
					  </tr>
				  </c:forEach>
              </tbody>
            </table>
          </div>
			

		<div class="widget-title" style="margin-top:30px;">
             <span class="icon"><i class="icon-th"></i></span> 
            <h5>全部企业用户 共230条</h5>
          </div>
		  <table class="table table-bordered data-table">
              <thead>
                <tr>
					<th>序号</th>
					<th>用户名</th>
					<th>角色</th>
					<th>邮箱</th>
					<th>地区</th>
					<th>状态</th>
					<th>身份证ID</th>
					<th>法人身份证正面</th>
					<th>法人身份证背面</th>
					<th>法人姓名</th>
					<th>政府名称</th>
					<th>政府类型</th>
					<th>组织机构代码</th>
					<th>组织机构图片</th>
					<th>审核</th>
					<th>操作</th>
                </tr>
              </thead>
              <tbody>
				  <c:forEach items="${pageAllGovUsers}" var="g" varStatus="sts">
					  <tr class="gradeX">
						  <td>${sts.index + 1}</td>
						  <c:forEach items="${pageAllUsers.result}" var="pa">
							  <c:if test="${g.userId == pa.userId}">
								  <td><a href="#" style="color:blue;">${pa.userName}</a></td>
								  <td>${pa.role.roleName}</td>
								  <td>${pa.email}</td>
								  <td>${pa.address}</td>
								  <td>
									  <c:if test="${pa.isAuthenticated == '0'}">未审核</c:if>
									  <c:if test="${pa.isAuthenticated == '1'}">审核通过</c:if>
									  <c:if test="${pa.isAuthenticated == '-1'}">审核不通过</c:if>
									  <c:if test="${pa.isAuthenticated == 'no'}">用户已被删除</c:if>
								  </td>
								  <td>${pa.registrantId}</td>
							  </c:if>
						  </c:forEach>
						  <td><li class="span2">
							  <a class="thumbnail lightbox_trigger" href="<%=basePath%>${g.idFrontUrl}">
								  <img src="<%=basePath%>${g.idFrontUrl}" alt="">
							  </a>
						  </li></td>
						  <td><li class="span2">
							  <a class="thumbnail lightbox_trigger" href="<%=basePath%>${g.idBackUrl}">
								  <img src="<%=basePath%>${g.idBackUrl}" alt="">
							  </a>
						  </li></td>
						  <td>${g.bossName}</td>
						  <td>${g.govName}</td>
						  <td>${g.type}</td>
						  <td>${g.govCode}</td>
						  <td><li class="span2">
							  <a class="thumbnail lightbox_trigger" href="<%=basePath%>${g.govCodeUrl}">
								  <img src="<%=basePath%>${g.govCodeUrl}" alt="">
							  </a>
						  </li></td>
						  <td class="taskOptions">
							  <a href="<%=basePath%>usersManager?userId=${g.userId}&isisAuthenticated=1&op=userVerify" class="tip-top" data-original-title="Update">
								  <i class="icon-ok"></i>
							  </a>
							  <a href="<%=basePath%>usersManager?userId=${g.userId}&isisAuthenticated=-1&op=userVerify">
								  <span class="label label-inverse">No</span>
							  </a>
						  </td>
						  <td class="center"><a href="<%=basePath%>usersManager?userId=${g.userId}&isisAuthenticated=no&op=userVerify" class="tip-top" data-original-title="Delete"><i class="icon-remove"></i></a></td>
					  </tr>
				  </c:forEach>
              </tbody>
            </table>
        </div>
		</div>
              </div>
            </div>
          </div>

        </div>
		
		<div>
		
		</div>
       
      </div>
    </div>
  </div>
</div>
<div class="row-fluid">
  <div id="footer" class="span12"> 2012 &copy; Marutii Admin. More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a> </div>
</div>
<script src="js/jquery.min.js"></script> 
<script src="js/jquery.ui.custom.js"></script> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/jquery.uniform.js"></script> 
<script src="js/select2.min.js"></script> 
<script src="js/jquery.dataTables.min.js"></script> 
<script src="js/maruti.js"></script> 
<script src="js/maruti.tables.js"></script>
</body>
</html>
