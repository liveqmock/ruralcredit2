package com.creditease.rc.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IFinanceMoneyDao;
import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.AmountConfirm;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.FinanceMoney;
import com.creditease.rc.framework.dao.impl.BaseDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.vo.ReceivedRecordVo;

/**
 * 
 * @author Administrator
 * 
 */
@Repository
public class FinanceMoneyDao extends BaseDao implements IFinanceMoneyDao {

	@Override
	public void insertFinanceMoney(FinanceMoney record) {
		this.insert("FINANCEMONEY.insertFinanceMoney", record);
	}

	@Override
	public int updateFinanceMoney(FinanceMoney record) {
		int rows = this.update("FINANCEMONEY.updateFinanceMoneyPayment", record);
		return rows;
	}

	@Override
	public int updateFinanceMoneyStatus(FinanceMoney record) {
		int rows = this.update("FINANCEMONEY.updateFinanceMoneyStatus", record);
		return rows;
	}

	@Override
	public FinanceMoney selectPaymentFinanceMoney(CreditApplication parameter) {
		// 涉及到两个表,所以得查两次
		FinanceMoney record = (FinanceMoney) this.queryUnique("FINANCEMONEY.selectPaymentFinanceMoney", parameter);
		if (null == record) {
			record = (FinanceMoney) this
					.queryUnique("FINANCEMONEY.selectPaymentFinanceMoneyFromAmountTable", parameter);
		}
		return record;
	}

	@Override
	public Long getBizId() {
		return (Long) this.queryUnique("FINANCEMONEY.getBizId");
	}

	@Override
	public List<FinanceMoney> selectPaymentFinanceMoneyList(FinanceMoney financeMoney) {
		List<FinanceMoney> record = (List<FinanceMoney>) this.queryList("FINANCEMONEY.selectPaymentFinanceMoneyList",
				financeMoney);
		if (CommonUtil.isEmpty(record)) {
			record = (List<FinanceMoney>) this.queryList("FINANCEMONEY.selectPaymentFinanceMoneyListFromAmountTable",
					financeMoney);
		}
		return record;
	}

	@Override
	public Pagination selectRecevieList(Pagination pagination, CreditApplication creditApplication) {
		Pagination pagination1 = this.queryForPaginatedList("FINANCEMONEY.selectReceiveList",
				"FINANCEMONEY.selectCountReceiveList", creditApplication, pagination.getStartResult(),
				pagination.getPageSize());
		return pagination1;
	}

	@Override
	public FinanceMoney selectReceiveFinanceMoney(FinanceMoney parameter) {
		FinanceMoney financeMoney = (FinanceMoney) this
				.queryUnique("FINANCEMONEY.selectReceiveFinanceMoney", parameter);
		return financeMoney;
	}

	@Override
	public void batchInsertReceiveFinanceMoney(List<FinanceMoney> financeMoneyList) {
		this.batchInsert("FINANCEMONEY.insertFinanceMoney", financeMoneyList);
	}

	@Override
	public void batchUpdateFinanceMoney(List<FinanceMoney> financeMoneyList) {
		this.batchUpdate("FINANCEMONEY.updateFinanceMoney", financeMoneyList);
	}
	@Override
	public void updateFinanceMoneyTYPEisF(List<FinanceMoney> financeMoneyList) {
		this.batchUpdate("FINANCEMONEY.updateFinanceMoneyTYPEisF", financeMoneyList);
	}
	@Override
	public List<FinanceMoney> selectOnline(List<Integer> receivedRecordIdList) {
		List<FinanceMoney> result = (List<FinanceMoney>) this.queryList("FINANCEMONEY.selectOnline",
				receivedRecordIdList);
		return result;
	}

	@Override
	public List<FinanceMoney> selectReceiveFinanceMoneyList(FinanceMoney financeMoney) {
		return (List<FinanceMoney>) this.queryList("FINANCEMONEY.selectReceiveFinanceMoneyList", financeMoney);
	}

	@Override
	public Integer selectCountReceiveFinanceMoneyList(FinanceMoney financeMoney) {
		return (Integer) this.queryUnique("FINANCEMONEY.selectCountReceiveFinanceMoneyList", financeMoney);
	}

	@Override
	public AccountInfo selectAccountInfo(Integer receivedRecordId) {
		AccountInfo accountInfo = (AccountInfo) this.queryUnique("FINANCEMONEY.selectAccountInfo", receivedRecordId);
		if (null == accountInfo) {
			accountInfo = (AccountInfo) this.queryUnique("FINANCEMONEY.selectAccountInfoForFinanceBack",
					receivedRecordId);
		}
		return accountInfo;
	}

	@Override
	public AccountInfo selectAccountInfoForFinanceBack(Integer associationId) {
		return (AccountInfo) this.queryUnique("FINANCEMONEY.selectAccountInfoForFinanceBack", associationId);
	}

	@Override
	public List<ReceivedRecordVo> selectReceiveRecordList(List<Integer> receivedRecordId) {
		return (List<ReceivedRecordVo>) this.queryList("FINANCEMONEY.selectReceiveRecordList", receivedRecordId);
	}

	@Override
	public FinanceMoney selectUndoFinanceMoney(Integer receiveRecordId) {
		FinanceMoney result = (FinanceMoney) this.queryUnique("FINANCEMONEY.selectUndoFinanceMoney", receiveRecordId);
		return result;
	}

	@Override
	public int updateFinanceMoneyById(FinanceMoney record) {
		return this.update("FINANCEMONEY.updateFinanceMoneyById", record);
	}

	@Override
	public List<FinanceMoney> selectFinanceMoneyListByBizId(Long bizId) {
		String bizIdString = String.valueOf(bizId);
		return (List<FinanceMoney>) this.queryList("FINANCEMONEY.selectFinanceMoneyListByBizId", bizIdString);
	}

	@Override
	public boolean updateFinanceMoneyByBizId(FinanceMoney financeMoney) {
		int count = this.update("FINANCEMONEY.updateFinanceMoneyByBizId", financeMoney);
		if (0 < count) {
			return true;
		}
		return false;
	}

	@Override
	public FinanceMoney selectFinanceMoney(FinanceMoney financeMoney) {
		FinanceMoney result = (FinanceMoney) this.queryUnique("FINANCEMONEY.selectFinanceMoney", financeMoney);
		if (null == result) {
			result = (FinanceMoney) this
					.queryUnique("FINANCEMONEY.selectFinanceMoneyForFinanceMoneyBack", financeMoney);
		}
// if(null==result){
// result=(FinanceMoney) this.queryUnique("FINANCEMONEY.selectFinanceMoneyForFinanceMoneyBack2", financeMoney);
// }
		return result;
	}

	@Override
	public AmountConfirm selectFinanceMoneyBack(AmountConfirm parameter) {
		AmountConfirm amountConfirm = (AmountConfirm) this.queryUnique("AmountConfirm.selectFinanceMoneyBack",
				parameter);
		return amountConfirm;
	}

	@Override
	public FinanceMoney selectFinanceMoneyByBizId(FinanceMoney financeMoney) {
		return (FinanceMoney) queryUnique("FINANCEMONEY.selectBizId", financeMoney);
	}

	@Override
	public Integer selectIsDuaplicatAccount(List<Integer> list) {
		return (Integer) queryUnique("FINANCEMONEY.selectIsDuaplicatAccount", list);
	}

	@Override
	public FinanceMoney selectFinanceMoneyByCondition(FinanceMoney financeMoney) {
		return (FinanceMoney) queryUnique("FINANCEMONEY.selectFinanceMoneyByCondition", financeMoney);
	}

	@Override
	public Pagination selectBackSectionList(Pagination pagination, CreditApplication creditApplication) {
		Pagination pagination1 = this.queryForPaginatedList("FINANCEMONEY.selectBackSectionList",
				"FINANCEMONEY.selectCountBackSectionList", creditApplication, pagination.getStartResult(),
				pagination.getPageSize());
		return pagination1;
	}

	@Override
	public List<String> queryAppointingReseive() {
		// TODO Auto-generated method stub
		return (List<String>) queryList("FINANCEMONEY.queryAppointingReseive");
	}

	@Override
	public boolean updateFinanceMoneyStatusAndReturnMSG(List<Map<String, String>> maps) {
		// TODO Auto-generated method stub
		batchUpdate("FINANCEMONEY.updateFinanceMoneyStatusAndReturnMSG", maps);
		return true;
	}

	@Override
	public boolean updateReceivedRecordStatusBybizId(List<Map<String, String>> maps) {
		// TODO Auto-generated method stub
		batchUpdate("FINANCEMONEY.updateReceivedRecordStatusBybizId", maps);
		return true;
	}

	@Override
	public Pagination selectRecevieListHQ(Pagination pagination, CreditApplication creditApplication) {
		// TODO Auto-generated method stub
		Pagination pagination1 = this.queryForPaginatedList("FINANCEMONEY.selectReceiveListHQ",
				"FINANCEMONEY.selectCountReceiveListHQ", creditApplication, pagination.getStartResult(),
				pagination.getPageSize());
		return pagination1;
	}

	@Override
	public List<String> queryBizIdListByReceivedRecordIdList(List<Integer> receivedRecordIdList) {
		// TODO Auto-generated method stub
		return (List<String>) queryList("FINANCEMONEY.queryBizIdListByReceivedRecordIdList", receivedRecordIdList);
	}

	@Override
	public void updateHistory(Long creditapplicationId) {
		update("FINANCEMONEY.updateHistory", creditapplicationId);
		
	}

	@Override
	public int updateIcp(String creditapplicationId) {
		return update("FINANCEMONEY.updateIcp", creditapplicationId);
	}
	
	
	

}