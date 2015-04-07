package com.creditease.rc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IReturnScheduleDAO;
import com.creditease.rc.framework.dao.impl.BaseDao;

/**
 * 虚拟现金
 * @author zhangman
 *
 */
@Repository
public class ReturnScheduleDAO extends BaseDao implements IReturnScheduleDAO {

	@Override
	public void batchInsert(String sqlId, List entityList) {
		// TODO Auto-generated method stub
		super.batchInsert(sqlId, entityList);
	}
}
