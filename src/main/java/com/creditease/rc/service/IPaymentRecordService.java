package com.creditease.rc.service;

import java.util.List;
import java.util.Vector;

import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.PaymentRecord;

/**
 * 
 * @author Administrator
 *
 */
public interface IPaymentRecordService {
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
     * 根据ID查信贷审批单ID
     * @param integerVector ID集合
     * @return List<CreditApplication>
     */
	List<CreditApplication> selectPaymentRecord(Vector<Integer> integerVector);

}