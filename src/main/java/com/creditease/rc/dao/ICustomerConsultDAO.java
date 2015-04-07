package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.CustomerConsult;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;

/**
 * 
 * @author zhangman
 *
 */
public interface ICustomerConsultDAO extends IBaseDao{
	/**
	 * 新增客户咨询
	 * @param customerConsult  客户咨询对象
	 * @return 客户咨询对象id
	 */
	public long insertCustomerConsult(CustomerConsult customerConsult);
	/**
	 * 按照id删除
	 * @param customerConsultId 客户咨询信息id
	 * @return 影响的行数
	 */
	public int deleteCustomerConsult(int customerConsultId);
	/**
	 * 修改客户咨询信息
	 * @param customerConsult 客户咨询对象
	 * @return 影响的条数
	 */
	public int updateCustomerConsult(CustomerConsult customerConsult);
	
	/**
	 * 修改客户咨询信息
	 * @param customerConsult 客户咨询对象
	 * @return 影响的条数
	 */
	public int updateCustomerConsultAll(CustomerConsult customerConsult);
	/**
	 * 分页查询客户咨询列表
	 * @param customerConsult 客户咨询对象
	 * @param pagination 分页条件
	 * @return 分页列表
	 */
	public Pagination searchCustomerConsults(CustomerConsult customerConsult,Pagination pagination);
	 /**
	  * 模糊查询
	  * @param fuzzyValue 唯一条件
	  * @param pagination 分页条件
	  * @return 分页列表
	  */
	 public Pagination searchByFuzzyValue(String fuzzyValue,Pagination pagination);
	 /**
	  * 模糊查询
	  * @param searchValue 唯一条件
	  * @param pagination 分页条件
	  * @return 分页列表
	  */
	 public Pagination searchByFuzzyValueMap(Map searchValue,Pagination pagination);
	 /**
	 * 按id查询
	 * @param customerConsultId id
	 * @return 客户咨询对象
	 */
	public CustomerConsult selectCustomerConsultById(Long customerConsultId);
	
	/**
	 * 按条件查询 
	 * 
	 * @param customerConsult 客户咨询对象
	 * @return  客户咨询对象
	 */
	public CustomerConsult selectCustomerConsult(CustomerConsult customerConsult);
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  
	 * @param borrowerServiceAppId 
	 * @return 列表
	 * @version v1.0 
	 * 2013-4-28
	 */
	public List<CustomerConsult> queryCustomerConsultByBorrowerServiceAppId( Integer borrowerServiceAppId);
	/**
	 *  按接待员 （信贷员 id 分组）
	 * @return List<CustomerConsult>
	 */
	public List<CustomerConsult> selectByreceptionistId();
	
	/**
	 * 按接待员 （信贷员 id 修改）
	 * @param customerConsult  CustomerConsult
	 * @return int
	 */
	public int updateByreceptionistId(CustomerConsult customerConsult);
	
	/**
	 * 查询md5加密号码为空的咨询
	 * @return List<CustomerConsult>
	 */
	public List<CustomerConsult> selectCustomerConsultList();
	
	/**
	 * 批量修改
	 * @param customerConsults list
	 * @return 影响的行数
	 */
	public int updateBatch(List<CustomerConsult> customerConsults);

	
	/**
	 * 根据接待人查询
	 * @param receptionistId
	 * @return
	 */
	public List<CustomerConsult> selectByReceptionist(String receptionistId);

	
	/**
	 * @author 罗红杰
	 */
	/**
	 * 根据电话号码查询咨询业务
	 * @return List<CustomerConsult>
	 */
	public List<CustomerConsult> selectCustomerConsultListByTel(String telPhone);
	public Pagination selectCustomerConsultList(CustomerConsult customerConsult, Pagination pagination);

    public List<CustomerConsult> queryCustomerConsultByCreditApplicationId(Integer creditApplicationId);
    /**
     * 通过申请id查询借款用途
     * @param creditApplicationId
     * @return
     */
    public Map<String, Object> queryBorrowUseBycreditapplicationId(String creditApplicationId);

    public String getManagerPhone(String userName);

    public void saveManagerPhone(String userName, String phone);

    public void updateManagerPhone(String userName, String phone);
}
