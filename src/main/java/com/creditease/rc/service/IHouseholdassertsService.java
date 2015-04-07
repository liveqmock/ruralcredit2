package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.domain.Householdasserts;

/**
 * 
 * @author haoqiang
 * 
 */
public interface IHouseholdassertsService {
	/**
	 * 
	 * @param householdassertss  家庭资产List集合
	 * @return 布尔类型
	 */
	boolean addHouseholdAssertsList(List<Householdasserts> householdassertss);

	/**
	 * 
	 * @param borrowerSurveyId 上述合计主键
	 * @return  家庭资产List集合
	 */
	List<Householdasserts> searchhouseholdassertsList(int borrowerSurveyId);

	/**
	 * 
	 * @param householdassertss 家庭资产List集合
	 * @return  布尔类型
	 */
	boolean updateHouseholdAssertsList(List<Householdasserts> householdassertss);

	/**
	 * 
	 * @param householdassertss  家庭资产List集合
	 * @return 布尔类型
	 */
	boolean insertHouseholdAssertsList(List<Householdasserts> householdassertss);

	/**
	 * 查询家庭资产
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return  家庭资产List集合
	 */
	List<Householdasserts> searchHouseholdassertss(Integer borrowerServiceAppId);

	/**
	 * 操作家庭资产
	 * 
	 * @param householdassertsList 家庭资产List集合
	 * @return 家庭资产List集合
	 */
	List<Householdasserts> saveHouseholdassertss(List<Householdasserts> householdassertsList);

}
