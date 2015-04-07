package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.AmountConfirm;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.FinanceMoney;
import com.creditease.rc.domain.ReceivedRecord;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.ReceivedRecordVo;

/**
 * 
 * @author Administrator
 * 
 */
public interface IFinanceMoneyDao {
	/**
	 * 插入财务对象到数据库
	 * 
	 * @param record 财务对象
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
	 * 获得订单号
	 * 
	 * @return Integer
	 */
	public Long getBizId();

	/**
	 * 修改财务对象状态
	 * 
	 * @param record 财务对象
	 * @return int 更新的条数
	 */
	public int updateFinanceMoneyStatus(FinanceMoney record);

	/**
	 * 查询账务收款列表
	 * 
	 * @param pagination 颁布条件
	 * @param creditApplication 查询参数
	 * @return Pagination
	 */
	public Pagination selectRecevieList(Pagination pagination, CreditApplication creditApplication);

	/**
	 * 查看账务收款
	 * 
	 * @param financeMoney 条件
	 * @return FinanceMoney
	 */
	public FinanceMoney selectReceiveFinanceMoney(FinanceMoney financeMoney);

	/**
	 * 批量插入财务对象
	 * 
	 * @param financeMoneyList 账务状态列表
	 */
	public void batchInsertReceiveFinanceMoney(List<FinanceMoney> financeMoneyList);

	/**
	 * 批量更新财务对象
	 * 
	 * @param financeMoneyList 账务状态列表
	 */
	public void batchUpdateFinanceMoney(List<FinanceMoney> financeMoneyList);
	
	void updateFinanceMoneyTYPEisF(List<FinanceMoney> financeMoneyList);

	/**
	 * 查询在途资金
	 * 
	 * @param receivedRecordIdList ID集合
	 * @return List<FinanceMoney> 财务对象列表
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
	 * 查询财务撤销对象
	 * 
	 * @param receiveRecordId 收款登记表ID
	 * @return FinanceMoney 财务撤销对象
	 */
	public FinanceMoney selectUndoFinanceMoney(Integer receiveRecordId);

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
	 * 查询账号信息
	 * 
	 * @param receivedRecordId 收款登记ID
	 * @return FinanceMoney
	 */
	public AccountInfo selectAccountInfoForFinanceBack(Integer receivedRecordId);

	/**
	 * 用订单查询预约金额用于手动收付款
	 * 
	 * @author wyf
	 * @param financeMoney 财务对象
	 * @return FinanceMoney
	 */
	public FinanceMoney selectFinanceMoneyByBizId(FinanceMoney financeMoney);

	/**
	 * 查询是否账号一致
	 * 
	 * @param list 主键List
	 * @return Integer
	 */
	public Integer selectIsDuaplicatAccount(List<Integer> list);

	/**
	 * 根据条件查财务表
	 * 
	 * @param financeMoney 财务对象
	 * @return FinanceMoney
	 */
	public FinanceMoney selectFinanceMoneyByCondition(FinanceMoney financeMoney);

	/**
	 * 查询财务付款回收列表
	 * 
	 * @param pagination 页面对象
	 * @param creditApplication 查询条件
	 * @return Pagination
	 */
	public Pagination selectBackSectionList(Pagination pagination, CreditApplication creditApplication);

	/**
	 * 查询所有预约到贷后的订单
	 * 
	 * @author 郝强
	 * 
	 * @return 订单集合
	 */
	public List<String> queryAppointingReseive();

	/**
	 * @author 郝强
	 * 
	 * @param maps 用于修改状态的mapList
	 * @return 是否修改成功
	 */
	public boolean updateFinanceMoneyStatusAndReturnMSG(List<Map<String, String>> maps);

	/**
	 * 
	 * @param maps 用于修改状态的mapList
	 * @return 是否修改成功
	 */
	public boolean updateReceivedRecordStatusBybizId(List<Map<String, String>> maps);

	/**
	 * 查询账务收款列表
	 * （用于新的财务收款列表 出掉了 付款回收）
	 * 
	 * @author 郝强
	 * @param pagination 颁布条件
	 * @param creditApplication 查询参数
	 * @return Pagination
	 */
	public Pagination selectRecevieListHQ(Pagination pagination, CreditApplication creditApplication);

	/**
	 * 通过收款登记IDLIST查询对应的BizIdList
	 * 
	 * @author 郝强
	 * @param receivedRecordIdList
	 * @return
	 */
	public List<String> queryBizIdListByReceivedRecordIdList(List<Integer> receivedRecordIdList);
	
	
	/**
	 * 根据信贷申请id修改为历史标识（财务退票的时候使用）
	 * @param creditapplicationId
	 */
	public void updateHistory(Long creditapplicationId);
	/**
	 * 综合信贷回退
	 * @param creditapplicationId
	 * @return
	 */
	int updateIcp(String creditapplicationId);
}