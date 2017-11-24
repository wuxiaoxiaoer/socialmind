<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>foreground/"/>
    <title>热度图</title>
    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <link href="assets/styles.css" rel="stylesheet" media="screen">
    <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="vendors/flot/excanvas.min.js"></script><![endif]-->
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script src="vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
</head>

<body>

<!-- 引入头模板 -->
<jsp:include page="/static/fore_header.jsp"/>




<div class="container-fluid" style="margin-top: 4%;">
    <div class="row-fluid">
        <div class="span3" id="sidebar">
            <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                <li>
                    <a href="#1"><i class="icon-chevron-right"></i> 事件概况</a>
                </li>
                <li>
                    <a href="#2"><i class="icon-chevron-right"></i>事件热度变化</a>
                </li>
                <li>
                    <a href="#3"><i class="icon-chevron-right"></i>关键词云</a>
                </li>
                <li>
                    <a href="#4"><i class="icon-chevron-right"></i> 关联词</a>
                </li>
                <li>
                    <a href="#5"><i class="icon-chevron-right"></i> 各职业事件热度</a>
                </li>
                <li>
                    <a href="#6"><i class="icon-chevron-right"></i> 各年龄段事件热度</a>
                </li>
                <li>
                    <a href="#7"><i class="icon-chevron-right"></i> 各性别事件热度</a>
                </li>
                <li>
                    <a href="#8"><i class="icon-chevron-right"></i> 事件媒体来源</a>
                </li>
                <li>
                    <a href="#9"><i class="icon-chevron-right"></i> 各地域事件热度</a>
                </li>

            </ul>
        </div>
        <!--/span-->
        <div class="span9" id="content">

            <h2>某事件热度分析</h2>


            <div class="navbar">
                <div class="navbar-inner">
                    <ul class="breadcrumb">

                        <i class="icon-chevron-right show-sidebar" style="display:none;"><a href='#' title="Show Sidebar" rel='tooltip'>&nbsp;</a></i>
                        <li>
                            <button class="btn">24 小 时</button>
                        </li>

                        <li>
                            <button class="btn">最近一周</button>
                        </li>
                        <li>
                            <button class="btn">自 定 义</button>
                        </li>
                        <li class="active"></li>
                    </ul>
                </div>
            </div>






            <div class="row-fluid">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left" id="1">事件概况</div>
                    </div>
                    <div class="block-content collapse in">
                        <div class="span12">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>热度值</th>
                                    <th>热度同比</th>
                                    <th>热度变化</th>
                                    <th>该事件信息量</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>7%</td>
                                    <td>2.7</td>
                                    <td>242342</td>
                                </tr>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- /block -->
            </div>



            <!-- morris stacked chart -->
            <div class="row-fluid">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left" id="2">事件热度变化</div>

                    </div>
                    <div class="block-content collapse in">
                        <div class="span12">
                            <div id="heatChangeChart" style="height: 400px;">

                            </div>
                        </div>
                    </div>
                </div>
                <!-- /block -->
            </div>


            <div class="row-fluid">
                <div class="span6">
                    <!-- block -->
                    <div class="block">
                        <div class="navbar navbar-inner block-header">
                            <div class="muted pull-left" id="3">关键词云</div>
                        </div>
                        <div class="block-content collapse in">
                            <div class="span12">
                                <div style="width:100%;height:250px">
                                    <img src="foreground/images/词云.png" >
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /block -->
                </div>
                <div class="span6">
                    <!-- block -->
                    <div class="block">
                        <div class="navbar navbar-inner block-header">
                            <div class="muted pull-left" id="4">关联词</div>
                        </div>
                        <div class="block-content collapse in">
                            <div class="span12">
                                <div style="width:100%;height:250px">
                                    <img src="foreground/images/关联.png" >
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /block -->
                </div>
            </div>


            <div class="row-fluid">
                <div class="span6">
                    <!-- block -->
                    <div class="block">
                        <div class="navbar navbar-inner block-header">
                            <div class="muted pull-left" id="5">各职业中事件热度</div>
                        </div>
                        <div class="block-content collapse in">
                            <div class="span12">
                                <div style="width:100%;height:250px">
                                    <img src="foreground/images/职业.png" >
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /block -->
                </div>
                <div class="span6">
                    <!-- block -->
                    <div class="block">
                        <div class="navbar navbar-inner block-header">
                            <div class="muted pull-left" id="6">各年龄段中事件热度</div>
                        </div>
                        <div class="block-content collapse in">
                            <div class="span12">
                                <div style="width:100%;height:250px">
                                    <img src="foreground/images/职业.png" >
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /block -->
                </div>
            </div>


            <div class="row-fluid">
                <div class="span6">
                    <!-- block -->
                    <div class="block">
                        <div class="navbar navbar-inner block-header">
                            <div class="muted pull-left" id="7">各性别事件热度</div>
                        </div>
                        <div class="block-content collapse in">
                            <div class="span12">
                                <div id="sexHeatChangeChart" style="width:100%;height:250px">

                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /block -->
                </div>
                <div class="span6">
                    <!-- block -->
                    <div class="block">
                        <div class="navbar navbar-inner block-header">
                            <div class="muted pull-left" id="8">事件媒体来源</div>
                        </div>
                        <div class="block-content collapse in">
                            <div class="span12">
                                <div id="piechart2" style="width:100%;height:250px">
                                    <img src="foreground/images/来源.png" >
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /block -->
                </div>
            </div>






            <!-- morris bar & donut charts -->
            <div class="row-fluid section">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left" id="9">各地域事件热度</div>

                    </div>
                    <div class="block-content collapse in">
                        <div class="span6 ">
                            <h5>地域</h5>
                            <div  style="height: 300px;">
                                <img src="foreground/images/地域.png" ></div>
                        </div>
                        <div class="span5 ">
                            <h5>地域分布</h5>
                            <div  style="height: 300px;">
                                <img src="images/地域分布.png" ></div>
                        </div>
                    </div>
                </div>
                <!-- /block -->
            </div>




        </div>
    </div>
    <hr>
    <!-- 引入尾部模板 -->
    <jsp:include page="/static/fore_footer.jsp"></jsp:include>
</div>
<!--/.fluid-container-->
<link rel="stylesheet" href="vendors/morris/morris.css">


<script src="vendors/jquery-1.9.1.min.js"></script>
<script src="vendors/jquery.knob.js"></script>
<script src="vendors/raphael-min.js"></script>
<script src="vendors/morris/morris.min.js"></script>

<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="vendors/flot/jquery.flot.js"></script>
<script src="vendors/flot/jquery.flot.categories.js"></script>
<script src="vendors/flot/jquery.flot.pie.js"></script>
<script src="vendors/flot/jquery.flot.time.js"></script>
<script src="vendors/flot/jquery.flot.stack.js"></script>
<script src="vendors/flot/jquery.flot.resize.js"></script>


<script src="js/echarts.min.js"></script>

<script src="assets/scripts.js"></script>
<script>

    var heatChangeChart = echarts.init(document.getElementById('heatChangeChart'));

//    var date = [];
//    var data = [];

    var date = JSON.parse('${date}');
    var data = JSON.parse('${heat}');
//
//    for (var c_num = 0; c_num < category.length;  c_num++) {
//        categories[c_num] = {
//            name: category[c_num]
//        }
//    }



//    var base = +new Date(1968, 9, 3);
//    var oneDay = 24 * 3600 * 1000;
//    var date = [];
//    var data = [Math.random() * 300];
//
//    for (var i = 1; i < 20000; i++) {
//        var now = new Date(base += oneDay);
//        date.push([now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'));
//        data.push(Math.round((Math.random() - 0.5) * 20 + data[i - 1]));
//    }


    option = {
        tooltip: {
            trigger: 'axis',
            position: function (pt) {
                return [pt[0], '10%'];
            }
        },
        title: {
            left: 'center',
            text: '事件热度变化',
        },
        toolbox: {
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: date
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, '100%']
        },
        dataZoom: [{
            type: 'inside',
            start: 0,
            end: 100
        }, {
            start: 0,
            end: 10,
            handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
            handleSize: '80%',
            handleStyle: {
                color: '#fff',
                shadowBlur: 3,
                shadowColor: 'rgba(0, 0, 0, 0.6)',
                shadowOffsetX: 2,
                shadowOffsetY: 2
            }
        }],
        series: [
            {
                name:'热度值',
                type:'line',
                smooth:true,
                symbol: 'none',
                sampling: 'average',
                itemStyle: {
                    normal: {
                        color: 'rgb(255, 70, 131)'
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgb(255, 158, 68)'
                        }, {
                            offset: 1,
                            color: 'rgb(255, 70, 131)'
                        }])
                    }
                },
                data: data
            }
        ]
    };

    heatChangeChart.setOption(option);
</script>

<script>

    var sexHeatChangeChart = echarts.init(document.getElementById('sexHeatChangeChart'));


    var sexdate = JSON.parse('${sexdate}');
    var mheat = JSON.parse('${mheat}');
    var fheat = JSON.parse('${fheat}');


    Image : {show: true}
    option = {
        title : {
            text: '各性别中的热度变化',
            subtext: ''
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['男','女']
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                restore : {show: true},
                saveAs : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : sexdate
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'女',
                type:'line',
                smooth:true,
                itemStyle: {normal: {areaStyle: {type: 'default'}}},
                data:fheat
            },

            {
                name:'男',
                type:'line',
                smooth:true,
                itemStyle: {normal: {areaStyle: {type: 'default'}}},
                data:mheat
            }
        ]
    };

    sexHeatChangeChart.setOption(option);

</script>
</body>

</html>
