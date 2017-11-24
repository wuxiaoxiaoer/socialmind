<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Maruti Admin</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="admin/css/bootstrap.min.css" />
<link rel="stylesheet" href="admin/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="admin/css/uniform.css" />
<%--<link rel="stylesheet" href="admin/css/select2.css" />--%>
<link rel="stylesheet" href="admin/css/maruti-style.css" />
<link rel="stylesheet" href="admin/css/maruti-media.css" class="skin-color" />
</head>
<body>

<!-- 引入后台头模板-->
<jsp:include page="/static/admin_header.jsp"></jsp:include>
<!--close-top-Header-menu-->

<div id="content">
  <div id="content-header">
    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">爬虫列表</a> </div>
   <h1>爬虫列表</h1>
	    <div style="padding-left:30px">
	 <a class="btn green" href="toAddSpider">增加爬虫 <i class="icon-plus"></i></a>
      </div>         

  </div>
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="widget-box">
          <div class="widget-title">
             <span class="icon"><i class="icon-th"></i></span> 
            <h5>爬虫列表</h5>
          </div>
          <div class="widget-content nopadding">
            <table class="table table-bordered data-table">
              <thead>

                <tr>
                  <th>爬虫名称</th>
                  <th>添加时间</th>
                  <th>网站名</th>
                  <th>网站URL</th>
                  <th>文件路径</th>
				  <th>文件名称</th>
                  <th>配置类型</th>
                  <th>爬虫状态</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${spiderList}" var="v">
                <tr class="gradeX" >
                  <td>${v.spiderName}</td>
                  <td>${v.addTime}</td>
                  <td>${v.websiteId}</td>
                  <td>${v.websiteId}</td>
				  <td>${v.spiderSourcePath}</td>
				  <td>${v.fileName}</td>
                  <td>${v.spiderConfigId}</td>
                  <td>${v.spiderState}</td>
				<td class="td-status">
				  <c:if test="${v.spiderState eq '开启'}">
				   <a href="#" class="tip-top" data-original-title="暂停" onclick="updateState(this,'${v.spiderId}');"><i class="icon-pause"></i></a>
				  </c:if>
                    <c:if test="${v.spiderState eq '暂停'}">
                        <a  href="#" class="tip-top" data-original-title="开启" onclick="updateState(this,'${v.spiderId}');"  ><i class="icon-play"></i></a>
                    </c:if>
                    <a href="#" class="tip-top" data-original-title="修改" onclick="popModel('${v.spiderId}','${v.spiderName}','${v.spiderConfigId}')"><i class="icon-edit"></i></a>
			      <a href="#" class="tip-top" data-original-title="删除" onclick="deleteSpider('${v.spiderId}')"><i class="icon-remove" ></i></a>
		        </td>
                </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>  
      </div>
</div>
<br/><br/><br/>



    <div style="padding-left:30px">
        <a class="btn green" href="toAddConfiger">增加配置 <i class="icon-plus"></i></a>
    </div>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"> <i class="icon-th"></i> </span>
                        <h5>爬虫配置</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <table class="table table-bordered data-table">
                            <thead>
                            <tr>

                                <th style="display: none;"></th>
                                <th>配置名称</th>
                                <th>爬虫频率</th>
                                <th>爬虫运行时间</th>
                                <th>日志路径</th>
                                <th>配置时间</th>

                                <th>ROBOTS风格</th>
                                <th>下载量(Byte)</th>
                                <th>下载超时(S)</th>
                                <th>DNS超时(S)</th>
                                <th>最大深度</th>
                                <th>IP并发量</th>
                                <th>网站并发量</th>
                                <th>同时处理item数</th>
                                <th>收集最大深度</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach items="${configList}" var="v">
                                <tr>

                                    <td style="display: none;">${v.spiderConfigId}</td>
                                    <td>${v.configName}</td>
                                    <td>${v.spiderFrequency}</td>
                                    <td>${v.spiderRunTime}</td>
                                    <td>${v.logPath}</td>
                                    <td>${v.configTime}</td>

                                    <td>${configmap[v.spiderConfigId]["ROBOTS风格"]}</td>
                                    <td>${configmap[v.spiderConfigId]["下载量"]}</td>
                                    <td>${configmap[v.spiderConfigId]["下载超时"]}</td>
                                    <td>${configmap[v.spiderConfigId]["DNS超时"]}</td>
                                    <td>${configmap[v.spiderConfigId]["最大深度"]}</td>
                                    <td>${configmap[v.spiderConfigId]["IP并发量"]}</td>
                                    <td>${configmap[v.spiderConfigId]["网站并发量"]}</td>
                                    <td>${configmap[v.spiderConfigId]["同时处理item数"]}</td>
                                    <td>${configmap[v.spiderConfigId]["收集最大深度"]}</td>
                                    <td>
                                        <a href="#" class="tip-top" data-original-title="修改" onclick="pop('${v.spiderConfigId}')"><i class="icon-edit"></i></a>
                                        <a href="#" class="tip-top"  onclick="deleteConfig('${v.spiderConfigId}');"><i class="icon-remove" ></i></a>
                                    </td>

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

<!-- Modal -->
<div class="modal hide fade" id="myModal" tabindex="-1" role="dialog" style="overflow:auto">
    <form class="form-horizontal" method="post" action="editSpider" name="basic_validate"  novalidate="novalidate">
    <div class="modal-header"><button class="close" type="button" data-dismiss="modal">×</button>
        <h3 id="myModalLabel">修改爬虫</h3>
    </div>
    <div class="modal-body">

        <input type="text"  style="display: none;" id="id" name="id" ><br/>
        爬虫名称：<input type="text" id="spiderNewName"  name="spiderNewName"><br/>
        爬虫配置: <select id="configId" name="configId" placeholder="请选择配置" style="z-index:1000">
                <c:forEach items="${configList}" var="v">
                    <option style="z-index:1000" value="${v.spiderConfigId}">${v.configName}</option>
                </c:forEach>
            </select>
    </div>
    <div class="modal-footer">
        <button  class="btn btn-primary"  type="submit" >保存</button>
        <a href="#" class="btn">取消</a>
    </div>
    </form>
</div>




<!-- 修改配置Modal -->
<div class="modal hide fade" id="config" tabindex="-1" role="dialog" style="overflow:auto">
    <form class="form-horizontal" method="post" action="editConfig" name="basic_validate" id="basic_validate" novalidate="novalidate">
    <div class="modal-header"><button class="close" type="button" data-dismiss="modal">×</button>
        <h3 id="ModalLabel">修改配置</h3>
    </div>
    <div class="modal-body">

        <input type="text"  style="display: none;" name="sConfigId" id="sConfigId">
        <div class="control-group">
            <label class="control-label">配置名称</label>
            <div class="controls">
                <input type="text" name="configName" id="configName" >
            </div>
        </div>

        <c:forEach items="${configItemList}" var="v">
            <c:if test="${v.type eq 'int'}">
                <div class="control-group">
                    <label class="control-label">${v.displayName}</label>
                    <div class="controls">
                        <input type="text"  name="${v.configItemId}" id="${v.configItemId}">

                    </div>
                </div>
            </c:if>
            <c:if test="${v.type eq 'boolean'}">
                <div class="control-group">
                    <label class="control-label">${v.displayName}</label>
                    <div class="controls">
                        <select  name="${v.configItemId}" id="${v.configItemId}">
                            <option  value="True">是</option>
                            <option  value="False">否</option>
                        </select>
                    </div>
                </div>
            </c:if>

        </c:forEach>
    </div>
    <div class="modal-footer">
        <button  class="btn btn-primary"  type="submit">保存</button>
        <a href="#" class="btn">取消</a>
    </div>
    </form>
</div>




<script src="admin/js/jquery.min.js"></script>
<script src="admin/js/jquery.ui.custom.js"></script>
<script src="admin/js/bootstrap.min.js"></script>
<script src="admin/js/jquery.uniform.js"></script>
<%--<script src="admin/js/select2.min.js"></script>--%>
<script src="admin/js/jquery.dataTables.min.js"></script>
<script src="admin/js/maruti.js"></script>
<script src="admin/js/maruti.tables.js"></script>


<script>


    /*删除配置*/
    function deleteConfig(configId) {

        var result = false;
        if (confirm("确定删除吗?") == false) {
            return;
        }
        $.ajax({
            async : false,
            type : 'post',
            url : 'deleteConfig?spiderConfigId=' + configId,
            success : function(msg) {
                if (msg == 'success') {
                    window.location.reload();
                    result = true;
                } else {
                    alert("删除失败");
                    result = false;
                }
            }
        });
        return result;
    }











    /*弹出修改配置*/
    function pop(Id){
        var result = false;
        $.ajax({
            async : false,//将async设置为false,才能使得return 返回true/false有效
            type : 'post',
            url : 'searchSpiderConfig?spiderConfigId='+Id,
            success : function(data){
                var configName = eval('(' + data + ')').configName; //获取后台list
                var configId = eval('(' + data + ')').configId;
                var itemMap = eval('(' + data + ')').itemMap;

                document.getElementById("configName").value=configName;
                document.getElementById("sConfigId").value=configId;

//                $("#configName").val(spiderConfig.configName);
//                document.getElementById("sConfigId").value=Id;


                for(var k in itemMap){  //通过定义一个局部变量k遍历获取到了map中所有的key值
                    //获取到了key所对应的value的值！
                    document.getElementById(k).value=itemMap[k];
                }


                $('#config').modal('show');
            }
        });



    }





    /*更改爬虫状态*/
    function updateState(obj,spiderId){
        var result = false;
        $.ajax({
            async : false,//将async设置为false,才能使得return 返回true/false有效
            type : 'post',
            url : 'updateState?spiderId='+spiderId,
            success : function(data){

                location.reload(true);//重新加载
            }
        });
    }

    /*弹出修改爬虫*/
    function popModel(spiderId,spiderName,spiderConfigId){

        document.getElementById("id").value=spiderId;
        document.getElementById("spiderNewName").value=spiderName;
        document.getElementById("configId").value=spiderConfigId;

        $('#myModal').modal('show');

    }




    /*删除爬虫*/
    function deleteSpider(spiderId) {

        var result = false;
        if (confirm("确定删除吗?") == false) {
            return;
        }
        $.ajax({
            async : false,
            type : 'post',
            url : 'deleteSpider?spiderId=' + spiderId,
            success : function(msg) {
                if (msg == 'success') {
                    window.location.reload();
                    result = true;
                } else {
                    alert("删除失败");
                    result = false;
                }
            }
        });
        return result;
    }





</script>



</body>
</html>
