package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.Familyotherincome;
import com.creditease.rc.framework.dao.IBaseDao;

/**
 * 
 * @author haoqiang
 * 
 */
public interface IFamilyotherincomeDao extends IBaseDao {
	/**
	 * 
	 * @param addfamilyotherincomes 其他收入List集合
	 */
	void batchFamilyotherincomeList(List<Familyotherincome> addfamilyotherincomes);

	/**
	 * 
	 * @param borrowerSurveyId 上述合计主键
	 * @return 其他收入List集合
	 */
	List<Familyotherincome> querySelectFamilyotherincome(int borrowerSurveyId);

	/**
	 * 
	 * @param updatefamilyotherincomes 其他收入List集合
	 */
	void batchUpdateFamilyOtherIncomeList(List<Familyotherincome> updatefamilyotherincomes);

	/**
	 * 
	 * @param addfamilyotherincomes 其他收入List集合
	 */
	void batchInsertfamilyotherincomeList(List<Familyotherincome> addfamilyotherincomes);

}
