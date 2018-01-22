<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <base href="<%=basePath%>admin/"/>
<title>信息可信度标注系统</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/uniform.css" />
<link rel="stylesheet" href="css/select2.css" />
<link rel="stylesheet" href="css/maruti-style.css" />
<link rel="stylesheet" href="css/maruti-media.css" class="skin-color" />
<script src="js/jquery.min.js"></script>
  <style>

    /*input[type="radio"] {
      width: 20px;
      height: 20px;
      opacity: 0;
    }

    label {
      position: absolute;
      left: 5px;
      top: 3px;
      width: 20px;
      height: 20px;
      border-radius: 50%;
      border: 1px solid #999;
    }

    !*设置选中的input的样式*!
    !* + 是兄弟选择器,获取选中后的label元素*!
    input:checked+label {
      background-color: #fe6d32;
      border: 1px solid #fe6d32;
    }

    input:checked+label::after {
      position: absolute;
      content: "";
      width: 5px;
      height: 10px;
      top: 3px;
      left: 6px;
      border: 2px solid #fff;
      border-top: none;
      border-left: none;
      transform: rotate(45deg)
    }*/

  </style>
</head>
<body>
  <!-- 引入后台头模板-->
  <jsp:include page="/static/admin_header.jsp"></jsp:include>

<div id="content">
  <div id="content-header">
    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">Tables</a> </div>
    <h1>信息可信度标注 - ${object.name}</h1>
  </div>
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="widget-box">
          <div class="widget-title">
             <span class="icon"><i class="icon-th"></i></span> 
            <h5>Data table</h5>
          </div>
          <div class="widget-content nopadding">
            <table class="table table-bordered data-table">
              <thead>
                <tr>
                  <th>序号</th>
                  <th>标题</th>
                  <th>内容</th>
                  <th>真/假</th>
                  <th>可信类型</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${articles}" var="a" varStatus="sts">
                  <tr class="gradeX">
                    <td>${sts.index + 1}</td>
                    <td>${a.title}</td>
                    <td>${a.content}</td>
                    <c:set var="flagIsTrue" value="0"></c:set>
                    <c:forEach items="${articleLabeleds}" var="al">
                      <c:if test="${a.articleId == al.articleId}">
                          <c:set var="flagIsTrue" value="1"></c:set>
                          <td width="10%">
                            <div>
                              <label for="true1"></label>
                              <span>真</span>
                              <c:if test="${al.isTrue == 'true'}">
                                <input id="true1" type="radio" name="isTrue${sts.index}" value="true" checked onclick="clickChoose(this, '${a.articleId}', '${sts.index}');">
                              </c:if>
                              <c:if test="${al.isTrue != 'true'}">
                                <input id="true1" type="radio" name="isTrue${sts.index}" value="true" onclick="clickChoose(this, '${a.articleId}', '${sts.index}');">
                              </c:if>
                            </div>
                            <div>
                              <label for="false1"></label>
                              <span>假</span>
                              <c:if test="${al.isTrue == 'false'}">
                                <input id="false1" type="radio" name="isTrue${sts.index}" checked value="false" onclick="clickChoose(this, '${a.articleId}', '${sts.index}');">
                              </c:if>
                              <c:if test="${al.isTrue != 'false'}">
                                <input id="false1" type="radio" name="isTrue${sts.index}" value="false" onclick="clickChoose(this, '${a.articleId}', '${sts.index}');">
                              </c:if>
                        </div>
                          </td>

                        <!-- 判断可信类型 -->
                        <td width="10%">
                          <div>
                            <label for="credType1"></label>
                            <span>网络偏激</span>
                            <c:if test="${al.credType == 'extreme'}">
                              <input id="credType1" type="radio" name="credType${sts.index}" value="extreme" checked onclick="clickChoose(this, '${a.articleId}', '${sts.index}');">
                            </c:if>
                            <c:if test="${al.credType != 'extreme'}">
                              <input id="credType1" type="radio" name="credType${sts.index}" value="extreme" onclick="clickChoose(this, '${a.articleId}', '${sts.index}');">
                            </c:if>
                          </div>
                          <div>
                            <label for="credType2"></label>
                            <span>谣言信息</span>
                            <c:if test="${al.credType == 'rumour'}">
                              <input id="credType2" type="radio" name="credType${sts.index}" value="rumour" checked onclick="clickChoose(this, '${a.articleId}', '${sts.index}');">
                            </c:if>
                            <c:if test="${al.credType != 'rumour'}">
                              <input id="credType2" type="radio" name="credType${sts.index}" value="rumour" onclick="clickChoose(this, '${a.articleId}', '${sts.index}');">
                            </c:if>
                          </div>
                          <div>
                            <label for="credType3"></label>
                            <span>虚假信息</span>
                            <c:if test="${al.credType == 'false'}">
                              <input id="credType3" type="radio" name="credType${sts.index}" value="false" checked onclick="clickChoose(this, '${a.articleId}', '${sts.index}');">
                            </c:if>
                            <c:if test="${al.credType != 'false'}">
                              <input id="credType3" type="radio" name="credType${sts.index}" value="false" onclick="clickChoose(this, '${a.articleId}', '${sts.index}');">
                            </c:if>
                          </div>
                          <div>
                            <label for="credType4"></label>
                            <span>误报信息</span>
                            <c:if test="${al.credType == 'Misinformation'}">
                              <input id="credType4" type="radio" name="credType${sts.index}" value="Misinformation" checked onclick="clickChoose(this, '${a.articleId}', '${sts.index}');">
                            </c:if>
                            <c:if test="${al.credType != 'Misinformation'}">
                              <input id="credType4" type="radio" name="credType${sts.index}" value="Misinformation" onclick="clickChoose(this, '${a.articleId}', '${sts.index}');">
                            </c:if>
                        </div>
                          <div>
                            <label for="credType5"></label>
                            <span>垃圾信息</span>
                            <c:if test="${al.credType == 'spam'}">
                              <input id="credType5" type="radio" name="credType${sts.index}" value="spam" checked onclick="clickChoose(this, '${a.articleId}', '${sts.index}');">
                            </c:if>
                            <c:if test="${al.credType != 'spam'}">
                              <input id="credType5" type="radio" name="credType${sts.index}" value="spam" onclick="clickChoose(this, '${a.articleId}', '${sts.index}');">
                            </c:if>
                          </div>
                        </td>
                      </c:if>
                    </c:forEach>

                    <c:if test="${flagIsTrue == 0}">
                      <td width="10%">
                        <div>
                          <label for="true1"></label>
                          <span>真</span>
                          <input id="true1" type="radio" name="isTrue${sts.index}" value="true" onclick="clickChoose(this, '${a.articleId}', '${sts.index}');">
                        </div>
                        <div>
                          <label for="false1"></label>
                          <span>假</span>
                          <input id="false1" type="radio" name="isTrue${sts.index}" value="false" onclick="clickChoose(this, '${a.articleId}', '${sts.index}');">
                        </div>
                      </td>
                      <td width="10%">
                        <div>
                          <label for="credType1"></label>
                          <span>网络偏激</span>
                          <input id="credType1" type="radio" name="credType${sts.index}" value="extreme" onclick="clickChoose(this, '${a.articleId}', '${sts.index}');">
                        </div>
                        <div>
                          <label for="credType2"></label>
                          <span>谣言信息</span>
                          <input id="credType2" type="radio" name="credType${sts.index}" value="rumour" onclick="clickChoose(this, '${a.articleId}', '${sts.index}');">
                        </div>
                        <div>
                          <label for="credType3"></label>
                          <span>虚假信息</span>
                          <input id="credType3" type="radio" name="credType${sts.index}" value="false" onclick="clickChoose(this, '${a.articleId}', '${sts.index}');">
                        </div>
                        <div>
                          <label for="credType4"></label>
                          <span>误报信息</span>
                          <input id="credType4" type="radio" name="credType${sts.index}" value="Misinformation" onclick="clickChoose(this, '${a.articleId}', '${sts.index}');">
                        </div>
                        <div>
                          <label for="credType5"></label>
                          <span>垃圾信息</span>
                          <input id="credType5" type="radio" name="credType${sts.index}" value="spam" onclick="clickChoose(this, '${a.articleId}', '${sts.index}');">
                        </div>
                      </td>
                    </c:if>

                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </div>

      </div>
    </div>
  </div>
</div>
  <script>
    function clickChoose(obj, articleId, index) {
        var name = $(obj).attr("name");
        var value = $(obj).val();
        $.ajax({
            type : "post",
            url : "<%=basePath%>label/labeled?articleId="+articleId+"&name="+name+"&value="+value,
            success : function (data) {
                //ok
            }
        })
    }
      /*$(function(){
          $(":radio").click(function(){
              var articleId = $(this).attr("articleId");
              var name = $(this).attr("name");
              var value = $(this).val();
              alert("您是..." + $(this).val() + " : " + $(this).attr("name"));
              $.ajax({
                  type : "post",
                  url : "<%=basePath%>label/labeled?articleId="+articleId+"&name="+name+"&value="+value,
                  success : function (data) {
                      alert(data);
                  }
              })
          });
      });*/
  </script>
<div class="row-fluid">
  <div id="footer" class="span12"> 2012 &copy; Marutii Admin. More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a> </div>
</div>
<script src="js/jquery.min.js"></script> 
<script src="js/jquery.ui.custom.js"></script> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/jquery.uniform.js"></script> 
<script src="js/select2.min.js"></script> 
<script src="js/jquery.dataTables.min.js"></script> 
<script src="js/maruti.js"></script> 
<script src="js/maruti.tables.js"></script>
</body>
</html>
