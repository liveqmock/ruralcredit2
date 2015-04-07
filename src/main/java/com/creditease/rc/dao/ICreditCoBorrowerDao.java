package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.CreditCoBorrower;
import com.creditease.rc.framework.dao.IBaseDao;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 14-10-22
 * Time: 下午1:43
 * To change this template use File | Settings | File Templates.
 */
public interface ICreditCoBorrowerDao extends IBaseDao {
    public CreditCoBorrower queryCreditCoBorrowerInfoByBorrowerServiceAppId(String borrowerServiceAppId) throws Exception;
    public int updateCreditCoBorrowerInfoByBorrowerServiceAppId(CreditCoBorrower creditCoBorrower) throws Exception;
    public int insertCreditCoBorrowerInfo(CreditCoBorrower creditCoBorrower) throws  Exception;
    /**
     * 根据借款服务申请ID查询共借人信息
     * @param borrowerServiceAppId
     * @return
     */
    public CreditCoBorrower queryCoBOrrowerInfo(Integer borrowerServiceAppId);
}
