package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IFamilyotherincomeDao;
import com.creditease.rc.domain.Familyotherincome;
import com.creditease.rc.service.IFamilyotherincomeService;
import com.creditease.rc.util.CommonUtil;

/**
 * 
 * @author haoqiang
 * 
 */
@Service
public class FamilyotherincomeService implements IFamilyotherincomeService {
	@Resource
	private IFamilyotherincomeDao familyotherincomeDao;

	public IFamilyotherincomeDao getFamilyotherincomeDao() {
		return familyotherincomeDao;
	}

	public void setFamilyotherincomeDao(IFamilyotherincomeDao familyotherincomeDao) {
		this.familyotherincomeDao = familyotherincomeDao;
	}

	@Override
	public boolean batchInsert(List<Familyotherincome> addfamilyotherincomes) {
		// TODO Auto-generated method stub
		familyotherincomeDao.batchFamilyotherincomeList(addfamilyotherincomes);
		return true;
	}

	@Override
	public List<Familyotherincome> searchfamilyotherincomeList(int borrowerSurveyId) {
		// TODO Auto-generated method stub
		List<Familyotherincome> familyotherincomes = familyotherincomeDao
				.querySelectFamilyotherincome(borrowerSurveyId);
		return familyotherincomes;

	}

	@Override
	public boolean updateFamilyOtherIncomeList(List<Familyotherincome> familyotherincomes) {
		// TODO Auto-generated method stub
		List<Familyotherincome> updatefamilyotherincomes = new ArrayList<Familyotherincome>();
		for (int i = 0; i < familyotherincomes.size(); i++) {
			Familyotherincome temp = familyotherincomes.get(i);
			String otherIncomeType = temp.getOtherIncomeType();
			if (otherIncomeType != null && !"".equals(otherIncomeType.trim())) {
				updatefamilyotherincomes.add(temp);
			}
		}
		familyotherincomeDao.batchUpdateFamilyOtherIncomeList(updatefamilyotherincomes);
		return true;
	}

	@Override
	public boolean addFamilyOtherIncomeList(List<Familyotherincome> familyotherincomes) {
		// TODO Auto-generated method stub
		List<Familyotherincome> addfamilyotherincomes = new ArrayList<Familyotherincome>();
		for (int i = 0; i < familyotherincomes.size(); i++) {
			Familyotherincome temp = familyotherincomes.get(i);
			String otherIncomeType = temp.getOtherIncomeType();
			if (otherIncomeType != null && !"".equals(otherIncomeType.trim())) {
				addfamilyotherincomes.add(temp);
			}
		}
		familyotherincomeDao.batchInsertfamilyotherincomeList(addfamilyotherincomes);
		return true;
	}

	@Override
	public boolean insertFamilyOtherIncomeList(List<Familyotherincome> familyotherincomes) {
		// TODO Auto-generated method stub
		familyotherincomeDao.batchInsertfamilyotherincomeList(familyotherincomes);
		return true;
	}

	// 对其他收入进行查询
	@Override
	public List<Familyotherincome> searchFamilyotherincomes(Integer borrowerServiceAppId) {
		// TODO Auto-generated method stub
		List<Familyotherincome> getFamilyotherincomes = (List<Familyotherincome>) familyotherincomeDao.queryList(
				"familyotherincome.selectfamilyotherincomeByBorrowerServiceAppId", borrowerServiceAppId);

		List<Familyotherincome> returnFamilyotherincomes = new ArrayList<Familyotherincome>();
		returnFamilyotherincomes = packagingFamilyotherincomeList(returnFamilyotherincomes, borrowerServiceAppId);
		/*
		 * Familyotherincome familyotherincome0 = new Familyotherincome();
		 * familyotherincome0.setBorrowerServiceAppId(borrowerServiceAppId);
		 * returnFamilyotherincomes.add(familyotherincome0);
		 * 
		 * Familyotherincome familyotherincome1 = new Familyotherincome();
		 * familyotherincome1.setBorrowerServiceAppId(borrowerServiceAppId);
		 * returnFamilyotherincomes.add(familyotherincome1);
		 * 
		 * Familyotherincome familyotherincome2 = new Familyotherincome();
		 * familyotherincome2.setBorrowerServiceAppId(borrowerServiceAppId);
		 * returnFamilyotherincomes.add(familyotherincome2);
		 * 
		 * Familyotherincome familyotherincome3 = new Familyotherincome();
		 * familyotherincome3.setBorrowerServiceAppId(borrowerServiceAppId);
		 * returnFamilyotherincomes.add(familyotherincome3);
		 */

		if (CommonUtil.isNotEmpty(getFamilyotherincomes)) {
			for (int i = 0; i < getFamilyotherincomes.size(); i++) {
				Familyotherincome tempFamilyotherincome = getFamilyotherincomes.get(i);
				returnFamilyotherincomes.set(i, tempFamilyotherincome);
			}

		}
		return returnFamilyotherincomes;
	}

	/**
	 * 
	 * @param returnFamilyotherincomes 其他收入List
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 其他收入List
	 */
	private List<Familyotherincome> packagingFamilyotherincomeList(List<Familyotherincome> returnFamilyotherincomes,
			Integer borrowerServiceAppId) {
		Familyotherincome familyotherincome0 = new Familyotherincome();
		familyotherincome0.setBorrowerServiceAppId(borrowerServiceAppId);
		returnFamilyotherincomes.add(familyotherincome0);

		Familyotherincome familyotherincome1 = new Familyotherincome();
		familyotherincome1.setBorrowerServiceAppId(borrowerServiceAppId);
		returnFamilyotherincomes.add(familyotherincome1);

		Familyotherincome familyotherincome2 = new Familyotherincome();
		familyotherincome2.setBorrowerServiceAppId(borrowerServiceAppId);
		returnFamilyotherincomes.add(familyotherincome2);

		Familyotherincome familyotherincome3 = new Familyotherincome();
		familyotherincome3.setBorrowerServiceAppId(borrowerServiceAppId);
		returnFamilyotherincomes.add(familyotherincome3);
		return returnFamilyotherincomes;
	}

	// 保存对家庭成员的操作
	@Override
	public List<Familyotherincome> saveFamilyotherincomes(List<Familyotherincome> familyotherincomeList) {
		// TODO Auto-generated method stub
		// 创建一个临时的FamilyotherincomeList用来实现后续的操作
		Integer borrowerServiceAppId = familyotherincomeList.get(0).getBorrowerServiceAppId();
		List<Familyotherincome> insertFamilyotherincomeList = new ArrayList<Familyotherincome>();
		List<Familyotherincome> updateFamilyotherincomeList = new ArrayList<Familyotherincome>();
		List<Integer> deleteFamilyotherincomeList = new ArrayList<Integer>();
		for (int i = 0; i < familyotherincomeList.size(); i++) {
			// 把从前Controller传过来的FamilyotherincomeList中的元素get到一个一个去判断
			Familyotherincome tempFamilyotherincome = familyotherincomeList.get(i);
			// 得到FamilyotherincomeId
			Integer familyOtherIncomeId = tempFamilyotherincome.getFamilyOtherIncomeId();
			String otherIncomeType = tempFamilyotherincome.getOtherIncomeType();
			String qulitily = tempFamilyotherincome.getQulitily();
			String price = tempFamilyotherincome.getPrice();
			Double amount = tempFamilyotherincome.getAmount();
			// 如果FamilyotherincomeId==null则做插入操作
			if (familyOtherIncomeId == null) {
				if ((otherIncomeType == null || "".equals(otherIncomeType.trim()))
						&& (qulitily == null || "".equals(qulitily.trim()))
						&& (price == null || "".equals(price.trim())) && amount == null) {
				} else {
					insertFamilyotherincomeList.add(tempFamilyotherincome);
				}
				// 如果FamilyotherincomeId!=null则可能是修改或者删除操作
			} else {
				// 如果输入的全是空则是删除操作
				if ((otherIncomeType == null || "".equals(otherIncomeType.trim()))
						&& (qulitily == null || "".equals(qulitily.trim()))
						&& (price == null || "".equals(price.trim())) && amount == null) {
					deleteFamilyotherincomeList.add(tempFamilyotherincome.getFamilyOtherIncomeId());
					// 如果输入的不是空就是修改操作
				} else {
					updateFamilyotherincomeList.add(tempFamilyotherincome);
				}
			}

		}
		if (CommonUtil.isNotEmpty(insertFamilyotherincomeList)) {
			familyotherincomeDao.batchInsert("familyotherincome.insertfamilyotherincome", insertFamilyotherincomeList);
		}
		if (CommonUtil.isNotEmpty(updateFamilyotherincomeList)) {
			familyotherincomeDao.batchUpdate("familyotherincome.updatefamilyotherincomeByPrimaryKey",
					updateFamilyotherincomeList);
		}
		if (CommonUtil.isNotEmpty(deleteFamilyotherincomeList)) {
			familyotherincomeDao.batchDelete("familyotherincome.deletefamilyotherincomeByPrimaryKey",
					deleteFamilyotherincomeList);
		}
		List<Familyotherincome> returnFamilyotherincomes = searchFamilyotherincomes(borrowerServiceAppId);
		return returnFamilyotherincomes;
	}
}
