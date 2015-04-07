/**
 * Title:DataBestowalController.java
 * Description:
 */
package com.creditease.rc.action;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.app.smp.EmployeeDTO;
import com.creditease.rc.common.DepartmentEntity;
import com.creditease.rc.domain.*;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.report.excel.ExcelUtils;
import com.creditease.rc.report.excel.TableData;
import com.creditease.rc.service.ICustomerConsultService;
import com.creditease.rc.service.IDataBestowalService;
import com.creditease.rc.service.IJsGridReportBaseXLSXService;
import com.creditease.rc.service.IRural2CreditService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.util.SmpWSUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Title:DataBestowalController.java
 * Description:
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 
 * @author 韩大年
 * @version v1.0
 *          2013-6-4
 */
@Controller
@RequestMapping(value = "dataBestowalController")
public class DataBestowalController {
	/**
	 * 
	 * @param binder
	 *            WebDataBinder对象
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * @Description 默认构造器
	 */
	public DataBestowalController() {
		// TODO Auto-generated constructor stub
	}

	@Resource
	private IDataBestowalService dataBestowalService;
	@Resource
	private ICustomerConsultService customerConsultService;
	@Resource
	private SmpWSUtil smpWSUtil;
	@Resource
	private IRural2CreditService rural2CreditService;
	@Resource
	private IJsGridReportBaseXLSXService jsGridReportBaseXLSXService;

	/**
	 * 根据营业部更新下面所有员工的权限
	 * @param objectIds
	 * @param departmentId
	 * @return
	 */
	@RequestMapping("updatesynchroAurhorityByDepartment")
	public @ResponseBody Message updatesynchroAurhorityByDepartment(String customerIds,String creditApplicationIds,String departmentId){
		List<Integer> customerIdsList = null;
		List<Integer> creditApplicationIdsList = null;
		if (customerIds != null) {
			customerIdsList = JsonUtil.getArray(customerIds);
		}
		if (creditApplicationIds != null) {
			creditApplicationIdsList = JsonUtil.getArray(creditApplicationIds);
		}
		Message message = dataBestowalService.updatesynchroAurhorityByDepartment(customerIdsList,creditApplicationIdsList,departmentId);
		return message;
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 根据分公司id查询系统中所有的客户经理
	 * @param creditApplication
	 * @return
	 *         2013-6-5
	 */
	@RequestMapping(value = "querySysloanOfficerList")
	public @ResponseBody
	List querySysloanOfficerList(CreditApplication creditApplication) {

		if (null != creditApplication && CommonUtil.isEmpty(creditApplication.getCompanyId())) {
			creditApplication.setCompanyId(SpringSecurityUtils.getCurrentUser().getAreaDepartmentId().toString());
		}
		return this.dataBestowalService.querySysloanOfficerList(creditApplication);
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 根据客户经理的departmentId，查询所有客户经理,去除自己
	 * @param departmentId
	 * @return
	 *         2013-6-5
	 */
	@RequestMapping(value = "queryloanOfficerNameListByDepartmentId")
	@ResponseBody
	public List<EmployeeDTO> queryloanOfficerNameListByDepartmentId(String departmentId, String loanOfficerId) {
		List<EmployeeDTO> employeeDTOList = null;
		List<EmployeeDTO> employeeDTOListTemp = new ArrayList<EmployeeDTO>();
		employeeDTOList = this.smpWSUtil.getIterUsersByDepId(departmentId);
		if (CommonUtil.isNotEmpty(employeeDTOList)) {
			for (EmployeeDTO employeeDTO : employeeDTOList) {
				Integer comEmpId = employeeDTO.getComEmpId();
				if (null == comEmpId) {
					continue;
				}
				if (comEmpId.toString().equals(loanOfficerId)) {
					continue;
				}
				employeeDTOListTemp.add(employeeDTO);
			}
		}
		return employeeDTOListTemp;
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 查询客户经理下的申请单
	 * @param creditApplication
	 * @param page
	 * @param rows
	 * @return
	 *         2013-6-4
	 */
	@RequestMapping(value = "selectBestowalCreditApplicationByPagination")
	@ResponseBody
	public Pagination selectBestowalCreditApplicationByPagination(CreditApplication creditApplication,
			@RequestParam(required = false) String page, @RequestParam(required = false) String rows, String querayFlag) {
		Pagination pagination = new Pagination();
		if ("T".equals(querayFlag)) {
			pagination.setItems(new ArrayList());
		} else {
			if (CommonUtil.isNotEmpty(page)) {
				pagination.setPage(Integer.valueOf(page));
			}
			if (CommonUtil.isNotEmpty(rows)) {
				pagination.setPageSize(Integer.valueOf(rows));
			}
			// 1 查找数据赠与
			pagination = dataBestowalService.selectBestowalCreditApplicationByPagination(creditApplication, pagination);

			/*2014-12-22查询逾期数据，由贷后切换为综合信贷
			List dataBestowalList = pagination.getRows();
			// 赠与数据列表的所有ID
			List<Long> creditapplicationIdList_Bestowal = new ArrayList<Long>();

			for (int i = 0; i < dataBestowalList.size(); i++) {
				Set<Map.Entry<String, Object>> set = ((HashMap) dataBestowalList.get(i)).entrySet();
				for (Iterator<Map.Entry<String, Object>> it = set.iterator(); it.hasNext();) {
					Map.Entry<String, Object> entry = it.next();
					if (entry.getKey().equals("CREDITAPPLICATION_ID")) {
						Long creditapplicationId_Bestowal = Long.parseLong(entry.getValue().toString());

						creditapplicationIdList_Bestowal.add(creditapplicationId_Bestowal);

					}

				}
			}
			// 2查找逾期数据
			List<LocalOfficeIdDTO> localOfficeIdDTOList = new ArrayList<LocalOfficeIdDTO>();
			String loanOfficerId = creditApplication.getLoanOfficerId();
			System.out.println(loanOfficerId);

			LocalOfficeIdDTO localOfficeIdDTO = new LocalOfficeIdDTO();
			// 员工编号列表
			List<LocalSellIdDTO> localSellIdDTOsList = new ArrayList<LocalSellIdDTO>();
			// 员工编号
			LocalSellIdDTO localSellIdDTO = new LocalSellIdDTO();
			localSellIdDTO.setSellId(loanOfficerId);

			// 放入列表中
			localSellIdDTOsList.add(localSellIdDTO);
			// 员工放入2
			localOfficeIdDTO.setLocalSellIdDTOs(localSellIdDTOsList);
			localOfficeIdDTOList.add(localOfficeIdDTO);
			LocalOverdueInfoResponse localOverdueInfoResponse = rural2CreditService.overdueInfo(localOfficeIdDTOList);

			// 逾期数据列表的所有ID
			List<Long> creditApplicationIdList_Overdue = localOverdueInfoResponse.getCreditApplicationIdList();

			// 3 判断数据赠与的id是否在逾期数据列表里面

			// 数据赠与和逾期列表的公共ID
			creditapplicationIdList_Bestowal.retainAll(creditApplicationIdList_Overdue);

			// 4给数据赠与和逾期列表的公共ID赋 是否逾期字段 值
			List dataBestowalList_ = pagination.getRows();

			for (int i = 0; i < creditapplicationIdList_Bestowal.size(); i++) {
				{
					// 是否逾期字段
					String discount = "";
					for (int a = 0; a < dataBestowalList_.size(); a++) {
						Set<Map.Entry<String, Object>> set = ((HashMap) dataBestowalList_.get(a)).entrySet();

						for (Iterator<Map.Entry<String, Object>> it = set.iterator(); it.hasNext();) {
							Map.Entry<String, Object> entry = it.next();
							if (entry.getKey().equals("CREDITAPPLICATION_ID")) {
								String creditapplicationId_Bestowal_ = entry.getValue().toString();

								// 数据赠与并逾期的id
								String creditapplicationId_BestowalAndOverDue = creditapplicationIdList_Bestowal.get(i)
										.toString();
								if (creditapplicationId_Bestowal_.equals(creditapplicationId_BestowalAndOverDue)) {
									discount = "1";
								}
							}

						}
						for (Iterator<Map.Entry<String, Object>> it = set.iterator(); it.hasNext();) {
							Map.Entry<String, Object> entry = it.next();
							if (entry.getKey().equals("DISCOUNT") && discount.equals("1")) {
								entry.setValue("1");
							}
						}
						discount = "";
					}
				}
			}*/
		}
		return pagination;
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 数据赠与
	 * @param dataBestowal
	 * @param creditApplicationIds
	 * @return
	 *         2013-6-5
	 */
	@RequestMapping(value = "insertDataBestowalAndDataBestowalDetail")
	@ResponseBody
	public Message insertDataBestowalAndDataBestowalDetail(DataBestowal dataBestowal, String creditApplicationIds,
			String customerConsult) {
		// 字符串 转换 数组
		List<Integer> ids = JsonUtil.getArray(creditApplicationIds);
		Long[] IdsLong = new Long[ids.size()];
		for (int i = 0; i < ids.size(); i++) {
			IdsLong[i] = ids.get(i).longValue();
		}
		Message message = new Message();
		try {
			if (customerConsult != null && "1".equals(customerConsult)) {
				message = this.dataBestowalService.updateCustomerConsultAurhority(dataBestowal, IdsLong);
			} else {
				message = this.dataBestowalService.insertDataBestowalAndDataBestowalDetail(dataBestowal, IdsLong);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setSuccess(false);
			message.setMsg("功能异常：" + e.getMessage());
		}
		return message;
	}

	/**
	 * smp取部门列表
	 * 
	 * @return 返回部门列表
	 */
	@RequestMapping(value = "getDepartmentList")
	public @ResponseBody
	List<DepartmentEntity> getDepartmentList() {
		List<DepartmentEntity> departmentList = smpWSUtil.getDepartmentList();
		return departmentList;
	}

	/**
	 * 分页列表 查询
	 * 
	 * @param record 数据转增对象
	 * @param page 页
	 * @param rows 行
	 * @return 分页列表
	 */
	@RequestMapping(value = "selectDataBestowalPaigantion")
	public @ResponseBody
	Pagination selectDataBestowalPaigantion(DataBestowal record, String page, String rows, String businessNumber) {
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		pagination = dataBestowalService.selectBestowalPagination(record, pagination, businessNumber);
		return pagination.getRows() == null ? new Pagination(new ArrayList(), 0, 0) : pagination;
	}

	/**
	 * 查询详情
	 * 
	 * @param record 数据转增对象
	 * @param page 页
	 * @param rows 行
	 * @return 分页列表
	 */
	@RequestMapping(value = "selectByDataBestowalDetail")
	public @ResponseBody
	List<DataBestowalDetail> selectByDataBestowalDetail(String dataBestowalId) {
		List<DataBestowalDetail> bestowalDetails = new ArrayList<DataBestowalDetail>();
		if (dataBestowalId != null) {
			bestowalDetails = dataBestowalService.selectDataBestowalDetailByDataBestowalId(Long
					.parseLong(dataBestowalId));
		}
		return bestowalDetails;
	}

	/**
	 * 查询详情
	 * 
	 * @param record 数据转增对象
	 * @param page 页
	 * @param rows 行
	 * @return 分页列表
	 */
	@RequestMapping(value = "selectCredit")
	public @ResponseBody
	List<HashMap> selectCredit(String dataBestowalId, String customerConsult) {
		List<HashMap> bestowalDetailsMap = new ArrayList<HashMap>();
		if (dataBestowalId != null) {
			if (customerConsult != null && "1".equals(customerConsult)) {
				bestowalDetailsMap = dataBestowalService.selectCustomerConsult(Long.parseLong(dataBestowalId));
			} else {
				bestowalDetailsMap = dataBestowalService.selectCredit(Long.parseLong(dataBestowalId));
			}
		}
		return bestowalDetailsMap;
	}

	/**
	 * 更新申请权限
	 * 
	 * @param objectId
	 * @param oldLoanOfficerId
	 * @param loanOfficerId
	 * @return
	 */
	@RequestMapping(value = "updateCreditApplicationAuthorization")
	public @ResponseBody
	Message updateCreditApplicationAuthorization(String objectIds, Integer loanOfficerId) {
		List<Integer> objectIdsList = null;
		if (objectIds != null) {
			objectIdsList = JsonUtil.getArray(objectIds);
		}
		return dataBestowalService.updateCreditApplicationAuthorization(objectIdsList, loanOfficerId);
	};

	/**
	 * 更新咨询权限
	 * 
	 * @param objectId
	 * @param oldLoanOfficerId
	 * @param loanOfficerId
	 * @return
	 */
	@RequestMapping(value = "updateCustomerConsultAuthorization")
	public @ResponseBody
	Message updateCustomerConsultAuthorization(String objectIds, int loanOfficerId) {
		List<Integer> objectIdsList = null;
		if (objectIds != null) {
			objectIdsList = JsonUtil.getArray(objectIds);
		}
		return dataBestowalService.updateCustomerConsultAuthorization(objectIdsList, loanOfficerId);
	};

	@RequestMapping(value = "selectCustomerConsultList")
	@ResponseBody
	public Pagination selectCustomerConsultList(CustomerConsult customerConsult,
			@RequestParam(required = false) String page, @RequestParam(required = false) String rows, String querayFlag) {
		Pagination pagination = new Pagination();
		if ("T".equals(querayFlag)) {
			pagination.setItems(new ArrayList());
		} else {
			if (CommonUtil.isNotEmpty(page)) {
				pagination.setPage(Integer.valueOf(page));
			}
			if (CommonUtil.isNotEmpty(rows)) {
				pagination.setPageSize(Integer.valueOf(rows));
			}

			pagination = customerConsultService.selectCustomerConsultList(customerConsult, pagination);
		}
		return pagination;

	}

	/*
	按查询条件导出数据
     */
	@RequestMapping(value = "exportDataBestowal")
	public void exportDataBestowal(DataBestowal record, String businessNumber, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/msexcel;charset=UTF-8");
		Pagination pagination = new Pagination();
		pagination.setPageSize(0);
		pagination = dataBestowalService.selectBestowalPagination(record, pagination, businessNumber == "" ? null : businessNumber);
		List<DataBestowal> list = pagination.getRows();
		String title = "数据赠与-统计数据";
		String[] hearders = new String[]{"原客户经理姓名", "新客户经理姓名", "操作人姓名", "操作时间", "备注"};
		String[] fields = new String[]{"oldLoanOfficerName", "newLoanOfficerName", "operatingName", "operatingTime", "note"};
		if (null != list && list.isEmpty()) {
			list.add(new DataBestowal());
			fields = new String[]{"", "", "", "", ""};
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
