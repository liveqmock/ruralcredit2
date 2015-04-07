package com.creditease.rc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.BorrowerService;
import com.creditease.rc.domain.BorrowerServiceApp;
import com.creditease.rc.domain.Familymember;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.BorrowerInfo;
import com.creditease.rc.vo.BorrowerInfoVo;
import com.creditease.rc.vo.BorrowerServiceAppVo2;
import com.creditease.rc.vo.BorrowerServiceVo;
import com.creditease.rc.vo.CreditApplicationSearch;

/**
 * 借款服务申请
 * 
 * @author zhangman
 * 
 */
public interface IBorrowerServiceAppService {
	/**
	 * 添加或修改
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
	 * @param customerBasicId 客户信息id
	 * @return 借款服务申请记录
	 */
	public BorrowerServiceApp selectByCustomerId(int customerBasicId);

	/**
	 * 按id 删除
	 * 
	 * @param borrowerServiceAppId
	 * @return
	 */
// public boolean deleteBorrowerServiceApp(int borrowerServiceAppId);

	/**
	 * 
	 * @param creditapplicationId
	 *            小组信贷申请单id
	 * @return 借款服务申请列表
	 */
	public List<BorrowerInfoVo> selectBorrowSerivceApp(int creditapplicationId);

	/**
	 * 担保人信息列表
	 * 
	 * @param creditApplicationId 信贷申请主键
	 * @return 担保人信息
	 */
	List<BorrowerServiceVo> quertyGuaranorList(Long creditApplicationId);

	/**
	 * 判断小组长
	 * 
	 * @param borrowerServiceAppId 借款服务申请id
	 * @param creditApplicationId 信贷申请id
	 */
	public void choseLeader(Integer borrowerServiceAppId, Integer creditApplicationId);

	/**
	 * 删除个人申请（更新删除个人申请标识）
	 * 
	 * @author qianghao
	 * @param borrowerServiceAppId 借款服务申请id
	 * @return true:成功，false：失败
	 */
	public boolean updateValidStateOfBorrowerServiceApp(Integer borrowerServiceAppId);

	/**
	 * 按身份证号 查询
	 * 
	 * @param credentialsNumber 借款服务申请 id
	 * @return 借款服务申请记录
	 */
	public String selectByNumber(String credentialsNumber);

	/**
	 * 按各种条件查询
	 * 
	 * @param borrowerInfoVo 借款人申请拓展类
	 * @return 列表
	 */
	public List<BorrowerServiceApp> selectByBorrowerInfoVo(BorrowerInfoVo borrowerInfoVo);

	/**
	 * 新增担保人
	 * 
	 * @param borrowerService 担保人申请
	 * @return 是否添加担保人成功
	 */
	boolean addNewGuaranor(BorrowerService borrowerService);
	/**
	 * @param borrowerServiceApp 信贷申请对象
	 * @param type 类型
	 * @return 客户基本信息id
	 */
	
	public int addOrUpdateCustomer(BorrowerServiceApp borrowerServiceApp,String type);
	/**
	 * @author manzhang
	 * @param familymember 家庭成员
	 * @return 配偶信息
	 */
	public Familymember selectPartner(Familymember familymember);
	
	/**
	 * 导出借款人详细信息
	 * @return List<HashMap> 
	 */
	public List<HashMap> exportExcelBorrower();

	/**
	 * 查询 客户信息导出列表
	 * @param map 参数
	 * @return 分页列表
	 */
	public Pagination exportCustomerInformationtList(Map<String, Object> map,Pagination pagination);

	/**
	 * 导出 客户信息导出列表
	 * @param map 参数
	 * @return  列表
	 */
	public List<Map> exportCustomerInformationt(Map map);
	/**
     * 查询所有借款申请
     * @param borrowerService 借款申请
     * @return list
     */
    public List<Map> select(BorrowerService borrowerService);
    
    /**
	 * 按各种条件查询
	 * 
	 * @param borrowerInfoVo 借款人申请拓展类
	 * @return 列表
	 */
	public List<BorrowerServiceApp> selectByBorrowerInfo(BorrowerInfoVo borrowerInfoVo);
	/**
	 *  修改借款申请
	 *  zhangman
	 * @param borrowerServiceApp
	 * @return 借款服务申请id
	 */
	public Message updateborrowerServiceApp(BorrowerServiceApp borrowerServiceApp);
	/**
     *
     * @param borrowerServiceApp
     * @param borrowerServiceAppId
     * @return
     */
    public Message addNewBorrower(String credentialsNumber, String creditapplicationId,BorrowerServiceApp borrowerServiceApp, Long borrowerServiceAppId) throws Exception;

    /**
     *        新增借款人申请   *        *       *
     *
     * @param borrowerServiceApp 借款人申请初始信息      *
     * * @param borrowerInfo   借款人信息
     * @return
     */
    public Message addNewBorrower(BorrowerServiceApp borrowerServiceApp, BorrowerInfo borrowerInfo) throws Exception;



    /**
	 * 查询与该电话有关担保人的借款结清次数
	 * @author luohongjie
	 */
	public List<CreditApplicationSearch> selectBorrowerServiceByTel(String telphone);
	
	/**
	 * 查询与该电话有关的借款人的借款结清次数
	 * @return List<CustomerConsult>
	 */
	public List<CreditApplicationSearch> selectBorrowerAuditListByTel(String telPhone);

	public Message checkBorrowerSave(Long creditapplicationId);
	
	/**
	 * 根据信贷申请id查询借款人信息
	 * @param creditapplicationId
	 * @return
	 */
	public List<BorrowerServiceAppVo2> queryBorrowerInfo(Long creditapplicationId);
}
