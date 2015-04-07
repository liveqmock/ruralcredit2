package com.creditease.rc.dao.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IDataBestowalDAO;
import com.creditease.rc.domain.DataBestowal;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;
/**
 * 
 * cs
 * Title:DataBestowalDAO.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-6上午10:06:53
 * @author wyf
 * @version v2.0
 */
@Repository
public class DataBestowalDAO  implements IDataBestowalDAO {

	/**
	 * 
	 * @Description 默认构造器
	 */
	public DataBestowalDAO(){
		
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
   public  void insertByDataBestowal(DataBestowal record){
	   baseDao.insert("dataBestowal.insertByDataBestowal", record);
   }

   /**
    * 
    * @author 韩大年  
    * @Description:  
    * @param record 
    * @return 
    * 2013-5-31
    */
   public  List<DataBestowal> selectByDataBestowalSelective(DataBestowal record){
	   return (List<DataBestowal>) baseDao.queryList("dataBestowal.selectByDataBestowalSelective", record);
	   
   }
   
   	@Override
	public Pagination selectBestowalPagination(DataBestowal record, Pagination pagination) {
		// TODO Auto-generated method stub
		return baseDao.queryForPaginatedList("dataBestowal.selectByDataBestowalSelective", "dataBestowal.countByDataBestowalSelective", record, pagination.getStartResult(),pagination.getPageSize());
	}
   	
   	
    /**
	    * zhangman
	    * @param DataBestowalId 主表id
	    * @return bestowalDetailsMap
	    */
   	@Override
   	public List<HashMap> selectCredit(Long DataBestowalId) {
   		// TODO Auto-generated method stub
   		return (List<HashMap>) baseDao.queryList("dataBestowalDetail.selectCredit", DataBestowalId);
   	}
   	
   	/**
	    * zhangman
	    * @param DataBestowalId 主表id
	    * @return bestowalDetailsMap
	    */
	@Override
	public List<HashMap> selectCustomerConsult(Long DataBestowalId) {
		// TODO Auto-generated method stub
		return (List<HashMap>) baseDao.queryList("dataBestowalDetail.selectCustomerConsult", DataBestowalId);
	}
   	@Override
   	public Pagination searchbusinessPagination(Long CreditapplicationId, Pagination pagination) {
   		// TODO Auto-generated method stub
   		return  baseDao.queryForPaginatedList("dataBestowal.searchbusiness", "dataBestowal.countbusiness", CreditapplicationId, pagination.getStartResult(),pagination.getPageSize());
   	}
}