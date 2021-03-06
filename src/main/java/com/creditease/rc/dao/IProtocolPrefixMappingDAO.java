package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.ProtocolPrefixMapping;
import com.creditease.rc.framework.pager.Pagination;
/**
 * 
 * cs
 * Title:IProtocolPrefixMappingDAO.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-6上午10:23:09
 * @author wyf
 * @version v2.0
 */
public interface IProtocolPrefixMappingDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table RL_PROTOCOL_PREFIX_MAPPING
     *
     * @abatorgenerated
     */
    void insert(ProtocolPrefixMapping record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table RL_PROTOCOL_PREFIX_MAPPING
     *
     * @abatorgenerated
     */
    int updateByPrimaryKey(ProtocolPrefixMapping record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table RL_PROTOCOL_PREFIX_MAPPING
     *
     * @abatorgenerated
     */
    int updateByPrimaryKeySelective(ProtocolPrefixMapping record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table RL_PROTOCOL_PREFIX_MAPPING
     *
     * @abatorgenerated
     */
    ProtocolPrefixMapping selectByPrimaryKey(Long prefixId);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table RL_PROTOCOL_PREFIX_MAPPING
     *
     * @abatorgenerated
     */
    int deleteByPrimaryKey(Long prefixId);
    
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
     public Pagination selectProtocolPrefixMappingByPagination(ProtocolPrefixMapping protocolPrefixMapping,Pagination pagination);
     
     /**
      * 
      * @author 韩大年  
      * @Description:  查询ProtocolPrefixMappingList
      * @param protocolPrefixMapping
      * @return 
      * @version v1.0 
      * 2013-3-25
      */
     public List<ProtocolPrefixMapping> selectProtocolPrefixMappingListSelective(ProtocolPrefixMapping protocolPrefixMapping);

	List<ProtocolPrefixMapping> queryDistrictAndDepartment();
}