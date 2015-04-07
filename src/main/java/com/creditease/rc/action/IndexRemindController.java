package com.creditease.rc.action;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.common.DepartmentEntity;
import com.creditease.rc.dao.IUrgeDao;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.LocalOfficeIdDTO;
import com.creditease.rc.domain.LocalOverdueInfoResponse;
import com.creditease.rc.domain.LocalReturnSchemeResponse;
import com.creditease.rc.domain.LocalSellIdDTO;
import com.creditease.rc.domain.OverDueObj;
import com.creditease.rc.domain.SystemAnnouncement;
import com.creditease.rc.domain.Urge;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.ICustomerReturnVisitService;
import com.creditease.rc.service.IIndexRemindService;
import com.creditease.rc.service.IRural2CreditService;
import com.creditease.rc.service.IStatisticalinfoService;
import com.creditease.rc.service.ISystemInfoService;
import com.creditease.rc.service.IUrgeService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.CurrencyUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.SmpWSUtil;
import com.creditease.rc.vo.CountInfo;
import com.creditease.rc.vo.DepartmentCountInfo;
import com.creditease.rc.vo.IndexRemindVo;
import com.creditease.rc.vo.UrgeVo;

/**
 * 首页提醒
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("indexremindcontroller")
public class IndexRemindController {

	@Resource
	private IIndexRemindService iIndexRemindService;

	@Resource
	private ISystemInfoService systemInfoService;

	@Resource
	private IRural2CreditService rural2CreditService;

	@Resource
	IStatisticalinfoService statisticalinfoService;

	@Resource
	private SmpWSUtil smpWSUtil;

	@Resource
	private IUrgeService urgeService;

	@Resource
	private ICustomerReturnVisitService customerReturnVisitService;
	@Resource
	private IUrgeDao urgeDao;

	/**
	 * 获得首页提醒
	 * 
	 * @param session
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("getRemind")
	public ModelAndView getRemind(HttpSession session) {
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
		CreditApplication creditApplication = new CreditApplication();
		creditApplication.setAuthList(sqlsid);
		SystemAnnouncement sAnnouncement = new SystemAnnouncement();
		Pagination pagination = new Pagination();
		String fuzzyValue = "";
		String sort = "";
		String order = "";
		pagination = systemInfoService.searchSystemAnnouncements(sAnnouncement, pagination, fuzzyValue, sort, order, session);
		List<SystemAnnouncement> l = pagination.getRows();
		int num = 1;
		Collections.sort(l, new Comparator<SystemAnnouncement>() {
			@Override
			public int compare(SystemAnnouncement o1, SystemAnnouncement o2) {
				// TODO Auto-generated method stub
				Calendar firstCalendar = Calendar.getInstance();
				if (o1.getUpdateTime() != null) {
					firstCalendar.setTime(o1.getUpdateTime());
				} else {
					firstCalendar.setTime(o1.getCreateTime());
				}

				Calendar secondCalendar = Calendar.getInstance();
				if (o2.getUpdateTime() != null) {
					secondCalendar.setTime(o2.getUpdateTime());
				} else {
					secondCalendar.setTime(o2.getCreateTime());
				}
				return secondCalendar.compareTo(firstCalendar);
			}
		});
		for (SystemAnnouncement s : l) {
			s.setNumber(String.valueOf(num));
			num += 1;
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/layout/home.jsp");
		modelAndView.addObject("sAnnouncements", l);

		// IndexRemindVo
		// indexRemind=iIndexRemindService.selectRemind(creditApplication);
		// modelAndView.addObject("indexRemind",indexRemind);
		return modelAndView;
	}

	/**
	 * 
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping("getReminds")
	public ModelAndView getReminds(HttpSession session, HttpServletRequest request) {
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
		CreditApplication creditApplication = new CreditApplication();
		creditApplication.setAuthList(sqlsid);
		SystemAnnouncement sAnnouncement = new SystemAnnouncement();
		Pagination pagination = new Pagination();
		String fuzzyValue = "";
		String sort = "";
		String order = "";
		pagination = systemInfoService.searchSystemAnnouncements(sAnnouncement, pagination, fuzzyValue, sort, order, session);
		List<SystemAnnouncement> l = pagination.getRows();
		int num = 1;
		Collections.sort(l, new Comparator<SystemAnnouncement>() {
			@Override
			public int compare(SystemAnnouncement o1, SystemAnnouncement o2) {
				// TODO Auto-generated method stub
				Calendar firstCalendar = Calendar.getInstance();
				if (o1.getUpdateTime() != null) {
					firstCalendar.setTime(o1.getUpdateTime());
				} else {
					firstCalendar.setTime(o1.getCreateTime());
				}

				Calendar secondCalendar = Calendar.getInstance();
				if (o2.getUpdateTime() != null) {
					secondCalendar.setTime(o2.getUpdateTime());
				} else {
					secondCalendar.setTime(o2.getCreateTime());
				}
				return secondCalendar.compareTo(firstCalendar);
			}
		});
		for (SystemAnnouncement s : l) {
			s.setNumber(String.valueOf(num));
			num += 1;
		}
		ModelAndView modelAndView = new ModelAndView();
		IndexRemindVo indexRemind = iIndexRemindService.selectRemind(creditApplication);

		/**
		 * 目前单独查了一下放款统计：实际上也通过视图来做了放款统计，但是由于找不到错误原因 为了 保证数据正确性，现用稍微麻烦的 方法 解决
		 * 现有问题 之后会改进
		 **/
		/** BEGIN **/
		// 添加权限查询
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("authList", sqlsid);
		List<HashMap<String, Object>> loanPortResults = statisticalinfoService.queryListForLoanPort(queryMap);
		// 接下来统计 本月 和总共 放款笔数 和金额
		String current = DateUtil.dateConvertString(new Date(), "yyyy-MM");// 获得当前
																			// 年
																			// 月
		int currentCount = 0;// 当月笔数统计
		int totalCount = 0;// 放款总笔数统计
		BigDecimal currentAmount = new BigDecimal("0");
		BigDecimal totalAmount = new BigDecimal("0");
		for (HashMap<String, Object> lmap : loanPortResults) {
			totalCount++;
			// System.out.println("" + lmap.get("AMOUNT"));
			totalAmount = totalAmount.add((BigDecimal) lmap.get("AMOUNT"));
			if (CommonUtil.isNotEmpty((String) lmap.get("SIGNAGREEMENTDATE")) && ((String) lmap.get("SIGNAGREEMENTDATE")).substring(0, 7).equals(current)) {
				currentAmount = currentAmount.add((BigDecimal) lmap.get("AMOUNT"));
				currentCount++;
			}
		}
		java.text.DecimalFormat df = new java.text.DecimalFormat("#,##0.00");
		modelAndView.addObject("currentCount", currentCount);
		modelAndView.addObject("totalCount", totalCount);
		modelAndView.addObject("currentAmount", df.format(currentAmount));
		modelAndView.addObject("totalAmount", df.format(totalAmount));
		/** END **/
		/**
		 * 目前单独查了一下放款统计：实际上也通过视图来做了放款统计，但是由于找不到错误原因 为了 保证数据正确性，现用稍微麻烦的 方法 解决
		 * 现有问题 之后会改进
		 **/
		/** 单独查了一遍改登录用户名下有多少咨询 **/
		Map<String, String> querySultMap = new HashMap<String, String>();
		querySultMap.put("authList", sqlsid);
		int conSultCount = iIndexRemindService.conSultCount(querySultMap);
		CountInfo getConsultToApply = indexRemind.getConsultToApply();
		int consultToApplyCount = getConsultToApply.getRemindCount();
		BigDecimal conSultCountB = new BigDecimal(conSultCount);
		BigDecimal consultToApplyCountB = new BigDecimal(consultToApplyCount);
		BigDecimal conversionRate = new BigDecimal(0);
		if (conSultCountB.compareTo(new BigDecimal(0)) == 0) {
			conversionRate = new BigDecimal(0);
		} else {
			conversionRate = consultToApplyCountB.divide(conSultCountB, 4, BigDecimal.ROUND_HALF_EVEN);

		}
		conversionRate = conversionRate.multiply(new BigDecimal(100));
		getConsultToApply.setRemindSum(conversionRate.doubleValue());

		/** 单独查了一遍改登录用户名下有多少咨询END **/

		/** 调用贷后WebService查询逾期信息 **/
		// String departmentId =
		// SpringSecurityUtils.getCurrentUser().getDepartmentId().toString();
		// String userId = SpringSecurityUtils.getCurrentUser().getUserId();
		//
		// List<LocalOfficeIdDTO> localOfficeIdDTOList = new
		// ArrayList<LocalOfficeIdDTO>();
		// LocalOfficeIdDTO localOfficeIdDTO = new LocalOfficeIdDTO();
		// localOfficeIdDTO.setOfficeId(departmentId);
		// List<LocalSellIdDTO> localSellIdDTOsList = new
		// ArrayList<LocalSellIdDTO>();
		// LocalSellIdDTO localSellIdDTO = new LocalSellIdDTO();
		// localSellIdDTO.setSellId(userId);
		// localSellIdDTOsList.add(localSellIdDTO);
		// localOfficeIdDTO.setLocalSellIdDTOs(localSellIdDTOsList);
		// localOfficeIdDTOList.add(localOfficeIdDTO);
		// LocalOverdueInfoResponse localOverdueInfoResponse =
		// rural2CreditService.overdueInfo(localOfficeIdDTOList);
		/** 调用贷后WebService查询逾期信息 END **/
		/** 销售计划 begin **/
		// Map<String, String> quertyMap = new HashMap<String, String>();
		// Calendar calendarS = Calendar.getInstance();
		// calendarS.set(calendarS.get(Calendar.YEAR), 0, 1);
		// Calendar calendarE = Calendar.getInstance();
		// calendarE.set(Calendar.DATE, 1);
		// System.out.println(calendarE);
		// System.out.println(calendarE);
		// System.out.println(calendarE);
		// System.out.println(calendarE);
		// String areaDepartmentIds = null;
		// List<DepartmentEntity> departmentEntities =
		// smpWSUtil.getDepartmentList();
		// if (CommonUtil.isNotEmpty(departmentEntities)) {
		// for (DepartmentEntity departmentEntity : departmentEntities) {
		// areaDepartmentIds = areaDepartmentIds + "," +
		// departmentEntity.getDepartmentId();
		// }
		// areaDepartmentIds = areaDepartmentIds.substring(5);
		// }
		// System.out.println(areaDepartmentIds);
		// System.out.println(areaDepartmentIds);
		// System.out.println(areaDepartmentIds);
		// System.out.println(areaDepartmentIds);
		// System.out.println(areaDepartmentIds);
		// String calS = calendarS.get(Calendar.YEAR) + "-" +
		// (calendarS.get(Calendar.MONTH) + 1) + "-01";
		// String calE = calendarE.get(Calendar.YEAR) + "-" +
		// (calendarE.get(Calendar.MONTH) + 1) + "-01";
		// quertyMap.put("authList", sqlsid);
		// quertyMap.put("areaDepartmentIds", areaDepartmentIds);
		// quertyMap.put("calS", calS);
		// quertyMap.put("calE", calE);
		// List<LoanRealAndPlanning> loanRealAndPlannings =
		// statisticalinfoService.queryLoanRealAndPlanningList(quertyMap);
		// int size = loanRealAndPlannings.size();
		// for (int i = 0; i < size; i++) {
		//
		// }
		// BigDecimal rAmountTotal = new BigDecimal("0");
		// BigDecimal pAmountTotal = new BigDecimal("0");
		// BigDecimal rCountTotal = new BigDecimal("0");
		// BigDecimal pCountTotal = new BigDecimal("0");
		// for (LoanRealAndPlanning loanRealAndPlanning : loanRealAndPlannings)
		// {
		// rAmountTotal = rAmountTotal.add(loanRealAndPlanning.getrAmount() ==
		// null ? new BigDecimal("0")
		// : loanRealAndPlanning.getrAmount());
		// pAmountTotal = pAmountTotal.add(loanRealAndPlanning.getpAmount() ==
		// null ? new BigDecimal("0")
		// : loanRealAndPlanning.getpAmount());
		// BigDecimal amountRatioTotal = new BigDecimal(0);
		// if (pAmountTotal.compareTo(new BigDecimal(0)) != 0) {
		// amountRatioTotal = rAmountTotal.divide(pAmountTotal, 4,
		// BigDecimal.ROUND_HALF_EVEN);
		// }
		// loanRealAndPlanning.setAmountRatioTotal(amountRatioTotal);
		// rCountTotal = rCountTotal.add(new
		// BigDecimal(loanRealAndPlanning.getrCount() == null ? 0
		// : loanRealAndPlanning.getrCount()));
		// pCountTotal = pCountTotal.add(new
		// BigDecimal(loanRealAndPlanning.getpCount() == null ? 0
		// : loanRealAndPlanning.getpCount()));
		// BigDecimal countRatioTotal = new BigDecimal(0);
		// if (pCountTotal.compareTo(new BigDecimal(0)) != 0) {
		// countRatioTotal = rCountTotal.divide(pCountTotal, 4,
		// BigDecimal.ROUND_HALF_EVEN);
		// }
		// loanRealAndPlanning.setCountRatioTotal(countRatioTotal);
		// BigDecimal rAmount = loanRealAndPlanning.getrAmount();
		// BigDecimal pAmount = loanRealAndPlanning.getpAmount();
		// BigDecimal amountRatio = new BigDecimal(0);
		// if (pAmount != null && pAmount.compareTo(new BigDecimal(0)) != 0) {
		// amountRatio = rAmount.divide(pAmount, 4, BigDecimal.ROUND_HALF_EVEN);
		// }
		// loanRealAndPlanning.setAmountRatio(amountRatio);
		// BigDecimal rCount = new BigDecimal(loanRealAndPlanning.getrCount() ==
		// null ? 0
		// : loanRealAndPlanning.getrCount());
		// BigDecimal pCount = new BigDecimal(loanRealAndPlanning.getpCount() ==
		// null ? 0
		// : loanRealAndPlanning.getpCount());
		// BigDecimal countRatio = new BigDecimal(0);
		// if (pCount.compareTo(new BigDecimal(0)) != 0) {
		// countRatio = rCount.divide(pCount, 4, BigDecimal.ROUND_HALF_EVEN);
		// }
		// loanRealAndPlanning.setCountRatio(countRatio);
		// }
		// modelAndView.addObject("loanRealAndPlannings", loanRealAndPlannings);
		/** 销售计划 end **/
		modelAndView.setViewName("/jsp/rc/layout/myHome.jsp");
		modelAndView.addObject("indexRemind", indexRemind);
		modelAndView.addObject("sAnnouncements", l);

		/** 贷后逾期统计信息 **/
		// modelAndView.addObject("localOverdueInfoResponse",
		// localOverdueInfoResponse);
		/** 咨询总数 **/
		modelAndView.addObject("conSultCount", conSultCount);

		/**
		 * 催收提醒 begin
		 */
		/*
		 * List<UrgeVo> urgeLists = urgeService.querUrgeCountList();
		 * 
		 * System.out.println(urgeLists.size());
		 * 
		 * List<String> toDayList = new ArrayList<String>(); // 承诺今天还款的单子的集合
		 * 
		 * List<String> threeDayList = new ArrayList<String>(); // 承诺日期是三个工作日的集合
		 * 
		 * List<String> sevenDayList = new ArrayList<String>(); // 承诺日期是七个工作日的集合
		 * 
		 * Calendar calendar = Calendar.getInstance(); Date toDayDate =
		 * calendar.getTime(); Date dtToDay = null; for (UrgeVo urgeVo :
		 * urgeLists) { Date curentPromiseTime = urgeVo.getCurentPromiseTime();
		 * // 判断当前承诺日期是否是今天 String curentPromiseTimea = DateUtil
		 * .dateConvertString(curentPromiseTime); Date between_today =
		 * DateUtil.getWeekDay(toDayDate, 0); Date between_threeday =
		 * DateUtil.getWeekDay(toDayDate, 2); Date between_sevenday =
		 * DateUtil.getWeekDay(toDayDate, 6);
		 * 
		 * // 当天的 String between_toDay =
		 * DateUtil.dateConvertString(between_today); // 三个工作日的 String
		 * between_threeDay = DateUtil .dateConvertString(between_threeday); //
		 * 七个工作日的 String between_sevenDay = DateUtil
		 * .dateConvertString(between_sevenday);
		 * 
		 * DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		 * 
		 * // 获取本次承诺还款日期 Date dt1; try { dt1 = df1.parse(curentPromiseTimea); //
		 * 当天的 dtToDay = df1.parse(between_toDay); // 三个工作日的 Date dtThree =
		 * df1.parse(between_threeDay); // 七个工作日的 Date dtSeven =
		 * df1.parse(between_sevenDay);
		 * 
		 * if (dt1.getTime() == dtToDay.getTime()) {
		 * toDayList.add(curentPromiseTimea);
		 * threeDayList.add(curentPromiseTimea);
		 * sevenDayList.add(curentPromiseTimea); } else if (toDayDate.getTime()
		 * <= dt1.getTime() && dt1.getTime() <= dtThree.getTime()) {
		 * threeDayList.add(curentPromiseTimea);
		 * sevenDayList.add(curentPromiseTimea); } else if (toDayDate.getTime()
		 * <= dt1.getTime() && dt1.getTime() <= dtSeven.getTime()) {
		 * sevenDayList.add(curentPromiseTimea); } } catch (ParseException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); } }
		 * 
		 * modelAndView.addObject("toDay", toDayList.size());
		 * modelAndView.addObject("threeDay", threeDayList.size());
		 * modelAndView.addObject("sevenDay", sevenDayList.size());
		 *//**
		 * end
		 */

		/**
		 * 回访提醒 begin
		 */
		/*
		 * 
		 * List<Map> toDayCustomerList = customerReturnVisitService.selectWarn(
		 * request, 0); List<Map> threeDayCustomerList =
		 * customerReturnVisitService.selectWarn( request, 2); List<Map>
		 * sevenDayCustomerList = customerReturnVisitService.selectWarn(
		 * request, 6); String id1s = ""; String id3s = ""; String id7s = "";
		 * for (Map object : toDayCustomerList) { Map<String, Object> map =
		 * (Map<String, Object>) object; id1s = id1s +
		 * map.get("CREDITAPPLICATION_ID") + ","; }
		 * 
		 * for (Map object : threeDayCustomerList) { Map<String, Object> map =
		 * (Map<String, Object>) object; id3s = id3s +
		 * map.get("CREDITAPPLICATION_ID") + ","; }
		 * 
		 * for (Map object : sevenDayCustomerList) { Map<String, Object> map =
		 * (Map<String, Object>) object; id7s = id7s +
		 * map.get("CREDITAPPLICATION_ID") + ","; } if (id1s.length() > 0) {
		 * id1s = id1s.substring(0, id1s.length() - 1); } if (id3s.length() > 0)
		 * { id3s = id3s.substring(0, id3s.length() - 1); } if (id7s.length() >
		 * 0) { id7s = id7s.substring(0, id7s.length() - 1); }
		 * modelAndView.addObject("id1s", id1s); modelAndView.addObject("id3s",
		 * id3s); modelAndView.addObject("id7s", id7s);
		 * modelAndView.addObject("toDayCustomerList",
		 * toDayCustomerList.size());
		 * modelAndView.addObject("threeDayCustomerList",
		 * threeDayCustomerList.size());
		 * modelAndView.addObject("sevenDayCustomerList",
		 * sevenDayCustomerList.size());
		 *//**
		 * end
		 */
		/**/
		return modelAndView;
	}

	/**
	 * 查询营业部经理业绩统计
	 * 
	 * @return Pagination
	 */
	@RequestMapping("selectDepartmentCountInfo")
	public @ResponseBody
	Pagination selectDepartmentCountInfo() {
		Pagination pagination = new Pagination();
		List<DepartmentCountInfo> list = iIndexRemindService.selectDepartmentCountInfo();
		DepartmentCountInfo departmentCountInfo = new DepartmentCountInfo();
		departmentCountInfo.setMonthName("总计");
		int count = 0;
		double sum = 0.0;
		for (int i = 0; i < list.size(); i++) {
			int pCount = list.get(i).getPaymentBusinessCount();
			count += pCount;
			double pSum = list.get(i).getPaymentBusinessSum();
			sum = CurrencyUtil.add(sum, pSum);
		}
		departmentCountInfo.setPaymentBusinessCount(count);
		departmentCountInfo.setPaymentBusinessSum(sum);
		list.add(departmentCountInfo);
		pagination.setItems(list);
		return pagination;
	}

	// 返回催收列表的myHome.jsp页面
	@RequestMapping("returnMyHomeJSP")
	@ResponseBody
	public Map returnMyHomeJSP(HttpServletRequest request) {
		/** 郝强添加 今日逾期提醒 **/
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

		Set<Long> set = overDueObjMap.keySet();
		List<Long> creditApplicationIds = urgeDao.queryInThePayment();
		String creditapplicationIds = "";
		Map<Long, String> overmap = new HashMap<Long, String>();
		for (Long applicationId : set) {
			if (!creditApplicationIds.contains(applicationId)) {
				continue;
			}
			List<LocalReturnSchemeResponse> localReturnSchemeResponses = rural2CreditService.returnScheme(applicationId);

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
				String getOverDays = localReturnSchemeResponse.getOverDays();

				if ("1".equals(isOverdue) && "0".equals(isReturned) && "1".equals(getOverDays)) {
					// 逾期未还清且逾期一天
					creditapplicationIds = creditapplicationIds + applicationId + ",";
					overmap.put(applicationId, period);
					break;
				}
			}
		}
		// 接下来刨除这些单子中已经做了催收的单子
		// 得到了逾期未还清且逾期一天
		List<Long> creditapplicationIdList = new ArrayList<Long>();
		if (CommonUtil.isNotEmpty(creditapplicationIds)) {

			creditapplicationIds = creditapplicationIds.substring(0, creditapplicationIds.length() - 1);
			List<Urge> urges = urgeService.queryUrgeBycreditapplicationIds(creditapplicationIds);
			for (Urge urge : urges) {
				Long getCreditapplicationId = urge.getCreditapplicationId();
				String getRepaymentCycle = urge.getRepaymentCycle();
				String period = overmap.get(getCreditapplicationId);
				if (CommonUtil.isNotEmpty(getRepaymentCycle) && getRepaymentCycle.equals(period)) {
					overmap.remove(getCreditapplicationId);
				}
			}
		}
		Set<Long> set2 = overmap.keySet();
		for (Long long1 : set2) {
			creditapplicationIdList.add(long1);
		}
		// 放到session中
		request.getSession().setAttribute("applicationIdListFormIndexByOverdue", creditapplicationIdList);

		/** 郝强添加 今日逾期提醒 **/

		List<UrgeVo> urgeLists = urgeService.querUrgeCountList();

		List<String> toDayList = new ArrayList<String>(); // 承诺今天还款的单子的集合

		List<String> threeDayList = new ArrayList<String>(); // 承诺日期是三个工作日的集合

		List<String> sevenDayList = new ArrayList<String>(); // 承诺日期是七个工作日的集合

		Calendar calendar = Calendar.getInstance();
		Date toDayDate = calendar.getTime();
		String temp = DateUtil.dateConvertString(toDayDate);
		toDayDate = DateUtil.stringConvertDate(temp);
		Date dtToDay = null;
		Date dtThree = null;
		Date dtSeven = null;
		String curentPromiseTimea = null;
		Date dt1 = null;
		for (UrgeVo urgeVo : urgeLists) {
			Date curentPromiseTime = urgeVo.getCurentPromiseTime();

			// 判断当前承诺日期是否是今天 并且不为空的数据
			if (curentPromiseTime != null) {
				curentPromiseTimea = DateUtil.dateConvertString(curentPromiseTime);

				Date between_today = DateUtil.getWeekDay(toDayDate, 0);
				Date between_threeday = DateUtil.getWeekDay(toDayDate, 2);
				Date between_sevenday = DateUtil.getWeekDay(toDayDate, 6);

				// 当天的
				String between_toDay = DateUtil.dateConvertString(between_today);
				// 三个工作日的
				String between_threeDay = DateUtil.dateConvertString(between_threeday);
				// 七个工作日的
				String between_sevenDay = DateUtil.dateConvertString(between_sevenday);

				DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");

				// 获取本次承诺还款日期
				try {
					if (curentPromiseTimea != null) {
						dt1 = df1.parse(curentPromiseTimea);
					}
					if (df1 != null) {
						// 当天的
						dtToDay = df1.parse(between_toDay);
						// 三个工作日的
						dtThree = df1.parse(between_threeDay);
						// 七个工作日的
						dtSeven = df1.parse(between_sevenDay);
					}

					if (dt1.getTime() == dtToDay.getTime()) {
						toDayList.add(curentPromiseTimea);
					}
					if (toDayDate.getTime() <= dt1.getTime() && dt1.getTime() <= dtThree.getTime()) {
						threeDayList.add(curentPromiseTimea);
					}
					if (toDayDate.getTime() <= dt1.getTime() && dt1.getTime() <= dtSeven.getTime()) {
						sevenDayList.add(curentPromiseTimea);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		Map map = new HashMap();

		map.put("overDay", creditapplicationIdList.size());
		map.put("toDay", toDayList.size());
		map.put("threeDay", threeDayList.size());
		map.put("sevenDay", sevenDayList.size());
		return map;
	}

	// 首页回访提醒
	@RequestMapping("returnVisteMyHomeJSP")
	@ResponseBody
	public Map returnVisteMyHomeJSP(HttpServletRequest request) {
		List<Map> toDayCustomerList = customerReturnVisitService.selectWarn(request, 0);
		List<Map> threeDayCustomerList = customerReturnVisitService.selectWarn(request, 2);
		List<Map> sevenDayCustomerList = customerReturnVisitService.selectWarn(request, 6);
		List<Map> forePeriodDontCallBackList = customerReturnVisitService.selectDontCallBackOfThisMonth(request);
		String id1s = "";
		String id3s = "";
		String id7s = "";
		String idDontCallBackIdStr = "";
		for (Map object : toDayCustomerList) {
			Map<String, Object> map = (Map<String, Object>) object;
			id1s = id1s + map.get("CREDITAPPLICATION_ID") + ",";
		}

		for (Map object : threeDayCustomerList) {
			Map<String, Object> map = (Map<String, Object>) object;
			id3s = id3s + map.get("CREDITAPPLICATION_ID") + ",";
		}

		for (Map object : sevenDayCustomerList) {
			Map<String, Object> map = (Map<String, Object>) object;
			id7s = id7s + map.get("CREDITAPPLICATION_ID") + ",";
		}

		for (Map object : forePeriodDontCallBackList) {
			Map<String, Object> map = (Map<String, Object>) object;
			idDontCallBackIdStr = idDontCallBackIdStr + map.get("CREDITAPPLICATION_ID") + ",";
		}
		if (id1s.length() > 0) {
			id1s = id1s.substring(0, id1s.length() - 1);
		}
		if (id3s.length() > 0) {
			id3s = id3s.substring(0, id3s.length() - 1);
		}
		if (id7s.length() > 0) {
			id7s = id7s.substring(0, id7s.length() - 1);
		}
		if (idDontCallBackIdStr.length() > 0) {
			idDontCallBackIdStr = idDontCallBackIdStr.substring(0, idDontCallBackIdStr.length() - 1);
		}
		if (CommonUtil.isEmpty(id1s)) {
			id1s = "0";
		}
		if (CommonUtil.isEmpty(id3s)) {
			id3s = "0";
		}
		if (CommonUtil.isEmpty(id7s)) {
			id7s = "0";
		}
		if (CommonUtil.isEmpty(idDontCallBackIdStr)) {
			idDontCallBackIdStr = "0";
		}
		Map map = new HashMap();
		map.put("id1s", id1s);
		map.put("id3s", id3s);
		map.put("id7s", id7s);
		map.put("idDontCallBackIdStr", idDontCallBackIdStr);
		map.put("toDayCustomerList", toDayCustomerList.size());
		map.put("threeDayCustomerList", threeDayCustomerList.size());
		map.put("sevenDayCustomerList", sevenDayCustomerList.size());
		map.put("forePeriodDontCallBackList", forePeriodDontCallBackList.size());
		return map;
	}
}
