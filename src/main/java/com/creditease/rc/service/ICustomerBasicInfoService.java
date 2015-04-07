package com.creditease.rc.service;

import com.creditease.rc.domain.CustomerBasicInfo;
import com.creditease.rc.domain.Employee;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.CustomerBasicInfoVo;
import com.creditease.rc.vo.BorrowerInfo;
import com.creditease.rc.vo.CustomerInfoVO;
import com.creditease.rc.vo.ReceiveCreditObj;

import java.text.ParseException;
import java.util.List;

/**
 * @author: zhangman
 * @version: v2.0
 */
public interface ICustomerBasicInfoService {

	/**
	 * 查询满足指定条件的客户信息总数
	 * 
	 * @param customerBasicInfo
	 * @return
	 */
// int getCountOfCustomerBasicInfos(CustomerBasicInfo customerBasicInfo);

	/**
	 * 带条件的分页查询
	 * 
	 * @param customerBasicInfo 客户基本信息对象
	 * @param pagination 分页条件
	 * @param fuzzyValue 模糊条件
	 * @return 客户基本信息 分页列表
	 */
	public Pagination listCustomerBasicInfos(CustomerBasicInfo customerBasicInfo, String fuzzyValue,
			Pagination pagination);

	/**
	 * 添加客户基本信息
	 * 
	 * @param customerBasicInfo 客户基本信息对象
	 * @return true:成功。false:失败
	 */
	public boolean addCustomerBasicInfo(CustomerBasicInfo customerBasicInfo);

	/**
	 * 修改客户基本信息
	 * 
	 * @param customerBasicInfo 客户基本信息对象
	 * @return true:成功。false:失败
	 */
	public boolean updateCustomerBasicInfo(CustomerBasicInfo customerBasicInfo);

	/**
	 * 删除客户基本信息
	 * 
	 * @param customerBasicInfoId 客户基本信息id
	 * @return true:成功。false:失败
	 */
	public boolean deleteCustomerBasicInfoById(int customerBasicInfoId);

	/**
	 * 按条件查询客户基本信息
	 * 
	 * @param cunstomerBasicInfo 客户基本信息对象
	 * @return 客户基本信息对象
	 */
	public CustomerBasicInfo selectCustomerBasicInfo(CustomerBasicInfo cunstomerBasicInfo);

	/**
	 * 按身份证客户基本信息
	 *
	 * @param credentialsNumber 身份证号码
	 * @param creditapplicationId
	 * @return 客户基本信息对象列表
	 */
	public List<CustomerBasicInfoVo> selectCustomerBasicInfo(String credentialsNumber, Long creditapplicationId);
    /**
     * 按身份证     查询借款人基本信息在信贷申请前进行验证使用
     * add by ygx 2014-09-17
     * * @param  identityNumber 身份证号码
     * @return 客户基本信息对象列表
     */
    public List<CustomerBasicInfoVo> getIdentityListInfo(String identityNumber) throws Exception;

	/**
	 * 按身份证号 查询
	 * 
	 * @param credentialsNumber 身份证号码
	 * @return 客户基本信息对象
	 */
	public CustomerBasicInfo selectByNumber(String credentialsNumber);

	/**
	 * 删除客户基本信息（同时删除历史信息)
	 * 
	 * @param customerBasicInfoId 客户基本信息id
	 * @return true:成功。false:失败
	 */
	public Message deleteCustomerBasicInfo(int customerBasicInfoId);

	/**
	 * 修改客户基本信息
	 * zhangman
	 * 
	 * @param customerBasicInfo
	 * @return 借款服务申请id
	 */
	public Message updateCustomerBasicInfoForSystem(CustomerBasicInfo customerBasicInfo);

	/**
	 * 新增一个借款人
	 * 
	 * @param credentialsNumber
	 * @param creditapplicationId
	 * @param customerConsultId
	 * @param borrowerServiceAppId
	 * @return
	 */
	public Message addNewBorrower(String credentialsNumber, String creditapplicationId,Long consultPoolId, Long customerConsultId,
			Long borrowerServiceAppId) throws Exception;

	// 郝强上传验证同一笔申请身份证号不同
	public Message checkForCredentialsNumber(String credentialsNumber, Long creditapplicationId);

	/**
	 * 郝强上传保存借款人并增加循环贷及打折验证
	 * 
	 * @param credentialsNumber
	 * @param creditapplicationId
	 * @param customerConsultId
	 * @param borrowerServiceAppId
	 * @return
	 */

	public Message addNewBorrowerIncludeRevolvingAndDiscount(String credentialsNumber, String creditapplicationId,Long consultPoolId,
			Long customerConsultId, Long borrowerServiceAppId) throws Exception;

    /**
     *增加借款人到申请单
     *  @param  borrowerInfo         借款人信息
          */
    public Message addNewBorrowerToCreditAppplication(BorrowerInfo borrowerInfo) throws Exception;


    /**
     *获取黑名单列表
     * @return         List<CustomerBasicInfo>     黑名单列表客户
     * */
    public   List<CustomerBasicInfo> getBlackListCustomerInfoList();

    /**
     * 模拟贷后放款通知消息
     * @param receiveCreditObjs
     * @return
     */
	Message updateStatusByReceiveCreditMsg(
			List<ReceiveCreditObj> receiveCreditObjs) throws ParseException;

	/**
	 * 查询32位业务编号
	 * @param businessNumber
	 * @return
	 */
	String selBusinessNumberByUUID(String businessNumber);
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