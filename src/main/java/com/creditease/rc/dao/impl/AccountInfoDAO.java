package com.creditease.rc.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IAccountInfoDAO;
import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.framework.dao.impl.BaseDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.AccountInfoVo;
import com.creditease.rc.vo.AccountInfoVo2ICP;

/**
 * 
 * @author zhangman
 * 
 */
@Repository
public class AccountInfoDAO extends BaseDao implements IAccountInfoDAO {

	@Override
	public int addAccountInfo(AccountInfo accountInfo) {
		// TODO Auto-generated method stub
		  try {
			return (Integer) insertObject("ACCOUNTINFO.insert", accountInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	 @Override
	public int updateAcount(AccountInfo accountInfo) {
		 if(accountInfo.getAccountInfoId()!= null){
			 return (Integer)  update("ACCOUNTINFO.update", accountInfo);
		 }else{
			 return 0;
		 }
	}
	@Override
	public Pagination selectAccountInfos(AccountInfo accountInfo, Pagination pagination) {
		return queryForPaginatedList("ACCOUNTINFO.selectByPage", "ACCOUNTINFO.count", accountInfo,
				pagination.getStartResult(), pagination.getPageSize());
	}

	@Override
	public Pagination searchFuzzy(String fuzzyValue, Pagination pagination,String accountType) {
		Map mapValue = new HashMap();
		mapValue.put("fuzzyValue", fuzzyValue);
		mapValue.put("accountType", accountType);
		return queryForPaginatedList("ACCOUNTINFO.selectFuzzy", "ACCOUNTINFO.countFuzzy", mapValue,
				pagination.getStartResult(), pagination.getPageSize());
	}

	@Override
	public AccountInfo select(AccountInfo accountInfo) {
		return (AccountInfo) queryUnique("ACCOUNTINFO.select", accountInfo);
	}
	
	@Override
	public List<AccountInfo> selectById(AccountInfo accountInfo) {
		return (List<AccountInfo>)queryList("ACCOUNTINFO.selectByCondition", accountInfo);
	}
	
	@Override
	public AccountInfo selectByAccountID(int accountInfoId) {
		return (AccountInfo) queryUnique("ACCOUNTINFO.selectById", accountInfoId);
	}

	@Override
	public AccountInfo selectByCrediApplicationId(Long creditApplicationId) {
		return (AccountInfo) queryUnique("ACCOUNTINFO.selectByCrediApplicationId", creditApplicationId);
	}
	
	@Override
	public AccountInfo selectReturnAccount(Long creditApplicationId) {
		return (AccountInfo) queryUnique("ACCOUNTINFO.selectReturnAccount", creditApplicationId);
	}
	
	/**
	 * 按接待员 （信贷员 id 修改）
	 * @param customerConsult  CustomerConsult
	 * @return int
	 */
	
	@Override
	public int updateByoperaterId(AccountInfo accountInfo) {
		// TODO Auto-generated method stub
		return update("ACCOUNTINFO.updateByoperaterId", accountInfo);
	}
	
	/**
	 *  按接待员 （信贷员 id 分组）
	 * @return List<CustomerConsult>
	 */
	@Override
	public List<AccountInfo> selectByoperaterId() {
		// TODO Auto-generated method stub
		return (List<AccountInfo>) queryList("ACCOUNTINFO.selectByoperaterId");
	}
	
	@Override
	public List<AccountInfo> selectAccountInfo(AccountInfo accountInfo) {
		// TODO Auto-generated method stub
		return (List<AccountInfo>) queryList("ACCOUNTINFO.selectByPage", accountInfo);
	}
	
	@Override
	public List<AccountInfoVo> selectAccountAddress(AccountInfo accountInfo) {
		return (List<AccountInfoVo>) queryList("ACCOUNTINFO.selectAccountAddress", accountInfo);
	}

	@Override
	public void insertOrUpdate(AccountInfoVo2ICP accountInfo) {

		update("ACCOUNTINFO.insertOrUpdate", accountInfo);
	}
}
