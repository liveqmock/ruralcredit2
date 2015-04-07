package com.creditease.rc.service;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.BorrowerServiceApp;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.ReceivedRecord;
import com.creditease.rc.domain.ReturnPlan;
import com.creditease.rc.domain.RuralReturnDis;
import com.creditease.rc.framework.pager.Pagination;

/**
 * 
 * @author haoqiang
 * 
 */

public interface IReceivablesRegistrationService {
	// ★━━━━━━━━━━━━━━━━━━━收款记录配相关━━━━━━━━━━━━━━━━━━━━━━━━★
	/**
	 * 
	 * @param passReceivedRecord 收款记录
	 * @return 布尔类型
	 */
	boolean saveReceivedRecord(ReceivedRecord passReceivedRecord);

	/**
	 * 
	 * @param receivedRecordId 收款记录主键
	 * @return 涉及条数
	 */
	public int updateReceivedStatus(Integer receivedRecordId);

	/**
	 * 
	 * @param passReceivedRecord 收款记录参数
	 * @return 收款记录
	 */
	List<ReceivedRecord> searchReceivedRecord(ReceivedRecord passReceivedRecord);

	/**
	 * 
	 * @param passReturnPlan 带查询条件的还款计划
	 * @return 返回结果还款计划List
	 */
	boolean updateAutomaticallyDeducted(ReturnPlan passReturnPlan);

	/**
	 * 传入主键List批量修改收款状态为收款完成
	 * 
	 * @param receivedRecordIdList 收款登记主键
	 * @param status 收款状态
	 * @return true or false
	 */
	boolean batchUpdateReceivedStatus(List<Integer> receivedRecordIdList, String status);

	// ★━━━━━━━━━━━━━━━━━━━收款记录配相关END━━━━━━━━━━━━━━━━━━━━━★

	// ★━━━━━━━━━━━━━━━━━━━━还款计划相关━━━━━━━━━━━━━━━━━━━━━━━━★
	/**
	 * 批量插入还款计划
	 * 
	 * @param passReturnPlanList 还款计划List
	 * @return 布尔类型
	 */
	boolean batchInsertReceivablesHistory(List<ReturnPlan> passReturnPlanList);

	/**
	 * 批量修改还款计划
	 * 
	 * @param passReturnPlanList 还款计划List
	 * @return 整型
	 */
	int batchUpdateReceivablesHistory(List<ReturnPlan> passReturnPlanList);

	/**
	 * 查询还款计划列表
	 * 
	 * @param pagination 列表
	 * @param creditapplicationId 信贷申请单主键
	 * @return 还款历史列表
	 */
	Pagination queryReceivablesHistoryDataGrid(Pagination pagination, Integer creditapplicationId);

	/**
	 * 刪除还款计划
	 * 
	 * @param borrowerServiceAppId 个人申请主键
	 * @return 整型
	 */
	int deleteReceivablesHistory(Integer borrowerServiceAppId);

	/**
	 * 
	 * @param creditapplicationId 信贷申请单主键
	 * @param param 默认还款方式类型
	 * @return 布尔
	 */
	boolean autoSwitch(Integer creditapplicationId, String param);

	// ★━━━━━━━━━━━━━━━━━━还款计划相关END━━━━━━━━━━━━━━━━━━━━━━★

	// ★━━━━━━━━━━━━━━━━━━━实收分配相关━━━━━━━━━━━━━━━━━━━━━━━━★
	/**
	 * 批量插入实收分配
	 * 
	 * @param passRuralReturnDisList 实收分配List
	 * @return 布尔类型
	 */
	boolean batchInsertDistribution(List<RuralReturnDis> passRuralReturnDisList);

	/**
	 * 批量修改实收分配
	 * 
	 * @param passRuralReturnDisList 实收分配List
	 * @return 整型
	 */
	int batchUpdateDistribution(List<RuralReturnDis> passRuralReturnDisList);

	/**
	 * 查询实收分配列表
	 * 
	 * @param pagination 列表参数
	 * @param returnPlanId 还款计划主键
	 * @return 实收分配列表
	 */
	Pagination queryDistributionDataGrid(Pagination pagination, Integer returnPlanId);

	/**
	 * 删除实收分配
	 * 
	 * @param returnPlanId 还款计划主键
	 * @return 整型
	 */
	int deleteDistribution(Integer returnPlanId);

	// ★━━━━━━━━━━━━━━━━━━━实收分配相关END━━━━━━━━━━━━━━━━━━━━━━━━★
	// ★━━━━━━━━━━━━━━━━━━━━分配算法相关━━━━━━━━━━━━━━━━━━━━━━━━━━★
	/**
	 * @return 布尔类型
	 */
	Integer saveDistribution();

	/**
	 * 将需要分配的登记和需要进行分配的还款计划进行匹配和分配
	 * 
	 * @param paramReceivedRecord 需要分配的登记
	 * @param allotReturnPlanList 相对应的还款计划
	 * @return 是否成功
	 */
	boolean allotReceivedAmount(ReceivedRecord paramReceivedRecord, List<ReturnPlan> allotReturnPlanList);

	/**
	 * 将传入的金额和当期的还款计划按照服务费、罚息、滞纳金、利息和本金的顺序依次收齐
	 * 
	 * @param toBeDistributed 当期被分配的金额
	 * @param paramReceivedRecord 当期分配的登记中取出金额
	 * @param r 还款计划
	 * @return 更新后的还款计划
	 */
	RuralReturnDis returnRuralReturnDis(Double toBeDistributed, ReceivedRecord paramReceivedRecord, ReturnPlan r);

	/**
	 * 这个是已经完成分配后调整减免的接口
	 * 
	 * @param paramReceivedRecord 收款登记
	 * @param adjustReturnPlanList 调整后的
	 * @return 布尔型
	 */
	boolean adjustment(ReceivedRecord paramReceivedRecord, List<ReturnPlan> adjustReturnPlanList);

	// ★━━━━━━━━━━━━━━━━━━━分配算法相关END━━━━━━━━━━━━━━━━━━━━━━━━★
	/**
	 * 测试定时任务
	 */
	public void test();

	/**
	 * 检查是否存在在途资金
	 * 
	 * @param creditapplicationId 信贷申请主键
	 * @return 一个对象
	 */
	Map<String, List<ReceivedRecord>> checkOnWay(Integer creditapplicationId);

	/**
	 * 撤销申请
	 * 
	 * @param param 目前无用（是否调用财务撤销接口）
	 * @param receivedRecordIdList 需要撤销申请的主键List
	 * @return 是否成功
	 */
	boolean updateAppointmentRevoked(String param, List<Integer> receivedRecordIdList);

	/**
	 * 
	 * @param creditapplicationId 传入信贷申请单主键
	 * @return 返回借款人信息
	 */
	BorrowerServiceApp selectBycId(Integer creditapplicationId);

	/**
	 * 添加个人卡信息
	 * 
	 * @param accountInfo 卡信息对象
	 * @return 卡信息对象
	 */
	AccountInfo addPersonalAccInfo(AccountInfo accountInfo);

	/**
	 * zxb
	 * 
	 * @param rrList 收款登记表ID集合
	 * @return List<CreditApplication>
	 */
	List<CreditApplication> selectCreditapplicationId(List<Integer> rrList);
}
