<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//UserEntity user = (UserEntity) session.getAttribute("user");
%>

<!DOCTYPE html>
<html class="no-js">
    
    <head>
        <title>全文检索 - socialmind大平台</title>
        <base href="<%=basePath%>foreground/"/>
        <!-- Bootstrap -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
        <link href="vendors/easypiechart/jquery.easy-pie-chart.css" rel="stylesheet" media="screen">
        <link href="assets/styles.css" rel="stylesheet" media="screen">
		<%--<script src="bootstrap/js/jquery-1.11.2.min.js"></script>--%>
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <script src="vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>

        <link rel="stylesheet" type="text/css" href="css/style/list.css"/>
        <link rel="stylesheet" type="text/css" href="css/dialog.css"/>
        <link rel="stylesheet" type="text/css" href="css/style/manhuaDate.1.0.css"/>
        <script type="text/javascript" src="js/jquery-1.5.1.js"></script><!--日期控件，JS库版本不能过高否则tab会失效-->
        <script type="text/javascript" src="js/datejs.js"></script>
        <script type="text/javascript" src="js/ui.tab.js"></script>
        <script type="text/javascript" src="js/dialog.js"></script>


        <script type="text/javascript" src="<%=basePath%>foreground/js/common.js"></script>
        <script type="text/javascript" src="<%=basePath%>foreground/js/quick_links.js"></script>

        <script type="text/javascript">
            $(document).ready(function(){
                var tab = new $.fn.tab({
                    tabList:"#demo1 .ui-tab-container .ui-tab-list li",
                    contentList:"#demo1 .ui-tab-container .ui-tab-content"
                });
                var tab = new $.fn.tab({
                    tabList:"#demo1 .ui-tab-container .ui-tab-list2 li",
                    contentList:"#demo1 .ui-tab-container .ui-tab-content2"
                });
            });
        </script>
        <script type="text/javascript">
            $(function (){
                $("input.mh_date").datejs({
                    Event : "click",//可选
                    Left : 0,//弹出时间停靠的左边位置
                    Top : -16,//弹出时间停靠的顶部边位置
                    fuhao : "-",//日期连接符默认为-
                    isTime : false,//是否开启时间值默认为false
                    beginY : 2010,//年份的开始默认为1949
                    endY :2015//年份的结束默认为2049
                });
            });
        </script>
        <script type="text/javascript">
            $(document).ready(function(e) {
                $("#selectList").find(".more").toggle(function(){
                    $(this).addClass("more_bg");
                    $(".more-none").show()
                },function(){
                    $(this).removeClass("more_bg");
                    $(".more-none").hide()
                });
            });
        </script>
        <script type="text/javascript">
            $(document).ready(function(){
                var taboy_box=$(".lefttable-list");
                taboy_box.children("tbody").find("tr:gt(2)").hide();
                $(".leftbox-morea").toggle(function(){
                            $(this).parent().prev().find("tr").show();
                            $(this).addClass("more-i")
                        },function(){
                            $(this).removeClass("more-i");
                            $(this).parent().prev().children("tbody").find("tr:gt(2)").hide();
                        }
                );
            });
        </script>
        <style>
            html,body,div,h2,p{margin: 0;padding: 0;}
            html{font: 1em Arial, Helvetica, sans-serif;color: #444;}
            p{margin-bottom: 5px;}
            #container{margin: 0 auto;width: 600px;}
            #container h2{font-size: 20px;color: #0087f1;}
            #wrap{position: relative;padding: 10px;overflow: hidden;}
            #gradient{width: 100%;height: 35px;background: url() repeat-x;position: absolute;bottom: 0;left: 0;}
            #read-more{padding: 5px; border-top: 1px double #ddd; background: #fff;color: #333;}
            #read-more a{padding-right: 22px;background: url() no-repeat 100% 50%;font-weight: bold;text-decoration: none;}
            #read-more a: hover{color: #000;}

            .text{
                display: -webkit-box;  /*将对象作为弹性盒子模型显示*/
                overflow: hidden; /**/
                text-overflow:ellipsis;
                word-break:break-all;
                -webkit-box-orient:vertical;/*伸缩盒对象的子元素的排列方式-垂直*/
                -webkit-line-clamp:10; /*限制在一个块级元素显示的文本的行数*/
            }
        </style>
    </head>
    <body>

    <!-- 引入头模板 -->
    <!-- 引入头模板 -->
    <jsp:include page="/static/fore_header.jsp" flush="true"></jsp:include>

    <div class="span3" id="sidebar" style="margin:4em 0em">
        <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
            <li>
                <a href="index.html"><i class="icon-chevron-right"></i> 全文检索</a>
            </li>
            <li>
                <a href="calendar.html"><i class="icon-chevron-right"></i> 热门事件</a>
            </li>
            <li>
                <a href="stats.html"><i class="icon-chevron-right"></i> 本单位/企业/个人/政府相关事件</a>
            </li>
        </ul>
    </div>
        <div class="container-fluid">
            <div class="row-fluid">
                <!--/span-->
                <div class="span9" id="content">
                    <div class="row-fluid">
                        <!-- block -->
                        <div class="block" style="margin: 4em 0em;">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">全文检索</div>
                            </div>
                            <form action="<%=basePath%>retrivalResults">
                                <!-- 判别全文检索 -->
                                <input type="hidden" name="flag" value="all"/>
                                <div class="block-content collapse in">
                                    <div align="center"><input type="text" name="name" data-required="1" class="span5 m-wrap" value="${object.name}">
                                        <button type="submit" class="btn btn-success" style="margin: -10px 0 0 10px;">搜索</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <!-- /block -->
                    </div>
                        <form action="<%=basePath%>retrivalResults" method="post">
                            <!-- 判别条件检索 -->
                            <input type="hidden" name="flag" value="condition"/>
                            <input type="hidden" name="objectId" value="${object.objectId}" id="objId"/>
                            <input type="hidden" name="name" value="${object.name}" id="objName"/>
                            <input type="hidden" value="<%=basePath%>" id="basePath"/>
                            <input type="hidden" value="" id="support"/>
                            <input type="hidden" value="" id="collect"/>
                            <input type="hidden" value="" id="isTrue"/>
                            <div class="w1200">
                            <div class="list-screen">
                                <div class="screen-top" style="position:relative;">
                                    <input type="submit" value="搜索" id="submit-btn" style="border: 0;"/>
                                </div>
                                <div style="padding:10px 30px 10px 10px;"><div class="screen-address">
                                    <div class="list-tab">
                                        <div id="demo1" class="clearfix">
                                            <div class="jiud-name">网站类型</div>
                                            <div class="ui-tab-container">
                                                <ul class="clearfix ui-tab-list">
                                                    <c:forEach items="${dataDictionarys}" var="d">
                                                        <li class="">${d.attributeValue}</li>
                                                    </c:forEach>
                                                </ul>
                                                <div class="ui-tab-bd">
                                                    <c:forEach items="${dataDictionarys}" var="d">
                                                        <div class="ui-tab-content clearfix" style="display: none;">
                                                            <c:forEach items="${websites}" var="web">
                                                                <c:if test="${web.websiteTypeId == d.dataDictionaryId}">
                                                                    <p>
                                                                        <label>
                                                                            <input name="websiteId" type="checkbox" value="${web.websiteId}" />
                                                                            ${web.websiteName}</label>
                                                                    </p>
                                                                </c:if>
                                                            </c:forEach>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                    <div class="screen-term">
                                        <div class="selectNumberScreen">
                                            <div id="selectList" class="screenBox screenBackground">
                                                <dl class="listIndex" attr="时间">
                                                    <dt>时间</dt>
                                                    <dd>
                                                        <label><a href="javascript:;" values2="" values1="" attrval="不限">不限</a></label>
                                                        <label>
                                                            <input name="time" type="radio" value="day1" />
                                                            <a href="javascript:;" values2="99" values1="1" attrval="1-99">最近一天</a></label>
                                                        <label>
                                                            <input name="time" type="radio" value="week1" />
                                                            <a href="javascript:;" values2="300" values1="100" attrval="100-300">最近一周 </a></label>
                                                        <label>
                                                            <input name="time" type="radio" value="month1" />
                                                            <a href="javascript:;" values2="600" values1="300" attrval="300-600">最近一月</a></label>
                                                        <label>
                                                            <input name="time" type="radio" value="year1" />
                                                            <a href="javascript:;" values2="1500" values1="600" attrval="5000以上">最近一年</a></label>

                                                    </dd>
                                                </dl>
                                                <dl class=" listIndex" attr="terminal_os_s">
                                                    <dt>相似度</dt>
                                                    <dd>
                                                        <label><a href="javascript:;" values2="" values1="" attrval="不限">不限</a></label>
                                                        <label>
                                                            <input name="similarDegree" type="radio" value="40" />
                                                            <a href="javascript:;" values2="99" values1="" attrval="1-99">大于0.4</a></label>
                                                        <label>
                                                            <input name="similarDegree" type="radio" value="60" />
                                                            <a href="javascript:;" values2="300" values1="" attrval="100-300">大于0.6</a></label>
                                                        <label>
                                                            <input name="similarDegree" type="radio" value="70" />
                                                            <a href="javascript:;" values2="600" values1="" attrval="300-600">大于0.7</a></label>
                                                        <label>
                                                            <input name="similarDegree" type="radio" value="80" />
                                                            <a href="javascript:;" values2="1500" values1="" attrval="5000以上">大于0.8</a></label>
                                                    </dd>
                                                </dl>
                                                <dl class=" listIndex" attr="terminal_os_s">
                                                    <dt>参与度>50</dt>
                                                    <dd data-more=true>
                                                        <label><a href="javascript:;" values2="" values1="" attrval="不限">不限</a></label>
                                                        <label>
                                                            <input name="partQltyCbx" type="checkbox" value="scanNumber" autocomplete="off"/>
                                                            <a href="javascript:;" values2="" values1="" attrval="小米">浏览量</a></label>
                                                        <label>
                                                            <input name="partQltyCbx" type="checkbox" value="likeNumber" autocomplete="off"/>
                                                            <a href="javascript:;" values2="" values1="" attrval="华为">点赞量</a> </label>
                                                        <label>
                                                            <input name="partQltyCbx" type="checkbox" value="commentNumber" autocomplete="off"/>
                                                            <a href="javascript:;" values2="" values1="" attrval="华为">评论量</a> </label>
                                                        <label>
                                                            <input name="partQltyCbx" type="checkbox" value="collectNumber" autocomplete="off"/>
                                                            <a href="javascript:;" values2="" values1="" attrval="华为">收藏量</a> </label>
                                                    </dd>
                                                </dl>

                                            </div>
                                        </div>
                                    </div>

                                </div>

                                <div class="hasBeenSelected clearfix">
                                    <div style="float:right;" class="eliminateCriteria">【清空全部】 </div>
                                    <dl>
                                        <dt>已选条件：</dt>
                                        <dd style="DISPLAY: none" class=clearDd>
                                            <div class=clearList style="height: 50px; font-size: 12px;"></div>
                                    </dl>
                                </div>
                                <script type="text/javascript" src="js/shaixuan.js"></script>
                            </div>
                        </div>
                        </form>
					<div class="row-fluid">
                        <!-- block -->
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">检索结果</div>
                            </div>
                            <div class="block-content collapse in">
                                <div class="span12">
  									<div id="example_wrapper" class="dataTables_wrapper form-inline" role="grid">
									<div class="row"><div class="span6"><div id="example_length" class="dataTables_length">
									<label>
									<select size="1" name="example_length" aria-controls="example"  style="margin-left:40px; width: 60px;">
									<option value="10" selected="selected">10</option>
									<option value="25">25</option><option value="50">50</option>
									<option value="100">100</option></select> records per page</label>
									</div>
									</div>
									<div class="span6">
									
									<div class="dataTables_filter" id="example_filter">
									<label>Search: <input type="text" aria-controls="example"></label>
									</div>
									</div>
									</div>
									<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered dataTable" id="example" aria-describedby="example_info">
										<thead>
											<tr role="row">
                                                <th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Rendering engine: activate to sort column descending" style="width: 206px;">
                                                标题</th>
                                            </tr>
										</thead>
										
									    <tbody role="alert" aria-live="polite" aria-relevant="all">
                                            <c:forEach items="${articles}" var="a" varStatus="sts">
                                                <tr class="gradeA odd">
                                                    <td class="">
                                                        <a href="<%=basePath%>authorInfo?authorId=${a.authorEntity.authorId}" target="_blank"><span class="badge badge-info" style="background-color: #118ecc; float: left;">${a.authorEntity.name}</span></a><br>
                                                        <b><a href="${a.newsResource}" target="_blank">${a.title}</a></b>
                                                        <div class="pull-right">
                                                            <span class="badge badge-info" style="background-color: #f89406">${a.similarDegree}</span>
                                                        </div>
                                                        <div id="content${sts.index}" class="text" style="">${a.content}</div>
                                                        <br/>
                                                            ${a.postTime}
                                                        <div style="float: right">
                                                            来自于:${a.websiteEntity.websiteName}
                                                        </div>
                                                        <br/>
                                                        <span class="badge badge-info" style="background-color: #ee5f5b; float: left; color:white;" onclick="commentShow(${sts.index});">评论量:${a.commentNumber}</span>
                                                        浏览量:${a.scanNumber}
                                                        参与量:${a.participationNumber}
                                                        喜欢量:${a.likeNumber}
                                                        推荐量:${a.recommendNumber}
                                                        收藏量:${a.collectNumber}
                                                    </td>
                                                </tr>
                                                <tr class="alert alert-info alert-block" style="color: #3a87ad;">
                                                    <td>
                                                        <div id="comment${sts.index}" style="display: none">
                                                            <c:forEach items="${a.articleComments}" var="c">
                                                                <c:if test="${c.fatherCommentId == null}">
                                                                    <a href="<%=basePath%>authorInfo?authorId=${c.authorEntity.authorId}" target="_blank"><b class="badge badge-info">${c.authorEntity.name}</b></a>  <br/>
                                                                    ${c.content} <br/>
                                                                    ${c.commentTime}<br/>
                                                                </c:if>
                                                                <c:forEach items="${a.articleComments}" var="c2">
                                                                    <c:if test="${c2.fatherCommentId == c.articleCommentId}">
                                                                        <div style="margin-left: 3%">
                                                                            <a href="<%=basePath%>authorInfo?authorId=${c2.authorEntity.authorId}" target="_blank"><b class="badge badge-info">${c2.authorEntity.name}</b></a> <br/>
                                                                                    <span style="color: #7b14ed;;">@${c.authorEntity.name}</span>
                                                                                ${c2.content} <br/>
                                                                                点赞量: ${c2.likeNumber}
                                                                                回复量: ${c2.replayNumber}
                                                                                <span style="margin-left: 10px;">${c2.commentTime}</span><br/>
                                                                        </div>
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:forEach>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
				                        </tbody>
                                    </table>
                                        <div class="row"><div class="span6"></div><div class="span6"><div class="dataTables_paginate paging_bootstrap pagination"><ul><li class="prev disabled"><a href="#">← Previous</a></li><li class="active"><a href="#">1</a></li><li><a href="#">2</a></li><li><a href="#">3</a></li><li><a href="#">4</a></li><li><a href="#">5</a></li><li class="next"><a href="#">Next → </a></li></ul></div></div></div></div>
                                </div>
                            </div>
                        </div>
                        <!-- /block -->
                    </div>

                    <script>
                        function commentShow(index) {
                            var css = $("#comment"+index).css("display");
                            if (css == 'none'){
                                $("#comment"+index).css("display", "");
                            }else{
                                $("#comment"+index).css("display", "none");
                            }
                        }
                    </script>

                    <div class="row-fluid">
                    </div>
                </div>
            </div>
        </div>
            <!-- 引入尾部模板 -->
            <jsp:include page="/static/fore_footer.jsp"></jsp:include>

            <!--/.fluid-container-->
        <%--<script src="vendors/jquery-1.9.1.min.js"></script>--%>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="vendors/easypiechart/jquery.easy-pie-chart.js"></script>
        <script src="assets/scripts.js"></script>
        <script>
        $(function() {
            // Easy pie charts
            $('.chart').easyPieChart({animate: 1000});
        });
        </script>



    <!--右侧贴边导航quick_links.js控制-->
    <div class="mui-mbar-tabs">
        <div class="quick_link_mian">
            <div class="quick_links_panel">
                <div id="quick_links" class="quick_links">
                    <li id="shopCart">
                        <a href="javascript:void(0);" onclick="" class="message_list" ><i class="message"></i><div class="span">舆情对象操作</div></a>
                    </li>
                </div>
                <div class="quick_toggle">

                    <li><a href="#top" class="return_top"><i class="top"></i></a></li>
                </div>
            </div>
            <div id="quick_links_pop" class="quick_links_pop hide"></div>
        </div>
    </div>

    <!--[if lte IE 8]>
    <script src="js/ieBetter.js"></script>
    <![endif]-->

    <script type="text/javascript" src="<%=basePath%>foreground/js/parabola.js"></script>
    <script type="text/javascript">
        $(".quick_links_panel li").mouseenter(function(){
            $(this).children(".mp_tooltip").animate({left:-92,queue:true});
            $(this).children(".mp_tooltip").css("visibility","visible");
            $(this).children(".ibar_login_box").css("display","block");
        });
        $(".quick_links_panel li").mouseleave(function(){
            $(this).children(".mp_tooltip").css("visibility","hidden");
            $(this).children(".mp_tooltip").animate({left:-121,queue:true});
            $(this).children(".ibar_login_box").css("display","none");
        });
        $(".quick_toggle li").mouseover(function(){
            $(this).children(".mp_qrcode").show();
        });
        $(".quick_toggle li").mouseleave(function(){
            $(this).children(".mp_qrcode").hide();
        });

        // 元素以及其他一些变量
        var eleFlyElement = document.querySelector("#flyItem"), eleShopCart = document.querySelector("#shopCart");
        var numberItem = 0;
        // 抛物线运动
        var myParabola = funParabola(eleFlyElement, eleShopCart, {
            speed: 400, //抛物线速度
            curvature: 0.0008, //控制抛物线弧度
            complete: function() {
                eleFlyElement.style.visibility = "hidden";
                eleShopCart.querySelector("span").innerHTML = ++numberItem;
            }
        });
        // 绑定点击事件
        if (eleFlyElement && eleShopCart) {

            [].slice.call(document.getElementsByClassName("btnCart")).forEach(function(button) {
                button.addEventListener("click", function(event) {
                    // 滚动大小
                    var scrollLeft = document.documentElement.scrollLeft || document.body.scrollLeft || 0,
                            scrollTop = document.documentElement.scrollTop || document.body.scrollTop || 0;
                    eleFlyElement.style.left = event.clientX + scrollLeft + "px";
                    eleFlyElement.style.top = event.clientY + scrollTop + "px";
                    eleFlyElement.style.visibility = "visible";
                    // 需要重定位
                    myParabola.position().move();
                });
            });
        }
    </script>

    <script>
        var arrObj = [1];
        $(document).ready(function () {
            sideInfo(arrObj);
        });
    </script>

    </body>
</html>