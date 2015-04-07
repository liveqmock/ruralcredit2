package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.creditease.rc.report.excel.*;
import com.creditease.rc.service.IJsGridReportBaseXLSXService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.common.DepartmentEntity;
import com.creditease.rc.domain.CodeTable;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.CustomerConsult;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.report.excel.ExcelUtils;
import com.creditease.rc.report.excel.JsGridReportBase;
import com.creditease.rc.report.excel.TableData;
import com.creditease.rc.service.IBorrowerServiceAppService;
import com.creditease.rc.service.ICustomerConsultService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DicUtil;
import com.creditease.rc.util.SmpWSUtil;
import com.creditease.rc.vo.CreditApplicationSearch;
import com.creditease.rc.vo.CustomerConsultVo;

/**
 * 
 * @author zhangman
 *
 */
@Controller
@RequestMapping(value="CustomerConsult")
public class CustomerConsultController {
	/**
	 * 
	 * @param binder
	 *            WebDataBinder对象
	 */
    @Resource
    private IJsGridReportBaseXLSXService jsGridReportBaseXLSXService;
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
//	/**
//	 * 
//	 * @param binder
//	 *            WebDataBinder对象
//	 */
//	@InitBinder
//	public void initBinde(WebDataBinder binder) {
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		dateFormat.setLenient(true);
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//	}
	@Resource
	private ICustomerConsultService customerConsultService;
	@Resource
	private SmpWSUtil smpWSUtil;
	@Resource
	private IBorrowerServiceAppService borrowerServiceAppService;
	/**
	 * 新增客户咨询
	 * @param customerConsult   客户咨询对象
	 * @return id
	 */
	@RequestMapping(value="addCustomerConsult")
	public @ResponseBody 
	long addCustomerConsultReturn(CustomerConsult customerConsult){
		
		return   customerConsultService.addCustomerConsult(customerConsult);
	};
	/**
	 * 修改客户咨询信息
	 * @param customerConsult 客户咨询对象
	 * @return 影响的条数
	 */
	@RequestMapping(value="update")
	public @ResponseBody 
	int updateCustomerConsult(CustomerConsult customerConsult){
		return customerConsultService.updateCustomerConsult(customerConsult);
	};
	
	/**
	 * 新增修改客户咨询信息
	 * @param customerConsult 客户咨询对象
	 * @return 影响的条数
	 */
	@RequestMapping(value="addUpdate")
	public @ResponseBody 
	Message addUpdate(CustomerConsult customerConsult){
		Message message = new Message();
		Date date = new Date();
		//当前时间延长 30分钟
		if(customerConsult.getConsultDate().getTime() > (date.getTime()+1000*60*30)){
			message.setSuccess(false);
			message.setMsg("咨询时间大于当前系统时间，当前时间为-"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(date.getTime()))+"-,允许误差在30 分钟以内");
		}else{
			if(customerConsultService.addUpdate(customerConsult) > 0){
				message.setSuccess(true);
				message.setMsg("保存成功");
			}else{
				message.setMsg("保存失败");
			};
		}
		return message;
	};
	
	/**
	 * 分页查询客户咨询列表
	 * @param customerConsult 客户咨询对象
	 * @param rows 显示行数
	 * @param page  当前页
	 * @param fuzzyValue 模糊查询条件
	 * @param sort  排序列
	 * @param order 排序
	 * @param request  请求
	 * @param history 历史
	 * @return 分页列表
	 */
	@RequestMapping(value="searchPagnation")
	public @ResponseBody 
	Pagination searchCustomerConsults(CustomerConsultVo customerConsult,String page,
		String rows,String fuzzyValue, String sort,String order,
		HttpServletRequest request,String history){
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page))
		{pagination.setPage(Integer.valueOf(page));	}
		if (!StringUtils.isBlank(rows))
		{pagination.setPageSize(Integer.valueOf(rows));}
		pagination=customerConsultService.searchCustomerConsults
		(customerConsult, pagination,fuzzyValue, sort, order,history,request.getSession());
		for (Object o : pagination.getRows()) {
			CustomerConsult m=(CustomerConsult)o;
		// 获取数据字典客户标签（processCausesPool）：code_value，用于前台数据显示，
		if (StringUtils.isNotEmpty(m.getProcessCauses())) {
			m.setProcessCausesDetail(getCodeValueStrs("processCauses", m.getProcessCauses().split(",")));
		}
		}
		return pagination;
	};
	
	/**
	 * 跳转到申请页面
	 * @param customerConsultId 客户咨询id
	 * @param request 请求类对象
	 * @return 几款申请页面
	 */
	@RequestMapping(value="toAddApplication")
	public String toAddApplication(Long customerConsultId,HttpServletRequest request){
		CreditApplication creditApplicationReturn = new CreditApplication();
		creditApplicationReturn.setCustomerConsultId(customerConsultId);
		request.setAttribute("creditApplicationReturn", creditApplicationReturn);
		return "/jsp/rc/business/loanApply.jsp";
	}
	
	/**
	 * 跳转到申请页面
	 * @author hongjieluo
	 * @param request 请求类对象
	 * @return 几款申请页面
	 */
	@RequestMapping(value="poolToAddApplication")
	public String poolToAddApplication(Long consultPoolId,HttpServletRequest request){
		CreditApplication creditApplicationReturn = new CreditApplication();
		creditApplicationReturn.setConsultPoolId(consultPoolId);
		request.setAttribute("creditApplicationReturn", creditApplicationReturn);
		return "/jsp/rc/business/loanApply.jsp";
	}
	/**
	 * 
	 * @param telphone 
	 * @return List<CustomerConsult> 
	 */
	@RequestMapping(value = "history")
	public @ResponseBody 
	List<CustomerConsult> selectHiatory(String telphone){
		List<CustomerConsult> customerConsultList=customerConsultService.selectHiatory(telphone);
		for (Object o : customerConsultList) {
			CustomerConsult m=(CustomerConsult)o;
		// 获取数据字典客户标签（processCausesPool）：code_value，用于前台数据显示，
		if (StringUtils.isNotEmpty(m.getProcessCauses())) {
			m.setProcessCausesDetail(getCodeValueStrs("processCauses", m.getProcessCauses().split(",")));
		}
		}
		return customerConsultList;
	}
	/**
	 * 部门下拉框
	* @author wyf  
	* @return: List<DepartmentEntity> 
	 */
	@RequestMapping(value = "departmentList")
	@ResponseBody
	public List<DepartmentEntity> departmentList(){
		List<DepartmentEntity> entities = new ArrayList<DepartmentEntity>();
		DepartmentEntity de = new DepartmentEntity();
		de.setDepartmentName("请选择");
		de.setDepartmentId("");
		List<DepartmentEntity> entities1 = smpWSUtil.getDepartmentList();
		entities.add(de);
		entities1.addAll(0, entities);
		return entities1;
	}
	
	/**
	 *  * 客户咨询Excel导出，获取的数据格式是List<JavaBean>
	 * @param customerConsult 客户咨询
	 * @param fuzzyValue 模糊查询值
	 * @param request 请求
	 * @param response 回应
	 * @throws Exception 异常
	 */
	@RequestMapping(value="exportConsultExcel")
	public void exportExcel(CustomerConsultVo customerConsult,
		String fuzzyValue,HttpServletRequest request,HttpServletResponse response) throws Exception{
        response.setContentType("application/msexcel;charset=UTF-8");
        Pagination pagination = new Pagination();
        pagination.setPageSize(0);
       // customerConsult.setHistory("");
        pagination = customerConsultService.searchCustomerConsults(customerConsult, pagination,fuzzyValue, null, null,null,request.getSession());
        
        List<CustomerConsult> list = pagination.getRows();//获取数据
        
        if(CommonUtil.isNotEmpty(list)){
        	String title = "客户咨询列表";
            String[] hearders = new String[] {"咨询时间","首次创建时间","最后操作时间","咨询方式 ","客户姓名","性别","行业分类",
            		"客户住址","借款金额(元)","借款用途","信息来源","客户分类","客户标签","备注","分公司名称","接待员工","咨询次数","状态","业务编号","创建客户经理"};//表头数组
            String[] fields = new String[] {"consultDate","createDate", "operateTime", "consultWayDetail", "customerName","genderDetail","industryCategoryDetail",
            		"address","borrowAmount","borrowUseDetail","infomationSourceDetail","processResultDetail","processCausesDetail",
            		"remark","departmentName","receptionist","count","auditStatusDetail","businessNumber","createManager"};//对象属性数组
            TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders),fields);
            //JsGridReportBase report = new JsGridReportBase(request, response);
            jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
            jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);
        }else{
        	String title = "客户咨询列表";
            String[] hearders = new String[] {"咨询时间","首次创建时间","最后操作时间","咨询方式 ","客户姓名","性别","行业分类",
            		"客户住址","借款金额(元)","借款用途","信息来源","客户分类","客户标签","备注","分公司名称","接待员工","咨询次数","状态","业务编号","创建客户经理"};//表头数组
            CustomerConsult cu=new CustomerConsult();
            list.add(cu);
            String[] fields = new String[] {"", "", "", "","","","","","","","","","","","","","","","",""};//对象属性数组
            TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders),fields);
           /* JsGridReportBase report = new JsGridReportBase(request, response);
            report.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);*/
            jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
            jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);
        }
       
	}

	/**
	 * 更新 同步 smp
	 * @param customerConsult CustomerConsult
	 * @return Message
	 */
	@RequestMapping(value = "updateSmp")
	public @ResponseBody 
	Message updateSmp(CustomerConsult customerConsult){
		return customerConsultService.updateSmp(customerConsult);
	}
	/**
	 *  批量 同步smp
	 * @return Message
	 */
	@RequestMapping(value = "updateSmpAll")
	public @ResponseBody Message updateSmpAll(){
		return customerConsultService.updateSmpAll();
	}
	
	/**
	 * 查询 客户咨询
	 * @param customerConsult CustomerConsult
	 * @return CustomerConsult
	 */
	@RequestMapping(value = "selectCustomerConsult")
	public @ResponseBody
	CustomerConsult selectCustomerConsult(CustomerConsult customerConsult){
		return customerConsultService.selectCustomerConsult(customerConsult);
	}
	
	/**
	 * 修改MD5加密的电话号码为空的数据
	 * 
	 * @return 消息
	 */
	@RequestMapping(value = "updateTelphoneMd5")
	public @ResponseBody Message updateTelphoneMd5(){
		return customerConsultService.updateTelphoneMd5();
	}
	
	@RequestMapping(value = "validConsultTime")
	public @ResponseBody
	Message validConsultTime(CustomerConsult customerConsult){
		Message message = new Message();
		Date date = new Date();
		//当前时间延长 30分钟
		if(customerConsult.getConsultDate().getTime() > (date.getTime()+1000*60*30)){
			message.setMsg("咨询时间大于当前系统时间，当前时间为-"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(date.getTime()))+"-,允许误差在30 分钟以内");
		}else{
			message.setSuccess(true);
		}
		return message;
	}
	
	/**
	 * 用于查询用 选择对话框
	 * @param section
	 * @return
	 */
	@RequestMapping(value = "selectDictionary")
	public @ResponseBody Map<String, List<CodeTable>> selectDictionary(String section){
		return customerConsultService.selectDictionary(section);
	}
	
	/**
	 * 根据电话号码查询咨询业务
	 */
	@RequestMapping(value = "selectCustomerConsultByTel")
	@ResponseBody
	public Map<String, String> selectCustomerConsultByTel(String telphone){
		Map<String, String> map = new HashMap<String, String>();
		 List<CustomerConsult> customerConsultlist =customerConsultService.selectCustomerConsultListByTel(telphone);
		if(customerConsultlist != null){
			map.put("customerNumber", String.valueOf(customerConsultlist.size()));
		}else{
			map.put("customerNumber", "0");
		}
		
		List<CreditApplicationSearch>  applicationBorrower= borrowerServiceAppService.selectBorrowerAuditListByTel(telphone);
		if(applicationBorrower != null){
			map.put("borrowerNum", String.valueOf(applicationBorrower.size()));
		}else{
			map.put("borrowerNum", "0");
		}
		
		List<CreditApplicationSearch>  applicationGuantor= borrowerServiceAppService.selectBorrowerServiceByTel(telphone);
		if(applicationGuantor != null){
			map.put("guantorNum", String.valueOf(applicationGuantor.size()));
		}else{
			map.put("guantorNum", "0");
		}
		return map;
	}
	/**
	 * 获取数据字典中多个 code_value 组成的字符串
	 * 
	 * @param section
	 * @param keys
	 * @return
	 */
	public String getCodeValueStrs(String section, String[] keys) {
		StringBuffer s = new StringBuffer();
		for (String key : keys) {
			if (s.length() > 0) {
				s.append(",");
			}
			s.append(DicUtil.convertCodeKeyToCodeValue(section, key));
		}
		return s.length() == 0 ? null : s.toString();
	}

    /*
    客户咨询-发送短信（包括客户经理联系方式等信息）
     */
    @RequestMapping(value = "sendSMS")
    public
    @ResponseBody
    Message sendSMS(HttpServletRequest request) {
        Message message;
        try {
            message = customerConsultService.sendSMS(request);
        } catch (Exception e) {
            message = new Message(false, "发送失败了..." + e.getMessage());
        }
        customerConsultService.saveManagerPhone(SpringSecurityUtils.getCurrentUser().getUsername(), request.getParameter("manager_phone"));
        return message;
    }

    /*
    客户咨询-客户经理联系方式
     */
    @RequestMapping(value = "getManagerNameAndPhone")
    public
    @ResponseBody
    Map<String, String> getManagerNameAndPhone() {
        ConcurrentHashMap<String, String> myInfo = new ConcurrentHashMap<String, String>();
        try {
            myInfo.put("myName", SpringSecurityUtils.getCurrentUser().getName_zh());
            myInfo.put("myPhone", customerConsultService.getManagerPhone());
        } catch (Exception e) {
        }
        return myInfo;
    }
}
