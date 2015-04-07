package com.creditease.rc.vo;

import java.util.List;

import com.creditease.rc.domain.Contact;
import com.creditease.rc.domain.JobInfo;
/**
 * 
 * @author zhangman
 *
 */
public class JobContentVo {

	private JobInfo jobInfo;
	
	private List<Contact> contacts;

	public JobInfo getJobInfo() {
		return jobInfo;
	}

	public void setJobInfo(JobInfo jobInfo) {
		this.jobInfo = jobInfo;
	}
	
	

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
}
