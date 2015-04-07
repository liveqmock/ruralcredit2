package com.creditease.rc.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IComplaintProposalDAO;
import com.creditease.rc.domain.ComplaintProposalRequestParam;

import com.creditease.rc.framework.dao.IBaseDao;
@Repository
public class ComplaintProposalDAO implements IComplaintProposalDAO{

	@Resource
	private IBaseDao baseDao;	
	
    public boolean insert(ComplaintProposalRequestParam complaintProposal) {
        baseDao.insert("COMPLAINTPROPOSAL.insert", complaintProposal);
        return true;
    }
}
