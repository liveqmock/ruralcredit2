package com.creditease.rc.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.creditease.rc.domain.CodeTable;
import com.creditease.rc.domain.CustomerConsult;
import com.creditease.rc.domain.CustomerConsultPool;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.CustomerConsultVo;

/**
 * 
 * @author zhangman
 *
 */
public interface ICustomerConsultService {
	/**
	 * 新增客户咨询
	 * @param customerConsult   客户咨询对象
	 * @return  客户咨询对象id
	 */
	public long addCustomerConsult(CustomerConsult customerConsult);
	/**
	 * 新增客户咨询
	 * @param customerConsult   客户咨询对象
	 * @return  客户咨询对象
	 */
	public CustomerConsult addCustomerConsultReturn(CustomerConsult customerConsult);
	/**
	 * 新增客户咨询
	 * @param customerConsult   客户咨询对象
	 * @return  客户咨询对象
	 */
	public long addUpdate(CustomerConsult customerConsult);
	/**
	 * 按照id删除 
	 * @param customerConsultId 客户咨询信息id
	 * @return 影响的行数
	 */
	public int deleteCustomerConsult(Integer customerConsultId);
	/**
	 * 修改客户咨询信息
	 * @param customerConsult 客户咨询对象
	 * @return 影响的条数
	 */
	public int updateCustomerConsult(CustomerConsult customerConsult);
	/**
	 * 分页查询客户咨询列表
	 * @param customerConsult 客户咨询对象
	 * @param pagination 分页条件
	 * @param fuzzyValue 模糊查询条件
	 * @param sort 配需字段
	 * @param order 顺序
	 * @param history 历史标记
	 * @param session  
	 * 
	 * @return 分页列表
	 */
	public Pagination searchCustomerConsults(CustomerConsultVo customerConsult,
		Pagination pagination,String fuzzyValue, String sort,String order,String history,HttpSession session);
	
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
	 * @param telphone 
	 * @return   List<CustomerConsult> 
	 */
	public List<CustomerConsult> selectHiatory(String telphone);
	
	/**
	 *  批量 同步smp
	 * @return Message
	 */
	public Message updateSmpAll();
	
	/**
	 * 更新 同步 smp
	 * @param customerConsult CustomerConsult
	 * @return Message
	 */
	public Message updateSmp(CustomerConsult customerConsult);
	
	/**
	 * 修改MD5加密的电话号码为空的数据
	 * 
	 * @return 消息
	 */
	public Message updateTelphoneMd5();
	/**
	 * 
	 * @param section
	 * @return
	 */
	public Map<String, List<CodeTable>> selectDictionary(String section);
	
	/**
	 * @author 罗红杰
	 */
	/**
	 * 根据电话号码查询咨询业务
	 * @return List<CustomerConsult>
	 */
	public List<CustomerConsult> selectCustomerConsultListByTel(String telPhone);
	public Pagination selectCustomerConsultList(CustomerConsult customerConsult, Pagination pagination);
	
	public Message sendSMS(HttpServletRequest request);

    public String getManagerPhone();

    public void saveManagerPhone(String userName, String phone);
}
