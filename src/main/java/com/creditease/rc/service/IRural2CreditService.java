/**
 * Title:IRural2Credit.java
 * Description:
 */
package com.creditease.rc.service;

import java.util.Date;
import java.util.List;

import com.creditease.rc.app.credit.LoanBalanceDataResponse;
import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.LocalChgReturnTypeRequest;
import com.creditease.rc.domain.LocalClientApplyRequest;
import com.creditease.rc.domain.LocalOfficeIdDTO;
import com.creditease.rc.domain.LocalOverdueInfoResponse;
import com.creditease.rc.domain.LocalReturnAmountRequest;
import com.creditease.rc.domain.LocalReturnAmountResult;
import com.creditease.rc.domain.LocalReturnSchemeResponse;
import com.creditease.rc.domain.Message;
import com.creditease.rc.vo.LocalReserveDTOVo;
import com.creditease.rc.vo.ReserveReturnRequestVo;
import com.creditease.rc.vo.ReturnSchemeResultVo;

/**
 * Title:IRural2Credit.java
 * Description:
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 
 * @author 郝强
 * @version v1.1
 *          2013-5-16
 */
public interface IRural2CreditService {

	/**
	 * 
	 * @author 郝强
	 * @Description: 1.1 接收借款信息
	 * @param creditapplicationId 信贷申请主键
	 * @return boolean
	 * @version v1.1
	 *          2013-5-16
	 */
	public Message clientApply(Long creditapplicationId);

	/**
	 * 
	 * @author 郝强
	 * @Description: 1.1.1 接收借款信息并记录
	 * @param creditapplicationId 信贷申请主键
	 * @return Message
	 * @version v1.1
	 *          2013-12-2
	 */
	public Message updateClientApplyRecord(Long creditapplicationId);

	/**
	 * 
	 * @author 郝强
	 * @Description: 1.2 查询还款计划
	 * @param creditapplicationId creditapplicationId
	 * @return List<LocalReturnSchemeResponse> 还款计划
	 * @version v1.1
	 *          2013-5-16
	 */

	public List<LocalReturnSchemeResponse> returnScheme(Long creditapplicationId);

	/**
	 * 
	 * @author 郝强
	 * @Description: 1.3 查询逾期合同
	 * @param localOfficeIdListDTOs 分公司/信贷员编号 List
	 * @return List<CreditApplication>
	 * @version v1.1
	 *          2013-5-20
	 */
	public LocalOverdueInfoResponse overdueInfo(List<LocalOfficeIdDTO> localOfficeIdListDTOs);

	/**
	 * 
	 * @author 郝强
	 * @Description: 1.4 还款金额查询
	 * @param parameters 还款金额查询
	 * @return LocalReturnAmountResult
	 * @version v1.1
	 *          2013-5-20
	 */
	public LocalReturnAmountResult returnAmount(LocalReturnAmountRequest parameters);

	/**
	 * 
	 * @author 郝强
	 * @Description: 1.5 预约还款
	 * @param receivedRecordIdList 收款登记IDlist
	 * @param destineDate 预约日期
	 * @param remark 备注
	 * @return ReserveReturnRequestVo
	 * @version v1.1
	 *          2013-5-21
	 */
	public ReserveReturnRequestVo reserveReturn(List<Long> receivedRecordIdList, Date destineDate, String remark);

	/**
	 * 
	 * @author 郝强
	 * @Description: 1.6 信贷员ID变更
	 * @param creditapplicationIdList 信贷申请单List
	 * @param sellId 信贷员ID
	 * @return boolean
	 * @version v1.1
	 *          2013-5-21
	 */
	public boolean modifySeller(List<Long> creditapplicationIdList, String sellId, String sellName);

	/**
	 * 
	 * @author 郝强
	 * @Description: 1.7 变更还款方式
	 * @param localChgReturnTypeRequest LocalChgReturnTypeRequest
	 * @return boolean
	 * @version v1.1
	 *          2013-5-21
	 */
	public Message chgReturnType(AccountInfo accountInfo);

	/**
	 * 
	 * @author 郝强
	 * @Description: 1.8 查询划扣结果
	 * @param bizIdList 订单号List
	 * @return LocalReserveDTOVo
	 * @version v1.0
	 *          2013-5-15
	 */
	public LocalReserveDTOVo qyReserveSearch(List<String> bizIdList, String businessNumber);

	/**
	 * @author 郝强
	 * @Description 1.9 查询借款人信息
	 * @param creditapplicationId
	 * @return LocalClientApplyRequest
	 * @version v1.0
	 *          2013-6-20
	 */
	public LocalClientApplyRequest qyClientApply(Long creditapplicationId);

	public LoanBalanceDataResponse loanBalanceData(Long creditapplicationId, String officeId, String sellerId);

	public ReturnSchemeResultVo returnSchemeResult(Long creditapplicationId);

    /**
     * 综合信贷人员变更
     * @param beforePropId 变更前人员ID
     * @param afterPropId  变更后人员ID
     * @return
     */
    public boolean updateStaffComprehensive(String beforePropId, String afterPropId);
}
