package com.creditease.rc.action;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.app.pdf.AllAheadSchedule;
import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.ActualPeriodScheduleDTO;
import com.creditease.rc.domain.AmountConfirm;
import com.creditease.rc.domain.BorrowerServiceApp;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.PaymentAmountReqCommon;
import com.creditease.rc.domain.PaymentAmountResultInfo;
import com.creditease.rc.domain.ReceivedRecord;
import com.creditease.rc.domain.ReturnPlan;
import com.creditease.rc.framework.exception.AppBusinessException;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IAccountInfoService;
import com.creditease.rc.service.IAmountConfirmService;
import com.creditease.rc.service.IBorrowingProductsService;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.IReceivablesRegistrationService;
import com.creditease.rc.service.IRepaymentPlanService;
import com.creditease.rc.service.IReturnPlanService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.CurrencyUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.vo.ReturnPlanForShowVo;

/**
 * 
 * @author haoqiang
 * 
 */
@Controller
@RequestMapping("receivablesRegistration")
public class ReceivablesRegistrationController {

	/**
	 * 
	 * @param binder 传入的所有参数
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@Resource
	private IReceivablesRegistrationService receivablesRegistrationService;
	@Resource
	private IRepaymentPlanService repaymentPlanService;
	@Resource
	private ICreditApplicationService creditApplicationService;
	@Resource
	private IAccountInfoService accountInfoService;
	@Resource
	private IBorrowingProductsService borrowingProductsService;
	@Resource
	private IReturnPlanService returnPlanService;
	@Resource
	private IAmountConfirmService amountConfirmService;

	/**
	 * 返回收款登记页面
	 * 
	 * @param creditapplicationid 信贷申请主键
	 * @param request request
	 * @param early 提前还款标识
	 * @param businessNumber 业务单号
	 * @return 页面
	 */
	@RequestMapping(value = "returnReceivablesRegistrationJSP")
	public String returnReceivablesRegistrationJSP(Integer creditapplicationid, HttpServletRequest request,
			String early, String businessNumber) {
		request.setAttribute("passCreditapplicationId", creditapplicationid);
		request.setAttribute("normalOrEarly", early);
		request.setAttribute("businessNumber", businessNumber);
		return "/jsp/rc/business/receivablesRegistration.jsp";
		// return "/jsp/rc/business/huankuandengji.jsp";
	}

	/**
	 * 返回收款登记页面
	 * 
	 * @param creditapplicationid 信贷申请主键
	 * @param request request
	 * @return 页面
	 */
	@RequestMapping(value = "returnEarlyRepaymentRegistrationJSP")
	public String returnEarlyRepaymentRegistrationJSP(Integer creditapplicationid, HttpServletRequest request) {
		request.setAttribute("passCreditapplicationId", creditapplicationid);
		// String getEarlyType = repaymentPlanService.getEarlyType(creditapplicationid);
		// request.setAttribute("type", getEarlyType);
		return "/jsp/rc/business/receivablesRegistration.jsp";
		// return "/jsp/rc/business/huankuandengji.jsp";
	}

	/**
	 * 
	 * @param page 页
	 * @param rows 行
	 * @param creditapplicationId 业务申请单主键
	 * @return 分页List
	 */
	@RequestMapping(value = "receivablesHistoryDataGrid", method = RequestMethod.POST)
	public @ResponseBody
	Pagination receivablesHistoryDataGrid(@RequestParam(required = false) String page,
			@RequestParam(required = false) String rows, Integer creditapplicationId) {
		Pagination pagination = new Pagination();
		// Integer creditapplicationId = passCreditapplicationId;
		if (creditapplicationId != null) {
			if (!StringUtils.isBlank(page)) {
				pagination.setPage(Integer.valueOf(page));
			}
			if (!StringUtils.isBlank(rows)) {
				pagination.setPageSize(Integer.valueOf(rows));
			}
		}
		return receivablesRegistrationService.queryReceivablesHistoryDataGrid(pagination, creditapplicationId);
	}

	/**
	 * @param receivedRecord 需要分配的金额
	 * @return 列表
	 */
	@RequestMapping(value = "distributorController", method = RequestMethod.POST)
	public @ResponseBody
	List<ReturnPlan> distributorController(ReceivedRecord receivedRecord) {
//		Integer creditapplicationId = receivedRecord.getCreditapplicationId();
//
//		// Date receivedDate = receivedRecord.getReceivedTime();
//
//		// 通过creditapplicationId去查对应的还款计划全部的哦要！
//		ReturnPlan passReturnPlan = new ReturnPlan();
//		passReturnPlan.setCreditapplicationId(creditapplicationId);
//		// 得到了全部的还款计划
//		List<ReturnPlan> returnPlanList = returnPlanService.queryList(passReturnPlan);
//		List<ReturnPlan> allotReturnPlanList = new ArrayList<ReturnPlan>();
//		for (ReturnPlan r : returnPlanList) {
//			// 得到一期的还款状态
//			String getCollectionStatus = r.getCollectionStatus();
//			// 将还款状态为未还款或者部分还款的挑出来放到allotReturnPlanList中
//			if ("0".equals(getCollectionStatus.trim()) || "2".equals(getCollectionStatus.trim())) {
//				allotReturnPlanList.add(r);
//			}
//		}
//		String receivedType = receivedRecord.getReceivedType();
//		if ("1".equals(receivedType.trim())) {
//			earlyReceviedListC(allotReturnPlanList, returnPlanList, receivedRecord);
//		}
//		List<ReturnPlan> resultList = allotReceivedAmountC(receivedRecord.getReceivedAmount(), allotReturnPlanList);
		return null;
	}

	/**
	 * 
	 * @param getReceivedAmount 金额
	 * @param allotReturnPlanList List
	 * @return List
	 */
	private List<ReturnPlan> allotReceivedAmountC(Double getReceivedAmount, List<ReturnPlan> allotReturnPlanList) {
		List<ReturnPlan> returnReturnPlanList = new ArrayList<ReturnPlan>();
		for (ReturnPlan r : allotReturnPlanList) {
//			Double getCurrAccountTotal = r.getCurrAccountTotal();// 当月应还款总额
//			Double getCurrMonPayTotal = r.getCurrMonPayTotal();// 当月实收还款总额
//			Double getCurrReduceTotal = r.getCurrReduceTotal();// 当月减免总额
//			Double getReceEarlyDamages = r.getReceEarlyDamages();// 当月应收一次性提前还款违约金
//			Double getPaidEarlyDamages = r.getPaidEarlyDamages();// 当月实收一次性提前还款违约金
//
//			Double actualReceivableTotal = CurrencyUtil.add(CurrencyUtil.add(getCurrAccountTotal, getCurrReduceTotal),
//					getReceEarlyDamages);// 当月实际应还款总额
//			Double currNotTotal = CurrencyUtil.sub(CurrencyUtil.sub(actualReceivableTotal, getCurrMonPayTotal),
//					getPaidEarlyDamages);// 当月未还款差值
//			// 新建一个变量 toBeDistributed = null 用来当作分配金额
//			Double toBeDistributed = null;
//			// 如果登记金额大于当月实际应还差值
//			if (getReceivedAmount.doubleValue() > currNotTotal.doubleValue()) {
//				// 那么被分配金额就等未还款差值金额
//				toBeDistributed = currNotTotal;
//				// 之后收款金额就等于减去被分配金额后的金额
//				getReceivedAmount = CurrencyUtil.sub(getReceivedAmount, toBeDistributed);
//				// 将被分配金额和当期的还款计划传入到returnRuralReturnDis(toBeDistributed, r)方法中
//				returnRuralReturnC(toBeDistributed, r);
//				r.setCollectionStatus("1");
//				returnReturnPlanList.add(r);
//				// 如果登记的金额等于当月未还款差值金额
//			} else if (getReceivedAmount.doubleValue() == currNotTotal.doubleValue()) {
//				toBeDistributed = getReceivedAmount;
//				// 收款金额置0
//				getReceivedAmount = 0.00;
//				// 将被分配金额和当期的还款计划传入到returnRuralReturnDis(toBeDistributed, r)方法中
//				returnRuralReturnC(toBeDistributed, r);
//				r.setCollectionStatus("1");
//				returnReturnPlanList.add(r);
//				break;
//				// 如果登记的金额小于当月未还款差值金额
//			} else if (getReceivedAmount.doubleValue() < currNotTotal.doubleValue()) {
//				// 那么待分配金额就等于收款金额
//				toBeDistributed = getReceivedAmount;
//				// 收款金额置0
//				getReceivedAmount = 0.00;
//				// 将被分配金额和当期的还款计划传入到returnRuralReturnDis(toBeDistributed, r)方法中
//				returnRuralReturnC(toBeDistributed, r);
//				if (getReceivedAmount.doubleValue() != 0.00) {
//					// 这是避免重新分配收款金额为0时判断当期状态错误的问题！
//					r.setCollectionStatus("2");
//				}
//				returnReturnPlanList.add(r);
//				break;
//			}
		}
		return returnReturnPlanList;
	}

	/**
	 * 
	 * @param toBeDistributed 金额
	 * @param r 期
	 * @return 期
	 */
	private ReturnPlan returnRuralReturnC(Double toBeDistributed, ReturnPlan r) {
//		Double getCurrMonPrincipal = r.getCurrMonPrincipal();// 应收本金
//		Double getCurrMonInterest = r.getCurrMonInterest();// 应收利息
//		Double getCurrMonManageFree = r.getCurrMonManageFree();// 应收服务费
//		Double getCurrMonPenalty = r.getCurrMonPenalty();// 应收罚息
//		Double getCurrMonLaterFree = r.getCurrMonLaterFree();// 应收滞纳金
//		Double getCurrReducePrincipal = r.getCurrReducePrincipal();// 减免本金
//		Double getCurrReduceInterest = r.getCurrReduceInterest();// 减免利息
//		Double getCurrReduceManageFree = r.getCurrReduceManageFree();// 减免服务费
//		Double getCurrReducePenalty = r.getCurrReducePenalty();// 减免罚息
//		Double getCurrReduceLaterFree = r.getCurrReduceLaterFree();// 减免滞纳金
//		Double actualReceivablePrincipal = CurrencyUtil.add(getCurrMonPrincipal, getCurrReducePrincipal);// 实际应收本金
//		Double actualReceivableInterest = CurrencyUtil.add(getCurrMonInterest, getCurrReduceInterest);// 实际应收利息
//		Double actualReceivableManageFree = CurrencyUtil.add(getCurrMonManageFree, getCurrReduceManageFree);// 实际应收服务费
//		Double actualReceivablePenalty = CurrencyUtil.add(getCurrMonPenalty, getCurrReducePenalty);// 实际应收罚息
//		Double actualReceivableLaterFree = CurrencyUtil.add(getCurrMonLaterFree, getCurrReduceLaterFree);// 实际应收滞纳金
//		Double getCurrPaindinCapital = r.getCurrPaindinCapital();// 实收本金
//		Double getCurrPaindinInterest = r.getCurrPaindinInterest();// 实收利息
//		Double getCurrPaindinManageFree = r.getCurrPaindinManageFree();// 实收服务费
//		Double getCurrMonPaidPenalty = r.getCurrMonPaidPenalty();// 实收罚息
//		Double getCurrLateFreePaid = r.getCurrLateFreePaid();// 实收滞纳金
//		Double getReceEarlyDamages = r.getReceEarlyDamages();// 应收提前还款违约金
//		Double getPaidEarlyDamages = r.getPaidEarlyDamages();// 实收提前还款违约金
//		Double freeDifference = CurrencyUtil.sub(actualReceivableLaterFree, getCurrLateFreePaid);// 滞纳金差
//		Double penaltyDifference = CurrencyUtil.sub(actualReceivablePenalty, getCurrMonPaidPenalty);// 罚息差
//		Double manageDifference = CurrencyUtil.sub(actualReceivableManageFree, getCurrPaindinManageFree);// 服务费差
//		Double interestDifference = CurrencyUtil.sub(actualReceivableInterest, getCurrPaindinInterest);// 利息差
//		Double principalDifference = CurrencyUtil.sub(actualReceivablePrincipal, getCurrPaindinCapital);// 本金差
//		Double earlyDamagesDifference = CurrencyUtil.sub(getReceEarlyDamages, getPaidEarlyDamages);
//		// 收服务费
//		if (manageDifference > 0.00 && toBeDistributed > 0.00) {
//			Double temp = toBeDistributed;
//			toBeDistributed = CurrencyUtil.sub(toBeDistributed, manageDifference);
//			if (toBeDistributed > 0.00) {
//				r.setCurrPaindinManageFree(CurrencyUtil.add(getCurrPaindinManageFree, manageDifference));
//			} else {
//				r.setCurrPaindinManageFree(CurrencyUtil.add(getCurrPaindinManageFree, temp));
//			}
//		}
//		if (manageDifference < 0.00 && toBeDistributed >= 0.00) {
//			toBeDistributed = CurrencyUtil.sub(toBeDistributed, manageDifference);
//			r.setCurrPaindinManageFree(CurrencyUtil.add(getCurrPaindinManageFree, manageDifference));
//		}
//		// 收罚息
//		if (penaltyDifference > 0.00 && toBeDistributed > 0.00) {
//			Double temp = toBeDistributed;
//			toBeDistributed = CurrencyUtil.sub(toBeDistributed, penaltyDifference);
//			if (toBeDistributed > 0.00) {
//				r.setCurrMonPaidPenalty(CurrencyUtil.add(getCurrMonPaidPenalty, penaltyDifference));
//			} else {
//				r.setCurrMonPaidPenalty(CurrencyUtil.add(getCurrMonPaidPenalty, temp));
//			}
//		}
//		if (penaltyDifference < 0.00 && toBeDistributed >= 0.00) {
//			toBeDistributed = CurrencyUtil.sub(toBeDistributed, penaltyDifference);
//			r.setCurrMonPaidPenalty(CurrencyUtil.add(getCurrMonPaidPenalty, penaltyDifference));
//		}
//		// 收滞纳金
//		if (freeDifference > 0.00 && toBeDistributed > 0.00) {
//			Double temp = toBeDistributed;
//			toBeDistributed = CurrencyUtil.sub(toBeDistributed, freeDifference);
//			if (toBeDistributed > 0.00) {
//				r.setCurrLateFreePaid(CurrencyUtil.add(getCurrLateFreePaid, freeDifference));
//			} else {
//				r.setCurrLateFreePaid(CurrencyUtil.add(getCurrLateFreePaid, temp));
//			}
//		}
//		if (freeDifference < 0.00 && toBeDistributed >= 0.00) {
//			toBeDistributed = CurrencyUtil.sub(toBeDistributed, freeDifference);
//			r.setCurrLateFreePaid(CurrencyUtil.add(getCurrLateFreePaid, freeDifference));
//		}
//		// 收利息
//		if (interestDifference > 0.00 && toBeDistributed > 0.00) {
//			Double temp = toBeDistributed;
//			toBeDistributed = CurrencyUtil.sub(toBeDistributed, interestDifference);
//			if (toBeDistributed > 0.00) {
//				r.setCurrPaindinInterest(CurrencyUtil.add(getCurrPaindinInterest, interestDifference));
//			} else {
//				r.setCurrPaindinInterest(CurrencyUtil.add(getCurrPaindinInterest, temp));
//			}
//		}
//		if (interestDifference < 0.00 && toBeDistributed >= 0.00) {
//			toBeDistributed = CurrencyUtil.sub(toBeDistributed, interestDifference);
//			r.setCurrPaindinInterest(CurrencyUtil.add(getCurrPaindinInterest, interestDifference));
//		}
//		// 收本金
//		if (principalDifference > 0.00 && toBeDistributed > 0.00) {
//			Double temp = toBeDistributed;
//			toBeDistributed = CurrencyUtil.sub(toBeDistributed, principalDifference);
//			if (toBeDistributed > 0.00) {
//				r.setCurrPaindinCapital(CurrencyUtil.add(getCurrPaindinCapital, principalDifference));
//			} else {
//				r.setCurrPaindinCapital(CurrencyUtil.add(getCurrPaindinCapital, temp));
//			}
//		}
//		if (principalDifference < 0.00 && toBeDistributed >= 0.00) {
//			toBeDistributed = CurrencyUtil.sub(toBeDistributed, principalDifference);
//			r.setCurrPaindinCapital(CurrencyUtil.add(getCurrPaindinCapital, principalDifference));
//		}
//		// 一次性还款产生的违约金
//		if (earlyDamagesDifference > 0.00 && toBeDistributed > 0.00) {
//			Double temp = toBeDistributed;
//			toBeDistributed = CurrencyUtil.sub(toBeDistributed, earlyDamagesDifference);
//			if (toBeDistributed > 0.00) {
//				r.setPaidEarlyDamages(CurrencyUtil.add(getPaidEarlyDamages, earlyDamagesDifference));
//			} else {
//				r.setPaidEarlyDamages(CurrencyUtil.add(getPaidEarlyDamages, temp));
//			}
//		}
//		// 实收总计
//		Double total = CurrencyUtil.add(
//				r.getCurrPaindinCapital(),
//				CurrencyUtil.add(
//						r.getCurrPaindinInterest(),
//						CurrencyUtil.add(r.getCurrPaindinManageFree(),
//								CurrencyUtil.add(r.getCurrMonPaidPenalty(), r.getCurrLateFreePaid()))));
//		r.setCurrMonPayTotal(total);
		return r;
	};

	/**
	 * 可能用不到了
	 * 
	 * @param allotReturnPlanList 需要分配的期
	 * @param returnPlanList 还款计划集合
	 * @param receivedRecord 收款登记对象
	 * @return 还款计划List
	 */
	private List<ReturnPlan> earlyReceviedListC(List<ReturnPlan> allotReturnPlanList, List<ReturnPlan> returnPlanList,
			ReceivedRecord receivedRecord) {

//		CreditApplication application = creditApplicationService.selectById(returnPlanList.get(0)
//				.getCreditapplicationId());
//		AmountConfirm amountConfirm = amountConfirmService.selectNew(returnPlanList.get(0).getCreditapplicationId());
//
//		Integer creditapplicationId = receivedRecord.getCreditapplicationId();
//
//		Date receivedDate = receivedRecord.getReceivedTime();
//
//		// 通过creditapplicationId去查对应的还款计划全部的哦要！
//		ReturnPlan passReturnPlan = new ReturnPlan();
//		passReturnPlan.setCreditapplicationId(creditapplicationId);
//		// 得到了全部的还款计划
//		// 当期
//		ReturnPlan thisRP = null;
//		// 找当期
//		for (ReturnPlan r : returnPlanList) {
//			if (receivedDate.compareTo(r.getRepaymentDate()) <= 0) {
//				// 找到这一期
//				thisRP = r;
//				break;
//			} else {
//				// 这是错的不可能大于零
//				// throw new AppBusinessException("后台日期判断出错！");
//			}
//		}
//
//		String getPeriod = thisRP.getPeriod();
//		int tPeriod = Integer.parseInt(getPeriod);
//		int fPeriod = Integer.parseInt(allotReturnPlanList.get(0).getPeriod());
//		Date submitDate;
//		if (tPeriod < fPeriod) {
//			// 当期已经还完了约的日期应该是未还款的第一期的上一期
//			int i = 0;
//			for (i = 0; i < returnPlanList.size(); i++) {
//				ReturnPlan r = returnPlanList.get(i);
//				int tempP = Integer.parseInt(r.getPeriod());
//				if (tempP == fPeriod) {
//					break;
//				}
//
//			}
//			submitDate = returnPlanList.get(i - 1).getRepaymentDate();
//		} else if (tPeriod == fPeriod) {
//			// 当期还没有还完及当期就是未还款的当期
//			submitDate = allotReturnPlanList.get(0).getRepaymentDate();
//		} else {
//			// 这是错的不可能大于零
//			throw new AppBusinessException("后台日期判断属于哪一期出错！");
//		}
//
//		/** 第一个参数对象 **/
//		PaymentAmountReqCommon paymentAmountReqCommon = new PaymentAmountReqCommon();
//		// 是否一次性还清
//		paymentAmountReqCommon.setaLLAhead(true);
//		// 预约还款时间
//		paymentAmountReqCommon.setAppointmentDate(DateUtil.dateConvertString(submitDate));
//		// 审核日期（系统审核通过的日期，本地表应该有）
//		// paymentAmountReqCommon.setAuditDate(new Date());
//		// 产品分类编号CP
//		// paymentAmountReqCommon.setCatagoryId(Long.getLong("1111111"));
//		// 合同金额
//		paymentAmountReqCommon.setContractMoney(BigDecimal.valueOf(amountConfirm.getAmount()));
//		// 营业部编号
//		paymentAmountReqCommon.setDepartmentId(Long.valueOf(application.getDepartmentId()));
//		// 放款日期
//		paymentAmountReqCommon.setLenderDate(DateUtil.dateConvertString(application.getSignagreementDate()));
//		// 分期数
//		paymentAmountReqCommon.setPeriodCount(Integer.parseInt(application.getInstalments()));
//		// 上一次计算逾期费用时间
//		// paymentAmountReqCommon.setPrevCalcDate(new Date());
//		// 产品版本编号--REPAYMENT_PLAN_ID
//		paymentAmountReqCommon.setProductInfoId(application.getRepaymentPlanId().longValue());
//		// 请求系统日期
//		paymentAmountReqCommon.setReqSysDate(DateUtil.dateConvertString(new Date()));
//		/** 第二个参数对象 **/
//
//		List<ActualPeriodScheduleDTO> apsDtos = new ArrayList<ActualPeriodScheduleDTO>();
//
//		if (CommonUtil.isNotEmpty(returnPlanList)) {
//			for (ReturnPlan r : returnPlanList) {
//
//				ActualPeriodScheduleDTO actualPeriodScheduleDTO = new ActualPeriodScheduleDTO();
//				boolean s = false;
//				if ("1".equals(r.getOverdueFlag())) {
//					s = !s;
//				}
//				// 逾期分期服务费相关
//				actualPeriodScheduleDTO.setOverdueFQFWFReceivableCharge(BigDecimal.valueOf(0));
//				actualPeriodScheduleDTO.setOverdueFQFWFReceivedCharge(BigDecimal.valueOf(0));
//				// 罚息相关
//				actualPeriodScheduleDTO.setOverdueFxReceivableCharge(BigDecimal.valueOf(r.getCurrMonPenalty()));
//				actualPeriodScheduleDTO.setOverdueFxReceivedCharge(BigDecimal.valueOf(CurrencyUtil.sub(
//						r.getCurrMonPaidPenalty(), r.getCurrReducePenalty())));
//				// 滞纳金相关
//				actualPeriodScheduleDTO.setOverdueZnjReceivableCharge(BigDecimal.valueOf(r.getCurrMonLaterFree()));
//				actualPeriodScheduleDTO.setOverdueZnjReceivedCharge(BigDecimal.valueOf(CurrencyUtil.sub(
//						r.getCurrLateFreePaid(), r.getCurrReduceLaterFree())));
//				// 分期服务费相关
//				actualPeriodScheduleDTO.setPeriodFQFWFReceivableCharge(BigDecimal.valueOf(r.getCurrMonManageFree()));
//				actualPeriodScheduleDTO.setPeriodFQFWFReceivedCharge(BigDecimal.valueOf(CurrencyUtil.sub(
//						r.getCurrPaindinManageFree(), r.getCurrReduceManageFree())));
//				// 利息相关
//				actualPeriodScheduleDTO.setReceivableInterest(BigDecimal.valueOf(r.getCurrMonInterest()));
//				actualPeriodScheduleDTO.setReceivedInterest(BigDecimal.valueOf(CurrencyUtil.sub(
//						r.getCurrPaindinInterest(), r.getCurrReduceInterest())));
//				// 本金相关
//				actualPeriodScheduleDTO.setReceivablePrincipal(BigDecimal.valueOf(r.getCurrMonPrincipal()));
//				actualPeriodScheduleDTO.setReceivedPrincipal(BigDecimal.valueOf(CurrencyUtil.sub(
//						r.getCurrPaindinCapital(), r.getCurrReducePrincipal())));
//				// 总金额相关
//				actualPeriodScheduleDTO.setReceivableMoney(BigDecimal.valueOf(r.getCurrAccountTotal()));
//				actualPeriodScheduleDTO.setReceivedMoney(BigDecimal.valueOf(CurrencyUtil.sub(r.getCurrMonPayTotal(),
//						r.getCurrReduceTotal())));
//				// 其他相关
//				actualPeriodScheduleDTO.setOverdue(s);
//				actualPeriodScheduleDTO.setPeriod(Integer.parseInt(r.getPeriod()));
//				actualPeriodScheduleDTO.setRepayDate(DateUtil.dateConvertString(r.getRepaymentDate()));
//				apsDtos.add(actualPeriodScheduleDTO);
//			}
//		}
//
//		// 转化成webservice
//		PaymentAmountResultInfo paymentAmountResultInfo = borrowingProductsService.paymentAmountCalculate(
//				paymentAmountReqCommon, apsDtos);
//		// 概况
//		paymentAmountResultInfo.getResultCode();
//		// System.out.println("返回接口操作代码，0表示成功，非0表示错误代码。===========" + paymentAmountResultInfo.getResultCode());
//		paymentAmountResultInfo.getResultMsg();
//		// System.out.println("返回接口操作信息，调用成功返回“success”，失败返回错误代码对应的错误信息。==========="
//		// + paymentAmountResultInfo.getResultMsg());
//		// 得到提前一次性还款对象
//		AllAheadSchedule aheadSchedule = paymentAmountResultInfo.getAheadSchedule();
//
//		BigDecimal getCurrentPrincipal = aheadSchedule.getCurrentPrincipal();
//		BigDecimal getCurrentInterest = aheadSchedule.getCurrentInterest();
//		BigDecimal getCurrentPeriodCharge = aheadSchedule.getCurrentPeriodCharge();
//
//		// 目前还没有考虑违约情况
//		if (getCurrentPrincipal.doubleValue() == 0.00 && getCurrentInterest.doubleValue() == 0.00
//				&& getCurrentPeriodCharge.doubleValue() == 0.00) {
//			// 当期要么不用还；要么已经还清，从未还清的第一期开始做减免
//			for (ReturnPlan r : allotReturnPlanList) {
//
//				r.setCurrReduceInterest(CurrencyUtil.sub(r.getCurrReduceInterest(), r.getCurrMonInterest()));
//				r.setCurrReduceManageFree(CurrencyUtil.sub(r.getCurrReduceManageFree(), r.getCurrMonManageFree()));
//				r.setCurrReduceTotal(CurrencyUtil.add(
//						r.getCurrReduceLaterFree(),
//						CurrencyUtil.add(
//								r.getCurrReducePenalty(),
//								CurrencyUtil.add(r.getCurrReducePrincipal(),
//										CurrencyUtil.add(r.getCurrReduceInterest(), r.getCurrReduceManageFree())))));
//			}
//			allotReturnPlanList.get(allotReturnPlanList.size() - 1).setReceEarlyDamages(
//					aheadSchedule.getPenalbond().doubleValue());
//		} else {
//			// 当期没有还清，当期不用做减免，从下一期开始做减免
//			for (int i = 1; i < allotReturnPlanList.size(); i++) {
//				allotReturnPlanList.get(i).setCurrReduceInterest(
//						CurrencyUtil.sub(allotReturnPlanList.get(i).getCurrReduceInterest(), allotReturnPlanList.get(i)
//								.getCurrMonInterest()));
//				allotReturnPlanList.get(i).setCurrReduceManageFree(
//						CurrencyUtil.sub(allotReturnPlanList.get(i).getCurrReduceManageFree(),
//								allotReturnPlanList.get(i).getCurrMonManageFree()));
//				allotReturnPlanList.get(i).setCurrReduceTotal(
//						CurrencyUtil.add(allotReturnPlanList.get(i).getCurrReduceLaterFree(), CurrencyUtil.add(
//								allotReturnPlanList.get(i).getCurrReducePenalty(),
//								CurrencyUtil.add(allotReturnPlanList.get(i).getCurrReducePrincipal(), CurrencyUtil.add(
//										allotReturnPlanList.get(i).getCurrReduceInterest(), allotReturnPlanList.get(i)
//												.getCurrReduceManageFree())))));
//			}
//			allotReturnPlanList.get(allotReturnPlanList.size() - 1).setReceEarlyDamages(
//					aheadSchedule.getPenalbond().doubleValue());
//		}

		return allotReturnPlanList;
	}

	/**
	 * 
	 * @param paramReceivedRecord 收款登记对象
	 * @return 布尔
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody
	boolean save(ReceivedRecord paramReceivedRecord) {
		paramReceivedRecord.setOperateDate(new Timestamp(new Date().getTime()));
		return receivablesRegistrationService.saveReceivedRecord(paramReceivedRecord);

	}

	/**
	 * 测试插入用
	 */
	@RequestMapping(value = "insert")
	public @ResponseBody
	void insert() {
		List<ReturnPlan> passReturnPlanList = new ArrayList<ReturnPlan>();
		for (int i = 0; i < 2; i++) {
//			ReturnPlan tempReturnPlan = new ReturnPlan();
//			tempReturnPlan.setCreditapplicationId(2497);
//			tempReturnPlan.setContractNo("123456");
//			tempReturnPlan.setCurrMonPrincipal(1000.00);
//			tempReturnPlan.setCurrMonInterest(100.00);
//			tempReturnPlan.setCurrMonManageFree(100.00);
//			tempReturnPlan.setCurrMonLaterFree(0.00);
//			tempReturnPlan.setCurrAccountTotal(1200.00);
//			tempReturnPlan.setCurrPaindinCapital(0.00);
//			tempReturnPlan.setCurrPaindinInterest(0.00);
//			tempReturnPlan.setCurrPaindinManageFree(0.00);
//			tempReturnPlan.setCurrLateFreePaid(0.00);
//			tempReturnPlan.setCurrMonPaidPenalty(0.00);
//			tempReturnPlan.setCurrMonPayTotal(0.00);
//			tempReturnPlan.setCollectionStatus("0");
//			tempReturnPlan.setPreRegistFlag(null);
//			passReturnPlanList.add(tempReturnPlan);
		}
		boolean isSuccess = false;
		isSuccess = receivablesRegistrationService.batchInsertReceivablesHistory(passReturnPlanList);
		// System.out.println(isSuccess);
	}

	/**
	 * 定时任务查询需要进行收款登记的还计划
	 */
	public void automaticallyDeducted() {
		ReturnPlan passReturnPlan = new ReturnPlan();
		passReturnPlan.setRepaymentDate(new Date());
		passReturnPlan.setAutoSwitch("0");
		boolean sss = receivablesRegistrationService.updateAutomaticallyDeducted(passReturnPlan);
		// System.out.println("定时任务查询需要进行收款登记的还计划");
		// System.out.println("结果为：" + sss);

	}

	/**
	 * 
	 * @param param 字符串
	 * @return 日期
	 */
	private Date formateDate(String param) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = sdf.parse(param);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 
	 * @param creditapplicationId 信贷申请单主键
	 * @param param 默认还款方式类型
	 * @return 布尔
	 */
	@RequestMapping(value = "autoSwitch", method = RequestMethod.POST)
	public @ResponseBody
	boolean autoSwitch(Integer creditapplicationId, String param) {
		// returnPlanService
		return false;
// paramReceivedRecord.setOperateDate(new Timestamp(new Date().getTime()));
// return receivablesRegistrationService.insertReceivedRecord(paramReceivedRecord);

	}

	/**
	 * 
	 * 
	 * @param creditapplicationId 信贷申请主键
	 * @return 信贷申请对象
	 */
	@RequestMapping(value = "selectAppliaction", method = RequestMethod.POST)
	public @ResponseBody
	CreditApplication selectAppliaction(Integer creditapplicationId) {
		CreditApplication creditApplication = creditApplicationService.selectById(creditapplicationId);
		return creditApplication;

	}

	/**
	 * 
	 * @param accountInfoId 卡信息主键
	 * @return 卡信息对象
	 */
	@RequestMapping(value = "selectCard", method = RequestMethod.POST)
	public @ResponseBody
	AccountInfo selectCard(Integer accountInfoId) {
		AccountInfo accountInfo = accountInfoService.selectByAccountID(accountInfoId);
		return accountInfo;

	}

	/**
	 * 
	 * @param creditapplicationId 信贷申请主键
	 * @return List<AccountInfo>卡信息List
	 */
	@RequestMapping(value = "selectCardInfo", method = RequestMethod.POST)
	public @ResponseBody
	List<AccountInfo> selectCardInfo(Integer creditapplicationId) {
		List<AccountInfo> accountInfoList = accountInfoService.selectCardInfo(creditapplicationId);
		return accountInfoList;

	}

	/**
	 * 检查有无在途资金
	 * 
	 * @param creditapplicationId 信贷申请主键
	 * @return financeMoneyList
	 */
	@RequestMapping(value = "checkOnWay", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, List<ReceivedRecord>> checkOnWay(Integer creditapplicationId) {
		Map<String, List<ReceivedRecord>> returnMap = receivablesRegistrationService.checkOnWay(creditapplicationId);
		return returnMap;
	}

	/**
	 * 
	 * @param param 判断是否
	 * @param iDs 传入的Json串
	 * @return 是否撤销成功
	 */
	@RequestMapping(value = "appointmentRevoked", method = RequestMethod.POST)
	public @ResponseBody
	boolean appointmentRevoked(String param, String iDs) {
		// Map<String, List<ReceivedRecord>> returnMap = receivablesRegistrationService.checkOnWay(creditapplicationId);
		Integer[] idList = (Integer[]) JsonUtil.getArray(iDs, Integer.class);
// RepaymentPlanItem[] tempRepaymentPlanItem = (RepaymentPlanItem[]) JsonUtil.getArray(javaname,
// RepaymentPlanItem.class);
		List<Integer> receivedRecordIdList = Arrays.asList(idList);
		boolean success = receivablesRegistrationService.updateAppointmentRevoked(param, receivedRecordIdList);
		return success;
	}

	/**
	 * 
	 * @param creditapplicationId 传入信贷申请单主键
	 * @return 返回借款人信息
	 */
	@RequestMapping(value = "borrowServiceApp")
	public @ResponseBody
	BorrowerServiceApp borrowServiceApp(Integer creditapplicationId) {

		return receivablesRegistrationService.selectBycId(creditapplicationId);
	}

	/**
	 * 添加个人卡信息
	 * 
	 * @param accountInfo 个人卡对象
	 * @return 插入后的个人卡的对象
	 */
	@RequestMapping("addPersonalCard")
	public @ResponseBody
	AccountInfo addPersonalCard(AccountInfo accountInfo) {
		return receivablesRegistrationService.addPersonalAccInfo(accountInfo);
	}

	/**
	 * 为还款计划添加备注
	 * 
	 * @param returnPlanId 信贷申请主键
	 * @param remark 备注文本信息
	 * @return 是否添加成功
	 */
	@RequestMapping(value = "saveRemark")
	public @ResponseBody
	boolean saveRemark(Integer returnPlanId, String remark) {
		return false;
//		ReturnPlan returnPlan = new ReturnPlan();
//		returnPlan.setReturnPlanId(returnPlanId);
//		returnPlan.setRemark(remark);
//		List<ReturnPlan> rlist = new ArrayList<ReturnPlan>();
//		rlist.add(returnPlan);
		//int j = receivablesRegistrationService.batchUpdateReceivablesHistory(rlist);
//		if (j == 0) {
//			return false;
//		} else {
//			return true;
//		}
	}

	/**
	 * 
	 * @param creditapplicationId 
	 * @return ReturnPlanForShowVo
	 */
	@RequestMapping(value = "appendNormalRow")
	public @ResponseBody
	ReturnPlanForShowVo appendNormalRow(Integer creditapplicationId) {

//		// 通过creditapplicationId去查对应的还款计划全部的哦要！
//		ReturnPlan passReturnPlan = new ReturnPlan();
//		passReturnPlan.setCreditapplicationId(creditapplicationId);
//		// 得到了全部的还款计划
//		List<ReturnPlan> returnPlanList = returnPlanService.queryList(passReturnPlan);
//		List<ReturnPlan> allotReturnPlanList = new ArrayList<ReturnPlan>();
//
//		// 得到最大还款金额
//		Double biggestRepaymentAmount = 0.00;
//		Date datetime = new Date();
//		ReturnPlan thisRp = new ReturnPlan();
//		for (ReturnPlan r : returnPlanList) {
//
//			Double getCurrAccountTotal = r.getCurrAccountTotal();// 当月应还款总额
//			Double getCurrMonPayTotal = r.getCurrMonPayTotal();// 当月实收还款总额
//			Double getCurrReduceTotal = r.getCurrReduceTotal();// 当月减免总额
//			Double getReceEarlyDamages = r.getReceEarlyDamages();// 当月应收一次性提前还款违约金
//			Double getPaidEarlyDamages = r.getPaidEarlyDamages();// 当月实收一次性提前还款违约金
//
//			Double actualReceivableTotal = CurrencyUtil.add(CurrencyUtil.add(getCurrAccountTotal, getCurrReduceTotal),
//					getReceEarlyDamages);// 当月实际应还款总额
//			Double currNotTotal = CurrencyUtil.sub(CurrencyUtil.sub(actualReceivableTotal, getCurrMonPayTotal),
//					getPaidEarlyDamages);// 当月未还款差值
//
//			biggestRepaymentAmount = CurrencyUtil.add(biggestRepaymentAmount, currNotTotal);
//			// System.out.println("=======================" + biggestRepaymentAmount);
//
//			String getCollectionStatus = r.getCollectionStatus();
//			// 将还款状态为未还款或者部分还款的挑出来放到allotReturnPlanList中
//			if ("0".equals(getCollectionStatus.trim()) || "2".equals(getCollectionStatus.trim())) {
//				allotReturnPlanList.add(r);
//			}
//
//		}
//		// 找当期
//		for (ReturnPlan r : returnPlanList) {
//// // System.out.println(r.getPeriod());
//// // System.out.println(r.getRepaymentDate());
//			if (datetime.compareTo(r.getRepaymentDate()) < 0) {
//				thisRp = r;
//				break;
//			}
//		}
//		// System.out.println("最大还款金额============" + biggestRepaymentAmount);
//		ReturnPlanForShowVo returnPlanForShowVo = new ReturnPlanForShowVo();
//		// 设置最大还款金额
//		returnPlanForShowVo.setBiggestRepaymentAmount(biggestRepaymentAmount);
//		// 计算当期应还
//		returnPlanForShowVo.setCurrAmount(CurrencyUtil.sub(
//				CurrencyUtil.add(thisRp.getCurrAccountTotal(), thisRp.getCurrReduceTotal()),
//				thisRp.getCurrMonPayTotal()));
//
//		List<ReturnPlan> returnReturnPlanList = new ArrayList<ReturnPlan>();
//		for (ReturnPlan r : allotReturnPlanList) {
//			if ("1".equals(r.getOverdueFlag())) {
//				returnReturnPlanList.add(r);
//				returnPlanForShowVo.setOverFlag(true);
//			} else {
//				returnReturnPlanList.add(r);
//				break;
//			}
//		}
//		// 计算近期应收
//		Double lastAmount = 0.00;
//		for (ReturnPlan r : returnReturnPlanList) {
//			lastAmount = CurrencyUtil.add(
//					lastAmount,
//					CurrencyUtil.sub(CurrencyUtil.add(r.getCurrAccountTotal(), r.getCurrReduceTotal()),
//							r.getCurrMonPayTotal()));
//		}
//		returnPlanForShowVo.setLastAmout(lastAmount);
//		// 计算逾期总应收
//		if (returnPlanForShowVo.isOverFlag()) {
//			returnPlanForShowVo.setOverAmount(CurrencyUtil.sub(lastAmount, returnPlanForShowVo.getCurrAmount()));
//
//		}
//
//		returnPlanForShowVo.setReturnPlanList(returnReturnPlanList);
		return null;
	}

	/**
	 * append提前还款Row
	 * 
	 * @param receivedRecord 信贷申请主键
	 * @return 还款计划
	 */
	@RequestMapping(value = "appendEarlyRow")
	public @ResponseBody
	ReturnPlanForShowVo appendEarlyRow(ReceivedRecord receivedRecord) {

		Integer creditapplicationId = receivedRecord.getCreditapplicationId();

		Date receivedDate = receivedRecord.getReceivedTime();

		// 通过creditapplicationId去查对应的还款计划全部的哦要！
		ReturnPlan passReturnPlan = new ReturnPlan();
//		passReturnPlan.setCreditapplicationId(creditapplicationId);
//		// 得到了全部的还款计划
//		List<ReturnPlan> returnPlanList = returnPlanService.queryList(passReturnPlan);
//		List<ReturnPlan> allotReturnPlanList = new ArrayList<ReturnPlan>();
//		CreditApplication application = creditApplicationService.selectById(creditapplicationId);
//		AmountConfirm amountConfirm = amountConfirmService.selectNew(creditapplicationId);
//		// 当期
//		ReturnPlan thisRP = null;
//		// 找当期
//		for (ReturnPlan r : returnPlanList) {
//			if (receivedDate.compareTo(r.getRepaymentDate()) <= 0) {
//				// 找到这一期
//				thisRP = r;
//				break;
//			} else {
//				// 这是错的不可能大于零
//				// throw new AppBusinessException("后台日期判断出错！");
//			}
//		}
//
//		// 得到最大还款金额
//		Double biggestRepaymentAmount = 0.00;
//		for (ReturnPlan r : returnPlanList) {
//			r.getCurrAccountTotal();
//			r.getCurrReduceTotal();
//			r.getCurrMonPayTotal();
//
//			Double getCurrAccountTotal = r.getCurrAccountTotal();// 当月应还款总额
//			Double getCurrMonPayTotal = r.getCurrMonPayTotal();// 当月实收还款总额
//			Double getCurrReduceTotal = r.getCurrReduceTotal();// 当月减免总额
//			Double getReceEarlyDamages = r.getReceEarlyDamages();// 当月应收一次性提前还款违约金
//			Double getPaidEarlyDamages = r.getPaidEarlyDamages();// 当月实收一次性提前还款违约金
//
//			Double actualReceivableTotal = CurrencyUtil.add(CurrencyUtil.add(getCurrAccountTotal, getCurrReduceTotal),
//					getReceEarlyDamages);// 当月实际应还款总额
//			Double currNotTotal = CurrencyUtil.sub(CurrencyUtil.sub(actualReceivableTotal, getCurrMonPayTotal),
//					getPaidEarlyDamages);// 当月未还款差值
//
//			biggestRepaymentAmount = CurrencyUtil.add(biggestRepaymentAmount, currNotTotal);
//
//			if ("0".equals(r.getCollectionStatus()) || "2".equals(r.getCollectionStatus())) {
//				allotReturnPlanList.add(r);
//			}
//
//		}
//		String getPeriod = thisRP.getPeriod();
//		int tPeriod = Integer.parseInt(getPeriod);
//		int fPeriod = Integer.parseInt(allotReturnPlanList.get(0).getPeriod());
//		Date submitDate;
//		if (tPeriod < fPeriod) {
//			// 当期已经还完了约的日期应该是未还款的第一期的上一期
//			int i = 0;
//			for (i = 0; i < returnPlanList.size(); i++) {
//				ReturnPlan r = returnPlanList.get(i);
//				int tempP = Integer.parseInt(r.getPeriod());
//				if (tempP == fPeriod) {
//					break;
//				}
//
//			}
//			submitDate = returnPlanList.get(i - 1).getRepaymentDate();
//		} else if (tPeriod == fPeriod) {
//			// 当期还没有还完及当期就是未还款的当期
//			submitDate = allotReturnPlanList.get(0).getRepaymentDate();
//		} else {
//			// 这是错的不可能大于零
//			throw new AppBusinessException("后台日期判断属于哪一期出错！");
//		}
//
//		// System.out.println("最大还款金额============" + biggestRepaymentAmount);
//		/** 第一个参数对象 **/
//		PaymentAmountReqCommon paymentAmountReqCommon = new PaymentAmountReqCommon();
//		// 是否一次性还清
//		paymentAmountReqCommon.setaLLAhead(true);
//		// 预约还款时间
//		paymentAmountReqCommon.setAppointmentDate(DateUtil.dateConvertString(submitDate));
//		// 审核日期（系统审核通过的日期，本地表应该有）
//		// paymentAmountReqCommon.setAuditDate(new Date());
//		// 产品分类编号CP
//		// paymentAmountReqCommon.setCatagoryId(Long.getLong("1111111"));
//		// 合同金额
//		paymentAmountReqCommon.setContractMoney(BigDecimal.valueOf(amountConfirm.getAmount()));
//		// 营业部编号
//		paymentAmountReqCommon.setDepartmentId(Long.valueOf(application.getDepartmentId()));
//		// 放款日期
//		paymentAmountReqCommon.setLenderDate(DateUtil.dateConvertString(application.getSignagreementDate()));
//		// 分期数
//		paymentAmountReqCommon.setPeriodCount(Integer.parseInt(application.getInstalments()));
//		// 上一次计算逾期费用时间
//		// paymentAmountReqCommon.setPrevCalcDate(new Date());
//		// 产品版本编号--REPAYMENT_PLAN_ID
//		paymentAmountReqCommon.setProductInfoId(application.getRepaymentPlanId().longValue());
//		// 请求系统日期
//		paymentAmountReqCommon.setReqSysDate(DateUtil.dateConvertString(new Date()));
//		/** 第二个参数对象 **/
//
//		List<ActualPeriodScheduleDTO> apsDtos = new ArrayList<ActualPeriodScheduleDTO>();
//
//		if (CommonUtil.isNotEmpty(returnPlanList)) {
//			for (ReturnPlan r : returnPlanList) {
//
//				ActualPeriodScheduleDTO actualPeriodScheduleDTO = new ActualPeriodScheduleDTO();
//				boolean s = false;
//				if ("1".equals(r.getOverdueFlag())) {
//					s = !s;
//				}
//				// 逾期分期服务费相关
//				actualPeriodScheduleDTO.setOverdueFQFWFReceivableCharge(BigDecimal.valueOf(0));
//				actualPeriodScheduleDTO.setOverdueFQFWFReceivedCharge(BigDecimal.valueOf(0));
//				// 罚息相关
//				actualPeriodScheduleDTO.setOverdueFxReceivableCharge(BigDecimal.valueOf(r.getCurrMonPenalty()));
//				actualPeriodScheduleDTO.setOverdueFxReceivedCharge(BigDecimal.valueOf(CurrencyUtil.sub(
//						r.getCurrMonPaidPenalty(), r.getCurrReducePenalty())));
//				// 滞纳金相关
//				actualPeriodScheduleDTO.setOverdueZnjReceivableCharge(BigDecimal.valueOf(r.getCurrMonLaterFree()));
//				actualPeriodScheduleDTO.setOverdueZnjReceivedCharge(BigDecimal.valueOf(CurrencyUtil.sub(
//						r.getCurrLateFreePaid(), r.getCurrReduceLaterFree())));
//				// 分期服务费相关
//				actualPeriodScheduleDTO.setPeriodFQFWFReceivableCharge(BigDecimal.valueOf(r.getCurrMonManageFree()));
//				actualPeriodScheduleDTO.setPeriodFQFWFReceivedCharge(BigDecimal.valueOf(CurrencyUtil.sub(
//						r.getCurrPaindinManageFree(), r.getCurrReduceManageFree())));
//				// 利息相关
//				actualPeriodScheduleDTO.setReceivableInterest(BigDecimal.valueOf(r.getCurrMonInterest()));
//				actualPeriodScheduleDTO.setReceivedInterest(BigDecimal.valueOf(CurrencyUtil.sub(
//						r.getCurrPaindinInterest(), r.getCurrReduceInterest())));
//				// 本金相关
//				actualPeriodScheduleDTO.setReceivablePrincipal(BigDecimal.valueOf(r.getCurrMonPrincipal()));
//				actualPeriodScheduleDTO.setReceivedPrincipal(BigDecimal.valueOf(CurrencyUtil.sub(
//						r.getCurrPaindinCapital(), r.getCurrReducePrincipal())));
//				// 总金额相关
//				actualPeriodScheduleDTO.setReceivableMoney(BigDecimal.valueOf(r.getCurrAccountTotal()));
//				actualPeriodScheduleDTO.setReceivedMoney(BigDecimal.valueOf(CurrencyUtil.sub(r.getCurrMonPayTotal(),
//						r.getCurrReduceTotal())));
//				// 其他相关
//				actualPeriodScheduleDTO.setOverdue(s);
//				actualPeriodScheduleDTO.setPeriod(Integer.parseInt(r.getPeriod()));
//				actualPeriodScheduleDTO.setRepayDate(DateUtil.dateConvertString(r.getRepaymentDate()));
//				apsDtos.add(actualPeriodScheduleDTO);
//			}
//		}
//
//		// 转化成webservice
//		PaymentAmountResultInfo paymentAmountResultInfo = borrowingProductsService.paymentAmountCalculate(
//				paymentAmountReqCommon, apsDtos);
//		// 概况
//		paymentAmountResultInfo.getResultCode();
//		// System.out.println("返回接口操作代码，0表示成功，非0表示错误代码。===========" + paymentAmountResultInfo.getResultCode());
//		paymentAmountResultInfo.getResultMsg();
//		// System.out.println("返回接口操作信息，调用成功返回“success”，失败返回错误代码对应的错误信息。==========="
//		// + paymentAmountResultInfo.getResultMsg());
//		// 得到提前一次性还款对象
//		AllAheadSchedule aheadSchedule = paymentAmountResultInfo.getAheadSchedule();
//
//		BigDecimal getCurrentPrincipal = aheadSchedule.getCurrentPrincipal();
//		BigDecimal getCurrentInterest = aheadSchedule.getCurrentInterest();
//		BigDecimal getCurrentPeriodCharge = aheadSchedule.getCurrentPeriodCharge();
//
//		// 目前还没有考虑违约情况
//		if (getCurrentPrincipal.doubleValue() == 0.00 && getCurrentInterest.doubleValue() == 0.00
//				&& getCurrentPeriodCharge.doubleValue() == 0.00) {
//			// 当期要么不用还；要么已经还清，从未还清的第一期开始做减免
//			for (ReturnPlan r : allotReturnPlanList) {
//
//				r.setCurrReduceInterest(CurrencyUtil.sub(r.getCurrReduceInterest(), r.getCurrMonInterest()));
//				r.setCurrReduceManageFree(CurrencyUtil.sub(r.getCurrReduceManageFree(), r.getCurrMonManageFree()));
//				r.setCurrReduceTotal(CurrencyUtil.add(
//						r.getCurrReduceLaterFree(),
//						CurrencyUtil.add(
//								r.getCurrReducePenalty(),
//								CurrencyUtil.add(r.getCurrReducePrincipal(),
//										CurrencyUtil.add(r.getCurrReduceInterest(), r.getCurrReduceManageFree())))));
//			}
//			allotReturnPlanList.get(allotReturnPlanList.size() - 1).setReceEarlyDamages(
//					aheadSchedule.getPenalbond().doubleValue());
//		} else {
//			// 当期没有还清，当期不用做减免，从下一期开始做减免
//			for (int i = 1; i < allotReturnPlanList.size(); i++) {
//				allotReturnPlanList.get(i).setCurrReduceInterest(
//						CurrencyUtil.sub(allotReturnPlanList.get(i).getCurrReduceInterest(), allotReturnPlanList.get(i)
//								.getCurrMonInterest()));
//				allotReturnPlanList.get(i).setCurrReduceManageFree(
//						CurrencyUtil.sub(allotReturnPlanList.get(i).getCurrReduceManageFree(),
//								allotReturnPlanList.get(i).getCurrMonManageFree()));
//				allotReturnPlanList.get(i).setCurrReduceTotal(
//						CurrencyUtil.add(allotReturnPlanList.get(i).getCurrReduceLaterFree(), CurrencyUtil.add(
//								allotReturnPlanList.get(i).getCurrReducePenalty(),
//								CurrencyUtil.add(allotReturnPlanList.get(i).getCurrReducePrincipal(), CurrencyUtil.add(
//										allotReturnPlanList.get(i).getCurrReduceInterest(), allotReturnPlanList.get(i)
//												.getCurrReduceManageFree())))));
//			}
//			allotReturnPlanList.get(allotReturnPlanList.size() - 1).setReceEarlyDamages(
//					aheadSchedule.getPenalbond().doubleValue());
//		}
//
//		List<ReturnPlan> returnReturnPlanList = new ArrayList<ReturnPlan>();
//
//		ReturnPlanForShowVo returnPlanForShowVo = new ReturnPlanForShowVo();
//
//		returnPlanForShowVo.setBiggestRepaymentAmount(biggestRepaymentAmount);
//		returnPlanForShowVo.setTotal(aheadSchedule.getTotal().doubleValue());
//		returnPlanForShowVo.setPenalbond(aheadSchedule.getPenalbond().doubleValue());
//		returnPlanForShowVo.setSurplusPrincipal(aheadSchedule.getSurplusPrincipal().doubleValue());
//		returnPlanForShowVo.setBehindInterests(aheadSchedule.getBehindInterests().doubleValue());
//		returnPlanForShowVo.setCharge(aheadSchedule.getCharge().doubleValue());
//		returnPlanForShowVo.setCurrPrincipal(aheadSchedule.getCurrentPrincipal().doubleValue());
//		returnPlanForShowVo.setCurrInterest(aheadSchedule.getCurrentInterest().doubleValue());
//		returnPlanForShowVo.setCurrCharge(aheadSchedule.getCurrentPeriodCharge().doubleValue());
//		CurrencyUtil.addBigDecimal(getCurrentPrincipal, getCurrentInterest);
//
//		returnPlanForShowVo.setCurrAmount(CurrencyUtil.add(
//				CurrencyUtil.addBigDecimal(getCurrentPrincipal, getCurrentInterest),
//				getCurrentPeriodCharge.doubleValue()));
//
//		returnPlanForShowVo.setReturnPlanList(allotReturnPlanList);

		return null;
	}

	/**
	 * 判断是否为空
	 * 
	 * @param a BigDecimal
	 * @return Null or 0
	 */
	private BigDecimal avoidNull(BigDecimal a) {
		if (a == null) {
			a = new BigDecimal("0");
		}
		return a;
	}
}
