function sideInfo(arrObj){
	//创建DOM
	var 
	quickHTML = document.querySelector("div.quick_link_mian"),
	quickShell = $(document.createElement('div')).html(quickHTML).addClass('quick_links_wrap'),
	quickLinks = quickShell.find('.quick_links');
	quickPanel = quickLinks.next();
	quickShell.appendTo('.mui-mbar-tabs');
	
	//具体数据操作 
	var quickPopXHR,
	loadingTmpl = '<div class="loading" style="padding:30px 80px"><i></i><span>Loading...</span></div>',
	popTmpl = '<a href="javascript:;" class="ibar_closebtn" title="关闭"></a><div class="ibar_plugin_title"><h3><%=title%></h3></div><div class="pop_panel"><%=content%></div><div class="arrow"><i></i></div><div class="fix_bg"></div>',
	historyListTmpl = '<ul><%for(var i=0,len=items.length; i<5&&i<len; i++){%><li><a href="<%=items[i].productUrl%>" target="_blank" class="pic"><img alt="<%=items[i].productName%>" src="<%=items[i].productImage%>" width="60" height="60"/></a><a href="<%=items[i].productUrl%>" title="<%=items[i].productName%>" target="_blank" class="tit"><%=items[i].productName%></a><div class="price" title="单价"><em>&yen;<%=items[i].productPrice%></em></div></li><%}%></ul>',
	newMsgTmpl = '<ul><li><a href="#"><span class="tips">新回复<em class="num"><b><%=items.commentNewReply%></b></em></span>商品评价/晒单</a></li><li><a href="#"><span class="tips">新回复<em class="num"><b><%=items.consultNewReply%></b></em></span>商品咨询</a></li><li><a href="#"><span class="tips">新回复<em class="num"><b><%=items.messageNewReply%></b></em></span>我的留言</a></li><li><a href="#"><span class="tips">新通知<em class="num"><b><%=items.arrivalNewNotice%></b></em></span>到货通知</a></li><li><a href="#"><span class="tips">新通知<em class="num"><b><%=items.reduceNewNotice%></b></em></span>降价提醒</a></li></ul>',
	quickPop = quickShell.find('#quick_links_pop'),
		strMessage = '<div class="span3" id="sidebar" style="float: right; display: block;">' +
			'<ul class="nav nav-list bs-docs-sidenav nav-collapse collapse">'+
		'<li class="">'+
		'<a><b>欢迎您的参与</b></a>'+
		'<a>事件名称：'+$("#objName").val()+'</a>'+
	'<a> 感觉不错：<button id="btnSupport" class="btn btn-success btn-mini" onclick="zanFunc(\'support\');">点赞</button>&nbsp;&nbsp;<button id="btnCollect" class="btn btn-warning btn-mini" onclick="zanFunc(\'collect\');">收藏</button></a>'+
		'<a>判别真假：<button id="btnTrue" class="btn btn-danger" onclick="zanFunc(\'isTrue\');">真/假</button>&nbsp;&nbsp;'+
		'</a>'+
		'<a> 评论留言: </a>'+
	    '<textarea id="comment" rows="8" cols="40" placeholder="对舆情对象进行评论..."></textarea>'+
		'<button onclick="commentClick();"  class="btn btn-danger">确 定</button>'+
		'</li>'+
			'<div class="alert alert-success">'+
	'<strong id="prop_comm"></strong></div>'+
	'<li class="">'+
		'<a href="'+$("#basePath").val()+'systemCommentsShow?objId='+$("#objId").val()+'" class="btn" style="float: right; margin-right:30px;">查看详情...</a>'+
		'</li>'+
		'</ul>'+
		'</div>';

	for(var i in arrObj){
		// strMessage += "<div class='alert alert-success alert-block'><h4 class='alert-heading'>"+ $("#objName").val() +"</h4></div><div>内容</div><div>参数</div>";
		// strMessage += '<div class="ibar_plugin_content"><div class="ibar_cart_group ibar_cart_product"><div class="ibar_cart_group_header"><span class="ibar_cart_group_title">模板堂</span><a href="#">我的购物车</a></div><ul><li class="cart_item"><div class="cart_item_pic"><a href="#"><img src="images/xiez.jpg" /></a></div><div class="cart_item_desc"><a href="#" class="cart_item_name">夏季透气真皮豆豆鞋反绒男士休闲鞋韩版磨砂驾车鞋英伦船鞋男鞋子</a><div class="cart_item_sku"><span>尺码：38码（精工限量版）</span></div><div class="cart_item_price"><span class="cart_price">￥700.00</span></div></div>	</li></ul></div><div class="cart_handler"><div class="cart_handler_header"><span class="cart_handler_left">共<span class="cart_price">1</span>件商品</span><span class="cart_handler_right">￥569.00</span></div><a href="#" class="cart_go_btn" target="_blank">去购物车结算</a></div></div>';
		strMessage += "";
	}
	strMessage += "</div>";

	quickDataFns = {
		//购物信息
		message_list: {
			title: '购物车',
			content: strMessage,
			init:$.noop
		}
	};
	
	//showQuickPop
	var 
	prevPopType,
	prevTrigger,
	doc = $(document),
	popDisplayed = false,
	hideQuickPop = function(){
		if(prevTrigger){
			prevTrigger.removeClass('current');
		}
		popDisplayed = false;
		prevPopType = '';
		quickPop.hide();
		quickPop.animate({left:280,queue:true});
	},
	showQuickPop = function(type){
		if(quickPopXHR && quickPopXHR.abort){
			quickPopXHR.abort();
		}
		if(type !== prevPopType){
			var fn = quickDataFns[type];
			quickPop.html(ds.tmpl(popTmpl, fn));
			fn.init.call(this, fn);
		}
		doc.unbind('click.quick_links').one('click.quick_links', hideQuickPop);

		quickPop[0].className = 'quick_links_pop quick_' + type;
		popDisplayed = true;
		prevPopType = type;
		quickPop.show();
		quickPop.animate({left:0,queue:true});
	};
	quickShell.bind('click.quick_links', function(e){
		e.stopPropagation();
	});
	quickPop.delegate('a.ibar_closebtn','click',function(){
		quickPop.hide();
		quickPop.animate({left:280,queue:true});
		if(prevTrigger){
			prevTrigger.removeClass('current');
		}
	});

	//通用事件处理
	var 
	view = $(window),
	quickLinkCollapsed = !!ds.getCookie('ql_collapse'),
	getHandlerType = function(className){
		return className.replace(/current/g, '').replace(/\s+/, '');
	},
	showPopFn = function(){
		var type = getHandlerType(this.className);
		if(popDisplayed && type === prevPopType){
			return hideQuickPop();
		}
		showQuickPop(this.className);
		if(prevTrigger){
			prevTrigger.removeClass('current');
		}
		prevTrigger = $(this).addClass('current');
	},
	quickHandlers = {
		//购物车，最近浏览，商品咨询
		my_qlinks: showPopFn,
		message_list: showPopFn,
		history_list: showPopFn,
		leave_message: showPopFn,
		mpbtn_histroy:showPopFn,
		mpbtn_recharge:showPopFn,
		mpbtn_wdsc:showPopFn,
		//返回顶部
		return_top: function(){
			ds.scrollTo(0, 0);
			hideReturnTop();
		}
	};
	quickShell.delegate('a', 'click', function(e){
		var type = getHandlerType(this.className);
		if(type && quickHandlers[type]){
			quickHandlers[type].call(this);
			e.preventDefault();
		}
	});
	
	//Return top
	var scrollTimer, resizeTimer, minWidth = 1350;

	function resizeHandler(){
		clearTimeout(scrollTimer);
		scrollTimer = setTimeout(checkScroll, 160);
	}
	
	function checkResize(){
		quickShell[view.width() > 1340 ? 'removeClass' : 'addClass']('quick_links_dockright');
	}
	function scrollHandler(){
		clearTimeout(resizeTimer);
		resizeTimer = setTimeout(checkResize, 160);
	}
	function checkScroll(){
		view.scrollTop()>100 ? showReturnTop() : hideReturnTop();
	}
	function showReturnTop(){
		quickPanel.addClass('quick_links_allow_gotop');
	}
	function hideReturnTop(){
		quickPanel.removeClass('quick_links_allow_gotop');
	}
	view.bind('scroll.go_top', resizeHandler).bind('resize.quick_links', scrollHandler);
	quickLinkCollapsed && quickShell.addClass('quick_links_min');
	resizeHandler();
	scrollHandler();
}

//点赞
function zanFunc(opera) {
	var objId = $("#objId").val();
	var basePath = $("#basePath").val();
	$.ajax({
		url : ""+basePath + "SystemOperation?objId="+objId+"&opera="+opera,
		type : "post",
		success : function (msg) {
		    alert(msg);
			if(msg == 'support'){
				$("#btnSupport").text("已点赞");
                $("#btnSupport").attr("class", "btn btn-mini");
			}
			if(msg == 'removesupport'){
				$("#btnSupport").text("点赞");
                $("#btnSupport").attr("class", "btn btn-success btn-mini");
			}
            if(msg == 'collect'){
                $("#btnCollect").text("已收藏");
                $("#btnCollect").attr("class", "btn btn-mini");
            }
            if(msg == 'removecollect'){
                $("#btnCollect").text("收藏");
                $("#btnCollect").attr("class", "btn btn-warning btn-mini");
            }
            if(msg == 'isTrue'){
                $("#btnTrue").text("判真");
                $("#btnTrue").attr("class", "btn btn-danger");
            }
            if(msg == 'isFalse'){
                $("#btnTrue").text("判假");
                $("#btnTrue").attr("class", "btn btn-inverse");
            }
		}
	});
}

//评论
function commentClick() {
	var objId = $("#objId").val();
	var comment = $("#comment").val();
	var basePath = $("#basePath").val();
	$.ajax({
		url : ""+ basePath + "systemComment?objId="+objId+"&comment="+comment,
		type : "post",
		success : function (msg) {
			$("#prop_comm").text("评论成功!");
		}
	});
}
