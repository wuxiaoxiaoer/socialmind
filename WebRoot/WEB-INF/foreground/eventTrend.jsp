
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <link rel="stylesheet" type="text/css" href="timebar/css/history.css">
    <script type="text/javascript" src="./timebar/js/jquery.js"></script>
    <script type="text/javascript" src="./timebar/js/jquery.mousewheel.js"></script>
    <script type="text/javascript" src="./timebar/js/jquery.easing.js"></script>
    <script type="text/javascript" src="./timebar/js/history.js"></script>

</head>
<body>

<div id="arrow">
    <ul>
        <li class="arrowup"></li>
        <li class="arrowdown"></li>
    </ul>
</div>

<div id="history">

    <div class="title">
        <c:forEach items="${event}" var="e" varStatus="sts">
        <h2>${e.object.name}</h2>
        </c:forEach>
        <div id="circle">
            <div class="cmsk"></div>
            <div class="circlecontent">
                <c:forEach items="${year}" var="a" varStatus="sts">
                <div thisyear="${a}" class="timeblock">
                    <span class="numf">2</span>
                    <span class="nums">3</span>
                    <span class="numt">4</span>
                    <span class="numfo">5</span>
                    <div class="clear"></div>
                </div>
                </c:forEach>
                <div class="timeyear">YEAR</div>
            </div>
            <a class="clock"></a>
        </div>

    </div>

    <div id="content">
        <ul class="list">
            <c:forEach items="${artileList}" var="al" varStatus="sts">
            <li>

                <div class="liwrap">
                    <div class="lileft">
                        <div class="date">
                            <span class="md">${al.postTime}</span>
                        </div>
                    </div>

                    <div class="point"><b></b></div>

                    <div class="liright">
                        <div class="histt"><a>${al.title} 【${al.newsResource}】</a></div>
                        <div class="hisct"></div>
                    </div>
                </div>

            </li>
            </c:forEach>

        </ul>
    </div>
</div>

</body>
</html>