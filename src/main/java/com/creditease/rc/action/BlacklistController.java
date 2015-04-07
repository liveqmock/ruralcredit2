package com.creditease.rc.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.security.User;
import com.creditease.rc.domain.Blacklist;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.exception.AppBusinessException;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IBlacklistService;
import com.creditease.rc.util.JsonUtil;

/**
 * 
 * @author haoqiang
 * 
 */
@Controller
@RequestMapping(value = "blacklistController")
public class BlacklistController {

	@Resource
	private IBlacklistService blacklistService;

	/**
	 * 
	 * @param page
	 * @param rows
	 * @param paramJsonMap
	 * @param session
	 * @return Pagination
	 */
	@RequestMapping(value = "blacklistDateGrid")
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
		Map<String, String> queryMap = new HashMap<String, String>();
		if (!"".equals(paramJsonMap) && paramJsonMap != null) {
			Map<String, String> temp = JsonUtil.getObject(paramJsonMap);
			String fuzzyValue = temp.get("fuzzyValue");
			String blackCredentialsNumber = temp.get("blackCredentialsNumber");
			String blackName = temp.get("blackName");
			String historyFlag = temp.get("historyFlag");
			queryMap.put("fuzzyValue", fuzzyValue);
			queryMap.put("blackCredentialsNumber", blackCredentialsNumber);
			queryMap.put("blackName", blackName);
			queryMap.put("historyFlag", historyFlag);
		}
		pagination = blacklistService.queryBlicklistList(queryMap, pagination);
		return pagination;
	}

	@RequestMapping(value = "blacklisted")
	public @ResponseBody
	Message blacklisted(Blacklist blacklist) {
		User user = SpringSecurityUtils.getCurrentUser();
		blacklist.setBlacklistedOperatorId(Long.valueOf(user.getUserId()));
		blacklist.setBlacklistedOperatorName(user.getName_zh());
		blacklist.setBlacklistedTime(new Timestamp(new Date().getTime()));
		Message message = new Message();
		boolean isSuccess = false;
		isSuccess = blacklistService.insertBlacklist(blacklist);
		message.setSuccess(isSuccess);
		return message;
	}

	@RequestMapping(value = "remove")
	public @ResponseBody
	Message remove(Blacklist blacklist) {
		User user = SpringSecurityUtils.getCurrentUser();
		blacklist.setRemoveOperatorId(Long.valueOf(user.getUserId()));
		blacklist.setRemoveOperatorName(user.getName_zh());
		blacklist.setRemoveTime(new Timestamp(new Date().getTime()));
		blacklist.setHistoryFlag("T");
		Message message = new Message();
		boolean isSuccess = false;
		isSuccess = blacklistService.updateBlacklist(blacklist);
		message.setSuccess(isSuccess);
		return message;
	}

	@RequestMapping(value = "checkIdNumber")
	public @ResponseBody
	Message checkIdNumber(String credentialsNumber) {
		Message message = new Message();
		boolean isSuccess = false;
		String mess = "身份证不可用";
		int count = blacklistService.checkIdNumber(credentialsNumber);
		if (count == 0) {
			isSuccess = true;
			mess = "身份证可用";
		}
		if (count > 1) {
			mess = "身份证号码已经重复！数据库数据有错！请与管理员联系！";
		}
		message.setSuccess(isSuccess);
		message.setMsg(mess);

		return message;
	}
}
