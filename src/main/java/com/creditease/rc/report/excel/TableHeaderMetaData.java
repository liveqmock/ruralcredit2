package com.creditease.rc.report.excel;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Title:TableHeaderMetaData.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-5-2下午02:51:57
 * @author 王毅峰
 * @version v2.0
 */
public class TableHeaderMetaData {
	private LinkedList<TableColumn> columns;

	private LinkedList<TableColumn> leafs;

	private String common;

	public int maxlevel = 0;

	public TableHeaderMetaData() {
		columns = new LinkedList<TableColumn>();
		leafs = new LinkedList<TableColumn>();
	}

	public void addColumn(TableColumn col) {
		setLevel(col, 1);
		columns.add(col);
		addLeafColumn(col);
	}

	public void refresh() {
		maxlevel = 1;
		for (TableColumn col : columns) {
			if (col.isVisible()) {
				col.level = 1;
				int level = refreshChildren(col);
				if (level > maxlevel)
					maxlevel = level;
			}
		}
	}

	private int refreshChildren(TableColumn parent) {
		if (parent.children.size() != 0) {
			int max = parent.level;
			for (TableColumn col : parent.children) {
				if (col.isVisible()) {
					col.parent = parent;
					col.level = parent.level + 1;
					int level = refreshChildren(col);
					if (level > max)
						max = level;
				}
			}
			return max;
		} else {
			return parent.level;
		}
	}

	private void setLevel(TableColumn col, int level) {
		col.level = level;
		if (col.isVisible() && level > maxlevel)
			maxlevel = level;
	}

	private void addLeafColumn(TableColumn col) {
		if (col.parent != null)
			setLevel(col, col.parent.level + 1);
		if (col.isComplex()) {
			for (TableColumn c : col.getChildren()) {
				addLeafColumn(c);
			}
		} else {
			leafs.add(col);
		}
	}

	public List<TableColumn> getColumns() {
		return leafs;
	}

	public List<TableColumn> getOriginColumns() {
		LinkedList<TableColumn> ret = new LinkedList<TableColumn>();
		for (TableColumn c : columns) {
			if (c.isVisible())
				ret.add(c);
		}
		return ret;
	}

	public TableColumn getColumnAt(int index) {
		return leafs.get(index);
	}

	public int getColumnCount() {
		return leafs.size();
	}

	public String getCommon() {
		return common;
	}

	public void setCommon(String common) {
		this.common = common;
	}
}
