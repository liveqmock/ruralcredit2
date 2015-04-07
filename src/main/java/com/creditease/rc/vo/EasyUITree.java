package com.creditease.rc.vo;

import java.util.ArrayList;
import java.util.List;

public class EasyUITree {

	/*
	 * id:节点id,这个很重要到加载远程服务器数据 which is important to load remote data
	 * text: 显示的节点文本
	 * state: 节点状态, 'open' 或者 'closed', 默认是 'open'. 当设置为 'closed', 节点所有的子节点将从远程服务器站点加载
	 * iconCls：图标
	 * checked: 指明检查节点是否选中.
	 * attributes: 可以添加到节点的自定义属性
	 * children: 一个节点数组,定义一些子节点
	 */

	private String id;
	private String text;
	private String state;
	private String iconCls;
	private String checked;
	NodeAttribute attributes = new NodeAttribute();
	private List<EasyUITree> children = new ArrayList<EasyUITree>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public NodeAttribute getAttributes() {
		return attributes;
	}

	public void setAttributes(NodeAttribute attributes) {
		this.attributes = attributes;
	}

	public List<EasyUITree> getChildren() {
		return children;
	}

	public void setChildren(List<EasyUITree> children) {
		this.children = children;
	}

}
