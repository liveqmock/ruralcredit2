package com.creditease.rc.dao.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IFirstSystemDao;
import com.creditease.rc.framework.dao.impl.RuralOneBaseDao;

/**
 * 
 * cs
 * Title:FirstSystemDao.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-7上午10:22:52
 * @author wyf
 * @version v2.0
 */
@Repository
public class FirstSystemDao extends RuralOneBaseDao implements IFirstSystemDao {
	

	@Override
	public int queryCountByUndone(String credentialsNumber) throws Exception {
		int count = (Integer) queryUnique("firstBorrower.selectCountUndone", credentialsNumber);
		return count;
	}

	@Override
	public String queryAuditStatus(String credentialsNumber) throws Exception {
		String auditStatus = (String) queryUnique("firstBorrower.selectAuditStatus", credentialsNumber);
		return auditStatus;
	}
	
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
	public int selectBorrowerCredentialsNumberCount(String credentialsNumber)throws Exception{
		return (Integer) getSqlMapClientTemplate().queryForObject("firstBorrower.selectBorrowerCredentialsNumberCount",credentialsNumber);
	}
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
	public int selectSpouseCredentialsNumberCount(String credentialsNumber,String spouseCredentialsNumber){
		Map<String, String> map = new HashMap<String, String>();
		map.put("credentialsNumber", credentialsNumber);
		map.put("spouseCredentialsNumber", spouseCredentialsNumber);
		return (Integer) getSqlMapClientTemplate().queryForObject("firstBorrower.selectSpouseCredentialsNumberCount",map);
	}
	

}
