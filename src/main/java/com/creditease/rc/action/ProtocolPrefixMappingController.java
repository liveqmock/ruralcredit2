/**
 * Title:ProtocolPrefixMappingController.java  
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

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.common.DepartmentEntity;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.ProtocolPrefixMapping;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IProtocolPrefixMappingService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.SmpWSUtil;

/**
 * Title:ProtocolPrefixMappingController.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-3-18 
 */
@Controller
@RequestMapping("protocolPrefixMappingController")
public class ProtocolPrefixMappingController {

	/**
	 * @Description 默认构造器 
	 */
	public ProtocolPrefixMappingController() {
		// TODO Auto-generated constructor stub
	}

	@Resource
	private IProtocolPrefixMappingService protocolPrefixMappingService;
	@Resource
	private SmpWSUtil smpWSUtil;
	/**
	 * 
	 * @author 韩大年
	 * @Description: 合同前缀分页查询
	 * @param operateLogVO
	 * @param page
	 * @param rows
	 * @param request
	 * @return
	 * @version v1.0 2013-3-12
	 */
	@RequestMapping("/queryProtocolPrefixMappingList")
	@ResponseBody
	public Pagination queryProtocolPrefixMappingList(ProtocolPrefixMapping protocolPrefixMapping,
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
		pagination = protocolPrefixMappingService.selectProtocolPrefixMappingByPagination(protocolPrefixMapping, pagination);
		return pagination;
	};
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  插入
	 * @return 
	 * @version v1.0 
	 * 2013-3-18
	 */
	@RequestMapping("/insertOrUpdateProtocolPrefixMapping")
	@ResponseBody
	public Message insertOrUpdateProtocolPrefixMapping(ProtocolPrefixMapping prefixMapping){
		
		Message  message=new Message();
		prefixMapping.setOperateDate(new Date());
		prefixMapping.setOperator(SpringSecurityUtils.getCurrentUser().getName_zh());
		try {
			if(null==prefixMapping.getPrefixId()){
				//新增时验证
				message=validateProtocolPrefixMapping(prefixMapping);
				if(message.isSuccess()){
					message.setMsg("新增操作成功");
					this.protocolPrefixMappingService.insert(prefixMapping);
					message.setSuccess(true);
				}
			}else{
				message.setMsg("修改操作成功");
				this.protocolPrefixMappingService.updateByPrimaryKeySelective(prefixMapping);
				message.setSuccess(true);
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
	 * @Description:  协议编号规则验证存在
	 * @param protocolPrefixMapping
	 * @return 
	 * @version v1.0 
	 * 2013-3-27
	 */
	@RequestMapping("/validateProtocolPrefixMapping")
	@ResponseBody
	public Message validateProtocolPrefixMapping(ProtocolPrefixMapping protocolPrefixMapping){
		ProtocolPrefixMapping newProtocolPrefixMapping=new ProtocolPrefixMapping();
		newProtocolPrefixMapping.setBranchofficeId(protocolPrefixMapping.getBranchofficeId());
		Message  message=new Message();
		try {
			List list=this.protocolPrefixMappingService.selectProtocolPrefixMappingListSelective(newProtocolPrefixMapping);
			if(CommonUtil.isEmpty(list)){
					message.setSuccess(true);
				
			}else{
				message.setSuccess(false);
				message.setMsg(protocolPrefixMapping.getBranchofficeName()+"配置已存在");
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
	 * @Description:  删除协议规则
	 * @param protocolPrefixMapping
	 * @return 
	 * @version v1.0 
	 * 2013-3-27
	 */
	@RequestMapping("/deleteProtocolPrefixMapping")
	@ResponseBody
	public Message deleteProtocolPrefixMapping(ProtocolPrefixMapping protocolPrefixMapping){
		Message message=new Message();
		message.setSuccess(true);
		if(null!=protocolPrefixMapping && null!=protocolPrefixMapping.getPrefixId()){
			int num=this.protocolPrefixMappingService.deleteByPrimaryKey(protocolPrefixMapping.getPrefixId());
			if(num>0){
				message.setMsg("删除成功！");
			}else{
				message.setMsg("删除失败！");
			}
		}else{
			message.setSuccess(false);
			message.setMsg("传入参数id为空");
		}
		return message;
	}
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
	
}
