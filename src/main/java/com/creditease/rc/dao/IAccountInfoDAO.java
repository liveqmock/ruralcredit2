package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.AccountInfoVo;
import com.creditease.rc.vo.AccountInfoVo2ICP;
/**
 * 
 * @author zhangman
 *
 */
public interface IAccountInfoDAO extends IBaseDao{
	/**
	 * 添加分公司账号信息
	 * @param accountInfo 分公司账户信息
	 * @return 分公司账号id
	 */
	public int addAccountInfo(AccountInfo accountInfo);
	/**
	 * 修改
	 * @param accountInfo 分公司账户信息
	 * @return 修改的行数
	 */
	public int updateAcount(AccountInfo accountInfo);
	/**
	 * 按搜索条件模糊查询 并分页
	 * @param accountInfo 搜索条件
	 * @param pagination 分页设置
	 * @return 账户信息列表
	 */
	public Pagination  selectAccountInfos(AccountInfo accountInfo,Pagination pagination);
	/**
	 * 模糊查询
	 * @param fuzzyValue 模糊查询条件
	 * @param pagination 分页条件
	 * @param accountType 账户类型
	 * @return 分公司财务信息分页列表
	 */
	public Pagination searchFuzzy(String fuzzyValue,Pagination pagination,String accountType);
	/**
	 *
	 * @param accountInfo 分公司账户信息对象
	 * @return 分公司信息
	 */
	public AccountInfo select(AccountInfo accountInfo);
	/**
	 *  根据id查公公司账号
	 * @param accountInfo 分公司id
	 * @return 分公司信息
	 */
	public List<AccountInfo> selectById(AccountInfo accountInfo);
	
	 /**
	 * 根据传入的信息查公公司账号
	 * @param accountInfoId 分公司id
	 * @return 分公司信息
	 */
	public AccountInfo selectByAccountID(int accountInfoId);
	/**
	 * 根据信贷审批单查账号
	 * @param creditApplicationId 根据信贷审批单ID
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
	 *  按操作员 （信贷员 id 分组）
	 * @return List<CustomerConsult>
	 */
	public List<AccountInfo> selectByoperaterId();
	
	/**
	 * 按操作员 （信贷员 id 修改）
	 * @param accountInfo  账户
	 * @return int
	 */
	public int updateByoperaterId(AccountInfo accountInfo);
	
	/**
	 * 按  查询个人账户
	 * @param accountInfo 账户
	 * @return  账户列表
	 */
	public  List<AccountInfo>  selectAccountInfo(AccountInfo accountInfo);
	/**
	 * 查询带地址的个人账户
	 * @param accountInfo  账户
	 * @return  账户列表
	 */
	public List<AccountInfoVo> selectAccountAddress(AccountInfo accountInfo);
	/**
	 * 如果该信息存在更新，否则就插入
	 * @param accountInfo
	 */
	void insertOrUpdate(AccountInfoVo2ICP accountInfo);
}
