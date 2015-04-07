package com.creditease.rc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IAmountConfirmDAO;
import com.creditease.rc.domain.AmountConfirm;
import com.creditease.rc.framework.dao.impl.BaseDao;
import com.creditease.rc.vo.AmountConfirmVo;
/**
 * 
 * @author zhangman
 *
 */
@Repository
public class AmountConfirmDAO extends BaseDao implements IAmountConfirmDAO {

	@Override
	public long addAmountConfirm(AmountConfirm amountConfirm) {
		// TODO Auto-generated method stub
		return  (Long) insertObject("AmountConfirm.insert", amountConfirm);
	}

	@Override
	public int updateAmountConfirm(AmountConfirm amountConfirm) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAmountConfirm(int creditapplicationId) {
		// TODO Auto-generated method stub
		return update("AmountConfirm.updateBycreditapplicationId", creditapplicationId);
	}

	@Override
	public List<AmountConfirm> searchAmountConfirm(AmountConfirm amountConfirm) {
		// TODO Auto-generated method stub
		return (List<AmountConfirm>) queryList("AmountConfirm.selectAmounts", amountConfirm);
	}
	@Override
	public AmountConfirm selectNew(Integer creditapplicationId) {
		// TODO Auto-generated method stub
		return (AmountConfirm) queryUnique("AmountConfirm.selectNew", creditapplicationId);
	}
	@Override
	public AmountConfirmVo selectAmount(Integer creditapplicationId) {
		// TODO Auto-generated method stub
		return (AmountConfirmVo) queryUnique("AmountConfirm.selectAmount", creditapplicationId);
	}

	/**
	 * 根据申请单号查询该单号的额度确认数据
	 * @param creditApplicationId
	 * @return
	 */
	@Override
	public List<AmountConfirm> selectAmountsByCreditAppId(
			Integer creditApplicationId) {
		List l = (List<AmountConfirm>) queryList("AmountConfirm.selectAmountsByCreditAppId", creditApplicationId);
		return l;
	}

	@Override
	public int updateByAmountConfirmId(String amountConfirmId) {
		int i = update("AmountConfirm.updateToHisByAmountConfirmId", Integer.valueOf(amountConfirmId));
		return i;
	}
	@Override
	public int updateBeginInterestTime(AmountConfirm amountConfirm){
		return update("GROUPLOANREGIST.updateGroupLoanRegistConfirm",amountConfirm);
	}

	@Override
	public String selectLendingChannel(Long creditApplicationId) {
		return (String) queryUnique("AmountConfirm.selectLendingChannel", creditApplicationId);
	}

	@Override
	public String selectLendingChannelByApplyId(String applyId) {
		return (String) queryUnique("AmountConfirm.selectLendingChannelByApplyId", applyId);
	}

	@Override
	public int selectIsExist(AmountConfirm amountConfirm) {
		return (Integer) queryUnique("AmountConfirm.selectIsExist", amountConfirm);
	}

	@Override
	public int updateAmountConfirmClass(AmountConfirm amountConfirm) {
		return update("AmountConfirm.updateAmountConfirmClass", amountConfirm);
	}

	/*@Override
	public int saveOrUpdate(AmountConfirm amountConfirm) {
		return update("AmountConfirm.saveOrUpdate", amountConfirm);
	}*/
}
