<%@ page language="java" import="java.util.*,com.creditease.rc.util.RoleUtil,com.creditease.rc.util.PropertiesUtil" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Properties properties = PropertiesUtil.loadProperties("spring/icp/icp.properties");
String icpUrl = properties.getProperty("icp.url");
%>
<script type="text/javascript" charset="UTF-8">
	var tree;
	
	$(function() {
	
     $.ajax({
         url: "<%=basePath%>menu/makeMenu.do",
         dataType:"xml",
         error:function(XMLHttpRequest, textStatus, errorThrown){
         },
         success: function(data){
            if(data){
                var id = 1;
                //var nav_text=" <li id=\"nav1\"><a href=\"<%=basePath%>showMainPageReminder.action\" target=\"import_frame\">首页</a></li>\n";;//新优化首页提醒
                var tree_root_text="";
                var first_lever_text = "";
                var vRootNode = data.documentElement;   //根节点
                var tree=$(vRootNode).children("tree");
                menusize=tree.length;
                for(i=0;i<tree.length;i++){
                    if(i > 0) first_lever_text+=",";
                    var c_node=$(tree[i]).children("tree");
                    if(c_node.length > 0) { 
                        var second_lever_text="";
	                    for(j=0;j<c_node.length;j++){
	                        if(j > 0) second_lever_text+=",";
	                        second_lever_text+="{\"id\":\""+ (id++) +"\", \"text\":\""+c_node[j].getAttribute("name")+"\", \"iconCls\":\"icon-nav\", \"attributes\":{ \"url\":\"<%=basePath%>"+c_node[j].getAttribute("menuAction")+"\" } }";
	                    }
                        first_lever_text += "{ \"id\":"+ (id++) +", \"text\":\""+tree[i].getAttribute("name")+"\", \"iconCls\":\"icon-channels\", \"children\":["+ second_lever_text +"] }";
                    } else {
                        first_lever_text += "{ \"id\":"+ (id++) +", \"text\":\""+tree[i].getAttribute("name")+"\", \"iconCls\":\"icon-channels\" }";
                    }
                }
                tree_root_text += "["+ first_lever_text +"]";
                // 生成树的数据
                // alert(tree_root_text);
                
                        // 生成树
                        tree = $('#tree').tree({
				            //url : '<%=basePath%>scripts/data/categories_<%=RoleUtil.getRolePathOfCurrentUser() %>.json',
				            data: eval("("+tree_root_text+")"),
				            animate : false,
				            //lines : !sy.isLessThanIe8(),
				            onClick : function(node){
				                if(node.attributes && node.attributes.url && node.attributes.url != ''){
				                    var href;
				                    if(/^\//.test(node.attributes.url)){
				                        href = node.attributes.url.substr(1);
				                        $.messager.progress({
				                            text : '请求数据中....',
				                            interval : 100
				                        });
				                    }else{
				                        href = node.attributes.url;
				                    }
				                    addTabFun({
				                        src : href,
				                        title : node.text,
				                        id: node.id
				                    });
				                }
				            },
				            onLoadSuccess : function(node, data) {
				                var t = $(this);
				                if (data) {
				                    $(data).each(function(index, d) {
				                        if (this.state == 'closed') {
				                            t.tree('expandAll');
				                        }
				                    });
				                }
				            }
				        });
            }
	    }
	});
    
     $("#accordion").accordion({onSelect:function(title){
    	 if(title=="综合信贷"){
    		 addTabFun({
                 src : "<%=icpUrl %>/welcome",
                 title : "综合信贷首页",
                 id: "icp"
             });
        	    	$("iframe[id='icp']").load(function(){ 
        	    		creatICP();
        	        }); 
        	    	
        	    	$("iframe[id='icp']").error(function(){ 
        	    		creatICP();
        	        }); 
    	 }
    	 
     }}); 
     
     
     
     
     

	});
	function collapseAll() {
		var node = tree.tree('getSelected');
		if (node) {
			tree.tree('collapseAll', node.target);
		} else {
			tree.tree('collapseAll');
		}
	}
	function expandAll() {
		var node = tree.tree('getSelected');
		if (node) {
			tree.tree('expandAll', node.target);
		} else {
			tree.tree('expandAll');
		}
	}
	function collapseAllICP() {
		var node = tree.tree('getSelected');
		if (node) {
			tree.tree('collapseAll', node.target);
		} else {
			tree.tree('collapseAll');
		}
	}
	function expandAllICP() {
		var node = treeICP.tree('getSelected');
		if (node) {
			treeICP.tree('expandAll', node.target);
		} else {
			treeICP.tree('expandAll');
		}
	}
	function creatICP(){
		$.ajax({
	         url: "<%=basePath%>menu/makeMenuICP.do",
	         dataType:"xml",
	         error:function(XMLHttpRequest, textStatus, errorThrown){
	         },
	         success: function(data){
	            if(data){
	                var id = 100;
	                //var nav_text=" <li id=\"nav1\"><a href=\"<%=basePath%>showMainPageReminder.action\" target=\"import_frame\">首页</a></li>\n";;//新优化首页提醒
	                var tree_root_text="";
	                var first_lever_text = "";
	                var vRootNode = data.documentElement;   //根节点
	                var tree=$(vRootNode).children("tree");
	                menusize=tree.length;
	                for(i=0;i<tree.length;i++){
	                    if(i > 0) first_lever_text+=",";
	                    var c_node=$(tree[i]).children("tree");
	                    if(c_node.length > 0) { 
	                        var second_lever_text="";
		                    for(j=0;j<c_node.length;j++){
		                        if(j > 0) second_lever_text+=",";
		                        second_lever_text+="{\"id\":\""+ (id++) +"\", \"text\":\""+c_node[j].getAttribute("name")+"\", \"iconCls\":\"icon-nav\", \"attributes\":{ \"url\":\"<%=icpUrl %>"+c_node[j].getAttribute("menuAction")+"\" } }";
		                    }
	                        first_lever_text += "{ \"id\":"+ (id++) +", \"text\":\""+tree[i].getAttribute("name")+"\", \"iconCls\":\"icon-channels\", \"children\":["+ second_lever_text +"] }";
	                    } else {
	                        first_lever_text += "{ \"id\":"+ (id++) +", \"text\":\""+tree[i].getAttribute("name")+"\", \"iconCls\":\"icon-channels\" }";
	                    }
	                }
	                tree_root_text += "["+ first_lever_text +"]";
	                // 生成树的数据
	                // alert(tree_root_text);
	                
	                        // 生成树
	                        treeICP = $('#treeICP').tree({
					            //url : '<%=basePath%>scripts/data/categories_<%=RoleUtil.getRolePathOfCurrentUser() %>.json',
					            data: eval("("+tree_root_text+")"),
					            animate : false,
					            //lines : !sy.isLessThanIe8(),
					            onClick : function(node){
					                if(node.attributes && node.attributes.url && node.attributes.url != ''){
					                    var href;
					                    if(/^\//.test(node.attributes.url)){
					                        href = node.attributes.url.substr(1);
					                        $.messager.progress({
					                            text : '请求数据中....',
					                            interval : 100
					                        });
					                    }else{
					                        href = node.attributes.url;
					                    }
					                    addTabFun({
					                        src : href,
					                        title : node.text,
					                        id: node.id
					                    });
					                }
					            },
					            onLoadSuccess : function(node, data) {
					                var t = $(this);
					                if (data) {
					                    $(data).each(function(index, d) {
					                        if (this.state == 'closed') {
					                            t.tree('expandAll');
					                        }
					                    });
					                }
					            }
					        });
	            }
		    }
		});
	}
</script>
<div class="easyui-panel" fit="true" border="false">
	<div id="accordion" class="easyui-accordion" fit="true" border="false">
		<div title="系统菜单" iconCls="icon-tip">
			<div class="easyui-layout" fit="true">
				<div region="north" border="false" style="overflow: hidden;">
					<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-redo" onclick="expandAll();">展开</a><a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-undo" onclick="collapseAll();">折叠</a><a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-reload" onclick="tree.tree('reload');">刷新</a>
					<hr style="border-color: #fff;" />
				</div>
				<div region="center" border="false">
					<ul id="tree" style="margin-top: 5px;"></ul>
				</div>
			</div>
		</div>
		<div title="综合信贷">
		<div class="easyui-layout" fit="true">
		<div region="north" border="false" style="overflow: hidden;">
					<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-redo" onclick="expandAllICP();">展开</a><a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-undo" onclick="collapseAllICP();">折叠</a><a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-reload" onclick="tree.tree('reload');">刷新</a>
					<hr style="border-color: #fff;" />
				</div>
				<div region="center" border="false">
					<ul id="treeICP" style="margin-top: 5px;"></ul>
				</div>
			</div>
		</div>
	</div>
</div>