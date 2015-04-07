package com.creditease.rc.action;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.security.User;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.WxPrize;
import com.creditease.rc.domain.WxupriRecord;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IPrizeManagementService;
import com.creditease.rc.service.IWxupriRecordService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DepartmentUtil;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.util.SmpWSUtil;
import com.creditease.rc.vo.DepartmentEntityVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("prizeManagementController")
public class PrizeManagementController {

	@Resource
	private IPrizeManagementService prizeManagementService;
	@Resource
	private IWxupriRecordService wxupriRecordService;

	@Resource
	private SmpWSUtil smpWSUtil;

	@RequestMapping(value = "constructPrizeTemp")
	public @ResponseBody
	Message constructPrizeTemp() {
		Message message = new Message();
		List<WxPrize> prizes = new ArrayList<WxPrize>();
		WxPrize wxPrize0 = new WxPrize();
		WxPrize wxPrize1 = new WxPrize();
		WxPrize wxPrize2 = new WxPrize();
		WxPrize wxPrize3 = new WxPrize();

		wxPrize0.setPrizeType("0");
		wxPrize0.setPrizeName("金靴奖");
		wxPrize0.setPrizeTotalCount(new BigDecimal(20));
		wxPrize0.setPictureAddress("/wxImages/prize0.png");
		wxPrize0.setPrizeContent("小米移动电源");
		wxPrize0.setCondition("活动结束后总排名前十");
		wxPrize0.setSpecial("1");
		wxPrize0.setValidFlag("0");

		wxPrize1.setPrizeType("1");
		wxPrize1.setPrizeName("金球奖");
		wxPrize1.setPrizeScore(60l);
		wxPrize1.setPrizeTotalCount(new BigDecimal(100));
		wxPrize1.setPictureAddress("/wxImages/prize1.png");
		wxPrize1.setPrizeContent("30元充值卡");
		wxPrize1.setCondition("60分");
		wxPrize1.setSpecial("0");
		wxPrize1.setValidFlag("0");

		wxPrize2.setPrizeType("2");
		wxPrize2.setPrizeName("普惠奖");
		wxPrize2.setPrizeScore(30l);
		wxPrize2.setPrizeTotalCount(new BigDecimal(460));
		wxPrize2.setPictureAddress("/wxImages/prize2.png");
		wxPrize2.setPrizeContent("经典足球");
		wxPrize2.setCondition("30分");
		wxPrize2.setSpecial("0");
		wxPrize2.setValidFlag("0");

		wxPrize3.setPrizeType("2");
		wxPrize3.setPrizeName("普惠奖");
		wxPrize3.setPrizeScore(10l);
		wxPrize3.setPrizeTotalCount(new BigDecimal(5600));
		wxPrize3.setPictureAddress("/wxImages/prize3.png");
		wxPrize3.setPrizeContent("世界杯吉祥物钥匙链");
		wxPrize3.setCondition("10分");
		wxPrize3.setSpecial("0");
		wxPrize3.setValidFlag("0");

		prizes.add(wxPrize0);
		prizes.add(wxPrize1);
		prizes.add(wxPrize2);
		prizes.add(wxPrize3);

		message = prizeManagementService.constructPrizeTemp(prizes);
		message.setMsg("初始化成功！");
		return message;
	}

	@RequestMapping(value = "returnPrizeManagementJsp")
	public ModelAndView returnPrizeManagementJsp(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		String areaDepartmentIdSs = "";
		User user = SpringSecurityUtils.getCurrentUser();
		Integer getAreaDepartmentId = user.getAreaDepartmentId();
		System.out.println(getAreaDepartmentId);
		Integer getCityDepartmentId = user.getCityDepartmentId();
		String isHQ = "0";
		if (getAreaDepartmentId != null) {
			DepartmentEntityVo departmentEntityVo = DepartmentUtil.departmentMap.get(getAreaDepartmentId.toString());
			areaDepartmentIdSs = areaDepartmentIdSs + departmentEntityVo.getDepartmentId() + ",";
		} else if (getCityDepartmentId != null) {
			DepartmentEntityVo departmentEntityVo = DepartmentUtil.departmentMap.get(getCityDepartmentId.toString());
			List<String> list = departmentEntityVo.getDepartmentIds();
			for (String s : list) {
				areaDepartmentIdSs = areaDepartmentIdSs + s + ",";
			}
		} else {
			Map<String, String> map = smpWSUtil.getAllAreaDepartmentNameMap();
			Set<String> set = map.keySet();
			for (String str : set) {
				areaDepartmentIdSs = areaDepartmentIdSs + "'" + str + "'" + ",";
			}
			isHQ = "1";
		}
		if (CommonUtil.isNotEmpty(areaDepartmentIdSs)) {
			areaDepartmentIdSs = areaDepartmentIdSs.substring(0, areaDepartmentIdSs.length() - 1);
		}
		session.setAttribute("areaDepartmentIdSs", areaDepartmentIdSs);
		session.setAttribute("isHQ", isHQ);
		modelAndView.setViewName("/jsp/rc/weixin/prizeManagement.jsp");
		return modelAndView;
	}

	@RequestMapping(value = "upriRecordDateGrid")
	public @ResponseBody
	Pagination upriRecordDateGrid(@RequestParam(required = false) String page, @RequestParam(required = false) String rows, String paramJsonMap, HttpSession session, String sort, String order) {
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("sort", sort);
		queryMap.put("order", order);
		if (!"".equals(paramJsonMap) && paramJsonMap != null) {
			Map<String, String> temp = JsonUtil.getObject(paramJsonMap);
			String recpriName = temp.get("recpriName");
			String branchofficeIds = temp.get("branchofficeIds");
			String beginTime = temp.get("beginTime");
			String endTime = temp.get("endTime");
			String receiverName = temp.get("receiverName");
			String isReceive = temp.get("isReceive");
			String prizeType = temp.get("prizeType");
			queryMap.put("recpriName", recpriName);
			queryMap.put("branchofficeIds", branchofficeIds);
			queryMap.put("beginTime", beginTime);
			queryMap.put("endTime", endTime);
			queryMap.put("receiverName", receiverName);
			queryMap.put("isReceive", isReceive);
			queryMap.put("prizeType", prizeType);
		}
		String areaDepartmentIdSs = (String) session.getAttribute("areaDepartmentIdSs");
		String isHQ = (String) session.getAttribute("isHQ");
		System.out.println(areaDepartmentIdSs);
		if (CommonUtil.isNotEmpty(areaDepartmentIdSs) && CommonUtil.isNotEmpty(isHQ)) {
			queryMap.put("areaDepartmentIdSs", areaDepartmentIdSs);
			queryMap.put("isHQ", isHQ);
			pagination = prizeManagementService.queryUpriRecordList(queryMap, pagination);
		}
		return pagination;
	}

	@RequestMapping(value = "receivePrize")
	public @ResponseBody
	Message receivePrize(WxupriRecord wxupriRecord) {
		Message message = new Message();
		User user = SpringSecurityUtils.getCurrentUser();
		wxupriRecord.setIsReceive("1");
		wxupriRecord.setReceiverId(user.getUserId());
		wxupriRecord.setReceiverName(user.getName_zh());
		message = wxupriRecordService.saveWxupriRecord(wxupriRecord);
		if (message.isSuccess()) {
			message.setMsg("领取奖品成功！");
		}
		return message;
	}
}
