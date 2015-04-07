package com.creditease.rc.service.impl;

import com.creditease.core.exception.BusinessException;
import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.app.cm.CreditCMClientWS;
import com.creditease.rc.app.smp.OrganizeWebService;
import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.IBusinessStatusChangeDAO;
import com.creditease.rc.domain.*;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.*;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.vo.LegalProceedingsVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class BusinessStatusChangeService implements IBusinessStatusChangeService {

	@Resource
	private IBusinessStatusChangeDAO iBusinessStatusChangeDAO;

	@Resource
	private IRural2CreditService rural2CreditService;

	@Resource
	private OrganizeWebService organizeService;

	@Resource
	private IDataDictionaryService dataDictionaryService;

	@Resource
	private IBusinessStatusChangeService businessStatusChangeService;

	@Resource
	private CreditCMClientWS cmClientWSPortType;

	@Resource
	private ICustomerReturnVisitService customerReturnVisitService;

	@Override
	public long saveChange(BusinessStatusChange change) {
		return iBusinessStatusChangeDAO.insert(change);
	}

	@Override
	public Pagination selectChangeLog(Pagination pagination, BusinessStatusChangeVo vo, String sort, String order) {
		Map searchValue = new HashMap();
		searchValue.put("SORT", sort);
		searchValue.put("ORDER", order);
		searchValue.put("groupNumber", StringUtils.isNotEmpty(vo.getGroupNumber()) ? vo.getGroupNumber().trim() : "");
		searchValue.put("type", StringUtils.isNotEmpty(vo.getType()) ? vo.getType().trim() : "");
		return iBusinessStatusChangeDAO.selectByPagination(pagination, searchValue);
	}

	@Override
	public Pagination selectPrepareCreditApplication(Pagination pagination, BusinessStatusChangeVo vo) {
		Map searchValue = new HashMap();
		searchValue.put("groupNumber", StringUtils.isNotEmpty(vo.getGroupNumber()) ? vo.getGroupNumber().trim() : "");
		searchValue.put("type", StringUtils.isNotEmpty(vo.getType()) ? vo.getType().trim() : "");
		return iBusinessStatusChangeDAO.selectPrepareCreditApplication(pagination, searchValue);
	}

	@Override
	public BusinessStatusChange selectChangeById(Long changeId) {
		return iBusinessStatusChangeDAO.selectChangeById(changeId);
	}

	@Override
	public Integer updateChangeHistoryFlag(BusinessStatusChange change) {
		return iBusinessStatusChangeDAO.updateChangeHistoryFlag(change);
	}

	@Override
	public void updateOverDueGt180() {
		logger.info("定时任务（逾期天数超180天检查）开始运行...");
		Integer updateRows = 0;
		Map<String, String> areaDepartMap = organizeService.getAllAreaDepart(Integer.parseInt(Constants.SYSTEM_SIGN));
		Set<Map.Entry<String, String>> set = areaDepartMap.entrySet();
		if (set.size() > 0) {
			List<LocalOfficeIdDTO> localOfficeIdDTOList = new ArrayList<LocalOfficeIdDTO>();
			for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = it.next();
				LocalOfficeIdDTO localOfficeIdDTO = new LocalOfficeIdDTO();
				localOfficeIdDTO.setOfficeId(entry.getKey());
				localOfficeIdDTOList.add(localOfficeIdDTO);
			}
			LocalOverdueInfoResponse localOverdueInfoResponse = rural2CreditService.overdueInfo(localOfficeIdDTOList);
			Map<Long, OverDueObj> overDueObjMap = localOverdueInfoResponse.getOverDueObjMap();
			// 数据字典
			List<CodeTable> tables = dataDictionaryService.getSpecifiedDic("", "auditStatus");
			Map<String, String> codeMap = new HashMap<String, String>();
			for (CodeTable table : tables) {
				codeMap.put(table.getCodeVlue(), table.getCodeKey());
			}
			BusinessStatusChange change;
			for (Map.Entry<Long, OverDueObj> entry : overDueObjMap.entrySet()) {
				if (entry.getValue().getOverdueDayCount().intValue() > 180) {

					// 检查自动任务是否已经进行过变更
					List<BusinessStatusChange> changesByTimer = iBusinessStatusChangeDAO.viewChangeLogByTimer(entry.getValue().getCreditApplicationId());
					if (changesByTimer == null || changesByTimer.isEmpty()) {
						// 更新记录数据状态为:不良贷款
						updateRows++;
						change = new BusinessStatusChange();
						change.setCreditapplicationId(entry.getValue().getCreditApplicationId());
						// 更新历史数据标记
						change.setHistoryFlag("0");
						businessStatusChangeService.updateChangeHistoryFlag(change);
						change.setOperator("定时任务");
						change.setOperateDate(new Date());
						change.setRemark("逾期天数超过180天");
						change.setBeforeStatus(codeMap.get("还款中"));
						change.setAfterStatus(codeMap.get("不良贷款"));
						change.setOperateType("0");
						change.setHistoryFlag("1");
						businessStatusChangeService.saveChange(change);
                        //更新催收及回访记录
                        businessStatusChangeService.affectROANDRV(change.getCreditapplicationId(), "0", change.getOperateDate());
					}
				}
			}
		}
		StringBuilder retMsg = new StringBuilder("定时任务（逾期天数超180天检查）结束!");
		logger.info(updateRows > 0 ? retMsg.append("共更新数据" + updateRows + "条！").toString() : retMsg.toString());
	}

	@Override
	public BusinessStatusChange selectLatestStatusByCreditApplicationId(Long creditApplicationId) {
		return iBusinessStatusChangeDAO.selectLatestStatusByCreditApplicationId(creditApplicationId);
	}

	@Override
	public Integer checkAttachment(String clientId) {
		Integer attachmentCount = 0;
		logger.info("调用cm接口cmClientWSPortType.getImgAmount 入参：clientId=" + clientId + ";bussTableName=" + Constants.CM_U_YINONGDAI2_YW);
		String amount = cmClientWSPortType.getImgAmount(clientId, Constants.CM_U_YINONGDAI2_YW);
		logger.info("调用cm接口cmClientWSPortType.getImgAmount 出参：" + amount);
		// count:2[] count:0[]
		if (CommonUtil.isNotEmpty(amount)) {
			if ("failure".equalsIgnoreCase(amount)) {
				throw new BusinessException("调用cm接口方法cmClientWSPortType.getImgAmount返回值异常,请联系cm人员,返回值=" + attachmentCount);
			}
			attachmentCount = Integer.valueOf(String.valueOf(amount.charAt(6)));
		}
		return attachmentCount;
	}

	@Override
	public Pagination selectChangeLogByNumber(Pagination pagination, String sort, String order, String tabTitle, String number) {
		Map searchValue = new HashMap();
		searchValue.put("groupNumber", number);
		searchValue.put("type", tabTitle);
		return iBusinessStatusChangeDAO.selectByPaginationByNumber(pagination, searchValue);
	}

	@Override
	public Pagination viewLegalProceedings(Pagination pagination, LegalProceedingsVo vo, String sort, String order, String sqlSid) {
		Map searchValue = new HashMap();
		if (null != vo) {
			searchValue.put("businessNumber", StringUtils.isNotEmpty(vo.getBusinessNumber()) ? vo.getBusinessNumber().trim() : "");
			searchValue.put("borrowerName", StringUtils.isNotEmpty(vo.getBorrowerName()) ? vo.getBorrowerName().trim() : "");
			searchValue.put("branchName", StringUtils.isNotEmpty(vo.getBranchName()) ? vo.getBranchName().trim() : "");
		}
		searchValue.put("authList", sqlSid);
		return iBusinessStatusChangeDAO.viewLegalProceedings(pagination, searchValue);
	}

	@Override
	public Pagination viewLegalProceedingsDetail(Pagination pagination, LegalProceedings legalProceedings, String sort, String order) {
		return iBusinessStatusChangeDAO.viewLegalProceedingsDetail(pagination, legalProceedings);
	}

	@Override
	public Long saveLegalProceedings(LegalProceedings legalProceedings) {
		String name_zh = "";
		try {
			name_zh = SpringSecurityUtils.getCurrentUser().getName_zh();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (CommonUtil.isEmpty(name_zh)) {
			name_zh = null;
		}
		legalProceedings.setOperator(name_zh);
		legalProceedings.setOperate_date(new Date());
		return iBusinessStatusChangeDAO.saveLegalProceedings(legalProceedings);
	}

	@Override
	public Message affectROANDRV(Long creditapplicationId, String type, Date date) {
		Message message = new Message();
		// 根据 类型不同 将 划分
		// 将回访计划中所有大于这个日期的计划 都至为1
		Map<String, String> map = new HashMap<String, String>();
		map.put("creditapplicationId", creditapplicationId.toString());
		map.put("date", DateUtil.dateConvertString(date));
		if ("0".equals(type)) {
			map.put("type", "1");// 置为无效
			message = customerReturnVisitService.updateCusReViVSByCreIdNDat(map);
		} else if ("1".equals(type)) {
			map.put("type", "0");// 置为有效
			message = customerReturnVisitService.updateCusReViVSByCreIdNDat(map);
		} else {
			message.setSuccess(false);
			message.setMsg("type类型合规！");
		}
		return message;
	}

}
