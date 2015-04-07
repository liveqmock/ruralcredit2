package com.creditease.rc.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IRollingForecastDao;
import com.creditease.rc.domain.RollingForecast;
import com.creditease.rc.service.IRollingForecastSerivce;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.vo.RollingForecastHistory;
import com.creditease.rc.vo.RollingForecastMessage;
import com.creditease.rc.vo.RollingForecastVo;

@Service
public class RollingForecastSerivce implements IRollingForecastSerivce {

	@Resource
	private IRollingForecastDao rollingForecastDao;

	@Override
	public boolean dynamicInsert(RollingForecast rollingForecast) {
		// TODO Auto-generated method stub
		return rollingForecastDao.dynamicInsert(rollingForecast);
	}

	@Override
	public boolean dynamicUpdate(RollingForecast rollingForecast) {
		// TODO Auto-generated method stub
		return rollingForecastDao.dynamicUpdate(rollingForecast);
	}

	@Override
	public Map<String, RollingForecastVo> queryForRollingForecastVoMap(int year, int month, String areaDepartmentIds) {
		// TODO Auto-generated method stub
		Map<String, String> queryMap = new HashMap<String, String>();
		Map<String, RollingForecastVo> rollingForecastMap = new HashMap<String, RollingForecastVo>();
		queryMap.put("year", String.valueOf(year));
		queryMap.put("month", String.valueOf(month));
		queryMap.put("areaDepartmentIds", areaDepartmentIds);
		List<RollingForecastVo> rollingForecastVos = rollingForecastDao.queryRollingForecastVoForInit(queryMap);
		rollingForecastMap = transforMap(rollingForecastVos);
		return rollingForecastMap;
	}

	private Map<String, RollingForecastVo> transforMap(List<RollingForecastVo> rollingForecastVos) {
		Map<String, RollingForecastVo> map = new HashMap<String, RollingForecastVo>();
		if (CommonUtil.isNotEmpty(rollingForecastVos)) {
			for (RollingForecastVo rollingForecastVo : rollingForecastVos) {
				map.put(rollingForecastVo.getAreaDepartmentId() + "|" + rollingForecastVo.getType(), rollingForecastVo);
			}
		}
		return map;
	}

	@Override
	public RollingForecastMessage saveRollingForecast(RollingForecast rollingForecast) {
		// TODO Auto-generated method stub
		RollingForecastMessage rollingForecastMessage = new RollingForecastMessage();
		// 先查询有多少预测年月类型和部门id相同的数据
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("year", String.valueOf(rollingForecast.getYear()));
		queryMap.put("month", String.valueOf(rollingForecast.getMonth()));
		queryMap.put("type", rollingForecast.getType());
		queryMap.put("areaDepartmentId", String.valueOf(rollingForecast.getAreaDepartmentId()));
		List<Long> rollingForecastIdList = rollingForecastIdList(queryMap);
		// 将这些数据置成历史
		boolean suscc = rollingForecastDao.batchUpdateHisitoryFlag(rollingForecastIdList);
		// 之后插入新的预测数据
		boolean susccess = rollingForecastDao.dynamicInsert(rollingForecast);
		rollingForecastMessage.setSuccess(true);
		rollingForecastMessage.setMsg("数据保存成功！");

		rollingForecastMessage.setRollingForecastId(rollingForecast.getRollingForecastId());
		rollingForecastMessage.setYear(rollingForecast.getYear());
		rollingForecastMessage.setMonth(rollingForecast.getMonth());
		rollingForecastMessage.setType(rollingForecast.getType());
		rollingForecastMessage.setAreaDepartmentId(rollingForecast.getAreaDepartmentId());
		rollingForecastMessage.setAreaDepartmentName(rollingForecast.getAreaDepartmentName());
		rollingForecastMessage.setFirstMonth(rollingForecast.getFirstMonth());
		rollingForecastMessage.setSecondMonth(rollingForecast.getSecondMonth());
		rollingForecastMessage.setThirdMonth(rollingForecast.getThirdMonth());
		rollingForecastMessage.setOperateTime(rollingForecast.getOperateTime());
		rollingForecastMessage.setOperatorId(rollingForecast.getOperatorId());
		rollingForecastMessage.setOperatorName(rollingForecast.getOperatorName());
		return rollingForecastMessage;
	}

	@Override
	public List<Long> rollingForecastIdList(Map<String, String> queryMap) {
		// TODO Auto-generated method stub
		return rollingForecastDao.rollingForecastIdList(queryMap);
	}

	@Override
	public Map<String, RollingForecastHistory> queryRollingForecastMapForHistory(Map<String, String> queryMap) {
		// TODO Auto-generated method stub
		List<RollingForecastHistory> forecastHistories = rollingForecastDao.queryRollingForecastForHistory(queryMap);
		return transforHistoryMap(forecastHistories);
	}

	private Map<String, RollingForecastHistory> transforHistoryMap(List<RollingForecastHistory> forecastHistories) {
		Map<String, RollingForecastHistory> map = new HashMap<String, RollingForecastHistory>();
		if (CommonUtil.isNotEmpty(forecastHistories)) {
			for (RollingForecastHistory forecastHistory : forecastHistories) {
				map.put(forecastHistory.getThisYearAndMonth() + "|" + forecastHistory.getType(), forecastHistory);
			}
		}
		return map;
	}

	@Override
	public List<RollingForecast> queryRollingForecastEditHistory(RollingForecast queryRollingForecast) {
		// TODO Auto-generated method stub
		return rollingForecastDao.queryRollingForecastEditHistory(queryRollingForecast);
	}

	@Override
	public Map<String, RollingForecast> rorecastHistoryDetailMap(Integer year, Integer month, String type,
			String areaDepartmentIds) {
		// TODO Auto-generated method stub
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("year", String.valueOf(year));
		queryMap.put("month", String.valueOf(month));
		queryMap.put("type", type);
		queryMap.put("areaDepartmentIds", areaDepartmentIds);
		List<RollingForecast> rollingForecasts = rollingForecastDao.rorecastHistoryDetailList(queryMap);

		return transforHistoryDetailMap(rollingForecasts);
	}

	private Map<String, RollingForecast> transforHistoryDetailMap(List<RollingForecast> rollingForecasts) {
		Map<String, RollingForecast> map = new HashMap<String, RollingForecast>();
		if (CommonUtil.isNotEmpty(rollingForecasts)) {
			for (RollingForecast rollingForecast : rollingForecasts) {
				map.put(String.valueOf(rollingForecast.getAreaDepartmentId()), rollingForecast);
			}
		}
		return map;

	}
}
