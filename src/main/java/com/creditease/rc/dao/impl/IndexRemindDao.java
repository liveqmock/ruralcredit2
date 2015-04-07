package com.creditease.rc.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IIndexRemindDao;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.vo.AuditRemindVo;
import com.creditease.rc.vo.CountInfo;
import com.creditease.rc.vo.CreditapplicationView;
import com.creditease.rc.vo.DepartmentCountInfo;
import com.creditease.rc.vo.InvestigationVo;

/**
 * 首页提醒
 * 
 * @author Administrator
 * 
 */
@Repository
public class IndexRemindDao implements IIndexRemindDao {
	@Resource
	private IBaseDao baseDao;

	@Override
	public List<AuditRemindVo> selectAuditRemind(CreditApplication creditApplication) {
		List<AuditRemindVo> aduitRemindList = (List<AuditRemindVo>) baseDao.queryList("INDEXREMIND.selectAuditRemind",
				creditApplication);
		return aduitRemindList;
	}

	@Override
	public InvestigationVo selectInvestigatioRemind(CreditApplication creditApplication) {
		InvestigationVo investigatio = (InvestigationVo) baseDao.queryUnique("INDEXREMIND.selectInvestigatioRemind",
				creditApplication);
		return investigatio;
	}

	@Override
	public List<AuditRemindVo> selectLoanRegister(CreditApplication creditApplication) {
		List<AuditRemindVo> list = (List<AuditRemindVo>) baseDao.queryList("INDEXREMIND.selectLoanRegister",
				creditApplication);
		return list;
	}

	@Override
	public AuditRemindVo selectFinancePaymentRemind(CreditApplication creditApplication) {
		AuditRemindVo auditRemind = (AuditRemindVo) baseDao.queryUnique("CREDITAPPLICATION.selectPaymentFinanceRemind",
				creditApplication);
		return auditRemind;
	}

	@Override
	public List<AuditRemindVo> selectFinanceReceiveRemind(CreditApplication creditApplication) {
		List<AuditRemindVo> auditRemind = (List<AuditRemindVo>) baseDao.queryList(
				"FINANCEMONEY.selectFinanceReceiveRemind", creditApplication);
		return auditRemind;
	}

	@Override
	public List<AuditRemindVo> selectReceiptRegisterRemind(CreditApplication creditApplication) {
		List<AuditRemindVo> auditRemindList = new ArrayList<AuditRemindVo>();
		List<Map> auditRemind1 = (List<Map>) baseDao.queryList("INDEXREMIND.selectReceiptRegisterRemind1",
				creditApplication);
		List<Map> auditRemind3 = (List<Map>) baseDao.queryList("INDEXREMIND.selectReceiptRegisterRemind3",
				creditApplication);
		List<Map> auditRemind7 = (List<Map>) baseDao.queryList("INDEXREMIND.selectReceiptRegisterRemind7",
				creditApplication);

		StringBuffer sbf1 = new StringBuffer(",");
		for (int i = 0; i < auditRemind1.size(); i++) {
			Map map = auditRemind1.get(i);
			BigDecimal creditapplicationId = (BigDecimal) map.get("CREDITAPPLICATIONID");
			sbf1.append(creditapplicationId);
		}
		AuditRemindVo auditRemindVo1 = new AuditRemindVo();
		auditRemindVo1.setAuditStatus("1");
		auditRemindVo1.setCreditapplicationIds(sbf1.substring(1, sbf1.length()));
		auditRemindVo1.setAuditCount(auditRemind1.size());

		sbf1 = new StringBuffer(",");
		for (int i = 0; i < auditRemind3.size(); i++) {
			Map map = auditRemind3.get(i);
			BigDecimal creditapplicationId = (BigDecimal) map.get("CREDITAPPLICATIONID");
			sbf1.append(creditapplicationId);
		}
		AuditRemindVo auditRemindVo3 = new AuditRemindVo();
		auditRemindVo3.setAuditStatus("3");
		auditRemindVo3.setCreditapplicationIds(sbf1.substring(1, sbf1.length()));
		auditRemindVo3.setAuditCount(auditRemind3.size());

		sbf1 = new StringBuffer(",");
		for (int i = 0; i < auditRemind7.size(); i++) {
			Map map = auditRemind7.get(i);
			BigDecimal creditapplicationId = (BigDecimal) map.get("CREDITAPPLICATIONID");
			sbf1.append(creditapplicationId);
		}
		AuditRemindVo auditRemindVo7 = new AuditRemindVo();
		auditRemindVo7.setAuditStatus("7");
		auditRemindVo7.setCreditapplicationIds(sbf1.substring(1, sbf1.length()));
		auditRemindVo7.setAuditCount(auditRemind7.size());

		auditRemindList.add(auditRemindVo1);
		auditRemindList.add(auditRemindVo3);
		auditRemindList.add(auditRemindVo7);

		return auditRemindList;
	}

	@Override
	public Map<String, CountInfo> selectCountInfo(CreditApplication application) {
		Map<String, CountInfo> map = new HashMap<String, CountInfo>();
		CountInfo receiptCountInfo = (CountInfo) baseDao.queryUnique("INDEXREMIND.selectCountInfo", application);
		CountInfo toPublicAll = (CountInfo) baseDao.queryUnique("INDEXREMIND.toPublic", application);
		CountInfo toPrivateAll = (CountInfo) baseDao.queryUnique("INDEXREMIND.toPrivate", application);
		toPublicAll = toPublicAll == null ? new CountInfo() : toPublicAll;
		toPrivateAll = toPrivateAll == null ? new CountInfo() : toPrivateAll;
		CountInfo allCountInfo = new CountInfo();
		allCountInfo.setRemindCount(toPublicAll.getRemindCount() + toPrivateAll.getRemindCount());
		allCountInfo.setRemindSum(toPublicAll.getRemindSum() + toPrivateAll.getRemindSum());
		application.setOperateFlag("1");
		CountInfo toPublic = (CountInfo) baseDao.queryUnique("INDEXREMIND.toPublic", application);
		CountInfo toPrivate = (CountInfo) baseDao.queryUnique("INDEXREMIND.toPrivate", application);
		map.put("receiptCountInfo", receiptCountInfo);
		map.put("toPublic", toPublic);
		map.put("toPrivate", toPrivate);
		map.put("allCountInfo", allCountInfo);

		return map;
	}

	@Override
	public List<DepartmentCountInfo> selectDepartmentCountInfo(Integer departmentId) {
		return (List<DepartmentCountInfo>) baseDao.queryList("INDEXREMIND.selectDepartmentCountInfo", departmentId);
	}

	@Override
	public List<CreditapplicationView> selectRemindFromView(CreditApplication creditapplication) {
		return (List<CreditapplicationView>) baseDao.queryList("INDEXREMIND.selectRemindFromView", creditapplication);
	}

	@Override
	public int conSultCount(Map<String, String> querySultMap) {
		// TODO Auto-generated method stub
		return (Integer) baseDao.queryUnique("ContomerConsult.countMap", querySultMap);
	}

    @Override
    public int selectUploadingContractRemind(CreditApplication application) {
        return (Integer) baseDao.queryUnique("INDEXREMIND.selectUploadingContractRemind", application);
    }

}
