package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IReceivedRecordDao;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.ReceivedRecord;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IReceivedRecordListService;
import com.creditease.rc.vo.ReceivedRecordVo;

/**
 * 
 * @author haoqiang
 * 
 */
@Service
public class ReceivedRecordListService implements IReceivedRecordListService {
	@Resource
	private IReceivedRecordDao receivedRecordDao;

	@Override
	public Pagination queryReceivedRecordList(ReceivedRecordVo receivedRecordVo, Pagination pagination, String param) {
		// TODO Auto-generated method stub

		if ("0".equals(param)) {
			// 模糊查询
			return receivedRecordDao.queryForPaginatedList("RECEIVEDRECORD.selectFuzzy", "RECEIVEDRECORD.countFuzzy",
					receivedRecordVo, pagination.getStartResult(), pagination.getPageSize());
		} else if ("1".equals(param)) {
			// 精确查询
			return receivedRecordDao.queryForPaginatedList("RECEIVEDRECORD.selectForGrid",
					"RECEIVEDRECORD.countForGrid", receivedRecordVo, pagination.getStartResult(),
					pagination.getPageSize());
		} else {
			return receivedRecordDao.queryForPaginatedList("RECEIVEDRECORD.selectForGrid",
					"RECEIVEDRECORD.countForGrid", receivedRecordVo, pagination.getStartResult(),
					pagination.getPageSize());
		}
	}

	@Override
	public List<CreditApplication> selectReceivedRecord(List<Integer> receivedRecordIdList) {
		List<CreditApplication> creditapplicationList = (List<CreditApplication>) receivedRecordDao.queryList(
				"RECEIVEDRECORD.selectReceivedRecordByList", receivedRecordIdList);
		return creditapplicationList;
	}

	@Override
	public List<ReceivedRecord> selectReceivedRecordStatus(List<Integer> receivedRecordIdList) {
		return (List<ReceivedRecord>) receivedRecordDao.queryList("RECEIVEDRECORD.selectReceivedRecordStatus",
				receivedRecordIdList);
	}

	@Override
	public boolean updateFinancialAppointmentRevoked(List<Integer> receivedRecordIdList) {
		// TODO Auto-generated method stub
		List<ReceivedRecord> receivedRecordList = new ArrayList<ReceivedRecord>();
		for (Integer i : receivedRecordIdList) {
			ReceivedRecord ReceivedRecord = new ReceivedRecord();
			ReceivedRecord.setReceivedRecordId(i);
			ReceivedRecord.setReceivedStatus("5");
			receivedRecordList.add(ReceivedRecord);
		}

		receivedRecordDao.batchUpdate("RECEIVEDRECORD.dynamicUpdate", receivedRecordList);
		return true;

	}

	@Override
	public ReceivedRecord queryReceivedRecordByPrimaryKey(Integer receivedRecordId) {
		// TODO Auto-generated method stub
		return (ReceivedRecord) receivedRecordDao.queryUnique("RECEIVEDRECORD.queryReceivedRecordByPrimaryKey",
				receivedRecordId);
	}

	@Override
	public List<ReceivedRecord> queryreceivedRecords(List<Integer> receivedRecordIdList) {
		// TODO Auto-generated method stub
		return (List<ReceivedRecord>) receivedRecordDao.queryList("RECEIVEDRECORD.queryreceivedRecords",
				receivedRecordIdList);
	}
}
