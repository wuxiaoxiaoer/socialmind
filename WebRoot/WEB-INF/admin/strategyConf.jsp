<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

                        <table border="0" width="400">
                            <tr>
                                <td align="center" width="200">未选择策略</td>
                                <td> </td>
                                <td align="center" width="200">已选择策略</td>
                            </tr>
                            <tr>
                                <td width="40%">
                                    <select  multiple name="left" id="left" size="8"
                                             ondblclick="moveOption(document.getElementById('left'), document.getElementById('right'),'0')">
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
                                    <input type="button" value=" >> " onclick="moveOption(document.getElementById('left'),document.getElementById('right'),'0')"><br><br>
                                    <input type="button" value=" << " onclick="moveOption(document.getElementById('right'), document.getElementById('left'),'1')">
                                </td>
                                <td width="40%">
                                    <select  multiple name="right" id="right" size="8"
                                             ondblclick="moveOption(document.getElementById('right'), document.getElementById('left'),'1')">
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


