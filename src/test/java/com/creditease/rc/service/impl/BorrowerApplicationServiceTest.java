package com.creditease.rc.service.impl;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.domain.Application;
import com.creditease.rc.domain.BorrowerService;
import com.creditease.rc.domain.Deposit;
import com.creditease.rc.domain.Familymember;
import com.creditease.rc.domain.JobInfo;
import com.creditease.rc.domain.Surveybusinessinfo;
import com.creditease.rc.service.IBorrowerApplicationService;
import com.creditease.rc.vo.HouseServeyVo;

/**
 * 
 * @author zhangman
 * @2013-6-18下午04:26:59
 * Copyright: Copyright (c) 2013
 * Company: 普信恒业科技发展（北京）有限公司
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring/rc/applicationContext.xml",
									"classpath:config/spring/rc/applicationContext-ws-service.xml",
									"classpath:config/spring/settle/applicationContext-settle.xml",
									"classpath:config/spring/pdf/applicationContext-pdf.xml",
									"classpath:config/spring/cm/applicationContext-cm.xml",
									"classpath:config/spring/smp/applicationContext-acl.xml",
									"classpath:config/spring/smp/applicationContext-security.xml",
									"classpath:config/spring/smp/applicationContext-ws-client.xml"
					})
public class BorrowerApplicationServiceTest extends AbstractJUnit4SpringContextTests{
	@Resource
	private IBorrowerApplicationService borrowerApplicationService;
//	private final static Log log = LogFactory.getLog(clazz) 
	
	 @BeforeClass
//	 protected void init() throws Exception {
//	  this.tester = new WebTester();
//	  this.tester.getTestContext().
//	   setBaseUrl("http://div.acme.com:8185/ceg/");
//	 }
	
	@Before
	public void setUp() throws Exception {
		System.out.println("开始前");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("开始后————————");
	}

	@Test
	public void testAddApplication() throws Exception{
		
		BorrowerService  borrowerService = new BorrowerService(); 		//个人申请
		List<Familymember> familymemberList = new ArrayList<Familymember>();					//家庭成员
		List<JobInfo> jobInfoList = new ArrayList<JobInfo>();							//工作信息
		List<Surveybusinessinfo> surveybusinessinfoList = new ArrayList<Surveybusinessinfo>();		//经营情况
		List<Deposit> depositList = new ArrayList<Deposit>();							//存款和应收现金
		List<Application> applicationList = new ArrayList<Application>();					//借款申请
		HouseServeyVo houseServeyVo = new HouseServeyVo();
		
		Short s = new Short("1");
		borrowerService.setAge(s);
		borrowerService.setApplyDate(new Date());
		borrowerService.setAuditStatus("01");
		borrowerService.setBirthday("1989-2-2");
		borrowerService.setBondsmanBorrower("qw");
		borrowerService.setBorrRemark("1234");
		borrowerService.setVillageId(2l);
		borrowerService.setResidenceAddress("乡镇村省市区");
		borrowerService.setBusinessArea("sssssss");
		borrowerService.setBusinessCommercial("1");
		borrowerService.setBusinessDate(new Date());
		borrowerService.setBusinessNumber("1233333");
		borrowerService.setBusinessOther("1");
		borrowerService.setBusinessRent("1");
		borrowerService.setBusinessRelative("1");
		borrowerService.setBusinessSelf("1");
		borrowerService.setBusinessYear(1231.00d);
		borrowerService.setCredentialsNumber("110109198001100308");
		borrowerService.setCreditapplicationId(34104l);
		borrowerService.setGender("1");
		borrowerService.setFormer("123");
		borrowerService.setFirstFlag("1");
		borrowerService.setHomeAddress("123333333333");
		borrowerService.setHourseholdAddress("234qdwsdf谁的凤飞飞");
		borrowerService.setLeader("1");
		borrowerService.setLivingArea("123");
		borrowerService.setNationality("2");
		borrowerService.setResidenceAddress("w3r4rrrrrrrr");
		borrowerService.setOfficePhone("234");
		borrowerService.setMobilephone("222222222");
		borrowerService.setLivingYear(2341.00d);
		borrowerService.setMaritalStatus("1");
		
		for( int i = 0 ; i < 2 ;i++){
			Familymember familymember = new Familymember();
			familymember.setAge(12);
			familymember.setBorrowerreRationship("1");
			familymember.setEducation("2");
			familymember.setGender("1");
			familymember.setIdNumber("110109198001100287");
			familymember.setName("李斯");
			familymember.setTelphone("12333333333");
			familymember.setYearIncome(200d);
			familymember.setWorkUnit("北京");
			familymemberList.add(familymember);
		}
		
		
		for( int i = 0 ; i < 2 ;i++){
			JobInfo jobInfo = new JobInfo();
			jobInfo.setCompany("阿斯顿");
			jobInfo.setContanTelephone("122222222222");
			jobInfo.setContantPerson("lian");
			jobInfo.setDepartment("sdf");
			jobInfo.setEnterTime(new Date());
			jobInfo.setYears( 12.5);
			jobInfo.setIncome(234d);
			jobInfoList.add(jobInfo);
			
		}
		
		for( int i = 0 ; i < 2 ;i++){
			Surveybusinessinfo surveybusinessinfo = new Surveybusinessinfo();
			surveybusinessinfo.setBusinessAddress("2222222222");
			surveybusinessinfo.setBusinessLicense("2");
			surveybusinessinfo.setBusinessPermitNo("1");
			surveybusinessinfo.setBusinessPermitNo("1");
			surveybusinessinfo.setBusinessPlaceDate(new Date());
			surveybusinessinfo.setBusinessPlaceOther("2");
			surveybusinessinfo.setBusinessPlaceType("1");
			surveybusinessinfo.setBusinessScope("1");
			surveybusinessinfoList.add(surveybusinessinfo);
		}
		
		for( int i = 0 ; i < 2 ;i++){
			Deposit deposit = new Deposit();
			deposit.setAddress("wwwww");
			deposit.setBalance(234d);
			deposit.setDepositOrganization("1");
			deposit.setDepositOrganizationDetail("234");
			deposit.setTelephone("22222222");
			deposit.setProveDocument("1");
			depositList.add(deposit);
		}
		
		for(int i = 0 ; i < 2 ;i++){
			Application application = new Application();
			application.setBorrowUse("1");
			application.setBorrowUseDetail("2222222222");
			application.setProveDocument("1");
			application.setUnitPrice(2134d);
			application.setTotalAmount(123d);
			application.setQuantity(2l);
			applicationList.add(application);
		}
	
		houseServeyVo.setApplicationList(applicationList);
		houseServeyVo.setBorrowerService(borrowerService);
		houseServeyVo.setDepositList(depositList);
		houseServeyVo.setFamilymemberList(familymemberList);
		houseServeyVo.setJobInfoList(jobInfoList);
		houseServeyVo.setSurveybusinessinfoList(surveybusinessinfoList);


Boolean boolean1 =		 borrowerApplicationService.addApplication(houseServeyVo, null);
System.out.println(boolean1);
	}

	@Test
	public void testSelectApplication() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateApplication() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCreditApplicationName() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddOrUpdateCustomer() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClass() {
		fail("Not yet implemented");
	}

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testEquals() {
		fail("Not yet implemented");
	}

	@Test
	public void testClone() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotify() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotifyAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitLongInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testWait() {
		fail("Not yet implemented");
	}

	@Test
	public void testFinalize() {
		fail("Not yet implemented");
	}

}
