package com.creditease.rc.dao.impl;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IPayplatformNoticeDao;
import com.creditease.rc.domain.PayplatformNotice;
import com.creditease.rc.framework.dao.impl.BaseDao;
/**
 *   
 * @author Administrator
 *
 */
@Repository
public class PayplatformNoticeDao extends BaseDao implements IPayplatformNoticeDao{


    @Override
    public void insertPayplatformNotice(PayplatformNotice record) {
    	this.insertObject("PAYPLATFORM_NOTICE.insertPayplatformNotice", record);
    }

    @Override
    public int updatePayplatformNotice(PayplatformNotice record) {
        int rows = this.update("PAYPLATFORM_NOTICE.updatePayplatformNotice", record);
        return rows;
    }

    @Override
    public PayplatformNotice selectByPrimaryKey(Integer payplatformNoticeId) {
        PayplatformNotice key = new PayplatformNotice();
        key.setPayplatformNoticeId(payplatformNoticeId);
        PayplatformNotice record = (PayplatformNotice)this.queryUnique("PAYPLATFORM_NOTICE.selectPayplatformNotice", key);
        return record;
    }

}