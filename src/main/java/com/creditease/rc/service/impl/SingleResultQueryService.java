package com.creditease.rc.service.impl;

import javax.annotation.Resource;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.creditease.rc.app.settle.CeFinalPaymentWSImplPortType;
import com.creditease.rc.app.settle.CePaymentResultQueryReq;
import com.creditease.rc.app.settle.CePaymentResultQueryRes;
import com.creditease.rc.app.settle.CeReceiptResultQueryReq;
import com.creditease.rc.app.settle.CeReceiptResultQueryRes;
import com.creditease.rc.common.Constants;
import com.creditease.rc.framework.exception.AppBusinessException;
import com.creditease.rc.service.ISingleResultQueryService;
import com.creditease.rc.util.PayplatformParamsPropertiesUtil;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class SingleResultQueryService implements ISingleResultQueryService{
	@Resource
	private CeFinalPaymentWSImplPortType ceFinalPaymentWSImplPortType;
	@Override
	public boolean singleReceiptResultQuery(String bizId)throws AppBusinessException {
		CeReceiptResultQueryReq request =new CeReceiptResultQueryReq();
		String systemSourceId =PayplatformParamsPropertiesUtil.getProperty(Constants.SYSTEM_SOURCE_ID);//系统来源ID 必填
		String productId =PayplatformParamsPropertiesUtil.getProperty(Constants.PRODUCT_ID);//产品ID 必填
		String encryptionKey =PayplatformParamsPropertiesUtil.getProperty(Constants.ENCRYPTION_KEY);//加密标识
		request.setBizId(bizId);
		request.setSystemSourceId(systemSourceId);
		request.setProductId(productId);
		request.setSignInfo(new Md5PasswordEncoder().encodePassword(systemSourceId+bizId, encryptionKey));		//摘要信息  必选
		CeReceiptResultQueryRes ceReceiptResultQueryRes=ceFinalPaymentWSImplPortType.singleReceiptResultQuery(request);
		if("1".equals(ceReceiptResultQueryRes.getState())||"9".equals(ceReceiptResultQueryRes.getState())){
			return true;
		}else{
			throw new AppBusinessException(ceReceiptResultQueryRes.getRetInfo());
		}
	}

	@Override
	public boolean singlePaymentResultQuery(String bizId) throws AppBusinessException{
		CePaymentResultQueryReq request=new CePaymentResultQueryReq(); 
		String systemSourceId =PayplatformParamsPropertiesUtil.getProperty(Constants.SYSTEM_SOURCE_ID);//系统来源ID 必填
		String productId =PayplatformParamsPropertiesUtil.getProperty(Constants.PRODUCT_ID);//产品ID 必填
		String encryptionKey =PayplatformParamsPropertiesUtil.getProperty(Constants.ENCRYPTION_KEY);//加密标识
		request.setBizId(bizId);
		request.setSystemSourceId(systemSourceId);
		request.setProductId(productId);
		request.setSignInfo(new Md5PasswordEncoder().encodePassword(systemSourceId+bizId, encryptionKey));		//摘要信息  必选
		CePaymentResultQueryRes cePaymentResultQueryRes=ceFinalPaymentWSImplPortType.singlePaymentResultQuery(request);
		if("1".equals(cePaymentResultQueryRes.getState())||"9".equals(cePaymentResultQueryRes.getState())){
			return true;
		}else{
			throw new AppBusinessException(cePaymentResultQueryRes.getRetInfo());
		}
	}
}
