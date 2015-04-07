package com.creditease.rc.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.creditease.rc.app.pdf.QueryProInfoResult;
import com.creditease.rc.app.pdf.RateReqResult;
import com.creditease.rc.app.pdf.RepaymentPlanReq;
import com.creditease.rc.app.pdf.RepaymentPlanReqResult;
import com.creditease.rc.domain.ActualPeriodScheduleDTO;
import com.creditease.rc.domain.CalculatedBorrowingRateInfo;
import com.creditease.rc.domain.ChargePeriodScheduleInfo;
import com.creditease.rc.domain.ChargePeriodScheduleInfo2;
import com.creditease.rc.domain.PaymentAmountReqCommon;
import com.creditease.rc.domain.PaymentAmountResultInfo;
import com.creditease.rc.domain.ProductInfo;
import com.creditease.rc.domain.RepaymentPlanInfo;
import com.creditease.rc.domain.ReturnPlan;
import com.creditease.rc.vo.AdvancePlanVo;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

/**
 * 
 * Title:IBorrowingProductsService.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-2-27上午10:03:01
 * @author 王毅峰
 * @version v2.0
 */
public interface IBorrowingProductsService {
	/**
	 * 费率计算
	* @author wyf  
	* @Description: 
	* @param: rateInfo
	* @return: CalculatedBorrowingRateInfo 
	* @throws:
	 */
	public List<ChargePeriodScheduleInfo> rateCalculate(CalculatedBorrowingRateInfo rateInfo);
	/**
	 * 费率计算
	* @author wyf  
	* @Description: 
	* @param: rateInfo
	* @return: CalculatedBorrowingRateInfo 
	* @throws:
	 */
	public ChargePeriodScheduleInfo2 rateCalculate2(CalculatedBorrowingRateInfo rateInfo);
	/**
	 * 产品计算（提前还款）
	* @author wyf  
	* @Description: 
	* @param: rateInfo
	* @return: RepaymentPlanInfo 
	* @throws:
	 */
	public RepaymentPlanInfo repaymentCalculate(CalculatedBorrowingRateInfo rateInfo);
	/**
	 * 产品方案查询
	* @author wyf  
	* @Description: 
	* @param: departmentId
	* @return: Map<String,List<ProductInfo>> 
	* @throws:
	 */
	public Map<String,List<ProductInfo>> queryProByDepart(Long departmentId);
	/**
	 * 计算客户某一天的还款金额
	* @author wyf  
	* @Description: 
	* @param: paymentAmountReq
	* @param: apsDtos
	* @return: PaymentAmountReqResult 
	 */
	public PaymentAmountResultInfo paymentAmountCalculate(PaymentAmountReqCommon paymentAmountReq,List<ActualPeriodScheduleDTO> apsDtos);
	/**
	 * 小组编号查询还款计划
	* @author wyf  
	* @Description: 
	* @param: groupNum
	* @return: List<ReturnPlan> 
	 */
	public List<ReturnPlan> queryReturnPlanByGroupNum(String groupNum);
	
	
	/**
	 * 
	 * @param departmentId
	 * @param ContractMoney
	 * @param lenderDate
	 * @param productInfoId
	 * @param periodCount
	 * @return
	 */
	public RateReqResult advanceCaculateResult(Long departmentId, BigDecimal ContractMoney,XMLGregorianCalendarImpl lenderDate,Long  productInfoId ,Integer periodCount);
	
	/**
	 * 
	 * @param departmentId
	 * @param ContractMoney
	 * @param lenderDate
	 * @param productInfoId
	 * @param periodCount
	 * @return
	 */
	public AdvancePlanVo advanceReturnPlanResult(Long departmentId, BigDecimal ContractMoney,XMLGregorianCalendarImpl lenderDate,Long  productInfoId ,Integer periodCount,String repaymentType);
	/**
	 * 根据部门id获取产品列表
	 * @param departMentId
	 * @return
	 */
	public List<Map> getProductList(Integer departMentId);
	
	
	public RepaymentPlanReqResult advanceReturnPlan(RepaymentPlanReq repaymentPlanReq);
	
	/**
	 * 查询产品大类，如小薇企业贷，房贷，农村商业贷等
	 * @return
	 */
	public QueryProInfoResult queryProducts();

}
