package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.BorrowerService;
import com.creditease.rc.domain.CreditCoBorrower;
import com.creditease.rc.domain.CustomerBasicInfo;
import com.creditease.rc.framework.dao.IBaseDao;

/**
 * 
 * @author zhangman
 * Copyright: Copyright (c) 2012
 * Company: 普信恒业科技发展（北京）有限公司
 */
public interface IBorrowerServiceDAO extends IBaseDao{
    /**
     * 
     * @param record 借款申请
     * @return id
     * 
     */
    long insert(BorrowerService record);

   /**
    * 更新
    * @param record 借款申请对象
    * @return int
    */
    int updateByPrimaryKey(BorrowerService record);

    /**
     * 跟新
     * @param record 借款申请
     * @return 影响的行数
     */
    int updateByPrimaryKeySelective(BorrowerService record);

   /**
    * 查询
    * @param borrowerServiceId 借款申请id
    * @return 借款申请
    */
    BorrowerService selectByPrimaryKey(Long borrowerServiceId);
    /**
     * 查询
     * @param businessNumber 借款申请编号
     * @return 借款申请
     */
    BorrowerService queryBorrowerServiceInfoByBussinessNumber(String businessNumber);
    /**
     * 查询
     * @param businessNumber 借款申请编号
     * @return
     */
       List<BorrowerService> queryGuarantyListInfoByBussinessNumber(String businessNumber);
    /**
     * 查询
     * @param businessNumber 借款申请编号
     * @return
     */
   CreditCoBorrower queryCreditCoBorrowerInfoByBussinessNumber(String businessNumber);


    /**
    /**
	 * 
	 * @param borrowerServiceId BorrowerServiceId
	 * @return int
	 */
    int deleteByPrimaryKey(Long borrowerServiceId);
    
    /**
     * 查询所有借款申请
     * @param borrowerService 借款申请
     * @return list
     */
    public List<Map> select(BorrowerService borrowerService);

    /**
     *    是否试点营业部
     * @param depId
     * @return
     */
    public boolean isSpecDep(Long depId);

    /**
     * 更新借款人信息
     * @param borrowerService
     */
    public void updateBorrowerServiceIdInfo(BorrowerService borrowerService);

    /**
     * 更新担保人信息
     * @param borrowerServices
     */
    public void updateGuarantyBorrowerServiceIdInfo(List<BorrowerService> borrowerServices);

    /**
     *  更新客户信息
     * @param customerBasicInfo
     */
    public void updateCutomerIdInfo(CustomerBasicInfo customerBasicInfo);

    /**
     * 更新共借人信息
     * @param creditCoBorrower
     */
    public void updateCreditCoBorrowerIdInfo(CreditCoBorrower creditCoBorrower);

    /**
     *  批量更新客户信息
     * @param customerBasicInfoList
     */
    public void updateCutomerIdListInfo(List<CustomerBasicInfo> customerBasicInfoList);
}