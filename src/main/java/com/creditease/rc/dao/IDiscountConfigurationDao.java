package com.creditease.rc.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.CreditChannelConfig;
import com.creditease.rc.domain.DiscountConfiguration;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.CreditDiscountVo;

public interface IDiscountConfigurationDao {

	Pagination queryDiscountConfigurationList(DiscountConfiguration discountConfiguration, Pagination pagination);

	boolean insertDiscountConfiguration(DiscountConfiguration discountConfiguration);

	boolean updateDiscountConfiguration(DiscountConfiguration discountConfiguration);

	List<DiscountConfiguration> queryDiscountConfiguration(DiscountConfiguration queryDiscountConfiguration);

	boolean removeDiscountConfigurationByDiscountConfigurationId(Long discountConfigurationId);

	List<Long> discountRule(Map<String, String> queryMap);

	boolean updateCreditDiscountVo(Long creditapplicationId, String discountFlag, BigDecimal discount);

	CreditDiscountVo checkDiscountConfiguration(Long creditapplicationId);

	CreditDiscountVo queryCreditDisConfig(Long creditapplicationId);

	Pagination queryCreditChannelConfig(CreditChannelConfig config, Pagination pagination);

	Object insertCreditChannelConfig(CreditChannelConfig config);

	Object updateCreditChannelConfig(CreditChannelConfig config);

	Object removeCreditChannelConfigById(Long discountConfigurationId);

	Object countCreditChannelConfig(String departmentId);
	/**
	 * 根据信贷申请id查询是否配置综合信贷
	 * @param creditapplicationId
	 * @return
	 */
	String queryIsZhxindai(Long creditapplicationId);

}
