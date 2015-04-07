package com.creditease.rc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IUrgeLinkmanDao;
import com.creditease.rc.domain.UrgeLinkman;
import com.creditease.rc.framework.dao.IBaseDao;

@Repository
public class UrgeLinkmanDao implements IUrgeLinkmanDao {

	@Resource
	private IBaseDao baseDao;

	@Override
	public boolean insert(UrgeLinkman urgeLinkman) {
		// TODO Auto-generated method stub
		baseDao.insert("URGELINKMAN.insert", urgeLinkman);
		return true;
	}

	@Override
	public boolean dynamicUpdate(UrgeLinkman urgeLinkman) {
		// TODO Auto-generated method stub
		baseDao.update("URGELINKMAN.dynamicUpdate", urgeLinkman);
		return true;
	}

	@Override
	public boolean insertUrgeLinkmanList(List<UrgeLinkman> urgeLinkmans) {
		// TODO Auto-generated method stub
		baseDao.batchInsert("URGELINKMAN.insert", urgeLinkmans);
		return true;
	}
	@Override
	public List<UrgeLinkman> queryUrgeLinkmanList(Long urgelinkmanId){
		return (List<UrgeLinkman>) baseDao.queryList("URGELINKMAN.selectByUrgeId",urgelinkmanId);
	}
}
