package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.DataBestowalDetail;
/**
 * 
 * cs
 * Title:IDataBestowalDetailDAO.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-6上午10:03:33
 * @author wyf
 * @version v2.0
 */
public interface IDataBestowalDetailDAO {
    /**
     * 
     * @author 韩大年  
     * @Description:  插入 DataBestowalDetail
     * @param record 
     * @version v1.0 
     * 2013-5-31
     */
    public void insertByDataBestowalDetail(DataBestowalDetail record);

    
    /**
     * 韩大年  
     * @Description:  根据赠与主表id查询赠与明细
    * checkstyle
    * @author wyf   
    * @param DataBestowalId 
    * @return    
    * List<DataBestowalDetail>
     */
    public List<DataBestowalDetail> selectDataBestowalDetailByDataBestowalId(Long DataBestowalId);
    
    /**
     * 韩大年  
     * @Description:  批量插入赠与子表
    * checkstyle
    * @author wyf   
    * @param dataBestowalDetailList     
    * void
     */
    public void insertDataBestowalDetailList(List<DataBestowalDetail> dataBestowalDetailList);

  
}