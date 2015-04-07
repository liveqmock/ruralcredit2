package com.creditease.rc.vo;

import com.creditease.rc.domain.BorrowerServiceApp;
/**
 * 
 * @author zhangman
 *
 */
public class BorrowerServiceAppVo {

	private BorrowerServiceApp borrowerServiceApp;
	private Double chgMount;

	public Double getChgMount() {
		return chgMount;
	}

	public void setChgMount(Double chgMount) {
		this.chgMount = chgMount;
	}

	public BorrowerServiceApp getBorrowerServiceApp() {
		return borrowerServiceApp;
	}

	public void setBorrowerServiceApp(BorrowerServiceApp borrowerServiceApp) {
		this.borrowerServiceApp = borrowerServiceApp;
	}
}
