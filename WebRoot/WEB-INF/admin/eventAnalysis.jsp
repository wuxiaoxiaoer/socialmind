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
        <div style=" margin-left: 15%;">
            <h2>事件分析</h2>
            <a href="" class="btn btn-primary btn-large">新增事件数</a>
            <a href="" class="btn btn-primary btn-large">最活跃事件</a>
            <a href="" class="btn btn-primary btn-large">事件情感统计</a>
            <a href="" class="btn btn-primary btn-large">事件访问次数</a>
            <a href="" class="btn btn-primary btn-large">事件延续时间</a>

        </div>
    </div>

    <div class="widget-box" style="width:60%; margin-left: 15%;" align="center">
        <div class="widget-title">
						<span class="icon">
							<i class="icon-eye-open"></i>
						</span>
            <h5>新增事件数</h5>
            <label>时间段：
                <select size="1" name="example_length" aria-controls="example">
                    <option value="1" selected="selected">年</option>
                    <option value="2">月</option>
                    <option value="3">周</option>
                </select>
            </label>
        </div>

        <div class="widget-content nopadding">
            <div id="addEvent" style="width: 80%; height:400px;">

            </div>
        </div>
    </div>

    <div class="widget-box" style="width:60%; margin-left: 15%;" align="center">
        <div class="widget-title">
						<span class="icon">
							<i class="icon-eye-open"></i>
						</span>
            <h5>最活跃事件</h5>
        </div>

        <div class="widget-content nopadding">
            <div id="activeEvent" style="width: 80%; height:600px;">123123</div>
        </div>
    </div>
    <div class="widget-box" style="width:60%; margin-left: 15%;" align="center">
        <div class="widget-title">
						<span class="icon">
							<i class="icon-eye-open"></i>
						</span>
            <h5>事件情感统计</h5>
        </div>

        <div class="widget-content nopadding">
            <div id="sensitiveInfo" style="width: 80%; height:600px;">123123</div>
        </div>
    </div>

    <div class="widget-box" style="width:60%; margin-left: 15%;" align="center">
        <div class="widget-title">
						<span class="icon">
							<i class="icon-eye-open"></i>
						</span>
            <h5>事件访问次数</h5>
        </div>

        <div class="widget-content nopadding">
            <div id="visitNum" style="width: 80%; height:600px;">123123</div>
        </div>
    </div>

    <div class="widget-box" style="width:60%; margin-left: 15%;" align="center">
        <div class="widget-title">
						<span class="icon">
							<i class="icon-eye-open"></i>
						</span>
            <h5>事件延续时间</h5>
        </div>

        <div class="widget-content nopadding">
            <div id="periodTime" style="width: 80%; height:600px;">123123</div>
        </div>
    </div>


</div>
<!-- 用户性别分布 -->
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
                data : ${years},
                axisTick: {
                    alignWithLabel: true
                },
                axisLabel: {
                    formatter: '{value}年'
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
                data:${yearCount}
            }
        ]
    };

    addEvent.setOption(option);
    addEvent.on('click', function (params) {
        alert(params.name);
        window.open('http://localhost:8080/socialmind/clickBackEvent?objecType="年"&&year=null&&object=' + params.name);
    });
</script>


<script>
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

