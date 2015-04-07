package com.creditease.rc.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.OrgamsDAO;
import com.creditease.rc.framework.dao.impl.BaseDao;

@Repository
public class OrgamsDAOImpl extends BaseDao implements OrgamsDAO {

	@Override
	public HashMap<Object, Object> selectOrgams(Long creditapplicationId) {
		return (HashMap<Object, Object>) queryUnique("CREDITAPPLICATION.selectOrgams", creditapplicationId);
	}

	@Override
	public HashMap<Object, Object> selectBorrowContract(Long creditapplicationId) {
		return (HashMap<Object, Object>) queryUnique("CREDITAPPLICATION.selectBorrowContract",creditapplicationId);
	}

}
