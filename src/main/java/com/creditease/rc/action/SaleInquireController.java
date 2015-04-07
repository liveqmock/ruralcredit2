package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.creditease.rc.domain.CodeTable;
import com.creditease.rc.service.IDataDictionaryService;
import com.creditease.rc.util.DicUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.creditease.rc.domain.CustomerConsultPool;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.ISaleInquireService;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.vo.DistributeDepartmentVO;
import com.creditease.rc.vo.SaleInquireSearchInfoVO;

@Controller
@RequestMapping("saleInquireController")
public class SaleInquireController {

	@Resource
	private ISaleInquireService saleInquireService;

	@Resource
	private IDataDictionaryService dataDictionaryService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, "registDate", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class, "registDate1", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class, "registDate2", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class, "distributionDate1", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class, "distributionDate2", new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "/addFrame")
	ModelAndView addFrame() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/CustomerConsultPool/register_inquire.jsp");
		return modelAndView;
	}

	;

	/**
	 * 提交登记咨询zw
	 * 
	 * @param customerConsultPool
	 * @return
	 */
	@RequestMapping("registerInquire")
	public @ResponseBody
	Message registerInquire(HttpServletRequest request, CustomerConsultPool customerConsultPool) {

		Message message = new Message();
		long flag;
		// 获取省、市、区名称
		StringBuilder pcaStr = new StringBuilder().append(request.getParameter("provinceName") + "-").append(
				request.getParameter("cityName"));
		if (StringUtils.isNotEmpty(request.getParameter("areaName"))) {
			pcaStr.append("-" + request.getParameter("areaName"));
		}
		customerConsultPool.setResidenceAddress(pcaStr.toString());
		flag = saleInquireService.saveInquire(customerConsultPool);
		if (flag > 0) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
			message.setMsg("保存失败");
		}
		return message;
	}

	/**
	 * 营销咨询列表查询zw
	 * 
	 * @param saleInquireSearchInfoVO
	 * @param page
	 * @param rows
	 * @param fuzzyValue
	 * @param sort
	 * @param order
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "searchSaleInquireInfo", method = RequestMethod.POST)
	public @ResponseBody
	Pagination searchSaleInquireInfo(SaleInquireSearchInfoVO saleInquireSearchInfoVO, String page, String rows,
			String fuzzyValue, String sort, String order, HttpServletRequest request) {
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		/* 限制状态：1.点击菜单显示待分配、已退回、无营业网点 2.点击搜索按钮无条件查询时显示所有状态 [pram : viewType] */
		if (StringUtils.isNotEmpty(request.getParameter("viewType"))) {
			// 按钮查询
			pagination = saleInquireService.searchSaleInquireInfo(saleInquireSearchInfoVO, pagination, fuzzyValue,
					sort, order, request.getSession());
		} else {
			// 菜单查询
			pagination = saleInquireService.searchSaleInquireInfoMenu(pagination, sort, order);
		}
		return pagination;
	}

	/*
	 * 营销咨询-手工分配
	 */
	@RequestMapping(value = "distributeDepartment")
	@ResponseBody
	public Map<String, Object> distributeDepartment(DistributeDepartmentVO distributeDepartmentVO, String consultPoolIds) {
		// 字符串 转换 数组
		List<Integer> ids = JsonUtil.getArray(consultPoolIds);
        Long[] IdsLong = new Long[ids.size()];
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String[] consultStates = request.getParameter("consultStates").split(",");
        String[] consultDates = request.getParameter("consultDates").split(",");


		for (int i = 0; i < ids.size(); i++) {
			IdsLong[i] = ids.get(i).longValue();
		}
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			retMap = saleInquireService.updateDistributeDepartment(distributeDepartmentVO, IdsLong,consultStates,consultDates);
		} catch (Exception e) {
			e.printStackTrace();
			retMap.put("error", "分配时发生错误");
		}
		return retMap;
	}

	// 获得需要编辑的数据zw
	@RequestMapping(value = "editFrame")
	ModelAndView selectCustomerPoolById(long consultPoolId) {
		ModelAndView modelAndView = new ModelAndView();
		CustomerConsultPool customerConsultPool = saleInquireService.selectCustomerPoolById(consultPoolId);
		modelAndView.setViewName("/jsp/rc/CustomerConsultPool/register_inquire_edit.jsp");
		// 获取数据字典客户标签（processCausesPool）：code_value，用于前台数据显示，
		if (StringUtils.isNotEmpty(customerConsultPool.getCustomerTag())) {
			customerConsultPool.setCustomerTagName(getCodeValueStrs("processCausesPool", customerConsultPool
					.getCustomerTag().split(",")));
		}
		modelAndView.addObject("customerConsultPool", customerConsultPool);
		return modelAndView;
	}

	/**
	 * 保存编辑咨询记录
	 * 
	 * @param customerConsultPool
	 * @return
	 */
	@RequestMapping("registerInquireEdit")
	public @ResponseBody
	Message registerInquireEdit(HttpServletRequest request, CustomerConsultPool customerConsultPool, String oldStatus,
			String newStatus) {
		Message message = new Message();
		boolean bool;
		// 获取省、市、区名称
		StringBuilder pcaStr = new StringBuilder().append(request.getParameter("provinceName") + "-").append(
				request.getParameter("cityName") + "-");
		if (StringUtils.isNotEmpty(request.getParameter("areaName"))) {
			pcaStr.append(request.getParameter("areaName"));
		}
		customerConsultPool.setResidenceAddress(pcaStr.toString());
		oldStatus = customerConsultPool.getMarketConsultState();
		if (StringUtils.isNotEmpty(customerConsultPool.getInvalidRegStatus())) {
			// 若用户选择无效登记状态，则将咨询记录业务状态更改为对应状态
			List<CodeTable> tables = dataDictionaryService.getSpecifiedDic("", "marketConsultStatus");
			for (CodeTable t : tables) {
				if (t.getCodeKey().equals(customerConsultPool.getInvalidRegStatus())) {
					customerConsultPool.setMarketConsultState(t.getCodeKey());
					break;
				}
			}
		}
		bool = saleInquireService.updateConsulePool(customerConsultPool, oldStatus, newStatus);
		if (bool) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
		}
		return message;

	}

	/**
	 * 营销咨询-查看详情zw
	 */
	@RequestMapping(value = "registerInquireView")
	public @ResponseBody
	Pagination registerInquireView(String page, String rows, String order, String sort, HttpServletRequest request) {
		/*
		 * 更改
		 * CustomerConsultPoolVo customerConsultPoolVo1 = saleInquireService.registerInquireView(consultPoolId);
		 * ModelAndView modelAndView = new ModelAndView();
		 * modelAndView.setViewName("/jsp/rc/CustomerConsultPool/registerInquireView.jsp");
		 * modelAndView.addObject("customerConsultPoolVo", customerConsultPoolVo1);
		 * return modelAndView;
		 */
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		/* 主键ID、联系方式均存在时，联系方式有限查询 */
		if (StringUtils.isNotEmpty(request.getParameter("mobilePhone"))
				&& !request.getParameter("mobilePhone").equals("null")) {
			// 联系方式
			pagination = saleInquireService.selectDetailByPhoneOrId(pagination, sort, order,
					request.getParameter("mobilePhone"), "phone");
		} else {
			pagination = saleInquireService.selectDetailByPhoneOrId(pagination, sort, order,
					request.getParameter("consultPoolId"), "primaryKeyId");
		}

		String status;
		for (Object o : pagination.getRows()) {
			Map<String, String> m = (HashMap<String, String>) o;
			if (StringUtils.isNotEmpty(m.get("CUSTOMER_TAG"))) {
				m.put("CUSTOMER_TAG_NAME", getCodeValueStrs("processCausesPool", m.get("CUSTOMER_TAG").split(",")));
			}
			status = m.get("MARKET_CONSULT_STATE");
			if (status.equals("拒绝") || status.equals("无效客户") || status.equals("未联系上客户")) {
				m.put("INVALID_CONSULT_STATUS", status);
			} else {
				m.put("INVALID_CONSULT_STATUS", "");
			}
		}
		return pagination;
	}

	@RequestMapping(value = "autoDistributeDepartment")
	@ResponseBody
	public Message autoDistributeDepartment() {
		Message message = new Message();
		try {
			// message = saleInquireService.updateAutoDistributeDepartment();
			message = saleInquireService.updateAutoDistributeSaleInquire();
		} catch (Exception e) {
			e.printStackTrace();
			message.setSuccess(false);
			message.setMsg("功能异常：" + e.getMessage());
		}
		return message;
	}

	@RequestMapping(value = "checkROLELocalFin")
	public @ResponseBody
	Message checkROLELocalFin() {
		Message message = saleInquireService.checkROLELocalFin();
		return message;
	}

	@RequestMapping(value = "distributeOrders")
	@ResponseBody
	public Message distributeOrders(CustomerConsultPool customerConsultPool, String consultPoolIds) {
		// 字符串 转换 数组
		List<Integer> ids = JsonUtil.getArray(consultPoolIds);
		Long[] IdsLong = new Long[ids.size()];
		for (int i = 0; i < ids.size(); i++) {
			IdsLong[i] = ids.get(i).longValue();
		}
		Message message = new Message();
		try {
			message = saleInquireService.updateDistributeOrders(customerConsultPool, IdsLong);
		} catch (Exception e) {
			e.printStackTrace();
			message.setSuccess(false);
			message.setMsg("功能异常：" + e.getMessage());
		}
		return message;
	}

	@RequestMapping(value = "distributeConfirm")
	@ResponseBody
	public Message distributeConfirm(CustomerConsultPool customerConsultPool, String consultPoolIds) {
		// 字符串 转换 数组
		List<Integer> ids = JsonUtil.getArray(consultPoolIds);
		Long[] IdsLong = new Long[ids.size()];
		for (int i = 0; i < ids.size(); i++) {
			IdsLong[i] = ids.get(i).longValue();
		}
		Message message = new Message();
		try {
			message = saleInquireService.updateDistributeConfirm(customerConsultPool, IdsLong);
		} catch (Exception e) {
			e.printStackTrace();
			message.setSuccess(false);
			message.setMsg("功能异常：" + e.getMessage());
		}
		return message;
	}

	// 废弃操作
	@RequestMapping(value = "dropregisterInquire")
	public @ResponseBody
	Message dropregisterInquire(Long consultPoolId) {
		Message message = new Message();
		boolean issuccess = saleInquireService.updateScrapConsult(consultPoolId);
		message.setSuccess(issuccess);
		return message;
	}

	/**
	 * 获取数据字典中多个 code_value 组成的字符串
	 * 
	 * @param section
	 * @param keys
	 * @return
	 */
	public String getCodeValueStrs(String section, String[] keys) {
		StringBuffer s = new StringBuffer();
		for (String key : keys) {
			if (s.length() > 0) {
				s.append(",");
			}
			s.append(DicUtil.convertCodeKeyToCodeValue(section, key));
		}
		return s.length() == 0 ? null : s.toString();
	}

}
