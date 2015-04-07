/**
 * 覆盖全局的onBeforeDestroy事件
 */
$.fn.panel.defaults = $.extend({},
		$.fn.panel.defaults,{
			onBeforeDestroy:function(){
				var frame = $("iframe",this);
				if(frame.length>0){
					frame[0].contentWindow.document.write("");
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
    $.blockUI({ message: "处理中，请稍候..."}); 
	$.ajax({
		url:url,
		type:type,
		data:data,
		dataType:"json",
		async:async,
		cache:cache,
		error:function(request,status,errorThrown){
			 $.unblockUI();
			$.messager.alert(errorThrown.message);
		},
		success:function(result,status){
			 $.unblockUI();
			var data = result;
			//回调函数
			retFun(data);
		}
	});
}
/**
 * 字典加载
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
//		alert(data);
		ajaxSubmit(getRootPath()+"/dicRequest/getAllDic.do",data ,dicAjaxRetFun);
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
				if(arr.length>1){
					var defaultValue = arr[1];
				}else{
					var defaultValue = "";
				}
				var _value=_dicObject.map.get(keySet[i]);
				if(_value == key){
//					$("#"+_key).append("");
//					$("#"+_key).append("<option value=''>---请选择---</option>");
//					$.each(value, function(i) {
//						$("#"+_key).append("<option value='" + value[i].codeKey + "'>"+ value[i].codeValue + "</option>");
//					});
					$("#"+_key+"").combobox({   
						data:value,   
						valueField:'codeKey',   
						textField:'codeVlue',
						mode:'remote',
						value:defaultValue,
						editable:false
					});  
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

/**
 * ajax文件删除
 * url :请求deleteUpLoad.action")
 * file:文件名
 * deleteReteurn:成功回调函数
 * function :用于新增上传时删除
 */

function ajaxDeleteUpLoad(fileName){
	$.ajax({
		url:"deleteUpLoad.action",
		type:"post",
		data:"fileDownName="+fileName,
		dataType:"json",
		error:function(request,status,errorThrown){
			alert(errorThrown.message);
		},
		success:function(result,status){
			var data = result.returnMsg;
			//回调函数必须为deleteReteurn,固定死的
			deleteReteurn(data);
		}
	});
}

/**
 * 
 * @param fileName 删除文件名
 * @param deleteReteurn 删除回调
 * function :用于查询后删除
 */
function ajaxDeleteUpLoadByRerun(fileName,deleteReteurn){
	$.ajax({
		url:"deleteUpLoad.action",
		type:"post",
		data:"fileDownName="+fileName,
		dataType:"json",
		error:function(request,status,errorThrown){
			alert(errorThrown.message);
		},
		success:function(result,status){
			var data = result.returnMsg;
			//回调函数
			deleteReteurn(data);
		}
	});
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
function addTab(subtitle,url){
	if(!$('#tabs').tabs('exists',subtitle)){
		$('#tabs').tabs('add',{
			title:subtitle,
			content:createFrame(url),
			closable:true,
			width:$('#mainPanle').width()-10,
			height:$('#mainPanle').height()-26,
			onClose:function(title){
				var frame = $('iframe',this);
				if(frame.length>0){
					frame[0].contentWindow.document.write("");
					frame[0].contentWindow.close();
					frame.remove();
					if($.browser.msie){
						CollectGarbage();
					}
				}
			}
		});
	}else{
		$('#tabs').tabs('select',subtitle);
	}
	tabClose();
}

function createFrame(url)
{
	var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}

function tabClose()
{
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children("span").text();
		$('#tabs').tabs('close',subtitle);
	});

	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});
		
		var subtitle =$(this).children("span").text();
		$('#mm').data("currtab",subtitle);
		
		return false;
	});
}
//绑定右键菜单事件
function tabCloseEven()
{
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			$('#tabs').tabs('close',t);
		});	
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t!=currtab_title)
				$('#tabs').tabs('close',t);
		});	
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	});
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