<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/10/30
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>制作简报-3</title>
    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <link href="vendors/easypiechart/jquery.easy-pie-chart.css" rel="stylesheet" media="screen">
    <link href="assets/styles.css" rel="stylesheet" media="screen">
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script src="vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    <script src="bootstrap/js/jquery-1.8.3.js"></script>
    <style type="text/css">
        *{margin:0;padding:0;}
        ol, ul {list-style: none;}
        blockquote,q {quotes: none;}
        a{text-decoration:none;color:#2d2f30;-webkit-transition: all .3s linear;-moz-transition: all .3s linear;-o-transition: all .3s linear;-ms-transition: all .3s linear;transition: all .3s linear;}
        a:focus{ outline:none;}
        .content{margin:auto;width:1200px;}
        .sx_updown{width:100%;border-top:1px solid #f2f2f2;background:#fff;overflow:hidden;z-index:9999;position:absolute;left:1;top:60px;text-align:left;}
        .updown_box{float:left;border-left:1px solid #f2f2f2;padding:0 10px;-webkit-box-sizing: content-box;-moz-box-sizing: content-box; box-sizing: content-box;}
        .updown_box h3{font-size:16px;margin-bottom:18px;padding-left:10px;}
        .updown_box ul li{}
        .updown_box ul li a{display:block;font-size:14px;padding:6px 8px;line-height:18px;}
        .updown_box ul li.selected a{background:#7ecbc8;color:#fff;}
        .w136{border-left:0;padding:0;}
        .w290{}
        .w160{padding:0 40px;}
        .sousuo{padding:10px 0 36px 0;width:100%;}
        .select-result{width:900px;float:left;}
        .select-result ul li a{height:30px;padding:0 24px 0 10px;line-height:30px;display:block;position:relative;float:left;margin:0 10px 10px 0;color:#fff;font-size:14px;background:url(close1.png) 90% center no-repeat #7ecbc8;}
    </style>
</head>

<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#">social mind舆情分析平台</a>
            <div class="nav-collapse collapse">
                <ul class="nav pull-right">
                    <li class="dropdown">
                        <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user"></i> 吴连伟 <i class="caret"></i>

                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a tabindex="-1" href="person_info.html">个人中心</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a tabindex="-1" href="login.html">Logout</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav">
                    <li class="">
                        <a href="heatIndex.html">热度指标</a>
                    </li>
                    <li class="">
                        <a href="emotionMap.html">情绪地图</a>
                    </li>
                    <li class="">
                        <a href="info_dectection.html">信息监测</a>
                    </li>
                    <li class="">
                        <a href="fullRetrival.html">全文检索</a>
                    </li>
                    <li class="">
                        <a href="event.html">全网事件分析</a>
                    </li>
                    <li class="">
                        <a href="makeReporter_1.html">简报制作</a>
                    </li>
                </ul>
            </div>
            <!--/.nav-collapse -->
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span3" id="sidebar">
            <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                <li class="active">
                    <a href="index.html"><i class="icon-chevron-right"></i> 素材选择</a>
                </li>

            </ul>
        </div>

        <!--/span-->
        <div class="span9" id="content">

            <div class="row-fluid">
                <div class="row-fluid">
                    <!-- block -->
                    <div class="block" style="margin-top: 200px;">
                        <div class="navbar navbar-inner block-header">
                            <div class="muted pull-left">选择素材</div>
                            <div class="pull-right"><span class="badge badge-info">4套</span>

                            </div>
                        </div>
                        <div class="block-content collapse in">
                            <div class="row-fluid padd-bottom">
                                <div class="span3">
                                    <a href="#" class="thumbnail">
                                        <img data-src="holder.js" alt="简报样式1" style="width: 260px; height: 180px;" src="images/summarizeStyle.jpg">
                                    </a>
                                </div>
                                <div class="span3">
                                    <a href="#" class="thumbnail">
                                        <img data-src="holder.js" alt="简报样式1" style="width: 260px; height: 180px;" src="images/summarizeStyle.jpg">
                                    </a>
                                </div>
                                <div class="span3">
                                    <a href="#" class="thumbnail">
                                        <img data-src="holder.js" alt="简报样式1" style="width: 260px; height: 180px;" src="images/summarizeStyle.jpg">
                                    </a>
                                </div>
                                <div class="span3">
                                    <a href="#" class="thumbnail">
                                        <img data-src="holder.js" alt="简报样式1" style="width: 260px; height: 180px;" src="images/summarizeStyle.jpg">
                                    </a>
                                </div>
                            </div>

                        </div>
                    </div>
                    <button class="btn btn-warning btn-large" style="margin-left:40%; margin-top: 20px; padding:10px;">选定样式&nbsp>></button>

                    <div class="sx_updown clearfix">
                        <div class="content">
                            <div class="updown_box w136">
                                <h3>筛选：</h3>
                            </div>
                            <div class="updown_box w290">
                                <h3>时间</h3>
                                <ul id="select1">
                                    <li><a href="javascript:;">今天</a></li>
                                    <li><a href="javascript:;">近一周</a></li>
                                    <li><a href="javascript:;">近一个月</a></li>
                                    <li><a href="javascript:;">近一年</a></li>
                                </ul>
                            </div>
                            <!--<div class="updown_box w290">
                                <h3>信息属性</h3>
                                <ul id="select2">
                                    <li><a href="javascript:;">全部</a></li>
                                    <li><a href="javascript:;">敏感</a></li>
                                    <li><a href="javascript:;">非敏感</a></li>
                                </ul>
                            </div> -->

                            <div class="updown_box w290">
                                <h3>信息属性</h3>
                                <ul id="select3">
                                    <li><a href="javascript:;">全部</a></li>
                                    <li><a href="javascript:;">敏感</a></li>
                                    <li><a href="javascript:;">非敏感</a></li>
                                </ul>
                            </div>
                            <div class="updown_box w290">
                                <h3>是否收藏过</h3>
                                <ul id="select4">
                                    <li><a href="javascript:;">全部</a></li>
                                    <li><a href="javascript:;">是</a></li>
                                    <li><a href="javascript:;">否</a></li>
                                </ul>
                            </div>
                            <div class="updown_box w290">
                                <h3>是否点赞过</h3>
                                <ul>
                                    <li><a href="javascript:;">全部</a></li>
                                    <li><a href="javascript:;">是</a></li>
                                    <li><a href="javascript:;">否</a></li>
                                </ul>
                            </div>
                            <div class="updown_box w290">
                                <h3>主题</h3>
                                <ul >
                                    <li><a href="javascript:;">全部</a></li>
                                    <li><a href="javascript:;">科技</a></li>
                                    <li><a href="javascript:;">社会</a></li>
                                    <li><a href="javascript:;">娱乐</a></li>
                                </ul>
                            </div>
                            <div class="updown_box w290">
                                <button class="btn btn-warning btn-large" style="margin-top:200px;">检索</button>
                            </div>
                        </div>


                        <div class="row-fluid" style="width:72%;">
                            <!-- block -->
                            <div class="block">
                                <div class="navbar navbar-inner block-header">
                                    <div class="muted pull-left">事件结果集</div>
                                </div>
                                <div class="block-content collapse in">
                                    <div class="span12">

                                        <div id="example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                            <div class="row"><div class="span6">
                                                <div id="example_length" class="dataTables_length"><label>
                                                    <select size="1" name="example_length" aria-controls="example">
                                                        <option value="10" selected="selected">10</option><option value="25">25</option>
                                                        <option value="50">50</option><option value="100">100</option></select>
                                                    records per page</label></div></div>
                                                <div class="span6"><div class="dataTables_filter" id="example_filter"><label>
                                                    Search: <input type="text" aria-controls="example"></label></div></div></div>
                                            <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered dataTable" id="example" aria-describedby="example_info">
                                                <thead>
                                                <tr role="row"><th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Rendering engine: activate to sort column descending" style="width: 206px;">
                                                    序号</th><th class="sorting" role="columnheader" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Browser: activate to sort column ascending" style="width: 300px;">
                                                    事件名</th><th class="sorting" role="columnheader" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Platform(s): activate to sort column ascending" style="width: 278px;">
                                                    事件热度</th><th class="sorting" role="columnheader" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Engine version: activate to sort column ascending" style="width: 177px;">
                                                    是否接触过（收藏/点赞/评论）</th><th class="sorting" role="columnheader" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="CSS grade: activate to sort column ascending" style="width: 129px;">
                                                    操作</th></tr>
                                                </thead>

                                                <tbody role="alert" aria-live="polite" aria-relevant="all">
                                                <tr class="gradeA odd">
                                                    <td class="  sorting_1">1</td>
                                                    <td class=" ">十九大召开</td>
                                                    <td class=" ">100</td>
                                                    <td class="center ">是</td>
                                                    <td class=" "><input class="uniform_on" type="checkbox" id="optionsCheckbox" value="option1"></td>
                                                </tr>
                                                <tr class="gradeA odd">
                                                    <td class="  sorting_1">1</td>
                                                    <td class=" ">十九大召开</td>
                                                    <td class=" ">100</td>
                                                    <td class="center ">是</td>
                                                    <td class=" "><input class="uniform_on" type="checkbox" value="option1"></td>
                                                </tr><tr class="gradeA odd">
                                                    <td class="  sorting_1">1</td>
                                                    <td class=" ">十九大召开</td>
                                                    <td class=" ">100</td>
                                                    <td class="center ">是</td>
                                                    <td class=" "><input class="uniform_on" type="checkbox" value="option1"></td>
                                                </tr>

                                                </tbody></table>
                                            <div class="row"><div class="span6"><div class="dataTables_info" id="example_info">Showing 1 to 10 of 57 entries</div></div><div class="span6"><div class="dataTables_paginate paging_bootstrap pagination"><ul><li class="prev disabled"><a href="#">← Previous</a></li><li class="active"><a href="#">1</a></li><li><a href="#">2</a></li><li><a href="#">3</a></li><li><a href="#">4</a></li><li><a href="#">5</a></li><li class="next"><a href="#">Next → </a></li></ul></div></div></div></div>
                                        <button class="btn btn-warning btn-large" style="margin-left:30%"
                                                onclick="javascript:window.location.href='makeReporter_4.html'">素材选择 >></button>

                                    </div>
                                </div>
                            </div>
                            <!-- /block -->
                        </div>


                    </div>
                    <!--<div class="sousuo clearfix">
                    <div class="select-result clearfix">
                        <ul>
                        </ul>
                    </div>
                    </div>
                    -->
                    <script type="text/javascript">
                        $("#select1 li").click(function () {
                            $(this).addClass("selected").siblings().removeClass("selected");
                            var copyThisA = $(this).clone();
//console.log(copyThisA);
                            if ($("#selectA").length > 0) {
                                $("#selectA a").html($(this).text());
                            } else {
                                $(".select-result ul").append(copyThisA.attr("id", "selectA"));
                            }
                        });
                        $("#select2 li").click(function() {
                            var type = $(this).attr("date-type");
                            var copyThisB = $(this).clone();
                            if ($(this).hasClass("selected")) {
                                $(".select-result li[date-type='" + type + "']").fadeToggle();
                            } else {
                                $(".select-result ul").append(copyThisB);
                            };
                            $(this).toggleClass("selected");
                        });
                        $("#select3 li").click(function () {
                            $(this).addClass("selected").siblings().removeClass("selected");
                            var copyThisC = $(this).clone();
                            if ($("#selectC").length > 0) {
                                $("#selectC a").html($(this).text());
                            } else {
                                $(".select-result ul").append(copyThisC.attr("id", "selectC"));
                            }
                        });
                        $("#select4 li").click(function () {
                            $(this).addClass("selected").siblings().removeClass("selected");
                            var copyThisC = $(this).clone();
                            if ($("#selectC").length > 0) {
                                $("#selectC a").html($(this).text());
                            } else {
                                $(".select-result ul").append(copyThisC.attr("id", "selectC"));
                            }
                        });
                        $("#select5 li").click(function () {
                            $(this).addClass("selected").siblings().removeClass("selected");
                            var copyThisC = $(this).clone();
                            if ($("#selectC").length > 0) {
                                $("#selectC a").html($(this).text());
                            } else {
                                $(".select-result ul").append(copyThisC.attr("id", "selectC"));
                            }
                        });
                        $("#selectA").live("click", function () {
                            $(this).remove();
                            $("#select1 li").removeClass("selected");
                        });
                        $(".select-result ul").delegate("li","click", function(){
                            var type = $(this).attr("date-type");
                            $(this).fadeOut();
                            $("#select2 li[date-type='" + type + "']").removeClass("selected");
                        });
                        $("#selectC").live("click", function () {
                            $(this).remove();
                            $("#select3 li").removeClass("selected");
                        });
                    </script>
                </div>

                <!-- /block -->
            </div>
        </div>
    </div>
    <hr>
    <footer>

    </footer>
</div>
<!--/.fluid-container-->
<script src="vendors/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="vendors/easypiechart/jquery.easy-pie-chart.js"></script>
<script src="assets/scripts.js"></script>
<script>
    $(function() {
        // Easy pie charts
        $('.chart').easyPieChart({animate: 1000});
    });
</script>
</body>

</html>
