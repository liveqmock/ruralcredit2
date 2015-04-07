package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

import com.creditease.core.exception.BusinessException;
import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.common.DepartmentEntity;
import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.FinanceMoney;
import com.creditease.rc.domain.GroupLoanRegist;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.ReceivedRecord;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IAccountInfoService;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.IFinanceMoneyService;
import com.creditease.rc.service.IFinanceReceiveService;
import com.creditease.rc.service.IReceivedRecordListService;
import com.creditease.rc.util.BankCardNOLock;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.CurrencyUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.DicUtil;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.util.SmpWSUtil;
import com.creditease.rc.vo.FinanceReceiveVo;
import com.creditease.rc.vo.ReceivedRecordVo;

/**
 * 财务付款
 * 
 * @author xubingzhao
 * 
 */
@Controller
@RequestMapping("financeMoneyController")
public class FinanceMoneyController {
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

	@Resource
	private IFinanceMoneyService financeMoneyService;
	@Resource
	private IReceivedRecordListService receivedRecordListService;
	@Resource
	private SmpWSUtil smpWSUtil;

	@Resource
	private IFinanceReceiveService financeReceiveService;

	@Resource
	private ICreditApplicationService creditApplicationService;

	@Resource
	private IAccountInfoService accountInfoService;

	/**
	 * 账务付款查看页面
	 * 
	 * @param parameter CreditApplication对象
	 * @return ModelAndView
	 */
	@RequestMapping(value = "selectPaymentFinanceMoney")
	public ModelAndView selectFinanceMoney(CreditApplication parameter) {
		ModelAndView modelAndView = selectData(parameter,"");
		modelAndView.setViewName("/jsp/rc/payment/viewFinance.jsp");
		return modelAndView;
	}

	/**
	 * 转到财务付款预约页面
	 * 
	 * @param parameter CreditApplication对象
	 * @return StrModelAndViewing
	 */
	@RequestMapping(value = "paymentFinance")
	public ModelAndView selectPaymentFinance(CreditApplication parameter) {
		ModelAndView modelAndView = selectData(parameter,"look");
		modelAndView.setViewName("/jsp/rc/payment/paymentFinance.jsp");
		return modelAndView;
	}

	/**
	 * 转到财务收款预约界面
	 * 
	 * @param datarow 行内容拼成的串
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "selectRecevieFinance")
	public ModelAndView selectRecevieFinance(String datarow, HttpServletRequest request) {
		ModelAndView model = new ModelAndView("/jsp/rc/receivables/recevieFinance.jsp");
		boolean flag = true;
		StringBuffer alert = new StringBuffer();
		Integer[] array = (Integer[]) JsonUtil.getArray(datarow, Integer.class);
		List<Integer> receivedRecordIdList = Arrays.asList(array);
		if (CommonUtil.isEmpty(receivedRecordIdList)) {
			model.addObject("alert", "没有合法的数据");
			return model;
		}
		List<CreditApplication> creditApplicationList = receivedRecordListService
				.selectReceivedRecord(receivedRecordIdList);
		if (CommonUtil.isNotEmpty(creditApplicationList)) {
			String companyName = creditApplicationList.get(0).getCompanyName();
			for (CreditApplication c : creditApplicationList) {
				if (!companyName.equals(c.getCompanyName())) {
					alert.append("所选有非同一分公司的记录，请检查!");
					flag = false;
					break;
				}
			}
		}
		int accountInfoIdCount = financeMoneyService.selectIsDuaplicatAccount(receivedRecordIdList);
		if (accountInfoIdCount != 1) {
			model.addObject("alert", "账号不统一，无法进行批量预约");
			return model;
		}
		List<ReceivedRecord> resultList = receivedRecordListService.selectReceivedRecordStatus(receivedRecordIdList);
		for (ReceivedRecord r : resultList) {
			if ("S".equals(r.getReceivedType())) {
				if (!("5".equals(r.getReceivedStatus()) || "4".equals(r.getReceivedStatus()) || "2".equals(r
						.getReceivedStatus()))) {
					alert.append("所选记录中有不能进行预约的记录，请检查！");
					flag = false;
					break;
				}
			} else if ("U".equals(r.getReceivedType())) {
				if (!("4".equals(r.getReceivedStatus()) || "3".equals(r.getReceivedStatus()))) {
					alert.append("所选记录中有不能进行预约的记录，请检查！");
					flag = false;
					break;
				}
			}
		}
		if (flag) {
			Integer receivedRecordId = receivedRecordIdList.get(0);
			List<ReceivedRecordVo> receivedRecordList = financeMoneyService
					.selectReceiveRecordList(receivedRecordIdList);
			Double sum = 0.0;
			StringBuffer sbf = new StringBuffer();
			for (ReceivedRecord r : receivedRecordList) {
				sum = CurrencyUtil.add(sum, r.getReceivedAmount());
				sbf.append(r.getReceivedRecordId()).append(",");
			}
			AccountInfo accountInfo = financeMoneyService.selectAccountInfo(receivedRecordId);
			model.addObject("accountInfo", accountInfo.getBranchName() + "|" + accountInfo.getAccount() + "|"
					+ accountInfo.getAccountName());
			model.addObject("receivedRecordAmount", sum);
			model.addObject("receiveRecordIds", sbf.substring(0, sbf.length() - 1));
			model.addObject("receivedRecordList", receivedRecordList);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date severDate = new Date();
			String strServerDate = sdf.format(severDate);
			model.addObject("severDate", strServerDate);
		} else {
			model.addObject("alert", alert.toString());
		}
		return model;
	}

	/**
	 * 转到财务收款预约界面 （贷后用！！！！！）
	 * 
	 * @param datarow 行内容拼成的串
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "selectRecevieFinanceATC")
	public ModelAndView selectRecevieFinanceATC(String datarow, HttpServletRequest request) {
		ModelAndView model = new ModelAndView("/jsp/rc/receivables/recevieFinanceATC.jsp");
		boolean flag = true;
		StringBuffer alert = new StringBuffer();
		Integer[] array = (Integer[]) JsonUtil.getArray(datarow, Integer.class);
		List<Integer> receivedRecordIdList = Arrays.asList(array);
		if (CommonUtil.isEmpty(receivedRecordIdList)) {
			model.addObject("alert", "没有合法的数据");
			return model;
		}

		/**
		 * 这里是新增的条件：
		 * 在接贷后以后，如果是约贷后而约失败的，是可以再重复预约的，如果约贷后是真正的收款失败的，是不能再重复预约的，
		 * 这里的判断就是为了这个而存在的。
		 * 那么通过登记去找这一条预约记录，再通过这一条预约记录，去找到reserveID，这个ID在贷后存着呢~~~
		 * 如果贷后有这笔记录，那么证明这个对应预约在贷后是有记录的。
		 */

// List<String> bizIdList = financeReceiveService.queryBizIdListByReceivedRecordIdList(receivedRecordIdList);
//
// if (CommonUtil.isNotEmpty(bizIdList)) {
// model.addObject("alert", "预约记录中有结算收款失败的记录，这样的记录要重新登记并预约");
// return model;
// }
// List<CreditApplication> creditApplicationList = receivedRecordListService
// .selectReceivedRecord(receivedRecordIdList);
// if (CommonUtil.isNotEmpty(creditApplicationList)) {
// String companyName = creditApplicationList.get(0).getCompanyName();
// for (CreditApplication c : creditApplicationList) {
// if (!companyName.equals(c.getCompanyName())) {
// alert.append("所选有非同一分公司的记录，请检查!");
// flag = false;
// break;
// }
// }
// }
// int accountInfoIdCount = financeMoneyService.selectIsDuaplicatAccount(receivedRecordIdList);
// if (accountInfoIdCount != 1) {
// model.addObject("alert", "账号不统一，无法进行批量预约");
// return model;
// }
		List<ReceivedRecord> resultList = receivedRecordListService.selectReceivedRecordStatus(receivedRecordIdList);
		for (ReceivedRecord r : resultList) {
			if ("S".equals(r.getReceivedType())) {
				if (!("5".equals(r.getReceivedStatus()) || "4".equals(r.getReceivedStatus()) || "2".equals(r
						.getReceivedStatus()))) {
					alert.append("所选记录中有不能进行预约的记录，请检查！");
					flag = false;
					break;
				}
			} else if ("U".equals(r.getReceivedType())) {
				if (!("4".equals(r.getReceivedStatus()) || "3".equals(r.getReceivedStatus()))) {
					alert.append("所选记录中有不能进行预约的记录，请检查！");
					flag = false;
					break;
				}
			}
		}

		if (flag) {
			Integer receivedRecordId = receivedRecordIdList.get(0);
			List<ReceivedRecordVo> receivedRecordList = financeMoneyService
					.selectReceiveRecordList(receivedRecordIdList);
			Double sum = 0.0;
			StringBuffer sbf = new StringBuffer();
			for (ReceivedRecord r : receivedRecordList) {
				sum = CurrencyUtil.add(sum, r.getReceivedAmount());
				sbf.append(r.getReceivedRecordId()).append(",");
			}
			AccountInfo accountInfo = financeMoneyService.selectAccountInfo(receivedRecordId);
			model.addObject("accountInfo", accountInfo.getBranchName() + "|" + accountInfo.getAccount() + "|"
					+ accountInfo.getAccountName());
			model.addObject("receivedRecordAmount", sum);
			model.addObject("receiveRecordIds", sbf.substring(0, sbf.length() - 1));
			model.addObject("receivedRecordList", receivedRecordList);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date severDate = new Date();
			String strServerDate = sdf.format(severDate);
			model.addObject("severDate", strServerDate);
		} else {
			model.addObject("alert", alert.toString());
		}
		return model;
	}

	/**
	 * 财务收款和财务收款失败再预约（统一的一个方法）
	 * 
	 * @param parameter FinanceReceiveVo
	 * @return boolean
	 */
	@RequestMapping(value = "toRecevieFinance")
	public @ResponseBody
	Message toRecevieFinance(FinanceReceiveVo parameter) {
		Message message = financeMoneyService.receiveAgin(parameter);
		return message;
	}

	/**
	 * 财务付款失败再预约（就是付款预约那个列表调用的这个方法）
	 * 
	 * @param parameter GroupLoanRegist
	 * @return boolean
	 */
	@RequestMapping(value = "toPaymentFinance")
	public @ResponseBody
	Message toPaymentFinance(GroupLoanRegist parameter) {
		Integer creditapplicationId = parameter.getCreditapplicationId();
		CreditApplication creditApplication = creditApplicationService.selectById(creditapplicationId);
		Integer accountInfoId = creditApplication.getAccountInfoId();
		AccountInfo accountInfoSelect = accountInfoService.selectByAccountID(accountInfoId);
		String account = accountInfoSelect.getAccount();
		Message message = new Message();
		if (CommonUtil.isNotEmpty(account)) {
			// 判断是否存在锁
			Boolean b = BankCardNOLock.lockBankCardNO(account);
			if (b) {
				// 获取银行卡号。加锁
				try {
					message = financeMoneyService.paymentAgin(parameter);
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
		return message;
	}

	/**
	 * 约贷后
	 * After the credit （贷后系统）缩写为：ATC
	 * 
	 * @author 郝强
	 * @param parameter FinanceReceiveVo
	 * @return boolean
	 */
	@RequestMapping(value = "toRecevieFinanceATC")
	public @ResponseBody
	Message toRecevieFinanceATC(FinanceReceiveVo parameter) {
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
			Integer receivedRecordId = receivedRecordIdList.get(0);
			ReceivedRecord receivedRecord = receivedRecordListService.queryReceivedRecordByPrimaryKey(receivedRecordId);
			Integer accountInfoId = receivedRecord.getAccountInfoId();
			AccountInfo accountInfoSelect = accountInfoService.selectByAccountID(accountInfoId);
			String account = accountInfoSelect.getAccount();
			if (CommonUtil.isNotEmpty(account)) {
				// 判断是否存在锁
				Boolean b = BankCardNOLock.lockBankCardNO(account);
				if (b) {
					// 获取银行卡号。加锁
					try {
						message = financeMoneyService.receiveAginATC(parameter);
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
	 * 查询账务收款列表
	 * 
	 * @param creditApplication 页面传入参数
	 * @param page 当前页
	 * @param rows 行数
	 * @param session
	 * @return pagination1
	 */
	@RequestMapping(value = "selectRecevieList")
	public @ResponseBody
	Pagination selectRecevieList(CreditApplication creditApplication, @RequestParam(required = false) String page,
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
		Pagination pagination1 = financeMoneyService.selectRecevieList(pagination, creditApplication);
		return pagination1;
	}

	/**
	 * 转到财务收款查看页面 （弹出Dialog）
	 * 
	 * @param parameter FinanceMoney
	 * @return model
	 */
	@RequestMapping(value = "selectReceiveFinanceMoney")
	public ModelAndView selectReceiveFinanceMoney(FinanceMoney parameter) {
		FinanceMoney financeMoney = financeMoneyService.selectFinanceMoney(parameter);
		ModelAndView model = new ModelAndView("/jsp/rc/receivables/viewReceive.jsp");
		if (null != financeMoney) {
			if (null != financeMoney.getReserveTime()) {
				String reserveTime = DateUtil.dateConvertString(financeMoney.getReserveTime(), "yyyy-MM-dd HH:mm:ss");
				model.addObject("reserveTime", reserveTime);
			}
			if (null != financeMoney.getOperateDate()) {
				String operateDate = DateUtil.dateConvertString(financeMoney.getOperateDate(), "yyyy-MM-dd HH:mm:ss");
				model.addObject("operateDate", operateDate);
			}
			if (null != financeMoney.getTradeTime()) {
				String tradeTime = DateUtil.dateConvertString(financeMoney.getTradeTime(), "yyyy-MM-dd HH:mm:ss");
				model.addObject("tradeTime", tradeTime);
			}
			model.addObject("financeMoney", financeMoney);
		}

		return model;
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

	/**
	 * 组装数据
	 * 
	 * @param parameter CreditApplication
	 * @return ModelAndView
	 */
	private ModelAndView selectData(CreditApplication parameter,String look) {
		ModelAndView modelAndView = new ModelAndView();
		FinanceMoney financeMoney = financeMoneyService.selectPaymentFinanceMoney(parameter);
		if(look != null && "look".equals(look)){
			//重新设置银行账号
			CreditApplication creditApplication = creditApplicationService.selectById(financeMoney.getCreditapplicationId());
			Integer accountInfoId = creditApplication.getAccountInfoId();
			AccountInfo accountInfoSelect = accountInfoService.selectByAccountID(accountInfoId);
			financeMoney.setAccountNo(accountInfoSelect.getAccount());	//账号
			financeMoney.setBankId(accountInfoSelect.getBankNum());
			financeMoney.setAccountName(accountInfoSelect.getAccountName());
		}
		
		
		String bankId = financeMoney.getBankId();
		bankId = DicUtil.convertCodeKeyToCodeValue("bankNum", bankId);
		financeMoney.setBankId(bankId);
		financeMoney.setCreditapplicationId(parameter.getCreditapplicationId());
		modelAndView.addObject("financeMoney", financeMoney);
		if (null != financeMoney.getReserveTime()) {
			String reserveTime = DateUtil.dateConvertString(financeMoney.getReserveTime(), "yyyy-MM-dd HH:mm:ss");
			modelAndView.addObject("reserveTime", reserveTime);
		}
		if (null != financeMoney.getTradeTime()) {
			String tradeTime = DateUtil.dateConvertString(financeMoney.getTradeTime(), "yyyy-MM-dd HH:mm:ss");
			modelAndView.addObject("tradeTime", tradeTime);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date severDate = new Date();
		String strServerDate = sdf.format(severDate);
		modelAndView.addObject("severDate", strServerDate);
		return modelAndView;
	}

	/**
	 * 财务付款撤销
	 * 
	 * @param parameter 付款登记表对象
	 * @return boolean
	 */
	@RequestMapping(value = "paymentUndo")
	public @ResponseBody
	Message paymentUndo(GroupLoanRegist parameter) {
		Message message = financeMoneyService.paymentUndo(parameter.getCreditapplicationId());
		return message;
	}

	/**
	 * 财务收款撤销
	 * * @param receivedRecord 收款登记表ID
	 * 
	 * @return boolean
	 */
	@RequestMapping(value = "receiveUndo")
	public @ResponseBody
	Message receiveUndo(ReceivedRecord receivedRecord) {
		Message message = financeMoneyService.receiveUndo(receivedRecord.getReceivedRecordId());
		return message;
	}

	/**
	 * 获得所有部门
	 * 
	 * @return List<DepartmentEntity>
	 */
	@RequestMapping(value = "getDepartmentList")
	public @ResponseBody
	List<DepartmentEntity> getDepartmentList() {
		List<DepartmentEntity> list = smpWSUtil.getDepartmentList();
		return list;
	}

	/**
	 * 校验当前单子是否可以预约
	 * 
	 * @param parameter 信贷申请单对象
	 * @return Message
	 */
	@RequestMapping(value = "isPayment")
	public @ResponseBody
	Message isPayment(CreditApplication parameter) {
		Message message = new Message(true);
		FinanceMoney financeMoney = financeMoneyService.selectPaymentFinanceMoney(parameter);
		if (!"3".equals(financeMoney.getFinanceStatus())) {
			message.setSuccess(false);
		}
		return message;
	}

	/**
	 * 手动查询划扣结果
	 * 
	 * @return 是否查询并更改状态成功
	 */
	@RequestMapping(value = "manualQueries")
	public @ResponseBody
	Message manualQueries() {
		Message message = financeReceiveService.updateQueryReserveResult(null);
		return message;
	}

	/**
	 * 查询财务付款对象列表
	 * zhangman
	 * 
	 * @param financeMoney 财务对象
	 * @return list 财务付款对象列表
	 */
	@RequestMapping(value = "selectPaymentFinanceMoneyList")
	public @ResponseBody
	List<FinanceMoney> selectPaymentFinanceMoneyList(FinanceMoney financeMoney) {
		return (List<FinanceMoney>) financeMoneyService.selectPaymentFinanceMoneyList(financeMoney);
	}

	/**
	 * 技术人员维护修改状态
	 * zhangman
	 * 
	 * @param financeMoney 修改对象
	 * @return boolean
	 */
	@RequestMapping(value = "updateFinanceMoneyByBizId")
	public @ResponseBody
	Message updateFinanceMoneyByBizId(FinanceMoney financeMoney) {
		Message message = new Message();
		boolean falg = financeMoneyService.updateFinanceMoneyByBizId(financeMoney);
		message.setSuccess(falg);
		if (falg == true) {
			message.setMsg("操作成功");
		} else {
			message.setMsg("操作失败");
		}
		return message;
	}
}
