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
        <input type="hidden" value="${e.object.objectId}">
        <div class="span9" id="content">
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
                        <div class="block-content collapse in">
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
                        <div class="span6 chart">
                            <h5>媒体活跃度</h5>
                            <div id="mediaType" style="width:80%;height: 250px;"></div>
                        </div>
                        <div class="span5 chart">
                            <h5>媒体来源比</h5>
                            <div id="media" style="width:100%;height:250px"></div>
                        </div>
                        <div class="span6 chart">
                            <h5>境内外分布</h5>
                            <div id="area" style="width:60%;height:250px"></div>
                        </div>
                        <div class="span5 chart">
                            <h5>敏感分析</h5>
                            <div id="sensitive" style="width:60%;height: 250px;"></div>
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
                        <div class="pull-right"><span class="badge badge-warning">View More</span>

                        </div>
                    </div>
                    <div class="block-content collapse in">
                        <div class="span12">
                            <div id="transfer" style="width:1000px;height: 400px;"></div>
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
                            <div id="keyword_related" style="width:1000px;height: 400px;"></div>
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
                            <c:forEach items="${articleCommentList}" var="ac" varStatus="sts">
                                <div class="alert">
                                    <h4 class="alert-heading">观点${sts.index+1}：${ac.content} 【${ac.sourceCommentId}】</h4>
                                </div>
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

    var keyword = ${keywords};
    //基于准备好的dom,初始化echarts实例
    var myChart = echarts.init(document.getElementById('keywords'));

    var option = {
        tooltip : {},
        series : [ {
            type : 'wordCloud',
            shape:'smooth',
            gridSize : 2,
            sizeRange : [ 50, 100 ],
            rotationRange : [ 46, 80 ],
            textStyle : {
                normal : {
                    color: function(params) {
                        // build a color map as your need.
                        var colorList = [
                            '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                            /*'#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0',
                            '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B'*/
                        ];
                        return colorList[params.dataIndex]
                    },
                },
                emphasis : {
                    shadowBlur : 10,
                    shadowColor : ''
                }
            },
            data : keyword
        } ]
    };
    // 为echarts对象加载数据
    myChart.setOption(option);
    myChart.on('click', function (params) {
        window.open('https://www.baidu.com/s?wd=' + encodeURIComponent(params.name));

    });
</script>
<input type="hidden" value="${webs}">

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
                data: ${mediaList}
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
                                /*'#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0',
                                '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B'*/
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
                        {xAxis:0, y: 350, name:'Line', symbolSize:20, symbol: ''},
                        {xAxis:1, y: 350, name:'Bar', symbolSize:20, symbol: ''},
                        {xAxis:2, y: 350, name:'Scatter', symbolSize:20, symbol: ''},
                        {xAxis:3, y: 350, name:'K', symbolSize:20, symbol: ''},
                        {xAxis:4, y: 350, name:'Pie', symbolSize:20, symbol: ''},
                        {xAxis:5, y: 350, name:'Radar', symbolSize:20, symbol: ''},
                        {xAxis:6, y: 350, name:'Chord', symbolSize:20, symbol: ''},
                        {xAxis:7, y: 350, name:'Force', symbolSize:20, symbol: ''},
                        {xAxis:8, y: 350, name:'Map', symbolSize:20, symbol: ''},
                        {xAxis:9, y: 350, name:'Gauge', symbolSize:20, symbol: ''},
                        {xAxis:10, y: 350, name:'Funnel', symbolSize:20, symbol: ''},
                    ]
                }
            }
        ]
    };
    myChart4.setOption(option4);
</script>
<script>
    var myChart5 = echarts.init(document.getElementById('keyword_related'));
    var option5 = {
        title : {
            text: '',
            subtext: '',
            x:'right',
            y:'bottom'
        },
        tooltip : {
            trigger: 'item',
            formatter: '{a} : {b}'
        },
        toolbox: {
            show : true,
            feature : {
                restore : {show: true},
                magicType: {show: true, type: ['force', 'chord']},
                saveAsImage : {show: true}
            }
        },
        legend: {
            x: 'left',
            data:['','']
        },
        series : [
            {
                type:'force',
                name : "",
                ribbonType: false,
                categories : [
                    {
                        name: '人物'
                    },
                    {
                        name: '家人'
                    },
                    {
                        name:'朋友'
                    }
                ],
                itemStyle: {
                    normal: {
                        label: {
                            show: true,
                            textStyle: {
                                color: '#333'
                            }
                        },
                        nodeStyle : {
                            brushType : 'both',
                            borderColor : 'rgba(255,215,0,0.4)',
                            borderWidth : 1
                        },
                        linkStyle: {
                            type: 'curve'
                        }
                    },
                    emphasis: {
                        label: {
                            show: false
                            // textStyle: 0      // 默认使用全局文本样式，详见TEXTSTYLE
                        },
                        nodeStyle : {
                            //r: 30
                        },
                        linkStyle : {}
                    }
                },
                useWorker: false,
                minRadius : 15,
                maxRadius : 25,
                gravity: 1.1,
                scaling: 1.1,
                roam: 'move',
                nodes:${keywordList},
                links :${listkey},
            }
        ]
    };

    function focus(param) {
        var data = param.data;
        var links = option5.series[0].links;
        var nodes = option5.series[0].nodes;
        if (
            data.source !== undefined
            && data.target !== undefined
        ) { //点击的是边
            var sourceNode = nodes.filter(function (n) {return n.name == data.source})[0];
            var targetNode = nodes.filter(function (n) {return n.name == data.target})[0];
            console.log("选中了边 " + sourceNode.name + ' -> ' + targetNode.name + ' (' + data.weight + ')');
        } else { // 点击的是点
            console.log("选中了" + data.name + '(' + data.value + ')');
        }
    }
    myChart5.setOption(option5);
</script>
<input type="hidden" value="${event}">
<script>
    var myChart6 = echarts.init(document.getElementById('transfer'));
    var option6 = {
        title : {
            text: '',
            subtext: ''
        },
        tooltip : {
            trigger: 'item',
            formatter: "{b}: {c}"
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : false,

        series : [
            {
                name:'树图',
                type:'tree',
                orient: 'horizontal',  // vertical horizontal
                rootLocation: {x: 400, y: '60%'}, // 根节点位置  {x: 'center',y: 10}
                nodePadding: 20,
                symbol: 'circle',
                symbolSize: 40,
                itemStyle: {
                    normal: {
                        label: {
                            show: true,
                            position: 'inside',
                            textStyle: {
                                color: '#cc9999',
                                fontSize: 15,
                                fontWeight:  'bolder'
                            }
                        },
                        lineStyle: {
                            color: '#000',
                            width: 1,
                            type: 'broken' // 'curve'|'broken'|'solid'|'dotted'|'dashed'
                        }
                    },
                    emphasis: {
                        label: {
                            show: true
                        }
                    }
                },
                data: [
                    {
                        name: '',
                        value: 6,
//                        symbol: 'circle',
//                        symbolSize: 60,
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,
                                    position: 'left'
                                }
                            }
                        },

                        children: [
                            {
                                name: '转发',
                                value: ${transferNum},
                                symbol: 'circle',
                                symbolSize: 40,
                                itemStyle: {
                                    normal: {
                                        label: {
                                            show: true,
                                            position: 'left'
                                        }
                                    }
                                },
                                children: [
                                    /*{
                                        name: ${mediaList},
                                        symbol: 'circle',
                                        symbolSize: 20,
                                        value: 4,
                                        itemStyle: {
                                            normal: {
                                                color: '#fa6900',
                                                label: {
                                                    show: true,
                                                    position: 'right'
                                                },

                                            },
                                            emphasis: {
                                                label: {
                                                    show: false
                                                },
                                                borderWidth: 0
                                            }
                                        }
                                    },*/
                                    {
                                        name: '新闻',
                                        value: 4,
                                        symbol: 'circle',
                                        symbolSize: 20,
                                        itemStyle: {
                                            normal: {
                                                label: {
                                                    show: true,
                                                    position: 'right',
                                                    formatter: "{b}"
                                                },
                                                color: '#fa6900',
                                                borderWidth: 2,
                                                borderColor: '#cc66ff'

                                            },
                                            emphasis: {
                                                borderWidth: 0
                                            }
                                        }
                                    },
                                    {
                                        name: '论坛',
                                        value: 2,
                                        symbol: 'circle',
                                        symbolSize: 20,
                                        itemStyle: {
                                            normal: {
                                                label: {
                                                    position: 'right'
                                                },
                                                color: '#fa6900',
                                                brushType: 'stroke',
                                                borderWidth: 1,
                                                borderColor: '#999966',
                                            },
                                            emphasis: {
                                                borderWidth: 0
                                            }
                                        }
                                    }
                                ]
                            },

                            {
                                name: '首发',
                                symbol: 'circle',
                                symbolSize: 40,
                                itemStyle: {
                                    normal: {
                                        label: {
                                            show: true,
                                            position: 'left'
                                        }
                                    }
                                },
                                value: ${firstWebsite}
                            }
                        ]
                    }
                ]
            }
        ]
    };

    myChart6.setOption(option6);
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
            data:['新闻','政府','社交媒体','博客','论坛']
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
                data:[
                    {value:335, name:'新闻'},
                    {value:310, name:'社交媒体'},
                    {value:234, name:'政府'},
                    {value:135, name:'博客'},
                    {value:1548, name:'论坛'}
                ]
            }
        ]
    };
    myChart7.setOption(option7);
</script>
<script>
    //基于准备好的dom,初始化echarts实例
    var myChart8 = echarts.init(document.getElementById('sensitive'));
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
                data:${}

            }
        ]
    };

    // 为echarts对象加载数据
    myChart8.setOption(option8);
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
