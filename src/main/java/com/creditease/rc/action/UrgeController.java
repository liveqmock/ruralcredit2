package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.creditease.rc.domain.*;
import com.creditease.rc.report.excel.*;
import com.creditease.rc.service.*;


import com.creditease.rc.domain.*;
import com.creditease.rc.report.excel.*;
import com.creditease.rc.service.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creditease.rc.vo.*;
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
import com.creditease.rc.app.credit.ReturnSchemeResult;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.report.excel.ExcelUtils;
import com.creditease.rc.report.excel.JsGridReportBase;
import com.creditease.rc.report.excel.TableData;
import com.creditease.rc.util.ComboUtil;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.JsonUtil;

@Controller
@RequestMapping("urgeController")
public class UrgeController {

	@Resource
	private IUrgeService urgeService;

	@Resource
	private IMessageInfoService messageInfoService;

	@Resource
	private ComboUtil comboUtil;

	@Resource
	private IUrgeLinkmanService iurgeLinkmanService;
    @Resource
    private IJsGridReportBaseXLSXService jsGridReportBaseXLSXService;


    @Resource
	private IRural2CreditService rural2CreditService;

	/**
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class, "receivedTime", new CustomDateEditor(dateFormat2, true));
	}

	/**
	 * 保存催收和催收联系人信息
	 * 
	 * @param urgeAndLinkmanVo
	 * @return
	 */
	@RequestMapping(value = "saveUrgeAndLinkmanVo")
	public @ResponseBody
	Message saveUrgeAndLinkmanVo(UrgeAndLinkmanVo urgeAndLinkmanVo) {

		Message message = new Message();
		// 获得还款日期(违约开始日期)
		urgeAndLinkmanVo.getUrgeVo().setRefundDate(urgeAndLinkmanVo.getUrgeVo().getBreakbegindate());
		message = urgeService.saveUrgeAndLinkmanVo(urgeAndLinkmanVo);
		return message;
	}


	/**
	 * 保存催收并返回页面
	 * 
	 * @param 参数中urgeAdd是在urge
	 *            .jsp中显示是保存页面还是查看页面，根据urgeAdd是否有值 催收
	 * @param creditapplicationId
	 *            业务申请单Id
	 * @return 页面
	 */
	@RequestMapping(value = "returnUrgeJSP")
	public ModelAndView returnUrgeJSP(UrgeVo urgeVo, Long creditapplicationId, String urgeAdd) {

		// 调用查询 上次承诺还款时间，金额方法
		urgeVo = urgeService.selectUrgeByCreditApplicationId(creditapplicationId);
		String previousPromiseTime = null;
		if (urgeVo != null) {
			// if(!urgeVo.getPreviousPromiseTime().equals(null)){
			if (urgeVo.getPreviousPromiseTime() != null) {
				previousPromiseTime = DateUtil.dateConvertString(urgeVo.getPreviousPromiseTime());
			}
		}
		// 获取当前时间
		Date date = new Date();

		String dateString = DateUtil.dateConvertString(date);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/business/urge.jsp");
		modelAndView.addObject("creditapplicationId", creditapplicationId);
		modelAndView.addObject("urgeAdd", urgeAdd);
		modelAndView.addObject("urgeVo", urgeVo);
		modelAndView.addObject("previousPromiseTime", previousPromiseTime);
		modelAndView.addObject("dateString", dateString);

		// 违约开始日期
		Map<String, String> map = getBreakbegindateSave(dateString, creditapplicationId);

		modelAndView.addObject("breakbegindateSave", map.get("breakbegindateSave"));
		modelAndView.addObject("repaymentCycle", map.get("repaymentCycle"));
		return modelAndView;
	}

	/**
	 * 根据信贷申请ID 查询需要发送逾期短信的内容
	 * @param creditapplicationId
	 * @return
	 */
	@RequestMapping(value = "returnOverDueMsgRemindJSP")
	public ModelAndView returnOverDueMsgRemindJSP( Long creditapplicationId) {
		ModelAndView modelAndView = new ModelAndView();
		MessageInfoVo messageInfoVo = new MessageInfoVo();
		if(creditapplicationId != null){
			//根据信贷申请ID查询逾期短信内容相关信息
			messageInfoVo	= messageInfoService.queryOverDueInfoForSendMsgByCreditId(String.valueOf(creditapplicationId));
			modelAndView.setViewName("/jsp/rc/overDue/overDueMsgRemind.jsp");
			if(messageInfoVo != null){

			}else {
			    messageInfoVo = new MessageInfoVo();
			}
		}
		modelAndView.addObject("messageInfoVo", messageInfoVo);
		return modelAndView;
	}

	/**
	 * 根据逾期短信内容发送逾期短信
	 * @param messageInfoVo
	 * @return
	 */
	@RequestMapping(value = "sendOverDueRemindMsgByCreditId")
	public @ResponseBody Message sendOverDueRemindMsgByCreditId( MessageInfoVo messageInfoVo) throws RuntimeException{
		Message message = new Message();
		message.setSuccess(true);
		message.setMsg("逾期提醒短信发送成功...");
		messageInfoService.sendMsgForOverDueRemind(messageInfoVo);
		return message;
	}
	/**
	 * 查看催收
	 * 
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping("toViewCreditUrge")
	public ModelAndView toViewCreditUrge(UrgeVo urgeVo, Long urgeId, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/jsp/rc/business/urge.jsp");
		List<UrgeLinkman> urgelinkmanlist = null;
		String hour = null;
		String minute = null;
		if (urgeId != null) {
			// 根据urgeId查询催收信息
			urgeVo = urgeService.queryUrgeListByUrgeId(urgeId);
			String previousPromiseTime = null;
			if (urgeVo.getPreviousPromiseTime() != null) {
				previousPromiseTime = DateUtil.dateConvertString(urgeVo.getPreviousPromiseTime());
			}
			String urgeLongTime = urgeVo.getUrgeLongTime();
			if (CommonUtil.isNotEmpty(urgeLongTime) && !urgeLongTime.equals("0") && !urgeLongTime.equals(null)) {
				hour = urgeLongTime.substring(0, 2);
				minute = urgeLongTime.substring(2, 4);
			}
			Date urgeD = urgeVo.getUrgeDate();
			Date cPromiseTime = urgeVo.getCurentPromiseTime();
			Date bBeginDate = urgeVo.getBreakbegindate();

			String urgeDate = null; // 催收日期
			String curentPromiseTime = null; // 当前承诺还款时间
			String breakBeginDate = null; // 还款开始日期
			if (urgeD != null) {
				urgeDate = DateUtil.dateConvertString(urgeD);
			}
			if (cPromiseTime != null) {
				curentPromiseTime = DateUtil.dateConvertString(cPromiseTime);
			}
			if (bBeginDate != null) {
				breakBeginDate = DateUtil.dateConvertString(bBeginDate);
			}
			// 根据urgeId查询催收联系人
			urgelinkmanlist = iurgeLinkmanService.queryUrgeLinkmanList(urgeId);
			modelAndView.addObject("urgeVo", urgeVo);
			modelAndView.addObject("urgelinkmanlist", urgelinkmanlist);
			modelAndView.addObject("previousPromiseTime", previousPromiseTime);
			modelAndView.addObject("urgeDate", urgeDate);
			modelAndView.addObject("curentPromiseTime", curentPromiseTime);
			modelAndView.addObject("breakBeginDate", breakBeginDate);
			modelAndView.addObject("hour", hour);
			modelAndView.addObject("minute", minute);

		}
		return modelAndView;
	}

	@RequestMapping(value = "departMentCombo")
	public @ResponseBody
	List<Combobox> departMentCombo(HttpSession session) {

		List<String> authList = SpringSecurityUtils.getAuthList(session);
		List<Combobox> comboboxs = comboUtil.localDepartmentCombo(authList);

		return comboboxs;
	}

	@RequestMapping(value = "dateCombo")
	public @ResponseBody
	List<Combobox> dateCombo(HttpSession session) {

		List<Combobox> comboboxs = comboUtil.todayAndYesterday();

		return comboboxs;
	}

	// 根据催收id查看催收联系人信息
	@RequestMapping(value = "queryUrgeLinkmanList")
	public @ResponseBody
	List<UrgeLinkman> queryUrgeLinkmanList(Long urgelinkmanId) {
		return iurgeLinkmanService.queryUrgeLinkmanList(urgelinkmanId);
	}

	// 返回催收列表的modelAndView页面
	@RequestMapping("returnCreditUrgeListJSP")
	public ModelAndView returnCreditUrgeListJSP(Long urgeId, String urgeNum, HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("/jsp/rc/business/creditUrgeList.jsp");
		if (urgeNum != null) {
			int urgeNums = Integer.parseInt(urgeNum);
			String promiseRefundDateBegin = null;
			String promiseRefundDateEnd = null;
			// List<UrgeVo> urgeLists = urgeService.querUrgeCountList();
			Calendar calendar = Calendar.getInstance();
			Date toDayDate = calendar.getTime();
			promiseRefundDateBegin = DateUtil.dateConvertString(toDayDate);
			// 当天的
			if (urgeNums == 0) {
				Date between_today = DateUtil.getWeekDay(toDayDate, urgeNums);
				String between_toDay = DateUtil.dateConvertString(between_today);
				promiseRefundDateEnd = between_toDay;
				// 三个工作日的
			} else if (urgeNums == 2) {
				Date between_threeday = DateUtil.getWeekDay(toDayDate, urgeNums);
				String between_threeDay = DateUtil.dateConvertString(between_threeday);
				promiseRefundDateEnd = between_threeDay;
				// 七个工作日的
			} else if (urgeNums == 6) {
				Date between_sevenday = DateUtil.getWeekDay(toDayDate, urgeNums);
				String between_sevenDay = DateUtil.dateConvertString(between_sevenday);
				promiseRefundDateEnd = between_sevenDay;
			}
			modelAndView.addObject("promiseRefundDateBegin", promiseRefundDateBegin);
			modelAndView.addObject("promiseRefundDateEnd", promiseRefundDateEnd);
		}
		return modelAndView;
	}

	// 查询催收列表
	@RequestMapping(value = "queryUrgeList")
	public @ResponseBody
	Pagination queryUrgeList(@RequestParam(required = false) String page, @RequestParam(required = false) String rows,
			HttpSession session, String paramJsonMap, String sort, String order, String promiseRefundDateBegin,
			String promiseRefundDateEnd) {

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

		if (promiseRefundDateBegin != null) {
			pramMap.put("promiseRefundDateBegin", promiseRefundDateBegin);
			pramMap.put("promiseRefundDateEnd", promiseRefundDateEnd);
		}
		if (!"".equals(paramJsonMap) && paramJsonMap != null) {
			Map<String, String> temp = JsonUtil.getObject(paramJsonMap);
			String businessNumber = temp.get("businessNumber");
			String name = temp.get("name");
			String loanOfficerName = temp.get("loanOfficerName");
			String companyId = temp.get("companyId");
			String urgeDateBegin = temp.get("urgeDateBegin");
			String urgeDateEnd = temp.get("urgeDateEnd");
			promiseRefundDateBegin = temp.get("promiseRefundDateBegin");
			promiseRefundDateEnd = temp.get("promiseRefundDateEnd");
			pramMap.put("businessNumber", businessNumber);
			pramMap.put("name", name);
			pramMap.put("loanOfficerName", loanOfficerName);
			pramMap.put("companyId", companyId);
			pramMap.put("urgeDateBegin", urgeDateBegin);
			pramMap.put("urgeDateEnd", urgeDateEnd);
			pramMap.put("promiseRefundDateBegin", promiseRefundDateBegin);
			pramMap.put("promiseRefundDateEnd", promiseRefundDateEnd);
		}

		pagination = urgeService.queryUrgeList(pagination, pramMap);

		return pagination;
	}

	/**
	 * 查询小组列表 催收列表 导出excel
	 * 
	 * @param creditApplication
	 *            查询条件
	 * @param page
	 *            第几页
	 * @param rows
	 *            每页多少行
	 * @param session
	 *            http请求
	 * @return 小组申请分页列表
	 */
	@RequestMapping(value = "exportCreditApplicationForFinanceLoan")
	public void exportCreditApplicationForFinanceLoan(CreditApplication creditApplication, HttpSession session,
			HttpServletRequest request, HttpServletResponse response, String paramJsonMap, String sort, String order)
			throws Exception {

		Pagination pagination = new Pagination();
		// 导出全部数据
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

		if (!"".equals(paramJsonMap) && paramJsonMap != null) {
			Map<String, String> temp = JsonUtil.getObject(paramJsonMap);
			String businessNumber = temp.get("businessNumber");
			String name = temp.get("name");
			String loanOfficerName = temp.get("loanOfficerName");
			String companyName = temp.get("companyName");
			String urgeDateBegin = temp.get("urgeDateBegin");
			String urgeDateEnd = temp.get("urgeDateEnd");
			String promiseRefundDateBegin = temp.get("promiseRefundDateBegin");
			String promiseRefundDateEnd = temp.get("promiseRefundDateEnd");
			pramMap.put("businessNumber", businessNumber);
			pramMap.put("name", name);
			pramMap.put("loanOfficerName", loanOfficerName);
			pramMap.put("companyName", companyName);
			pramMap.put("urgeDateBegin", urgeDateBegin);
			pramMap.put("urgeDateEnd", urgeDateEnd);
			pramMap.put("promiseRefundDateBegin", promiseRefundDateBegin);
			pramMap.put("promiseRefundDateEnd", promiseRefundDateEnd);
		}
		// 调用查询催收列表的所有信息
		pagination = urgeService.queryUrgeList(pagination, pramMap);

		List<CreditApplication> list = pagination.getRows();// 获取数据
		String title = "催收列表";
		String[] hearders = new String[] { "业务单号", "借款人姓名", "分公司名称", "客户经理", "催收日期", "还款日期", "催收概括", "催收方式", "催收状态",
				"收回金额", "金额来源", "本次承诺还款金额", "本次承诺还款时间" };// 表头数组
		String[] fields = new String[] { "businessNumber", "name", "companyName", "loanOfficerName", "urgeDate",
				"refundDate", "urgeSummarize", "urgeWay", "urgeState", "reginMoney", "moneySource",
				"currentPromiseMoney", "curentPromiseTime" };// 对象属性数组
		TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders), fields);
		/*JsGridReportBase report = new JsGridReportBase(request, response);
		report.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);*/
        jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
        jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);
    };

	/*
	 * public List<UrgeVo> querUrgeCountList(int urgeNum) throws Exception {
	 * List<UrgeVo> urgeLists = urgeService.querUrgeCountList();
	 * 
	 * List<String> toDay = new ArrayList<String>(); // 承诺今天还款的单子的集合
	 * 
	 * List<String> threeDay = new ArrayList<String>(); // 承诺日期是三个工作日的集合
	 * 
	 * List<String> sevenDay = new ArrayList<String>(); // 承诺日期是七个工作日的集合
	 * 
	 * for (UrgeVo urgeVo : urgeLists) { Date toDayDate = new Date(); Date
	 * curentPromiseTime = urgeVo.getCurentPromiseTime(); Calendar cal =
	 * Calendar.getInstance(); cal.setTime(toDayDate);
	 * cal.setTime(curentPromiseTime); // 判断当前承诺日期是否是今天 String
	 * curentPromiseTimea = DateUtil.dateConvertString(curentPromiseTime); Date
	 * between_today = DateUtil.getWeekDay(toDayDate, urgeNum); Date
	 * between_threeday = DateUtil.getWeekDay(toDayDate, 2); Date
	 * between_sevenday = DateUtil.getWeekDay(toDayDate, 6);
	 * 
	 * // 当天的 String between_toDay = DateUtil.dateConvertString(between_today);
	 * // 三个工作日的 String between_threeDay =
	 * DateUtil.dateConvertString(between_threeday); // 七个工作日的 String
	 * between_sevenDay = DateUtil.dateConvertString(between_sevenday);
	 * 
	 * DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	 * 
	 * // 获取本次承诺还款日期 Date dt1 = df.parse(curentPromiseTimea); // 当天的 Date
	 * dtToDay = df.parse(between_toDay); // 三个工作日的 Date dtThree =
	 * df.parse(between_threeDay); // 七个工作日的 Date dtSeven =
	 * df.parse(between_sevenDay);
	 * 
	 * if (dt1.getTime() == dtToDay.getTime()) { toDay.add(curentPromiseTimea);
	 * threeDay.add(curentPromiseTimea); sevenDay.add(curentPromiseTimea); }
	 * else if (dt1.getTime() <= dtThree.getTime()) {
	 * threeDay.add(curentPromiseTimea); sevenDay.add(curentPromiseTimea); }
	 * else if (dt1.getTime() <= dtSeven.getTime()) {
	 * sevenDay.add(curentPromiseTimea); } } return urgeLists; }
	 */
	/** 根据选择的催收日期判断违约开始日期 **/
	@RequestMapping("getBreakbegindateSave")
	public @ResponseBody
	Map<String, String> getBreakbegindateSave(String urgeDateString, Long creditapplicationId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("success", "true");
		Date urgeDate = DateUtil.stringConvertDate(urgeDateString);

		List<LocalReturnSchemeResponse> localReturnSchemeResponses = rural2CreditService
				.returnScheme(creditapplicationId);

		Collections.sort(localReturnSchemeResponses, new Comparator<LocalReturnSchemeResponse>() {

			@Override
			public int compare(LocalReturnSchemeResponse o1, LocalReturnSchemeResponse o2) {
				// TODO Auto-generated method stub
				return o2.getPeriod().compareTo(o1.getPeriod());
			}
		});
		String period = "";
		String repayDate = "";
		for (LocalReturnSchemeResponse localReturnSchemeResponse : localReturnSchemeResponses) {
			Date getRepayDate = localReturnSchemeResponse.getRepayDate();
			String isOverdue = localReturnSchemeResponse.getIsOverdue();// 1代表逾期
			String isReturned = localReturnSchemeResponse.getIsReturned();// 1代表已还清
			Integer getPeriod = localReturnSchemeResponse.getPeriod();
			if (urgeDate.compareTo(getRepayDate) > 0 && "1".equals(isOverdue) && "0".equals(isReturned)) {
				// 逾期未还清
				period = getPeriod.toString();
				repayDate = DateUtil.dateConvertString(getRepayDate);
				break;
			}
		}
		if (CommonUtil.isEmpty(period) || CommonUtil.isEmpty(repayDate)) {
			map.put("success", "false");
		}
		map.put("breakbegindateSave", repayDate);
		map.put("repaymentCycle", period);

		return map;
	}

	/** 生成催收历史计划郝强添加 **/

	@RequestMapping(value = "creatUrgeHistory")
	public @ResponseBody
	Message creatUrgeHistory() {
		return urgeService.InsertCreateUrgeHistory();
	}

}
