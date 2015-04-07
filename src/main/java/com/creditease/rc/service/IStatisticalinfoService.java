package com.creditease.rc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditease.rc.common.DepartmentEntity;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.LoanRealAndPlanning;
import com.creditease.rc.vo.OverDueListVo;

/**
 * 
 * @author haoqiang
 * 
 */
public interface IStatisticalinfoService {
	/**
	 * 
	 * @param map 动态查询map
	 * @param pagination 分页列表
	 * @return Pagination
	 */
	Pagination queryLoanPortfolioList(Map<String, String> map, Pagination pagination);

	Pagination queryOverdueInfoList(Map<String, String> map, Pagination pagination);

	List<HashMap<String, Object>> queryListForLoanPort(Map<String, String> map);

	Pagination queryNewReceivablesDataGrid(Map<String, String> pramMap, Pagination pagination);

	List<LoanRealAndPlanning> queryLoanRealAndPlanningList(Map<String, String> quertyMap);

	List<OverDueListVo> queryForOverDueListEX(Map<String, String> pramMap);

	List<DepartmentEntity> queryDepartmentEntities(Map<String, String> pramMap);
}
