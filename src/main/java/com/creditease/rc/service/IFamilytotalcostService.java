package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.domain.Familytotalcost;

/**
 * 
 * @author haoqiang
 * 
 */

public interface IFamilytotalcostService {
	/**
	 * 
	 * @param addfamilytotalcosts 日常总开支List集合
	 * @return 布尔类型
	 */
	boolean batchInsert(List<Familytotalcost> addfamilytotalcosts);

	/**
	 * 
	 * @param borrowerSurveyId 上述合计主键
	 * @return 日常总开支List集合
	 */
	List<Familytotalcost> searchfamilytotalcostList(int borrowerSurveyId);

	/**
	 * 
	 * @param familytotalcosts 日常总开支List集合
	 * @return 布尔类型
	 */
	boolean updateFamilyTotalCostList(List<Familytotalcost> familytotalcosts);

	/**
	 * 
	 * @param familytotalcosts 日常总开支List集合
	 * @return 布尔类型
	 */
	boolean addFamilyTotalCostList(List<Familytotalcost> familytotalcosts);

	/**
	 * 
	 * @param familytotalcosts 日常总开支List集合
	 * @return 布尔类型
	 */
	boolean insertFamilyTotalCostList(List<Familytotalcost> familytotalcosts);

	/**
	 * 日常总开支回显
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 日常总开支List集合
	 */
	List<Familytotalcost> searchFamilytotalcosts(Integer borrowerServiceAppId);

	/**
	 * 日常总开支操作
	 * 
	 * @param familytotalcostList 日常总开支List集合
	 * @return 日常总开支List集合
	 */
	List<Familytotalcost> saveFamilytotalcosts(List<Familytotalcost> familytotalcostList);

}
