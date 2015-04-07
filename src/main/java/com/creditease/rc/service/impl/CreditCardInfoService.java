package com.creditease.rc.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.ICreditCardInfoDAO;
import com.creditease.rc.dao.ICreditOrganizationDAO;
import com.creditease.rc.domain.CreditCardInfo;
import com.creditease.rc.domain.CreditOrganization;
import com.creditease.rc.service.ICreditCardInfoService;
import com.creditease.rc.vo.CreditCardInfoVo;

/**
 * 
 * @author zhangman
 * 
 */
@Service
public class CreditCardInfoService implements ICreditCardInfoService {

	@Resource
	private ICreditCardInfoDAO creditCardInfoDAO;
	@Resource
	private ICreditOrganizationDAO creditOrganizationDAO;

	@Override
	public CreditCardInfoVo addOrUpdateCreditCardInfo(CreditCardInfoVo creditCardInfoVo) {
		CreditCardInfoVo creditCardInfoVoTemp = new CreditCardInfoVo();
		if (creditCardInfoVo.getCreditCardInfo().getCreditCardInfoId() != null
				&& !"".equals(creditCardInfoVo.getCreditCardInfo().getCreditCardInfoId())) {
			creditCardInfoDAO.updateCardInfo(creditCardInfoVo.getCreditCardInfo());
			creditCardInfoVoTemp.setCreditCardInfo(creditCardInfoVo.getCreditCardInfo());
			// 添加信用卡详细

			// 保留的信用卡发卡机构 List
			List<CreditOrganization> tempCreditOrganizations = new ArrayList<CreditOrganization>();
			for (CreditOrganization creditorganization : creditCardInfoVo
					.getCreditOrganization()) {
				creditorganization.setCreditCardInfoId(creditCardInfoVo
						.getCreditCardInfo().getCreditCardInfoId());
				// creditOrganizationDAO.updateCreditOrganization(creditorganization);
				// id存在
				if (creditorganization.getCreditOrgId() != null
						&& !"".equals(creditorganization.getCreditOrgId())) {
					// 贷款机构存在 则修改
					if (creditorganization.getCreditCardIssue() != null
							&& creditorganization.getCreditCardIssue() != "") {
						creditOrganizationDAO
								.updateCreditOrganization(creditorganization);
						tempCreditOrganizations.add(creditorganization);
					} else {
						// 贷款机构不存在 则删除
						creditOrganizationDAO.deleteById(creditorganization
								.getCreditOrgId());
					}
				} else {
					// id 不存在 机构名称存在
					if (creditorganization.getCreditCardIssue() != null
							&& creditorganization.getCreditCardIssue() != "") {

						int creditOrganizationId = creditOrganizationDAO
								.addCreditOrganization(creditorganization);
						creditorganization.setCreditOrgId(creditOrganizationId);
						tempCreditOrganizations.add(creditorganization);
					}
				}
			}
			creditCardInfoVoTemp.setCreditOrganization(tempCreditOrganizations);
			return creditCardInfoVoTemp;

		} else {
			int cciId = creditCardInfoDAO.addCreditCardInfo(creditCardInfoVo
					.getCreditCardInfo());
			creditCardInfoVo.getCreditCardInfo().setCreditCardInfoId(cciId);
			creditCardInfoVoTemp.setCreditCardInfo(creditCardInfoVo.getCreditCardInfo());
			// 添加信用卡详细
			List<CreditOrganization> tempCreditOrganizations = new ArrayList<CreditOrganization>();
			for (CreditOrganization creditorganization : creditCardInfoVo
					.getCreditOrganization()) {
				if (creditorganization.getCreditCardIssue() != null
						&& creditorganization.getCreditCardIssue() != "") {
					creditorganization.setCreditCardInfoId(cciId);
					int creditOrgId = creditOrganizationDAO
							.addCreditOrganization(creditorganization);
					creditorganization.setCreditOrgId(creditOrgId);
					tempCreditOrganizations.add(creditorganization);
				}
			}
			creditCardInfoVoTemp.setCreditOrganization(tempCreditOrganizations);
		}
		return creditCardInfoVoTemp;
	}

	@Override
	public List<CreditCardInfo> selectCreditCardInfo(int borrowerServiceAppId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteCreditCradInfo(int borrowerServiceAppId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CreditCardInfoVo selectByBorrowerServiceAppId(int borrowerServiceAppId) {
		CreditCardInfoVo creditCardInfoVo = new CreditCardInfoVo();
		CreditCardInfo creditCardInfo = creditCardInfoDAO
				.selectByBorrowerServiceAppId(borrowerServiceAppId);
		creditCardInfoVo.setCreditCardInfo(creditCardInfo);

		List<CreditOrganization> creditOrganizations = new ArrayList<CreditOrganization>();
		if (creditCardInfo != null) {
			try {
				creditOrganizations = creditOrganizationDAO
						.selectCreditOrganization(creditCardInfo.getCreditCardInfoId());
				creditCardInfoVo.setCreditOrganization(creditOrganizations);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return creditCardInfoVo;
	}
}
