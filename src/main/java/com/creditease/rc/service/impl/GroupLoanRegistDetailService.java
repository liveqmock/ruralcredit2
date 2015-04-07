package com.creditease.rc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IGroupLoanRegistDetailDAO;
import com.creditease.rc.domain.GroupLoanRegistDetail;
import com.creditease.rc.service.IGroupLoanRegistDetailService;

/**
 * 放款登记详细服务
 * @author zhangman
 *
 */
@Service
public class GroupLoanRegistDetailService implements IGroupLoanRegistDetailService {

	@Resource
	private IGroupLoanRegistDetailDAO groupLoanRegistDetailDAO;
	@Override
	public void addBatchGroupLoanDetail(List<GroupLoanRegistDetail> groupLoanRegistDetailList) {
		// TODO Auto-generated method stub
		groupLoanRegistDetailDAO.addBatch(groupLoanRegistDetailList);
	}

	
}
