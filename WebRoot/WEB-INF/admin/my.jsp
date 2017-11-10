<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sicdlib.entity.UserEntity" %>
<%@ page import="com.sicdlib.entity.AdminEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
UserEntity commonUser = (UserEntity) request.getAttribute("commonUser");
    AdminEntity user = (AdminEntity) request.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">
	
<head>
	<base href="<%=basePath%>admin/"/>
	<title>个人中心 - socialmind后台</title><meta charset="UTF-8" />
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

  </ul>
		</div>
		<div id="content">
			<div id="content-header">
				<div id="breadcrumb">
				<a href="index.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
				<a href="#">Form elements</a>
				<a href="#" class="current">Validation</a>
				</div>
                <h1>个人中心</h1>
			</div>
			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-info-sign"></i>									
								</span>
								<h5>个人中心</h5>
							</div>
							<div class="widget-content nopadding" style="width:50%">
								<form class="form-horizontal" method="post" action="<%=basePath%>user/modMyInfo" name="basic_validate" id="basic_validate" novalidate="novalidate">
                                    <input type="hidden" name="userId" value="<%=user.getUserId()%>"/>
                                    <div class="control-group">
                                        <label class="control-label">用户名：</label>
                                        <div class="controls">
                                            <input type="text" name="userName" id="required" value="<%=commonUser.getUserName()%>">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">密码：</label>
                                        <div class="controls">
                                            <input type="text" name="password" id="password" placeholder="不填写表无需修改密码">
                                        </div>
                                    </div>
									<div class="control-group">
                                        <label class="control-label">确认密码：</label>
                                        <div class="controls">
                                            <input type="text" name="repwd" id="repassword">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">邮箱：</label>
                                        <div class="controls">
                                            <input type="text" name="email" id="email" value="<%=commonUser.getEmail()%>">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">手机号：</label>
                                        <div class="controls">
                                            <input type="text" name="phoneNumber" value="<%=user.getPhoneNumber()%>">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">真实姓名：</label>
                                        <div class="controls">
                                            <input type="text" name="realName" value="<%=user.getRealName()%>">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">地区</label>
                                        <div class="controls" align="left">
                                            <div>
                                                <select name="province" id="cmbProvinceAdmin" style="width: 15%;"></select>
                                                <select name="city" id="cmbCityAdmin" style="width: 18%;"></select>
                                                <select name="area" id="cmbAreaAdmin" style="width: 18%;"></select>
                                                <script type="text/javascript">
                                                    addressInit('cmbProvinceAdmin', 'cmbCityAdmin', 'cmbAreaAdmin', '陕西', '宝鸡市', '金台区');
                                                </script>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-actions">
                                        <input type="submit" value="修改个人信息" class="btn btn-success">
                                    </div>
                                </form>
							</div>
						</div>			
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-info-sign"></i>									
								</span>
								<h5>用户日志信息</h5>
							</div>
          <div class="widget-content nopadding">
            <table class="table table-bordered data-table">
              <thead>
                <tr>
                  <th>角色</th>               
                  <th>姓名</th>
                  <th>Ip</th>
				  <th>操作时间</th>
				  <th>方法逻辑名</th>
				  <th>方法名</th>
				  <th>参数</th>
                </tr>
              </thead>
              <tbody>
                <tr class="gradeX">
                  <td>管理员</td>
                  <td><a href="#" style="color:blue;">张三</a></td>
                  <td>127.0.0.1</td>
				  <td>2017-10-23 04:46</td>
				  <td>展示用户信息</td>
				  <td>userInforDisplay</td>
				  <td>参数{1,2}</td>
                </tr>
                <tr class="gradeC">
                  <td>管理员</td>			
                  <td><a href="#" style="color:blue;">张三</a></td>
                  <td>127.0.0.1</td>
				  <td>2017-10-23 04:46</td>
				  <td>用户信息更新</td>
				  <td>userInforModify</td>
				  <td>参数{1,2}</td>
                </tr>
                <tr class="gradeA">
                  <td>管理员</td>
                  <td><a href="#" style="color:blue;">张三</a></td>
                  <td>127.0.0.1</td>
				  <td>2017-10-23 04:46</td>
				  <td>用户信息删除</td>
				  <td>userInforDelete</td>
				  <td>参数{1,2}</td>
                </tr>
              </tbody>
            </table>
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
            <script src="js/jquery.validate.js"></script>
            <script src="js/maruti.js"></script>
            <script src="js/maruti.form_validation.js"></script>
	</body>
</html>
