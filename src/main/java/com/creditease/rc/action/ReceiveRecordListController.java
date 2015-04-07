package com.creditease.rc.action;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.domain.ReturnPlan;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IFinanceMoneyService;
import com.creditease.rc.service.IReceivablesRegistrationService;
import com.creditease.rc.service.IReceivedRecordListService;
import com.creditease.rc.vo.ReceivedRecordVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author haoqiang
 * 
 */
@Controller
@RequestMapping("receiveRecordList")
public class ReceiveRecordListController {
	@Resource
	private IReceivedRecordListService receivedRecordListService;
	@Resource
	private IReceivablesRegistrationService receivablesRegistrationService;
	@Resource
	private IFinanceMoneyService financeMoneyService;

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
	 * 撤销登记
	 * 
	 * @param receivedRecordId 收款登记主键
	 * @param status 状态
	 * @return 返回代码：
	 */
	@RequestMapping(value = "cancelReceivedRecord")
	public @ResponseBody
	int cancelReceivedRecord(Integer receivedRecordId, String status) {
		int j = 0;
		if ("4".equals(status)) {
			// 未预约
			List<Integer> idList = new ArrayList<Integer>();
			idList.add(receivedRecordId);
			boolean isSuccess = receivablesRegistrationService.updateAppointmentRevoked("1", idList);
			if (isSuccess == true) {
				j = 11;
			} else {
				j = 10;
			}
		} else if ("0".equals(status) || "5".equals(status)) {
			// 预约中selectUndoFinanceMoney

			boolean isSuccess = financeMoneyService.undoFinanceMoney(receivedRecordId);
			// boolean isSuccess2 = financeMoneyService.undoFinanceMoneyHQ(receivedRecordId);
			if (isSuccess == true) {
				List<Integer> idList = new ArrayList<Integer>();
				idList.add(receivedRecordId);
				boolean isSuccessO = receivablesRegistrationService.updateAppointmentRevoked("1", idList);
				if (isSuccessO == true) {
					j = 21;
				} else {
					j = 22;
				}
			} else {
				j = 20;
			}
		} else if ("2".equals(status)) {
			// 收款失败
			boolean isSuccess = financeMoneyService.undoFinanceMoney(receivedRecordId);
			if (isSuccess == true) {
				List<Integer> idList = new ArrayList<Integer>();
				idList.add(receivedRecordId);
				boolean isSuccessO = receivablesRegistrationService.updateAppointmentRevoked("1", idList);
				if (isSuccessO == true) {
					j = 31;
				} else {
					j = 32;
				}
			} else {
				j = 30;
			}
		}
		return j;
	}

	/**
	 * 分配Test
	 * 
	 * @return 是否成功
	 */
	@RequestMapping(value = "fen")
	public @ResponseBody
	boolean fen() {
		// System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		// System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		// System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		// System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		// System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		// System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		receivablesRegistrationService.saveDistribution();

		return true;
	}

	/**
	 * 定时任务查询需要进行收款登记的还计划
	 * 
	 * @return 是否预约成功
	 */
	@RequestMapping(value = "yuyue")
	public @ResponseBody
	boolean automaticallyDeducted() {
		ReturnPlan passReturnPlan = new ReturnPlan();
		passReturnPlan.setRepaymentDate(new Date());
		// System.out.println("当前系统时间为" + passReturnPlan.getRepaymentDate());
		passReturnPlan.setAutoSwitch("0");
		// System.out.println("即将进入automaticallyDeducted方法");
		boolean sss = receivablesRegistrationService.updateAutomaticallyDeducted(passReturnPlan);
		// System.out.println("定时任务查询需要进行收款登记的还计划");
		// System.out.println("结果为：" + sss);
		return sss;

	}

	/**
	 * 查询收款登记List
	 * 
	 * @param page 页
	 * @param rows 行
	 * @param receivedRecordVo 收款登记
	 * @param fuzzyValue 模糊查询值
	 * @param param 模糊查询还是高级查询
	 * @param session session
	 * @return 分页List
	 */
	@RequestMapping(value = "receivedRecordDataGrid")
	public @ResponseBody
	Pagination receivedRecordDataGrid(@RequestParam(required = false) String page,
			@RequestParam(required = false) String rows, ReceivedRecordVo receivedRecordVo, String fuzzyValue,
			String param, HttpSession session) {
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
		if (fuzzyValue != null) {
			fuzzyValue = fuzzyValue.trim();
		}
		receivedRecordVo.setAuthList(sqlsid);
		receivedRecordVo.setFuzzyValue(fuzzyValue);
		pagination = receivedRecordListService.queryReceivedRecordList(receivedRecordVo, pagination, param);
		return pagination;

	}

}
