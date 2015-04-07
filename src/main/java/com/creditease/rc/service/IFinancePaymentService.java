package com.creditease.rc.service;

import com.creditease.rc.domain.GroupLoanRegist;
import com.creditease.rc.domain.Message;
/**
 * 调用结算平台的服务
 * @author xubingzhao
 *
 */
public interface IFinancePaymentService {
	/**
	 * 财务付款预约服务
	 * @param creditApplicationId 信贷申请ID
	 * @param parameter  页面传的备注日期等信息
	 * @return  Message 
	 */
	public Message financialpayment(Integer creditApplicationId, GroupLoanRegist parameter);
}
