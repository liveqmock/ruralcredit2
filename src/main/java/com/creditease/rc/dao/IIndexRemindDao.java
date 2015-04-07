package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.vo.AuditRemindVo;
import com.creditease.rc.vo.CountInfo;
import com.creditease.rc.vo.CreditapplicationView;
import com.creditease.rc.vo.DepartmentCountInfo;
import com.creditease.rc.vo.InvestigationVo;

/**
 * 
 * @author Administrator
 * 
 */
public interface IIndexRemindDao {
	/**
	 * 查询审批提醒 无用
	 * checkstyle
	 * 
	 * @author wyf
	 * @param creditApplication
	 * @return
	 *         List<AuditRemindVo>
	 */
	public List<AuditRemindVo> selectAuditRemind(CreditApplication creditApplication);

	/**
	 * 查询新增申请单提醒 无用
	 * checkstyle
	 * 
	 * @author wyf
	 * @param creditApplication
	 * @return
	 *         InvestigationVo
	 */
	public InvestigationVo selectInvestigatioRemind(CreditApplication creditApplication);

	/**
	 * 查询放款登记提醒 无用
	 * checkstyle
	 * 
	 * @author wyf
	 * @param creditApplication
	 * @return
	 *         List<AuditRemindVo>
	 */
	public List<AuditRemindVo> selectLoanRegister(CreditApplication creditApplication);

	/**
	 * 查询财务付款提醒 无用
	 * checkstyle
	 * 
	 * @author wyf
	 * @param creditApplication
	 * @return
	 *         AuditRemindVo
	 */
	public AuditRemindVo selectFinancePaymentRemind(CreditApplication creditApplication);

	/**
	 * 查询财务收款提醒 无用
	 * checkstyle
	 * 
	 * @author wyf
	 * @param creditApplication
	 * @return
	 *         List<AuditRemindVo>
	 */
	public List<AuditRemindVo> selectFinanceReceiveRemind(CreditApplication creditApplication);

	/**
	 * 查询收款登记提醒 无用
	 * checkstyle
	 * 
	 * @author wyf
	 * @param creditApplication
	 * @return
	 *         List<AuditRemindVo>
	 */
	public List<AuditRemindVo> selectReceiptRegisterRemind(CreditApplication creditApplication);

	/**
	 * 查询信贷员业绩 无用
	 * checkstyle
	 * 
	 * @author wyf
	 * @param application
	 * @return
	 *         Map<String,CountInfo>
	 */
	public Map<String, CountInfo> selectCountInfo(CreditApplication application);

	/**
	 * 查询营业部业绩统计 无用
	 * checkstyle
	 * 
	 * @author wyf
	 * @param departmentId
	 * @return
	 *         List<DepartmentCountInfo>
	 */
	public List<DepartmentCountInfo> selectDepartmentCountInfo(Integer departmentId);

	/**
	 * 从视图中查提醒
	 * 
	 * @param creditapplication 信贷申请对象
	 * @return List<CreditapplicationView>
	 */
	public List<CreditapplicationView> selectRemindFromView(CreditApplication creditapplication);

	/**
	 * 从库中查询次权限下有多少条申请
	 * 
	 * @param querySultMap 用于查询申请的map
	 * @return 返回申请条数
	 */
	public int conSultCount(Map<String, String> querySultMap);

    public int selectUploadingContractRemind(CreditApplication application);
}
