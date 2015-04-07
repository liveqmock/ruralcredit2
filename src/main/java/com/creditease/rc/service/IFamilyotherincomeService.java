package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.domain.Familyotherincome;

/**
 * 
 * @author haoqiang
 * 
 */
public interface IFamilyotherincomeService {
	/**
	 * 
	 * @param addfamilyotherincomes 其他收入List集合
	 * @return 布尔类型
	 */
	boolean batchInsert(List<Familyotherincome> addfamilyotherincomes);

	/**
	 * 
	 * @param borrowerSurveyId 上述合计主键
	 * @return 其他收入List集合
	 */
	List<Familyotherincome> searchfamilyotherincomeList(int borrowerSurveyId);

	/**
	 * 
	 * @param familyotherincomes 其他收入List集合
	 * @return 布尔类型
	 */
	boolean updateFamilyOtherIncomeList(List<Familyotherincome> familyotherincomes);

	/**
	 * 
	 * @param familyotherincomes 其他收入List集合
	 * @return 布尔类型
	 */
	boolean addFamilyOtherIncomeList(List<Familyotherincome> familyotherincomes);

	/**
	 * 
	 * @param familyotherincomes 其他收入List集合
	 * @return 布尔类型
	 */
	boolean insertFamilyOtherIncomeList(List<Familyotherincome> familyotherincomes);

	/**
	 * 查询其他收入
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 其他收入List集合
	 */
	List<Familyotherincome> searchFamilyotherincomes(Integer borrowerServiceAppId);

	/**
	 * 保存其他收入操作
	 * 
	 * @param familyotherincomeList 其他收入List集合
	 * @return 其他收入List集合
	 */
	List<Familyotherincome> saveFamilyotherincomes(List<Familyotherincome> familyotherincomeList);

}
