package com.creditease.rc.service;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.RollingForecast;
import com.creditease.rc.vo.RollingForecastHistory;
import com.creditease.rc.vo.RollingForecastMessage;
import com.creditease.rc.vo.RollingForecastVo;

public interface IRollingForecastSerivce {

	public boolean dynamicInsert(RollingForecast rollingForecast);

	public boolean dynamicUpdate(RollingForecast rollingForecast);

	public Map<String, RollingForecastVo> queryForRollingForecastVoMap(int year, int month, String areaDepartmentIds);

	public RollingForecastMessage saveRollingForecast(RollingForecast rollingForecast);

	public List<Long> rollingForecastIdList(Map<String, String> queryMap);

	public Map<String, RollingForecastHistory> queryRollingForecastMapForHistory(Map<String, String> queryMap);

	public List<RollingForecast> queryRollingForecastEditHistory(RollingForecast queryRollingForecast);

	public Map<String, RollingForecast> rorecastHistoryDetailMap(Integer year, Integer month, String type,
			String areaDepartmentIds);

}
