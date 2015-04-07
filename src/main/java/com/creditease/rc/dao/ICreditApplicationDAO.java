package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.BorrowerServiceApp;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.LocalChgReturnTypeRequest;
import com.creditease.rc.domain.LocalClientApplyRequest;
import com.creditease.rc.domain.LocalReturnDTO;
import com.creditease.rc.domain.OtherFileUpload;
import com.creditease.rc.domain.OverDueObj;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.BorrowerServiceVo;

/**
 * 
 * @author zhangman
 * 
 */
public interface ICreditApplicationDAO extends IBaseDao {

	/**
	 * 添加小组申请
	 * 
	 * @param creditApplication 信贷申请对象
	 * @return 信贷申请id
	 */
	public int addCreditApplication(CreditApplication creditApplication);

	/**
	 * 
	 * @param creditApplication 信贷申请对象
	 * @return 信贷申请对象
	 */
	public CreditApplication selectCreditApplicationById(CreditApplication creditApplication);

	/**
	 * 按主键查询
	 * 
	 * @param creditApplicationId 信贷申请id
	 * @return 信贷申请对象
	 */
	public CreditApplication selectById(Long creditApplicationId);

	/**
	 * 模糊查询 分页显示
	 * 
	 * @param creditApplication 信贷申请对象
	 * @param pagination 分页对象
	 * @return 信贷申请分页显示
	 */
	public Pagination selectCreditApplicationList(CreditApplication creditApplication, Pagination pagination);

	/**
	 * 修改信贷申请
	 * 
	 * @param creditApplication 信贷申请对象
	 * @return 修改的行数
	 */

	public int updateCreditApplication(CreditApplication creditApplication);

	/**
	 * 批量修改信贷申请
	 * 
	 * @param creditApplication 信贷申请对象
	 * @return 修改的行数
	 */

	public int updateBatchCreditApplication(List<CreditApplication> creditApplication);

	/**
	 * 按id删除
	 * 
	 * @param creditapplicationId 信贷申请id
	 * @return 删除了几条
	 */
	public int deleteById(int creditapplicationId);

	/**
	 * 按 小组编号查询
	 * 
	 * @param groupNumber 小组编号
	 * @return 信贷申请对象
	 */
	public CreditApplication selectByGroupNumber(String groupNumber);

	/**
	 * 
	 * checkstyle
	 * 
	 * @author wyf
	 * @param creditApplication
	 * @return
	 *         int
	 */
	public int submitAuditing(CreditApplication creditApplication);

	/**
	 * 按上传文件名查询借款人服务信息
	 * 
	 * @author yifengwang
	 * @param searchMap 查询条件
	 * @return 2012-12-24下午02:38:11
	 */
	public BorrowerServiceApp queryBspForUploadFile(Map searchMap);

	/**
	 * 
	 * @author yifengwang
	 * @param otherFileUploads 2012-12-24下午02:36:11
	 */
	public void addOtherFileUpload(List<OtherFileUpload> otherFileUploads);

	/**
	 * 
	 * @author yifengwang
	 * @param caId 小组ID
	 * @param pagination 分页对象
	 * @return 2012-12-24下午02:36:22
	 */
	public Pagination queryUploadFileList(Integer caId, Pagination pagination);

	/**
	 * 查询小组中上传文件列表
	 * checkstyle
	 * 
	 * @author wyf
	 * @param caId
	 * @param fileType
	 * @return
	 *         List<OtherFileUpload>
	 */
	public List<OtherFileUpload> queryUploadFiles(Integer caId, String fileType);

	/**
	 * 
	 * @author yifengwang
	 * @param caId 小组ID
	 * @return 2012-12-24下午02:36:25
	 */
	public List<String> queryUploadFileList(Integer caId);

	/**
	 * 
	 * @author yifengwang
	 * @param uploadFileName 2012-12-24下午02:36:29
	 */
	public void deleteOtherFileUpLoadByUploadFileName(String uploadFileName);

	/**
	 * 
	 * @param pagination 分页条件
	 * @param searchValue 查询条件
	 * @return 分页列表
	 */
	public Pagination select(Pagination pagination, Map searchValue);

	/**
	 * 
	 * @param pagination 分页条件
	 * @param searchValue 查询条件
	 * @return 分页列表
	 */
	public Pagination selectForLoan(Pagination pagination, Map searchValue);

	/**
	 * 
	 * @param pagination 分页条件
	 * @param searchValue 查询条件
	 * @return 分页列表
	 */
	public Pagination selectForFinanceLoan(Pagination pagination, Map searchValue);

	/**
	 * 
	 * @param creditapplicationId 信贷Id
	 * @return 该小组下组员人数
	 */
	public int getMembers(int creditapplicationId);

	/**
	 * 
	 * @param creditapplicationId 信贷id
	 * @return 该小组贷款总金额
	 */
	public double getGroupAppTotal(int creditapplicationId);

	/**
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
	 * 删除借款姓名
	 * checkstyle
	 * 
	 * @author wyf
	 * @param creditApplication
	 * @return
	 *         int
	 */
	public int updateName(CreditApplication creditApplication);

	/**
	 * 查金额
	 * checkstyle
	 * 
	 * @author wyf
	 * @param creditapplicationId
	 * @return
	 *         CreditApplication
	 */
	public CreditApplication selectAmount(int creditapplicationId);

	/**
	 * 
	 * @param customerBasicId id
	 * @return CreditApplication
	 */
	public CreditApplication selectCreditByCustomerId(Integer customerBasicId);

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
	 * checkstyle
	 * 
	 * @author wyf
	 * @param credentialsNumber
	 * @return
	 *         List<BorrowerServiceVo>
	 */
	public List<BorrowerServiceVo> selectBorrowerFamilyByID(String credentialsNumber);

	/**
	 * 查询入户起已满30天未放款的且能执行回退到入户的业务
	 * checkstyle
	 * 
	 * @author wyf
	 * @return
	 *         List<CreditApplication>
	 */
	public List<CreditApplication> selectUnPayOver30Days();

	/**
	 * 按信贷员id分组
	 * 
	 * @return List<CreditApplication>
	 */
	public List<CreditApplication> selectGroupByLoanOperaterId();

	/**
	 * 按信贷员 id 修改 部门信息
	 * 
	 * @param creditApplication CreditApplication
	 * @return int
	 */
	public int updateByLoanOperaterId(CreditApplication creditApplication);

	/**
	 * 按条件 查询
	 * 
	 * @param creditApplication CreditApplication
	 * @return CreditApplication
	 */
	public List<CreditApplication> selectCreditApp(CreditApplication creditApplication);

	/**
	 * @author 郝强
	 * @param creditapplicationId 信贷申请主键
	 * @return 销售系统申请编号
	 */
	public String queryUUid(Long creditapplicationId);

	/**
	 * @author 郝强
	 * @param creditapplicationId 信贷申请主键
	 * @return LocalClientApplyRequest 本地定义的ClientApplyRequest用于调用clientApply接口
	 */
	public LocalClientApplyRequest queryLocalClientApplyRequest(Long creditapplicationId);

	/**
	 * 
	 * checkstyle
	 * 
	 * @author wyf
	 * @param receivedRecordIdList
	 * @return
	 *         List<LocalReturnDTO>
	 */
	public List<LocalReturnDTO> queryLocalReturnDTOList(List<Long> receivedRecordIdList);

	/**
	 * @author 郝强
	 * @param creditapplicationIdList 信贷申请主键List
	 * @return List<LocalApplyDTO>
	 */
	public List<String> queryUUidList(List<Long> creditapplicationIdList);

	/**
	 * @author 郝强
	 * @param creditapplicationId 信贷申请主键
	 * @return LocalChgReturnTypeRequest
	 */
	public LocalChgReturnTypeRequest queryLocalChgReturnTypeRequest(Long creditapplicationId);

	/**
	 * 通过UUIDList 查询相应的申请单
	 * checkstyle
	 * 
	 * @author wyf
	 * @param UUIdList
	 * @return
	 *         List<CreditApplication>
	 */
	public List<OverDueObj> queryCreditApplicationVoListByUUIdList(List<String> UUIdList);

	/**
	 * 查询当前客户经理的可赠与的申请单
	 * checkstyle
	 * 
	 * @author wyf
	 * @param creditApplication
	 * @return
	 *         List<Object[]>
	 */
	public List<Object[]> selectBestowalCreditApplicationListByCreditApplication(CreditApplication creditApplication);

	/**
	 * 分页查询可赠与的申请单
	 * checkstyle
	 * 
	 * @author wyf
	 * @param creditApplication
	 * @param pagination
	 * @return
	 *         Pagination
	 */
	public Pagination selectBestowalCreditApplicationByPagination(CreditApplication creditApplication,
			Pagination pagination);

	/**
	 * 更新客户经理姓名和id，用于数据赠与功能
	 * checkstyle
	 * 
	 * @author wyf
	 * @param creditApplication
	 * @return
	 *         Integer
	 */
	public Integer updateLoanOfficerByCreditApplication(CreditApplication creditApplication);

	/**
	 * 查询系统中所有的客户经理
	 * checkstyle
	 * 
	 * @author wyf
	 * @param creditApplication
	 * @return
	 *         List
	 */
	public List querySysloanOfficerList(CreditApplication creditApplication);

	/**
	 * @author 郝强
	 * @param creditapplication  信贷申请对象
	 * @return CreditApplication
	 */
	public CreditApplication selectCreditApplicationByIdIsTrue(CreditApplication creditapplication);

	
	/**  以下用于循环贷   **/
	/**
	 * 按照借款人 身份证号 查询 还款完成的 申请
	 */
	public List<Map> selectFinishCreditApplicationIdByBorrower(String credentialsNumber);
	/**
	 *  按照借款人配偶身份证号 查询 还款完成的 申请
	 * @param idNumber
	 * @return
	 */
	public List<Map> selectFinishCreditApplicationIdByPartner(String idNumber);
	/**
	 * 按照借款人 以及配偶身份证号 查询 还款完成的 申请
	 * @param ids
	 * @return
	 */
	public List<Map> selectFinishCreditApplicationIdByBoth(Map<String, String> ids);
	/**
	 * 按照借款人身份证号 以及配偶身份证号 查询 需要更改 循环贷标记的 申请
	 * @param ids
	 * @return
	 */
	public List<Map> selectCreditApplicationIdByBoth(Map<String, String> ids);
	/**
	 * 按照借款人身份证号  查询 需要更改 循环贷标记的 申请
	 * @param ids
	 * @return
	 */
	public List<Map> selectCreditApplicationIdByBorrower(String credentialsNumber);
	/**
	 * 修改一笔 标记为循环贷
	 * @param creditapplicationId
	 * @return
	 */
	public int updateRevolvingCredit(Integer creditapplicationId);
	/**
	 * 修改多笔标记为循环贷
	 * @param creditapplicationIds
	 * @return
	 */
	public int updateRevolvingCreditBatch(String creditapplicationIds);
	/**
	 * 按照姓名 身份证 查询信息
	 * @param map
	 * @return
	 */
	public Pagination creditApplicationSearch(Pagination pagination,Map map);
	
	/**
	 * 按照客户经理id查询业务
	 * @param loanOfficerId
	 * @return
	 */
	public List<CreditApplication> selectByLoanOfficer(Integer loanOfficerId);

	/**
	 * 更改为非循环贷
	 * @author manzhang
	 * @param creditapplicationId
	 * @return
	 */
	public int updateRevolvingCreditFalse(Integer creditapplicationId);
	


	/**
	 * @author hongjieluo
     * 根据信贷申请ID获取年化利率
     * @param creditApplicationId
     * @return
     */
	public Double getAnnualizedRateById(int creditApplicationId);


	/**
	 * 按咨询池查询信贷申请
	 * @author hongjieluo
	 * @param consultPoolId 咨询池id
	 * @return 申请列表
	 */
	List<CreditApplication> selectCreditByconsultPool(Long consultPoolId);
	
    /**
     * 获取全部产品类型
     */
    public List selectProInfoAll(Map map);

    /**
     * 保存参审操作记录
     */
    public Long saveParticipateApprove(Map m);

    /*
    获取信贷申请参审人数
     */
    public Integer getParticateApproveCount(Integer creditApplicationId);

    /*
    更新城市参审状态
     */
    public Integer updateCityParticipate(Integer creditApplicationId,String yesOrNo);

    /*
    获取信贷申请：城市参审情况、审批情况（营业部经理、风险经理
     */
    public List<Integer> getParticipateAndApproveInfo(Integer creditApplicationId);

    /*
    获取前两级节点审批结果
     */
    public List<Integer> getTop2ApproveInfo(Integer creditApplicationId);

    /*
    更新区域经理/区域风险经理参审状态
     */
    public Integer updateCityParticipatePerman(Integer creditApplicationId,String yesOrNo);

    /*
    审批通过状态下，回退操作清除审批金额
     */
    public Integer updateAmountByCreditApplicationId(Long creditApplicationId);
/*
 * 同步申请表中的放款时间
 */
	Integer updateSignagreementDate(CreditApplication creApplication);

    /*
    更新打折标记
     */
    public Integer updateDiscountFlagById(Integer creditApplicationId,String discountFlag);


    /**
     * create by ygx
     * 2014-10-14
     * get product id list
     */
    public List<String> getProductIdList();

    /**
     *
     通过是否试点营业部 控制借款人婚姻状况 共借人关系
     * @param creditApplicationId
     * @return
     */
    public CreditApplication getIndentityInfoByCreditApplicationId(Integer creditApplicationId);

/**
     * 根据信贷申请单ID查询同步综合信贷信息
     * @param creditApplicationId
     * @return
     */
    public Map<String,Object> queryForZh(Long creditApplicationId);
    
    /**
     * 根据根据信贷申请单ID查询当前状态
     * @param creditApplicationId
     * @return
     */
    public String queryStatus(Long creditApplicationId);

    /**
     *
     通过业务单号查询数量
     * @param businessNumber
     * @return
     */
    public int queryCountByBussinessNumber(String businessNumber);
    
    /**
     * 根据信贷申请id更新放款日期
     * @param application
     */
   void updataSignagreementDate(CreditApplication application);
}

