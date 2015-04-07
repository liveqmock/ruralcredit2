package com.creditease.rc.service;

import com.creditease.rc.domain.AcceptConsultRollback;

public interface IAcceptConsultRollbackService {

	AcceptConsultRollback getAcceptConsultRollbackByForeignKey(Long consultPoolId);

	boolean updateAcceptConsultRollbackForHis(Long consultPoolId);

	boolean addAcceptConsultRollback(AcceptConsultRollback acceptConsultRollback);
}
