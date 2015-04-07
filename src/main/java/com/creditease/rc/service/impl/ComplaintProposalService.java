package com.creditease.rc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IComplaintProposalDAO;
import com.creditease.rc.domain.ComplaintProposalRequestParam;
import com.creditease.rc.service.IComplaintProposalService;
import com.creditease.rc.vo.WebServiceMessage;
@Service
public class ComplaintProposalService implements IComplaintProposalService{

	@Resource
	private IComplaintProposalDAO complaintProposalDAO;
	@Override
	public WebServiceMessage saveComplaintProposal(ComplaintProposalRequestParam complaintProposalRequestParam) {
		// TODO Auto-generated method stub
		WebServiceMessage webServiceMessage=new WebServiceMessage();
		String complaintProposal = complaintProposalRequestParam.getComplaintProposal();
		String phoneNumber = complaintProposalRequestParam.getPhoneNumber();
		if("".equals(complaintProposal) || complaintProposal == null ){
			webServiceMessage.setCodeinfo("1");
			webServiceMessage.setMessgae("入参complaintProposal投诉意见为空！");
		}else if("".equals(phoneNumber) || phoneNumber == null ){
			webServiceMessage.setCodeinfo("1");
			webServiceMessage.setMessgae("入参phoneNumber联系电话为空！");
		
		}else{
		
			boolean f=complaintProposalDAO.insert(complaintProposalRequestParam);
			if(f){
				webServiceMessage.setCodeinfo("0");
				webServiceMessage.setMessgae("保存成功");
			}else{
				webServiceMessage.setCodeinfo("3");
				webServiceMessage.setMessgae("农贷系统内部错误");
			}
		}
		return webServiceMessage;
	}

}
