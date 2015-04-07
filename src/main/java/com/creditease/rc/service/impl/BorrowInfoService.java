package com.creditease.rc.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IBorrowInfoDAO;
import com.creditease.rc.domain.BorrowInfo;
import com.creditease.rc.service.IBorrowInfoService;
import com.creditease.rc.vo.BorrowInfoVo;
/**
 * 
 * @author zhangman
 *
 */
@Service
public class BorrowInfoService implements IBorrowInfoService {

	@Resource
	private IBorrowInfoDAO borrowInfoDAO;

	@Override
	public List<BorrowInfo> addOrUpdateBorrowInfo(List<BorrowInfo> borrowInfos) {
		List<BorrowInfo>  borrowInfoTemp = new ArrayList<BorrowInfo>();
		for (BorrowInfo borrowInfo : borrowInfos) {
			if(borrowInfo.getLender()!= null && !"".equals(borrowInfo.getLender())){
				if(borrowInfo.getBorrowInfoId()!= null && !"".equals(borrowInfo.getBorrowInfoId())){
					borrowInfoDAO.updateBorrowInfo(borrowInfo);
					borrowInfoTemp.add(borrowInfo);
				}else{
					int borrowInfoId = borrowInfoDAO.addBorrowInfo(borrowInfo);
					borrowInfo.setBorrowInfoId(borrowInfoId);
					borrowInfoTemp.add(borrowInfo);
				}
			}else{
				if(borrowInfo.getBorrowInfoId()!= null && !"".equals(borrowInfo.getBorrowInfoId())){
					borrowInfoDAO.deleteByborrowInfoId(borrowInfo.getBorrowInfoId());
				}
			}
		}
		return borrowInfoTemp;
	}

	@Override
	public List<BorrowInfo> selectBorrowInfo(int borrowerServiceAppId)
			throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteBorrowInfo(int borrowerServiceAppId) {
		return 0;
	}

	@Override
	public BorrowInfoVo selectById(int borrowerServiceAppId) {
		BorrowInfoVo borrowInfoVo = new BorrowInfoVo();
		List<BorrowInfo> borrowInfos = new ArrayList<BorrowInfo>();
		try {
			borrowInfos = borrowInfoDAO.selectBorrowInfo(borrowerServiceAppId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		borrowInfoVo.setBorrowInfos(borrowInfos);
		return borrowInfoVo;
	}
}
