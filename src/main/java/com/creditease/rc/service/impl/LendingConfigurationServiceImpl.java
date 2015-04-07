package com.creditease.rc.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.LendingConfigurationDAO;
import com.creditease.rc.domain.LendingConfiguration;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.LendingConfigurationService;
@Service
public class LendingConfigurationServiceImpl implements
		LendingConfigurationService {

	@Resource
	private LendingConfigurationDAO lendingConfigurationDAO;
	@Override
	public Pagination selectAll(Pagination pagination, LendingConfiguration lendingConfiguration) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("lendingConfiguration", lendingConfiguration);
		return lendingConfigurationDAO.selectAll(pagination, map);
	}
	@Override
	public Long insert(LendingConfiguration record) {
		int selectIsExist = lendingConfigurationDAO.selectIsExist(record);
		if(selectIsExist <=0){
			
			return lendingConfigurationDAO.insert(record);
		}else{
			if(record.getTrustLending().equals("是")){
				record.setTrustLending("0");
			}
			if(record.getTrustLending().equals("否")){
				record.setTrustLending("1");
			}
			if(record.getDebentureTransfer().equals("是")){
				record.setDebentureTransfer("0");
			}
			if(record.getDebentureTransfer().equals("否")){
				record.setDebentureTransfer("1");
			}
			return (long) lendingConfigurationDAO.updateLendingConfiguration(record);
		}
	}
	@Override
	public int removeLendingConfiguration(Long lendingConfigurationId) {
		return lendingConfigurationDAO.removeLendingConfiguration(lendingConfigurationId);
	}

}
