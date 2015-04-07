package com.creditease.rc.service.impl;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.dao.IFinanceMoneyDao;
import com.creditease.rc.domain.*;
import com.creditease.rc.framework.exception.AppBusinessException;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.*;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.vo.FinanceReceiveVo;
import com.creditease.rc.vo.OperateLogBusinessStruct;
import com.creditease.rc.vo.ReceivedRecordVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 
 * @author Administrator
 * 
 */
@Service
public class FinanceMoneyService implements IFinanceMoneyService {

	@Resource
	private IFinanceMoneyDao financeMoneyDao;
	@Resource
	private IReturnPlanService returnPlanService;
	@Resource
	private IFinancePaymentService financePaymentService;
	@Resource
	private IFinanceReceiveService financeReceiveService;
	@Resource
	private IGroupLoanRegistService groupLoanRegistService;
	@Resource
	private IReceivablesRegistrationService receivablesRegistrationService;
	@Resource
	private IRlCreditApplicationService rlCreditApplicationService;
	@Resource
	private IReceivedRecordListService receivedRecordListService;
	@Resource
	private IFinanceMoneySingleUndoService financeMoneySingleUndoService;
	@Resource
	private IOperateLogService operateLogService;
	@Resource
	private ICreditApplicationService creditApplicationService;
	@Resource
	private IAmountConfirmService amountConfirmService;

	@Override
	public void insertFinanceMoney(FinanceMoney record) {
		financeMoneyDao.insertFinanceMoney(record);
	}

	@Override
	public int updateFinanceMoney(FinanceMoney record) {
		int rows = financeMoneyDao.updateFinanceMoney(record);
		return rows;
	}

	@Override
	public FinanceMoney selectPaymentFinanceMoney(CreditApplication parameter) {
		FinanceMoney record = (FinanceMoney) financeMoneyDao.selectPaymentFinanceMoney(parameter);
		return record;
	}

	/**
	 * 财务消息处理
	 * 
	 * @param list List<ProcessMessage>
	 * @return String
	 */
	@Override
	public String updateFinanceMoney(List<ProcessMessage> list) {
		List<OperateLogBusinessStruct> oList = new ArrayList<OperateLogBusinessStruct>();
		for (ProcessMessage p : list) {
			FinanceMoney financeMoney = new FinanceMoney();
			financeMoney.setBizId(p.getBizId());
			List<FinanceMoney> flist = null;
			String state = null;
			String jiesuanresult = "";
			// 0代表结算划扣成功，否则失败
			if ("0".equals(p.getState())) {
				financeMoney.setFinanceStatus("2");
				state = "0";
				jiesuanresult = "划扣成功";
			} else {
				financeMoney.setFinanceStatus("3");
				state = "1";
				jiesuanresult = "划扣失败";
			}
// noticfetype是结算发的消息中带的一个字段，表示收款还是付款
			if ("F".equals(p.getNoticeType())) {
				flist = this.selectPaymentFinanceMoneyList(financeMoney);
			} else if ("S".equals(p.getNoticeType())) {
				flist = this.selectReceiveFinanceMoneyList(financeMoney);
			}
			for (FinanceMoney f : flist) {
				OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
				operateLogBusinessStruct.setBizid(p.getBizId());
				operateLogBusinessStruct.setCreditapplicationId(f.getCreditapplicationId().longValue());
				String type = f.getType();
// 根据状态加日志
				if ("S".equals(type)) {
					operateLogBusinessStruct.setFunctionCode("50004" + state);
				} else if ("U".equals(type)) {
					operateLogBusinessStruct.setFunctionCode("50006" + state);
				} else {
					operateLogBusinessStruct.setFunctionCode("50003" + state);
				}
				operateLogBusinessStruct.setTradeTime(p.getTradeTime());
				StringBuffer sbf = new StringBuffer();
				sbf.append("订单号=").append(operateLogBusinessStruct.getBizid()).append("信贷申请单ID=")
						.append(operateLogBusinessStruct.getCreditapplicationId()).append("结算平台执行时间=")
						.append(p.getTradeTime());
				operateLogBusinessStruct.setRemark(sbf.toString());
				operateLogBusinessStruct.setResult(jiesuanresult);
				oList.add(operateLogBusinessStruct);
			}
			String pattern = "yyyy-MM-dd HH:mm:ss";
			financeMoney.setTradeTime(DateUtil.stringConvertDate(p.getTradeTime(), pattern));
			financeMoney.setOperateDate(new Date());
			financeMoney.setType(p.getNoticeType());
			financeMoney.setReturnMsg(p.getRetInfo() + ";订单号=" + p.getBizId());
			if ("F".equals(p.getNoticeType())) {
				Integer creditApplicationId = flist.get(0).getAssociationId();
				List<CreditApplication> crediApplicationList = new ArrayList<CreditApplication>();
				String status = null;
				CreditApplication result = creditApplicationService.selectById(creditApplicationId);
				// 根据业务类型改信贷申请单状态 如果是0：分公司业务就改成10：款项已到位；如果是1：个人业务就改成15：还款中
				if ("2".equals(financeMoney.getFinanceStatus()) && "1".equals(result.getBusinessType())) {
					returnPlanService.deleteReturnPlan(creditApplicationId);
//					生成还款计划  标记循环贷
					returnPlanService.addForCreditApplication(creditApplicationId);
					status = "15";
				} else if ("2".equals(financeMoney.getFinanceStatus()) && "0".equals(result.getBusinessType())) {
					status = "10";
				}
				// 如果状态不为空，则修改信贷申请单的状态
				if (null != status) {
					for (FinanceMoney f : flist) {
						Integer creditId = f.getAssociationId();
						CreditApplication c = new CreditApplication();
						c.setCreditapplicationId(creditId);
						c.setAuditStatus(status);
						crediApplicationList.add(c);
					}
					rlCreditApplicationService.updateiRlCreditApplicationByList(crediApplicationList);
				}
			} else if ("S".equals(p.getNoticeType())) {
				financeMoney.setAmount(p.getSuccessAmount().toString());
				Vector<Integer> integerVector = new Vector<Integer>();
				List<Integer> receiveRecordIdList = new ArrayList<Integer>();
				for (FinanceMoney f : flist) {
					Integer receiveRecordId = f.getAssociationId();
					integerVector.add(receiveRecordId);
					receiveRecordIdList.add(receiveRecordId);
				}
				String prsResult = null;
				if ("2".equals(financeMoney.getFinanceStatus())) {
					prsResult = "1";
				} else {
					prsResult = "2";
				}
				receivablesRegistrationService.batchUpdateReceivedStatus(receiveRecordIdList, prsResult);
			}
			financeMoneyDao.updateFinanceMoneyStatus(financeMoney);
		}
		operateLogService.batchInsert(oList);
		return "SUCCESS";
	}

	@Override
	public Pagination selectRecevieList(Pagination pagination, CreditApplication creditApplication) {
		return financeMoneyDao.selectRecevieList(pagination, creditApplication);
	}

	@Override
	public FinanceMoney selectReceiveFinanceMoney(FinanceMoney financeMoney) {
		// TODO Auto-generated method stub
		return financeMoneyDao.selectReceiveFinanceMoney(financeMoney);
	}

	@Override
	public List<FinanceMoney> selectOnline(List<Integer> receivedRecordIdList) {
		return financeMoneyDao.selectOnline(receivedRecordIdList);
	}

	@Override
	public List<FinanceMoney> selectPaymentFinanceMoneyList(FinanceMoney financeMoney) {
		List<FinanceMoney> list = financeMoneyDao.selectPaymentFinanceMoneyList(financeMoney);
		return list;
	}

	@Override
	public List<FinanceMoney> selectReceiveFinanceMoneyList(FinanceMoney financeMoney) {
		return (List<FinanceMoney>) financeMoneyDao.selectReceiveFinanceMoneyList(financeMoney);
	}

	@Override
	public Integer selectCountReceiveFinanceMoneyList(FinanceMoney financeMoney) {
		return (Integer) financeMoneyDao.selectCountReceiveFinanceMoneyList(financeMoney);
	}

	@Override
	public Message paymentAgin(GroupLoanRegist parameter) {
		// 查询放款登记表和额度确认表
		Message message = new Message();
		GroupLoanRegist groupLoanRegist = new GroupLoanRegist();
		groupLoanRegist.setCreditapplicationId(parameter.getCreditapplicationId());
		groupLoanRegist.setRegistStatus("1");
		GroupLoanRegist groupLoanRegistResult = groupLoanRegistService.selectDengji(groupLoanRegist);
		AmountConfirm amountConfirm = amountConfirmService.selectNew(parameter.getCreditapplicationId());
		if (null != groupLoanRegistResult || null != amountConfirm) {
			FinanceMoney record = new FinanceMoney();
			record.setAssociationId(parameter.getGroupLoanRegistId());
			FinanceMoney result = this.selectFinanceMoneyByCondition(record);
			if (!"3".equals(result.getFinanceStatus())) {
				message.setMsg("请勿重复预约！");
				message.setSuccess(false);
			} else {
				this.updateFinanceMoney(record);
				Integer creditApplicationId = parameter.getCreditapplicationId();
				message = financePaymentService.financialpayment(creditApplicationId, parameter);
			}
		}
		return message;
	}

	@Override
	public AccountInfo selectAccountInfo(Integer receivedRecordId) {
		return (AccountInfo) financeMoneyDao.selectAccountInfo(receivedRecordId);
	}

	@Override
	public List<ReceivedRecordVo> selectReceiveRecordList(List<Integer> receivedRecordId) {
		List<ReceivedRecordVo> list = financeMoneyDao.selectReceiveRecordList(receivedRecordId);
		return list;
	}

	@Override
	public Message receiveAgin(FinanceReceiveVo parameter) {
		Message message = new Message();
		String receiveRecordIds = parameter.getReceiveRecordIds();
		String[] receiveRecordId = receiveRecordIds.split(",");
		List<Integer> receivedRecordIdList = new ArrayList<Integer>();
		if (receiveRecordId.length < 1) {
			message.setMsg("不合法的数据");
			message.setSuccess(false);
			return message;
		}
		for (int i = 0; i < receiveRecordId.length; i++) {
			Integer rId = Integer.valueOf(receiveRecordId[i]);
			receivedRecordIdList.add(rId);
		}
		List<CreditApplication> creditApplicationList = receivedRecordListService
				.selectReceivedRecord(receivedRecordIdList);
		if (CommonUtil.isNotEmpty(creditApplicationList)) {
			String companyName = creditApplicationList.get(0).getCompanyName();
			for (CreditApplication c : creditApplicationList) {
				if (!companyName.equals(c.getCompanyName())) {
					message.setSuccess(false);
					message.setMsg("所选记录有非同一分公司的记录");
					break;
				}
			}
		}
		int accountInfoIdCount = this.selectIsDuaplicatAccount(receivedRecordIdList);
		if (accountInfoIdCount != 1) {
			message.setMsg("账号不统一，无法进行批量预约");
			message.setSuccess(false);
			return message;
		}
		List<ReceivedRecord> resultList = receivedRecordListService.selectReceivedRecordStatus(receivedRecordIdList);
		for (ReceivedRecord r : resultList) {
			if ("S".equals(r.getReceivedType())) {
				if (!("5".equals(r.getReceivedStatus()) || "4".equals(r.getReceivedStatus()) || "2".equals(r
						.getReceivedStatus()))) {
					message.setSuccess(false);
					message.setMsg("所选记录有不合法记录");
					break;
				}
			} else if ("U".equals(r.getReceivedType())) {
				if (!("4".equals(r.getReceivedStatus()) || "3".equals(r.getReceivedStatus()))) {
					message.setSuccess(false);
					message.setMsg("所选记录有不合法记录");
					break;
				}
			}
		}
		if (!message.isSuccess()) {
			List<FinanceMoney> fList = new ArrayList<FinanceMoney>();
			for (Integer receivedRecordId : receivedRecordIdList) {
				FinanceMoney record = new FinanceMoney();
				record.setAssociationId(receivedRecordId);
				fList.add(record);
			}
			System.out.println(fList);
			this.updateFinanceMoneyTYPEisF(fList);
			message = financeReceiveService.financialReceive(receivedRecordIdList, parameter);
		}
		return message;
	}

	/**
	 * 批量更新财务表(变为历史数据)
	 * 
	 * @param fList
	 *            财务列表
	 */
	private void batchUpdateFinanceMoney(List<FinanceMoney> fList) {
		financeMoneyDao.batchUpdateFinanceMoney(fList);
	}

	/** 郝强新增的关于 付款回收的 东西估计 还要好好测测 **/
	private void updateFinanceMoneyTYPEisF(List<FinanceMoney> fList) {
		financeMoneyDao.updateFinanceMoneyTYPEisF(fList);
	}

	@Override
	public void batchInsertReceiveFinanceMoney(List<FinanceMoney> financeMoneyList) {
		financeMoneyDao.batchInsertReceiveFinanceMoney(financeMoneyList);

	}

	@Override
	public Long getBizId() {
		return financeMoneyDao.getBizId();
	}

	@Override
	public boolean undoFinanceMoney(Integer receiveRecordId) {
		boolean result = false;
		FinanceMoney financeMoney = this.selectUndoFinanceMoney(receiveRecordId);
		if (null != financeMoney) {
			FinanceMoney record = new FinanceMoney();
			record.setFinanceMoneyId(financeMoney.getFinanceMoneyId());
			record.setRevocationName(SpringSecurityUtils.getCurrentUser().getName_zh());
			record.setRevocationTime(new Date());
			int i = this.updateFinanceMoneyById(record);
			if (i > 0) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public Message paymentUndo(Integer creditapplicationId) {
		CreditApplication creditapplication = new CreditApplication();
		creditapplication.setCreditapplicationId(creditapplicationId);
		FinanceMoney financeMoney = this.selectPaymentFinanceMoney(creditapplication);
		Message message = new Message();
		if (null != financeMoney) {
			if ("1".equals(financeMoney.getFinanceStatus())) {
				try {
					message = financeMoneySingleUndoService.singleUndo(Long.valueOf(financeMoney.getBizId()),
							creditapplicationId);
					if (message.isSuccess()) {
						paymentUndoBusiness(creditapplicationId);
					}
				} catch (AppBusinessException e) {
					message.setMsg("撤销失败！" + e.getMessage());
					message.setSuccess(false);
				}
			} else if ("3".equals(financeMoney.getFinanceStatus())) {
				boolean result = paymentUndoBusiness(creditapplicationId);
				message.setSuccess(result);
			} else {
				String status = financeMoney.getFinanceStatus();
				if ("2".equals(status)) {
					status = "付款成功";
				} else if ("4".equals(status)) {
					status = "已撤销";
				}
				message.setMsg("当前状态为 \"" + status + "\"不能撤销");
			}
		}
		return message;
	}

	@Override
	public Message receiveUndo(Integer receivedRecord) {
		FinanceMoney financeMoney = new FinanceMoney();
		financeMoney.setAssociationId(receivedRecord);
		FinanceMoney fResult = this.selectFinanceMoney(financeMoney);
		Message message = new Message();
		if (null != fResult && !"1".equals(fResult.getFinanceStatus())) {
			message.setMsg("订单不能撤销，请刷新页面");
			message.setSuccess(false);
			return message;
		}
		try {
			message = financeMoneySingleUndoService.singleUndo(Long.valueOf(fResult.getBizId()),
					fResult.getCreditapplicationId());
		} catch (AppBusinessException e) {
			message.setMsg("撤销失败！" + e.getMessage());
			message.setSuccess(false);
		}
		return message;
	}

	/**
	 * 判断付款预约是否可以再预约
	 * 
	 * @return boolean
	 */
	private boolean isHavePayment() {
		return false;
	}

	@Override
	public FinanceMoney selectFinanceMoney(FinanceMoney financeMoney) {
		return financeMoneyDao.selectFinanceMoney(financeMoney);
	}

	@Override
	public int updateFinanceMoneyById(FinanceMoney record) {
		return financeMoneyDao.updateFinanceMoneyById(record);
	}

	@Override
	public FinanceMoney selectUndoFinanceMoney(Integer receiveRecordId) {
		return financeMoneyDao.selectUndoFinanceMoney(receiveRecordId);
	}

	@Override
	public List<FinanceMoney> selectFinanceMoneyListByBizId(Long bizId) {
		return financeMoneyDao.selectFinanceMoneyListByBizId(bizId);
	}

	@Override
	public boolean updateFinanceMoneyByBizId(FinanceMoney financeMoney) {
		return financeMoneyDao.updateFinanceMoneyByBizId(financeMoney);
	}

	@Override
	public AmountConfirm selectFinanceMoneyBack(AmountConfirm parameter) {
		return financeMoneyDao.selectFinanceMoneyBack(parameter);
	}

	@Override
	public AccountInfo selectAccountInfoForFinanceBack(Integer associationId) {
		return financeMoneyDao.selectAccountInfoForFinanceBack(associationId);
	}

	@Override
	public Integer selectIsDuaplicatAccount(List<Integer> list) {
		return financeMoneyDao.selectIsDuaplicatAccount(list);
	}

	@Override
	public FinanceMoney selectFinanceMoneyByBizId(FinanceMoney financeMoney) {
		return financeMoneyDao.selectFinanceMoneyByBizId(financeMoney);
	}

	@Override
	public FinanceMoney selectFinanceMoneyByCondition(FinanceMoney financeMoney) {
		return financeMoneyDao.selectFinanceMoneyByCondition(financeMoney);
	}

	@Override
	public void insertFinanceMoney(int associationId, int accountInfoId, String businessType) {
		FinanceMoney financeMoney = new FinanceMoney();
		financeMoney.setType(businessType);
		financeMoney.setAssociationId(associationId);
		financeMoney.setFinanceStatus("0");
		this.insertFinanceMoney(financeMoney);
	}

	/**
	 * 财务付款撤销
	 * 
	 * @param creditapplicationId 业务单ID
	 * @return boolean
	 */
	public boolean paymentUndoBusiness(int creditapplicationId) {
		CreditApplication resultCreditApplication = creditApplicationService.selectById(creditapplicationId);
		// 财务付款撤销根据业务类型修改信贷申请单状态0：分公司业务撤到04：审批通过状态；1：个人业务撤到11：已放款登记状态
		String status = null;
		List<OperateLogBusinessStruct> oList = new ArrayList<OperateLogBusinessStruct>();
		CreditApplication creditapplication = new CreditApplication();
		creditapplication.setCreditapplicationId(creditapplicationId);
		FinanceMoney financeMoney = this.selectPaymentFinanceMoney(creditapplication);
		StringBuffer functionCode = new StringBuffer("3000");
		String result = "";
		if ("1".equals(resultCreditApplication.getBusinessType())) {
			status = "11";
			functionCode.append("1*");
			result = "对私撤销成功";
			groupLoanRegistService.changeToRegist(creditapplicationId);
		} else {
			status = "04";
			functionCode.append("4*");
			result = "对公撤销成功";
			amountConfirmService.updateAmountConfirmHistory(creditapplicationId);
		}
		List<FinanceMoney> operateLogFinanceMoneyList = this.selectPaymentFinanceMoneyList(financeMoney);
		if (CommonUtil.isNotEmpty(operateLogFinanceMoneyList)) {
			String bizId = financeMoney.getBizId();
			for (FinanceMoney f : operateLogFinanceMoneyList) {
				OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
				operateLogBusinessStruct.setBizid(bizId);
				operateLogBusinessStruct.setFunctionCode("50001*");
				operateLogBusinessStruct.setCreditapplicationId((long) creditapplicationId);
				operateLogBusinessStruct.setResult(result);
				oList.add(operateLogBusinessStruct);
			}
		}
		operateLogService.batchInsert(oList);
		FinanceMoney parameter = new FinanceMoney();
		parameter.setFinanceStatus("4");
		parameter.setHistoryFlag("T");
		parameter.setBizId(financeMoney.getBizId());
		this.updateFinanceMoneyByBizId(parameter);
		return creditApplicationService.changeAuditing(new CreditApplication(), creditapplicationId, status);

	}

	@Override
	public Pagination selectBackSectionList(Pagination pagination, CreditApplication creditApplication) {
		return financeMoneyDao.selectBackSectionList(pagination, creditApplication);
	}

	@Override
	public Message receiveAginATC(FinanceReceiveVo parameter) {
		// TODO Auto-generated method stub
		Message message = new Message();
		/*
		Date paramDate = parameter.getReceivedTime();
		2015-01-15 预约划扣修改为实时划扣，22:30-24:00不能进行划扣
		*/
		int curHour = new Date().getHours();
		int curMin = new Date().getMinutes();
		if ((curHour == 22 && curMin > 30) || curHour == 23) {
			message.setMsg("每日22:30-24:00之间不能进行预约划扣！");
			message.setSuccess(false);
			return message;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR_OF_DAY, 1);
		Date paramDate = calendar.getTime();

		String remark = parameter.getRemark();
		String receiveRecordIds = parameter.getReceiveRecordIds();
		String[] receiveRecordId = receiveRecordIds.split(",");
		List<Integer> receivedRecordIdList = new ArrayList<Integer>();
		if (receiveRecordId.length < 1) {
			message.setMsg("不合法的数据");
			message.setSuccess(false);
			return message;
		}
		for (int i = 0; i < receiveRecordId.length; i++) {
			Integer rId = Integer.valueOf(receiveRecordId[i]);
			receivedRecordIdList.add(rId);
		}
// List<CreditApplication> creditApplicationList = receivedRecordListService
// .selectReceivedRecord(receivedRecordIdList);
// if (CommonUtil.isNotEmpty(creditApplicationList)) {
// String companyName = creditApplicationList.get(0).getCompanyName();
// for (CreditApplication c : creditApplicationList) {
// if (!companyName.equals(c.getCompanyName())) {
// message.setSuccess(false);
// message.setMsg("所选记录有非同一分公司的记录");
// break;
// }
// }
// }
// int accountInfoIdCount = this.selectIsDuaplicatAccount(receivedRecordIdList);
// if (accountInfoIdCount != 1) {
// message.setMsg("账号不统一，无法进行批量预约");
// message.setSuccess(false);
// return message;
// }
		List<ReceivedRecord> resultList = receivedRecordListService.selectReceivedRecordStatus(receivedRecordIdList);
		for (ReceivedRecord r : resultList) {
			if ("S".equals(r.getReceivedType())) {
				if (!("5".equals(r.getReceivedStatus()) || "4".equals(r.getReceivedStatus()) || "2".equals(r
						.getReceivedStatus()))) {
					message.setSuccess(false);
					message.setMsg("所选记录有不合法记录");
					break;
				}
			} else if ("U".equals(r.getReceivedType())) {
				if (!("4".equals(r.getReceivedStatus()) || "3".equals(r.getReceivedStatus()))) {
					message.setSuccess(false);
					message.setMsg("所选记录有不合法记录");
					break;
				}
			}
		}
		if (!message.isSuccess()) {
			List<FinanceMoney> fList = new ArrayList<FinanceMoney>();
			for (Integer receivedRecordId : receivedRecordIdList) {
				FinanceMoney record = new FinanceMoney();
				record.setAssociationId(receivedRecordId);
				fList.add(record);
			}
// 把以前的记录给置为历史数据
			this.batchUpdateFinanceMoney(fList);
			message = financeReceiveService.financialReceiveATC(receivedRecordIdList, paramDate, remark);
		}
		return message;
	}

	@Override
	public Pagination selectRecevieListHQ(Pagination pagination, CreditApplication creditApplication) {
		// TODO Auto-generated method stub
		return financeMoneyDao.selectRecevieListHQ(pagination, creditApplication);
	}

}