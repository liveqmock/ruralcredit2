package com.creditease.rc.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * 
 * @author haoqiang
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring/rc/applicationContext.xml"})
public class MyJUnit extends AbstractTransactionalJUnit4SpringContextTests {

	/**
	 * 
	 * @throws Exception 错误
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * 
	 * @throws Exception 错误
	 */
	@After
	public void tearDown() throws Exception {
	}
/**
 * 
 */
	@Test
	public void test() {
		System.out.println("aaaaaaaa");
	}
}
