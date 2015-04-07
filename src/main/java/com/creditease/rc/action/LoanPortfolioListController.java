package com.creditease.rc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.util.JSONUtils;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IStatisticalinfoService;
import com.creditease.rc.util.JsonUtil;

/**
 * 
 * @author haoqiang
 * 
 */
@Controller
@RequestMapping("loanPortfolioListController")
public class LoanPortfolioListController {

	@Resource
	IStatisticalinfoService statisticalinfoService;

	/**
	 * 
	 * @param page
	 * @param rows
	 * @param paramJsonMap
	 * @param session
	 * @return Pagination
	 */
	@RequestMapping(value = "LoanPortfolioDateGrid")
	public @ResponseBody
	Pagination loanPortfolioDateGrid(@RequestParam(required = false) String page,
			@RequestParam(required = false) String rows, String paramJsonMap, HttpSession session) {
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
		if (!"".equals(paramJsonMap) && paramJsonMap != null) {
			Map<String, String> temp = JsonUtil.getObject(paramJsonMap);
			String fuzzyValue = temp.get("fuzzyValue");
			String businessNumber = temp.get("businessNumber");
			String borrowerName = temp.get("borrowerName");
			String companyId=temp.get("companyId");
			String startDate = temp.get("startDate");
			String endDate = temp.get("endDate");
			queryMap.put("fuzzyValue", fuzzyValue);
			queryMap.put("businessNumber", businessNumber);
			queryMap.put("borrowerName", borrowerName);
			queryMap.put("companyId", companyId);
			queryMap.put("startDate", startDate);
			queryMap.put("endDate", endDate);
		}
		queryMap.put("authList", sqlsid);
		pagination = statisticalinfoService.queryLoanPortfolioList(queryMap, pagination);
		return pagination;
	}

	@RequestMapping(value = "queryListForLoanPort")
	public @ResponseBody
	List<HashMap<String, Object>> queryListForLoanPort(HttpSession session,String paramJsonMap) {
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
		
		if (!"".equals(paramJsonMap) && paramJsonMap != null) {
			Map<String, String> temp = JsonUtil.getObject(paramJsonMap);
			String companyId=temp.get("companyId");
			queryMap.put("companyId", companyId);
		}
		queryMap.put("authList", sqlsid);
		return statisticalinfoService.queryListForLoanPort(queryMap);
	}
}
