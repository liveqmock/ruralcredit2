package com.creditease.rc.dao.impl;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IPayInfoDao;
import com.creditease.rc.domain.PayInfo;
import com.creditease.rc.framework.dao.impl.BaseDao;
/**
 * 
 * @author Administrator
 *
 */
@Repository
public class PayInfoDao extends BaseDao implements IPayInfoDao{

	@Override
	public Integer insertPayInfo(PayInfo payInfo) {
		return (Integer) this.insertObject("PAYINFO.insertPayInfo", payInfo);
	}
	
	@Override
	public PayInfo selectPayInfo(PayInfo payInfo){
		return (PayInfo)this.queryUnique("PAYINFO.selectPayInfo", payInfo);
	}

}
