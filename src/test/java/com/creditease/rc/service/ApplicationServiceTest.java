package com.creditease.rc.service;

import junit.framework.Test;
import junit.framework.TestResult;

import org.springframework.beans.factory.annotation.Autowired;

import com.creditease.rc.vo.ApplicationTableVo1;

/**
 * 
 * Title:ApplicationServiceTest.java 
 * Description: 
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * @version v1.0
 * 2012-12-27
 */

public class ApplicationServiceTest implements Test {

	@Autowired
	IApplicationService applicationService;
	@Override
	public int countTestCases() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void run(TestResult result) {
		// TODO Auto-generated method stub

	}

	/**
	 * public void testAddApplication
	 */
	public void testAddApplication(){
		ApplicationTableVo1 testApplication = new ApplicationTableVo1();
		
	}
}
