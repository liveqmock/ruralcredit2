package com.creditease.rc.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.creditease.rc.domain.Employee;
import com.creditease.rc.vo.CustomerInfoVO;
import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.ICustomerBasicInfoDao;
import com.creditease.rc.domain.BorrowerServiceApp;
import com.creditease.rc.domain.CustomerBasicInfo;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.CustomerBasicInfoVo;

/**
 * 
 * @author zhangman
 * 
 */
@Repository
public class CustomerBasicInfoDao implements ICustomerBasicInfoDao {

	@Resource
	private IBaseDao baseDao;

	/**
	 * 构造方法
	 */
	public CustomerBasicInfoDao() {
		super();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Pagination selectCustomerBasic(CustomerBasicInfo customerBasicInfo, Pagination pagination) {
		Pagination pagination1 = new Pagination();
		pagination1 = baseDao.queryForPaginatedList("CUSTOMER.selectCustomerBasicInfo", "CUSTOMER.countByPage",
				customerBasicInfo, pagination.getStartResult(), pagination.getPageSize());
		return pagination1;
	}

	@Override
	public CustomerBasicInfo selectByPrimaryKey(Integer customerBasicid) {
		CustomerBasicInfo key = new CustomerBasicInfo();
		key.setCustomerBasicId(customerBasicid);
		CustomerBasicInfo record = (CustomerBasicInfo) baseDao.queryUnique("CUSTOMER.selectByPrimaryKey", key);
		return record;
	}

	@Override
	public int insert(CustomerBasicInfo record) {
		return (Integer) baseDao.insertObject("CUSTOMER.insert", record);
	}

	@Override
	public int updateByPrimaryKeySelective(CustomerBasicInfo record) {
		int rows = baseDao.update("CUSTOMER.updateByPrimaryKey", record);
		return rows;
	}

	@Override
	public int deleteByPrimaryKey(Integer customerBasicId) {
		int rows = baseDao.delete("CUSTOMER.deleteByPrimaryKey", customerBasicId);
		return rows;
	}

	@Override
	public CustomerBasicInfo selectCustomerBasicInfo(CustomerBasicInfo customer) {
		return (CustomerBasicInfo) baseDao.queryUnique("CUSTOMER.selectByPrimaryKey", customer);
	}

	@Override
	public List<CustomerBasicInfoVo> selectByCredentialsNumber(String credentialsNumber) {
		return (List<CustomerBasicInfoVo>) baseDao.queryList("CUSTOMER.selectByCredentialsNumber", credentialsNumber);
	}

    /**
     *根据身份证号     查询借款人      历史借款信息
     * @param identityNumber 身份证号
     * @return    客户基本信息
     *    2014-09-17    add by ygx
     *    * *
     * */
    @Override
    public List<CustomerBasicInfoVo> getCustomerInfo(String identityNumber) {
        return (List<CustomerBasicInfoVo>) baseDao.queryList("CUSTOMER.getCustomerInfo", identityNumber);
    }
    /**
     *根据身份证号     查询借款人     黑名单信息
     *  @param identityNumber 身份证号
     * @return    客户黑名单信息
     *    2014-09-17    add by ygx
     *    * *
     * */
    @Override
    public List<CustomerBasicInfoVo> getCustomerBlackInfo(String identityNumber) {

        return (List<CustomerBasicInfoVo>) baseDao.queryList("CUSTOMER.getCustomerBlackInfo", identityNumber);
    }

    /**
     *   根据身份证号 查询共借人信息
     * @param identityNumber
     * @return
     */
    @Override
    public List<CustomerBasicInfoVo> getCoBorrowerInfo(String identityNumber) {
        return (List<CustomerBasicInfoVo>) baseDao.queryList("CUSTOMER.getCoBorrowerInfo", identityNumber);
    }

    /**
     *根据身份证号     查询借款人         配偶信息
     * @param identityNumber 身份证号
     * @return    客户配偶信息
     *    2014-09-17    add by ygx
     *    * *
     * */
    @Override
    public List<CustomerBasicInfoVo> getMateInfo(String identityNumber) {
        return (List<CustomerBasicInfoVo>) baseDao.queryList("CUSTOMER.getMateInfo", identityNumber);
    }
    /**
     *根据身份证号     查询借款人       担保信息
     *  @param identityNumber 身份证号
     * @return    客户担保信息
     *    2014-09-17    add by ygx
     *    * *
     * */
    @Override
    public List<CustomerBasicInfoVo> getGuaranorInfo(String identityNumber) {
        return (List<CustomerBasicInfoVo>) baseDao.queryList("CUSTOMER.getGuaranorInfo", identityNumber);
    }
    /*
    *根据身份证号查询查询此人直系亲属是否可以借款
    *  @param identityNumber 身份证号
    *
    * */
    @Override
    public List<CustomerBasicInfoVo> getConsanguinityInfo(String identityNumber) {
        return (List<CustomerBasicInfoVo>) baseDao.queryList("CUSTOMER.getConsanguinityInfo", identityNumber);
    }


    @Override
	public List<CustomerBasicInfoVo> selectGuaranorByCredentialsNumber(String credentialsNumber) {
		return (List<CustomerBasicInfoVo>) baseDao.queryList("CUSTOMER.selectGuaranorByCredentialsNumber",
				credentialsNumber);
	}

// 查询语句 身份证号 已经忽略大小写)
	@Override
	public List<CustomerBasicInfoVo> selectFamilyByCredentialsNumber(String credentialsNumber) {
		// TODO Auto-generated method stub
		return (List<CustomerBasicInfoVo>) baseDao.queryList("CUSTOMER.selectFamilyByCredentialsNumber",
				credentialsNumber);
	}

// 查询语句 身份证号 已经忽略大小写)
	@Override
	public List<CustomerBasicInfoVo> selectGuanor(BorrowerServiceApp borrowerServiceApp) {
		// TODO Auto-generated method stub
		return (List<CustomerBasicInfoVo>) baseDao.queryList("CUSTOMER.selectGuanor", borrowerServiceApp);
	}

	@Override
	public CustomerBasicInfo selectByNumber(String credentialsNumber) {
		return (CustomerBasicInfo) baseDao.queryUnique("CUSTOMER.selectByNumber", credentialsNumber);
	}

	@Override
	public Pagination selectFuzzy(String fuzzyValue, Pagination pagination) {
		// TODO Auto-generated method stub
		return baseDao.queryForPaginatedList("CUSTOMER.selectFuzzy", "CUSTOMER.countFuzzy", fuzzyValue,
				pagination.getStartResult(), pagination.getPageSize());
	}

	@Override
	public Map selectAutus(int customerBasicId) {
		// TODO Auto-generated method stub
		return (Map) baseDao.queryUnique("CUSTOMER.selectAuditStatus", customerBasicId);
	}

	@Override
	public int updateDynamic(CustomerBasicInfo customerBasicInfo) {
		// TODO Auto-generated method stub
		return baseDao.update("CUSTOMER.update_dynamic", customerBasicInfo);
	}

	@Override
	public List<Map<String, String>> checkForCredentialsNumber(Long creditapplicationId) {
		// TODO Auto-generated method stub
		return (List<Map<String, String>>) baseDao.queryList("CUSTOMER.checkForCredentialsNumber", creditapplicationId);
	}

    @Override
    public List<CustomerBasicInfo> getBlackListCustomerInfoList() {
        return (List<CustomerBasicInfo>) baseDao.queryList("CUSTOMER.getBlackListCustomerInfoList");
    }
    
    @Override
    public String selBusinessNumberByUUID(String businessNumber) {
        return  (String) baseDao.queryUnique("CUSTOMER.selBusinessNumberByUUID",businessNumber);
    }

	@Override
	public List<Employee> getEmployeeListByDeparmentId(String departmentId) {
		return  (List<Employee>) baseDao.queryList("CUSTOMER.getEmployeeListByDeparmentId", departmentId);
	}

	@Override
	public List<CustomerInfoVO> getCustomerInfoListByCreditApplicationID(long creditapplicationId) {
		return  (List<CustomerInfoVO>) baseDao.queryList("CUSTOMER.getCustomerInfoListByCreditApplicationID", creditapplicationId);
	}


}