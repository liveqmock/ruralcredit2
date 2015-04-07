package com.creditease.rc.vo;

import java.util.List;

import com.creditease.rc.domain.CreditCardInfo;
import com.creditease.rc.domain.CreditOrganization;
/**
 * 
 * @author zhangman
 *
 */
public class CreditCardInfoVo {

	private CreditCardInfo creditCardInfo;
	
	private List<CreditOrganization> creditOrganization;

	public CreditCardInfo getCreditCardInfo() {
		return creditCardInfo;
	}

	public void setCreditCardInfo(CreditCardInfo creditCardInfo) {
		this.creditCardInfo = creditCardInfo;
	}

	public List<CreditOrganization> getCreditOrganization() {
		return creditOrganization;
	}

	public void setCreditOrganization(List<CreditOrganization> creditOrganization) {
		this.creditOrganization = creditOrganization;
	}

}
