package com.creditease.rc.domain;

public class ClientApplyHistory {

	private Long clientApplyHistoryId; // 贷后进件历史记录表主键
	private Long creditapplicationId; // 本地信贷申请单ID
	private String sysGuid; // 贷后进件ID
	private String isSuccess; // 调用接口结果：0代表成功，1代表失败
	private String message; // 接口返回的消息

	public Long getClientApplyHistoryId() {
		return clientApplyHistoryId;
	}

	public void setClientApplyHistoryId(Long clientApplyHistoryId) {
		this.clientApplyHistoryId = clientApplyHistoryId;
	}

	public Long getCreditapplicationId() {
		return creditapplicationId;
	}

	public void setCreditapplicationId(Long creditapplicationId) {
		this.creditapplicationId = creditapplicationId;
	}

	public String getSysGuid() {
		return sysGuid;
	}

	public void setSysGuid(String sysGuid) {
		this.sysGuid = sysGuid;
	}

	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
