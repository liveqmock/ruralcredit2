package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;

import com.creditease.rc.framework.dao.IBaseDao;

/**
 * 
 * @author haoqiang
 * 
 */
public interface IReturnPlanDao extends IBaseDao {
	/**
	 * 查询产品ID对应的产品NAME 返回map
	 * 
	 * @return Map<String, String>
	 */
	Map<String, String> queryProductMap();

	List<Long> queryHasNoReturnPlan();

}
