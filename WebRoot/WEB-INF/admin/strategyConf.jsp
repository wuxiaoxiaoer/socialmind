<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="row-fluid">
    <div class="span12">
        <div class="widget-box">
            <div class="widget-title">
								<span class="icon">
									<i class="icon-pencil"></i>
								</span>
                <h5>策略管理</h5>
            </div>
            <div class="widget-content nopadding">
                <div id="form-wizard-1" class="step">
                    选择策略分类
                    <div class="btn-group">
                        <button type="button" id="strategyClassify" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">选择策略分类
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a onclick="strategyConf('1')">缺失值</a></li>
                            <li><a onclick="strategyConf('2')">字符串错误</a></li>
                            <li><a onclick="strategyConf('3')">行操作</a></li>
                            <li><a onclick="strategyConf('4')">时间格式</a></li>
                            <li><a onclick="strategyConf('5')">日期格式</a></li>
                        </ul>
                    </div>
                    <label class="control-label">管理策略</label>
                    <span id='feedback'></span>
                    <form method="post" name="myform">
                        <table border="0" width="400">
                            <tr>
                                <td align="center" width="200">可选择排序方式</td>
                                <td> </td>
                                <td align="center" width="200">已选择排序方式</td>
                            </tr>
                            <tr>
                                <td width="40%">
                                    <select  multiple name="left" id="left" size="8"
                                             ondblclick="moveOption(document.getElementById('left'), document.getElementById('right'))">
                                        <%--<option value="20">ASP</option>--%>
                                        <%--<option value="30">PHP</option>--%>
                                        <%--<option value="40">ASP.NET</option>--%>
                                        <%--<option value="50">JSP</option>--%>
                                        <%--<option value="60">VB</option>--%>
                                        <%--<option value="70">DELPHI</option>--%>
                                        <%--<option value="80">AJAX</option>--%>
                                        <%--<option value="90">JQUERY</option>--%>
                                        <c:forEach  items="${leftList}" var="v">
                                            <option value="${v.cleanStategyId}">${v.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td width="20%" align="center">
                                    <input type="button" value=" >> " onclick="moveOption(document.getElementById('left'),document.getElementById('right'))"><br><br>
                                    <input type="button" value=" << " onclick="moveOption(document.getElementById('right'), document.getElementById('left'))">
                                </td>
                                <td width="40%">
                                    <select  multiple name="right" id="right" size="8"
                                             ondblclick="moveOption(document.getElementById('right'), document.getElementById('left'))">
                                        <c:forEach  items="${rightList}" var="v">
                                            <option value="${v.cleanStategyId}">${v.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" align="center">
                                    <INPUT TYPE="button" value="置顶" onclick="moveTop(document.getElementById('right'));">
                                    <INPUT TYPE="button" value="上移" onclick="moveUp(document.getElementById('right'));">
                                    <INPUT TYPE="button" value="下移" onclick="moveDown(document.getElementById('right'));">
                                    <INPUT TYPE="button" value="置底" onclick="moveBottom(document.getElementById('right'));">
                            </tr>
                        </table>

                    </form>
                </div>

                <div class="form-actions">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button id="goBack" class="btn btn-primary" type="text" >撤销</button>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button id="submit1" class="btn btn-primary" type="text" >提交</button>
                    <div id="status"></div>
                </div>
                <div id="submitted"></div>
            </div>
        </div>
    </div>
</div>
