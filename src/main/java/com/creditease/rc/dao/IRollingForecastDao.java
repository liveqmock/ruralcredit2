package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.RollingForecast;
import com.creditease.rc.vo.RollingForecastHistory;
import com.creditease.rc.vo.RollingForecastVo;

public interface IRollingForecastDao {

	public boolean dynamicInsert(RollingForecast rollingForecast);

	public boolean dynamicUpdate(RollingForecast rollingForecast);

	public List<RollingForecastVo> queryRollingForecastVoForInit(Map<String, String> queryMap);

	public List<Long> rollingForecastIdList(Map<String, String> queryMap);

	public boolean batchUpdateHisitoryFlag(List<Long> rollingForecastIdList);

	public List<RollingForecastHistory> queryRollingForecastForHistory(Map<String, String> queryMap);

	public List<RollingForecast> queryRollingForecastEditHistory(RollingForecast queryRollingForecast);

	public List<RollingForecast> rorecastHistoryDetailList(Map<String, String> queryMap);
}
