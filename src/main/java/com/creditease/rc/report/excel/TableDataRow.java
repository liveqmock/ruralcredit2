package com.creditease.rc.report.excel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.creditease.rc.util.DateUtil;

/**
 * 
 * Title:TableDataRow.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-5-2下午02:51:46
 * @author 王毅峰
 * @version v2.0
 */
public class TableDataRow {
	private LinkedList<TableDataCell> cells;

	private TableData table;

	private int rowStyle = TableData.STYLE_TYPE_STRING;

	public void addCell(TableDataCell cell) {
		cells.add(cell);
	}

	public void addCell(String value) {
		TableDataCell cell = new TableDataCell(this);
		cell.setValue(value);
		cell.setCellStyle(rowStyle);
		addCell(cell);
	}

	public void addCell(Integer value) {
		TableDataCell cell = new TableDataCell(this);
		cell.setValue(value);
		cell.setCellStyle(rowStyle);
		addCell(cell);
	}
	public void addCell(Date value) {
		TableDataCell cell = new TableDataCell(this);
		cell.setValue(DateUtil.dateConvertString(value));
		cell.setCellStyle(rowStyle);
		addCell(cell);
	}

	public void addCell(Double value) {
		TableDataCell cell = new TableDataCell(this);
		cell.setCellStyle(TableData.STYLE_TYPE_FLOAT_2);
		cell.setValue(value);
		addCell(cell);
	}

	public void addCell(Object value){
		if (value instanceof String){
			addCell((String) value);
		}else if (value instanceof Integer){
			addCell((Integer) value);
		}else if (value instanceof Double){
			addCell((Double) value);
		}else if (value instanceof BigDecimal){
			addCell(value.toString());
		}else if (value instanceof Long){
			addCell(value.toString());
		}else if(value instanceof Date){
			addCell((Date)value);
		}else if(value == null){
			addCell("");
		}
	}

	public TableDataCell getCellAt(int index) {
		return cells.get(index);
	}

	public List<TableDataCell> getCells() {
		return cells;
	}

	public TableData getTable() {
		return table;
	}

	public TableDataRow(TableData table) {
		cells = new LinkedList<TableDataCell>();
		this.table = table;
	}

	public void setRowStyle(int rowStyle) {
		this.rowStyle = rowStyle;
	}

	public int getRowStyle() {
		return rowStyle;
	}
}
