/**
 * 扩展easyui dialog的两个方法.动态添加和删除buttons的按钮
 */
$.extend($.fn.dialog.methods, {  
	addButtonsItem: function(jq, items){  
		return jq.each(function(){  
			var buttonbar = $(this).children("div.dialog-button");
			for(var i = 0;i<items.length;i++){
				var item = items[i];
				var btn=$("<a href=\"javascript:void(0)\"></a>");
				btn[0].onclick=eval(item.handler||function(){});
				btn.css("float","left").appendTo(buttonbar).linkbutton($.extend({},item,{plain:false}));
			}
			buttonbar = null;
		});  
	},
	removeButtonsItem: function(jq, param){  
		return jq.each(function(){  
			var btns = $(this).children("div.dialog-button").children("a");
			var cbtn = null;
			if(typeof param == "number"){
				cbtn = btns.eq(param);
			}else if(typeof param == "string"){
				var text = null;
				btns.each(function(){
					text = $(this).data().linkbutton.options.text;
					if(text == param){
						cbtn = $(this);
						text = null;
						return;
					}
				});
			} 
			if(cbtn){
				var prev = cbtn.prev()[0];
				var next = cbtn.next()[0];
				if(prev && next && prev.nodeName == "DIV" && prev.nodeName == next.nodeName){
					$(prev).remove();
				}else if(next && next.nodeName == "DIV"){
					$(next).remove();
				}else if(prev && prev.nodeName == "DIV"){
					$(prev).remove();
				}
				cbtn.remove();	
				cbtn= null;
			}						
		});  
	} 				
});

/**
  * JQuery扩展方法，用户对JQuery EasyUI的validatebox控件进行操作。
 * 修改DataGrid对象的默认大小，以适应页面宽度。 
 * @param heightMargin 高度对页内边距的距离。 
 * @param widthMargin 宽度对页内边距的距离。 
 * @param minHeight  最小高度。 
 * @param minWidth 最小宽度。 
 */
$.extend($.fn.validatebox.methods, {   
    moveTips: function(jq, newposition){   
       return jq.each(function(){   
    	   var tip=$.data(this,"validatebox").tip;
    	   if(tip){
    	   tip.remove();
    	   $.data(this,"validatebox").tip=null;
    	   }
        });   
    }   
});  
/** 
 * JQuery扩展方法，用户对JQuery EasyUI的DataGrid控件进行操作。
 * 修改DataGrid对象的默认大小，以适应页面宽度。 
 * @param heightMargin 高度对页内边距的距离。 
 * @param widthMargin 宽度对页内边距的距离。 
 * @param minHeight  最小高度。 
 * @param minWidth 最小宽度。 
 */  
$.fn.extend({  
    resizeDataGrid : function(heightMargin, widthMargin, minHeight, minWidth) {
        var height = $(document.body).height() - heightMargin;  
        var width = $(document.body).width() - widthMargin;  
        height = height < minHeight ? minHeight : height;  
        width = width < minWidth ? minWidth : width;  
        $(this).datagrid('resize', {  
            height : height,  
            width : width  
        });  
    }  
});  
/**
 * 覆盖全局的onBeforeDestroy事件
 */
$.fn.panel.defaults = $.extend({},
		$.fn.panel.defaults,{
			onBeforeDestroy:function(){
				var frame = $("iframe",this);
				if(frame.length>0){
					//2014-5-14 郝强为了避免综合信贷报错注掉了这段代码
					//这段代码主要是为了
					//frame[0].contentWindow.document.write("");
					frame[0].contentWindow.close();
					frame.remove();
					if($.browser.msie){
						CollectGarbage();
					}
				}
			}
});


/**
 * 序列化成json
 */
$.fn.serializeObject = function(){
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
/**datagrid单元格内容提示**/
$.extend($.fn.datagrid.methods, {
	/**
	 * 开启提示功能
	 * @param {} jq
	 * @param {} params 提示消息框的样式
	 * @return {}
	 */
	doCellTip : function(jq, params) {
		function showTip(data, td, e) {
			
			if ($(td).text() == "")
				{return;}
			else{
				var textNew = "";
				if($(td).text().length > 15){
					var length = 0;
					if($(td).text().length%15 == 0){
						length =$(td).text().length/15;
					}else{
						length =$(td).text().length/15+1;
					}
					var n = 0;
					for(var i = 0 ; i <length;i++ ){
						textNew =textNew + $(td).text().substring(i+n,i+n+15)+"\n\r";
						n = n+14;
					}
				}else{
					textNew = $(td).text();
				};
				
				data.tooltip.text(textNew).css({
						top : (e.pageY + 10) + 'px',
						left : (e.pageX + 20) + 'px',
						'z-index' : $.fn.window.defaults.zIndex,
						display : 'block'
					});
			}
		};
		return jq.each(function() {
			var grid = $(this);
			var options = $(this).data('datagrid');
			if (!options.tooltip) {
				var panel = grid.datagrid('getPanel').panel('panel');
				var defaultCls = {
					'border' : '1px solid #333',
					'padding' : '1px',
					'color' : '#333',
					'background' : '#f7f5d1',
					'position' : 'absolute',
					'max-width' : '200px',
					'border-radius' : '4px',
					'-moz-border-radius' : '4px',
					'-webkit-border-radius' : '4px',
					'display' : 'none'
				};
				var tooltip = $("<div id='celltip'></div>").appendTo('body');
				tooltip.css($.extend({}, defaultCls, params.cls));
				options.tooltip = tooltip;
				panel.find('.datagrid-body').each(function() {
					var delegateEle = $(this).find('> div.datagrid-body-inner').length
							? $(this).find('> div.datagrid-body-inner')[0]
							: this;
					$(delegateEle).undelegate('td', 'mouseover').undelegate(
							'td', 'mouseout').undelegate('td', 'mousemove')
							.delegate('td', {
								'mouseover' : function(e) {
									if (params.delay) {
										if (options.tipDelayTime)
											clearTimeout(options.tipDelayTime);
										var that = this;
										options.tipDelayTime = setTimeout(
												function() {
													if($(that).attr("field") != params.notShowfield){
														showTip(options, that, e);
													}
												}, params.delay);
									} else {
										if($(that).attr("field") != params.notShowfield){
										showTip(options, this, e);
										}
									}

								},
								'mouseout' : function(e) {
									if (options.tipDelayTime)
										clearTimeout(options.tipDelayTime);
									options.tooltip.css({
												'display' : 'none'
											});
								},
								'mousemove' : function(e) {
									var that = this;
									if (options.tipDelayTime) {
										clearTimeout(options.tipDelayTime);
										options.tipDelayTime = setTimeout(
												function() {
													if($(that).attr("field") != params.notShowfield){
													showTip(options, that, e);
													}
												}, params.delay);
									} else {
										if($(that).attr("field") != params.notShowfield){
										showTip(options, that, e);
										}
									}
								}
							});
				});

			}

		});
	},
	/**
	 * 关闭消息提示功能
	 * @param {} jq
	 * @return {}
	 */
	cancelCellTip : function(jq) {
		return jq.each(function() {
					var data = $(this).data('datagrid');
					if (data.tooltip) {
						data.tooltip.remove();
						data.tooltip = null;
						var panel = $(this).datagrid('getPanel').panel('panel');
						panel.find('.datagrid-body').undelegate('td',
								'mouseover').undelegate('td', 'mouseout')
								.undelegate('td', 'mousemove');
					}
					if (data.tipDelayTime) {
						clearTimeout(data.tipDelayTime);
						data.tipDelayTime = null;
					}
				});
	},
	/**
	 * 特殊的datagrid 提示功能
	 */
	doCellTipSpecial:function(jq,params){

		function showTip(data, td, e,showTd) {
			var index =$(td.parentNode).attr("datagrid-row-index");
			var rowdata = data.data.rows[index];
			var showContant = eval("rowdata."+showTd);
			if (showContant == null || showContant == "")
				{return;}
			else{
				var textNew = "";
					if(showContant.length > 15){
						var length = 0;
						if(rowdata.length%15 == 0){
							length =showContant.length/15;
						}else{
							length =showContant.length/15+1;
						}
						var n = 0;
						for(var i = 0 ; i <length;i++ ){
							textNew =textNew + showContant.substring(i+n,i+n+15)+"\n\r";
							n = n+14;
						}
					}else{
						textNew = showContant;
					};
					data.tooltip.text(textNew).css({
						top : (e.pageY + 10) + 'px',
						left : (e.pageX + 20) + 'px',
						'z-index' : $.fn.window.defaults.zIndex,
						display : 'block'
					});
				
			}
		};
		return jq.each(function() { 
			var grid = $(this);
			var options = $(this).data('datagrid');
			if (!options.tooltip) {
				var panel = grid.datagrid('getPanel').panel('panel');
				var defaultCls = {
					'border' : '1px solid #333',
					'padding' : '1px',
					'color' : '#333',
					'background' : '#f7f5d1',
					'position' : 'absolute',
					'max-width' : '200px',
					'border-radius' : '4px',
					'-moz-border-radius' : '4px',
					'-webkit-border-radius' : '4px',
					'display' : 'none'
				};
				var tooltip = $("<div id='celltip'></div>").appendTo('body');
				tooltip.css($.extend({}, defaultCls, params.cls));
				options.tooltip = tooltip;
				panel.find('.datagrid-body').each(function() {
					var delegateEle = $(this).find('> div.datagrid-body-inner').length
							? $(this).find('> div.datagrid-body-inner')[0]
							: this;
					$(delegateEle).undelegate('td', 'mouseover').undelegate(
							'td', 'mouseout').undelegate('td', 'mousemove')
							.delegate('td', {
								'mouseover' : function(e) {
									if (params.delay) {
										if (options.tipDelayTime)
											clearTimeout(options.tipDelayTime);
										var that = this;
										options.tipDelayTime = setTimeout(
												function() {
													if($(that).attr("field") == params.showfield){
														showTip(options, that, e,params.showContant);
													}
												}, params.delay);
									} else {
										if($(that).attr("field") == params.showfield){
											showTip(options, this, e,params.showContant);
										}
									}

								},
								'mouseout' : function(e) {
									if (options.tipDelayTime)
										clearTimeout(options.tipDelayTime);
									options.tooltip.css({
												'display' : 'none'
											});
								},
								'mousemove' : function(e) {
									var that = this;
									if (options.tipDelayTime) {
										clearTimeout(options.tipDelayTime);
										options.tipDelayTime = setTimeout(
												function() {
													if($(that).attr("field") == params.showfield){
														showTip(options, that, e,params.showContant);
													}
												}, params.delay);
									} else {
										if($(that).attr("field") == params.showfield){
											showTip(options, that, e,params.showContant);
										}
									}
								}
							});
				});

			}

		});
	}
});

/**
 * 
 * 针对panel window dialog三个组件拖动时会超出父级元素的修正
 * 如果父级元素的overflow属性为hidden，则修复上下左右个方向
 * 如果父级元素的overflow属性为非hidden，则只修复上左两个方向
 * @param left
 * @param top
 * @returns
var easyuiPanelOnMove = function(left, top) {
	var parentObj = $(this).panel('panel').parent();
	if (left < 0) {
		$(this).window('move', {
			left : 1
		});
	}
	if (top < 0) {
		$(this).window('move', {
			top : 1
		});
	}
	var width = $(this).panel('options').width;
	var height = $(this).panel('options').height;
	var right = left + width; 
	var buttom = top + height;
	var parentWidth = parentObj.width();
	var parentHeight = parentObj.height();
	if(parentObj.css("overflow")=="hidden"){
		if(left > parentWidth-width){
			$(this).window('move', {
				"left":parentWidth-width
			});
		}
		if(top > parentHeight-height){
			$(this).window('move', {
				"top":parentHeight-height
			});
		}
	}
};
$.fn.panel.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
 */

/**
 * 
 * @param para_url:URL
 * @param para_type: POST/GET,默认POST
 * @param para_data:传入参数
 * @param para_retFun:回调函数
 * @param prar_async:同步:false,异步:true;默认true
 * @param para_cache:浏览器缓存,true:加载缓存;false:不加载缓存;默认false
 * 传入参数个数length =arguments.length:
 *   length==2:则第一个为para_url,第二个为para_retFun,其他取默认
 *   length==3:则第一个为para_url,第二个为para_data,第三个为para_retFun,其他取默认
 *   length=4:则第一个为para_url,第二个为para_type,第三个为para_data,第四个为para_retFun,其他取默认
 *   length=5:则前四个不变,第五个为prar_async,其他取默认
 *   length=6:则前四个不变,第六个为para_cache
 */
function ajaxSubmit(para_url,para_type,para_data,para_retFun,prar_async,para_cache){
	var url,data,retFun;
	url="";
	data="";
	retFun="";
	var type="post";
	var async=true;
	var cache = false;
	// 获取被传递参数的数值。   
	var numargs = arguments.length;   
	// 获取期望参数的数值。   
    var expargs = ajaxSubmit.length;   
    if(numargs==2){
    	url=arguments[0];
    	retFun=arguments[1];
    }else if(numargs==3){
    	url=arguments[0];
    	data=arguments[1];
    	retFun=arguments[2];
    }else if(numargs>=4){
    	url=arguments[0];
    	var tempTpye=arguments[1];
    	if(tempTpye=="get"||tempTpye=="GET"){
    		type=tempTpye;
    	}
    	data=arguments[2];
    	retFun=arguments[3];
    	if(numargs==5){
    		async=arguments[4];
    	}
    	if(numargs==6){
    		cache=arguments[5];
    	}
    }
	$.ajax({
		url:url,
		type:type,
		data:data,
		dataType:"json",
		async:async,
		cache:cache,
		error:function(request,status,errorThrown){
//			$.unblockUI();
			$.messager.alert(errorThrown.message);
		},
		success:function(result,status){
//			$.unblockUI();
			var data = result;
			//回调函数
			retFun(data);
		}
	});
}
/**
 * 字典加载-异步加载
 * @returns {DataDictionary}
 */
var _dicObject;//字典变量
function DataDictionary() {
	_dicObject=null;
	_dicObject=this;
	this.map = new HashMap();
	this.arrayList = new Array();
	//id:select的id;type:字典类型
	this.addDic = function(id, type) {
		this.map.put(id,type);
	};
	this.dicAjax=function(){
		var temp=this.map;
		var arr =this.map.values();
		var data = "dicArray="+this.map.values();
		//alert(data);
		ajaxSubmit(serverName+"/dicRequest/getAllDic.do",data ,dicAjaxRetFun);
	};
}

/**
 * 字典加载-同步加载
 * @returns {DataDictionary}
 */
function DataDictionaryBySynchronize() {
	_dicObject=null;
	_dicObject=this;
	this.map = new HashMap();
	this.arrayList = new Array();
	//id:select的id;type:字典类型
	this.addDic = function(id, type) {
		this.map.put(id,type);
	};
	this.dicAjax=function(){
		var temp=this.map;
		var arr =this.map.values();
		var data = "dicArray="+this.map.values();
		//alert(data);
		ajaxSubmit(serverName+"/dicRequest/getAllDic.do","post",data ,dicAjaxRetFun,false);
	};
}

function dicAjaxRetFun(jsonString) {
	//alert(_dicObject.map.values());
	//key:字典类型,value:字典对象list
	$.each(jsonString, function(key, value) {
		var keySet =_dicObject.map.keySet();
		var len  = keySet.length;
		if(len >0){
			for(var i = 0 ;i<len;i++){
				//var _key = keySet[i];
				var arr = keySet[i].split("_");
				var _key = arr[0];
				var validType="";
				if(arr.length==2){
					validType =arr[1];
				}else if(arr.length>2){
					validType =arr[arr.length-1];
				}
				var _value=_dicObject.map.get(keySet[i]);
				if(_value == key){
//					$("#"+_key).append("");
//					$("#"+_key).append("<option value=''>---请选择---</option>");
//					$.each(value, function(i) {
//						$("#"+_key).append("<option value='" + value[i].codeKey + "'>"+ value[i].codeValue + "</option>");
//					});
					if(validType=="Y"){
						$("#"+_key+"").combobox({
							data:value,
							valueField:'codeKey',
							textField:'codeVlue',
							mode:'remote',
							editable:false,
							multiple:false,
							panelHeight:'auto',
							validType:'selectValueRequired["'+_key+'","请选择"]'
						});  
					}else{
						$("#"+_key+"").combobox({
							data:value,
							valueField:'codeKey', 
							textField:'codeVlue',
							mode:'remote',
							editable:false,
							multiple:false,
							panelHeight:'auto'
						});
					}
					
					var data =$("#"+_key+"").combobox("getData");
					if(data.length > 8){
						$("#"+_key+"").combobox({panelHeight:200});
					}
					
					if(_key == "urgeSummarize"){
						$("#"+_key+"").combobox({panelHeight:200});
					}
					if(_key =="urgeRemark"){
						$("#"+_key+"").combobox({panelHeight:200});
					}
				}
			}
		}
		
	});

}



/**
 * ajax文件上传
 */
function ajaxFileUpload(url,fileId,ajaxReturnFunction){
	$.ajaxFileUpload
    (
        {
            url:url,		//用于文件上传的服务器端请求地址
            secureuri:false,								//一般设置为false
            fileElementId:fileId,							//文件上传空间的id属性  <input type="file" id="file" name="file" />
            dataType:"json",								//返回值类型 一般设置为json
            success: function (result, status)  			//服务器成功响应处理函数
            {
            	var data = result.returnMsg;
            	ajaxReturnFunction(data);
                //从服务器返回的json中取出message中的数据,其中message为在struts2中action中定义的成员变量
                if(typeof(result.error) != 'undefined')
                {
                    if(result.error != '')
                    {
                        alert(result.error);
                    }else
                    {
                        alert(result.message);
                    }
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown)//服务器响应失败处理函数
            {
                alert(errorThrown.message);
            }
        }
    );
}

//除法函数，用来得到精确的除法结果
//说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。
//调用：accDiv(arg1,arg2)
//返回值：arg1除以arg2的精确结果

function accDiv(arg1,arg2){
	var t1=0,t2=0,r1,r2;
	try{
		t1=arg1.toString().split(".")[1].length;
	}catch(e){}
	try{
		t2=arg2.toString().split(".")[1].length;
	}catch(e){}
	with(Math){
		r1=Number(arg1.toString().replace(".",""));
		r2=Number(arg2.toString().replace(".",""));
	return (r1/r2)*pow(10,t2-t1);
	}
}
//给Number类型增加一个div方法，调用起来更加方便。
Number.prototype.div = function (arg){
return accDiv(this, arg);
};


//乘法函数，用来得到精确的乘法结果
//说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
//调用：accMul(arg1,arg2)
// 返回值：arg1乘以arg2的精确结果
function accMul(arg1,arg2){
	var m=0,s1=arg1.toString(),s2=arg2.toString();
	try{
		m+=s1.split(".")[1].length;
	}catch(e){}
	try{
		m+=s2.split(".")[1].length;
	}catch(e){}
	return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
}
//给Number类型增加一个mul方法，调用起来更加方便。
Number.prototype.mul = function (arg){
	return accMul(arg, this);
};



//加法函数，用来得到精确的加法结果
// 说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
//调用：accAdd(arg1,arg2)
//返回值：arg1加上arg2的精确结果
function accAdd(arg1,arg2){
	var r1,r2,m;
	try{
		r1=arg1.toString().split(".")[1].length;
	}catch(e){r1=0;}
	try{
		r2=arg2.toString().split(".")[1].length;
	}catch(e){r2=0;}
	m=Math.pow(10,Math.max(r1,r2));
	return (arg1*m+arg2*m)/m;
}
	//给Number类型增加一个add方法，调用起来更加方便。
Number.prototype.add = function (arg){
	return accAdd(arg,this);
};


/**
 * js 四舍五入
 *  // param srcStr表示要格式化的数 param nAfterDot 要保留的位数
 * @param srcStr 表示要格式化的数
 * @param nAfterDot 要保留的位数
 * @returns
 */
function limitNumber(srcStr,nAfterDot){
      var srcStr,nAfterDot;
      var resultStr,nTen;
      srcStr = ""+srcStr+"";
      strLen = srcStr.length;
      dotPos = srcStr.indexOf(".",0);
      if (dotPos == -1){
          resultStr = srcStr+".";
          for (i=0;i<nAfterDot;i++){
              resultStr = resultStr+"0";
          }
          return resultStr;
      } else{
          if ((strLen - dotPos - 1) >= nAfterDot){
              nAfter = dotPos + nAfterDot + 1;
              nTen =1;
              for(j=0;j<nAfterDot;j++){
              nTen = nTen*10;
          }
          resultStr = Math.round(parseFloat(srcStr)*nTen)/nTen;
          return resultStr;
          } else{
              resultStr = srcStr;
              for (i=0;i<(nAfterDot - strLen + dotPos + 1);i++){
                  resultStr = resultStr+"0";
              }
              return resultStr;
          }
      }
} 

	
//--HashMap构造函数--
function HashMap(){
    this.length = 0;
    this.prefix = "hashmap_prefix_20040716_";
	//向HashMap中添加键值对
	this.put = function (key, value){
		this[this.prefix + key] = value;
		this.length ++;
	};
	//从HashMap中获取value值
	this.get = function(key){
		return typeof this[this.prefix + key] == "undefined"? null : this[this.prefix + key];
	};
	//从HashMap中获取所有key的集合，以数组形式返回
	this.keySet = function(){
		var arrKeySet = new Array();
		var index = 0;
		for(var strKey in this){
			if(strKey.substring(0,this.prefix.length) == this.prefix)
				arrKeySet[index ++] = strKey.substring(this.prefix.length);
		}
		return arrKeySet.length == 0 ? null : arrKeySet;
	};
	//从HashMap中获取value的集合，以数组形式返回
	this.values = function(){
		var arrValues = new Array();
		var index = 0;
		for(var strKey in this){
			if(strKey.substring(0,this.prefix.length) == this.prefix)
				arrValues[index ++] = this[strKey];
		}
		return arrValues.length == 0 ? null : arrValues;
	};
	//获取HashMap的value值数量
	this.size = function(){
		return this.length;
	};
	//删除指定的值
	this.remove = function(key){
		delete this[this.prefix + key];
		this.length --;
	};
	//清空HashMap
	this.clear = function(){
		for(var strKey in this){
			if(strKey.substring(0,this.prefix.length) == this.prefix)
				delete this[strKey];   
		}
		this.length = 0;
	};
	// 判断HashMap是否为空
	this.isEmpty = function(){
		return this.length == 0;
	};
	//判断HashMap是否存在某个key
	this.containsKey = function(key){
		for(var strKey in this){
			if(strKey == this.prefix + key){
		   		return true; 
		  	}
		}
		return false;
	};
	//判断HashMap是否存在某个value
	this.containsValue = function(value){
		for(var strKey in this){
		  	if(this[strKey] == value){
		   		return true;  
		   	}
		}
		return false;
	};
	//把一个HashMap的值加入到另一个HashMap中，参数必须是HashMap
	this.putAll = function(map){
		if(map == null){
			return;
		}
		if(map.constructor != HashMap){
			return;
		}
		var arrKey = map.keySet();
		var arrValue = map.values();
		for(var i in arrKey){
			this.put(arrKey[i],arrValue[i]);
		}
	}	;
	//转化为String
	this.toString = function(){
		var str = "";
		for(var strKey in this){
			if(strKey.substring(0,this.prefix.length) == this.prefix){
				 str += strKey.substring(this.prefix.length)+ " : " + this[strKey] + "\r\n";
			}
		}
		return str;
	};
	//以字符串返回所有键值对用","分隔
	this.getIterator = function(){
	    var str = "";
	    for(var strKey in this)
	    {
	        if(strKey.substring(0,this.prefix.length) == this.prefix)
	              str += strKey.substring(this.prefix.length)+ " = " + this[strKey] + ";";
	    }
	    return str;
	};
}
	

/**
 * @description 获得当前时间
 * @return {String}
 */
function getCurrentTime(){
    var time = new Date();
    var hours = time.getHours(); //获取当前小时数(0-23)
    var minutes = time.getMinutes(); //获取当前分钟数(0-59)
    var seconds = time.getSeconds(); //获取当前秒数(0-59)
    return hours + ":" + minutes + ":" + seconds;
}

// js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath() {
	// 获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	var curWwwPath = window.document.location.href;
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	// 获取主机地址，如： http://localhost:8083
	var localhostPaht = curWwwPath.substring(0, pos);
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName);
}

//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}

function clockon() {
    var now = new Date();
    var year = now.getFullYear(); //getFullYear getYear
    var month = now.getMonth();
    var date = now.getDate();
    var day = now.getDay();
    var hour = now.getHours();
    var minu = now.getMinutes();
    var sec = now.getSeconds();
    var week;
    month = month + 1;
    if (month < 10) month = "0" + month;
    if (date < 10) date = "0" + date;
    if (hour < 10) hour = "0" + hour;
    if (minu < 10) minu = "0" + minu;
    if (sec < 10) sec = "0" + sec;
    var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
    week = arr_week[day];
    var time = "";
    time = year + "年" + month + "月" + date + "日" + " " + hour + ":" + minu + ":" + sec + " " + week;

    $("#bgclock").html(time);

    var timer = setTimeout("clockon()", 200);
}

//减法函数，用来得到精确的减法结果
//说明：javascript的减法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的减法结果。
//调用：accSubtr(arg1,arg2)
//返回值：arg1减去arg2的精确结果
function accSubtr(arg1,arg2){
	var r1,r2,m,n;
	try{
		r1=arg1.toString().split(".")[1].length;
	}catch(e){
		r1=0;
	}
	try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;}
	m=Math.pow(10,Math.max(r1,r2));
	//动态控制精度长度
	n=(r1>=r2)?r1:r2;
	return ((arg1*m-arg2*m)/m).toFixed(n);
} 

function tableTrValidate(nameString,tableId){
	var length =$("#"+tableId+" tbody tr td:nth-child(2)").length;
	for(var i = 0 ; i < length-1 ;i++){
		$.each($("[name ^= '"+nameString+"["+i+"]']"),function(j,element){
			$("#"+element.id).blur(function(){
				var elementName = element.name.substring(element.name.indexOf("[")+1, element.name.indexOf("]"));
				var valueq ="";
				$.each($("[name ^= '"+nameString+"["+elementName+"]']"),function(l,elementObject){
					if($("#"+elementObject.id).val() != undefined){
						valueq = valueq+$("#"+elementObject.id).val();
					}
				});
				if(valueq != null && valueq != "" ){
					$.each($("[name ^= '"+nameString+"["+elementName+"]']"),function(k,elementObject){
						$("#"+elementObject.id).validatebox({
							required:true
						});
						$("#"+elementObject.id).validatebox('validate');
					});
				}else{
					$.each($("[name ^= '"+nameString+"["+elementName+"]']"),function(k,elementObject){
						$("#"+elementObject.id).validatebox({
							required:false
						});
						$("#"+elementObject.id).validatebox('validate');
					});
				}
			});
		});
	}
}
/** wyf-datagrid生成滚动条 **/
function defaultHaveScroll(gridid){ 
    var opts=$('#'+gridid).datagrid('options'); 
    var text='{'; 
    for(var i=0;i<opts.columns.length;i++){
       var inner_len=opts.columns[i].length; 
       for(var j=0;j<inner_len;j++){ 
           if((typeof opts.columns[i][j].field)=='undefined')break; 
            text+="'"+opts.columns[i][j].field+"':''"; 
            if(j!=inner_len-1){ 
                text+=","; 
            } 
       } 
    } 
    text+="}"; 
    text=eval("("+text+")"); 
    var data={"total":1,"rows":[text]}; 
    $('#'+gridid).datagrid('loadData',data); 
   $("tr[datagrid-row-index='0']").css({"visibility":"hidden"}); 
} 
/** wyf datagrid时间格式化 **/
function DateFormatter(value, rec, index) {
    if (!value && typeof(value)!="undefined") {
        return "";
    }
    var format = 'yyyy-MM-dd';
    return formatDate(value,format);
}
function DateTimeFormatter(value, rec, index) {
    if (!value && typeof(value)!="undefined") {
        return "";
    }
    var format = 'yyyy-MM-dd HH:mm:ss';
    return formatDate(value,format);
}
/** 转时间格式 **/
function formatDate(time,format){
	var t = new Date(time);
    var tf = function(i){
    	return (i < 10 ? '0' : '') + i;
    };
    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
        switch(a){
            case 'yyyy':
                return tf(t.getFullYear());
                break;
            case 'MM':
                return tf(t.getMonth() + 1);
                break;
            case 'mm':
                return tf(t.getMinutes());
                break;
            case 'dd':
                return tf(t.getDate());
                break;
            case 'HH':
                return tf(t.getHours());
                break;
            case 'ss':
                return tf(t.getSeconds());
                break;
        }
    });
}
function provinceShowPublic(province, city, county){
	var cityHome;
	var provinceHome;
				var urlCity = serverName+"/NSC/selectByCode.do";
				ajaxSubmit(serverName+"/NSC/selectByAreaCode.do",{},function(resultMap){
					if(resultMap != null && resultMap != ""){
					 $("#" + province).combobox(
							{
								editable : false,
								//url : serverName + '/NSC/list.do',
								data : resultMap.nationalStandardCodeSheng,
								textField : 'cityName',
								valueField : 'cityCode',
								mode : 'remote',
								delay : 500,
								width : '150',
								value:resultMap.nationalStandardCodeShi.parentId,
								onSelect : function(value) {
									$("#" + city).combobox("clear");
									$("#" + county).combobox("setValue","");
									 $("#" + city).combobox({
											editable : false,
											url : serverName+ '/NSC/listCity.do?parentId='+ value.cityCode,
											textField : 'cityName',
											valueField : 'cityCode',
											mode : 'remote',
											delay : 500,
											width : '150',
											value:""
										});
									
								}
							});
					 $("#" + city).combobox({
									editable : false,
									//url : serverName+ '/NSC/listCity.do',
									data : resultMap.nationalStandardCodeShiList,
									textField : 'cityName',
									valueField : 'cityCode',
									mode : 'remote',
									delay : 500,
									width : '150',
									value:resultMap.nationalStandardCodeShi.cityCode,
									onSelect : function(value) {
										$("#" + county).combobox("setValue","");
										$("#" + county).combobox(
												{
													editable : false,
													url : serverName
															+ '/NSC/listCity.do?parentId='
															+ value.cityCode,
													textField : 'cityName',
													valueField : 'cityCode',
													mode : 'remote',
													width : '150',
													delay : 500,
													value:""
												});
									}
								});
					 $("#" + county).combobox(
								{
									editable : false,
									//url : serverName + '/NSC/listCity.do',
									data : resultMap.nationalStandardCodeQuList,
									textField : 'cityName',
									valueField : 'cityCode',
									mode : 'remote',
									width : '150',
									delay : 500,
									value: resultMap.nationalStandardCodeQu.cityCode,
									onSelect:function(value){
										 
										 $("#"+town).combobox({
												url : serverName + '/town/list.do?parentId='+value.cityCode,
												textField : 'townshipinfName',
												valueField : 'townshipinfoid',
												mode : 'remote',
												delay : 500,
												width:'150', 
												value:""
											});
									}
								});
					 
//				ajaxSubmit(serverName+"/NSC/selectByAreaCode.do",{},function(parentId){
//					if(parentId != null && parentId != ""){
//						cityHome = parentId.nationalStandardCode.parentId;
//						countryId = parentId.nationalStandardCode.cityCode;
//						ajaxSubmit(urlCity,{cityCode:cityHome},function(parentId){
//							provinceHome = parentId.parentId;
//							$("#" + province).combobox(
//									{
//										editable: false,
//										url : serverName + '/NSC/list.do',
//										textField : 'cityName',
//										valueField : 'cityCode',
//										mode :'remote',
//										delay :500,
//										width :'150',
//										value:provinceHome,
//										onSelect: function(value) {
//											$("#" + city).combobox("clear");
//											$("#" + county).combobox("clear");
//											$("#" + city).combobox({
//												editable : false,
//												url : serverName+ '/NSC/listCity.do?parentId='+ value.cityCode,
//												textField : 'cityName',
//												valueField : 'cityCode',
//												mode : 'remote',
//												delay : 500,
//												width : '150',
//												value:""
//											});
//										}
//									});
//							$("#" + county).combobox({
//								url : serverName + '/NSC/listCity.do?parentId=' + cityHome,
//								textField : 'cityName',
//								valueField : 'cityCode',
//								mode : 'remote',
//								delay : 500,
//								width : '150',
//								value : countryId
//							});
//							$("#" + city).combobox({
//								editable : false,
//								url : serverName + '/NSC/listCity.do?parentId=' + provinceHome,
//								textField : 'cityName',
//								valueField : 'cityCode',
//								mode : 'remote',
//								delay : 500,
//								width : '150',
//								value : cityHome,
//								onSelect:function(cityNSC){
//									$("#" + county).combobox("setValue","");
//									$("#" + county).combobox({
//										editable : false,
//										url : serverName + '/NSC/listCity.do?parentId=' + cityNSC.cityCode,
//										textField : 'cityName',
//										valueField : 'cityCode',
//										mode : 'remote',
//										delay : 500,
//										width : '150',
//										value:""
//									});
//								}
//							
//							});
//							
//						});
					}else{
						$("#" + province).combobox(
								{
									editable : false,
									url : serverName + '/NSC/list.do',
									textField : 'cityName',
									valueField : 'cityCode',
									mode : 'remote',
									delay : 500,
									width : '150',
									value:provinceHome,
									onSelect : function(value) {
										$("#" + city).combobox("clear");
										$("#" + county).combobox("clear");
										 $("#" + city).combobox({
												editable : false,
												url : serverName+ '/NSC/listCity.do?parentId='+ value.cityCode,
												textField : 'cityName',
												valueField : 'cityCode',
												mode : 'remote',
												delay : 500,
												width : '150',
												value:""
											});
										
									}
								});
						 $("#" + city).combobox({
										editable : false,
										url : serverName+ '/NSC/listCity.do',
										textField : 'cityName',
										valueField : 'cityCode',
										mode : 'remote',
										delay : 500,
										width : '150',
										value:"",
										onSelect : function(value) {
											$("#" + county).combobox("setValue","");
											$("#" + county).combobox(
													{
														editable : false,
														url : serverName
																+ '/NSC/listCity.do?parentId='
																+ value.cityCode,
														textField : 'cityName',
														valueField : 'cityCode',
														mode : 'remote',
														width : '150',
														delay : 500,
														value:""
													});
										}
									});
						 $("#" + county).combobox(
									{
										editable : false,
										url : serverName
												+ '/NSC/listCity.do',
										textField : 'cityName',
										valueField : 'cityCode',
										mode : 'remote',
										width : '150',
										delay : 500,
										value:""
									});
						 
					}
				});
	 
}



function provinceShowPublicWidth(province, city, county){
	var cityHome;
	var provinceHome;
				var urlCity = serverName+"/NSC/selectByCode.do";
				ajaxSubmit(serverName+"/NSC/selectByAreaCode.do",{},function(resultMap){
					if(resultMap != null && resultMap != ""){
					 $("#" + province).combobox(
							{
								editable : false,
								//url : serverName + '/NSC/list.do',
								data : resultMap.nationalStandardCodeSheng,
								textField : 'cityName',
								valueField : 'cityCode',
								mode : 'remote',
								delay : 500,
								width : '100',
								value:resultMap.nationalStandardCodeShi.parentId,
								onSelect : function(value) {
									$("#" + city).combobox("clear");
									$("#" + county).combobox("setValue","");
									 $("#" + city).combobox({
											editable : false,
											url : serverName+ '/NSC/listCity.do?parentId='+ value.cityCode,
											textField : 'cityName',
											valueField : 'cityCode',
											mode : 'remote',
											delay : 500,
											width : '100',
											value:""
										});
									
								}
							});
					 $("#" + city).combobox({
									editable : false,
									//url : serverName+ '/NSC/listCity.do',
									data : resultMap.nationalStandardCodeShiList,
									textField : 'cityName',
									valueField : 'cityCode',
									mode : 'remote',
									delay : 500,
									width : '100',
									value:resultMap.nationalStandardCodeShi.cityCode,
									onSelect : function(value) {
										$("#" + county).combobox("setValue","");
										$("#" + county).combobox(
												{
													editable : false,
													url : serverName
															+ '/NSC/listCity.do?parentId='
															+ value.cityCode,
													textField : 'cityName',
													valueField : 'cityCode',
													mode : 'remote',
													width : '100',
													delay : 500,
													value:""
												});
									}
								});
					 $("#" + county).combobox(
								{
									editable : false,
									//url : serverName + '/NSC/listCity.do',
									data : resultMap.nationalStandardCodeQuList,
									textField : 'cityName',
									valueField : 'cityCode',
									mode : 'remote',
									width : '100',
									delay : 500,
									value: resultMap.nationalStandardCodeQu.cityCode,
									onSelect:function(value){
										 
										 $("#"+town).combobox({
												url : serverName + '/town/list.do?parentId='+value.cityCode,
												textField : 'townshipinfName',
												valueField : 'townshipinfoid',
												mode : 'remote',
												delay : 500,
												width:'50', 
												value:""
											});
									}
								});
					 
//				ajaxSubmit(serverName+"/NSC/selectByAreaCode.do",{},function(parentId){
//					if(parentId != null && parentId != ""){
//						cityHome = parentId.nationalStandardCode.parentId;
//						countryId = parentId.nationalStandardCode.cityCode;
//						ajaxSubmit(urlCity,{cityCode:cityHome},function(parentId){
//							provinceHome = parentId.parentId;
//							$("#" + province).combobox(
//									{
//										editable: false,
//										url : serverName + '/NSC/list.do',
//										textField : 'cityName',
//										valueField : 'cityCode',
//										mode :'remote',
//										delay :500,
//										width :'150',
//										value:provinceHome,
//										onSelect: function(value) {
//											$("#" + city).combobox("clear");
//											$("#" + county).combobox("clear");
//											$("#" + city).combobox({
//												editable : false,
//												url : serverName+ '/NSC/listCity.do?parentId='+ value.cityCode,
//												textField : 'cityName',
//												valueField : 'cityCode',
//												mode : 'remote',
//												delay : 500,
//												width : '150',
//												value:""
//											});
//										}
//									});
//							$("#" + county).combobox({
//								url : serverName + '/NSC/listCity.do?parentId=' + cityHome,
//								textField : 'cityName',
//								valueField : 'cityCode',
//								mode : 'remote',
//								delay : 500,
//								width : '150',
//								value : countryId
//							});
//							$("#" + city).combobox({
//								editable : false,
//								url : serverName + '/NSC/listCity.do?parentId=' + provinceHome,
//								textField : 'cityName',
//								valueField : 'cityCode',
//								mode : 'remote',
//								delay : 500,
//								width : '150',
//								value : cityHome,
//								onSelect:function(cityNSC){
//									$("#" + county).combobox("setValue","");
//									$("#" + county).combobox({
//										editable : false,
//										url : serverName + '/NSC/listCity.do?parentId=' + cityNSC.cityCode,
//										textField : 'cityName',
//										valueField : 'cityCode',
//										mode : 'remote',
//										delay : 500,
//										width : '100',
//										value:""
//									});
//								}
//							
//							});
//							
//						});
					}else{
						$("#" + province).combobox(
								{
									editable : false,
									url : serverName + '/NSC/list.do',
									textField : 'cityName',
									valueField : 'cityCode',
									mode : 'remote',
									delay : 500,
									width : '100',
									value:provinceHome,
									onSelect : function(value) {
										$("#" + city).combobox("clear");
										$("#" + county).combobox("clear");
										 $("#" + city).combobox({
												editable : false,
												url : serverName+ '/NSC/listCity.do?parentId='+ value.cityCode,
												textField : 'cityName',
												valueField : 'cityCode',
												mode : 'remote',
												delay : 500,
												width : '100',
												value:""
											});
										
									}
								});
						 $("#" + city).combobox({
										editable : false,
										url : serverName+ '/NSC/listCity.do',
										textField : 'cityName',
										valueField : 'cityCode',
										mode : 'remote',
										delay : 500,
										width : '100',
										value:"",
										onSelect : function(value) {
											$("#" + county).combobox("setValue","");
											$("#" + county).combobox(
													{
														editable : false,
														url : serverName
																+ '/NSC/listCity.do?parentId='
																+ value.cityCode,
														textField : 'cityName',
														valueField : 'cityCode',
														mode : 'remote',
														width : '100',
														delay : 500,
														value:""
													});
										}
									});
						 $("#" + county).combobox(
									{
										editable : false,
										url : serverName
												+ '/NSC/listCity.do',
										textField : 'cityName',
										valueField : 'cityCode',
										mode : 'remote',
										width : '100',
										delay : 500,
										value:""
									});
						 
					}
				});
	 
}

//省省市区镇村 下拉框公用
//province :省下拉框的id
//city :市下拉框的id
//county :区县下拉框的id
//town :镇下拉框的id
//village :村id
//detailHome:详细
//homeAddress: 赋值id
//address2 : 第二个地址
function cityComboboxPublic(province, city, county,town,village,provinceHome,detailHome,homeAddress,address2){
	//provinceShowPublic(province, city, county);
	
	

	var cityHome;
	var provinceHome;
				var urlCity = serverName+"/NSC/selectByCode.do";
				ajaxSubmit(serverName+"/NSC/selectByAreaCode.do",{},function(resultMap){
					if(resultMap != null && resultMap != ""){
					 $("#" + province).combobox(
							{
								editable : false,
								//url : serverName + '/NSC/list.do',
								data : resultMap.nationalStandardCodeSheng,
								textField : 'cityName',
								valueField : 'cityCode',
								mode : 'remote',
								delay : 500,
								width : '150',
								value:resultMap.nationalStandardCodeShi.parentId,
								onSelect : function(value) {
									$("#" + city).combobox("clear");
									$("#" + county).combobox("setValue","");
									$("#"+town).combobox("clear");
									$("#"+village).combobox("clear");
									//if(province == "provinceHome"){
										addAddressPublic(true,province, city, county,town,village,detailHome,homeAddress,address2);
									//}else{
									//	addBusinessAddress();
									//}
									 $("#" + city).combobox({
											editable : false,
											url : serverName+ '/NSC/listCity.do?parentId='+ value.cityCode,
											textField : 'cityName',
											valueField : 'cityCode',
											mode : 'remote',
											delay : 500,
											width : '150',
											value:""
										});
									
								}
							});
					 $("#" + city).combobox({
									editable : false,
									//url : serverName+ '/NSC/listCity.do',
									data : resultMap.nationalStandardCodeShiList,
									textField : 'cityName',
									valueField : 'cityCode',
									mode : 'remote',
									delay : 500,
									width : '150',
									value:resultMap.nationalStandardCodeShi.cityCode,
									onSelect : function(value) {
										$("#" + county).combobox("setValue","");
										$("#"+town).combobox("clear");
										$("#"+village).combobox("clear");
										//if(province == "provinceHome"){
											addAddressPublic(true,province, city, county,town,village,detailHome,homeAddress,address2);
										//}else{
										//	addBusinessAddress();
										//}
										$("#" + county).combobox(
												{
													editable : false,
													url : serverName
															+ '/NSC/listCity.do?parentId='
															+ value.cityCode,
													textField : 'cityName',
													valueField : 'cityCode',
													mode : 'remote',
													width : '150',
													delay : 500,
													value:""
												});
									}
								});
					 $("#" + county).combobox(
								{
									editable : false,
									//url : serverName + '/NSC/listCity.do',
									data : resultMap.nationalStandardCodeQuList,
									textField : 'cityName',
									valueField : 'cityCode',
									mode : 'remote',
									width : '150',
									delay : 500,
									value: resultMap.nationalStandardCodeQu.cityCode,
									onSelect:function(value){
										$("#"+town).combobox("clear");
										$("#"+village).combobox("clear");
										//if(province == "provinceHome"){
											addAddressPublic(true,province, city, county,town,village,detailHome,homeAddress,address2);
										//}else{
										//	addBusinessAddress();
										//}
										 
										 $("#"+town).combobox({
												url : serverName + '/town/list.do?parentId='+value.cityCode,
												textField : 'townshipinfName',
												valueField : 'townshipinfoid',
												mode : 'remote',
												delay : 500,
												width:'150', 
												value:""
											});
									}
								});
					 
					 $("#"+town).combobox({
							//url : serverName + '/town/list.do?parentId='+countryIdUser,
						 data : resultMap.toList,
							textField : 'townshipinfName',
							valueField : 'townshipinfoid',
							mode : 'remote',
							delay : 500,
							width:'150', 
							value:"",
							onSelect : function(value) {
								$("#"+village).combobox("clear");
								//if(province == "provinceHome"){
									addAddressPublic(true,province, city, county,town,village,detailHome,homeAddress,address2);
								//}else{
								//	addBusinessAddress();
								//}
								$("#"+village).combobox({
									url : serverName+ '/town/list.do?parentId='+value.townshipinfoid,
									textField : 'townshipinfName',
									valueField : 'townshipinfoid',
									mode : 'remote',
									delay : 500,
									width:'150',
									value:"",
									onSelect:function(value){
										//if(province == "provinceHome"){
											addAddressPublic(true,province, city, county,town,village,detailHome,homeAddress,address2);
										//}else{
										//	addBusinessAddress();
										//}
									}
								});
							}
						});
					
////						cityHome = parentId.nationalStandardCode.parentId;
////						countryId = parentId.nationalStandardCode.cityCode;
////						ajaxSubmit(urlCity,{cityCode:cityHome},function(parentId){
////							provinceHome = parentId.parentId;
//							$("#" + province).combobox(
//									{
//										editable: false,
//										//url : serverName + '/NSC/list.do',
//										data : resultMap.nationalStandardCodeSheng,
//										textField : 'cityName',
//										valueField : 'cityCode',
//										mode :'remote',
//										delay :500,
//										width :'150',
//										value:resultMap.nationalStandardCodeShi.parentId/*,
//										onSelect: function(value) {
//											$("#" + city).combobox("clear");
//											$("#" + county).combobox("clear");
//											$("#"+town).combobox("clear");
//											$("#"+village).combobox("clear");
//											$("#" + city).combobox({
//												editable : false,
//												url : serverName+ '/NSC/listCity.do?parentId='+ value.cityCode,
//												textField : 'cityName',
//												valueField : 'cityCode',
//												mode : 'remote',
//												delay : 500,
//												width : '150',
//												value:""
//											});
//										}*/
//									});
//							$("#" + county).combobox({
//								//url : serverName + '/NSC/listCity.do?parentId=' + resultMap.nationalStandardCodeQu.parentId,
//								data : resultMap.nationalStandardCodeQuList,
//								textField : 'cityName',
//								valueField : 'cityCode',
//								mode : 'remote',
//								delay : 500,
//								width : '150'/*,
//								value : resultMap.nationalStandardCodeQu.cityCode*/
//							});
//							$("#" + city).combobox({
//								editable : false,
////								url : serverName + '/NSC/listCity.do?parentId=' + resultMap.nationalStandardCodeShi.parentId,
//								data : resultMap.nationalStandardCodeShiList,
//								textField : 'cityName',
//								valueField : 'cityCode',
//								mode : 'remote',
//								delay : 500,
//								width : '150',
//								value : resultMap.nationalStandardCodeShi.cityCode/*,
//								onSelect:function(cityNSC){
//									$("#" + county).combobox("setValue","");
//									$("#"+town).combobox("clear");
//									$("#"+village).combobox("clear");
//									$("#" + county).combobox({
//										editable : false,
//										url : serverName + '/NSC/listCity.do?parentId=' + cityNSC.cityCode,
//										textField : 'cityName',
//										valueField : 'cityCode',
//										mode : 'remote',
//										delay : 500,
//										width : '150',
//										value:""
//									});
//								}*/
//							
//							});
//							
////						});
					}else{
//						$("#" + province).combobox(
//								{
//									editable : false,
//									url : serverName + '/NSC/list.do',
//									textField : 'cityName',
//									valueField : 'cityCode',
//									mode : 'remote',
//									delay : 500,
//									width : '150',
//									value:provinceHome,
//									onSelect : function(value) {
//										$("#" + city).combobox("clear");
//										$("#" + county).combobox("clear");
//										$("#"+town).combobox("clear");
//										$("#"+village).combobox("clear");
//										
//										 $("#" + city).combobox({
//												editable : false,
//												url : serverName+ '/NSC/listCity.do?parentId='+ value.cityCode,
//												textField : 'cityName',
//												valueField : 'cityCode',
//												mode : 'remote',
//												delay : 500,
//												width : '150',
//												value:""
//											});
//										
//									}
//								});
//						 $("#" + city).combobox({
//										editable : false,
//										url : serverName+ '/NSC/listCity.do',
//										textField : 'cityName',
//										valueField : 'cityCode',
//										mode : 'remote',
//										delay : 500,
//										width : '150',
//										value:"",
//										onSelect : function(value) {
//											$("#" + county).combobox("setValue","");
//											$("#"+town).combobox("clear");
//											$("#"+village).combobox("clear");
//											$("#" + county).combobox(
//													{
//														editable : false,
//														url : serverName
//																+ '/NSC/listCity.do?parentId='
//																+ value.cityCode,
//														textField : 'cityName',
//														valueField : 'cityCode',
//														mode : 'remote',
//														width : '150',
//														delay : 500,
//														value:""
//													});
//										}
//									});
//						 $("#" + county).combobox(
//									{
//										editable : false,
//										url : serverName
//												+ '/NSC/listCity.do',
//										textField : 'cityName',
//										valueField : 'cityCode',
//										mode : 'remote',
//										width : '150',
//										delay : 500,
//										value:""
//									});
						
						$("#" + province).combobox(
								{
									editable : false,
									url : serverName + '/NSC/list.do',
									textField : 'cityName',
									valueField : 'cityCode',
									mode : 'remote',
									delay : 500,
									width : '150',
									value:"",
									onSelect : function(value) {
										$("#" + city).combobox("clear");
										$("#" + county).combobox("setValue","");
										$("#"+town).combobox("clear");
										$("#"+village).combobox("clear");
										//if(province == "provinceHome"){
											addAddressPublic(true,province, city, county,town,village,detailHome,homeAddress,address2);
										//}else{
										//	addBusinessAddress();
										//}
										 $("#" + city).combobox({
												editable : false,
												url : serverName+ '/NSC/listCity.do?parentId='+ value.cityCode,
												textField : 'cityName',
												valueField : 'cityCode',
												mode : 'remote',
												delay : 500,
												width : '150',
												value:"",
												onSelect : function(value) {
													$("#" + county).combobox("setValue","");
													$("#"+town).combobox("clear");
													$("#"+village).combobox("clear");
													//if(province == "provinceHome"){
														addAddressPublic(true,province, city, county,town,village,detailHome,homeAddress,address2);
													//}else{
													//	addBusinessAddress();
													//}
													$("#" + county).combobox(
															{
																editable : false,
																url : serverName
																		+ '/NSC/listCity.do?parentId='
																		+ value.cityCode,
																textField : 'cityName',
																valueField : 'cityCode',
																mode : 'remote',
																width : '150',
																delay : 500,
																value:"",
																onSelect:function(value){
																	$("#"+town).combobox("clear");
																	$("#"+village).combobox("clear");
																	//if(province == "provinceHome"){
																		addAddressPublic(true,province, city, county,town,village,detailHome,homeAddress,address2);
																	//}else{
																	//	addBusinessAddress();
																	//}
																	 
																	 $("#"+town).combobox({
																			url : serverName + '/town/list.do?parentId='+value.cityCode,
																			textField : 'townshipinfName',
																			valueField : 'townshipinfoid',
																			mode : 'remote',
																			delay : 500,
																			width:'150', 
																			value:"",
																			onSelect : function(value) {
																				$("#"+village).combobox("clear");
																				//if(province == "provinceHome"){
																					addAddressPublic(true,province, city, county,town,village,detailHome,homeAddress,address2);
																				//}else{
																				//	addBusinessAddress();
																				//}
																				$("#"+village).combobox({
																					url : serverName+ '/town/list.do?parentId='+value.townshipinfoid,
																					textField : 'townshipinfName',
																					valueField : 'townshipinfoid',
																					mode : 'remote',
																					delay : 500,
																					width:'150',
																					value:"",
																					onSelect:function(value){
																						//if(province == "provinceHome"){
																							addAddressPublic(true,province, city, county,town,village,detailHome,homeAddress,address2);
																						//}else{
																						//	addBusinessAddress();
																						//}
																					}
																				});
																			}
																		});
																}
															});
												}
											});
										
									}
								});
						 
					}
				});
	
	
}

//求 省市区镇村 和值
//paramValidate ： boolean,
//provinceHome :省下拉框的id
//cityHome :市下拉框的id
//countyHome :区县下拉框的id
//townHome :镇下拉框的id
//villageHome :村id
//detailHome:详细
//homeAddress: 赋值id
function addAddressPublic(paramValidate,provinceHome,cityHome,countyHome,townHome,villageHome,detailHome,Address,address2){
	if(paramValidate){
		var provinceHome=$("#"+provinceHome).combobox("getText");
		var cityHome=$("#"+cityHome).combobox("getText");
		var countyHome=$("#"+countyHome).combobox("getText");
		var townHome=$("#"+townHome).combobox("getText");
		var villageHome=$("#"+villageHome).combobox("getText");
		var detailHome=$("#"+detailHome).val();
		var homeAddress = provinceHome+"-"+cityHome+"-"+countyHome+"-"+townHome+"-"+villageHome+"-"+detailHome;
		if(Address != ""){
			$("#"+Address).val(homeAddress);
		}
		if(address2!= null &&  address2 != ""){
			$("#"+address2).val(homeAddress);
			$("#"+address2).validatebox("validate");
		}
	}
}
 

function showCityCombopublic(province, city, district) {
	var provinceid = $("#" + province).combobox("getValue");

	var province = $("#" + province).combobox(
					{
						// required : true,
						editable : false,
						url : serverName + '/NSC/list.do',
						textField : 'cityName',
						valueField : 'cityCode',
						mode : 'remote',
						delay : 500,
						width : '150',
						value : provinceid,
						onSelect : function(value) {
							$("#" + city).combobox(
											{
												editable : false,
												url : serverName
														+ '/NSC/listCity.do?parentId='
														+ value.cityCode,
												textField : 'cityName',
												valueField : 'cityCode',
												mode : 'remote',
												delay : 500,
												width : '150',
												value : '',
												onSelect : function(value) {
													$("#" + district).combobox(
																	{
																		editable : false,
																		url : serverName
																				+ '/NSC/listCity.do?parentId='
																				+ value.cityCode,
																		textField : 'cityName',
																		valueField : 'cityCode',
																		mode : 'remote',
																		width : '150',
																		delay : 500,
																		value : ''
																	});
												}
											});
						}
					});

	var ciryId = $("#" + city).combobox("getValue");
	var districtId =$("#" + district).combobox("getValue");

	if (provinceid != null && provinceid != undefined) {
		$("#" + city).combobox({
			editable : false,
			url : serverName + '/NSC/listCity.do?parentId=' + provinceid,
			textField : 'cityName',
			valueField : 'cityCode',
			mode : 'remote',
			panelHeight : 'auto',
			delay : 500,
			width : '150',
			value : ciryId
		});
	}
	if (ciryId != null && ciryId != undefined) {
		$("#" + district).combobox({
			editable : false,
			url : serverName + '/NSC/listCity.do?parentId=' + ciryId,
			textField : 'cityName',
			valueField : 'cityCode',
			mode : 'remote',
			panelHeight : 'auto',
			delay : 500,
			width : '150',
			value : districtId
		});
	}

}
function showCityCombopublicHight(province, city, district) {
	var provinceid = $("#" + province).combobox("getValue");

	var province = $("#" + province).combobox(
					{
						// required : true,
						editable : false,
						url : serverName + '/NSC/list.do',
						textField : 'cityName',
						valueField : 'cityCode',
						mode : 'remote',
						delay : 500,
						width : '150',
						value : provinceid,
						panelHeight:200,
						onSelect : function(value) {
							$("#" + city).combobox(
											{
												editable : false,
												url : serverName
														+ '/NSC/listCity.do?parentId='
														+ value.cityCode,
												textField : 'cityName',
												valueField : 'cityCode',
												mode : 'remote',
												delay : 500,
												width : '150',
												value : '',
												panelHeight:200,
												onSelect : function(value) {
													$("#" + district).combobox(
																	{
																		editable : false,
																		url : serverName
																				+ '/NSC/listCity.do?parentId='
																				+ value.cityCode,
																		textField : 'cityName',
																		valueField : 'cityCode',
																		mode : 'remote',
																		width : '150',
																		delay : 500,
																		value : '',
																		panelHeight:200
																	});
												}
											});
						}
					});

	var ciryId = $("#" + city).combobox("getValue");
	var districtId =$("#" + district).combobox("getValue");

	if (provinceid != null && provinceid != undefined) {
		$("#" + city).combobox({
			editable : false,
			url : serverName + '/NSC/listCity.do?parentId=' + provinceid,
			textField : 'cityName',
			valueField : 'cityCode',
			mode : 'remote',
			panelHeight : 'auto',
			delay : 500,
			width : '150',
			value : ciryId,
			panelHeight:120
		});
	}
	if (ciryId != null && ciryId != undefined) {
		$("#" + district).combobox({
			editable : false,
			url : serverName + '/NSC/listCity.do?parentId=' + ciryId,
			textField : 'cityName',
			valueField : 'cityCode',
			mode : 'remote',
			panelHeight : 'auto',
			delay : 500,
			width : '150',
			value : districtId,
			panelHeight:120
		});
	}

}
/**
 * 通过身份证判断是男是女
 * 
 * @param idCard
 *            15/18位身份证号码
 * @return '0'-男 '1'-女、
 */ 
function maleOrFemalByIdCard(IdCard){
    if(IdCard.length==15){  
        if(IdCard.substr(14,1)%2==0){  
            return '1';  
        }else{  
            return '0';  
        }  
    }else if(IdCard.length ==18){ 
        if(IdCard.substr(16,1)%2==0){  
            return '1';  
        }else{  
            return '0';  
        }  
    }
}
//--去除全部空格--
function TrimAll(s) {
	$(s).val($(s).val().replace(/\s+/g, ""));
}
//禁止输入空格,前台keydown事件
function prohibitBlank(s){
	$(s).keypress(function (){
		if(event.keyCode==32) return false;
	});
}


/**
 * 
 * @param id:ID
 * @param required:必填
 */
function departmentComboboxTree(id, required) {
	ajaxSubmit(serverName + "/easyUIController/getDepartmentComboboxTree.do", {}, function(r) {
		$("#" + id + "").combotree({
			data : r,
			multiple : true,
			editable : false,
			required : required,
			onCheck : function(record) {
			}
		});
	});

}
/**
 *
 * @param id:ID
 * @param required:必填
 * 获取分中心及分中心下的用户并放入EasyUITree里面
 */
function departmentComboboxTreeContainUsers(id, required) {
	ajaxSubmit(serverName + "/easyUIController/getDepartmentAndUsersComboboxTree.do", {}, function(r) {
		$("#" + id + "").combotree({
			data : r,
			multiple : true,
			editable : false,
			required : required,
			onCheck : function(record) {
			}
		});
	});

}

// --------输入框提示方法 manzhang----------

var tooltip ;

function showInputTip(tips) {
		var defaultCls = {
			'border' : '1px solid #333',
			'padding' : '1px',
			'color' : '#333',
			'background' : '#f7f5d1',
			'position' : 'absolute',
			'max-width' : '200px',
			'border-radius' : '4px',
			'-moz-border-radius' : '4px',
			'-webkit-border-radius' : '4px',
			'display' : 'none'
		};
		tooltip = $("<div id='celltip'></div>").appendTo('body');
		var event =window.event;
		if(event == undefined){
			event = arguments.callee.caller.arguments[0];
		}
			tooltip.css(defaultCls);
			tooltip.text(tips).css({
				top : (event.pageY + 10) + 'px',
				left : (event.pageX + 20) + 'px',
				'z-index' : $.fn.window.defaults.zIndex,
				display : 'block'
			});
};

function hideInputTip() {
	tooltip.css({'display' : 'none'});	
}

function departmentComboboxTreeWithPanelWidth(id, required,panelWidth) {
    ajaxSubmit(serverName + "/easyUIController/getDepartmentComboboxTree.do", {}, function(r) {
        $("#" + id + "").combotree({
            data : r,
            multiple : true,
            editable : false,
            required : required,
            panelWidth: panelWidth,
            onCheck : function(record) {
            }
        });
    });

}

function showCityCombopublicWidth(province, city, district) {
	var provinceid = $("#" + province).combobox("getValue");

	var province = $("#" + province).combobox(
					{
						// required : true,
						editable : false,
						url : serverName + '/NSC/list.do',
						textField : 'cityName',
						valueField : 'cityCode',
						mode : 'remote',
						delay : 500,
						width : '100',
						value : provinceid,
						onSelect : function(value) {
							$("#" + city).combobox(
											{
												editable : false,
												url : serverName
														+ '/NSC/listCity.do?parentId='
														+ value.cityCode,
												textField : 'cityName',
												valueField : 'cityCode',
												mode : 'remote',
												delay : 500,
												width : '100',
												value : '',
												onSelect : function(value) {
													$("#" + district).combobox(
																	{
																		editable : false,
																		url : serverName
																				+ '/NSC/listCity.do?parentId='
																				+ value.cityCode,
																		textField : 'cityName',
																		valueField : 'cityCode',
																		mode : 'remote',
																		width : '100',
																		delay : 500,
																		value : ''
																	});
												}
											});
						}
					});

	var ciryId = $("#" + city).combobox("getValue");
	var districtId =$("#" + district).combobox("getValue");

	if (provinceid != null && provinceid != undefined) {
		$("#" + city).combobox({
			editable : false,
			url : serverName + '/NSC/listCity.do?parentId=' + provinceid,
			textField : 'cityName',
			valueField : 'cityCode',
			mode : 'remote',
			panelHeight : 'auto',
			delay : 500,
			width : '100',
			value : ciryId
		});
	}
	if (ciryId != null && ciryId != undefined) {
		$("#" + district).combobox({
			editable : false,
			url : serverName + '/NSC/listCity.do?parentId=' + ciryId,
			textField : 'cityName',
			valueField : 'cityCode',
			mode : 'remote',
			panelHeight : 'auto',
			delay : 500,
			width : '100',
			value : districtId
		});
	}

}