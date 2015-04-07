package com.creditease.rc.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IWxPrizeDao;
import com.creditease.rc.domain.WxPrize;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.vo.Prize;

@Repository
public class WxPrizeDao implements IWxPrizeDao {

	@Resource
	private IBaseDao baseDao;

	@Override
	public boolean batchInsetPrizeList(List<WxPrize> prizes) {
		baseDao.batchInsert("WXPRIZE.abatorgenerated_insert", prizes);
		return true;
	}


}