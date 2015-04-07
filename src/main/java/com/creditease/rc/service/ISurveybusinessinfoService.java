package com.creditease.rc.service;

import com.creditease.rc.domain.Surveybusinessinfo;

/**
 * 
 * @author haoqiang
 * 
 */
public interface ISurveybusinessinfoService {
	/**
	 * 
	 * @param surveybusinessinfo 经营情况对象
	 * @return 布尔类型
	 */
	boolean insersurveybusinessinfo(Surveybusinessinfo surveybusinessinfo);

	/**
	 * 
	 * @param borrowerSurveyId 上述合计主键
	 * @return 经营情况对象
	 */
	Surveybusinessinfo searchSurveybusinessinfo(Long borrowerSurveyId);

	/**
	 * 
	 * @param surveybusinessinfo 经营情况对象
	 * @return 布尔类型
	 */
	boolean updateSurveyBusinessInfo(Surveybusinessinfo surveybusinessinfo);

	/**
	 * 
	 * @param surveybusinessinfo 经营情况对象
	 * @return 布尔类型
	 */
	boolean addSurveyBusinessInfo(Surveybusinessinfo surveybusinessinfo);

	/**
	 * 
	 * @param surveybusinessinfo 经营情况对象
	 * @return 布尔类型
	 */
	boolean insertSurveyBusinessInfo(Surveybusinessinfo surveybusinessinfo);

	/**
	 * 保存经营情况操作
	 * 
	 * @param surveybusinessinfo 经营情况对象
	 * @return 经营情况对象
	 */
	Surveybusinessinfo saveSurveybusinessinfo(Surveybusinessinfo surveybusinessinfo);

}
