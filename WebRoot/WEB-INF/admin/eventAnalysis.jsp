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

<div>
<!-- 引入后台头模板-->
<jsp:include page="/static/admin_header.jsp"></jsp:include>

<!--close-top-Header-menu-->

    <div id="content">
        <div id="content-header">
            <div id="breadcrumb">
                <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
                <a href="" class="current">舆情分析</a>
            </div>
            <br>
            <div style=" margin-left: 15%;">

                <a onclick="document.getElementById('001').scrollIntoView();" class="btn btn-primary btn-large">事件访问次数</a>
                <a onclick="document.getElementById('002').scrollIntoView();" class="btn btn-primary btn-large">新增事件数</a>
                <a onclick="document.getElementById('003').scrollIntoView();" class="btn btn-primary btn-large">事件分类</a>
                <a onclick="document.getElementById('004').scrollIntoView();" class="btn btn-primary btn-large">事件延续时间</a>
                <a onclick="document.getElementById('005').scrollIntoView();" class="btn btn-primary btn-large">事件情感统计</a>
            </div>
        </div>

        <div class="container-fluid" style="width: 95%;">

            <div class="row-fluid">
                <div class="span6">
                    <div class="widget-box">
                        <div class="widget-title">
								<span class="icon">
									<i class="icon-signal"></i>
								</span>
                            <h5>全部事件统计</h5>
                        </div>
                        <div class="widget-content">
                            <div id="allevent" style="width: 100%;height: 300px"></div>
                        </div>
                    </div>
                </div>


                <div class="span6">
                    <div class="widget-box">
                        <div class="widget-title">
								<span class="icon">
									<i class="icon-signal"></i>
								</span>
                            <h5>全部实体统计</h5>
                        </div>
                        <div class="widget-content">
                            <div id="allobject" style="width: 100%;height: 300px"></div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row-fluid">
                <div class="span6">
                    <div class="widget-box">
                        <div class="widget-title">
								<span class="icon">
									<i class="icon-signal"></i>
								</span>
                            <h5>事件分类</h5>
                        </div>
                        <div class="widget-content">
                            <div id="classify" style="width: 100%;height: 300px"></div>
                        </div>
                    </div>
                </div>


                <div class="span6">
                    <div class="widget-box">
                        <div class="widget-title">
								<span class="icon">
									<i class="icon-signal"></i>
								</span>
                            <h5>事件访问量</h5>
                        </div>
                        <div class="widget-content">
                            <div id="visit" style="width: 100%;height: 300px"></div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row-fluid">
                <div class="span6">
                    <div class="widget-box">
                        <div class="widget-title">
								<span class="icon">
									<i class="icon-signal"></i>
								</span>
                            <h5>新增事件数</h5>
                        </div>
                        <div class="widget-content">
                            <div id="addEvent" style="width: 100%;height: 300px"></div>
                        </div>
                    </div>
                </div>


                <div class="span6">
                    <div class="widget-box">
                        <div class="widget-title">
								<span class="icon">
									<i class="icon-signal"></i>
								</span>
                            <h5>事件延续时间</h5>
                        </div>
                        <div class="widget-content">
                            <div id="periodTime" style="width: 100%;height: 300px"></div>
                        </div>
                    </div>
                </div>
            </div>


            <%--<div class="row-fluid">
                <div class="widget-box" id="005">
                    <div class="widget-title">
						<span class="icon">
							<i class="icon-eye-open"></i>
						</span>
                        <h5>事件情感统计</h5>
                    </div>
                    <div class="widget-content">
                        <div id="sensitiveInfo" style="width: 80%; height:200px;">123123</div>
                    </div>
                </div>
            </div>
--%>
        </div>
    </div>

<!-- 引入后台尾部模板 -->
<jsp:include page="/static/admin_footer.jsp"></jsp:include>

</body>

<script src="admin/js/jquery.min.js"></script>
<script src="admin/js/jquery.ui.custom.js"></script>
<script src="admin/js/bootstrap.min.js"></script>
<script src="admin/js/maruti.js"></script>

<script type="text/javascript">
    var allevent = echarts.init(document.getElementById('allevent'));
    var eventNum = ${allEvent};
    var eventOption = {
        title : {
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['全部事件数']
        },
        series : [
            {
                name: '访问来源',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[

                    {value:eventNum, name:'全部事件数'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    allevent.setOption(eventOption);
    allevent.on('click', function (params) {
//        window.open('http://localhost:8080/socialmind/clickBackEvent?objecType="年"&&year=null&&object=' + params.name);
    });
</script>

<script type="text/javascript">
    var allobject = echarts.init(document.getElementById('allobject'));
    var objectNum = ${entityNum};
    var objectOption = {
        title : {
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['全部对象数']
        },
        series : [
            {
                name: '访问来源',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[

                    {value:objectNum, name:'全部对象数'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    allobject.setOption(objectOption);
    allobject.on('click', function (params) {
//        window.open('http://localhost:8080/socialmind/clickBackEvent?objecType="年"&&year=null&&object=' + params.name);
    });
</script>

<script type="text/javascript">
    var visit = echarts.init(document.getElementById('visit'));
    var visitOption = {
        title : {
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ${eventName}
        },
        series : [
            {
                name: '访问来源',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:${eventClickNum}
                ,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    visit.setOption(visitOption);
    visit.on('click', function (params) {
//        window.open('http://localhost:8080/socialmind/clickBackEvent?objecType="年"&&year=null&&object=' + params.name);
    });
</script>

<script type="text/javascript">
    var addEvent = echarts.init(document.getElementById('addEvent'));
    var option = {
        xAxis: {
            type: 'category',
            data: ${years},
            axisLabel: {
                formatter: '{value}年'
            }
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value}条'
            }
        },
        series: [{
            data: ${yearCount},
            type: 'line',
            smooth: true
        }]
    };


    addEvent.setOption(option);
    addEvent.on('click', function (params) {
        window.open('http://localhost:8080/socialmind/clickBackEvent?objecType="年"&&year=null&&object=' + params.name);
    });
</script>

<script type="text/javascript">
    var periodTime = echarts.init(document.getElementById('periodTime'));
    var option2 = {
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
                data : ${eventName},
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
                    formatter: '{value}天'
                }
            }
        ],
        series : [
            {
                name:'数量',
                type:'bar',
                barWidth: '60%',
                data:${eventPeriod}
            }
        ]
    };

    periodTime.setOption(option2);
    periodTime.on('click', function (params) {
        <%--window.open('http://localhost:8080/socialmind/event/eventInfo?type=view&&objectId=${eventClickNum.objectID}');--%>
    });
</script>
<script type="text/javascript">
    var objectType = echarts.init(document.getElementById('classify'));
    var option3 = {
        title : {
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ${objects}
        },
        series : [
            {
                name: '访问来源',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:${objectList},
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: function() {
                            return 'rgb(' + [
                                Math.round(Math.random() * 160),
                                Math.round(Math.random() * 160),
                                Math.round(Math.random() * 160)
                            ].join(',') + ')';
                        }
                    }
                }
            }
        ]
    };

    objectType.setOption(option3);
    objectType.on('click', function (params) {
//        window.open('http://localhost:8080/socialmind/clickBackEvent?objecType="年"&&year=null&&object=' + params.name);
    });
</script>
</html>

