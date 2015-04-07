package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
import com.creditease.rc.domain.BalanceAccountApply;
import com.creditease.rc.domain.BalanceAccountApplyVo;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.impl.IBalanceAccountApplyService;
import com.creditease.rc.util.DESPlus;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.util.PropertiesUtil;

@Controller
@RequestMapping("balanceAccountApplyController")
public class BalanceAccountApplyController {
	@Resource
	private IBalanceAccountApplyService balanceAccountService;
	@Resource
	private ICreditApplicationService creditApplicationService;
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
		binder.registerCustomEditor(Date.class, "saveMoneyTime", new CustomDateEditor(dateFormat2, true));
	}
	@RequestMapping(value = "accountApplyHistoryDateGrid")
	public @ResponseBody
	Pagination accountApplyHistoryDateGrid(@RequestParam(required = false) String page, @RequestParam(required = false) String rows, String paramJsonMap, HttpSession session,
			String sort, String order) {
		
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

		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("authList", sqlsid);
		queryMap.put("sort", sort);
		queryMap.put("order", order);
		if (!"".equals(paramJsonMap) && paramJsonMap != null) {
			Map<String, String> temp = JsonUtil.getObject(paramJsonMap);
			String businessNumber = temp.get("businessNumber");
			String name = temp.get("name");
			String loanOfficerName = temp.get("loanOfficerName");
			String companyId = temp.get("companyId");
			String auditAccountApplyState = temp.get("auditAccountApplyState");
			queryMap.put("businessNumber", businessNumber);
			queryMap.put("name", name);
			queryMap.put("loanOfficerName", loanOfficerName);
			queryMap.put("companyId", companyId);
			queryMap.put("auditAccountApplyState", auditAccountApplyState);
		}
		pagination = balanceAccountService.accountApplyHistoryDateGrid(queryMap, pagination);
		return pagination;
	}
	/**
	 * 要申请现金收款的数据
	 * 
	 */
	@RequestMapping(value = "accountApplyDateGrid")
	public @ResponseBody
	Pagination accountApplyDateGrid(BalanceAccountApplyVo balanceAccountApplyVo, @RequestParam(required = false) String page, @RequestParam(required = false) String rows, String paramJsonMap, HttpSession session,
			String sort, String order) {
		
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

		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("authList", sqlsid);
		queryMap.put("sort", sort);
		queryMap.put("order", order);
		if (!"".equals(paramJsonMap) && paramJsonMap != null) {
			Map<String, String> temp = JsonUtil.getObject(paramJsonMap);
			String businessNumber = temp.get("businessNumber");
			queryMap.put("businessNumber", businessNumber);
			
		}
		pagination = balanceAccountService.accountApplyDateGrid(queryMap, pagination);
		return pagination;
	}
	/**
	 * 保存对账申请信息并返回页面
	 * 
	 * @param creditapplicationId
	 *            业务申请单Id
	 * @return 页面
	 */
	@RequestMapping(value = "returnAccountApplyJSP")
	public ModelAndView returnAccountApplyJSP(BalanceAccountApplyVo balanceAccountApply,Long creditapplicationId,Long balanceAccountApplyId, String add) {
		DESPlus desPlus;
		Map map = new HashMap();
		 Calendar cal=Calendar.getInstance();//使用日历类
		 //获取当前日期的日，以便区分当前操作日期有没有上传资料的判断
		 long balanceDay;
		 if(null!=balanceAccountApply.getBalanceDay()){
			 balanceDay=balanceAccountApply.getBalanceDay();
		 }else{
			 balanceDay=cal.get(Calendar.DAY_OF_MONTH);
		 }
		try {
			desPlus = new DESPlus();
			balanceAccountApply.setBalanceDay((long) balanceDay);
			String balanceAccountApplyEncrypt=balanceAccountApply.getCreditapplicationId()+String.valueOf(balanceAccountApply.getBalanceDay());
			map.put("clientid", desPlus.encrypt(balanceAccountApplyEncrypt+ "BalanceAccountApply"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String clientid = (String) map.get("clientid");

		long nowTime = System.currentTimeMillis();
		String sMillis[] = new String[2];
		try {
			Properties properties = PropertiesUtil.loadProperties("spring/cm/cm.properties");
			DESPlus desPlus2 = new DESPlus();
			sMillis[0] = desPlus2.encrypt(nowTime + "");
			String cmIp = properties.getProperty("cm.hostip");
			String DESIp = desPlus2.encrypt(cmIp);
			sMillis[1] = DESIp;
		} catch (Exception e) {
			e.printStackTrace();
		}

		String signTime = sMillis[0];
		String signIp = sMillis[1];
		ModelAndView modelAndView = new ModelAndView();
		// 判断specialApplyId是否为空
		if (balanceAccountApply.getBalanceAccountApplyId() == null) {
		} else {
			balanceAccountApply=balanceAccountService.queryBalanceAccountApplyByPrimaryKey(balanceAccountApplyId);
		}
		System.out.println();
		modelAndView.setViewName("/jsp/rc/auditAccount/accountApply.jsp");
		modelAndView.addObject("clientid", clientid);
		modelAndView.addObject("signTime", signTime);
		modelAndView.addObject("signIp", signIp);
		modelAndView.addObject("add", add);
		modelAndView.addObject("balanceDate", balanceDay);
		modelAndView.addObject("creditapplicationId", creditapplicationId);
		modelAndView.addObject("balanceAccountApply", balanceAccountApply);
		return modelAndView;
	}
	/**
	 * 保存对账申请信息
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "saveBalanceAccountApply")
	public @ResponseBody
	Message saveBalanceAccountApply(BalanceAccountApply balanceAccountApply) {

		Message message = new Message();
		boolean flag=balanceAccountService.insertAccountApply(balanceAccountApply);
		if(flag){
			message.setSuccess(true);
		}else{
			message.setSuccess(false);
		}
		return message;
	}
	/**
	 * 审批对账申请信息
	 * 
	 * @author luohongjie
	 * 
	 */
	@RequestMapping(value = "auditAccountApply")
	public @ResponseBody
	Message auditAccountApply(BalanceAccountApplyVo balanceAccountApplyVo) {
		Message message = new Message();

		boolean success = balanceAccountService.dynamicUpdate(balanceAccountApplyVo);
		message.setSuccess(success);
		if (success) {
			message.setMsg("保存成功！");
		} else {
			message.setMsg("保存失败！");
		}
		return message;
	}
	/**
	 * 现金收款对账申请时是否上传文件
	 * 
	 * @author luohongjie
	 * 
	 */
	@RequestMapping("getImgAmountByBalanceAccountApply")
	@SuppressWarnings("all")
	public @ResponseBody Integer getImgAmountByBalanceAccountApply(String creditAppId,String balanceDate) {
		Integer i=null;
		try {
			i=creditApplicationService.getImgAmount(creditAppId+balanceDate+"BalanceAccountApply");
			return  i;
		} catch (Exception e) {
			e.printStackTrace();
			return i;
		}
	}
}
