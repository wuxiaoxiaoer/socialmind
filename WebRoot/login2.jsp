<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>"/>
		<meta charset="utf-8">
	    <title>微博舆情分析系统</title>
	    <script type="text/javascript" src="<%=basePath%>foreground/js/jquery-1.12.0.min.js"></script>
	</head>
	<body>
		<input type="button" value="点击" onclick="loadJson()"/>

	</body>
	<script type="text/javascript">
		function loadJson(){
			var nameArr = [];
			var numArr = [];
			$.ajax({
				type : "POST",
				dataType : "json",
				url : "lazy.json",
				success : function (data) {
					$.each(data, function (index, obj) {
						$.each(obj, function (i, object) {
							console.log(object["data"].name);
							nameArr[i] = object["data"].name;
						})
					})
					var flag = 0;
					var target = [];
					for(var i in nameArr){
						if (isExist(nameArr[i] , target, 0) == -1){
							target[flag] = nameArr[i];
							numArr[flag] = 1;
							flag ++;
						}else{
							var ii = isExist(nameArr[i] , target, 0);
							numArr[ii] += 1;
						}
					}
					for (var i in target){
						alert(target[i] + " : " + numArr[i]);
					}
				}
			});
		}

		function isExist( elem, arr, i ) {
			core_indexOf = Array.prototype.indexOf;
			var len;
			if ( arr ) {
				if ( core_indexOf ) {
					return core_indexOf.call( arr, elem, i );
				}

				len = arr.length;
				i = i ? i < 0 ? Math.max( 0, len + i ) : i : 0;

				for ( ; i < len; i++ ) {
					// Skip accessing in sparse arrays
					if ( i in arr && arr[ i ] === elem ) {
						return i;
					}
				}
			}
			return -1;
		}

	</script>

</html>
































