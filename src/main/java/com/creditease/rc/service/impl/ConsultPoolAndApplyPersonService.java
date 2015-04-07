package com.creditease.rc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.core.security.Authorization;
import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.security.User;
import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.INationalStandardCodeDAO;
import com.creditease.rc.domain.Application;
import com.creditease.rc.domain.CustomerConsultPool;
import com.creditease.rc.domain.NationalStandardCode;
import com.creditease.rc.service.IConsultPoolAndApplyPersonService;
import com.creditease.rc.service.ICustomerConsultPoolService;
import com.creditease.rc.service.ICustomerConsultService;
import com.creditease.rc.service.IOperateLogService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.Environment;
import com.creditease.rc.vo.ApplicationInfo;
import com.creditease.rc.vo.FamilyInfo;
import com.creditease.rc.vo.MobileInfo;
import com.creditease.rc.vo.NewApplicationInfo;
import com.creditease.rc.vo.NewCustomerConsult;
import com.creditease.rc.vo.PartnerInfo;
import com.creditease.rc.vo.PersonArrested;

@Service
public class ConsultPoolAndApplyPersonService implements IConsultPoolAndApplyPersonService {

// @Resource
// private ApplicationDAO applicationDAO;
//
// @Resource
// private ApplicationInfoDAO applicationInfoDAO;
//
// @Resource
// private PartnerInfoDAO partnerInfoDAO;
//
// @Resource
// private familyInfoDAO familyInfoDAO;
//
// @Resource
// private PersonArrestedDAO personArrestedDAO;
//
// @Resource
// private IValidateService validateService;
//
// @Resource
// private IMobileInfoService iMobileInfoService;

	@Resource
	private ICustomerConsultService customerConsultService;

	@Resource
	private IOperateLogService operateLogService;

	@Resource
	private INationalStandardCodeDAO nationalStandardDAO;

	@Resource
	private ICustomerConsultPoolService customerConsultPoolService;

	@Override
	public void insert(Application hisApp, NewApplicationInfo hisAppInfo,
			List<NewApplicationInfo> hisApplicationInfoList, List<MobileInfo> hisMobileInfoList,
			PartnerInfo hisPartnerInfo, List<FamilyInfo> hisFamilyInfoList, List<PersonArrested> arresteds,
			NewCustomerConsult customerConsult, CustomerConsultPool customerConsultPool) {

		// 将之前的所有申请基本信息更新为历史标识（包括主借款人和共同借款人）
		List<ApplicationInfo> newApplicationInfoList = new ArrayList<ApplicationInfo>();
		if (hisApplicationInfoList != null && hisApplicationInfoList.size() > 0) {
			for (NewApplicationInfo appInfo : hisApplicationInfoList) {
				ApplicationInfo appInfoHis = new ApplicationInfo();
				appInfoHis.setApplicationId(appInfo.getApplicationId());
				appInfoHis.setHistoryflag((short) 1);
				newApplicationInfoList.add(appInfoHis);
			}

			// applicationInfoDAO.updateAllByHistoryAppId(newApplicationInfoList);
		}

		// 查询是否循环贷（false：0，true：1）
		Short isXD = 0;
		boolean flag = true;
		// boolean flag = validateService.isRevolvingLoan(hisAppInfo.getIdentityCard());
		if (flag) {
			isXD = 1;
		}
		// hisApp.setIsXd(isXD);

		// 验证是否为循环贷优惠
		String isXdPreferential = "0";// 默认0为不优惠
		if (flag) { // 如果是循环贷
			boolean rlpFlag = true;
			// boolean rlpFlag = validateService.isRevolvingLoanPreferential(hisAppInfo.getIdentityCard());
			if (rlpFlag) {
				isXdPreferential = "1";
			}
		}
		// hisApp.setIsXdPreferential(isXdPreferential);
		// 插入受理人员id及name
		User user = SpringSecurityUtils.getCurrentUser();
		if (null != user) {
			// hisApp.setProceedName(user.getName_zh());
			if (CommonUtil.isNotEmpty(user.getUserId())) {
				// hisApp.setProceedId(Long.valueOf(user.getUserId()));
			}
		}
		Long appid = 1l;
		// Long appid = applicationDAO.insert(hisApp);

		// 增加权限
		try {
			Environment env = Environment.getInstance();
			Authorization authorization = null;
			authorization = env.getAuthorizationService();

			authorization.createAuth(Application.class, appid.intValue(),
					Integer.parseInt(SpringSecurityUtils.getCurrentUser().getUserId()),
					Integer.parseInt(Constants.SYSTEM_SIGN));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		hisAppInfo.setApplicationId(null);
		hisAppInfo.setApplicationInfoId(null);
		hisAppInfo.setApplicationId(appid);

		// 将受理咨询表的固定电话，设置到申请人信息的固定电话字段
		hisAppInfo.setAppInfoTelephone(customerConsultPool.getFixedTelephone());

		// 将历史基本信息更新为历史的同时，可能把新增的这条记录也被更新掉了，所以在重新设置一下“历史标示”为“新增（0）”
		hisAppInfo.setHistoryflag((short) 0);
		// Long appInfoId = applicationInfoDAO.insert(hisAppInfo);
		Long appInfoId = 1l;
		// Long appInfoId = applicationInfoDAO.insertApplyPersonInfo(hisAppInfo);

		if (hisMobileInfoList != null && hisMobileInfoList.size() > 0) {
			for (MobileInfo mInfo : hisMobileInfoList) {
				mInfo.setApplicationId(appid);
			}
			// iMobileInfoService.saveTelephones(hisMobileInfoList);
		}

		if (hisPartnerInfo != null) {
			hisPartnerInfo.setApplicationInfoId(appInfoId);
			// partnerInfoDAO.insert(hisPartnerInfo);
		}

		if (hisFamilyInfoList != null && hisFamilyInfoList.size() > 0) {
			for (FamilyInfo fInfo : hisFamilyInfoList) {
				fInfo.setApplicationInfoId(appInfoId);
			}
			// familyInfoDAO.SaveAllFamilyInfos(hisFamilyInfoList);
		}

		if (arresteds != null && arresteds.size() > 0) {
			for (int i = 0; i < arresteds.size(); i++) {
				PersonArrested pArrested = arresteds.get(i);
				pArrested.setApplicationId(appid);
			}
			// personArrestedDAO.batchInsert(arresteds);
		}

		/******************** 更新咨询客户信息start ***************************************/
		customerConsult.setConsultDate(DateUtil.stringConvertDate(
				DateUtil.dateConvertString(customerConsultPool.getRegistDate()) + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));

		customerConsult.setCustomerName(hisAppInfo.getAppInfoName());
		String sex = "";
		if (hisAppInfo.getAppInfoSex().equals("男")) {
			sex = "1";
		} else if (hisAppInfo.getAppInfoSex().equals("女")) {
			sex = "0";
		}
		customerConsult.setConSex(sex);

		NationalStandardCode provinceStandard = nationalStandardDAO.selectByCode(Integer.valueOf(customerConsultPool
				.getProvince()));
		NationalStandardCode cityStandard = nationalStandardDAO.selectByCode(Integer.valueOf(customerConsultPool
				.getCity()));
		if (customerConsultPool.getArea() != null && !customerConsultPool.getArea().equals("")) {
			NationalStandardCode areaStandard = nationalStandardDAO.selectByCode(Integer.valueOf(customerConsultPool
					.getArea()));
			customerConsult.setConAddress(provinceStandard.getCityName() + cityStandard.getCityName()
					+ areaStandard.getCityName());
		} else {
			customerConsult.setConAddress(provinceStandard.getCityName() + cityStandard.getCityName());
		}
		customerConsult.setConTelephone(customerConsultPool.getMobilePhone());

		// 受理咨询“借款金额”由“万元”转换成咨询信息里的“元”
		customerConsult.setBorrowAmount(customerConsultPool.getBorrowAmount().multiply(new BigDecimal(10000)));
		customerConsult.setCustomerSource(customerConsultPool.getChannel());
		customerConsult.setCommunicateRecord(customerConsultPool.getMarketConsultRemark());
		customerConsult.setDepartmentId(customerConsultPool.getDepartmentId());
		customerConsult.setDepartmentName(customerConsultPool.getDepartmentName());
		customerConsult.setCityDepartmentId(customerConsultPool.getCityDepartmentId());
		customerConsult.setCityDepartmentName(customerConsultPool.getCityDepartmentName());
		customerConsult.setTeamDepartmentId(customerConsultPool.getDistributeDepartmentId());
		customerConsult.setTeamDepartmentName(customerConsultPool.getDistributeDepartmentName());
		customerConsult.setAreaDepartmentId(customerConsultPool.getAreaDepartmentId());
		customerConsult.setAreaDepartmentName(customerConsultPool.getAreaDepartmentName());
		customerConsult.setHistoryflag((short) 0);

		// 咨询次数
		Map p = new HashMap();
		p.put("conTelephone", customerConsult.getConTelephone());
		// customerConsult.setConsultCount((short) (customerConsultService.countHistoryByPhone(p) + 1));

		customerConsult.setIsSystem((short) 0);
		Date now = new Date();
		customerConsult.setCreateBy(Long.valueOf(user.getUserId()));
		customerConsult.setCreateTime(DateUtil.stringConvertDate(
				DateUtil.dateConvertString(now, "yyyy/MM/dd HH:mm:ss"), "yyyy/MM/dd HH:mm:ss"));
		customerConsult.setLastUpdateBy(Long.valueOf(user.getUserId()));
		customerConsult.setLastUpdateTime(DateUtil.stringConvertDate(
				DateUtil.dateConvertString(now, "yyyy/MM/dd HH:mm:ss"), "yyyy/MM/dd HH:mm:ss"));

		// 复制的客户咨询填写的联系方式已存在历史咨询时，自动合并咨询记录
		// customerConsultService.updateByExample(customerConsult);

		customerConsult.setCustomerStatus("2");
		// customerConsultService.updateByPrimaryKeySelective(customerConsult);

		// 更新受理咨询客户姓名，同时受理咨询状态更新为“已申请”
		customerConsultPool.setCustomerName(hisAppInfo.getAppInfoName());
		customerConsultPool.setConSex(sex);
		customerConsultPool.setCustomerConsultId(customerConsult.getCustomerConsultId());
		customerConsultPool.setAcceptConsultState("2");
		customerConsultPoolService.updateCustomerConsultPool(customerConsultPool, "apply");

		/********************* 更新咨询客户信息end **************************************/
		// 加日志
// CunsultLog cunsultLog = new CunsultLog();
// cunsultLog.setConnectionId(customerConsultPool.getConsultPoolId());
// cunsultLog.setOptModule("b00040");
// cunsultLog.setCurrApplicationStatus("1");
// cunsultLog.setNextApplicationStatus("2");
// cunsultLog.setConnectionCetegory("1");
// cunsultLog.setConnectionDictionarySection("acceptConsultStatus");
// Map contentMap = new HashMap();// 客户姓名、身份证号
// contentMap.put("客户姓名", hisAppInfo.getAppInfoName());
// contentMap.put("身份证号", hisAppInfo.getIdentityCard());
// operateLogService.insertOpt(cunsultLog, contentMap);
	}

	/**
	 * 根据身份证号，查询信贷申请表中客户的姓名
	 * 
	 * @param code
	 *            身份证号
	 * @return 客户姓名
	 * 
	 */
	@Override
	public ApplicationInfo getCustomerName(String code) {
		ApplicationInfo info = new ApplicationInfo();
		// ApplicationInfo info = applicationInfoDAO.getCustomerName(code);
		return info;
	}

	/**
	 * 根据身份证号，查询历史申请基本信息
	 */
	@Override
	public List<ApplicationInfo> getHistoryBasicInfoBycode(String code) {
		List<ApplicationInfo> infoList = new ArrayList<ApplicationInfo>();
		// List<ApplicationInfo> infoList = applicationInfoDAO.selectByIdentityCode(code);
		return infoList;
	}

	/**
	 * 根据身份证号，查询新历史申请基本信息
	 */
	@Override
	public List<NewApplicationInfo> getNewHistoryBasicInfoBycode(String code) {
		List<NewApplicationInfo> infoList = new ArrayList<NewApplicationInfo>();
		// List<NewApplicationInfo> infoList = applicationInfoDAO.selectNewByIdentityCode(code);
		return infoList;
	}

	/**
	 * 查询历史申请联系方式列表
	 * 
	 * @param map
	 *            信贷申请ID和所属类型
	 * @return List<mobileInfo> 联系方式列表
	 * 
	 */
	@Override
	public List<MobileInfo> getHistoryMobileInfo(Map map) {
		List<MobileInfo> mobileInfoList = new ArrayList<MobileInfo>();
		// List<MobileInfo> mobileInfoList = iMobileInfoService.selectByAppIDAndBelongType(map);
		return mobileInfoList;

	}

	/**
	 * 根据信贷申请基本信息id，查询历史配偶信息
	 */
	@Override
	public PartnerInfo getHistoryPartnerInfo(Long applicationInfoId) {
		PartnerInfo partnerInfo = new PartnerInfo();
		// PartnerInfo partnerInfo = partnerInfoDAO.selectByForeignKey(applicationInfoId);
		return partnerInfo;
	}

	/**
	 * 根据信贷申请基本信息id，查询历史其他家庭成员列表信息
	 */
	@Override
	public List<FamilyInfo> getHistoryFamilyInfoList(Long applicationInfoId) {
		List<FamilyInfo> familyInfoList = new ArrayList<FamilyInfo>();
		// List<FamilyInfo> familyInfoList = familyInfoDAO.selectAllByForeignKey(applicationInfoId);
		return familyInfoList;
	}

}
