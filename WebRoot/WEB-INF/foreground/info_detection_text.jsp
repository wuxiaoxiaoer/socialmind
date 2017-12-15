<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    
    <head>
        <title>Tables</title>
        <!-- Bootstrap -->
        <link href="<%=basePath%>foreground/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="<%=basePath%>foreground/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
        <link href="<%=basePath%>foreground/assets/styles.css" rel="stylesheet" media="screen">
        <link href="<%=basePath%>foreground/assets/DT_bootstrap.css" rel="stylesheet" media="screen">
        <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="foreground/vendors/flot/excanvas.min.js"></script><![endif]-->
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <script src="<%=basePath%>foreground/vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    </head>
    
    <body>

    <!-- 引入头模板 -->
    <jsp:include page="/static/fore_header.jsp"/>

        <div class="container-fluid" style="margin-top: 4%;">
            <div class="row-fluid">
                <div class="span3" id="sidebar">
                    <ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">
                        <li>
                            <a href="index.html"><i class="icon-chevron-right"></i> 检测结果</a>
                        </li>
                        <li>
                            <a href="calendar.html"><i class="icon-chevron-right"></i> 筛选条件</a>
                        </li>
                        <li>
                            <a href="stats.html"><i class="icon-chevron-right"></i> 信息列表</a>
                        </li>
                        
                    </ul>
                </div>
				

				
                <!--/span-->
                <div class="span9" id="content">
                   	<!--title-->
					<h3 align="center">信息检测</h3>
					<div align="left">
                        <button class="btn btn-large"><a href="info_detection_text.jsp" style="text-decoration:none">信息列表</a></button>
                        <button class="btn btn-large"><a href="info_dectection_info_graph.html" style="text-decoration:none">图表分析</a></button>
                        
                        
					</div>

					<!--推荐事件-->
				    <br/>
					<!--map-->
					<div class="row-fluid">
                        <div class="navbar">
                            	<div class="navbar-inner">
	                                <ul class="breadcrumb">
	                                   
	                                    <li>
	                                        <h4>西安交通大学(信息检测内容)</h4> 
	                                    </li>
	                                    
	                                </ul>
                            	</div>
                        </div>
                        
                       
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">筛选条件</div>
                                <div class="pull-right"><span class="badge badge-warning">刷新</span>
                                </div>
                            </div>
                            <div class="block-content collapse in">
                                    <div>
                                       <h4>检测时间：<button class="btn btn-mini">今天</button>
											<button class="btn btn-primary btn-mini">24小时</button>
											<button class="btn btn-info btn-mini">2天</button>
											<button class="btn btn-success btn-mini">3天</button>
											<button class="btn btn-warning btn-mini">7天</button>
											<button class="btn btn-danger btn-mini">10天</button>
											<button class="btn btn-inverse btn-mini">自定义</button></h4>
                                      
                                     </div>
                                     <div>
                                       <h4>信息排序：<button class="btn btn-mini">时间降序</button>
											<button class="btn btn-primary btn-mini">时间升序</button>
											<button class="btn btn-info btn-mini">相似度</button>
											<button class="btn btn-success btn-mini">相似文章数</button></h4>
                                       
											
											
										
                                     </div>
                             </div>
                            
                        </div>
                        
                     
                </div>
                
                
                <div class="row-fluid">
                        <!-- block -->
                        <div class="block">
                            <div class="navbar navbar-inner block-header">
                                <div class="muted pull-left">信息列表</div>
                            </div>
                            <div class="block-content collapse in">
                                <div class="span12">
                                    
  									<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered" id="example">
										<thead>
											<tr>
												<th><i class="icon-ok-sign"></i></th>
												<th>标题</th>
												<th>相似文章</th>
												<th>来自</th>
												<th>时间</th>
                                                <th>收藏</th>
											</tr>
										</thead>
										<tbody>
											<tr class="odd gradeX">
                                                <td class="center"><i class="icon-ok-sign"></i></td>
												<td width="70%">不久之后， 宝文理终将实现由“宝鸡文理学院→宝鸡大学、二本→一本”的质的飞跃！同时，祝贺我宝文理被拟为新增博士学位授予单位立项建设单位！ 我在宝鸡，发来贺电 愿学校越办越好！ 争取在我毕业之前正式升为宝鸡大学 我为母校打call@宝鸡文理学院团委:转发微博 【原微博】 【@陕西省教育厅 @宝鸡发布 签约共同支持筹建宝鸡大学[鼓掌]】签约仪式今日上午在@宝鸡文理学院 举行。省教育厅厅长王建利、宝鸡市市长惠进才代表双方签署协议，宝鸡市委书记徐启方出席会议。此外，@西安交通大学 @西北农林科技大学 @陕西师范大学 @西北大学 等高校分别与宝鸡文理签订对口支持协议。</td>
												<td>508</td>
												<td>新浪微博</td>
												<td class="center"> 今天 12：49</td>
												<td class="center"><i class="icon-heart"></i></td>
                                                
											</tr>
                                            
                                            <tr class="odd gradeX">
                                                <td class="center"><i class="icon-ok-sign"></i></td>
												<td width="70%">转发微博 【原微博】 【明天我校将迎来学校发展史上的大事件，让我们共同见证】一：@陕西省教育厅 宝鸡市人民政府共同推进宝鸡文理学院建成宝鸡大学协议签字仪式；二：@西安交通大学 、@西北农林科技大学 、@陕西师范大学 、@西北大学 对口支援宝鸡文理学院协议签字仪式。</td>
												<td>408</td>
												<td>贴吧</td>
												<td class="center"> 今天 12：49</td>
												<td class="center"><i class="icon-heart"></i></td>
                                                
											</tr>
                                            
                                            <tr class="odd gradeX">
                                                <td class="center"><i class="icon-ok-sign"></i></td>
												<td width="70%">主席。现任前海开源基金管理有限公司荣誉董事长。王宏远先生，联席董事长，西安交通大学经济学硕士，美国哥伦比亚大学公共管理硕士，国籍：中国。曾任深圳特区证券有限公司行业研究员，南方基金</td>
												<td>100</td>
												<td>强国论坛</td>
												<td class="center"> 今天 12：49</td>
												<td class="center"><i class="icon-heart"></i></td>
											</tr>

										</tbody>
									</table>
                                </div>
                            </div>
                        </div>
                        <!-- /block -->
                    </div>
              
                </div>
            </div>
            <hr>

            <!-- 引入尾部模板 -->
            <jsp:include page="/static/fore_footer.jsp"></jsp:include>

        </div>
        <!--/.fluid-container-->

        <script src="<%=basePath%>foreground/vendors/jquery-1.9.1.js"></script>
        <script src="<%=basePath%>foreground/bootstrap/js/bootstrap.min.js"></script>
        <script src="<%=basePath%>foreground/vendors/datatables/js/jquery.dataTables.min.js"></script>


        <script src="<%=basePath%>foreground/assets/scripts.js"></script>
        <script src="<%=basePath%>foreground/assets/DT_bootstrap.js"></script>
        <script>
        $(function() {
            
        });
        </script>
    </body>

</html>