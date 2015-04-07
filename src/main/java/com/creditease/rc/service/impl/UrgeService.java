package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.security.User;
import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.IUrgeDao;
import com.creditease.rc.dao.IUrgeLinkmanDao;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.LocalOfficeIdDTO;
import com.creditease.rc.domain.LocalOverdueInfoResponse;
import com.creditease.rc.domain.LocalReturnSchemeResponse;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.Urge;
import com.creditease.rc.domain.UrgeLinkman;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.IRural2CreditService;
import com.creditease.rc.service.IUrgeService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.DicUtil;
import com.creditease.rc.util.ObjectUtil;
import com.creditease.rc.util.SmpWSUtil;
import com.creditease.rc.vo.UrgeAndLinkmanVo;
import com.creditease.rc.vo.UrgeVo;

@Service
public class UrgeService implements IUrgeService {
	@Resource
	private IUrgeDao urgeDao;

	@Resource
	private IUrgeLinkmanDao urgeLinkmanDao;

	@Resource
	private IRural2CreditService rural2CreditService;

	@Resource
	private SmpWSUtil smpWSUtil;

	@Resource
	private ICreditApplicationService creditApplicationService;

	@Override
	public boolean insert(Urge urge) {
		// TODO Auto-generated method stub

		return urgeDao.insert(urge);
	}

	@Override
	public boolean dynamicUpdate(Urge urge) {
		// TODO Auto-generated method stub
		return urgeDao.dynamicUpdate(urge);
	}

	@Override
	public Message saveUrge(Urge urge) {
		// TODO Auto-generated method stub
		Message message = new Message();
		boolean success = insert(urge);
		message.setSuccess(success);
		if (success) {
			message.setMsg("保存催收信息成功！");
		} else {
			message.setMsg("保存催收信息失败！");
		}
		return message;
	}

	/**
	 * 查询催收列表信息
	 */
	@Override
	public Pagination queryUrgeList(Pagination pagination, Map<String, String> pramMap) {
		// TODO Auto-generated method stub
		pagination = urgeDao.queryUrgeList(pagination, pramMap);
		List<UrgeVo> urgeVos = pagination.getRows();

		if (CommonUtil.isNotEmpty(urgeVos)) {
			for (UrgeVo urgeVo : urgeVos) {
				String getUrgeSummarize = urgeVo.getUrgeSummarize();
				String getUrgeWay = urgeVo.getUrgeWay();
				String getUrgeState = urgeVo.getUrgeState();
				String getMoneySource = urgeVo.getMoneySource();
				String getBigWarning = urgeVo.getBigWarning();
				String getUrgeRemark = urgeVo.getUrgeRemark();
				String getYnPromise = urgeVo.getYnPromise();

				getUrgeSummarize = DicUtil.convertCodeKeyToCodeValue("urgesummarize", getUrgeSummarize);
				getUrgeWay = DicUtil.convertCodeKeyToCodeValue("urgeway", getUrgeWay);
				getUrgeState = DicUtil.convertCodeKeyToCodeValue("urgestate", getUrgeState);
				getMoneySource = DicUtil.convertCodeKeyToCodeValue("moneysource", getMoneySource);
				getBigWarning = DicUtil.convertCodeKeyToCodeValue("bigwarning", getBigWarning);
				getUrgeRemark = DicUtil.convertCodeKeyToCodeValue("urgeremark", getUrgeRemark);
				getYnPromise = DicUtil.convertCodeKeyToCodeValue("ynpromise", getYnPromise);

				urgeVo.setUrgeSummarize(getUrgeSummarize);
				urgeVo.setUrgeWay(getUrgeWay);
				urgeVo.setUrgeState(getUrgeState);
				urgeVo.setMoneySource(getMoneySource);
				urgeVo.setBigWarning(getBigWarning);
				urgeVo.setUrgeRemark(getUrgeRemark);
				urgeVo.setYnPromise(getYnPromise);
			}
		}
		return pagination;
	}

	/**
	 * 保存催收信息及催收联系人信息
	 */
	@Override
	public Message saveUrgeAndLinkmanVo(UrgeAndLinkmanVo urgeAndLinkmanVo) {
		// TODO Auto-generated method stub
		Message message = new Message();
		boolean result = false;
		UrgeVo urgeVotemp = urgeAndLinkmanVo.getUrgeVo();
		Urge urgeforquery = new Urge();
		urgeforquery.setCreditapplicationId(urgeVotemp.getCreditapplicationId());
		urgeforquery.setUrgeDate(urgeVotemp.getUrgeDate());
		urgeforquery.setRepaymentCycle(urgeVotemp.getRepaymentCycle());
		urgeforquery.setBreakbegindate(urgeVotemp.getBreakbegindate());
		urgeforquery.setRefundDate(urgeVotemp.getRefundDate());
		urgeDao.deleteUrgeForSave(urgeforquery);
		UrgeVo urgeVo = urgeAndLinkmanVo.getUrgeVo();
		User user = SpringSecurityUtils.getCurrentUser();
		urgeVo.setOperatorId(Long.parseLong(user.getUserId()));
		urgeVo.setOperatorName(user.getName_zh());
		urgeVo.setOperateTime(new Date());
		int hour = 0;
		if (!urgeVo.getHour().equals(null) && urgeVo.getHour() != "") {
			hour = Integer.parseInt(urgeVo.getHour());
		}
		String longDate = null;
		if (hour < 10) {
			longDate = "0" + urgeVo.getHour() + urgeVo.getMinute();
		} else {
			longDate = urgeVo.getHour() + urgeVo.getMinute();
		}
		// Date previousPromiseTime=urgeVo.getPreviousPromiseTime();
		urgeVo.setUrgeLongTime(longDate);

		List<UrgeLinkman> urgeLinkmans = urgeAndLinkmanVo.getUrgeLinkmans();

		urgeDao.insert(urgeVo);

		/*
		 * Long getUrgeId = urgeVo.getUrgeId(); if
		 * (CommonUtil.isNotEmpty(urgeLinkmans)) { for (UrgeLinkman urgeLinkman
		 * : urgeLinkmans) {
		 * if(!urgeLinkman.getUrgeLinkName().equals(null)&&!urgeLinkman
		 * .getBorrowerRelation().eq){ } } }
		 */
		Long getUrgeId = urgeVo.getUrgeId();
		List<UrgeLinkman> temps = new ArrayList<UrgeLinkman>();
		// UrgeLinkman urgeLinkman=new UrgeLinkman();
		if (CommonUtil.isNotEmpty(urgeLinkmans)) {
			for (int i = 0; i < urgeLinkmans.size(); i++) {
				UrgeLinkman urgelink = urgeLinkmans.get(i);
				if (!ObjectUtil.checkObjectIsNull(urgelink)) {
					temps.add(urgelink);
					urgelink.setUrgeId(getUrgeId);
				}
			}
			result = urgeLinkmanDao.insertUrgeLinkmanList(temps);
		}
		// urgeLinkman.setUrgeId(getUrgeId);
		// result=urgeLinkmanDao.insertUrgeLinkmanList(urgeLinkmans);
		if (result) {
			message.setSuccess(true);
			message.setMsg("保存成功！");
		} else {
			message.setSuccess(false);
			message.setMsg("保存失败！");
		}
		return message;
	}

	/**
	 * 根据催收ID查询催收信息
	 */
	@Override
	public UrgeVo queryUrgeListByUrgeId(Long urgeId) {
		return urgeDao.queryUrgeListByUrgeId(urgeId);
	}

	/**
	 * 根据业务creditapplicationId查询催收信息
	 */
	@Override
	public UrgeVo selectUrgeByCreditApplicationId(Long creditApplicationId) {
		return urgeDao.selectUrgeByCreditApplicationId(creditApplicationId);
	}

	/**
	 * 根据查询催收承诺还款日提醒
	 */
	@Override
	public List<UrgeVo> querUrgeCountList() {
		List<UrgeVo> urgeVo = urgeDao.querUrgeCountList();
		return urgeVo;
	}

	@Override
	public List<Urge> queryUrgeBycreditapplicationIds(String creditapplicationIds) {
		// TODO Auto-generated method stub
		return urgeDao.queryUrgeBycreditapplicationIds(creditapplicationIds);
	}

	@Override
	public Message InsertCreateUrgeHistory() {
		// TODO Auto-generated method stub
		Message message = new Message();
		message.setSuccess(true);
		message.setMsg("插入完成！");
		// 查询还款中数据
		List<Long> creditApplicationIds = urgeDao.queryInThePayment();
		for (Long creditApplicationId : creditApplicationIds) {
			List<LocalReturnSchemeResponse> localReturnSchemeResponses = rural2CreditService.returnScheme(creditApplicationId);
			for (LocalReturnSchemeResponse localReturnSchemeResponse : localReturnSchemeResponses) {
				String getIsOverdue = localReturnSchemeResponse.getIsOverdue();
				Date getRepayDate = localReturnSchemeResponse.getRepayDate();
				String getOverDays = localReturnSchemeResponse.getOverDays();
				Integer getPeriod = localReturnSchemeResponse.getPeriod();

				if ("1".equals(getIsOverdue) && (getRepayDate.compareTo(DateUtil.stringConvertDate("2014-03-23")) > 0) && CommonUtil.isNotEmpty(getOverDays) && Integer.parseInt(getOverDays) > 1) {
					// 符合逾期数据且还款日在2014年3月24且逾期天数大于1的数据
					// 查询这些数据有没有做过催收
					Map<String, String> queryMap = new HashMap<String, String>();
					// creditapplicationId
					// repaymentCycle
					queryMap.put("creditapplicationId", creditApplicationId.toString());
					queryMap.put("repaymentCycle", getPeriod.toString());
					List<Urge> urges = urgeDao.queryUrgeListByMap(queryMap);
					if (CommonUtil.isEmpty(urges)) {
						// 如果为空就插入数据
						// 可能要查询数据最后插入数据
						Urge urge = new Urge();
						urge.setCreditapplicationId(creditApplicationId);
						urge.setRepaymentCycle(getPeriod.toString());
						urge.setBreakbegindate(getRepayDate);
						urge.setRefundDate(getRepayDate);
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(getRepayDate);
						calendar.add(calendar.DATE, 1);
						Date tempDate = calendar.getTime();
						tempDate = DateUtil.stringConvertDate(DateUtil.dateConvertString(tempDate));
						urge.setUrgeDate(tempDate);
						urgeDao.insert(urge);
					}
				}

			}
		}
		// 调用贷后接口
		return message;
	}

	@Override
	public Message insertCreateUrge() {
		// TODO Auto-generated method stub
		Message message = new Message();
		message.setSuccess(true);
		message.setMsg("插入完成！");

		Map<String, String> AllAreaDepartmentNameMap = smpWSUtil.getAllAreaDepartmentNameMap();
		Set<String> set = AllAreaDepartmentNameMap.keySet();
		List<LocalOfficeIdDTO> localOfficeIdListDTOs = new ArrayList<LocalOfficeIdDTO>();

		for (String areaDepartmentId : set) {
			LocalOfficeIdDTO localOfficeIdDTO = new LocalOfficeIdDTO();
			localOfficeIdDTO.setOfficeId(areaDepartmentId);
			localOfficeIdListDTOs.add(localOfficeIdDTO);
		}
		LocalOverdueInfoResponse localOverdueInfoResponse = rural2CreditService.overdueInfo(localOfficeIdListDTOs);
		List<Long> getCreditApplicationIdList = localOverdueInfoResponse.getCreditApplicationIdList();

		for (Long creditApplicationId : getCreditApplicationIdList) {
			List<LocalReturnSchemeResponse> localReturnSchemeResponses = rural2CreditService.returnScheme(creditApplicationId);
			CreditApplication creditApplication = creditApplicationService.selectById(creditApplicationId.intValue());
			String getAuditStatus = creditApplication.getAuditStatus();
			if (!Constants.STATE_30.equals(getAuditStatus) && !Constants.STATE_15.equals(getAuditStatus)) {
				continue;
			}
			for (LocalReturnSchemeResponse localReturnSchemeResponse : localReturnSchemeResponses) {
				String getIsReturned = localReturnSchemeResponse.getIsReturned();
				String getIsOverdue = localReturnSchemeResponse.getIsOverdue();
				Date getRepayDate = localReturnSchemeResponse.getRepayDate();
				String getOverDays = localReturnSchemeResponse.getOverDays();
				Integer getPeriod = localReturnSchemeResponse.getPeriod();
				if ("0".equals(getIsReturned) && "1".equals(getIsOverdue) && (getRepayDate.compareTo(new Date()) < 0) && "2".equals(getOverDays)) {
					// 符合逾期数据且还款日在今天之前且逾期天数为2的数据
					// 查询这些数据有没有做过催收
					Map<String, String> queryMap = new HashMap<String, String>();
					queryMap.put("creditapplicationId", creditApplicationId.toString());
					queryMap.put("repaymentCycle", getPeriod.toString());
					List<Urge> urges = urgeDao.queryUrgeListByMap(queryMap);
					if (CommonUtil.isEmpty(urges)) {
						// 如果为空就插入数据
						// 可能要查询数据最后插入数据
						Urge urge = new Urge();
						urge.setCreditapplicationId(creditApplicationId);
						urge.setRepaymentCycle(getPeriod.toString());
						urge.setBreakbegindate(getRepayDate);
						urge.setRefundDate(getRepayDate);
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(new Date());
						calendar.add(calendar.DATE, -1);
						Date tempDate = calendar.getTime();
						tempDate = DateUtil.stringConvertDate(DateUtil.dateConvertString(tempDate));
						urge.setUrgeDate(tempDate);
						urgeDao.insert(urge);
					}
				}

			}
		}
		return message;
	}
}
