package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.ICreditApplicationDAO;
import com.creditease.rc.dao.IReceivedRecordDao;
import com.creditease.rc.dao.IReturnPlanDao;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.LocalOfficeIdDTO;
import com.creditease.rc.domain.LocalOverdueInfoResponse;
import com.creditease.rc.domain.LocalSellIdDTO;
import com.creditease.rc.domain.ReceivedRecord;
import com.creditease.rc.domain.ReturnPlan;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IReceivablesListService;
import com.creditease.rc.service.IReceivablesRegistrationService;
import com.creditease.rc.service.IReturnPlanService;
import com.creditease.rc.service.IRural2CreditService;
import com.creditease.rc.service.IStatisticalinfoService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.vo.OverDueListVo;

/**
 * 
 * @author haoqiang
 * 
 */
@Service
public class ReceivablesListService implements IReceivablesListService {
	@Resource
	private ICreditApplicationDAO creditApplicationDao;

	@Resource
	private IReturnPlanDao returnPlanDao;

	@Resource
	private IReceivedRecordDao receivedRecordDao;

	@Resource
	private IReturnPlanService returnPlanService;

	@Resource
	private IRural2CreditService rural2CreditService;

	@Resource
	private IStatisticalinfoService statisticalinfoService;

	@Override
	public Pagination receivablesDataGrid(Pagination pagination, CreditApplication creditapplication) {
		// TODO Auto-generated method stub
		Pagination rPagination = creditApplicationDao.queryForPaginatedList("CREDITAPPLICATION.selectfuzzay",
				"CREDITAPPLICATION.countByPage", creditapplication, pagination.getStartResult(),
				pagination.getPageSize());
		return rPagination;
	}

	@Override
	public boolean updateDefaultReturnmentWay(Integer creditapplicationId, String param, String p, Integer accountInfoId) {
		// TODO Auto-generated method stub
		ReturnPlan returnPlan = new ReturnPlan();
		//returnPlan.setCreditapplicationId(creditapplicationId);
		List<ReturnPlan> returnPlanList = (List<ReturnPlan>) returnPlanDao.queryList("RETURNPLAN.dynamicSelect",
				returnPlan);
		if ("0".equals(p)) {
			// 直接更改默认还款方式
			CreditApplication creditApplication = new CreditApplication();
			creditApplication.setCreditapplicationId(creditapplicationId);
			creditApplication.setReturnAccountInfoId(accountInfoId);
			if ("0".equals(param)) {
				creditApplication.setDefaultReturnmentWay("1");
				creditApplicationDao.update("CREDITAPPLICATION.updateDefaultReturnmentWay", creditApplication);
				for (ReturnPlan r : returnPlanList) {
					r.setAutoSwitch("1");
				}
				returnPlanDao.batchUpdate("RETURNPLAN.dynamicUpdate", returnPlanList);
				return true;
			} else if ("1".equals(param)) {
				creditApplication.setDefaultReturnmentWay("0");
				creditApplicationDao.update("CREDITAPPLICATION.updateDefaultReturnmentWay", creditApplication);
				for (ReturnPlan r : returnPlanList) {
					r.setAutoSwitch("0");
				}
				returnPlanDao.batchUpdate("RETURNPLAN.dynamicUpdate", returnPlanList);
				return true;
			} else {
				return false;
			}
		} else if ("1".equals(p)) {
			// 先撤销在更改默认还款方式
			// receivablesRegistrationService.updateAppointmentRevoked("", receivedRecordIdList);
			return false;
		} else {
			return false;
		}

	}

	@Override
	public int checkForEarlyReturn(Integer creditapplicationId) {
		// TODO Auto-generated method stub
		int re = 0;
		List<ReceivedRecord> receivedRecordList = (List<ReceivedRecord>) receivedRecordDao.queryList(
				"RECEIVEDRECORD.selectForCancel", creditapplicationId);
		if (receivedRecordList.size() == 0) {
			// 证明没有在途资金
			// 通过creditapplicationId去查对应的还款计划全部的哦要！
			ReturnPlan passReturnPlan = new ReturnPlan();
			//passReturnPlan.setCreditapplicationId(creditapplicationId);
			// 得到了全部的还款计划
			List<ReturnPlan> returnPlanList = returnPlanService.queryList(passReturnPlan);
			if (CommonUtil.isNotEmpty(returnPlanList)) {
				// 违约标志false没有违约true违约
				boolean flag = false;
				for (ReturnPlan r : returnPlanList) {
					if ("1".equals(r.getOverdueFlag())) {
						flag = true;
						break;
					}
				}
				if (flag) {
					// 如果违约
					re = 2;
				} else {
					// 如果没有违约
					for (ReturnPlan r : returnPlanList) {
						if ("2".equals(r.getCollectionStatus())) {
							re = 3;
							break;
						}
					}
				}
			} else {
				// returnPlanList是不对滴！
				re = 4;
			}

		} else {
			// 有在途资金
			re = 1;
		}
		return re;
	}

	@Override
	public Pagination queryOverdueGrid(Map<String, String> pramMap, Pagination pagination) {
		// TODO Auto-generated method stub

		return statisticalinfoService.queryOverdueInfoList(pramMap, pagination);
	}

}
