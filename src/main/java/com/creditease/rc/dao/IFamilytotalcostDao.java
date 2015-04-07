package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.Familytotalcost;
import com.creditease.rc.framework.dao.IBaseDao;

/**
 * 
 * @author haoqiang
 * 
 */
public interface IFamilytotalcostDao extends IBaseDao {
	/**
	 * 
	 * @param addfamilytotalcosts 日常总开支List集合
	 */
	void batchFamilytotalcostList(List<Familytotalcost> addfamilytotalcosts);

	/**
	 * 
	 * @param borrowerSurveyId 上述合计主键
	 * @return 日常总开支List集合
	 */
	List<Familytotalcost> querySelectFamilytotalcost(int borrowerSurveyId);

	/**
	 * 
	 * @param updatefamilytotalcosts 日常总开支List集合
	 */
	void batchUpdateFamilytotalcostList(List<Familytotalcost> updatefamilytotalcosts);

	/**
	 * 
	 * @param addfamilytotalcosts 日常总开支List集合
	 */
	void batchInsertfamilytotalcostList(List<Familytotalcost> addfamilytotalcosts);

}
