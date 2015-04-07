package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;

/**
 * 
 * @author zhangman
 * 
 */
public interface IAccountInfoService {

	/**
	 * 添加分公司财务信息
	 * 
	 * @param accountInfo 分公司财务信息对象
	 * @return true: 成功，false：失败
	 */
	public boolean addAccountInfo(AccountInfo accountInfo);
	
	/**
	 * 添加分公司财务信息
	 * 
	 * @param accountInfo 分公司财务信息对象
	 * @return true: 成功，false：失败
	 */
	public int addAccount(AccountInfo accountInfo);

	/**
	 * 按搜索条件模糊查询 并分页
	 * 
	 * @param accountInfo 搜索条件
	 * @param paginationString 分页设置
	 * @param accountType 
	 * @return 账户信息列表
	 */
	public Pagination selectAccountInfos(AccountInfo accountInfo, Pagination paginationString,String accountType);

	/**
	 * 修改 账户信息
	 * 
	 * @param accountInfo 分公司财务信息对象
	 * @return true: 成功，false：失败
	 */
	public boolean updateAccountInfo(AccountInfo accountInfo);

	/**
	 * 添加或 修改数据
	 * 
	 * @param accountInfo 账户对象
	 * @return 账户对象
	 */
	public AccountInfo addOrUpdate(AccountInfo accountInfo);

	/**
	 * 模糊查询
	 * 
	 * @param fuzzyValue 模糊查询条件
	 * @param pagination 分页条件
	 * @param accountType 账户类型
	 * @return 分公司财务信息分页列表
	 */
	public Pagination selectFuzzy(String fuzzyValue, Pagination pagination,String accountType);

	/**
	 * 根据传入的信息查公公司账号
	 * 
	 * @param accountInfo 分公司id
	 * @return 分公司信息
	 */
	public List<AccountInfo> selectById(AccountInfo accountInfo);

	/**
	 * 根据传入的信息查公公司账号
	 * 
	 * @param accountInfoId 分公司id
	 * @return 分公司信息
	 */
	public AccountInfo selectByAccountID(int accountInfoId);

	/**
	 * 通过creditapplicationId查询所有关于这个申请的卡信息
	 * 
	 * @param creditapplicationId 信贷申请主键
	 * @return List<AccountInfo> 卡信息List
	 */
	public List<AccountInfo> selectCardInfo(Integer creditapplicationId);

	/**
	 * 通过creditapplicationId查询所有关于这个申请的卡信息
	 * 
	 * @param creditapplicationId 信贷申请主键
	 * @return List<AccountInfo> 卡信息List
	 */
	public List<AccountInfo> selectCompanyAccounts(Integer creditapplicationId);
	/**
	 * 根据信贷审批查对应账号信息
	 * 
	 * @param creditApplicationId 
	 * @return AccountInfo
	 */
	public AccountInfo selectByCrediApplicationId(Long creditApplicationId);
	
	/**
	 * 根据信贷申请id 查询 对应还款账号信息
	 * @author zhangman
	 * @param creditApplicationId 
	 * @return AccountInfo
	 */
	public AccountInfo selectReturnAccount(Long creditApplicationId);
	
	/**
	 *  批量 同步smp
	 * @return Message
	 */
	public Message updateSmpAll();
	
	/**
	 * 更新 同步 smp
	 * @param accountInfo accountInfo
	 * @return Message
	 */
	public Message updateSmp(AccountInfo accountInfo);
	
	/**
	 * 按  查询个人账户
	 * @param accountInfo 账户类 
	 * @return 账户类
	 */
	public AccountInfo selctAccountInfo(AccountInfo accountInfo);

	boolean updateAccountInfo(AccountInfo accountInfo, String type);
}
