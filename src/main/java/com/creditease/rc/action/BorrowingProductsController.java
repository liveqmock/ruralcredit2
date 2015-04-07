package com.creditease.rc.action;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.domain.ActualPeriodScheduleDTO;
import com.creditease.rc.domain.CalculatedBorrowingRateInfo;
import com.creditease.rc.domain.ChargePeriodScheduleInfo;
import com.creditease.rc.domain.ChargePeriodScheduleInfo2;
import com.creditease.rc.domain.PaymentAmountReqCommon;
import com.creditease.rc.domain.PaymentAmountResultInfo;
import com.creditease.rc.domain.ProductInfo;
import com.creditease.rc.domain.RepaymentPlanInfo;
import com.creditease.rc.domain.ReturnPlan;
import com.creditease.rc.domain.ReturnPlanDTO;
import com.creditease.rc.service.IBorrowingProductsService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.JsonUtil;

/**
 * 
 * Title:BorrowingProductsController.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-2-27上午10:31:21
 * @author 王毅峰
 * @version v2.0
 */
@Controller
@RequestMapping("borrowingProducts")
public class BorrowingProductsController {
	
	/**
	 * 
	 * @param binder 传入的所有参数
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@Resource
	public IBorrowingProductsService productsService;
	
	/**
	 * 费率计算1
	* @author wyf  
	* @Description: 
	* @param: rateInfo
	* @return: List<PeriodSchedule> 
	* @throws: 
	 */
	@RequestMapping(value="rateCalculate",method=RequestMethod.POST)
	@ResponseBody
	public List<ChargePeriodScheduleInfo> rateCalculate(CalculatedBorrowingRateInfo rateInfo){
		List<ChargePeriodScheduleInfo> periodSchedules = new ArrayList<ChargePeriodScheduleInfo>();
		periodSchedules = productsService.rateCalculate(rateInfo);
		return periodSchedules;
	}
	/**
	 * 费率计算2
	* @author wyf  
	* @Description: 
	* @param: rateInfo
	* @return: ChargePeriodScheduleInfo2 
	* @throws:
	 */
	@RequestMapping(value="rateCalculate2",method=RequestMethod.POST)
	@ResponseBody
	public ChargePeriodScheduleInfo2 rateCalculate2(CalculatedBorrowingRateInfo rateInfo){
		ChargePeriodScheduleInfo2 periodSchedules = productsService.rateCalculate2(rateInfo);
		return periodSchedules;
	}
	/**
	 * 还款计划计算
	* @author wyf  
	* @Description: 
	* @param: rateInfo
	* @return: ChargePeriodScheduleInfo2 
	* @throws:
	 */
	@RequestMapping(value="repaymentCalculate",method=RequestMethod.POST)
	@ResponseBody
	public RepaymentPlanInfo repaymentCalculate(CalculatedBorrowingRateInfo rateInfo){
		RepaymentPlanInfo repaymentPlanInfo = productsService.repaymentCalculate(rateInfo);
		return repaymentPlanInfo;
	}
	/**
	 * 查询产品方案
	* @author wyf  
	* @Description: 
	* @param: departmentId
	* @return: Map<String,List<ProductInfo>> 
	* @throws:
	 */
	@RequestMapping(value="queryProByDepart",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, List<ProductInfo>> queryProByDepart(String departmentId){
		Map<String, List<ProductInfo>> map = new HashMap<String, List<ProductInfo>>();
		if(CommonUtil.isNotEmpty(departmentId)){
			map = productsService.queryProByDepart(Long.valueOf(departmentId));
		}
		return map;
	}
	/**
	 * 查询产品方案
	* @author wyf  
	* @Description: 
	* @param: departmentId
	* @return: Map<String,List<ProductInfo>> 
	* @throws:
	 */
	@RequestMapping(value="queryProByDepartByList",method=RequestMethod.POST)
	@ResponseBody
	public List<ProductInfo> queryProByDepartByList(String departmentId){
		Map<String, List<ProductInfo>> map = new HashMap<String, List<ProductInfo>>();
		if(CommonUtil.isNotEmpty(departmentId)){
			map = productsService.queryProByDepart(Long.valueOf(departmentId));
		}
		return map.get("paymentType");
	}
	/**
	 * 计算客户某一天的还款金额
	* @author wyf  
	* @Description: 
	* @param: departmentId
	* @return: Map<String,List<ProductInfo>> 
	* @throws:
	 */
	@RequestMapping(value="paymentAmountCalculate",method=RequestMethod.POST)
	@ResponseBody
	public PaymentAmountResultInfo paymentAmountCalculate(String paymentAmountReq,String apsDtos){
		PaymentAmountReqCommon amountReqCommon = (PaymentAmountReqCommon)JsonUtil.getObject(paymentAmountReq,PaymentAmountReqCommon.class);
		List<ActualPeriodScheduleDTO> apsList = JsonUtil.getListFromJsonArrStr(apsDtos, ActualPeriodScheduleDTO.class);
		PaymentAmountResultInfo resultInfo = productsService.paymentAmountCalculate(amountReqCommon, apsList);
		return resultInfo;
	}
	
	/**
	 * 计算客户某一天的还款金额
	* @author wyf  
	* @Description: 
	* @param: departmentId
	* @return: Map<String,List<ProductInfo>> 
	* @throws:
	 */
	@RequestMapping(value="paymentAmountCalculate2",method=RequestMethod.POST)
	@ResponseBody
	public PaymentAmountResultInfo paymentAmountCalculate2(String paymentAmountReq,String apsDtos){
		PaymentAmountReqCommon amountReqCommon = (PaymentAmountReqCommon)JsonUtil.getObject(paymentAmountReq,PaymentAmountReqCommon.class);
		List<ActualPeriodScheduleDTO> apsList = new ArrayList<ActualPeriodScheduleDTO>();
		List<ReturnPlanDTO> plans = JsonUtil.getListFromJsonArrStr(apsDtos, ReturnPlanDTO.class);
		for(ReturnPlanDTO rp:plans){
			ActualPeriodScheduleDTO aps = new ActualPeriodScheduleDTO();
			if("1".equals(rp.getOverdueFlag())){
				aps.setOverdue(true);
			}else{
				aps.setOverdue(false);
			}
			aps.setOverdueFxReceivableCharge(new BigDecimal(rp.getCurrMonPenalty()));//应还罚息
			aps.setOverdueFxReceivedCharge(new BigDecimal(rp.getCurrMonPaidPenalty()));//实收罚息
			aps.setOverdueZnjReceivableCharge(new BigDecimal(rp.getCurrMonLaterFree()));//应还滞纳金
			aps.setOverdueZnjReceivedCharge(new BigDecimal(rp.getCurrLateFreePaid()));//实收滞纳金
			aps.setPeriod(Integer.valueOf(rp.getPeriod()));//分期数
			aps.setPeriodFQFWFReceivableCharge(new BigDecimal(rp.getCurrMonManageFree()));// 应还服务费
			aps.setPeriodFQFWFReceivedCharge(new BigDecimal(rp.getCurrPaindinManageFree()));//实收服务费
			aps.setReceivableInterest(new BigDecimal(rp.getCurrMonInterest()));//应还利息
			aps.setReceivableMoney(new BigDecimal(rp.getCurrAccountTotal()));//应还总额
			aps.setReceivablePrincipal(new BigDecimal(rp.getCurrMonPrincipal()));//应还本金
			aps.setReceivedInterest(new BigDecimal(rp.getCurrPaindinInterest()));//实收利息
			aps.setReceivedMoney(new BigDecimal(rp.getCurrMonPayTotal()));//实收总金额
			aps.setReceivedPrincipal(new BigDecimal(rp.getCurrPaindinCapital()));//实收本金
			aps.setRepayDate(rp.getRepaymentDate());//还款日期
			apsList.add(aps);
		}
		PaymentAmountResultInfo amountResultInfo = productsService.paymentAmountCalculate(amountReqCommon, apsList);
		if(CommonUtil.isEmpty(amountResultInfo.getResultMsg()))amountResultInfo.setResultMsg("无结果");
		return amountResultInfo;
	}
	@RequestMapping(value="queryReturnPlanByGroupNum",method=RequestMethod.POST)
	@ResponseBody
	public List<ReturnPlan> queryReturnPlanByGroupNum(String groupNum){
		List<ReturnPlan> plans = new ArrayList<ReturnPlan>();
		plans = productsService.queryReturnPlanByGroupNum(groupNum);
		return plans;
	}
}
