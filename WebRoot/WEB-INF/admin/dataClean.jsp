<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2017/9/6
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<%--<jsp:include page="static/head_jsp.jsp"/>--%>
<head>
  <script src="js/etljs/html5.js"></script>
  <![endif]-->
  <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <!-- core js files -->
  <script>src="js/etljs/jquery.js"</script>
  <script src="js/etljs/jquery-1.11.0.min.js"></script>
  <%--下面这个包有问题--%>
  <%--<script src="js/etljs/bootstrap.min.js"></script>--%>
  <script src="js/etljs/modernizr.custom.js"></script>
  <script src="js/etljs/core.js"></script>
  <script>src="js/etljs/mock.js"</script>

  <script>src="js/etljs/jquery.dropdown.js"</script>
  <!-- core js files -->

  <link rel="stylesheet" href="css/etlcss/bootstrap.min.css">
  <link rel="stylesheet" href="css/etlcss/font-awesome.min.css">
  <link rel="stylesheet" href="css/etlcss/style.css">


  <link rel="stylesheet" href="css/etlcss/plugins/chosen/chosen.css">
  <script src="js/etljs/plugins/chosen.jquery.min.js"></script>

  <link rel="stylesheet" href="css/etlcss/demo_table.css">
  <link rel="stylesheet" href="css/etlcss/dtable.css">
  <script src="js/etljs/jquery.dataTables.min.js"></script>
  <script src="js/etljs/sorting.js"></script>

  <script src="js/etljs/plugins/jquery.tipsy.js"></script>
  <link rel="stylesheet" href="css/etlcss/plugins/files/tipsy.css">

  <link rel="stylesheet" href="css/etlcss/plugins/datepicker/datepicker.css">
  <script src="js/etljs/plugins/bootstrap-datepicker.js"></script>
  <script src="js/etljs/plugins/jquery.maskedinput.min.js"></script>

  <script src="js/etljs/plugins/bootstrap3-typeahead.min.js"></script>

  <script src="js/etljs/plugins/bootbox.min.js"></script>
  <script src="js/etljs/plugins/jquery.dlmenu.js"></script>

  <link rel="stylesheet" href="css/etlcss/plugins/files/bootstrap-checkbox.css">
  <script src="js/etljs/plugins/bootstrap-checkbox.js"></script>

  <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
  <script type="text/javascript" src="js/etljs/echarts.js"></script>
  <script type="text/javascript">
      var ac_siteURL='';  //PHP Application üzerinde autocomplete verisi işlenmesi için temel adres tanımlaması için
  </script>
  <style type="text/css">
    <%--表格中的form一开始不可见--%>
    .tdForm{display:none}

    #btn_bbs_mop{display: none}
    #btn_bbs_news{display: none}
    #btn_bbs_people{display: none}
    #btn_bbs_sohu{display: none}
    #btn_bbs_tianya{display: none}
    #btn_bbs_xici{display: none}
    #btn_blog_163{display: none}
    #btn_blog_sina{display: none}
    #btn_blogchina{display: none}
    #btn_douban{display: none}
    #btn_kdnet{display: none}
    #btn_moe{display: none}
    /*#btn_sanqin{display: none}*/
    /*#btn_news_sina{display: none}*/
    /*#btn_people_news{display: none}*/
    /*#btn_weibo{display: none}*/
    #btn_xinhua_news{display: none}
    /*#btn_xinwen110{display: none}*/
  </style>
  <script>
    //点击表头时将名字传给“当前操作的字段”
      function getHeadTag(headName) {
        var btnTag=document.getElementById("currentHead");
        btnTag.innerText=headName;
    }
    //将浏览器会自动转义的html标签转化为不被转义的
      function html2Escape(sHtml)
      {
          return sHtml.replace(/[<>&"]/g,function(c){return{'<':'&lt;','>':'&gt;','&':'&amp;','"':'&quot;'}[c];});
      }
      //点击表格时的处理，第一个参数是点击单元格的id，第二个是当前表的名字，第二个是当前列名字
      function editData(pId,tbName,columnName){
          //点击任意一个单元格时获取当前列
          getHeadTag(columnName);
          /*表内数据可编辑*/
          //获取p标签
          var pTag =document.getElementById(pId);
          //获取input标签
          var inputTag =document.getElementById(pId+"Input");
          inputTag.value=pTag.innerText;
          pTag.style.display ="none";
          inputTag.style.display="inline";
          //文本框获得焦点，使不编辑使也可执行失去焦点的函数
          inputTag.focus();
          /*提交当前点击的列名字来改变统计值*/
          var myChart = echarts.init(document.getElementById("stats"));
          $.post("ClickTableServlet",
              {
                  tableName: tbName,
                  column: columnName
              },
              function (data, status) {
//                  alert(data);
                  var dataJson= eval('(' + data + ')');
                  var statsContent ="";
                  var Arr = new Array();//key
                  var ArrNum = new Array()//个数
                  for (var key in dataJson)
                  {
                              Arr.push( key);//存入arr
                              ArrNum.push({"value": dataJson[key],"name":key});
                  }
                  option = {
//                      title : {
//                          text: '字段中频繁项统计图',
////                          subtext: '纯属虚构',
//                          x:'center'
//                      },
                      tooltip : {
                          trigger: 'item',
                          formatter: "{a} <br/>{b} : {c} ({d}%)"
                      },
                      legend: {
                          orient: 'vertical',
                          left: 'left',
                          data: Arr
                      },
                      series : [
                          {
                              name: '频繁项',
                              type: 'pie',
                              radius : '60%',
                              center: ['52%', '70%'],
                              data: ArrNum,
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
                  myChart.setOption(option);
              });

      }
      //input失去焦点的事件
      function loseBlur(inputId) {
          var pId = inputId.replace("Input","");
          var pTag =document.getElementById(pId);
          var inputTag =document.getElementById(inputId);
          pTag.innerText=inputTag.value;
          inputTag.style.display="none";
          pTag.style.display ="inline";
      }

      //1、获取到xmlhttprequest
      function xmlhttprequest() {
          var xmlhttp;
          if (window.XMLHttpRequest) { // code for IE7+, Firefox, Chrome, Opera, Safari
              xmlhttp = new XMLHttpRequest();
          } else { // code for IE6, IE5
              xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
          }
          return xmlhttp;
      }
      //用户点击某表btn时，将该表名存入input引发其改变，从而触发ajax的onchange函数，清空

      function display(value) {
          $.post("TableServlet",
              {
                  tablename: value
              },
              function (data, status) {
                  //前台拿到的是一个字符串
//                  data=data.replace(new RegExp("&","gm"),"null");

                  var headData = data.split("++")[0];
                  //bodyData得到的是中括号包含大括号的json
                  headData="("+headData+")";
//                  alert("head解析前"+headData);
                  var headObj = eval('(' + headData + ')');
//                  alert("head解析成json"+headObj);
                  var bodyData = data.split("++")[1];

                  bodyData ="("+bodyData+")";
//                  alert("body解析前"+bodyData);
                  var bodyObj = eval('(' + bodyData + ')');
//                  alert("body解析后"+eval('(' + bodyData + ')'));
                  var html = "";
                  html +="<thead><tr role=\'role\'>";
                  for(var i=0;i<headObj.length;i++){    //遍历data数组
                      var headLs = headObj[i];
                      html +="<th align=\"center\" role=\"columnheader\" rowspan=\"1\" colspan=\'1\' tabindex=\"0\"  bgcolor=\'#ffe4c4\' onclick=\"getHeadTag(\'"+headLs+"\')\">"+headLs+"</th>";
                  }
                  html =html+"</tr></thead>";
                  html =html+"<tbody role=\"alert\" aria-live=\"polite\" aria-relevant=\"all\">";
                  for(var k = 0; k < bodyObj.length; ){    //遍历data数组,取100个值#############这里需要调整可控制
                      if (k%2==0){
                          html +="<tr class=\"gradeA odd\">";
                      }else{
                          html +="<tr class=\"gradeA even\">";
                      }
                      for(var j =0;j<headObj.length;j++){
                          var bodyLs = bodyObj[j+k];
                          //处理掉原文中包含的标签
//                          bodyLs.replace("<","&lt;");
//                          bodyLs.replace(">","&gt;");
                          //一个变化的独一无二的数
                          var pId=k+"&"+j;
                          var inputId=pId+"Input";
                          bodyLs =html2Escape(bodyLs);
                          html +="<td><p id=\'"+pId+"\' onclick=\"editData(\'"+pId+"\',\'"+value+"\',\'"+headObj[j]+"\')\">"+bodyLs+"</p><input id=\'"+inputId+"\' class=\"tdForm\" onblur=\"loseBlur(\'"+inputId+"\')\" /></td>";
                      }
                      html +="</tr>";
                      k=k+headObj.length;
                  }
                  html=html+"</tbody>";
                  $("#displayTable").html(html); //在html页面id=的标签里显示html内容
                  var htmlDiv ="";
                  htmlDiv +="<ul class=\"pagination pagination-sm\"><li><a href=\"#\">"+"&laquo;"+"</a></li>    <li><a href=\"#\">1</a></li>\n" +
                      "<li><a href=\"#\">2</a></li>\n" +
                      "<li><a href=\"#\">3</a></li>\n" +
                      "<li><a href=\"#\">4</a></li>\n" +
                      "<li><a href=\"#\">...</a></li>\n" +
                      "<li><a href=\"#\">末页</a></li>\n" +
                      "<li><a href=\"#\">&raquo;</a></li></ul>";
                  $("#showPage").html(htmlDiv);
              });

      }
      //        //保存表的名字(修改了input的值
      ////        document.getElementById("tablename").value=value;
      //        //清空展示区的内容来展示新内容
      ////        $("#tb_form").html("");
      //
      ////处理点击表名字法ajax请求从而获得后台数据库中的内容
      //
      ////        window.onload = function() {
      //
      ////            document.getElementById('tablename').onchange = function() {
      //
      //                //1、获取到xmlhttprequest 对象
      //        var xmlhttp = xmlhttprequest();
      //                //2、xmlhttprequest的响应事件
      //        xmlhttp.onreadystatechange = function() {
      //            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
      //                        //5、操作获取到的信息
      //                        //5.1ajax返回的数据,转换为javascript对象
      //                        // 5.1.1 利用eval实现转换
      //                var ajaxResult = eval("(" + xmlhttp.responseText + ")");
      //                        //5.1.2 利用jquery的方法,对于jQuery.parseJSON(),键值必须为双引号
      //                        /* var ajaxResult = jQuery.parseJSON(xmlhttp.responseText); */
      //                alert(ajaxResult);
      //                        //5.2获取JavaScript对象的属性
      ////                var age = ajaxResult.age;
      ////                var name = ajaxResult.name;
      //
      //                        //5.3获取到页面的DIV,并设置内容
      //                var mydiv = document.getElementById("myAjax");
      ////                mydiv.innerHTML = "name:" + name + "," + "age:" + age;
      //            }
      //        }
      //                //3、准备获取ajax请求
      //                //3.1 对于get请求,带参数的方式,直接在open函数的请求地址带上参数
      //        xmlhttp.open("POST", "TableServerlet", true);
      //                //4、发送ajax请求
      //        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
      //        xmlhttp.send("tablename=\'"+value+ "\'");
      //
      ////            };
      //    }
    //选择数据清洗策略的下拉框展示
  </script>

</head>
<body style="overflow: hidden">
<div>
  <!--BEGIN BACK TO TOP-->
  <a id="totop" href="#"><i class="fa fa-angle-up"></i></a>
  <!--END BACK TO TOP-->
  <jsp:include page="static/headerInfo.jsp"/>
  <div id="wrapper">

  <jsp:include page="static/leftNav_one.jsp"/>
    <!--BEGIN PAGE WRAPPER-->
    <div id="page-wrapper">
      <!--BEGIN TITLE & BREADCRUMB PAGE-->
      <div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
        <div class="page-header pull-left">
          <div class="page-title">
            <font color="#808080">数据清洗</font></div>
        </div>
        <ol class="breadcrumb page-breadcrumb pull-right">
          <li><i class="fa fa-home"></i>&nbsp;<a href="dashboard.html">Home</a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
          <li class="hidden"><a href="#">数据清洗</a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
          <li class="active">数据清洗</li>
        </ol>
        <div class="clearfix">
        </div>
      </div>
      <!--END TITLE & BREADCRUMB PAGE-->

      <!--BEGIN CONTENT-->
      <div class="page-content">
        <div class="content">
          <div class="row">

            <div class="col-md-4">
              <div class="widget">
                <div class="whead">
                  <h6><i class="fa fa-cloud"></i> 请选择要操作的网站</h6>
                  <a href="#" class="btn btn-sm btn-default">Kaydet</a>
                  <a href="#" class="btn btn-sm btn-success">İptal</a>
                </div>
                <div class="wbody">
                  <td>选择网站</td>
                  <td>
                    <select name="websites" id="websites" onchange="onchange_websites()">
                      <option value="bbs_china" selected>中华网</option><%--bbs_china--%>
                      <option value="bbs_mop">猫扑网</option><%--bbs_mop--%>
                      <option value="bbs_news">新华网</option><%--bbs_news--%>
                      <option value="bbs_people">强国论坛</option><%--bbs_people--%>
                      <option value="bbs_sohu">搜狐BBS</option><%--bbs_sohu--%>
                      <option value="bbs_tianya">天涯BBS</option><%--bbs_tianya--%>
                      <option value="bbs_xici">西祠胡同</option><%--bbs_xici--%>
                      <option value="blog_163">博客163</option><%--blog_163-->
                                            <option value="blog_sina">新浪微博</option><%--blog_sina--%>
                      <option value="blogchina">博客中国</option><%--blogchina--%>
                      <option value="douban">豆瓣</option><%--douban--%>
                      <option value="kdnet">凯迪网络</option><%--kdnet--%>
                      <option value="moe">教育部</option><%--moe--%>
                      <%--<option value="sanqin">三秦网</option>&lt;%&ndash;sanqin&ndash;%&gt;--%>
                      <%--<option value="news_sina">新浪新闻</option>&lt;%&ndash;news_sina&ndash;%&gt;--%>
                      <%--<option value="people_news">人民新闻</option>&lt;%&ndash;people_news&ndash;%&gt;--%>
                      <%--<option value="weibo">微博</option>&lt;%&ndash;people_news&ndash;%&gt;--%>
                      <option value="xinhua_news">新华新闻</option><%--xinhua_news--%>
                      <%--<option value="xinwen110">社会新闻</option>&lt;%&ndash;xiwenn110&ndash;%&gt;--%>
                    </select>
                  </td>
                  <!-- Button trigger modal -->
                  <%--<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">--%>
                  <%--Launch demo modal--%>
                  <%--</button>--%>

                  <!-- Modal -->
                  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                      <div class="modal-content">
                        <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                          <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                        </div>
                        <div class="modal-body">
                          ...
                        </div>
                        <div class="modal-footer">
                          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                          <button type="button" class="btn btn-primary">Save changes</button>
                        </div>
                      </div>
                    </div>
                  </div>
                  <br/>
                  <br/>
                  <br/>



                  <fieldset>
                    <h6><i class="fa fa-cloud"> </i>请选择要操作的数据表</h6>

                    <div id="btn_bbs_china">
                      <a href="javascript:display('bbs_china_author')" class="btn btn-success tipUst" original-title="Lorem ipsum dolar sit amed">bbs_china_author</a>
                      <a href="javascript:display('bbs_china_comment')" class="btn btn-success tipAlt" original-title="Lorem ipsum dolar sit amed">bbs_china_comment</a>
                      <a href="javascript:display('bbs_china_post')" class="btn btn-success tipSol" original-title="Lorem ipsum dolar sit amed">bbs_china_post</a>
                    </div>
                    <div id="btn_bbs_mop">
                      <a href="javascript:display('bbs_mop_author')" class="btn btn-success tipUst" original-title="Lorem ipsum dolar sit amed">bbs_mop_author</a>
                      <a href="javascript:display('bbs_mop_comment')" class="btn btn-success tipAlt" original-title="Lorem ipsum dolar sit amed">bbs_mop_comment</a>
                      <a href="javascript:display('bbs_mop_post')" class="btn btn-success tipSol" original-title="Lorem ipsum dolar sit amed">bbs_mop_post</a>
                      <a href="javascript:display('bbs_mop_fans')" class="btn btn-success tipSag" original-title="Lorem ipsum dolar sit amed">bbs_mop_fans</a>
                    </div>
                    <div id="btn_bbs_news">
                      <a href="javascript:display('bbs_news_author')" class="btn btn-success tipUst" original-title="Lorem ipsum dolar sit amed">bbs_news_author</a>
                      <a href="javascript:display('bbs_news_comment')" class="btn btn-success tipAlt" original-title="Lorem ipsum dolar sit amed">bbs_news_comment</a>
                      <a href="javascript:display('bbs_news_post')" class="btn btn-success tipSol" original-title="Lorem ipsum dolar sit amed">bbs_news_post</a>
                      <a href="javascript:display('bbs_news_fans')" class="btn btn-success tipSag" original-title="Lorem ipsum dolar sit amed">bbs_news_fans</a>
                    </div>
                    <div id="btn_bbs_people">
                      <a href="javascript:display('bbs_people_author')" class="btn btn-success tipUst" original-title="Lorem ipsum dolar sit amed">bbs_people_author</a>
                      <a href="javascript:display('bbs_people_post')" class="btn btn-success tipSol" original-title="Lorem ipsum dolar sit amed">bbs_people_post</a>
                    </div>
                    <div id="btn_bbs_sohu">
                      <a href="javascript:display('bbs_sohu_author')" class="btn btn-success tipUst" original-title="Lorem ipsum dolar sit amed">bbs_sohu_author</a>
                      <a href="javascript:display('bbs_sohu_comment')" class="btn btn-success tipAlt" original-title="Lorem ipsum dolar sit amed">bbs_sohu_comment</a>
                      <a href="javascript:display('bbs_sohu_post')" class="btn btn-success tipSol" original-title="Lorem ipsum dolar sit amed">bbs_sohu_post</a>
                      <a href="javascript:display('bbs_sohu_fans')" class="btn btn-success tipSag" original-title="Lorem ipsum dolar sit amed">bbs_sohu_fans</a>
                    </div>
                    <div id="btn_bbs_tianya">
                      <a href="javascript:display('bbs_tianya_author')" class="btn btn-success tipUst" original-title="Lorem ipsum dolar sit amed">bbs_tianya_author</a>
                      <a href="javascript:display('bbs_tianya_fans')" class="btn btn-success tipAlt" original-title="Lorem ipsum dolar sit amed">bbs_tianya_fans</a>
                      <a href="javascript:display('bbs_tianya_post')" class="btn btn-success tipSol" original-title="Lorem ipsum dolar sit amed">bbs_tianya_post</a>
                    </div>
                    <div id="btn_bbs_xici">
                      <a href="javascript:display('bbs_xici_comment')" class="btn btn-success tipAlt" original-title="Lorem ipsum dolar sit amed">bbs_xici_comment</a>
                      <a href="javascript:display('bbs_xici_post')" class="btn btn-success tipSol" original-title="Lorem ipsum dolar sit amed">bbs_xici_post</a>
                    </div>
                    <div id="btn_blog_163">
                      <a href="javascript:display('blog_163_author')" class="btn btn-success tipUst" original-title="Lorem ipsum dolar sit amed">blog_163_author</a>
                      <a href="javascript:display('blog_163_comment')" class="btn btn-success tipAlt" original-title="Lorem ipsum dolar sit amed">blog_163_comment</a>
                      <a href="javascript:display('blog_163_post')" class="btn btn-success tipSol" original-title="Lorem ipsum dolar sit amed">blog_163_post</a>
                    </div>
                    <div id="btn_blog_sina">
                      <a href="javascript:display('blog_sina_author')" class="btn btn-success tipUst" original-title="Lorem ipsum dolar sit amed">blog_sina_author</a>
                      <a href="javascript:display('blog_sina_comment')" class="btn btn-success tipAlt" original-title="Lorem ipsum dolar sit amed">blog_sina_comment</a>
                      <a href="javascript:display('blog_sina_post')" class="btn btn-success tipSol" original-title="Lorem ipsum dolar sit amed">blog_sina_post</a>
                    </div>
                    <div id="btn_blogchina">
                      <a href="javascript:display('blogchina_author')" class="btn btn-success tipUst" original-title="Lorem ipsum dolar sit amed">blogchina_author</a>
                      <%--<a href="javascript:display('blogchina_comment')" class="btn btn-success tipAlt" original-title="Lorem ipsum dolar sit amed">blogchina_comment</a>--%>
                      <%--<a href="javascript:display('blogchina_blog')" class="btn btn-success tipSol" original-title="Lorem ipsum dolar sit amed">blogchina_blog</a>--%>
                    </div>
                    <div id="btn_douban">
                      <a href="javascript:display('douban_group_author')" class="btn btn-success tipUst" original-title="Lorem ipsum dolar sit amed">douban_group_author</a>
                      <a href="javascript:display('douban_group_comment')" class="btn btn-success tipAlt" original-title="Lorem ipsum dolar sit amed">douban_group_comment</a>
                      <a href="javascript:display('douban_group_group')" class="btn btn-success tipSol" original-title="Lorem ipsum dolar sit amed">douban_group_group</a>
                      <a href="javascript:display('douban_group_post')" class="btn btn-success tipSag" original-title="Lorem ipsum dolar sit amed">douban_group_post</a>
                    </div>
                    <div id="btn_kdnet">
                      <a href="javascript:display('kdnet_author')" class="btn btn-success tipUst" original-title="Lorem ipsum dolar sit amed">kdnet_author</a>
                      <a href="javascript:display('kdnet_comment')" class="btn btn-success tipAlt" original-title="Lorem ipsum dolar sit amed">kdnet_comment</a>
                      <a href="javascript:display('kdnet_comment')" class="btn btn-success tipSol" original-title="Lorem ipsum dolar sit amed">kdnet_post</a>
                      <a href="javascript:display('kdnet_fans')" class="btn btn-success tipSag" original-title="Lorem ipsum dolar sit amed">kdnet_fans</a>
                    </div>
                    <div id="btn_moe">
                      <a href="javascript:display('moe_data')" class="btn btn-success tipUst" original-title="Lorem ipsum dolar sit amed">moe_data</a>
                      <a href="javascript:display('moe_declaration')" class="btn btn-success tipAlt" original-title="Lorem ipsum dolar sit amed">moe_declaration</a>
                      <a href="javascript:display('moe_news')" class="btn btn-success tipSol" original-title="Lorem ipsum dolar sit amed">moe_news</a>
                    </div>
                    <%--<div id="btn_sanqin">--%>
                    <%--<a href="javascript:display('news_sanqin')" class="btn btn-success tipUst" original-title="Lorem ipsum dolar sit amed">news_sanqin</a>--%>
                    <%--</div>--%>
                    <%--<div id="btn_news_sina">--%>
                    <%--<a href="javascript:display('news_sina')" class="btn btn-success tipUst" original-title="Lorem ipsum dolar sit amed">news_sina</a>--%>
                    <%--</div>--%>
                    <%--<div id="btn_weibo">--%>
                    <%--<a href="javascript:display('weibo_author')" class="btn btn-success tipUst" original-title="Lorem ipsum dolar sit amed">weibo_author</a>--%>
                    <%--<a href="javascript:display('weibo_comment')" class="btn btn-success tipAlt" original-title="Lorem ipsum dolar sit amed">weibo_comment</a>--%>
                    <%--<a href="javascript:display('weibo_comment')" class="btn btn-success tipSol" original-title="Lorem ipsum dolar sit amed">weibo_content</a>--%>
                    <%--</div>--%>
                    <%--<div id="btn_people_news">--%>
                    <%--<a href="javascript:display('people_news')" class="btn btn-success tipUst" original-title="Lorem ipsum dolar sit amed">people_news</a>--%>
                    <%--</div>--%>
                    <div id="btn_xinhua_news">
                      <a href="javascript:display('xinhua_news')" class="btn btn-success tipUst" original-title="Lorem ipsum dolar sit amed">xinhua_news</a>
                    </div>
                    <%--<div id="btn_xinwen110">--%>
                    <%--<a href="javascript:display('xinwen110_news')" class="btn btn-success tipUst" original-title="Lorem ipsum dolar sit amed">xinwen110_news</a>--%>
                    <%--</div>--%>
                  </fieldset>
                  <%--<div>--%>
                    <%--<h6><i class="fa fa-cloud"> </i>请选择要操作的表的版本并开始清洗</h6>--%>
                    <%--<div>该表对应的所有版本</div><div class="version"></div>--%>
                    <%--<div>新建版本:<input id="newTable" placeholder="请输入新的版本名…"></input>&nbsp;&nbsp;<button type="button" class="btn btn-primary dropdown-toggle" id="ANewTable" >新建版本</button></div>--%>
                  <%--</div>--%>
                </div>
              </div>
            </div>
            <div class="col-md-5">
              <div class="widget">
                <div class="whead">
                  <h6><i class="fa fa-cloud"></i> 数据清洗策略的选择</h6>
                  <div class="btn-group">
                    <a href="" class="btn btn-primary" class="btn btn-default" data-message=""><i class="fa fa-share"></i> 撤销</a>
                    <a href="" class="btn btn-info" data-message=""><i class="fa fa-plus"></i> 保存</a>
                    <a href="" class="btn btn-default" data-message=""><i class="fa fa-home"></i> 生成版本</a>
                  </div>
                </div>
                <div class="wbody">
                  <table class="table">
                    <tr>
                      <td>当前操作的字段:</td>
                      <td >
                        <button type="button" class="btn btn-primary dropdown-toggle" id="currentHead">
                          点击表格上的字段名开始操作
                        </button>
                      </td>
                    </tr>
                    <tr>
                      <td>选择脏数据类型:</td>
                      <td>
                        <%--<select class="select form-control" name="dirtyType" id="dirtyType" onchange="dirtyTypeChange()">--%>
                          <%--<option value="miss">缺失值</option>--%>
                          <%--<option value="stringFalse">字符串错误</option>--%>
                          <%--<option value="address">地址格式化</option>--%>
                          <%--<option value="data">时间格式化</option>--%>
                          <%--<option value="split">拆分为多列</option>--%>
                          <%--<option value="combine">合并两列</option>--%>
                        <%--</select>--%>
                          <div class="btn-group">
                            <button type="button" class="btn btn-primary dropdown-toggle"
                                    data-toggle="dropdown">
                              缺失值 <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu">
                              <li><a href="#" onclick="loseData(1)">填充为NULL</a></li>
                              <li><a href="#" onclick="loseData(2)">填充均值</a></li>
                              <li><a href="#" onclick="loseData(3)">填充频繁值</a></li>
                              <li><a href="#" onclick="loseData(3)">填充最大值</a></li>
                              <li><a href="#" onclick="loseData(3)">填充最小值</a></li>
                              <li class="divider"></li>
                              <li><a href="#" onclick="loseData(4)">填充特定的值</a></li>
                            </ul>
                          </div>
                          <div class="btn-group">
                            <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">字符串错误
                              <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu">
                              <li><a href="#">删除该列</a></li>
                              <li><a href="#">删除首尾多余空格</a></li>
                              <li><a href="#">将字符串中间的空格改为一个</a></li>
                              <li><a href="#">修改某特定字符串</a></li>
                            </ul>
                          </div>
                          <div class="btn-group">
                            <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">行操作
                              <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu">
                              <li><a href="#">删除某字段包含特定字符串的所有行</a></li>
                              <li><a href="#">删除重复的行</a></li>
                            </ul>
                          </div>
                          <div class="btn-group">
                            <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">日期格式
                              <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu">
                              <li><a href="#">xxxx-xx-xx xx:xx:xx</a></li>
                              <li><a href="#">xxxx年xx月xx日 xx时xx分xx秒</a></li>
                            </ul>
                          </div>
                          <div class="btn-group">
                            <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">列的合并与拆分
                              <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu" role="menu">
                              <li><a href="#">合并两列</a></li>
                              <li><a href="#">拆分为两列</a></li>
                            </ul>
                          </div>
                      </td>

                      <%--<td><input type="text" class="form-control"/></td>--%>
                    </tr>


                    <tr>
                      <td>字段中要替换的子字符串</td>
                      <td><input type="text" class="form-control autocomplete"/></td>
                    </tr>
                    <tr>
                      <td>替换为</td>
                      <td><input type="text"  class="form-control autocomplete" data-source="etldemodata/autocomplete.json"/></td>
                    </tr>
                    <%--<tr>--%>
                      <%--<td>Chechbox</td>--%>
                      <%--<td>--%>
                        <%--<input type="checkbox"  class="checkboxcustom" value="yes" name="a" />--%>
                        <%--<input type="checkbox"  class="checkboxcustom1" value="yes" name="b" />--%>
                        <%--<input type="checkbox"  class="checkboxcustom2" value="yes" name="c" />--%>
                      <%--</td>--%>
                    <%--</tr>--%>
                  </table>
                </div>
              </div>
            </div>
            <div class="col-md-3">
              <div class="widget">
                <div class="whead">
                  <h6><i class="fa fa-cloud"></i>字段中频繁项统计图</h6>
                  <a href="#" class="btn btn-sm btn-default">Kaydet</a>
                  <a href="#" class="btn btn-sm btn-success">İptal</a>
                </div>
                <div class="wbody" id="stats">
                  <%--<div class="progress">--%>
                    <%--<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">--%>
                      <%--<span class="sr-only">40% Complete (success)</span>--%>
                    <%--</div>--%>
                  <%--</div>--%>
                  <%--<div class="progress">--%>
                    <%--<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">--%>
                      <%--<span class="sr-only">20% Complete</span>--%>
                    <%--</div>--%>
                  <%--</div>--%>
                  <%--<div class="progress">--%>
                    <%--<div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">--%>
                      <%--<span class="sr-only">60% Complete (warning)</span>--%>
                    <%--</div>--%>
                  <%--</div>--%>
                  <%--<div class="progress">--%>
                    <%--<div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">--%>
                      <%--<span class="sr-only">80% Complete</span>--%>
                    <%--</div>--%>
                  <%--</div>--%>

                </div>
              </div>
            </div>

            <%--这里展示返回的数据表--%>
            <form method="post" action="tbform" name="tbform1" id="tbform1">
              <%--接收用户选择的表名--%>
              <input type="hidden" name="tablename" id ="tablename" value="" />
                <div class="col-md-12" >
                <div class="widget">
                <div class="whead" style="height:50px;" >
                  <h6><i class="fa fa-cloud"></i>数据展示与清洗</h6>
                  <div class="showPage" id="showPage" style="height:50px; float:right; margin: 0;"></div>
                </div>

              <div class="col-md-12" style="width:99.5%; height:420px; overflow:scroll;">
                <div class="widget">

                  <table cellpadding="0" cellspacing="0" border="0" height="500px" class="displayTable" id="displayTable" width="100%" data-table-ajax="">
                  </table>
                  <div class="clearfix"></div>
                </div>

                <div class="clearfix"></div>
                  </div>
                </div>
                </div>
            </form>

            <div class="clearfix"></div>
            <div class="ara" id="ara"></div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <script>
      //选择不同网站时显示不同的对应数据表
      function onchange_websites() {
          var obj = document.getElementById("websites").value;
          switch (obj)
          {
              case "bbs_china":
                  document.getElementById("btn_bbs_china").style.display="inline";
                  document.getElementById("btn_bbs_mop").style.display="none";
                  document.getElementById("btn_bbs_news").style.display="none";
                  document.getElementById("btn_bbs_people").style.display="none";
                  document.getElementById("btn_bbs_sohu").style.display="none";
                  document.getElementById("btn_bbs_tianya").style.display="none";
                  document.getElementById("btn_bbs_xici").style.display="none";
                  document.getElementById("btn_blog_163").style.display="none";
                  document.getElementById("btn_blog_sina").style.display="none";
                  document.getElementById("btn_blogchina").style.display="none";
                  document.getElementById("btn_douban").style.display="none";
                  document.getElementById("btn_kdnet").style.display="none";
                  document.getElementById("btn_moe").style.display="none";
//                document.getElementById("btn_sanqin").style.display="none";
//                document.getElementById("btn_news_sina").style.display="none";
//                document.getElementById("btn_people_news").style.display="none";
//                document.getElementById("btn_weibo").style.display="none";
                  document.getElementById("btn_xinhua_news").style.display="none";
//                document.getElementById("btn_xinwen110").style.display="none";
                  break;
              case "bbs_mop":
                  document.getElementById("btn_bbs_china").style.display="none";
                  document.getElementById("btn_bbs_mop").style.display="inline";
                  document.getElementById("btn_bbs_news").style.display="none";
                  document.getElementById("btn_bbs_people").style.display="none";
                  document.getElementById("btn_bbs_sohu").style.display="none";
                  document.getElementById("btn_bbs_tianya").style.display="none";
                  document.getElementById("btn_bbs_xici").style.display="none";
                  document.getElementById("btn_blog_163").style.display="none";
                  document.getElementById("btn_blog_sina").style.display="none";
                  document.getElementById("btn_blogchina").style.display="none";
                  document.getElementById("btn_douban").style.display="none";
                  document.getElementById("btn_kdnet").style.display="none";
                  document.getElementById("btn_moe").style.display="none";
//                document.getElementById("btn_sanqin").style.display="none";
//                document.getElementById("btn_news_sina").style.display="none";
//                document.getElementById("btn_people_news").style.display="none";
//                document.getElementById("btn_weibo").style.display="none";
                  document.getElementById("btn_xinhua_news").style.display="none";
//                document.getElementById("btn_xinwen110").style.display="none";
                  break;
              case "bbs_news":
                  document.getElementById("btn_bbs_china").style.display="none";
                  document.getElementById("btn_bbs_mop").style.display="none";
                  document.getElementById("btn_bbs_news").style.display="inline";
                  document.getElementById("btn_bbs_people").style.display="none";
                  document.getElementById("btn_bbs_sohu").style.display="none";
                  document.getElementById("btn_bbs_tianya").style.display="none";
                  document.getElementById("btn_bbs_xici").style.display="none";
                  document.getElementById("btn_blog_163").style.display="none";
                  document.getElementById("btn_blog_sina").style.display="none";
                  document.getElementById("btn_blogchina").style.display="none";
                  document.getElementById("btn_douban").style.display="none";
                  document.getElementById("btn_kdnet").style.display="none";
                  document.getElementById("btn_moe").style.display="none";
//                document.getElementById("btn_sanqin").style.display="none";
//                document.getElementById("btn_news_sina").style.display="none";
//                document.getElementById("btn_people_news").style.display="none";
//                document.getElementById("btn_weibo").style.display="none";
                  document.getElementById("btn_xinhua_news").style.display="none";
//                document.getElementById("btn_xinwen110").style.display="none";
                  break;
              case "bbs_people":
                  document.getElementById("btn_bbs_china").style.display="none";
                  document.getElementById("btn_bbs_mop").style.display="none";
                  document.getElementById("btn_bbs_news").style.display="none";
                  document.getElementById("btn_bbs_people").style.display="inline";
                  document.getElementById("btn_bbs_sohu").style.display="none";
                  document.getElementById("btn_bbs_tianya").style.display="none";
                  document.getElementById("btn_bbs_xici").style.display="none";
                  document.getElementById("btn_blog_163").style.display="none";
                  document.getElementById("btn_blog_sina").style.display="none";
                  document.getElementById("btn_blogchina").style.display="none";
                  document.getElementById("btn_douban").style.display="none";
                  document.getElementById("btn_kdnet").style.display="none";
                  document.getElementById("btn_moe").style.display="none";
//                document.getElementById("btn_sanqin").style.display="none";
//                document.getElementById("btn_news_sina").style.display="none";
//                document.getElementById("btn_people_news").style.display="none";
//                document.getElementById("btn_weibo").style.display="none";
                  document.getElementById("btn_xinhua_news").style.display="none";
//                document.getElementById("btn_xinwen110").style.display="none";
                  break;
              case "bbs_sohu":
                  document.getElementById("btn_bbs_china").style.display="none";
                  document.getElementById("btn_bbs_mop").style.display="none";
                  document.getElementById("btn_bbs_news").style.display="none";
                  document.getElementById("btn_bbs_people").style.display="none";
                  document.getElementById("btn_bbs_sohu").style.display="inline";
                  document.getElementById("btn_bbs_tianya").style.display="none";
                  document.getElementById("btn_bbs_xici").style.display="none";
                  document.getElementById("btn_blog_163").style.display="none";
                  document.getElementById("btn_blog_sina").style.display="none";
                  document.getElementById("btn_blogchina").style.display="none";
                  document.getElementById("btn_douban").style.display="none";
                  document.getElementById("btn_kdnet").style.display="none";
                  document.getElementById("btn_moe").style.display="none";
//                document.getElementById("btn_sanqin").style.display="none";
//                document.getElementById("btn_news_sina").style.display="none";
//                document.getElementById("btn_people_news").style.display="none";
//                document.getElementById("btn_weibo").style.display="none";
                  document.getElementById("btn_xinhua_news").style.display="none";
//                document.getElementById("btn_xinwen110").style.display="none";
                  break;
              case "bbs_tianya":
                  document.getElementById("btn_bbs_china").style.display="none";
                  document.getElementById("btn_bbs_mop").style.display="none";
                  document.getElementById("btn_bbs_news").style.display="none";
                  document.getElementById("btn_bbs_people").style.display="none";
                  document.getElementById("btn_bbs_sohu").style.display="none";
                  document.getElementById("btn_bbs_tianya").style.display="inline";
                  document.getElementById("btn_bbs_xici").style.display="none";
                  document.getElementById("btn_blog_163").style.display="none";
                  document.getElementById("btn_blog_sina").style.display="none";
                  document.getElementById("btn_blogchina").style.display="none";
                  document.getElementById("btn_douban").style.display="none";
                  document.getElementById("btn_kdnet").style.display="none";
                  document.getElementById("btn_moe").style.display="none";
//                document.getElementById("btn_sanqin").style.display="none";
//                document.getElementById("btn_news_sina").style.display="none";
//                document.getElementById("btn_people_news").style.display="none";
//                document.getElementById("btn_weibo").style.display="none";
                  document.getElementById("btn_xinhua_news").style.display="none";
//                document.getElementById("btn_xinwen110").style.display="none";
                  break;
              case "bbs_xici":
                  document.getElementById("btn_bbs_china").style.display="none";
                  document.getElementById("btn_bbs_mop").style.display="none";
                  document.getElementById("btn_bbs_news").style.display="none";
                  document.getElementById("btn_bbs_people").style.display="none";
                  document.getElementById("btn_bbs_sohu").style.display="none";
                  document.getElementById("btn_bbs_tianya").style.display="none";
                  document.getElementById("btn_bbs_xici").style.display="inline";
                  document.getElementById("btn_blog_163").style.display="none";
                  document.getElementById("btn_blog_sina").style.display="none";
                  document.getElementById("btn_blogchina").style.display="none";
                  document.getElementById("btn_douban").style.display="none";
                  document.getElementById("btn_kdnet").style.display="none";
                  document.getElementById("btn_moe").style.display="none";
//                document.getElementById("btn_sanqin").style.display="none";
//                document.getElementById("btn_news_sina").style.display="none";
//                document.getElementById("btn_people_news").style.display="none";
//                document.getElementById("btn_weibo").style.display="none";
                  document.getElementById("btn_xinhua_news").style.display="none";
//                document.getElementById("btn_xinwen110").style.display="none";
                  break;
              case "blog_163":
                  document.getElementById("btn_bbs_china").style.display="none";
                  document.getElementById("btn_bbs_mop").style.display="none";
                  document.getElementById("btn_bbs_news").style.display="none";
                  document.getElementById("btn_bbs_people").style.display="none";
                  document.getElementById("btn_bbs_sohu").style.display="none";
                  document.getElementById("btn_bbs_tianya").style.display="none";
                  document.getElementById("btn_bbs_xici").style.display="none";
                  document.getElementById("btn_blog_163").style.display="inline";
                  document.getElementById("btn_blog_sina").style.display="none";
                  document.getElementById("btn_blogchina").style.display="none";
                  document.getElementById("btn_douban").style.display="none";
                  document.getElementById("btn_kdnet").style.display="none";
                  document.getElementById("btn_moe").style.display="none";
//                document.getElementById("btn_sanqin").style.display="none";
//                document.getElementById("btn_news_sina").style.display="none";
//                document.getElementById("btn_people_news").style.display="none";
//                document.getElementById("btn_weibo").style.display="none";
                  document.getElementById("btn_xinhua_news").style.display="none";
//                document.getElementById("btn_xinwen110").style.display="none";
                  break;
              case "blog_sina":
                  document.getElementById("btn_bbs_china").style.display="none";
                  document.getElementById("btn_bbs_mop").style.display="none";
                  document.getElementById("btn_bbs_news").style.display="none";
                  document.getElementById("btn_bbs_people").style.display="none";
                  document.getElementById("btn_bbs_sohu").style.display="none";
                  document.getElementById("btn_bbs_tianya").style.display="none";
                  document.getElementById("btn_bbs_xici").style.display="none";
                  document.getElementById("btn_blog_163").style.display="none";
                  document.getElementById("btn_blog_sina").style.display="inline";
                  document.getElementById("btn_blogchina").style.display="none";
                  document.getElementById("btn_douban").style.display="none";
                  document.getElementById("btn_kdnet").style.display="none";
                  document.getElementById("btn_moe").style.display="none";
//                document.getElementById("btn_sanqin").style.display="none";
//                document.getElementById("btn_news_sina").style.display="none";
//                document.getElementById("btn_people_news").style.display="none";
//                document.getElementById("btn_weibo").style.display="none";
                  document.getElementById("btn_xinhua_news").style.display="none";
//                document.getElementById("btn_xinwen110").style.display="none";
                  break;
              case "blogchina":
                  document.getElementById("btn_bbs_china").style.display="none";
                  document.getElementById("btn_bbs_mop").style.display="none";
                  document.getElementById("btn_bbs_news").style.display="none";
                  document.getElementById("btn_bbs_people").style.display="none";
                  document.getElementById("btn_bbs_sohu").style.display="none";
                  document.getElementById("btn_bbs_tianya").style.display="none";
                  document.getElementById("btn_bbs_xici").style.display="none";
                  document.getElementById("btn_blog_163").style.display="none";
                  document.getElementById("btn_blog_sina").style.display="none";
                  document.getElementById("btn_blogchina").style.display="inline";
                  document.getElementById("btn_douban").style.display="none";
                  document.getElementById("btn_kdnet").style.display="none";
                  document.getElementById("btn_moe").style.display="none";
//                document.getElementById("btn_sanqin").style.display="none";
//                document.getElementById("btn_news_sina").style.display="none";
//                document.getElementById("btn_people_news").style.display="none";
//                document.getElementById("btn_weibo").style.display="none";
                  document.getElementById("btn_xinhua_news").style.display="none";
//                document.getElementById("btn_xinwen110").style.display="none";
                  break;
              case "douban":
                  document.getElementById("btn_bbs_china").style.display="none";
                  document.getElementById("btn_bbs_mop").style.display="none";
                  document.getElementById("btn_bbs_news").style.display="none";
                  document.getElementById("btn_bbs_people").style.display="none";
                  document.getElementById("btn_bbs_sohu").style.display="none";
                  document.getElementById("btn_bbs_tianya").style.display="none";
                  document.getElementById("btn_bbs_xici").style.display="none";
                  document.getElementById("btn_blog_163").style.display="none";
                  document.getElementById("btn_blog_sina").style.display="none";
                  document.getElementById("btn_blogchina").style.display="none";
                  document.getElementById("btn_douban").style.display="inline";
                  document.getElementById("btn_kdnet").style.display="none";
                  document.getElementById("btn_moe").style.display="none";
//                document.getElementById("btn_sanqin").style.display="none";
//                document.getElementById("btn_news_sina").style.display="none";
//                document.getElementById("btn_people_news").style.display="none";
//                document.getElementById("btn_weibo").style.display="none";
                  document.getElementById("btn_xinhua_news").style.display="none";
//                document.getElementById("btn_xinwen110").style.display="none";
                  break;
              case "kdnet":
                  document.getElementById("btn_bbs_china").style.display="none";
                  document.getElementById("btn_bbs_mop").style.display="none";
                  document.getElementById("btn_bbs_news").style.display="none";
                  document.getElementById("btn_bbs_people").style.display="none";
                  document.getElementById("btn_bbs_sohu").style.display="none";
                  document.getElementById("btn_bbs_tianya").style.display="none";
                  document.getElementById("btn_bbs_xici").style.display="none";
                  document.getElementById("btn_blog_163").style.display="none";
                  document.getElementById("btn_blog_sina").style.display="none";
                  document.getElementById("btn_blogchina").style.display="none";
                  document.getElementById("btn_douban").style.display="none";
                  document.getElementById("btn_kdnet").style.display="inline";
                  document.getElementById("btn_moe").style.display="none";
//                document.getElementById("btn_sanqin").style.display="none";
//                document.getElementById("btn_news_sina").style.display="none";
//                document.getElementById("btn_people_news").style.display="none";
//                document.getElementById("btn_weibo").style.display="none";
                  document.getElementById("btn_xinhua_news").style.display="none";
//                document.getElementById("btn_xinwen110").style.display="none";
                  break;
              case "moe":
                  document.getElementById("btn_bbs_china").style.display="none";
                  document.getElementById("btn_bbs_mop").style.display="none";
                  document.getElementById("btn_bbs_news").style.display="none";
                  document.getElementById("btn_bbs_people").style.display="none";
                  document.getElementById("btn_bbs_sohu").style.display="none";
                  document.getElementById("btn_bbs_tianya").style.display="none";
                  document.getElementById("btn_bbs_xici").style.display="none";
                  document.getElementById("btn_blog_163").style.display="none";
                  document.getElementById("btn_blog_sina").style.display="none";
                  document.getElementById("btn_blogchina").style.display="none";
                  document.getElementById("btn_douban").style.display="none";
                  document.getElementById("btn_kdnet").style.display="none";
                  document.getElementById("btn_moe").style.display="inline";
//                document.getElementById("btn_sanqin").style.display="none";
//                document.getElementById("btn_news_sina").style.display="none";
//                document.getElementById("btn_people_news").style.display="none";
//                document.getElementById("btn_weibo").style.display="none";
                  document.getElementById("btn_xinhua_news").style.display="none";
//                document.getElementById("btn_xinwen110").style.display="none";
                  break;
//            case "sanqin":
//                document.getElementById("btn_bbs_china").style.display="none";
//                document.getElementById("btn_bbs_mop").style.display="none";
//                document.getElementById("btn_bbs_news").style.display="none";
//                document.getElementById("btn_bbs_people").style.display="none";
//                document.getElementById("btn_bbs_sohu").style.display="none";
//                document.getElementById("btn_bbs_tianya").style.display="none";
//                document.getElementById("btn_bbs_xici").style.display="none";
//                document.getElementById("btn_blog_sina").style.display="none";
//                document.getElementById("btn_blogchina").style.display="none";
//                document.getElementById("btn_douban").style.display="none";
//                document.getElementById("btn_kdnet").style.display="none";
//                document.getElementById("btn_moe").style.display="none";
//                document.getElementById("btn_sanqin").style.display="inline";
//                document.getElementById("btn_news_sina").style.display="none";
//                document.getElementById("btn_people_news").style.display="none";
//                document.getElementById("btn_weibo").style.display="none";
//                document.getElementById("btn_xinhua_news").style.display="none";
//                document.getElementById("btn_xinwen110").style.display="none";
//                break;
//            case "news_sina":
//                document.getElementById("btn_bbs_china").style.display="none";
//                document.getElementById("btn_bbs_mop").style.display="none";
//                document.getElementById("btn_bbs_news").style.display="none";
//                document.getElementById("btn_bbs_people").style.display="none";
//                document.getElementById("btn_bbs_sohu").style.display="none";
//                document.getElementById("btn_bbs_tianya").style.display="none";
//                document.getElementById("btn_bbs_xici").style.display="none";
//                document.getElementById("btn_blog_sina").style.display="none";
//                document.getElementById("btn_blogchina").style.display="none";
//                document.getElementById("btn_douban").style.display="none";
//                document.getElementById("btn_kdnet").style.display="none";
//                document.getElementById("btn_moe").style.display="none";
//                document.getElementById("btn_sanqin").style.display="none";
//                document.getElementById("btn_news_sina").style.display="inline";
//                document.getElementById("btn_people_news").style.display="none";
//                document.getElementById("btn_weibo").style.display="none";
//                document.getElementById("btn_xinhua_news").style.display="none";
//                document.getElementById("btn_xinwen110").style.display="none";
//                break;
//            case "people_news":
//                document.getElementById("btn_bbs_china").style.display="none";
//                document.getElementById("btn_bbs_mop").style.display="none";
//                document.getElementById("btn_bbs_news").style.display="none";
//                document.getElementById("btn_bbs_people").style.display="none";
//                document.getElementById("btn_bbs_sohu").style.display="none";
//                document.getElementById("btn_bbs_tianya").style.display="none";
//                document.getElementById("btn_bbs_xici").style.display="none";
//                document.getElementById("btn_blog_sina").style.display="none";
//                document.getElementById("btn_blogchina").style.display="none";
//                document.getElementById("btn_douban").style.display="none";
//                document.getElementById("btn_kdnet").style.display="none";
//                document.getElementById("btn_moe").style.display="none";
//                document.getElementById("btn_sanqin").style.display="none";
//                document.getElementById("btn_news_sina").style.display="none";
//                document.getElementById("btn_people_news").style.display="inline";
//                document.getElementById("btn_weibo").style.display="none";
//                document.getElementById("btn_xinhua_news").style.display="none";
//                document.getElementById("btn_xinwen110").style.display="none";
//                break;
//            case "weibo":
//                document.getElementById("btn_bbs_china").style.display="none";
//                document.getElementById("btn_bbs_mop").style.display="none";
//                document.getElementById("btn_bbs_news").style.display="none";
//                document.getElementById("btn_bbs_people").style.display="none";
//                document.getElementById("btn_bbs_sohu").style.display="none";
//                document.getElementById("btn_bbs_tianya").style.display="none";
//                document.getElementById("btn_bbs_xici").style.display="none";
//                document.getElementById("btn_blog_sina").style.display="none";
//                document.getElementById("btn_blogchina").style.display="none";
//                document.getElementById("btn_douban").style.display="none";
//                document.getElementById("btn_kdnet").style.display="none";
//                document.getElementById("btn_moe").style.display="none";
//                document.getElementById("btn_sanqin").style.display="none";
//                document.getElementById("btn_news_sina").style.display="none";
//                document.getElementById("btn_people_news").style.display="none";
//                document.getElementById("btn_weibo").style.display="inline";
//                document.getElementById("btn_xinhua_news").style.display="none";
//                document.getElementById("btn_xinwen110").style.display="none";
//                break;
              case "xinhua_news":
                  document.getElementById("btn_bbs_china").style.display="none";
                  document.getElementById("btn_bbs_mop").style.display="none";
                  document.getElementById("btn_bbs_news").style.display="none";
                  document.getElementById("btn_bbs_people").style.display="none";
                  document.getElementById("btn_bbs_sohu").style.display="none";
                  document.getElementById("btn_bbs_tianya").style.display="none";
                  document.getElementById("btn_bbs_xici").style.display="none";
                  document.getElementById("btn_blog_163").style.display="none";
                  document.getElementById("btn_blog_sina").style.display="none";
                  document.getElementById("btn_blogchina").style.display="none";
                  document.getElementById("btn_douban").style.display="none";
                  document.getElementById("btn_kdnet").style.display="none";
                  document.getElementById("btn_moe").style.display="none";
//                document.getElementById("btn_sanqin").style.display="none";
//                document.getElementById("btn_news_sina").style.display="none";
//                document.getElementById("btn_people_news").style.display="none";
//                document.getElementById("btn_weibo").style.display="none";
                  document.getElementById("btn_xinhua_news").style.display="inline";
//                document.getElementById("btn_xinwen110").style.display="none";
                  break;
//            case "xinwen110":
//                document.getElementById("btn_bbs_china").style.display="none";
//                document.getElementById("btn_bbs_mop").style.display="none";
//                document.getElementById("btn_bbs_news").style.display="none";
//                document.getElementById("btn_bbs_people").style.display="none";
//                document.getElementById("btn_bbs_sohu").style.display="none";
//                document.getElementById("btn_bbs_tianya").style.display="none";
//                document.getElementById("btn_bbs_xici").style.display="none";
//                document.getElementById("btn_blog_sina").style.display="none";
//                document.getElementById("btn_blogchina").style.display="none";
//                document.getElementById("btn_douban").style.display="none";
//                document.getElementById("btn_kdnet").style.display="none";
//                document.getElementById("btn_moe").style.display="none";
//                document.getElementById("btn_sanqin").style.display="none";
//                document.getElementById("btn_news_sina").style.display="none";
//                document.getElementById("btn_people_news").style.display="none";
//                document.getElementById("btn_weibo").style.display="none";
//                document.getElementById("btn_xinhua_news").style.display="none";
//                document.getElementById("btn_xinwen110").style.display="inline";
//                break;
              default:
                  alert("error");
          }
      }
  </script>

        <!--END CONTENT-->


        <!--BEGIN FOOTER-->

        <!--END FOOTER-->
      </div>
      <!--END PAGE WRAPPER-->



<script src="js/js1/echarts.min.js"></script>
  <script>
      jQuery(function(){
          amazonmenu.init({
              menuid: 'mysidebarmenu'
          })
      })
  </script>
</body>
</html>
