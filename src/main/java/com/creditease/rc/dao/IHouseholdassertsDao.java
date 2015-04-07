package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.Householdasserts;
import com.creditease.rc.framework.dao.IBaseDao;

/**
 * 
 * @author haoqiang
 * 
 */
public interface IHouseholdassertsDao extends IBaseDao {
	/**
	 * 
	 * @param addhouseholdassertss 家庭资产List集合
	 */
	void batchInsertToHoldasserts(List<Householdasserts> addhouseholdassertss);

	/**
	 * 
	 * @param borrowerSurveyId 上述合计主键
	 * @return 家庭资产List集合
	 */
	List<Householdasserts> querySelectHouseholdasserts(int borrowerSurveyId);

	/**
	 * 
	 * @param updatehouseholdassertss 家庭资产List集合
	 */
	void batchUpdateHouseholdAssertsList(List<Householdasserts> updatehouseholdassertss);

}
