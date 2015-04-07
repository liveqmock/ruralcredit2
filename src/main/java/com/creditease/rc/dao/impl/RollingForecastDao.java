package com.creditease.rc.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IRollingForecastDao;
import com.creditease.rc.domain.RollingForecast;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.vo.RollingForecastHistory;
import com.creditease.rc.vo.RollingForecastVo;

@Repository
public class RollingForecastDao implements IRollingForecastDao {

	@Resource
	private IBaseDao baseDao;

	@Override
	public boolean dynamicInsert(RollingForecast rollingForecast) {
		// TODO Auto-generated method stub
		baseDao.insert("ROLLINGFORECAST.dynamicInsert", rollingForecast);
		return true;
	}

	@Override
	public boolean dynamicUpdate(RollingForecast rollingForecast) {
		// TODO Auto-generated method stub
		baseDao.update("ROLLINGFORECAST.dynamicUpdate", rollingForecast);
		return true;
	}

	@Override
	public List<RollingForecastVo> queryRollingForecastVoForInit(Map<String, String> queryMap) {
		// TODO Auto-generated method stub
		return (List<RollingForecastVo>) baseDao.queryList("ROLLINGFORECAST.queryRollingForecastForInit", queryMap);
	}

	@Override
	public List<Long> rollingForecastIdList(Map<String, String> queryMap) {
		// TODO Auto-generated method stub
		return (List<Long>) baseDao.queryList("ROLLINGFORECAST.rollingForecastIdList", queryMap);
	}

	@Override
	public boolean batchUpdateHisitoryFlag(List<Long> rollingForecastIdList) {
		// TODO Auto-generated method stub
		baseDao.batchUpdate("ROLLINGFORECAST.updateHistoryFlag", rollingForecastIdList);
		return true;
	}

	@Override
	public List<RollingForecastHistory> queryRollingForecastForHistory(Map<String, String> queryMap) {
		// TODO Auto-generated method stub
		return (List<RollingForecastHistory>) baseDao.queryList("ROLLINGFORECAST.queryRollingForecastForHistory",
				queryMap);
	}

	@Override
	public List<RollingForecast> queryRollingForecastEditHistory(RollingForecast queryRollingForecast) {
		// TODO Auto-generated method stub
		return (List<RollingForecast>) baseDao.queryList("ROLLINGFORECAST.queryRollingForecastEditHistory",
				queryRollingForecast);
	}

	@Override
	public List<RollingForecast> rorecastHistoryDetailList(Map<String, String> queryMap) {
		// TODO Auto-generated method stub
		return (List<RollingForecast>) baseDao.queryList("ROLLINGFORECAST.rorecastHistoryDetailList", queryMap);
	}

}
