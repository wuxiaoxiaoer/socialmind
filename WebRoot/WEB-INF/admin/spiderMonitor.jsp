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
<link rel="stylesheet" href="admin/css/select2.css" />
<link rel="stylesheet" href="admin/css/maruti-style.css" />
<link rel="stylesheet" href="admin/css/maruti-media.css" class="skin-color" />
</head>
<body>

<!-- 引入后台头模板-->
<jsp:include page="/static/admin_header.jsp"></jsp:include>
<!--close-top-Header-menu-->


<div id="content">
  <div id="content-header">
    <div id="breadcrumb"> <a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="current">Tables</a> </div>
    <h1>爬虫监控</h1>
  </div>
  
  
  
  
  
  
  
  
  <div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-signal"></i>
								</span>
								<h5>爬虫速率</h5>
								<select  name="roleID" id="roleID" onchange="permissionByrole()">
							
								<option value="123">新华网</option>
							
								</select>
								&nbsp;&nbsp;&nbsp;
								<select  name="roleID" id="roleI" onchange="permissionByrole()">
								<option value="123">今天</option>
								<option value="123">最近一周</option>
							    <option value="123">最近一月</option>
								
								</select>	
							</div>
							<div class="widget-content">
								<div class="chart"></div>
							</div>
						</div>
					</div>
					
				</div>
                
				
		 
    <div class="row-fluid">
      <div class="span12">		
			 <div class="widget-box">
          <div class="widget-title"> <span class="icon"> <i class="icon-th"></i> </span>
            <h5>爬虫速率</h5>
			
          </div>
          <div class="widget-content nopadding">
            <table class="table table-bordered table-striped">
              <thead>
                <tr>
                  <th>最高速率</th>
                  <th>最低速率</th>
                  <th>平均速率</th>
                  <th>日间平均速率</th>
                  <th>夜间平均速率</th>
				  <th>最快速率网站</th>
                  <th>最慢速率网站</th>
                </tr>
              </thead>
              <tbody>
                <tr class="odd gradeX">
                  <td>Trident</td>
                  <td>Internet</td>
                  <td>Win 95+</td>
                  <td> 4</td>
                  <td>X</td>
				  <td> 4</td>
                  <td>X</td>
                </tr>
                
              </tbody>
            </table>
          </div>
        </div>
		</div>
      </div>  
        
				
				
				
		
				
				
				
				
                <div class="row-fluid">
					<div class="span6">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-signal"></i>
								</span>
								<h5>爬虫出错比</h5>
								
								<select  name="roleID" id="1" onchange="permissionByrole()">
							
							<option value="123">新华网</option>
							
						</select>
							&nbsp;&nbsp;&nbsp;
								<select  name="roleID" id="role" onchange="permissionByrole()">
								
								<option value="123">最近一周</option>
							    <option value="123">最近一月</option>
								<option value="123">今天</option>
								</select>	
								
							</div>
							<div class="widget-content">
								<div class="bars"></div>
							</div>
						</div>
					</div>
					
			
                    <div class="span6">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-signal"></i>
								</span>
								<h5>各网站爬取数据量</h5>
								&nbsp;&nbsp;&nbsp;
								<select  name="roleID" id="2" onchange="permissionByrole()">
								
								<option value="123">最近一周</option>
							    <option value="123">最近一月</option>
								<option value="123">今天</option>
								</select>
								&nbsp;&nbsp;&nbsp;<a>平均量 0</a>
							</div>
							<div class="widget-content">
								<div class="pie"></div>
							</div>
						</div>
					</div>
				</div>
				
				
				<div class="row-fluid">
					<div class="span6">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-signal"></i>
								</span>
								<h5>各网站爬取耗时统计</h5>
								&nbsp;&nbsp;&nbsp;
								<select  name="roleID" id="3" onchange="permissionByrole()">
								
								<option value="123">最近一周</option>
							    <option value="123">最近一月</option>
								<option value="123">今天</option>
								</select>
								&nbsp;&nbsp;&nbsp;<a>平均耗时 0 s</a>
							</div>
							<div class="widget-content">
								<div class="bars"></div>
							</div>
						</div>
					</div>
                    <div class="span6">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-signal"></i>
								</span>
								<h5>系统爬虫状态统计</h5>
								&nbsp;&nbsp;&nbsp;
								<select  name="roleID" id="4" onchange="permissionByrole()">
								<option value="123">最近新增爬虫</option>
							    <option value="123">长时间未运行</option>
								</select>
								&nbsp;&nbsp;&nbsp;
								<select  name="roleID" id="11" onchange="permissionByrole()">
								
								<option value="123">最近一周</option>
							    <option value="123">最近一月</option>
								<option value="123">今天</option>
								</select>
							</div>
							<div class="widget-content">
								<div class="pie"></div>
							</div>
						</div>
					</div>
				</div>
     
				 
			</div>
  
  
  
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="widget-box">
          <div class="widget-title">
             <span class="icon"><i class="icon-th"></i></span> 
            <h5>爬虫日志</h5>
          </div>
          <div class="widget-content nopadding">
            <table class="table table-bordered data-table">
              <thead>
                <tr>
                  <th>时间</th>
                  <th>网站</th>
                  <th>网址</th>
                  <th>错误的item</th>
				  <th>错误详情</th>
                </tr>
              </thead>
              <tbody>
                <tr class="gradeX">
                  <td>Trident</td>
                  <td>Internet</td>
                  <td>Win 95+</td>
				  <td>Win 95+</td>
                  <td>4</td>
                </tr>

                
                <tr class="gradeA">
                  <td>Presto</td>
                  <td>Opera 9.5</td>
                  <td>Win 88+ / OSX.3+</td>
				  <td>Win 95+</td>
                  <td >-</td>
                </tr>
               
                <tr class="gradeC">
                  <td>Misc</td>
                  <td>IE Mobile</td>
				  <td>Win 95+</td>
                  <td>Windows Mobile 6</td>
                  <td >-</td>
                </tr>
                <tr class="gradeC">
                  <td>Misc</td>
                  <td>PSP browser</td>
				  <td>Win 95+</td>
                  <td>PSP</td>
                  <td >-</td>
                </tr>
                <tr class="gradeU">
                  <td>Other browsers</td>
				  <td>Win 95+</td>
                  <td>All others</td>
                  <td>-</td>
                  <td >-</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        
        
       
        
      </div>
    </div>
  </div>
</div>


<!-- 引入后台尾部模板 -->
<jsp:include page="/static/admin_footer.jsp"></jsp:include>


<script src="admin/js/jquery.min.js"></script>
<script src="admin/js/jquery.ui.custom.js"></script>
<script src="admin/js/bootstrap.min.js"></script>
<script src="admin/js/jquery.uniform.js"></script>
<script src="admin/js/select2.min.js"></script>
<script src="admin/js/jquery.dataTables.min.js"></script>
<script src="admin/js/maruti.js"></script>
<script src="admin/js/maruti.tables.js"></script>
</body>
</html>
