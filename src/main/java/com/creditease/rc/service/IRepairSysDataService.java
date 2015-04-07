package com.creditease.rc.service;

public interface IRepairSysDataService {
	/**
	 * 依据旧业务编号更改业务编号
	* @author wyf  
	* @param: oldBusNum
	* @param: newNusNum
	* @return: boolean 
	* @throws:
	 */
	public boolean repairBusNum(String oldBusNum, String newNusNum)throws Exception;
	/**
	 * 依据主键更改业务编号
	* @author wyf  
	* @param: oldBusNum
	* @param: newNusNum
	* @return: boolean 
	* @throws:
	 */
	public boolean repairBusId(String caId, String newNusNum)throws Exception;
	/**
	 * 查询与我无编号是否重复
	* @author wyf  
	* @param: oldBusNum
	* @param: newNusNum
	* @return: int 
	* @throws:
	 */
	public int queryCountByOld(String oldBusNum) throws Exception;
}
