// var dic = new DataDictionary();
// dic.addDic("businessType", "businessType");
// dic.addDic("defaultReturnmentWay", "defaultReturnmentWay");
// dic.dicAjax();
$(function() {
	$("#receivedType").combobox({
		valueField : 'id',
		textField : 'text',
		data : [ {
			"id" : "0",
			"text" : "正常还款类型"
		}, {
			"id" : "1",
			"text" : "提前还款类型"
		} ]
	});
	$("#receivedStatus").combobox({
		valueField : 'id',
		textField : 'text',
		data : [ {
			"id" : "4",
			"text" : "未预约"
		}, {
			"id" : "0",
			"text" : "预约中"
		}, {
			"id" : "1",
			"text" : "收款成功"
		}, {
			"id" : "2",
			"text" : "收款失败"
		}, {
			"id" : "3",
			"text" : "分配完成"
		}, {
			"id" : "5",
			"text" : "财务已撤销"
		} ]
	});
	$("#historyFlag").combobox({
		valueField : 'id',
		textField : 'text',
		data : [ {
			"id" : "F",
			"text" : "当前"
		}, {
			"id" : "T",
			"text" : "历史"
		} ]
	});
	$("#receivedRecordDataGrid").datagrid({
		url : serverName + "/receiveRecordList/receivedRecordDataGrid.do",
		method : 'post',
		loadMsg : "数据装载中....",
		fitColumns : true,
		nowrap : false,
		striped : true,
		singleSelect : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		rownumbers : true,
// frozenColumns : [ [ {
// field : 'creditapplicationId',
// title : '信贷申请单ID',
// width : 100,
// hidden : true
// }, {
// field : 'accountInfoId',
// title : '公司财务账号表ID',
// width : 100,
// hidden : true
// }, {
// field : 'receivedRecordId',
// title : '收款登记id',
// width : 100,
// hidden : true
// }, {
// field : 'borrowerServiceAppId',
// title : '还款人id',
// width : 100,
// hidden : true
// }, {
// field : 'businessNumber',
// title : '业务单号',
// width : 150,
// hidden : false
// }, {
// field : 'contactNumber',
// title : '合同编号',
// width : 100,
// hidden : true
// }, {
// field : 'borrowerName',
// title : '借款人姓名',
// width : 100,
// hidden : false
// }, {
// field : 'receivedTime',
// title : '实际还款日期',
// width : 100,
// hidden : false
// }, {
// field : 'receivedAmount',
// title : '收款登记金额',
// width : 100,
// hidden : false,
// formatter : function(value) {
// return value.toFixed(2);
// }
// }, {
// field : 'receivedType',
// title : '收款登记类型',
// width : 100,
// hidden : false,
// formatter : function(value) {
// if ("0" == value) {
// return "正常收款登记";
// } else if ("1" == value) {
// return "提前还款登记";
// } else if ("2" == value) {
// return "逾期还款登记";
// } else {
// return "Null";
// }
// }
// }, {
// field : 'receivedStatus',
// title : '收款状态',
// width : 100,
// hidden : false,
// formatter : function(value, rowData, rowIndex) {
// // if (rowData.historyFlag == "F") {
// if ("4" == value) {
// return "未预约";
// } else if ("0" == value) {
// return "预约中";
// } else if ("1" == value) {
// return "收款成功";
// } else if ("2" == value) {
// return "收款失败";
// } else if ("3" == value) {
// return "分配完成";
// } else if ("5" == value) {
// return "财务已撤销";
// } else {
// return "Null";
// }
// // } else if (rowData.historyFlag == "T") {
// // return "已撤销";
// // } else {
// // return "历史状态Null";
// // }
// }
// }, {
// field : 'historyFlag',
// title : '数据状态',
// width : 100,
// hidden : false,
// formatter : function(value, rowData, rowIndex) {
// if (value == "F") {
// return "有效数据";
// } else if (value == "T") {
// return "历史数据";
// } else {
// return "历史状态Null";
// }
// }
// }, {
// field : 'remark',
// title : '备注',
// width : 100,
// hidden : false
// }, {
// field : 'operate',
// title : '操作',
// width : 100,
// hidden : false,
// formatter : operate
// } ] ],
		columns : [ [ {
			field : 'operate',
			title : '操作',
			width : 100,
			hidden : false,
			formatter : operate
		}, {
			field : 'creditapplicationId',
			title : '信贷申请单ID',
			width : 100,
			hidden : true
		}, {
			field : 'accountInfoId',
			title : '公司财务账号表ID',
			width : 100,
			hidden : true
		}, {
			field : 'receivedRecordId',
			title : '收款登记id',
			width : 100,
			hidden : true
		}, {
			field : 'borrowerServiceAppId',
			title : '还款人id',
			width : 100,
			hidden : true
		}, {
			field : 'businessNumber',
			title : '业务单号',
			width : 150,
			hidden : false
		}, {
			field : 'contactNumber',
			title : '合同编号',
			width : 100,
			hidden : true
		}, {
			field : 'borrowerName',
			title : '借款人姓名',
			width : 100,
			hidden : false
		}, {
			field : 'receivedTime',
			title : '实际还款日期',
			width : 100,
			hidden : false
		}, {
			field : 'receivedAmount',
			title : '收款登记金额',
			width : 100,
			hidden : false,
			formatter : function(value) {
				return value.toFixed(2);
			}
		}, {
			field : 'receivedType',
			title : '收款登记类型',
			width : 100,
			hidden : false,
			formatter : function(value) {
				if ("0" == value) {
					return "正常收款登记";
				} else if ("1" == value) {
					return "提前还款登记";
				} else if ("2" == value) {
					return "逾期还款登记";
				} else {
					return "Null";
				}
			}
		}, {
			field : 'receivedStatus',
			title : '收款状态',
			width : 100,
			hidden : false,
			formatter : function(value, rowData, rowIndex) {
				// if (rowData.historyFlag == "F") {
				if ("4" == value) {
					return "未预约";
				} else if ("0" == value) {
					return "预约中";
				} else if ("1" == value) {
					return "收款成功";
				} else if ("2" == value) {
					return "收款失败";
				} else if ("3" == value) {
					return "分配完成";
				} else if ("5" == value) {
					return "财务已撤销";
				} else {
					return "Null";
				}
// } else if (rowData.historyFlag == "T") {
// return "已撤销";
// } else {
// return "历史状态Null";
// }
			}
		}, {
			field : 'historyFlag',
			title : '数据状态',
			width : 100,
			hidden : false,
			formatter : function(value, rowData, rowIndex) {
				if (value == "F") {
					return "有效数据";
				} else if (value == "T") {
					return "历史数据";
				} else {
					return "历史状态Null";
				}
			}
		}, {
			field : 'remark',
			title : '备注',
			width : 100,
			hidden : true
		}, {
			field : 'address',
			title : '地址',
			width : 100,
			hidden : true
		}, {
			field : 'defaultReturnmentWay',
			title : '默认还款方式',
			width : 100,
			hidden : true,
			formatter : function(value) {
				if ("0" == value) {
					return "自动划扣";
				} else if ("1" == value) {
					return "现金";
				} else {
					return "Null";
				}
			}
		}, {
			field : 'operatorId',
			title : '操作人ID',
			width : 100,
			hidden : true
		}, {
			field : 'operatorName',
			title : '操作人姓名',
			width : 100,
			hidden : true
		}, {
			field : 'capitalSource',
			title : '资金来源',
			width : 100,
			hidden : true,
			formatter : function(value) {
				if ("C" == value) {
					return "现金";
				} else if ("R" == value) {
					return "客户汇款";
				} else if ("A" == value) {
					return "个人卡划扣";
				} else {
					return "Null";
				}
			}
		}, {
			field : 'operateDate',
			title : '操作日期',
			width : 100,
			hidden : true
		}, {
			field : 'branchName',
			title : '分公司名称',
			width : 100,
			hidden : true
		}, {
			field : 'accountName',
			title : '账户名称',
			width : 100,
			hidden : true
		}, {
			field : 'account',
			title : '账户',
			width : 100,
			hidden : true
		}, {
			field : 'bankName',
			title : '银行名称',
			width : 100,
			hidden : true
		}, {
			field : 'accountType',
			title : '财务账户类型',
			width : 100,
			hidden : true,
			formatter : function(value) {
				if ("0" == value) {
					return "公司账户";
				} else if ("1" == value) {
					return "个人账户";
				} else {
					return "Null";
					// 你猜这是谁干的！！！！！！！！！！！！刘鑫
				}
			}
		} ] ],
		onLoadSuccess : function(data) {
			// console.info(data);
		},
		view : detailview,
		detailFormatter : function(index, row) {
			return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';
		},
		onExpandRow : function(index, row) {
			var temp = [];
			temp.push(row);
			$('#ddv-' + index).datagrid({
				fitColumns : true,
				singleSelect : true,
				rownumbers : true,
				loadMsg : '',
				height : 'auto',
				rowStyler : function(rowIndex, rowData) {
					return "background:#CCFFCC";
				},
				columns : [ [ {
					field : 'defaultReturnmentWay',
					title : '默认还款方式',
					width : 100,
					hidden : false,
					formatter : function(value) {
						if ("0" == value) {
							return "自动划扣";
						} else if ("1" == value) {
							return "现金";
						} else {
							return "Null";
						}
					}
				}, {
					field : 'operatorName',
					title : '操作人姓名',
					width : 100,
					hidden : false
				}, {
					field : 'capitalSource',
					title : '资金来源',
					width : 100,
					hidden : false,
					formatter : function(value) {
						if ("C" == value) {
							return "现金";
						} else if ("R" == value) {
							return "客户汇款";
						} else if ("A" == value) {
							return "个人卡划扣";
						} else {
							return "Null";
						}
					}
				}, {
					field : 'operateDate',
					title : '操作日期',
					width : 100,
					hidden : false
				}, {
					field : 'branchName',
					title : '分公司名称',
					width : 100,
					hidden : false
				}, {
					field : 'accountName',
					title : '账户名称',
					width : 100,
					hidden : false
				}, {
					field : 'account',
					title : '账户',
					width : 100,
					hidden : false
				}, {
					field : 'bankName',
					title : '银行名称',
					width : 100,
					hidden : false
				}, {
					field : 'accountType',
					title : '财务账户类型',
					width : 100,
					hidden : false,
					formatter : function(value) {
						if ("0" == value) {
							return "公司账户";
						} else if ("1" == value) {
							return "个人账户";
						} else {
							return "Null";
							// 你猜这是谁干的！！！！！！！！！！！！刘鑫
						}
					}
				} ] ],
				onResize : function() {
					$('#receivedRecordDataGrid').datagrid('fixDetailRowHeight', index);
				},
				onLoadSuccess : function(r) {
					setTimeout(function() {
						$('#receivedRecordDataGrid').datagrid('fixDetailRowHeight', index);
					}, 0);
				}
			});
			$('#ddv-' + index).datagrid('loadData', temp);
			$('#receivedRecordDataGrid').datagrid('fixDetailRowHeight', index);
		}
	});
	$('#menu').menu({
		onClick : function(item) {
			if (item.id == "item1") {
				ajaxSubmit(serverName + "/receiveRecordList/fen.do", {}, function(r) {
					alert(r);
				});
				$("#receivedRecordDataGrid").datagrid('reload');
			} else if (item.id == "item2") {
				ajaxSubmit(serverName + "/receiveRecordList/yuyue.do", {}, function(r) {
					alert(r);
				});
				$("#receivedRecordDataGrid").datagrid('reload');
			}
		}
	});
});
function jump(param) {
	window.location = serverName + "/receivablesRegistration/returnReceivablesRegistrationJSP.do?creditapplicationid=" + param;

}
function doSearch(param) {
	if (param == 0) {
		$('#receivedRecordDataGrid').datagrid('load', {
			fuzzyValue : $("#fuzzy").val(),
			param : param
		});
	} else if (param == 1) {
		var s = $('#receivedTime').datebox('getText');
		var t = $('#receivedType').combobox('getValue');
		var u = $('#receivedStatus').combobox('getValue');
		var h = $("#historyFlag").combobox('getValue');
		$('#receivedRecordDataGrid').datagrid('load', {
			receivedTime : s,
			receivedType : t,
			receivedStatus : u,
			businessNumber : $("#searchform input[name='businessNumber']").val(),
			borrowerName : $("#searchform input[name='borrowerName']").val(),
			historyFlag : h,
			param : param
		});
	} else {
		$.messager.alert('提示', "doSearchRepayment的JS出错了！", 'error');
	}
}
function doClear(param) {
	if (param == 0) {
		$('#fuzzy').val("");
	} else if (param == 1) {
		$("#searchform").form('clear');
	} else {
		$.messager.alert('提示', "doClearRepayment的JS出错了！", 'error');
	}
}
function cancel(param, h) {
	$.messager.confirm('撤销收款', '确定要撤销这笔收款吗？', function(r) {
		if (r) {
			ajaxSubmit(serverName + "/receiveRecordList/cancelReceivedRecord.do", {
				receivedRecordId : param,
				status : h
			}, function(r) {
				if (11 == r || 21 == r || 31 == r) {
					$.messager.show({
						showType : "show",
						timeout : 1000,
						title : "消息",
						msg : "撤销成功"
					});
					$("#receivedRecordDataGrid").datagrid('reload');
				} else if (20 == r) {
					$.messager.alert("消息！", "该笔收款登记不可撤销！", "warning", function(r) {
						$("#receivedRecordDataGrid").datagrid('reload');
					});
				} else {
					$.messager.show({
						showType : "show",
						timeout : 1000,
						title : "消息",
						msg : "撤销失败"
					});
					$("#receivedRecordDataGrid").datagrid('reload');
				}
			});
		}
	});

}

function newCancel(param, h) {
	$.messager.confirm('撤销收款', '确定要撤销这笔收款吗？', function(r) {
		if (r) {
			ajaxSubmit(serverName + "/receiveRecordList/cancelReceivedRecord.do", {
				receivedRecordId : param,
				status : h
			}, function(r) {
				if (11 == r || 21 == r || 31 == r) {
					$.messager.show({
						showType : "show",
						timeout : 1000,
						title : "消息",
						msg : "撤销成功"
					});
					$("#receivedRecordDataGrid").datagrid('reload');
				} else if (20 == r) {
					$.messager.alert("消息！", "该笔收款登记不可撤销！", "warning", function(r) {
						$("#receivedRecordDataGrid").datagrid('reload');
					});
				} else {
					$.messager.show({
						showType : "show",
						timeout : 1000,
						title : "消息",
						msg : "撤销失败"
					});
					$("#receivedRecordDataGrid").datagrid('reload');
				}
			});
		}
	});
}

function tiaozhuanxinyemian() {
	// /ruralcredit2/WebContent/jsp/rc/receivables/newReceivablesList.jsp
	var centerj = window.parent;
	centerj.addTabFun({
		src : serverName + "/jsp/rc/receivables/newReceivablesList.jsp",
		title : "（新）收款列表"
	});
}

function fen() {
	ajaxSubmit(serverName + "/receiveRecordList/fen.do", {}, function(r) {
		alert(r);
	});
}
function yuyue() {
	ajaxSubmit(serverName + "/receiveRecordList/yuyue.do", {}, function(r) {
		alert(r);
	});
}