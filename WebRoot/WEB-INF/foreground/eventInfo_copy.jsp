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
                                    【 ${al.newsResource}】
                                    <br/>

                                </c:forEach>
                                <strong>待续</strong>
                            </div>
                        </div>
                    </div>

                </div>


                <!-- /block -->
            </div>

            <div class="alert alert-block">
                <c:forEach items="${event}" var="e" varStatus="sts">
                <h4 class="alert-heading" id="011">舆情总结</h4>
                    ${e.event.eventSummary}

                <div class="alert alert-success">
                    综合信息可得，该事件可信度为<strong>真</strong>
                </div>
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
<script src="vendors/echarts.min.js"></script>
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
<c:forEach items="${keywords}" var="k" varStatus="sts">
    ${keywords}
</c:forEach>
<input type="hidden" value="${keywords}" id="key">
<script  type="text/javascript">
 var key = document.getElementById('key').value;
 alert(key);
    <%--var keyWords = ${keywords};--%>

    //基于准备好的dom,初始化echarts实例
    var myChart = echarts.init(document.getElementById('keywords'));
    function createRandomItemStyle() {
        var colors = ['#fda67e', '#81cacc', '#cca8ba', "#88cc81", "#82a0c5", '#fddb7e', '#735ba1', '#bda29a', '#6e7074', '#546570', '#c4ccd3'];
        return colors[parseInt(Math.random() * 10)];
        /*return {
            normal: {
                color: 'rgb(' + [
                    Math.round(Math.random() * 160),
                    Math.round(Math.random() * 160),
                    Math.round(Math.random() * 160)
                ].join(',') + ')'
            }
        };*/
    }

    var option = {
        title: {
            text: 'Google Trends',
            link: 'http://www.google.com/trends/hottrends'
        },
        tooltip: {
            show: true
        },
        series: [{
            name: 'Google Trends',
            type: 'wordCloud',
            size: ['80%', '80%'],
            textRotation : [0, 45, 90, -45],
            textPadding: 0,
            autoSize: {
                enable: true,
                minSize: 14
            },
            data: [
                {
                    name: key,
                    value: 10000,
                    itemStyle: {
                        normal: {
                            color: 'black'
                        }
                    }
                },
                {
                    name: "Macys",
                    value: 6181,
                    itemStyle: createRandomItemStyle()
                },
                {
                    name: "Amy Schumer",
                    value: 4386,
                    itemStyle: createRandomItemStyle()
                },
                {
                    name: "Jurassic World",
                    value: 4055,
                    itemStyle: createRandomItemStyle()
                },
                {
                    name: "Charter Communications",
                    value: 2467,
                    itemStyle: createRandomItemStyle()
                },
                {
                    name: "Chick Fil A",
                    value: 2244,
                    itemStyle: createRandomItemStyle()
                },
                {
                    name: "Planet Fitness",
                    value: 1898,
                    itemStyle: createRandomItemStyle()
                },
                {
                    name: "Pitch Perfect",
                    value: 1484,
                    itemStyle: createRandomItemStyle()
                },
                {
                    name: "Express",
                    value: 1112,
                    itemStyle: createRandomItemStyle()
                },
                {
                    name: "Home",
                    value: 965,
                    itemStyle: createRandomItemStyle()
                },

            ]
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
    alert(websdata);
    alert(webstimedata);
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
                data : ['2017.11.07','2017.11.08','2017.11.09']

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
</body>

</html>
