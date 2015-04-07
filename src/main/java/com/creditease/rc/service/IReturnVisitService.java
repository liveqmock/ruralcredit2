package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.domain.ReturnVisit;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.ReturnVisitVo;
/**
 * 
 * @author zhangman
 * Copyright: Copyright (c) 2012
 * Company: 普信恒业科技发展（北京）有限公司
 */
public interface IReturnVisitService {

	/**
	 * 
	 * @param returnVisit 客户回访对象
	 * @return 对象
	 */
	public ReturnVisit addReturnVisit(ReturnVisit returnVisit);
	/**
	 * 
	 * @param returnVisit 客户回访对象 
	 * @return 行数
	 */
	public int updateReturnVisit(ReturnVisit returnVisit);
	/**
	 * 
	 * @param returnVisit 客户回访对象
	 * @return vo对象
	 */
	public ReturnVisitVo  selectReturnVisit(ReturnVisit returnVisit);
	/**
	 * @param returnVisit 客户回访对象
	 * @param pagination 分页条件
	 * @param fuzzyValue 模糊查询条件
	 * @return list
	 */
	public Pagination  selectReturnVisitList(ReturnVisitVo returnVisit,Pagination pagination ,String fuzzyValue);
}
