<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/10/30
  Time: 18:44
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
    <base href="<%=basePath%>foreground/"/>
    <title>信息监测 - socialmind大平台</title>
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
<body style="overflow: hidden">
<!-- 引入头模板 -->
<jsp:include page="/static/fore_header.jsp"/>
<div class="container-fluid"style="margin-top: 4%;">
    <div class="row-fluid">
        <div class="span3" id="sidebar">
            <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                <li>
                    <a onclick="document.getElementById('001').scrollIntoView();"><i class="icon-chevron-right"></i> 媒体活跃度</a>
                </li>
                <li>
                    <a onclick="document.getElementById('002').scrollIntoView();"><i class="icon-chevron-right"></i>信息走势图</a>
                </li>
                <li>
                    <a onclick="document.getElementById('003').scrollIntoView();"><i class="icon-chevron-right"></i> 信息属性走势图</a>
                </li>
                <li>
                    <a onclick="document.getElementById('004').scrollIntoView();"><i class="icon-chevron-right"></i>数据类型</a>
                </li>

                <li>
                    <a onclick="document.getElementById('005').scrollIntoView();"><i class="icon-chevron-right"></i>地域分布图</a>
                </li>

            </ul>
        </div>
        <!--/span-->

        <input type="hidden" value="${objectId}" name="objectId">
        <div class="span9" id="content" style="height:90%; overflow-y:auto">

            <!--title-->
            <h3 align="center">信息检测</h3>
            <div align="left">
                <c:forEach items="${event}" var="e" varStatus="sts">
                <button class="btn btn-large"><a href="<%=basePath%>retrivalResults?flag=all&turn=detection&name=${e.object.name}" style="text-decoration:none">信息列表</a></button>
                <button class="btn btn-large"><a href="<%=basePath%>infodetection/graph?objectId=${e.object.objectId}">图表分析</a></button>
                </c:forEach>

            </div>
            <!-- morris stacked chart -->
            <br/>
            <div class="row-fluid">
                <!-- block -->
                <div class="alert alert-info alert-block">
                    <%
                        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
                                "yyyy-MM-dd HH:mm:ss");
                        java.util.Date currentTime = new java.util.Date();
                        String time = simpleDateFormat.format(currentTime).toString();

                    %>
                    <%
                        java.text.SimpleDateFormat simpleDateFormat0 = new java.text.SimpleDateFormat(
                                "yyyy-MM-dd");
                        java.util.Date currentTime0 = new java.util.Date();
                        String time0 = simpleDateFormat0.format(currentTime0).toString();

                    %>
                    <div> 统计时间:<%=time0%> 00:00:00~<%=time%>
                        </div>
                </div>

                <div class="row-fluid">
                    <div class="span6">
                        <!-- block -->
                        <div class="block">
                            <div class="navbar navbar-inner block-header" id="001">
                                <div class="muted pull-left">媒体活跃度统计</div>
                            </div>
                            <div class="block-content collapse in">
                                <div class="span12" style="height: 300px">
                                    <div id="website_source" style="width:100%;height:250px"></div>

                                </div>
                            </div>
                        </div>
                        <!-- /block -->
                    </div>
                    <div class="span6">
                        <!-- block -->
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">来源类型统计图</div>
                            </div>
                            <div class="block-content collapse in">
                                <div class="span12" style="height: 300px">
                                    <div id="website" style="width:100%;height:250px"></div>
                                    <div align="center"><b>各媒体信息总数为<font color="orange">${articleNum}</font>条</b></div>
                                </div>
                            </div>
                        </div>
                        <!-- /block -->
                    </div>
                </div>


                <div class="block">
                    <div class="navbar navbar-inner block-header" id="002">
                        <div class="muted pull-left" >信息走势图</div>
                        <div class="pull-right"><span class="badge badge-warning">View More</span>

                        </div>
                    </div>
                    <div class="block-content collapse in">
                        <div class="span12">
                            <div id="website_statistic" style="width:1200px;height: 400px;"></div>
                        </div>
                    </div>
                </div>

                <%--<div class="row-fluid">
                    <div class="block">
                        <div class="navbar navbar-inner block-header" id="003">
                            <div class="muted pull-left" >信息属性走势图</div>
                            <div class="pull-right"><span class="badge badge-warning">View More</span>

                            </div>
                        </div>
                        <div class="block-content collapse in">
                            <div class="span12">
                                <div id="infoAttribute" style="width:1000px;height: 400px;"></div>
                            </div>
                        </div>
                    </div>
                    <!-- /block -->
                </div>--%>



                <div class="row-fluid">
                    <div class="span6">
                        <!-- block -->
                        <div class="block">
                            <div class="navbar navbar-inner block-header" id="004">
                                <div class="muted pull-left">敏感信息</div>
                            </div>
                            <div class="block-content collapse in">
                                <div class="span12">
                                    <div id="mingan" style="width:100%;height:250px"></div>

                                </div>
                            </div>
                        </div>
                        <!-- /block -->
                    </div>

                    <div class="span6">
                        <!-- block -->
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">敏感类型统计</div>
                            </div>
                            <div class="block-content collapse in">
                                <div class="span12">
                                    <div id="sensitive" style="width:100%;height:250px"></div>
                                </div>
                            </div>
                        </div>
                        <!-- /block -->
                    </div>

                </div>


                <div class="row-fluid">
                    <div class="block">
                        <div class="navbar navbar-inner block-header" id="005">
                            <div class="muted pull-left">地域分布图</div>
                            <div class="pull-right"><span class="badge badge-warning">View More</span>

                            </div>
                        </div>
                        <div class="block-content collapse in">
                            <div class="span12">
                                <div id="map" style="width:1100px;height: 900px;"></div>
                            </div>
                        </div>
                    </div>
                    <!-- /block -->
                </div>


            </div>
        </div>
    <hr>
    </div>
    <!-- 引入尾部模板 -->
    <jsp:include page="/static/fore_footer.jsp"></jsp:include>
</div>

<!--/.fluid-container-->
<link rel="stylesheet" href="vendors/morris/morris.css">
<script src="vendors/flot/echarts.min.js"></script>
<%--<script src="vendors/flot/echarts-all.js"></script>--%>
<script src="vendors/jquery-1.9.1.min.js"></script>
<script src="vendors/jquery.knob.js"></script>
<script src="vendors/raphael-min.js"></script>
<script src="vendors/china.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="vendors/flot/jquery.flot.js"></script>
<script src="vendors/flot/jquery.flot.categories.js"></script>
<script src="vendors/flot/jquery.flot.pie.js"></script>
<script src="vendors/flot/jquery.flot.time.js"></script>
<script src="vendors/flot/jquery.flot.stack.js"></script>
<script src="vendors/flot/jquery.flot.resize.js"></script>
<script src="assets/scripts.js"></script>
<script src="vendors/echarts-wordcloud.min.js"></script>

<script type="text/javascript" src="vendors/flot/jquery.js"></script>

<script>
    //基于准备好的dom,初始化echarts实例
    var myChart = echarts.init(document.getElementById('website_source'));
    var option = {
        title: {
            text: '媒体来源',
            x: 'center',
            y: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{b}'
        },
        legend: {
            x: 'center',
            y: 'bottom'
        },
        calculable: true,
        series: [
            {
                color: ['#d0a8fc',  '#78bff5', '#7899f5', '#fcba61', '#c8f578'],
                type: 'pie',
                radius: [40, 100],
                center: ['50%', '50%'],
                roseType: 'radius',
                data: ${mediaSource}
            }
        ]
    }
    // 为echarts对象加载数据
    myChart.setOption(option);
    myChart.on('click', function (params) {
        alert((params.name));
        <%--window.open('http://localhost:8080/socialmind/infodetection/click?objectId=${objectId}&&object=' + params.name);--%>
    });
</script>
<script>
    //基于准备好的dom,初始化echarts实例
    var myChart1 = echarts.init(document.getElementById('website_statistic'));
    var option1 = {
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:${webs}
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : ${periodList}

            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series :${websiteStatistic}

    };
    // 为echarts对象加载数据
    myChart1.setOption(option1);
</script>
<script>
    //基于准备好的dom,初始化echarts实例
    var myChart2 = echarts.init(document.getElementById('website'));
    var option2 = {
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'horizontal',
            y : 'top',
            data:${websites}
        },
        toolbox: {
            show: true,
            feature: {
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable : true,
        series : [
            {
                name:'访问来源',
                type:'pie',
                radius : ['50%', '70%','70%','70%','70%'],
                itemStyle : {
                    color: function(params) {
                        // build a color map as your need.
                        var colorList = [
                            '#F3A43B','#FE8463','#9BCA63','#FAD860','#60C0DD',
                            /*'#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0',
                            '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B'*/
                        ];
                        return colorList[params.dataIndex]
                    },
                    normal : {
                        label : {
                            show : false
                        },
                        labelLine : {
                            show : false
                        }
                    },
                    emphasis : {
                        label : {
                            show : true,
                            position : 'center',
                            textStyle : {
                                fontSize : '30',
                                fontWeight : 'bold'
                            }
                        }
                    }
                },
                data:${websiteSource}

            }
        ]
    };

    // 为echarts对象加载数据
    myChart2.setOption(option2);
    myChart2.on('click', function (params) {
        alert((params.name));
        <%--window.open('http://localhost:8080/socialmind/infodetection/click?objectId=${objectId}&&object=' + params.name);--%>
    });
</script>
<script>
    //基于准备好的dom,初始化echarts实例
   /* var myChart3 = echarts.init(document.getElementById('infoAttribute'));
    var option3 = {

        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['敏感','非敏感']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['周一','周二','周三','周四','周五','周六','周日']
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'敏感',
                type:'line',
                stack: '总量',
                data:[120, 132, 101, 134, 90, 230, 210]
            },
            {
                name:'非敏感',
                type:'line',
                stack: '总量',
                data:[220, 182, 191, 234, 290, 330, 310]
            }
        ]
    };

    // 为echarts对象加载数据
    myChart3.setOption(option3);*/
</script>
<script>
    //基于准备好的dom,初始化echarts实例
    var myChart4 = echarts.init(document.getElementById('map'));

    // legend内容
    var legendData=['信息量'];
    // legend自定义颜色 不设置有默认色
    var legendColor=['blue','gray','#000','cyan', 'red'];
    // 映射颜色  不设置有默认色
    var visColor=["#ffffff","#e8192f","#d6664d","#B9044E","#f0ba2e","#d3ce2b","#169A7f","#0b6573","#1BB3c8"];
    // seriesData Array [{name:'',type:'map',mapType:'china',
    //           label: { normal: {show: true},emphasis: { show: true}},data:[{name:'',value:''},...]},{...}]
    var seriseData=[
        {
            name: '',
            type: 'map',
            mapType: 'china',
            label: { normal: {show: true},emphasis: { show: true}},
            data:${provinceList}
        }

    ]

    var option4 = {
        title: {
            text: '通过颜色映射信息量',
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
            min: 0,
            max: ${count},
            left: 'left',
            bottom: '3%',
            text: ['高','低'],           // 文本，默认为数值文本
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
    myChart4.setOption(option4);
    myChart4.on('click', function (params) {
        alert((params.name));
        <%--window.open('http://localhost:8080/socialmind/infodetection/click?objectId=${objectId}&&object=' + params.name);--%>
    });

</script>

<script>
    //基于准备好的dom,初始化echarts实例
    var myChart5 = echarts.init(document.getElementById('mingan'));
    var option5 = {
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'horizontal',
            y : 'top',
            data:['敏感','非敏感']
        },
        toolbox: {
            show: true,
            feature: {
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable : true,
        series : [
            {
                name:'访问来源',
                type:'pie',
                radius : ['50%', '70%','70%','70%','70%'],
                itemStyle : {
                    color: function(params) {
                        // build a color map as your need.
                        var colorList = [
                            '#F3A43B','#FE8463','#9BCA63','#FAD860','#60C0DD',
                            /*'#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0',
                            '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B'*/
                        ];
                        return colorList[params.dataIndex]
                    },
                    normal : {
                        label : {
                            show : false
                        },
                        labelLine : {
                            show : false
                        }
                    },
                    emphasis : {
                        label : {
                            show : true,
                            position : 'center',
                            textStyle : {
                                fontSize : '30',
                                fontWeight : 'bold'
                            }
                        }
                    }
                },
                data:${sensitiveCount}

            }
        ]
    };
    // 为echarts对象加载数据
    myChart5.setOption(option5);
    myChart5.on('click', function (params) {
        alert((params.name));
        window.open('http://localhost:8080/socialmind/infodetection/click?objectId=${objectId}&&object=' + params.name);
    });
</script>
<script>
    //基于准备好的dom,初始化echarts实例
    var myChart6 = echarts.init(document.getElementById('sensitive'));
    var option6 = {
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'horizontal',
            y : 'top',
            data:['色情','反动','贪腐','暴恐','民生','其他']
        },
        toolbox: {
            show: true,
            feature: {
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable : true,
        series : [
            {
                name:'访问来源',
                type:'pie',
                radius : ['50%', '70%','70%','70%','70%'],
                itemStyle : {
                    color: function(params) {
                        // build a color map as your need.
                        var colorList = [
                            '#F3A43B','#FE8463','#9BCA63','#FAD860','#60C0DD',
                            /*'#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0',
                            '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B'*/
                        ];
                        return colorList[params.dataIndex]
                    },
                    normal : {
                        label : {
                            show : false
                        },
                        labelLine : {
                            show : false
                        }
                    },
                    emphasis : {
                        label : {
                            show : true,
                            position : 'center',
                            textStyle : {
                                fontSize : '30',
                                fontWeight : 'bold'
                            }
                        }
                    }
                },
                data:${sensilist}

            }
        ]
    };

    // 为echarts对象加载数据
    myChart6.setOption(option6);
    myChart6.on('click', function (params) {
        alert((params.name));
        window.open('http://localhost:8080/socialmind/infodetection/click?objectId=${objectId}&&object=' + params.name);
    });
</script>
</body>

</html>