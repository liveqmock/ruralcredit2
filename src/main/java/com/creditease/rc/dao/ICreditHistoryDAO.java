package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.CreditHistory;
import com.creditease.rc.framework.dao.IBaseDao;

/**
 * 
 * @author haoqiang
 * 
 */
public interface ICreditHistoryDAO extends IBaseDao {
	/**
	 * 
	 * @param record CreditHistory对象
	 */
	void insert(CreditHistory record);

	/**
	 * 
	 * @param record CreditHistory对象
	 * @return 更新的条数
	 */
	int updateByPrimaryKey(CreditHistory record);

	/**
	 * 
	 * @param record CreditHistory对象
	 * @return 更新的条数
	 */
	int updateByPrimaryKeySelective(CreditHistory record);

	/**
	 * 
	 * @param creditHistoryId 主键
	 * @return CreditHistory对象
	 */
	CreditHistory selectByPrimaryKey(Long creditHistoryId);

	/**
	 * 
	 * @param creditHistoryId 主键
	 * @return 删除的条数
	 */
	int deleteByPrimaryKey(Long creditHistoryId);

	/**
	 * 
	 * @param sqlId SQL的ID
	 * @param entityList CreditHistoryList
	 */
	void batchInsert(String sqlId, List<CreditHistory> entityList);

	/**
	 * 
	 * @param sqlId SQL的ID
	 * @param entityList CreditHistoryList
	 * @return 更新的条数
	 */
	int batchUpdate(String sqlId, List<CreditHistory> entityList);

}