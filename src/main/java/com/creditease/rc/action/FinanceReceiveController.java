package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.creditease.core.exception.BusinessException;
import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.AmountConfirm;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.ReceivedRecord;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IAccountInfoService;
import com.creditease.rc.service.IAmountConfirmService;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.IFinanceMoneyService;
import com.creditease.rc.service.IReceivedRecordListService;
import com.creditease.rc.util.BankCardNOLock;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.vo.FinanceReceiveVo;

/**
 * 财务付款回收
 * 
 * @author haoqiang
 * 
 */
/**
 * 付款回收
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("financeReceiveController")
public class FinanceReceiveController {

	@Resource
	private IFinanceMoneyService financeMoneyService;

	@Resource
	private IReceivedRecordListService receivedRecordListService;
	@Resource
	private IAccountInfoService accountInfoService;
	@Resource
	private IAmountConfirmService amountConfirmService;
	@Resource
	private ICreditApplicationService creditApplicationService;

	/**
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * 财务付款回收
	 * 
	 * @param parameter
	 * @return Message
	 */
	@RequestMapping(value = "toRecevieFinanceBySettle")
	public @ResponseBody
	Message toRecevieFinanceBySettle(FinanceReceiveVo parameter) {
		Message message = new Message();
		String receiveRecordIds = parameter.getReceiveRecordIds();
		String[] receiveRecordId = receiveRecordIds.split(",");
		List<Integer> receivedRecordIdList = new ArrayList<Integer>();
		if (receiveRecordId.length != 1) {
			message.setMsg("不合法的数据");
			message.setSuccess(false);
			return message;
		} else {
			for (int i = 0; i < receiveRecordId.length; i++) {
				Integer rId = Integer.valueOf(receiveRecordId[i]);
				receivedRecordIdList.add(rId);
			}
			// 其实在付款回收里 他是 AmountConfirm 的主键
			Integer amountConfirmId = receivedRecordIdList.get(0);
			AmountConfirm amountConfirm = amountConfirmService.queryAmountConfirmByPrimaryKey(amountConfirmId
					.longValue());
			Integer creditapplicationId = amountConfirm.getCreditapplicationId();
			CreditApplication creditApplication = creditApplicationService.selectById(creditapplicationId);
			// ReceivedRecord receivedRecord = receivedRecordListService.queryReceivedRecordByPrimaryKey(receivedRecordId);
			Integer accountInfoId = creditApplication.getAccountInfoId();
			AccountInfo accountInfoSelect = accountInfoService.selectByAccountID(accountInfoId);
			String account = accountInfoSelect.getAccount();
			if (CommonUtil.isNotEmpty(account)) {
				// 判断是否存在锁
				Boolean b = BankCardNOLock.lockBankCardNO(account);
				if (b) {
					// 获取银行卡号。加锁
					try {
						message = financeMoneyService.receiveAgin(parameter);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						throw new BusinessException(e.getMessage());
					} finally {
						// 解锁
						BankCardNOLock.unLockBankCardNO(account);
					}
				} else {
					throw new BusinessException("卡号：" + account + "已锁住，请等待或解锁");
				}

			}
		}
		return message;
	}

	/**
	 * 查询财务付款回收列表
	 * 
	 * @param creditApplication 业务单对象
	 * @param page
	 * @param rows
	 * @param session
	 * @return Pagination
	 */
	@RequestMapping(value = "selectBackSectionList")
	public @ResponseBody
	Pagination selectBackSectionList(CreditApplication creditApplication, @RequestParam(required = false) String page,
			@RequestParam(required = false) String rows, HttpSession session) {
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
		creditApplication.setAuthList(sqlsid);
		Date beginDate = creditApplication.getBeginLoanDate();
		Date endDate = creditApplication.getEndLoanDate();
		if (null != beginDate) {
			String strBedinDate = DateUtil.dateConvertString(beginDate);
			creditApplication.setBeginLoanDate(DateUtil.stringConvertDate(strBedinDate));
		}
		if (null != endDate) {
			String strEndDate = DateUtil.dateConvertString(endDate);
			creditApplication.setEndLoanDate(DateUtil.stringConvertDate(strEndDate));
		}
		Pagination pagination = parameter2Pagination(page, rows);
		Pagination pagination1 = financeMoneyService.selectBackSectionList(pagination, creditApplication);
		return pagination1;
	}

	/**
	 * 财务收款预约(当时想用这个方法调用贷后接口)
	 * 
	 * @param parameter
	 * @return Message
	 */
	@RequestMapping(value = "toRecevieFinanceByLoan")
	public @ResponseBody
	Message toRecevieFinanceByLoan(FinanceReceiveVo parameter) {
		Message messag = new Message();
		return messag;
	}

	/**
	 * 组装pagination
	 * 
	 * @param page 当前页
	 * @param rows 行
	 * @return Pagination
	 */
	private Pagination parameter2Pagination(String page, String rows) {
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		return pagination;
	}

}
