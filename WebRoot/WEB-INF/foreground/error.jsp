<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	 <script language="javascript">

		<!--
			text = "";
			i = 0;
			function flash() {
				str = text.charAt(i);
				str = "<b>" + str + "</b>";
				leftStr = text.substr(0, i);
				rightStr = text.substr(i + 1, text.length - i);
				txt.innerHTML = leftStr + str + rightStr;
				i++;
				if (i >= text.length)
					i = 0;
				window.history.go(-1);
				setTimeout("flash()", 1000);
			}
		//-->
		</script>
</head>
<body onLoad="flash()" bgcolor="#fafbfb">
<div id="txt" style="font-size:30px;font-family:Arial;margin-top: 300; color:#FF6600" align="center" >
</div>
</body>
</html>
