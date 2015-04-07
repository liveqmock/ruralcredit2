package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.BorrowerServiceApp;
import com.creditease.rc.domain.CustomerBasicInfo;
import com.creditease.rc.domain.Employee;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.CustomerBasicInfoVo;
import com.creditease.rc.vo.CustomerInfoVO;

/**
 * 
 * @author zhangman
 * 
 */
public interface ICustomerBasicInfoDao {

	/**
	 * 分页查询
	 *
	 * @param pagination 分页条件
	 * @param customerBasicInfo 客户基本信息
	 * @return 客户基本信息分页 列表
	 */
	public Pagination selectCustomerBasic(CustomerBasicInfo customerBasicInfo, Pagination pagination);

	/**
	 * 添加 客户基本 信息
	 *
	 * @param customerBasicInfo 客户基本信息
	 * @return 客户基本信息id
	 */

	public int insert(CustomerBasicInfo customerBasicInfo);

	/**
	 * 根据主键修改CustomerBasic
	 *
	 * @param customerBasicInfo 客户基本信息
	 * @return 修改的行数
	 */
	public int updateByPrimaryKeySelective(CustomerBasicInfo customerBasicInfo);

	/**
	 * 用主键查找CustomerBasic
	 *
	 * @param customerBasicId 客户基本信息id
	 * @return 客户基本信息对象
	 */
	public CustomerBasicInfo selectByPrimaryKey(Integer customerBasicId);

	/**
	 * 删除CustomerBasic
	 *
	 * @param customerBasicId 客户基本信息id
	 * @return 删除的行数
	 */
	public int deleteByPrimaryKey(Integer customerBasicId);

	/**
	 * 按条件查询
	 *
	 * @param customer 客户基本信息对象
	 * @return 客户基本信息对象
	 */
	public CustomerBasicInfo selectCustomerBasicInfo(CustomerBasicInfo customer);

	/**
	 * 按身份证查询 (查询语句 已经忽略大小写)
	 *
	 * @param credentialsNumber 身份证号
	 * @return 客户基本信息列表
	 */
	public List<CustomerBasicInfoVo> selectByCredentialsNumber(String credentialsNumber);
	/**
	 * 按身份证查询     借款人历史借款信息
	 *
	 * @param identityNumber 身份证号
	 * @return 客户基本信息列表
	 */
	public List<CustomerBasicInfoVo> getCustomerInfo(String identityNumber);

	/*
    *根据身份证号查询借款人黑名单信息
    *  @param identityNumber 身份证号
    *
    * */
	public List<CustomerBasicInfoVo>   getCustomerBlackInfo(String identityNumber);
	/*
 *根据身份证号查询借款人 共借人信息
 *  @param identityNumber 身份证号
 *
 * */
	public List<CustomerBasicInfoVo>   getCoBorrowerInfo(String identityNumber);

	/*
*根据身份证号查询借款人配偶信息
*  @param identityNumber 身份证号
*
* */
	public List<CustomerBasicInfoVo>   getMateInfo(String identityNumber);
	/*
   *根据身份证号查询借款人担保信息
   *  @param identityNumber 身份证号
   *
   * */
	public List<CustomerBasicInfoVo>   getGuaranorInfo(String identityNumber);
	/*
    *根据身份证号查询查询此人直系亲属是否可以借款
    *  @param identityNumber 身份证号
    *
    * */
	public List<CustomerBasicInfoVo>   getConsanguinityInfo(String identityNumber);

	/**
	 * 按身份证查询担保人 (查询语句 已经忽略大小写)
	 *
	 * @param credentialsNumber 身份证号
	 * @return 客户基本信息列表
	 */
	public List<CustomerBasicInfoVo> selectGuaranorByCredentialsNumber(String credentialsNumber);

	/**
	 * 按身份证查询
	 *
	 * @param credentialsNumber 身份证号
	 * @return 担保人列表
	 */
	public List<CustomerBasicInfoVo> selectFamilyByCredentialsNumber(String credentialsNumber);

	/**
	 * 按身份证查询
	 *
	 * @param borrowerServiceApp 借款申请
	 * @return 借款人配偶列表
	 */
	public List<CustomerBasicInfoVo> selectGuanor(BorrowerServiceApp borrowerServiceApp);

	/**
	 * 按身份证查询
	 *
	 * @param credentialsNumber 身份证号
	 * @return 返回一条客户记录
	 */
	public CustomerBasicInfo selectByNumber(String credentialsNumber);

	/**
	 * 模糊查询
	 *
	 * @param fuzzyValue 条件
	 * @param pagination 分页条件
	 * @return 分页列表
	 */
	public Pagination selectFuzzy(String fuzzyValue, Pagination pagination);

	/**
	 * 模糊查询
	 *
	 * @param customerBasicId 条件
	 * @return 状态
	 */
	public Map selectAutus(int customerBasicId);

	/**
	 * 修改客户基本信息
	 * zhangman
	 *
	 * @param borrowerServiceApp
	 * @return 借款服务申请id
	 */
	public int updateDynamic(CustomerBasicInfo customerBasicInfo);

	// 郝强上传关于验证同一笔单子身份证号不同

	public List<Map<String, String>> checkForCredentialsNumber(Long creditapplicationId);

	/**
	 *获取黑名单列表
	 * @return         List<CustomerBasicInfo>     黑名单列表客户
	 * */
	public   List<CustomerBasicInfo> getBlackListCustomerInfoList();

	/**
	 * 查询32位业务单号
	 * @return
	 */
	public String selBusinessNumberByUUID(String businessNumber);
	/**
	 * 根据部门ID
	 * @param departmentId
	 */
	public List<Employee> getEmployeeListByDeparmentId( String departmentId);

	/**
	 * 根据新的申请id查询 共借人 借款人 担保人信息
	 * @param creditapplicationId
	 */
	public List<CustomerInfoVO> getCustomerInfoListByCreditApplicationID( long creditapplicationId);


}