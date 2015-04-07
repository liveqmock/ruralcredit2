package com.creditease.rc.service;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.RlApplyAuditTable;
import com.creditease.rc.domain.RlAuditDetail;
import com.creditease.rc.vo.TBorrowerServiceAppVo;

/**
 * 审批类服务层
 * @author xubingzhao
 *
 */
public interface IRlCreditApplicationService {
	/**
	 * 查询组员信息
	 * @param rlCreditApplication  信贷申请对象
	 * @return list 组员信息
	 */
	public List<TBorrowerServiceAppVo> selectRlBorrowerServiceApp(CreditApplication rlCreditApplication);
	/**
	 * 查询小组信息 
	 * @param rlCreditApplication 小组VO
	 * @return map  返回小组信息（包含业务单号，客户经理等信息） 
	 */
	public CreditApplication selectRlCreditApplication(CreditApplication rlCreditApplication);
	/**
	 * 当时审批有放款时间，后来放款时间挪到额度确认和放款登记  所以这个方法没用了
	 * @param rlCreditApplication  
	 * @return  int
	 */
	public int updateLoanDate(CreditApplication rlCreditApplication);
	/**
	 * 更改信贷审批单的状态
	 * @param creditApplication 信贷审批单
	 * @return boolean
	 */
	public boolean updateiRlCreditApplicationByList(List<CreditApplication> creditApplication);
	/**
	 * 审批拒绝
	 * @param list  小组模式下的组员列表对象
	 * @param rlApplyAuditTable  审批记录对象
	 * @param rlCreditApplication   信贷申请对象
	 * @return result 返回结果
	 */
	public boolean updateAppraval2Refuse(List<RlAuditDetail> list,
			RlApplyAuditTable rlApplyAuditTable,
			CreditApplication rlCreditApplication);
	/**
	 * 审批通过
	 * 审批拒绝
	 * @param list  小组模式下的组员列表对象
	 * @param rlApplyAuditTable  审批记录对象
	 * @param rlCreditApplication   信贷申请对象
	 * @return result 返回结果
	 */
	boolean updateAppraval(List<RlAuditDetail> list,
			RlApplyAuditTable rlApplyAuditTable,
			CreditApplication rlCreditApplication);
	
}
