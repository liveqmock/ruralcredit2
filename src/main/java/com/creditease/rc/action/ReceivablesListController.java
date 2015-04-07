package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import com.creditease.rc.report.excel.*;
import com.creditease.rc.service.IJsGridReportBaseXLSXService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creditease.rc.service.*;
import com.creditease.rc.vo.OverDueQueryInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.common.DepartmentEntity;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.LocalOfficeIdDTO;
import com.creditease.rc.domain.LocalOverdueInfoResponse;
import com.creditease.rc.domain.LocalSellIdDTO;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.OverDueObj;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.report.excel.ExcelUtils;
import com.creditease.rc.report.excel.JsGridReportBase;
import com.creditease.rc.report.excel.TableData;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.util.SmpWSUtil;
import com.creditease.rc.vo.OverDueListVo;

/**
 * 
 * @author haoqiang
 * 
 */
@Controller
@RequestMapping("receivablesList")
public class ReceivablesListController {

	@Resource
	private IReceivablesListService receivablesListService;
	@Resource
	private ICreditApplicationService creditApplicationService;
	@Resource
	private IRural2CreditService rural2CreditService;
	@Resource
	private SmpWSUtil smpWSUtil;
	@Resource
	private IStatisticalinfoService statisticalinfoService;
    @Resource
    private IJsGridReportBaseXLSXService jsGridReportBaseXLSXService;


    @Resource
    private IOverDueDataService overDueDataService;
	/**
	 * 
	 * @param page 页
	 * @param rows 行
	 * @param creditApplication 用于查询的申请单对象
	 * @param fuzzyValue 模糊查询值
	 * @param request 目前没用
	 * @return 分页List
	 */
	@RequestMapping(value = "receivablesDataGrid")
	public @ResponseBody
	Pagination receivablesDataGrid(@RequestParam(required = false) String page,
			@RequestParam(required = false) String rows, CreditApplication creditApplication, String fuzzyValue,
			HttpServletRequest request) {
		creditApplication.setAuditStatus("15");
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		if (fuzzyValue != null) {
			fuzzyValue = fuzzyValue.trim();
		}
		return creditApplicationService.selectCreditApplicationList(creditApplication, fuzzyValue, null, pagination,
				request.getSession());

	}

	/**
	 * 更改默认还款方式
	 * 
	 * @param creditapplicationId 信贷申请单主键
	 * @param param 现金转卡还是卡转现金 0 是卡转现金 1是现金转卡
	 * @param p （直接更改默认还款方式 或者先撤销在更改默认还款方式 目前只有前一种状态）
	 * @param accountInfoId 卡信息主键
	 * @return 是否撤销成功
	 */
	@RequestMapping(value = "changeDefaultReturnmentWay")
	public @ResponseBody
	boolean changeDefaultReturnmentWay(Integer creditapplicationId, String param, String p, Integer accountInfoId) {

		return receivablesListService.updateDefaultReturnmentWay(creditapplicationId, param, p, accountInfoId);
	}

	/**
	 * 
	 * @param creditapplicationId
	 * @return Message
	 */
	@RequestMapping(value = "checkForEarlyReturn")
	public @ResponseBody
	Message checkForEarlyReturn(Integer creditapplicationId) {
		int resint = receivablesListService.checkForEarlyReturn(creditapplicationId);
		Message message = new Message();
		if (resint == 0) {
			message.setSuccess(true);
		} else if (resint == 1) {
			message.setSuccess(false);
			message.setMsg("有在途资金！不能进行一次性提前还款！");

		} else if (resint == 2) {
			message.setSuccess(false);
			message.setMsg("有违约！不能进行一次性提前还款！");

		} else if (resint == 3) {
			message.setSuccess(false);
			message.setMsg("有未完全还清的期数存在！不能进行一次性提前还款！");

		} else if (resint == 4) {
			message.setSuccess(false);
			message.setMsg("系统内部错误！");

		} else {
			message.setSuccess(false);
			message.setMsg("系统内部错误！");
		}
		return message;
	}

	@RequestMapping(value = "returnOverdueListJSP")
	public ModelAndView returnOverdueListJSP(HttpSession session, String type) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/receivables/overdueList.jsp");

		List<DepartmentEntity> departmentEntities = smpWSUtil.getDepartmentList();
		List<LocalOfficeIdDTO> localOfficeIdDTOList = new ArrayList<LocalOfficeIdDTO>();
		if (departmentEntities.size() == 1) {
			Set<String> userAuthoritiesSet = smpWSUtil.getUserAuthorities();
			if (userAuthoritiesSet.contains("ROLE_LOAN_OFFICER")) {// 如果是客户经理
				String areaDepartmentId = SpringSecurityUtils.getCurrentUser().getAreaDepartmentId().toString();
				String userId = SpringSecurityUtils.getCurrentUser().getUserId();
				LocalOfficeIdDTO localOfficeIdDTO = new LocalOfficeIdDTO();
				localOfficeIdDTO.setOfficeId(areaDepartmentId);
				List<LocalSellIdDTO> localSellIdDTOsList = new ArrayList<LocalSellIdDTO>();
				LocalSellIdDTO localSellIdDTO = new LocalSellIdDTO();
				localSellIdDTO.setSellId(userId);
				localSellIdDTOsList.add(localSellIdDTO);
				localOfficeIdDTO.setLocalSellIdDTOs(localSellIdDTOsList);
				localOfficeIdDTOList.add(localOfficeIdDTO);
			} else {// 如果是营业部经理 和风险经理
				String areaDepartmentId = SpringSecurityUtils.getCurrentUser().getAreaDepartmentId().toString();
				LocalOfficeIdDTO localOfficeIdDTO = new LocalOfficeIdDTO();
				localOfficeIdDTO.setOfficeId(areaDepartmentId);
				localOfficeIdDTOList.add(localOfficeIdDTO);
			}
		} else {
			for (DepartmentEntity departmentEntity : departmentEntities) {
				LocalOfficeIdDTO localOfficeIdDTO = new LocalOfficeIdDTO();
				localOfficeIdDTO.setOfficeId(departmentEntity.getDepartmentId());
				localOfficeIdDTOList.add(localOfficeIdDTO);
			}
		}
		LocalOverdueInfoResponse localOverdueInfoResponse = rural2CreditService.overdueInfo(localOfficeIdDTOList);
		Map<Long, OverDueObj> overDueObjMap = localOverdueInfoResponse.getOverDueObjMap();

		if ("formIndexByOverdue".equals(type)) {
			List<Long> creditapplicationIdList = (List<Long>) session
					.getAttribute("applicationIdListFormIndexByOverdue");

			Map<Long, OverDueObj> overDueObjMapFormIndex = new HashMap<Long, OverDueObj>();
			for (Long creditapplicationId : creditapplicationIdList) {
				OverDueObj dueObj = overDueObjMap.get(creditapplicationId);
				overDueObjMapFormIndex.put(creditapplicationId, dueObj);
			}
			session.setAttribute("overDueObjMap", overDueObjMapFormIndex);
		} else {
			session.setAttribute("overDueObjMap", overDueObjMap);
		}

		return modelAndView;
	}

    @RequestMapping(value = "goToOverDueListJSP")
    public ModelAndView goToOverDueListJSP(HttpSession session, String type) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/jsp/rc/overDue/overdueList.jsp");
        return  mv;
    }

	/**
	 * 
	 * @param page 页
	 * @param rows 行
	 * @param creditApplication 用于查询的申请单对象
	 * @param fuzzyValue 模糊查询值
	 * @param request 目前没用
	 * @return 分页List
	 */
	@RequestMapping(value = "overdueGrid")
	public @ResponseBody
	Pagination overdueGrid(@RequestParam(required = false) String page, @RequestParam(required = false) String rows,
			HttpSession session, String paramJsonMap) {
		Pagination pagination = new Pagination();

		String getCreditApplitionIdString = "";

		Map<Long, OverDueObj> overDueObjMap = (Map<Long, OverDueObj>) session.getAttribute("overDueObjMap");
		Set<Entry<Long, OverDueObj>> entries = overDueObjMap.entrySet();
		Iterator<Entry<Long, OverDueObj>> iterator = entries.iterator();
		while (iterator.hasNext()) {
			Entry<Long, OverDueObj> entry = iterator.next();

			OverDueObj dueObj = entry.getValue();
			Long getCreditApplicationId = dueObj.getCreditApplicationId();

			if (getCreditApplicationId != null) {
				getCreditApplitionIdString = getCreditApplitionIdString + getCreditApplicationId.toString() + ",";
			}

			System.out.println(entry.getKey() + "--------");

		}

		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		if (CommonUtil.isEmpty(getCreditApplitionIdString) || "null".equals(getCreditApplitionIdString)) {
			pagination.setItems(new ArrayList<CreditApplication>());
			return pagination;
		} else {
			// 添加权限查询
			getCreditApplitionIdString = getCreditApplitionIdString.substring(0,
					getCreditApplitionIdString.length() - 1);
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
			Integer departmentId = SpringSecurityUtils.getCurrentUser().getDepartmentId();
			String userId = SpringSecurityUtils.getCurrentUser().getUserId();
			Map<String, String> pramMap = new HashMap<String, String>();
			pramMap.put("sqlsid", sqlsid);
			pramMap.put("departmentId", departmentId.toString());
			pramMap.put("userId", userId);
			pramMap.put("getCreditApplitionIdString", getCreditApplitionIdString);
			System.out.println(pramMap.get("getCreditApplitionIdString"));

			if (!"".equals(paramJsonMap) && paramJsonMap != null) {
				Map<String, String> temp = JsonUtil.getObject(paramJsonMap);
				String fuzzyValue = temp.get("fuzzyValue");
				String businessNumber = temp.get("businessNumber");
				String borrowerName = temp.get("borrowerName");
				String businessType = temp.get("businessType");
				String defaultReturnmentWay = temp.get("defaultReturnmentWay");
				String companyId = temp.get("companyId");
				String startDate = temp.get("startDate");
				String endDate = temp.get("endDate");

				pramMap.put("fuzzyValue", fuzzyValue);
				pramMap.put("businessNumber", businessNumber);
				pramMap.put("borrowerName", borrowerName);
				pramMap.put("businessType", businessType);
				pramMap.put("defaultReturnmentWay", defaultReturnmentWay);
				pramMap.put("companyId", companyId);
				pramMap.put("startDate", startDate);
				pramMap.put("endDate", endDate);
			}

// System.out.println("----------" + pramMap.get("businessNumber"));
// System.out.println("----------" + pramMap.get("borrowerName"));
// System.out.println("----------" + pramMap.get("businessType"));
// System.out.println("----------" + pramMap.get("defaultReturnmentWay"));
			pagination = receivablesListService.queryOverdueGrid(pramMap, pagination);
			List<OverDueListVo> dueListVos = pagination.getRows();
			if (CommonUtil.isNotEmpty(dueListVos)) {
				for (OverDueListVo dueListVo : dueListVos) {
					Long getCreditapplicationId = dueListVo.getCreditapplicationId();
					OverDueObj temp = overDueObjMap.get(getCreditapplicationId);
					dueListVo.setaOverdueCount(temp.getaOverdueCount());
					dueListVo.setaOverdueMoney(temp.getaOverdueMoney());
					dueListVo.setaOverdueStart(temp.getaOverdueStart());
					dueListVo.setOverdueDayCount(temp.getOverdueDayCount());
				}
			}
			return pagination;
		}

	}

    /**
     *
     查询逾期列表信息 新页面
     * @param page
     * @param rows
     * @param session
     * @param overDueQueryInfo
     * @return     逾期列表
     * */
    @RequestMapping(value = "overDueInfoList")
    public @ResponseBody
    Pagination overDueInfoList(@RequestParam(required = false) String page, @RequestParam(required = false) String rows,
                           HttpSession session,OverDueQueryInfo overDueQueryInfo) {
        //封装请求参数
            //封装page对象
                Pagination pagination = new Pagination();
                if (!StringUtils.isBlank(page)) {
                    pagination.setPage(Integer.valueOf(page));
                }
                if (!StringUtils.isBlank(rows)) {
                    pagination.setPageSize(Integer.valueOf(rows));
                }
            //封装权限
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
                overDueQueryInfo.setAuthList(sqlsid);
        //查询逾期信息
            pagination = overDueDataService.queryOverdueGrid(overDueQueryInfo,pagination);
            return pagination;
   }

    /**
     *  页面同步逾期使用
     *
     *   **/

    @RequestMapping(value = "insertOverDueList")
    public @ResponseBody
    Message insertOverDueList() throws Exception {
        return overDueDataService.insertOverDueData();
    }



	@RequestMapping(value = "getCreditApplitionIdString")
	public @ResponseBody
	String getCreditApplitionIdString(HttpSession session) {

		List<DepartmentEntity> departmentEntities = smpWSUtil.getDepartmentList();
		List<LocalOfficeIdDTO> localOfficeIdDTOList = new ArrayList<LocalOfficeIdDTO>();
		if (departmentEntities.size() == 1) {
			Set<String> userAuthoritiesSet = smpWSUtil.getUserAuthorities();
			if (userAuthoritiesSet.contains("ROLE_LOAN_OFFICER")) {// 如果是客户经理
				String areaDepartmentId = SpringSecurityUtils.getCurrentUser().getAreaDepartmentId().toString();
				String userId = SpringSecurityUtils.getCurrentUser().getUserId();
				LocalOfficeIdDTO localOfficeIdDTO = new LocalOfficeIdDTO();
				localOfficeIdDTO.setOfficeId(areaDepartmentId);
				List<LocalSellIdDTO> localSellIdDTOsList = new ArrayList<LocalSellIdDTO>();
				LocalSellIdDTO localSellIdDTO = new LocalSellIdDTO();
				localSellIdDTO.setSellId(userId);
				localSellIdDTOsList.add(localSellIdDTO);
				localOfficeIdDTO.setLocalSellIdDTOs(localSellIdDTOsList);
				localOfficeIdDTOList.add(localOfficeIdDTO);
			} else {// 如果是营业部经理 和风险经理
				String areaDepartmentId = SpringSecurityUtils.getCurrentUser().getAreaDepartmentId().toString();
				LocalOfficeIdDTO localOfficeIdDTO = new LocalOfficeIdDTO();
				localOfficeIdDTO.setOfficeId(areaDepartmentId);
				localOfficeIdDTOList.add(localOfficeIdDTO);
			}
		} else {
			for (DepartmentEntity departmentEntity : departmentEntities) {
				LocalOfficeIdDTO localOfficeIdDTO = new LocalOfficeIdDTO();
				localOfficeIdDTO.setOfficeId(departmentEntity.getDepartmentId());
				localOfficeIdDTOList.add(localOfficeIdDTO);
			}
		}
		LocalOverdueInfoResponse localOverdueInfoResponse = rural2CreditService.overdueInfo(localOfficeIdDTOList);
		List<Long> creditApplicationIdList = localOverdueInfoResponse.getCreditApplicationIdList();
		String getCreditApplitionIdString = "";
		Map<Long, OverDueObj> overDueObjMap = localOverdueInfoResponse.getOverDueObjMap();

		if (CommonUtil.isNotEmpty(creditApplicationIdList)) {
			getCreditApplitionIdString = creditApplicationIdList.toString();
		}
		session.setAttribute("overDueObjMap", overDueObjMap);
		return getCreditApplitionIdString;
	}

	/**
	 * 新的还款列表查询用自己的方法 从张嫚的方法中剥离出来了
	 * 
	 * @param page 页
	 * @param rows 行
	 * @param creditApplication 用于查询的申请单对象
	 * @param fuzzyValue 模糊查询值
	 * @param request 目前没用
	 * @return 分页List
	 */
	@RequestMapping(value = "newReceivablesDataGrid")
	public @ResponseBody
	Pagination newReceivablesDataGrid(@RequestParam(required = false) String page,
			@RequestParam(required = false) String rows, HttpSession session, String paramJsonMap, String sort,
			String order, String ids) {

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
		Integer departmentId = SpringSecurityUtils.getCurrentUser().getDepartmentId();
		String userId = SpringSecurityUtils.getCurrentUser().getUserId();
		Map<String, String> pramMap = new HashMap<String, String>();
		pramMap.put("authList", sqlsid);
		pramMap.put("departmentId", departmentId.toString());
		pramMap.put("userId", userId);
		pramMap.put("sort", sort);
		pramMap.put("order", order);
		pramMap.put("ids", ids);

		System.out.println(pramMap.get("sort"));
		System.out.println(pramMap.get("order"));

		if (!"".equals(paramJsonMap) && paramJsonMap != null) {
			Map<String, String> temp = JsonUtil.getObject(paramJsonMap);
			String fuzzyValue = temp.get("fuzzyValue");
			String businessNumber = temp.get("businessNumber");
			String borrowerName = temp.get("borrowerName");
			String businessType = temp.get("businessType");
			String defaultReturnmentWay = temp.get("defaultReturnmentWay");
			String companyId = temp.get("companyId");
			String startDate = temp.get("startDate");
			String endDate = temp.get("endDate");
            String repaymentPlanName = temp.get("repaymentPlanName");

			pramMap.put("fuzzyValue", fuzzyValue);
			pramMap.put("businessNumber", businessNumber);
			pramMap.put("borrowerName", borrowerName);
			pramMap.put("businessType", businessType);
			pramMap.put("defaultReturnmentWay", defaultReturnmentWay);
			pramMap.put("startDate", startDate);
			pramMap.put("endDate", endDate);
			pramMap.put("companyId", companyId);
			pramMap.put("repaymentPlanName", repaymentPlanName);
		}
		return statisticalinfoService.queryNewReceivablesDataGrid(pramMap, pagination);

	}

	@RequestMapping(value = "exportExcel")
	public @ResponseBody
	void exportExcel(String paramJsonMap, HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		response.setContentType("application/msexcel;charset=UTF-8");
		Map<Long, OverDueObj> overDueObjMap = (Map<Long, OverDueObj>) session.getAttribute("overDueObjMap");
		String getCreditApplitionIdString = "";

		Set<Entry<Long, OverDueObj>> entries1 = overDueObjMap.entrySet();
		Iterator<Entry<Long, OverDueObj>> iterator1 = entries1.iterator();
		while (iterator1.hasNext()) {
			Entry<Long, OverDueObj> entry = iterator1.next();

			OverDueObj dueObj = entry.getValue();
			Long getCreditApplicationId = dueObj.getCreditApplicationId();

			if (getCreditApplicationId != null) {
				getCreditApplitionIdString = getCreditApplitionIdString + getCreditApplicationId.toString() + ",";
			}

			System.out.println(entry.getKey() + "--------");

		}
		if (CommonUtil.isNotEmpty(getCreditApplitionIdString)) {
			getCreditApplitionIdString = getCreditApplitionIdString.substring(0,
					getCreditApplitionIdString.length() - 1);
		}
		Map<String, String> pramMap = new HashMap<String, String>();
		pramMap.put("getCreditApplitionIdString", getCreditApplitionIdString);
		if (!"".equals(paramJsonMap) && paramJsonMap != null) {
			Map<String, String> temp = JsonUtil.getObject(paramJsonMap);
			String fuzzyValue = temp.get("fuzzyValue");
			String businessNumber = temp.get("businessNumber");
			String borrowerName = temp.get("borrowerName");
			String businessType = temp.get("businessType");
			String defaultReturnmentWay = temp.get("defaultReturnmentWay");
			String companyId = temp.get("companyId");
			String startDate = temp.get("startDate");
			String endDate = temp.get("endDate");

			pramMap.put("fuzzyValue", fuzzyValue);
			pramMap.put("businessNumber", businessNumber);
			pramMap.put("borrowerName", borrowerName);
			pramMap.put("businessType", businessType);
			pramMap.put("defaultReturnmentWay", defaultReturnmentWay);
			pramMap.put("startDate", startDate);
			pramMap.put("endDate", endDate);
			pramMap.put("companyId", companyId);
		}
		Set<Entry<String, String>> entries = pramMap.entrySet();
		Iterator<Entry<String, String>> iterator = entries.iterator();

		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			String k = entry.getKey();
			String v = entry.getValue();
			System.out.println("k=" + k);
			System.out.println("v=" + v);
		}
		List<OverDueListVo> overDueListVos = statisticalinfoService.queryForOverDueListEX(pramMap);
		for (OverDueListVo dueListVo : overDueListVos) {
			String getBusinessType = dueListVo.getBusinessType();
			if ("0".equals(getBusinessType)) {
				dueListVo.setBusinessType("公司");
			} else if ("1".equals(getBusinessType)) {
				dueListVo.setBusinessType("个人");
			}
			String getDefaultReturnmentWay = dueListVo.getDefaultReturnmentWay();
			if ("0".equals(getDefaultReturnmentWay)) {
				dueListVo.setDefaultReturnmentWay("自动划扣");
			} else if ("1".equals(getDefaultReturnmentWay)) {
				dueListVo.setDefaultReturnmentWay("现金");
			}
			OverDueObj dueObj = overDueObjMap.get(dueListVo.getCreditapplicationId());
			if (dueObj != null) {
				dueListVo.setOverdueDayCount(dueObj.getOverdueDayCount());
				dueListVo.setaOverdueMoney(dueObj.getaOverdueMoney());
				dueListVo.setaOverdueStart(dueObj.getaOverdueStart());
			}
		}

		if (CommonUtil.isEmpty(getCreditApplitionIdString)) {
			overDueListVos.clear();
		}

		String title = "逾期列表";
		String[] hearder = new String[] { "业务单号", "借款人姓名", "信贷员姓名", "业务类型", "分公司名称", "默认还款方式", "产品类型", "放款日期",
				"逾期开始时间", "逾期天数", "逾期金额" };
		String[] fields = new String[] { "groupNumber", "groupName", "loanOfficerName", "businessType", "companyName",
				"defaultReturnmentWay", "repaymentPlanName", "signagreementDate", "aOverdueStart", "overdueDayCount",
				"aOverdueMoney" };
		if (CommonUtil.isEmpty(overDueListVos)) {
			OverDueListVo dueListVo = new OverDueListVo();
			overDueListVos.add(dueListVo);
		}
		TableData td = ExcelUtils.createTableData(overDueListVos, ExcelUtils.createTableHeader(hearder), fields);
		/*JsGridReportBase report = new JsGridReportBase(request, response);
		report.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);
*/
        jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
        jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);
    }

    /**
     *  导出逾期信息到Excel
     * @param session
     * @param request
     * @param response
     * @param overDueQueryInfo
     * @throws Exception
     */
    @RequestMapping(value = "insertOverDueListToExcel")
    public @ResponseBody
    void insertOverDueListToExcel( HttpSession session,HttpServletRequest request, HttpServletResponse response,OverDueQueryInfo overDueQueryInfo)
            throws Exception {
        response.setContentType("application/msexcel;charset=UTF-8");
        //封装请求参数
        //封装权限
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
        overDueQueryInfo.setAuthList(sqlsid);
        //查询逾期信息
        List<OverDueListVo> overDueListVos = (List<OverDueListVo>)overDueDataService.queryOverdueListForExport(overDueQueryInfo);
        //设置导出表格格式
        String title = "逾期列表";
        String[] hearder = new String[] { "业务单号", "借款人姓名", "信贷员姓名",  "分公司名称", "默认还款方式", "产品类型", "放款日期",
                "逾期开始时间", "逾期天数", "逾期金额" };
        String[] fields = new String[] { "groupNumber", "groupName", "loanOfficerName", "companyName",
                "defaultReturnmentWay", "repaymentPlanName", "signagreementDate", "aOverdueStart", "overdueDayCount",
                "aOverdueMoney" };
        if (CommonUtil.isEmpty(overDueListVos)) {
            OverDueListVo dueListVo = new OverDueListVo();
            overDueListVos.add(dueListVo);
        }
        //导出数据到Excel
        TableData td = ExcelUtils.createTableData(overDueListVos, ExcelUtils.createTableHeader(hearder), fields);
       /* JsGridReportBase report = new JsGridReportBase(request, response);
        report.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);*/
        jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
        jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);
    }
}
