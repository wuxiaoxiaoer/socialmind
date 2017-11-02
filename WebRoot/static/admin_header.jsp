<%--
  Created by IntelliJ IDEA.
  User: DeMH
  Date: 2017/11/2
  Time: 9:15
  系统后台 - 头模板.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!--top-Header-messaages-->
<div class="btn-group rightzero"> <a class="top_message tip-left" title="Manage Files"><i class="icon-file"></i></a> <a class="top_message tip-bottom" title="Manage Users"><i class="icon-user"></i></a> <a class="top_message tip-bottom" title="Manage Comments"><i class="icon-comment"></i><span class="label label-important">5</span></a> <a class="top_message tip-bottom" title="Manage Orders"><i class="icon-shopping-cart"></i></a> </div>
<!--close-top-Header-messaages-->

<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse">
    <ul class="nav">
        <li class="" ><a title="" href="#"><i class="icon icon-user"></i> <span class="text">个人中心</span></a></li>
        <li class=" dropdown" id="menu-messages"><a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle"><i class="icon icon-envelope"></i> <span class="text">消息</span> <span class="label label-important">5</span> <b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li><a class="sAdd" title="" href="#">新消息</a></li>
            </ul>
        </li>
        <li class=""><a title="" href="login.html"><i class="icon icon-share-alt"></i> <span class="text">注销</span></a></li>
    </ul>
</div>
<div id="search">
    <input type="text" placeholder="Search here..."/>
    <button type="submit" class="tip-left" title="Search"><i class="icon-search icon-white"></i></button>
</div>
<!--close-top-Header-menu-->

<div id="sidebar"><a href="#" class="visible-phone"><i class="icon icon-home"></i></a>
    <ul>
        <li class="active"><a href="index.jsp"><i class="icon icon-home"></i> <span>首页</span></a></li>
        <li class="active"><a href="index.jsp"><i class="icon icon-home"></i> <span>爬虫</span><span class="label">3</span></a>
            <ul>
                <li><a href="spiderMonitor.html">爬虫监控</a></li>
                <li><a href="spiderList.html">爬虫管理</a></li>
                <li><a href="configList.html">爬虫配置</a></li>
            </ul>
        </li>
        <li> <a href="charts.html"><i class="icon icon-signal"></i> <span>数据清洗</span><span class="label">4</span></a>
            <ul>
                <li><a href="data_stats.html">清洗数据统计</a></li>
                <li><a href="dataClean_conf.html">数据清洗策略配置</a></li>
                <li><a href="#.html">数据清洗</a></li>
                <li><a href="dataClean_diary.html">数据清洗日志管理</a></li>
            </ul>
        </li>
        <li> <a href="widgets.html"><i class="icon icon-inbox"></i> <span>舆情对象管理</span><span class="label">1</span></a>
            <ul>
                <li><a href="eventManager.html">事件管理</a></li>
                <li><a href="form-common.html">事件统计</a></li>
                <!--<li><a href="eventManager.html">实体管理</a></li>
                <li><a href="form-common.html">实体统计</a></li>-->
            </ul>
        </li>
        <li> <a href="widgets.html"><i class="icon icon-inbox"></i> <span>主题管理</span><span class="label">1</span></a>
            <ul>
                <li><a href="topics.html">主题管理</a></li>
            </ul>
        </li>
        <li> <a href="widgets.html"><i class="icon icon-inbox"></i> <span>语义工具集</span><span class="label">1</span></a>
            <ul>
                <li><a href="semanticsToolkits.html">语义工具集管理</a></li>
            </ul>
        </li>
        <li><a href="tables.html"><i class="icon icon-th"></i> <span>用户留言管理</span></a><span class="label">1</span>
            <ul>
                <li><a href="userComments.html">用户留言管理</a></li>
            </ul>
        </li>
        <li><a href="grid.html"><i class="icon icon-fullscreen"></i> <span>用户管理</span></a><span class="label">1</span>
            <ul>
                <li><a href="usersManager.html">用户管理</a></li>
            </ul>
        </li>
        <li class="submenu"> <a href="roleManagement.html"><i class="icon icon-th-list"></i> <span>角色管理</span> <span class="label">1</span></a>
            <ul>
                <li><a href="roleManagement.html">角色管理</a></li>
            </ul>
        </li>
        <li><a href="interface.html"><i class="icon icon-pencil"></i> <span>权限管理</span></a>
            <ul>
                <li><a href="permission.html">权限管理</a></li>
            </ul></li>
        <li><a href="buttons.html"><i class="icon icon-tint"></i> <span>日志管理</span></a>
            <ul>
                <li><a href="operateDiary.html">操作日志</a></li>
            </ul></li>

        <li class="submenu"> <a href="#"><i class="icon icon-file"></i> <span>个人中心</span> <span class="label">1</span></a>
            <ul>
                <li><a href="my.html">个人信息管理</a></li>
            </ul>
        </li>

    </ul>
</div>
