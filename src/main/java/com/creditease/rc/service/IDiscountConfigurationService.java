package com.creditease.rc.service;

import java.math.BigDecimal;
import java.util.Map;

import com.creditease.rc.domain.CreditChannelConfig;
import com.creditease.rc.domain.DiscountConfiguration;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;

public interface IDiscountConfigurationService {

	Pagination queryDiscountConfigurationList(DiscountConfiguration discountConfiguration, Pagination pagination);

	Message saveDiscountConfiguration(DiscountConfiguration discountConfiguration);

	Message removeDiscountConfigurationByDiscountConfigurationId(Long discountConfigurationId);

	Message discountRule(Long creditapplicationId);

	Map<String, String> checkDiscountConfiguration(Long creditapplicationId);

	boolean updateCreditDiscountVo(Long creditapplicationId, String discountFlag, BigDecimal discount);

	Pagination queryCreditChannelConfig(String ids, Pagination pagination);

	Message saveCreditChannelConfig(CreditChannelConfig config);

	Message removeCreditChannelConfigById(Long discountConfigurationId);

	boolean checkExistOfConfig(String departmentId);
	
	/**
	 * 根据信贷申请id查询是否配置综合信贷
	 * @param creditapplicationId
	 * @return
	 */
	String queryIsZhxindai(Long creditapplicationId);

}
