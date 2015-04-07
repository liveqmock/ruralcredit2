package com.creditease.rc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IRepairSysDataDao;
import com.creditease.rc.service.IRepairSysDataService;

@Service
public class RepairSysDataService implements IRepairSysDataService {

	@Resource
	private IRepairSysDataDao repairSysDataDao;
	
	public boolean repairBusNum(String caId, String newNusNum, String repairType)
			throws Exception {
		boolean result = true;
		if("0".equals(repairType)){//ById
			result = repairSysDataDao.repairBusId(caId, newNusNum);
		}else if("1".equals(repairType)) {//ByNum
			result = repairSysDataDao.repairBusNum(caId, newNusNum);
		}
		return result;
	}

	@Override
	public int queryCountByOld(String oldBusNum) throws Exception {
		int num = repairSysDataDao.queryCountByOld(oldBusNum);
		return num;
	}

	@Override
	public boolean repairBusNum(String oldBusNum, String newNusNum)
			throws Exception {
		boolean result = repairSysDataDao.repairBusNum(oldBusNum, newNusNum);
		return result;
	}

	@Override
	public boolean repairBusId(String caId, String newNusNum) throws Exception {
		boolean result = repairSysDataDao.repairBusId(caId, newNusNum);
		return result;
	}

}
