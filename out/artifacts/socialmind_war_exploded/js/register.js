var overall_user_name = "";
function checkUser(){
	var u_name = $.trim($("#u_name").val());
	if(u_name==""||u_name.length<6){
		$("#u_name_prompt").html("请输入6位数密码");
		$("#u_name_prompt").css("display","");
		return false;
	}else {
		$.ajax({
			type: 'post',
			url: 'user/isExistUser?u_name='+u_name,
			success:function(msg){
				if('success' == msg){
					$("#u_name_prompt").html("该用户名已被使用");
					$("#u_name_prompt").css("display","");
					return false;
				}
			}
		});
		$("#u_name_prompt").html("");
		$("#u_name_prompt").css("display","none");
		return true;
	}
}


function checkPwd(){
	var u_pwd = $("#u_pwd").val();
	if(u_pwd==""||u_pwd.length<6){
		$("#u_pwd_prompt").html("请输入6位数密码");
		$("#u_pwd_prompt").css("display","");
		return false;
	}else{
		$("#u_pwd_prompt").html("");
		$("#u_pwd_prompt").css("display","none");	
		return true;				
	}				
}

//修改个人信息检查密码
function modInfo_checkPwd(){
	var u_pwd = $("#u_pwd").val();
	if(u_pwd != '***'){
		if(u_pwd==""||u_pwd.length<6){
			$("#u_pwd_prompt").html("请输入6位数密码");
			$("#u_pwd_prompt").css("display","");
			return false;
		}else{
			$("#u_pwd_prompt").html("");
			$("#u_pwd_prompt").css("display","none");	
			return true;				
		}
	}
}

function check_R_Pwd(){
	var r_pwd = $("#u_r_pwd").val();
	if(r_pwd!=$("#u_pwd").val()){
		$("#u_r_pwd_prompt").html("两次密码不一致");
		$("#u_r_pwd_prompt").css("display","");
		return false;
	}else{
		$("#u_r_pwd_prompt").html("");
		$("#u_r_pwd_prompt").css("display","none");	
		return true;				
	}
}

function check_u_mail(){
	var u_mail = $("#u_mail").val();
	var reg = /[a-z0-9-]{1,30}@[a-z0-9-]{1,65}.[a-z]{3}/;
	if(!reg.test(u_mail)){
		$("#u_mail_prompt").html("邮箱有误");
		$("#u_mail_prompt").css("display","");		
		return false;			
	}else{
		$("#u_mail_prompt").html("");
		$("#u_mail_prompt").css("display","none");
		$("#btn_send_code").css("display","");
		$("#txt_verfication_code").css("display","inline");
		return true;			
	}
}

//修改个人信息
function modInfo_check_u_mail(user_email){
	var u_mail = $("#u_mail").val();
	if(user_email != u_mail){
		var reg = /[a-z0-9-]{1,30}@[a-z0-9-]{1,65}.[a-z]{3}/;
		if(!reg.test(u_mail)){
			$("#u_mail_prompt").html("邮箱有误");
			$("#u_mail_prompt").css("display","");		
			return false;			
		}else{
			$("#u_mail_prompt").html("");
			$("#u_mail_prompt").css("display","none");
			$("#btn_send_code").css("display","");
			$("#txt_verfication_code").css("display","inline");
			return true;			
		}
	}
}

function check_u_telphone(){
	var u_telphone = $("#u_telphone").val();
	var reg = /^1\d{10}$/;
	if(!reg.test(u_telphone)){
		$("#u_telphone_prompt").html("手机号有误");
		$("#u_telphone_prompt").css("display","");		
		return false;			
	}else{
		$("#u_telphone_prompt").html("");
		$("#u_telphone_prompt").css("display","none");	
		$("#btn_send_telphone_code").css("display","");
		$("#txt_verfication_telphone_code").css("display","inline");
		return true;
	}
}
var second = 10;
function send_verfication_code(){
	var u_mail = $("#u_mail").val();
	if(second == 10){
		$.ajax({
			type : 'post',
			url : 'user/register/sendMail?u_mail='+u_mail,
			success:function(msg){
				
			}
		});
	}
	if(second == 0){
		$("#btn_send_code").val("重发验证码");
		$("#btn_send_code").removeAttr("disabled");
		$("#btn_send_code").css("color","white");
		second = 10;					 
	}else{
		$("#btn_send_code").val("重新发送("+second+")");
		$("#btn_send_code").attr("disabled","disabled");
		$("#btn_send_code").css("color","grey");
		second--;
		setTimeout(function (){
			send_verfication_code();
		}, 1000);
	}
}

function send_verfication_telphone_code(){
	var u_telphone = $("#u_telphone").val();
	if (check_u_telphone()) {
		$.ajax({
			type : 'post',
			url : '<%=basePath%>user/register/sendTelephoneCode?u_telphone='+u_telphone,
			success:function (msg){
				
			}
		});
	}
}

function registerCheck(){
	if(checkUser()&&checkPwd()&&check_R_Pwd()&&check_u_mail()&&check_u_telphone()&&valid_mail_code()){
		return true;
	}else{
		return false;
	}
}

//修改个人信息
function modInfoCheck(){
	if(modInfo_checkUser()&&modInfo_checkPwd()&&check_R_Pwd()&&modInfo_check_u_mail()&&check_u_telphone()&&valid_mail_code()){
		alert(123);
		return true;
	}else{
		return false;
	}
}