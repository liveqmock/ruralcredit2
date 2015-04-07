package com.creditease.rc.service;


import org.springframework.stereotype.Service;

import com.creditease.rc.domain.ComplaintProposalRequestParam;
import com.creditease.rc.vo.WebServiceMessage;

public interface IComplaintProposalService {

	WebServiceMessage saveComplaintProposal(ComplaintProposalRequestParam complaintProposalRequestParam);
}
