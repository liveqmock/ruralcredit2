package com.creditease.rc.service;

import com.creditease.rc.domain.CreditCoBorrower;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 14-10-22
 * Time: 下午1:46
 * To change this template use File | Settings | File Templates.
 */
public interface ICreditCoBorrowerService {

    public CreditCoBorrower queryCreditCoBorrowerInfoByBorrowerServiceAppId(String borrowerServiceAppId) throws  Exception;
    public int updateCreditCoBorrowerInfo(CreditCoBorrower creditCoBorrower) throws Exception;
    public  int insertOrUpdateCreditCoBorrowerInfo(CreditCoBorrower creditCoBorrower) throws Exception;
}
