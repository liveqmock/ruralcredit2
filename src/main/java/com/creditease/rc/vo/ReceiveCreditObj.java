package com.creditease.rc.vo;

public class ReceiveCreditObj {
	private String applyId;// 32位的业务编号
	private String type;// 类型：‘0’还款中，‘1’正常还款完成，‘2’提前还款完成,
	private String time;// 如果类型为‘0’则传实际放款时间，如果类型为‘1’或‘2’则传结清时间

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
