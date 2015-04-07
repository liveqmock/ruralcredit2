package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.AmountConfirm;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.FinanceMoney;
import com.creditease.rc.domain.GroupLoanRegist;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.ProcessMessage;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.FinanceReceiveVo;
import com.creditease.rc.vo.ReceivedRecordVo;

/**
 * 
 * @author Administrator
 * 
 */
public interface IFinanceMoneyService {
	/**
	 * 插入财务对象
	 * 
	 * @param record 财务表对象
	 */
	public void insertFinanceMoney(FinanceMoney record);

	/**
	 * 更新财务对象
	 * 
	 * @param record 财务对象
	 * @return int 更新的条数
	 */
	public int updateFinanceMoney(FinanceMoney record);

	/**
	 * 查询财务付款对象
	 * 
	 * @param parameter 业务申请单对象
	 * @return FinanceMoney 财务对象
	 */
	public FinanceMoney selectPaymentFinanceMoney(CreditApplication parameter);

	/**
	 * 查询财务收款对象
	 * 
	 * @param financeMoney 财务对象 条件
	 * @return FinanceMoney 财务对象
	 */
	public FinanceMoney selectReceiveFinanceMoney(FinanceMoney financeMoney);

	/**
	 * 更新财务对象
	 * 
	 * @param list 财务对象列表
	 * @return string
	 */
	public String updateFinanceMoney(List<ProcessMessage> list);

	/**
	 * 查询财务收款列表（页面展现）
	 * 
	 * @param pagination 分布条件
	 * @param creditApplication 查询参数
	 * @return Pagination 页面对象
	 */
	public Pagination selectRecevieList(Pagination pagination, CreditApplication creditApplication);

	/**
	 * 查询在途资金
	 * 
	 * @param receivedRecordIdList ID集合
	 * @return List<FinanceMoney>
	 */
	public List<FinanceMoney> selectOnline(List<Integer> receivedRecordIdList);

	/**
	 * 查询财务付款对象列表
	 * 
	 * @param financeMoney 财务对象
	 * @return list 财务付款对象列表
	 */
	public List<FinanceMoney> selectPaymentFinanceMoneyList(FinanceMoney financeMoney);

	/**
	 * 查询财务收款对象列表
	 * 
	 * @param financeMoney 财务对象
	 * @return list 财务收款对象列表
	 */
	public List<FinanceMoney> selectReceiveFinanceMoneyList(FinanceMoney financeMoney);

	/**
	 * 查询财务表对象数量
	 * 
	 * @param financeMoney 财务对象
	 * @return Integer
	 */
	public Integer selectCountReceiveFinanceMoneyList(FinanceMoney financeMoney);

	/**
	 * 付款重发
	 * 
	 * @param parameter VO
	 * @return boolean
	 */
	public Message paymentAgin(GroupLoanRegist parameter);

	/**
	 * 查询账号信息
	 * 
	 * @param receivedRecordId 收款登记ID
	 * @return AccountInfo 账户对象
	 */
	public AccountInfo selectAccountInfo(Integer receivedRecordId);

	/**
	 * 查询收款登记数据
	 * 
	 * @param receivedRecordId 收款登记ID
	 * @return List<ReceivedRecord> 收款登记对象列表
	 */
	public List<ReceivedRecordVo> selectReceiveRecordList(List<Integer> receivedRecordId);

	/**
	 * 收款重发
	 * 
	 * @param parameter 财务收款对象
	 * @return boolean
	 */
	public Message receiveAgin(FinanceReceiveVo parameter);

	/**
	 * 财务收款失败再预约
	 * After the credit （贷后系统）缩写为：ATC
	 * 
	 * @author 郝强
	 * @param parameter 财务收款对象
	 * @return Message
	 */
	public Message receiveAginATC(FinanceReceiveVo parameter);

	/**
	 * 批量插入
	 * 
	 * @param financeMoneyList 财务收款集合
	 */
	public void batchInsertReceiveFinanceMoney(List<FinanceMoney> financeMoneyList);

	/**
	 * 获得订单号
	 * 
	 * @return Integer
	 */
	public Long getBizId();

	/**
	 * 查询财务表是否可撤销，如可撤销就撤掉
	 * 
	 * @param receiveRecordId 收款登记表
	 * @return boolean
	 */
	public boolean undoFinanceMoney(Integer receiveRecordId);

	/**
	 * 查询财务撤销对象
	 * 
	 * @param associationId 收款登记表ID或放款登记表ID
	 * @return FinanceMoney 财务撤销对象
	 */
	public FinanceMoney selectUndoFinanceMoney(Integer associationId);

	/**
	 * 根据主键更新财务表
	 * 
	 * @param record 财务表主键
	 * @return int
	 */
	public int updateFinanceMoneyById(FinanceMoney record);

	/**
	 * 根据业务单号查财务表
	 * 
	 * @param bizId 业务单号
	 * @return List<FinanceMoney>
	 */
	public List<FinanceMoney> selectFinanceMoneyListByBizId(Long bizId);

	/**
	 * 财务撤销修改状态
	 * 
	 * @param financeMoney 修改对象
	 * @return boolean
	 */
	public boolean updateFinanceMoneyByBizId(FinanceMoney financeMoney);

	/**
	 * 财务付款撤销
	 * 
	 * @param creditapplicationId 信贷申请表ID
	 * @return Message
	 */
	public Message paymentUndo(Integer creditapplicationId);

	/**
	 * 财务收款撤销
	 * 
	 * @param receivedRecord 收款登记表ID
	 * @return Message
	 */
	public Message receiveUndo(Integer receivedRecord);

	/**
	 * 查询财务表
	 * 
	 * @param financeMoney 财务表对象
	 * @return FinanceMoney
	 */
	public FinanceMoney selectFinanceMoney(FinanceMoney financeMoney);

	/**
	 * 查询财务表
	 * 
	 * @param parameter 额度确认表对象
	 * @return AmountConfirm
	 */
	public AmountConfirm selectFinanceMoneyBack(AmountConfirm parameter);

	/**
	 * 查询财务表
	 * 
	 * @param associationId 财务表关联ID
	 * @return FinanceMoney
	 */
	public AccountInfo selectAccountInfoForFinanceBack(Integer associationId);

	/**
	 * 查询一组收款数据是否是同一个账户
	 * 
	 * @param list List<Integer>
	 * @return Integer
	 */
	public Integer selectIsDuaplicatAccount(List<Integer> list);

	/**
	 * 用订单查询预约金额用于手动收付款
	 * 
	 * @author wyf
	 * @param financeMoney FinanceMoney
	 * @return FinanceMoney
	 */
	public FinanceMoney selectFinanceMoneyByBizId(FinanceMoney financeMoney);

	/**
	 * 根据条件查财务表
	 * 
	 * @param financeMoney 财务对象
	 * @return FinanceMoney
	 */
	public FinanceMoney selectFinanceMoneyByCondition(FinanceMoney financeMoney);

	/**
	 * 收款登记，放款失败登记调用的接口
	 * 
	 * @param associationId 关联表ID
	 * @param accountInfoId 账户表ID
	 * @param businessType 业务类型 放款失败登记为U，正常收款登记为S
	 * @return
	 */
	public void insertFinanceMoney(int associationId, int accountInfoId, String businessType);

	/**
	 * 查询财务付款回收列表
	 * 
	 * @param pagination 页面对象
	 * @param creditApplication 查询条件
	 * @return Pagination
	 */
	public Pagination selectBackSectionList(Pagination pagination, CreditApplication creditApplication);

	/**
	 * 查询财务收款列表（页面展现）
	 * 郝强上传的新的查询财务收款列表页面除去了付款回收的状态~
	 * 
	 * @author 郝强
	 * @param pagination 分布条件
	 * @param creditApplication 查询参数
	 * @return Pagination 页面对象
	 */
	public Pagination selectRecevieListHQ(Pagination pagination, CreditApplication creditApplication);
}