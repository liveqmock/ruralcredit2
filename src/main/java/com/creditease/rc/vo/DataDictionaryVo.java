package com.creditease.rc.vo;
/**
 * 
 * DataDictionaryVo.java
 * @author yifengwang
 * 2012-12-24 下午05:50:05
 * @version V2.0
 */
public class DataDictionaryVo {
	private String section;
	private String codeKey;
	private String codeValue;
	private String sort;
	private String order;
	private String fuzzyQuery;
	private String available;
	private String selectType;
	private String parentId;
	
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getCodeKey() {
		return codeKey;
	}
	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}
	public String getCodeValue() {
		return codeValue;
	}
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getFuzzyQuery() {
		return fuzzyQuery;
	}
	public void setFuzzyQuery(String fuzzyQuery) {
		this.fuzzyQuery = fuzzyQuery;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	public String getSelectType() {
		return selectType;
	}
	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
}
