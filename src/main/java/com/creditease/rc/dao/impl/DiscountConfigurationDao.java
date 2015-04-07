package com.creditease.rc.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.creditease.rc.domain.CreditChannelConfig;
import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IDiscountConfigurationDao;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.DiscountConfiguration;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.CreditDiscountVo;

@Repository
public class DiscountConfigurationDao implements IDiscountConfigurationDao {

	@Resource
	private IBaseDao baseDao;

	@Override
	public Pagination queryDiscountConfigurationList(DiscountConfiguration discountConfiguration, Pagination pagination) {
		// TODO Auto-generated method stub
		return baseDao.queryForPaginatedList("DISCOUNTCONFIGURATION.queryDiscountConfigurationEntity",
				"DISCOUNTCONFIGURATION.queryDiscountConfigurationCount", discountConfiguration,
				pagination.getStartResult(), pagination.getPageSize());
	}

	@Override
	public boolean insertDiscountConfiguration(DiscountConfiguration discountConfiguration) {
		// TODO Auto-generated method stub
		baseDao.insertObject("DISCOUNTCONFIGURATION.insert", discountConfiguration);
		return true;
	}

	@Override
	public boolean updateDiscountConfiguration(DiscountConfiguration discountConfiguration) {
		// TODO Auto-generated method stub
		baseDao.update("DISCOUNTCONFIGURATION.dynamicUpdate", discountConfiguration);
		return true;
	}

	@Override
	public List<DiscountConfiguration> queryDiscountConfiguration(DiscountConfiguration queryDiscountConfiguration) {
		// TODO Auto-generated method stub
		return (List<DiscountConfiguration>) baseDao.queryList("DISCOUNTCONFIGURATION.queryDiscountConfiguration",
				queryDiscountConfiguration);
	}

	@Override
	public boolean removeDiscountConfigurationByDiscountConfigurationId(Long discountConfigurationId) {
		// TODO Auto-generated method stub
		baseDao.delete("DISCOUNTCONFIGURATION.deleteByPrimeKey", discountConfigurationId);
		return true;
	}

	@Override
	public List<Long> discountRule(Map<String, String> queryMap) {
		// TODO Auto-generated method stub
		return (List<Long>) baseDao.queryList("DISCOUNTCONFIGURATION.discountRule", queryMap);
	}

	@Override
	public boolean updateCreditDiscountVo(Long creditapplicationId, String discountFlag, BigDecimal discount) {
		// TODO Auto-generated method stub
		CreditDiscountVo creditDiscountVo = new CreditDiscountVo();
		creditDiscountVo.setCreditapplicationId(creditapplicationId);
		creditDiscountVo.setDiscountFlag(discountFlag);
		creditDiscountVo.setDiscount(discount);
		baseDao.update("DISCOUNTCONFIGURATION.updateCreditDiscountVo", creditDiscountVo);
		return true;
	}

	@Override
	public CreditDiscountVo checkDiscountConfiguration(Long creditapplicationId) {
		// TODO Auto-generated method stub
		return (CreditDiscountVo) baseDao.queryUnique("DISCOUNTCONFIGURATION.checkDiscountConfiguration",
				creditapplicationId);
	}

	@Override
	public CreditDiscountVo queryCreditDisConfig(Long creditapplicationId) {
		// TODO Auto-generated method stub
		return (CreditDiscountVo) baseDao
				.queryUnique("DISCOUNTCONFIGURATION.queryCreditDisConfig", creditapplicationId);
	}

	@Override
	public Pagination queryCreditChannelConfig(CreditChannelConfig config, Pagination pagination) {
		return baseDao.queryForPaginatedList("DISCOUNTCONFIGURATION.queryCreditChannelConfig",
				"DISCOUNTCONFIGURATION.queryCreditChannelConfigCount", config,
				pagination.getStartResult(), pagination.getPageSize());
	}

	@Override
	public Object insertCreditChannelConfig(CreditChannelConfig config) {
		return baseDao.insertObject("DISCOUNTCONFIGURATION.insertCreditChannelConfig", config);
	}

	@Override
	public Object updateCreditChannelConfig(CreditChannelConfig config) {
		return baseDao.update("DISCOUNTCONFIGURATION.updateCreditChannelConfig", config);
	}

	@Override
	public Object removeCreditChannelConfigById(Long configId) {
		return baseDao.delete("DISCOUNTCONFIGURATION.removeCreditChannelConfigById", configId);
	}

	@Override
	public Object countCreditChannelConfig(String departmentId){
		return baseDao.queryUnique("DISCOUNTCONFIGURATION.countCreditChannelConfig", departmentId);
	}

	@Override
	public String queryIsZhxindai(Long creditapplicationId) {
		return (String) baseDao.queryUnique("DISCOUNTCONFIGURATION.queryIsZhxindai", creditapplicationId);
	}
}
