package com.creditease.rc.dao;

import java.util.HashMap;
import java.util.List;

import com.creditease.rc.domain.DataBestowal;
import com.creditease.rc.framework.pager.Pagination;
/**
 * 
 * cs
 * Title:IDataBestowalDAO.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-6上午10:03:12
 * @author wyf
 * @version v2.0
 */
public interface IDataBestowalDAO {
    /**
     * 
     * @author 韩大年  
     * @Description:  插入数据赠与表
     * @param record 
     * @version v1.0 
     * 2013-5-31
     */
   public  void insertByDataBestowal(DataBestowal record);

   /**
    * 查询数据赠与表
   * checkstyle
   * @author wyf   
   * @param record 
   * @return    
   * List<DataBestowal>
    */
   public  List<DataBestowal> selectByDataBestowalSelective(DataBestowal record);
   
  
   
 
   /**
    * 分页查询赠与列表
    * @param record 赠与对象
    * @param pagination 分页条件
    * @return 分页列表
    */
	public Pagination selectBestowalPagination(DataBestowal record, Pagination pagination);
	  /**
	    * zhangman
	    * @param DataBestowalId 主表id
	    * @return bestowalDetailsMap
	    */
	   public List<HashMap> selectCredit(Long DataBestowalId);
	   /**
	    * zhangman
	    * @param DataBestowalId 主表id
	    * @return bestowalDetailsMap
	    */
	   public List<HashMap> selectCustomerConsult(Long DataBestowalId);
	   /**
	    * 分页列表查询
	    * @param CreditapplicationId 申请id
	    * @param pagination 分页条件
	    * @return 分页列表
	    */
		public Pagination searchbusinessPagination(Long CreditapplicationId, Pagination pagination);
    
}