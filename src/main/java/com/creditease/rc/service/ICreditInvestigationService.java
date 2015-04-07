package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.vo.CreditInvestigationVo;

/**
 * Title:
 * Description: 农村商贷系统研发
 * Copyright: Copyright (c) 2012
 * Company: 普信恒业科技发展（北京）有限公司
 * Date: 2013-3-14
 * 
 * @author: 解兵丰
 * @version: v1.0
 */
public interface ICreditInvestigationService {

	/**
	 * 新增或者修改 信用及背景调查
	 * 
	 * @param creditInvestigationVo 信用及背景调查
	 * @return boolean
	 */
	public boolean addOrUpdateCreditInvestigation(CreditInvestigationVo creditInvestigationVo);

	/**
	 * 查询 信用及背景调查
	 * 
	 * @param creditInvestigationId 信用及背景调查主键
	 * @return CreditInvestigationVo
	 */
	public CreditInvestigationVo selectInvestigationById(Long creditInvestigationId);

	/**
	 * 
	 * @param creditapplicationId 信贷申请主键
	 * @return List<CreditInvestigationVo>
	 */
	List<CreditInvestigationVo> quertyReportList(Long creditapplicationId);

	/**
	 * 
	 * @param creditInvestigationId 信用及背景调查主键
	 * @return CreditInvestigationVo
	 */
	CreditInvestigationVo showReturnCreditInvestigation(Long creditInvestigationId);

	/**
	 * 
	 * @param creditInvestigationVo 信用及背景调查
	 * @return 条数
	 */
	int updateCheckStatus(CreditInvestigationVo creditInvestigationVo);

	/**
	 * 
	 * @param creditInvestigationId  信用及背景调查主键
	 * @param creditapplicationId 信贷申请主键
	 * @return boolean
	 */
	boolean updateSubmit(Long creditInvestigationId, Long creditapplicationId);

	/**
	 * 
	 * @param creditapplicationId 信贷申请主键
	 * @return CreditApplication
	 */
	CreditApplication selectCreditApplication(Long creditapplicationId);

	/**
	 * 
	 * @param creditapplicationId 信贷申请主键
	 * @return List<CreditInvestigationVo>
	 */
	List<CreditInvestigationVo> selectCreditInvestigationVoList(Long creditapplicationId);

	/**
	 * 
	 * @param creditapplicationId 信贷申请主键
	 * @return CreditInvestigationVo
	 */
	CreditInvestigationVo selectNameAndSpousName(Long creditapplicationId);

	/**
	 * 
	 * @param creditApplication 信贷申请
	 * @return boolean
	 */
	boolean updateSecondFlag(CreditApplication creditApplication);

	/**
	 * 查看信用背景调查表时将选择项对应的code显示为汉字
	 * liuli 2013-05-03
	 * 
	 * @param creditInvestigation 信用及背景调查主键
	 */
	public void displayNameByCode(CreditInvestigationVo creditInvestigation);

	/**
	 * 
	 * @param creditInvestigation 信用及背景调查报告对象
	 * @param creditapplicationId 信贷申请主键
	 * @return 是否提交成功
	 */
	public boolean saveAndSubmit(CreditInvestigationVo creditInvestigation, Long creditapplicationId);

}
