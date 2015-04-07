package com.creditease.rc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.creditease.rc.service.IMessageInfoService;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.dao.ICustomerConsultDAO;
import com.creditease.rc.domain.CodeTable;
import com.creditease.rc.domain.CustomerConsult;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.ICustomerConsultService;
import com.creditease.rc.service.IDataDictionaryService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.SmpWSUtil;
import com.creditease.rc.vo.CustomerConsultVo;

/**
 * 
 * @author zhangman
 *
 */
@Service
public class CustomerConsultService implements ICustomerConsultService {
	@Resource
	private ICustomerConsultDAO customerConsultDAO;
	@Resource
	private SmpWSUtil smpWSUtil;
	@Resource
	private IDataDictionaryService dataDictionaryService;
    @Resource
    private IMessageInfoService messageInfoService;

	@Override
	public long addCustomerConsult(CustomerConsult customerConsult) {
		return customerConsultDAO.insertCustomerConsult(customerConsult);
	}
	
	@Override
	public CustomerConsult addCustomerConsultReturn(CustomerConsult customerConsult) {
		customerConsultDAO.update("ContomerConsult.updateByNumber", customerConsult.getTelphone());
		Long customerConsultId = customerConsultDAO.insertCustomerConsult(customerConsult);
		customerConsult.setCustomerConsultId(customerConsultId);
		return customerConsult;
	}
	
	
	// * 新增客户咨询
	 //* zhangman
	@Override
	public long addUpdate(CustomerConsult customerConsult) {
		Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
		String telphoneMd5 = md5PasswordEncoder.encodePassword(customerConsult.getTelphone(), null);
		System.out.println("加密的电话号码:"+telphoneMd5);
		customerConsult.setTelphoneMd5(telphoneMd5);
		if(customerConsult.getCustomerConsultId() == null || "".equals(customerConsult.getCustomerConsultId()) ){
			long count = (Long) customerConsultDAO.queryUnique("ContomerConsult.selectCount", customerConsult);
			customerConsult.setCount(count+1);
			customerConsultDAO.update("ContomerConsult.updateFlagCount",customerConsult);
			return customerConsultDAO.insertCustomerConsult(customerConsult);
		}else{
			return customerConsultDAO.updateCustomerConsult(customerConsult);
		}
	}
	
	@Override
	public int deleteCustomerConsult(Integer customerConsultId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCustomerConsult(CustomerConsult customerConsult) {
		// TODO Auto-generated method stub
		return customerConsultDAO.updateCustomerConsult(customerConsult);
	}

	@Override
	public Pagination searchCustomerConsults(CustomerConsultVo customerConsult, 
		Pagination pagination,String fuzzyValue, String sort,String order,String history,HttpSession session) {
		Integer arearDepartmentIdInteger = SpringSecurityUtils.getCurrentUser().getAreaDepartmentId();
		String arearDepartmentId= "";
		if(arearDepartmentIdInteger != null){
			arearDepartmentId= String.valueOf(arearDepartmentIdInteger);	
		}
		
		
		// 添加权限查询
		List<String> authList = SpringSecurityUtils.getAuthList(session);
		String sqlsid = "";
		if(authList.size()>0){
			for(String e : authList){
				if(sqlsid!=null){
					sqlsid= sqlsid+"'"+e+"'"+",";
				}else{
					sqlsid= "'"+e+"'"+",";
				}
			}
			sqlsid = sqlsid.substring(0, sqlsid.length()-1);
		}
		
		if("consultDate".equals(sort)){
			sort = "CONSULT_DATE";
		}else if("consultWay".equals(sort)){
			sort = "CONSULT_WAY";
		}else if("consultWayDetail".equals(sort)){
			sort = "CONSULT_WAY";
		}else if("borrowAmount".equals(sort)){
			sort = "BORROW_AMOUNT";
		}else if("borrowAmountDetail".equals(sort)){
			sort = "BORROW_AMOUNT";
		}else if("borrowUse".equals(sort)){
			sort = "BORROW_USE";
		}else if("borrowUseDetail".equals(sort)){
			sort = "BORROW_USE";
		}else if("infomationSource".equals(sort)){
			sort = "INFOMATION_SOURCE";
		}else if("infomationSourceDetail".equals(sort)){
			sort = "INFOMATION_SOURCE";
		}else if("status".equals(sort)){
			sort = "STATUS";
		}else if("receptionist".equals(sort)){
			sort = "RECEPTIONIST";
		}else if("receptionistId".equals(sort)){
			sort = "RECEPTIONIST_ID";
		}else if("operateTime".equals(sort)){
			sort = "OPERATE_TIME";
		}else if("departmentName".equals(sort)){
			sort = "DEPARTMENT_NAME";
		}else if("createDate".equals(sort)){
			sort = "CREATE_DATE";
		}else{
			sort = "";
		}
		 
		
		Map searchValue = new HashMap();
		searchValue.put("sort", sort);
		searchValue.put("order", order);
		searchValue.put("authList", sqlsid);
		searchValue.put("arearDepartmentId", arearDepartmentId);
		searchValue.put("history", history);
		if(CommonUtil.isNotEmpty(fuzzyValue)){
			searchValue.put("fuzzyValue", fuzzyValue);
			return customerConsultDAO.searchByFuzzyValueMap(searchValue,pagination);
		}else{
			searchValue.put("customerConsult", customerConsult);
			return customerConsultDAO.searchByFuzzyValueMap(searchValue, pagination);
		}
	}
	@Override
	public CustomerConsult selectCustomerConsult(CustomerConsult customerConsult) {
		// TODO Auto-generated method stub
		customerConsult =customerConsultDAO.selectCustomerConsult(customerConsult);
		return customerConsult;
	}
	
	@Override
	public CustomerConsult selectCustomerConsultById(Long customerConsultId) {
		// TODO Auto-generated method stub
		if(customerConsultId != null){
			return customerConsultDAO.selectCustomerConsultById(customerConsultId);
		}else{
			return null;
		}
	}
	
	@Override
	public List<CustomerConsult> selectHiatory(String telphone) {
		// TODO Auto-generated method stub
		return (List<CustomerConsult>) customerConsultDAO.queryList("ContomerConsult.selectHistory",telphone);
	}
	/**
	 * 更新 同步 smp
	 * @param customerConsult CustomerConsult
	 * @return Message
	 */
	@Override
	public Message updateSmp(CustomerConsult customerConsult) {
		Message message = new Message();
		int result = 0;
			Map<String, String> map = smpWSUtil.getAreaDepartmentNameMapByLoanOfficerId(customerConsult.getReceptionistId());
			Set<Map.Entry<String, String>> entries = map.entrySet();
			for (Entry<String, String> entry : entries) {
				customerConsult.setDepartmentId(entry.getKey());
				customerConsult.setDepartmentName(entry.getValue());
				
				if(customerConsult.getDepartmentId() != null
						&& !"".equals(customerConsult.getDepartmentId())
						&& customerConsult.getDepartmentName() != null
						&&!"".equals(customerConsult.getDepartmentName() )){
					result = customerConsultDAO.updateCustomerConsult(customerConsult);
				}
			}
		if(result> 0){
			message.setSuccess(true);
		}
		return message;
	}
	
	/**
	 *  批量 同步smp
	 * @return Message
	 */
	@Override
	public Message updateSmpAll() {
		Message message = new Message();
		List<CustomerConsult> consults = customerConsultDAO.selectByreceptionistId();
		int result = 0;
		for (CustomerConsult customerConsult : consults) {
			Map<String, String> map = smpWSUtil.getAreaDepartmentNameMapByLoanOfficerId(customerConsult.getReceptionistId());
			Set<Map.Entry<String, String>> entries = map.entrySet();
			for (Entry<String, String> entry : entries) {
				customerConsult.setDepartmentId(entry.getKey());
				customerConsult.setDepartmentName(entry.getValue());
				
				if(customerConsult.getDepartmentId() != null
						&& !"".equals(customerConsult.getDepartmentId())
						&& customerConsult.getDepartmentName() != null
						&&!"".equals(customerConsult.getDepartmentName() )){
					int count = customerConsultDAO.updateByreceptionistId(customerConsult);
					result  = result +count;
				}
			}
		}
		if(result> 0){
			message.setSuccess(true);
		}
		return message;
	}
	
	//修改MD5加密的电话号码为空的数据
	@Override
	public Message updateTelphoneMd5() {
		Message message = new Message();
		//查询md5加密号码为空的咨询
		List<CustomerConsult> customerConsults = customerConsultDAO.selectCustomerConsultList();
		for (CustomerConsult customerConsult : customerConsults) {
			Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
			String telphoneMd5 = md5PasswordEncoder.encodePassword(customerConsult.getTelphone(), null);
			System.out.println("加密的电话号码:"+telphoneMd5);
			customerConsult.setTelphoneMd5(telphoneMd5);
		}
		int result = customerConsultDAO.updateBatch(customerConsults);
		if(customerConsults.size() ==0){
			message.setMsg("没有加密电话号码为空的数据");
			return message;
		}
		if(result == 0){
			message.setMsg(customerConsults.size()+"条数据为空。更新数据0条");
		}
		else if(customerConsults.size() == result){
			message.setSuccess(true);
			message.setMsg(customerConsults.size()+"条数据为空。成功更新"+result+"条数据");
		}else if(customerConsults.size() > result){ 
			message.setSuccess(true);
			message.setMsg(customerConsults.size()+"条数据为空。成功更新"+result+"条数据");
		} 
		return message;
	}
	
	@Override
	public Map<String, List<CodeTable>> selectDictionary(String section) {
		Map<String, List<CodeTable>> map = new HashMap<String, List<CodeTable>>();
		List<CodeTable> codeTables =  dataDictionaryService.getSpecifiedDic("", section);
		map.put(section, codeTables);
		for (CodeTable codeTable : codeTables) {
			List<CodeTable> list = dataDictionaryService.selectByParentId(codeTable.getCodaTableId());
			map.put(section + codeTable.getCodaTableId(), list);
		}
		return map;
	}
	 /**
	    * @author luohongjie
	    */
		@Override
		public List<CustomerConsult> selectCustomerConsultListByTel(String telPhone) {
		    return customerConsultDAO.selectCustomerConsultListByTel(telPhone);
		}
		
		@Override
		public Pagination selectCustomerConsultList(CustomerConsult customerConsult, Pagination pagination) {
			return customerConsultDAO.selectCustomerConsultList(customerConsult,pagination);
		}

    @Override
    public Message sendSMS(HttpServletRequest request) {
        Map map = new HashMap();
        map.put("phone", request.getParameter("customer_phone"));
        map.put("custName", request.getParameter("manager_name"));
        map.put("phoneCode", request.getParameter("manager_phone"));
        return messageInfoService.sendMsgForCustomerConsultation(map);
    }

    @Override
    public String getManagerPhone(){
        return customerConsultDAO.getManagerPhone(SpringSecurityUtils.getCurrentUser().getUsername());
    }

    @Override
    public void saveManagerPhone(String userName, String phone){
        if(null != this.getManagerPhone()){
            customerConsultDAO.updateManagerPhone(SpringSecurityUtils.getCurrentUser().getUsername(), phone);
            return;
        }
        customerConsultDAO.saveManagerPhone(userName, phone);
    }
}

