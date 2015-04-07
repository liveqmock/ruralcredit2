package com.creditease.rc.service;

import com.creditease.rc.vo.SurveySecondVo;

/**
 * 
 * @author haoqiang
 * 
 */
public interface ISurveySecondService {
	/**
	 * 
	 * @param surveySecondVo 入户2大对象
	 * @return 布尔类型
	 */
	boolean saveSurveySecondTable(SurveySecondVo surveySecondVo);

	/**
	 * 
	 * @param borrowerSurveyId 上述合计ID
	 * @return 入户2对象
	 */
	SurveySecondVo searchSurveySecondTable(int borrowerSurveyId);

	/**
	 * 
	 * @param surveySecondVo 入户2对象
	 * @return 布尔类型
	 */
	boolean updateSurveySecondTable(SurveySecondVo surveySecondVo);

	/**
	 * 
	 * @param surveySecondVo 入户2对象
	 * @return 布尔类型
	 */
	boolean insertSurveySecondVo(SurveySecondVo surveySecondVo);

	/**
	 * 
	 * @param surveySecondVo 入户2对象
	 * @return 布尔类型
	 */
	boolean saveSurveySecondVo(SurveySecondVo surveySecondVo);

	/**
	 * 删除入户调查表2和3的接口
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 布尔类型
	 */
	boolean deleteSurveySecondVo(Integer borrowerServiceAppId);

	/**
	 * 
	 * @param borrowerServiceAppId sd
	 * @return sd
	 */
	SurveySecondVo searchSurveySecondVo(Integer borrowerServiceAppId);

}
