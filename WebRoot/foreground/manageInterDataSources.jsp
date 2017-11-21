<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/10/31
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>数据源</title>
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
            <a class="brand" href="#">Admin Panel</a>
            <div class="nav-collapse collapse">
                <ul class="nav pull-right">
                    <li class="dropdown">
                        <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user"></i> Vincent Gabriel <i class="caret"></i>

                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a tabindex="-1" href="#">Profile</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a tabindex="-1" href="login.html">Logout</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <ul class="nav">
                    <li class="active">
                        <a href="#">Dashboard</a>
                    </li>
                    <li class="dropdown">
                        <a href="#" data-toggle="dropdown" class="dropdown-toggle">Settings <b class="caret"></b>

                        </a>
                        <ul class="dropdown-menu" id="menu1">
                            <li>
                                <a href="#">Tools <i class="icon-arrow-right"></i>

                                </a>
                                <ul class="dropdown-menu sub-menu">
                                    <li>
                                        <a href="#">Reports</a>
                                    </li>
                                    <li>
                                        <a href="#">Logs</a>
                                    </li>
                                    <li>
                                        <a href="#">Errors</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="#">SEO Settings</a>
                            </li>
                            <li>
                                <a href="#">Other Link</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">Other Link</a>
                            </li>
                            <li>
                                <a href="#">Other Link</a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">Content <i class="caret"></i>

                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a tabindex="-1" href="#">Blog</a>
                            </li>
                            <li>
                                <a tabindex="-1" href="#">News</a>
                            </li>
                            <li>
                                <a tabindex="-1" href="#">Custom Pages</a>
                            </li>
                            <li>
                                <a tabindex="-1" href="#">Calendar</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a tabindex="-1" href="#">FAQ</a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">Users <i class="caret"></i>

                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a tabindex="-1" href="#">User List</a>
                            </li>
                            <li>
                                <a tabindex="-1" href="#">Search</a>
                            </li>
                            <li>
                                <a tabindex="-1" href="#">Permissions</a>
                            </li>
                        </ul>
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
                    <a href="index.html"><i class="icon-chevron-right"></i> Dashboard</a>
                </li>
                <li>
                    <a href="calendar.html"><i class="icon-chevron-right"></i> Calendar</a>
                </li>
                <li>
                    <a href="stats.html"><i class="icon-chevron-right"></i> Statistics (Charts)</a>
                </li>
                <li>
                    <a href="form.html"><i class="icon-chevron-right"></i> Forms</a>
                </li>
                <li class="active">
                    <a href="tables.html"><i class="icon-chevron-right"></i> Tables</a>
                </li>
                <li>
                    <a href="buttons.html"><i class="icon-chevron-right"></i> Buttons & Icons</a>
                </li>
                <li>
                    <a href="interface.html"><i class="icon-chevron-right"></i> UI & Interface</a>
                </li>
                <li>
                    <a href="#"><span class="badge badge-success pull-right">731</span> Orders</a>
                </li>
                <li>
                    <a href="#"><span class="badge badge-success pull-right">812</span> Invoices</a>
                </li>
                <li>
                    <a href="#"><span class="badge badge-info pull-right">27</span> Clients</a>
                </li>
                <li>
                    <a href="#"><span class="badge badge-info pull-right">1,234</span> Users</a>
                </li>
                <li>
                    <a href="#"><span class="badge badge-info pull-right">2,221</span> Messages</a>
                </li>
                <li>
                    <a href="#"><span class="badge badge-info pull-right">11</span> Reports</a>
                </li>
                <li>
                    <a href="#"><span class="badge badge-important pull-right">83</span> Errors</a>
                </li>
                <li>
                    <a href="#"><span class="badge badge-warning pull-right">4,231</span> Logs</a>
                </li>
            </ul>
        </div>
        <!--/span-->
        <div class="span9" id="content">


            <div class="row-fluid">
                <!-- block -->
                <div class="block">
                    <div class="navbar navbar-inner block-header">
                        <div class="muted pull-left">内部数据源管理</div>
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
                                    <th>网站名称</th>
                                    <th>网站简介</th>
                                    <th>网站关键词</th>
                                    <th>网站主页</th>
                                    <th>运行情况</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr class="odd gradeX">
                                    <td>1</td>
                                    <td>兵马俑BBS</td>
                                    <td>交大内部网站</td>
                                    <td>西交大，西安，陕西，校园</td>
                                    <td class="center">http://www.bmy.com</td>
                                    <td><div class="pull-right"><span class="badge badge-info" style="background-color: green;">运行</span></div></td>
                                    <td class="center">
                                        <div class="pull-right"><span class="badge badge-info" style="background-color: #f89406;">删除</span></div>
                                        <div class="pull-right"><span class="badge badge-info" style="background-color: #1b561b;">编辑</span></div>

                                    </td>
                                </tr>
                                <tr class="odd gradeX">
                                    <td>1</td>
                                    <td>兵马俑BBS</td>
                                    <td>交大内部网站</td>
                                    <td>西交大，西安，陕西，校园</td>
                                    <td class="center">http://www.bmy.com</td>
                                    <td><div class="pull-right"><span class="badge badge-info" style="background-color: red;">未运行</span></div></td>
                                    <td class="center">
                                        <div class="pull-right"><span class="badge badge-info" style="background-color: #f89406;">删除</span></div>
                                        <div class="pull-right"><span class="badge badge-info" style="background-color: #1b561b;">编辑</span></div>

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
                            <div class="muted pull-left">新增网站</div>
                        </div>
                        <div class="block-content collapse in">
                            <div class="span12">
                                <form class="form-horizontal">
                                    <fieldset>
                                        <legend>新增网站</legend>
                                        <div class="control-group">
                                            <label class="control-label" for="focusedInput">网站名称</label>
                                            <div class="controls">
                                                <input class="input-xlarge focused" type="text" value="网站名...">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label">网站简介</label>
                                            <div class="controls">
                                                <textarea rows="8" style="width: 270px;">人物简介</textarea>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="focusedInput">网站关键词</label>
                                            <div class="controls">
                                                <input class="input-xlarge focused" type="text" value="以逗号分割...">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="focusedInput">网站主页</label>
                                            <div class="controls">
                                                <input class="input-xlarge focused" id="focusedInput" type="text" value="http://...">
                                            </div>
                                        </div>
                                        <div class="form-actions">
                                            <button type="submit" class="btn btn-primary">提交审核</button>
                                            <button type="reset" class="btn">取消</button>
                                        </div>

                                    </fieldset>
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