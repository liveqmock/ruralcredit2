package com.creditease.rc.service.impl;

import java.util.List;
import java.util.Vector;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IPaymentRecordDao;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.PaymentRecord;
import com.creditease.rc.service.IPaymentRecordService;
/**
 * 
 * @author Administrator
 *
 */
@Service
public class PaymentRecordService implements IPaymentRecordService {
	
	@Resource
	private IPaymentRecordDao paymentRecord;
    @Override
    public void insertPaymentRecord(PaymentRecord record) {
    	paymentRecord.insertPaymentRecord(record);
    }

    @Override
    public int updatePaymentRecord(PaymentRecord record) {
        int rows = paymentRecord.updatePaymentRecord(record);
        return rows;
    }

    @Override
    public PaymentRecord selectPaymentRecord(PaymentRecord record) {
        PaymentRecord tmp = (PaymentRecord) paymentRecord.selectPaymentRecord(record);
        return tmp;
    }

	@Override
	public List<CreditApplication> selectPaymentRecord(
			Vector<Integer> integerVector) {
        List<CreditApplication>creditApplicationList=paymentRecord.selectPaymentRecord(integerVector);
		return creditApplicationList;
	}
    
}