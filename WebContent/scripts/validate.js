/** 扩展自定义验证 * */
	$.fn.extend({
		 /**
			 * 修改DataGrid对象的默认大小，以适应页面宽度。
			 * 
			 * @param heightMargin
			 *            高度对页内边距的距离。
			 * @param widthMargin
			 *            宽度对页内边距的距离。
			 * @param minHeight
			 *            最小高度。
			 * @param minWidth
			 *            最小宽度。
			 * 
			 */
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
$.extend($.fn.validatebox.defaults.rules, {
	emptyValidate : {
		validator : function(value1, param) {
			var section = $("#"+param[0]).combobox("getValue");
			if (section == "") {
				$.fn.validatebox.defaults.rules.remoteValidate.message = param[1];
				return false;
			}
		},
		message : ''

	},
	remoteValidate : {
		validator : function(value1, param) {
			var section = document.getElementById("" + param[0] + "").value;
			if (section == "") {
				$.fn.validatebox.defaults.rules.remoteValidate.message = param[1];
				return false;
			} else {
				if(value1=="请选择"){
					$.fn.validatebox.defaults.rules.remoteValidate.message = "请选择";
					return false;
				}
				var postdata = {};
				postdata[param[2]] = value1;
				postdata["mysection"] = section;
				var result = $.ajax({
					url : param[4],
					data : postdata,
					async : false,
					type : "post"
				});
				if (result.responseText == "false") {
					$.fn.validatebox.defaults.rules.remoteValidate.message = param[3];
					return false;
				} else {
					return true;
				}
			}
		},
		message : ''

	},

	// 验证电话号码
	phoneNumber:{
		validator:function(value,param){
			if(value){
				 return /^[0-9]{11}$/.test(value);
			}else{
				return true;
			}
		},
		message : '请输入11位手机号码，或加了区号的固定电话'
	},

	// 验证电话号码郝强
	phoneNumberHQ:{
		validator:function(value,param){
			var a = /^[1][3-8]\d{9}$|^((\d{4}|\d{3})-(\d{7,8}))$/;
		    if (a.test(value) || value == "") {
		        return true;
		    }
		    else {
		        return false;
		    }
		},
		message : '请输入正确的11位手机号码'
	},

	// 验证姓名郝强
	nameHQ:{
		validator:function(value,param){
			 var a = /^[\\Α-\￥]+$/i;
			    var b = /^[A-Za-z]+$/i;
			    var c = /^[a-zA-Z\u4e00-\u9fa5]+$/;
			    if (a.test(value) || b.test(value) || value == "" || c.test(value)) {
			        return true;
			    }
			    else {
			        return false;
			    }

		},
		message : '请输入合规范的姓名'
	},
	// 验证 被0.5 整除
	numberDivided : {
		validator : function(value, param) {
			if (value) {
				return (value != 0)&& (value < 100) && (value % 0.5 ==0);
			} else {
				return true;
			}
		},
		message : '请输入 0 -100 之间 的 0.5 的整数倍.'
	},
	// 验证数字
	number : {
		validator : function(value, param) {
			if (value) {
				return /^[0-9]*[1-9][0-9]*$/.test(value);
			} else {
				return true;
			}
		},
		message : '请输入大于0的正整数.'
	},
	
	
	// 验证数字
	numberZeroOver : {
		validator : function(value, param) {
			if (value) {
				return (eval(value) > eval(0.00));
			} else {
				return true;
			}
		},
		message : '只能输入大于0的数字.'
	},
	numberZero : {
		validator : function(value, param) {
			if (value) {
				return /^[0-9]{6,20}$/.test(value);
			} else {
				return true;
			}
		},
		message : '请输入6~20位的数字'
	},
	numberOnly: {
		validator : function(value, param) {
			if (value) {
				return /^[0-9]*$/.test(value);
			} else {
				return true;
			}
		},
		message : '请输入数字'
	},
	numberOnlyLength: {
		validator : function(value, param) {
			if (value) {
				var min =param[0];
				var max = param[1];
				
				if(min<=value.length&&max>=value.length){
					return /^[0-9]*$/.test(value);
				}else{
					return false;
				}
			} else {
				return true;
			}
		},
		message : '请输入{0}-{1}内的数字'
	},
	// 验证非负数
	numberNonNegative : {
		validator : function(value, param) {
			if (value) {
				return /^[0-9]+\.?[0-9]*$/.test(value);
			} else {
				return true;
			}
		},
		message : '请输入大于0的数字'
	},
	persents : {
		validator : function(value, param) {
			if (value) {
				return /^[0-9]$/.test(value);
			} else {
				return true;
			}
		},
		message : '请输入 0 到 100 之间的数字！'
	},
	date : {
		validator : function(value, param) {
			if (value) {
				return /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})/.test(value);
			} else {
				return true;
			}
		},
		message : '请选择日期.'
	},
	select : {
		validator : function(value, param) {
			if (value == "" | value == null | value == undefined) {
				return false;
			} else {
				return true;
			}
		},
		message : '请选择日期.'
	},
	idnumberAll : {
		validator : function(value, param) {
			if (value) {
				return validNumberAll(value);
			} else {
				return true;
			}
		},
		message : '请输入正确的身份证号码.'
	},
	yearValidate : {
		validator : function(value, param) {
			if (value) {
				return ((/^[1-2][0-9]*$/.test(value))&& eval(value) < eval(2015) && eval(value) > eval(1900));
			} else {
				return true;
			}
		},
		message : '请输入正确的年份(1900 ~ 2015)'
	},
	selectValueRequired: {
		validator: function(value,param){
			var key = $("#"+param[0]).combobox("getValue");
			var r = key != '' && typeof(key) != "undefined" && key!='请选择';
			if(r){
				return r; 
			}else{
				$.fn.validatebox.defaults.rules.selectValueRequired.message = param[1];
				return false;
			}
		}, 
		message: '' 
	},
    /* 必须和某个字段相等 */
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'密码不相同'
    },
    notEqualTo: {
        validator:function(value,param){
        	var r = $("#"+param[0]).val() != value;
        	if(!r){
        		$.fn.validatebox.defaults.rules.notEqualTo.message = param[1];
        	}
        	return r;
        },
        message:''
    }
	
});


function validNumberAll(credentialsNumber){
	var result= true;
		var num = $.trim(credentialsNumber);
		if(num == null){ result = false;}
		var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
		var error;
		var varArray = new Array();
		var intValue;
		var lngProduct = 0;
		var intCheckDigit;
		var intStrLen = num.length;
		var idNumber = num;
		var area={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};
		// 地区检验
		if(area[parseInt(idNumber.substr(0,2))]==null) {result = false;}
		// initialize
		if ((intStrLen != 15) && (intStrLen != 18)) {
			result = false;
		}
		for (var i = 0; i < intStrLen; i++) {
			varArray[i] = idNumber.charAt(i);
			if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
				result = false;
			} else if (i < 17) {
				varArray[i] = varArray[i] * factorArr[i];
			}
		}
		if (intStrLen == 18) {
			// check date
			if (isValidityBrithBy18IdCard(idNumber) == false) {
				result = false;
			}
			// calculate the sum of the products
			for (i = 0; i < 17; i++) {
				lngProduct = lngProduct + varArray[i];
			}
			// calculate the check digit
			intCheckDigit = 12 - lngProduct % 11;
			switch (intCheckDigit) {
			case 10:
				intCheckDigit = 'X';
				break;
			case 11:
				intCheckDigit = 0;
				break;
			case 12:
				intCheckDigit = 1;
				break;
			}
			// check last digit
			if (varArray[17].toUpperCase() != intCheckDigit) {
				
				result = false;
			}
		} else { // length is 15
			// check date
			if (isValidityBrithBy15IdCard(idNumber) == false) {
				// alert("身份证日期信息有误！.");
				result = false;
			}
		} 
		return result;
	}


/**
 * 验证18位数身份证号码中的生日是否是有效生日
 * 
 * @param idCard
 *            18位书身份证字符串
 * @return
 */  
function isValidityBrithBy18IdCard(idCard18){   
   var year =  idCard18.substring(6,10);   
   var month = idCard18.substring(10,12);   
   var day = idCard18.substring(12,14);   
   var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
   // 这里用getFullYear()获取年份，避免千年虫问题
   if(temp_date.getFullYear()!=parseFloat(year)   
         ||temp_date.getMonth()!=parseFloat(month)-1   
         ||temp_date.getDate()!=parseFloat(day)){   
           return false;   
   }else{   
       return true;   
   }   
}   
 /**
	 * 验证15位数身份证号码中的生日是否是有效生日
	 * 
	 * @param idCard15
	 *            15位书身份证字符串
	 * @return
	 */  
 function isValidityBrithBy15IdCard(idCard15){   
     var year =  idCard15.substring(6,8);   
     var month = idCard15.substring(8,10);   
     var day = idCard15.substring(10,12);   
     var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
     // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法
     if(temp_date.getYear()!=parseFloat(year)   
             ||temp_date.getMonth()!=parseFloat(month)-1   
             ||temp_date.getDate()!=parseFloat(day)){   
               return false;   
       }else{   
           return true;   
       }   
 }  
 
 // 按照身份证 生成年龄
 function setAge(sid) {
		var ageValue = "";
		var len = sid.length;
		if(len == 18){
			var birthYearMonth = sid.substr(6, 6);
		} else if (len == 15){
			var birthYearMonth = 19 + sid.substr(6, 4);
		}
		if (birthYearMonth == null) {
			ageValue = "";
		} else {
			var today = new Date();
			ageValue = today.getFullYear()*100+today.getMonth()+1 - birthYearMonth;
		}
		if(Math.floor(ageValue/100) == 0){
			return "";
		}else{
			return Math.floor(ageValue/100);
		}
	}