package com.creditease.rc.dao.impl;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IAcceptConsultRollbackDao;
import com.creditease.rc.domain.AcceptConsultRollback;
import com.creditease.rc.framework.dao.impl.BaseDao;

@Repository
public class AcceptConsultRollbackDao extends BaseDao implements IAcceptConsultRollbackDao {

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method corresponds to the database table ACCEPTCONSULTROLLBACK
	 * 
	 * @abatorgenerated
	 */
	public AcceptConsultRollbackDao() {
		super();
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method corresponds to the database table ACCEPTCONSULTROLLBACK
	 * 
	 * @abatorgenerated
	 */
	public Long insert(AcceptConsultRollback record) {
		Object newKey = getSqlMapClientTemplate().insert("ACCEPTCONSULTROLLBACK.abatorgenerated_insert", record);
		return (Long) newKey;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method corresponds to the database table ACCEPTCONSULTROLLBACK
	 * 
	 * @abatorgenerated
	 */
	public int updateByPrimaryKey(AcceptConsultRollback record) {
		int rows = getSqlMapClientTemplate().update("ACCEPTCONSULTROLLBACK.abatorgenerated_updateByPrimaryKey",
				record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method corresponds to the database table ACCEPTCONSULTROLLBACK
	 * 
	 * @abatorgenerated
	 */
	public int updateByPrimaryKeySelective(AcceptConsultRollback record) {
		int rows = getSqlMapClientTemplate().update(
				"ACCEPTCONSULTROLLBACK.abatorgenerated_updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method corresponds to the database table ACCEPTCONSULTROLLBACK
	 * 
	 * @abatorgenerated
	 */
	public AcceptConsultRollback selectByPrimaryKey(Long acceptConsultRollbackId) {
		AcceptConsultRollback key = new AcceptConsultRollback();
		key.setAcceptConsultRollbackId(acceptConsultRollbackId);
		AcceptConsultRollback record = (AcceptConsultRollback) getSqlMapClientTemplate().queryForObject(
				"ACCEPTCONSULTROLLBACK.abatorgenerated_selectByPrimaryKey", key);
		return record;
	}

	/**
	 * 根据咨询池id查询有效的退回原因对象信息
	 */
	public AcceptConsultRollback selectByForeignKey(Long consultPoolId) {
		AcceptConsultRollback key = new AcceptConsultRollback();
		key.setConsultPoolId(consultPoolId);
		key.setHistoryFlag("0");
		AcceptConsultRollback record = (AcceptConsultRollback) getSqlMapClientTemplate().queryForObject(
				"ACCEPTCONSULTROLLBACK.selectByForeignKey", key);
		return record;
	}

	/**
	 * This method was generated by Abator for iBATIS.
	 * This method corresponds to the database table ACCEPTCONSULTROLLBACK
	 * 
	 * @abatorgenerated
	 */
	public int deleteByPrimaryKey(Long acceptConsultRollbackId) {
		AcceptConsultRollback key = new AcceptConsultRollback();
		key.setAcceptConsultRollbackId(acceptConsultRollbackId);
		int rows = getSqlMapClientTemplate().delete("ACCEPTCONSULTROLLBACK.abatorgenerated_deleteByPrimaryKey",
				key);
		return rows;
	}

	/**
	 * 更新退回原因对象信息为历史
	 */
	public int updateAcceptConsultRollbackForHis(Long consultPoolId) {
		AcceptConsultRollback key = new AcceptConsultRollback();
		key.setConsultPoolId(consultPoolId);
		return update("ACCEPTCONSULTROLLBACK.updateAcceptConsultForHis", key);
	}

	/**
	 * 添加一条退回原因对象信息
	 */
	public Long addAcceptConsultRollback(AcceptConsultRollback acceptConsultRollback) {
		return (Long) getSqlMapClientTemplate().insert("ACCEPTCONSULTROLLBACK.abatorgenerated_insert",
				acceptConsultRollback);
	}
	/**
	 *根据consultPoolId 和 历史标识0查询出信息
	 *lhj
	 */
	public AcceptConsultRollback getAcceptConsultRollbackByHistoryFlag(Long consultPoolId){
		AcceptConsultRollback key = new AcceptConsultRollback();
		key.setConsultPoolId(consultPoolId);
		key.setHistoryFlag("0");
		AcceptConsultRollback record = (AcceptConsultRollback) getSqlMapClientTemplate().queryForObject(
				"ACCEPTCONSULTROLLBACK.selectByForeignKey", key);
		return record;
	}
}