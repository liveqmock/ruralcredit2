package com.creditease.rc.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.core.security.Authorization;
import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.ICustomerConsultDAO;
import com.creditease.rc.domain.CustomerConsult;
import com.creditease.rc.framework.dao.impl.BaseDao;
import com.creditease.rc.framework.pager.Pagination;
/**
 * 
 * @author zhangman
 */
@Repository
public class CustomerConsultDAO extends BaseDao implements ICustomerConsultDAO {

	@Resource
	private Authorization authorization;
	@Override
	public Pagination searchByFuzzyValueMap(Map searchValue, Pagination pagination) {
		// TODO Auto-generated method stub
		return queryForPaginatedList("ContomerConsult.selectMap", "ContomerConsult.countMap", searchValue,
				pagination.getStartResult(), pagination.getPageSize());
	}
	@Override
	public long insertCustomerConsult(CustomerConsult customerConsult) {
		customerConsult.setDepartmentName(SpringSecurityUtils.getCurrentUser().getAreaDepartmentName());	
		customerConsult.setReceptionist(SpringSecurityUtils.getCurrentUser().getName_zh());
		customerConsult.setDepartmentId(String.valueOf(SpringSecurityUtils.getCurrentUser().getAreaDepartmentId()));
		customerConsult.setReceptionistId(String.valueOf(SpringSecurityUtils.getCurrentUser().getUserId()));
		//创建客户经理字段
		customerConsult.setCreateManager(SpringSecurityUtils.getCurrentUser().getName_zh());
		customerConsult.setCreateManagerId(String.valueOf(SpringSecurityUtils.getCurrentUser().getUserId()));
		
		Long customerConsultId = (Long) insertObject("ContomerConsult.dynamicInsert", customerConsult);		
		if(customerConsult.getReceptionistId() != null && !("0".equalsIgnoreCase(customerConsult.getReceptionistId())) ){
			try {
				authorization.createAuth(CustomerConsult.class, Integer.valueOf(customerConsultId.intValue()), Integer.valueOf(customerConsult.getReceptionistId()), Integer.parseInt(Constants.SYSTEM_SIGN));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  customerConsultId;
	}

	@Override
	public int deleteCustomerConsult(int customerConsultId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCustomerConsult(CustomerConsult customerConsult) {
		// TODO Auto-generated method stub
		return update("ContomerConsult.dynamicUpdate", customerConsult);
	}

	
	@Override
	public int updateCustomerConsultAll(CustomerConsult customerConsult) {
		// TODO Auto-generated method stub
		return update("ContomerConsult.dynamicUpdateAll", customerConsult);
	}
	@Override
	public Pagination searchCustomerConsults(CustomerConsult customerConsult, Pagination pagination) {
		return queryForPaginatedList("ContomerConsult.selectPagnation", "ContomerConsult.countPagnation", customerConsult,
				pagination.getStartResult(), pagination.getPageSize());
	}
	
	@Override
	public Pagination searchByFuzzyValue(String fuzzyValue, Pagination pagination) {
		// TODO Auto-generated method stub
		return queryForPaginatedList("ContomerConsult.selectFuzzy", "ContomerConsult.countFuzzy", fuzzyValue,
				pagination.getStartResult(), pagination.getPageSize());
	}

	@Override
	public CustomerConsult selectCustomerConsult(CustomerConsult customerConsult) {
		// TODO Auto-generated method stub
		return (CustomerConsult) queryUnique("ContomerConsult.selectCustomerConsult", customerConsult);
	}
	
	@Override
	public CustomerConsult selectCustomerConsultById(Long customerConsultId) {
		// TODO Auto-generated method stub
			return (CustomerConsult) queryUnique("ContomerConsult.selectCustomerConsultById", customerConsultId);
	}
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  
	 * @param borrowerServiceAppId
	 * @return 
	 * @version v1.0 
	 * 2013-4-28
	 */
	public List<CustomerConsult> queryCustomerConsultByBorrowerServiceAppId( Integer borrowerServiceAppId){
		return (List<CustomerConsult>) this.queryList("ContomerConsult.queryCustomerConsultByBorrowerServiceAppId", borrowerServiceAppId);
		
	}
	
	@Override
	public List<CustomerConsult> selectByreceptionistId() {
		return (List<CustomerConsult>) queryList("ContomerConsult.groupByreceptionistId");
	}
	
	@Override
	public int updateByreceptionistId(CustomerConsult customerConsult) {
		return update("ContomerConsult.updateByreceptionistId", customerConsult);
	}
	@Override
	public List<CustomerConsult> selectCustomerConsultList() {
		return (List<CustomerConsult>) queryList("ContomerConsult.selectCustomerConsultList");
	}
	@Override
	public int updateBatch(List<CustomerConsult> customerConsults) {
		return batchUpdate("ContomerConsult.dynamicUpdate", customerConsults);
	}
 
	@Override
	public List<CustomerConsult> selectByReceptionist(String receptionistId) {
		return (List<CustomerConsult>) queryList("ContomerConsult.selectByReceptionist",receptionistId);
	}
 
	
	/**
	 * 查询与该电话有关的咨询次数
	 * @author luohongjie
	 */
	
	@Override
	public List<CustomerConsult> selectCustomerConsultListByTel(String telPhone){
		return (List<CustomerConsult>) queryList("ContomerConsult.selectCustomerConsultListByTel",telPhone);
	}
 
	@Override
	public Pagination selectCustomerConsultList(CustomerConsult customerConsult, Pagination pagination) {
		return queryForPaginatedList("ContomerConsult.selectByReceptionistnothistory", "ContomerConsult.countByReceptionistnothistory", customerConsult, pagination.getStartResult(), pagination.getPageSize());
	}

    @Override
    public List<CustomerConsult> queryCustomerConsultByCreditApplicationId(Integer creditApplicationId) {
        return (List<CustomerConsult>) this.queryList("ContomerConsult.queryCustomerConsultByCreditApplicationId", creditApplicationId);
    }
	@Override
	public Map<String, Object> queryBorrowUseBycreditapplicationId(
			String creditApplicationId) {
		return (Map<String, Object>) this.queryUnique("ContomerConsult.queryBorrowUseBycreditapplicationId", creditApplicationId);
	}

    @Override
    public String getManagerPhone(String userName){
        return (String)queryUnique("ContomerConsult.getManagerPhone", userName);
    }

    @Override
    public void saveManagerPhone(String userName, String phone){
        Map map = new HashMap();
        map.put("userName", userName);
        map.put("phone", phone);
        this.insert("ContomerConsult.saveManagerPhone", map);
    }

    @Override
    public void updateManagerPhone(String userName, String phone){
        Map map = new HashMap();
        map.put("userName", userName);
        map.put("phone", phone);
        this.update("ContomerConsult.updateManagerPhone", map);
    }
}
