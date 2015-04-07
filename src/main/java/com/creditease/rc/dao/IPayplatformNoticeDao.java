package com.creditease.rc.dao;

import com.creditease.rc.domain.PayplatformNotice;
/**
 * 
 * @author Administrator
 *
 */
public interface IPayplatformNoticeDao {
   /**
    * 
    * @param record  
    */
    public void insertPayplatformNotice(PayplatformNotice record);

   /**
    * 
    * @param record 
    * @return int 
    */
    public int updatePayplatformNotice(PayplatformNotice record);

   /**
    * 
    * @param payplatformNoticeId 
    * @return PayplatformNotice
    */
    public PayplatformNotice selectByPrimaryKey(Integer payplatformNoticeId);

}