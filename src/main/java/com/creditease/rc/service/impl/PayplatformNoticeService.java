package com.creditease.rc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IPayplatformNoticeDao;
import com.creditease.rc.domain.PayplatformNotice;
import com.creditease.rc.service.IPayplatformNoticeService;
/**
 *   
 * @author Administrator
 *
 */
@Service
public class PayplatformNoticeService implements IPayplatformNoticeService{

	@Resource
	private IPayplatformNoticeDao payplatformNoticeDao; 
    @Override
    public void insertPayplatformNotice(PayplatformNotice record) {
    	payplatformNoticeDao.insertPayplatformNotice(record);
    }

    @Override
    public int updatePayplatformNotice(PayplatformNotice record) {
        int rows = payplatformNoticeDao.updatePayplatformNotice(record);
        return rows;
    }

    @Override
    public PayplatformNotice selectByPrimaryKey(Integer payplatformNoticeId) {
        PayplatformNotice record = (PayplatformNotice)this.selectByPrimaryKey(payplatformNoticeId);
        return record;
    }

}