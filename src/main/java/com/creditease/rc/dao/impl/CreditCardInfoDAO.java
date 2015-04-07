package com.creditease.rc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.ICreditCardInfoDAO;
import com.creditease.rc.domain.CreditCardInfo;
import com.creditease.rc.framework.dao.impl.BaseDao;
/**
 * 
 * @author zhangman
 *
 */
@Repository
public class CreditCardInfoDAO implements ICreditCardInfoDAO {

	@Resource
	private BaseDao baseDao;

	@Override
	public int addCreditCardInfo(CreditCardInfo creditCardInfo) {
		return (Integer) baseDao.insertObject("CREDITCARD.insert", creditCardInfo);
	}

	@Override
	public void updateCardInfo(CreditCardInfo creditCardInfo) {
		baseDao.update("CREDITCARD.update", creditCardInfo);
	}

	@Override
	public List<CreditCardInfo> selectCreditCardInfo(int borrowerServiceAppId) {
		return (List<CreditCardInfo>) baseDao.queryList("CREDITCARD.selectAll",
				borrowerServiceAppId);
	}

	@Override
	public int deleteCreditCradInfo(int borrowerServiceAppId) {
		// TODO Auto-generated method stub
		return baseDao.delete("CREDITCARD.deleteByBorrowerServiceAppId",
				borrowerServiceAppId);
	}

	@Override
	public CreditCardInfo selectByBorrowerServiceAppId(int borrowerServiceAppId) {
		return (CreditCardInfo) baseDao.queryUnique("CREDITCARD.selectAll",
				borrowerServiceAppId);
	}
}
