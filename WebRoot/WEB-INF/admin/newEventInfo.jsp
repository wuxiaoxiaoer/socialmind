<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/1/23
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
    <title>舆情事件分析</title><meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="admin/css/bootstrap.min.css" />
    <link rel="stylesheet" href="admin/css/bootstrap-responsive.min.css" />
    <link rel="stylesheet" href="admin/css/maruti-style.css" />
    <link rel="stylesheet" href="admin/css/maruti-media.css" class="skin-color" />
    <script src="foreground/js/echarts.min.js"></script>
    <script src="foreground/js/jquery.min.js"></script>
</head>
<body>

<!-- 引入后台头模板-->
<jsp:include page="/static/admin_header.jsp"></jsp:include>

<!--close-top-Header-menu-->
<div id="content" style="width:100%; height:100%;">
    <div id="content-header">
        <div id="breadcrumb">
            <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
            <a href="" class="current">舆情分析</a>
        </div>

    </div>
    <c:if test="${monthCount!=null}">
        <div class="widget-box" style="width:60%; margin-left: 15%;" align="center">
            <div class="widget-title">
                            <span class="icon">
                                <i class="icon-eye-open"></i>
                            </span>
                <h5>${object}年事件数</h5>

            </div>

            <div class="widget-content nopadding">
                <div id="addEvent" style="width: 80%; height:400px;">

                </div>
            </div>
        </div>
    </c:if>

    <c:if test="${dayCount!=null}">
        <div class="widget-box" style="width:60%; margin-left: 15%;" align="center">
            <div class="widget-title">
                            <span class="icon">
                                <i class="icon-eye-open"></i>
                            </span>
                <h5>${year}年${object}月事件数</h5>

            </div>

            <div class="widget-content nopadding">
                <div id="addEvent1" style="width: 80%; height:400px;">
                </div>
            </div>
        </div>
    </c:if>

</div>

<script type="text/javascript">
    var addEvent = echarts.init(document.getElementById('addEvent'));
    var option = {
        color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ${month},
                axisTick: {
                    alignWithLabel: true
                },
                axisLabel: {
                    formatter: '{value}月'
                }
            }
        ],
        yAxis : [
            {
                type : 'value',
                axisLabel: {
                    formatter: '{value}条'
                }
            }
        ],
        series : [
            {
                name:'数量',
                type:'bar',
                barWidth: '60%',
                data:${monthCount}
            }
        ]
    };

    addEvent.setOption(option);
    addEvent.on('click', function (params) {
        window.open('http://localhost:8080/socialmind/clickBackEvent?objecType="年"&&year=${object}&&object=' + params.name);
    });
</script>


<script>
    var addEvent1 = echarts.init(document.getElementById('addEvent1'));
    var option1 = {
        color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ${days},
                axisTick: {
                    alignWithLabel: true
                },
                axisLabel: {
                    formatter: '{value}日'
                }
            }
        ],
        yAxis : [
            {
                type : 'value',
                axisLabel: {
                    formatter: '{value}条'
                }
            }
        ],
        series : [
            {
                name:'数量',
                type:'bar',
                barWidth: '60%',
                data:${dayCount}
            }
        ]
    };

    addEvent1.setOption(option1);
    addEvent1.on('click', function (params) {
        alert(params.name);
        <%--window.open('http://localhost:8080/socialmind/clickBackEvent?objecType="年"&&year=${object}&&object=' + params.name);--%>
    });
</script>

<script>

</script>

<!-- 引入后台尾部模板 -->
<jsp:include page="/static/admin_footer.jsp"></jsp:include>

<script src="admin/js/jquery.min.js"></script>
<script src="admin/js/jquery.ui.custom.js"></script>
<script src="admin/js/bootstrap.min.js"></script>
<script src="admin/js/maruti.js"></script>
</body>

</html>

