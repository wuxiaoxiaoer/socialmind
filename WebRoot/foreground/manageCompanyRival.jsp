<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/10/31
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>公司竞争对手管理</title>
    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <link href="assets/styles.css" rel="stylesheet" media="screen">
    <link href="assets/DT_bootstrap.css" rel="stylesheet" media="screen">
    <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="vendors/flot/excanvas.min.js"></script><![endif]-->
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script src="vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
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
                <li>
                    <a href="index.html"><i class="icon-chevron-right"></i> 企业产品</a>
                </li>
                <li>
                    <a href="calendar.html"><i class="icon-chevron-right"></i> 新增产品</a>
                </li>
                <li>
                    <a href="stats.html"><i class="icon-chevron-right"></i> 产品关键词</a>
                </li>

            </ul>
        </div>
        <!--/span-->
        <div class="span9" id="content">


            <div class="row-fluid">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left">竞争对手管理</div>
                    </div>
                    <div class="block-content collapse in">
                        <div class="span12">
                            <div class="table-toolbar">
                                <div class="btn-group">
                                    <a href="#"><button class="btn btn-success">新增 <i class="icon-plus icon-white"></i></button></a>
                                </div>
                                <div class="btn-group pull-right">
                                    <button data-toggle="dropdown" class="btn dropdown-toggle">Tools <span class="caret"></span></button>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Print</a></li>
                                        <li><a href="#">Save as PDF</a></li>
                                        <li><a href="#">Export to Excel</a></li>
                                    </ul>
                                </div>
                            </div>

                            <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example2">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>同行名称</th>
                                    <th>企业简介</th>
                                    <th>企业关键词</th>
                                    <th>企业主页</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr class="odd gradeX">
                                    <td>1</td>
                                    <td>企业1</td>
                                    <td>这是一个……的企业</td>
                                    <td>游戏 在线</td>
                                    <td class="center">http://www.sjtu.edu.cn</td>
                                    <td class="center">
                                        <div class="pull-right"><span class="badge badge-info" style="background-color: #f89406;">删除</span></div>
                                        <div class="pull-right"><span class="badge badge-info" style="background-color: #1b561b;">编辑</span></div>
                                        <div class="pull-right"><span class="badge badge-info" style="background-color: green;">显示</span></div>
                                    </td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td>1</td>
                                    <td>企业1</td>
                                    <td>这是一个……的企业</td>
                                    <td>游戏 在线</td>
                                    <td class="center">http://www.sjtu.edu.cn</td>
                                    <td class="center">
                                        <div class="pull-right"><span class="badge badge-info" style="background-color: #f89406;">删除</span></div>
                                        <div class="pull-right"><span class="badge badge-info" style="background-color: #1b561b;">编辑</span></div>
                                        <div class="pull-right"><span class="badge badge-info" style="background-color: green;">显示</span></div>
                                    </td>
                                </tr>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- /block -->

                <div class="row-fluid">
                    <!-- block -->
                    <div class="block">
                        <div class="navbar navbar-inner block-header">
                            <div class="muted pull-left">新增企业</div>
                        </div>
                        <div class="block-content collapse in">
                            <div class="span12">
                                <form class="form-horizontal">
                                    <fieldset>
                                        <legend>新增企业</legend>
                                        <div class="control-group">
                                            <label class="control-label" for="focusedInput">企业名称</label>
                                            <div class="controls">
                                                <input class="input-xlarge focused" type="text" value="产品名称...">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label">企业简介</label>
                                            <div class="controls">
                                                <textarea rows="8" style="width: 270px;">企业简介</textarea>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="focusedInput">企业关键词</label>
                                            <div class="controls">
                                                <input class="input-xlarge focused" type="text" value="以逗号分割...">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="focusedInput">企业主页</label>
                                            <div class="controls">
                                                <input class="input-xlarge focused" type="text" value="http://...">
                                            </div>
                                        </div>
                                        <div class="form-actions">
                                            <button type="submit" class="btn btn-primary">下一步>></button>
                                            <button type="reset" class="btn">取消</button>
                                        </div>
                                    </fieldset>
                                </form>

                            </div>
                        </div>
                    </div>

                    <div class="block">
                        <div class="navbar navbar-inner block-header">
                            <div class="muted pull-left">企业关键词</div>
                        </div>
                        <div class="block-content collapse in">
                            <div class="span12">
                                <form class="form-horizontal">
                                    <fieldset>
                                        <legend>企业关键词</legend>
                                        <div class="control-group">
                                            <label class="control-label" for="focusedInput">企业关键词</label>
                                            <div class="controls">
                                                <input class="input-xlarge focused" id="focusedInput" type="text" value="关键词1, 关键词2...为了实体消歧">
                                            </div>
                                        </div>

                                    </fieldset>
                                    <div class="form-actions">
                                        <button type="submit" class="btn btn-primary">确认+</button>
                                        <button type="reset" class="btn">取消</button>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                    <!-- /block -->
                </div>

            </div>
        </div>
    </div>
    <hr>
    <footer>
        <p>&copy; Vincent Gabriel 2013</p>
    </footer>
</div>
<!--/.fluid-container-->

<script src="vendors/jquery-1.9.1.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="vendors/datatables/js/jquery.dataTables.min.js"></script>


<script src="assets/scripts.js"></script>
<script src="assets/DT_bootstrap.js"></script>
<script>
    $(function() {

    });
</script>
</body>

</html>
