package com.creditease.rc.action;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.domain.CustomerConsult;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.RefuseReason;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.report.excel.ExcelUtils;
import com.creditease.rc.report.excel.JsGridReportBase;
import com.creditease.rc.report.excel.TableData;
import com.creditease.rc.service.IRefuseReasonService;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.vo.RefuseReasonVo;


@Controller
@RequestMapping(value = "RefuseReasonController")
public class RefuseReasonController {
    @Resource
    private IJsGridReportBaseXLSXService jsGridReportBaseXLSXService;

    @Resource
	private IRefuseReasonService refuseReasonService;
	@RequestMapping(value="insert")
	public @ResponseBody Message insert(RefuseReason  refuseReason){
		return refuseReasonService.insert(refuseReason);
	}
	
	@RequestMapping(value="refuse")
	public @ResponseBody Message refuse(Long creditapplicationId,String section,String refuseReasons,String auditStatus){
		return refuseReasonService.updateRefuse(creditapplicationId,section,refuseReasons,auditStatus);
	}
	/**
	 * 查询客户放弃列表
	 * @param customerGiveUpList
	 */
	@RequestMapping(value="customerGiveUpList")
	public @ResponseBody
	Pagination customerGiveUpList(@RequestParam(required = false) String page,
			@RequestParam(required = false) String rows, HttpSession session, String paramJsonMap, String sort,
			String order) {
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		// 添加权限查询
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
		Map<String, String> pramMap = new HashMap<String, String>();

		pramMap.put("authList", sqlsid);
		pramMap.put("sort", sort);
		pramMap.put("order", order);

		if (!"".equals(paramJsonMap) && paramJsonMap != null) {
			Map<String, String> temp = JsonUtil.getObject(paramJsonMap);
			String businessNumber = temp.get("businessNumber");
			String name = temp.get("name");
			String companyId = temp.get("companyId");
			String codeKey = temp.get("customerQiutReason");
			pramMap.put("businessNumber", businessNumber);
			pramMap.put("name", name);
			pramMap.put("companyId", companyId);
			pramMap.put("codeKey", codeKey);

		}
		pagination= refuseReasonService.customerGiveUpList(pagination, pramMap);
		return pagination;
	}
	/**
	 *  * 客户放弃Excel导出，获取的数据格式是List<JavaBean>
	 * @param request 请求
	 * @param response 回应
	 * @throws Exception 异常
	 */
	@RequestMapping(value="exportExcelCustimerGiveUp")
	public void exportExcelCustimerGiveUp(RefuseReasonVo refuseReason,
			String paramJsonMap,HttpSession session,HttpServletRequest request,HttpServletResponse response,String sort,
			String order) throws Exception{
        response.setContentType("application/msexcel;charset=UTF-8");
        Pagination pagination = new Pagination();
        pagination.setPageSize(0);

     // 添加权限查询
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
		Map<String, String> pramMap = new HashMap<String, String>();

		pramMap.put("authList", sqlsid);
		pramMap.put("sort", sort);
		pramMap.put("order", order);
		//判断导出查询条件是否为空
		if (!"".equals(refuseReason) && refuseReason != null) {
			String businessNumber =refuseReason.getBusinessNumber();
			String name = refuseReason.getName();
			String companyId = refuseReason.getCompanyId();
			String codeKey = refuseReason.getCodeKey();
			pramMap.put("businessNumber", businessNumber);
			pramMap.put("name", name);
			pramMap.put("companyId", companyId);
			pramMap.put("codeKey", codeKey);

		}
		pagination = refuseReasonService.customerGiveUpList(pagination, pramMap);
        List<RefuseReasonVo> list = pagination.getRows();//获取数据
        String title = "客户放弃列表";
        if(list.size()!=0){
        String[] hearders = new String[] {"业务单号","借款人姓名","分公司名称 ","操作人","操作时间","客户放弃原因"};//表头数组
        String[] fields = new String[] {"businessNumber", "name","companyName","operatorName","operateDate","refuseReasons"};//对象属性数组
        TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders),fields);
    /*	JsGridReportBase report = new JsGridReportBase(request, response);
    	report.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);*/
            jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
            jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);

        }else{
        	List<RefuseReasonVo> list2=new ArrayList<RefuseReasonVo>();
        	RefuseReasonVo refuseReason2=new RefuseReasonVo();
        	list2.add(refuseReason2);
        	 String[] hearders = new String[] {"业务单号","借款人姓名","分公司名称 ","操作人","操作时间","客户放弃原因"};//表头数组
             String[] fields = new String[] {"","","","","",""};//对象属性数组
        	TableData td = ExcelUtils.createTableData(list2, ExcelUtils.createTableHeader(hearders),fields);
        	/*JsGridReportBase report = new JsGridReportBase(request, response);
        	report.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);*/
            jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
            jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);

        }
	}
	/**
	 * @author hongjieluo
	 * 查询拒贷列表
	 * @param deniedLoansList
	 */
	@RequestMapping(value="deniedLoansList")
	public @ResponseBody
	Pagination deniedLoansList(@RequestParam(required = false) String page,
			@RequestParam(required = false) String rows, HttpSession session, String paramJsonMap, String sort,
			String order) {
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		// 添加权限查询
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
		Map<String, String> pramMap = new HashMap<String, String>();

		pramMap.put("authList", sqlsid);
		pramMap.put("sort", sort);
		pramMap.put("order", order);

		if (!"".equals(paramJsonMap) && paramJsonMap != null) {
			Map<String, String> temp = JsonUtil.getObject(paramJsonMap);
			String businessNumber = temp.get("businessNumber");
			String name = temp.get("name");
			String companyId = temp.get("companyId");
			pramMap.put("businessNumber", businessNumber);
			pramMap.put("name", name);
			pramMap.put("companyId", companyId);

		}
		pagination = refuseReasonService.deniedLoansList(pagination, pramMap);
		return pagination;
	}
	/**
	 * @author hongjieluo
	 *  拒贷Excel导出，获取的数据格式是List<JavaBean>
	 * @param request 请求
	 * @param response 回应
	 * @throws Exception 异常
	 */
	@RequestMapping(value="exportExcelRefuseList")
	public void exportExcelRefuseList(RefuseReasonVo refuseReason,
			String paramJsonMap,HttpSession session,HttpServletRequest request,HttpServletResponse response,String sort,
			String order) throws Exception{
        response.setContentType("application/msexcel;charset=UTF-8");
        Pagination pagination = new Pagination();
        pagination.setPageSize(0);
        Map<String, String> pramMap = new HashMap<String, String>();
        // 添加权限查询
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
		pramMap.put("authList", sqlsid);
		pramMap.put("sort", sort);
		pramMap.put("order", order);
		//判断导出查询条件是否为空
		if (!"".equals(refuseReason) && refuseReason != null) {
			String businessNumber =refuseReason.getBusinessNumber();
			String name = refuseReason.getName();
			String companyId = refuseReason.getCompanyId();
			pramMap.put("businessNumber", businessNumber);
			pramMap.put("name", name);
			pramMap.put("companyId", companyId);

		}
		pagination = refuseReasonService.deniedLoansList(pagination, pramMap);
        List<RefuseReasonVo> list = pagination.getRows();//获取数据
        String title = "拒贷列表";
        if(list.size()!=0){
        String[] hearders = new String[] {"业务单号","借款人姓名","分公司名称 ","操作人","操作时间","拒贷原因"};//表头数组
        String[] fields = new String[] {"businessNumber", "name","companyName","operatorName","operateDate","refuseReasons"};//对象属性数组
        TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders),fields);
    	/*JsGridReportBase report = new JsGridReportBase(request, response);
    	report.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);*/
            jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
            jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);

        }else{
        	List<RefuseReasonVo> list2=new ArrayList<RefuseReasonVo>();
        	RefuseReasonVo refuseReason2=new RefuseReasonVo();
        	list2.add(refuseReason2);
        	 String[] hearders = new String[] {"业务单号","借款人姓名","分公司名称 ","操作人","操作时间","拒贷原因"};//表头数组
             String[] fields = new String[] {"","","","","",""};//对象属性数组
        	TableData td = ExcelUtils.createTableData(list2, ExcelUtils.createTableHeader(hearders),fields);
        /*	JsGridReportBase report = new JsGridReportBase(request, response);
        	report.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);*/
            jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
            jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);

        }
	}


	/*
	导出修改
     */
	@RequestMapping(value = "exportModify")
	public void exportModify(RefuseReasonVo refuseReason, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/msexcel;charset=UTF-8");
		Pagination pagination = new Pagination();
		pagination.setPageSize(0);
		Map<String, String> pramMap = new HashMap<String, String>();

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
		pramMap.put("authList", sqlsid);

		if (!"".equals(refuseReason) && refuseReason != null) {
			String businessNumber = refuseReason.getBusinessNumber();
			String name = refuseReason.getName();
			String companyId = refuseReason.getCompanyId();
			pramMap.put("businessNumber", businessNumber);
			pramMap.put("name", name);
			pramMap.put("companyId", companyId);

		}
		pagination = refuseReasonService.deniedLoansList(pagination, pramMap);
		List<RefuseReasonVo> list = pagination.getRows();//获取数据
		String title = "拒贷列表";


		String[] hearders = new String[]{"业务单号", "借款人姓名", "分公司名称 ", "操作人", "操作时间", "拒贷原因"};
		String[] fields = new String[]{"businessNumber", "name", "companyName", "operatorName", "operateDate", "refuseReasons"};


		if (list.isEmpty()) {
			list.add(new RefuseReasonVo());
			fields = new String[]{"", "", "", "", "", "", "", "", "", "", "", ""};
		}
		TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders), fields);
		try {
			jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request, response);
			jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
