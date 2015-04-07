/**
 * Title:IProtocolSuffixMappingService.java  
 * Description:  
 */
package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.domain.ProtocolSuffixMapping;
import com.creditease.rc.framework.pager.Pagination;

/**
 * Title:IProtocolSuffixMappingService.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-3-18 
 */
public interface IProtocolSuffixMappingService {
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  插入
	 * @param record 
	 * @version v1.0 
	 * 2013-3-18
	 */
	public void insert(ProtocolSuffixMapping record);

   /**
    * 
    * @author 韩大年  
    * @Description:  全部修改
    * @param record
    * @return 
    * @version v1.0 
    * 2013-3-18
    */
    public int updateByPrimaryKey(ProtocolSuffixMapping record);

   /**
    * 
    * @author 韩大年  
    * @Description:  动态修改
    * @param record
    * @return 
    * @version v1.0 
    * 2013-3-18
    */
    public int updateByPrimaryKeySelective(ProtocolSuffixMapping record);

   /**
    * 
    * @author 韩大年  
    * @Description:  查询
    * @param suffixId
    * @return 
    * @version v1.0 
    * 2013-3-18
    */
    public ProtocolSuffixMapping selectByPrimaryKey(Long suffixId);

    /**
     * 
     * @author 韩大年  
     * @Description:  删除
     * @param suffixId
     * @return 
     * @version v1.0 
     * 2013-3-18
     */
    public int deleteByPrimaryKey(Long suffixId);
    
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
     public Pagination selectProtocolSuffixMappingByPagination(ProtocolSuffixMapping protocolSuffixMapping,Pagination pagination);
     
     /**
      * 
      * @author 韩大年  
      * @Description:  查询编号起始号码List
      * @param protocolSuffixMapping
      * @return 
      * @version v1.0 
      * 2013-3-27
      */
     public List<ProtocolSuffixMapping> selectProtocolSuffixMappingListByCondition(ProtocolSuffixMapping protocolSuffixMapping);

}
