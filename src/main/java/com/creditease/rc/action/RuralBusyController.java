package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
import com.creditease.rc.app.credit.ReturnSchemeRequest;
import com.creditease.rc.app.credit.RuralBusyService;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.LocalClientApplyRequest;
import com.creditease.rc.domain.LocalOfficeIdDTO;
import com.creditease.rc.domain.LocalOverdueInfoResponse;
import com.creditease.rc.domain.LocalReserveListDTO;
import com.creditease.rc.domain.LocalReturnAmountRequest;
import com.creditease.rc.domain.LocalReturnSchemeResponse;
import com.creditease.rc.domain.LocalSellIdDTO;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.IFinanceMoneyService;
import com.creditease.rc.service.IReturnPlanService;
import com.creditease.rc.service.IRural2CreditService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.DicUtil;
import com.creditease.rc.util.SmpWSUtil;
import com.creditease.rc.vo.LocalReserveDTOVo;

@Controller
@RequestMapping("ruralBusyController")
public class RuralBusyController {

	@Resource
	private RuralBusyService ruralBusyService;

	@Resource
	private IRural2CreditService rural2CreditService;

	@Resource
	private ICreditApplicationService creditApplicationService;

	@Resource
	private IFinanceMoneyService financeMoneyService;

	@Resource
	private IReturnPlanService returnPlanService;
	@Resource
	private SmpWSUtil smpWSUtil;

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

	@RequestMapping(value = "test")
	public @ResponseBody
	void test() {
		// System.out.println("测试0：为了测试");
		// System.out.println("=============开始==========");
		ReturnSchemeRequest parameters = new ReturnSchemeRequest();
		parameters.setApplyId("1111");
		ruralBusyService.returnScheme(parameters);
		// System.out.println("=============结束==========");
	}

	@RequestMapping(value = "testClientApply")
	public @ResponseBody
	Message testClientApply(Long creditapplicationId) {
		// System.out.println("测试1：1.1 接收借款信息。");
		// System.out.println("=============开始==========");
		Message message = rural2CreditService.updateClientApplyRecord(creditapplicationId);

		// System.out.println("测试1：1.1 接收借款信息的结果是-------------------" + s.getMsg());
		// System.out.println("=============结束==========");
		return message;
	}

	@RequestMapping(value = "testReturnScheme")
	public @ResponseBody
	void testReturnScheme(Long creditapplicationId) {
		// System.out.println("测试2：1.2 查询还款计划。");
		// System.out.println("=============开始==========");
		List<LocalReturnSchemeResponse> s = rural2CreditService.returnScheme(creditapplicationId);
		// System.out.println("=============结束==========");
	}

	@RequestMapping(value = "testOverdueInfo")
	public @ResponseBody
	void testOverdueInfo(Long creditapplicationId) {
		// System.out.println("测试3：1.3 查询逾期合同。");
		String departmentId = SpringSecurityUtils.getCurrentUser().getDepartmentId().toString();
		String userId = SpringSecurityUtils.getCurrentUser().getUserId();

		List<LocalOfficeIdDTO> localOfficeIdDTOList = new ArrayList<LocalOfficeIdDTO>();
		LocalOfficeIdDTO localOfficeIdDTO = new LocalOfficeIdDTO();
		localOfficeIdDTO.setOfficeId(departmentId);
		List<LocalSellIdDTO> localSellIdDTOsList = new ArrayList<LocalSellIdDTO>();
		LocalSellIdDTO localSellIdDTO = new LocalSellIdDTO();
		localSellIdDTO.setSellId(userId);
		localSellIdDTOsList.add(localSellIdDTO);
		localOfficeIdDTO.setLocalSellIdDTOs(localSellIdDTOsList);
		localOfficeIdDTOList.add(localOfficeIdDTO);
		// System.out.println("=============开始==========");
		LocalOverdueInfoResponse localOverdueInfoResponse = rural2CreditService.overdueInfo(localOfficeIdDTOList);
		List<Long> creditApplicationIdList = localOverdueInfoResponse.getCreditApplicationIdList();
		for (Long a : creditApplicationIdList) {
			// System.out.println(a);
		}
		// System.out.println("=============结束==========");
	}

	@RequestMapping(value = "testReturnAmount")
	public @ResponseBody
	void testReturnAmount(Long creditapplicationId) {
// System.out.println("测试2：1.2 查询还款计划。");
// System.out.println("=============开始==========");
		LocalReturnAmountRequest parameters = new LocalReturnAmountRequest();
		parameters.setCreditapplicationId(1L);
		rural2CreditService.returnAmount(parameters);
		// System.out.println("=============结束==========");
	}

	@RequestMapping(value = "testReserveReturn")
	public @ResponseBody
	void testReserveReturn(Long receivedRecordId) {
		List<Long> receivedRecordIds = new ArrayList<Long>();
		receivedRecordIds.add(receivedRecordId);
		boolean is = false;
		// is = rural2CreditService.reserveReturn(receivedRecordIds, new Date());

		// System.out.println("返回的结果是：----------" + is);
	}

	@RequestMapping(value = "testQyReserveSearch")
	public @ResponseBody
	void testQyReserveSearch(String receivedRecordId) {
		List<String> receivedRecordIds = new ArrayList<String>();
		receivedRecordIds.add(receivedRecordId);
		rural2CreditService.qyReserveSearch(receivedRecordIds, null);

	}

	@RequestMapping(value = "testQyClientApply")
	public @ResponseBody
	LocalClientApplyRequest testQyClientApply(String bussesNumber) {
		CreditApplication application = creditApplicationService.selectByGroupNumber(bussesNumber);
		if (application != null) {
			if (application.getCreditapplicationId() != null && !"".equals(application.getCreditapplicationId())) {
				return rural2CreditService.qyClientApply(application.getCreditapplicationId().longValue());
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@RequestMapping(value = "jumpToQyClientApplyJsp")
	public ModelAndView jumpToQyClientApplyJsp(String bussesNumber) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/basicInfo/showQyClientApply.jsp");
		modelAndView.addObject("bussesNumber", bussesNumber);
		return modelAndView;
	}

	@RequestMapping(value = "queryReserveSearchList")
	public @ResponseBody
	List<LocalReserveListDTO> queryReserveSearchList(String businessNumber, String bizIdString, String param) {
		List<LocalReserveListDTO> localReserveListDTOs = new ArrayList<LocalReserveListDTO>();
		if ("0".equals(param)) {
			LocalReserveDTOVo localReserveDTOVo = rural2CreditService.qyReserveSearch(null, businessNumber);
			localReserveListDTOs = localReserveDTOVo.getLocalReserveListDTOs();
		} else if ("1".equals(param)) {
			String[] strs = bizIdString.split(",");
			List<String> bizIdList = Arrays.asList(strs);
			LocalReserveDTOVo localReserveDTOVo = rural2CreditService.qyReserveSearch(bizIdList, null);
			localReserveListDTOs = localReserveDTOVo.getLocalReserveListDTOs();
		}
		if (CommonUtil.isNotEmpty(localReserveListDTOs)) {
			// 去本地分组查产品编号返回map
			Map<String, String> productMap = returnPlanService.queryProductMap();
			Map<String, String> map = smpWSUtil.getAllAreaDepartmentNameMap();
			for (LocalReserveListDTO l : localReserveListDTOs) {
				String getReturnType = l.getReturnType();
				if (getReturnType != null && !"".equals(getReturnType)) {
					getReturnType = DicUtil.convertCodeKeyToCodeValue("afterLoanBank", getReturnType);
					l.setReturnType(getReturnType);
				}

				String productId = l.getProductId();
				if (CommonUtil.isNotEmpty(productId)) {
					productId = productId.toUpperCase();
					productId = productMap.get(productId);
					l.setProductId(productId);
				}
				String officeId = l.getOfficeId();
				if (CommonUtil.isNotEmpty(officeId)) {
					officeId = map.get(officeId);
					l.setOfficeId(officeId);
				}
			}
		}
		return localReserveListDTOs;
	}

	/**
	 * 查询账务收款列表
	 * 
	 * @param creditApplication 页面传入参数
	 * @param page 当前页
	 * @param rows 行数
	 * @param session
	 * @return pagination1
	 */
	@RequestMapping(value = "selectRecevieList")
	public @ResponseBody
	Pagination selectRecevieList(CreditApplication creditApplication, @RequestParam(required = false) String page,
			@RequestParam(required = false) String rows, HttpSession session) {
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
		creditApplication.setAuthList(sqlsid);
		Date beginDate = creditApplication.getBeginLoanDate();
		Date endDate = creditApplication.getEndLoanDate();
		if (null != beginDate) {
			String strBedinDate = DateUtil.dateConvertString(beginDate);
			creditApplication.setBeginLoanDate(DateUtil.stringConvertDate(strBedinDate));
		}
		if (null != endDate) {
			String strEndDate = DateUtil.dateConvertString(endDate);
			creditApplication.setEndLoanDate(DateUtil.stringConvertDate(strEndDate));
		}
		String fuzzyValue = creditApplication.getFuzzyValue();
		if (fuzzyValue != null) {
			fuzzyValue = fuzzyValue.trim();
			creditApplication.setFuzzyValue(fuzzyValue);
		}
		Pagination pagination = parameter2Pagination(page, rows);
		Pagination pagination1 = financeMoneyService.selectRecevieListHQ(pagination, creditApplication);
		return pagination1;
	}

	/**
	 * 组装pagination
	 * 
	 * @param page 当前页
	 * @param rows 行
	 * @return Pagination
	 */
	private Pagination parameter2Pagination(String page, String rows) {
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		return pagination;
	}

	@RequestMapping(value = "testUpdateClientApplyHistory")
	public @ResponseBody
	Message testUpdateClientApplyHistory() {
		Message message = new Message();
		message = returnPlanService.updateClientMission();

		return message;

	}
}
