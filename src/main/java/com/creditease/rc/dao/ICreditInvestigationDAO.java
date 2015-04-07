package com.creditease.rc.dao;

import com.creditease.rc.domain.CreditInvestigation;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.vo.CreditInvestigationVo;

/**
 * 
 * @author haoqiang
 * 
 */
public interface ICreditInvestigationDAO extends IBaseDao {
	/**
	 * 
	 * @param record CreditInvestigation对象
	 * @return 主键
	 */
	Long insert(CreditInvestigation record);

	/**
	 * 
	 * @param record CreditInvestigation对象
	 * @return 更新的条数
	 */
	int updateByPrimaryKey(CreditInvestigation record);

	/**
	 * 
	 * @param record CreditInvestigation对象
	 * @return 更新的条数
	 */
	int updateByPrimaryKeySelective(CreditInvestigation record);

	/**
	 * 
	 * @param creditInvestigatioId 主键
	 * @return CreditInvestigationVo对象
	 */
	CreditInvestigationVo selectByPrimaryKey(Long creditInvestigatioId);

	/**
	 * 
	 * @param creditInvestigatioId 主键
	 * @return 删除的条数
	 */
	int deleteByPrimaryKey(Long creditInvestigatioId);
	/**
	 * 更新信用和背景调查表中的借款人姓名name,配偶姓名
	 */
	int updateCreditInvestigationName(CreditInvestigation creditInvestigation);
}