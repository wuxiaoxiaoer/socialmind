<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html lang="en">
	
<head>
		<title>系统用户分析</title><meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="admin/css/bootstrap.min.css" />
		<link rel="stylesheet" href="admin/css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" href="admin/css/maruti-style.css" />
		<link rel="stylesheet" href="admin/css/maruti-media.css" class="skin-color" />
	<script src="foreground/js/echarts.min.js"></script>
</head>
<body>

<!-- 引入后台头模板-->
<jsp:include page="/static/admin_header.jsp"></jsp:include>

<!--close-top-Header-menu-->
		<div id="content" style="width:100%;height:100%;">
			<div id="content-header">
			<div id="breadcrumb">
				<a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
				<a href="sysusersAnalysis?unit=week&times=7" class="current">用户分析</a>
			</div>
				<div style="margin: 2% 0 2% 2%">
					<h2>用户分析</h2>
					<a href="sysusersAnalysis?unit=week&times=7" class="btn btn-primary btn-large">用户增长</a>
					<a href="userAnalysisAttr" class="btn btn-primary btn-large">用户属性</a>
				</div>
			</div>
			
			<div class="widget-box" style="width:100%; align: center;">
				<div class="widget-title">
					<span class="icon">
						<i class="icon-eye-open"></i>
					</span>
					<h5>用户增长</h5>

				</div>

				<div class="widget-content nopadding">
					<div style="margin:4% 0% 0% 20%;">
						<a class="btn btn-success" href="<%=basePath%>sysusersAnalysis?unit=week&times=7">一周内</a>
						<a class="btn btn-primary" href="<%=basePath%>sysusersAnalysis?unit=month&times=15">一个月内</a>
						<a class="btn btn-danger" href="<%=basePath%>sysusersAnalysis?unit=year&times=12">一年内</a>
						<a class="btn btn-warning" href="<%=basePath%>sysusersAnalysis?unit=ALL&times=10">全部</a>
					</div>

					<div id="main" style="width: 80%; height:600px;">123</div>

				</div>
			</div>

		</div>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'));
    myChart.title = '折柱混合';

    option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            data:['总用户数量', '管理员','政府用户','事业单位用户','企业用户','个人用户', '总用户折线', '管理员折线', '政府折线', '事业单位折线', '企业折线', '个人折线']
        },
        xAxis: [
            {
                type: 'category',
                data: ${units},
                axisPointer: {
                    type: 'shadow'
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '个数',
                min: ${min},
                max: ${max},
                interval: ${interval},
                axisLabel: {
                    formatter: '{value} '
                }
            },
            {
                type: 'value',
                name: '个数',
                min: ${min},
                max: ${max},
                interval: ${interval},
                axisLabel: {
                    formatter: '{value} '
                }
            }
        ],
        series: [
            {
                name:'总用户数量',
                type:'bar',
                data:${counts}
            },
            {
                name:'管理员',
                type:'bar',
                data:${adminCounts}
            },
            {
                name:'政府用户',
                type:'bar',
                data:${govCounts}
            },
            {
                name:'事业单位用户',
                type:'bar',
                data:${insCounts}
            },
            {
                name:'企业用户',
                type:'bar',
                data:${comCounts}
            },
            {
                name:'个人用户',
                type:'bar',
                data:${perCounts}
            },
            {
                name:'总用户折线',
                type:'line',
                yAxisIndex: 1,
                data:${counts}
            },
            {
                name:'管理员折线',
                type:'line',
                yAxisIndex: 1,
                data:${adminCounts}
            },
            {
                name:'政府折线',
                type:'line',
                yAxisIndex: 1,
                data:${govCounts}
            },
            {
                name:'事业单位折线',
                type:'line',
                yAxisIndex: 1,
                data:${insCounts}
            },
            {
                name:'企业折线',
                type:'line',
                yAxisIndex: 1,
                data:${comCounts}
            },
            {
                name:'个人折线',
                type:'line',
                yAxisIndex: 1,
                data:${perCounts}
            }
        ]
    };
    //设置显示
    myChart.setOption(option);
</script>

<!-- 引入后台尾部模板 -->
<jsp:include page="/static/admin_footer.jsp"></jsp:include>

<script src="admin/js/jquery.min.js"></script>
<script src="admin/js/jquery.ui.custom.js"></script>
<script src="admin/js/bootstrap.min.js"></script>
<script src="admin/js/maruti.js"></script>
</body>

</html>
