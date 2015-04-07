package com.creditease.rc.dao;

import java.util.HashMap;

public interface OrgamsDAO {

	/**
	 * 为机构资产借款请求提供数据
	 * 
	 */
	public HashMap<Object, Object> selectOrgams(Long creditapplicationId);
	
	/**
	 * 为合同同步接口提供数据
	 * @param creditapplicationId
	 * @return
	 */
	public HashMap<Object, Object> selectBorrowContract(Long creditapplicationId);
}
