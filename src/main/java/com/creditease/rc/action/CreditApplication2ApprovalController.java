package com.creditease.rc.action;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.app.sia.service.IcomprehensiveServe;
import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.ICreditApplicationDAO;
import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.RlApplyAuditTable;
import com.creditease.rc.domain.RlAuditDetail;
import com.creditease.rc.domain.*;
import com.creditease.rc.service.IAccountInfoService;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.IDiscountConfigurationService;
import com.creditease.rc.service.IRlApplyAuditTableService;
import com.creditease.rc.service.IRlCreditApplicationService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.CurrencyUtil;
import com.creditease.rc.util.DESPlus;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.PropertiesUtil;
import com.creditease.rc.vo.RlAuditDetails;
import com.creditease.rc.vo.TBorrowerServiceAppVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 审批类控制层
 * 
 * @author xubingzhao
 * 
 */
@Controller
@RequestMapping("creditapplication2Approval")
public class CreditApplication2ApprovalController {
	@Resource
	private IRlCreditApplicationService iRlCreditApplicationService;
	@Resource
	private IAccountInfoService iAccountInfoService;
	@Resource
	private IRlApplyAuditTableService rlApplyAuditTableService;
	@Resource
	private ICreditApplicationService creditApplicationService;
	@Resource
	private IDiscountConfigurationService discountConfigurationService;
	@Resource
	private IcomprehensiveServe icomprehensiveServe;
	@Resource
	private ICreditApplicationDAO creditApplicationDAO;
	/**
	 * 审批通过
	 * 
	 * @param rlAuditDetails 前期小组模式所需对象，后改为个贷后，实际没有作用，但不建议改代码和删表。
	 * @param rlApplyAuditTable 审批记录对象
	 * @param rlCreditApplication 信贷申请对象
	 * @return result 返回结果
	 */
	@RequestMapping(value = "updateRlcreditapplication", method = RequestMethod.POST)
	public @ResponseBody
	Message updateAppraval(RlAuditDetails rlAuditDetails, RlApplyAuditTable rlApplyAuditTable,
			CreditApplication rlCreditApplication){
		rlCreditApplication.setAuditStatus(Constants.STATE_4);
		rlApplyAuditTable.setExamResult(Constants.AUDIT_STATE_PASS);
		List<RlAuditDetail> list = rlAuditDetails.getList();
		Double sum = 0.0;
		for (RlAuditDetail r : list) {
			sum = CurrencyUtil.add(sum, r.getAmount());
			r.setAuditStatus(Constants.AUDIT_STATE_PASS);
			r.setAuditFlag("1");
		}
		rlApplyAuditTable.setExamAmount(sum);
		rlApplyAuditTable.setAuditType("1");
		String auditor = SpringSecurityUtils.getCurrentUser().getName_zh();
		rlApplyAuditTable.setExamPaerson(auditor);
		String date = DateUtil.dateConvertString(new Date(), "yyyy-MM-dd HH:mm:ss");
		rlApplyAuditTable.setExamDate(date);
		// 审批者角色[approve, fxapprove, participateApprove] <=> 审批类型 [0, 1, 4]
		if (Constants.ROLE_RISK_MGR.equals(rlCreditApplication.getRole())) {
			rlApplyAuditTable.setAuditType(Constants.AUDIT_TYPE_FX);
		} else if (Constants.ROLE_LOAN_MGR.equals(rlCreditApplication.getRole())) {
			rlApplyAuditTable.setAuditType(Constants.AUDIT_TYPE_BS);
		} else if (Constants.ROLE_PARTICIPATE.equals(rlCreditApplication.getRole())) {
			rlApplyAuditTable.setAuditType(Constants.AUDIT_TYPE_PARTICIPATE);
		}
// 日期不要了
// rlCreditApplication.setExpectLoanDate(DateUtil.stringConvertDateTime(rlApplyAuditTable.getLoanTime()+" 00:00:00"));
		Message message = new Message();
		try {
		boolean result = iRlCreditApplicationService.updateAppraval(list, rlApplyAuditTable, rlCreditApplication);
		
		message.setSuccess(result);
		if (result) {
			// TODO 判断是否是推送到综合信贷
			String queryIsZhxindai = discountConfigurationService.queryIsZhxindai(Long.parseLong(rlCreditApplication.getCreditapplicationId().toString()));
			if(CommonUtil.isNotEmpty(queryIsZhxindai)){
				if("1".equals(queryIsZhxindai)){
					//查询该笔数据的状态
					String queryStatus = creditApplicationService.queryStatus(Long.parseLong(rlCreditApplication.getCreditapplicationId().toString()));
					if(CommonUtil.isNotEmpty(queryStatus)){
						if("04".equals(queryStatus)){
							// TODO 等待商讨后更改状态
							
								icomprehensiveServe.send2Comprehensive(Long.parseLong(rlCreditApplication.getCreditapplicationId().toString()));
								//更改本地状态为等待icp合同签订状态‘40’
								CreditApplication creditApplication = new CreditApplication();
								creditApplication.setAuditStatus("40");
								creditApplication.setCreditapplicationId(rlCreditApplication.getCreditapplicationId());
								creditApplicationDAO.submitAuditing(creditApplication);
								message.setMsg("审批成功！数据推送到综合信贷成功！");
							 
						}
					}
				}
			}else{
				message.setMsg("审批成功！");
			}
		} else {
			message.setMsg("审批失败！");
		}
		} catch (Exception e) {
			message.setMsg("审批成功！但数据推送到综合信贷失败！");
			throw new RuntimeException(e.getMessage());
		}
		
		return message;
		// 审批过后自动预约
// int accountInfoId=rlCreditApplication.getAccountInfoId();
// AccountInfo accountInfo=accountInfoService.selectByAccountID(accountInfoId);
// PaymentRecord paymentRecord=new PaymentRecord();
// paymentRecord.setCreditapplicationId(rlCreditApplication.getCreditapplicationId());
// DecimalFormat f = new DecimalFormat ("######.0");
// paymentRecord.setPayStatus("0");
// paymentRecord.setOperatorId(auditorId);
// paymentRecord.setOperatorName(auditor);
// paymentRecord.setOperateDate(DateUtil.stringConvertDate(date));
// paymentRecord.setRemark("");
// //放款日期
// Date loanDate = null;
// Date loanTime = DateUtil.stringConvertDateTime(rlApplyAuditTable.getLoanTime()+" 00:00:00");
// Date examDate =DateUtil.stringConvertDateTime(rlApplyAuditTable.getExamDate()+" 00:00:00");
// if(0==examDate.compareTo(loanTime)){
// loanDate=examDate;
// }else{
// loanDate = DateUtil.stringConvertDate(DateUtil.dateConvertString(loanTime)+" 10:00:00");
// }
// //放款金额
// CreditApplication c=creditApplicationService.selectById(rlCreditApplication.getCreditapplicationId());
// List<RepaymentPlan> listRepaymentPlan=repaymentPlanService.returnComboboxRepayment(c.getRepaymentPlanId(), null);
// if(listRepaymentPlan.size()>0){
// Double examAmount=rlApplyAuditTable.getExamAmount();
// Double fiserServiceCharge=CurrencyUtil.mul(examAmount, listRepaymentPlan.get(0).getFirstServiceFree());
// Double actualLoanAmount=CurrencyUtil.sub(examAmount, fiserServiceCharge);
// paymentRecord.setPayAmount(new BigDecimal(f.format(actualLoanAmount).toString()));
// }
// paymentRecordService.insertPaymentRecord(paymentRecord);//存入paymentRecord
// FinanceMoney financeMoney=new FinanceMoney();
// financeMoney.setReserveTime(loanDate);
// financePaymentService.financialpayment(accountInfo,paymentRecord,financeMoney);
// return false;
	}

	/**
	 * 审批拒绝
	 * 
	 * @param rlAuditDetails 前期小组模式所需对象，后改为个贷后，实际没有作用，但不建议改代码和删表。
	 * @param rlApplyAuditTable 审批记录对象
	 * @param rlCreditApplication 信贷申请对象
	 * @return result 返回结果
	 */
	@RequestMapping(value = "unUpdateRlcreditapplication", method = RequestMethod.POST)
	public @ResponseBody
	Message unUpdateAppraval(RlAuditDetails rlAuditDetails, RlApplyAuditTable rlApplyAuditTable,
			CreditApplication rlCreditApplication) {
		rlCreditApplication.setAuditStatus(Constants.STATE_18);
		rlApplyAuditTable.setExamResult(Constants.AUDIT_STATE_REFUSE);
		List<RlAuditDetail> list = rlAuditDetails.getList();
		Double sum = 0.0;
		for (RlAuditDetail r : list) {
			sum += r.getAmount();
			r.setAuditFlag("1");
		}
		rlApplyAuditTable.setExamAmount(sum);
		rlApplyAuditTable.setAuditType("1");
		String auditor = SpringSecurityUtils.getCurrentUser().getName_zh();
		rlApplyAuditTable.setExamPaerson(auditor);

		String date = DateUtil.dateConvertString(new Date(), "yyyy-MM-dd HH:mm:ss");

		rlApplyAuditTable.setExamDate(date);
		// 审批者角色[approve, fxapprove, participateApprove] <=> 审批类型 [0, 1, 4]
		if (Constants.ROLE_RISK_MGR.equals(rlCreditApplication.getRole())) {
			rlApplyAuditTable.setAuditType(Constants.AUDIT_TYPE_FX);
		} else if (Constants.ROLE_LOAN_MGR.equals(rlCreditApplication.getRole())) {
			rlApplyAuditTable.setAuditType(Constants.AUDIT_TYPE_BS);
		} else if (Constants.ROLE_PARTICIPATE.equals(rlCreditApplication.getRole())) {
			rlApplyAuditTable.setAuditType(Constants.AUDIT_TYPE_PARTICIPATE);
		}
		boolean result = iRlCreditApplicationService
				.updateAppraval2Refuse(list, rlApplyAuditTable, rlCreditApplication);
		Message message = new Message();
		message.setSuccess(result);
		if (result) {
			message.setMsg("拒绝成功！");
		} else {
			message.setMsg("拒绝失败！");
		}
		return message;
	}

	/**
	 * 变更额度
	 * 
	 * @param rlAuditDetails
	 * @param rlApplyAuditTable
	 * @param creditApplication
	 * @return result
	 */
	/*
	 * @RequestMapping(value="chgmount",method = RequestMethod.POST)
	 * public
	 * 
	 * @ResponseBody boolean chgmount(RlAuditDetails rlAuditDetails ,
	 * RlApplyAuditTable rlApplyAuditTable,CreditApplication creditApplication){
	 * creditApplication.setAuditStatus(Constants.STATE_17);
	 * rlApplyAuditTable.setExamResult(Constants.STATE_17);
	 * List<RlAuditDetail>list=rlAuditDetails.getList();
	 * Double sum=0.0;
	 * for(RlAuditDetail r:list){
	 * sum+=r.getAmount();
	 * r.setAuditStatus(Constants.STATE_17);
	 * r.setAuditFlag("1");
	 * }
	 * rlApplyAuditTable.setExamAmount(sum);
	 * rlApplyAuditTable.setAuditType("1");
	 * String auditor=SpringSecurityUtils.getCurrentUser().getName_zh();
	 * rlApplyAuditTable.setExamPaerson(auditor);
	 * String date=DateUtil.dateConvertString(new Date());
	 * rlApplyAuditTable.setExamDate(date);
	 * boolean result=iRlCreditApplicationService.updateRlAuditDetail(list,rlApplyAuditTable,creditApplication);
	 * return result;
	 * }
	 */
	/**
	 * 补充资料
	 * 
	 * @param rlAuditDetails
	 * @param rlApplyAuditTable
	 * @param creditApplication
	 * @return result
	 */
	/*
	 * @RequestMapping(value="addinfo",method = RequestMethod.POST)
	 * public
	 * 
	 * @ResponseBody boolean addinfo(RlAuditDetails rlAuditDetails ,
	 * RlApplyAuditTable rlApplyAuditTable,CreditApplication creditApplication){
	 * creditApplication.setAuditStatus(Constants.STATE_5);
	 * rlApplyAuditTable.setExamResult(Constants.STATE_5);
	 * List<RlAuditDetail>list=rlAuditDetails.getList();
	 * Double sum=0.0;
	 * for(RlAuditDetail r:list){
	 * sum+=r.getAmount();
	 * r.setAuditStatus(Constants.STATE_5);
	 * r.setAuditFlag("1");
	 * }
	 * rlApplyAuditTable.setExamAmount(sum);
	 * rlApplyAuditTable.setAuditType("1");
	 * String auditor=SpringSecurityUtils.getCurrentUser().getName_zh();
	 * rlApplyAuditTable.setExamPaerson(auditor);
	 * 
	 * String date=DateUtil.dateConvertString(new Date());
	 * 
	 * rlApplyAuditTable.setExamDate(date);
	 * boolean result=iRlCreditApplicationService.updateRlAuditDetail(list,rlApplyAuditTable,creditApplication);
	 * return result;
	 * }
	 */
	/**
	 * 查询审批记录信息
	 * 
	 * @param rlCreditApplication RlCreditApplication 信贷申请单对象
	 * @param request HttpServletRequest
	 * @return ModelAndView
	 */
	@RequestMapping(value = "selectById")
	public ModelAndView selectById(CreditApplication rlCreditApplication, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/jsp/rc/approval/approval.jsp");
		modelAndView.addObject("role", rlCreditApplication.getRole());// 业务经理&风险经理角色标志
		// 获得小组信息 页面查看资料时传递
		CreditApplication creditApplication = iRlCreditApplicationService
				.selectRlCreditApplication(rlCreditApplication);
		String creditapplicationId = creditApplication.getCreditapplicationId().toString();
		modelAndView.addObject("creditApplication", creditApplication);
		try {
			DESPlus desPlus = new DESPlus();
			String caDESId = desPlus.encrypt(creditapplicationId);// 内容管理平台需要的参数
			String caDESId1 = desPlus.encrypt(creditapplicationId + Constants.CM_EXAM);// 内容管理平台需要的参数
			modelAndView.addObject("caDESId", caDESId);
			modelAndView.addObject("caDESId1", caDESId1);
			modelAndView.addObject("cmexam", creditapplicationId + Constants.CM_EXAM);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 获得借款人信息
		rlCreditApplication.setLeader("1");
		List<TBorrowerServiceAppVo> borrowerList = iRlCreditApplicationService
				.selectRlBorrowerServiceApp(rlCreditApplication);
		// 获取担保人信息
		rlCreditApplication.setLeader("0");
		List<TBorrowerServiceAppVo> guaRanorList = iRlCreditApplicationService
				.selectRlBorrowerServiceApp(rlCreditApplication);
		for (TBorrowerServiceAppVo s : guaRanorList) {
			try {
				DESPlus desPlus = new DESPlus();
				String caDESId = desPlus.encrypt(s.getBorrowerServiceAppId().toString());// 内容管理平台需要的参数
				// 借用了一个删除标识字段用来存储加密后的getBorrowerServiceAppId
				s.setValidState(caDESId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		modelAndView.addObject("list", borrowerList);
		modelAndView.addObject("guaRanorList", guaRanorList);
		// 审批获得账号信息 （已不用）
		AccountInfo rlAccountInfo = new AccountInfo();
		String useType = creditApplication.getBusinessType();
		// 1代表个人业务 0代表分公司业务-----------------------------------
		if ("1".equals(useType)) {
			Integer accountInfoId = creditApplication.getAccountInfoId();
			rlAccountInfo.setAccountInfoId(accountInfoId);
			// 账户类型 0 分公司账户，1个人账户
			rlAccountInfo.setAccountType("1");
		} else {
			String branchName = creditApplication.getCompanyName();
			rlAccountInfo.setBranchName(branchName);
			// 账户类型 0 分公司账户，1个人账户
			rlAccountInfo.setAccountType("0");
		}
		rlAccountInfo.setUseType("0");
		// 获得分公司账号或个人账号信息
		List<AccountInfo> resultAccountInfoList = iAccountInfoService.selectById(rlAccountInfo);
		modelAndView.addObject("rlAccountInfoList", resultAccountInfoList);
		String accountType = "";
		if (resultAccountInfoList.size() > 0) {
			accountType = resultAccountInfoList.get(0).getAccountType();
		} else {
			String alert = "无此分公司或无收款账户，请检查分公司名称是否正确或账户是否是收款账户";
			modelAndView.addObject("alert", alert);
		}
		// ---------------------------------------------------------------
		modelAndView.addObject("accountType", accountType);
		// 获得历史审批信息
		Integer id = rlCreditApplication.getCreditapplicationId();
		List<RlApplyAuditTable> listRlAAT = rlApplyAuditTableService.getRemarks(id);
		modelAndView.addObject("listRlAAT", listRlAAT);
        /*区域经理/区域风险经理审批时，获取审批金额最小值。*/
        Integer finalAmount = rlApplyAuditTableService.getMinExamineAmount(rlCreditApplication.getCreditapplicationId());
        if (finalAmount != null) {
            modelAndView.addObject("finalAmount", finalAmount.doubleValue());
        }
		return modelAndView;
	}

	/**
	 * 查询当前登录人是否已经审批过
	 * 
	 * @param rlApplyAuditTable
	 * @param creditApplication
	 * @return boolean
	 */
	@RequestMapping(value = "isAduited")
	public @ResponseBody
	Message isAduited(RlApplyAuditTable rlApplyAuditTable, CreditApplication creditApplication) {
		Message message = isHavAudited(creditApplication);
		if (!message.isSuccess()) {
			return message;
		}
		List<RlApplyAuditTable> result = null;
// rlApplyAuditTable.setExamResult(Constants.STATE_4);
		String auditor = SpringSecurityUtils.getCurrentUser().getName_zh();
		rlApplyAuditTable.setExamPaerson(auditor);
		result = rlApplyAuditTableService.selectAuditRecord(rlApplyAuditTable);
		if (0 < result.size()) {
			message.setMsg("已经审批过了，请勿重复审批");
			message.setSuccess(false);
			return message;
		}
		return message;
	}

	/**
	 * 组织给内容管理平台的参数，第一个为时间毫秒数，第二个cm.hostip的加密结果
	 * 
	 * @return String[]
	 */
	@RequestMapping(value = "getDate")
	public @ResponseBody
	String[] getDate() {
// Calendar now=Calendar.getInstance();
		long nowTime = System.currentTimeMillis();
// Date now = new Date();
// String nowTime = DateUtil.dateConvertString(now);
		String sMillis[] = new String[2];
		try {
			Properties properties = PropertiesUtil.loadProperties("spring/cm/cm.properties");
			DESPlus desPlus = new DESPlus();
			sMillis[0] = desPlus.encrypt(nowTime + "");
			String cmIp = properties.getProperty("cm.hostip");
			String DESIp = desPlus.encrypt(cmIp);
			sMillis[1] = DESIp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sMillis;
	}

	/**
	 * 验证是否可以做审批
	 * 
	 * @param creditApplication 信贷申请单对象
	 * @return Message
	 */
	private Message isHavAudited(CreditApplication creditApplication) {
		int creditapplicationId = creditApplication.getCreditapplicationId();
		Message message = new Message(true);
		CreditApplication creditApplicationResult = creditApplicationService.selectById(creditapplicationId);
        if (!("01".equals(creditApplicationResult.getAuditStatus()) ||
              "24".equals(creditApplicationResult.getAuditStatus()) ||
              "31".equals(creditApplicationResult.getAuditStatus()) ||
              "32".equals(creditApplicationResult.getAuditStatus()))) {
            message.setSuccess(false);
            message.setMsg("审批已通过或已撤回，请刷新页面");
        }
        return message;

	}
	
	
	/**
	 * 验证是否可以做审批
	 * 
	 */
	@RequestMapping(value="getCreditId")
	public  @ResponseBody Map getCreditId(String getCreditId) {
		Map map = new HashMap(); 
		DESPlus desPlus;
		try {
			desPlus = new DESPlus();
			map.put("creditapplicationId", desPlus.encrypt(getCreditId).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;

	}

    /**
     * 保存参审操作记录
     *
     * @param application 信贷申请
     * @return 响应消息
     */
    @RequestMapping(value = "participateApprove", method = RequestMethod.POST)
    public
    @ResponseBody
    Message participateApprove(CreditApplication application) {
        return creditApplicationService.saveParticipateApprove(application);
    }
}
