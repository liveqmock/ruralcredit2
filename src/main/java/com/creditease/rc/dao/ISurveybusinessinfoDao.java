package com.creditease.rc.dao;

import java.util.List;
import com.creditease.rc.domain.Surveybusinessinfo;
import com.creditease.rc.framework.dao.IBaseDao;

/**
 * 
 * @author haoqiang
 * 
 */
public interface ISurveybusinessinfoDao extends IBaseDao {
	/**
	 * 
	 * @param surveybusinessinfo 经营情况对象
	 */
	void insersurveybusinessinfo(Surveybusinessinfo surveybusinessinfo);

	/**
	 * 
	 * @param borrowerSurveyId 上述合计主键
	 * @return 经营情况对象
	 */
	Surveybusinessinfo querySelectSurveybusinessinfo(int borrowerSurveyId);

	/**
	 * 
	 * @param surveybusinessinfo 经营情况对象
	 */
	void batchUpdateSurveyBusinessInfo(Surveybusinessinfo surveybusinessinfo);
	/**
	 * 
	 * @param borrowerServiceAppId
	 * @return
	 */
	public List<Surveybusinessinfo> selectByBorrowerServiceAppId(Long borrowerServiceAppId);

}
