package com.creditease.rc.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.BorrowerServiceApp;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.OtherFileUpload;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.BorrowerServiceVo;

/**
 * 操作小组申请表
 * 
 * @author zhangman
 * 
 */
public interface ICreditApplicationService {

	/**
	 * 
	 * @param creditApplication
	 *            信贷申请对象
	 * @return 信贷申请id
	 */
	public int addCreditApplication(CreditApplication creditApplication);

	/**
	 * 
	 * @param creditApplication
	 *            信贷申请对象
	 * @return 信贷申请id
	 */
	public Message updateCreditApplication(CreditApplication creditApplication);

	/**
	 * 
	 * @param creditApplication
	 *            信贷申请对象
	 * @return 信贷申请id
	 */
	public Message updateCreditApplicationForAccount(CreditApplication creditApplication);

	/**
	 * 关单
	 * 
	 * @param creditApplication
	 *            信贷申请对象
	 * @return 信贷申请id
	 */
	public Message updateClose(CreditApplication creditApplication);

	/**
	 * 
	 * @param creditapplication
	 *            小组信贷申请对象
	 * @return 小组信贷申请信息
	 */
	public CreditApplication selectCreditApplication(CreditApplication creditapplication);

	/**
	 * 
	 * @param creditapplication
	 *            小组信贷申请对象
	 * @return 小组信贷申请信息
	 */
	public CreditApplication selectCreditApplicationForConfirm(CreditApplication creditapplication);

	/**
	 * 
	 * @param creditapplicationId
	 *            小组信贷申请id
	 * @return 小组信贷申请信息
	 */
	public CreditApplication selectById(int creditapplicationId);

	/**
	 * 按条件模糊查询 分页显示
	 * 
	 * @param creditApplication
	 *            小组信贷申请对象
	 * @param fuzzyValue
	 *            模糊查询条件
	 * @param pagination
	 *            分页条件
	 * 
	 * @param session HttpSession
	 * @return 小组信贷申请对象 分页列表
	 */
	public Pagination selectCreditApplicationList(CreditApplication creditApplication, String fuzzyValue,
			String auditStatusArray, Pagination pagination, HttpSession session);

	/**
	 * * 按条件模糊查询 分页显示
	 * 
	 * @param creditApplication
	 *            小组信贷申请对象
	 * @param fuzzyValue
	 *            模糊查询条件
	 * @param pagination
	 *            分页条件
	 * @param session HttpSession
	 * 
	 * @param sort 排序列
	 * @param order 顺序
	 * @return 小组信贷申请对象 分页列表
	 */
	public Pagination selectCreditApplicationForLoan(CreditApplication creditApplication, String fuzzyValue,
			Pagination pagination, HttpSession session, String sort, String order);

	/**
	 * 按条件模糊查询 分页显示
	 * 
	 * @param creditApplication
	 *            小组信贷申请对象
	 * @param fuzzyValue
	 *            模糊查询条件
	 * @param pagination
	 *            分页条件
	 * @param session HttpSession
	 * @return 小组信贷申请对象 分页列表
	 */
	public Pagination selectCreditApplicationForFinanceLoan(CreditApplication creditApplication, String fuzzyValue,
			Pagination pagination, HttpSession session);

	/**
	 * 
	 * @param creditApplication
	 *            小组信贷申请对象
	 * @param account 账户
	 * @param returnAccount 收款账户
	 * @return 小组信贷申请对象
	 */
	public CreditApplication addOrUpdateCreditApplication(CreditApplication creditApplication, AccountInfo account,
			AccountInfo returnAccount) throws Exception;

	/**
	 * 按id删除
	 * 
	 * @param creditapplicationId
	 *            小组信贷申请id
	 * @return 删除的行数
	 */
	public int deleteById(int creditapplicationId);

	/**
	 * 按 小组编号查询
	 * 
	 * @param groupNumber
	 *            小组编号
	 * @return 小组信贷申请对象
	 */
	public CreditApplication selectByGroupNumber(String groupNumber);

	/**
	 * 删除小组申请（即更新删除小组标识） 自动判断能不能删除，如若删除是完全删除还是更新标识位
	 * 
	 * @author qianghao
	 * @param creditapplicationId
	 *            小组信贷申请id
	 * @return true：成功，false:失败
	 */
	public boolean updateValidStateOfCreditApplication(Integer creditapplicationId);

	/**
	 * (作废申请功能)删除小组申请（即更新删除小组标识） 自动判断能不能删除，如若删除是完全删除还是更新标识位
	 * 
	 * @author zhangman
	 * @param creditapplicationId
	 *            小组信贷申请id
	 * @return message
	 */
	public Message updateCancel(Integer creditapplicationId);

	/**
	 * 提交小组准备审核
	 * 
	 * @param creditApplication 小组
	 * @param creditapplicationId
	 *            小组id
	 * @param auditStatus
	 *            审核标记
	 * @return true:成功，false：失败
	 */
	public boolean changeAuditing(CreditApplication creditApplication, int creditapplicationId, String auditStatus);

	/**
	 * 撤销接口
	 * 
	 * @param creditApplication 小组
	 * @param creditapplicationId
	 *            小组id
	 * @param auditStatus
	 *            审核标记
	 * @return true:成功，false：失败
	 */
	public Message loanregistRevocation(CreditApplication creditApplication, int creditapplicationId, String auditStatus);

	/**
	 * 按上传文件名查询借款人服务信息
	 * 
	 * @author wyf
	 * @param searchMap 查询条件
	 * @return BorrowerServiceApp s
	 */
	public BorrowerServiceApp queryBspForUploadFile(Map searchMap);

	/**
	 * 保存上传文件信息
	 * 
	 * @author wyf
	 * @param uploadFileList 上传文件
	 * @param uploadFileTypeList 上传文件
	 * @param originalFileList 上传文件
	 * @param fileType
	 * @param caId sdf
	 * @throws Exception
	 */
	public void addOtherFileUpload(Integer caId, String[] uploadFileList, String fileType, String[] uploadFileTypeList,
			String[] originalFileList) throws Exception;

	/**
	 * 查询小组中上传文件列表
	 * 
	 * @author wyf
	 * @param caId 小组编号
	 * @param pagination 分页对象
	 * @return Pagination 分页对象
	 * @throws
	 */
	public Pagination queryUploadFileList(Integer caId, Pagination pagination);

	/**
	 * 查询小组中上传文件列表
	 * 
	 * @author zhangman
	 * @param caId 外间编号
	 * @param fileType 类型
	 * @return List<OtherFileUpload>列表
	 */
	public List<OtherFileUpload> queryUploadFiles(Integer caId, String fileType);

	/**
	 * 查询小组所有文件名
	 * 
	 * @author yifengwang
	 * @param caId 小组编号
	 * @return 2012-12-7下午03:56:13
	 */
	public List<String> queryUploadFileList(Integer caId);

	/**
	 * 删除上传文件
	 * 
	 * @author wyf
	 * @param file 文件
	 * @return boolean ifSuccess
	 */
	public boolean delUploadFile(File file);

	/**
	 * 财务付款列表查询
	 * 
	 * @param pagination 分页条件
	 * @param creditApplication 查询条件
	 * @return pagination
	 */
	public Pagination selectFinanceLoan(Pagination pagination, CreditApplication creditApplication);

	/**
	 * 根据收款登记ID查询信贷审批
	 * 
	 * @param receiveRecordId 收款登记表ID
	 * @return CreditApplication
	 */
	public CreditApplication selectCreditApplicationByReceiveRecordId(Integer receiveRecordId);

	/**
	 * 查金额
	 * 
	 * @param creditapplicationId
	 * @return CreditApplication申请
	 */
	public CreditApplication selectAmount(int creditapplicationId);

	/**
	 * 提交申请
	 * 
	 * @param creditapplicationId
	 * @param auditStatus
	 */
	public void submitCreditApplication(int creditapplicationId, String auditStatus);

	/**
	 * CM有无上传文件
	 * 
	 * @author wyf
	 * @param clientId
	 * @return int
	 * @throws Exception yc
	 * 
	 */
	public int getImgAmount(String clientId) throws Exception;

	/**
	 * 生成业务编码
	 * 
	 * @author wyf
	 * @Description:
	 * @param creditApplication
	 * @return String
	 * @throws Exception
	 * 
	 */
	public String createGroupNumber(CreditApplication creditApplication) throws Exception;

	/**
	 * 变更产品时更新小组编号
	 * 
	 * @author wyf
	 * @param creditApplication
	 * @return String
	 */
	public String updateGroupNumber(CreditApplication creditApplication);

	/**
	 * @author zhangman
	 * @param customerBasicId id
	 * @return CreditApplication
	 */
	public CreditApplication selectCreditByCustomerId(Integer customerBasicId);

	/**
	 * 
	 * @param creditapplicationId 信贷申请对象
	 * @param creditApplication
	 * @param auditStatus
	 * 
	 * @return 消息
	 */
	public Message updateSubmit(CreditApplication creditApplication, Integer creditapplicationId, String auditStatus);

	/**
	 * 按客户咨询查询信贷申请
	 * 
	 * @param customerConsultId 咨询id
	 * @return 申请列表
	 */
	public List<CreditApplication> selectCreditByconsult(Long customerConsultId);

	/**
	 * 查询借款人 配偶姓名 身份证号
	 * 
	 * @param creditapplicationId 申请id
	 * @return 姓名 身份证map
	 */
	public List<BorrowerServiceVo> selectBorrowerFamily(int creditapplicationId);

	/**
	 * 查询借款人 配偶姓名 身份证号
	 * 
	 * @param accountInfoId 账户id
	 * @return 姓名 身份证map
	 */
	public List<BorrowerServiceVo> selectBorrowerFamilyByAccount(int accountInfoId);

	/**
	 * 查询借款人 配偶姓名 身份证号
	 * 
	 * @param credentialsNumber 身份证
	 * @return 姓名 身份证map
	 */
	public List<BorrowerServiceVo> selectBorrowerFamilyByID(String credentialsNumber);

	/**
	 * 定时调度，已审批但从入户起已满30天未放款的业务，重回入户调查环节
	 * liuli 2013-05-09
	 */
	public void updateToInvestigate();

	/**
	 * 根据相应的creditApplication对象回退状态到入户调查环节
	 * liuli 2013-05-10
	 * 
	 * @param creditApplication
	 */
	public void updateCaToInvestigate(CreditApplication creditApplication);

	/**
	 * SMP同步
	 * 
	 * @param application CreditApplication
	 * @return Message
	 */
	public Message updateSmp(CreditApplication application);

	/**
	 * SMP同步
	 * 
	 * @return Message
	 */
	public Message updateSmpAll();

	/**
	 * 客户放弃或拒绝
	 * 
	 * @author wyf
	 * @param creditapplicationId
	 * @return
	 *         Message
	 */
	public boolean updateRefuse(Integer creditapplicationId, String flag) throws Exception;

	/**
	 * @author 郝强
	 * @param creditApplication 信贷申请对象
	 * @return CreditApplication
	 */
	public CreditApplication selectCreditApplicationIsTrue(CreditApplication creditApplication);
	
	/**
	 * 设置循环贷 标记
	 * @param creditapplicationId
	 * @return
	 */
	public Message updateRevolving(Integer creditapplicationId);
	/**
	 * 一期数据导入的 时候更改 二期系统内 进入"还款中" 或  还款完成状态的申请 循环贷标记
	 * @param borrowerCredentialsNumber
	 * @param mateCredentialsNumber
	 * @return
	 */
	public Message updateRevolvingForImport(String  borrowerCredentialsNumber,  String mateCredentialsNumber);
	/**
	 * 按照姓名 身份证 查询信息
	 * @param map
	 * @return
	 */
	public Pagination selectCreditApplicationSearch(Pagination pagination,String name,String identityNumber);

	/**
	 * 按咨询池查询信贷申请
	 * @author hongjieluo
	 * @param consultPoolId 咨询池id
	 * @return 申请列表
	 */
	List<CreditApplication> selectCreditByconsultPool(Long consultPoolId);
	/**
	 * @author hongjieluo
     * 根据信贷申请ID获取年化利率
     * @param creditApplicationId
     * @return
     */
    public Double getAnnualizedRateById(int creditApplicationId);

    /**
     * 获取产品信息
     */
    public List selectProInfoAll(String authList);

    /**
     * 保存参审操作记录
     */
    public Message saveParticipateApprove(CreditApplication application);

    /*修改信贷申请城市参审状态*/
    public Integer updateCityParticipate(Integer creditApplicationId, String yesOrNo);

    /**
     * 同步申请列表中的放款时间
     * @param creditapplicationId
     * @return
     */
	public Integer updateSignagreementDate(CreditApplication creditApplication);

    /*更新打折标记*/
    public Integer updateDiscountFlagById(Integer creditApplicationId, String discountFlag);


    /**
     * create by ygx
     * 2014-10-14
     * get product id list
     */
    public List<String> getProductIdList();

    /**
     * create by ygx
     * 2014-10-14
     * get product id list
     */
    public CreditApplication getIndentityInfoByCreditApplicationId(Integer creditApplicationId) ;
    
    
    /**
     * 根据根据信贷申请单ID查询当前状态
     * @param creditApplicationId
     * @return
     */
    public String queryStatus(Long creditApplicationId);

	/*
    更新区域经理/区域风险经理参审状态
     */
	public Integer updateCityParticipatePerman(Integer creditApplicationId,String yesOrNo);
    /**
     *
     通过业务单号查询数量
     * @param businessNumber
     * @return
     */
    public int queryCountByBussinessNumber(String businessNumber);
}
