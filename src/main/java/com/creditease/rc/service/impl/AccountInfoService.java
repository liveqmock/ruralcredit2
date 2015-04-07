package com.creditease.rc.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.dao.IAccountInfoDAO;
import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.AmountConfirm;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IAccountInfoService;
import com.creditease.rc.service.IRural2CreditService;
import com.creditease.rc.util.SmpWSUtil;

/**
 * 
 * @author zhangman
 * 
 */
@Service
public class AccountInfoService implements IAccountInfoService {

	@Resource
	private IAccountInfoDAO accountInfoDAO;
	@Resource
	private SmpWSUtil smpWSUtil;
	@Resource
	private IRural2CreditService creditService;

	@Override
	public boolean addAccountInfo(AccountInfo accountInfo) {
		accountInfo.setOperaterId(SpringSecurityUtils.getCurrentUser().getUserId());
		accountInfo.setOperaterName(SpringSecurityUtils.getCurrentUser().getName_zh());
		int accountInfoId = accountInfoDAO.addAccountInfo(accountInfo);
		if (accountInfoId != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int addAccount(AccountInfo accountInfo) {
		accountInfo.setOperaterId(SpringSecurityUtils.getCurrentUser().getUserId());
		accountInfo.setOperaterName(SpringSecurityUtils.getCurrentUser().getName_zh());
		return accountInfoDAO.addAccountInfo(accountInfo);
	}

	@Override
	public Pagination selectAccountInfos(AccountInfo accountInfo, Pagination pagination,String accountType) {
		return accountInfoDAO.selectAccountInfos(accountInfo, pagination);
	}

	@Override
	public boolean updateAccountInfo(AccountInfo accountInfo) {
		Message message = creditService.chgReturnType(accountInfo);
		
		if(message.isSuccess()){
			accountInfo.setOperaterId(SpringSecurityUtils.getCurrentUser().getUserId());
			accountInfo.setOperaterName(SpringSecurityUtils.getCurrentUser().getName_zh());
			int rows = accountInfoDAO.update("ACCOUNTINFO.update", accountInfo);
			if (rows > 0) {
				return true;
			} else {
				return false;
			}
		}else{
			return false;
		}
		
	}

	/**
	 * 重载方法  罗红杰
	 * 
	 */
	@Override
	public boolean updateAccountInfo(AccountInfo accountInfo,String type) {
		//Message message = creditService.chgReturnType(accountInfo);
		
		//if(message.isSuccess()){
			accountInfo.setOperaterId(SpringSecurityUtils.getCurrentUser().getUserId());
			accountInfo.setOperaterName(SpringSecurityUtils.getCurrentUser().getName_zh());
			int rows = accountInfoDAO.update("ACCOUNTINFO.update", accountInfo);
			if (rows > 0) {
				return true;
			} else {
				return false;
			}
		/*}else{
			return false;
		}*/
		
	}
	
	@Override
	public AccountInfo addOrUpdate(AccountInfo accountInfo) {
		accountInfo.setOperaterId(SpringSecurityUtils.getCurrentUser().getUserId());
		accountInfo.setOperaterName(SpringSecurityUtils.getCurrentUser().getName_zh());
		if (accountInfo.getAccountInfoId() != null) {
			Message message = creditService.chgReturnType(accountInfo);
			if(message.isSuccess()){
				accountInfoDAO.updateAcount(accountInfo);
				return accountInfo;
			}else{
				return null;
			}
			
		} else {
			int accountInfoId = accountInfoDAO.addAccountInfo(accountInfo);
			accountInfo.setAccountInfoId(accountInfoId);
			return accountInfo;
		}
	}

	@Override
	public Pagination selectFuzzy(String fuzzyValue, Pagination pagination,String accountType) {
		return accountInfoDAO.searchFuzzy(fuzzyValue, pagination,accountType);
	}

	@Override
	public List<AccountInfo> selectById(AccountInfo accountInfo) {
		return accountInfoDAO.selectById(accountInfo);
	}

	@Override
	public AccountInfo selectByAccountID(int accountInfoId) {
		return accountInfoDAO.selectByAccountID(accountInfoId);
	}

	@Override
	public AccountInfo selectByCrediApplicationId(Long creditApplicationId) {
		return accountInfoDAO.selectByCrediApplicationId(creditApplicationId);
	}

	@Override
	public List<AccountInfo> selectCardInfo(Integer creditapplicationId) {
		// TODO Auto-generated method stub
		List<AccountInfo> accountInfoList = (List<AccountInfo>) accountInfoDAO.queryList(
				"ACCOUNTINFO.selectByCreditApplicationId", creditapplicationId);
		return accountInfoList;
	}
	@Override
	public List<AccountInfo> selectCompanyAccounts(Integer creditapplicationId) {
		// TODO Auto-generated method stub
		List<AccountInfo> accountInfoList = (List<AccountInfo>) accountInfoDAO.queryList(
				"ACCOUNTINFO.selectCompanyAccounts", creditapplicationId);
		return accountInfoList;
	}
	@Override
	public AccountInfo selectReturnAccount(Long creditApplicationId) {
		return accountInfoDAO.selectReturnAccount(creditApplicationId);
	}
	/**
	 * 更新 同步 smp
	 * @param accountInfo accountInfo
	 * @return Message
	 */
	@Override
	public Message updateSmp(AccountInfo accountInfo) {
		Message message = new Message();
		int result = 0;
			Map<String, String> map = smpWSUtil.getAreaDepartmentNameMapByLoanOfficerId(accountInfo.getOperaterId());
			Set<Map.Entry<String, String>> entries = map.entrySet();
			for (Entry<String, String> entry : entries) {
				accountInfo.setDepartmentId(entry.getKey());
				accountInfo.setBranchName(entry.getValue());
				
				if(accountInfo.getDepartmentId() != null
						&& !"".equals(accountInfo.getDepartmentId())
						&& accountInfo.getBranchName()!= null
						&&!"".equals(accountInfo.getBranchName())){
					result = accountInfoDAO.updateByoperaterId(accountInfo);
				}
			}
		if(result> 0){
			message.setSuccess(true);
		}
		return message;
	}
	/**
	 *  批量 同步smp
	 * @return Message
	 */
	@Override
	public Message updateSmpAll() {
		Message message = new Message();
		List<AccountInfo> accountInfos = accountInfoDAO.selectByoperaterId();
		int result = 0;
		for (AccountInfo accountInfo : accountInfos) {
			Map<String, String> map = smpWSUtil.getAreaDepartmentNameMapByLoanOfficerId(accountInfo.getOperaterId());
			Set<Map.Entry<String, String>> entries = map.entrySet();
			for (Entry<String, String> entry : entries) {
				accountInfo.setDepartmentId(entry.getKey());
				accountInfo.setBranchName(entry.getValue());
				
				if(accountInfo.getDepartmentId() != null
						&& !"".equals(accountInfo.getDepartmentId())
						&& accountInfo.getBranchName() != null
						&&!"".equals(accountInfo.getBranchName())){
					int count = accountInfoDAO.updateByoperaterId(accountInfo);
					result  = result +count;
				}
			}
		}
		if(result> 0){
			message.setSuccess(true);
		}
		return message;
	}
	
	@Override
	public AccountInfo selctAccountInfo(AccountInfo accountInfo) {
		// TODO Auto-generated method stub
		List<AccountInfo> accountInfos = accountInfoDAO.selectAccountInfo(accountInfo);
		if(accountInfos.size() > 0 ){
			return accountInfos.get(0);
		}else{
			return null;
		}
	}
}
