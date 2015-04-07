package com.creditease.rc.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.framework.pager.Pagination;

/**
 * 
 * Title:ExportExcelServiceTest.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-5下午03:16:46
 * @author 王毅峰
 * @version v2.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring/rc/applicationContext.xml" })
public class ExportExcelServiceTest  extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Resource
	private IExportExcelService excelService;
	private Map map = new HashMap();
	private Pagination pagination = new Pagination();
	/**
	 * 
	 * @throws Exception 错误
	 */
	@Before
	public void setUp() throws Exception {
		pagination.setPage(Integer.valueOf(0));	
		pagination.setPageSize(Integer.valueOf(10));
		System.out.println("测试开始....");
	}

	/**
	 * 
	 * @throws Exception 错误
	 */
	@After
	public void tearDown() throws Exception {
		System.out.println("测试结束");
	}


	/**
	 * 
	* @author wyf   
	* @throws Exception
	* void
	 */
	@Test
	public void testgetQMData() throws Exception{
		pagination = excelService.getQMData(map,pagination);
		System.out.println(pagination.getRows());
	}
	/**
	 * 
	* @author wyf   
	* @throws Exception
	* void
	 */
	@Test
	public void testgetCMPData() throws Exception{
		pagination = excelService.getCMPData(map,pagination);
		System.out.println(pagination.getRows());
	}
}
