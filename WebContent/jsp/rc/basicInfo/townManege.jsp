<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>"></base>

		<title>My JSP 'townManege.jsp' starting page</title>
		<jsp:include page="/jsp/rc/include/easyui.jsp"></jsp:include>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>scripts/uilib/uploadify/uploadify.css" />
		<script type="text/javascript" src="<%=basePath%>scripts/uilib/uploadify/jquery.uploadify-3.1.min.js"></script>

		<script type="text/javascript">
	$(function() {
		serverName="<%=path%>";
		flagValidate = true;
		townKey = true;
		window.onresize = function() {
			setTimeout(function() {
				$('#townList').resizeDataGrid(155, 20, 0, 0);
			}, 500);
		};

		$("#online").combobox({
			editable : false,
			required : true,
			valueField : 'id',
			textField : 'text',
			data : [ {
				id : '1',
				text : '上线'
			}, {
				id : '0',
				text : '下线'
			} ]
		});
		editrow = undefined;
		//easyui-validatebox拓展验证
		$.extend($.fn.validatebox.defaults.rules, {
			//验证数字
			number : {
				validator : function(value, param) {
					if (value) {
						return /^[1-9][0-9]*(\.[0-9]+)?$/.test(value);
					} else {
						return true;
					}
				},
				message : '只能输入非0开头的数字.'
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
			wordAndNumber : {
				validator : function(value, param) {
					if (value) {
						return /^([0-9]|W)*$/.test(value);
					} else {
						return true;
					}
				},
				message : '请输入数字与字母的组合！'
			}
		});

		$("#townList").datagrid({
			url : serverName + '/town/listTown.do',
			pagination : true,
			idfield : 'townshipinfoid',
			fitColumns : true,
			striped : true,
			singleSelect : true,
			sortName : 'townshipinfName',
			rownumbers : true,
			columns : [ [ {
				title : '所在省',
				field : 'province',
				width : 150
			}, {
				title : '所在市',
				field : 'city',
				width : 150
			}, {
				title : '所在县',
				field : 'district',
				width : 150
			}, {
				title : '所在县Code',
				field : 'districtCode',
				width : 150,
				hidden : true
			}, {
				title : '所在镇',
				field : 'town',
				width : 150,
				editor : {
					type : 'validatebox',
					options : {
						required : true
					}
				}
			}, {
				title : '镇编号',
				field : 'townCode',
				width : 150
			}, {
				title : '所在村',
				field : 'village',
				width : 150,
				editor : {
					type : 'validatebox',
					options : {
						required : true
					}
				}
			}, {
				title : '村编号',
				field : 'code',
				width : 150
			}, {
				title : '是否在线',
				field : 'onLine',
				formatter : function(value) {
					if (value == "1") {
						return '<font color="red">上线</font>';
					}
					if (value == "0") {
						return '<font color="green">下线</font>';
					}
				},
				width : 150,
				editor : {
					type : 'combobox',
					options : {
						url : serverName + '/scripts/data/combo_online.json',
						valueField : 'id',
						textField : 'text',
						required : true,
						editable : false
					}
				}
			}, {
				title : '镇ID',
				field : 'parentId',
				width : 150,
				hidden : true
			} ] ],

			toolbar : [ {
				id : 'townadd',
				text : '添加乡镇信息',
				iconCls : 'icon-addOne',
				handler : function() {
					if (editrow != undefined) {
						$.messager.alert("提示信息", "请结束当前编辑");
						return;
					}
					$("#townInfo").form("clear");
					$("#townInfo").form('validate');
					$("#uploadFile").form("clear");
					$("#showTownInfo").dialog("open").dialog("setTitle", "新增乡镇信息");
					showCityCombo();
				}
			}, {
				id : 'townupdate',
				text : '修改乡镇',
				iconCls : 'icon-edit',
				handler : function() {
					if (editrow != undefined) {
						$.messager.alert("提示信息", "请结束当前编辑");
						return;
					} else {
						$("#townList").datagrid('getSelections').length;
						if ($("#townList").datagrid('getSelections').length == 0) {
							$.messager.alert("消息", "请选择一条记录!");
							return;
						}
						var row = $("#townList").datagrid('getSelections');
						if (row.length == 1) {
							var rowindex = $("#townList").datagrid('getRowIndex', row[0]);
							$("#townList").datagrid('beginEdit', rowindex);
							editrow = rowindex;
							$("#townList").datagrid('unselectAll');
						}

					}
				}
			}, {
				id : 'towncancel',
				text : '取消编辑',
				iconCls : 'icon-undo',
				handler : function() {

					$("#townList").datagrid('rejectChanges');
					$("#townList").datagrid('unselectAll');
					editrow = undefined;

				}
			}, {
				id : 'townsave',
				text : '保存',
				iconCls : 'icon-save',
				handler : function() {

					if (editrow != undefined) {
						//先按规则查一下
						$("#townList").datagrid('endEdit', editrow);
					}
					;
				}
			} ],

			onAfterEdit : function(rowIndex, rowData, changes) {
				var inserted = $("#townList").datagrid('getChanges', 'inserted');

				var updata = $("#townList").datagrid('getChanges', 'updated');
				if (updata == null || updata == "") {
					editrow = undefined;
				}
				if (updata[0] == rowData) {
					$.ajax({
						url : serverName + "/town/update.do",
						data : rowData,
						cache : false,
						dataType : "json",
						type : "post",
						success : function(r) {
							if (r.success) {
								$("#townList").datagrid('unselectAll');
								editrow = undefined;
								$.messager.show({
									msg : r.msg,
									title : '消息'
								});

								$("#townList").datagrid('reload');
							} else {
								$("#townList").datagrid('rejectChanges');
								$("#townList").datagrid('unselectAll');
								editrow = undefined;
								$.messager.show({
									msg : r.msg,
									title : '提示'
								});
							}

						}
					});

				}

			}

		});
		$('#townList').resizeDataGrid(155, 20, 0, 0);

	});
	//动态加载 省、市、区 下拉列表
	function showCityCombo() {
		var province = $("#province1").combobox({
			//required : true,
			url : serverName + '/NSC/list.do',
			textField : 'cityName',
			valueField : 'cityCode',
			mode : 'remote',
			width : 150,
			delay : 500,
			value : '',
			onSelect : function(value) {
				$("#city1").combobox({
					url : serverName + '/NSC/listCity.do?parentId=' + value.cityCode,
					textField : 'cityName',
					valueField : 'cityCode',
					mode : 'remote',
					width : 150,
					delay : 500,
					value : '',
					onSelect : function(value) {
						$("#prefecture1").combobox({
							url : serverName + '/NSC/listCity.do?parentId=' + value.cityCode,
							textField : 'cityName',
							valueField : 'cityCode',
							mode : 'remote',
							delay : 500,
							width : 150,
							value : '',
							onSelect : function(value) {
								$("#town").combobox({
									url : serverName + '/town/list.do?parentId=' + value.cityCode,
									textField : 'townshipinfName',
									valueField : 'townshipinfName',
									mode : 'remote',
									width : 150,
									delay : 500,
									value : '',
									onSelect : function(value) {
										$("#t_key").val(value.key);
										$("#t_key").validatebox("validate");
										$("#t_townShipId").val(value.townshipinfoid);
									},
									onChange : function(value) {
										validaTownshipId();
									},
									onLoadSuccess : function(data) {
										var val1 = $('#town').combobox('getValue');
										$("#t_name").val(val1);
										var val = $('#town').combobox('getData');
										if (val.length > 0) {
											for ( var i = 0; i < val.length; i++) {
												if (val[i].townshipinfName == val1) {
													$("#v_parentId").attr('value', val[i].townshipinfoid);
													$("#t_townShipId").attr('value', val[i].townshipinfoid);
													$("#t_key").val(val[i].key);
													break;
												} else {
													$("#v_parentId").attr('value', "");
													$("#t_townShipId").attr('value', "");
													$("#t_key").val("");
												}

											}
										} else {
											$("#v_parentId").attr('value', "");
											$("#t_townShipId").attr('value', "");
											$("#t_key").val("");
										}
									}

								});

							}
						});
					}
				});
			}
		});
	}

	//为镇名称赋值
	function giveTownName() {
		var val1 = $('#town').combobox('getText');
		$("#t_name").val(val1);
		var val = $('#town').combobox('getData');
		for ( var i = 0; i < val.length; i++) {
			if (val[i].townshipinfName == val1) {
				$("#v_parentId").attr('value', val[i].townshipinfoid);
				$("#t_townShipId").attr('value', val[i].townshipinfoid);
				//$("#t_key").val(val[i].key);
				break;
			} else {
				$("#v_parentId").attr('value', "");
				$("#t_townShipId").attr('value', "");
			}

		}

		if (val1 == undefined || val1 == null || val1 == "") {
			$("#v_parentId").attr('value', "");
			$("#t_townShipId").attr('value', "");
			$("#t_key").val("");
		}

	}

	function searchTown() {
		$("#townList").datagrid('rejectChanges');
		$("#townList").datagrid('unselectAll');
		editrow = undefined;
		$("#townList").datagrid('load', {
			province : $('#province').val(),
			city : $('#city').val(),
			district : $('#district').val(),
			town : $("#townName").val(),
			village : $("#village").val(),
			code : $('#code').val()
		});
	}
	function clearSearchBox() {
		$("#province").val("");
		$("#city").val("");
		$("#district").val("");
		$("#townName").val("");
		$("#village").val("");
		$("#code").val("");
	}

	function submit() {
		$("#submitTown").linkbutton('disable');
		//为镇名称赋值
		giveTownName();
		flagValidate = false;
		townKey = false;
		var flag = $("#townInfo").form('validate');
		flagValidate = true;
		townKey = true;
		if (flag) {
			ajaxSubmit(serverName + '/town/addTown.do', $("#townInfo").serialize(), saveRetFunc);

		} else {
			$("#submitTown").linkbutton('enable');
			$.messager.alert("消息", "请将数据填写完整！");
		}
	};

	function saveRetFunc(result) {
		if (result.success) {
			$("#townInfo").form("clear");
			$("#showTownInfo").dialog("close");
			$("#townList").datagrid("reload");
			$("#submitTown").linkbutton('enable');
			$.messager.show({
				msg : '数据保存成功！',
				title : '消息'
			});
		} else {
			$("#submitTown").linkbutton('enable');
			$.messager.alert('错误', result.msg);
		}
	};

	function cancel() {
		$("#townInfo").form("clear");
		$("#showTownInfo").dialog("close");
	};
	function validaTownshipId() {
		if (townKey) {
			var t_key = $("#t_key").val();
			var prefecture1 = $("#prefecture1").combobox("getValue");
			var t_townShipId = $("#t_townShipId").val();
			url = serverName + "/town/varifyTownshipId.do";
			ajaxSubmit(url, {
				parentId : prefecture1,
				key : t_key,
				townShipInfoId : t_townShipId
			}, function(result) {
				if (result.success == false) {
					$.messager.show({
						title : "消息",
						msg : "镇编号已存在，请重新填写！"
					});
					$("#t_key").val("");
					return false;
				} else {
					return true;
				}
			});
		} else {
			return true;
		}

	}
	function validaVillage(flagValidate) {
		if (flagValidate) {
			var v_key = $("#v_key").val();
			var t_townShipId = $("#t_townShipId").val();
			if (t_townShipId != null && t_townShipId != "" && t_townShipId != undefined) {
				url = serverName + "/town/varifyVillage.do";
				ajaxSubmit(url, {
					parentId : t_townShipId,
					key : v_key
				}, function(result) {
					if (result.success == false) {
						$.messager.show({
							title : "消息",
							msg : "村编号已存在，请重新填写！"
						});
						$("#v_key").val("");
						return false;
					} else {
						return true;
					}
				});
			}
		} else {
			return true;
		}

	};

	//（郝强）提交按钮
	function uploadFile() {

		if (($("#province1").combobox("getValue") != "") && ($("#city1").combobox("getValue") != "") && ($("#prefecture1").combobox("getValue") != "")) {
			if ($("#fileTown").val() != "") {
				$('#uploadFile').form('submit', {
					url : serverName + "/town/uploadfile.do?parentId=" + $("#prefecture1").combobox("getValue"),
					type : "post",

					success : function(data) {
						var data = JSON.parse(data);
						if (data.success) {
							$.messager.alert("消息", data.msg, "info", function() {
								$("#showTownInfo").dialog("close");
								$("#townList").datagrid("load");
							});
						} else {
							$.messager.alert("消息", data.msg);
						}
					}
				});
			} else {
				$.messager.alert("消息", "请选择要上传的文件");
			}
		} else {
			$.messager.alert("消息", "请选择省，市，区县");
		}
	}

	//模糊查询
	function fuzzySearch() {
		$("#townList").datagrid('rejectChanges');
		$("#townList").datagrid('unselectAll');
		editrow = undefined;
		$("#townList").datagrid('load', {
			fuzzy : $('#fuzzy').val()
		});
	}
	function clearFuzzy() {
		$('#fuzzy').val("");
	}
</script>

	</head>

	<body id="cc" class="easyui-layout" fit="true">
		<div region="center" style="padding: 0px;">
			<div class="easyui-tabs" style="padding: 10px;">
				<div title="模糊搜索" style="padding: 10px;">
					模糊搜索条件：
					<input id="fuzzy" />
					<a class="easyui-linkbutton" onclick="fuzzySearch();">搜索</a>
					<a class="easyui-linkbutton" onclick="clearFuzzy();">清除</a><font color="red">(搜索条件可以是省、市、区、乡镇、村的名称，村编码)</font>
				</div>
				<div title="高级搜索" style="padding: 10px;">
					<table>
						<tr>
							<td>
								省:
							</td>
							<td>
								<input type="text" id="province" />
							</td>
							<td>
								市:
							</td>
							<td>
								<input type="text" id="city" />

							</td>

							<td>
								区:
							</td>
							<td>
								<input type="text" id="district" />

							</td>
							<td>
								<a class="easyui-linkbutton" onclick="javascript:searchTown();">搜索</a>
							</td>
						</tr>
						<tr>
							<td>
								乡镇:
							</td>
							<td>
								<input type="text" id="townName" />

							</td>
							<td>
								村:
							</td>
							<td>
								<input type="text" id="village" />
							</td>
							<td>
								村编码:
							</td>
							<td>
								<input id="code" />
							</td>
							<td>
								<a class="easyui-linkbutton" onclick="javascript:clearSearchBox();">清空</a>
							</td>
						</tr>
						<tr>

						</tr>
					</table>
				</div>
			</div>
			<div>
				<div style="padding: 10px;">
					<table id="townList">
					</table>
				</div>
			</div>
		</div>

		<div id="showTownInfo" class="easyui-dialog" align="center" modal="true" style="padding: 30px; border: 0px; margin: 0px; width: 650px; height: 350px" closed="true" resizable="true">
			<form id="townInfo" novalidate>

				<table align="center">
					<tr align="center">
						<td align="right">
							省 :
						</td>
						<td align="left">
							<select required="true" id="province1" class="easyui-combobox" editable="false" style="width: 150px;"></select>
						</td>
						<td align="right">
							市:
						</td>
						<td align="left">
							<select required="true" id="city1" class="easyui-combobox" editable="false" style="width: 150px;"></select>
						</td>
						<td align="right">
							区:
						</td>
						<td align="left">
							<select required="true" id="prefecture1" name="listTown[0].parentId" class="easyui-combobox" editable="false" style="width: 150px;"></select>
						</td>
					</tr>
				</table>

				<table style="padding: 7px; border-top-color: olive; border-top-style: solid;">
					<tr style="border-top-style: solid; border-top-color: fuchsia;">
						<td>
							<input id="v_parentId" name="listTown[1].parentId" type="hidden" />
						</td>
						<td>
							<input id="t_townShipId" name="listTown[0].townshipinfoid" type="hidden" />
						</td>
						<td>
							<input name="listTown[0].townshipinfName" id="t_name" type="hidden" />
						</td>
						<td>
							<input id="" name="listTown[1].townshipinfoid" type="hidden" />
							<input id="townON" name="listTown[0].onLine" type="hidden" value="1" />
						</td>

					</tr>
					<tr>
						<td>
							镇:
						</td>
						<td align="left">
							<input required="true" id="town" style="width: 150px;" class="easyui-combobox" />
						</td>
						<td width="20"></td>
						<td>
							镇编码:
						</td>
						<td align="left">
							<input required="true" name="listTown[0].key" id="t_key" style="width: 150px;" class="easyui-validatebox" />
						</td>

					</tr>
					<tr>
						<td>
							村:
						</td>
						<td align="left">
							<input required="true" name="listTown[1].townshipinfName" class="easyui-validatebox" style="width: 148px;" />
						</td>
						<td width="20"></td>
						<td>
							村编码:
						</td>
						<td align="left">
							<input required="true" name="listTown[1].key" id="v_key" class="easyui-validatebox" style="width: 150px;" />
						</td>
					</tr>
					<tr>
						<td align="right">
							是否上线:
						</td>
						<td align="left">
							<input id="online" name="listTown[1].onLine" style="width: 150px;" />
						</td>
					</tr>
				</table>
				<table align="center">
					<tr align="right">
						<td align="right">
							<a id="submitTown" class="easyui-linkbutton" onclick="submit();"> 确定</a>
						</td>
						<td align="right"></td>
						<td align="right">
							<a class="easyui-linkbutton" onclick="cancel();"> 取消</a>
						</td>
					</tr>
				</table>
			</form>
			<div style="padding: 7px; border-top-color: olive; border-top-style: solid;">
				批量上传乡镇信息：
				<form id="uploadFile" method="post" enctype="multipart/form-data">
					<input id="fileTown" type="file" name="file" />
					（请选择excel文件）
				</form>
				<br />
				<a class="easyui-linkbutton" onclick="uploadFile();">提交</a>
			</div>


		</div>

	</body>
</html>
