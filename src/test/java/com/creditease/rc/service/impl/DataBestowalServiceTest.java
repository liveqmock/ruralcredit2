package com.creditease.rc.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.domain.DataBestowal;
import com.creditease.rc.domain.Message;
import com.creditease.rc.service.IDataBestowalService;

/**
 * 测试数据赠予功能service
 * 
 * @author zhangman
 * @2013-6-21下午02:56:29
 *                      Copyright: Copyright (c) 2013
 *                      Company: 普信恒业科技发展（北京）有限公司
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring/rc/applicationContext.xml",
		"classpath:config/spring/rc/applicationContext-ws-service.xml",
		"classpath:config/spring/settle/applicationContext-settle.xml",
		"classpath:config/spring/pdf/applicationContext-pdf.xml",
		"classpath:config/spring/cm/applicationContext-cm.xml",
		"classpath:config/spring/smp/applicationContext-acl.xml",
		"classpath:config/spring/smp/applicationContext-security.xml",
		"classpath:config/spring/smp/applicationContext-ws-client.xml" })
public class DataBestowalServiceTest extends AbstractJUnit4SpringContextTests {

	@Resource
	private IDataBestowalService dataBestowalService;

	// 插入数据赠与主表与子表
	@Test
	public void testInsertDataBestowalAndDataBestowalDetail() {
		DataBestowal bestowal = new DataBestowal();
		bestowal.setOldLoanOfficerId("11000788");
		bestowal.setOldLoanOfficerName("崇信信贷员");
		bestowal.setNewLoanOfficerId("11000790");
		bestowal.setNewLoanOfficerName("静宁信贷员");
		bestowal.setNote("123344556778989");
		bestowal.setOperatingId("234");
		bestowal.setOperatingName("234");
		bestowal.setOperatingTime(new Date());

		Long id[] = new Long[] { 2497l, 2703l };
		Message message = dataBestowalService.insertDataBestowalAndDataBestowalDetail(bestowal, id);
		System.out.println(message.getCode());
		System.out.println(message.isSuccess());
	}

}
