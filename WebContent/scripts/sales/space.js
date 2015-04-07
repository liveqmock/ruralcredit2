$(function () {
	var dic = new DataDictionary();
	dic.addDic("spacestudit", "spacestudit");
	dic.dicAjax();
	$('#spaceList').datagrid({
		url: serverName + '/spaceController/spaceList.do',
		 height: 330,
	     pagination: true,
	     pageSize: 10,
	     pageList: [10, 20, 30, 40, 50],
	     rownumbers: true,
	     singleSelect: true,
	     loadMsg : '正在加载....',
	     striped : true,
	     frozenColumns:[[
					{
					    title: '操作', field: 'spaceid', width: 90, align: 'left', formatter: operate
					},
	                     ]],
	     columns:[[
					
	               {title: '大区',field: 'regionname', width: 100, align: 'left', sortable: true},
	               {title: '分中心',field: 'citydepartmentname', width: 100, align: 'left', sortable: true},
	               {title: '营业部',field: 'areadepartmentname', width: 100, align: 'left', sortable: true},
	               {title: '计划展业日期',field: 'spacedate', width: 100, align: 'left', sortable: true},
	               {title: '计划展业时间',field: 'spacetime', width: 100, align: 'left', sortable: true},
	               {title: '计划展业地点',field: 'spaceplace', width: 100, align: 'left', sortable: true},
	               {title: '人员规划',field: 'personplanning', width: 100, align: 'left', sortable: true},
	               {title: '预计客户量',field: 'customerconsultcount', width: 100, align: 'left', sortable: true},
	               {title: '展业状态',field: 'sapceText', width: 100, align: 'left', sortable: true},
	               {title: '展业状态隐藏',field: 'spacestudit', width: 100, align: 'left', hidden: true},
	               {title: '实际展业日期',field: 'spacedateReality', width: 100, align: 'left', sortable: true},
	               {title: '实际展业时间',field: 'spacetimeReality', width: 100, align: 'left', sortable: true},
	               {title: '实际展业地点',field: 'spaceplaceReality', width: 100, align: 'left', sortable: true},
	               {title: '实际参与人员',field: 'participantsReality', width: 100, align: 'left', sortable: true},
	               {title: '实际客户量',field: 'customerconsultcountReality', width: 100, align: 'left', sortable: true},
	               {title: '提供照片数量',field: 'photocount', width: 100, align: 'left', sortable: true}
	               ]]
	});
	/*更改：分公司名称下拉选择框为两级树形菜单*/
    departmentComboboxTreeWithPanelWidth("areadepartmentid",false,250);
});
//条件搜索
function spaceSearch(){
	var areadepartmentid = $("#areadepartmentid").combotree("getValues");
	if(areadepartmentid==undefined){
		areadepartmentid = "";
	}
	$("#spaceList").datagrid("load",{
		areadepartmentid : $.trim(areadepartmentid),
		spacedate_begin : $("#spacedate_begin").datebox("getValue"),
		spacedate_end : $("#spacedate_end").datebox("getValue"),
		spacestudit : $("#spacestudit").combobox("getValue")
	});
}

function clearAll(){
	$('#areadepartmentid').combotree('setValues','');
	$("#spacedate_begin").datebox("clear");
	$("#spacedate_end").datebox("clear");
	$("#spacestudit").combobox('setValues','');
}

//打开登记展业计划dialog
function registerSpace() {
    $('#registerSpaceFrame').attr('src', serverName + '/spaceController/addFrame.do');
    $("#registerSpace").dialog({
        title: "新增展业计划",
        close: true,
        modal: true,
        draggable: true,
        cache: false,
        onClose: function () {
            $("#registerSpaceFrame").attr('src', "");
        }
    });
    $('#registerSpace').dialog('open');
}
//新增展业计划
function saveSpace() {
	registerSpaceFrame.window.saveSpace();
}
//执行展业计划
function saveExecuteSpace() {
	registerSpaceExecuteFrame.window.saveExecuteSpace();
}
//保存延期
function delaySpaceSave() {
	registerSpaceExecuteFrame.window.delaySpaceSave();
}
//取消登记展业计划
function unSpace() {
    $('#registerSpace').dialog('close');
}

//操作
function operate(value,param){
	var regEx = new RegExp("\\-","gi");
    spacetDateStr=param.spacedate.replace(regEx,"/");
    var spacetDate = new Date(spacetDateStr);
    var spaceMonth = spacetDate.getMonth()+1;
    var spaceYear = spacetDate.getFullYear();
	if(value ==null || value=="" || value==undefined){
		return;
	}
	if(param.spacestudit=="0"){
		
		var links="<a href='javascript:execute("+value+");'>执行</a> "+"| <a href='javascript:delay("+value+");'>延期</a>";
	}
	if(param.spacestudit=="1"){
		var links ="<a href='javascript:download("+value+");'>下载</a>";
	}
	if(param.spacestudit=="2"){
		
		var links="<a href='javascript:execute("+value+");'>执行</a>";
	}
	if(param.spacedateReality =="" || param.spacedateReality==undefined || param.spacedateReality==null){
    	if(spaceYear==new Date().getFullYear()){
    		if(spaceMonth<(new Date().getMonth()+1)){
    			links="";
    		}
    	}else if(spaceYear<new Date().getFullYear()){
    		links="";
    	}
    }
	
	return links;
}

function download(value){
	ajaxSubmit(serverName + "/spaceController/encodingSpaceId.do",{value:value}, function (result) {
		if(result.success){
			var href=downloadUrl+"/zipDownload.do?clientId=" +result.msg+ "&bussTableName=9b3093e360e403da5c4741775e3ce7a29b5d20e444c27529";
			window.location.href=href;
		}
	});
}


function execute(value){
	$('#registerSpaceExecuteFrame').attr('src', serverName + '/spaceController/registerSpaceExecuteFrame.do?value='+value);
    $("#registerSpaceExecute").dialog({
        title: "执行展业计划",
        close: true,
        modal: true,
        draggable: true,
        cache: false,
        onClose: function () {
            $("#registerSpaceExecuteFrame").attr('src', "");
        }
    });
    $('#registerSpaceExecute').dialog('open');
}
function delay(value){
	ajaxSubmit(serverName + "/spaceController/updateSpaceStud.do", {value:value}, function (result) {
		if(result.success){
			$.messager.show({
                title: '消息',
                msg: result.msg,
                timeout: 5000,
                showType: 'slide'
            });
            clearAll();
            spaceSearch();
		}else{
			$.messager.show({
                title: '消息',
                msg: result.msg,
                timeout: 5000,
                showType: 'slide'
            });
		}
	});
}
//取消执行展业计划窗口
function unExecuteSpace(){
	$('#registerSpaceExecute').dialog('close');
}
//取消延期窗口
function undelaySpaceSave(){
	$('#delaySpace').dialog('close');
}

//登记展业计划保存按钮变灰
function regSpaceGray(){
	$("#registSave").linkbutton('disable');
}

//登记展业计划保存按钮取消变灰
function unRegSpaceGray(){
	$("#registSave").linkbutton('enable');
}

//执行展业计划保存按钮变灰
function exeSpaceGray(){
	$("#editCus").linkbutton('disable');
}

//执行展业计划保存按钮取消变灰
function unExeSpaceGray(){
	$("#editCus").linkbutton('enable');
}






