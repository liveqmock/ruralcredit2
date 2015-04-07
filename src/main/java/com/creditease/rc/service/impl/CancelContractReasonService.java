package com.creditease.rc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.app.orgams.BaseResponse;
import com.creditease.rc.dao.BorrowMatchingDAO;
import com.creditease.rc.dao.ICancelContractReasonDao;
import com.creditease.rc.domain.BorrowMatching;
import com.creditease.rc.domain.CancelContractReason;
import com.creditease.rc.domain.Message;
import com.creditease.rc.service.ICancelContractReasonService;
import com.creditease.rc.service.IContractAndLoanService;
import com.creditease.rc.service.IorgamsService;

@Service
public class CancelContractReasonService implements ICancelContractReasonService {
	

	@Resource
	private ICancelContractReasonDao cancelContractReasonDao;
	@Resource
	private IContractAndLoanService contractAndLoanService;
	@Resource
	private IorgamsService orgamsService;
	@Resource
	private BorrowMatchingDAO borrowMatchingDAO;
	
	@Override
	public Message saveCancelReasonRepeal(CancelContractReason cancelContractReason) {
		Message message=new Message();
		// 调用撤销撮合
		BaseResponse baseResponse = orgamsService
				.upMatching(cancelContractReason.getCreditapplicationId());
		Long status = baseResponse.getStatus();
		if (status == 1) {
			// 更新撮合结果
			BorrowMatching borrowMatching = new BorrowMatching();
			borrowMatching.setStatus("0");// 失败
			borrowMatching.setCreditapplicationId(cancelContractReason
					.getCreditapplicationId());
			borrowMatchingDAO.updateByCaId(borrowMatching);
			cancelContractReason.setHistoryFlag("F");
			cancelContractReasonDao.saveCancelReason(cancelContractReason);
			contractAndLoanService.updateFailContractState(cancelContractReason
					.getCreditapplicationId());
			System.out.println("撤销成功！");
			message.setMsg("撤销成功！");
			message.setSuccess(true);
		}else{
			System.out.println("撤销失败！");
			message.setMsg("撤销失败！");
			message.setSuccess(false);
		}
			
		return message;
	}

	@Override
	public Message updateCancelReasonHistoryRepeal(Long creditapplicationId) {
		 Message message=new Message();
			// 调用撤销撮合
			BaseResponse baseResponse = orgamsService
					.upMatching(creditapplicationId);
			Long status = baseResponse.getStatus();
			if (status == 1) {
			// 更新撮合结果
			BorrowMatching borrowMatching = new BorrowMatching();
			borrowMatching.setStatus("0");// 失败
			borrowMatching.setCreditapplicationId(creditapplicationId);
			borrowMatchingDAO.updateByCaId(borrowMatching);
			//更新历史标识
			cancelContractReasonDao.updateCancelReasonHistory(creditapplicationId);
			//更改业务状态
			contractAndLoanService.updateFailContractState(creditapplicationId);
			System.out.println("撤销成功！");
			message.setMsg("撤销成功！");
			message.setSuccess(true);
			}else{
				System.out.println("撤销失败！");
				message.setMsg("撤销失败！");
				message.setSuccess(false);
			}
			return message;
	}
		
	@Override
	public void saveCancelReason(CancelContractReason cancelContractReason) {
		cancelContractReason.setHistoryFlag("F");
		cancelContractReasonDao.saveCancelReason(cancelContractReason);
		contractAndLoanService.updateFailContractState(cancelContractReason.getCreditapplicationId());
	}

	@Override
	public void updateCancelReasonHistory(Long creditapplicationId) {
			cancelContractReasonDao.updateCancelReasonHistory(creditapplicationId);
			contractAndLoanService.updateFailContractState(creditapplicationId);
	}
		
	@Override
	public  CancelContractReason selectReason(Long creditapplicationId){
		CancelContractReason cancelContractReason=new CancelContractReason();
		 cancelContractReason=cancelContractReasonDao.selectReason(creditapplicationId);
		 return cancelContractReason;
	}
	
}
