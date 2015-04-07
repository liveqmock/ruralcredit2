package com.creditease.rc.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IComprehensiveCreditDao;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.vo.OrderInfoList;

@Repository
public class ComprehensiveCreditDao implements IComprehensiveCreditDao {

	@Resource
	private IBaseDao baseDao;

	@Override
	public List<OrderInfoList> getOrderInfoList(Map<String, String> queryMap) {
		// TODO Auto-generated method stub
		return (List<OrderInfoList>) baseDao.queryList("COMPREHENSIVECREDIT.queryOrderInfo", queryMap);
	}
}
