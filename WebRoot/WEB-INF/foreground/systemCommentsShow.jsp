<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html class="no-js">
    
    <head>
        <base href="<%=basePath%>foreground/"/>
        <title>评论列表 - 舆情对象评论</title>
        <!-- Bootstrap -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
        <link href="vendors/easypiechart/jquery.easy-pie-chart.css" rel="stylesheet" media="screen">
        <link href="assets/styles.css" rel="stylesheet" media="screen">
		<link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/comment.css">
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        <script src="vendors/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    </head>
    
    <body>

    <!-- 引入头模板 -->
    <jsp:include page="/static/fore_header.jsp"/>

    <div class="container-fluid" style="padding-top:100px">

        <h1>
            <center>
                <a href="<%=basePath%>retrivalResults?flag=all&name=十九大召开" style="font-size: 20px;">${objectEntity.name}</a>
            </center>
            <input type="hidden" id="objId" value="${objectEntity.objectId}"/>
            <input type="hidden" value="<%=basePath%>" id="basePath"/>
        </h1><br/><br/>
                        <li class="" style="padding-left:370px">							
							<a>浏览量: ${objectEntity.scanNumber} </a>&nbsp;&nbsp;
							<a>点赞量: ${objectEntity.likeNumber} </a>&nbsp;&nbsp;
							<a>收藏量: ${objectEntity.collectNumber}</a>&nbsp;&nbsp;
							<a>判别真: ${objectEntity.trueNumber} </a>&nbsp;&nbsp;
							<a>判别假: ${objectEntity.falseNumber}</a>&nbsp;&nbsp;
							<a>留言量: ${objectEntity.commentNumber}</a>
                        </li>
		   
<div class="commentAll" >
    <!--评论区域 begin-->
    <div class="reviewArea clearfix">
        <textarea id="comment" class="content comment-input" placeholder="对该舆情对象进行评论" onkeyup="keyUP(this)"></textarea>
        <a href="javascript:;" onclick="commentInitClick()" class="plBtn">发布</a>
    </div>
    <!--评论区域 end-->
    <!--回复区域 begin-->
    <div class="comment-show">
	    <c:forEach items="${comments}" var="c" varStatus="sts">
            <c:if test="${c.fathercommentId == null}">
            <div class="comment-show-con clearfix">
                <div class="comment-show-con-img pull-left"><img src="images/header-img-comment_03.png" alt=""></div>
                <div class="comment-show-con-list pull-left clearfix">
                    <div class="pl-text clearfix">
                        <a href="#" class="comment-size-name">${c.userEntity.userName} : </a>
                        <span class="my-pl-con">&nbsp;${c.commentContent}</span>
                    </div>
                    <div class="date-dz">
                        <span class="date-dz-left pull-left comment-time">${c.commentTime}</span>
                        <div class="date-dz-right pull-right comment-pl-block">
                            <!--<a href="javascript:;" class="removeBlock">删除</a>-->
                            <a href="javascript:;" class="date-dz-pl pl-hf hf-con-block pull-left" onclick="clickReply(this, '${c.commentId}', '${sts.index}')">回复</a>
                            <%--<span class="pull-left date-dz-line">|</span>
                            <a href="javascript:;" class="date-dz-z pull-left"><i class="date-dz-z-click-red"></i>(<i class="z-num">666</i>)</a>
                            <span class="pull-left date-dz-line">|</span>
                            <a href="javascript:;" class="date-dz-z pull-left">真 (<i class="z-num">7</i>)</a>
                            <span class="pull-left date-dz-line">|</span>
                            <a href="javascript:;" class="date-dz-z pull-left">假 (<i class="z-num">6</i>)</a>--%>
                        </div>
                    </div>
                    <div style="margin-left:6px;">
                        <%--<span class="date-dz-left pull-left comment-time"> <a href="javascript:void(0)" onclick="showReply(${c.commentId})">展开↓↓</a></span>--%>
                    </div>
                    <div class="hf-list-con"></div>
                </div>
            </div>
            </c:if>
            <div>
            <c:forEach items="${comments}" var="c2" varStatus="sts2">
                <c:if test="${c.commentId == c2.fathercommentId}">
                    <div style="margin-left: 100px;" class="reply">
                        <a href="#" class="comment-size-name">${c2.userEntity.userName}: </a>
                        <span class="my-pl-con">&nbsp;<span style="color: #e10d0d;">@${c.userEntity.userName}</span>: ${c2.commentContent}</span>
                    </div>
                    <div class="date-dz">
                        <span style="margin-left: 100px;" class="date-dz-left pull-left comment-time">${c2.commentTime}</span>
                        <div class="date-dz-right pull-right comment-pl-block">
                            <!--<a href="javascript:;" class="removeBlock">删除</a>-->
                            <a href="javascript:;" class="date-dz-pl pl-hf hf-con-block pull-left" onclick="clickReply(this, '${c.commentId}', '${sts.index+sts2.index+100}');">回复</a>
                            <%--<span class="pull-left date-dz-line">|</span>
                            <a href="javascript:;" class="date-dz-z pull-left"><i class="date-dz-z-click-red"></i>(<i class="z-num">666</i>)</a>
                            <span class="pull-left date-dz-line">|</span>
                            <a href="javascript:;" class="date-dz-z pull-left">真 (<i class="z-num">7</i>)</a>
                            <span class="pull-left date-dz-line">|</span>
                            <a href="javascript:;" class="date-dz-z pull-left">假 (<i class="z-num">6</i>)</a>--%>
                        </div>
                    </div>
                    <br>
                </c:if>
            </c:forEach>
            </div>
        </c:forEach>

        <div class="">
            <div class="">
                <%--<div class="" style="float:right;">
					<button class="btn" style="color:grey; margin-top:-24px;">&nbsp;加载更多...</button>
                </div>--%>
            </div>
        </div>
        <script>
            function showReply(index) {
                if ($(".reply"+index).css("display") == 'none'){
                    $(".reply"+index).css("display", "");
                }else {
                    $(".reply"+index).css("display", "none");
                }
            }

        </script>

    </div>
    <!--回复区域 end-->
</div>

            <hr>
            <footer>
                <p>&copy; Vincent Gabriel 2013</p>
            </footer>
        </div>
        <!--/.fluid-container-->

      
        <script src="bootstrap/js/bootstrap.min.js"></script>
		
<script type="text/javascript" src="js/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="js/jquery.flexText.js"></script>
<!--textarea高度自适应-->
<script type="text/javascript">
    $(function () {
        $('.content').flexText();
    });
</script>
<!--textarea限制字数-->
<script type="text/javascript">
    function keyUP(t){
        var len = $(t).val().length;
        if(len > 139){
            $(t).val($(t).val().substring(0,140));
        }
    }
</script>
<!--点击评论创建评论条-->
<script type="text/javascript">
    function createComment(commentId, c2CommentId){
        var myDate = new Date();
        //获取当前年
        var year=myDate.getFullYear();
        //获取当前月
        var month=myDate.getMonth()+1;
        //获取当前日
        var date=myDate.getDate();
        var h=myDate.getHours();       //获取当前小时数(0-23)
        var m=myDate.getMinutes();     //获取当前分钟数(0-59)
        if(m<10) m = '0' + m;
        var s=myDate.getSeconds();
        if(s<10) s = '0' + s;
        var now=year+'-'+month+"-"+date+" "+h+':'+m+":"+s;
        //获取输入内容
        var oSize = $(this).siblings('.flex-text-wrap').find('.comment-input').val();
        console.log(oSize);
        //动态创建评论模块
        oHtml = '<div class="comment-show-con clearfix"><div class="comment-show-con-img pull-left"><img src="images/header-img-comment_03.png" alt=""></div> <div class="comment-show-con-list pull-left clearfix"><div class="pl-text clearfix"> <a href="#" class="comment-size-name">David Beckham : </a> <span class="my-pl-con">&nbsp;'+ oSize +'</span> </div> <div class="date-dz"> <span class="date-dz-left pull-left comment-time">'+now+'</span> <div class="date-dz-right pull-right comment-pl-block"><a href="javascript:;" class="removeBlock">删除</a> <a href="javascript:;" onclick="commentClick('+commentId+','+c2CommentId+')" class="date-dz-pl pl-hf hf-con-block pull-left">回复</a> <span class="pull-left date-dz-line">|</span> <a href="javascript:;" class="date-dz-z pull-left"><i class="date-dz-z-click-red"></i>赞 (<i class="z-num">666</i>)</a> </div> </div><div class="hf-list-con"></div></div> </div>';
        if(oSize.replace(/(^\s*)|(\s*$)/g, "") != ''){
            $(this).parents('.reviewArea ').siblings('.comment-show').prepend(oHtml);
            $(this).siblings('.flex-text-wrap').find('.comment-input').prop('value','').siblings('pre').find('span').text('');
        }
    }
</script>
<!--点击回复动态创建回复块-->
<script type="text/javascript">
    function clickReply($this, commentId, c2CommentId){
        //获取回复人的名字
        var fhName = $($this).parents('.date-dz-right').parents('.date-dz').siblings('.pl-text').find('.comment-size-name').html();
        //回复@
        var fhN = '';
        //var oInput = $(this).parents('.date-dz-right').parents('.date-dz').siblings('.hf-con');
        var fhHtml = '<div class="hf-con pull-left"> <textarea id="comment'+c2CommentId+'" class="content comment-input hf-input" placeholder="" onkeyup="keyUP(this)"></textarea> <a href="javascript:;" onclick="commentClick(\''+commentId+'\',\''+c2CommentId+'\');" class="hf-pl">评论</a></div>';
        //显示回复
        if($($this).is('.hf-con-block')){
            $($this).parents('.date-dz-right').parents('.date-dz').append(fhHtml);
            $($this).removeClass('hf-con-block');
            $('.content').flexText();
            $($this).parents('.date-dz-right').siblings('.hf-con').find('.pre').css('padding','6px 15px');
            //console.log($(this).parents('.date-dz-right').siblings('.hf-con').find('.pre'))
            //input框自动聚焦
            $($this).parents('.date-dz-right').siblings('.hf-con').find('.hf-input').val('').focus().val(fhN);
        }else {
            $($this).addClass('hf-con-block');
            $($this).parents('.date-dz-right').siblings('.hf-con').remove();
        }
    }
</script>

    <!--父评论-->
    <script>
        //评论
        function commentInitClick() {
            var objId = $("#objId").val();
            var comment = $("#comment").val();
            var basePath = $("#basePath").val();
            $.ajax({
                url : "" + basePath + "systemComment?objId="+objId+"&comment="+comment,
                type : "post",
                success : function (msg) {
                    window.location.reload();
                }
            });
        }
    </script>

    <!--评论传入后台-->
    <script>
        //评论
        function commentClick(commentId, c2CommentId) {
            var objId = $("#objId").val();
            var comment = $("#comment"+c2CommentId).val();
            var basePath = $("#basePath").val();
            $.ajax({
                url : ""+ basePath + "systemComment?objId="+objId+"&commentId="+commentId+"&comment="+comment,
                type : "post",
                success : function (msg) {
                    window.location.reload();
                }
            });
        }
    </script>
<!--评论回复块创建-->
<script type="text/javascript">
//    $('.comment-show').on('click','.hf-pl',function(){
//        var oThis = $(this);
//        var myDate = new Date();
//        //获取当前年
//        var year=myDate.getFullYear();
//        //获取当前月
//        var month=myDate.getMonth()+1;
//        //获取当前日
//        var date=myDate.getDate();
//        var h=myDate.getHours();       //获取当前小时数(0-23)
//        var m=myDate.getMinutes();     //获取当前分钟数(0-59)
//        if(m<10) m = '0' + m;
//        var s=myDate.getSeconds();
//        if(s<10) s = '0' + s;
//        var now=year+'-'+month+"-"+date+" "+h+':'+m+":"+s;
//        //获取输入内容
//        var oHfVal = $(this).siblings('.flex-text-wrap').find('.hf-input').val();
//        console.log(oHfVal)
//        var oHfName = $(this).parents('.hf-con').parents('.date-dz').siblings('.pl-text').find('.comment-size-name').html();
//        var oAllVal = '回复@'+oHfName;
//        if(oHfVal.replace(/^ +| +$/g,'') == '' || oHfVal == oAllVal){
//
//        }else {
//            $.getJSON("json/pl.json",function(data){
//                var oAt = '';
//                var oHf = '';
//                $.each(data,function(n,v){
//                    delete v.hfContent;
//                    delete v.atName;
//                    var arr;
//                    var ohfNameArr;
//                    if(oHfVal.indexOf("@") == -1){
//                        data['atName'] = '';
//                        data['hfContent'] = oHfVal;
//                    }else {
//                        arr = oHfVal.split(':');
//                        ohfNameArr = arr[0].split('@');
//                        data['hfContent'] = arr[1];
//                        data['atName'] = ohfNameArr[1];
//                    }
//
//                    if(data.atName == ''){
//                        oAt = data.hfContent;
//                    }else {
//                        oAt = '回复<a href="#" class="atName">@'+data.atName+'</a> : '+data.hfContent;
//                    }
//                    oHf = data.hfName;
//                });
//
//                var oHtml = '<div class="all-pl-con"><div class="pl-text hfpl-text clearfix"><a href="#" class="comment-size-name">我的名字 : </a><span class="my-pl-con">'+oAt+'</span></div><div class="date-dz"> <span class="date-dz-left pull-left comment-time">'+now+'</span> <div class="date-dz-right pull-right comment-pl-block"> <a href="javascript:;" class="removeBlock">删除</a> <a href="javascript:;" class="date-dz-pl pl-hf hf-con-block pull-left">回复</a> <span class="pull-left date-dz-line">|</span> <a href="javascript:;" class="date-dz-z pull-left"><i class="date-dz-z-click-red"></i>赞 (<i class="z-num">666</i>)</a> </div> </div></div>';
//                oThis.parents('.hf-con').parents('.comment-show-con-list').find('.hf-list-con').css('display','block').prepend(oHtml) && oThis.parents('.hf-con').siblings('.date-dz-right').find('.pl-hf').addClass('hf-con-block') && oThis.parents('.hf-con').remove();
//            });
//        }
//    });
</script>
<!--删除评论块-->
<script type="text/javascript">
    $('.commentAll').on('click','.removeBlock',function(){
        var oT = $(this).parents('.date-dz-right').parents('.date-dz').parents('.all-pl-con');
        if(oT.siblings('.all-pl-con').length >= 1){
            oT.remove();
        }else {
            $(this).parents('.date-dz-right').parents('.date-dz').parents('.all-pl-con').parents('.hf-list-con').css('display','none')
            oT.remove();
        }
        $(this).parents('.date-dz-right').parents('.date-dz').parents('.comment-show-con-list').parents('.comment-show-con').remove();

    })
</script>
<!--点赞-->
<script type="text/javascript">
    $('.comment-show').on('click','.date-dz-z',function(){
        var zNum = $(this).find('.z-num').html();
        if($(this).is('.date-dz-z-click')){
            zNum--;
            $(this).removeClass('date-dz-z-click red');
            $(this).find('.z-num').html(zNum);
            $(this).find('.date-dz-z-click-red').removeClass('red');
        }else {
            zNum++;
            $(this).addClass('date-dz-z-click');
            $(this).find('.z-num').html(zNum);
            $(this).find('.date-dz-z-click-red').addClass('red');
        }
    })
</script>		

    </body>

</html>