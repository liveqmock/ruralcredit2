package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.security.User;
import com.creditease.rc.domain.LinkmanInvestigation;
import com.creditease.rc.domain.Message;
import com.creditease.rc.service.ICreditInvestigationService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.ObjectUtil;
import com.creditease.rc.vo.CreditInvestigationVo;

/**
 * Title: Description: 农村商贷系统研发 Copyright: Copyright (c) 2012 Company:
 * 普信恒业科技发展（北京）有限公司 Date: 2013-3-14
 * 
 * @author: 解兵丰
 * @version: v1.0
 */

@Controller
@RequestMapping(value = "creditInvestigation")
public class CreditInvestigationController {

	/**
	 * 
	 * @param binder
	 *            WebDataBinder对象
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@Resource
	private ICreditInvestigationService creditInvestigationService;

	/**
	 * 回显信用背景调查报告页面
	 * 
	 * @param creditInvestigation
	 *            信用背景调查报告对象
	 * @return 信用背景调查报告页面
	 */
	@RequestMapping("toAddAndEditPageCreditInvestigation")
	public ModelAndView toAddAndEditPageCreditInvestigation(
			CreditInvestigationVo creditInvestigation) {
		creditInvestigation.getCreditapplicationId();
		creditInvestigation = creditInvestigationService
				.selectNameAndSpousName(creditInvestigation
						.getCreditapplicationId());
		User user = SpringSecurityUtils.getCurrentUser();
		creditInvestigation.setRiskManagerId(user.getUserId());
		creditInvestigation.setRiskManagerName(user.getName_zh());
		if (creditInvestigation != null
				&& creditInvestigation.getCreditInvestigatioId() != null) {
			creditInvestigation = creditInvestigationService
					.showReturnCreditInvestigation(creditInvestigation
							.getCreditInvestigatioId());
		}
		ModelAndView modelAndView = new ModelAndView(
				"/jsp/rc/business/creditBackgroundInvestigation.jsp");
		modelAndView.addObject("creditInvestigation", creditInvestigation);
		return modelAndView;
	}

	/**
	 * 查看信用及背景调查 liuli 2013-05-03
	 * 
	 * @param creditInvestigationId
	 *            调查报告主键
	 * @param request
	 *            request
	 * @return ModelAndView
	 */
	@RequestMapping("toViewCreditInvestigation")
	public ModelAndView toViewCreditInvestigation(String creditInvestigationId,
			HttpServletRequest request) {

		CreditInvestigationVo creditInvestigation = null;
		// 根据信用和背景调查ID获取信用和背景VO对象
		if (creditInvestigationId != "") {
			creditInvestigation = creditInvestigationService
					.showReturnCreditInvestigation(Long
							.valueOf(creditInvestigationId));
			// 根据字典表将编码转换为汉字
			creditInvestigationService.displayNameByCode(creditInvestigation);

			if (creditInvestigation == null) {
				request.setAttribute("obj", "false");
			}

			ModelAndView modelAndView = new ModelAndView(
					"/jsp/rc/business/creditBackgroundInvestigationView.jsp");
			modelAndView.addObject("creditInvestigation", creditInvestigation);

            /*合规检查：查看信用及背景调查时，添加查看审批单附件*/
            modelAndView.addObject("showAttach", request.getParameter("showAttach"));

            return modelAndView;
		}

		return null;
	}

	/**
	 * 现已不用
	 * 
	 * @param creditInvestigation
	 *            调查对象
	 * @return ModelAndView
	 */
	@RequestMapping("toAddAndEditPageCreditInvestigationBak")
	public ModelAndView toAddAndEditPageCreditInvestigationBak(
			CreditInvestigationVo creditInvestigation) {
		ModelAndView modelAndView = this
				.toAddAndEditPageCreditInvestigation(creditInvestigation);
		modelAndView
				.setViewName("/jsp/rc/business/creditBackgroundInvestigationBak.jsp");
		return modelAndView;
	}

	/**
	 * 回显信用背景调查报告对象
	 * 
	 * @param creditInvestigation
	 *            信用背景调查报告对象
	 * @return 信用背景调查报告对象
	 */
	@RequestMapping("getCreditInvestigation")
	public @ResponseBody
	CreditInvestigationVo getCreditInvestigation(
			CreditInvestigationVo creditInvestigation) {
		creditInvestigation.getCreditapplicationId();
		creditInvestigation = creditInvestigationService
				.selectNameAndSpousName(creditInvestigation
						.getCreditapplicationId());
		User user = SpringSecurityUtils.getCurrentUser();
		creditInvestigation.setRiskManagerId(user.getUserId());
		creditInvestigation.setRiskManagerName(user.getName_zh());
		if (creditInvestigation != null
				&& creditInvestigation.getCreditInvestigatioId() != null) {
			creditInvestigation = creditInvestigationService
					.showReturnCreditInvestigation(creditInvestigation
							.getCreditInvestigatioId());
		}
		return creditInvestigation;
	}

	/**
	 * 保存信用及背景调查报告并返回页面
	 * 
	 * @param creditInvestigationVo
	 *            信用及背景调查报告对象
	 * @param creditapplicationId
	 *            业务申请单Id
	 * @return 页面
	 */
	@RequestMapping("saveCreditInvestigationAndReturnPage")
	public ModelAndView saveCreditInvestigationAndReturnPage(
			@ModelAttribute("creditInvestigationVo") CreditInvestigationVo creditInvestigationVo,
			Long creditapplicationId) {
		creditInvestigationService
				.addOrUpdateCreditInvestigation(creditInvestigationVo);
		ModelAndView modelAndView = new ModelAndView(
				"/jsp/rc/business/creditBackgroundInvestigation.jsp");
		return modelAndView;
	}

	/**
	 * 保存信用及背景调查报告
	 * 
	 * @param creditInvestigationVo
	 *            信用及背景调查报告对象
	 * @param creditapplicationId
	 *            业务申请单Id
	 * @return 是否保存成功
	 */
	@RequestMapping("saveCreditInvestigation")
	public @ResponseBody
	Message saveCreditInvestigation(
			CreditInvestigationVo creditInvestigationVo,
			Long creditapplicationId) {
		Message message = new Message();
		boolean isSuccess = false;
		List<LinkmanInvestigation> linkmanInvestigations = creditInvestigationVo.getLinkmanInvestigationList();
		List<LinkmanInvestigation> temps = new ArrayList<LinkmanInvestigation>();
		if (CommonUtil.isNotEmpty(linkmanInvestigations)) {
			for (int i = 0; i < linkmanInvestigations.size(); i++) {
				LinkmanInvestigation investigation = linkmanInvestigations
						.get(i);
				if (!ObjectUtil.checkObjectIsNull(investigation)) {
					temps.add(investigation);
				}
			}
		}
		linkmanInvestigations.clear();
		linkmanInvestigations.addAll(temps);
		Long h = (long) 1;
		if (CommonUtil.isNotEmpty(linkmanInvestigations)) {
			for (LinkmanInvestigation investigation : linkmanInvestigations) {
				investigation.setSeq(h);
				h++;
			}
		}
		isSuccess = creditInvestigationService
				.addOrUpdateCreditInvestigation(creditInvestigationVo);
		message.setSuccess(isSuccess);
		return message;
	}

	/**
	 * 保存并提交信用及背景调查报告
	 * 
	 * @param creditInvestigationVo
	 *            信用及背景调查报告对象
	 * @param creditapplicationId
	 *            业务申请单Id
	 * @return 是否保存成功
	 */
	@RequestMapping("submit")
	public @ResponseBody
	Message submit(CreditInvestigationVo creditInvestigationVo,
			Long creditapplicationId) {
		Message message = new Message();
		boolean isSuccess = false;
		
		List<LinkmanInvestigation> linkmanInvestigations = creditInvestigationVo.getLinkmanInvestigationList();
		List<LinkmanInvestigation> temps = new ArrayList<LinkmanInvestigation>();
		if (CommonUtil.isNotEmpty(linkmanInvestigations)) {
			for (int i = 0; i < linkmanInvestigations.size(); i++) {
				LinkmanInvestigation investigation = linkmanInvestigations
						.get(i);
				if (!ObjectUtil.checkObjectIsNull(investigation)) {
					temps.add(investigation);
				}
			}
		}
		linkmanInvestigations.clear();
		linkmanInvestigations.addAll(temps);
		Long h = (long) 1;
		if (CommonUtil.isNotEmpty(linkmanInvestigations)) {
			for (LinkmanInvestigation investigation : linkmanInvestigations) {
				investigation.setSeq(h);
				h++;
			}
		}
		isSuccess = creditInvestigationService.saveAndSubmit(
				creditInvestigationVo, creditapplicationId);
		message.setSuccess(isSuccess);
		return message;
	}

	/**
	 * 查询信用及背景调查报告List
	 * 
	 * @param creditapplicationId
	 *            信贷申请Id
	 * @param groupNumber
	 *            小组编号
	 * @return 信用及背景调查报告List
	 */
	@RequestMapping(value = "reportList")
	public @ResponseBody
	List<CreditInvestigationVo> reportList(Long creditapplicationId,
			String groupNumber) {
		List<CreditInvestigationVo> creditInvestigationVoList = new ArrayList<CreditInvestigationVo>();
		if (creditapplicationId != null) {
			creditInvestigationVoList = (List<CreditInvestigationVo>) creditInvestigationService
					.quertyReportList(creditapplicationId);
			if (CommonUtil.isEmpty(creditInvestigationVoList)) {
				CreditInvestigationVo creditInvestigationVo = new CreditInvestigationVo();
				creditInvestigationVo.setIsValid("0");
				creditInvestigationVo
						.setCreditapplicationId(creditapplicationId);
				creditInvestigationVo.setGroupNumber(groupNumber);
				creditInvestigationVoList.add(creditInvestigationVo);
			}
		}

		return creditInvestigationVoList;
	}
}
