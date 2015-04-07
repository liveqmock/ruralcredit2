package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IFamilytotalcostDao;
import com.creditease.rc.domain.Familytotalcost;
import com.creditease.rc.service.IFamilytotalcostService;
import com.creditease.rc.util.CommonUtil;

/**
 * 
 * @author haoqiang
 * 
 */
@Service
public class FamilytotalcostService implements IFamilytotalcostService {

	@Resource
	private IFamilytotalcostDao familytotalcostDao;

	public IFamilytotalcostDao getFamilytotalcostDao() {
		return familytotalcostDao;
	}

	public void setFamilytotalcostDao(IFamilytotalcostDao familytotalcostDao) {
		this.familytotalcostDao = familytotalcostDao;
	}

	@Override
	public boolean batchInsert(List<Familytotalcost> addfamilytotalcosts) {
		// TODO Auto-generated method stub
		familytotalcostDao.batchFamilytotalcostList(addfamilytotalcosts);
		return true;
	}

	@Override
	public List<Familytotalcost> searchfamilytotalcostList(int borrowerSurveyId) {
		// TODO Auto-generated method stub
		List<Familytotalcost> familytotalcosts = familytotalcostDao.querySelectFamilytotalcost(borrowerSurveyId);
		return familytotalcosts;
	}

	@Override
	public boolean updateFamilyTotalCostList(List<Familytotalcost> familytotalcosts) {
		// TODO Auto-generated method stub
		List<Familytotalcost> updatefamilytotalcosts = new ArrayList<Familytotalcost>();
		for (int i = 0; i < familytotalcosts.size(); i++) {
			Familytotalcost temp = familytotalcosts.get(i);
			Double amount = temp.getAmount();
			if (amount != null) {
				updatefamilytotalcosts.add(temp);
			}
		}
		familytotalcostDao.batchUpdateFamilytotalcostList(updatefamilytotalcosts);
		return true;
	}

	@Override
	public boolean addFamilyTotalCostList(List<Familytotalcost> familytotalcosts) {
		// TODO Auto-generated method stub
		List<Familytotalcost> addfamilytotalcosts = new ArrayList<Familytotalcost>();
		for (int i = 0; i < familytotalcosts.size(); i++) {
			Familytotalcost temp = familytotalcosts.get(i);
			Double amount = temp.getAmount();
			if (amount != null) {
				addfamilytotalcosts.add(temp);
			}
		}
		familytotalcostDao.batchInsertfamilytotalcostList(addfamilytotalcosts);
		return true;
	}

	@Override
	public boolean insertFamilyTotalCostList(List<Familytotalcost> familytotalcosts) {
		// TODO Auto-generated method stub
		familytotalcostDao.batchInsertfamilytotalcostList(familytotalcosts);
		return true;
	}

	// 对日常总开支进行查询
	@Override
	public List<Familytotalcost> searchFamilytotalcosts(Integer borrowerServiceAppId) {
		// TODO Auto-generated method stub
		List<Familytotalcost> getFamilytotalcosts = (List<Familytotalcost>) familytotalcostDao.queryList(
				"familytotalcost.selectfamilytotalcostByBorrowerServiceAppId", borrowerServiceAppId);

		List<Familytotalcost> returnFamilytotalcosts = new ArrayList<Familytotalcost>();

		returnFamilytotalcosts = packagingFamilytotalcostList(returnFamilytotalcosts, borrowerServiceAppId);
		/*
		 * Familytotalcost familytotalcost0 = new Familytotalcost();
		 * familytotalcost0.setBorrowerServiceAppId(borrowerServiceAppId);
		 * familytotalcost0.setTotalCostType("1");
		 * returnFamilytotalcosts.add(familytotalcost0);
		 * 
		 * Familytotalcost familytotalcost1 = new Familytotalcost();
		 * familytotalcost1.setBorrowerServiceAppId(borrowerServiceAppId);
		 * familytotalcost1.setTotalCostType("3");
		 * returnFamilytotalcosts.add(familytotalcost1);
		 * 
		 * Familytotalcost familytotalcost2 = new Familytotalcost();
		 * familytotalcost2.setBorrowerServiceAppId(borrowerServiceAppId);
		 * familytotalcost2.setTotalCostType("4");
		 * returnFamilytotalcosts.add(familytotalcost2);
		 * 
		 * Familytotalcost familytotalcost3 = new Familytotalcost();
		 * familytotalcost3.setBorrowerServiceAppId(borrowerServiceAppId);
		 * familytotalcost3.setTotalCostType("5");
		 * returnFamilytotalcosts.add(familytotalcost3);
		 * 
		 * Familytotalcost familytotalcost4 = new Familytotalcost();
		 * familytotalcost4.setBorrowerServiceAppId(borrowerServiceAppId);
		 * familytotalcost4.setTotalCostType("6");
		 * returnFamilytotalcosts.add(familytotalcost4);
		 * 
		 * Familytotalcost familytotalcost5 = new Familytotalcost();
		 * familytotalcost5.setBorrowerServiceAppId(borrowerServiceAppId);
		 * familytotalcost5.setTotalCostType("8");
		 * returnFamilytotalcosts.add(familytotalcost5);
		 * 
		 * Familytotalcost familytotalcost6 = new Familytotalcost();
		 * familytotalcost6.setBorrowerServiceAppId(borrowerServiceAppId);
		 * familytotalcost6.setTotalCostType("2");
		 * returnFamilytotalcosts.add(familytotalcost6);
		 * 
		 * Familytotalcost familytotalcost7 = new Familytotalcost();
		 * familytotalcost7.setBorrowerServiceAppId(borrowerServiceAppId);
		 * familytotalcost7.setTotalCostType("10");
		 * returnFamilytotalcosts.add(familytotalcost7);
		 * 
		 * Familytotalcost familytotalcost8 = new Familytotalcost();
		 * familytotalcost8.setBorrowerServiceAppId(borrowerServiceAppId);
		 * familytotalcost8.setTotalCostType("9");
		 * returnFamilytotalcosts.add(familytotalcost8);
		 */

		if (CommonUtil.isNotEmpty(getFamilytotalcosts)) {
			for (int i = 0; i < getFamilytotalcosts.size(); i++) {
				Familytotalcost tempFamilytotalcost = getFamilytotalcosts.get(i);
				String totalCostType = tempFamilytotalcost.getTotalCostType();
				if ("1".equals(totalCostType.trim())) {
					returnFamilytotalcosts.set(0, tempFamilytotalcost);
				} else if ("3".equals(totalCostType.trim())) {
					returnFamilytotalcosts.set(1, tempFamilytotalcost);
				} else if ("4".equals(totalCostType.trim())) {
					returnFamilytotalcosts.set(2, tempFamilytotalcost);
				} else if ("5".equals(totalCostType.trim())) {
					returnFamilytotalcosts.set(3, tempFamilytotalcost);
				} else if ("6".equals(totalCostType.trim())) {
					returnFamilytotalcosts.set(4, tempFamilytotalcost);
				} else if ("8".equals(totalCostType.trim())) {
					returnFamilytotalcosts.set(5, tempFamilytotalcost);
				} else if ("2".equals(totalCostType.trim())) {
					returnFamilytotalcosts.set(6, tempFamilytotalcost);
				} else if ("10".equals(totalCostType.trim())) {
					returnFamilytotalcosts.set(7, tempFamilytotalcost);
				} else if ("9".equals(totalCostType.trim())) {
					returnFamilytotalcosts.set(8, tempFamilytotalcost);
				}
			}

		}
		return returnFamilytotalcosts;
	}

	/**
	 * 
	 * @param returnFamilytotalcosts 日常总开支List
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 日常总开支List
	 */
	private List<Familytotalcost> packagingFamilytotalcostList(List<Familytotalcost> returnFamilytotalcosts,
			Integer borrowerServiceAppId) {
		Familytotalcost familytotalcost0 = new Familytotalcost();
		familytotalcost0.setBorrowerServiceAppId(borrowerServiceAppId);
		familytotalcost0.setTotalCostType("1");
		returnFamilytotalcosts.add(familytotalcost0);

		Familytotalcost familytotalcost1 = new Familytotalcost();
		familytotalcost1.setBorrowerServiceAppId(borrowerServiceAppId);
		familytotalcost1.setTotalCostType("3");
		returnFamilytotalcosts.add(familytotalcost1);

		Familytotalcost familytotalcost2 = new Familytotalcost();
		familytotalcost2.setBorrowerServiceAppId(borrowerServiceAppId);
		familytotalcost2.setTotalCostType("4");
		returnFamilytotalcosts.add(familytotalcost2);

		Familytotalcost familytotalcost3 = new Familytotalcost();
		familytotalcost3.setBorrowerServiceAppId(borrowerServiceAppId);
		familytotalcost3.setTotalCostType("5");
		returnFamilytotalcosts.add(familytotalcost3);

		Familytotalcost familytotalcost4 = new Familytotalcost();
		familytotalcost4.setBorrowerServiceAppId(borrowerServiceAppId);
		familytotalcost4.setTotalCostType("6");
		returnFamilytotalcosts.add(familytotalcost4);

		Familytotalcost familytotalcost5 = new Familytotalcost();
		familytotalcost5.setBorrowerServiceAppId(borrowerServiceAppId);
		familytotalcost5.setTotalCostType("8");
		returnFamilytotalcosts.add(familytotalcost5);

		Familytotalcost familytotalcost6 = new Familytotalcost();
		familytotalcost6.setBorrowerServiceAppId(borrowerServiceAppId);
		familytotalcost6.setTotalCostType("2");
		returnFamilytotalcosts.add(familytotalcost6);

		Familytotalcost familytotalcost7 = new Familytotalcost();
		familytotalcost7.setBorrowerServiceAppId(borrowerServiceAppId);
		familytotalcost7.setTotalCostType("10");
		returnFamilytotalcosts.add(familytotalcost7);

		Familytotalcost familytotalcost8 = new Familytotalcost();
		familytotalcost8.setBorrowerServiceAppId(borrowerServiceAppId);
		familytotalcost8.setTotalCostType("9");
		returnFamilytotalcosts.add(familytotalcost8);
		return returnFamilytotalcosts;
	}

	@Override
	public List<Familytotalcost> saveFamilytotalcosts(List<Familytotalcost> familytotalcostList) {
		// TODO Auto-generated method stub
		// 创建一个临时的FamilytotalcostList用来实现后续的操作
		Integer borrowerServiceAppId = familytotalcostList.get(0).getBorrowerServiceAppId();
		List<Familytotalcost> insertFamilytotalcostList = new ArrayList<Familytotalcost>();
		List<Familytotalcost> updateFamilytotalcostList = new ArrayList<Familytotalcost>();
		List<Integer> deleteFamilytotalcostList = new ArrayList<Integer>();
		for (int i = 0; i < familytotalcostList.size(); i++) {
			// 把从前Controller传过来的FamilytotalcostList中的元素get到一个一个去判断
			Familytotalcost tempFamilytotalcost = familytotalcostList.get(i);
			// 得到FamilytotalcostId
			Integer familytotalcostId = tempFamilytotalcost.getFamilyTotalCostId();
			Double amount = tempFamilytotalcost.getAmount();
			// 如果FamilytotalcostId==null则做插入操作
			if (familytotalcostId == null) {
				if (amount == null) {
				} else {
					insertFamilytotalcostList.add(tempFamilytotalcost);
				}
				// 如果FamilytotalcostId!=null则可能是修改或者删除操作
			} else {
				// 如果输入的全是空则是删除操作
				if (amount == null) {
					deleteFamilytotalcostList.add(tempFamilytotalcost.getFamilyTotalCostId());
					// 如果输入的不是空就是修改操作
				} else {
					updateFamilytotalcostList.add(tempFamilytotalcost);
				}
			}

		}
		if (CommonUtil.isNotEmpty(insertFamilytotalcostList)) {
			familytotalcostDao.batchInsert("familytotalcost.insertIntoFamilytotalcost", insertFamilytotalcostList);
		}
		if (CommonUtil.isNotEmpty(updateFamilytotalcostList)) {
			familytotalcostDao.batchUpdate("familytotalcost.updateFamilytotalcostByPrimaryKey",
					updateFamilytotalcostList);
		}
		if (CommonUtil.isNotEmpty(deleteFamilytotalcostList)) {
			familytotalcostDao.batchDelete("familytotalcost.deleteFamilytotalcostByPrimaryKey",
					deleteFamilytotalcostList);
		}
		List<Familytotalcost> returnFamilytotalcosts = searchFamilytotalcosts(borrowerServiceAppId);
		return returnFamilytotalcosts;
	}
}
