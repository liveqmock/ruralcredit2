package com.creditease.rc.dao;

import com.creditease.rc.domain.AppelleeRecord;
import com.creditease.rc.framework.dao.IBaseDao;

/**
 * 
 * @author haoqiang
 * 
 */
public interface IAppelleeRecordDAO extends IBaseDao {
	/**
	 * 
	 * @param record AppelleeRecord 对象
	 */
	void insert(AppelleeRecord record);

	/**
	 * 
	 * @param record AppelleeRecord 对象
	 * @return 更新条目
	 */
	int updateByPrimaryKey(AppelleeRecord record);

	/**
	 * 
	 * @param record AppelleeRecord 对象
	 * @return 更新条目
	 */
	int updateByPrimaryKeySelective(AppelleeRecord record);

	/**
	 * 
	 * @param appelleeRecordId appelleeRecordId主键
	 * @return AppelleeRecord对象
	 */
	AppelleeRecord selectByPrimaryKey(Long appelleeRecordId);

	/**
	 * 
	 * @param appelleeRecordId appelleeRecordId主键
	 * @return 删除的条数
	 */
	int deleteByPrimaryKey(Long appelleeRecordId);
}