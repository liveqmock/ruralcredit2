package com.creditease.rc.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.ICreditOrganizationDAO;
import com.creditease.rc.domain.CreditOrganization;
import com.creditease.rc.framework.dao.impl.BaseDao;

/**
 * 
 * @author zhangman
 * 
 */
@Repository
public class CreditOrganizationDAO implements ICreditOrganizationDAO {

	@Resource
	private BaseDao baseDao;

	@Override
	public void addCreditOrganization(List<CreditOrganization> creditOrganizations) {
		baseDao.batchInsert("CREDITORGANYZATION.insert", creditOrganizations);
	}

	@Override
	public int addCreditOrganization(CreditOrganization creditOrganization) {
		return (Integer) baseDao.insertObject("CREDITORGANYZATION.insert",
				creditOrganization);
	}

	@Override
	public void updateCreditOrganization(CreditOrganization creditOrganization) {
		baseDao.update("CREDITORGANYZATION.update", creditOrganization);
	}

	@Override
	public void batchUpdateCreditOrganization(List<CreditOrganization> creditOrganizations) {
		baseDao.batchUpdate("CREDITORGANYZATION.update", creditOrganizations);
	}

	@Override
	public List<CreditOrganization> selectCreditOrganization(int creditCardInfoId)
			throws ParseException {
		List<CreditOrganization> creditOrganizationList = new ArrayList<CreditOrganization>();
		creditOrganizationList = (List<CreditOrganization>) baseDao.queryList(
				"CREDITORGANYZATION.selectAll", creditCardInfoId);
		return creditOrganizationList;
	}

	@Override
	public int deleteCreditOrganization(int creditCardInfoId) {
		return baseDao
				.delete("CREDITORGANYZATION.deleteByCreditCardId", creditCardInfoId);
	}

	@Override
	public int deleteById(int creditOrgId) {
		return baseDao.delete("CREDITORGANYZATION.deleteById", creditOrgId);
	}
}
