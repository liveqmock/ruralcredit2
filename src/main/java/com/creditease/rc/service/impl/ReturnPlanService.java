package com.creditease.rc.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.creditease.rc.util.DateUtil;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormat;
import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IReturnPlanDao;
import com.creditease.rc.dao.IReturnScheduleDAO;
import com.creditease.rc.domain.ClientApplyHistory;
import com.creditease.rc.domain.CustomerReturnVisit;
import com.creditease.rc.domain.LocalReturnSchemeResponse;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.ReturnPlan;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IClientApplyHistoryService;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.ICustomerConsultPoolService;
import com.creditease.rc.service.ICustomerReturnVisitService;
import com.creditease.rc.service.IReturnPlanService;
import com.creditease.rc.service.IRural2CreditService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.vo.CustomerReturnVisitVo;

/**
 * 还款计划服务
 * 
 * @author zhangman
 * 
 */
@Service
public class ReturnPlanService implements IReturnPlanService {
	@Resource
	private IReturnPlanDao returnPlanDao;
	// 信贷申请服务
	@Resource
	private ICreditApplicationService creditApplicationService;
	@Resource
	private IReturnScheduleDAO returnScheduleDAO;
	@Resource
	private ICustomerReturnVisitService customerReturnVisitService;

	/** 贷后封装好的接口 **/
	@Resource
	private IRural2CreditService rural2CreditService;
	@Resource
	private IClientApplyHistoryService clientApplyHistoryService;
	@Resource
	ICustomerConsultPoolService customerConsultPoolService;

	private Logger log = Logger.getLogger(ReturnPlanService.class);

	@Override
	public void addBatchReturnPlan(List<ReturnPlan> returnPlans) {
		returnPlanDao.batchInsert("RETURNPLAN.dynamicInsert", returnPlans);
	}

	@Override
	public boolean addForCreditApplication(Integer creditApplicationId) {
		if (creditApplicationId != null) {
			// 修改了 位置 放在了 提交申请的地方
// 判断更改循环贷标记
			// creditApplicationService.updateRevolving(creditApplicationId);
			customerConsultPoolService.updateAcceptConsultStateByCreditApplicationId(creditApplicationId);
			Message message = rural2CreditService.updateClientApplyRecord(Long.valueOf(creditApplicationId.toString()));
			return message.isSuccess();
		} else {
			return false;
		}
//
// CreditApplication creditApplication = creditApplicationService.selectAmount(creditApplicationId);
//
// GregorianCalendar cal = new GregorianCalendar();
// cal.setTime(creditApplication.getSignagreementDate());
// XMLGregorianCalendar gc = null;
// try {
// gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
// } catch (DatatypeConfigurationException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
//
// RepaymentPlanReq repaymentPlanReq = new RepaymentPlanReq();
// repaymentPlanReq.setDepartmentId(Long.valueOf(creditApplication.getCompanyId()));
// repaymentPlanReq.setContractMoney(new BigDecimal(creditApplication.getLoanAmount()));
// repaymentPlanReq.setProductInfoId(Long.valueOf(creditApplication.getRepaymentPlanId()));
// repaymentPlanReq.setPeriodCount(Integer.valueOf(creditApplication.getInstalments()));
// repaymentPlanReq.setLenderDate(gc);
//
// PaymentTypeConfig p = new PaymentTypeConfig();
// p.setPaymentTypeCode(creditApplication.getRepaymentType());
// PaymentTypeList ptlist = new PaymentTypeList();
// ptlist.getPaymentTypeConfig().add(p);
// repaymentPlanReq.setPaymentTypeList(ptlist);
//
// log.info("ceBorrowingProductsWS.ceRepaymentPlanReq(repaymentPlanReq) request_params:******");
// log.info(JsonUtil.getJackson(repaymentPlanReq));
// RepaymentPlanReqResult result = ceBorrowingProductsWS.ceRepaymentPlanReq(repaymentPlanReq);// 费率计算请求-查询借款产品费率，并计算利息和服务费等
// log.info("ceBorrowingProductsWS.ceRepaymentPlanReq(repaymentPlanReq) response_params:******");
// log.info(JsonUtil.getJackson(result));
// if ("0".equals(result.getResultCode())) {
// RepaymentPlanReqResult.PeriodScheduleList periodScheduleList = result.getPeriodScheduleList();
// List<PeriodSchedule> periodSchedules = periodScheduleList.getPeriodSchedule();
// List<ReturnPlan> returnPlanList = new ArrayList<ReturnPlan>();
// for (PeriodSchedule periodSchedule : periodSchedules) {
// ReturnPlan returnPlan = new ReturnPlan();
// // 本期还款应还款时间
// returnPlan.setRepaymentDate(periodSchedule.getRepayDate().toGregorianCalendar().getTime());
// returnPlan.setCreditapplicationId(creditApplicationId);
// // 当前应还本金
// returnPlan.setCurrMonPrincipal(periodSchedule.getAmortizedPrincipal().doubleValue());
// // 当月应还利息
// returnPlan.setCurrMonInterest(periodSchedule.getAmortizedInterest().doubleValue());
// // 当月应还管理费
// returnPlan.setCurrMonManageFree(periodSchedule.getPeriodCharge().doubleValue());
// // 当月应还合计
// returnPlan.setCurrAccountTotal(periodSchedule.getPeriodMoney().doubleValue());
// // 提前还款总额 allHeadMoney
// returnPlan.setAllHeadMoney(periodSchedule.getAllheadMoney().doubleValue());
// // 分期数
// returnPlan.setPeriod(String.valueOf(periodSchedule.getPeriod()));
// // 如果还款方式是现金 设置AUTO_SWITCH 为'1'
// if ("1".equals(creditApplication.getDefaultReturnmentWay())) {
// returnPlan.setAutoSwitch("1");
// }
// if (returnPlan.getCurrAccountTotal() == 0) {
// returnPlan.setCollectionStatus("3");
// }
// returnPlanList.add(returnPlan);
// }
// try {
// this.addBatchReturnPlan(returnPlanList);
// return true;
// } catch (Exception e) {
// e.printStackTrace();
// throw new AppBusinessException(e, "生成还款计划出错！");
// }
// } else {
// throw new AppBusinessException(result.getResultMessage());
// }
	}

	@Override
	public List<ReturnPlan> selectList(ReturnPlan returnPlan) {
		return (List<ReturnPlan>) returnScheduleDAO.queryList("RETURNPLAN.queryForShow", returnPlan);
	}

	@Override
	public List<ReturnPlan> queryList(ReturnPlan returnPlan) {
		// TODO Auto-generated method stub
		return (List<ReturnPlan>) returnScheduleDAO.queryList("RETURNPLAN.dynamicSelect", returnPlan);
	}

	@Override
	public int deleteReturnPlan(Integer creditApplicationId) {
		int i = returnScheduleDAO.delete("RETURNPLAN.deleteReturnPlanByCreditapplicationId", creditApplicationId);
		return i;
	}

	/**
	 * 返回分页列表调用贷后webservice
	 * 
	 * @author 郝强
	 * @param creditApplicationId 信贷申请ID
	 * @param pagination 分页对象
	 * @return Pagination
	 */
	@Override
	public Pagination queryReturnPlanDataGrid(Long creditApplicationId, Pagination pagination)  {
		// TODO Auto-generated method stub
		List<LocalReturnSchemeResponse> localReturnSchemeResponses = rural2CreditService
				.returnScheme(creditApplicationId);
        //  如果是当前还款日期和系统时间相同 不允许一次性提前还款登记
        String sysDate = DateUtil.dateConvertString(new Date(),"yyyy-MM-dd");
        for(LocalReturnSchemeResponse localReturnSchemeResponse:localReturnSchemeResponses){
            Date repayDate = localReturnSchemeResponse.getRepayDate();
            localReturnSchemeResponse.setCanUseReturnButton("true");
			localReturnSchemeResponse.setNeedRemindReceived("false");
            String isReturned = localReturnSchemeResponse.getIsReturned();
            //判断当前系统日期是否与还款日期相同
            if (repayDate != null && sysDate.equals(DateUtil.dateConvertString(repayDate,"yyyy-MM-dd").trim())){
				//判断是否还款为未还款
				if(isReturned != null  && "0".equals(isReturned)){
					localReturnSchemeResponse.setCanUseReturnButton("false");
				}
			}
			//判断当期是否已经还款
			Date today = new Date();
			Calendar calendarToday = Calendar.getInstance();

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String td= df.format(today);
			Calendar calendar = Calendar.getInstance();
			try {
				calendarToday.setTime(df.parse(td));
				calendar.setTime(df.parse(td));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			calendar.add(Calendar.MONTH, -1);
			Date  lastMonth = calendar.getTime();
			if((calendarToday.getTime().getTime() > lastMonth.getTime()) && (calendarToday.getTime().getTime()<=repayDate.getTime()) && (localReturnSchemeResponse.getReceivedMoney().doubleValue()>=localReturnSchemeResponse.getReceivableMoney().doubleValue() )){
				localReturnSchemeResponse.setNeedRemindReceived("true");
			}
        }
		pagination.setItems(localReturnSchemeResponses);
		pagination.setCurrentPage(1);
		pagination.setPage(1);
		pagination.setPageSize(1);
		pagination.setTotal(localReturnSchemeResponses.size());
		return pagination;
	}

	@Override
	public Map<String, String> queryProductMap() {
		// TODO Auto-generated method stub
		return returnPlanDao.queryProductMap();
	}

	@Override
	public Message clientApply(Long creditapplicationId) {
		// TODO Auto-generated method stub
		Message message = rural2CreditService.clientApply(creditapplicationId);
		return message;
	}

	@Override
	public Message updateClientMission() {
		// TODO Auto-generated method stub
		System.out.println("updateClientMission重发借款人消息");
		Message message = new Message();
		message.setSuccess(true);
		int sCount = 0;
		int fCount = 0;
		ClientApplyHistory queryClientApplyHistory = new ClientApplyHistory();
		queryClientApplyHistory.setIsSuccess("1");
		List<ClientApplyHistory> clientApplyHistories = clientApplyHistoryService
				.dynamicSelect(queryClientApplyHistory);
		if (CommonUtil.isNotEmpty(clientApplyHistories)) {
			for (ClientApplyHistory clientApplyHistory : clientApplyHistories) {
				Message temp = rural2CreditService.updateClientApplyRecord(clientApplyHistory.getCreditapplicationId());
				if (temp.isSuccess()) {
					sCount = sCount + 1;
				} else {
					fCount = fCount + 1;
				}
			}
			message.setMsg(clientApplyHistories.size() + "条已完成！成功：" + sCount + "条,失败：" + fCount + "条");
		} else {
			message.setMsg("0条已完成！");
		}
		return message;
	}

	@Override
	public Message insertBatchReturnPlans() {
		// TODO Auto-generated method stub
		Message message = new Message();
		List<Long> creditAppliationIds = returnPlanDao.queryHasNoReturnPlan();
		if (CommonUtil.isNotEmpty(creditAppliationIds)) {
			List<ReturnPlan> insertReturnPlans = new ArrayList<ReturnPlan>();
			for (Long creditAppliationId : creditAppliationIds) {
				List<LocalReturnSchemeResponse> localReturnSchemeResponses = rural2CreditService
						.returnScheme(creditAppliationId);
				List<ReturnPlan> returnPlans = trancReturnPlanList(localReturnSchemeResponses, creditAppliationId);
				insertReturnPlans.addAll(returnPlans);
			}
			returnPlanDao.batchInsert("RETURNPLAN.insertNoType", insertReturnPlans);
			message.setSuccess(true);
			message.setMsg("同步完成！");
		} else {
			message.setSuccess(true);
			message.setMsg("没有需要同步的数据！");
		}
		return message;
	}

	private List<ReturnPlan> trancReturnPlanList(List<LocalReturnSchemeResponse> localReturnSchemeResponses,
			Long creditAppliationId) {
		List<ReturnPlan> returnPlans = new ArrayList<ReturnPlan>();
		if (CommonUtil.isNotEmpty(localReturnSchemeResponses)) {
			for (LocalReturnSchemeResponse localReturnSchemeResponse : localReturnSchemeResponses) {
				Integer getPeriod = localReturnSchemeResponse.getPeriod();
				Date getRepayDate = localReturnSchemeResponse.getRepayDate();
				BigDecimal getReceivableMoney = localReturnSchemeResponse.getReceivableMoney();
				BigDecimal getReceivablePrincipal = localReturnSchemeResponse.getReceivablePrincipal();
				BigDecimal getReceivableInterest = localReturnSchemeResponse.getReceivableInterest();
				BigDecimal getfQFWFReceivableCharge = localReturnSchemeResponse.getfQFWFReceivableCharge();
				BigDecimal getfXReceivableCharge = localReturnSchemeResponse.getfXReceivableCharge();
				BigDecimal getzNJReceivableCharge = localReturnSchemeResponse.getzNJReceivableCharge();

				BigDecimal getReceivedMoney = localReturnSchemeResponse.getReceivedMoney();
				BigDecimal getReceivedPrincipal = localReturnSchemeResponse.getReceivedPrincipal();
				BigDecimal getReceivedInterest = localReturnSchemeResponse.getReceivedInterest();
				BigDecimal getfQFWFReceivedCharge = localReturnSchemeResponse.getfQFWFReceivedCharge();
				BigDecimal getfXReceivedCharge = localReturnSchemeResponse.getfXReceivedCharge();
				BigDecimal getzNJReceivedCharge = localReturnSchemeResponse.getzNJReceivedCharge();

				ReturnPlan returnPlan = new ReturnPlan();
				returnPlan.setCreditapplicationId(creditAppliationId);
				returnPlan.setPeriod(getPeriod == null ? "" : String.valueOf(getPeriod));
				returnPlan.setRepaymentDate(getRepayDate);
				returnPlan.setCurrAccountTotal(getReceivableMoney);
				returnPlan.setCurrMonPrincipal(getReceivablePrincipal);
				returnPlan.setCurrMonInterest(getReceivableInterest);
				returnPlan.setCurrMonManageFree(getfQFWFReceivableCharge);
				returnPlan.setCurrMonPenalty(getfXReceivableCharge);
				returnPlan.setCurrMonLaterFree(getzNJReceivableCharge);

				returnPlan.setCurrMonPayTotal(getReceivedMoney);
				returnPlan.setCurrPaindinCapital(getReceivedPrincipal);
				returnPlan.setCurrPaindinInterest(getReceivedInterest);
				returnPlan.setCurrPaindinManageFree(getfQFWFReceivedCharge);
				returnPlan.setCurrMonPaidPenalty(getfXReceivedCharge);
				returnPlan.setCurrLateFreePaid(getzNJReceivedCharge);
				returnPlans.add(returnPlan);
			}
		}
		return returnPlans;
	}

	@Override
	public Pagination queryHaveNoReturnPlanList(Pagination pagination, Map<String, String> queryMap) {
		// TODO Auto-generated method stub
		return returnPlanDao.queryForPaginatedList("RETURNPLAN.queryHaveNoReturnPlanListEntity",
				"RETURNPLAN.queryHaveNoReturnPlanCount", queryMap, pagination.getStartResult(),
				pagination.getPageSize());
	}

	@Override
	public boolean insertReturnPlanFromIocalReturnSchemeResponses(
			List<LocalReturnSchemeResponse> localReturnSchemeResponses, Long creditAppliationId) {
		// TODO Auto-generated method stub
		if (CommonUtil.isNotEmpty(localReturnSchemeResponses)) {
			// 生成回访计划 //优先 判断是否有回访计划
			CustomerReturnVisitVo customerReturnVisitVo = new CustomerReturnVisitVo();
			customerReturnVisitVo.setCreditapplicationId(creditAppliationId);
			List<CustomerReturnVisitVo> customerReturnVisitVos = customerReturnVisitService
					.selectAllList(customerReturnVisitVo);
			if (localReturnSchemeResponses.size() > 0 && customerReturnVisitVos.size() == 0) {
				List<CustomerReturnVisit> customerReturnVisits = new ArrayList<CustomerReturnVisit>();
				for (int i = 0; i < localReturnSchemeResponses.size(); i++) {
					CustomerReturnVisit customerReturnVisit = new CustomerReturnVisit();
					customerReturnVisit.setCreditapplicationId(creditAppliationId);
					customerReturnVisit.setStatus("0");
					customerReturnVisit.setRepaymentDate(localReturnSchemeResponses.get(i).getRepayDate());
					customerReturnVisits.add(customerReturnVisit);
				}
				customerReturnVisitService.insertBatch(customerReturnVisits);
			}
			List<ReturnPlan> returnPlans = trancReturnPlanList(localReturnSchemeResponses, creditAppliationId);
			returnPlanDao.batchInsert("RETURNPLAN.insertNoType", returnPlans);
		}
		return true;
	}
}
