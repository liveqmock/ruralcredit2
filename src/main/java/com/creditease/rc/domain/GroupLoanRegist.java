package com.creditease.rc.domain;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;
import com.creditease.rc.common.JsonYMDHMDDateSerializer;
import com.creditease.rc.common.JsonYMDHmsDateSerializer;


/**
 * 放款登记类
 * @author zhangman
 *
 */

public class GroupLoanRegist  implements java.io.Serializable {

		private Integer groupLoanRegistId;	//		小组放款登记id
		private	Integer	creditapplicationId;//		信贷申请单id
		private String groupNumber;		//			小组编号
		private String loanOfficerId;	//			信贷员id
		private String loanOfficerName;	//			信贷员姓名
		private Date loanTime;			//			放款日期(要求放款时间)
		private String protocolFile;	//			协议文件
		private String loanPerson;		//			放款确认人
		private Date loanConfirmTime;	//			放款确认日期
	 	private String remarkComment;	//			备注和意见
	 	private Date loanRegistDate;	//			放款登记日期（上传合同时间）
	 	private String registStatus;	//			状态:0 登记、1 确认、2回退
	 	private String protocolID;		//			协议编号
	 	private Double loanAmount;		//			放款金额
	 	private Double realAmount;		//			实发金额（放款金额-服务费，向财务预约的金额）
	 	private Date contractSignedTime;//			合同签订时间
	 	private String historyFlag;		//			历史标识（T历史记录，F非历史记录）
	 	
	   /**
	    * 构造方法
	    */

    public GroupLoanRegist() {
    }

    
	public String getHistoryFlag() {
		return historyFlag;
	}


	public void setHistoryFlag(String historyFlag) {
		this.historyFlag = historyFlag;
	}

	@JsonSerialize(using = JsonYMDHMDDateSerializer.class)
    public Date getContractSignedTime() {
		return contractSignedTime;
	}

	public void setContractSignedTime(Date contractSignedTime) {
		this.contractSignedTime = contractSignedTime;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
    public Date getLoanTime() {
        return this.loanTime;
    }
    
    public void setLoanTime(Date loanTime) {
        this.loanTime = loanTime;
    }

    public String getProtocolFile() {
        return this.protocolFile;
    }
    
    public void setProtocolFile(String protocolFile) {
        this.protocolFile = protocolFile;
    }

    public String getLoanPerson() {
        return this.loanPerson;
    }
    
    public void setLoanPerson(String loanPerson) {
        this.loanPerson = loanPerson;
    }

    @JsonSerialize(using = JsonYMDHmsDateSerializer.class)
    public Date getLoanConfirmTime() {
        return this.loanConfirmTime;
    }
    
    public void setLoanConfirmTime(Date loanConfirmTime) {
        this.loanConfirmTime = loanConfirmTime;
    }

    public String getRemarkComment() {
        return this.remarkComment;
    }
    
    public void setRemarkComment(String remarkComment) {
        this.remarkComment = remarkComment;
    }

	public String getLoanOfficerId() {
		return loanOfficerId;
	}


	public void setLoanOfficerId(String loanOfficerId) {
		this.loanOfficerId = loanOfficerId;
	}

	public String getLoanOfficerName() {
		return loanOfficerName;
	}

	public void setLoanOfficerName(String loanOfficerName) {
		this.loanOfficerName = loanOfficerName;
	}

	@JsonSerialize(using = JsonYMDDateSerializer.class)
	public Date getLoanRegistDate() {
		return loanRegistDate;
	}

	public void setLoanRegistDate(Date loanRegistDate) {
		this.loanRegistDate = loanRegistDate;
	}

	public String getRegistStatus() {
		return registStatus;
	}

	public void setRegistStatus(String registStatus) {
		this.registStatus = registStatus;
	}

	public String getProtocolID() {
		return protocolID;
	}

	public void setProtocolID(String protocolID) {
		this.protocolID = protocolID;
	}


	public Integer getCreditapplicationId() {
		return creditapplicationId;
	}


	public void setCreditapplicationId(Integer creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}


	public String getGroupNumber() {
		return groupNumber;
	}


	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}



	public Integer getGroupLoanRegistId() {
		return groupLoanRegistId;
	}



	public void setGroupLoanRegistId(Integer groupLoanRegistId) {
		this.groupLoanRegistId = groupLoanRegistId;
	}


	public Double getLoanAmount() {
		return loanAmount;
	}


	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}


	public Double getRealAmount() {
		return realAmount;
	}


	public void setRealAmount(Double realAmount) {
		this.realAmount = realAmount;
	}

}