/**
 * Title:ProtocolMappingController.java  
 * Description:  
 */
package com.creditease.rc.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.common.DepartmentEntity;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IProtocolManagementService;
import com.creditease.rc.service.IProtocolMappingService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.SmpWSUtil;
import com.creditease.rc.vo.ProtocolVO;

/**
 * Title:ProtocolMappingController.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-3-18 
 */
@Controller
@RequestMapping("/protocolMappingController")
public class ProtocolMappingController {

	/**
	 * @Description 默认构造器 
	 */
	public ProtocolMappingController() {
		// TODO Auto-generated constructor stub
	}
	@Resource
	private IProtocolMappingService protocolMappingService;
	@Resource
	private SmpWSUtil smpWSUtil;
	@Resource
	private IProtocolManagementService protocolManagementService;
	/**
	 * 
	 * @author 韩大年
	 * @Description: 合同记录分页查询
	 * @param operateLogVO
	 * @param page
	 * @param rows
	 * @param request
	 * @return
	 * @version v1.0 2013-3-12
	 */
	@RequestMapping("queryProtocolMappingList")
	@ResponseBody
	public Pagination queryProtocolMappingList(ProtocolVO protocolVO,
			@RequestParam(required = false) String page,
			@RequestParam(required = false) String rows,
			HttpServletRequest request) {
		Pagination pagination = new Pagination();
		if (CommonUtil.isNotEmpty(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (CommonUtil.isNotEmpty(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		pagination = protocolMappingService.selectProtocolMappingByPagination(protocolVO, pagination);
		return pagination;
	};
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  获取分公司名称
	 * @return 
	 * @version v1.0 
	 * 2013-3-18
	 */
	@RequestMapping("/getDepartmentList")
	@ResponseBody
	public List<DepartmentEntity> getDepartmentList(){
		
		return this.smpWSUtil.getDepartmentList();
	}
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  打印合同
	 * @param creditApplication
	 * @return 
	 * @version v1.0 
	 * 2013-3-26
	 */
	@RequestMapping("/saveAndDownProtocol")
	@ResponseBody
	public Message saveAndDownProtocol(CreditApplication creditApplication,String loanDate,HttpServletRequest request){
		Message message= new Message();
		try{
			Date loan_Date = null;
			if(CommonUtil.isNotEmpty(loanDate)){
				loan_Date=DateUtil.stringConvertDate(loanDate);
			}else{
				loan_Date=new Date();
			}
			String url=protocolManagementService.saveAndDownProtocol(creditApplication.getCreditapplicationId(),loan_Date);
			String isURL = request.getRequestURL().toString();
			if(isURL.indexOf(".cn")>0){
				if(url.indexOf(".corp")>0){
					url=url.replaceAll(".corp",".cn");
				}
			}else if(isURL.indexOf(".corp")>0){
				if(url.indexOf(".cn")>0){
					url=url.replaceAll(".cn",".corp");
				}
			}
			message.setSuccess(true);
			message.setMsg(url);
		}catch (Exception e) {
			// TODO: handle exception
			message.setSuccess(false);
			message.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return message;
	}

	
	@RequestMapping("/searchAndDownProtocol")
	@ResponseBody
	public Message searchAndDownProtocol(Integer creditapplicationId,HttpServletRequest request){
		return protocolManagementService.searchAndDownProtocol(creditapplicationId,request);
	}
}
