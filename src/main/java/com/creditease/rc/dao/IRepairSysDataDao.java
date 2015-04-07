package com.creditease.rc.dao;
/**
 * 
 * cs
 * Title:IRepairSysDataDao.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-6上午10:23:24
 * @author wyf
 * @version v2.0
 */
public interface IRepairSysDataDao {
	/**
	 * 依据旧业务编号更改业务编号
	* @author wyf   
	* @param oldBusNum 
	* @param newNusNum 
	* @return boolean 
	* @throws Exception    
	 */
	public boolean repairBusNum(String oldBusNum, String newNusNum)throws Exception;
	/**
	 * 依据主键更改业务编号
	* @author wyf   
	* @param caId 
	* @param newNusNum 
	* @return boolean 
	* @throws Exception    
	 */
	public boolean repairBusId(String caId, String newNusNum)throws Exception;
	/**
	 * 查询与我无编号是否重复
	* checkstyle
	* @author wyf   
	* @param oldBusNum 
	* @return int 
	* @throws Exception    
	 */
	public int queryCountByOld(String oldBusNum)throws Exception;
}
