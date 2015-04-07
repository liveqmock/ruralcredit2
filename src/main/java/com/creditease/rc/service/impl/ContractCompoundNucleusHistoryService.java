package com.creditease.rc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.IContractCompoundNucleusHistoryDao;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IContractCompoundNucleusHistoryService;
import com.creditease.rc.util.DESPlus;
import com.creditease.rc.vo.ContractAndLoan;

/**
 * 
 * @author luohongjie
 *
 */
@Service
public class ContractCompoundNucleusHistoryService implements
		IContractCompoundNucleusHistoryService {

	@Resource
	private IContractCompoundNucleusHistoryDao contractHistoryDao;
	@Override
	  public Pagination contractDateGrid(Map<String, String> queryMap, Pagination pagination) {
	    // TODO Auto-generated method stub
	    Pagination paginationResult = new Pagination();
	    paginationResult =contractHistoryDao.contractDateGrid(queryMap, pagination);
	    List<CreditApplication> items = paginationResult.getRows();
	    for (CreditApplication c : items) {
	      if (c.getCreditapplicationId() != null) {
	        try {
	          DESPlus des = new DESPlus();
	          StringBuffer sb = new StringBuffer(String.valueOf(c.getCreditapplicationId()));
	          sb.append(Constants.CM_LOAN);
	          String creditapplicationDESId = des.encrypt(sb.toString());
	          // 查看申请单附件的 id （与定义的属性 意义不同）
	          c.setLaonDESId((des.encrypt(String.valueOf(c.getCreditapplicationId()))));
	          // 查看代后资料的 id （与定义的属性 意义不同）（LaonDESId 与 CreditapplicationDESId 的意义对调了 ）
	          c.setCreditapplicationDESId(creditapplicationDESId);
	        } catch (Exception e) {
	          e.printStackTrace();
	        }
	      }
	    }
	    return paginationResult;
	  }

}
