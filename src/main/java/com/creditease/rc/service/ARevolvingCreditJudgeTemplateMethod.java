/**
 * Title:RevolvingCreditJudgeTemplateMethod.java  
 * Description:  
 */
package com.creditease.rc.service;

/**
 * Title:RevolvingCreditJudgeTemplateMethod.java  
 * Description:  设计模式：模板方法
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-11-4 
 */
public abstract class ARevolvingCreditJudgeTemplateMethod {

	/**
	 * @Description 默认构造器 
	 */
	public ARevolvingCreditJudgeTemplateMethod() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  判断是否是循环贷的模板方法（包含一期和二期借款人）
	 * @param borrowerCredentialsNumber
	 * @param mateCredentialsNumber
	 * @return 
	 * @version v1.0 
	 * 2013-11-4
	 */
	public final Boolean isRelovingCreditTemplateMethod(  String  borrowerCredentialsNumber,  String mateCredentialsNumber){
		Boolean boolean1=false;
		//二期借款人判定
		boolean1=isRelovingCreditRural2(borrowerCredentialsNumber,mateCredentialsNumber);
		if(!boolean1){
			//一期借款人判定
			boolean1=isRelovingCreditRural1(borrowerCredentialsNumber,mateCredentialsNumber);
		}
		
		return boolean1;
		
		
	}
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  二期判定是否是循环贷
	 * @param borrowerCredentialsNumber
	 * @param mateCredentialsNumber
	 * @return 
	 * @version v1.0 
	 * 2013-11-4
	 */
	private Boolean isRelovingCreditRural2(String  borrowerCredentialsNumber,  String mateCredentialsNumber){
		Boolean boolean1=false;
		//二期：是否是借款人（子类必须重写该方法）
		boolean1=isRural2BorrowerCredentialsNumber(borrowerCredentialsNumber);
		if(!boolean1){
			//二期：借款人不同，判断新借款人是不是以前借款人的配偶，且本次共同借款人为以前借款人（子类必须重写该方法）
			boolean1=isRural2MateCredentialsNumber(borrowerCredentialsNumber,mateCredentialsNumber);
		}
		
		return boolean1;
	}
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  一期判定是否是循环贷
	 * @param borrowerCredentialsNumber
	 * @param mateCredentialsNumber
	 * @return 
	 * @version v1.0 
	 * 2013-11-4
	 */
	private Boolean isRelovingCreditRural1(String  borrowerCredentialsNumber,  String mateCredentialsNumber){
		Boolean boolean1=false;
		//一期：是否是借款人（默认false，子类可以重写该方法）
		boolean1=isRural1BorrowerCredentialsNumber(borrowerCredentialsNumber);
		if(!boolean1){
			//一期：借款人不同，判断新借款人是不是以前借款人的配偶，且本次共同借款人为以前借款人（默认false，子类可以重写该方法）
			boolean1=isRural1MateCredentialsNumber(borrowerCredentialsNumber,mateCredentialsNumber);
		}
		
		return boolean1;
	}
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description: 一期：是否是借款人（默认false，子类可以重写该方法）
	 * @param credentialsNumber
	 * @return 
	 * @version v1.0 
	 * 2013-11-4
	 */
	public  Boolean isRural1BorrowerCredentialsNumber(String  credentialsNumber){
		Boolean boolean1=false;
		return boolean1;
	};
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  一期：借款人不同，判断新借款人是不是以前借款人的配偶，且本次共同借款人为以前借款人（默认false，子类可以重写该方法）
	 * @param borrowerCredentialsNumber
	 * @param mateCredentialsNumber
	 * @return 
	 * @version v1.0 
	 * 2013-11-4
	 */
	public  Boolean isRural1MateCredentialsNumber(String  borrowerCredentialsNumber,  String mateCredentialsNumber) {
		Boolean boolean1=false;
		return boolean1;
	}
	/**
	 * 
	 * @author 韩大年  
	 * @Description: 二期：是否是借款人（子类必须重写该方法）
	 * @param credentialsNumber
	 * @return 
	 * @version v1.0 
	 * 2013-11-4
	 */
	public abstract Boolean isRural2BorrowerCredentialsNumber(String  credentialsNumber) ;
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  二期：借款人不同，判断新借款人是不是以前借款人的配偶，且本次共同借款人为以前借款人（子类必须重写该方法）
	 * @param borrowerCredentialsNumber
	 * @param mateCredentialsNumber
	 * @return 
	 * @version v1.0 
	 * 2013-11-4
	 */
	public abstract Boolean isRural2MateCredentialsNumber(String  borrowerCredentialsNumber,  String mateCredentialsNumber) ;
	
	

}
