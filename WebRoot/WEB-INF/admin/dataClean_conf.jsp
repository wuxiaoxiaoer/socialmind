<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=basePath%>admin/"/>
	<title>清洗策略配置 - Socialmind大平台</title>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="css/bootstrap.min.css" />
	<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
	<link rel="stylesheet" href="css/uniform.css" />
	<link rel="stylesheet" href="css/select2.css" />
	<link rel="stylesheet" href="css/maruti-style.css" />
	<link rel="stylesheet" href="css/maruti-media.css" class="skin-color" />

	<SCRIPT LANGUAGE="JavaScript">
        <!--
        //上移
        function moveUp(obj)
        {
            for(var i=1; i < obj.length; i++)
            {//最上面的一个不需要移动，所以直接从i=1开始
                if(obj.options[i].selected)
                {
                    if(!obj.options.item(i-1).selected)
                    {
                        var selText = obj.options[i].text;
                        var selValue = obj.options[i].value;
                        obj.options[i].text = obj.options[i-1].text;
                        obj.options[i].value = obj.options[i-1].value;
                        obj.options[i].selected = false;
                        obj.options[i-1].text = selText;
                        obj.options[i-1].value = selValue;
                        obj.options[i-1].selected=true;
                    }
                }
            }
        }
        //下移
        function moveDown(obj)
        {
            for(var i = obj.length -2 ; i >= 0; i--)
            {//向下移动，最后一个不需要处理，所以直接从倒数第二个开始
                if(obj.options[i].selected)
                {
                    if(!obj.options[i+1].selected)
                    {
                        var selText = obj.options[i].text;
                        var selValue = obj.options[i].value;
                        obj.options[i].text = obj.options[i+1].text;
                        obj.options[i].value = obj.options[i+1].value;
                        obj.options[i].selected = false;
                        obj.options[i+1].text = selText;
                        obj.options[i+1].value = selValue;
                        obj.options[i+1].selected=true;
                    }
                }
            }
        }
        //移动
        function moveOption(obj1, obj2)
        {
            for(var i = obj1.options.length - 1 ; i >= 0 ; i--)
            {
                if(obj1.options[i].selected)
                {
                    var opt = new Option(obj1.options[i].text,obj1.options[i].value);
                    opt.selected = true;
                    obj2.options.add(opt);
                    obj1.remove(i);
                }
            }
        }
        //置顶
        function  moveTop(obj)
        {
            var  opts = [];
            for(var i =obj.options.length -1 ; i >= 0; i--)
            {
                if(obj.options[i].selected)
                {
                    opts.push(obj.options[i]);
                    obj.remove(i);
                }
            }
            var index = 0 ;
            for(var t = opts.length-1 ; t>=0 ; t--)
            {
                var opt = new Option(opts[t].text,opts[t].value);
                opt.selected = true;
                obj.options.add(opt, index++);
            }
        }
        //置底
        function  moveBottom(obj)
        {
            var  opts = [];
            for(var i =obj.options.length -1 ; i >= 0; i--)
            {
                if(obj.options[i].selected)
                {
                    opts.push(obj.options[i]);
                    obj.remove(i);
                }
            }
            for(var t = opts.length-1 ; t>=0 ; t--)
            {
                var opt = new Option(opts[t].text,opts[t].value);
                opt.selected = true;
                obj.options.add(opt);
            }
        }
        //-->
		
		//when choose a strategyClass
        function strategyConf(strategyID) {
//            show current strategy name
			switch(strategyID){
				case "1":
                    $("#strategyClassify").text("缺失值");
                    break;
                case "2":
                    $("#strategyClassify").text("字符串错误");
                    break;
                case "3":
                    $("#strategyClassify").text("行操作");
                    break;
                case "4":
                    $("#strategyClassify").text("时间格式");
                    break;
                case "5":
                    $("#strategyClassify").text("日期格式");
                    break;
			}
            $.post("strategyConfAction",
                {
                    strategyID: strategyID
                },
                function (data, status) {
					$("#getStrategy").html(data);
				});
        }
	</SCRIPT>
</head>
<body>

<!-- 引入后台头模板-->
<jsp:include page="/static/admin_header.jsp"></jsp:include>
		
		
		
		<div id="content">
			<div id="content-header">
				<div id="breadcrumb">
				<a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
				<a href="#">Form elements</a>
				<a href="#" class="current">Form wizard</a>
			</div>
                <h1>数据清洗策略配置</h1>
				
			</div>
									<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-pencil"></i>
								</span>
								<h5>当前清洗策略的展示</h5>
							</div>
							<p>缺失值：
								<c:forEach  items="${loseDataList}" var="v">
									<a onclick="cleanProcess(${v.cleanStategyId})">${v.name}</a> |
								</c:forEach>
							</p>
							<p>字符串错误：
								<c:forEach  items="${errorString}" var="v">
									<a onclick="cleanProcess(${v.cleanStategyId})">${v.name}</a> |
								</c:forEach>
							</p>
							<p>行操作：
								<c:forEach  items="${recordOperating}" var="v">
									<a onclick="cleanProcess(${v.cleanStategyId})">${v.name}</a> |
								</c:forEach>
							</p>
							<p>时间格式：
								<c:forEach  items="${dateOperating}" var="v">
									<a onclick="cleanProcess(${v.cleanStategyId})">${v.name}</a> |
								</c:forEach>
							</p>
							<p>日期格式
								<c:forEach  items="${addressOperating}" var="v">
									<a onclick="cleanProcess(${v.cleanStategyId})">${v.name}</a> |
								</c:forEach>
							</p>
						</div>
					</div>
				</div>
			</div>

			
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-pencil"></i>
								</span>
								<h5>策略统计</h5>
							</div>
							<div class="widget-content nopadding">
								<p>最常用策略：</p> 
								<p>最不常用策略：</p>
								<p>策略使用频度条形统计图：</p>
                                
							</div>
						</div>
					</div>
				</div>
			</div>
			
						<div class="container-fluid" id="getStrategy">
							<%@ include file="strategyConf.jsp"%>
			</div>
			

		</div>
		
		<div class="row-fluid">
      <div id="footer" class="span12"> 2012 &copy; Marutii Admin. More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a> </div>
    </div>
            
            <script src="js/jquery.min.js"></script>
            <script src="js/jquery.ui.custom.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/jquery.validate.js"></script>
            <script src="js/jquery.wizard.js"></script>
            <script src="js/maruti.js"></script>
            <script src="js/maruti.wizard.js"></script>
	</body>

</html>
