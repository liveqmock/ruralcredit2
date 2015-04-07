/**
 * Title:BankCardNOLock.java  
 * Description:  
 */
package com.creditease.rc.util;

import org.apache.log4j.Logger;

/**
 * Title:BankCardNOLock.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-8-5 
 */
public class BankCardNOLock {

	/**
	 * @Description 默认构造器 
	 */
	public BankCardNOLock() {
		// TODO Auto-generated constructor stub
	}
	private  static Logger log = Logger.getLogger(BankCardNOLock.class);
	
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  银行卡号加锁
	 * @param <T>
	 * @param bankCardNo
	 * @return 
	 * @version v1.0 
	 * 2013-8-5
	 */
	public  synchronized static <T> Boolean lockBankCardNO(T bankCardNo){
		Boolean b= false;
		if(null!=bankCardNo){
			Object value=LockUtil.getInstance().get(bankCardNo);
			if(null==value){
				b=true;
				LockUtil.getInstance().put(bankCardNo, bankCardNo);
				log.info(bankCardNo.toString()+"加锁成功");
			}else{
				log.info(bankCardNo.toString()+"锁已存在,加锁失败");
			}
		}
		return b;
		
	}
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  银行卡解锁
	 * @param <T>
	 * @param bankCardNo
	 * @return 
	 * @version v1.0 
	 * 2013-8-5
	 */
	public  synchronized static<T> Boolean unLockBankCardNO(T bankCardNo){
		Boolean b= false;
		if(null!=bankCardNo){
			Object value=LockUtil.getInstance().get(bankCardNo);
			if(null!=value){
				b=true;
				LockUtil.getInstance().remove(value);
				log.info(bankCardNo.toString()+"已解锁");
			}else{
				log.info(bankCardNo.toString()+"未加锁。不需要解锁");
			}
		}
		return b;
	}

}
