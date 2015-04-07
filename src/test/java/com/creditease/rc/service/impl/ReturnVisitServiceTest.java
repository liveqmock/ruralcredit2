package com.creditease.rc.service.impl;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.creditease.rc.domain.ReturnVisit;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IReturnVisitService;
import com.creditease.rc.vo.ReturnVisitVo;
import com.ibatis.common.util.PaginatedArrayList;

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
public class ReturnVisitServiceTest {

	 @Resource
	 private IReturnVisitService returnVisitService;

	@Test
	public void testAddReturnVisit() {
		ReturnVisit returnVisit = new ReturnVisit();
		returnVisit.setAddressChange("1");
		returnVisit.setAddressChangeDetail("顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶");
		returnVisit.setBorrowerId(7634l);
		returnVisit.setBorrowerName("啦啦啦啦啦啦啦啦");
		returnVisit.setClienteleAttitude("1");
		returnVisit.setContactChange("1");
		returnVisit.setContactChangeDetail("的的顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶");
		returnVisit.setContactTime(new Date());
		returnVisit.setContactWay("啦啦啦啦啦");
		returnVisit.setCustomManager("1");
		returnVisit.setFamilymembersChange("1");
		returnVisit.setFamilymembersChangeDetail("啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦");
		returnVisit.setGroupNumber("1");
		returnVisit.setKeytoFollowup("0");
		returnVisit.setKeytoFollowupDetail("啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦");
		returnVisit.setMonthIncome("1");
		returnVisit.setMonthIncomeDetail("啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦");
		returnVisit.setOtherFamilystatusDetail(" 啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦");
		returnVisit.setOtherFamilystatusReason("1");
		returnVisit.setOtherPaypressureDetail("啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦");
		returnVisit.setOtherPaypressureReason("0");
		returnVisit.setOtherVastexpenseDetail("啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦");
		returnVisit.setOtherVastexpenseReason("0");
		returnVisit.setOtherWorkingChange("0");
		returnVisit.setOtherWorkingDetail("啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦");
		ReturnVisit returnVisit2 = returnVisitService.addReturnVisit(returnVisit);
		System.out.println(
				returnVisit2.getAddressChange()+","
				+returnVisit2.getAddressChangeDetail()+","
				+returnVisit2.getBorrowerId()+","
				+returnVisit2.getBorrowerName()+","
				+returnVisit2.getClienteleAttitude()+","
				+returnVisit2.getContactChange()+","
				+returnVisit2.getContactChangeDetail()+","
				+returnVisit2.getContactTime()+","
				+returnVisit2.getContactWay()+","
				+returnVisit2.getCustomManager()+","
				+returnVisit2.getFamilymembersChange()+","
				+returnVisit2.getFamilymembersChangeDetail()+","
				+returnVisit2.getGroupNumber()+","
				+returnVisit2.getKeytoFollowup()+","
				+returnVisit2.getKeytoFollowupDetail()+","
				+returnVisit2.getMonthIncome()+","
				+returnVisit2.getMonthIncomeDetail()+","
				+returnVisit2.getOtherFamilystatusDetail()+","
				+returnVisit2.getOtherFamilystatusReason()+","
				+returnVisit2.getOtherPaypressureDetail()+","
				+returnVisit2.getOtherPaypressureReason()+","
				+returnVisit2.getOtherVastexpenseDetail()+","
				+returnVisit2.getOtherVastexpenseReason()+","
				+returnVisit2.getOtherWorkingChange()+","
				+returnVisit2.getOtherWorkingDetail()
		);
		
	}

	@Test
	public void testSelectReturnVisitList() {
		ReturnVisitVo returnVisit = new ReturnVisitVo();
		returnVisit.setAddressChange("");
		returnVisit.setAddressChangeDetail("");
		returnVisit.setBorrowerId(7634l);
		returnVisit.setBorrowerName("");
		returnVisit.setClienteleAttitude("");
		returnVisit.setContactChange("");
		returnVisit.setContactChangeDetail("");
		returnVisit.setContactWay("");
		returnVisit.setCustomManager("1");
		returnVisit.setFamilymembersChange("1");
		returnVisit.setFamilymembersChangeDetail("");
		returnVisit.setGroupNumber("1");
		returnVisit.setKeytoFollowup("0");
		returnVisit.setKeytoFollowupDetail("");
		returnVisit.setMonthIncome("1");
		returnVisit.setMonthIncomeDetail("");
		returnVisit.setOtherFamilystatusDetail("");
		returnVisit.setOtherFamilystatusReason("1");
		returnVisit.setOtherPaypressureDetail("");
		returnVisit.setOtherPaypressureReason("0");
		returnVisit.setOtherVastexpenseDetail("");
		returnVisit.setOtherVastexpenseReason("0");
		returnVisit.setOtherWorkingChange("0");
		returnVisit.setOtherWorkingDetail("");
		Pagination pagination = new Pagination();
		pagination.setPage(1);
		pagination.setPageSize(10);
		Pagination pagination2 =returnVisitService.selectReturnVisitList(returnVisit, pagination, "");
		List<ReturnVisitVo> returnVisitVos = pagination2.getRows();
		System.out.println(returnVisitVos.size());
		for (ReturnVisitVo returnVisitVo : returnVisitVos) {
			System.out.println(returnVisitVo.getReturnvisitId());
		}
	}
	
	public static void  main(String[] args){
//		String a = "ceshi";
//		String b = "ceshi";
//		System.out.println(a.equals(b));
		System.out.println("32测试");
		
	}
	
	public class threadMy implements Runnable{
		public void start(){
			System.out.println("ming名字:+"+Thread.currentThread().getName());
		}
		public void run(){
			System.out.println("ming名字 run:+"+Thread.currentThread().getName());
		}
	}

}
