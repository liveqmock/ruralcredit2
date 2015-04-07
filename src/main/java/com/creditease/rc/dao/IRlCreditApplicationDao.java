package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.vo.TBorrowerServiceAppVo;

/**
 * 审批类数据层
 * @author xubingzhao
 *
 */
public interface IRlCreditApplicationDao extends IBaseDao{
	/**
	 * 更新审查审批状态
	 * @param rlCreditApplication  RlCreditApplication 
	 * @return   result
	 */
	public boolean updateiRlCreditApplication(CreditApplication rlCreditApplication);

	/**
	 * 查询组员信息
	 * @param rlCreditApplication RlCreditApplication
	 * @return list
	 */
	public List<TBorrowerServiceAppVo> selectRlBorrowerServiceApp(CreditApplication rlCreditApplication);

	/**
	 * 查询小组信息
	 * @param rlCreditApplication 小组VO
	 * @return map
	 */
	public CreditApplication selectRlCreditApplication(
			CreditApplication rlCreditApplication);
	/**
	 * 
	 * @param rlCreditApplication 
	 * @return int
	 */
	public int updateLoanDate(CreditApplication rlCreditApplication);
	
	/**
	 * 修改信贷审批状态
	 * @param creditApplicationList ID集合
	 * @return boolean
	 */
	public boolean updateiRlCreditApplicationByList(
			List<CreditApplication> creditApplicationList);
}
