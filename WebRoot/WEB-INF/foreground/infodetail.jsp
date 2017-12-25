<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/12/18
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
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
            <a href="index.html"><i class="icon-chevron-right"></i> 详细信息</a>
        </li>
    </ul>
</div>
<div class="container-fluid" style="margin-top: 4%;">
    <div class="row-fluid">
        <!--/span-->
        <div class="span9" id="content">

            <div class="row-fluid">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left">检索结果</div>
                    </div>
                    <div class="block-content collapse in">
                        <div class="span12">
                            <div id="example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                <%--<div class="row"><div class="span6"><div id="example_length" class="dataTables_length">
                                    <label>
                                        <select size="1" name="example_length" aria-controls="example"  style="margin-left:40px; width: 60px;">
                                            <option value="10" selected="selected">10</option>
                                            <option value="25">25</option><option value="50">50</option>
                                            <option value="100">100</option>
                                        </select> records per page
                                    </label>
                                </div>--%>
                            </div>
                            <div class="span6">

                                        <%--<div class="dataTables_filter" id="example_filter">--%>
                                            <%--<label>Search: <input type="text" aria-controls="example"></label>--%>
                                        <%--</div>--%>
                            </div>
                                </div>
                                <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered dataTable" id="example" aria-describedby="example_info">

                                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                                    <c:forEach items="${sensitiveList}" var="s" varStatus="sts">
                                        <tr class="gradeA odd">

                                           <td class="">

                                                   <a href="<%=basePath%>authorInfo?authorId=${s.article.articleId}"><span class="badge badge-info" style="background-color: #118ecc; float: left;">${s.article.authorEntity.name}</span></a>
                                                   <span class="badge badge-info" style="background-color: #FFFFFF;float: right"><font color="red">敏感类型：${s.sensitiveType}</font></span><br>
                                                <b>
                                                    <%--<a href="${a.newsResource}" target="_blank">--%>
                                                    <a>${s.article.title}</a></b>

                                                <div class="pull-right">
                                                    <span class="badge badge-info" style="background-color: #f89406">相似度：${s.article.similarDegree}</span>
                                                </div>
                                                <div id="content${sts.index}" class="text" style="">${s.article.content}</div>
                                                <br/>
                                                    ${s.article.postTime}
                                                <div style="float: right">
                                                    来自于:${s.article.websiteEntity.websiteName}
                                                </div>
                                                <br/>
                                                <span class="badge badge-info" style="background-color: #ee5f5b; float: left; color:white;" onclick="commentShow(${sts.index});">评论量:${s.article.commentNumber}</span>
                                                浏览量:${s.article.scanNumber}
                                                参与量:${s.article.participationNumber}
                                                喜欢量:${s.article.likeNumber}
                                                推荐量:${s.article.recommendNumber}
                                                收藏量:${s.article.collectNumber}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:forEach items="${sensitiveInfo}" var="a" varStatus="sts">
                                        <tr class="gradeA odd">

                                            <td class="">

                                                <a href="<%=basePath%>authorInfo?authorId=${a.article.articleId}"><span class="badge badge-info" style="background-color: #118ecc; float: left;">${a.article.authorEntity.name}</span></a>
                                                <span class="badge badge-info" style="background-color: #FFFFFF;float: right"><font color="red">敏感类型：${a.sensitiveType}</font></span><br>
                                                <b>
                                                        <%--<a href="${a.newsResource}" target="_blank">--%>
                                                    <a>${a.article.title}</a></b>

                                                <div class="pull-right">
                                                    <span class="badge badge-info" style="background-color: #f89406">相似度：${a.article.similarDegree}</span>
                                                </div>
                                                <div id="content${sts.index}" class="text" style="">${a.article.content}</div>
                                                <br/>
                                                    ${a.article.postTime}
                                                <div style="float: right">
                                                    来自于:${a.article.websiteEntity.websiteName}
                                                </div>
                                                <br/>
                                                <span class="badge badge-info" style="background-color: #ee5f5b; float: left; color:white;" onclick="commentShow(${sts.index});">评论量:${a.article.commentNumber}</span>
                                                浏览量:${a.article.scanNumber}
                                                参与量:${a.article.participationNumber}
                                                喜欢量:${a.article.likeNumber}
                                                推荐量:${a.article.recommendNumber}
                                                收藏量:${a.article.collectNumber}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:forEach items="${mediaList}" var="m" varStatus="sts">
                                        <tr class="gradeA odd">

                                            <td class="">

                                                <a href="<%=basePath%>authorInfo?authorId=${m.articleId}"><span class="badge badge-info" style="background-color: #118ecc; float: left;">${m.authorEntity.name}</span></a>
                                                <span class="badge badge-info" style="background-color: #FFFFFF;float: right"><font color="red">媒体类型：${m.websiteEntity.websiteUrl}</font></span><br>
                                                <b>
                                                        <%--<a href="${a.newsResource}" target="_blank">--%>
                                                    <a>${m.title}</a></b>

                                                <div class="pull-right">
                                                    <span class="badge badge-info" style="background-color: #f89406">相似度：${m.similarDegree}</span>
                                                </div>
                                                <div id="content${sts.index}" class="text" style="">${m.content}</div>
                                                <br/>
                                                    ${m.postTime}
                                                <div style="float: right">
                                                    来自于:${m.websiteEntity.websiteName}
                                                </div>
                                                <br/>
                                                <span class="badge badge-info" style="background-color: #ee5f5b; float: left; color:white;" onclick="commentShow(${sts.index});">评论量:${m.commentNumber}</span>
                                                浏览量:${m.scanNumber}
                                                参与量:${m.participationNumber}
                                                喜欢量:${m.likeNumber}
                                                推荐量:${m.recommendNumber}
                                                收藏量:${m.collectNumber}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:forEach items="${websiteList}" var="w" varStatus="sts">
                                        <tr class="gradeA odd">

                                            <td class="">

                                                <a href="<%=basePath%>authorInfo?authorId=${w.articleId}"><span class="badge badge-info" style="background-color: #118ecc; float: left;">${w.authorEntity.name}</span></a>
                                                <span class="badge badge-info" style="background-color: #FFFFFF;float: right"><font color="red">网站类型：${w.websiteEntity.websiteName}</font></span><br>
                                                <b>
                                                        <%--<a href="${a.newsResource}" target="_blank">--%>
                                                    <a>${w.title}</a></b>

                                                <div class="pull-right">
                                                    <span class="badge badge-info" style="background-color: #f89406">相似度：${w.similarDegree}</span>
                                                </div>
                                                <div id="content${sts.index}" class="text" style="">${w.content}</div>
                                                <br/>
                                                    ${w.postTime}
                                                <div style="float: right">
                                                    来自于:${w.websiteEntity.websiteName}
                                                </div>
                                                <br/>
                                                <span class="badge badge-info" style="background-color: #ee5f5b; float: left; color:white;" onclick="commentShow(${sts.index});">评论量:${w.commentNumber}</span>
                                                浏览量:${w.scanNumber}
                                                参与量:${w.participationNumber}
                                                喜欢量:${w.likeNumber}
                                                推荐量:${w.recommendNumber}
                                                收藏量:${w.collectNumber}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:forEach items="${articleInfo}" var="w" varStatus="sts">
                                        <tr class="gradeA odd">

                                            <td class="">

                                                <a href="<%=basePath%>authorInfo?authorId=${w.articleId}"><span class="badge badge-info" style="background-color: #118ecc; float: left;">${w.authorEntity.name}</span></a>
                                                <br>
                                                <b>
                                                        <%--<a href="${a.newsResource}" target="_blank">--%>
                                                    <a>${w.title}</a></b>

                                                <div class="pull-right">
                                                    <span class="badge badge-info" style="background-color: #f89406">相似度：${w.similarDegree}</span>
                                                </div>
                                                <div id="content${sts.index}" class="text" style="">${w.content}</div>
                                                <br/>
                                                    ${w.postTime}
                                                <div style="float: right">
                                                    来自于:${w.websiteEntity.websiteName}
                                                </div>
                                                <br/>
                                                <span class="badge badge-info" style="background-color: #ee5f5b; float: left; color:white;" onclick="commentShow(${sts.index});">评论量:${w.commentNumber}</span>
                                                浏览量:${w.scanNumber}
                                                参与量:${w.participationNumber}
                                                喜欢量:${w.likeNumber}
                                                推荐量:${w.recommendNumber}
                                                收藏量:${w.collectNumber}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:forEach items="${nosensitive}" var="w" varStatus="sts">
                                        <tr class="gradeA odd">

                                            <td class="">

                                                <a href="<%=basePath%>authorInfo?authorId=${w.articleId}"><span class="badge badge-info" style="background-color: #118ecc; float: left;">${w.authorEntity.name}</span></a>
                                                <br>
                                                <span class="badge badge-info" style="background-color: #FFFFFF;float: right"><font color="red">数据类型：非敏感</font></span><br>
                                                <b>
                                                        <%--<a href="${a.newsResource}" target="_blank">--%>
                                                    <a>${w.title}</a></b>

                                                <div class="pull-right">
                                                    <span class="badge badge-info" style="background-color: #f89406">相似度：${w.similarDegree}</span>
                                                </div>
                                                <div id="content${sts.index}" class="text" style="">${w.content}</div>
                                                <br/>
                                                    ${w.postTime}
                                                <div style="float: right">
                                                    来自于:${w.websiteEntity.websiteName}
                                                </div>
                                                <br/>
                                                <span class="badge badge-info" style="background-color: #ee5f5b; float: left; color:white;" onclick="commentShow(${sts.index});">评论量:${w.commentNumber}</span>
                                                浏览量:${w.scanNumber}
                                                参与量:${w.participationNumber}
                                                喜欢量:${w.likeNumber}
                                                推荐量:${w.recommendNumber}
                                                收藏量:${w.collectNumber}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${!empty arealist}">
                                    <c:forEach items="${arealist}" var="area" varStatus="sts">
                                        <c:forEach items="${area}" var="w">
                                        <tr class="gradeA odd">

                                            <td class="">

                                                <a href="<%=basePath%>authorInfo?authorId=${w.articleId}"><span class="badge badge-info" style="background-color: #118ecc; float: left;">${w.authorEntity.name}</span></a>
                                                <br>
                                                <b>
                                                        <%--<a href="${a.newsResource}" target="_blank">--%>
                                                    <a>${w.title}</a></b>

                                                <div class="pull-right">
                                                    <span class="badge badge-info" style="background-color: #f89406">相似度：${w.similarDegree}</span>
                                                </div>
                                                <div id="content${sts.index}" class="text" style="">${w.content}</div>
                                                <br/>
                                                    ${w.postTime}
                                                <div style="float: right">
                                                    来自于:${w.websiteEntity.websiteName}
                                                </div>
                                                <br/>
                                                <span class="badge badge-info" style="background-color: #ee5f5b; float: left; color:white;" onclick="commentShow(${sts.index});">评论量:${w.commentNumber}</span>
                                                浏览量:${w.scanNumber}
                                                参与量:${w.participationNumber}
                                                喜欢量:${w.likeNumber}
                                                推荐量:${w.recommendNumber}
                                                收藏量:${w.collectNumber}
                                            </td>
                                        </tr>
                                        </c:forEach>
                                    </c:forEach>
                                    </c:if>
                                    <c:if test="${empty arealist}">
                                        <div align="center">暂无数据</div>
                                    </c:if>
                                    </tbody>
                                </table>
                               <%-- <div class="row">
                                    <div class="span6"></div>
                                    <div class="span6">
                                        <div class="dataTables_paginate paging_bootstrap pagination">
                                            <ul>
                                                <li class="prev disabled"><a href="#">← Previous</a></li>
                                                <li class="active"><a href="#">1</a></li>
                                                <li><a href="#">2</a></li>
                                                <li><a href="#">3</a></li>
                                                <li><a href="#">4</a></li>
                                                <li><a href="#">5</a></li>
                                                <li class="next"><a href="#">Next → </a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>--%>

                            </div>
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
