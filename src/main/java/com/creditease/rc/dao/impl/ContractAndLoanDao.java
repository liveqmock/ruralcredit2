package com.creditease.rc.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IContractAndLoanDao;
import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.AmountConfirm;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.LendingConfiguration;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.ContractRateForQYResult;
import com.creditease.rc.vo.LoanConfirmMessageVo;

@Repository
public class ContractAndLoanDao implements IContractAndLoanDao {

	@Resource
	private IBaseDao baseDao;

	@Override
	public Pagination contractDateGrid(Map<String, String> queryMap, Pagination pagination) {
		// TODO Auto-generated method stub
		return baseDao.queryForPaginatedList("CONTRACTANDLOAN.queryContractDateGrid", "CONTRACTANDLOAN.countContractDateGrid", queryMap, pagination.getStartResult(), pagination.getPageSize());
	}

	@Override
	public ContractRateForQYResult qyContractRateForQYResult(Long creditapplicationId) {
		// TODO Auto-generated method stub
		return (ContractRateForQYResult) baseDao.queryUnique("CONTRACTANDLOAN.qyContractRateForQYResult", creditapplicationId);
	}

	@Override
	public int updatePrintContractState(Long creditapplicationId) {
		int rows = baseDao.update("CONTRACTANDLOAN.updatePrintContractState", creditapplicationId);
		return rows;
	}

	@Override
	public int updateFailContractState(Long creditapplicationId) {
		int rows = baseDao.update("CONTRACTANDLOAN.updateFailContractState", creditapplicationId);
		return rows;
	}

	@Override
	public int updateUploadingContractState(Long creditapplicationId) {
		int rows = baseDao.update("CONTRACTANDLOAN.updateUploadingContractState", creditapplicationId);
		return rows;
	}

	@Override
	public LoanConfirmMessageVo searchForConfirm(Long creditapplicationId) {
		return (LoanConfirmMessageVo) baseDao.queryUnique("CONTRACTANDLOAN.searchForConfirm",creditapplicationId);
	}

	@Override
	public AmountConfirm searchForConfirmAmount(Long creditapplicationId) {
		return (AmountConfirm) baseDao.queryUnique("CONTRACTANDLOAN.searchForConfirmAmount",creditapplicationId);
	}

	@Override
	public CreditApplication searchForCreditApplicationStates(Long creditapplicationId) {
		// TODO Auto-generated method stub
		return (CreditApplication) baseDao.queryUnique("CONTRACTANDLOAN.searchForCreditApplicationStates",creditapplicationId);
	}

	@Override
	public List<Long> queryCreditIdListLoan() {
		// TODO Auto-generated method stub
		return (List<Long>) baseDao.queryList("CONTRACTANDLOAN.queryCreditIdListLoan");
	}

	@Override
	public LendingConfiguration searchConfigureChannel(Long creditapplicationId) {
		return (LendingConfiguration) baseDao.queryUnique("CONTRACTANDLOAN.searchConfigureChannel",creditapplicationId);
	}

	@Override
	public String selectByCaIdLf(Long creditapplicationId) {
		return (String) baseDao.queryUnique("CONTRACTANDLOAN.selectByCaIdLf", creditapplicationId);
	}
	

	@Override
	public AccountInfo searchForCompanyNameAndMobilePhone(
			Long creditapplicationId) {
		return (AccountInfo) baseDao.queryUnique("CONTRACTANDLOAN.searchForCompanyNameAndMobilePhone", creditapplicationId);
	}
	@Override
	public int updateWaitLoanContractState(Long creditapplicationId){
		int rows = baseDao.update("CONTRACTANDLOAN.updateWaitLoanContractState", creditapplicationId);
		return rows;
	}
	@Override
	public int updateSupplementaryDataContractStateHistryFlag(Long creditapplicationId){
		int rows=baseDao.update("CONTRACTANDLOAN.updateSupplementaryDataContractStateHistryFlag",creditapplicationId);
		return rows;
	}

	@Override
	public int updateWaitContractAgainCheckState(Long creditapplicationId) {
		int rows=baseDao.update("CONTRACTANDLOAN.updateWaitContractAgainCheckState",creditapplicationId);
		return rows;
	}

	@Override
	public int updateChangeLoanChannelXinTuo(Long creditapplicationId) {
		int rows=baseDao.update("CONTRACTANDLOAN.updateChangeLoanChannelXinTuo", creditapplicationId);
		return rows;
	}
}
