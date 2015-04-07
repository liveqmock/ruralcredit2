package com.creditease.rc.dao;


/**
 * 
 * cs
 * Title:IFirstSystemDao.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-6上午10:21:14
 * @author wyf
 * @version v2.0
 */
public interface IFirstSystemDao{
	/**
	 * 查询借款人未还完期数
	* @author wyf   
	* @param credentialsNumber 
	* @return int 
	* @throws Exception    
	 */
	public int queryCountByUndone(String credentialsNumber)throws Exception;
	/**
	 * 查询借款人申请状态
	* @author wyf   
	* @param credentialsNumber
	* @return
	* @throws Exception    
	* int
	 */
	public String queryAuditStatus(String credentialsNumber)throws Exception;
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  根据身份证（借款人或者配偶）查询是否是一期借款人
	 * @param credentialsNumber  身份证（借款人或者配偶）
	 * @return
	 * @throws Exception 
	 * @version v1.0 
	 * 2014-7-7
	 */
	public int selectBorrowerCredentialsNumberCount(String credentialsNumber)throws Exception;
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  根据借款人身份证查询是否是一期借款人的配偶
	 * @param credentialsNumber 借款人身份证
	 * @param spouseCredentialsNumber 借款人配偶身份证
	 * @return 
	 * @throws Exception 
	 * @version v1.0 
	 * 2014-7-7
	 */
	public int selectSpouseCredentialsNumberCount(String credentialsNumber,String spouseCredentialsNumber)throws Exception;
}
