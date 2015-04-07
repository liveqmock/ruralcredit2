package com.creditease.rc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.IAppelleeRecordDAO;
import com.creditease.rc.dao.ICreditApplicationDAO;
import com.creditease.rc.dao.ICreditHistoryDAO;
import com.creditease.rc.dao.ICreditInvestigationDAO;
import com.creditease.rc.dao.ILinkmanInvestigationDAO;
import com.creditease.rc.domain.AppelleeRecord;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.CreditHistory;
import com.creditease.rc.domain.CreditInvestigation;
import com.creditease.rc.domain.LinkmanInvestigation;
import com.creditease.rc.service.IBorrowerServiceAppService;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.ICreditInvestigationService;
import com.creditease.rc.service.IOperateLogService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.CurrencyUtil;
import com.creditease.rc.util.DicUtil;
import com.creditease.rc.vo.CreditInvestigationVo;
import com.creditease.rc.vo.OperateLogBusinessStruct;

/**
 * Title: Description: 农村商贷系统研发 Copyright: Copyright (c) 2012 Company:
 * 普信恒业科技发展（北京）有限公司 Date: 2013-3-14
 * 
 * @author: 解兵丰
 * @version: v1.0
 */
@Service
public class CreditInvestigationService implements ICreditInvestigationService {

	@Resource
	private ICreditInvestigationDAO creditInvestigationDao;

	@Resource
	private ICreditApplicationDAO creditApplicationDAO;

	@Resource
	private ICreditHistoryDAO creditHistoryDAO;

	@Resource
	private ICreditApplicationService creditApplicationService;

	@Resource
	private ILinkmanInvestigationDAO linkmanInvestigationDAO;

	@Resource
	private IAppelleeRecordDAO appelleeRecordDAO;

	@Resource
	private IBorrowerServiceAppService borrowerServiceAppService;

	@Resource
	private IOperateLogService operateLogService;

	@Override
	public boolean addOrUpdateCreditInvestigation(
			CreditInvestigationVo creditInvestigationVo) {

		if (creditInvestigationVo == null) {
			return false;
		} else {
			// update
			if (creditInvestigationVo.getCreditInvestigatioId() != null) {

				creditInvestigationVo.setIsSubmit("0");
				Long getCreditInvestigatioId = creditInvestigationVo
						.getCreditInvestigatioId();
				List<LinkmanInvestigation> linkmanInvestigations = creditInvestigationVo
						.getLinkmanInvestigationList();
				if (CommonUtil.isNotEmpty(linkmanInvestigations)) {
					for (LinkmanInvestigation linkmanInvestigation : linkmanInvestigations) {
						linkmanInvestigation.setCreditInvestigatioId(getCreditInvestigatioId);
					}
				}
				// creditInvestigationDao.updateByPrimaryKey(creditInvestigationVo);
				creditHistoryDAO.batchUpdate(
						"RL_CREDIT_HISTORY.abatorgenerated_updateByPrimaryKey",
						creditInvestigationVo.getCreditHistoryList());
				// linkmanInvestigationDAO.batchUpdate("RL_LINKMAN_INVESTIGATION.abatorgenerated_updateByPrimaryKey",
				// creditInvestigationVo.getLinkmanInvestigationList());
				
				linkmanInvestigationDAO.deleteBycreditInvestigatioId(creditInvestigationVo.getCreditInvestigatioId());
				linkmanInvestigationDAO.batchInsert(
						"RL_LINKMAN_INVESTIGATION.abatorgenerated_insert",
						creditInvestigationVo.getLinkmanInvestigationList());
				 appelleeRecordDAO.updateByPrimaryKey(creditInvestigationVo.getAppelleeRecord());

				OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
				operateLogBusinessStruct
						.setCreditapplicationId(creditInvestigationVo
								.getCreditapplicationId());
				operateLogBusinessStruct.setFunctionCode("100030");
				operateLogService.insert(operateLogBusinessStruct);

				return true;
			} else {
				// insert
				Long getCreditapplicationId = creditInvestigationVo
						.getCreditapplicationId();
				if (getCreditapplicationId == null) {
					return false;
				} else {
					List<CreditInvestigationVo> creditInvestigationVos = (List<CreditInvestigationVo>) creditInvestigationDao
							.queryList(
									"RL_CREDIT_INVESTIGATIO.queryBycreditapplicationId",
									getCreditapplicationId);

					if (CommonUtil.isNotEmpty(creditInvestigationVos)) {
						return false;
					}
				}

				creditInvestigationVo.setIsValid("1");
				creditInvestigationDao.insert(creditInvestigationVo);
				Long creditInvestigatioId = creditInvestigationVo
						.getCreditInvestigatioId();
				List<CreditHistory> creditHistoryList = creditInvestigationVo
						.getCreditHistoryList();
				for (CreditHistory creditHistory : creditHistoryList) {
					creditHistory.setCreditInvestigatioId(creditInvestigatioId);
				}
				creditHistoryDAO.batchInsert(
						"RL_CREDIT_HISTORY.abatorgenerated_insert",
						creditHistoryList);

				List<LinkmanInvestigation> linkmanInvestigationList = creditInvestigationVo
						.getLinkmanInvestigationList();
				for (LinkmanInvestigation linkmanInvestigation : linkmanInvestigationList) {
					linkmanInvestigation
							.setCreditInvestigatioId(creditInvestigatioId);
				}
				linkmanInvestigationDAO.batchInsert(
						"RL_LINKMAN_INVESTIGATION.abatorgenerated_insert",
						linkmanInvestigationList);

				creditInvestigationVo.getAppelleeRecord()
						.setCreditInvestigatioId(creditInvestigatioId);
				appelleeRecordDAO.insert(creditInvestigationVo
						.getAppelleeRecord());

				OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
				operateLogBusinessStruct
						.setCreditapplicationId(creditInvestigationVo
								.getCreditapplicationId());
				operateLogBusinessStruct.setFunctionCode("100030");
				operateLogService.insert(operateLogBusinessStruct);

				return true;
				// TODO: handle exception
			}
		}
	}

	@Override
	public CreditInvestigationVo selectInvestigationById(
			Long creditInvestigationId) {
		return creditInvestigationDao.selectByPrimaryKey(creditInvestigationId);
	}

	@Override
	public List<CreditInvestigationVo> quertyReportList(Long creditapplicationId) {
		// TODO Auto-generated method stub
		List<CreditInvestigationVo> s = (List<CreditInvestigationVo>) creditInvestigationDao
				.queryList("RL_CREDIT_INVESTIGATIO.quertyReportList",
						creditapplicationId);
		return s;
	}

	@Override
	public CreditInvestigationVo showReturnCreditInvestigation(
			Long creditInvestigationId) {
		// TODO Auto-generated method stub
		CreditInvestigationVo creditInvestigation = new CreditInvestigationVo();
		creditInvestigation = (CreditInvestigationVo) creditInvestigationDao
				.queryUnique("RL_CREDIT_INVESTIGATIO.select",
						creditInvestigationId);
		creditInvestigation
				.setCreditHistoryList((List<CreditHistory>) creditHistoryDAO
						.queryList("RL_CREDIT_HISTORY.queryList",
								creditInvestigationId));
		creditInvestigation
				.setLinkmanInvestigationList((List<LinkmanInvestigation>) linkmanInvestigationDAO
						.queryList("RL_LINKMAN_INVESTIGATION.queryList",
								creditInvestigationId));
		creditInvestigation
				.setAppelleeRecord((AppelleeRecord) appelleeRecordDAO
						.queryUnique("RL_APPELLEE_RECORD.query",
								creditInvestigationId));
		return creditInvestigation;
	}

	@Override
	public int updateCheckStatus(CreditInvestigationVo creditInvestigationVo) {
		// TODO Auto-generated method stub

		String status = (String) creditApplicationDAO.queryUnique(
				"CREDITAPPLICATION.selectAuditStatusByPrimaryKey",
				creditInvestigationVo.getCreditapplicationId());
		if (status != null) {
			if (Constants.STATE_0.equals(status)
					|| Constants.STATE_7.equals(status)) {
				// 如果状态是未提交或者撤回则将业务状态改为风险经理提交
				CreditApplication creditApplication = new CreditApplication();
				creditApplication.setCreditapplicationId(creditInvestigationVo
						.getCreditapplicationId().intValue());

				creditApplication.setAuditStatus(Constants.STATE_23);
				creditApplicationDAO.update(
						"CREDITAPPLICATION.updateAuditStatus",
						creditApplication);
				return 1;
			} else if (Constants.STATE_22.equals(status)) {
				// 如果状态是客户经理提交则将业务状态改成审查中
				creditApplicationService.submitCreditApplication(
						creditInvestigationVo.getCreditapplicationId()
								.intValue(), Constants.STATE_1);
				return 1;
			} else if (Constants.STATE_23.equals(status)) {
				// 如果状态是风险经理提交则不做操作
				return 0;
			} else {
				// 其他状态则是状态有问题 不做操作
				return 0;
			}
		} else {
			return 2;
		}
	}

	@Override
	public CreditApplication selectCreditApplication(Long creditapplicationId) {
		// TODO Auto-generated method stub
		return (CreditApplication) creditInvestigationDao.queryUnique(
				"CREDITAPPLICATION.selectByCreditApplicationId",
				creditapplicationId);
	}

	@Override
	public List<CreditInvestigationVo> selectCreditInvestigationVoList(
			Long creditapplicationId) {
		// TODO Auto-generated method stub
		return (List<CreditInvestigationVo>) creditInvestigationDao.queryList(
				"RL_CREDIT_INVESTIGATIO.quertyReportList", creditapplicationId);
	}

	@Override
	public boolean updateSecondFlag(CreditApplication creditApplication) {
		// TODO Auto-generated method stub
		creditApplicationDAO.update("", creditApplication);
		return false;
	}

	@Override
	public boolean updateSubmit(Long creditInvestigationId,
			Long creditapplicationId) {
		// TODO Auto-generated method stub
		creditApplicationDAO.update("RL_CREDIT_INVESTIGATIO.updateSubmit",
				creditInvestigationId);
		return true;

	}

	@Override
	public CreditInvestigationVo selectNameAndSpousName(Long creditapplicationId) {
		// TODO Auto-generated method stub

		// List<CreditInvestigationVo> creditInvestigationVoList =

		return (CreditInvestigationVo) creditInvestigationDao.queryList(
				"RL_CREDIT_INVESTIGATIO.selectNameAndSpousName",
				creditapplicationId).get(0);
	}

	/**
	 * 查看信用背景调查表时将选择项对应的code显示为汉字
	 * 
	 * @param creditInvestigation
	 *            CreditInvestigationVo
	 */
	@Override
	public void displayNameByCode(CreditInvestigationVo creditInvestigation) {

		List<CreditHistory> chis = creditInvestigation.getCreditHistoryList();
		List<LinkmanInvestigation> lkis = creditInvestigation
				.getLinkmanInvestigationList();
		AppelleeRecord ar = creditInvestigation.getAppelleeRecord();

		// 处理信用历史
		if (chis != null) {
			int chisLength = chis.size();
			if (chisLength > 0) {
				for (int i = 0; i < chisLength; i++) {
					CreditHistory ch = chis.get(i);
					// 贷款机构
					String depositOrganizationValue = DicUtil
							.convertCodeKeyToCodeValue("depositOrganization",
									ch.getLoanOrganization());
					// 账户状态
					String accountStatusValue = DicUtil
							.convertCodeKeyToCodeValue("accountStatus",
									ch.getAccountStatus());
					// 还款方式
					String repaymentTypeValue = DicUtil
							.convertCodeKeyToCodeValue("repaymentType",
									ch.getRepaymentType());

					ch.setLoanOrganization(depositOrganizationValue);
					ch.setAccountStatus(accountStatusValue);
					ch.setRepaymentType(repaymentTypeValue);
				}
			}
		}
		// 处理联系人调查
		if (lkis != null) {
			int lkisLength = lkis.size();
			if (lkisLength > 0) {
				for (int i = 0; i < lkisLength; i++) {
					LinkmanInvestigation lig = lkis.get(i);
					// 和申请人关系
					String relationValue = DicUtil.convertCodeKeyToCodeValue(
							//"borrowerreRationship", lig.getRelation());
							"borrowerreRationB", lig.getNewrelation());
					// 你认识（申请人姓名）多久了
					String howLongValue = DicUtil.convertCodeKeyToCodeValue(
							"howLong", lig.getHowLong());
					// 对（申请人夫妻姓名）有任何不满吗
					String discontValue = DicUtil.convertCodeKeyToCodeValue(
							"answer", lig.getDiscontentment());
					// （申请人夫妇姓名）是否有长期赌博的习惯呢
					String gamblingValue = DicUtil.convertCodeKeyToCodeValue(
							"answer", lig.getGambling());

					//lig.setRelation(relationValue);
					lig.setNewrelation(relationValue);
					lig.setHowLong(howLongValue);
					lig.setDiscontentment(discontValue);
					lig.setGambling(gamblingValue);

				}
			}
		}
		// 被执行记录
		if (ar != null) {
			String isAppelleeValue = DicUtil.convertCodeKeyToCodeValue(
					"answer", ar.getIsAppelleeRecord());
			String statusOneValue = DicUtil.convertCodeKeyToCodeValue("accusalStatus",
					ar.getAccusalStatusOne());
			String statusTwoValue = DicUtil.convertCodeKeyToCodeValue("accusalStatus",
					ar.getAccusalStatusTwo());

			ar.setIsAppelleeRecord(isAppelleeValue);
			ar.setAccusalStatusOne(statusOneValue);
			ar.setAccusalStatusTwo(statusTwoValue);
		}
		// 总结
		String resultValue = DicUtil.convertCodeKeyToCodeValue("result",
				creditInvestigation.getResult());
		creditInvestigation.setResult(resultValue);

	}

	@Override
	public boolean saveAndSubmit(CreditInvestigationVo creditInvestigationVo,
			Long creditapplicationId) {
		// TODO Auto-generated method stub
		boolean isSuccess = false;
		isSuccess = addOrUpdateCreditInvestigation(creditInvestigationVo);

		if (isSuccess) {
			isSuccess = updateSubmit(
					creditInvestigationVo.getCreditInvestigatioId(),
					creditapplicationId);

		}
		return isSuccess;
	}
}
