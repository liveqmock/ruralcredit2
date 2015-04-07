package com.creditease.rc.framework.pager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.creditease.rc.framework.pager.annotation.DBDialect;
import com.creditease.rc.framework.util.PropertiesUtil;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.mapping.statement.RowHandlerCallback;
import com.ibatis.sqlmap.engine.scope.StatementScope;
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
@DBDialect(OracleDialect.class)
public class LimitSqlExecutor extends SqlExecutor{
	
	private String path = "jdbc.properties";
	
	private String key = "jdbc.driverClass";

	private final Log logger = LogFactory.getLog(LimitSqlExecutor.class);

	private Dialect dialect;

	private boolean enableLimit = true;

	/**
	 * 分页构成器，有两种配置方式：
	 * 1. 根据jdbc.properties文件中的driverClass中是否包含MySQL 或 Oracle 来初始化相应dialect
	 * 2. 根据annotation配置的数据库方言自动初始化dialect
	 */
	public LimitSqlExecutor(){
		Class clazzDialect = null;
		
		// 读取方式一配置
		String val = PropertiesUtil.getVlaue(PropertiesUtil.loadProperties(path), key);
		if(val != null && val.contains("Oracle")){
			clazzDialect = OracleDialect.class;
		} else if(val != null && val.contains("MySQL")){
			clazzDialect = MySQLDialect.class;
		}
		
		// 方式一未配置时，读取方式二配置
		if(clazzDialect == null){
			DBDialect dBDialect = LimitSqlExecutor.class.getAnnotation(DBDialect.class);
			if(dBDialect != null){
				clazzDialect = dBDialect.value();
			}
		}
		
		// 初始化方言
		if(clazzDialect != null){
			Constructor constructor;
			try {
				constructor = clazzDialect.getConstructor(new Class[]{});
				setDialect((Dialect) constructor.newInstance(null));
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Dialect getDialect() {
		return dialect;
	}

	/**
	 * 设置方言
	 * @param dialect 方言
	 */
	public void setDialect(Dialect dialect) {
		if (dialect != null) {
			logger.info("[iBATIS] 设置 ibatis LimitSqlExecutor.dialect ="+dialect.getClass().getName());			
		}
		this.dialect = dialect;
	}

	public boolean isEnableLimit() {
		return enableLimit;
	}

	public void setEnableLimit(boolean enableLimit) {
		this.enableLimit = enableLimit;
	}
	
	/**
	 * 执行查询
	 * @param request 请求
	 * @param conn 连接
	 * @param sql sql语句
	 * @param parameters 参数
	 * @param skipResults 起始记录位置
	 * @param maxResults 每页显示的最大记录条数
	 * @param callback 回调对象
	 * @throws SQLException 
	 */
	public void executeQuery(StatementScope request, Connection conn, String sql,
			Object[] parameters, int skipResults, int maxResults,
			RowHandlerCallback callback) throws SQLException {
		String limitSql = sql;
		int changedSkipResults = skipResults;
		int changedMaxResults = maxResults;
		if (supportsLimit()
				&& (skipResults != NO_SKIPPED_RESULTS || maxResults != NO_MAXIMUM_RESULTS)) {
			limitSql = limitSql.trim();
			if (dialect.supportsLimitOffset()) {
				limitSql = dialect.getLimitString(sql, skipResults, maxResults);
				changedSkipResults = NO_SKIPPED_RESULTS;
			} else {
				limitSql = dialect.getLimitString(sql, 0, maxResults);
			}
			changedMaxResults = NO_MAXIMUM_RESULTS;
		}
		super.executeQuery(request, conn, limitSql, parameters,
				changedSkipResults, changedMaxResults, callback);
	}

	/**
	 * 是否支持分页实现
	 * @return boolean
	 */
	public boolean supportsLimit() {
		if (enableLimit && dialect != null) {
			return dialect.supportsLimit();
		}
		return false;
	}

}
