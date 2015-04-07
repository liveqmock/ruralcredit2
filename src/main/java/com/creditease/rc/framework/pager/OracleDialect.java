/**
 * 
 */
package com.creditease.rc.framework.pager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright:Copyright (c) 2011</p>
 * <p>Company:普信恒业科技发展(北京)有限公司</p>
 * <p>Date:2011-10-17</p>
 * <p>Modify:</p>
 * <p>Bug:</p>
 * 
 * @author wangxinqiang
 * @version 1.0
 */
public class OracleDialect implements Dialect {

	private final Log logger = LogFactory.getLog(OracleDialect.class);
	
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
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		if (offset > 0) {
			pagingSelect
					.append("select * from ( select row_.*, rownum rownum_ from ( ");
		} else {
			pagingSelect.append("select * from ( ");
		}
		pagingSelect.append(sql);
		if (offset > 0) {
			int end = offset + limit;
			pagingSelect.append(" ) row_ ) where rownum_ <= " + end
					+ " and rownum_ > " + offset);
		} else {
			pagingSelect.append(" ) where rownum <= " + limit);
		}

		if (isForUpdate) {
			pagingSelect.append(" for update");
		}
		logger.info("分页SQL:"+pagingSelect.toString());
	
		return pagingSelect.toString();
	}
}
