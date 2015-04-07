/**
 * Title:CompactRulePrefixVO.java
 * Description:
 */
package com.creditease.rc.vo;


/**
 * Title:CompactRulePrefixVO.java
 * Description:
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 
 * @author 韩大年
 * @version v1.0
 *          2013-9-22
 */
public class CompactRulePrefixVO extends CompactRulePrefix_1 {

	/**
	 * @Description 默认构造器
	 */
	public CompactRulePrefixVO() {
		// TODO Auto-generated constructor stub
	}

	// 排序字段
	private String sort;
	// 升序or降序
	private String order;

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

}
