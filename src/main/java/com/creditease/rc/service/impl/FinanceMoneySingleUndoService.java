package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.creditease.rc.app.settle.CeFinalPaymentWSImplPortType;
import com.creditease.rc.app.settle.CeUndoReq;
import com.creditease.rc.app.settle.CeUndoRes;
import com.creditease.rc.common.Constants;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.FinanceMoney;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.exception.AppBusinessException;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.IFinanceMoneyService;
import com.creditease.rc.service.IFinanceMoneySingleUndoService;
import com.creditease.rc.service.IGroupLoanRegistService;
import com.creditease.rc.service.IOperateLogService;
import com.creditease.rc.service.IReceivedRecordListService;
import com.creditease.rc.service.ISingleResultQueryService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.PayplatformParamsPropertiesUtil;
import com.creditease.rc.vo.OperateLogBusinessStruct;

/**
 * 
 * @author Administrator
 * 
 */
@Service
public class FinanceMoneySingleUndoService implements IFinanceMoneySingleUndoService{
	@Resource
	private CeFinalPaymentWSImplPortType ceFinalPaymentWSImplPortType;
	@Resource
	private ISingleResultQueryService singleResultQueryService;
	@Resource
	private IFinanceMoneyService financeMoneyService;
	@Resource
	private IOperateLogService operateLogService;
	@Resource
	private ICreditApplicationService creditApplicationService;
	@Resource
	private IReceivedRecordListService  receivedRecordListService ;
	@Resource
	private IGroupLoanRegistService groupLoanRegistService;
	
	@Override
	public Message singleUndo(Long bizId,Integer creditapplicationId)throws AppBusinessException{
		Message message=new Message();
		if (null != bizId) {
			List<FinanceMoney> financeMoneyList = financeMoneyService
					.selectFinanceMoneyListByBizId(bizId);
			List<OperateLogBusinessStruct> oList = new ArrayList<OperateLogBusinessStruct>();
			if (CommonUtil.isNotEmpty(financeMoneyList)) {
				CeUndoReq ceUndoReq = new CeUndoReq();
				String fBizId=financeMoneyList.get(0).getBizId();
				ceUndoReq.setBizId(fBizId);
				String type=financeMoneyList.get(0).getType();
				FinanceMoney parameter=new FinanceMoney();
				parameter.setBizId(fBizId);
				List<FinanceMoney> operateLogFinanceMoneyList=null;
				if("F".equals(type)){
					operateLogFinanceMoneyList=financeMoneyService.selectPaymentFinanceMoneyList(parameter);
				}else{
					operateLogFinanceMoneyList=financeMoneyService.selectReceiveFinanceMoneyList(parameter);
				}
				if(CommonUtil.isNotEmpty(operateLogFinanceMoneyList)){
					for(FinanceMoney f:operateLogFinanceMoneyList){
						OperateLogBusinessStruct operateLogBusinessStruct=new OperateLogBusinessStruct();
						operateLogBusinessStruct.setBizid(bizId.toString());
						type=f.getType();
						if("S".equals(type)){
							operateLogBusinessStruct.setFunctionCode("50002*");
							operateLogBusinessStruct.setCreditapplicationId(f.getCreditapplicationId().longValue());
						}else if("U".equals(type)){
							operateLogBusinessStruct.setFunctionCode("50005*");
							operateLogBusinessStruct.setCreditapplicationId(f.getCreditapplicationId().longValue());
						}else {
							operateLogBusinessStruct.setFunctionCode("50001*");
							operateLogBusinessStruct.setCreditapplicationId(creditapplicationId.longValue());
						}
						StringBuffer sbf=new StringBuffer();
						sbf.append("信贷申请单号：").append(f.getCreditapplicationId()).append("；账户名：")
						.append(f.getAccountName()).append("；账号：").append(f.getAccountNo())
						.append("；金额：").append(f.getAmount());
						operateLogBusinessStruct.setRemark(sbf.toString());
						operateLogBusinessStruct.setResult("撤销成功");
						oList.add(operateLogBusinessStruct);
					}
				}
				String undoTypeId="";
				if("S".equals(type)||"U".equals(type)){
					undoTypeId="UD01";
					boolean result=singleResultQueryService.singleReceiptResultQuery(fBizId);
					message.setSuccess(result);
				}else {
					undoTypeId="UD02";
					boolean result=singleResultQueryService.singlePaymentResultQuery(fBizId);
					message.setSuccess(result);
				}
				if(message.isSuccess()){
					ceUndoReq.setUndoTypeId(undoTypeId);
					String systemSourceId =PayplatformParamsPropertiesUtil.getProperty(Constants.SYSTEM_SOURCE_ID);//系统来源ID 必填
					ceUndoReq.setSystemSourceId(systemSourceId);
					String productId =PayplatformParamsPropertiesUtil.getProperty(Constants.PRODUCT_ID);//产品ID 必填
					ceUndoReq.setProductId(productId);
					String encryptionKey =PayplatformParamsPropertiesUtil.getProperty(Constants.ENCRYPTION_KEY);//加密标识
					ceUndoReq.setSignInfo(new Md5PasswordEncoder().encodePassword(systemSourceId+bizId, encryptionKey));//摘要信息  必选
					CeUndoRes ceUndoRes=ceFinalPaymentWSImplPortType.singleUndo(ceUndoReq);
					FinanceMoney financeMoney=new FinanceMoney();
					if(Constants.PAYPLATFORM_STATE_UNDO.equals(ceUndoRes.getState())){
						financeMoney.setBizId(fBizId);
						financeMoney.setFinanceStatus("4");
						financeMoney.setReturnMsg(ceUndoRes.getRetInfo()+";订单号="+ceUndoRes.getBizId());
//						financeMoney.setHistoryFlag("T");
//						CreditApplication resultCreditApplication=creditApplicationService.selectById(creditapplicationId);
						//财务付款撤销根据业务类型修改信贷申请单状态0：分公司业务撤到04：审批通过状态；1：个人业务撤到11：已放款登记状态
//						if("F".equals(type)){
//							String status=null;
//							if("1".equals(resultCreditApplication.getBusinessType())){
//								status="11";
//								groupLoanRegistService.changeToRegist(creditapplicationId);
//							}else{
//								status="04";
//							}
//							creditApplicationService.changeAuditing(new CreditApplication(), creditapplicationId, status);
//						}else{
						if("S".equals(type)||"U".equals(type)){
							List<Integer>list=new ArrayList<Integer>();
							for(FinanceMoney f:financeMoneyList){
								Integer receiveRecordId=f.getAssociationId();
								list.add(receiveRecordId);
							}
							receivedRecordListService.updateFinancialAppointmentRevoked(list);//调用郝强接口 根据收款撤销修改收款登记表状态
							financeMoneyService.updateFinanceMoneyByBizId(financeMoney);
						}
						operateLogService.batchInsert(oList);
					}
					else{
						message.setSuccess(false);
						message.setMsg(ceUndoRes.getRetInfo());
					}
				}
			}
		}
		return message;
	}

}
