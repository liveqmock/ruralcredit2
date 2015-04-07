package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IFamilydepositdebtDao;
import com.creditease.rc.domain.Familydepositdebt;
import com.creditease.rc.service.IFamilydepositdebtService;
import com.creditease.rc.util.CommonUtil;

/**
 * 
 * @author haoqiang
 * 
 */
@Service
public class FamilydepositdebtService implements IFamilydepositdebtService {

	@Resource
	private IFamilydepositdebtDao familydepositdebtDao;

	public IFamilydepositdebtDao getFamilydepositdebtDao() {
		return familydepositdebtDao;
	}

	public void setFamilydepositdebtDao(IFamilydepositdebtDao familydepositdebtDao) {
		this.familydepositdebtDao = familydepositdebtDao;
	}

	@Override
	public boolean batchInsert(List<Familydepositdebt> addfamilydepositdebts) {
		// TODO Auto-generated method stub
		familydepositdebtDao.batchFamilydepositdebtList(addfamilydepositdebts);
		return true;
	}

	@Override
	public List<Familydepositdebt> searchfamilydepositdebtList(int borrowerSurveyId) {
		// TODO Auto-generated method stub
		List<Familydepositdebt> familydepositdebts = familydepositdebtDao
				.querySelectFamilydepositdebt(borrowerSurveyId);
		return familydepositdebts;
	}

	@Override
	public boolean updateFamilyDepositDebtsList(List<Familydepositdebt> familydepositdebts) {
		List<Familydepositdebt> updatefamilydepositdebts = new ArrayList<Familydepositdebt>();
		for (int i = 0; i < familydepositdebts.size(); i++) {
			Familydepositdebt temp = familydepositdebts.get(i);
			String bank = temp.getBank();
			if (bank != null && !"".equals(bank.trim())) {
				updatefamilydepositdebts.add(temp);
			}
		}
		familydepositdebtDao.batchUpdateFamilyDepositDebtsList(updatefamilydepositdebts);
		return true;
	}

	@Override
	public boolean addFamilyDepositDebtsList(List<Familydepositdebt> familydepositdebts) {
		// TODO Auto-generated method stub
		List<Familydepositdebt> addfamilydepositdebts = new ArrayList<Familydepositdebt>();
		for (int i = 0; i < familydepositdebts.size(); i++) {
			Familydepositdebt temp = familydepositdebts.get(i);
			String bank = temp.getBank();
			if (bank != null && !"".equals(bank.trim())) {
				addfamilydepositdebts.add(temp);
			}
		}
		familydepositdebtDao.batchInsertfamilydepositdebtList(addfamilydepositdebts);
		return true;
	}

	@Override
	public boolean insertFamilyDepositDebtsList(List<Familydepositdebt> familydepositdebts) {
		// TODO Auto-generated method stub
		familydepositdebtDao.batchInsertfamilydepositdebtList(familydepositdebts);
		return true;
	}

	// 对储蓄债权债务进行操作
	@Override
	public List<Familydepositdebt> saveFamilydepositdebts(List<Familydepositdebt> familydepositdebtList) {
		// TODO Auto-generated method stub
		// 创建一个临时的FamilydepositdebtList用来实现后续的操作
		Integer borrowerServiceAppId = familydepositdebtList.get(0).getBorrowerServiceAppId();
		List<Familydepositdebt> insertFamilydepositdebtList = new ArrayList<Familydepositdebt>();
		List<Familydepositdebt> updateFamilydepositdebtList = new ArrayList<Familydepositdebt>();
		List<Integer> deleteFamilydepositdebtList = new ArrayList<Integer>();
		for (int i = 0; i < familydepositdebtList.size(); i++) {
			// 把从前Controller传过来的familydepositdebtList中的元素get到一个一个去判断
			Familydepositdebt tempfamilydepositdebt = familydepositdebtList.get(i);
			// 得到familyDepositDebtId
			Integer familyDepositDebtId = tempfamilydepositdebt.getFamilyDepositDebtId();
			String bank = tempfamilydepositdebt.getBank();
			Double amount = tempfamilydepositdebt.getAmount();
			// String depositDebtType = tempfamilydepositdebt.getDepositDebtType();

			// 如果familyDepositDebtId==null则做插入操作
			if (familyDepositDebtId == null) {
				if ((bank == null || "".equals(bank.trim())) && amount == null) {
				} else {
					insertFamilydepositdebtList.add(tempfamilydepositdebt);
				}
				// 如果familyDepositDebtId!=null则可能是修改或者删除操作
			} else {
				// 如果输入的全是空则是删除操作
				if ((bank == null || "".equals(bank.trim())) && amount == null) {
					deleteFamilydepositdebtList.add(tempfamilydepositdebt.getFamilyDepositDebtId());
					// 如果输入的不是空就是修改操作
				} else {
					updateFamilydepositdebtList.add(tempfamilydepositdebt);
				}
			}

		}
		if (CommonUtil.isNotEmpty(insertFamilydepositdebtList)) {
			familydepositdebtDao.batchInsert("familydepositdebt.insertIntofamilydepositdebt",
					insertFamilydepositdebtList);
		}
		if (CommonUtil.isNotEmpty(updateFamilydepositdebtList)) {
			familydepositdebtDao.batchUpdate("familydepositdebt.updatefamilydepositdebtByPrimaryKey",
					updateFamilydepositdebtList);
		}
		if (CommonUtil.isNotEmpty(deleteFamilydepositdebtList)) {
			familydepositdebtDao.batchDelete("familydepositdebt.deletefamilydepositdebtByPrimaryKey",
					deleteFamilydepositdebtList);
		}
		List<Familydepositdebt> returnFamilydepositdebts = searchFamilydepositdebts(borrowerServiceAppId);
		return returnFamilydepositdebts;
	}

	// 对储蓄债权债务进行查询
	@Override
	public List<Familydepositdebt> searchFamilydepositdebts(Integer borrowerServiceAppId) {
		// TODO Auto-generated method stub
		List<Familydepositdebt> getFamilydepositdebts = (List<Familydepositdebt>) familydepositdebtDao.queryList(
				"familydepositdebt.selectfamilydepositdebtByBorrowerServiceAppId", borrowerServiceAppId);

		List<Familydepositdebt> returnFamilydepositdebts = new ArrayList<Familydepositdebt>();
		returnFamilydepositdebts = packagingFamilydepositdebtList(returnFamilydepositdebts, borrowerServiceAppId);
		/*
		 * for (int i = 0; i < 2; i++) {
		 * Familydepositdebt familydepositdebt0 = new Familydepositdebt();
		 * familydepositdebt0.setBorrowerServiceAppId(borrowerServiceAppId);
		 * familydepositdebt0.setDepositDebtType("5");
		 * returnFamilydepositdebts.add(familydepositdebt0);
		 * Familydepositdebt familydepositdebt1 = new Familydepositdebt();
		 * familydepositdebt1.setBorrowerServiceAppId(borrowerServiceAppId);
		 * familydepositdebt1.setDepositDebtType("4");
		 * returnFamilydepositdebts.add(familydepositdebt1);
		 * Familydepositdebt familydepositdebt2 = new Familydepositdebt();
		 * familydepositdebt2.setBorrowerServiceAppId(borrowerServiceAppId);
		 * familydepositdebt2.setDepositDebtType("6");
		 * returnFamilydepositdebts.add(familydepositdebt2);
		 * Familydepositdebt familydepositdebt3 = new Familydepositdebt();
		 * familydepositdebt3.setBorrowerServiceAppId(borrowerServiceAppId);
		 * familydepositdebt3.setDepositDebtType("3");
		 * returnFamilydepositdebts.add(familydepositdebt3);
		 * 
		 * }
		 */
		int depositDebtType5Count = 0;
		int depositDebtType4Count = 0;
		int depositDebtType6Count = 0;
		int depositDebtType3Count = 0;
		if (CommonUtil.isNotEmpty(getFamilydepositdebts)) {
			for (int i = 0; i < getFamilydepositdebts.size(); i++) {
				Familydepositdebt tempFamilydepositdebt = getFamilydepositdebts.get(i);
				String depositDebtType = tempFamilydepositdebt.getDepositDebtType();
				if ("5".equals(depositDebtType.trim())) {
					if (depositDebtType5Count == 0) {
						returnFamilydepositdebts.set(0, tempFamilydepositdebt);
						depositDebtType5Count++;
					} else {
						returnFamilydepositdebts.set(4, tempFamilydepositdebt);
					}
				} else if ("4".equals(depositDebtType.trim())) {
					if (depositDebtType4Count == 0) {
						returnFamilydepositdebts.set(1, tempFamilydepositdebt);
						depositDebtType4Count++;
					} else {
						returnFamilydepositdebts.set(5, tempFamilydepositdebt);
					}
				} else if ("6".equals(depositDebtType.trim())) {
					if (depositDebtType6Count == 0) {
						returnFamilydepositdebts.set(2, tempFamilydepositdebt);
						depositDebtType6Count++;
					} else {
						returnFamilydepositdebts.set(6, tempFamilydepositdebt);
					}
				} else if ("3".equals(depositDebtType.trim())) {
					if (depositDebtType3Count == 0) {
						returnFamilydepositdebts.set(3, tempFamilydepositdebt);
						depositDebtType3Count++;
					} else {
						returnFamilydepositdebts.set(7, tempFamilydepositdebt);
					}
				}
			}

		}
		return returnFamilydepositdebts;
	}

	/**
	 * 
	 * @param returnFamilydepositdebts 储蓄债权债务List
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 储蓄债权债务List
	 */
	private List<Familydepositdebt> packagingFamilydepositdebtList(List<Familydepositdebt> returnFamilydepositdebts,
			Integer borrowerServiceAppId) {
		for (int i = 0; i < 2; i++) {
			Familydepositdebt familydepositdebt0 = new Familydepositdebt();
			familydepositdebt0.setBorrowerServiceAppId(borrowerServiceAppId);
			familydepositdebt0.setDepositDebtType("5");
			returnFamilydepositdebts.add(familydepositdebt0);
			Familydepositdebt familydepositdebt1 = new Familydepositdebt();
			familydepositdebt1.setBorrowerServiceAppId(borrowerServiceAppId);
			familydepositdebt1.setDepositDebtType("4");
			returnFamilydepositdebts.add(familydepositdebt1);
			Familydepositdebt familydepositdebt2 = new Familydepositdebt();
			familydepositdebt2.setBorrowerServiceAppId(borrowerServiceAppId);
			familydepositdebt2.setDepositDebtType("6");
			returnFamilydepositdebts.add(familydepositdebt2);
			Familydepositdebt familydepositdebt3 = new Familydepositdebt();
			familydepositdebt3.setBorrowerServiceAppId(borrowerServiceAppId);
			familydepositdebt3.setDepositDebtType("3");
			returnFamilydepositdebts.add(familydepositdebt3);

		}
		return returnFamilydepositdebts;
	}
}
