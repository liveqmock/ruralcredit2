package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.domain.Familydepositdebt;

/**
 * 
 * @author haoqiang
 * 
 */
public interface IFamilydepositdebtService {
	/**
	 * 
	 * @param addfamilydepositdebts 储蓄债权债务集合
	 * @return 布尔类型
	 */
	boolean batchInsert(List<Familydepositdebt> addfamilydepositdebts);

	/**
	 * 
	 * @param borrowerSurveyId 上述合计主键
	 * @return 储蓄债权债务集合
	 */
	List<Familydepositdebt> searchfamilydepositdebtList(int borrowerSurveyId);

	/**
	 * 
	 * @param familydepositdebts 储蓄债权债务集合
	 * @return 布尔类型
	 */
	boolean updateFamilyDepositDebtsList(List<Familydepositdebt> familydepositdebts);

	/**
	 * 
	 * @param familydepositdebts 储蓄债权债务集合
	 * @return 布尔类型
	 */
	boolean addFamilyDepositDebtsList(List<Familydepositdebt> familydepositdebts);

	/**
	 * 
	 * @param familydepositdebts 储蓄债权债务集合
	 * @return 布尔类型
	 */
	boolean insertFamilyDepositDebtsList(List<Familydepositdebt> familydepositdebts);

	/**
	 * 储蓄债务的增删改查操作
	 * 
	 * @param familydepositdebtList 储蓄债权债务集合
	 * @return 储蓄债权债务集合
	 */
	List<Familydepositdebt> saveFamilydepositdebts(List<Familydepositdebt> familydepositdebtList);

	/**
	 * 储蓄债务的查寻操作
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 储蓄债权债务集合
	 */
	List<Familydepositdebt> searchFamilydepositdebts(Integer borrowerServiceAppId);

}
