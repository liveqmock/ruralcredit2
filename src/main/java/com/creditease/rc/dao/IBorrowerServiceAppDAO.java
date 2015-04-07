package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.BorrowerServiceApp;
import com.creditease.rc.domain.Familymember;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.BorrowerInfoVo;
import com.creditease.rc.vo.BorrowerServiceAppVo2;
import com.creditease.rc.vo.CreditApplicationSearch;

/**
 * 
 * @author zhangman
 * 
 */
public interface IBorrowerServiceAppDAO extends IBaseDao {
	/**
	 * 
	 * @param borrowerServiceApp 借款服务申请对象
	 * @return 借款服务申请id
	 */
	public int addBorrowerServiceApp(BorrowerServiceApp borrowerServiceApp);

	/**
	 * 按小组申请id 查询该组 借款人列表
	 * 
	 * @param creditapplicationId 小组申请id
	 * @return 借款人列表
	 */
	public List<BorrowerServiceApp> selectBorrowerServiceAppList(int creditapplicationId);

	/**
	 * 
	 * @param borrowerServiceApp 借款服务申请对象
	 * @return 被修改的行数
	 */
	public int updateBorrowerServiceApp(BorrowerServiceApp borrowerServiceApp);

	/**
	 * 按借款服务申请 id 查询
	 * 
	 * @param borrowerServiceAppId 借款服务申请 id
	 * @return 借款服务申请记录
	 */
	public BorrowerServiceApp selectByBorrowerServiceAppId(int borrowerServiceAppId);

	/**
	 * 
	 * 按客户id 查询
	 * 
	 * @param customerBasicId 客户基本信息id
	 * @return 借款服务申请记录
	 */
	public BorrowerServiceApp selectByCustomerId(int customerBasicId);

	/**
	 * 按身份证号 查询
	 * 
	 * @param credentialsNumber 身份证号码
	 * @return 借款服务申请记录
	 */
	public String selectByNumber(String credentialsNumber);

	/**
	 * 按id 删除
	 * 
	 * @param borrowerServiceAppId 借款服务申请 id
	 * @return true或 false
	 */
	public boolean deleteBorrowerServiceApp(int borrowerServiceAppId);

	/**
	 * 设置入户一 标识
	 * 
	 * @param borrowerServiceAppId 借款服务申请 id
	 * @param firstFlag 入户一填写标记
	 * @return boolean（true flase）
	 */
	public boolean updateFirstFlag(int borrowerServiceAppId, String firstFlag);

	/**
	 * 设置入户二 标识
	 * 
	 * @param borrowerServiceAppId 借款服务申请 id
	 * @param secondFlag 入户二填写标记
	 * @return 成功 true ，失败：false
	 */
	public boolean updateSecondFlag(int borrowerServiceAppId, String secondFlag);

	/**
	 * 设置 联系人调查标示
	 * 
	 * @param borrowerServiceAppId 借款服务申请 id
	 * @param thirdFlag 调查人填写标记
	 * @return 成功 ： true，失败：false
	 */
	public boolean updateThirdFlag(int borrowerServiceAppId, String thirdFlag);

	/**
	 * 
	 * @param creditapplicationId
	 *            小组信贷申请单id
	 * @return 借款服务申请列表
	 */
	public List<BorrowerInfoVo> selectBorrowerInfoVoList(int creditapplicationId);

	/**
	 * 做删除标记
	 * 
	 * @param borrowerServiceAppId 借款服务申请 id
	 * @return 删除的行数
	 */
	public int updateDeleteFlag(int borrowerServiceAppId);

	/**
	 * 修改 审核标记
	 * 
	 * @param borrowerServiceApp 信贷申请
	 * @return 整形
	 */
	public int submitAuditing(BorrowerServiceApp borrowerServiceApp);

	/**
	 * 按各种条件查询
	 * 
	 * @param borrowerInfoVo 借款人申请拓展类
	 * @return 列表
	 */
	public List<BorrowerServiceApp> selectByBorrowerInfoVo(BorrowerInfoVo borrowerInfoVo);

	/**
	 * 
	 * @author 韩大年
	 * @Description: 根据creditapplicationId查询借款人
	 * @param creditapplicationId
	 * @return BorrowerServiceApp
	 * @version v1.0
	 *          2013-3-26
	 */
	public BorrowerServiceApp selectBorrowerLeaderByCreditApplicationId(Integer creditapplicationId);

	/**
	 * @author manzhang
	 * @param familymember 家庭成员
	 * @return 配偶信息
	 */
	public Familymember selectPartner(Familymember familymember);

	/**
	 * 
	 * @author 韩大年
	 * @Description: 查询配偶信息
	 * @param borrowerServiceAppId
	 * @return Familymember
	 * @version v1.0
	 *          2013-4-26
	 */
	public Familymember selectSpouseByBorrowerServiceAppId(Integer borrowerServiceAppId);

	/**
	 * 查询 客户信息导出列表
	 * 
	 * @param map 参数
	 * @return 分页列表
	 */
	public Pagination exportCustomerInformationtList(Map<String, Object> map, Pagination pagination);

	/**
	 * 导出 客户信息导出列表
	 * 
	 * @param map 参数
	 * @return 列表
	 */
	public List<Map> exportCustomerInformationt(Map map);

	/**
	 * 修改借款申请
	 * zhangman
	 * 
	 * @param borrowerServiceApp
	 * @return 借款服务申请id
	 */
	public int updateBorrowerServiceAppForSistem(BorrowerServiceApp borrowerServiceApp);

	/**
	 * 查询与该电话有关的作为担保人的借款结清次数
	 * 
	 * @author luohongjie
	 */
	public List<CreditApplicationSearch> selectBorrowerServiceByTel(String telphone);

	/**
	 * 查询与该电话有关的借款人的借款结清次数
	 * 
	 * @return List<CustomerConsult>
	 */
	public List<CreditApplicationSearch> selectBorrowerAuditListByTel(String telPhone);

	/**
	 * 郝强提交检查主借款人有无提交申请调查
	 * 
	 * @param creditapplicationId
	 * @return
	 */
	public List<BorrowerServiceApp> checkBorrowerSave(Long creditapplicationId);
	
	/**
	 * 根据信贷申请id查询借款人信息
	 * @param creditapplicationId
	 * @return
	 */
	public List<BorrowerServiceAppVo2> queryBorrowerInfo(Long creditapplicationId);
}
