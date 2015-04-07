package com.creditease.rc.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.IBorrowerServiceAppDAO;
import com.creditease.rc.domain.BorrowerServiceApp;
import com.creditease.rc.domain.Familymember;
import com.creditease.rc.framework.dao.impl.BaseDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.vo.BorrowerInfoVo;
import com.creditease.rc.vo.BorrowerServiceAppVo2;
import com.creditease.rc.vo.CreditApplicationSearch;

/**
 * 
 * @author zhangman
 * 
 */
@Repository
public class BorrowerServiceAppDAO extends BaseDao implements IBorrowerServiceAppDAO {

	@Resource
	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	@SuppressWarnings("unchecked")
	public int addBorrowerServiceApp(BorrowerServiceApp borrowerServiceApp) {
		//状态设置
		borrowerServiceApp.setAuditStatus(Constants.STATE_0);
		return (Integer) baseDao.insertObject("BORROWERSERVICE.insert",
				borrowerServiceApp);
	}


	@Override
	public List<BorrowerServiceApp> selectBorrowerServiceAppList(int creditapplicationId) {
		List<BorrowerServiceApp> borrowerServiceAppList = new ArrayList<BorrowerServiceApp>();
		borrowerServiceAppList = (List<BorrowerServiceApp>) baseDao.queryList(
				"BORROWERSERVICE.selectByCreditapplicationId", creditapplicationId);
		return borrowerServiceAppList;
	};

	@Override
	public BorrowerServiceApp selectByBorrowerServiceAppId(int borrowerServiceAppId) {
		BorrowerServiceApp borrowerServiceApp = new BorrowerServiceApp();
		borrowerServiceApp = (BorrowerServiceApp) baseDao.queryUnique(
				"BORROWERSERVICE.selectByBorrowerServiceAppId", borrowerServiceAppId);
		return borrowerServiceApp;
	};

	@Override
	public BorrowerServiceApp selectByCustomerId(int customerBasicId) {
		// TODO Auto-generated method stub
		return (BorrowerServiceApp) baseDao.queryUnique("BORROWERSERVICE.selectByCustomerId", customerBasicId);
	}
	
	@Override
	public int updateBorrowerServiceApp(BorrowerServiceApp borrowerServiceApp) {
		return baseDao.update("BORROWERSERVICE.update", borrowerServiceApp);
	};

	@Override
	public boolean deleteBorrowerServiceApp(int borrowerServiceAppId) {
		int number = baseDao.delete("BORROWERSERVICE.delete", borrowerServiceAppId);
		if (number > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateFirstFlag(int borrowerServiceAppId, String firstFlag) {
		BorrowerServiceApp borroweServiceApp = new BorrowerServiceApp();
		borroweServiceApp.setBorrowerServiceAppId(borrowerServiceAppId);
		borroweServiceApp.setFirstFlag(firstFlag);
		int count = baseDao.update("BORROWERSERVICE.updateFirstFlag", borroweServiceApp);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateSecondFlag(int borrowerServiceAppId, String secondFlag) {
		BorrowerServiceApp borroweServiceApp = new BorrowerServiceApp();
		borroweServiceApp.setBorrowerServiceAppId(borrowerServiceAppId);
		borroweServiceApp.setSecondFlag(secondFlag);
		int count = baseDao.update("BORROWERSERVICE.updateSecondFlag", borroweServiceApp);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateThirdFlag(int borrowerServiceAppId, String thirdFlag) {
		BorrowerServiceApp borroweServiceApp = new BorrowerServiceApp();
		borroweServiceApp.setBorrowerServiceAppId(borrowerServiceAppId);
		borroweServiceApp.setThirdFlag(thirdFlag);
		int count = baseDao.update("BORROWERSERVICE.updateThirdFlag", borroweServiceApp);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<BorrowerInfoVo> selectBorrowerInfoVoList(int creditapplicationId) {
		return (List<BorrowerInfoVo>) baseDao.queryList(
				"BORROWERSERVICE.selectBorrowerInfoVo", creditapplicationId);
	}

	//不用的方法
	@Override
	public int updateDeleteFlag(int borrowerServiceAppId) {
		return baseDao.update("", borrowerServiceAppId);
	}

	@Override
	public String selectByNumber(String credentialsNumber) {
		if (CommonUtil.isNotEmpty(credentialsNumber)) {
			return (String) baseDao.queryUnique("BORROWERSERVICE.selectByNumber",
					credentialsNumber);
		}
		return null;
	}
	
	@Override
	public int submitAuditing(BorrowerServiceApp borrowerServiceApp) {
		return baseDao.update("BORROWERSERVICE.updateAuditing", borrowerServiceApp);
	}
	@Override
	public List<BorrowerServiceApp> selectByBorrowerInfoVo(BorrowerInfoVo borrowerInfoVo) {
		// TODO Auto-generated method stub
		return (List<BorrowerServiceApp>) baseDao.queryList("BORROWERSERVICE.selectByBorrowerServiceApp", borrowerInfoVo);
	}
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  根据creditapplicationId查询借款人
	 * @param creditapplicationId
	 * @return 
	 * @version v1.0 
	 * 2013-3-26
	 */
	public BorrowerServiceApp selectBorrowerLeaderByCreditApplicationId(Integer creditapplicationId){
		List<BorrowerServiceApp> borrowerServiceAppList =(List<BorrowerServiceApp>) this.queryList("BORROWERSERVICE.selectBorrowerLeaderByCreditApplicationId", creditapplicationId);
		BorrowerServiceApp borrowerServiceApp=null;
		if(CommonUtil.isNotEmpty(borrowerServiceAppList)){
			borrowerServiceApp=borrowerServiceAppList.get(0);
		}
		return borrowerServiceApp;
	}
	
	/**
	 * @author manzhang 
	 */
	@Override
	public Familymember selectPartner(Familymember familymember) {
		// TODO Auto-generated method stub
		return (Familymember) baseDao.queryUnique("BORROWERSERVICE.selectPartner", familymember);
	}
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  查询配偶信息
	 * @param borrowerServiceAppId
	 * @return 
	 * @version v1.0 
	 * 2013-4-26
	 */
	public Familymember selectSpouseByBorrowerServiceAppId(Integer borrowerServiceAppId){
		List <Familymember> listFamilymember= (List<Familymember>) baseDao.queryList("familymember.selectSpouseByBorrowerServiceAppId", borrowerServiceAppId);
		if(CommonUtil.isEmpty(listFamilymember)){
			return new Familymember();
		}
		return listFamilymember.get(0);
		
	}
	
	@Override
	public Pagination exportCustomerInformationtList(Map<String, Object> map,Pagination pagination) {
		// TODO Auto-generated method stub
		return baseDao.queryForPaginatedList("BORROWERSERVICE.exportCustomerInformation", "BORROWERSERVICE.exportCustomerInformation_count", map, 	pagination.getStartResult(), pagination.getPageSize());
	}
	
	@Override
	public List<Map> exportCustomerInformationt(Map map) {
		// TODO Auto-generated method stub
		return (List<Map>) baseDao.queryList("BORROWERSERVICE.exportCustomerInformation", map);
	}
	@Override
	public int updateBorrowerServiceAppForSistem(BorrowerServiceApp borrowerServiceApp) {
		// TODO Auto-generated method stub
		return baseDao.update("BORROWERSERVICE.update_dynamic", borrowerServiceApp);
	}

	/**
	 * 查询与该电话有关的担保人的借款结清次数
	 * @author luohongjie
	 */
	
	@Override
	public List<CreditApplicationSearch> selectBorrowerServiceByTel(String telphone) {
		return (List<CreditApplicationSearch>) queryList("BORROWERSERVICE.selectBorrowerListByTel",telphone);
	}
	/**
	 * 查询与该电话有关的借款人的借款结清次数
	 * @author luohongjie
	 */
	@Override
	public List<CreditApplicationSearch> selectBorrowerAuditListByTel(String telPhone) {
		return (List<CreditApplicationSearch>) queryList("BORROWERSERVICE.selectBorrowerAuditListByTel",telPhone);
	}

	@Override
	public List<BorrowerServiceApp> checkBorrowerSave(Long creditapplicationId) {
		// TODO Auto-generated method stub
		return (List<BorrowerServiceApp>) queryList("BORROWERSERVICE.checkBorrowerSave",creditapplicationId);
	}

	@Override
	public List<BorrowerServiceAppVo2> queryBorrowerInfo(Long creditapplicationId) {
		return  (List<BorrowerServiceAppVo2>) queryList("RL_BORROWER_SERVICE_APP.queryBorrowerInfo", creditapplicationId);
	}
}
