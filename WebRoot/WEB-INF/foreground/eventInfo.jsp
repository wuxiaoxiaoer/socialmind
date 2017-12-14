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
<body style="overflow: hidden">
<!-- 引入头模板 -->
<jsp:include page="/static/fore_header.jsp"/>
<div class="container-fluid"style="margin-top: 4%;">
    <div class="row-fluid">
        <div class="span3" id="sidebar">
            <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                <li>
                    <a onclick="document.getElementById('001').scrollIntoView();"><i class="icon-chevron-right"></i> 事件简介</a>
                </li>
                <li>
                    <a onclick="document.getElementById('002').scrollIntoView();"><i class="icon-chevron-right"></i>事件走势</a>
                </li>
                <li>
                    <a onclick="document.getElementById('003').scrollIntoView();"><i class="icon-chevron-right"></i> 网站统计</a>
                </li>
                <li>
                    <a onclick="document.getElementById('004').scrollIntoView();"><i class="icon-chevron-right"></i>数据类型</a>
                </li>
                <li>
                    <a onclick="document.getElementById('005').scrollIntoView();"><i class="icon-chevron-right"></i>关键词云</a>
                </li>
                <li>
                    <a onclick="document.getElementById('006').scrollIntoView();"><i class="icon-chevron-right"></i>热门信息</a>
                </li>
                <li>
                    <a onclick="document.getElementById('007').scrollIntoView();"><i class="icon-chevron-right"></i>热点网民</a>
                </li>
                <li>
                    <a onclick="document.getElementById('008').scrollIntoView();"><i class="icon-chevron-right"></i> 传播路径</a>
                </li>
                <li>
                    <a onclick="document.getElementById('009').scrollIntoView();"><i class="icon-chevron-right"></i>关联词</a>
                </li>
                <li>
                    <a onclick="document.getElementById('010').scrollIntoView();"><i class="icon-chevron-right"></i>网民观点</a>
                </li>
                <li>
                    <a onclick="document.getElementById('011').scrollIntoView();"><i class="icon-chevron-right"></i> 舆情总结</a>
                </li>
            </ul>
        </div>
        <!--/span-->
        <c:forEach items="${event}" var="e" varStatus="sts">
        <div class="span9" id="content" style="height:90%; overflow-y:auto">
            <!-- morris stacked chart -->
            <div class="row-fluid">
                <!-- block -->
                <div class="alert alert-info alert-block">
                    <div style="margin-left:40%" id="objectName"><a style="size: 60pc">${e.object.name}</a></div>
                </div>
                <div class="alert alert-block">
                    <h1 class="alert-heading" id="001">事件简介</h1>
                        ${e.event.introduction}
                </div>
                </c:forEach>
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
                        <div class="block-content collapse in" style="height:40%;overflow-y:auto">
                            <div>

                                <strong>开始</strong>
                                <br/>
                                <c:forEach items="${artileList}" var="al" varStatus="sts">

                                    <img src="./images/11.png"  width="20px"></img>[${al.postTime}] ${al.title}
                                    【 ${al.websiteEntity.websiteId}】
                                    <br/>

                                </c:forEach>
                                <strong>待续</strong>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left" id="003">网站统计</div>
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
                        <%--<div class="span6 chart">
                            <h5>媒体活跃度</h5>
                            <div id="hero-bar" style="height: 250px;"></div>
                        </div>--%>
                            <div class="span6 chart">
                                <h5>境内外分布</h5>
                                <div id="area" style="width:60%;height:250px"></div>
                            </div>
                        <div class="span5 chart">
                            <h5>媒体来源比</h5>
                            <div id="media" style="width:100%;height:250px"></div>
                        </div>

                            <%--<div class="span6 chart">
                                <h5>境内外分布</h5>
                                <div id="mediaType" style="width:60%;height:250px"></div>
                            </div>--%>
                        <%--<div class="span5 chart">
                            <h5>敏感分析</h5>
                            <div id="sensitive" style="width:60%;height: 250px;"></div>
                        </div>--%>
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
                                        <td>${h.websiteEntity.websiteId}</td>
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
                                        <li><span class="label label-info"><a href="<%=basePath%>authorInfo?authorId=${h.authorId}">${h.name}</a></span></li>
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
                        <div class="pull-right"><span class="badge badge-warning"></span>
                        </div>
                    </div>
                    <div class="block-content collapse in">
                        <div class="span12">
                            <div id="transfer" style="width:900px;height: 500px;float: left"></div>
                            <div id="graph" style="width:200px;height: 500px;float: right"></div>
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
                            <div id="keyword_related" style="width:1000px;height: 600px;"></div>
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
                            <div id="opinion" style="width:1000px;height: 250px;"></div>
                            <c:forEach items="${hotOpinionList}" var="ac" varStatus="sts">

                                <c:forEach items="${ac}" var="o" varStatus="sts">

                                    <c:if test="${o.postTime == '新闻' }">
                                        <div class="alert">
                                        <h4 class="alert-heading">观点${sts.index+1}：${o.title} 【来自网友：${o.content}】 --${o.articleId} </h4>
                                        </div>
                                    </c:if>
                                    <c:if test="${o.postTime == '论坛' }">
                                        <div class="alert alert-info">
                                            <h4 class="alert-heading">观点${sts.index+1}：${o.title} 【${o.content}】--${o.articleId}</h4>
                                        </div>
                                    </c:if>
                                    <c:if test="${o.postTime == '博客' }">
                                        <div class="alert alert-success">
                                            <h4 class="alert-heading">观点${sts.index+1}：${o.title} 【${o.content}】--${o.articleId}</h4>
                                        </div>
                                    </c:if>
                                    <c:if test="${o.postTime == '社交媒体' }">
                                        <div class="alert alert-error">
                                            <h4 class="alert-heading">观点${sts.index+1}：${o.title} 【${o.content}】--${o.articleId}</h4>
                                        </div>
                                    </c:if>
                                    <c:if test="${o.postTime == '政府' }">
                                        <div >
                                            <h4 class="alert-heading">观点${sts.index+1}：${o.title} 【${o.content}】--${o.articleId}</h4>
                                        </div>
                                    </c:if>
                                </c:forEach>

                            </c:forEach>

                        </div>
                    </div>
                </div>
                <!-- /block -->
            </div>

            <div class="alert alert-block">
                <c:forEach items="${event}" var="e">
                <h4 class="alert-heading" id="011">舆情总结</h4>
                    &nbsp;&nbsp;&nbsp;&nbsp;${e.event.eventSummary}
                </c:forEach>
                <div class="alert alert-success">
                    <c:forEach items="${reliablity}" var="r">
                    综合信息可得，该事件可信度为&nbsp;&nbsp;<strong>${r.indicatorValue}</strong>
                    </c:forEach>
                </div>
            </div>
        </div>

    </div>
    <hr>
    <!-- 引入尾部模板 -->
    <jsp:include page="/static/fore_footer.jsp"></jsp:include>
</div>
<script>
    function go(){//js获取超链接的id，取得里面的html内容
        var s = document.getElementById("name").innerHTML;
        window.open('https://www.baidu.com/s?wd=' + s);
    }
</script>
<!--/.fluid-container-->
<link rel="stylesheet" href="vendors/morris/morris.css">
<script src="vendors/flot/echarts.min.js"></script>
<%--<script src="vendors/flot/echarts-all.js"></script>--%>
<script src="vendors/jquery-1.9.1.min.js"></script>
<script src="vendors/jquery.knob.js"></script>
<script src="vendors/raphael-min.js"></script>

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

<script  type="text/javascript">

    var keyWords = JSON.parse('${keywords}');

    //基于准备好的dom,初始化echarts实例
    var myChart = echarts.init(document.getElementById('keywords'));
    //指定图表的配置项和数据
    var  option = {
        title: {
            text: '热点词分析',
            link: 'https://www.baidu.com/s?wd=' + encodeURIComponent('ECharts'),
            x: 'center',
            textStyle: {
                fontSize: 23
            }

        },
        backgroundColor: '#F7F7F7',
        tooltip: {
            show: true
        },
        toolbox: {
            feature: {
                saveAsImage: {
                    iconStyle: {
                        normal: {
                            color: '#FFFFFF'
                        }
                    }
                }
            }
        },
        series: [{
            name: '热点分析',
            type: 'wordCloud',
            //size: ['9%', '99%'],
            sizeRange: [6, 66],
            //textRotation: [0, 45, 90, -45],
            rotationRange: [-45, 90],
            //shape: 'circle',
            textPadding: 0,
            autoSize: {
                enable: true,
                minSize: 6
            },
            textStyle: {
                normal: {
                    color: function() {
                        return 'rgb(' + [
                            Math.round(Math.random() * 160),
                            Math.round(Math.random() * 160),
                            Math.round(Math.random() * 160)
                        ].join(',') + ')';
                    }
                },
                emphasis: {
                    shadowBlur: 10,
                    shadowColor: '#333'
                }
            },
            data: []
        }]
    };


    option.series[0].data = keyWords;

    myChart.setOption(option);
    myChart.on('click', function (params) {
        alert((params.name));
        window.open('https://www.baidu.com/s?wd=' + encodeURIComponent(params.name));

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
<input type="hidden" value="${hotOpinionList}">
<script>
    //基于准备好的dom,初始化echarts实例
    var myChart2 = echarts.init(document.getElementById('media'));
    var option2 = {
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'horizontal',
            y : 'top',
            data:['博客', '新闻','政府', '论坛', '社交媒体']
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
                    color: function(params) {
                        // build a color map as your need.
                        var colorList = [
                            '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
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
                data:${areaSource}

            }
        ]
    };

    // 为echarts对象加载数据
    myChart3.setOption(option3);
</script>

<script  type="text/javascript">
    /*var myChart4 = echarts.init(document.getElementById('mediaType'));
    var option4 = {
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
                data : [mediaList],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'直接访问',
                type:'bar',
                barWidth: '60%',
                data:[mediaSource]
            }
        ]
    };

    /!*var option4 = {
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
                                '#FE8463','#60C0DD','#9BCA63','#FAD860','#F3A43B',
                                /!*'#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0',
                                '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B'*!/
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
                data:
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

                        {xAxis:0, y: 350, name:'Chord', symbolSize:20, symbol: ''},
                        {xAxis:1, y: 350, name:'Force', symbolSize:20, symbol: ''},
                        {xAxis:2, y: 350, name:'Map', symbolSize:20, symbol: ''},
                        {xAxis:3, y: 350, name:'Gauge', symbolSize:20, symbol: ''},
                        {xAxis:4, y: 350, name:'Funnel', symbolSize:20, symbol: ''},
                    ]
                }
            }
        ]
    };*!/
    myChart4.setOption(option4);*/
</script>

<script>
    var myChart7 = echarts.init(document.getElementById('opinion'));
    var option7 = {
        title : {
            text: '媒体观点分析',
            subtext: '',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:${mediaList}
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
                            funnelAlign: 'left',
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
                radius : '55%',
                center: ['50%', '60%'],
                data:${opinionSource}
            }
        ]
    };
    myChart7.setOption(option7);
</script>
<script>
    //基于准备好的dom,初始化echarts实例
    /*var myChart8 = echarts.init(document.getElementById('sensitive'));
    var option8 = {
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:['敏感','非敏感']
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
                    color: function(params) {
                        // build a color map as your need.
                        var colorList = [
                            '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                            /!*'#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0',
                            '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B'*!/
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
                data:

            }
        ]
    };

    // 为echarts对象加载数据
    myChart8.setOption(option8);*/
</script>
<script type="text/javascript">
    //基于准备好的dom,初始化echarts实例
    var myChart9 = echarts.init(document.getElementById("transfer"));
    //指定图表的配置项和数据
    var category = JSON.parse('${category}');
    var categories = [];

    for (var c_num = 0; c_num < category.length;  c_num++) {
        categories[c_num] = {
            name: category[c_num]
        }
    }

    var nodesAndEdges = JSON.parse('${nodesAndEdges}');

    var nodes = nodesAndEdges['nodes'];
    var edges = nodesAndEdges['edges'];


    //不同阈值下的SNA列表
    var SNAList = JSON.parse('${SNAList}');//会导致$ref错误
    var graphSNA =SNAList[4]['graphSNA'];
    var nodeSNAList =SNAList[4]['nodeSNAList'];



    var titleMap = new Map();
    var numMap = new Map();
    var tableMap = new Map();

    //获取每个阈值下节点的最大值
    var max_num = [];
    var slice_num = nodes[0]['num'].length;
    for(var x = 0; x < slice_num; x++) {
        max_num[x] = 0;
        for (var xi = 0; xi < nodes.length; xi++) {
            if(nodes[xi]['num'][x] > max_num[x]) {
                max_num[x] = nodes[xi]['num'][x];
            }
        }
    }

    var dataSliceList = [];
    var layout = [];
    for(var y = 0; y < slice_num; y++) {

        //第一张采用环形布局，其余的采用力导向图
        if (y === 0) {
            layout.push('circular');
        } else {
            layout.push('force');
        }

        var data = [];
        for (var i = 0; i < nodes.length; i++) {
            if (y === 0) {
                titleMap.set(nodes[i]['id'], nodes[i]['title']);
                numMap.set(nodes[i]['id'], nodes[i]['num']);
                tableMap.set(nodes[i]['id'], nodes[i]['table']);
            }

            data.push({
                "name": nodes[i]['id'],
                "symbolSize": (nodes[i]['num'][y] + 1) / (max_num[y] + 1),
                "draggable": true,
                "value": [nodes[i]['title'], nodes[i]['num'][y]],
                "category": nodes[i]['website'],
                "label": {
                    "normal": {
                        "show": true
                    }
                }
            });
        }
        dataSliceList.push(data)
    }

    var edgeOptions = [];
    for(var ii = 0; ii < slice_num; ii++) {
        edgeOptions.push({
            visualMap: {
                max: max_num[ii]
            },
            series: {
                layout: layout[ii],
                edges: edges[ii],
                data: dataSliceList[ii],
                nodeSNAList:SNAList[ii]['nodeSNAList'],
                graphSNA:SNAList[ii]['graphSNA']
            }
        })
    }

    var timeLineData = [];
    for(var s = 0; s <= 1; s += 0.1) {
        timeLineData.push(s.toFixed(1))
    }


    var tip1 = graphSNA['degreeCentralization'];
    var tip2 = graphSNA['closeCentralization'];
    var tip3 = graphSNA['scale'];
    var tip4 = graphSNA['density'];
    var tip5 = graphSNA['diameter'];
    var tip6 = graphSNA['averageength'];
    var tip7 = graphSNA['correlationDegree'];
    var tip8 = graphSNA['rankDegree'];

    var option9 = {
        baseOption: {
            timeline: {
                data: timeLineData,
                axisType: 'category',
                currentIndex: 4,

            },

            visualMap: {
                left: 'right',
                top: '10%',
                dimension: 1,
                hoverLink: true,
                min: 0,
//                max: max_num,
                itemWidth: 30,
                itemHeight: 120,
                calculable: true,
                precision: 1,
                text: ['大小'],
                textGap: 30,
                textStyle: {
                    color: '#b90c25'
                },
                inRange: {
                    symbolSize: [10, 70]
                },
                outOfRange: {
                    symbolSize: [10, 70],
                    color: ['rgba(255,255,255,.2)']
                },
                controller: {
                    inRange: {
                        color: ['#a2c6f8']
                    },
                    outOfRange: {
                        color: ['#444']
                    }
                }
            },
            tooltip: {
                show: true,
//                position: function (pos, params, dom, rect, size) {
//                    // 鼠标在左侧时 tooltip 显示到右侧，鼠标在右侧时 tooltip 显示到左侧。
//                    var obj = {top: 60};
//                    obj[['left', 'right'][+(pos[0] < size.viewSize[0] / 2)]] = 5;
//                    return obj;
//                },
                hideDelay: 600,
                formatter: function (params, ticket, callback) {
                    var result = '';
                    if (params.dataType == 'node') {
                        var id = params.name;
                        result = "<span style='color: sienna'>标题：</span> " + titleMap.get(id).replace(/(.{25})/g, '$1<br />') + "<br />";

                        result +="出度  "+nodeSNAList[id]['outdegree']+"<br />";
                        result +="入度  "+nodeSNAList[id]['indegree']+"<br />";
                        result +="相对中心度  "+nodeSNAList[id]['relativeCentrality']+"<br />"
                        result +="绝对接近中心度  "+nodeSNAList[id]['aCloseCentrality']+"<br />"
                        result +="相对接近中心度  "+nodeSNAList[id]['rCloseCentrality']+"<br />"

                        $.ajax({
                            type: "post",
//                            url: "tooltipContent",
                            data: {'table': tableMap.get(id), 'articleID': id},
                            contentType: "application/x-www-form-urlencoded; charset=utf-8",
                            success: function (msg) {
//                                var content = msg.replace(/(.{30})/g, '$1<br />'); //换行显示

                                var newStr = '';
                                if(msg.length <= 30) {
                                    newStr = msg;
                                }
                                var t = 30;
                                for (; t < msg.length; t+=30) {
                                    var tmp = msg.substring(t - 30, t);
                                    newStr = newStr + '<br />' + tmp;
                                }
                                result = result + newStr + "<br />" + msg.substring(t - 30, msg.length);
                                callback(ticket, result);
                            },
                            error: function (msg) {

                            }
                        });
                        return result;
                    } else if (params.dataType == 'edge') {
                        var src_id = params.data.source;
                        var tar_id = params.data.target;
                        result = "<span style='color: #b90c25'>source:</span> " + titleMap.get(src_id);
                        result += "<br />"
                        result += "<span style='color: #d54ac9'>target: </span>" + titleMap.get(tar_id);

                        return result;
                    }
                }
            },
            legend: [{
                // selectedMode: 'single',
                data: categories.map(function (a) {
                    return a.name;
                })
            }],
            toolbox: {
                show: true,
                feature: {
                    dataView: {
                        show: true,
                        readOnly: true
                    },
                    restore: {
                        show: true
                    },
                    saveAsImage: {
                        show: true
                    }
                }
            },
            animationDuration: 1500,
            animationEasingUpdate: 'quinticInOut',
            series: [{
//                name: '媒体',
                type: 'graph',
//                layout: 'force',

                force: {
                    initLayout: 'circular',
                    edgeLength: 100,
                    repulsion: 300,
                    gravity: 0.2,
                    layoutAnimation: true
                },
//                data: data,
//                edges: edges,
                categories: categories,
                focusNodeAdjacency: true,
                roam: true,
                label: {
                    normal: {
                        textStyle: {
                            color: ["#bda29a"],
                            fontStyle: 'oblique',
                            fontWeight: 'lighter'
                        },
                        show: false,
                        position: 'right',
                        formatter: function (params) {
                            return params.value[0];
                        }
                    }
                },
                edgeSymbol: ['none', 'arrow'],
                lineStyle: {
                    normal: {
                        color: "source",
                        width: 3,
                        curveness: 0.3
                    }
                }
            }]
        },
        options:edgeOptions,
    };

    myChart9.setOption(option9);
</script>
<script>
    var myChart10 = echarts.init(document.getElementById("graph"));

    var option10 = {
        series: [
            {
                type: 'scatter',
                data: [[0,0]],
                symbolSize: 1,
                label: {
                    normal: {
                        show: true,
                        formatter: [
                            '            {term|图说}',
                            ' 度数中心势  ：' +tip1,
                            ' 接近中心势  ：' + tip2,
                            ' 图规模 ：' + tip3,
                            ' 图密度 ：' + tip4,
                            ' 直径 ：' +tip5,
                            ' 平均长度 ：' + tip6,
                            ' 关联度 ：' + tip7,
                            ' 等级度 ：'+tip8,
                        ].join('\n'),
                        backgroundColor: '#eee',
                        // borderColor: '#333',
                        borderColor: 'rgb(199,86,83)',
                        borderWidth: 2,
                        borderRadius: 5,
                        padding: 10,
                        color: '#000',
                        fontSize: 14,
                        shadowBlur: 3,
                        shadowColor: '#888',
                        shadowOffsetX: 0,
                        shadowOffsetY: 3,
                        lineHeight: 30,
                        rich: {
                            term: {
                                fontSize: 18,
                                color: 'rgb(199,86,83)'
                            },
                            fregment1: {
                                backgroundColor: '#000',
                                color: 'yellow',
                                padding: 5
                            },
                            fregment2: {
                                backgroundColor: '#339911',
                                color: '#fff',
                                borderRadius: 15,
                                padding: 5
                            }
                        }
                    }
                }
            }
        ],
        xAxis: {
            axisLabel: {show: false},
            axisLine: {show: false},
            splitLine: {show: false},
            axisTick: {show: false},
            min: -1,
            max: 1
        },
        yAxis: {
            axisLabel: {show: false},
            axisLine: {show: false},
            splitLine: {show: false},
            axisTick: {show: false},
            min: -1,
            max: 1
        }
    };
    myChart10.setOption(option10);

</script>
<%--<script>
    var myChart11 = echarts.init(document.getElementById("keyword_related"));
    var option11 = {
        backgroundColor: new echarts.graphic.RadialGradient(0.3, 0.3, 0.8, [{
            offset: 0,
            color: '#FFFFFF'
        }, {
            offset: 1,
            color: '#FFFFFF'
        }]),

        tooltip: {},
        legend: [{
            formatter: function (name) {
                return echarts.format.truncateText(name, 40, '14px Microsoft Yahei', '…');
            },
            tooltip: {
                show: true
            },
            selectedMode: 'false',
            bottom: 20,
            data: ['核心', '非核心']
        }],
        toolbox: {
            show : true,
            feature : {
                dataView : {show: true, readOnly: true},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        animationDuration: 3000,
        animationEasingUpdate: 'quinticInOut',
        series: [{
            name: '关键词',
            type: 'graph',
            layout: 'force',
            force: {
                repulsion: 50
            },
            data: ${keywords},
            links: ${keywordRelated},
            categories: [{
                'name': '核心'
            }, {
                'name': '非核心'
            }],
            focusNodeAdjacency: true,
            roam: true,
            label: {
                normal: {

                    show: true,
                    position: 'top',

                }
            },
            lineStyle: {
                normal: {
                    color: 'source',
                    curveness: 0,
                    type: "solid"
                }
            }
        }]
    };
    myChart11.setOption(option11);
</script>--%>
<script>
    var myChart12 = echarts.init(document.getElementById("keyword_related"));
    var markLineOpt = {};

    var option12 = {
        backgroundColor: '',
        title: {
            text: '',
            x: '35%',
            y: 0
        },
        xAxis: [{
            gridIndex: 0,
            type: 'value',
            show: false,
            min: 0,
            max: 100,
            name: '市场需求指数',
            nameLocation: 'middle',
            nameGap: 30,

        }, ],
        yAxis: [{
            gridIndex: 0,
            min: 0,
            show: false,
            max: 100,
            name: '竞争强度指数',
            nameLocation: 'middle',
            nameGap: 30,
        }, ],
        series: [{
            name: '强相关',
            type: 'scatter',
            xAxisIndex: 0,
            yAxisIndex: 0,
            symbol: 'circle',
            symbolSize: 40,
            label: {
                normal: {
                    show: true,
                    formatter: function(param) {
                        return param.data[2];
                    },
                },

            },
            itemStyle: {
                normal: {
                    color: '#5aa8ee',
                    shadowColor: 'rgba(0,0,139, 0.8)',
                    shadowBlur: 15,

                    shadowOffsetX: 5,
                    shadowOffsetY: 5,
                    opacity: 0.9

                },

            },

            data: ${keywordRelated},

        },

             {
                name: '访问来源',
                type: 'pie',
                radius: ['0', '38%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: false,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: '#E3E3FF'
                        }, {
                            offset: 0.25,
                            color: '#DFDFFF'
                        }, {
                            offset: 0.50,
                            color: '#DFDFFF'
                        }, {
                            offset: 0.75,
                            color: '#DFDFFF'
                        },


                            {
                                offset: 1,
                                color: '#E3E3FF'
                            }
                        ], false),
                    },
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: [{
                    value: 335,
                    name: '直接访问'
                },

                ]
            },

            {
                name: '相关背景',
                type: 'pie',
                radius: ['38%', '77%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: false,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: '#FFFFFF'
                        }, {
                            offset: 0.10,
                            color: '#FFFFFF'
                        }, {
                            offset: 0.45,
                            color: '#DFDFFF'
                        }, {
                            offset: 0.55,
                            color: '#DFDFFF'
                        }, {
                            offset: 0.90,
                            color: '#FFFFFF'
                        },

                            {
                                offset: 1,
                                color: '#FFFFFF'
                            }
                        ], false),
                    },
                },

                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: [{
                    value: 3235,
                    name: '直接访问'
                },


                ]
            }, {
                name: '相关背景',
                type: 'pie',
                radius: ['76%', '100%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: false,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: '#FFFFFF'
                        },

                            {
                                offset: 0.30,
                                color: '#E3E3FF'
                            }, {
                                offset: 0.50,
                                color: '#E3E3FF'
                            }, {
                                offset: 0.70,
                                color: '#E3E3FF'
                            },

                            {
                                offset: 1,
                                color: '#FFFFFF'
                            }
                        ], false),
                    },
                },

                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: [{
                    value: 3235,
                    name: '直接访问'
                },


                ]
            }

        ]
    };
    myChart12.setOption(option12);
</script>

</body>

</html>