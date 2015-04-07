/**
 * Title:IProtocolMappingService.java  
 * Description:  
 */
package com.creditease.rc.service;

import com.creditease.rc.domain.ProtocolMapping;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.ProtocolVO;

/**
 * Title:IProtocolMappingService.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-3-18 
 */
public interface IProtocolMappingService {

	/**
	 * 
	 * @author 韩大年  
	 * @Description:  新增
	 * @param record 
	 * @version v1.0 
	 * 2013-3-18
	 */
	 void insert(ProtocolMapping record);

   /**
    * 
    * @author 韩大年  
    * @Description:  全部修改
    * @param record
    * @return 
    * @version v1.0 
    * 2013-3-18
    */
    int updateByPrimaryKey(ProtocolMapping record);

    /**
     * 
     * @author 韩大年  
     * @Description:  动态修改
     * @param record
     * @return 
     * @version v1.0 
     * 2013-3-18
     */
    int updateByPrimaryKeySelective(ProtocolMapping record);

    /**
     * 
     * @author 韩大年  
     * @Description:  根据主键查询
     * @param mappingId
     * @return 
     * @version v1.0 
     * 2013-3-18
     */
    ProtocolMapping selectByPrimaryKey(Long mappingId);

    /**
     * 
     * @author 韩大年  
     * @Description:  根据主键删除
     * @param mappingId
     * @return 
     * @version v1.0 
     * 2013-3-18
     */
    int deleteByPrimaryKey(Long mappingId);
    
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
     public Pagination selectProtocolMappingByPagination(ProtocolVO protocolVO,Pagination pagination);
     
     /**
      * @author 罗红杰  
      * 根据creditapplicationId查询合同编号
      * 
      */
     public ProtocolMapping selectProtocolNumber(Long creditapplicationId);
}
