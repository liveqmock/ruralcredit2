package com.creditease.rc.app.sia.vo;

import com.creditease.rc.app.sia.util.CMQMessageRequest;
import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.AmountConfirm;
import com.creditease.rc.domain.GroupLoanRegist;
import com.creditease.rc.domain.ProtocolMapping;
import com.creditease.rc.vo.AccountInfoVo2ICP;

/**
 * Created by Administrator on 2014-11-25.
 */
public class ICPStatusSynchronizationVo extends CMQMessageRequest {

    private String applicationId;//申请编号
    private String currentApplicationStatus;//操作前状态码
    private String nextApplicationStatus;//操作后状态码
    private String operator;//操作人
    private String operatorId;//操作人ID
    private String operatorDate;//操作时间
    private String remark;//备注
    private AccountInfoVo2ICP accountInfo; //公司/个人财务账号表
    private AmountConfirm amountConfirm;//金额确认
    private ProtocolMapping protocolMapping;//合同编号表与小组关系表
    private GroupLoanRegist groupLoanRegist;//放款登记表
    
    

    public GroupLoanRegist getGroupLoanRegist() {
		return groupLoanRegist;
	}

	public void setGroupLoanRegist(GroupLoanRegist groupLoanRegist) {
		this.groupLoanRegist = groupLoanRegist;
	}

	public ProtocolMapping getProtocolMapping() {
		return protocolMapping;
	}

	public void setProtocolMapping(ProtocolMapping protocolMapping) {
		this.protocolMapping = protocolMapping;
	}

	public AmountConfirm getAmountConfirm() {
		return amountConfirm;
	}

	public void setAmountConfirm(AmountConfirm amountConfirm) {
		this.amountConfirm = amountConfirm;
	}

	

	

	public AccountInfoVo2ICP getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(AccountInfoVo2ICP accountInfo) {
		this.accountInfo = accountInfo;
	}

	public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getCurrentApplicationStatus() {
        return currentApplicationStatus;
    }

    public void setCurrentApplicationStatus(String currentApplicationStatus) {
        this.currentApplicationStatus = currentApplicationStatus;
    }

    public String getNextApplicationStatus() {
        return nextApplicationStatus;
    }

    public void setNextApplicationStatus(String nextApplicationStatus) {
        this.nextApplicationStatus = nextApplicationStatus;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatorDate() {
        return operatorDate;
    }

    public void setOperatorDate(String operatorDate) {
        this.operatorDate = operatorDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorId() {
        return operatorId;
    }


}
