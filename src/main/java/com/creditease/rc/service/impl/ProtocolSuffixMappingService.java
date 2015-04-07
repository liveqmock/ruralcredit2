/**
 * Title:ProtocolSuffixMappingService.java  
 * Description:  
 */
package com.creditease.rc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IProtocolSuffixMappingDAO;
import com.creditease.rc.domain.ProtocolSuffixMapping;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IProtocolSuffixMappingService;

/**
 * Title:ProtocolSuffixMappingService.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-3-18 
 */
@Service
public class ProtocolSuffixMappingService implements IProtocolSuffixMappingService {

	/**
	 * @Description 默认构造器 
	 */
	public ProtocolSuffixMappingService() {
		// TODO Auto-generated constructor stub
	}
	@Resource
	private IProtocolSuffixMappingDAO protocolSuffixMappingDAO;
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  插入
	 * @param record 
	 * @version v1.0 
	 * 2013-3-18
	 */
	public void insert(ProtocolSuffixMapping record){
		this.protocolSuffixMappingDAO.insert(record);
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
    public int updateByPrimaryKey(ProtocolSuffixMapping record){
    	return this.protocolSuffixMappingDAO.updateByPrimaryKey(record);
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
    public int updateByPrimaryKeySelective(ProtocolSuffixMapping record){
    	return this.protocolSuffixMappingDAO.updateByPrimaryKeySelective(record);
    }

   /**
    * 
    * @author 韩大年  
    * @Description:  查询
    * @param suffixId
    * @return 
    * @version v1.0 
    * 2013-3-18
    */
    public ProtocolSuffixMapping selectByPrimaryKey(Long suffixId){
    	return this.protocolSuffixMappingDAO.selectByPrimaryKey(suffixId);
    }

    /**
     * 
     * @author 韩大年  
     * @Description:  删除
     * @param suffixId
     * @return 
     * @version v1.0 
     * 2013-3-18
     */
    public int deleteByPrimaryKey(Long suffixId){
    	return this.protocolSuffixMappingDAO.deleteByPrimaryKey(suffixId);
    }
    
    /**
     * 
     * @author 韩大年  
     * @Description:  分页查询
     * @param protocolSuffixMapping
     * @param pagination
     * @return 
     * @version v1.0 
     * 2013-3-18
     */
     public Pagination selectProtocolSuffixMappingByPagination(ProtocolSuffixMapping protocolSuffixMapping,Pagination pagination){
    	 return this.protocolSuffixMappingDAO.selectProtocolSuffixMappingByPagination(protocolSuffixMapping, pagination);
     }
     /**
      * 
      * @author 韩大年  
      * @Description:  查询编号起始号码List
      * @param protocolSuffixMapping
      * @return 
      * @version v1.0 
      * 2013-3-27
      */
     public List<ProtocolSuffixMapping> selectProtocolSuffixMappingListByCondition(ProtocolSuffixMapping protocolSuffixMapping){
    	 return this.protocolSuffixMappingDAO.selectProtocolSuffixMappingListByCondition(protocolSuffixMapping);
     }

}
