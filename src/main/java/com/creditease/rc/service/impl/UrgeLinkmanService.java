package com.creditease.rc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IUrgeLinkmanDao;
import com.creditease.rc.domain.Urge;
import com.creditease.rc.domain.UrgeLinkman;
import com.creditease.rc.service.IUrgeLinkmanService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DicUtil;

@Service
public class UrgeLinkmanService implements IUrgeLinkmanService {

	@Resource
	private IUrgeLinkmanDao urgeLinkmanDao;

	@Override
	public boolean insert(UrgeLinkman urgeLinkman) {
		// TODO Auto-generated method stub
		return urgeLinkmanDao.insert(urgeLinkman);
	}

	@Override
	public boolean dynamicUpdate(UrgeLinkman urgeLinkman) {
		// TODO Auto-generated method stub
		return urgeLinkmanDao.dynamicUpdate(urgeLinkman);
	}

	@Override
	public boolean insertUrgeLinkmanList(List<UrgeLinkman> urgeLinkmans) {
		// TODO Auto-generated method stub
		return urgeLinkmanDao.insertUrgeLinkmanList(urgeLinkmans);
	}
	//根据催收id查询催收联系人信息
	@Override
	public List<UrgeLinkman> queryUrgeLinkmanList(Long urgelinkmanId){
		List<UrgeLinkman> urgelinkmanlist=urgeLinkmanDao.queryUrgeLinkmanList(urgelinkmanId);
		if (CommonUtil.isNotEmpty(urgelinkmanlist)){
			for(UrgeLinkman urgelinkman:urgelinkmanlist){
			String getBorrowerRelation = urgelinkman.getBorrowerRelation();
			getBorrowerRelation = DicUtil.convertCodeKeyToCodeValue("moneysource",
					getBorrowerRelation);
			urgelinkman.setBorrowerRelation(getBorrowerRelation);
			}
		}
		return urgelinkmanlist;
	}
}
