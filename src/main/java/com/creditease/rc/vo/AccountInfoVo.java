package com.creditease.rc.vo;

import java.io.Serializable;

import com.creditease.rc.domain.AccountInfo;

/**
 * 账户vo类
 * @author zhangman
 *
 */
public class AccountInfoVo extends AccountInfo implements Serializable {
	private String payWayName;//支付方式
	private String sheng;
	private String shi;
	private String qu;
	

	public String getPayWayName() {
		return payWayName;
	}

	public void setPayWayName(String payWayName) {
		this.payWayName = payWayName;
	}

	public String getSheng() {
		return sheng;
	}

	public void setSheng(String sheng) {
		this.sheng = sheng;
	}

	public String getShi() {
		return shi;
	}

	public void setShi(String shi) {
		this.shi = shi;
	}

	public String getQu() {
		return qu;
	}

	public void setQu(String qu) {
		this.qu = qu;
	}
	
}
