package com.creditease.rc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IPayInfoDao;
import com.creditease.rc.domain.PayInfo;
import com.creditease.rc.service.IPayInfoService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class PayInfoService implements IPayInfoService{
	
	@Resource
	private IPayInfoDao payInfoDao;
	@Override
	public Integer insertPayInfo(PayInfo payInfo) {
		return payInfoDao.insertPayInfo(payInfo);
	}
	@Override
	public PayInfo selectPayInfo(PayInfo payInfo) {
		return payInfoDao.selectPayInfo(payInfo);
	}

}
