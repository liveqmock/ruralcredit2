/**
 * Title:ProtocolPrefixMappingService.java  
 * Description:  
 */
package com.creditease.rc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IProtocolPrefixMappingDAO;
import com.creditease.rc.domain.ProtocolPrefixMapping;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IProtocolPrefixMappingService;

/**
 * Title:ProtocolPrefixMappingService.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-3-18 
 */
@Service
public class ProtocolPrefixMappingService implements
		IProtocolPrefixMappingService {

	/**
	 * @Description 默认构造器 
	 */
	public ProtocolPrefixMappingService() {
		// TODO Auto-generated constructor stub
	}
	@Resource
	private IProtocolPrefixMappingDAO prefixMappingDAO;
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  插入
	 * @param record 
	 * @version v1.0 
	 * 2013-3-18
	 */
	public void insert(ProtocolPrefixMapping record){
		this.prefixMappingDAO.insert(record);
	}

    /**
     * 
     * @author 韩大年  
     * @Description:  全部更新
     * @param record
     * @return 
     * @version v1.0 
     * 2013-3-18
     */
    public int updateByPrimaryKey(ProtocolPrefixMapping record){
    	return this.prefixMappingDAO.updateByPrimaryKey(record);
    }
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  动态更新
	 * @param record
	 * @return 
	 * @version v1.0 
	 * 2013-3-18
	 */
    public int updateByPrimaryKeySelective(ProtocolPrefixMapping record){
    	return this.prefixMappingDAO.updateByPrimaryKeySelective(record);
    }

   /**
    * 
    * @author 韩大年  
    * @Description:  查询
    * @param prefixId
    * @return 
    * @version v1.0 
    * 2013-3-18
    */
    public ProtocolPrefixMapping selectByPrimaryKey(Long prefixId){
    	return this.prefixMappingDAO.selectByPrimaryKey(prefixId);
    }

    /**
     * 
     * @author 韩大年  
     * @Description:  删除
     * @param prefixId
     * @return 
     * @version v1.0 
     * 2013-3-18
     */
    public int deleteByPrimaryKey(Long prefixId){
    	return this.prefixMappingDAO.deleteByPrimaryKey(prefixId);
    }
    
   /**
    * 
    * @author 韩大年  
    * @Description:  分页查询
    * @param protocolPrefixMapping
    * @param pagination
    * @return 
    * @version v1.0 
    * 2013-3-18
    */
     public Pagination selectProtocolPrefixMappingByPagination(ProtocolPrefixMapping protocolPrefixMapping,Pagination pagination){
    	 return this.prefixMappingDAO.selectProtocolPrefixMappingByPagination(protocolPrefixMapping, pagination);
     }
     /**
      * 
      * @author 韩大年  
      * @Description:  查询ProtocolPrefixMappingList
      * @param protocolPrefixMapping
      * @return 
      * @version v1.0 
      * 2013-3-25
      */
     public List<ProtocolPrefixMapping> selectProtocolPrefixMappingListSelective(ProtocolPrefixMapping protocolPrefixMapping){
    	 return this.prefixMappingDAO.selectProtocolPrefixMappingListSelective(protocolPrefixMapping);
     }

	@Override
	public List<ProtocolPrefixMapping> queryDistrictAndDepartment() {
		// TODO Auto-generated method stub
		return prefixMappingDAO.queryDistrictAndDepartment();
	}

}
