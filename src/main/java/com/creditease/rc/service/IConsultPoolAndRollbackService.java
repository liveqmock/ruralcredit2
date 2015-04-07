package com.creditease.rc.service;

import com.creditease.rc.domain.AcceptConsultRollback;
import com.creditease.rc.vo.CustomerConsultPoolVo;

public interface IConsultPoolAndRollbackService {
	//申请退回
	boolean updateRollBackAndConsultStatus(CustomerConsultPoolVo customerConsultPoolVo,AcceptConsultRollback acceptConsultRollback);

	//确认退回
	boolean updateMarkingConsultStatusConfirm(CustomerConsultPoolVo customerConsultPoolVo,AcceptConsultRollback acceptConsultRollback);

	//取消退回
	boolean updateRollbackReasonCancel(CustomerConsultPoolVo customerConsultPoolVo,AcceptConsultRollback acceptConsultRollback);
}
