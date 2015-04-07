package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.LocalReturnAmountRequest;
import com.creditease.rc.domain.LocalReturnAmountResult;
import com.creditease.rc.domain.LocalReturnSchemeResponse;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.ReceivedRecord;
import com.creditease.rc.framework.exception.AppBusinessException;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IAccountInfoService;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.IReturnPlanService;
import com.creditease.rc.service.IRural2CreditService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DicUtil;

/**
 * 还款计划
 * 
 * @author zhangman
 * @2013-6-8上午10:47:24
 *                     Copyright: Copyright (c) 2012
 *                     Company: 普信恒业科技发展（北京）有限公司
 */
@Controller
@RequestMapping(value = "returnPlanController")
public class ReturnPlanController {

	@Resource
	private IRural2CreditService iRural2CreditService;
	@Resource
	private ICreditApplicationService creditApplicationService;

	@Resource
	private IAccountInfoService accountInfoService;

	@Resource
	private IReturnPlanService returnPlanService;

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
	 * 生成还款计划
	 * 
	 * @param creditapplicationId 申请id
	 * @return 消息
	 */
	@RequestMapping(value = "clientApply")
	public @ResponseBody
	Message clientApply(String groupNumber) {
		Message message = new Message();
		if (groupNumber != null) {
			CreditApplication credit = creditApplicationService.selectByGroupNumber(groupNumber);
			if (credit != null) {
				message = iRural2CreditService.clientApply(credit.getCreditapplicationId().longValue());
			}
		} else {
			message.setMsg("生成还款计划失败，请核对业务单号是否正确");
		}
		return message;
	}

	/**
	 * 查询还款计划
	 * 
	 * @param creditapplicationId 申请id
	 * @return 还款计划
	 */
	@RequestMapping(value = "returnScheme")
	public @ResponseBody
	List<LocalReturnSchemeResponse> returnScheme(String groupNumber) {
		List<LocalReturnSchemeResponse> list = new ArrayList<LocalReturnSchemeResponse>();
		if (groupNumber != null) {
			CreditApplication credit = creditApplicationService.selectByGroupNumber(groupNumber);
			if (credit != null) {
				list = iRural2CreditService.returnScheme(credit.getCreditapplicationId().longValue());
			}
		}
		return list;
	}

	/** 以下为郝强提交 begin **/

	/**
	 * 跳转到还款计划页面
	 * 
	 * @author 郝强
	 * @param creditApplication creditApplication对象
	 * @return ModelAndView
	 */
	@RequestMapping(value = "returnPlanJSP")
	public ModelAndView returnPlanJSP(CreditApplication creditApplication) {

		CreditApplication application = creditApplicationService.selectCreditApplicationIsTrue(creditApplication);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/receivables/returnPlan.jsp");

		if (application != null) {
			modelAndView.addObject("creditApplication", application);
		}
		return modelAndView;
	}

	/**
	 * 打开还款计划dialog
	 * 
	 * @author 郝强
	 * @param receivedRecord 收款登记对象
	 * @return ModelAndView
	 */
	@RequestMapping(value = "registrationDialog")
	public ModelAndView registrationDialog(ReceivedRecord receivedRecord) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/receivables/registrationDialog.jsp");
		modelAndView.addObject("receivedRecord", receivedRecord);

		return modelAndView;
	}

	/**
	 * 查询卡信息
	 * 
	 * @author 郝强
	 * @param creditapplicationId 信贷申请主键
	 * @return List<AccountInfo>卡信息List
	 */
	@RequestMapping(value = "cardInfoGrid", method = RequestMethod.POST)
	public @ResponseBody
	List<AccountInfo> cardInfoGrid(CreditApplication creditApplication) {

		if (creditApplication != null) {
			List<AccountInfo> accountInfoList = accountInfoService.selectCardInfo(creditApplication
					.getCreditapplicationId());
			if (CommonUtil.isNotEmpty(accountInfoList)) {
				for (AccountInfo accountInfo : accountInfoList) {
					String getBankNum = accountInfo.getBankNum();
					if (getBankNum != null || !"".equals(getBankNum)) {
						accountInfo.setOnUsed(DicUtil.convertCodeKeyToCodeValue("bankNum", getBankNum));
					}
				}
			} else {
				throw new AppBusinessException("accountInfoList为空");
			}
			return accountInfoList;
		} else {
			throw new AppBusinessException("取不到getCreditapplicationId");
		}

	}

	/**
	 * 还款计划列表查询
	 * 
	 * @author 郝强
	 * @param page 页
	 * @param rows 行
	 * @param creditapplicationId 业务申请单主键
	 * @return 分页List
	 */
	@RequestMapping(value = "returnPlanDataGrid", method = RequestMethod.POST)
	public @ResponseBody
	Pagination returnPlanDataGrid(@RequestParam(required = false) String page,
			@RequestParam(required = false) String rows, Long creditapplicationId) {
		Pagination pagination = new Pagination();
		if (creditapplicationId != null) {
			if (!StringUtils.isBlank(page)) {
				pagination.setPage(Integer.valueOf(page));
			}
			if (!StringUtils.isBlank(rows)) {
				pagination.setPageSize(Integer.valueOf(rows));
			}
		}
		return returnPlanService.queryReturnPlanDataGrid(creditapplicationId, pagination);
	}

	@RequestMapping(value = "queryReturnAmount")
	public @ResponseBody
	LocalReturnAmountResult queryReturnAmount(LocalReturnAmountRequest localReturnAmountRequest) {
		LocalReturnAmountResult localReturnAmountResult = iRural2CreditService.returnAmount(localReturnAmountRequest);
		localReturnAmountResult.getTotalReceivableMoney();
		// System.out.println(localReturnAmountResult.getTotalReceivableMoney());
		return localReturnAmountResult;
	}

	/** 以上为郝强提交 end **/

	@RequestMapping(value = "insertBatchReturnPlans")
	public @ResponseBody
	Message insertBatchReturnPlans() {
		Message message = returnPlanService.insertBatchReturnPlans();
		return message;
	}

	@RequestMapping(value = "haveNoReturnPlanList")
	public @ResponseBody
	Pagination haveNoReturnPlanList(@RequestParam(required = false) String page,
			@RequestParam(required = false) String rows) {
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		Map<String, String> queryMap = new HashMap<String, String>();
		return returnPlanService.queryHaveNoReturnPlanList(pagination, queryMap);
	}
}
