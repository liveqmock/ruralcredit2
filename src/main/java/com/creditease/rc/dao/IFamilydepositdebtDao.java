package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.Familydepositdebt;
import com.creditease.rc.framework.dao.IBaseDao;

/**
 * 
 * @author haoqiang
 * 
 */
public interface IFamilydepositdebtDao extends IBaseDao {
	/**
	 * 
	 * @param addfamilydepositdebts 储蓄债权债务List集合
	 */
	void batchFamilydepositdebtList(List<Familydepositdebt> addfamilydepositdebts);

	/**
	 * 
	 * @param borrowerSurveyId 上述合计主键
	 * @return 储蓄债权债务List集合
	 */
	List<Familydepositdebt> querySelectFamilydepositdebt(int borrowerSurveyId);

	/**
	 * 
	 * @param updatefamilydepositdebts 储蓄债权债务List集合
	 */
	void batchUpdateFamilyDepositDebtsList(List<Familydepositdebt> updatefamilydepositdebts);

	/**
	 * 
	 * @param addfamilydepositdebts 储蓄债权债务List集合
	 */
	void batchInsertfamilydepositdebtList(List<Familydepositdebt> addfamilydepositdebts);

}
