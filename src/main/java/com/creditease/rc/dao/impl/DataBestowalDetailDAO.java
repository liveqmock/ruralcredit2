package com.creditease.rc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IDataBestowalDetailDAO;
import com.creditease.rc.domain.DataBestowalDetail;
import com.creditease.rc.framework.dao.IBaseDao;
/**
 * 
 * cs
 * Title:DataBestowalDetailDAO.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-6上午10:07:09
 * @author wyf
 * @version v2.0
 */
@Repository
public class DataBestowalDetailDAO implements IDataBestowalDetailDAO {

	/**
	 * 
	 * @Description 默认构造器
	 */
	public DataBestowalDetailDAO(){
		
	}
	@Resource
	private IBaseDao baseDao;
	  /**
     * 
     * @author 韩大年  
     * @Description:  插入
     * @param record 
     * @version v1.0 
     * 2013-5-31
     */
    public void insertByDataBestowalDetail(DataBestowalDetail record){
    	 baseDao.insert("dataBestowalDetail.insertByDataBestowalDetail", record);
    	
    }

    
    /**
     * 
     * @author 韩大年  
     * @Description:  根据赠与主表id查询赠与明细
     * @param DataBestowalId 
     * @return 
     * 2013-5-31
     */
     public List<DataBestowalDetail> selectDataBestowalDetailByDataBestowalId(Long DataBestowalId){
    	return (List<DataBestowalDetail>) baseDao.queryList("dataBestowalDetail.selectDataBestowalDetailByDataBestowalId", DataBestowalId);
    	
    }
     /**
      * 
      * @author 韩大年  
      * @Description:  批量插入赠与子表
      * @param dataBestowalDetailList   
      * 2013-5-31
      */
     public void insertDataBestowalDetailList(List<DataBestowalDetail> dataBestowalDetailList){
    	 this.baseDao.batchInsert("dataBestowalDetail.insertDataBestowalDetail", dataBestowalDetailList);
     }
}