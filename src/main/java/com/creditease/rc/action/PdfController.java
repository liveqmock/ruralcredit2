package com.creditease.rc.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.service.IBorrowingProductsService;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.vo.AdvancePlanVo;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

@Controller
@RequestMapping(value = "PdfController")
public class PdfController {
	
	@Resource
	private IBorrowingProductsService borrowingProductsService;
	@RequestMapping(value = "advanceReturnPlanResult")
	public @ResponseBody  AdvancePlanVo advanceReturnPlanResult(Long departmentId, BigDecimal ContractMoney,String lenderDate,Long  productInfoId ,Integer periodCount,String repaymentType){
		GregorianCalendar gregorianCalendar = null;
		try {
			gregorianCalendar = DateUtil.getGregorianCalendar(new SimpleDateFormat("yyyy-MM-dd").parse(lenderDate));
		} catch (ParseException e) {
			gregorianCalendar = DateUtil.getGregorianCalendar(new Date());
			e.printStackTrace();
		}
		return borrowingProductsService.advanceReturnPlanResult(departmentId, ContractMoney, new XMLGregorianCalendarImpl(gregorianCalendar), productInfoId, periodCount,repaymentType);
	}
	
	
	@RequestMapping(value = "getProductList")
	public @ResponseBody List<Map> getProductList(String departMentId){
		 List<Map> list = new ArrayList<Map>();
		if(departMentId != null){
			list = borrowingProductsService.getProductList(Integer.valueOf(departMentId));
		}
		return list;
	}
}
