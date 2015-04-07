package com.creditease.rc.action;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.creditease.rc.dao.TradeDealFormDAO;
import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.AmountConfirm;
import com.creditease.rc.domain.CancelContractReason;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.DiscountConfiguration;
import com.creditease.rc.domain.GroupLoanRegist;
import com.creditease.rc.domain.LendingConfiguration;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.ProtocolMapping;
import com.creditease.rc.domain.TradeDealForm;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IAccountInfoService;
import com.creditease.rc.service.IAmountConfirmService;
import com.creditease.rc.service.ICancelContractReasonService;
import com.creditease.rc.service.IContractAndLoanService;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.IDiscountConfigurationService;
import com.creditease.rc.service.IGroupLoanRegistService;
import com.creditease.rc.service.INationalStandardCodeService;
import com.creditease.rc.service.IProtocolMappingService;
import com.creditease.rc.service.IorgamsService;
import com.creditease.rc.util.BankCardNOLock;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.vo.ContractAndLoan;
import com.creditease.rc.vo.ContractRate;
import com.creditease.rc.vo.ContractRateForQYResult;
import com.creditease.rc.vo.LoanConfirmMessageVo;


@Controller
@RequestMapping("contractAndLoanController")
public class ContractAndLoanController {

	/**
	 * 
	 * @param binder
	 *            WebDataBinder对象
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class, "contractSignedTime", new CustomDateEditor(dateFormat2, true));
		binder.registerCustomEditor(Date.class, "lastDownloadContractTime", new CustomDateEditor(dateFormat2, true));
	}

	@Resource
	private IContractAndLoanService contractAndLoanService;

	@Resource
	private IAccountInfoService accountInfoService;

	@Resource
	private IAmountConfirmService amountConfirmService;

	@Resource
	private IGroupLoanRegistService groupLoanRegistService;

	@Resource
	private IProtocolMappingService protocolMappingService;

	@Resource
	private ICancelContractReasonService cancelContractReasonService;

	@Resource
	private ICreditApplicationService creditApplicationService;

	@Resource
	private INationalStandardCodeService nationalStandardCodeService;
	
	@Resource
	private IorgamsService orgamsService;

    @Resource
    private IDiscountConfigurationService discountConfigurationService;
    
    @Resource
	private TradeDealFormDAO dealFormDAO;
	

	@RequestMapping(value = "returnNewLoanListJSP")
	public ModelAndView returnNewLoanListJSP(HttpSession httpSession) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/payment/newLoanList.jsp");
		return modelAndView;
	}

	@RequestMapping(value = "contractDateGrid")
	public @ResponseBody
	Pagination contractDateGrid(ContractAndLoan contractAndLoan, @RequestParam(required = false) String page, @RequestParam(required = false) String rows, String paramJsonMap, HttpSession session,
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
			String fuzzy = temp.get("fuzzy");
			String businessNumber = temp.get("businessNumber");
			String name = temp.get("name");
			String loanDateS = temp.get("loanDateS");
			String loanDateE = temp.get("loanDateE");
			String status = temp.get("status");
			String companyId = temp.get("companyId");
			String loanConfirmDateS = temp.get("loanConfirmDateS");
			String loanConfirmDateE = temp.get("loanConfirmDateE");
			String lendingChannel=temp.get("lendingChannel");
			queryMap.put("fuzzy", fuzzy);
			queryMap.put("businessNumber", businessNumber);
			queryMap.put("name", name);
			queryMap.put("loanDateS", loanDateS);
			queryMap.put("loanDateE", loanDateE);
			queryMap.put("status", status);
			queryMap.put("companyId", companyId);
			queryMap.put("loanConfirmDateS", loanConfirmDateS);
			queryMap.put("loanConfirmDateE", loanConfirmDateE);
			queryMap.put("lendingChannel", lendingChannel);
		}
		// 获取role的值是不是空值，来判断是不是从首页传过来的值
		List<Long> creditIdListLoan = (List<Long>) session.getAttribute("creditIdListLoan");
		if (CommonUtil.isNotEmpty(creditIdListLoan)) {
			String creditIdListLoanStr = "";
			for (Long test : creditIdListLoan) {
				creditIdListLoanStr = creditIdListLoanStr + test.toString() + ",";
			}
			creditIdListLoanStr = creditIdListLoanStr.substring(0, creditIdListLoanStr.length()-1);
			queryMap.put("creditIdListLoanStr", creditIdListLoanStr);
		}
		pagination = contractAndLoanService.contractDateGrid(queryMap, pagination);
		return pagination;
	}

	/**
	 * 返回合同页面
	 * 
	 * @param httpSession
	 * @param creditapplicationId
	 * @param type
	 *            类型 1 合同打印
	 * @return
	 */
	@RequestMapping(value = "returnContractJSP")
	public ModelAndView returnContractJSP(HttpSession httpSession, Long creditapplicationId, String clientid, String creditApplicationEscId, String type,String flag) {
		ModelAndView modelAndView = new ModelAndView();

		System.out.println(type);
		Calendar calendar = Calendar.getInstance();
		Date todayDate = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date tomorrowDate = calendar.getTime();
		long todayStamp = todayDate.getTime();
		long tomorrowStamp = tomorrowDate.getTime();
		calendar.add(Calendar.DATE, -1);
		calendar.add(Calendar.MINUTE, 30);
		Date after30M = calendar.getTime();
		long after30MStamp = after30M.getTime();
		String dateString = DateUtil.dateConvertString(todayDate);
		modelAndView.addObject("dateString", dateString);
		System.out.println(after30MStamp);
		
		modelAndView.addObject("creditapplicationId", creditapplicationId);
		modelAndView.addObject("dateString", dateString);

		modelAndView.addObject("todayStamp", todayStamp);
		modelAndView.addObject("tomorrowStamp", tomorrowStamp);
		modelAndView.addObject("after30MStamp", after30MStamp);
		modelAndView.addObject("clientid", clientid);
		modelAndView.addObject("creditApplicationEscId", creditApplicationEscId);
		// 判断type 0：打印合同，1：合同签订,2:重新打印合同
		ProtocolMapping protocolMapping = new ProtocolMapping();
		AccountInfo accountInfo = new AccountInfo();
		AmountConfirm amountConfirm = new AmountConfirm();
		String lastDownloadContractTime = "";
		String beginInterestTime = "";
		String lendingChannel="";
		String planName="";
		if (type.equals("1") || type.equals("2")) {
			protocolMapping = protocolMappingService.selectProtocolNumber(creditapplicationId);
			accountInfo = accountInfoService.selectByCrediApplicationId(creditapplicationId);
			// 查询最后一次打印合同时间，起息日期
			amountConfirm = contractAndLoanService.searchForConfirmAmount(creditapplicationId);
			// 最后一次下载合同时间
			lastDownloadContractTime = DateUtil.dateConvertStringTime(amountConfirm.getLastDownloadContractTime());
			// 显示起息日期
			beginInterestTime = DateUtil.dateConvertString(amountConfirm.getBeginInterestTime());
			//放款渠道
			lendingChannel=amountConfirm.getLendingChannel();
			//查询信托计划
			List<TradeDealForm> tradeDealFormList=contractAndLoanService.selectTradeDealFormResultByCaId(creditapplicationId);
			if(tradeDealFormList.size()>0){
			planName=tradeDealFormList.get(0).getPlanname();
			}
		}
		//查询担保人姓名
		//关联家庭成员表  查询共借人信息（配偶信息）
		LoanConfirmMessageVo  loanConfirmMessageVo= contractAndLoanService.searchForConfirm(creditapplicationId);
		// 查询最小审批金额
		ContractRate contractRate = contractAndLoanService.CalculateByCreditapplicationId(creditapplicationId);
		//查询放款渠道
		String selectLendingChannel = amountConfirmService.selectLendingChannel(creditapplicationId);
		String lendingConfiguration = "";
		if("0".equals(selectLendingChannel)){
			lendingConfiguration = selectLendingChannel;
		}else{
			lendingConfiguration = contractAndLoanService.selectByCaIdLf(creditapplicationId);
		}
		//查询打印合同之前   是否变更  放款渠道
		modelAndView.addObject("lendingChannel",lendingChannel);
		modelAndView.addObject("lendingConfiguration",lendingConfiguration);

		
		modelAndView.addObject("planName", planName);
		modelAndView.addObject("protocolMapping", protocolMapping);
		modelAndView.addObject("contractRate", contractRate);
		modelAndView.addObject("loanConfirmMessageVo", loanConfirmMessageVo);
		modelAndView.addObject("accountInfo", accountInfo);
		modelAndView.addObject("beginInterestTime", beginInterestTime);
		modelAndView.addObject("lastDownloadContractTime", lastDownloadContractTime);
		modelAndView.addObject("type", type);
		modelAndView.addObject("flag",flag);
		modelAndView.setViewName("/jsp/rc/contract/contract.jsp");
		return modelAndView;
	}

	/**
	 * 打印合同
	 * @param amountConfirm
	 * @param accountInfo
	 * @param groupLoadRegist
	 * @param loanRegistList
	 * @param groupLoanRegist
	 * @param uploadFileList
	 * @param uploadFileTypeList
	 * @param originalFileList
	 * @param time
	 * @param creditApplication
	 * @param loanDate
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "printContract")
	public @ResponseBody
	Message printContract(AmountConfirm amountConfirm, AccountInfo accountInfo, GroupLoanRegist groupLoadRegist, String loanRegistList, String groupLoanRegist, String[] uploadFileList,
			String[] uploadFileTypeList, String[] originalFileList, Date time, CreditApplication creditApplication, String loanDate, HttpServletRequest request) {
		System.out.println(amountConfirm);
		System.out.println(amountConfirm.getLoanTime());
		/** 对最终额度确认加了 账号内存锁 **/
		Message message = new Message();
        message = amountConfirmService.addPrintContract(amountConfirm, accountInfo, groupLoadRegist, loanRegistList, groupLoanRegist, uploadFileList, uploadFileTypeList, originalFileList, time, creditApplication, loanDate, request);
        /*获取折扣率*/
        creditApplication = creditApplicationService.selectById(creditApplication.getCreditapplicationId());
        DiscountConfiguration configuration = new DiscountConfiguration();
        configuration.setAreaDepartmentId(Long.valueOf(creditApplication.getCompanyId()));
        configuration.setProductCategoryId(Long.valueOf(creditApplication.getProducttypeid()));
        List<DiscountConfiguration> configurations = discountConfigurationService.queryDiscountConfigurationList(configuration, new Pagination()).getRows();
        if (CommonUtil.isNotEmpty(configurations) && configurations.get(0).getDiscountRate().toString().equals("1")) {
            /*更新打折标记：rl_credit_application.discount_flag为非打折*/
            creditApplicationService.updateDiscountFlagById(creditApplication.getCreditapplicationId(), "1");
        }
        return message;
	}

	/**
	 * 点击修改按钮重新调用产品中心接口
	 * 
	 * @param contractRateRequest
	 * @return
	 */
	@RequestMapping(value = "CalculateByCreditapplicationIdAndAmont")
	public @ResponseBody
	ContractRate CalculateByCreditapplicationIdAndAmont(HttpSession httpSession, Long creditapplicationId, BigDecimal amount) {
		System.out.println(creditapplicationId);
		System.out.println(amount);
		// 修改审批金额

		// 查询最小审批金额
		ContractRate contractRate = contractAndLoanService.CalculateByCreditapplicationId(creditapplicationId, amount);
		return contractRate;
	}

	/**
	 * * 合同签订
	 * @param uploadFileList
	 *            上传文件
	 * @param uploadFileTypeList
	 *            上传文件类型
	 * @param originalFileList
	 *            文件
	 * @param time
	 *            时间
	 * @return true ， false
	 */
	@RequestMapping(value = "contractSigned")
	public @ResponseBody
	Message contractSigned(GroupLoanRegist groupLoadRegist, AmountConfirm amountConfirm) {
		Message message = new Message();
		boolean isSuccess = false;
		int rows = 0;
		rows = groupLoanRegistService.addGroupLoanRegist(groupLoadRegist);
		isSuccess = rows > 0 ? true : false;
		message.setSuccess(isSuccess);
		return message;
	}

	/**
	 * 作废合同时保存 原因  放款渠道为信托时
	 */
	@RequestMapping(value = "saveCancelReasonRepeal")
	public @ResponseBody
	Message saveCancelReasonRepeal(CancelContractReason cancelContractReason) {
		Message message = new Message();
		//查询是否保存过合同作废原因
		CancelContractReason cancelContractReason2=cancelContractReasonService.selectReason(cancelContractReason.getCreditapplicationId());
		if(cancelContractReason2!=null){
			message=cancelContractReasonService.updateCancelReasonHistoryRepeal(cancelContractReason.getCreditapplicationId());
		}else{
		    message=cancelContractReasonService.saveCancelReasonRepeal(cancelContractReason);
		}
		return message;
	}
	/**
	 * 作废合同时保存 原因
	 */
	@RequestMapping(value = "saveCancelReason")
	public @ResponseBody
	Message saveCancelReason(CancelContractReason cancelContractReason) {
		Message message = new Message();
		boolean isSuccess = true;
		//查询是否保存过合同作废原因
		CancelContractReason cancelContractReason2=cancelContractReasonService.selectReason(cancelContractReason.getCreditapplicationId());
		if(cancelContractReason2!=null){
			cancelContractReasonService.updateCancelReasonHistory(cancelContractReason.getCreditapplicationId());
		}else{
		    cancelContractReasonService.saveCancelReason(cancelContractReason);
		}
		message.setSuccess(isSuccess);
		return message;
	}

	/**
	 * 放款确认 查出的基本信息 luohongjie
	 * 
	 * @param groupLoanRegist
	 * @return LoanConfirmMessageVo
	 */
	@RequestMapping(value = "searchForConfirm")
	public @ResponseBody
	LoanConfirmMessageVo searchForConfirm(GroupLoanRegist groupLoanRegist, Long creditapplicationId) {
		// TODO Auto-generated method stub
		LoanConfirmMessageVo loanConfirmMessageVo = new LoanConfirmMessageVo();
		AccountInfo accountInfo = accountInfoService.selectByCrediApplicationId(creditapplicationId);
		AmountConfirm amountConfirm = contractAndLoanService.searchForConfirmAmount(creditapplicationId);
		ContractRateForQYResult contractRateForQYResult = contractAndLoanService.qyContractRateForQYResult(creditapplicationId);
		List<TradeDealForm> tradeDealFormList=contractAndLoanService.selectTradeDealFormResultByCaId(creditapplicationId);
		String planName="";
		if(tradeDealFormList.size()>0){
			planName=tradeDealFormList.get(0).getPlanname();
		}
		groupLoanRegist = groupLoanRegistService.searchForGroupLoanRegist(creditapplicationId);
		// groupLoanRegist=groupLoanRegistService.searchForConfirm(groupLoanRegist);
		ContractRate contractRate = null;
		if (amountConfirm != null&&amountConfirm.getAmount()!=null) {
			BigDecimal amount = new BigDecimal(amountConfirm.getAmount());
			// 查询最小审批金额
			contractRate = contractAndLoanService.CalculateByCreditapplicationId(creditapplicationId, amount);
		}

		//机构资产传给我们那个  信托代码  就是给产品中心传的合同编码
		//信托合同编号
		String plancode = "";
		List<TradeDealForm> tradeDealFormResult = dealFormDAO.selectTradeDealFormResultByCaId(Long.parseLong(creditapplicationId.toString()));
		if(CommonUtil.isNotEmpty(tradeDealFormResult)){
			plancode = tradeDealFormResult.get(0).getPlancode();
		}
		if(contractRate!=null){
			contractRate.setContractNo(plancode+contractRate.getContractNo());
		}
		//关联家庭成员表  查询共借人信息（配偶信息）
		loanConfirmMessageVo = contractAndLoanService.searchForConfirm(creditapplicationId);
		
		loanConfirmMessageVo.setPlanName(planName);
		loanConfirmMessageVo.setContractRateForQYResult(contractRateForQYResult);
		loanConfirmMessageVo.setAccountInfo(accountInfo);
		loanConfirmMessageVo.setAmountConfirm(amountConfirm);
		loanConfirmMessageVo.setGroupLoanRegist(groupLoanRegist);
		loanConfirmMessageVo.setContractRate(contractRate);
		return loanConfirmMessageVo;
	}

	/**
	 * 放款确认（回退）
	 * 
	 * @param groupLoanRegist
	 *            放款登记对象
	 * @param registStatus
	 *            放款确认状态
	 * @param creditApplication
	 *            借款申请
	 * @return 成功失败
	 */
	@RequestMapping(value = "loanConfirm")
	public @ResponseBody
	Message loanConfirm(GroupLoanRegist groupLoanRegist, CreditApplication creditApplication, String registStatus, String type) {
		//查询放款渠道是否信托
		String str = amountConfirmService.selectLendingChannel(Long.parseLong(creditApplication.getCreditapplicationId().toString()));
		//判断是信托还是债权转让
		if("1".equals(str)){
			//信托
			type = "xintuo";
		}
		Message message = new Message();
		Integer creditapplicationId = creditApplication.getCreditapplicationId();
		CreditApplication creditApplication2 = creditApplicationService.selectById(creditapplicationId);
		Integer accountInfoId = creditApplication2.getAccountInfoId();

		AccountInfo accountInfoSelect = accountInfoService.selectByAccountID(accountInfoId);
		String account = accountInfoSelect.getAccount();
		if (CommonUtil.isNotEmpty(account)) {
			// 判断是否存在锁
			Boolean b = BankCardNOLock.lockBankCardNO(account);
			if (b) {
				// 获取银行卡号。加锁
				try {
					message = groupLoanRegistService.addOrUpdateGroupLoanRegist(groupLoanRegist, creditApplication, registStatus, null, type);
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

	// 查询当前的业务状态
	@RequestMapping(value = "searchForCreditApplicationStates")
	public @ResponseBody
	CreditApplication searchForCreditApplicationStates(Long creditapplicationId) {
		// TODO Auto-generated method stub
		CreditApplication creditApplication = new CreditApplication();
		creditApplication = contractAndLoanService.searchForCreditApplicationStates(creditapplicationId);
		return creditApplication;
	}

	@RequestMapping("reutrnNewLoanListSession")
	public ModelAndView reutrnNewLoanListSession(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/payment/newLoanList.jsp");
		List<Long> creditIdListLoan = contractAndLoanService.queryCreditIdListLoan();
		session.setAttribute("creditIdListLoan", creditIdListLoan);
		return modelAndView;

	}
	/**
	 * 查询营业部名称，和借款人的电话号码
	 * 
	 */
	// 查询当前的业务状态
	@RequestMapping(value = "searchForCompanyNameAndMobilePhone")
	public @ResponseBody
	AccountInfo searchForCompanyNameAndMobilePhone(AccountInfo accountInfo,Long creditapplicationId) {
		accountInfo = contractAndLoanService.searchForCompanyNameAndMobilePhone(creditapplicationId);
		return accountInfo;
	}
	/**
	 * 查询是否做过渠道配置
	 * 
	 */
	@RequestMapping("searchConfigureChannel")
	public @ResponseBody
	LendingConfiguration searchConfigureChannel(Long creditapplicationId) {
		LendingConfiguration lendingConfiguration=new LendingConfiguration();
		lendingConfiguration=contractAndLoanService.searchConfigureChannel(creditapplicationId);
		return lendingConfiguration;
	}
	/**
	 *调用信托的撮合接口
	 *
	 */
	@RequestMapping("trustprintContract")
	public @ResponseBody
	Message trustprintContract(AmountConfirm amountConfirm, AccountInfo accountInfo, GroupLoanRegist groupLoadRegist,CreditApplication creditApplication, String type) {
		Message message=new Message();
		message=contractAndLoanService.addTtrustprintContract(amountConfirm,accountInfo,groupLoadRegist,creditApplication,type);
		return message;
	}
	
	
	/**
	 * 调用撤销撮合的接口  客户放弃  拒贷时
	 */
	@RequestMapping("revokeMatchmakingRefuseReason")
	public @ResponseBody
	Message revokeMatchmakingRefuseReason(Long creditapplicationId,String section,String refuseReasons,String auditStatus) {
		Message message=new Message();
		message=contractAndLoanService.revokeMatchmaking(creditapplicationId,section,refuseReasons,auditStatus);
		return message;
	}
	
	/**
	 * 调用撤销撮合的接口  退回重签时
	 */
	@RequestMapping("revokeMatchmaking")
	public @ResponseBody
	Message revokeMatchmaking(CreditApplication creditApplication) {
		Message message=new Message();
		message=contractAndLoanService.revokeMatchmaking(creditApplication);
		return message;
	}
	
	/**
	 * 信托打印合同
	 * @param creditapplicationId
	 * @param request
	 * @return
	 */
	@RequestMapping("updatePrintContractState")
	public @ResponseBody Message updatePrintContractState(Long creditapplicationId,HttpServletRequest request){
		Message message = new Message();
		try {
			String url = amountConfirmService.updatePrintContract(creditapplicationId,request);
			message.setSuccess(true);
			message.setMsg(url);
		} catch (Exception e) {
			//经讨论又不用调撤销撮合了   2014-09-15
			/*try {
				orgamsService.upMatching(creditapplicationId);//调用撤销撮合
			} catch (Exception e2) {
				message.setSuccess(false);
				message.setMsg("撤销撮合失败");
				e2.printStackTrace();
			}*/
			e.printStackTrace();
			message.setSuccess(false);
			message.setMsg("打印合同失敗");
		}
		return message;
	}
	/**
	 * 变更放款渠道
	 */
	@RequestMapping("updateChangeLoanChannelXinTuo")
	public @ResponseBody Message updateChangeLoanChannelXinTuo(Integer creditapplicationId,Long amountConfirmId){
		AmountConfirm amountConfirm=new AmountConfirm();
		amountConfirm.setAmountConfirmId(amountConfirmId);
		amountConfirm.setCreditapplicationId(creditapplicationId);
		amountConfirm.setLendingChannel("0");
		amountConfirm.setHistoryFlag("0");
		Message message = new Message();
		boolean isSuccess=false;
		isSuccess=amountConfirmService.selectIsExist(amountConfirm);
		if(isSuccess){
			message.setSuccess(true);
		}
		return message;
	}
}
