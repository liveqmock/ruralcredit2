package com.creditease.rc.vo;

import java.util.Date;
import java.util.List;

import com.creditease.rc.domain.BorrowInfo;
import com.creditease.rc.domain.BorrowerServiceApp;
import com.creditease.rc.domain.Contact;
import com.creditease.rc.domain.CreditCardInfo;
import com.creditease.rc.domain.CreditOrganization;
import com.creditease.rc.domain.JobInfo;
/**
 * 
 * @author zhangman
 *
 */
public class ApplicationTableVo1 {

	private BorrowerServiceApp borrowerServiceApp;
	private List<BorrowInfo> borrowInfos;
	private CreditCardInfo creditCardInfo;
	private List<Contact> contacts;
	private JobInfo jobInfo;
	private List<CreditOrganization> creditOrganization;

	public BorrowerServiceApp getBorrowerServiceApp() {
		return borrowerServiceApp;
	}

	public void setBorrowerServiceApp(BorrowerServiceApp borrowerServiceApp) {
		this.borrowerServiceApp = borrowerServiceApp;
	}

	public CreditCardInfo getCreditCardInfo() {
		return creditCardInfo;
	}

	public void setCreditCardInfo(CreditCardInfo creditCardInfo) {
		this.creditCardInfo = creditCardInfo;
	}

	public JobInfo getJobInfo() {
		return jobInfo;
	}

	public void setJobInfo(JobInfo jobInfo) {
		this.jobInfo = jobInfo;
	}

	public List<BorrowInfo> getBorrowInfos() {
		return borrowInfos;
	}

	public void setBorrowInfos(List<BorrowInfo> borrowInfos) {
		this.borrowInfos = borrowInfos;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<CreditOrganization> getCreditOrganization() {
		return creditOrganization;
	}

	public void setCreditOrganization(
			List<CreditOrganization> creditOrganization) {
		this.creditOrganization = creditOrganization;
	}

}
