package com.creditease.rc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.creditease.rc.report.excel.*;
import com.creditease.rc.service.IJsGridReportBaseXLSXService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.utils.JsonUtil;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.report.excel.ExcelUtils;
import com.creditease.rc.report.excel.JsGridReportBase;
import com.creditease.rc.report.excel.TableData;
import com.creditease.rc.service.IExportExcelService;
import com.creditease.rc.util.CommonUtil;

/**
 * 
 * Title:ExportController.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-5上午09:47:14
 * @author 王毅峰
 * @version v2.0
 */
@Controller
@RequestMapping("excelExport")
public class ExportController {
	
	@Resource
	private IExportExcelService excelService;
    @Resource
    private IJsGridReportBaseXLSXService jsGridReportBaseXLSXService;

    /**
	 * 量化管理报表表格数据查询
	* @author wyf  
	* @param param 
	* @param page 
	* @param rows 
	* @return Pagination  
	* @throws
	 */
	@RequestMapping(value="loadQMData")
	@ResponseBody
	public Pagination loadQMData(String param,String page, String rows,HttpServletRequest request){
		HttpSession session=request.getSession();
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
		Map map = new HashMap();
		if(CommonUtil.isNotEmpty(param)){
			map = JsonUtil.getMapFromJsonObjStr(param);
		}
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page))
		{pagination.setPage(Integer.valueOf(page));	}
		if (!StringUtils.isBlank(rows))
		{pagination.setPageSize(Integer.valueOf(rows));}
		try {
			//数据权限
			map.put("authList", sqlsid);
			pagination = excelService.getQMData(map,pagination);
			return pagination;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 量化管理报表Excel导出
	* @param param 
	* @param request 
	* @param response 
	* @throws Exception  
	 */
	@RequestMapping(value="exportQMExcel")
	public void exportQMExcel(String param,HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();
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
        response.setContentType("application/msexcel;charset=UTF-8");
        Map map = JsonUtil.getMapFromJsonObjStr(param);
        //增加查询权限
        map.put("authList", sqlsid);
        Pagination pagination = new Pagination();
        pagination.setPageSize(0);
        pagination = excelService.getQMData(map,pagination);
        
        List<Map> list = pagination.getRows();//获取数据
        String title = "量化管理报表";
        String[] hearders = new String[] {"分公司", "业务编号", "信息来源", "户籍乡镇", "经营乡镇","产品类型",
        		"申请额度(元)", "申请客户经理", "最大借款额度(元)", "首次审批额度(元)","合同金额(元)", "放款日期", "放款确认日期","财务打款日期",
        		"借款用途","一级借款用途行业", "借款用途行业","经营现金流入项目频率","经营现金流入项目行业","家庭现金流入项目","家庭现金流入频率","循环贷标记","贷款余额",
		"主要收入来源行业","主要收入来源明细","主要收入频次","收入项目数"};//表头数组
        String[] fields = new String[] {"comName", "busNum", "infoSor", "hTown", "mTown","planName",
        		"applyLimit","offerName","maxCapital","firAmount","contract_amount","loanDate","cofDate","trade_time",
        		"loanUse","loanUseIndStair","loanUseInd","manageFre","manageInd","homePro","homeFre","revolving_credit","loanbalance",
				"industryName","industryDetails","incomeFrequency","projectCT"};//对象属性数组
        TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders),fields);
     /*   JsGridReportBase report = new JsGridReportBase(request, response);
        report.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);*/
        jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
        jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);

    }
	
	/**
	 * 客户JINGLI绩效表格数据查询
	* @author wyf  
	* @param param 
	* @param page 
	* @param rows 
	* @return Pagination   
	 */
	@RequestMapping(value="loadCMPData")
	@ResponseBody
	public Pagination loadCMPData(String param,String page, String rows){
		Map map = new HashMap();
		if(CommonUtil.isNotEmpty(param)){
			map = JsonUtil.getMapFromJsonObjStr(param);
		}
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page))
		{pagination.setPage(Integer.valueOf(page));	}
		if (!StringUtils.isBlank(rows))
		{pagination.setPageSize(Integer.valueOf(rows));}
		try {
			pagination = excelService.getCMPData(map,pagination);
			return pagination;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 客户JINGLI绩效报表Excel导出
	* @author wyf  
	* @Description: 
	* @param param 
	* @param request 
	* @param response 
	* @throws Exception 
	 */
	@RequestMapping(value="exportCMPExcel")
	public void exportCMPExcel(String param,HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("application/msexcel;charset=UTF-8");
        Map map = JsonUtil.getMapFromJsonObjStr(param);
        Pagination pagination = new Pagination();
        pagination.setPageSize(0);
        pagination = excelService.getCMPData(map,pagination);
        
        List<Map> list = pagination.getRows();//获取数据
        
        String title = "客户经理绩效报表";
        String[] hearders = new String[] {"客户经理", "分公司", "放款组数", "放款金额(元)", "回款组数","回款金额(元)", 
        		"业务周期", "最长业务周期", "最短业务周期"};//表头数组
        String[] fields = new String[] {"loanOfficerName", "companyName", "lendNum", "lendAmount", "returnedNum","returnedAmount",
        		"businessCycle","maxBusinessCycle","minBusinessCycle"};//对象属性数组
        TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders),fields);
    /*    JsGridReportBase report = new JsGridReportBase(request, response);
        report.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);*/
        jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
        jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);

    }
	
	/**
	 * 业务详情报表表格数据查询
	* @author wyf  
	* @param param 
	* @param page 
	* @param rows 
	* @return Pagination 
	* @throws
	 */
	@RequestMapping(value="loadBDData")
	@ResponseBody
	public Pagination loadBDData(String param,String page, String rows){
		Map map = new HashMap();
		if(CommonUtil.isNotEmpty(param)){
			map = JsonUtil.getMapFromJsonObjStr(param);
			String auditStatus  = (String) map.get("auditStatus");
			if(CommonUtil.isNotEmpty(auditStatus)){
				String auditStr = "";
				String[] audit = auditStatus.split(",");
				for (String string : audit) {
					auditStr = auditStr + "'" + string + "',";
				}
				auditStr = auditStr.substring(0, (auditStr.length() - 1));
				map.put("auditStatus", auditStr);
			}
			
		}
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page))
		{pagination.setPage(Integer.valueOf(page));	}
		if (!StringUtils.isBlank(rows))
		{pagination.setPageSize(Integer.valueOf(rows));}
		try {
			pagination = excelService.getBDData(map, pagination);
			return pagination;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 业务详情报表Excel导出
	* @author wyf  
	* @param param 
	* @param request 
	* @param response 
	* @throws Exception 
	 */
	@RequestMapping(value="exportBDExcel")
	public void exportBDExcel(String param,HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("application/msexcel;charset=UTF-8");
        Map map = JsonUtil.getMapFromJsonObjStr(param);
        Pagination pagination = new Pagination();
        pagination.setPageSize(0);
        pagination = excelService.getBDData(map, pagination);
        
        List<Map> list = pagination.getRows();//获取数据
        
        String title = "业务详情报表";
        String[] hearders = new String[] {"分公司", "业务编号","身份证号","信息来源","经营乡镇","产品类型","业务状态", 
        		"申请额度(元)","申请时间","申请客户经理", "最大借款额度(元)","创建时间","提交时间","首次放款登记日期","最终放款登记日期",
        		"放款日期","放款登记客户经理","放款确认日期","财务转账日期","借款用途","一级借款用途行业","借款用途行业","首次审批日期","最终审批日期","首次审批额度(元)","最终审批额度(元)","合同金额(元)","循环贷标记","贷款余额",
		"主要收入来源行业","主要收入来源明细","主要收入频次","收入项目数"};//表头数组
        String[] fields = new String[] {"comName", "busNum", "identityNum", "infoSor", "mTown","planName","auditStatusShow",
        		"applyLimit","applyTime","offerName","maxCapital","create_date","submitTime","firLoanTime","endLoanTime",
        		"loanDate","loanOfficer","cofDate","tradeDate","loanUse","loanUseIndStair","loanUseInd","minExamDate","maxExamDate","firAmount","endAmount","contract_amount","revolving_credit","loanbalance",
		"industryName","industryDetails","incomeFrequency","projectCT"};//对象属性数组
        TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders),fields);
        /*JsGridReportBase report = new JsGridReportBase(request, response);
        report.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);*/
        jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
        jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);

    }
}
