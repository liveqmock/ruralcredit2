package com.creditease.rc.dao;

import com.creditease.rc.domain.Borrowerserviceapptest;

/**
 * 
 * @author haoqiang
 * 
 */
public interface IBorrowerserviceapptestDao {
	/**
	 * 
	 * @return 信贷申请主键
	 */
	int borrowerServiceAppIdTest();

	/**
	 * 
	 * @param borrowerServiceAppIdTest 信贷申请主键
	 * @return 上述合计主键
	 */
	int borrowersurveyIdTest(int borrowerServiceAppIdTest);

	/**
	 * 
	 * @param borrowerServiceAppIdTest 信贷申请主键
	 * @return 信贷申请对象
	 */
	Borrowerserviceapptest selecetBorrowerappNameandId(int borrowerServiceAppIdTest);
}
