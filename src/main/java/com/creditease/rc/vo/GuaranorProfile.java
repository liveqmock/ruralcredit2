package com.creditease.rc.vo;

import java.util.List;

import com.creditease.rc.domain.BorrowerService;
import com.creditease.rc.domain.BorrowerServiceApp;
import com.creditease.rc.domain.Familymember;
import com.creditease.rc.domain.JobInfo;
import com.creditease.rc.domain.OtherQuestions;
import com.creditease.rc.domain.Surveybusinessinfo;
/**
 * 
 * @author haoqiang
 *
 */
public class GuaranorProfile {

	private BorrowerService borrowerService;

	private List<Surveybusinessinfo> surveybusinessinfos;

	private List<JobInfo> jobInfos;

	private OtherQuestions otherQuestions;

	private List<Familymember> familymembers;
	
	private Familymember spouseInfo;   //配偶信息

	public BorrowerService getBorrowerService() {
		return borrowerService;
	}

	public void setBorrowerService(BorrowerService borrowerService) {
		this.borrowerService = borrowerService;
	}

	public List<Familymember> getFamilymembers() {
		return familymembers;
	}

	public void setFamilymembers(List<Familymember> familymembers) {
		this.familymembers = familymembers;
	}

	public OtherQuestions getOtherQuestions() {
		return otherQuestions;
	}

	public void setOtherQuestions(OtherQuestions otherQuestions) {
		this.otherQuestions = otherQuestions;
	}

	public List<JobInfo> getJobInfos() {
		return jobInfos;
	}

	public void setJobInfos(List<JobInfo> jobInfos) {
		this.jobInfos = jobInfos;
	}

	public List<Surveybusinessinfo> getSurveybusinessinfos() {
		return surveybusinessinfos;
	}

	public void setSurveybusinessinfos(List<Surveybusinessinfo> surveybusinessinfos) {
		this.surveybusinessinfos = surveybusinessinfos;
	}

	public Familymember getSpouseInfo() {
		return spouseInfo;
	}

	public void setSpouseInfo(Familymember spouseInfo) {
		this.spouseInfo = spouseInfo;
	}

}
