<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" charset="UTF-8">
	var centerTabs;
	function addTabFun(opts) {
		var options = $.extend({
			title : '',
			content : '<iframe marginwidth="0" id="' + opts.id + '" marginheight="0" src="' + opts.src + '" frameborder="0" style="border:0;width:100%;height:100%;"></iframe>',
			closable : true,
			iconCls : '',
			fit:true
		}, opts);
		if(!centerTabs.tabs('exists', options.title)){
			centerTabs.tabs('add', options);
		}else{
			centerTabs.tabs('close', options.title);
			centerTabs.tabs('add', options);
		}
		tabClose();
	};
	function closeTab(subtitle){
		centerTabs.tabs('close',subtitle);
	}
	function tabClose(){
		/*双击关闭TAB选项卡*/
		$(".tabs-inner").dblclick(function(){
			var subtitle = $(this).children("span").text();
			centerTabs.tabs('close',subtitle);
		});
		/*右键菜单*/
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
	function tabCloseEven(){
		//关闭当前
		$('#mm-tabclose').click(function(){
			var currtab_title = $('#mm').data("currtab");
			centerTabs.tabs('close',currtab_title);
		})
		//全部关闭
		$('#mm-tabcloseall').click(function(){
			$('.tabs-inner span').each(function(i,n){
				var t = $(n).text();
				centerTabs.tabs('close',t);
			});	
		});
		//关闭除当前之外的TAB
		$('#mm-tabcloseother').click(function(){
			var currtab_title = $('#mm').data("currtab");
			$('.tabs-inner span').each(function(i,n){
				var t = $(n).text();
				if(t!=currtab_title)
					centerTabs.tabs('close',t);
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
				centerTabs.tabs('close',t);
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
				centerTabs.tabs('close',t);
			});
			return false;
		});

		//退出
		$("#mm-exit").click(function(){
			$('#mm').menu('hide');
		});
	}
	$(function() {
		tabCloseEven();
		centerTabs = $('#centerTabs').tabs({
			border : false,
			fit : true
		});
		setTimeout(function() {
		//var src = '<%=basePath%>indexremindcontroller/getRemind.do';
		//centerTabs.tabs('add', {
			//title : '首页',
			//content : '<iframe src="' + src + '" frameborder="0" style="border:0;width:100%;height:99.2%;"></iframe>',
			//closable : false,
			//iconCls : ''
		//});
		var src2 = '<%=basePath%>indexremindcontroller/getReminds.do';
		centerTabs.tabs('add', {
			title : '首页',
			content : '<iframe src="' + src2 + '" frameborder="0" style="border:0;width:100%;height:99.2%;"></iframe>',
			closable : false,
			iconCls : ''
		});
		}, 0);
		
		
	});
</script>
<div id="centerTabs"></div>
<div id="mm" class="easyui-menu" style="width:150px;">
	<div id="mm-tabclose">关闭</div>
	<div id="mm-tabcloseall">全部关闭</div>
	<div id="mm-tabcloseother">除此之外全部关闭</div>
	<div class="menu-sep"></div>
	<div id="mm-tabcloseright">当前页右侧全部关闭</div>
	<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
	<div class="menu-sep"></div>
	<div id="mm-exit">退出</div>
</div>