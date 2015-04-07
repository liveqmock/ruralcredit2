package com.creditease.rc.dao;

import java.util.List;
import java.util.Vector;

import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.PaymentRecord;

/**
 * 
 * @author Administrator
 *
 */
public interface IPaymentRecordDao {
    /**
     * 
     * @param record 
     */
    void insertPaymentRecord(PaymentRecord record);


    /**
     * 
     * @param record 
     * @return int 
     */
    int updatePaymentRecord(PaymentRecord record);

    /**
     * 
     * @param paymentRecord 
     * @return selectByPrimaryKey 
     */
    PaymentRecord selectPaymentRecord(PaymentRecord paymentRecord);
    
    /**
     * @author zhangman
     * 查询付款记录list
     * @param paymentRecord 付款记录对象
     * @return 付款list
     */
    List<PaymentRecord> selectPaymentRecords(PaymentRecord paymentRecord);

    /**
     * 根据ID查信贷审批单ID
     * @param integerVector ID集合
     * @return List<CreditApplication>
     */
	List<CreditApplication> selectPaymentRecord(Vector<Integer> integerVector);

}