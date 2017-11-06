<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    
    <head>
        <title>注册 - Socialmind大平台</title>
        <!-- Bootstrap -->
        <link href="foreground/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="foreground/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
        <link href="foreground/assets/styles.css" rel="stylesheet" media="screen">
        <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="foreground/vendors/flot/excanvas.min.js"></script><![endif]-->
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
		<script src="foreground/vendors/jquery-1.9.1.js"></script>
        <script src="foreground/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
		<style type="text/css">
			/** 背景样式 - 开始 **/
			.bg {
				background: url('foreground/images/bg.jpg');
				background-repeat: no-repeat;
				background-position: center;
				background-size: cover;
				text-align: center;
				background-size:100% 100%;
				/*overflow:hidden;*/
				background-color: #464646;
				background-size: cover;
				/*设置图片全屏*/
				background-attachment: fixed;
				background-position: center center;
			}
			/** 背景样式 - 结束 **/

		</style>
		<script type="text/javascript" src="foreground/js/jsAddress.js"></script>
		<script>
			//取消验证
//			$("#formPersonUser").validate().resetForm();
		</script>
    </head>
    
    <body class="bg">

        <div class="container-fluid">
            <div class="row-fluid">
                <!--/span-->
                <div class="span9" id="content" style="width:40%; margin:5% 20%;">
                      <!-- morris stacked chart -->
                    <div class="row-fluid">
                        <!-- block -->
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">用户注册</div>
                            </div>
                            
                                <div class="tabtable">

                                        <ul class="nav nav-tabs">
										  <li class="active"><a data-toggle="tab" href="#tab1">个人用户</a></li>
										  <li class=""><a data-toggle="tab" href="#tab2">企业用户</a></li>
										  <li class=""><a data-toggle="tab" href="#tab3">事业单位用户</a></li>
										  <li class=""><a data-toggle="tab" href="#tab4">政府用户</a></li>
										  <li class=""><a data-toggle="tab" href="#tab5">管理员</a></li>
										</ul>
									<center>
									<div class="tab-content" style="">
										<div id="tab1" class="tab-pane active">
											<form action="user/register" method="post" id="formPersonUser" class="form-horizontal" enctype="multipart/form-data">
												<!-- 设置用户类型：个人用户,4 -->
												<input type="hidden" name="userType" value="个人"/>
												<fieldset>
													<div class="control-group">
														<label class="control-label">用户名<span class="required">*</span></label>
														<div class="controls" align="left">
															<input type="text" name="userName" data-required="1" class="span6 m-wrap" onblur="valid_username(this)"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">密码<span class="required">*</span></label>
														<div class="controls" align="left">
															<input name="password" type="password" class="span6 m-wrap" onblur="valid_password(this)"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">重复密码<span class="required">*</span></label>
														<div class="controls" align="left">
															<input name="repwd" type="password" class="span6 m-wrap" onblur="valid_repassword(this)"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">性别 <span class="required">*</span></label>
														<div class="controls" align="left">
															<select name="sex" id="selectSex" class="chzn-select">
																<option value="1">男</option>
																<option value="0">女</option>
															</select>
														</div>
													</div>
													<div class="control-group">
													  <label class="control-label" for="date01">生日<span class="required">*</span></label>
													  <div class="controls" align="left">
														<input type="text" name="birthday" class="input-xlarge datepicker" style="width: 46%;" id="date01" value="02/16/12">
													  </div>
													</div>
													<div class="control-group">
														<label class="control-label">真实姓名<span class="required">*</span></label>
														<div class="controls" align="left">
															<input name="realName" type="text" class="span6 m-wrap" onblur="valid_telphone(this)"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">手机<span class="required">*</span></label>
														<div class="controls" align="left">
															<input name="phoneNumber" type="text" class="span6 m-wrap" onblur="valid_telphone(this)"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">邮箱<span class="required">*</span></label>
														<div class="controls" align="left">
															<input name="email" type="text" class="span6 m-wrap" onblur="valid_email(this);"/>
														</div>
													</div>
													<div class="control-group">
													  <label class="control-label">地区</label>
													  <div class="controls" align="left">
														  <div>
															  <select name="province" id="cmbProvince" style="width: 15%;"></select>
															  <select name="city" id="cmbCity" style="width: 18%;"></select>
															  <select name="area" id="cmbArea" style="width: 18%;"></select>
															  <script type="text/javascript">
																  addressInit('cmbProvince', 'cmbCity', 'cmbArea', '陕西', '宝鸡市', '金台区');
															  </script>
														  </div>
													</div>
													<div class="control-group" style="margin-top:3%;">
														<label class="control-label">兴趣<span class="required">*</span></label>
														<div class="controls" align="left">
															<input name="hobby" type="text" class="span6 m-wrap"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">职业<span class="required">*</span></label>
														<div class="controls" align="left">
															<input name="career" type="text" class="span6 m-wrap"/>
														</div>
													</div>

													<div class="form-actions">
														<button type="submit" class="btn btn-primary">注 册</button>
														<button type="reset" class="btn">重置</button>
													</div>
												</div>
											  </fieldset>
											</form>
										</div>
                                        
										<div id="tab2" class="tab-pane">
											<form action="user/register" method="post" id="formCompanyUser" class="form-horizontal" enctype="multipart/form-data">
												<!-- 设置用户类型：企业,3 -->
												<input type="hidden" name="userType" value="企业"/>
												<div class="control-group">
													<label class="control-label">用户名<span class="required">*</span></label>
													<div class="controls" align="left">
														<input type="text" name="userName" data-required="1" class="span6 m-wrap" onblur="valid_username(this)"/>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">密码<span class="required">*</span></label>
													<div class="controls" align="left">
														<input name="password" type="password" class="span6 m-wrap" onblur="valid_password(this)"/>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">重复密码<span class="required">*</span></label>
													<div class="controls" align="left">
														<input name="repwd" type="password" class="span6 m-wrap" onblur="valid_repassword(this)"/>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">邮箱<span class="required">*</span></label>
													<div class="controls" align="left">
														<input name="email" type="text" class="span6 m-wrap" onblur="valid_email(this);"/>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">企业法人代表名字<span class="required">*</span></label>
													<div class="controls" align="left">
														<input name="bossName" type="text" class="span6 m-wrap"/>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">身份证号<span class="required">*</span></label>
													<div class="controls" align="left">
														<input name="registrantId" type="text" class="span6 m-wrap"/>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">法人代表身份证正面<span class="required">*</span></label>
													<div class="controls" align="left">
														<div class="uploader">
															<input class="input-file uniform_on" id="idFrontUrl" name="files" type="file">
															<span class="filename" style="user-select: none;">No file selected</span>
															<span class="action" style="user-select: none;">上传正面</span>
														</div>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">法人代表身份证反面<span class="required">*</span></label>
													<div class="controls" align="left">
														<div class="uploader">
															<input class="input-file uniform_on" id="idBackUrl" name="files" type="file">
															<span class="filename" style="user-select: none;">No file selected</span>
															<span class="action" style="user-select: none;">上传反面</span>
														</div>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">企业名称<span class="required">*</span></label>
													<div class="controls" align="left">
														<input type="text" name="companyName" data-required="1" class="span6 m-wrap"/>
													</div>
												</div>		
												<div class="control-group">
													<label class="control-label">企业类型<span class="required">*</span></label>
													<div class="controls" align="left">
														<input type="text" name="type" data-required="1" class="span6 m-wrap"/>
													</div>
												</div>		
												<div class="control-group">
													<label class="control-label">营业执照<span class="required">*</span></label>
													<div class="controls" align="left">
														<input type="text" name="businessLicenceId" data-required="1" class="span6 m-wrap"/>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">营业执照图片<span class="required">*</span></label>
													<div class="controls" align="left">
														<div class="uploader" id="uniform-fileInput">
															<input class="input-file uniform_on" id="fileBusinessLicenceUrl" name="files" type="file">
															<span class="filename" style="user-select: none;">No file selected</span>
															<span class="action" style="user-select: none;">上传营业执照图片</span>
														</div>
													</div>
												</div>
												<div class="control-group">
												  <label class="control-label">地区</label>
												  <div class="controls" align="left">
													  <div>
														  <select name="province" id="cmbProvince_com" style="width: 15%;"></select>
														  <select name="city" id="cmbCity_com" style="width: 18%;"></select>
														  <select name="area" id="cmbArea_com" style="width: 18%;"></select>
														  <script type="text/javascript">
															  addressInit('cmbProvince_com', 'cmbCity_com', 'cmbArea_com', '陕西', '宝鸡市', '金台区');
														  </script>
													  </div>
												  </div>
												</div>
												<div class="form-actions">
													<button type="submit" class="btn btn-primary">注 册</button>
													<button type="reset" class="btn">重置</button>
												</div>
											</form>
										</div>

										<!--事业单位用户-->
										<div id="tab3" class="tab-pane">
											<form action="user/register" method="post" id="formInstitutionUser" class="form-horizontal" enctype="multipart/form-data">
												<!-- 设置用户类型：事业单位,2 -->
												<input type="hidden" name="userType" value="事业单位"/>

												<div class="control-group">
													<label class="control-label">用户名<span class="required">*</span></label>
													<div class="controls" align="left">
														<input type="text" name="userName" data-required="1" class="span6 m-wrap" onblur="valid_username(this)"/>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">密码<span class="required">*</span></label>
													<div class="controls" align="left">
														<input name="password" type="password" class="span6 m-wrap" onblur="valid_password(this)"/>
													</div>
												</div>												
												<div class="control-group">
													<label class="control-label">重复密码<span class="required">*</span></label>
													<div class="controls" align="left">
														<input name="repwd" type="password" class="span6 m-wrap" onblur="valid_repassword(this)"/>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">邮箱<span class="required">*</span></label>
													<div class="controls" align="left">
														<input name="email" type="text" class="span6 m-wrap" onblur="valid_email(this);"/>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">事业单位法人代表名<span class="required">*</span></label>
													<div class="controls" align="left">
														<input name="bossName" type="text" class="span6 m-wrap"/>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">身份证号<span class="required">*</span></label>
													<div class="controls" align="left">
														<input name="registrantId" type="text" class="span6 m-wrap"/>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">法人代表身份证正面<span class="required">*</span></label>
													<div class="controls" align="left">
														<div class="uploader">
															<input class="input-file uniform_on" id="filegovIdFrontUrl" name="files" type="file">
															<span class="filename" style="user-select: none;">No file selected</span>
															<span class="action" style="user-select: none;">上传身份证正面</span>
														</div>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">法人代表身份证反面<span class="required">*</span></label>
													<div class="controls" align="left">
														<div class="uploader">
															<input class="input-file uniform_on" id="filegovIdBackUrl" name="files" type="file">
															<span class="filename" style="user-select: none;">No file selected</span>
															<span class="action" style="user-select: none;">上传身份证反面</span>
														</div>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">事业单位名称<span class="required">*</span></label>
													<div class="controls" align="left">
														<input type="text" name="companyName" data-required="1" class="span6 m-wrap"/>
													</div>
												</div>		
												<div class="control-group">
													<label class="control-label">事业单位类型<span class="required">*</span></label>
													<div class="controls" align="left">
														<input type="text" name="type" data-required="1" class="span6 m-wrap"/>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">事业单位登记号<span class="required">*</span></label>
													<div class="controls" align="left">
														<input type="text" name="institutionCode" data-required="1" class="span6 m-wrap"/>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">事业单位登记图片<span class="required">*</span></label>
													<div class="controls" align="left">
														<div class="uploader">
															<input class="input-file uniform_on" id="institutionCodeUrl" name="files" type="file">
															<span class="filename" style="user-select: none;">No file selected</span>
															<span class="action" style="user-select: none;">事业单位登记图片</span>
														</div>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">地区</label>
													<div class="controls" align="left">
														<div>
															<select name="province" id="cmbProvince_institution" style="width: 15%;"></select>
															<select name="city" id="cmbCity_institution" style="width: 18%;"></select>
															<select name="area" id="cmbArea_institution" style="width: 18%;"></select>
															<script type="text/javascript">
																addressInit('cmbProvince_institution', 'cmbCity_institution', 'cmbArea_institution', '陕西', '宝鸡市', '金台区');
															</script>
														</div>
													</div>
												</div>
												<div class="form-actions">
													<button type="submit" class="btn btn-primary">注 册</button>
													<button type="reset" class="btn">重置</button>
												</div>
											</form>
										</div>

										<!--政府用户-->
										<div id="tab4" class="tab-pane">
											<form action="user/register" method="post" id="form_gov" class="form-horizontal" enctype="multipart/form-data">
												<!-- 设置用户类型：政府,1 -->
												<input type="hidden" name="userType" value="政府"/>
												<div class="control-group">
													<label class="control-label">用户名<span class="required">*</span></label>
													<div class="controls" align="left">
														<input type="text" name="userName" data-required="1" class="span6 m-wrap" onblur="valid_username(this)"/>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">密码<span class="required">*</span></label>
													<div class="controls" align="left">
														<input name="password" type="password" class="span6 m-wrap" onblur="valid_password(this)"/>
													</div>
												</div>												
												<div class="control-group">
													<label class="control-label">重复密码<span class="required">*</span></label>
													<div class="controls" align="left">
														<input name="repwd" type="password" class="span6 m-wrap" onblur="valid_repassword(this)"/>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">邮箱<span class="required">*</span></label>
													<div class="controls" align="left">
														<input name="email" type="text" class="span6 m-wrap" onblur="valid_email(this);" />
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">政府法人代表名<span class="required">*</span></label>
													<div class="controls" align="left">
														<input name="bossName" type="text" class="span6 m-wrap"/>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">政府名称<span class="required">*</span></label>
													<div class="controls" align="left">
														<input type="text" name="govName" data-required="1" class="span6 m-wrap"/>
													</div>
												</div>	
												<div class="control-group">
													<label class="control-label">政府类型<span class="required">*</span></label>
													<div class="controls" align="left">
														<input type="text" name="type" data-required="1" class="span6 m-wrap"/>
													</div>
												</div>	
												<div class="control-group">
													<label class="control-label">组织机构代码<span class="required">*</span></label>
													<div class="controls" align="left">
														<input type="text" name="govCode" data-required="1" class="span6 m-wrap"/>
													</div>
												</div>		
												<div class="control-group">
													<label class="control-label">身份证号<span class="required">*</span></label>
													<div class="controls" align="left">
														<input name="registrantId" type="text" class="span6 m-wrap"/>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">法人代表身份证正面<span class="required">*</span></label>
													<div class="controls" align="left">
														<div class="uploader">
															<input class="input-file uniform_on" id="govIdFrontUrl" name="files" type="file">
															<span class="filename" style="user-select: none;">No file selected</span>
															<span class="action" style="user-select: none;">上传身份证正面</span>
														</div>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">法人代表身份证反面<span class="required">*</span></label>
													<div class="controls" align="left">
														<div class="uploader">
															<input class="input-file uniform_on" id="govIdBackUrl" name="files" type="file">
															<span class="filename" style="user-select: none;">No file selected</span>
															<span class="action" style="user-select: none;">上传身份证反面</span>
														</div>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">组织机构图片<span class="required">*</span></label>
													<div class="controls" align="left">
														<div class="uploader">
															<input class="input-file uniform_on" id="govCodeUrl" name="files" type="file">
															<span class="filename" style="user-select: none;">No file selected</span>
															<span class="action" style="user-select: none;">组织机构图片</span>
														</div>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">地区</label>
													<div class="controls" align="left">
														<div>
															<select name="province" id="cmbProvince_gov" style="width: 15%;"></select>
															<select name="city" id="cmbCity_gov" style="width: 18%;"></select>
															<select name="area" id="cmbArea_gov" style="width: 18%;"></select>
															<script type="text/javascript">
																addressInit('cmbProvince_gov', 'cmbCity_gov', 'cmbArea_gov', '陕西', '宝鸡市', '金台区');
															</script>
														</div>
													</div>
												</div>
												<div class="form-actions">
													<button type="submit" class="btn btn-primary">注 册</button>
													<button type="reset" class="btn">重置</button>
												</div>
											</form>
										</div>

										<!-- 管理员 -->
										<div id="tab5" class="tab-pane active">
											<form action="user/register" method="post" id="formAdmin" class="form-horizontal" enctype="multipart/form-data">
												<!-- 设置用户类型：管理员,0 -->
												<input type="hidden" name="userType" value="管理员"/>
												<fieldset>
													<div class="control-group">
														<label class="control-label">用户名<span class="required">*</span></label>
														<div class="controls" align="left">
															<input type="text" name="userName" data-required="1" class="span6 m-wrap" onblur="valid_username(this)"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">密码<span class="required">*</span></label>
														<div class="controls" align="left">
															<input name="password" type="password" class="span6 m-wrap" onblur="valid_password(this)"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">重复密码<span class="required">*</span></label>
														<div class="controls" align="left">
															<input name="repwd" type="password" class="span6 m-wrap" onblur="valid_repassword(this)"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">手机<span class="required">*</span></label>
														<div class="controls" align="left">
															<input name="phoneNumber" type="text" class="span6 m-wrap" onblur="valid_telphone(this)"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">邮箱<span class="required">*</span></label>
														<div class="controls" align="left">
															<input name="email" type="text" class="span6 m-wrap" onblur="valid_email(this);"/>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">真实姓名<span class="required">*</span></label>
														<div class="controls" align="left">
															<input name="realName" type="text" class="span6 m-wrap"/>
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


														<div class="form-actions">
															<button type="submit" class="btn btn-primary">注 册</button>
															<button type="reset" class="btn">重置</button>
														</div>
													</div>
												</fieldset>
											</form>
										</div>
									</div>
									</center>
								</div>
                        </div>
                        <!-- /block -->
                    </div>
                     <!-- wizard -->
	            <!-- /wizard -->
                </div>
            </div>
            <hr>
        </div>
        <!--/.fluid-container-->
        <link href="foreground/vendors/datepicker.css" rel="stylesheet" media="screen">
        <link href="foreground/vendors/uniform.default.css" rel="stylesheet" media="screen">
        <link href="foreground/vendors/chosen.min.css" rel="stylesheet" media="screen">

        <link href="foreground/vendors/wysiwyg/bootstrap-wysihtml5.css" rel="stylesheet" media="screen">

        <script src="foreground/bootstrap/js/bootstrap.min.js"></script>
        <script src="foreground/vendors/jquery.uniform.min.js"></script>
        <script src="foreground/vendors/chosen.jquery.min.js"></script>
        <script src="foreground/vendors/bootstrap-datepicker.js"></script>

        <script src="foreground/vendors/wysiwyg/wysihtml5-0.3.0.js"></script>
        <script src="foreground/vendors/wysiwyg/bootstrap-wysihtml5.js"></script>

        <script src="foreground/vendors/wizard/jquery.bootstrap.wizard.min.js"></script>

	<script type="text/javascript" src="foreground/vendors/jquery-validation/dist/jquery.validate.min.js"></script>
	<script src="foreground/assets/form-validation.js"></script>
        
	<script src="foreground/assets/scripts.js"></script>

		<script>
			jQuery(document).ready(function() {
			   FormValidation.init();
			});
	

        $(function() {
            $(".datepicker").datepicker();
            $(".uniform_on").uniform();
            $(".chzn-select").chosen();
            $('.textarea').wysihtml5();

            $('#rootwizard').bootstrapWizard({onTabShow: function(tab, navigation, index) {
                var $total = navigation.find('li').length;
                var $current = index+1;
                var $percent = ($current/$total) * 100;
                $('#rootwizard').find('.bar').css({width:$percent+'%'});
                // If it's the last tab then hide the last button and show the finish instead
                if($current >= $total) {
                    $('#rootwizard').find('.pager .next').hide();
                    $('#rootwizard').find('.pager .finish').show();
                    $('#rootwizard').find('.pager .finish').removeClass('disabled');
                } else {
                    $('#rootwizard').find('.pager .next').show();
                    $('#rootwizard').find('.pager .finish').hide();
                }
            }});
            $('#rootwizard .finish').click(function() {
                alert('Finished!, Starting over!');
                $('#rootwizard').find("a[href*='tab1']").trigger('click');
            });
        });
        </script>
    </body>

	<!-- 正则表达式验证js -->
	<script src="foreground/js/validationForms.js"></script>
	<script>
		var newObj_propt = "<span id='lab_propt' style='color: red; float:right; width:50%; margin-top:5px;'></span>";
		//验证用户名是否存在、格式是否正确
		function valid_username(obj) {
			var isStr = IsString(obj.value);
			$("#lab_propt").remove();
			if (!isStr){
				$(obj).parent().append($(newObj_propt).text("用户名格式错误"));
				return false;
			}else{
				$.ajax({
					type : "post",
					async : false,
					url : "user/getUserByUsername?username=" + obj.value,
					success : function (data){
						if (data == 'isNotExist'){
							return true;
						}else{
							$(obj).parent().append($(newObj_propt).text("该用户名已存在"));
							return false;
						}
					}
				});
			}
		}
		//验证密码格式是否正确
		function valid_password(obj){
			var isStr = IsString(obj.value);
			$("#lab_propt").remove();
			if (!isStr){
				$(obj).parent().append($(newObj_propt).text("密码格式错误"));
				return false;
			}else{
				return true;
			}
		}
		//验证重复密码
		function valid_repassword(obj){
			var password = $(obj).parent().parent().siblings(".control-group").children().children("input[name='password']").val();
			console.log(password);
			$("#lab_propt").remove();
			if (password != obj.value){
				$(obj).parent().append($(newObj_propt).text("与密码不同"));
				return false;
			}else{
				return true;
			}
		}
		//验证手机格式
		function valid_telphone(obj){
			var isStr = IsString(obj.value);
			$("#lab_propt").remove();
			if (!isStr){
				$(obj).parent().append($(newObj_propt).text("手机格式错误"));
				return false;
			}else{
				return true;
			}
		}
		//验证邮箱格式、该邮箱下用户是否存在
		function valid_email(obj) {
			var isStr = validation_email(obj.value);
			var isTure = false;
//			$(obj).parent().append($(newObj).text("qqq"));
			$("#lab_propt").remove();
			if(!isStr){
				$(obj).parent().after($(newObj_propt).text("邮箱格式错误"));
				return false;
			}else{
				$("#lab_propt").text("");
				$.ajax({
					type : "post",
					async : false,
					url : "user/getUserByEmail?email="+obj.value,
					success : function (data) {
						if(data == 'isNotExist'){
							isTure = true;
						}else{
							$(obj).parent().after($(newObj_propt).text("该邮箱已注册"));
							isTure = false;
						}
					}
				});
				return isTure;
			}
		}

	</script>

</html>