package com.creditease.rc.service.impl;

import com.creditease.rc.dao.ICreditCoBorrowerDao;
import com.creditease.rc.domain.CreditCoBorrower;
import com.creditease.rc.service.ICreditCoBorrowerService;
import com.creditease.rc.util.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 14-10-22
 * Time: 下午1:47
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CreditCoBorrowerService implements ICreditCoBorrowerService {
    @Resource
    private ICreditCoBorrowerDao  creditCoBorrowerDao;
    @Override
    public CreditCoBorrower queryCreditCoBorrowerInfoByBorrowerServiceAppId(String borrowerServiceAppId) throws Exception {
        return creditCoBorrowerDao.queryCreditCoBorrowerInfoByBorrowerServiceAppId(borrowerServiceAppId);
    }


    @Override
    public int updateCreditCoBorrowerInfo(CreditCoBorrower creditCoBorrower) throws Exception {
        return  creditCoBorrowerDao.updateCreditCoBorrowerInfoByBorrowerServiceAppId(creditCoBorrower);
    }

    @Override
    public int insertOrUpdateCreditCoBorrowerInfo(CreditCoBorrower creditCoBorrower) throws Exception {
            CreditCoBorrower coBorrower =  creditCoBorrowerDao.queryCreditCoBorrowerInfoByBorrowerServiceAppId(String.valueOf(creditCoBorrower.getBorrowerServiceAppId()));

        if(coBorrower != null ){
          return  creditCoBorrowerDao.updateCreditCoBorrowerInfoByBorrowerServiceAppId(creditCoBorrower);
        } else {
            return  creditCoBorrowerDao.insertCreditCoBorrowerInfo(creditCoBorrower);
        }
    }
}
