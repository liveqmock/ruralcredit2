/**
 * Title:ProtocolSuffixMappingController.java  
 * Description:  
 */
package com.creditease.rc.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.common.DepartmentEntity;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.ProtocolSuffixMapping;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IProtocolSuffixMappingService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.SmpWSUtil;

/**
 * Title:ProtocolSuffixMappingController.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-3-18 
 */
@Controller
@RequestMapping("protocolSuffixMappingController")
public class ProtocolSuffixMappingController {

	/**
	 * @Description 默认构造器 
	 */
	public ProtocolSuffixMappingController() {
		// TODO Auto-generated constructor stub
	}

	@Resource
	private IProtocolSuffixMappingService protocolSuffixMappingService;
	@Resource
	private SmpWSUtil smpWSUtil;
	/**
	 * 
	 * @author 韩大年
	 * @Description: 合同后缀分页查询
	 * @param operateLogVO
	 * @param page
	 * @param rows
	 * @param request
	 * @return
	 * @version v1.0 2013-3-12
	 */
	@RequestMapping("queryProtocolSuffixMappingList")
	@ResponseBody
	public Pagination queryProtocolSuffixMappingList(ProtocolSuffixMapping protocolSuffixMapping,
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
		pagination = protocolSuffixMappingService.selectProtocolSuffixMappingByPagination(protocolSuffixMapping, pagination);
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
	 * @Description:  插入
	 * @return 
	 * @version v1.0 
	 * 2013-3-18
	 */
	@RequestMapping("/insertOrUpdateProtocolSuffixMapping")
	@ResponseBody
	public Message insertOrUpdateProtocolSuffixMapping(ProtocolSuffixMapping ProtocolSuffixMapping){
		
		Message  message=new Message();
		message.setSuccess(true);
		try {
			if(null==ProtocolSuffixMapping.getSuffixId()){
				message=validateProtocolSuffixMapping(ProtocolSuffixMapping);
				if(message.isSuccess()){
					message.setMsg("新增操作成功");
					this.protocolSuffixMappingService.insert(ProtocolSuffixMapping);
				}
			}else{
				message.setMsg("修改操作成功");
				this.protocolSuffixMappingService.updateByPrimaryKeySelective(ProtocolSuffixMapping);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setCode("9999");
			message.setMsg(e.getMessage());
			message.setSuccess(false);
		}
		
		return message;
		
	}
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  验证分公司与年度数据存在
	 * @param ProtocolSuffixMapping
	 * @return 
	 * @version v1.0 
	 * 2013-3-27
	 */
	public Message validateProtocolSuffixMapping(ProtocolSuffixMapping ProtocolSuffixMapping){
		ProtocolSuffixMapping vo= new ProtocolSuffixMapping();
		vo.setBranchofficeId(ProtocolSuffixMapping.getBranchofficeId());
		vo.setSimpleYear(ProtocolSuffixMapping.getSimpleYear());
		Message message= new Message();
		List<ProtocolSuffixMapping > protocolSuffixMappingList=this.protocolSuffixMappingService.selectProtocolSuffixMappingListByCondition(vo);
		if(CommonUtil.isEmpty(protocolSuffixMappingList)){
			message.setSuccess(true);
		}else{
			message.setSuccess(false);
			message.setMsg(ProtocolSuffixMapping.getBranchofficeName()+ProtocolSuffixMapping.getSimpleYear()+"年编号已存在");
		}
		return message;
		
	}
}
