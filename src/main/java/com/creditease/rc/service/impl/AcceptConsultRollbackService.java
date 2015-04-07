package com.creditease.rc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IAcceptConsultRollbackDao;
import com.creditease.rc.domain.AcceptConsultRollback;
import com.creditease.rc.service.IAcceptConsultRollbackService;

@Service
public class AcceptConsultRollbackService implements IAcceptConsultRollbackService {

	@Resource
	private IAcceptConsultRollbackDao acceptConsultRollbackDAO;

	/**
	 * 根据咨询池id获取退回原因对象信息
	 */
	public AcceptConsultRollback getAcceptConsultRollbackByForeignKey(Long consultPoolId) {
		return acceptConsultRollbackDAO.selectByForeignKey(consultPoolId);
	}

	/**
	 * 更新退回原因对象信息为历史
	 */
	public boolean updateAcceptConsultRollbackForHis(Long consultPoolId) {
		AcceptConsultRollback acceptConsultRollback = getAcceptConsultRollbackByForeignKey(consultPoolId);
		if (acceptConsultRollback != null) {
			int rows = acceptConsultRollbackDAO.updateAcceptConsultRollbackForHis(consultPoolId);
			return (rows > 0) ? true : false;
		} else {
			return true;
		}
	}

	/**
	 * 增加退回原因对象信息
	 */
	public boolean addAcceptConsultRollback(AcceptConsultRollback acceptConsultRollback) {
		Long pk = acceptConsultRollbackDAO.addAcceptConsultRollback(acceptConsultRollback);
		return pk.longValue() > 0 ? true : false;
	}

}
