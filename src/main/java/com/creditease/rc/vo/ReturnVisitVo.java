package com.creditease.rc.vo;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.creditease.rc.common.JsonYMDDateSerializer;
import com.creditease.rc.domain.ReturnVisit;
/**
 * 
 * @author zhangman
 * Company: 普信恒业科技发展（北京）有限公司
 */
public class ReturnVisitVo extends ReturnVisit {
		private String contactChangeDetails;//联系方式变更详细
	    private String addressChangeDetails;//家庭住址变更详细
	    private String workingChangeDetails;//工作情况变更详细
	    private String monthIncomeDetails;//月收入变更详细
	    private String vastexpenseReasonDetails;//大笔开支详细
	    private String paypressureReasonDetails;//偿还压力原因详细
	    private String familymembersChangeDetails;//家庭成员变更详细
	    private String otherFamilystatusDetails;//担保人员家庭状况详细
	    private String otherWorkingDetails;//担保人员工作有无变化详细
	    private String otherVastexpenseDetails;//担保人员大笔开支详细
	    private String otherPaypressureDetails;//担保人员偿还压力原因详细
	    private String keytoFollowupDetails;//重点跟进详细
	    private Date beginContactTime;		//咨寻开始
	    private Date endContactTime;		//咨询结束


		public String getContactChangeDetails() {
			return contactChangeDetails;
		}

		public void setContactChangeDetails(String contactChangeDetails) {
			this.contactChangeDetails = contactChangeDetails;
		}

		public String getAddressChangeDetails() {
			return addressChangeDetails;
		}

		public void setAddressChangeDetails(String addressChangeDetails) {
			this.addressChangeDetails = addressChangeDetails;
		}


		public String getWorkingChangeDetails() {
			return workingChangeDetails;
		}


		public void setWorkingChangeDetails(String workingChangeDetails) {
			this.workingChangeDetails = workingChangeDetails;
		}


		public String getMonthIncomeDetails() {
			return monthIncomeDetails;
		}


		public void setMonthIncomeDetails(String monthIncomeDetails) {
			this.monthIncomeDetails = monthIncomeDetails;
		}


		public String getVastexpenseReasonDetails() {
			return vastexpenseReasonDetails;
		}


		public void setVastexpenseReasonDetails(String vastexpenseReasonDetails) {
			this.vastexpenseReasonDetails = vastexpenseReasonDetails;
		}


		public String getPaypressureReasonDetails() {
			return paypressureReasonDetails;
		}


		public void setPaypressureReasonDetails(String paypressureReasonDetails) {
			this.paypressureReasonDetails = paypressureReasonDetails;
		}


		public String getFamilymembersChangeDetails() {
			return familymembersChangeDetails;
		}

		public void setFamilymembersChangeDetails(String familymembersChangeDetails) {
			this.familymembersChangeDetails = familymembersChangeDetails;
		}

		public String getOtherFamilystatusDetails() {
			return otherFamilystatusDetails;
		}


		public void setOtherFamilystatusDetails(String otherFamilystatusDetails) {
			this.otherFamilystatusDetails = otherFamilystatusDetails;
		}


		public String getOtherWorkingDetails() {
			return otherWorkingDetails;
		}


		public void setOtherWorkingDetails(String otherWorkingDetails) {
			this.otherWorkingDetails = otherWorkingDetails;
		}


		public String getOtherVastexpenseDetails() {
			return otherVastexpenseDetails;
		}


		public void setOtherVastexpenseDetails(String otherVastexpenseDetails) {
			this.otherVastexpenseDetails = otherVastexpenseDetails;
		}


		public String getOtherPaypressureDetails() {
			return otherPaypressureDetails;
		}


		public void setOtherPaypressureDetails(String otherPaypressureDetails) {
			this.otherPaypressureDetails = otherPaypressureDetails;
		}


		public String getKeytoFollowupDetails() {
			return keytoFollowupDetails;
		}

		public void setKeytoFollowupDetails(String keytoFollowupDetails) {
			this.keytoFollowupDetails = keytoFollowupDetails;
		}

		@JsonSerialize(using = JsonYMDDateSerializer.class)
		public Date getBeginContactTime() {
			return beginContactTime;
		}

		public void setBeginContactTime(Date beginContactTime) {
			this.beginContactTime = beginContactTime;
		}

		@JsonSerialize(using = JsonYMDDateSerializer.class)
		public Date getEndContactTime() {
			return endContactTime;
		}

		public void setEndContactTime(Date endContactTime) {
			this.endContactTime = endContactTime;
		}
}
