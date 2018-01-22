<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
								<select  name="speed" id="speed" onchange="speedByWebsite();">

									<option value="douban">豆瓣小组</option>
									<option value="mop">猫扑</option>
									<option value="bbs_sohu">搜狐BBS</option>
									<option value="bbs_people">人民网BBS</option>
									<option value="blog_163">163博客</option>
									<option value="kdnet_spider">凯迪社区</option>
									<option value="sina_blog">新浪博客</option>
									<option value="sina_news">新浪新闻</option>
									<option value="tianya_bbs">天涯论坛</option>
									<option value="xici">西祠</option>
									<option value="weibo_spider">微博</option>

								</select>
								&nbsp;&nbsp;&nbsp;
								<%--<select  name="roleID" id="roleI" onchange="permissionByrole()">--%>
								<%--<option value="123">今天</option>--%>
								<%--<option value="123">最近一周</option>--%>
							    <%--<option value="123">最近一月</option>--%>
								<%----%>
								<%--</select>	--%>
							</div>
							<div class="widget-content">

								<div style="height:400px" id="speedChart">

								</div>


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
                  <%--<th>日间平均速率</th>--%>
                  <%--<th>夜间平均速率</th>--%>
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
								
					  <select  name="website1" id="website1" onchange="errorRate()">
							
						  <option value="douban">豆瓣小组</option>
						  <option value="mop">猫扑</option>
						  <option value="bbs_sohu">搜狐BBS</option>
						  <option value="bbs_people">人民网BBS</option>
						  <option value="blog_163">163博客</option>
						  <option value="kdnet">凯迪社区</option>
						  <option value="sina_blog">新浪博客</option>
						  <option value="sina_news">新浪新闻</option>
						  <option value="tianya_bbs">天涯论坛</option>
						  <option value="xici">西祠</option>
						  <option value="weibo">微博</option>

					  </select>
							&nbsp;&nbsp;&nbsp;
								<%--<select  name="roleID" id="role" onchange="permissionByrole()">--%>
								<%--<option value="123">今天</option>--%>
								<%--<option value="123">最近一周</option>--%>
							    <%--<option value="123">最近一月</option>--%>

								<%--</select>	--%>
								<%----%>
				  </div>
				  <div class="widget-content">
					  <div class="bars" id="errorChart"></div>
				  </div>
			  </div>
		  </div>
					
			
                    <div class="span6">
						<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<i class="icon-signal"></i>
								</span>
								<h5>爬取数据量</h5>
								&nbsp;&nbsp;&nbsp;
								<%--<select  name="roleID" id="2" onchange="permissionByrole()">--%>
								<%--<option value="123">今天</option>--%>
								<%--<option value="123">最近一周</option>--%>
							    <%--<option value="123">最近一月</option>--%>

								<%--</select>--%>
								&nbsp;&nbsp;&nbsp;<a>平均量 0</a>
							</div>
							<div class="widget-content">
								<div class="pie" id="dataSizeChart"></div>
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
								<h5>爬取耗时统计</h5>
								&nbsp;&nbsp;&nbsp;
								<%--<select  name="roleID" id="3" onchange="permissionByrole()">--%>
								<%--<option value="123">今天</option>--%>
								<%--<option value="123">最近一周</option>--%>
							    <%--<option value="123">最近一月</option>--%>

								<%--</select>--%>
								&nbsp;&nbsp;&nbsp;<a>平均耗时 0 s</a>
							</div>
							<div class="widget-content">
								<div class="bars" id="durationChart"></div>
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
								<%--<select  name="roleID" id="11" onchange="permissionByrole()">--%>
								<%--<option value="123">今天</option>--%>
								<%--<option value="123">最近一周</option>--%>
							    <%--<option value="123">最近一月</option>--%>

								<%--</select>--%>
							</div>
							<div class="widget-content">
								<div class="pie" id="stateChart"></div>
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
            <table class="table table-bordered data-table" id="hidden-table-info">
              <thead>
                <tr>
                  <th>时间</th>
                  <th>网站</th>
                  <th>网址</th>
                  <th>错误的item</th>
				  <th>错误详情</th>
					<th style="display: none;"></th>
					<th style="display: none;"></th>
                </tr>
              </thead>
				<tbody>
				<c:forEach items="${errorDetailMap['douban']}" var="v">

					<tr class="gradeX">
						<td>${v.logTime}</td>
						<td>${v.website}</td>
						<td>${v.url}</td>
						<td>${fn:substring(v.errorItem, 0, 100)}</td>
						<td>${fn:substring(v.errorDetail, 0, 100)}</td>
						<td style="display: none;">${v.errorItem}</td>
						<td style="display: none;">${v.errorDetail}</td>
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


<!-- 引入后台尾部模板 -->
<jsp:include page="/static/admin_footer.jsp"></jsp:include>

<script src="admin/js/jquery.min.js"></script>
<script src="admin/js/jquery.ui.custom.js"></script>
<script src="admin/js/bootstrap.min.js"></script>
<script src="admin/js/jquery.uniform.js"></script>
<%--<script src="admin/js/select2.min.js"></script>--%>
<script src="admin/js/jquery.dataTables.min.js"></script>
<script src="admin/js/maruti.js"></script>
<script src="admin/js/maruti.tables.js"></script>

<script src="admin/js/echarts.min.js"></script>




<%--详细信息显示--%>


<script type="text/javascript">
	/* Formating function for row details */
    function fnFormatDetails ( oTable, nTr )
    {
        var aData = oTable.fnGetData( nTr );
        var sOut = '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">';
        sOut += '<tr><td>错误条目:</td><td>' + aData[5] + '</td></tr>';
        sOut += '<tr><td>错误详情:</td><td>' + aData[6] + '</td></tr>';
        sOut += '</table>'
        return sOut;
    }

    $(document).ready(function() {
		/*
		 * Insert a 'details' column to the table
		 */
        var nCloneTh = document.createElement( 'th' );
        var nCloneTd = document.createElement( 'td' );
        nCloneTd.innerHTML = '<img src="<%=basePath %>/admin/img/details_open.png">';
        nCloneTd.className = "center";

        $('#hidden-table-info thead tr').each( function () {
            this.insertBefore( nCloneTh, this.childNodes[0] );
        } );

        $('#hidden-table-info tbody tr').each( function () {
            this.insertBefore(  nCloneTd.cloneNode( true ), this.childNodes[0] );
        } );

		/*
		 * Initialse DataTables, with no sorting on the 'details' column
		 */
        var oTable = $('#hidden-table-info').dataTable( {
            "aoColumnDefs": [
                { "bSortable": false, "aTargets": [ 0 ] }
            ],
            "aaSorting": [[1, 'desc']]
        });

		/* Add event listener for opening and closing details
		 * Note that the indicator for showing which row is open is not controlled by DataTables,
		 * rather it is done here
		 */
        $('#hidden-table-info tbody td img').live('click', function () {
            var nTr = $(this).parents('tr')[0];
            if ( oTable.fnIsOpen(nTr) )
            {
				/* This row is already open - close it */
                this.src = "<%=basePath %>/admin/img/details_open.png";
                oTable.fnClose( nTr );
            }
            else
            {
				/* Open this row */
                this.src = "<%=basePath %>/admin/img/details_close.png";
                oTable.fnOpen( nTr, fnFormatDetails(oTable, nTr), 'details' );
            }
        } );
    } );
</script>




<script>

    var speedChart = echarts.init(document.getElementById('speedChart'));
    var map = JSON.parse('${map}');

    var logtimeList =map['speedmap']['logtimeList'];
    var speedList =map['speedmap']['speedList'];

   <%--var data = JSON.parse('${speed}');--%>

    option = {
        tooltip: {
            trigger: 'axis',
            position: function (pt) {
                return [pt[0], '10%'];
            }
        },
        title: {
            left: 'center',
            text: '爬虫速度变化',
        },
        toolbox: {
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
//            axisLabel:{
//                //X轴刻度配置
//                interval:auto//0：表示全部显示不间隔；auto:表示自动根据刻度个数和宽度自动设置间隔个数
//            },
            data: logtimeList
        },
        yAxis: {

            type: 'value',
            boundaryGap: [0, '100%'],

            axisLabel : {
                formatter: '{value} pages/min'
            }


        },
        dataZoom: [{
            type: 'inside',
            start: 0,
            end: 100
        }, {
            start: 0,
            end: 10,
            handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
            handleSize: '30%',
            handleStyle: {
                color: '#fff',
                shadowBlur: 3,
                shadowColor: 'rgba(0, 0, 0, 0.6)',
                shadowOffsetX: 2,
                shadowOffsetY: 2
            }
        }],
        series: [
            {
                name:'速率',
                type:'line',
                smooth:true,
                symbol: 'none',
                sampling: 'average',
                itemStyle: {
                    normal: {
                        color: 'rgb(255, 70, 131)'
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgb(255, 158, 68)'
                        }, {
                            offset: 1,
                            color: 'rgb(255, 70, 131)'
                        }])
                    }
                },
                data:speedList
            }
        ]
    };

    speedChart.setOption(option);





</script>


<script>
    function speedByWebsite(){

        var website = $("#speed").val();
        var options = speedChart.getOption();

//        通过Ajax获取数据
        $.ajax({
            async : false,
            type : 'post',
            url : 'speedByWebsite?website=' + website,
            dataType:"json",
            success:function(data) {
                if (data) {

                    options.series[0].data =eval(data).speedList;
                    options.xAxis[0].data = eval(data).logtimeList;
                    speedChart.hideLoading();
                    speedChart.setOption(options);
                }else {
                    alert("无数据");
                }
            }
        });



    }

</script>


<script>
    var errorChart = echarts.init(document.getElementById('errorChart'));


    var map = JSON.parse('${map}');
    var errorRateMap =map['errorRateMap'];
    b("douban");
    function b(s){

        var infoNum =errorRateMap[s]['infoNum'];
        var errorNum =errorRateMap[s]['errorNum'];
        var debugNum =errorRateMap[s]['debugNum'];

        option = {
            title : {
                text: '爬虫出错比',
                subtext: '',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['info','error','debug']
            },
            series : [
                {
                    name: '状态',
                    type: 'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:[
                        {value:debugNum, name:'debug'},
                        {value:errorNum, name:'error'},
                        {value:infoNum, name:'info'},
                    ],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        errorChart.setOption(option);
	}


    function errorRate(){
        var website = $("#website1").val();

        var data=[
            {value:errorRateMap[website]['debugNum'], name:'debug'},
            {value:errorRateMap[website]['errorNum'], name:'error'},
            {value:errorRateMap[website]['infoNum'], name:'info'}
        ];
        var options = errorChart.getOption();
        options.series[0].data = data;

        errorChart.setOption(options);
    }




</script>
<script>

</script>

<script>

    var durationChart = echarts.init(document.getElementById('durationChart'));

    var map = JSON.parse('${map}');
    var durationMap =map['durationMap'];

	var website=[];
    var duration=[];



    for(var key in durationMap)
	{
        website.push(key);
        duration.push(durationMap[key]);

    }

//    durationMap.forEach(function (value, key, map) {
//        website.push(key);
//        duration.push(value);
//    });


    option = {
        color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },

        xAxis : [
            {
                type : 'category',
                data : website,
                axisLabel:{
                //X轴刻度配置
                interval:0//0：表示全部显示不间隔；auto:表示自动根据刻度个数和宽度自动设置间隔个数
            }

            }
        ],
        yAxis : [
            {
                type : 'value',
                axisLabel : {
                    formatter: '{value} h'
                }
            }
        ],
        series : [
            {
                name:'爬取时长',
                type:'bar',
                barWidth: '60%',
                data:duration
            }
        ]
    };

    durationChart.setOption(option);


</script>


</body>
</html>
