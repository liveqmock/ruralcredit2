package com.creditease.webservice.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.creditease.rc.app.orgams.BaseResponse;
import com.creditease.rc.dao.BorrowMatchingDAO;
import com.creditease.rc.domain.Message;
import com.creditease.rc.service.IContractAndLoanService;
import com.creditease.rc.service.IRural2CreditService;
import com.creditease.rc.service.IorgamsService;
import com.creditease.rc.service.impl.Rural2CreditService;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.vo.ContractConfirmNoticeReqVO;
import com.creditease.rc.vo.ContractConfirmNoticeResVO;
import com.creditease.webservice.IcontractConfirmation;

public class ContractConfirmationImpl implements IcontractConfirmation {

	@Resource
	private BorrowMatchingDAO borrowMatchingDAO;
	@Resource
	private IRural2CreditService iRural2CreditService;
	@Resource
	private IorgamsService iorgamsService;
	@Resource
	private IContractAndLoanService contractAndLoanService;
	Logger log = Logger.getLogger(Rural2CreditService.class);
	@Override
	public ContractConfirmNoticeResVO contractConfirmation(
			 ContractConfirmNoticeReqVO contractConfirmNoticeReqVO) {
		log.info("************** requestParam******************");
		log.info(JsonUtil.getJackson(contractConfirmNoticeReqVO));
		ContractConfirmNoticeResVO contractConfirmNoticeResVO = new ContractConfirmNoticeResVO();
		if(contractConfirmNoticeReqVO.getIsPass() == true){
			String status = borrowMatchingDAO.selectStatusbyCreditapplicationId(contractConfirmNoticeReqVO.getTransportId());
			if(contractConfirmNoticeReqVO.getSignInfo().equals("07") && status.equals("1")){
				// 放款确认
				if(!contractConfirmNoticeReqVO.getTransportId().equals(null)&&contractConfirmNoticeReqVO.getTransportId()!=null){
					// TODO 更改状态，等待放款 罗红杰
					contractAndLoanService.updateWaitLoanContractState(contractConfirmNoticeReqVO.getTransportId());
				}
				
				Message message = iRural2CreditService.clientApply(contractConfirmNoticeReqVO.getTransportId());//调用贷后通知放款
				if("0".equals(message.getCode())){//调用贷后通知放款成功
					
					//待确定
					contractConfirmNoticeResVO.setTransactionCompleteTime(new Date());
					contractConfirmNoticeResVO.setTransactionCode("0");
					contractConfirmNoticeResVO.setTransactionMsg("贷后放款成功");
					log.info("**************responseParam******************");
					log.info(JsonUtil.getJackson(contractConfirmNoticeResVO));
				}else{
					//掉用贷后通知放款失败
					iorgamsService.synBorrowContractState(contractConfirmNoticeReqVO.getTransportId(),"4");//放款状态同步(3:表示成功)
					contractConfirmNoticeResVO.setTransactionCompleteTime(new Date());
					contractConfirmNoticeResVO.setTransactionCode("0");
					contractConfirmNoticeResVO.setTransactionMsg("贷后放款失败");
					log.info("**************responseParam******************");
					log.info(JsonUtil.getJackson(contractConfirmNoticeResVO));
				}
				
			}else{
				contractConfirmNoticeResVO.setTransactionCompleteTime(new Date());
				contractConfirmNoticeResVO.setTransactionCode("0");
				contractConfirmNoticeResVO.setTransactionMsg("撮合id或进件编号不匹配");
				log.info("**************responseParam******************");
				log.info(JsonUtil.getJackson(contractConfirmNoticeResVO));
			}
		}else{
				//更改业务状态：等待合同重新复核   
				boolean updateWaitContractAgainCheck = contractAndLoanService.updateWaitContractAgainCheckOther(contractConfirmNoticeReqVO.getTransportId());
				if(updateWaitContractAgainCheck){
					contractConfirmNoticeResVO.setTransactionCompleteTime(new Date());
					contractConfirmNoticeResVO.setTransactionCode("0");
					contractConfirmNoticeResVO.setTransactionMsg("合同复核拒绝");
					log.info("**************responseParam******************");
					log.info(JsonUtil.getJackson(contractConfirmNoticeResVO));
				}else{
					contractConfirmNoticeResVO.setTransactionCompleteTime(new Date());
					contractConfirmNoticeResVO.setTransactionCode("-1");
					contractConfirmNoticeResVO.setTransactionMsg("农贷操作失败");
					log.info("**************responseParam******************");
					log.info(JsonUtil.getJackson(contractConfirmNoticeResVO));
				}
		}
		return contractConfirmNoticeResVO;
	}

	

}
