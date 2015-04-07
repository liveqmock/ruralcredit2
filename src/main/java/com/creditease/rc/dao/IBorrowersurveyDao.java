package com.creditease.rc.dao;

import com.creditease.rc.domain.Borrowersurvey;
import com.creditease.rc.framework.dao.IBaseDao;

/**
 * 
 * @author haoqiang
 * 
 */
public interface IBorrowersurveyDao extends IBaseDao {
	/**
	 * 
	 * @param borrowersurvey 上述合计对象
	 * @return 整型
	 */
	int updateBorrowersurvey(Borrowersurvey borrowersurvey);

	/**
	 * 
	 * @param borrowerSurveyId 上述合计主键
	 * @return 上述合计对象
	 */
	Borrowersurvey querySelectBorrowersurvey(int borrowerSurveyId);

	/**
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 数量
	 */
	int searchBorrowerSurveyId(int borrowerServiceAppId);

	/**
	 * 
	 * @param borrowerSurvey 上述合计对象
	 */
	public void addBorrowerSurvey(Borrowersurvey borrowerSurvey);
}
