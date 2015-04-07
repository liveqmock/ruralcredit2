/**
 * 
 */
package com.creditease.rc.framework.pager;


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
public interface Dialect {

	/**
	 * 是否支持分页
	 * @return boolean
	 */
    public boolean supportsLimit();
    
    /**
     * 拼装sql语句
     * @param sql sql语句
     * @param offset 偏移量
     * @param limit 最大记录条数
     * @return String 
     */
    public String getLimitString(String sql, int offset, int limit);
 
    /**
     * 是否支持偏移量
     * @return boolean
     */
    public boolean supportsLimitOffset();

}
