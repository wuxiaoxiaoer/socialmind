//全局设置
var lzc = {};

lzc.id = function(id){
	return typeof id === 'object' ? id : document.getElementById(id);
};

lzc.tagName = function(tagName, oParent){
	return (oParent || document).getElementsByTagName(tagName);
};

lzc.Class = function(oParent, sClass){
	var aElem = lzc.tagName('*', oParent);
	var aClass = [];
	var i = 0;
	for(i=0;i<aElem.length;i++)if(aElem[i].className == sClass)aClass.push(aElem[i]);
	return aClass;
};

lzc.myAddEvent = function(obj, sEvent, fn){
	if(obj!=null)
	return obj.attachEvent ? obj.attachEvent('on' + sEvent, fn) : obj.addEventListener(sEvent, fn, false);
}

lzc.operate = function(){};

//弹出层

lzc.Alert = function(){
	this.initialize.apply(this, arguments);
}

Object.extend = function(destination, source){
    for(var property in source)destination[property] = source[property];
    return destination;
};

lzc.Alert.prototype = {
	initialize : function(obj, fnEnd){
		if(typeof obj == 'string'){
			var _this = this;
			this.bian = 16;
			this.property(fnEnd);
			this.width = this.fnEnd.width;
			this.height = this.fnEnd.height;
			this.sl = this.fnEnd.left;
			this.st = this.fnEnd.top;
			this.content = this.fnEnd.content;
			this.sidebar = this.fnEnd.sidebar;
			this.traction = this.fnEnd.traction;
			this.fixed = this.fnEnd.fixed;
			this.close = this.fnEnd.close;
			if(obj == ''){
				_this.create();
			}else{
				switch(obj.charAt(0)){
					case '#':
						this.obj = lzc.id(obj.substring(1));
						lzc.myAddEvent(this.obj, 'click', function(){
							_this.create();
						});
						break;
					default:
						this.obj = lzc.Class(document,obj.substring(1));
						for(var len=this.obj.length,i=0;i<len;i++){
							lzc.myAddEvent(this.obj[i], 'click', function(){
								_this.create();
							});
						}
				}
			}
		}		
	},
	property : function(fnEnd){
		this.fnEnd = {};
		Object.extend(this.fnEnd, fnEnd || {});
	},
	create : function(){
		//创建
		this.box = document.createElement('div');
		this.angle = document.createElement('div');
		this.LT = document.createElement('span');
		this.RT = document.createElement('span');
		this.LB = document.createElement('span');
		this.RB = document.createElement('span');
		this.T = document.createElement('span');
		this.B = document.createElement('span');
		this.L = document.createElement('span');
		this.R = document.createElement('span');
		this.Clos = document.createElement('span');
		this.can =  document.createElement('div');
		this.bg =  document.createElement('div');
		//设置class
		this.box.className = 'alert';
		this.LT.className = 'LT';
		this.RT.className = 'RT';
		this.LB.className = 'LB';
		this.RB.className = 'RB';
		this.T.className = 'T';
		this.B.className = 'B';
		this.L.className = 'L';
		this.R.className = 'R';
		this.Clos.className = 'close';
		this.can.className =  'C';
		this.bg.className =  'bg';
		if(this.traction == 'traction'){
			this.LT.style.cursor = 'nw-resize';
			this.RT.style.cursor = 'ne-resize';
			this.LB.style.cursor = 'ne-resize';
			this.RB.style.cursor = 'nw-resize';
			this.T.style.cursor = 'n-resize';
			this.B.style.cursor = 'n-resize';
			this.L.style.cursor = 'w-resize';
			this.R.style.cursor = 'w-resize';
		}
		if(this.sidebar == "sidebar"){
			//创建
			this.left =  document.createElement('div');
			this.lAngle = document.createElement('div');
			this.left_LT = document.createElement('span');
			this.left_LB = document.createElement('span');
			this.left_T = document.createElement('span');
			this.left_B = document.createElement('span');
			this.left_L = document.createElement('span');
			this.right =  document.createElement('div');
			//设置CSS
			this.left.className =  'left';		
			this.left_LT.className = 'left_LT';
			this.left_LB.className = 'left_LB';
			this.left_T.className = 'left_T';
			this.left_B.className = 'left_B';
			this.left_L.className = 'left_L';		
			this.right.className = 'right';
			if(this.traction == 'traction'){
				this.left_LT.style.cursor = 'nw-resize';
				this.left_LB.style.cursor = 'ne-resize';
				this.left_T.style.cursor = 'n-resize';
				this.left_B.style.cursor = 'n-resize';
				this.left_L.style.cursor = 'w-resize';
			}
			//插入
			this.left.appendChild(this.lAngle);
			this.left.appendChild(this.left_LT);
			this.left.appendChild(this.left_LB);
			this.left.appendChild(this.left_T);
			this.left.appendChild(this.left_B);
			this.left.appendChild(this.left_L);
			this.can.appendChild(this.left);
			this.can.appendChild(this.right);
		}
		if(this.fixed == 'fixed'){
			this.drag = document.createElement('div');
			this.dragImg = document.createElement('img');
			this.drag.className = 'drag';
			this.dragImg.src = 'images/openwindow/san.jpg'
			this.drag.appendChild(this.dragImg);
			this.can.appendChild(this.drag);
			
		}
		//插入
		this.angle.appendChild(this.LT);
		this.angle.appendChild(this.RT);
		this.angle.appendChild(this.LB);
		this.angle.appendChild(this.RB);
		this.angle.appendChild(this.T);
		this.angle.appendChild(this.B);
		this.angle.appendChild(this.L);
		this.angle.appendChild(this.R);
		this.angle.appendChild(this.Clos);
		this.box.appendChild(this.angle);
		this.box.appendChild(this.can);
		
		document.body.appendChild(this.box);
		document.body.appendChild(this.bg);
		//调用
		this.conent();
		this.attribute();
	},
	conent : function(){
		if(typeof this.content == 'string'){
			switch(this.content.charAt(0)){
				case '#':
					this.con = lzc.id(this.content.substring(1));
					this.sidebar == "sidebar"?this.right.innerHTML += this.con.innerHTML:this.can.innerHTML += this.con.innerHTML;
					break;
				default:
					this.sidebar == "sidebar"?this.right.innerHTML += this.content:this.can.innerHTML += this.content;
			}
		}
		
	},
	attribute : function(){
		switch(this.width){
			case undefined:
			break;
			default:
				if(this.width != ''){
					this.box.style.width = this.width + 'px';
					this.box.style.marginLeft = -(this.width / 2) + 'px';
					this.can.style.width = this.width - this.bian + 'px';
					this.T.style.width = this.width - this.bian + 'px';
					this.B.style.width = this.width - this.bian + 'px';
					if(this.sidebar == "sidebar")this.right.style.width = this.width - 143 + 'px';
				}
		}
		switch(this.height){
			case undefined:
			break;
			default:
				if(this.height != ''){
					this.box.style.height = this.height + 'px';
					this.box.style.marginTop = -(this.height / 2) + 'px';
					this.can.style.height = this.height - this.bian + 'px';
					this.L.style.height = this.height - this.bian + 'px';
					this.R.style.height = this.height - this.bian + 'px';
					if(this.sidebar == "sidebar")this.right.style.height = this.height - this.bian + 'px';
				}
		}
		switch(this.sl){
			case undefined:
			break;
			default:
				if(this.sl != ''){
					this.box.style.left = this.sl + 'px';
					this.box.style.marginLeft = 0;
				}
		}
		switch(this.st){
			case undefined:
			break;
			default:
				if(this.st != ''){
					this.box.style.top = this.st + 'px';
					this.box.style.marginTop = 0;
				}
		}
		if(this.fixed == 'fixed'){
			var _this = this;
			this.box.style.position = 'fixed'
			this.oBox = lzc.Class(document, 'alert')[0];
			var oDrag = lzc.Class(this.oBox, 'drag')[0];
			oDrag.onmousedown = function(e){
				var _thisE = this;
				this.oEvent = e || event;
				this.X = this.oEvent.clientX - _this.box.offsetLeft;
				this.Y = this.oEvent.clientY - _this.box.offsetTop;
				document.onmousemove = function(e){
					this.oEvent = e || event;
					this.L = this.oEvent.clientX - _thisE.X;
					this.T = this.oEvent.clientY - _thisE.Y;
					if(this.L < 0){
						this.L = 0;
					}else if(this.L > document.documentElement.clientWidth - _this.box.offsetWidth){
						this.L = document.documentElement.clientWidth - _this.box.offsetWidth
					}
					if(this.T < 0){
						this.T = 0;
					}else if(this.T > document.documentElement.clientHeight - _this.box.offsetHeight){
						this.T = document.documentElement.clientHeight - _this.box.offsetHeight;
					}
					_this.box.style.left = this.L + 'px';
					_this.box.style.top = this.T + 'px';
					_this.box.style.margin = 0;
					return false;
				};
				document.onmouseup = function(){
					document.onmouseup = null;
					document.onmousemove = null;
					oDrag.releaseCapture && oDrag.releaseCapture()
				};
				this.setCapture && this.setCapture();
				return false;
			};
			if(this.box){
				if(!-[1,] && !window.XMLHttpRequest){
					document.documentElement.style.textOverflow = "ellipsis";
					this.box.style.position = "absolute";
					this.box.style.setExpression("top", "eval(documentElement.scrollTop + " + this.box.offsetTop + ') + "px"');
					this.box.style.marginTop = 0;
				}
			}
		}
		if(this.close == 'close'){
			this.oClose = lzc.Class(document, 'close')[0];
			var oBox = lzc.Class(document, 'alert')[0];
			var oBg = lzc.Class(document, 'bg')[0];			
			this.Clos.style.display = 'block';
			lzc.myAddEvent(this.oClose, 'click', function(){
				document.body.removeChild(oBox);
				document.body.removeChild(oBg);
				if(!-[1,] && !window.XMLHttpRequest){
					var oPngfix = lzc.id('pngfix');
					var oPng = lzc.id('png');
					document.body.removeChild(oPngfix);
					document.body.removeChild(oPng);
				}
			});
		}
		setTimeout(this.png,30)
		this.oTraction();
		lzc.operate();
	},
	oTraction : function(){
		if(this.traction == 'traction'){
			//获取边角
			var oAlert = lzc.Class(document,'alert')[0];
			var oL = lzc.Class(oAlert,'L')[0];
			var oT = lzc.Class(oAlert,'T')[0];
			var oR = lzc.Class(oAlert,'R')[0];
			var oB = lzc.Class(oAlert,'B')[0];
			var oLT = lzc.Class(oAlert,'LT')[0];
			var oRT = lzc.Class(oAlert,'RT')[0];
			var oRB = lzc.Class(oAlert,'RB')[0];
			var oLB = lzc.Class(oAlert,'LB')[0];
			if(this.sidebar == "sidebar"){
				var oLeft_LT = lzc.Class(oAlert,'left_LT')[0];
				var oLeft_LB = lzc.Class(oAlert,'left_LB')[0];
				var oLeft_T = lzc.Class(oAlert,'left_T')[0];
				var oLeft_B = lzc.Class(oAlert,'left_B')[0];
				var oLeft_L = lzc.Class(oAlert,'left_L')[0];
				this.resize(oAlert, oLeft_LT, true, true, false, false);
				this.resize(oAlert, oLeft_LB, true, false, false, false);
				this.resize(oAlert, oLeft_T, false, true, true, false);
				this.resize(oAlert, oLeft_B, false, false, true, false);
				this.resize(oAlert, oLeft_L, true, false, false, true);
			}
			//四角
			this.resize(oAlert, oLT, true, true, false, false);
			this.resize(oAlert, oRT, false, true, false, false);
			this.resize(oAlert, oRB, false, false, false, false);
			this.resize(oAlert, oLB, true, false, false, false);
			//四边
			this.resize(oAlert, oL, true, false, false, true);
			this.resize(oAlert, oT, false, true, true, false);
			this.resize(oAlert, oR, false, false, false, true);
			this.resize(oAlert, oB, false, false, true, false);
		}
	},
	resize : function(oParent, handle, isLeft, isTop, lockX, lockY){
		var dragMinWidth = 390;
		var dragMinHeight = 200;
		handle.onmousedown = function(e){
			var oEvent = e || event;
			var disX = oEvent.clientX - handle.offsetLeft;
			var disY = oEvent.clientY - handle.offsetTop;	
			var iParentTop = oParent.offsetTop;
			var iParentLeft = oParent.offsetLeft;
			var iParentWidth = oParent.offsetWidth;
			var iParentHeight = oParent.offsetHeight;
			var oC = lzc.Class(oParent,'C')[0];
			var oT = lzc.Class(oParent,'T')[0];
			var oB = lzc.Class(oParent,'B')[0];
			var oL = lzc.Class(oParent,'L')[0];
			var oR = lzc.Class(oParent,'R')[0];
			document.onmousemove = function(e){
				var oEvent = e || event;
				var iL = oEvent.clientX - disX;
				var iT = oEvent.clientY - disY;
				var maxW = document.documentElement.clientWidth - oParent.offsetLeft - 2;
				var maxH = document.documentElement.clientHeight - oParent.offsetTop - 2;			
				var iW = isLeft ? iParentWidth - iL : handle.offsetWidth + iL;
				var iH = isTop ? iParentHeight - iT : handle.offsetHeight + iT;
				isLeft && (oParent.style.left = iParentLeft + iL + "px",oParent.style.marginLeft = 0);
				isTop && (oParent.style.top = iParentTop + iT + "px",oParent.style.marginTop = 0);
				iW < dragMinWidth && (iW = dragMinWidth);
				iW > maxW && (iW = maxW);
				lockX || (oParent.style.width = iW + "px",oC.style.width = oB.style.width = oT.style.width = iW - 16 + "px");
				iH < dragMinHeight && (iH = dragMinHeight);
				iH > maxH && (iH = maxH);
				lockY || (oParent.style.height = iH + "px",oC.style.height = oL.style.height = oR.style.height = iH - 16 + "px");
				if((isLeft && iW == dragMinWidth) || (isTop && iH == dragMinHeight)) document.onmousemove = null;
				return false;	
			};
			document.onmouseup = function(){
				document.onmousemove = null;
				document.onmouseup = null;
			};
			return false;
		}
	},
	png : function(){
		if(!-[1,] && !window.XMLHttpRequest){
			this.script = document.createElement('script');
			this.script.src = 'js/pngfix.js';
			this.script.id = 'pngfix';
			this.script.type = 'text/javascript';
			this.scriptPng = document.createElement('script');
			this.scriptPng.src = 'js/png.js';
			this.scriptPng.id = 'png';
			this.scriptPng.type = 'text/javascript';
			document.body.appendChild(this.script);
			document.body.appendChild(this.scriptPng);
		}
	}
	
};
function Alert(obj, fnEnd){
	return new lzc.Alert(obj, fnEnd);
}