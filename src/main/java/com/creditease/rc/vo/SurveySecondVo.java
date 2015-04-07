package com.creditease.rc.vo;

import java.io.Serializable;
import java.util.List;

import com.creditease.rc.domain.Borrowersurvey;
import com.creditease.rc.domain.Familydepositdebt;
import com.creditease.rc.domain.Familymember;
import com.creditease.rc.domain.Familyotherincome;
import com.creditease.rc.domain.Familytotalcost;
import com.creditease.rc.domain.Householdasserts;
import com.creditease.rc.domain.Surveybusinessinfo;

/**
 * 
 * @author haoqiang
 * 
 */
public class SurveySecondVo implements Serializable {

	private List<Familymember> familymembers;

	private List<Familyotherincome> familyotherincomes;

	private Surveybusinessinfo surveybusinessinfo;

	private List<Familytotalcost> familytotalcosts;

	private List<Familydepositdebt> familydepositdebts;

	private List<Householdasserts> householdassertss;

	private Borrowersurvey borrowersurvey;

	public List<Familymember> getFamilymembers() {
		return familymembers;
	}

	public void setFamilymembers(List<Familymember> familymembers) {
		this.familymembers = familymembers;
	}

	public List<Familyotherincome> getFamilyotherincomes() {
		return familyotherincomes;
	}

	public void setFamilyotherincomes(List<Familyotherincome> familyotherincomes) {
		this.familyotherincomes = familyotherincomes;
	}

	public Surveybusinessinfo getSurveybusinessinfo() {
		return surveybusinessinfo;
	}

	public void setSurveybusinessinfo(Surveybusinessinfo surveybusinessinfo) {
		this.surveybusinessinfo = surveybusinessinfo;
	}

	public List<Familytotalcost> getFamilytotalcosts() {
		return familytotalcosts;
	}

	public void setFamilytotalcosts(List<Familytotalcost> familytotalcosts) {
		this.familytotalcosts = familytotalcosts;
	}

	public List<Familydepositdebt> getFamilydepositdebts() {
		return familydepositdebts;
	}

	public void setFamilydepositdebts(List<Familydepositdebt> familydepositdebts) {
		this.familydepositdebts = familydepositdebts;
	}

	public List<Householdasserts> getHouseholdassertss() {
		return householdassertss;
	}

	public void setHouseholdassertss(List<Householdasserts> householdassertss) {
		this.householdassertss = householdassertss;
	}

	public Borrowersurvey getBorrowersurvey() {
		return borrowersurvey;
	}

	public void setBorrowersurvey(Borrowersurvey borrowersurvey) {
		this.borrowersurvey = borrowersurvey;
	}

}
