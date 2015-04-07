package com.creditease.rc.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.security.User;
import com.creditease.rc.app.orgams2.BorrowContractRes;
import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.IAccountInfoDAO;
import com.creditease.rc.dao.IGroupLoanRegistDAO;
import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.GroupLoanRegist;
import com.creditease.rc.domain.LoanRegist;
import com.creditease.rc.domain.Message;
import com.creditease.rc.service.IContractAndLoanService;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.IFinancePaymentService;
import com.creditease.rc.service.IGroupLoanRegistService;
import com.creditease.rc.service.ILoanRegistService;
import com.creditease.rc.service.IModifyCatdAppService;
import com.creditease.rc.service.IOperateLogService;
import com.creditease.rc.service.IReturnPlanService;
import com.creditease.rc.service.IRural2CreditService;
import com.creditease.rc.service.IorgamsService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.vo.AccountInfoVo;
import com.creditease.rc.vo.GroupLoanRegistVo;
import com.creditease.rc.vo.OperateLogBusinessStruct;

/**
 * 放款登记service 实现类
 * 
 * @author zhangman
 * 
 */
@Service
public class GroupLoanRegistService implements IGroupLoanRegistService {

	// 放款登记服务
	@Resource
	private IGroupLoanRegistDAO groupLoanRegistDAO;

	// 信贷申请服务
	@Resource
	private ICreditApplicationService creditApplicationService;
	//
	@Resource
	private ILoanRegistService loanRegistService;
	// 预约
	@Resource
	private IFinancePaymentService financePaymentService;
	@Resource
	private IOperateLogService operateLogService;
	// 还款计划
	@Resource
	private IReturnPlanService returnPlanService;
	@Resource
	private IAccountInfoDAO accountInDao;

	@Resource
	private IModifyCatdAppService modifyCatdAppService;
	@Resource
	private IContractAndLoanService contractAndLoanService;

	//调用贷后  接收借款信息
	@Resource
	private IRural2CreditService  rural2CreditService;
	//合同同步
	@Resource
	private IorgamsService orgamsService;
	@Override
	public int addGroupLoanRegist(GroupLoanRegist groupLoanRegist) {
		// TODO Auto-generated method stubloanRegistDate
		int row=0;
		groupLoanRegist.setLoanRegistDate(new Timestamp( new Date().getTime()));
		groupLoanRegist.setRegistStatus("0");
		groupLoanRegist.setHistoryFlag("F");
		//获取当前登陆人的id  name
		User user = SpringSecurityUtils.getCurrentUser();
		groupLoanRegist.setLoanOfficerId((user.getUserId()));
		groupLoanRegist.setLoanOfficerName(user.getName_zh());
		row=groupLoanRegistDAO.addGroupLoanRegist(groupLoanRegist);
		boolean isSuccess=false;
		if(row>0){
			isSuccess=contractAndLoanService.updateUploadingContractState(Long.valueOf(groupLoanRegist.getCreditapplicationId()));
			if(isSuccess){
				return row;
			}else{
				return 0;
			}
		}else{
			return 0;
		}
	}

	@Override
	public boolean addGroupLoanRegist(GroupLoanRegist groupLoanRegist, CreditApplication creditApplication,
			List<LoanRegist> loanRegistList, String[] uploadFileList, String[] uploadFileTypeList,
			String[] originalFileList) {
		CreditApplication creditApplication2 = creditApplicationService.selectById(creditApplication
				.getCreditapplicationId());
		if ("21".equals(creditApplication2.getAuditStatus()) || "10".equals(creditApplication2.getAuditStatus())
				|| "14".equals(creditApplication2.getAuditStatus())) {
			// 修改信贷申请状态 //修改个人申请状态
			groupLoanRegist.setRegistStatus("0");
			creditApplication.setSignagreementDate(groupLoanRegist.getLoanTime());
			// 放款登记
			creditApplicationService.changeAuditing(creditApplication, groupLoanRegist.getCreditapplicationId(),
					Constants.STATE_11);
			// 新增放款登记 //设置放款登记操作时间
			groupLoanRegist.setLoanRegistDate(new Date());
			int groupLoanRegistId = this.addGroupLoanRegist(groupLoanRegist);
			if (loanRegistList != null) {
				for (LoanRegist loanRegist : loanRegistList) {
					loanRegist.setGroupLoanRegistId(groupLoanRegistId);
				}
				loanRegistService.addBatchLoanRegist(loanRegistList);
				/******** 日志 ********/
				OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
				operateLog.setCreditapplicationId(Long.valueOf(creditApplication.getCreditapplicationId()));
				operateLog.setFunctionCode("300030");
				operateLog.setResult("放款登记");
				operateLog.setRemark("放款登记日期（页面上的填写日期）是："
						+ new SimpleDateFormat("yyyy-MM-dd").format(groupLoanRegist.getLoanTime()));
				operateLog.setLoanAmount(String.valueOf(groupLoanRegist.getLoanAmount()));
				operateLogService.insert(operateLog);

			}
			return true;
		} else {
			return false;
		}
		// 放款详细登记 页面不做实际放款改动的情况
	}

	@Override
	public Message addOrUpdateGroupLoanRegist(GroupLoanRegist groupLoanRegist, CreditApplication creditApplication,
			String registStatus, List<LoanRegist> loanRegistList) {
		Message message = new Message();
		if (null == groupLoanRegist.getCreditapplicationId()) {
			message.setSuccess(false);
			System.out.println("groupLoanRegist中CreditapplicationId属性:" + groupLoanRegist.getCreditapplicationId());
			message.setMsg("数据不完整 或者状态已经不是“已放款登记” 将为您刷新页面");
			return message;
		}
		creditApplication = creditApplicationService.selectById(groupLoanRegist.getCreditapplicationId());
		message.setSuccess(true);

		Integer creditapplicationId = groupLoanRegist.getCreditapplicationId();
		// 修改信贷申请状态 //修改个人申请状态
		creditApplication.setSignagreementDate(groupLoanRegist.getLoanTime());
		groupLoanRegist.setRegistStatus(registStatus);
		// 放款登记
		if ("0".equals(registStatus)) {
			creditApplicationService.changeAuditing(creditApplication, groupLoanRegist.getCreditapplicationId(),
					Constants.STATE_11);
			// 新增放款登记 //设置放款登记操作时间
			groupLoanRegist.setLoanRegistDate(new Date());
			int groupLoanRegistId = this.addGroupLoanRegist(groupLoanRegist);
			if (loanRegistList != null) {
				for (LoanRegist loanRegist : loanRegistList) {
					loanRegist.setGroupLoanRegistId(groupLoanRegistId);
				}
				loanRegistService.addBatchLoanRegist(loanRegistList);
			}
		}
		// 查询该数据当前 状态（放款登记？放款回退？ 放款登记的状态下 执行确认操作）
		GroupLoanRegist groupLoanRegistNew = groupLoanRegistDAO.selectById(groupLoanRegist.getGroupLoanRegistId());
		if (Constants.LOAN_STATE_0.equals(groupLoanRegistNew.getRegistStatus())) {
			// 放款确认
			if ("1".equals(registStatus)) {
				if (Constants.STATE_11.equals(creditApplication.getAuditStatus())) {
					// 放款确认时间
					groupLoanRegist.setLoanConfirmTime(new Date());

					// 如果是个人账户 调财务预约接口
					if ("1".equals(creditApplication.getBusinessType())) {
						// 放款确认
						groupLoanRegistDAO.updateGroupLoanRegist(groupLoanRegist);
						/******** 日志 ********/
						OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
						operateLog.setCreditapplicationId(Long.valueOf(creditApplication.getCreditapplicationId()));
						operateLog.setFunctionCode("300040");
						operateLog.setResult("放款确认");
						operateLogService.insert(operateLog);
						// 放款确认
						creditApplicationService.changeAuditing(creditApplication,
								groupLoanRegist.getCreditapplicationId(), Constants.STATE_13);
						// 调财务预约接口
						if (financePaymentService.financialpayment(creditapplicationId, null).isSuccess()) {
						} else {
							message.setSuccess(false);
							message.setMsg("放款确认成功，自动预约失败");
						}
					} else {
						// 还款中 生成还款计划 标记循环贷
						if (returnPlanService.addForCreditApplication(creditapplicationId)) {
							// 放款确认
							groupLoanRegistDAO.updateGroupLoanRegist(groupLoanRegist);
							/******** 日志 ********/
							OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
							operateLog.setCreditapplicationId(Long.valueOf(creditApplication.getCreditapplicationId()));
							operateLog.setFunctionCode("300040");
							operateLog.setResult("放款确认");
							operateLogService.insert(operateLog);
							// 还款中
							creditApplicationService.changeAuditing(creditApplication,
									groupLoanRegist.getCreditapplicationId(), Constants.STATE_15);
						} else {
							message.setSuccess(false);
							message.setMsg("生成还款计划失败");
						}

					}

				} else {
					System.out.println("状态:" + creditApplication.getAuditStatus());
					message.setMsg("申请单的状态 已经改变，不是“已放款登记”的状态，请刷新页面");
					message.setSuccess(false);
				}
			}
			// 放款回退
			if ("2".equals(registStatus)) {
				if (Constants.STATE_11.equals(creditApplication.getAuditStatus())) {
					if ("1".equals(creditApplication.getBusinessType())) {
						OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
						operateLog.setCreditapplicationId(Long.valueOf(creditApplication.getCreditapplicationId()));
						operateLog.setFunctionCode("30003*");
						operateLog.setResult("额度确认");
						operateLog.setRemark("放款登记回退操作，放款类型是个人的时候 状态改为 “额度确认”");
						operateLogService.insert(operateLog);
						creditApplicationService.changeAuditing(creditApplication,
								groupLoanRegist.getCreditapplicationId(), Constants.STATE_21);
					} else {
						OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
						operateLog.setCreditapplicationId(Long.valueOf(creditApplication.getCreditapplicationId()));
						operateLog.setFunctionCode("30003*");
						operateLog.setResult("款项到位");
						operateLog.setRemark("放款登记回退操作，放款类型是分公司的时候 状态改为 “款项到位”");
						operateLogService.insert(operateLog);
						creditApplicationService.changeAuditing(creditApplication,
								groupLoanRegist.getCreditapplicationId(), Constants.STATE_10);
					}
					groupLoanRegist.setLoanConfirmTime(new Date());
					groupLoanRegistDAO.updateGroupLoanRegist(groupLoanRegist);
				} else {
					System.out.println("状态:" + creditApplication.getAuditStatus());
					message.setMsg("申请单的状态 已经改变，不是“已放款登记”的状态，请刷新页面");
					message.setSuccess(false);
				}
			}
		} else {
			message.setMsg("放款登记的数据发生改变，请刷新页面后再做“放款确认”或“回退操作”操作");
			message.setSuccess(false);
		}
		return message;
	}

	@Override
	public List<GroupLoanRegist> searchHistory(GroupLoanRegist groupLoanRegist) {
		return groupLoanRegistDAO.searchHistory(groupLoanRegist);
	}

	@Override
	public List<GroupLoanRegist> searchConfirm(GroupLoanRegist groupLoanRegist) {
		return (List<GroupLoanRegist>) groupLoanRegistDAO.queryList("GROUPLOANREGIST.searchList", groupLoanRegist);
	}

	@Override
	public List<LoanRegist> selectLoanDetail(Integer creditApplicationId) {
		List<LoanRegist> loanRegistList = new ArrayList<LoanRegist>();
		loanRegistList = loanRegistService.selectLoanRegists(creditApplicationId);
		return loanRegistList;
	}

	@Override
	public List<LoanRegist> selectLoanRegist(Integer creditApplicationId) {
		GroupLoanRegist groupLoanRegist = new GroupLoanRegist();
		groupLoanRegist.setCreditapplicationId(creditApplicationId);
		groupLoanRegist.setRegistStatus("0");
		List<GroupLoanRegist> groupLoanRegistList = this.searchHistory(groupLoanRegist);
		List<LoanRegist> loanRegistList = new ArrayList<LoanRegist>();
		if (groupLoanRegistList.size() != 0) {
			loanRegistList = loanRegistService.selectLoanRegistList(groupLoanRegistList.get(0).getGroupLoanRegistId());
		}
		return loanRegistList;
	}

	@Override
	public GroupLoanRegist selectDengji(GroupLoanRegist groupLoanRegist) {
		return groupLoanRegistDAO.selectDengji(groupLoanRegist);
	}

	@Override
	public void changeToRegist(Integer creditapplicationId) {
		GroupLoanRegist groupLoanRegist = new GroupLoanRegist();
		groupLoanRegist.setCreditapplicationId(creditapplicationId);
		groupLoanRegist.setRegistStatus(Constants.LOAN_STATE_1);
		groupLoanRegist = groupLoanRegistDAO.selectDengji(groupLoanRegist);
		groupLoanRegist.setRegistStatus(Constants.LOAN_STATE_0);
		groupLoanRegistDAO.updateGroupLoanRegist(groupLoanRegist);
	}

	@Override
	public GroupLoanRegist selectRegist(GroupLoanRegist groupLoanRegist) {
		return (GroupLoanRegist) groupLoanRegistDAO.queryUnique("GROUPLOANREGIST.selectRegist", groupLoanRegist);
	}

	/**
	 * 根据业务单号查询放款登记数据
	 * liuli 2013-05-20
	 * 
	 * @param businessNum
	 * @return List<GroupLoanRegist>
	 */
	@Override
	public List<GroupLoanRegist> selectByBusinessNum(String businessNum) {
		List<GroupLoanRegist> l = new ArrayList<GroupLoanRegist>();
		l = groupLoanRegistDAO.selectByBusinessNum(businessNum);
		return l;
	}

	@Override
	public int changeRegistStatus(GroupLoanRegist groupLoanRegist) {
		int i = groupLoanRegistDAO.updateGroupLoanRegist(groupLoanRegist);
		return i;
	}

	// 放款登记查看
	@Override
	public Map selectForLook(Integer creditApplicationId) {
		Map map = new HashMap();
		CreditApplication application = creditApplicationService.selectById(creditApplicationId);
		List<Long> ModifyCatdAppIds = modifyCatdAppService.queryModifyCatdAppInSHENQINGZHONG(creditApplicationId);
		String getAuditStatus = application.getAuditStatus();
		String showButton = "0";
		if (Constants.STATE_15.equals(getAuditStatus) && CommonUtil.isEmpty(ModifyCatdAppIds)) {
			showButton = "1";
			map.put("applicationDENGJI", creditApplicationId);
		}

		GroupLoanRegist groupLoanRegist = new GroupLoanRegist();
		// 查询账户条件
		AccountInfo accountInfo = new AccountInfo();
		AccountInfoVo accountInfoVo = new AccountInfoVo();
		if (application.getAccountInfoId() != null) {
			accountInfo.setAccountInfoId(application.getAccountInfoId());
			// 付款账号
			List<AccountInfoVo> accountInfos = accountInDao.selectAccountAddress(accountInfo);
			if (accountInfos.size() > 0) {
				accountInfoVo = accountInfos.get(0);
			}
		}

		List<LoanRegist> loanRegists = new ArrayList<LoanRegist>();
		List<GroupLoanRegist> groupLoanRegists = new ArrayList<GroupLoanRegist>();
		// 如果状态是 已经 放款登记
		groupLoanRegist.setCreditapplicationId(application.getCreditapplicationId());
		if (Constants.STATE_11.equals(application.getAuditStatus())) {
			groupLoanRegist.setRegistStatus(Constants.LOAN_STATE_0);
			groupLoanRegist = this.selectRegist(groupLoanRegist);
		} else
		// 如果状态是 已经 放款确认
		{
			groupLoanRegist.setRegistStatus(Constants.LOAN_STATE_1);
			groupLoanRegist.setCreditapplicationId(application.getCreditapplicationId());
			groupLoanRegist = this.selectRegist(groupLoanRegist);
		}

		// 明细
		loanRegists = this.selectLoanDetail(application.getCreditapplicationId());
		LoanRegist loanRegist = new LoanRegist();
		if (CommonUtil.isNotEmpty(loanRegists)) {
			loanRegist = loanRegists.get(0);
		}
		// 备注
		groupLoanRegists = this.searchConfirm(groupLoanRegist);
		// 放款登记信息
		map.put("groupLoanRegist", groupLoanRegist);
		map.put("accountInfo", accountInfoVo);
		map.put("loanRegists", loanRegist);
		map.put("groupLoanRegists", groupLoanRegists);
		map.put("showButton", showButton);
		return map;
	}

	@Override
	public GroupLoanRegistVo searchForConfirm(GroupLoanRegist groupLoanRegist) {
		// TODO Auto-generated method stub
		List<GroupLoanRegistVo> groupLoanRegistVos = groupLoanRegistDAO.searchConfirm(groupLoanRegist);
		if (groupLoanRegistVos.size() > 0) {
			return groupLoanRegistVos.get(0);
		} else {
			return new GroupLoanRegistVo();
		}
	}
	
	@Override
	public boolean updateGroupLoanRegistConfirm(GroupLoanRegist groupLoadRegist){
		int rows=groupLoanRegistDAO.updateGroupLoanRegistConfirm(groupLoadRegist);
		return rows>0 ? true:false;
	}
	
	//重载方法 
	@Override
	public Message addOrUpdateGroupLoanRegist(GroupLoanRegist groupLoanRegist, CreditApplication creditApplication,
			String registStatus, List<LoanRegist> loanRegistList,String type) {
		Message message = new Message();
		if (null == groupLoanRegist.getCreditapplicationId()) {
			message.setSuccess(false);
			System.out.println("groupLoanRegist中CreditapplicationId属性:" + groupLoanRegist.getCreditapplicationId());
			message.setMsg("数据不完整 或者状态已经不是“等待合同复核”或者“等待重新合同复核的状态” 将为您刷新页面");
			return message;
		}
		creditApplication = creditApplicationService.selectById(groupLoanRegist.getCreditapplicationId());
		message.setSuccess(true);

		Integer creditapplicationId = groupLoanRegist.getCreditapplicationId();
		// 修改信贷申请状态 //修改个人申请状态
		creditApplication.setSignagreementDate(groupLoanRegist.getLoanTime());
		groupLoanRegist.setRegistStatus(registStatus);
		// 放款登记
		if ("0".equals(registStatus)) {
			creditApplicationService.changeAuditing(creditApplication, groupLoanRegist.getCreditapplicationId(),
					Constants.STATE_11);
			// 新增放款登记 //设置放款登记操作时间
			groupLoanRegist.setLoanRegistDate(new Date());
			int groupLoanRegistId = this.addGroupLoanRegist(groupLoanRegist);
			if (loanRegistList != null) {
				for (LoanRegist loanRegist : loanRegistList) {
					loanRegist.setGroupLoanRegistId(groupLoanRegistId);
				}
				loanRegistService.addBatchLoanRegist(loanRegistList);
			}
		}
		// 查询该数据当前 状态（放款登记？放款回退？ 放款登记的状态下 执行确认操作）
		GroupLoanRegist groupLoanRegistNew = groupLoanRegistDAO.selectById(groupLoanRegist.getGroupLoanRegistId());
		if (Constants.LOAN_STATE_0.equals(groupLoanRegistNew.getRegistStatus())||
				Constants.LOAN_STATE_2.equals(groupLoanRegistNew.getRegistStatus())||
				Constants.LOAN_STATE_1.equals(groupLoanRegistNew.getRegistStatus())) {
			// 放款确认
			if ("1".equals(registStatus)) {
				if (Constants.STATE_35.equals(creditApplication.getAuditStatus())||Constants.STATE_37.equals(creditApplication.getAuditStatus())) {
					// 放款确认时间
					groupLoanRegist.setLoanConfirmTime(new Date());

					//判断放款渠道是信托
					if(type=="xintuo"){
						//调用机构资产的  合同同步接口
						BorrowContractRes contractRes=orgamsService.synBorrowContract(Long.valueOf(groupLoanRegist.getCreditapplicationId()));
						if(contractRes.isSuccess()){
							// 放款确认
							groupLoanRegistDAO.updateGroupLoanRegist(groupLoanRegist);
							/******** 日志 ********/
							OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
							operateLog.setCreditapplicationId(Long.valueOf(creditApplication.getCreditapplicationId()));
							operateLog.setFunctionCode("300040");
							operateLog.setResult("放款确认");
							operateLogService.insert(operateLog);
							// 等待信托复核
							creditApplicationService.changeAuditing(creditApplication,
							groupLoanRegist.getCreditapplicationId(), Constants.STATE_36);
						}
					}else{
					
					// 如果是个人账户 调财务预约接口
					if ("1".equals(creditApplication.getBusinessType())) {
						// 放款确认
						groupLoanRegistDAO.updateGroupLoanRegist(groupLoanRegist);
						/******** 日志 ********/
						OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
						operateLog.setCreditapplicationId(Long.valueOf(creditApplication.getCreditapplicationId()));
						operateLog.setFunctionCode("300040");
						operateLog.setResult("放款确认");
						operateLogService.insert(operateLog);
						// 放款确认
						creditApplicationService.changeAuditing(creditApplication,
								groupLoanRegist.getCreditapplicationId(), Constants.STATE_38);
						//流程改造     不调用了 调财务预约接口/*financePaymentService.financialpayment(creditapplicationId, null).isSuccess()*/
						//调用贷后的同步合同的接口
						if (!rural2CreditService.clientApply(Long.valueOf(creditapplicationId)).isSuccess()) {
							message.setSuccess(false);
							//message.setMsg("放款确认成功，自动预约失败");  原来的
							message.setMsg("贷后已经存在了此笔数据了！");  
							return message;
						} 
					} else {
						// 还款中 生成还款计划 标记循环贷
						if (returnPlanService.addForCreditApplication(creditapplicationId)) {
							// 放款确认
							groupLoanRegistDAO.updateGroupLoanRegist(groupLoanRegist);
							/******** 日志 ********/
							OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
							operateLog.setCreditapplicationId(Long.valueOf(creditApplication.getCreditapplicationId()));
							operateLog.setFunctionCode("300040");
							operateLog.setResult("放款确认");
							operateLogService.insert(operateLog);
							// 还款中
							creditApplicationService.changeAuditing(creditApplication,
									groupLoanRegist.getCreditapplicationId(), Constants.STATE_15);
						} else {
							message.setSuccess(false);
							message.setMsg("生成还款计划失败");
						}

					}
				 }
				} else {
					System.out.println("状态:" + creditApplication.getAuditStatus());
					message.setMsg("申请单的状态 已经改变，不是“等待合同复核”或者“等待重新合同复核的状态”，请刷新页面");
					message.setSuccess(false);
				}
			}
			// 放款 回退，补充资料，退回重签
			if ("2".equals(registStatus)||"3".equals(registStatus)||"4".equals(registStatus)) {
				if (Constants.STATE_35.equals(creditApplication.getAuditStatus())||Constants.STATE_37.equals(creditApplication.getAuditStatus())) {
					if ("1".equals(creditApplication.getBusinessType())) {
						if("4".equals(registStatus)){
							//退回重签  撤销撮合
							message=contractAndLoanService.revokeMatchmaking(creditApplication);
							if(!message.isSuccess()){
								return message;
							}
							groupLoanRegist.setRegistStatus("2");
							OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
							operateLog.setCreditapplicationId(Long.valueOf(creditApplication.getCreditapplicationId()));
							operateLog.setFunctionCode("30003*");
							operateLog.setResult("放款确认");
							operateLog.setRemark("放款确认退回重签操作，放款类型是个人的时候 状态改为 “等待重新打印合同”");
							operateLogService.insert(operateLog);
							creditApplicationService.changeAuditing(creditApplication,
									groupLoanRegist.getCreditapplicationId(), Constants.STATE_34);
						}
						if("3".equals(registStatus)){
							groupLoanRegist.setRegistStatus("2");
							OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
							operateLog.setCreditapplicationId(Long.valueOf(creditApplication.getCreditapplicationId()));
							operateLog.setFunctionCode("30003*");
							operateLog.setResult("放款确认");
							operateLog.setRemark("放款确认补充资料操作，放款类型是个人的时候 状态改为 “等待上传合同”");
							operateLogService.insert(operateLog);
							creditApplicationService.changeAuditing(creditApplication,
									groupLoanRegist.getCreditapplicationId(), Constants.STATE_33);
						}
						if("2".equals(registStatus)){
						OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
						operateLog.setCreditapplicationId(Long.valueOf(creditApplication.getCreditapplicationId()));
						operateLog.setFunctionCode("30003*");
						operateLog.setResult("放款确认");
						operateLog.setRemark("放款确认回退操作，放款类型是个人的时候 状态改为 “等待上传合同”");
						operateLogService.insert(operateLog);
						creditApplicationService.changeAuditing(creditApplication,
								groupLoanRegist.getCreditapplicationId(), Constants.STATE_33);
						}
					} else {
						OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
						operateLog.setCreditapplicationId(Long.valueOf(creditApplication.getCreditapplicationId()));
						operateLog.setFunctionCode("30003*");
						operateLog.setResult("款项到位");
						operateLog.setRemark("放款登记回退操作，放款类型是分公司的时候 状态改为 “款项到位”");
						operateLogService.insert(operateLog);
						creditApplicationService.changeAuditing(creditApplication,
								groupLoanRegist.getCreditapplicationId(), Constants.STATE_10);
					}
					groupLoanRegist.setLoanConfirmTime(new Date());
					groupLoanRegist.setHistoryFlag("T");
					groupLoanRegistDAO.updateGroupLoanRegist(groupLoanRegist);
				} else {
					System.out.println("状态:" + creditApplication.getAuditStatus());
					message.setMsg("申请单的状态 已经改变，不是“等待合同复核”或者“等待重新合同复核”的状态，请刷新页面");
					message.setSuccess(false);
				}
			}
		} else {
			message.setMsg("放款确认的数据发生改变，请刷新页面后再做“放款确认”或“回退操作”操作");
			message.setSuccess(false);
		}
		return message;
	}

	@Override
	public GroupLoanRegist searchByCreditApplicationId(GroupLoanRegist groupLoadRegist) {
		return groupLoanRegistDAO.searchByCreditApplicationId(groupLoadRegist);
	}

	@Override
	public GroupLoanRegist searchForGroupLoanRegist(Long creditapplicationId) {
		// TODO Auto-generated method stub
		return groupLoanRegistDAO.searchForGroupLoanRegist(creditapplicationId);
	}

	@Override
	public GroupLoanRegist searchForGroupLoanRegistForGroupLoanRegist(
			Long creditapplicationId) {
		// TODO Auto-generated method stub
		return groupLoanRegistDAO.searchForGroupLoanRegistForGroupLoanRegist(creditapplicationId);
	}
}
