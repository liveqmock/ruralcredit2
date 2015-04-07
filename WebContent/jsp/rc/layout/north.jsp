<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="UTF-8">
	function logout(b) {
		$.post('userController.do?logout', function() {
			if (b) {
				if (jq.isLessThanIe8()) {
					loginAndRegDialog.dialog('open');
				} else {
					location.replace(jq.bp());
				}
			} else {
				loginAndRegDialog.dialog('open');
			}
		});
	}

	function userInfo() {
		$.messager.show({
			width:250,
			height:100,
			msg:'个人信息',
			title:'欢迎',
			timeout:5000
		});
	}
</script>
<div style="position: absolute; right: 0px; bottom: 0px; ">
	<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_kzmbMenu" iconCls="icon-help">控制面板</a><a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_zxMenu" iconCls="icon-back">注销</a>
</div>
<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
	<div onclick="userInfo();">个人信息</div>
	<div class="menu-sep"></div>
	<div>
		<span>更换主题</span>
		<div style="width: 100px;">
			<div onclick="jq.changeTheme('default');">default</div>
			<div onclick="jq.changeTheme('gray');">gray</div>
		</div>
	</div>
</div>
<div id="layout_north_zxMenu" style="width: 100px; display: none;">
	<div onclick="">锁定窗口</div>
	<div class="menu-sep"></div>
	<div onclick="">重新登录</div>
	<div onclick="">退出系统</div>
</div>