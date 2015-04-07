package com.creditease.rc.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IContractCompoundNucleusHistoryService;
import com.creditease.rc.util.JsonUtil;

/**
 * 
 * @author luohongjie
 *
 */
@Controller
@RequestMapping("contractHistory")
public class ContractCompoundNucleusHistoryController {

	@Resource
	private IContractCompoundNucleusHistoryService contractHistoryService;
	/**
	 * 查询合同复核历史列表
	 * @author hongjieluo
	 */
	@RequestMapping(value = "selectContractCompoundNucleusHistory")
	public @ResponseBody
	Pagination selectContractCompoundNucleusHistory(CreditApplication creditApplication, String paramJsonMap,
			@RequestParam(required = false) String page, @RequestParam(required = false) String rows,
			HttpServletRequest request, String sort, String order) {
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		Map<String, String> searchValue = new HashMap<String, String>();
		searchValue.put("sort", sort);
		searchValue.put("order", order);
		//searchValue = this.searchValues(creditApplication, fuzzyValue, null, session);
		if (!"".equals(paramJsonMap) && paramJsonMap != null) {
			Map<String, String> temp = JsonUtil.getObject(paramJsonMap);
			String businessNumber = temp.get("businessNumber");
			String name = temp.get("name");
			String loanPerson = temp.get("loanPerson");
			String repaymentPlanName=temp.get("repaymentPlanName");
			String compoundNucleusResults=temp.get("compoundNucleusResults");
			String companyId = temp.get("companyId");
			String lendingChannel = temp.get("lendingChannel");
			String loanConfirmDateBegin = temp.get("loanConfirmDateBegin");
			String loanConfirmDateEnd = temp.get("loanConfirmDateEnd");
			searchValue.put("businessNumber", businessNumber);
			searchValue.put("name", name);
			searchValue.put("loanConfirmDateBegin", loanConfirmDateBegin);
			searchValue.put("loanConfirmDateEnd", loanConfirmDateEnd);
			searchValue.put("loanPerson", loanPerson);
			searchValue.put("repaymentPlanName", repaymentPlanName);
			searchValue.put("compoundNucleusResults", compoundNucleusResults);
			searchValue.put("companyId", companyId);
			searchValue.put("lendingChannel", lendingChannel);
		}
		pagination = contractHistoryService.contractDateGrid(searchValue, pagination);
		return pagination;
	};
}
