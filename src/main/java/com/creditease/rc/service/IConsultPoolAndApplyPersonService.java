package com.creditease.rc.service;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.Application;
import com.creditease.rc.domain.CustomerConsultPool;
import com.creditease.rc.vo.ApplicationInfo;
import com.creditease.rc.vo.FamilyInfo;
import com.creditease.rc.vo.MobileInfo;
import com.creditease.rc.vo.NewApplicationInfo;
import com.creditease.rc.vo.NewCustomerConsult;
import com.creditease.rc.vo.PartnerInfo;
import com.creditease.rc.vo.PersonArrested;

public interface IConsultPoolAndApplyPersonService {
	public void insert(Application hisApp, NewApplicationInfo hisAppInfo,
			List<NewApplicationInfo> hisApplicationInfoList, List<MobileInfo> hisMobileInfoList,
			PartnerInfo hisPartnerInfo, List<FamilyInfo> hisFamilyInfoList, List<PersonArrested> arresteds,
			NewCustomerConsult customerConsult, CustomerConsultPool customerConsultPool);

	public ApplicationInfo getCustomerName(String code);

	public List<ApplicationInfo> getHistoryBasicInfoBycode(String code);

	public List<NewApplicationInfo> getNewHistoryBasicInfoBycode(String code);

	public List<MobileInfo> getHistoryMobileInfo(Map map);

	public PartnerInfo getHistoryPartnerInfo(Long applicationInfoId);

	public List<FamilyInfo> getHistoryFamilyInfoList(Long applicationInfoId);
}
