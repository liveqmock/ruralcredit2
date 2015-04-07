package com.creditease.rc.dao.impl;

import com.creditease.rc.dao.ICreditCoBorrowerDao;
import com.creditease.rc.domain.CreditCoBorrower;
import com.creditease.rc.framework.dao.impl.BaseDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 14-10-22
 * Time: 下午1:45
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CreditCoBorrowerDaoImpl extends BaseDao implements ICreditCoBorrowerDao {
    @Resource
    private BaseDao baseDao;
    @Override
    public CreditCoBorrower queryCreditCoBorrowerInfoByBorrowerServiceAppId(String borrowerServiceAppId) throws Exception {
        List<CreditCoBorrower> creditCoBorrowerList = new ArrayList<CreditCoBorrower>();
        creditCoBorrowerList = ( List<CreditCoBorrower>)baseDao.queryList("creditCoBOrrower.queryCreditCoBorrowerInfoByBorrowerServiceAppId",borrowerServiceAppId) ;
        CreditCoBorrower creditCoBorrower = null;
        if(creditCoBorrowerList.size() >0){
            creditCoBorrower = creditCoBorrowerList.get(0);
        }
          //  return (CreditCoBorrower)baseDao.queryUnique("creditCoBOrrower.queryCreditCoBorrowerInfoByBorrowerServiceAppId",borrowerServiceAppId);
        return   creditCoBorrower;
    }

    @Override
    public    int updateCreditCoBorrowerInfoByBorrowerServiceAppId(CreditCoBorrower creditCoBorrower) throws Exception {
        return baseDao.update("creditCoBOrrower.updateCreditCoBorrowerInfoByBorrowerServiceAppId",creditCoBorrower);
    }

    @Override
    public int insertCreditCoBorrowerInfo(CreditCoBorrower creditCoBorrower) throws Exception {
          baseDao.insertObject("creditCoBOrrower.creditCoBOrrower_insert",creditCoBorrower);
        return 1;
    }

	@Override
	public CreditCoBorrower queryCoBOrrowerInfo(
			Integer borrowerServiceAppId) {
		return (CreditCoBorrower) queryUnique("creditCoBOrrower.queryCoBOrrowerInfo", borrowerServiceAppId);
	}


}
