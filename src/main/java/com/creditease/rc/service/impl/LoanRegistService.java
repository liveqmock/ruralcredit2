package com.creditease.rc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.ILoanRegistDAO;
import com.creditease.rc.domain.LoanRegist;
import com.creditease.rc.service.ILoanRegistService;

/**
 * 放款登记详细服务
 * @author zhangman
 *
 */
@Service
public class LoanRegistService implements ILoanRegistService {

	@Resource
	private ILoanRegistDAO loanRegistDAO;
	 
	
	@Override
	public void addBatchLoanRegist(List<LoanRegist> loanRegistList) {
			loanRegistDAO.addBatch(loanRegistList);
	}
	@Override
	public List<LoanRegist> selectLoanRegistList(int groupLoanRegistId) {
		// TODO Auto-generated method stub
		return loanRegistDAO.selectLoanRegistList(groupLoanRegistId);
	}
	@Override
	public List<LoanRegist> selectLoanRegists(int creditApplicationId) {
		return loanRegistDAO.selectLoanRegists(creditApplicationId);
	}
}
