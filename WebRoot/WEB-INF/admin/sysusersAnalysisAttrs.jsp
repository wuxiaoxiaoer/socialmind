<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html lang="en">
	
<head>
		<title>系统用户分析 - 属性分析</title><meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="admin/css/bootstrap.min.css" />
		<link rel="stylesheet" href="admin/css/bootstrap-responsive.min.css" />
		<link rel="stylesheet" href="admin/css/maruti-style.css" />
		<link rel="stylesheet" href="admin/css/maruti-media.css" class="skin-color" />
		<script src="foreground/js/echarts.min.js"></script>
		<script src="foreground/js/china.js"></script>
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
						<a href="sysusersAnalysis?unit=week&times=7" class="current">用户分析</a>
					</div>
					<div style=" margin-left: 15%;">
						<h2>用户分析</h2>
						<a href="sysusersAnalysis?unit=week&times=7" class="btn btn-primary btn-large">用户增长</a>
						<a href="userAnalysisAttr" class="btn btn-primary btn-large">用户属性</a>
					</div>
				</div>

				<div class="widget-box" style="width:60%; margin-left: 15%;" align="center">
					<div class="widget-title">
						<span class="icon">
							<i class="icon-eye-open"></i>
						</span>
						<h5>用户性别</h5>

					</div>

					<div class="widget-content nopadding">
						<div id="sexInfo" style="width: 80%; height:600px;">123</div>
					</div>
				</div>

				<div class="widget-box" style="width:60%; margin-left: 15%;" align="center">
					<div class="widget-title">
						<span class="icon">
							<i class="icon-eye-open"></i>
						</span>
						<h5>用户年龄</h5>
					</div>

					<div class="widget-content nopadding">
						<div id="ageInfo" style="width: 80%; height:600px;">123123</div>
					</div>
				</div>
			<div class="widget-box" style="width:60%; margin-left: 15%;" align="center">
				<div class="widget-title">
						<span class="icon">
							<i class="icon-eye-open"></i>
						</span>
					<h5>用户星座分布</h5>
				</div>

				<div class="widget-content nopadding">
					<div id="constellationInfo" style="width: 80%; height:600px;">123123</div>
				</div>
			</div>

				<div class="widget-box" style="width:60%; margin-left: 15%;" align="center">
					<div class="widget-title">
						<span class="icon">
							<i class="icon-eye-open"></i>
						</span>
						<h5>用户地区分布${adminMap}</h5>
					</div>

					<div class="widget-content nopadding">
						<div id="areaInfo" style="width: 80%; height:600px;">123123</div>
					</div>
				</div>
			</div>
<!-- 用户性别分布 -->
<script type="text/javascript">
    var sexInfo = echarts.init(document.getElementById('sexInfo'));
    sexInfo.title = '环形图';
    option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data:['男','女']
        },
        series: [
            {
                name:'用户类型',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:[
                    {value:${menNum}, name:'男'},
                    {value:${womenNum}, name:'女'}
                ]
            }
        ]
    };
    //用户性别分布 - 设置显示
    sexInfo.setOption(option);
</script>

<!-- 用户年龄分布 -->
<script>
    var ageInfo = echarts.init(document.getElementById('ageInfo'));
    ageInfo.title = '坐标轴刻度与标签对齐';

    option = {
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
                name: '年龄',
                data : ${ageStages},
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value',
                name: '个数'
            }
        ],
        series : [
            {
                name:'人数',
                type:'bar',
                barWidth: '60%',
                data:${agesNum}
            }
        ]
    };
    //用户年龄分布 - 设置显示
    ageInfo.setOption(option);
</script>

<!-- 用户星座分布 -->
<script>

    var constellationInfo = echarts.init(document.getElementById('constellationInfo'));
    constellationInfo.title = '堆叠柱状图';

    option = {
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data:['管理员', '政府', '事业单位', '企业', '个人']
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
                data : ${constellationList}
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'管理员',
                type:'bar',
                stack: '广告',
                data:${adminConsNumList}
            },
            {
                name:'政府',
                type:'bar',
                stack: '广告',
                data:${govConsNumList}
            },
            {
                name:'事业单位',
                type:'bar',
                stack: '广告',
                data:${insConsNumList}
            },
            {
                name:'企业',
                type:'bar',
                stack: '广告',
                data:${comConsNumList}
            },
            {
                name:'个人',
                type:'bar',
                stack: '广告',
                data:${perConsNumList}
            }
        ]
    };
    //用户星座分布 - 设置显示
    constellationInfo.setOption(option);
</script>


<!-- 用户地区分布 -->
<script>
    var areaInfo = echarts.init(document.getElementById('areaInfo'));
    // 随机0-1000的数
    function randomData() {
        return Math.round(Math.random()*1000);
    }

    function getDatas(maps) {
        var jsonObj = maps;
        var length = 0;
        //获得json大小
        for (var k in jsonObj){
            length ++;
		}
//        var jsonObj = eval(adminJson);
		var objArr = new Array(length);
		var i = 0;
        for (var k in jsonObj){
            var rowobj = {};
            rowobj.name = k;
            rowobj.value = jsonObj[k];
//            console.log(rowobj);
            objArr[i] = rowobj;
            i = i + 1;
        }
        console.log(objArr);
//        console.log(objArr);
        return objArr;
    }
    // legend内容
    var legendData=['管理员','政府','事业单位','企业', '个人'];
    // legend自定义颜色 不设置有默认色
    var legendColor=['blue','gray','#000','cyan', 'red'];
    // 映射颜色  不设置有默认色
    var visColor=["#ffffff","#e8192f","#d6664d","#B9044E","#f0ba2e","#d3ce2b","#169A7f","#0b6573","#1BB3c8"];
    // seriesData Array [{name:'',type:'map',mapType:'china',
    //           label: { normal: {show: true},emphasis: { show: true}},data:[{name:'',value:''},...]},{...}]
	
    var seriseData=[
        {
            name: '管理员',
            type: 'map',
            mapType: 'china',
            label: { normal: {show: true},emphasis: { show: true}},
            data:getDatas(${adminJson})
//            data:[{name:'山东',value:1}]
        },
        {
            name: '政府',
            type: 'map',
            mapType: 'china',
            label: { normal: {show: true},emphasis: { show: true}},
            data: getDatas(${govJson})
        },
        {
            name: '事业单位',
            type: 'map',
            mapType: 'china',
            label: { normal: {show: true},emphasis: { show: true}},
            data: getDatas(${insJson})
        },
        {
            name: '企业',
            type: 'map',
            mapType: 'china',
            label: { normal: {show: true},emphasis: { show: true}},
            data:getDatas(${comJson})
        },
        {
            name: '个人',
            type: 'map',
            mapType: 'china',
            label: { normal: {show: true},emphasis: { show: true}},
            data:getDatas(${perJson})
        }
    ]

    option = {
        title: {
            text: '用户地区分布',
            left: 'left'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            right: '3%',
            bottom:'3%',
            data:legendData
        },
        color:legendColor,
        visualMap: {
            min: ${minNum},
            max: ${maxNum},
            left: 'left',
            bottom: '3%',
            text: ['高','低'], // 文本，默认为数值文本
            calculable: true,
            color:visColor
        },
        toolbox: {
            show: true,
            right: '3%',
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        series: seriseData
    };
    //地区分布 - 设置显示
    areaInfo.setOption(option);
</script>

<!-- 引入后台尾部模板 -->
<jsp:include page="/static/admin_footer.jsp"></jsp:include>

<script src="admin/js/jquery.min.js"></script>
<script src="admin/js/jquery.ui.custom.js"></script>
<script src="admin/js/bootstrap.min.js"></script>
<script src="admin/js/maruti.js"></script>
</body>

</html>
