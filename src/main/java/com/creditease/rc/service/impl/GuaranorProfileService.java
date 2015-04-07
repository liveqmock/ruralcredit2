package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.IFamilymemberDao;
import com.creditease.rc.dao.IGuaranorProfileDao;
import com.creditease.rc.dao.IJobInfoDAO;
import com.creditease.rc.dao.IOtherQuestionsDao;
import com.creditease.rc.dao.ISurveybusinessinfoDao;
import com.creditease.rc.domain.BorrowerService;
import com.creditease.rc.domain.BorrowerServiceApp;
import com.creditease.rc.domain.Familymember;
import com.creditease.rc.domain.JobInfo;
import com.creditease.rc.domain.OtherQuestions;
import com.creditease.rc.domain.Surveybusinessinfo;
import com.creditease.rc.service.IBorrowerServiceAppService;
import com.creditease.rc.service.IGuaranorProfileService;
import com.creditease.rc.service.IOperateLogService;
import com.creditease.rc.service.ITownService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DicUtil;
import com.creditease.rc.util.ObjectUtil;
import com.creditease.rc.vo.BorrowerInfoVo;
import com.creditease.rc.vo.CustomerBasicInfoVo;
import com.creditease.rc.vo.GuaranorProfile;
import com.creditease.rc.vo.OperateLogBusinessStruct;

/**
 * 
 * @author haoqiang
 * 
 */
@Service
public class GuaranorProfileService implements IGuaranorProfileService {

	@Resource
	private ISurveybusinessinfoDao surveybusinessinfoDao;
	@Resource
	private IJobInfoDAO jobInfoDAO;
	@Resource
	private IOtherQuestionsDao otherQuestionsDao;
	@Resource
	private IGuaranorProfileDao guaranorProfileDao;
	// 家庭
	@Resource
	private IFamilymemberDao familymemberDao;
	@Resource
	private IBorrowerServiceAppService borrowerServiceAppService;

	@Resource
	private IOperateLogService operateLogService;

	@Resource
	// liuli 用于在后台取地址
	private ITownService townService;

	@Override
	public boolean saveGuaranorProfile(GuaranorProfile guaranorProfile,
			String firstFlag) {
		// TODO Auto-generated method stub
		Long getBorrowerServiceAppId = guaranorProfile.getBorrowerService()
				.getBorrowerServiceAppId();
		List<Familymember> getFamilymembers = guaranorProfile
				.getFamilymembers();
		for (Familymember f : getFamilymembers) {
			f.setBorrowerServiceAppId(getBorrowerServiceAppId);
		}
		List<Surveybusinessinfo> getSurveybusinessinfos = (List<Surveybusinessinfo>) guaranorProfile
				.getSurveybusinessinfos();
		for (Surveybusinessinfo s : getSurveybusinessinfos) {
			s.setBorrowerServiceAppId(getBorrowerServiceAppId);
		}
		List<JobInfo> getJobInfos = guaranorProfile.getJobInfos();
		for (JobInfo j : getJobInfos) {
			j.setBorrowerServiceAppId(getBorrowerServiceAppId);
		}
		OtherQuestions getOtherQuestions = guaranorProfile.getOtherQuestions();
		getOtherQuestions.setBorrowerServiceAppId(getBorrowerServiceAppId);
		BorrowerService getBorrowerService = guaranorProfile
				.getBorrowerService();
		getBorrowerService.setFirstFlag(firstFlag);
		getBorrowerService.setSecondFlag("0");
		getBorrowerService.setThirdFlag("0");
		getBorrowerService.setValidState("1");
		getBorrowerService.setLeader("0");

		BorrowerServiceApp borrowerServiceApp = new BorrowerServiceApp();
		if (CommonUtil.isNotEmpty(getBorrowerService.getName()))
			borrowerServiceApp.setName(getBorrowerService.getName());
		if (CommonUtil.isNotEmpty(getBorrowerService.getFormer()))
			borrowerServiceApp.setFormer(getBorrowerService.getFormer());
		if (CommonUtil.isNotEmpty(getBorrowerService.getGender()))
			borrowerServiceApp.setGender(getBorrowerService.getGender());
		if (CommonUtil.isNotEmpty(getBorrowerService.getHomeAddress()))
			borrowerServiceApp.setHomeAddress(getBorrowerService
					.getHomeAddress());
		if (CommonUtil.isNotEmpty(getBorrowerService.getResidenceAddress()))
			borrowerServiceApp.setResidenceAddress(getBorrowerService
					.getResidenceAddress());
		if (CommonUtil.isNotEmpty(getBorrowerService.getMobilephone()))
			borrowerServiceApp.setMobilephone(getBorrowerService
					.getMobilephone());
		if (CommonUtil.isNotEmpty(getBorrowerService.getCredentialsNumber()))
			borrowerServiceApp.setCredentialsNumber(getBorrowerService
					.getCredentialsNumber());
		if (CommonUtil.isNotEmpty(getBorrowerService.getNationality()))
			borrowerServiceApp.setNational(getBorrowerService.getNationality());
		if (CommonUtil.isNotEmpty(getBorrowerService.getMaritalStatus()))
			borrowerServiceApp.setMaritalStatus(getBorrowerService
					.getMaritalStatus());

		Long getVillageId = getBorrowerService.getVillageId();
		if (getVillageId != null) {
			borrowerServiceApp.setVillageId(getBorrowerService.getVillageId()
					.intValue());
		}
		Long getCustomerBasicId = getBorrowerService.getCustomerBasicId();
		if (getCustomerBasicId != null) {
			borrowerServiceApp.setCustomerBasicId(getBorrowerService
					.getCustomerBasicId().intValue());
		}

		borrowerServiceAppService.addOrUpdateCustomer(borrowerServiceApp,
				Constants.CUSTOMER_TYPE2);
		guaranorProfileDao.update(
				"RL_BORROWER_SERVICE_APP.abatorgenerated_updateByPrimaryKey",
				getBorrowerService);
		List<Surveybusinessinfo> surveybusinessinfosRe = (List<Surveybusinessinfo>) surveybusinessinfoDao
				.queryList("surveybusinessinfo.selectSurveybusinessinfo",
						getBorrowerServiceAppId);
		if (CommonUtil.isNotEmpty(surveybusinessinfosRe)) {
			guaranorProfileDao
					.batchUpdate(
							"surveybusinessinfo.abatorgenerated_updateByPrimaryKeySelective",
							getSurveybusinessinfos);
		} else {
			guaranorProfileDao.batchInsert("surveybusinessinfo.insert",
					getSurveybusinessinfos);
		}
		List<JobInfo> jobInfosre = (List<JobInfo>) jobInfoDAO.queryList(
				"JOB.selectJob", getBorrowerServiceAppId);
		if (CommonUtil.isNotEmpty(jobInfosre)) {
			guaranorProfileDao.batchUpdate(
					"JOB.abatorgenerated_updateByPrimaryKeySelective",
					getJobInfos);
		} else {
			guaranorProfileDao.batchInsert("JOB.insert", getJobInfos);
		}
		List<OtherQuestions> OtherQuestionsRe = (List<OtherQuestions>) otherQuestionsDao
				.queryList("OTHERQUESTIONS.selectByPrimaryKey",
						getBorrowerServiceAppId);
		if (CommonUtil.isNotEmpty(OtherQuestionsRe)) {
			guaranorProfileDao.update("OTHERQUESTIONS.dynamicUpdate",
					getOtherQuestions);
		} else {
			guaranorProfileDao.insert("OTHERQUESTIONS.insert",
					getOtherQuestions);
		}
		List<Familymember> familymembersRe = familymemberDao
				.querySelectFamilymember(getBorrowerServiceAppId);
		if (CommonUtil.isNotEmpty(familymembersRe)) {
			guaranorProfileDao.batchUpdate(
					"familymember.updatefamilymemberByPrimaryKey",
					getFamilymembers);
		} else {
			guaranorProfileDao.batchInsert("familymember.insert",
					getFamilymembers);
		}
		if (Constants.DO_SAVE_STATUS.equals(firstFlag)) {
			OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
			operateLogBusinessStruct.setCreditapplicationId(getBorrowerService
					.getCreditapplicationId().longValue());
			operateLogBusinessStruct.setFunctionCode("100050");
			operateLogBusinessStruct.setRemark("保存（提交）担保人信息（*担保人姓名："
					+ getBorrowerService.getName() + "、*担保人身份证号："
					+ getBorrowerService.getCredentialsNumber() + "、*联系电话："
					+ getBorrowerService.getMobilephone() + "）");
			operateLogService.insert(operateLogBusinessStruct);
		}

		return true;

	}

	@Override
	public boolean updateGuaranorProfile(GuaranorProfile guaranorProfile,
			String firstFlag) {
		// TODO Auto-generated method stub
		List<Familymember> getFamilymembers = guaranorProfile
				.getFamilymembers();
		List<Surveybusinessinfo> getSurveybusinessinfos = (List<Surveybusinessinfo>) guaranorProfile
				.getSurveybusinessinfos();
		List<JobInfo> getJobInfos = guaranorProfile.getJobInfos();
		OtherQuestions getOtherQuestions = guaranorProfile.getOtherQuestions();
		BorrowerService getBorrowerService = guaranorProfile
				.getBorrowerService();
		getBorrowerService.setFirstFlag(firstFlag);
		BorrowerServiceApp borrowerServiceApp = new BorrowerServiceApp();
		if (CommonUtil.isNotEmpty(getBorrowerService.getName()))
			borrowerServiceApp.setName(getBorrowerService.getName());
		if (CommonUtil.isNotEmpty(getBorrowerService.getFormer()))
			borrowerServiceApp.setFormer(getBorrowerService.getFormer());
		if (CommonUtil.isNotEmpty(getBorrowerService.getGender()))
			borrowerServiceApp.setGender(getBorrowerService.getGender());
		if (CommonUtil.isNotEmpty(getBorrowerService.getHomeAddress()))
			borrowerServiceApp.setHomeAddress(getBorrowerService
					.getHomeAddress());
		if (CommonUtil.isNotEmpty(getBorrowerService.getResidenceAddress()))
			borrowerServiceApp.setResidenceAddress(getBorrowerService
					.getResidenceAddress());
		if (CommonUtil.isNotEmpty(getBorrowerService.getMobilephone()))
			borrowerServiceApp.setMobilephone(getBorrowerService
					.getMobilephone());
		if (CommonUtil.isNotEmpty(getBorrowerService.getCredentialsNumber()))
			borrowerServiceApp.setCredentialsNumber(getBorrowerService
					.getCredentialsNumber());
		if (CommonUtil.isNotEmpty(getBorrowerService.getNationality()))
			borrowerServiceApp.setNational(getBorrowerService.getNationality());
		if (CommonUtil.isNotEmpty(getBorrowerService.getMaritalStatus()))
			borrowerServiceApp.setMaritalStatus(getBorrowerService
					.getMaritalStatus());
		Long getVillageId = getBorrowerService.getVillageId();
		if (getVillageId != null) {
			borrowerServiceApp.setVillageId(getBorrowerService.getVillageId()
					.intValue());
		}

		Long getCustomerBasicId = getBorrowerService.getCustomerBasicId();
		if (getCustomerBasicId != null) {
			borrowerServiceApp.setCustomerBasicId(getBorrowerService
					.getCustomerBasicId().intValue());
		}
		borrowerServiceAppService.addOrUpdateCustomer(borrowerServiceApp,
				Constants.CUSTOMER_TYPE2);
		guaranorProfileDao.update(
				"RL_BORROWER_SERVICE_APP.updateBorroerServiceApp",
				getBorrowerService);
		guaranorProfileDao
				.batchUpdate(
						"surveybusinessinfo.abatorgenerated_updateByPrimaryKeySelective",
						getSurveybusinessinfos);
		guaranorProfileDao.batchUpdate(
				"JOB.abatorgenerated_updateByPrimaryKeySelective", getJobInfos);
		guaranorProfileDao.update("OTHERQUESTIONS.dynamicUpdate",
				getOtherQuestions);
		guaranorProfileDao
				.batchUpdate("familymember.updatefamilymemberByPrimaryKey",
						getFamilymembers);

		OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
		operateLogBusinessStruct.setCreditapplicationId(getBorrowerService
				.getCreditapplicationId().longValue());
		operateLogBusinessStruct.setFunctionCode("100050");
		operateLogBusinessStruct.setRemark("保存（提交）担保人信息（*担保人姓名："
				+ getBorrowerService.getName() + "、*担保人身份证号："
				+ getBorrowerService.getCredentialsNumber() + "、*联系电话："
				+ getBorrowerService.getMobilephone() + "）");
		operateLogService.insert(operateLogBusinessStruct);

		return true;
	}

	@Override
	public GuaranorProfile selectBorrowerService(Long borrowerServiceAppId) {
		// TODO Auto-generated method stub
		GuaranorProfile guaranorProfile = new GuaranorProfile();
		BorrowerService borrowerService = new BorrowerService();
		borrowerService.setBorrowerServiceAppId(borrowerServiceAppId);
		borrowerService = (BorrowerService) guaranorProfileDao.queryUnique(
				"RL_BORROWER_SERVICE_APP.abatorgenerated_selectByPrimaryKey",
				borrowerService);
		guaranorProfile.setBorrowerService(borrowerService);
		return guaranorProfile;
	}

	@Override
	public GuaranorProfile selectGuaranorProfile(Long borrowerServiceAppId) {
		// TODO Auto-generated method stub
		GuaranorProfile guaranorProfile = new GuaranorProfile();
		BorrowerService borrowerService = new BorrowerService();
		borrowerService.setBorrowerServiceAppId(borrowerServiceAppId);
		borrowerService = (BorrowerService) guaranorProfileDao.queryUnique(
				"RL_BORROWER_SERVICE_APP.abatorgenerated_selectByPrimaryKey",
				borrowerService);
		List<Familymember> getFamilymembers = (List<Familymember>) guaranorProfileDao
				.queryList(
						"familymember.selectfamilymemberByBorrowerServiceAppId",
						borrowerServiceAppId);
		List<JobInfo> getJobInfos = (List<JobInfo>) guaranorProfileDao
				.queryList("JOB.selectJob", borrowerServiceAppId);
		List<Surveybusinessinfo> getSurveybusinessinfos = (List<Surveybusinessinfo>) guaranorProfileDao
				.queryList("surveybusinessinfo.selectSurveybusinessinfo",
						borrowerServiceAppId);
		OtherQuestions getOtherQuestions = (OtherQuestions) guaranorProfileDao
				.queryUnique("OTHERQUESTIONS.selectByPrimaryKey",
						borrowerServiceAppId);

		// 填充 数据 zhangman 修改
		Familymember f = new Familymember();
		for (Familymember familymember : getFamilymembers) {
			if (familymember.getSeq() != null
					&& !"".equals(familymember.getSeq())) {
				f = familymember;
			}
		}
		
		boolean addPeio = false;   //判断有没有添加配偶的标识
		//循环判断联系人关系是否为2(配偶) 
		for (Familymember familymember : getFamilymembers) {
			String getBorrowerreRationship = familymember
					.getBorrowerreRationship();
			//如果为2  给配偶标识  等于true
			if ("2".equals(getBorrowerreRationship)) {
				addPeio = true;
				familymember.setSeq("0");
				break;
			}
		}
		//如果没有添加配偶  默认添加配偶   把seq的值默认赋值为  0  
		if (!addPeio) {
			Familymember familymember = new Familymember();
			familymember.setBorrowerreRationship("2");
			familymember.setSeq("0");
			getFamilymembers.add(familymember);
		}
		// 把seq的值默认赋值为  0  后，把getFamilymembers集合进行从大到小排序
		Collections.sort(getFamilymembers, new Comparator<Familymember>() {
			@Override
			public int compare(Familymember o1, Familymember o2) {
				// TODO Auto-generated method stub
				if(CommonUtil.isEmpty(o1.getSeq())||CommonUtil.isEmpty(o2.getSeq())){
					return -1;
				}else{
					return o1.getSeq().compareTo(o2.getSeq());
				}
			}
		});
		int i = 1;
		//getFamilymembers集合进行从大到小排序后，给seq值重新赋值   
		for (Familymember familymember : getFamilymembers) {
			familymember.setSeq(i + "");
			i++;
		}
		if (f.getSeq() != null && !"".equals(f.getSeq())) {

			Familymember[] familymembers2 = new Familymember[5];
			for (Familymember familymember : getFamilymembers) {
				if (familymember.getSeq() != null) {
					familymembers2[Integer.valueOf(familymember.getSeq()) - 1] = familymember;
				}
			}
			getFamilymembers = Arrays.asList(familymembers2);
			// 工作

			JobInfo[] infos = new JobInfo[2];
			for (JobInfo jobInfo : getJobInfos) {
				if (jobInfo.getSeq() != null) {
					infos[Integer.valueOf(jobInfo.getSeq()) - 1] = jobInfo;
				}
			}
			getJobInfos = Arrays.asList(infos);

			// 经营

			Surveybusinessinfo[] surveybusinessinfos2 = new Surveybusinessinfo[3];
			for (Surveybusinessinfo surveybusinessinfo : getSurveybusinessinfos) {
				if (surveybusinessinfo.getSeq() != null) {
					surveybusinessinfos2[Integer.valueOf(surveybusinessinfo
							.getSeq()) - 1] = surveybusinessinfo;
				}
			}
			getSurveybusinessinfos = Arrays.asList(surveybusinessinfos2);
		}

		guaranorProfile.setBorrowerService(borrowerService);
		guaranorProfile.setFamilymembers(getFamilymembers);
		guaranorProfile.setJobInfos(getJobInfos);
		guaranorProfile.setSurveybusinessinfos(getSurveybusinessinfos);
		guaranorProfile.setOtherQuestions(getOtherQuestions);
		return guaranorProfile;
	}

	@Override
	public List<CustomerBasicInfoVo> selectCustomerBasicInfoForGuaranorProfile(
			BorrowerInfoVo borrowerService) {
		// TODO Auto-generated method stub

		List<CustomerBasicInfoVo> customerBasicInfoList = (List<CustomerBasicInfoVo>) guaranorProfileDao
				.queryList("BORROWERSERVICE.forGuaranorProfile",
						borrowerService);
		return customerBasicInfoList;
	}

	/*
	 * 将编码对应到汉字 liuli 2013-05-07
	 */
	@Override
	public void displayNameByCode(GuaranorProfile guaranorProfile) {
		
		BorrowerService borrowerService = guaranorProfile.getBorrowerService();
		// 民族
		String nationalityValue = DicUtil.convertCodeKeyToCodeValue("national",
				borrowerService.getNationality());
		// 婚姻状况
		String maritalStatusValue = DicUtil.convertCodeKeyToCodeValue(
				"maritalStatus", borrowerService.getMaritalStatus());
		// 就业状况
		String jobStatusValue = DicUtil.convertCodeKeyToCodeValue("jobStatus",
				borrowerService.getJobStatus());
		// 与借款人关系
		String bondsmanBorrowerValue = DicUtil.convertCodeKeyToCodeValue(
				"bondsmanBorrower", borrowerService.getBondsmanBorrower());

		borrowerService.setNationality(nationalityValue);
		borrowerService.setMaritalStatus(maritalStatusValue);
		borrowerService.setJobStatus(jobStatusValue);
		borrowerService.setBondsmanBorrower(bondsmanBorrowerValue);

		// 家庭成员情况
		List<Familymember> familymembers = guaranorProfile.getFamilymembers();
		if (null != familymembers && familymembers.size() > 0) {
			int fNum = familymembers.size();
			for (int i = 0; i < fNum; i++) {
				Familymember f = familymembers.get(i);
				if (f != null) {
					// 与担保人关系
					String borrowerreRationshipValue = DicUtil
							.convertCodeKeyToCodeValue("borrowerreRationship",
									f.getBorrowerreRationship());
					System.out.println(borrowerreRationshipValue);
					// 职业
					String professionValue = DicUtil.convertCodeKeyToCodeValue(
							"profession", f.getProfession());

					f.setBorrowerreRationship(borrowerreRationshipValue);
					f.setProfession(professionValue);
				}
			}
		}
		// 经营情况
		List<Surveybusinessinfo> surveybusinessinfos = guaranorProfile
				.getSurveybusinessinfos();
		if (null != surveybusinessinfos && surveybusinessinfos.size() > 0) {
			int sNum = surveybusinessinfos.size();
			for (int i = 0; i < sNum; i++) {
				Surveybusinessinfo surveybusinessinfo = surveybusinessinfos
						.get(i);
				if (surveybusinessinfo != null) {
					// 状态
					String stateValue = DicUtil.convertCodeKeyToCodeValue(
							"stateSurveybusinessinfoList",
							surveybusinessinfo.getState());

					surveybusinessinfo.setState(stateValue);
				}
			}
		}

		// 其他问题
		OtherQuestions otherQuestions = guaranorProfile.getOtherQuestions();
		// 以前是否为别人做过担保
		String guaranteeValue = DicUtil.convertCodeKeyToCodeValue("yesOrNo",
				otherQuestions.getGuarantee());
		// 你认识（申请人姓名）多久了
		String howLongValue = DicUtil.convertCodeKeyToCodeValue("howLong",
				otherQuestions.getHowLong());
		// 是否有长期赌博的习惯呢
		String gamblingValue = DicUtil.convertCodeKeyToCodeValue("answer",
				otherQuestions.getGambling());
		// 以前有没有向你家请求过帮助
		String helpValue = DicUtil.convertCodeKeyToCodeValue("answer",
				otherQuestions.getHelp());
		// 你有这么多存款替他/她还款吗
		String enoughDepositsValue = DicUtil.convertCodeKeyToCodeValue(
				"yesOrNo", otherQuestions.getEnoughDeposits());
		// 你的配偶是否知道并同意你担保这笔借款呢
		String spouseInformedValue = DicUtil.convertCodeKeyToCodeValue(
				"yesOrNo", otherQuestions.getSpouseInformed());

		otherQuestions.setGuarantee(guaranteeValue);
		otherQuestions.setHowLong(howLongValue);
		otherQuestions.setGambling(gamblingValue);
		otherQuestions.setHelp(helpValue);
		otherQuestions.setEnoughDeposits(enoughDepositsValue);
		otherQuestions.setSpouseInformed(spouseInformedValue);

	}

	@Override
	public boolean saveUpdateGuaranorProfile(GuaranorProfile guaranorProfile,
			String firstFlag) {
		// TODO Auto-generated method stub
		Long getBorrowerServiceAppId = guaranorProfile.getBorrowerService()
				.getBorrowerServiceAppId();
		List<Familymember> getFamilymembers = guaranorProfile
				.getFamilymembers();

		// 删除
		familymemberDao.delete("familymember.deleteByBorrowerServiceAppId",
				getBorrowerServiceAppId);
		jobInfoDAO.deleteJobInfo(Long.valueOf(getBorrowerServiceAppId)
				.intValue());
		surveybusinessinfoDao.delete(
				"surveybusinessinfo.deleteByBorrowerServiceAppId",
				getBorrowerServiceAppId);

		// 真正存储的 list
		List<Familymember> insertFamilymembers = new ArrayList<Familymember>();
		List<JobInfo> insertJobInfos = new ArrayList<JobInfo>();
		List<Surveybusinessinfo> insertSurveybusinessinfos = new ArrayList<Surveybusinessinfo>();

		// familyFielfList
		List<String> familyFielfList = new ArrayList<String>();
		familyFielfList.add("name");
		familyFielfList.add("borrowerreRationship");
		familyFielfList.add("idNumber");
		familyFielfList.add("age");
		familyFielfList.add("profession");
		familyFielfList.add("professionDetail");
		familyFielfList.add("telphone");

		System.out.println(familyFielfList.size());

		boolean b = false;
		for (Familymember f : getFamilymembers) {
			f.setBorrowerServiceAppId(getBorrowerServiceAppId);
			b = ObjectUtil.checkObjectOneNeed(f, familyFielfList);
			if (b) {
				insertFamilymembers.add(f);
				System.out.println(insertFamilymembers.get(0).getIdNumber());
				System.out.println(insertFamilymembers.get(0).getName());
				System.out.println(f.getIdNumber());
				System.out.println(f.getName());
			}
		}
		List<Surveybusinessinfo> getSurveybusinessinfos = (List<Surveybusinessinfo>) guaranorProfile
				.getSurveybusinessinfos();
		for (Surveybusinessinfo s : getSurveybusinessinfos) {
			s.setBorrowerServiceAppId(getBorrowerServiceAppId);
			if (s.getOperatingItemsDetail() != null
					&& !"".equals(s.getOperatingItemsDetail())) {
				insertSurveybusinessinfos.add(s);
			}
		}
		List<JobInfo> getJobInfos = guaranorProfile.getJobInfos();
		for (JobInfo j : getJobInfos) {
			j.setBorrowerServiceAppId(getBorrowerServiceAppId);
			if (j.getCompany() != null && !"".equals(j.getCompany())) {
				insertJobInfos.add(j);
			}
		}
		OtherQuestions getOtherQuestions = guaranorProfile.getOtherQuestions();
		getOtherQuestions.setBorrowerServiceAppId(getBorrowerServiceAppId);
		BorrowerService getBorrowerService = guaranorProfile
				.getBorrowerService();
		getBorrowerService.setFirstFlag(firstFlag);
		getBorrowerService.setSecondFlag("0");
		getBorrowerService.setThirdFlag("0");
		getBorrowerService.setValidState("1");
		getBorrowerService.setLeader("0");

		BorrowerServiceApp borrowerServiceApp = new BorrowerServiceApp();
		if (CommonUtil.isNotEmpty(getBorrowerService.getName()))
			borrowerServiceApp.setName(getBorrowerService.getName());
		if (CommonUtil.isNotEmpty(getBorrowerService.getFormer()))
			borrowerServiceApp.setFormer(getBorrowerService.getFormer());
		if (CommonUtil.isNotEmpty(getBorrowerService.getGender()))
			borrowerServiceApp.setGender(getBorrowerService.getGender());
		if (CommonUtil.isNotEmpty(getBorrowerService.getHomeAddress()))
			borrowerServiceApp.setHomeAddress(getBorrowerService
					.getHomeAddress());
		if (CommonUtil.isNotEmpty(getBorrowerService.getResidenceAddress()))
			borrowerServiceApp.setResidenceAddress(getBorrowerService
					.getResidenceAddress());
		if (CommonUtil.isNotEmpty(getBorrowerService.getMobilephone()))
			borrowerServiceApp.setMobilephone(getBorrowerService
					.getMobilephone());
		if (CommonUtil.isNotEmpty(getBorrowerService.getCredentialsNumber()))
			borrowerServiceApp.setCredentialsNumber(getBorrowerService
					.getCredentialsNumber());
		if (CommonUtil.isNotEmpty(getBorrowerService.getNationality()))
			borrowerServiceApp.setNational(getBorrowerService.getNationality());
		if (CommonUtil.isNotEmpty(getBorrowerService.getMaritalStatus()))
			borrowerServiceApp.setMaritalStatus(getBorrowerService
					.getMaritalStatus());

		Long getVillageId = getBorrowerService.getVillageId();
		if (getVillageId != null) {
			borrowerServiceApp.setVillageId(getBorrowerService.getVillageId()
					.intValue());
		}
		Long getCustomerBasicId = getBorrowerService.getCustomerBasicId();
		if (getCustomerBasicId != null) {
			borrowerServiceApp.setCustomerBasicId(getBorrowerService
					.getCustomerBasicId().intValue());
		}

		borrowerServiceAppService.addOrUpdateCustomer(borrowerServiceApp,
				Constants.CUSTOMER_TYPE2);
		guaranorProfileDao.update(
				"RL_BORROWER_SERVICE_APP.abatorgenerated_updateByPrimaryKey",
				getBorrowerService);
		List<Surveybusinessinfo> surveybusinessinfosRe = (List<Surveybusinessinfo>) surveybusinessinfoDao
				.queryList("surveybusinessinfo.selectSurveybusinessinfo",
						getBorrowerServiceAppId);
		if (CommonUtil.isNotEmpty(surveybusinessinfosRe)) {
			guaranorProfileDao
					.batchUpdate(
							"surveybusinessinfo.abatorgenerated_updateByPrimaryKeySelective",
							insertSurveybusinessinfos);
		} else {
			guaranorProfileDao.batchInsert("surveybusinessinfo.insert",
					insertSurveybusinessinfos);
		}
		List<JobInfo> jobInfosre = (List<JobInfo>) jobInfoDAO.queryList(
				"JOB.selectJob", getBorrowerServiceAppId);
		if (CommonUtil.isNotEmpty(jobInfosre)) {
			guaranorProfileDao.batchUpdate(
					"JOB.abatorgenerated_updateByPrimaryKeySelective",
					insertJobInfos);
		} else {
			guaranorProfileDao.batchInsert("JOB.insert", insertJobInfos);
		}
		List<OtherQuestions> OtherQuestionsRe = (List<OtherQuestions>) otherQuestionsDao
				.queryList("OTHERQUESTIONS.selectByPrimaryKey",
						getBorrowerServiceAppId);
		if (CommonUtil.isNotEmpty(OtherQuestionsRe)) {
			guaranorProfileDao.update("OTHERQUESTIONS.dynamicUpdate",
					getOtherQuestions);
		} else {
			guaranorProfileDao.insert("OTHERQUESTIONS.insert",
					getOtherQuestions);
		}
		List<Familymember> familymembersRe = familymemberDao
				.querySelectFamilymember(getBorrowerServiceAppId);
		if (CommonUtil.isNotEmpty(familymembersRe)) {
			guaranorProfileDao.batchUpdate(
					"familymember.updatefamilymemberByPrimaryKey",
					insertFamilymembers);
		} else {
			guaranorProfileDao.batchInsert("familymember.insert",
					insertFamilymembers);
		}
		if (Constants.DO_SAVE_STATUS.equals(firstFlag)) {
			OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
			operateLogBusinessStruct.setCreditapplicationId(getBorrowerService
					.getCreditapplicationId().longValue());
			operateLogBusinessStruct.setFunctionCode("100050");
			operateLogBusinessStruct.setRemark("保存（提交）担保人信息（*担保人姓名："
					+ getBorrowerService.getName() + "、*担保人身份证号："
					+ getBorrowerService.getCredentialsNumber() + "、*联系电话："
					+ getBorrowerService.getMobilephone() + "）");
			operateLogService.insert(operateLogBusinessStruct);
		}

		return true;
	}
}
