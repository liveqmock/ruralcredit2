package com.creditease.rc.dao.impl;

import com.creditease.core.security.Authorization;
import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.security.User;
import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.ICreditApplicationDAO;
import com.creditease.rc.domain.*;
import com.creditease.rc.framework.dao.impl.BaseDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.BorrowerServiceVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

/**
 * 
 * @author zhangman
 * 
 */
@Repository
public class CreditApplicationDAO extends BaseDao implements ICreditApplicationDAO {

	@Resource
	private BaseDao baseDao;

	@Resource
	private Authorization authorization;

	@Override
	public int addCreditApplication(CreditApplication creditApplication) {
		// 状态设置
		// 状态设置 00 未提交
		creditApplication.setAuditStatus(Constants.STATE_0);
		// 创建时间
		creditApplication.setCreateDate(new Date());
		// 创建经理 id 和姓名
		creditApplication.setCreateLoanOfficerId(creditApplication.getLoanOfficerId());
		creditApplication.setCreateLoanOfficerName(creditApplication.getLoanOfficerName());

		Integer id = (Integer) baseDao.insertObject("CREDITAPPLICATION.insert", creditApplication);
// Authorization authorization = env.getAuthorizationService();
		if (creditApplication.getLoanOfficerId() != null
				&& !("0".equalsIgnoreCase(creditApplication.getLoanOfficerId()))) {
			try {
				authorization
						.createAuth(CreditApplication.class, id,
								Integer.parseInt(creditApplication.getLoanOfficerId()),
								Integer.parseInt(Constants.SYSTEM_SIGN));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return id;
	}

	@Override
	public Pagination selectCreditApplicationList(CreditApplication creditApplication, Pagination pagination) {
		pagination = baseDao.queryForPaginatedList("CREDITAPPLICATION.selectAll", "CREDITAPPLICATION.countByPage",
				creditApplication, pagination.getStartResult(), pagination.getPageSize());
		List<CreditApplication> creditApplicationVoList = pagination.getRows();
		List<CreditApplication> creditApplicationVoListTemp = new ArrayList<CreditApplication>();
		for (CreditApplication creditApplicationVo : creditApplicationVoList) {
			int creditapplicationId = creditApplicationVo.getCreditapplicationId();
			int countMenber = (Integer) baseDao.queryUnique("BORROWERSERVICE.countByCreditapplicationId",
					creditapplicationId);
			creditApplicationVo.setGroupMemberCount(countMenber);
			creditApplicationVoListTemp.add(creditApplicationVo);
		}
		pagination.setItems(creditApplicationVoListTemp);
		return pagination;
	}

	@Override
	public CreditApplication selectCreditApplicationById(CreditApplication creditApplication) {
		return (CreditApplication) baseDao.queryUnique("CREDITAPPLICATION.selectById", creditApplication);
	}

	@Override
	public CreditApplication selectCreditApplicationByIdIsTrue(CreditApplication creditApplication) {
		return (CreditApplication) baseDao.queryUnique("CREDITAPPLICATION.selectByIdNameIsTrue", creditApplication);
	}

	@Override
	public int updateCreditApplication(CreditApplication creditApplication) {
		return baseDao.update("CREDITAPPLICATION.update", creditApplication);
	}

	@Override
	public int updateBatchCreditApplication(List<CreditApplication> creditApplication) {
		return baseDao.batchUpdate("CREDITAPPLICATION.update", creditApplication);
	}

	@Override
	public CreditApplication selectById(Long creditApplicationId) {
		return (CreditApplication) baseDao.queryUnique("CREDITAPPLICATION.selectByCreditApplicationId",
				creditApplicationId);
	}

	@Override
	public int deleteById(int creditapplicationId) {
		return baseDao.delete("CREDITAPPLICATION.deleteById", creditapplicationId);
	}

	@Override
	public CreditApplication selectByGroupNumber(String groupNumber) {
		return (CreditApplication) baseDao.queryUnique("CREDITAPPLICATION.selectByGroupNumber", groupNumber);
	}

	@Override
	public BorrowerServiceApp queryBspForUploadFile(Map searchMap) {
		return (BorrowerServiceApp) baseDao.queryUnique("BORROWERSERVICE.selectByIdentityCard", searchMap);
	}

	@Override
	public void addOtherFileUpload(List<OtherFileUpload> otherFileUploads) {
		baseDao.batchInsert("otherFileUpload.insertFile", otherFileUploads);
	}

	@Override
	public Pagination queryUploadFileList(Integer caId, Pagination pagination) {
		if (caId != null) {
			return queryForPaginatedList("otherFileUpload.selectByPagination", "otherFileUpload.countByPage", caId,
					pagination.getStartResult(), pagination.getPageSize());
		}
		return null;
	}

	@Override
	public List<OtherFileUpload> queryUploadFiles(Integer caId, String fileType) {
		Map mapVlaue = new HashMap();
		mapVlaue.put("caId", caId);
		mapVlaue.put("fileType", fileType);
		return (List<OtherFileUpload>) queryList("otherFileUpload.selectOtherFiles", mapVlaue);
	}

	@Override
	public void deleteOtherFileUpLoadByUploadFileName(String uploadFileName) {
		baseDao.delete("otherFileUpload.deleteByFileName", uploadFileName);
	}

	@Override
	public List<String> queryUploadFileList(Integer caId) {
		if (caId != null) {
			return (List<String>) queryList("otherFileUpload.selectBycaId", caId);
		}
		return null;
	}

	@Override
	public int submitAuditing(CreditApplication creditApplication) {
		return baseDao.update("CREDITAPPLICATION.updateAuditStatus", creditApplication);

	}

	@Override
	public Pagination select(Pagination pagination, Map searchValue) {
        try {
            User u = SpringSecurityUtils.getCurrentUser();
            searchValue.put("participator_id",u.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
            searchValue.put("participator_id",-999);
        }
        return baseDao.queryForPaginatedList("CREDITAPPLICATION.selectfuzzay", "CREDITAPPLICATION.countByPage",
				searchValue, pagination.getStartResult(), pagination.getPageSize());
	}

	@Override
	public Pagination selectForLoan(Pagination pagination, Map searchValue) {
		return baseDao.queryForPaginatedList("CREDITAPPLICATION.selectByAuditStatus",
				"CREDITAPPLICATION.countByAuditStatus", searchValue, pagination.getStartResult(),
				pagination.getPageSize());
	}

	@Override
	public Pagination selectForFinanceLoan(Pagination pagination, Map searchValue) {
		return baseDao.queryForPaginatedList("CREDITAPPLICATION.selectByFinanceLoan",
				"CREDITAPPLICATION.countByFinanceLoan", searchValue, pagination.getStartResult(),
				pagination.getPageSize());
	}

	@Override
	public double getGroupAppTotal(int creditapplicationId) {
		if (baseDao.queryUnique("BORROWERSERVICE.countGroupAppTotal", creditapplicationId) == null) {
			return 0.0;
		} else {
			return (Double) baseDao.queryUnique("BORROWERSERVICE.countGroupAppTotal", creditapplicationId);
		}

	}

	@Override
	public int getMembers(int creditapplicationId) {
		return (Integer) baseDao.queryUnique("BORROWERSERVICE.countByCreditapplicationId", creditapplicationId);
	}

	@Override
	public Pagination selectFinanceLoan(Pagination pagination, CreditApplication creditApplication) {
// return queryForPaginatedList("CREDITAPPLICATION.selectFinanceLoan", "CREDITAPPLICATION.selectCountFinanceLoan",
// creditApplication, pagination.getStartResult(), pagination.getPageSize());
		return queryForPaginatedList("CREDITAPPLICATION.selectFinanceLoanBak",
				"CREDITAPPLICATION.selectCountFinanceLoanBak", creditApplication, pagination.getStartResult(),
				pagination.getPageSize());
	}

	@Override
	public CreditApplication selectCreditApplicationByReceiveRecordId(Integer receiveRecordId) {
		CreditApplication creditapplication = (CreditApplication) queryUnique(
				"CREDITAPPLICATION.selectCreditApplicationByReceiveRecordId", receiveRecordId);
		if (null == creditapplication) {
			creditapplication = (CreditApplication) queryUnique(
					"CREDITAPPLICATION.selectCreditApplicationByAmountConfirmId", receiveRecordId);
		}
		return creditapplication;
	}

	@Override
	public int updateName(CreditApplication creditApplication) {
		return baseDao.update("CREDITAPPLICATION.updateName", creditApplication);
	}

	@Override
	public CreditApplication selectAmount(int creditapplicationId) {
		return (CreditApplication) baseDao.queryUnique("CREDITAPPLICATION.selectAmount", creditapplicationId);
	}

	@Override
	public CreditApplication selectCreditByCustomerId(Integer customerBasicId) {
		// TODO Auto-generated method stub
		return (CreditApplication) baseDao.queryUnique("CREDITAPPLICATION.selectCreditByCustomerId", customerBasicId);
	}

	@Override
	public List<CreditApplication> selectCreditByconsult(Long customerConsultId) {
		// TODO Auto-generated method stub
		return (List<CreditApplication>) baseDao
				.queryList("CREDITAPPLICATION.selectCreditByconsult", customerConsultId);
	}

	@Override
	public List<CreditApplication> selectCreditByconsultPool(Long consultPoolId) {
		// TODO Auto-generated method stub
		return (List<CreditApplication>) baseDao
				.queryList("CREDITAPPLICATION.selectCreditByconsultPool", consultPoolId);
	}
	
	@Override
	public List<BorrowerServiceVo> selectBorrowerFamily(int creditapplicationId) {
		return (List<BorrowerServiceVo>) baseDao.queryList("CREDITAPPLICATION.selectBorrowerFamily",
				creditapplicationId);
	}

	@Override
	public List<BorrowerServiceVo> selectBorrowerFamilyByID(String credentialsNumber) {
		return (List<BorrowerServiceVo>) baseDao.queryList("CREDITAPPLICATION.selectBorrowerFamilyByID",
				credentialsNumber);
	}

	@Override
	public List<CreditApplication> selectUnPayOver30Days() {
		// TODO Auto-generated method stub
		return (List<CreditApplication>) baseDao.queryList("CREDITAPPLICATION.selectUnPayOver30Days");
	}

	@Override
	public String queryUUid(Long creditapplicationId) {
		// TODO Auto-generated method stub
		return (String) baseDao.queryUnique("CREDITAPPLICATION.queryUUid", creditapplicationId);
	}

	@Override
	public LocalClientApplyRequest queryLocalClientApplyRequest(Long creditapplicationId) {
		// TODO Auto-generated method stub
		return (LocalClientApplyRequest) baseDao.queryUnique("RURAL2CREDIT.queryLocalClientApplyRequest",
				creditapplicationId);
	}

	@Override
	public List<LocalReturnDTO> queryLocalReturnDTOList(List<Long> receivedRecordIdList) {
		// TODO Auto-generated method stub
		return (List<LocalReturnDTO>) baseDao.queryList("RURAL2CREDIT.queryLocalReturnDTOList", receivedRecordIdList);
	}

	@Override
	public List<String> queryUUidList(List<Long> creditapplicationIdList) {
		// TODO Auto-generated method stub
		return (List<String>) baseDao.queryList("CREDITAPPLICATION.queryUUidList", creditapplicationIdList);
	}

	@Override
	public LocalChgReturnTypeRequest queryLocalChgReturnTypeRequest(Long creditapplicationId) {
		// TODO Auto-generated method stub
		return (LocalChgReturnTypeRequest) baseDao.queryUnique("RURAL2CREDIT.queryLocalChgReturnTypeRequest",
				creditapplicationId);
	}

	@Override
	public List<CreditApplication> selectGroupByLoanOperaterId() {
		// TODO Auto-generated method stub
		return (List<CreditApplication>) baseDao.queryList("CREDITAPPLICATION.selectGroupByLoanOperaterId");
	}

	@Override
	public int updateByLoanOperaterId(CreditApplication creditApplication) {
		// TODO Auto-generated method stub
		return baseDao.update("CREDITAPPLICATION.updateByLoanOperaterId", creditApplication);
	}

	@Override
	public List<CreditApplication> selectCreditApp(CreditApplication creditApplication) {
		return (List<CreditApplication>) baseDao.queryList("CREDITAPPLICATION.selectAll", creditApplication);
	}

	@Override
	public List<OverDueObj> queryCreditApplicationVoListByUUIdList(List<String> UUIdList) {
		// TODO Auto-generated method stub
		return (List<OverDueObj>) baseDao.queryList("CREDITAPPLICATION.queryCreditApplicationVoListByUUIdList",
				UUIdList);
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 查询当前客户经理的可赠与的申请单
	 * @param creditApplication
	 * @return
	 *         2013-6-3
	 */
	public List<Object[]> selectBestowalCreditApplicationListByCreditApplication(CreditApplication creditApplication) {
		return (List<Object[]>) baseDao.queryList(
				"CREDITAPPLICATION.selectBestowalCreditApplicationListByCreditApplication", creditApplication);
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 分页查询可赠与的申请单
	 * @param creditApplication
	 * @param pagination
	 * @return
	 *         2013-6-3
	 */
	public Pagination selectBestowalCreditApplicationByPagination(CreditApplication creditApplication,
			Pagination pagination) {
		return this.queryForPaginatedList("CREDITAPPLICATION.selectBestowalCreditApplicationListByCreditApplication",
				"CREDITAPPLICATION.selectBestowalCreditApplicationCountByCreditApplication", creditApplication,
				pagination.getStartResult(), pagination.getPageSize());
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 查询系统中所有的客户经理
	 * @param creditApplication
	 * @return
	 *         2013-6-5
	 */
	public List querySysloanOfficerList(CreditApplication creditApplication) {

		return this.baseDao.queryList("CREDITAPPLICATION.querySysloanOfficerList", creditApplication);

	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 更新客户经理姓名和id，用于数据赠与功能
	 * @param creditApplication
	 * @return
	 *         2013-6-3
	 */
	public Integer updateLoanOfficerByCreditApplication(CreditApplication creditApplication) {
		return baseDao.update("CREDITAPPLICATION.updateLoanOfficerByCreditApplication", creditApplication);
	}

	@Override
	public List<Map> selectFinishCreditApplicationIdByBorrower(String credentialsNumber) {
		return (List<Map>) baseDao.queryList("RL_BORROWER_SERVICE_APP.selectBorrowerFinishLoan", credentialsNumber);
	}

	@Override
	public List<Map> selectFinishCreditApplicationIdByPartner(String idNumber) {
		return (List<Map>) baseDao.queryList("RL_BORROWER_SERVICE_APP.selectPartnerFinishLoan", idNumber);
	}

	@Override
	public List<Map> selectFinishCreditApplicationIdByBoth(Map<String, String> ids) {
		return (List<Map>) baseDao.queryList("RL_BORROWER_SERVICE_APP.selectFinishCreditApplicationIdByBoth", ids);
	}

	@Override
	public List<Map> selectCreditApplicationIdByBoth(Map<String, String> ids) {
		return (List<Map>) baseDao.queryList("RL_BORROWER_SERVICE_APP.selectCreditApplicationIdByBoth", ids);
	}

	@Override
	public int updateRevolvingCredit(Integer creditapplicationId) {
		return baseDao.update("RL_BORROWER_SERVICE_APP.updateRevolvingCredit", creditapplicationId);
	}

	@Override
	public int updateRevolvingCreditBatch(String creditapplicationIds) {
		return baseDao.update("RL_BORROWER_SERVICE_APP.updateRevolvingCreditBatch", creditapplicationIds);
	}

	@Override
	public List<Map> selectCreditApplicationIdByBorrower(String credentialsNumber) {
		return (List<Map>) baseDao.queryList("RL_BORROWER_SERVICE_APP.selectCreditApplicationIdByBorrower",
				credentialsNumber);
	}
	
	@Override 
	public Pagination creditApplicationSearch(Pagination pagination,Map map) {
		return   baseDao.queryForPaginatedList("CREDITAPPLICATION.creditApplicationSearch", "CREDITAPPLICATION.countCreditApplicationSearch", map, pagination.getStartResult(),
			pagination.getPageSize() );
	}
	
	@Override
	public List<CreditApplication> selectByLoanOfficer(Integer loanOfficerId) {
		return (List<CreditApplication>) baseDao.queryList("CREDITAPPLICATION.selectByLoanOfficer", loanOfficerId.toString());
	}
	
	@Override
	public int updateRevolvingCreditFalse(Integer creditapplicationId) {
		return baseDao.update("RL_BORROWER_SERVICE_APP.updateRevolvingCreditFalse", creditapplicationId);
	}

	/**
     * 根据信贷申请ID获取年化利率
     * @param creditApplicationId
     * @return
     */
	@Override
    public Double getAnnualizedRateById(int creditApplicationId){
        return (Double)baseDao.queryUnique("CREDITAPPLICATION.getAnnualizedRate", creditApplicationId);
    }

    @Override
    public List selectProInfoAll(Map map) {
        return baseDao.queryList("CREDITAPPLICATION.selectProInfoAll",map);
    }

    @Override
    public Long saveParticipateApprove(Map m){
        Integer i = (Integer)baseDao.insertObject("CREDITAPPLICATION.saveParticipateApprove",m);
        return Long.valueOf(i.longValue());
    }

    @Override
    public Integer getParticateApproveCount(Integer creditApplicationId) {
        return (Integer) baseDao.queryUnique("CREDITAPPLICATION.getParticateApproveCount", creditApplicationId);
    }

    @Override
    public Integer updateCityParticipate(Integer creditApplicationId,String yesOrNo){
        Map map = new HashMap();
        map.put("creditApplicationId",creditApplicationId);
        map.put("yesOrNo",yesOrNo);
        return baseDao.update("CREDITAPPLICATION.updateCityParticipate",map);
    }

    @Override
    public List<Integer> getParticipateAndApproveInfo(Integer creditApplicationId) {
        return (List<Integer>) baseDao.queryList("CREDITAPPLICATION.getParticipateAndApproveInfo",creditApplicationId);
    }

	@Override
    public List<Integer> getTop2ApproveInfo(Integer creditApplicationId) {
        return (List<Integer>) baseDao.queryList("CREDITAPPLICATION.getTop2ApproveInfo",creditApplicationId);
    }

    @Override
    public Integer updateCityParticipatePerman(Integer creditApplicationId,String yesOrNo){
        Map map = new HashMap();
        map.put("creditApplicationId",creditApplicationId);
        map.put("yesOrNo",yesOrNo);
        return baseDao.update("CREDITAPPLICATION.updateCityParticipatePerman",map);
    }

    @Override
    public Integer updateAmountByCreditApplicationId(Long creditApplicationId) {
        return baseDao.update("CREDITAPPLICATION.updateAmountByCreditApplicationId", creditApplicationId);
    }
	@Override
    public Integer updateSignagreementDate(CreditApplication creditApplication){
		return update("CREDITAPPLICATION.updateSignagreementDate",creditApplication);
	}

    @Override
    public Integer updateDiscountFlagById(Integer creditApplicationId,String discountFlag){
        Map map = new HashMap();
        map.put("creditApplicationId", creditApplicationId);
        map.put("conditionFlag", discountFlag);
        map.put("retFlag", "0");
        if (discountFlag.equals("0")) {
            map.put("retFlag", "1");
        }
        return baseDao.update("CREDITAPPLICATION.updateDiscountFlagById", map);
    }

    @Override
    public List<String> getProductIdList() {
        return (List<String>)baseDao.queryList("CREDITAPPLICATION.getProductIdList");
    }

    /**
     *    //根据信贷申请id查询借款人身份证号 等信息       编辑回显使用
     * @param creditApplicationId
     * @return
     */
    @Override
    public CreditApplication getIndentityInfoByCreditApplicationId(Integer creditApplicationId) {
        CreditApplication creditApplication = new CreditApplication();
        List<CreditApplication> list = ( List<CreditApplication>)baseDao.queryList("CREDITAPPLICATION.getIndentityInfoByCreditApplicationId",creditApplicationId);
          if(list.size()>0){
              creditApplication = list.get(0);
          }
         return creditApplication;
    }
@Override
    public int queryCountByBussinessNumber(String businessNumber) {
        return (Integer)baseDao.queryUnique("CREDITAPPLICATION.queryCountByBussinessNumber",businessNumber) ;
    }
@Override
	public Map<String, Object> queryForZh(Long creditApplicationId) {
		return (Map<String, Object>) queryUnique("CREDITAPPLICATION.queryForZh", creditApplicationId);
	}

	@Override
	public String queryStatus(Long creditApplicationId) {
		return (String) queryUnique("CREDITAPPLICATION.queryStatus", creditApplicationId);
	}

	@Override
	public void updataSignagreementDate(CreditApplication application) {
		update("CREDITAPPLICATION.updataSignagreementDate", application);
		
	}}
