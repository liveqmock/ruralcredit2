package com.creditease.rc.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.security.User;
import com.creditease.rc.dao.IMsgLogDAO;
import com.creditease.rc.domain.MsgLog;
import com.creditease.rc.service.IMsgLogService;
@Service
public class MsgLogService implements IMsgLogService {

	@Resource
	private IMsgLogDAO msgLogDao;
	/**
	 * 保存现金流入流出错误的最大借款额度的相关信息
	 * CreditApplicationDAO 实现方法：
	 * @param MsgLog
	 */
	//保存操作日志
	@Override
	public void insert(MsgLog msgLog) {
		// TODO Auto-generated method stub
		User user = SpringSecurityUtils.getCurrentUser();
		//操作人ID
		msgLog.setOperatorId(Long.parseLong(user.getUserId()));
		System.out.println(user.getUserId());
		//操作人姓名
		msgLog.setOperatorName(user.getName_zh());
		//操作时间
		msgLog.setOperateDate(new Date());
		msgLogDao.insert(msgLog);
	}
	
	/**
	 * 保存现金流入流出旧数据的错误的最大借款额度的相关信息
	 * CreditApplicationDAO 实现方法：
	 * @param MsgLog
	 * 2014-06-25
	 */
	//保存操作日志
	@Override
	public void insertOldData(MsgLog msgLog) {
		msgLogDao.insert(msgLog);
	}


	@Override
	public int updateByPrimaryKeySelective(MsgLog record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MsgLog selectByPrimaryKey(Short msgLogId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MsgLog> selectMsgLogList(int creditapplicationId) {
		return msgLogDao.selectMsgLogList(creditapplicationId);
	}

	@Override
	public int deleteBycreditapllicationId(Long creditapplicationId) {
		return msgLogDao.deleteBycreditapllicationId(creditapplicationId);
	}

	@Override
	public int updateMsgLogDataByCreditapplicationId(MsgLog msgLog) {
		return msgLogDao.updateMsgLogDataByCreditapplicationId(msgLog);
	}
}
