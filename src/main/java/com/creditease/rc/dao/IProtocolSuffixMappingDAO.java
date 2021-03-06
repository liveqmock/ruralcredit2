package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.ProtocolSuffixMapping;
import com.creditease.rc.framework.pager.Pagination;
/**
 * 
 * cs
 * Title:IProtocolSuffixMappingDAO.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-6上午10:23:16
 * @author wyf
 * @version v2.0
 */
public interface IProtocolSuffixMappingDAO {
    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table RL_PROTOCOL_SUFFIX_MAPPING
     *
     * @abatorgenerated
     */
    void insert(ProtocolSuffixMapping record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table RL_PROTOCOL_SUFFIX_MAPPING
     *
     * @abatorgenerated
     */
    int updateByPrimaryKey(ProtocolSuffixMapping record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table RL_PROTOCOL_SUFFIX_MAPPING
     *
     * @abatorgenerated
     */
    int updateByPrimaryKeySelective(ProtocolSuffixMapping record);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table RL_PROTOCOL_SUFFIX_MAPPING
     *
     * @abatorgenerated
     */
    ProtocolSuffixMapping selectByPrimaryKey(Long suffixId);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table RL_PROTOCOL_SUFFIX_MAPPING
     *
     * @abatorgenerated
     */
    int deleteByPrimaryKey(Long suffixId);
    
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
      * @Description:  查询合同编号后缀list
      * @param protocolSuffixMapping
      * @return 
      * @version v1.0 
      * 2013-3-25
      */
     public List<ProtocolSuffixMapping> selectProtocolSuffixMappingListByCondition(ProtocolSuffixMapping protocolSuffixMapping );
}