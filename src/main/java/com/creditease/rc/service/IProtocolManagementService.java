/**
 * Title:IProtocolManagementService.java  
 * Description:  
 */
package com.creditease.rc.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.creditease.rc.domain.AmountConfirm;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.ProtocolMapping;

/**
 * Title:IProtocolManagementService.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-3-25 
 */
public interface IProtocolManagementService {
	/**
	 * 
	 * @author 韩大年  
	 * @Description: 创建协议编号并保存,返回协议编号
	 * @version v1.0
	 * 2012-12-21
	 */
	public ProtocolMapping saveOrGetProtocolMapping(CreditApplication creditapplication);
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  调用借款协议下载请求服务
	 * @param creditApplication
	 * @param amountConfirm
	 * @return 
	 * @version v1.0 
	 * 2013-3-26
	 */
	public String  CeAgreementDownloadReq(CreditApplication creditApplication,AmountConfirm amountConfirm);
	
	/**
	 * 协议下载
	 * @author 韩大年  
	 * @Description:  
	 * @param creditApplication
	 * @param amountConfirm
	 * @return 
	 * @version v1.0 
	 * 2013-3-26
	 */
	public String saveAndDownProtocol( Integer creditapplicationId,Date loanDate);
	/**
	 * 
	 * @param creditApplication
	 * @param loanDate
	 * @param request
	 * @return
	 */
	public Message searchAndDownProtocol(Integer creditapplicationId,HttpServletRequest request);
	
	/**
	 * 协议下载
	 * @author 罗红杰
	 * @Description:  
	 * @param creditApplication
	 * @param amountConfirm
	 * @return 
	 * @version v1.0 
	 * 2013-3-26
	 */
	public String saveAndDownProtocolXinTuo( Integer creditapplicationId,Date loanDate);
	
	/**
	 * 
	 * @author 罗红杰
	 * @Description:  调用借款协议下载请求服务
	 * @param creditApplication
	 * @param amountConfirm
	 * @return 
	 * @version v1.0 
	 * 2013-3-26
	 */
	public String  CeAgreementDownloadReqXinTuo(CreditApplication creditApplication,AmountConfirm amountConfirm);
	
}
