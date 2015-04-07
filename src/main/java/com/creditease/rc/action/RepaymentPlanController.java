package com.creditease.rc.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.RepaymentPlan;
import com.creditease.rc.domain.RepaymentPlanItem;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IRepaymentPlanService;
import com.creditease.rc.util.JsonUtil;

/**
 * 
 * @author haoqiang
 * 
 */
@Controller
@RequestMapping("repaymentplan")
public class RepaymentPlanController {

	@Resource
	private IRepaymentPlanService repaymentPlanService;

	/**
	 * 
	 * @param repaymentPlan 还款计划对象
	 * @return 消息
	 */
	@RequestMapping(value = "insertRepaymentPlan")
	public @ResponseBody
	Message insertRepaymentPlan(RepaymentPlan repaymentPlan) {

		boolean isSuccess = repaymentPlanService.insertRepaymentPlan(repaymentPlan);

		Message message = new Message();
		if (isSuccess) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
		}

		return message;
	}

	/**
	 * 
	 * @param repaymentPlan 还款计划对象
	 * @return 返回自身ID
	 */
	@RequestMapping(value = "insertRepaymentPlanReturnId")
	public @ResponseBody
	Integer insertRepaymentPlanReturnId(RepaymentPlan repaymentPlan) {

		Integer repaymentPlanId = repaymentPlanService.insertRepaymentPlanReturnId(repaymentPlan);

		return repaymentPlanId;

	}

	/**
	 * 可能用不到了
	 * 
	 * @param javaname json字符串
	 * @return 消息
	 */
	@RequestMapping(value = "insertRepaymentPlanItem")
	public @ResponseBody
	Message insertRepaymentPlanItem(String javaname) {
		List<RepaymentPlanItem> passRepaymentPlanItem = new ArrayList<RepaymentPlanItem>();
		RepaymentPlanItem[] tempRepaymentPlanItem = (RepaymentPlanItem[]) JsonUtil.getArray(javaname,
				RepaymentPlanItem.class);
		for (int i = 0; i < tempRepaymentPlanItem.length; i++) {
			passRepaymentPlanItem.add(tempRepaymentPlanItem[i]);
		}
		for (int i = 0; i < passRepaymentPlanItem.size(); i++) {
			RepaymentPlanItem temp = passRepaymentPlanItem.get(i);
			String tempPrincipalRepayment = temp.getPrincipalRepayment();
			String tempIntertestRepayment = temp.getIntertestRepayment();
			String tempChargeServiceMethod = temp.getChargeServiceMethod();
			if ("按期平均".equals(tempPrincipalRepayment.trim())) {
				temp.setPrincipalRepayment("0");
			} else if ("自定义".equals(tempPrincipalRepayment.trim())) {
				temp.setPrincipalRepayment("1");
			}
			if ("按期平均".equals(tempIntertestRepayment.trim())) {
				temp.setIntertestRepayment("0");
			} else if ("自定义".equals(tempIntertestRepayment.trim())) {
				temp.setIntertestRepayment("1");
			}
			if ("按期平均".equals(tempChargeServiceMethod.trim())) {
				temp.setChargeServiceMethod("0");
			} else if ("自定义".equals(tempChargeServiceMethod.trim())) {
				temp.setChargeServiceMethod("1");
			}

		}
		boolean isSuccess = repaymentPlanService.insertRepaymentPlanItem(passRepaymentPlanItem);
		Message message = new Message();
		if (isSuccess) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
		}

		return message;
	}

	/**
	 * 通过还款计划ID查询还款计划明细
	 * 
	 * @param page 页数
	 * @param rows 行数
	 * @param repaymentPlanId 还款计划ID
	 * @return 分页列表
	 */
	@RequestMapping(value = "repaymentPlanItemDataGrid")
	public @ResponseBody
	Pagination repaymentPlanItemDataGrid(@RequestParam(required = false) String page,
			@RequestParam(required = false) String rows, Integer repaymentPlanId) {
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		pagination = repaymentPlanService.queryRepaymentPlanItemDataGrid(repaymentPlanId, pagination);
		return pagination;
	}

	/**
	 * 通过还款计划主键返回还款计划对象
	 * 
	 * @param repaymentPlanId 还款计划主键
	 * @return 还款计划对象
	 */
	@RequestMapping(value = "searchRepaymentPlanByRepaymentPlanId", method = RequestMethod.POST)
	public @ResponseBody
	RepaymentPlan searchRepaymentPlanByRepaymentPlanId(Integer repaymentPlanId) {

		RepaymentPlan repaymentPlan = repaymentPlanService.searchRepaymentPlanByRepaymentPlanId(repaymentPlanId);
		return repaymentPlan;

	}

	/**
	 * 更新还款计划
	 * 
	 * @param repaymentPlan 还款计划对象
	 * @return 消息
	 */
	@RequestMapping(value = "updateRepaymentPlan", method = RequestMethod.POST)
	public @ResponseBody
	Message updateRepaymentPlan(RepaymentPlan repaymentPlan) {

		boolean isSuccess = repaymentPlanService.updateRepaymentPlan(repaymentPlan);
		Message message = new Message();
		if (isSuccess) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
		}
		return message;
	}

	/**
	 * 更新还款计划明细
	 * 
	 * @param repaymentPlanItem 还款计划明细
	 * @return 消息
	 */
	@RequestMapping(value = "updateRepaymentPlanItem", method = RequestMethod.POST)
	public @ResponseBody
	Message updateRepaymentPlanItem(RepaymentPlanItem repaymentPlanItem) {

		boolean isSuccess = repaymentPlanService.updateRepaymentPlanItem(repaymentPlanItem);
		Message message = new Message();
		if (isSuccess) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
		}
		return message;
	}

	/**
	 * 检查是否有相同名称的还款方案
	 * 
	 * @param repaymentPlanName 还款计划名称
	 * @return 布尔型
	 */
	@RequestMapping(value = "checkRepaymentPlanName", method = RequestMethod.POST)
	public @ResponseBody
	boolean checkRepaymentPlanName(String repaymentPlanName) {
		RepaymentPlan passRepaymentPlan = new RepaymentPlan();
		passRepaymentPlan.setRepaymentPlanName(repaymentPlanName);
		Integer countOfRepaymentPlan = repaymentPlanService.searchRepaymentPlan(passRepaymentPlan);
		if (countOfRepaymentPlan == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @return 页面
	 */
	@RequestMapping(value = "north")
	public String north() {
		// return "/layout/north.jsp";
		return "/ruralcredit2/jsp/rc/business/testnorth.jsp";
	}

	/**
	 * 划款计划datagrid的查询操作都在这里哦~
	 * 
	 * @param page 页数
	 * @param rows 行数
	 * @param repaymentPlan 还款计划对象
	 * @param fuzzyQueryValue 模糊搜索传入值
	 * @param searchFlag 搜索标识，0代表模糊查询1代表高级查询NULL则是初始化
	 * @return 分页列表
	 */
	@RequestMapping(value = "repaymentPlanDataGrid")
	public @ResponseBody
	Pagination repaymentPlanDataGrid(@RequestParam(required = false) String page,
			@RequestParam(required = false) String rows, RepaymentPlan repaymentPlan, String fuzzyQueryValue,
			Integer searchFlag) {
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		pagination = repaymentPlanService.queryRepaymentPlanDataGrid(repaymentPlan, pagination, fuzzyQueryValue,
				searchFlag);
		return pagination;
	}

	/**
	 * 
	 * @param repaymentPlan 还款计划对象Json
	 * @param javaname 还款计划明细对象数组Json
	 * @return 消息
	 */
	@RequestMapping(value = "insertRepaymentPlanAndItem")
	public @ResponseBody
	Message insertRepaymentPlanAndItem(String repaymentPlan, String javaname) {

		RepaymentPlan passRepaymentPlan = new RepaymentPlan();
		passRepaymentPlan = (RepaymentPlan) JsonUtil.getObject(repaymentPlan, RepaymentPlan.class);

		List<RepaymentPlanItem> passRepaymentPlanItems = new ArrayList<RepaymentPlanItem>();
		RepaymentPlanItem[] tempRepaymentPlanItem = (RepaymentPlanItem[]) JsonUtil.getArray(javaname,
				RepaymentPlanItem.class);
		for (int i = 0; i < tempRepaymentPlanItem.length; i++) {
			passRepaymentPlanItems.add(tempRepaymentPlanItem[i]);
		}
		boolean isSuccess = repaymentPlanService.insertRepaymentPlanAndItem(passRepaymentPlan, passRepaymentPlanItems);
		Message message = new Message();
		if (isSuccess) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
		}
		return message;
	}

	/**
	 * 
	 * @param repaymentPlan 还款计划对象Json
	 * @param javaname 还款计划明细对象数组Json
	 * @return 消息
	 */
	@RequestMapping(value = "updateRepaymentPlanAndItem")
	public @ResponseBody
	Message updateRepaymentPlanAndItem(String repaymentPlan, String javaname) {

		RepaymentPlan passRepaymentPlan = new RepaymentPlan();
		passRepaymentPlan = (RepaymentPlan) JsonUtil.getObject(repaymentPlan, RepaymentPlan.class);

		List<RepaymentPlanItem> passRepaymentPlanItems = new ArrayList<RepaymentPlanItem>();
		RepaymentPlanItem[] tempRepaymentPlanItem = (RepaymentPlanItem[]) JsonUtil.getArray(javaname,
				RepaymentPlanItem.class);
		System.out.println(tempRepaymentPlanItem.length);
		for (int i = 0; i < tempRepaymentPlanItem.length; i++) {
			passRepaymentPlanItems.add(tempRepaymentPlanItem[i]);
		}
		boolean isSuccess = repaymentPlanService.updateRepaymentPlanAndItem(passRepaymentPlan, passRepaymentPlanItems);
		Message message = new Message();
		if (isSuccess) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
		}
		return message;
	}

	/**
	 * 这个是用来显示combobox的
	 * 
	 * @param id 还款方案ID
	 * @param q 还款方案名称
	 * @return 还款方案List
	 */
	@RequestMapping(value = "comboboxRepayment", method = RequestMethod.POST)
	public @ResponseBody
	List<RepaymentPlan> comboboxRepayment(Integer id, String q) {

		return repaymentPlanService.returnComboboxRepayment(id, q);
	}
}
