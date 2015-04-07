package com.creditease.rc.service.impl;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.security.User;
import com.creditease.rc.dao.IModifyCatdAppDao;
import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.ModifyCatdApp;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IAccountInfoService;
import com.creditease.rc.service.IModifyCatdAppService;
import com.creditease.rc.service.IOperateLogService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.vo.OperateLogBusinessStruct;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ModifyCatdAppService implements IModifyCatdAppService {

	@Resource
	private IModifyCatdAppDao modifyCatdAppDao;

	@Resource
	private IAccountInfoService accountInfoService;

	@Resource
	private IOperateLogService operateLogService;

	@Override
	public Message saveModifyCatdApp(ModifyCatdApp modifyCatdApp) {
		// TODO Auto-generated method stub
		Message message = new Message();
		User user = SpringSecurityUtils.getCurrentUser();
		modifyCatdApp.setProposerId(Long.parseLong(user.getUserId()));
		modifyCatdApp.setProposerName(user.getName_zh());
		modifyCatdApp.setApplicationTime(new Date());
		modifyCatdApp.setStatus("0");
		boolean s = modifyCatdAppDao.saveModifyCatdApp(modifyCatdApp);

		if (s) {
			message.setSuccess(true);
			message.setMsg("提交申请成功！");
			OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
			operateLogBusinessStruct.setCreditapplicationId(modifyCatdApp.getCreditapplicationId());
			operateLogBusinessStruct.setFunctionCode("100050");
			operateLogBusinessStruct.setRemark("提交卡信息变更申请。申请人：" + modifyCatdApp.getProposerName());
			operateLogService.insert(operateLogBusinessStruct);
		} else {
			message.setSuccess(false);
			message.setMsg("提交申请失败！");
		}
		return message;
	}

	@Override
	public Pagination queryAccountInformationChangeList(Pagination pagination, Map<String, String> pramMap) {
		// TODO Auto-generated method stub
		return modifyCatdAppDao.queryAccountInformationChangeList(pagination, pramMap);
	}

	@Override
	public ModifyCatdApp queryModifyCatdAppByPrimarKey(Long modifyCatdAppId) {
		// TODO Auto-generated method stub
		return modifyCatdAppDao.queryModifyCatdAppByPrimarKey(modifyCatdAppId);
	}

	@Override
	public Message approvalModifyCatdApp(Long modifyCatdAppId, Long accountInfoId, String type, Long creditapplicationId) {
		// TODO Auto-generated method stub
		Message message = new Message();
		ModifyCatdApp modifyCatdApp = new ModifyCatdApp();
		if (CommonUtil.isNotEmpty(type)) {
			if ("1".equals(type)) {
				// type=1代表审批通过
				// 通过modifyCatdAppId 去查询要修改成为的卡信息
				modifyCatdApp = this.queryModifyCatdAppByPrimarKey(modifyCatdAppId);

				String branchName = modifyCatdApp.getBranchName();
				String bankPrefectureNum = modifyCatdApp.getBankPrefectureNum();
				String bankNum = modifyCatdApp.getBankNum();
				String bankRankName = modifyCatdApp.getBankRankName();
				String bankName = modifyCatdApp.getBankName();
				String accountName = modifyCatdApp.getAccountName();
				String account = modifyCatdApp.getAccount();
				String onUsed = modifyCatdApp.getOnUsed();
				String cardFlag = modifyCatdApp.getCardFlag();
				String mobilephone = modifyCatdApp.getMobilephone();
				String useType = modifyCatdApp.getUseType();
				String payWay = modifyCatdApp.getPayWay();
				String payBranchno = modifyCatdApp.getPayBranchno();
				String credentialsNumber = modifyCatdApp.getCredentialsNumber();
				Long provinceId = modifyCatdApp.getProvinceId();
				Long cityId = modifyCatdApp.getCityId();
				Long districtId = modifyCatdApp.getDistrictId();
				String accountType = modifyCatdApp.getAccountType();
				String companyId = modifyCatdApp.getCompanyId();
				String borrowerName = modifyCatdApp.getBorrowerName();
				String borrowerCredentialsNumber = modifyCatdApp.getBorrowerCredentialsNumber();
				String operaterName = modifyCatdApp.getOperaterName();
				String operaterId = modifyCatdApp.getOperaterId();
				String departmentId = modifyCatdApp.getDepartmentId();
				Long proposerId = modifyCatdApp.getProposerId();
				String proposerName = modifyCatdApp.getProposerName();
				Date applicationTime = modifyCatdApp.getApplicationTime();
				Long approvalId = modifyCatdApp.getApprovalId();
				String approvalName = modifyCatdApp.getApprovalName();
				Date examineTime = modifyCatdApp.getExamineTime();
				String status = modifyCatdApp.getStatus();

				// 将ModifyCatdApp 对象更改为 AccountInfo 对象
				AccountInfo accountInfo = new AccountInfo();
				accountInfo.setAccountInfoId(accountInfoId.intValue());
				accountInfo.setBranchName(branchName);
				accountInfo.setBankPrefectureNum(bankPrefectureNum);
				accountInfo.setBankNum(bankNum);
				accountInfo.setBankRank(bankRankName);
				accountInfo.setBankName(bankName);
				accountInfo.setAccountName(accountName);
				accountInfo.setAccount(account);
				accountInfo.setOnUsed(onUsed);
				accountInfo.setCardFlag(cardFlag);
				accountInfo.setMobilephone(mobilephone);
				accountInfo.setUseType(useType);
				accountInfo.setPayWay(payWay);
				accountInfo.setPayBranchno(payBranchno);
				accountInfo.setCredentialsNumber(credentialsNumber);
				accountInfo.setProvinceId(provinceId.intValue());
				accountInfo.setCityId(cityId.intValue());
				accountInfo.setDistrictId(districtId.intValue());
				accountInfo.setAccountType(accountType);
				accountInfo.setCompanyId(companyId);
				accountInfo.setBorrowerName(borrowerName);
				accountInfo.setBorrowerCredentialsNumber(borrowerCredentialsNumber);
				accountInfo.setDepartmentId(departmentId);

				// 更改卡信息
				accountInfoService.updateAccountInfo(accountInfo);
			}
			// 审批
			modifyCatdApp = new ModifyCatdApp();
			modifyCatdApp.setModifyCatdAppId(modifyCatdAppId);

			User user = SpringSecurityUtils.getCurrentUser();
			modifyCatdApp.setApprovalId(Long.parseLong(user.getUserId()));
			modifyCatdApp.setApprovalName(user.getName_zh());
			modifyCatdApp.setExamineTime(new Date());
			modifyCatdApp.setStatus(type);
			modifyCatdAppDao.updateModifyCatdApp(modifyCatdApp);

			message.setSuccess(true);
			message.setMsg("审批成功！");
			String jieguo = "";
			if ("1".equals(type)) {
				jieguo = "通过";
			} else if ("2".equals(type)) {
				jieguo = "拒绝";
			}
			OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
			operateLogBusinessStruct.setCreditapplicationId(creditapplicationId);
			operateLogBusinessStruct.setFunctionCode("100050");
			operateLogBusinessStruct.setRemark("提交卡信息变更审批。审批人：" + modifyCatdApp.getApprovalName() + ",审批结果：" + jieguo);
			operateLogService.insert(operateLogBusinessStruct);
		} else {
			message.setSuccess(false);
			message.setMsg("type为空！");
		}
		return message;
	}

	@Override
	public List<Long> queryModifyCatdAppInSHENQINGZHONG(Integer creditApplicationId) {
		// TODO Auto-generated method stub
		Long applicationId = creditApplicationId.longValue();
		return modifyCatdAppDao.queryModifyCatdAppInSHENQINGZHONG(applicationId);
	}

	@Override
	public Message approvalModifyCatdAppBasedPre(ModifyCatdApp app){
		Message message = this.approvalModifyCatdApp(app.getModifyCatdAppId(), app.getAccountInfoId(), app.getStatus(), app.getCreditapplicationId());
		ModifyCatdApp modifyCatdApp = new ModifyCatdApp();
		modifyCatdApp.setModifyCatdAppId(app.getModifyCatdAppId());
		modifyCatdApp.setRefusecause(app.getRefusecause());
		this.modifyCatdAppDao.updateModifyCatdApp(modifyCatdApp);
		return message;
	}
}
