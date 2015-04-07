package com.creditease.rc.service;

import com.creditease.rc.domain.Borrowersurvey;

/**
 * 
 * @author haoqiang
 * 
 */
public interface IBorrowersurveyService {
	/**
	 * 
	 * @param borrowersurvey 上述合计对象
	 * @return 布尔类型
	 */
	boolean updateBorrowersurvey(Borrowersurvey borrowersurvey);

	/**
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 上述合计主键
	 */
	int searchBorrowerSurveyId(int borrowerServiceAppId);

	/**
	 * 
	 * @param borrowersurvey 上述合计对象
	 * @return 布尔类型
	 */
	boolean updateContactSurveyAlt(Borrowersurvey borrowersurvey);

	/**
	 * 回显上述合计
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 上述合计对象
	 */
	Borrowersurvey searchBorrowersurvey(Integer borrowerServiceAppId);

	/**
	 * 对上述合计操作进行保存
	 * 
	 * @param passborrowersurvey 上述合计对象
	 * @return 上述合计对象
	 */
	Borrowersurvey saveBorrowersurvey(Borrowersurvey passborrowersurvey);

	/**
	 * 更新修改状态
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 */
	void updateFlag3th(Integer borrowerServiceAppId);

}
