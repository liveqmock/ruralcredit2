package com.creditease.rc.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IBorrowInfoDAO;
import com.creditease.rc.domain.BorrowInfo;
import com.creditease.rc.framework.dao.impl.BaseDao;
/**
 * 
 * @author zhangman
 *
 */
@Repository
public class BorrowInfoDAO implements IBorrowInfoDAO {

	@Resource
	private BaseDao baseDao;

	@Override
	public int addBorrowInfo(BorrowInfo borrowInfo) {
		return (Integer) baseDao.insertObject("BORROWEINFO.insert", borrowInfo);
	}

	@Override
	public void addBorrowInfo(List<BorrowInfo> borrowInfos) {
		baseDao.batchInsert("BORROWEINFO.insert", borrowInfos);
	}

	@Override
	public void updateBorrowInfo(BorrowInfo borrowInfo) {
		baseDao.update("BORROWEINFO.update", borrowInfo);
	}

	@Override
	public List<BorrowInfo> selectBorrowInfo(int borrowerServiceAppId)
			throws ParseException {
		List<BorrowInfo> borrowInfoList = new ArrayList<BorrowInfo>();
		borrowInfoList = (List<BorrowInfo>) baseDao.queryList(
				"BORROWEINFO.selectAll", borrowerServiceAppId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return borrowInfoList;
	}
	
	@Override
	public int deleteBorrowInfo(int borrowerServiceAppId) {
		// TODO Auto-generated method stub
		return baseDao.delete("BORROWEINFO.deleteByBSAId", borrowerServiceAppId);
	}
	
	@Override
	public int deleteByborrowInfoId(int borrowInfoId) {
		return baseDao.delete("BORROWEINFO.deleteById", borrowInfoId);
	}
}
