<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">

<head>
	<base href="<%=basePath%>admin/"/>
	<title>SocialMind底层配置管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="css/bootstrap.min.css" />
	<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
	<link rel="stylesheet" href="css/fullcalendar.css" />
	<link rel="stylesheet" href="css/maruti-style.css" />
	<link rel="stylesheet" href="css/maruti-media.css" class="skin-color" />
</head>
<body>


<!-- 引入后台头模板-->
<jsp:include page="/static/admin_header.jsp"></jsp:include>

<div id="content">
  <div id="content-header">
    <div id="breadcrumb"> <a href="index.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
  </div>
  <div  class="quick-actions_homepage">
    <!--<ul class="quick-actions">
          <li> <a href="#"> <i class="icon-dashboard"></i> My Dashboard </a> </li>
          <li> <a href="#"> <i class="icon-shopping-bag"></i> Shopping Cart</a> </li>
          <li> <a href="#"> <i class="icon-web"></i> Web Marketing </a> </li>
          <li> <a href="#"> <i class="icon-people"></i> Manage Users </a> </li>
          <li> <a href="#"> <i class="icon-calendar"></i> Manage Events </a> </li>
        </ul>-->
  </div>
  <div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >网页模板</a></div>
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12"> 
        <div class="widget-box">
          <div class="widget-title"><span class="icon"><i class="icon-signal"></i></span>
            <h5>事件个数统计</h5>
            <div class="buttons"><a href="#" class="btn btn-mini"><i class="icon-refresh"></i> Update stats</a></div>
          </div>
          <div class="widget-content">
            <div class="row-fluid">
              <div class="span12">
                <div class="chart"></div>
              </div>
            </div></div></div>
			<div class="widget-title"><span class="icon"><i class="icon-file"></i></span>
                    <h5>指标统计 共计76项</h5>
                    </div>
		  <c:set var="perNum" value="0"/>
		  <c:set var="comNum" value="0"/>
		  <c:set var="insNum" value="0"/>
		  <c:set var="govNum" value="0"/>
		  <c:set var="adminNum" value="0"/>
		<c:forEach items="${users}" var="u">
			<c:if test="${u.role.roleName == '个人'}">
				<c:set var="perNum" value="${perNum + 1}"/>
			</c:if>
			<c:if test="${u.role.roleName == '企业'}">
				<c:set var="comNum" value="${comNum + 1}"/>
			</c:if>
			<c:if test="${u.role.roleName == '事业单位'}">
				<c:set var="insNum" value="${insNum + 1}"/>
			</c:if>
			<c:if test="${u.role.roleName == '政府'}">
				<c:set var="govNum" value="${govNum + 1}"/>
			</c:if>
			<c:if test="${u.role.roleName == '管理员'}">
				<c:set var="adminNum" value="${adminNum + 1}"/>
			</c:if>
		</c:forEach>
        <div class="row-fluid">
		
              <div class="span6">
                <div class="widget-box">
                  <div class="widget-title"> <span class="icon"> <i class="icon-refresh"></i> </span>
                    <h5>系统统计 共18项</h5>
                  </div>
                  <ul class="quick-actions">
					  <li> <a href="<%=basePath%>sysusersAnalysis?unit=week&times=7"> <span style="font-size: 20px;"><c:out value="${fn:length(users)}"></c:out>/${counts[0]}</span> <br>新增用户数/总用户数 </a> </li>
					  <li> <a href="#"> <span style="font-size: 20px;">${perNum}/${counts[5]}</span> <br>新增个人用户数/总个人数</a> </li>
					  <li> <a href="#"> <span style="font-size: 20px;">${comNum}/${counts[4]}</span> <br>新增企业用户数/总企业数</a> </li>
					  <li> <a href="#"> <span style="font-size: 20px;">${insNum}/${counts[3]}</span> <br>新增事业单位数/总事业单位数 </a> </li>
					  <li> <a href="#"> <span style="font-size: 20px;">${govNum}/${counts[2]}</span> <br>新增政府用户数/总政府数 </a> </li>
					  <li> <a href="#"> <span style="font-size: 20px;">${adminNum}/${counts[1]}</span> <br>新增管理员数/总管理员数 </a> </li>
					  <li> <a href="#"> <span style="font-size: 20px;">${latestCommentsCount}/${allCommentsCount}</span> <br>新增系统留言量/总留言量 </a> </li>
					  <li> <a href="#"> <span style="font-size: 20px;">${latestLogsCount}/${allLogsCount}</span> <br>新增日志生成量/总日志生成量 </a> </li>
					  <li> <a href="<%=basePath%>eventStatisticsAnalysis"> <span style="font-size: 20px;">全部事件统计分析</span> </a> </li>
					</ul>
                </div>
              </div>
			  
              <div class="span6">
                <div class="widget-box">
                  <div class="widget-title"><span class="icon"><i class="icon-file"></i></span>
                    <h5>爬虫指标统计 共18项</h5>
                    </div>
                  <div class="widget-content nopadding">
                    <ul class="quick-actions">
					  <li> <a href="#"> <i class="icon-book"></i> 最高爬虫速率</a> </li>
					  <li> <a href="#"> <i class="icon-cabinet"></i> 最低爬虫速率</a> </li>
					  <li> <a href="#"> <i class="icon-calendar"></i> 平均爬虫速率 </a> </li>
					  <li> <a href="#"> <i class="icon-client"></i> 日间平均爬虫速率</a> </li>
					  <li> <a href="#"> <i class="icon-database"></i> 夜间平均爬虫速率 </a> </li>
					  <li> <a href="#"> <i class="icon-download"></i> 最快速率网站 </a> </li>
					  
					  <li> <a href="#"> <i class="icon-graph"></i> 最慢速率网站 </a> </li>
					  <li> <a href="#"> <i class="icon-home"></i>各网站的出错比 </a> </li>
					  <li> <a href="#"> <i class="icon-lock"></i> 出错最多的网站</a> </li>
					  <li> <a href="#"> <i class="icon-mail"></i>出错最少的网站 </a> </li>
					  <li> <a href="#"> <i class="icon-pdf"></i> 最大爬取量</a> </li>
					  <li> <a href="#"> <i class="icon-people"></i> 最小爬取量</a> </li>
					  <li> <a href="#"> <i class="icon-piechart"></i> 平均爬取量</a> </li>
					  <li> <a href="#"> <i class="icon-search"></i> 最大耗时 </a> </li>
					  <li> <a href="#"> <i class="icon-shopping-bag"></i>最小耗时</a> </li>
					  <li> <a href="#"> <i class="icon-survey"></i> 平均耗时 </a> </li>
					  <li> <a href="#"> <i class="icon-tag"></i> 最近新增爬虫 </a> </li>
					   <li> <a href="#"> <i class="icon-user"></i> 长时间未运行的爬虫</a> </li>
					  <!---<li> <a href="#"> <i class="icon-wallet"></i> icon-wallet </a> </li>
					  <li> <a href="#"> <i class="icon-web"></i>icon-web </a> </li>
					  <li> <a href="#"> <i class="icon-dashboard"></i> icon-dashboard </a> </li>-->
					</ul>
                      
                    </ul> 
                  </div>
                </div>
              </div>  
			  
			  <div class="row-fluid">	  
              <div class="span6">
                <div class="widget-box">
                  <div class="widget-title"><span class="icon"><i class="icon-file"></i></span>
                    <h5>舆情事件统计 25项</h5>
                    </div>
                  <div class="widget-content nopadding">
                    <ul class="quick-actions">
					  <li> <a href="#"> <i class="icon-book"></i> 总事件数 </a> </li>
					  <li> <a href="#"> <i class="icon-cabinet"></i> 昨日新增事件数</a> </li>
					  <li> <a href="#"> <i class="icon-calendar"></i> 最近一周新增事件数 </a> </li>
					  <li> <a href="#"> <i class="icon-client"></i> 最近一月新增事件数 </a> </li>
					  <li> <a href="#"> <i class="icon-database"></i> 平均每周新增事件数 </a> </li>
					  <li> <a href="#"> <i class="icon-book"></i> 总实体数 </a> </li>
					  <li> <a href="#"> <i class="icon-cabinet"></i> 昨日新增实体数</a> </li>
					  <li> <a href="#"> <i class="icon-calendar"></i> 最近一周新增实体数 </a> </li>
					  <li> <a href="#"> <i class="icon-client"></i> 最近一月新增实体数 </a> </li>
					  <li> <a href="#"> <i class="icon-database"></i> 平均每周新增实体数 </a> </li>
					  <li> <a href="#"> <i class="icon-download"></i> 积极情感事件数 </a> </li>
					  <li> <a href="#"> <i class="icon-graph"></i> 消极情感事件数 </a> </li>
					  <li> <a href="#"> <i class="icon-home"></i>用户参与总人次 </a> </li>
					  <li> <a href="#"> <i class="icon-lock"></i> 事件最长时间跨度</a> </li>
					  <li> <a href="#"> <i class="icon-mail"></i>事件最短时间跨度 </a> </li>
					  <li> <a href="#"> <i class="icon-pdf"></i> 最活跃的网站 </a> </li>
					  <li> <a href="#"> <i class="icon-people"></i> 最近一年最活跃事件 </a> </li>
					  <li> <a href="#"> <i class="icon-piechart"></i> 最近一月最活跃事件 </a> </li>
					  <li> <a href="#"> <i class="icon-search"></i> 最近一周最活跃事件</a> </li>
					  <li> <a href="#"> <i class="icon-shopping-bag"></i>该系统访问次数最多事件 </a> </li>
					  <li> <a href="#"> <i class="icon-survey"></i> 该系统访问次数最多主题 </a> </li>
					  <li> <a href="#"> <i class="icon-tag"></i> 包含事件最多的主题 </a> </li>
					   <li> <a href="#"> <i class="icon-user"></i> 包含事件最少的主题</a> </li>
					  <li> <a href="#"> <i class="icon-user"></i> 近月包含事件最多的主题</a> </li>
					  <li> <a href="#"> <i class="icon-user"></i> 近月包含事件最少的主题</a> </li>
					</ul>
                      
                    </ul> 
                  </div>
                </div>
              </div>	  
              
              <div class="span6">
                <div class="widget-box">
                  <div class="widget-title"> <span class="icon"> <i class="icon-refresh"></i> </span>
                    <h5>数据清洗指标统计 15项</h5>
                  </div>
                  <div class="widget-content">
					<ul class="quick-actions">
					  <li> <a href="#"> <i class="icon-book"></i> 尚未清洗的表</a> </li>
					  <li> <a href="#"> <i class="icon-cabinet"></i> 正在清洗的表</a> </li>
					  <li> <a href="#"> <i class="icon-calendar"></i> 已经清洗的表 </a> </li>
					  <li> <a href="#"> <i class="icon-client"></i> 上一次清洗的表</a> </li>
					  <li> <a href="#"> <i class="icon-database"></i> 上一次清洗的管理员 </a> </li>
					  <li> <a href="#"> <i class="icon-download"></i> 清洗待解决问题 </a> </li>
					  
					  <li> <a href="#"> <i class="icon-graph"></i> 总清洗次数 </a> </li>
					  <li> <a href="#"> <i class="icon-people"></i> 数据清洗频率 </a> </li>
					  <li> <a href="#"> <i class="icon-home"></i>上一次清洗时间 </a> </li>
					 <li> <a href="#"> <i class="icon-pdf"></i> 每次平均清洗时间 </a> </li>
					  <li> <a href="#"> <i class="icon-lock"></i> 上次清洗后新增数据量 </a> </li>
					  <li> <a href="#"> <i class="icon-mail"></i>上次清洗的数据量 </a> </li>
					  <li> <a href="#"> <i class="icon-piechart"></i> 总清洗数据量 </a> </li>
					  <li> <a href="#"> <i class="icon-search"></i> 清洗人员统计 </a> </li>
					  <li> <a href="#"> <i class="icon-shopping-bag"></i>每次平均清洗数据量 </a> </li>
					  <!-- <li> <a href="#"> <i class="icon-shopping-bag"></i>icon-shopping-bag </a> </li>
					  <li> <a href="#"> <i class="icon-survey"></i> icon-survey </a> </li>
					  <li> <a href="#"> <i class="icon-tag"></i> icon-tag </a> </li>
					   <li> <a href="#"> <i class="icon-user"></i> icon-user</a> </li>
					  <li> <a href="#"> <i class="icon-wallet"></i> icon-wallet </a> </li>
					  <li> <a href="#"> <i class="icon-web"></i>icon-web </a> </li>   -->

					</ul>
					</div>
                </div>
              </div>  
			 </div>			  
        </div> 
      </div>
      </div>
    </div>

  </div>
</div>

<!-- 引入后台尾部模板 -->
<jsp:include page="/static/admin_footer.jsp"></jsp:include>

<script src="js/excanvas.min.js"></script> 
<script src="js/jquery.min.js"></script> 
<script src="js/jquery.ui.custom.js"></script> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/jquery.flot.min.js"></script> 
<script src="js/jquery.flot.resize.min.js"></script> 
<script src="js/jquery.peity.min.js"></script>
<script src="js/fullcalendar.min.js"></script> 
<script src="js/maruti.js"></script> 
<script src="js/maruti.dashboard.js"></script> 
<script src="js/maruti.chat.js"></script> 
<script type="text/javascript">
  // This function is called from the pop-up menus to transfer to
  // a different page. Ignore if the value returned is a null string:
  function goPage (newURL) {

      // if url is empty, skip the menu dividers and reset the menu selection to default
      if (newURL != "") {
      
          // if url is "-", it is this page -- reset the menu:
          if (newURL == "-" ) {
              resetMenu();            
          } 
          // else, send page to designated URL            
          else {  
            document.location.href = newURL;
          }
      }
  }

// resets the menu selection upon entry to this page:
function resetMenu() {
   document.gomenu.selector.selectedIndex = 2;
}
</script>
</body>

</html>
