package com.creditease.rc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.domain.CustomerBasicInfo;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.ICustomerBasicInfoService;
/**
 * 客户管理单元测试
 * @author zhangman
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring/rc/applicationContext.xml",
									"classpath:config/spring/rc/applicationContext-ws-service.xml",
									"classpath:config/spring/settle/applicationContext-settle.xml",
									"classpath:config/spring/pdf/applicationContext-pdf.xml",
									"classpath:config/spring/smp/applicationContext-acl.xml",
									"classpath:config/spring/smp/applicationContext-security.xml",
									"classpath:config/spring/smp/applicationContext-ws-client.xml"
					})

public class CustomerBasicInfoServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}

	@Resource
	private ICustomerBasicInfoService customerBasicInfoService;
	
//	 测试分页模糊查询
	  
	/**
	 * 测试 客户信息列表查询方法
	 */
 	@Test
	public void testListCustomerBasicInfos() {
 		Pagination pagination = new Pagination();
 		pagination .setCurrentPage(0);
 		pagination .setPageSize(9);
 		
		CustomerBasicInfo customInfo= new CustomerBasicInfo();  
		customInfo.setCredentialsNumber("");
		customInfo.setName("");
		customInfo.setPresentAddress("开封");
		customInfo.setTelephone("");
//		customInfo.setCredentialsType("1");
//		customInfo.setCustomerBasicId(1395);
//		customInfo.setFormer("李贵");
//		customInfo.setGender("1");
//		customInfo.setHighestEducation("本科");
//		customInfo.setMaritalStatus("1");
//		customInfo.setMobilephone( "9521753");
//	
//		customInfo.setNational("1");
//		customInfo.setOperateId(513656);
//		customInfo.setOperateName("客户经理");
//		customInfo.setPersonIncome(6000);
//	
//		customInfo.setRemark("河北省-石家庄-社么先-什么镇-什么村");
//		customInfo.setResidenceAddress("河北省-石家庄-社么先-什么镇-什么村");
//		
//		customInfo.setCustomerBasicId(1395);
		
		pagination  = customerBasicInfoService.listCustomerBasicInfos(customInfo,"", pagination);
		
		List<CustomerBasicInfo> CustomerBasicInfoList =pagination.getRows(); 
		for (CustomerBasicInfo customerBasicInfo2 : CustomerBasicInfoList) {
			System.out.println("证件编码-----"+customerBasicInfo2.getCredentialsNumber());
			System.out.println("证件类型-----"+customerBasicInfo2 .getCredentialsType());
			System.out.println("曾用名------"+customerBasicInfo2.getFormer());
			System.out.println("性别-------"+customerBasicInfo2.getGender());
			System.out.println("学历-------"+customerBasicInfo2.getHighestEducation());
			System.out.println("是否结婚-----"+customerBasicInfo2.getMaritalStatus());
			System.out.println("电话号码-----"+customerBasicInfo2.getMobilephone());
			System.out.println("姓名-------"+customerBasicInfo2.getName());
			System.out.println("民族-------"+customerBasicInfo2.getNational());
			System.out.println("操作人------"+customerBasicInfo2.getOperateName());
			System.out.println("家庭住址（现住址）--"+customerBasicInfo2.getPresentAddress());
			System.out.println("备注-------"+customerBasicInfo2.getRemark());
			System.out.println("户籍地址-----"+customerBasicInfo2.getResidenceAddress());
			System.out.println("固话号码-----"+customerBasicInfo2.getTelephone());
			System.out.println("编码主键-----"+customerBasicInfo2.getCustomerBasicId());
			System.out.println("操作人id-----"+customerBasicInfo2.getOperateId());
			System.out.println("个人年收入----"+customerBasicInfo2.getPersonIncome());
			System.out.println("=============================================================");
		}
		
	}

 	/**
 	 * 测试客户信息添加 修改 方法
 	 */
	@Test
	public void testAddCustomerBasicInfo() {
		CustomerBasicInfo customInfo= new CustomerBasicInfo();  
		customInfo.setCredentialsNumber("110101198006010132");
		customInfo.setCredentialsType("1");
		customInfo.setCustomerBasicId(null);
		customInfo.setFormer("李贵");
		customInfo.setGender("1");
		customInfo.setHighestEducation("本科");
		customInfo.setMaritalStatus("1");
		customInfo.setMobilephone( "9521753");
		customInfo.setName("李斯");
		customInfo.setNational("1");
		customInfo.setOperateId(513656);
		customInfo.setOperateName("客户经理");
		customInfo.setPersonIncome(6000.00);
		customInfo.setPresentAddress("河北省-石家庄-社么先-什么镇-什么村");
		customInfo.setRemark("河北省-石家庄-社么先-什么镇-什么村");
		customInfo.setResidenceAddress("河北省-石家庄-社么先-什么镇-什么村");
		customInfo.setTelephone("4665465465");
		customerBasicInfoService.addCustomerBasicInfo(customInfo);
//		fail("Not yet implemented");
	}

	/**
	 * 测试修改方法
	 */
	@Test
	public void testUpdateCustomerBasicInfo() {
		CustomerBasicInfo customInfo= new CustomerBasicInfo();  
		customInfo.setCredentialsNumber("11010119800901039X");
		customInfo.setCredentialsType("1");
		customInfo.setCustomerBasicId(1542);
		customInfo.setFormer("李贵");
		customInfo.setGender("1");
		customInfo.setHighestEducation("本科");
		customInfo.setMaritalStatus("1");
		customInfo.setMobilephone( "9521753");
		customInfo.setName("李斯");
		customInfo.setNational("1");
		customInfo.setOperateId(513656);
		customInfo.setOperateName("客户经理");
		customInfo.setPersonIncome(6000.00);
		customInfo.setPresentAddress("河北省-石家庄-社么先-什么镇-什么村");
		customInfo.setRemark("河北省-石家庄-社么先-什么镇-什么村");
		customInfo.setResidenceAddress("河北省-石家庄-社么先-什么镇-什么村");
		customInfo.setTelephone("4665465465");
		
		boolean flag = customerBasicInfoService.updateCustomerBasicInfo(customInfo);
		System.out.println(flag);
	}

	/**
	 * 测试删除方法
	 */
	@Test
	public void testDeleteCustomerBasicInfoById() {
		int customerBasicInfoId= 1476;
		boolean flag = customerBasicInfoService.deleteCustomerBasicInfoById(customerBasicInfoId);
		System.out.println(flag);
	}

	/**
	 * 测试查询方法
	 */
	@Test
	public void testSelectCustomerBasicInfo() {
		CustomerBasicInfo customInfo= new CustomerBasicInfo();  
		customInfo.setCredentialsNumber("11010119800901039X");
		customInfo.setName("");
		customInfo.setPresentAddress("李镇");
		customInfo.setTelephone("");
//		customInfo.setCredentialsType("1");
//		customInfo.setCustomerBasicId(1395);
//		customInfo.setFormer("李贵");
//		customInfo.setGender("1");
//		customInfo.setHighestEducation("本科");
//		customInfo.setMaritalStatus("1");
//		customInfo.setMobilephone( "9521753");
//	
//		customInfo.setNational("1");
//		customInfo.setOperateId(513656);
//		customInfo.setOperateName("客户经理");
//		customInfo.setPersonIncome(6000);
//	
//		customInfo.setRemark("河北省-石家庄-社么先-什么镇-什么村");
//		customInfo.setResidenceAddress("河北省-石家庄-社么先-什么镇-什么村");
//		
		customInfo.setCustomerBasicId(1542);
		
		CustomerBasicInfo customerBasicInfo = customerBasicInfoService.selectCustomerBasicInfo(customInfo);
		
		System.out.println("证件编码-----"+customerBasicInfo.getCredentialsNumber());
		System.out.println("证件类型-----"+customerBasicInfo .getCredentialsType());
		System.out.println("曾用名------"+customerBasicInfo.getFormer());
		System.out.println("性别-------"+customerBasicInfo.getGender());
		System.out.println("学历-------"+customerBasicInfo.getHighestEducation());
		System.out.println("是否结婚-----"+customerBasicInfo.getMaritalStatus());
		System.out.println("电话号码-----"+customerBasicInfo.getMobilephone());
		System.out.println("姓名-------"+customerBasicInfo.getName());
		System.out.println("民族-------"+customerBasicInfo.getNational());
		System.out.println("操作人------"+customerBasicInfo.getOperateName());
		System.out.println("家庭住址（现住址）--"+customerBasicInfo.getPresentAddress());
		System.out.println("备注-------"+customerBasicInfo.getRemark());
		System.out.println("户籍地址-----"+customerBasicInfo.getResidenceAddress());
		System.out.println("固话号码-----"+customerBasicInfo.getTelephone());
		System.out.println("编码主键-----"+customerBasicInfo.getCustomerBasicId());
		System.out.println("操作人id-----"+customerBasicInfo.getOperateId());
		System.out.println("个人年收入----"+customerBasicInfo.getPersonIncome());
	}

}
