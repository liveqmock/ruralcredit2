//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}
/**
 *  页面加载等待页面
 */
 var height = window.screen.height-250;
 var width = window.screen.width;
 var leftW = 300;
 if(width>1200){
 	leftW = 500;
 }else if(width>1000){
 	leftW = 350;
 }else {
 	leftW = 100;
 }
 
 var _html = "<div id='loading' style='position:absolute;left:0;width:100%;height:"+height+"px;top:0;background:#E0ECFF;" +
 		"opacity:1;filter:alpha(opacity=100);'>\<div style='position:absolute;	cursor1:wait;left:"+leftW+"px;top:200px;width:auto;height:16px;padding:12px 5px 10px 30px;\background:#fff " +
 				"url(/uilib/themes/default/images/pagination_loading.gif) no-repeat scroll 5px 10px;border:2px solid #ccc;" +
 				"color:#000;'>\正在加载，请等待...\</div></div>";
 
 window.onload = function(){
 	var _mask = document.getElementById('loading');
 	_mask.parentNode.removeChild(_mask);
 };
 document.write(_html);
 
$(function(){
	$("#editDialog").show().dialog({
		title:"修改密码",
		//closed : true,防止两次渲染
		closed : true,
		modal : true,
		inline : false,
		width : 500,
		height : 300,
		border : false,
		dosize : false,
		draggable : false,
		buttons : [ {
			id : "addButton",
			text : "修改",
			handler : function() {
				updatePassword()
					}
			}, {
				id : "cencleButton",
				text : "取消",
				handler : function() {
					$("#editDialog").dialog('close');
				}
			} ]
	});
}) 
 	
/**
 * 弹出修改密码dialog
 */
function openDialog(){
	$("#editDialog").dialog('open');
	$("#addButton").linkbutton('enable'); 
	$("#newPassword").val("");
	$("#repeatPassword").val("");
}
 
/**
 * 修改密码
 */
function updatePassword(){
 	if($("#password_form").form("validate")){
 		$("#addButton").linkbutton('disable');    
 		$("#editDialog").dialog('close');
		var data="password="+$("#newPassword").val();
		//console.info(data);
		var url=serverName + "/menu/updatePassword.do";
		ajaxSubmit(url,data, function(r){
			//console.info(r);
			$.messager.show({
				title:'提示信息',
				msg:r.msg
			});
		});
	}
	/*else{
		$("#editDialog").dialog('open');
		$("#addButton").linkbutton('enable');   
	}*/
};
 	
 

