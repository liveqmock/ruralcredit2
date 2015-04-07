package com.creditease.rc.framework.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.creditease.rc.framework.common.Contants;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.exception.AppDaoException;
import com.creditease.rc.framework.pager.LimitSqlExecutor;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.framework.util.RefUtils;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;

/**
 * <p>Title:BaseDao基类 </p>
 * <p>Description:</p>
 * <p>Copyright:Copyright (c) 2011</p>
 * <p>Company:普信恒业科技发展(北京)有限公司</p>
 * <p>Date:2011-10-12</p>
 * <p>Modify:</p>
 * <p>Bug:</p>
 * 
 * @author wangxinqiang
 * @version 1.0
 */
@Repository
public class BaseDao extends SqlMapClientDaoSupport implements IBaseDao {

	private final Log logger = LogFactory.getLog(BaseDao.class);
	// 默认批量提交数
	private int COMMIT_NUM = 100;
	
	private SqlMapClient sqlMapClient ;

	/**
	 * 
	 */
	public BaseDao() {
		logger.info("BaseDao实例化");
		int batchSize = Contants.BATCH_SIZE;
		if (batchSize != 0){
			COMMIT_NUM = batchSize;
		}
		sqlMapClient = getSqlMapClientTemplate().getSqlMapClient();
	}
	
	/**
	 * 注入sqlMapClient
	 * @param sqlMapClient sqlMapClient
	 */
	@Resource(name="sqlMapClient")
	public void setSqlMapClientForAutowire(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}
	@Override
	public int delete(String sqlId, Object pk) {
		return getSqlMapClientTemplate().delete(sqlId, pk);
	}
	@Override
	public void batchDelete(String sqlId, List<Integer> list) {
		batchDelete(sqlId, list, COMMIT_NUM);
	}
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void batchDelete(final String sqlId, final List<Integer> list, int commitNum) {
		if(list!=null){
			getSqlMapClientTemplate().execute(new SqlMapClientCallback(){
				@Override
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					executor.delete(sqlId,list);
					executor.executeBatch();
					return null;
				}
			});
		}
	}
	@Override
	public Object insertObject(String sqlId, Object entity){
		return (Object)getSqlMapClientTemplate().insert(sqlId, entity);
	}
	@Override
	public void insert(String sqlId, Object entity) {
			getSqlMapClientTemplate().insert(sqlId, entity);
	}
	@Override
	@SuppressWarnings("unchecked")
	public void batchInsert(String sqlId, List entityList) {
		batchInsert(sqlId, entityList, COMMIT_NUM);
	}
	@Override
	@SuppressWarnings("unchecked")
	public void batchInsert(String sqlId, List entityList, int commitNum) {
		getSqlMapClient();
		if(sqlMapClient == null){
			sqlMapClient = getSqlMapClientTemplate().getSqlMapClient();
		}
		try {
			if (commitNum == 0){
				commitNum = COMMIT_NUM;
			}
			sqlMapClient.startTransaction();
			sqlMapClient.startBatch();
			int size = entityList.size();
			int batch = 0;  
			for (int i = 0; i < size; i++) {
				sqlMapClient.insert(sqlId, entityList.get(i));
				batch++;
				if(batch==commitNum){
					sqlMapClient.executeBatch();
					 batch = 0; 
				}
			}
				sqlMapClient.executeBatch();
				sqlMapClient.commitTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppDaoException(e);
		} finally {
			try {
				sqlMapClient.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new AppDaoException(e.getMessage());
			}
		}
	}
	@Override
	public int update(String sqlId, Object entity) {
		return getSqlMapClientTemplate().update(sqlId, entity);
	}
	@Override
	@SuppressWarnings("unchecked")
	public int batchUpdate(String sqlId, List entityList) {
		return batchUpdate(sqlId, entityList, COMMIT_NUM);
	}
	@Override
	@SuppressWarnings("unchecked")
	public int batchUpdate(String sqlId, List entityList, int commitNum) {
		int batchCount = 0;
			int size = entityList.size();
			for (int i = 0; i < size; i++) {
				batchCount+=getSqlMapClientTemplate().update(sqlId, entityList.get(i));
				
			}
			return batchCount;
	}
	@Override
	public Object queryUnique(String sqlId) {
		return queryUnique(sqlId, null);
	}
	@Override
	public Object queryUnique(String sqlId, Object parameter) {
		return getSqlMapClientTemplate().queryForObject(sqlId, parameter);
	}
	@Override
	public List<?> queryList(String sqlId, Object parameter) {
		return getSqlMapClientTemplate().queryForList(sqlId, parameter);
	}
	@Override
	public List<?> queryList(String sqlId) {
		return queryList(sqlId, null);
	}
	@Override
	public Pagination queryForPaginatedList(String itemSqlId,
			String countSqlId, Object parameter, int skip) {
		return queryForPaginatedList(itemSqlId, countSqlId, parameter, skip,
				Pagination.DEFAULT_PAGE_SIZE);
	}
	@Override
	@SuppressWarnings({ "unchecked", "deprecation" })
	public Pagination queryForPaginatedList(String itemSqlId,
			String countSqlId, Object parameter, int skip, int max) {

			sqlMapClient = getSqlMapClientTemplate().getSqlMapClient();
			if (sqlMapClient instanceof ExtendedSqlMapClient) {
				LimitSqlExecutor limitSqlExecutor = new LimitSqlExecutor();
				RefUtils.setFieldValue(((ExtendedSqlMapClient) sqlMapClient)
						.getDelegate(), "sqlExecutor", SqlExecutor.class,
						limitSqlExecutor);
			}
			
			// 数据项
			List listItem = null;
			// 总页数
			int total = 0;
			try {
				if(max==0){//不分页,
					listItem = sqlMapClient.queryForList(itemSqlId, parameter);
				}else{
					listItem = sqlMapClient.queryForList(itemSqlId, parameter,skip, max);
				}
				total = queryForTotalResult(countSqlId, parameter);
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new AppDaoException(e);
			}
			return new Pagination(listItem, total);
	}

	/**
	 * 
	 *@author yifengwang
	 *@param countSqlId 总页数sqlmap名称
	 *@param parameter 查询条件
	 *@return 2012-12-24下午03:50:13
	 *@throws SQLException 
	 */
	private int queryForTotalResult(String countSqlId, Object parameter)throws SQLException {
		return ((Integer) getSqlMapClientTemplate().queryForObject(countSqlId, parameter)).intValue();
	}
}
