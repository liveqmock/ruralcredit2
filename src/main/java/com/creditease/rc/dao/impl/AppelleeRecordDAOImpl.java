package com.creditease.rc.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IAppelleeRecordDAO;
import com.creditease.rc.domain.AppelleeRecord;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.dao.impl.BaseDao;

/**
 * 
 * @author haoqiang
 * 
 */
@Repository
public class AppelleeRecordDAOImpl extends BaseDao implements IAppelleeRecordDAO {

	@Resource
	private IBaseDao baseDao;

	public IBaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method corresponds to the database table RL_APPELLEE_RECORD
	 * 
	 * @abatorgenerated
	 */
	public AppelleeRecordDAOImpl() {
		super();
	}

	/**
	 * 
	 * @param record AppelleeRecord 对象
	 */
	public void insert(AppelleeRecord record) {
		baseDao.insert("RL_APPELLEE_RECORD.abatorgenerated_insert", record);
	}

	/**
	 * 
	 * @param record AppelleeRecord 对象
	 * @return 更新条目
	 */
	public int updateByPrimaryKey(AppelleeRecord record) {
		int rows = baseDao.update("RL_APPELLEE_RECORD.abatorgenerated_updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * 
	 * @param record AppelleeRecord 对象
	 * @return 更新条目
	 */
	public int updateByPrimaryKeySelective(AppelleeRecord record) {
		int rows = baseDao.update("RL_APPELLEE_RECORD.abatorgenerated_updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * 
	 * @param appelleeRecordId appelleeRecordId主键
	 * @return AppelleeRecord对象
	 */
	public AppelleeRecord selectByPrimaryKey(Long appelleeRecordId) {
		AppelleeRecord key = new AppelleeRecord();
		key.setAppelleeRecordId(appelleeRecordId);
		AppelleeRecord record = (AppelleeRecord) baseDao.queryUnique(
				"RL_APPELLEE_RECORD.abatorgenerated_selectByPrimaryKey", key);
		return record;
	}

	/**
	 * 
	 * @param appelleeRecordId appelleeRecordId主键
	 * @return 删除的条数
	 */
	public int deleteByPrimaryKey(Long appelleeRecordId) {
		AppelleeRecord key = new AppelleeRecord();
		key.setAppelleeRecordId(appelleeRecordId);
		int rows = baseDao.delete("RL_APPELLEE_RECORD.abatorgenerated_deleteByPrimaryKey", key);
		return rows;
	}

}