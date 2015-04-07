package com.creditease.rc.framework.pager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Title: 
 * Description: 农村商贷系统研发
 * Copyright: Copyright (c) 2012
 * Company: 普信恒业科技发展（北京）有限公司
 * Date: 2012-6-7
 * @author: 解兵丰
 * @version: v1.0
 */
public class MySQLDialect implements Dialect {
	
	private final Log logger = LogFactory.getLog(MySQLDialect.class);
	
	/**
	 * 是否支持分页
	 * @return boolean
	 */
	public boolean supportsLimit() {
		return true;
	}

	/**
     * 是否支持偏移量
     * @return boolean
     */
	public boolean supportsLimitOffset() {
		return true;
	}

	/**
     * 拼装sql语句
     * @param sql sql语句
     * @param offset 偏移量
     * @param limit 最大记录条数
     * @return String 
     */
	public String getLimitString(String sql, int offset, int limit) {
		sql = sql.trim();

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		pagingSelect.append(sql).append(" limit ").append(offset).append(", ").append(limit);

		logger.info("分页SQL:"+pagingSelect.toString());
	
		return pagingSelect.toString();
	}

}
