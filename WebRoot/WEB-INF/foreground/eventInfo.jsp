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
    <title>全网事件分析 - socialmind大平台</title>
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
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span3" id="sidebar">
            <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                <li>
                    <a href="#001"><i class="icon-chevron-right"></i> 事件简介</a>
                </li>
                <li>
                    <a href="#002"><i class="icon-chevron-right"></i>事件走势</a>
                </li>
                <li>
                    <a href="#003"><i class="icon-chevron-right"></i> 网站统计</a>
                </li>
                <li>
                    <a href="#004"><i class="icon-chevron-right"></i>数据类型</a>
                </li>
                <li>
                    <a href="#005"><i class="icon-chevron-right"></i>关键词云</a>
                </li>
                <li>
                    <a href="#006"><i class="icon-chevron-right"></i>热门信息</a>
                </li>
                <li>
                    <a href="#007"><i class="icon-chevron-right"></i>热点网民</a>
                </li>
                <li>
                    <a href="#008"><i class="icon-chevron-right"></i> 传播路径</a>
                </li>
                <li>
                    <a href="#009"><i class="icon-chevron-right"></i>关联词</a>
                </li>
                <li>
                    <a href="#010"><i class="icon-chevron-right"></i>网民观点</a>
                </li>
                <li>
                    <a href="#011"><i class="icon-chevron-right"></i> 舆情总结</a>
                </li>
            </ul>
        </div>
        <!--/span-->
        <c:forEach items="${event}" var="e" varStatus="sts">
        <input type="hidden" value="${e.object.objectId}">
        <div class="span9" id="content">
            <!-- morris stacked chart -->
            <div class="row-fluid">
                <!-- block -->
                <div class="alert alert-info alert-block">
                    <div style="margin-left:40%"><h4>${e.object.name}</h4></div>
                </div>
                <div class="alert alert-block">
                    <h4 class="alert-heading" id="001">事件简介</h4>
                        ${e.event.introduction}
                </div>

                <div class="block" >
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left" id="002">事件走势</div>
                        <div class="pull-right">
                            <span class="badge badge-warning">
                                view more
                            </span>
                        </div>
                    </div>

                    <div class="block-content collapse in">
                        <div class="block-content collapse in">
                            <div>

                                <strong>开始</strong>
                                </c:forEach><br/>
                                <c:forEach items="${artileList}" var="al" varStatus="sts">

                                    <img src="./images/11.png"  width="20px"></img>[${al.postTime}] ${al.title}
                                    【 ${al.websiteId}】
                                    <br/>

                                </c:forEach>
                                <strong>待续</strong>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left">网站统计</div>
                        <div class="pull-right"><span class="badge badge-warning">View More</span>

                        </div>
                    </div>
                    <div class="block-content collapse in">
                        <div class="span12">
                            <div id="website_statistic" style="width:1000px;height: 400px;"></div>
                        </div>
                    </div>
                </div>
                <!-- /block -->
            </div>


            <!-- morris bar & donut charts -->
            <div class="row-fluid section">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left" id="004">数据类型</div>
                        <div class="pull-right"><span class="badge badge-warning">View More</span>

                        </div>
                    </div>
                    <div class="block-content collapse in">
                        <div class="span6 chart">
                            <h5>媒体活跃度</h5>
                            <div id="mediaType" style="width:80%;height: 250px;"></div>
                        </div>
                        <div class="span5 chart">
                            <h5>情感分析</h5>
                            <div id="hero-donut" style="height: 250px;"></div>
                        </div>
                        <div class="span6 chart">
                            <h5>境内外分布</h5>
                            <div id="area" style="width:60%;height:250px"></div>
                        </div>
                        <div class="span5 chart">
                            <h5>媒体来源比</h5>
                            <div id="media" style="height:250px"></div>
                        </div>
                    </div>
                </div>
                <!-- /block -->
            </div>



            <div class="row-fluid">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left" id="005">关键词云</div>
                        <div class="pull-right"><span class="badge badge-warning">View More</span>

                        </div>
                    </div>
                    <div class="block-content collapse in">
                        <div class="span12">
                            <div id="keywords" style="width: 1000px;height: 400px;" ></div>
                        </div>
                    </div>
                </div>
                <!-- /block -->
            </div>

            <div class="row-fluid">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left" id="006">热门信息</div>
                    </div>
                    <div class="block-content collapse in">
                        <div class="span12">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>标题</th>
                                    <th>来源站点</th>
                                    <th>时间</th>
                                    <th>转发数</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${hotInformation}" var="h" varStatus="sts">
                                    <tr>
                                        <td>${h.title}</td>
                                        <td>${h.websiteId}</td>
                                        <td>${h.postTime}</td>
                                        <td>${h.recommendNumber}</td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- /block -->
            </div>

            <!-- morris graph chart -->
            <div class="row-fluid section">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left" id="007" >热点网民</div>
                        <div class="pull-right">

                        </div>
                    </div>
                    <div class="block-content collapse in">
                        <div class="block-content collapse in">
                            <c:forEach items="${hotAuthor}" var="h" varStatus="sts">
                            <div class="span3">
                                <div class="chart" data-percent="73">
                                    <ul>
                                        <div><img src="./images/01.jpg" width="60px" height="60px"></img> </div>
                                        <li><span class="label label-info">${h.name}</span></li>
                                        <li><span class="label label-info">发帖数：${h.elitePostNumber}</span></li>
                                    </ul>
                                </div>
                            </div>
                            </c:forEach>

                        </div>
                    </div>
                </div>
                <!-- /block -->
            </div>



            <div class="row-fluid">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left" id="008">传播路径</div>
                        <div class="pull-right"><span class="badge badge-warning">View More</span>

                        </div>
                    </div>
                    <div class="block-content collapse in">
                        <div class="span12">
                            <%--<img src="./images/route.png"></img>--%>
                        </div>
                    </div>
                </div>
                <!-- /block -->
            </div>

            <div class="row-fluid">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left" id="009">关联词</div>
                        <div class="pull-right"><span class="badge badge-warning">View More</span>

                        </div>
                    </div>
                    <div class="block-content collapse in">
                        <div class="span12">
                            <%--<img src="./images/connection_words.png"></img>--%>
                        </div>
                    </div>
                </div>
                <!-- /block -->
            </div>

            <div class="row-fluid">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left" id="010">网民观点</div>
                    </div>
                    <div class="block-content collapse in">
                        <div class="span12">

                        </div>
                    </div>
                    <div class="block-content collapse in">
                        <div class="span12">
                            <%--<div class="alert">
                                <h4 class="alert-heading">观点一：高考成绩陆续放榜(87%)</h4>
                                <button class="close" data-dismiss="alert">&times;</button>
                                &nbsp;&nbsp;&nbsp;&nbsp;婷婷:高考成绩近期陆续放榜，对分数有异议可申请复核。
                                <br/>
                                &nbsp;&nbsp;&nbsp;&nbsp;单身植物:我不同意
                            </div>
                            <div class="alert alert-success">
                                <h4 class="alert-heading">观点二：高考成绩昨天放榜(12%)</h4>
                                <button class="close" data-dismiss="alert">&times;</button>
                                &nbsp;&nbsp;&nbsp;&nbsp;婷婷:高考成绩近期陆续放榜，对分数有异议可申请复核。
                                <br/>
                                &nbsp;&nbsp;&nbsp;&nbsp;单身植物:我不同意
                            </div>
                            <div class="alert alert-info">
                                <h4 class="alert-heading">观点三：高考成绩不放榜(87%)</h4>
                                <button class="close" data-dismiss="alert">&times;</button>
                                &nbsp;&nbsp;&nbsp;&nbsp;婷婷:高考成绩近期陆续放榜，对分数有异议可申请复核。
                                <br/>
                                &nbsp;&nbsp;&nbsp;&nbsp;单身植物:我不同意
                            </div>--%>


                        </div>
                    </div>
                </div>
                <!-- /block -->
            </div>

            <div class="alert alert-block">
                <c:forEach items="${event}" var="e" varStatus="sts">
                <h4 class="alert-heading" id="011">舆情总结</h4>
                    ${e.event.eventSummary}

                <%--<div class="alert alert-success">
                    综合信息可得，该事件可信度为<strong>真</strong>
                </div>--%>
            </div>

        </div>
        </c:forEach>
    </div>
    <hr>
    <!-- 引入尾部模板 -->
    <jsp:include page="/static/fore_footer.jsp"></jsp:include>
</div>
<!--/.fluid-container-->
<link rel="stylesheet" href="vendors/morris/morris.css">
<script type="text/javascript" src="vendors/flot/echarts-all.js"></script>
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
<script src="assets/scripts.js"></script>
<script src="vendors/echarts-wordcloud.min.js"></script>

<script type="text/javascript" src="vendors/flot/jquery.js"></script>

<input type="hidden" value="${keywords}" id="key">


<script  type="text/javascript">

    var keyWords = [{"name":"老百姓","value":1},
        {"name":"市场经济","value":1},
        {"name":"基层","value":1},
        {"name":"工作队","value":1},
        {"name":"十九大","value":1}];

    //基于准备好的dom,初始化echarts实例
    var myChart = echarts.init(document.getElementById('keywords'));
    function createRandomItemStyle() {
        return {
            normal: {
                color: 'rgb(' + [
                    Math.round(Math.random() * 160),
                    Math.round(Math.random() * 160),
                    Math.round(Math.random() * 160)
                ].join(',') + ')'
            }
        };
    }

    var option = {
        title: {
            text: '',
            link: 'http://www.google.com/trends/hottrends'
        },
        tooltip: {
            show: true
        },
        series: [{
            name: '',
            type: 'wordCloud',
            size: ['80%', '80%'],
            textRotation : [0, 45, 90, -45],
            textPadding: 0,
            autoSize: {
                enable: true,
                minSize: 14
            },
            data:keyWords
        }]

    };
    // 为echarts对象加载数据
    myChart.setOption(option);
    myChart.on('click', function (params) {
        alert((params.name));
        window.open('https://www.baidu.com/s?wd=' + encodeURIComponent(params.name));

    });
</script>
<script>
    var websdata = JSON.stringify(${webs}).replace(/\"/g,"'");
    var webstimedata = JSON.stringify(${periodList}).replace(/\-/g,".").replace(/\"/g,"'");
//    alert(websdata);
//    alert(webstimedata);
    //基于准备好的dom,初始化echarts实例
    var myChart1 = echarts.init(document.getElementById('website_statistic'));

    var option1 = {
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:websdata

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
                data : ['2017.11.07','2017.11.08','2017.11.09','2017.11.10','2017.11.11']

            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'网易博客',
                type:'line',
                stack: '总量',
                data:[120, 132, 101, 134, 90, 230, 210]
            },
            {
                name:'天涯BBS',
                type:'line',
                stack: '总量',
                data:[220, 182, 191, 234, 290, 330, 310]
            },
            {
                name:'人民网BBS',
                type:'line',
                stack: '总量',
                data:[150, 232, 201, 154, 190, 330, 410]
            },
            {
                name:'新华网BBS',
                type:'line',
                stack: '总量',
                data:[320, 332, 301, 334, 390, 330, 320]
            },
            {
                name:'三秦网',
                type:'line',
                stack: '总量',
                data:[820, 932, 901, 934, 1290, 1330, 1320]
            },
            {
                name:'豆瓣小组',
                type:'line',
                stack: '总量',
                data:[120, 132, 101, 134, 90, 230, 210]
            },
            {
                name:'搜狐BBS',
                type:'line',
                stack: '总量',
                data:[220, 182, 191, 234, 290, 330, 310]
            },
            {
                name:'中国社会新闻网',
                type:'line',
                stack: '总量',
                data:[150, 232, 201, 154, 190, 330, 410]
            },
            {
                name:'新浪新闻',
                type:'line',
                stack: '总量',
                data:[320, 332, 301, 334, 390, 330, 320]
            },
            {
                name:'网易新闻',
                type:'line',
                stack: '总量',
                data:[820, 932, 901, 934, 1290, 1330, 1320]
            },
            {
                name:'博客中国',
                type:'line',
                stack: '总量',
                data:[120, 132, 101, 134, 90, 230, 210]
            },
            {
                name:'教育部',
                type:'line',
                stack: '总量',
                data:[220, 182, 191, 234, 290, 330, 310]
            },
            {
                name:'中华网社区',
                type:'line',
                stack: '总量',
                data:[150, 232, 201, 154, 190, 330, 410]
            },
            {
                name:'猫扑社区',
                type:'line',
                stack: '总量',
                data:[320, 332, 301, 334, 390, 330, 320]
            },
            {
                name:'新华网',
                type:'line',
                stack: '总量',
                data:[820, 932, 901, 934, 1290, 1330, 1320]
            }
            ,
            {
                name:'凯迪社区',
                type:'line',
                stack: '总量',
                data:[150, 232, 201, 154, 190, 330, 410]
            },
            {
                name:'西祠社区',
                type:'line',
                stack: '总量',
                data:[320, 332, 301, 334, 390, 330, 320]
            },
            {
                name:'人民网',
                type:'line',
                stack: '总量',
                data:[820, 932, 901, 934, 1290, 1330, 1320]
            }
        ]
    };
    // 为echarts对象加载数据
    myChart1.setOption(option1);

</script>
<script>
    //基于准备好的dom,初始化echarts实例
    var myChart2 = echarts.init(document.getElementById('media'));
    var option2 = {
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:['政府','新闻','社交媒体','博客','论坛']
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {
                    show: true,
                    type: ['pie', 'funnel'],
                    option: {
                        funnel: {
                            x: '25%',
                            width: '50%',
                            funnelAlign: 'center',
                            max: 1548
                        }
                    }
                },
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        series : [
            {
                name:'访问来源',
                type:'pie',
                radius : ['50%', '70%'],
                itemStyle : {
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
                data:${mediaSource}

            }
        ]
    };

    // 为echarts对象加载数据
    myChart2.setOption(option2);
</script>
<script>
    //基于准备好的dom,初始化echarts实例
    var myChart3 = echarts.init(document.getElementById('area'));
    var option3 = {
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:['国内','国外']
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {
                    show: true,
                    type: ['pie', 'funnel'],
                    option: {
                        funnel: {
                            x: '25%',
                            width: '50%',
                            funnelAlign: 'center',
                            max: 1548
                        }
                    }
                },
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        series : [
            {
                name:'访问来源',
                type:'pie',
                radius : ['50%', '70%'],
                itemStyle : {
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
                data:${areaSource}

            }
        ]
    };

    // 为echarts对象加载数据
    myChart3.setOption(option3);
</script>
<script  type="text/javascript">
    var myChart4 = echarts.init(document.getElementById('mediaType'));
    var option4 = {
        title: {
            x: 'center',
            text: '',
            subtext: '',
            link: 'http://echarts.baidu.com/doc/example.html'
        },
        tooltip: {
            trigger: 'item'
        },
        toolbox: {
            show: true,
            feature: {
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable: true,
        grid: {
            borderWidth: 0,
            y: 80,
            y2: 60
        },
        xAxis: [
            {
                type: 'category',
                show: false,
                data: ['博客', '新闻','政府', '论坛', '社交媒体']
            }
        ],
        yAxis: [
            {
                type: 'value',
                show: false
            }
        ],
        series: [
            {
                name: '媒体类型',
                type: 'bar',
                itemStyle: {
                    normal: {
                        color: function(params) {
                            // build a color map as your need.
                            var colorList = [
                                '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
                                '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                                '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
                            ];
                            return colorList[params.dataIndex]
                        },
                        label: {
                            show: true,
                            position: 'top',
                            formatter: '{b}\n{c}'
                        }
                    }
                },
                data: ${mediaSource},
                markPoint: {
                    tooltip: {
                        trigger: 'item',
                        backgroundColor: 'rgba(0,0,0,0)',
                        formatter: function(params){
                            return '<img src="'
                                + params.data.symbol.replace('image://', '')
                                + '"/>';
                        }
                    },
                    data: [
                        {xAxis:0, y: 350, name:'Line', symbolSize:20, symbol: 'image://../asset/ico/折线图.png'},
                        {xAxis:1, y: 350, name:'Bar', symbolSize:20, symbol: 'image://../asset/ico/柱状图.png'},
                        {xAxis:2, y: 350, name:'Scatter', symbolSize:20, symbol: 'image://../asset/ico/散点图.png'},
                        {xAxis:3, y: 350, name:'K', symbolSize:20, symbol: 'image://../asset/ico/K线图.png'},
                        {xAxis:4, y: 350, name:'Pie', symbolSize:20, symbol: 'image://../asset/ico/饼状图.png'},
                        {xAxis:5, y: 350, name:'Radar', symbolSize:20, symbol: 'image://../asset/ico/雷达图.png'},
                        {xAxis:6, y: 350, name:'Chord', symbolSize:20, symbol: 'image://../asset/ico/和弦图.png'},
                        {xAxis:7, y: 350, name:'Force', symbolSize:20, symbol: 'image://../asset/ico/力导向图.png'},
                        {xAxis:8, y: 350, name:'Map', symbolSize:20, symbol: 'image://../asset/ico/地图.png'},
                        {xAxis:9, y: 350, name:'Gauge', symbolSize:20, symbol: 'image://../asset/ico/仪表盘.png'},
                        {xAxis:10, y: 350, name:'Funnel', symbolSize:20, symbol: 'image://../asset/ico/漏斗图.png'},
                    ]
                }
            }
        ]
    };

    myChart4.setOption(option4);
</script>
<script>
    $(function() {
        var data = [ ["January", 10], ["February", 8], ["March", 4], ["April", 13], ["May", 17], ["June", 9] ];

//        $.plot("#catchart", [ data ], {
//            series: {
//                bars: {
//                    show: true,
//                    barWidth: 0.6,
//                    align: "center"
//                }
//            },
//            xaxis: {
//                mode: "categories",
//                tickLength: 0
//            }
//        });

        var data = [],
            series = Math.floor(Math.random() * 6) + 3;

        for (var i = 0; i < series; i++) {
            data[i] = {
                label: "Series" + (i + 1),
                data: Math.floor(Math.random() * 100) + 1
            }
        }

        /*$.plot('#piechart1', data, {
            series: {
                pie: {
                    show: true,
                    radius: 1,
                    label: {
                        show: true,
                        radius: 3/4,
                        formatter: labelFormatter,
                        background: {
                            opacity: 0.5,
                            color: '#000'
                        }
                    }
                }
            },
            legend: {
                show: false
            }
        });

        $.plot('#piechart2', data, {
            series: {
                pie: {
                    show: true,
                    radius: 1,
                    tilt: 0.5,
                    label: {
                        show: true,
                        radius: 1,
                        formatter: labelFormatter,
                        background: {
                            opacity: 0.8
                        }
                    },
                    combine: {
                        color: '#999',
                        threshold: 0.1
                    }
                }
            },
            legend: {
                show: false
            }
        });
*/

        function euroFormatter(v, axis) {
            return v.toFixed(axis.tickDecimals) + "€";
        }

        /*function doPlot(position) {
            $.plot("#timechart", [
                { data: oilprices, label: "Oil price ($)" },
                { data: exchangerates, label: "USD/EUR exchange rate", yaxis: 2 }
            ], {
                xaxes: [ { mode: "time" } ],
                yaxes: [ { min: 0 }, {
                    // align if we are to the right
                    alignTicksWithAxis: position == "right" ? 1 : null,
                    position: position,
                    tickFormatter: euroFormatter
                } ],
                legend: { position: "sw" }
            });
        }

        doPlot("right");*/

    });

    // Morris Bar Chart
    Morris.Bar({
        element: 'hero-bar',
        data: [
            {device: '政府', sells: 25452},
            {device: '新闻', sells: 3549},
            {device: '博客', sells: 938},
            {device: '论坛', sells: 768},
            {device: '社交媒体', sells: 255},

        ],
        xkey: 'device',
        ykeys: ['sells'],
        labels: ['Sells'],
        barRatio: 0.4,
        xLabelMargin: 10,
        hideHover: 'auto',
        barColors: ["#3d88ba"]
    });

    // Morris Donut Chart
    Morris.Donut({
        element: 'hero-donut',
        data: [
            {label: '敏感', value: 93.82 },
            {label: '非敏感', value: 6.18 },
            {label: 'Search engines', value: 0 },
            {label: 'Unique visitors', value: 0 }
        ],
        colors: ["#30a1ec", "#76bdee", "#c4dafe"],
        formatter: function (y) { return y + "%" }
    });

    Morris.Donut({
        element: 'in-out',
        data: [
            {label: '境内', value: 99 },
            {label: '境外', value: 1 },
            {label: 'Search engines', value: 0 },
            {label: 'Unique visitors', value: 0 }
        ],
        colors: ["#30a1ec", "#76bdee", "#c4dafe"],
        formatter: function (y) { return y + "%" }
    });


    // Morris Line Chart
    var tax_data = [
        {"period": "2013-04", "visits": 2407, "signups": 660},
        {"period": "2013-03", "visits": 3351, "signups": 729},
        {"period": "2013-02", "visits": 2469, "signups": 1318},
        {"period": "2013-01", "visits": 2246, "signups": 461},
        {"period": "2012-12", "visits": 3171, "signups": 1676},
        {"period": "2012-11", "visits": 2155, "signups": 681},
        {"period": "2012-10", "visits": 1226, "signups": 620},
        {"period": "2012-09", "visits": 2245, "signups": 500}
    ];
    /*var tax_data = JSON.stringify('');*/
    Morris.Line({
        element: 'hero-graph',
        data: tax_data,
        xkey: 'period',
        xLabels: "时间",
        ykeys: ['visits', 'signups'],
        labels: ['网站类型', '数量']
    });



    // Morris Area Chart
    Morris.Area({
        element: 'hero-area',
        data: [
            {period: '2010 Q1', iphone: 2666, ipad: null, itouch: 2647},
            {period: '2010 Q2', iphone: 2778, ipad: 2294, itouch: 2441},
            {period: '2010 Q3', iphone: 4912, ipad: 1969, itouch: 2501},
            {period: '2010 Q4', iphone: 3767, ipad: 3597, itouch: 5689},
            {period: '2011 Q1', iphone: 6810, ipad: 1914, itouch: 2293},
            {period: '2011 Q2', iphone: 5670, ipad: 4293, itouch: 1881},
            {period: '2011 Q3', iphone: 4820, ipad: 3795, itouch: 1588},
            {period: '2011 Q4', iphone: 15073, ipad: 5967, itouch: 5175},
            {period: '2012 Q1', iphone: 10687, ipad: 4460, itouch: 2028},
            {period: '2012 Q2', iphone: 8432, ipad: 5713, itouch: 1791}
        ],
        xkey: 'period',
        ykeys: ['iphone', 'ipad', 'itouch'],
        labels: ['iPhone', 'iPad', 'iPod Touch'],
        lineWidth: 2,
        hideHover: 'auto',
        lineColors: ["#81d5d9", "#a6e182", "#67bdf8"]
    });



    // Build jQuery Knobs
    $(".knob").knob();

    function labelFormatter(label, series) {
        return "<div style='font-size:8pt; text-align:center; padding:2px; color:white;'>" + label + "<br/>" + Math.round(series.percent) + "%</div>";
    }


</script>
</body>

</html>
