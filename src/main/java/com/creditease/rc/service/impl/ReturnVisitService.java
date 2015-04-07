package com.creditease.rc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IReturnVisitDAO;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.ReturnVisit;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.IReturnVisitService;
import com.creditease.rc.vo.ReturnVisitVo;

/**
 * 
 * @author zhangman
 * Copyright: Copyright (c) 2012
 * Company: 普信恒业科技发展（北京）有限公司
 */
@Service
public class ReturnVisitService implements IReturnVisitService {

	@Resource
	private IReturnVisitDAO returnVisitDAO;
	@Resource
	private ICreditApplicationService creditApplicationService;
	@Override
	public ReturnVisit addReturnVisit(ReturnVisit returnVisit) {
		// TODO Auto-generated method stub
		CreditApplication creditApplication = creditApplicationService.selectCreditByCustomerId(returnVisit.getBorrowerId().intValue());
		if(creditApplication != null){
			returnVisit.setGroupNumber(creditApplication.getGroupNumber());
		}
		long returnvisitId =  returnVisitDAO.insertObject(returnVisit);
		returnVisit.setReturnvisitId(returnvisitId);
		return returnVisit;
	}

	@Override
	public int updateReturnVisit(ReturnVisit returnVisit) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ReturnVisitVo selectReturnVisit(ReturnVisit returnVisit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagination selectReturnVisitList(ReturnVisitVo returnVisit,Pagination pagination,String fuzzyValue) {
		// TODO Auto-generated method stub
		if(fuzzyValue != null && !"".equals(fuzzyValue)){
			return   returnVisitDAO.selectReturnVisitListFuzzy(fuzzyValue, pagination);
		}else{
			return   returnVisitDAO.selectReturnVisitList(returnVisit, pagination);
		}
	}

}
