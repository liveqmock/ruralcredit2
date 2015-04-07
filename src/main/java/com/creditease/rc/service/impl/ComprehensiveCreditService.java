package com.creditease.rc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IComprehensiveCreditDao;
import com.creditease.rc.service.IComprehensiveCreditService;
import com.creditease.rc.vo.OrderInfoList;

@Service
public class ComprehensiveCreditService implements IComprehensiveCreditService {

	@Resource
	private IComprehensiveCreditDao comprehensiveCreditDao;

	@Override
	public List<OrderInfoList> getOrderInfoList(Map<String, String> queryMap) {
		// TODO Auto-generated method stub
		return comprehensiveCreditDao.getOrderInfoList(queryMap);
	}
}
