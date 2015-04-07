/**
 * Title:ProtocolMappingService.java  
 * Description:  
 */
package com.creditease.rc.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.dao.IProtocolMappingDAO;
import com.creditease.rc.domain.ProtocolMapping;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IProtocolMappingService;
import com.creditease.rc.vo.ProtocolVO;

/**
 * Title:ProtocolMappingService.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-3-18 
 */
@Service
public class ProtocolMappingService implements IProtocolMappingService {

	/**
	 * @Description 默认构造器 
	 */
	public ProtocolMappingService() {
		// TODO Auto-generated constructor stub
	}
	@Resource
	private IProtocolMappingDAO protocolMappingDAO;
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  新增
	 * @param record 
	 * @version v1.0 
	 * 2013-3-18
	 */
	 public void insert(ProtocolMapping record){
		  record.setOperateDate(new Date());
		  record.setOperator(SpringSecurityUtils.getCurrentUser().getName_zh());
		  this.protocolMappingDAO.insert(record);
	 }

   /**
    * 
    * @author 韩大年  
    * @Description:  全部修改
    * @param record
    * @return 
    * @version v1.0 
    * 2013-3-18
    */
	public int updateByPrimaryKey(ProtocolMapping record){
		return this.protocolMappingDAO.updateByPrimaryKey(record);
	}

    /**
     * 
     * @author 韩大年  
     * @Description:  动态修改
     * @param record
     * @return 
     * @version v1.0 
     * 2013-3-18
     */
	public int updateByPrimaryKeySelective(ProtocolMapping record){
		return this.protocolMappingDAO.updateByPrimaryKeySelective(record);
	}

    /**
     * 
     * @author 韩大年  
     * @Description:  根据主键查询
     * @param mappingId
     * @return 
     * @version v1.0 
     * 2013-3-18
     */
	public ProtocolMapping selectByPrimaryKey(Long mappingId){
		return this.protocolMappingDAO.selectByPrimaryKey(mappingId);
	}

    /**
     * 
     * @author 韩大年  
     * @Description:  根据主键删除
     * @param mappingId
     * @return 
     * @version v1.0 
     * 2013-3-18
     */
	public int deleteByPrimaryKey(Long mappingId){
		return this.protocolMappingDAO.deleteByPrimaryKey(mappingId);
	}
    
    /**
     * 
     * @author 韩大年  
     * @Description:  分页查询
     * @param operateLogVO
     * @param pagination
     * @return 
     * @version v1.0 
     * 2013-3-18
     */
     public Pagination selectProtocolMappingByPagination(ProtocolVO protocolVO,Pagination pagination){
    	 return this.protocolMappingDAO.selectProtocolMappingByPagination(protocolVO, pagination);
     }

	@Override
	public ProtocolMapping selectProtocolNumber(Long creditapplicationId) {
		return this.protocolMappingDAO.selectProtocolNumber(creditapplicationId);
	}

}
