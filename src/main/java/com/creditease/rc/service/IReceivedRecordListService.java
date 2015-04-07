package com.creditease.rc.service;

import java.util.List;
import java.util.Vector;

import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.ReceivedRecord;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.ReceivedRecordVo;

/**
 * 
 * @author haoqiang
 * 
 */
public interface IReceivedRecordListService {
	/**
	 * 查询收款登记List
	 * 
	 * @param receivedRecordVo 用于查询的收款登记对象
	 * @param pagination 分页对象
	 * @param param 区分是模糊查询还是高级查询
	 * @return 分页列表对象
	 */
	Pagination queryReceivedRecordList(ReceivedRecordVo receivedRecordVo, Pagination pagination, String param);

	/**
	 * 
	 * 根据主键集合查信贷审批单集合
	 * 
	 * @param receivedRecordIdList 主键集合
	 * @return List<CreditApplication>
	 */
	List<CreditApplication> selectReceivedRecord(List<Integer> receivedRecordIdList);

	/**
	 * 查信贷审批单的状态
	 * 
	 * @param receivedRecordIdList 收款登记列表集合
	 * @return List<ReceivedRecord>
	 */
	List<ReceivedRecord> selectReceivedRecordStatus(List<Integer> receivedRecordIdList);

	/**
	 * 批量更新撤销财务预约
	 * 
	 * @param receivedRecordIdList 收款登记IdList
	 * @return 是否更新成功
	 */
	boolean updateFinancialAppointmentRevoked(List<Integer> receivedRecordIdList);

	ReceivedRecord queryReceivedRecordByPrimaryKey(Integer receivedRecordId);

	List<ReceivedRecord> queryreceivedRecords(List<Integer> receivedRecordIdList);

}
