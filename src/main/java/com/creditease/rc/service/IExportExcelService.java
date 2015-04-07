package com.creditease.rc.service;

import java.util.Map;

import com.creditease.rc.framework.pager.Pagination;
/**
 * 
 * cs
 * Title:IExportExcelService.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-5下午03:43:56
 * @author wyf
 * @version v2.0
 */
public interface IExportExcelService {
	/**
	 * 量化管理报表数据查询
	* checkstyle
	* @author wyf   
	* @param map 
	* @param pagination  
	* @return Pagination 
	* @throws Exception    
	* 
	 */
	public Pagination getQMData(Map map,Pagination pagination)throws Exception;
	/**
	 * 客户经理业绩数据查询
	* checkstyle
	* @author wyf   
	* @param map 
	* @param pagination 
	* @return Pagination
	* @throws Exception    
	* 
	 */
	public Pagination getCMPData(Map map,Pagination pagination)throws Exception;
	/**
	 * 业务详情报表数据查询
	* checkstyle
	* @author wyf   
	* @param map 
	* @param pagination 
	* @return Pagination
	* @throws Exception    
	* 
	 */
	public Pagination getBDData(Map map,Pagination pagination)throws Exception;
}
