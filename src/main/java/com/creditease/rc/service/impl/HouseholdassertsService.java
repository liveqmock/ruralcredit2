package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IApplicationDao;
import com.creditease.rc.dao.IBorrowerServiceDAO;
import com.creditease.rc.dao.IDepositDao;
import com.creditease.rc.dao.IFamilymemberDao;
import com.creditease.rc.dao.IHouseholdassertsDao;
import com.creditease.rc.dao.IJobInfoDAO;
import com.creditease.rc.dao.ISurveybusinessinfoDao;
import com.creditease.rc.domain.Householdasserts;
import com.creditease.rc.service.IHouseholdassertsService;
import com.creditease.rc.util.CommonUtil;

/**
 * 
 * @author haoqiang
 * 
 */
@Service
public class HouseholdassertsService implements IHouseholdassertsService {
	
	//个人申请
	@Resource
	private IBorrowerServiceDAO borrowerServiceDAO;
	//家庭
	@Resource
	private IFamilymemberDao familymemberDao;
	//工作
	@Resource
	private IJobInfoDAO jobInfoDAO;
	//经营请款
	@Resource
	private ISurveybusinessinfoDao surveybusinessinfoDao;
	
	//存款应收款
	@Resource
	private IDepositDao depositDao;
	
	//房产 
	@Resource
	private IHouseholdassertsDao householdassertsDao;

	//借款申请
	@Resource
	private IApplicationDao applicationDao;
	
	@Override
	public boolean addHouseholdAssertsList(List<Householdasserts> householdassertss) {
		// TODO Auto-generated method stub
		List<Householdasserts> addhouseholdassertss = new ArrayList<Householdasserts>();
		for (int i = 0; i < householdassertss.size(); i++) {

			Householdasserts temp = householdassertss.get(i);
			String description1 = temp.getDescription1();
			if (description1 != null && !"".equals(description1.trim())) {
				addhouseholdassertss.add(temp);
			}
		}

		householdassertsDao.batchInsertToHoldasserts(addhouseholdassertss);
		return true;
	}

	@Override
	public List<Householdasserts> searchhouseholdassertsList(int borrowerSurveyId) {
		// TODO Auto-generated method stub
		List<Householdasserts> householdassertss = householdassertsDao.querySelectHouseholdasserts(borrowerSurveyId);
		return householdassertss;
	}

	@Override
	public boolean updateHouseholdAssertsList(List<Householdasserts> householdassertss) {
		// TODO Auto-generated method stub
		List<Householdasserts> updatehouseholdassertss = new ArrayList<Householdasserts>();
		for (int i = 0; i < householdassertss.size(); i++) {

			Householdasserts temp = householdassertss.get(i);
			String description1 = temp.getDescription1();
			if (description1 != null && !"".equals(description1.trim())) {
				updatehouseholdassertss.add(temp);
			}
		}

		householdassertsDao.batchUpdateHouseholdAssertsList(updatehouseholdassertss);
		return true;
	}

	@Override
	public boolean insertHouseholdAssertsList(List<Householdasserts> householdassertss) {
		// TODO Auto-generated method stub
		householdassertsDao.batchInsertToHoldasserts(householdassertss);
		return true;
	}

	// 查询家庭资产
	@Override
	public List<Householdasserts> searchHouseholdassertss(Integer borrowerServiceAppId) {
		// TODO Auto-generated method stub
		List<Householdasserts> getHouseholdassertss = (List<Householdasserts>) householdassertsDao.queryList(
				"householdasserts.selecthouseholdassertsByBorrowerServiceAppId", borrowerServiceAppId);

		List<Householdasserts> returnHouseholdassertss = new ArrayList<Householdasserts>();
		returnHouseholdassertss = packagingHouseholdassertsList(returnHouseholdassertss, borrowerServiceAppId);
		/*
		 * for (int i = 0; i < 2; i++) {
		 * Householdasserts householdasserts0 = new Householdasserts();
		 * householdasserts0.setBorrowerServiceAppId(borrowerServiceAppId);
		 * householdasserts0.setHouseholdAssertsType("1");
		 * returnHouseholdassertss.add(householdasserts0);
		 * Householdasserts householdasserts1 = new Householdasserts();
		 * householdasserts1.setBorrowerServiceAppId(borrowerServiceAppId);
		 * householdasserts1.setHouseholdAssertsType("3");
		 * returnHouseholdassertss.add(householdasserts1);
		 * Householdasserts householdasserts2 = new Householdasserts();
		 * householdasserts2.setBorrowerServiceAppId(borrowerServiceAppId);
		 * householdasserts2.setHouseholdAssertsType("2");
		 * returnHouseholdassertss.add(householdasserts2);
		 * Householdasserts householdasserts3 = new Householdasserts();
		 * householdasserts3.setBorrowerServiceAppId(borrowerServiceAppId);
		 * householdasserts3.setHouseholdAssertsType("4");
		 * returnHouseholdassertss.add(householdasserts3);
		 * 
		 * }
		 */
		int householdAssertsType1Count = 0;
		int householdAssertsType3Count = 0;
		int householdAssertsType2Count = 0;
		int householdAssertsType4Count = 0;
		if (CommonUtil.isNotEmpty(getHouseholdassertss)) {
			for (int i = 0; i < getHouseholdassertss.size(); i++) {
				Householdasserts tempHouseholdasserts = getHouseholdassertss.get(i);
				String householdAssertsType = tempHouseholdasserts.getHouseholdAssertsType();
				if ("1".equals(householdAssertsType.trim())) {
					if (householdAssertsType1Count == 0) {
						returnHouseholdassertss.set(0, tempHouseholdasserts);
						householdAssertsType1Count++;
					} else {
						returnHouseholdassertss.set(4, tempHouseholdasserts);
					}
				} else if ("3".equals(householdAssertsType.trim())) {
					if (householdAssertsType3Count == 0) {
						returnHouseholdassertss.set(1, tempHouseholdasserts);
						householdAssertsType3Count++;
					} else {
						returnHouseholdassertss.set(5, tempHouseholdasserts);
					}
				} else if ("2".equals(householdAssertsType.trim())) {
					if (householdAssertsType2Count == 0) {
						returnHouseholdassertss.set(2, tempHouseholdasserts);
						householdAssertsType2Count++;
					} else {
						returnHouseholdassertss.set(6, tempHouseholdasserts);
					}
				} else if ("4".equals(householdAssertsType.trim())) {
					if (householdAssertsType4Count == 0) {
						returnHouseholdassertss.set(3, tempHouseholdasserts);
						householdAssertsType4Count++;
					} else {
						returnHouseholdassertss.set(7, tempHouseholdasserts);
					}
				}
			}

		}
		return returnHouseholdassertss;
	}

	/**
	 * 
	 * @param returnHouseholdassertss 家庭资产List
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 家庭资产List
	 */
	private List<Householdasserts> packagingHouseholdassertsList(List<Householdasserts> returnHouseholdassertss,
			Integer borrowerServiceAppId) {
		for (int i = 0; i < 2; i++) {
			Householdasserts householdasserts0 = new Householdasserts();
			householdasserts0.setBorrowerServiceAppId(borrowerServiceAppId);
			householdasserts0.setHouseholdAssertsType("1");
			returnHouseholdassertss.add(householdasserts0);
			Householdasserts householdasserts1 = new Householdasserts();
			householdasserts1.setBorrowerServiceAppId(borrowerServiceAppId);
			householdasserts1.setHouseholdAssertsType("3");
			returnHouseholdassertss.add(householdasserts1);
			Householdasserts householdasserts2 = new Householdasserts();
			householdasserts2.setBorrowerServiceAppId(borrowerServiceAppId);
			householdasserts2.setHouseholdAssertsType("2");
			returnHouseholdassertss.add(householdasserts2);
			Householdasserts householdasserts3 = new Householdasserts();
			householdasserts3.setBorrowerServiceAppId(borrowerServiceAppId);
			householdasserts3.setHouseholdAssertsType("4");
			returnHouseholdassertss.add(householdasserts3);

		}
		return returnHouseholdassertss;
	}

	// 操作家庭资产包括增删改查
	@Override
	public List<Householdasserts> saveHouseholdassertss(List<Householdasserts> householdassertsList) {
		// TODO Auto-generated method stub
		Integer borrowerServiceAppId = householdassertsList.get(0).getBorrowerServiceAppId();
		List<Householdasserts> insertHouseholdassertsList = new ArrayList<Householdasserts>();
		List<Householdasserts> updateHouseholdassertsList = new ArrayList<Householdasserts>();
		List<Integer> deleteHouseholdassertsList = new ArrayList<Integer>();
		for (int i = 0; i < householdassertsList.size(); i++) {
			// 把从前Controller传过来的HouseholdassertsList中的元素get到一个一个去判断
			Householdasserts tempHouseholdasserts = householdassertsList.get(i);
			// 得到HouseholdassertsId
			Integer householdAssertsId = tempHouseholdasserts.getHouseholdAssertsId();
			String description1 = tempHouseholdasserts.getDescription1();
			String description2 = tempHouseholdasserts.getDescription2();
			Double value = tempHouseholdasserts.getValue();
			// 如果HouseholdassertsId==null则做插入操作
			if (householdAssertsId == null) {
				if ((description1 == null || "".equals(description1.trim()))
						&& (description2 == null || "".equals(description2.trim())) && value == null) {
				} else {
					insertHouseholdassertsList.add(tempHouseholdasserts);
				}
				// 如果HouseholdassertsId!=null则可能是修改或者删除操作
			} else {
				// 如果输入的全是空则是删除操作
				if ((description1 == null || "".equals(description1.trim()))
						&& (description2 == null || "".equals(description2.trim())) && value == null) {
					deleteHouseholdassertsList.add(tempHouseholdasserts.getHouseholdAssertsId());
					// 如果输入的不是空就是修改操作
				} else {
					updateHouseholdassertsList.add(tempHouseholdasserts);
				}
			}

		}
		if (CommonUtil.isNotEmpty(insertHouseholdassertsList)) {
			householdassertsDao.batchInsert("householdasserts.insertIntohouseholdasserts", insertHouseholdassertsList);
		}
		if (CommonUtil.isNotEmpty(updateHouseholdassertsList)) {
			householdassertsDao.batchUpdate("householdasserts.updatehouseholdassertsByPrimaryKey",
					updateHouseholdassertsList);
		}
		if (CommonUtil.isNotEmpty(deleteHouseholdassertsList)) {
			householdassertsDao.batchDelete("householdasserts.deletehouseholdassertsByPrimaryKey",
					deleteHouseholdassertsList);
		}
		List<Householdasserts> returnHouseholdassertss = searchHouseholdassertss(borrowerServiceAppId);
		return returnHouseholdassertss;
	}

}
