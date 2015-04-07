package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.creditease.rc.report.excel.*;
import com.creditease.rc.service.IJsGridReportBaseXLSXService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.domain.CustomerConsult;
import com.creditease.rc.domain.CustomerConsultPool;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.report.excel.ExcelUtils;
import com.creditease.rc.report.excel.JsGridReportBase;
import com.creditease.rc.report.excel.TableData;
import com.creditease.rc.service.IInquirePoolOfficeService;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.util.SmpWSUtil;
import com.creditease.rc.vo.CustomerConsultPoolVo;

@Controller
@RequestMapping(value = "inquirePoolOfficeController")
public class InquirePoolOfficeController {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@Resource
	private IInquirePoolOfficeService inquirePoolOfficeService;
    @Resource
    private IJsGridReportBaseXLSXService jsGridReportBaseXLSXService;

    @Resource
	private SmpWSUtil smpWSUtil;

	/**
	 * 查询受理咨询列表
	 * 
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @param customerConsultPoolVo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "inquirePoolOfficeList")
	@ResponseBody
	public Pagination inquirePoolOfficeList(String page, String rows,
			String sort, String order, String paramJsonMap,
			CustomerConsultPoolVo customerConsultPoolVo, HttpSession session) {
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		Map<String, String> pramMap = new HashMap<String, String>();
		// 列表权限问题记得加上哦
		List<String> authList = SpringSecurityUtils.getAuthList(session);
		String sqlsid = "";
		if (authList.size() > 0) {
			for (String e : authList) {
				if (sqlsid != null) {
					sqlsid = sqlsid + "'" + e + "'" + ",";
				} else {
					sqlsid = "'" + e + "'" + ",";
				}
			}
			sqlsid = sqlsid.substring(0, sqlsid.length() - 1);
		}
		// 判断 登录人角色
		Set<String> userAuthoritiesSet = smpWSUtil.getUserAuthorities();

		userAuthoritiesSet.contains("ROLE_LOAN_OFFICER");
		if (userAuthoritiesSet.contains("ROLE_LOAN_OFFICER")) {
			pramMap.put("role", "ROLE_LOAN_OFFICER");
		} else {
			pramMap.put("role", "ELSE");
		}
		pramMap.put("authList", sqlsid);
		pramMap.put("sort", sort);
		pramMap.put("order", order);

		if (!"".equals(paramJsonMap) && paramJsonMap != null) {
			Map<String, String> temp = JsonUtil.getObject(paramJsonMap);
			String customerName = temp.get("customerName");
			String mobilePhone = temp.get("mobilePhone");
			String consultWay = temp.get("consultWay");
			String acceptConsultState = temp.get("acceptConsultState");
			String beginDistributeDate1 = temp.get("beginDistributeDate");
			String endDistributeDate1= temp.get("endDistributeDate");
			String beginDistributeDate=null;
			String endDistributeDate=null;
			if(!("").equals(beginDistributeDate1)){
				beginDistributeDate=beginDistributeDate1+" 00:00:00";
			}
			if(!("").equals(endDistributeDate1)){
				endDistributeDate=endDistributeDate1+" 23:59:59";
			}
			
			String beginSentDate=null;
			String endSentDate=null;
			String beginSentDate1 = temp.get("beginSentDate");
			String endSentDate1 = temp.get("endSentDate");
			
			if(!("").equals(beginSentDate1)){
				beginSentDate=beginSentDate1+" 00:00:00";
			}
			if(!("").equals(endSentDate1)){
				endSentDate=endSentDate1+" 23:59:59";
			}
			String province = temp.get("province");
			String city = temp.get("city");
			String area = temp.get("area");
			String history = temp.get("history");
			

			pramMap.put("customerName", customerName);
			pramMap.put("mobilePhone", mobilePhone);
			pramMap.put("consultWay", consultWay);
			pramMap.put("acceptConsultState", acceptConsultState);
			pramMap.put("beginDistributeDate", beginDistributeDate);
			pramMap.put("endDistributeDate", endDistributeDate);
			pramMap.put("beginSentDate", beginSentDate);
			pramMap.put("endSentDate", endSentDate);
			pramMap.put("province", province);
			pramMap.put("city", city);
			pramMap.put("area", area);
			pramMap.put("history", history);
			

		}
		//如果consultPoolId不为空就put   Map中
		if(customerConsultPoolVo.getConsultPoolId()!=null){
			String consultPoolId=(customerConsultPoolVo.getConsultPoolId()).toString();
			pramMap.put("consultPoolId", consultPoolId);
		}
		pagination = inquirePoolOfficeService.selectInquirePoolOfficeList(
				pagination, pramMap);
		return pagination;
	}

	/**
	 * 查询 咨询池
	 * @param customerConsultPool CustomerConsultPool
	 * @return CustomerConsultPool
	 */
	@RequestMapping(value = "selectCustomerConsultPool")
	public @ResponseBody
	CustomerConsultPool selectCustomerConsult(CustomerConsultPool customerConsultPool){
		return inquirePoolOfficeService.selectCustomerConsultPool(customerConsultPool);
	}
	
	// 查看受理咨询详情
	/**
	 * 
	 * @param telphone
	 * @return List<CustomerConsult>
	 */
	@RequestMapping(value = "history")
	public @ResponseBody
	List<CustomerConsultPoolVo> selectHistory(String telphone) {
		return inquirePoolOfficeService.selectHistory(telphone);
	}

	// 导出数据
	@RequestMapping("downloadExcel")
	public void downloadExcel(String page, String rows, String sort,
			String order, CustomerConsultPoolVo customerConsultPoolVo,
			HttpServletRequest request, HttpServletResponse response) {

		Pagination pagination = new Pagination();
		pagination = inquirePoolOfficeService.downloadExcel(pagination,
				customerConsultPoolVo, sort, order, request);
		List<CustomerConsultPoolVo> list = pagination.getRows();
		String title = "受理咨询列表";
		String[] hearders = new String[] { "收件日期", "客户姓名", "性别", "年龄", "城市",
				"经营年限", "借款额度(万)", "推荐渠道", "咨询状态", "业务状态", "分中心营业部名称",
				"分受理客户经理", "分件日期", "反馈日期" };// 表头数组
		String[] fields = new String[] { "receiveDate", "customerName",
				"conSexShow", "conAge", "cityShow", "businessPeriodShow",
				"borrowAmount", "channelShow", "acceptConsultStateShow",
				"businessStatusText", "teamdepartmentName", "loanOfficerName",
				"sentDate", "feedbackDate" };// 对象属性数组
		TableData td = ExcelUtils.createTableData(list,
				ExcelUtils.createTableHeader(hearders), fields);
		try {
			/*JsGridReportBase report = new JsGridReportBase(request, response);
			report.exportToExcel(title, "", td);*/
            jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
            jsGridReportBaseXLSXService.exportToExcel(title, "", td);
        } catch (Exception e) {
			e.printStackTrace();
		}
	}
}
