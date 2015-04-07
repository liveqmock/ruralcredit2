package com.creditease.rc.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IRepairSysDataDao;
import com.creditease.rc.framework.dao.IBaseDao;

@Repository
public class RepairSysDataDao implements IRepairSysDataDao {

	@Resource
	private IBaseDao baseDao;
	@Override
	public boolean repairBusNum(String oldBusNum, String newNusNum)
			throws Exception {
		boolean result = false;
		Map<String,String> map = new HashMap<String, String>();
		map.put("oldBusNum", oldBusNum);
		map.put("newBusNum", newNusNum);
		if(null != map && map.size()>0){
			int count = baseDao.update("repairsysData.updateBusNumByOld", map);
			if(count>0){
				result = true;
			}
		}
		return result;
	}

	@Override
	public boolean repairBusId(String caId, String newNusNum) throws Exception {
		boolean result = false;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("caId", Integer.valueOf(caId));
		map.put("newBusNum", newNusNum);
		if(null != map && map.size()>0){
			int count = baseDao.update("repairsysData.updateBusNumById", map);
			if(count>0){
				result = true;
			}
		}
		return result;
	}

	@Override
	public int queryCountByOld(String oldBusNum) throws Exception {
		//查询是否有重复数据
		int num = (Integer) baseDao.queryUnique("repairsysData.queryCountByOld", oldBusNum);
		return num;
	}

}
