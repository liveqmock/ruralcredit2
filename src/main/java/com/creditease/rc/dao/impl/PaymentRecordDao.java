package com.creditease.rc.dao.impl;

import java.util.List;
import java.util.Vector;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IPaymentRecordDao;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.PaymentRecord;
import com.creditease.rc.framework.dao.impl.BaseDao;
/**
 * 
 * @author Administrator
 *
 */
@Repository
public class PaymentRecordDao extends BaseDao implements IPaymentRecordDao {

    @Override
    public void insertPaymentRecord(PaymentRecord record) {
       	this.insertObject("PAYMENTRECORD.insertPaymentRecord", record);
    }

    @Override
    public int updatePaymentRecord(PaymentRecord record) {
        int rows = this.update("PAYMENTRECORD.updatePaymentRecord", record);
        return rows;
    }

    @Override
    public PaymentRecord selectPaymentRecord(PaymentRecord paymentRecord) {
        PaymentRecord record =  (PaymentRecord) this.queryUnique("PAYMENTRECORD.selectPaymentRecord", paymentRecord);
        return record;
    }
    
    @Override
    public List<PaymentRecord> selectPaymentRecords(PaymentRecord paymentRecord) {
    	List<PaymentRecord> record = (List<PaymentRecord>) this.queryList("PAYMENTRECORD.selectPaymentRecord", paymentRecord);
    	return record;
    }

	@Override
	public List<CreditApplication> selectPaymentRecord(
			Vector<Integer> integerVector) {
		 List<CreditApplication>creditApplicationList=
			 (List<CreditApplication>) this.queryList("PAYMENTRECORD.selectPaymentRecordByList", integerVector);
		 return creditApplicationList;
	}
}