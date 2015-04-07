package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.LinkmanInvestigation;
import com.creditease.rc.framework.dao.IBaseDao;

/**
 * 
 * @author haoqiang
 * 
 */
public interface ILinkmanInvestigationDAO extends IBaseDao {
	/**
	 * 
	 * @param record LinkmanInvestigation对象
	 */
	void insert(LinkmanInvestigation record);

	/**
	 * 
	 * @param record LinkmanInvestigation对象
	 * @return 更新的条目
	 */
	int updateByPrimaryKey(LinkmanInvestigation record);

	/**
	 * 
	 * @param record LinkmanInvestigation对象
	 * @return 更新的条目
	 */
	int updateByPrimaryKeySelective(LinkmanInvestigation record);

	/**
	 * 
	 * @param linkmanInvestigationId 主键
	 * @return LinkmanInvestigation对象
	 */
	LinkmanInvestigation selectByPrimaryKey(Long linkmanInvestigationId);

	/**
	 * 
	 * @param linkmanInvestigationId 主键
	 * @return 删除的条数
	 */
	int deleteByPrimaryKey(Long linkmanInvestigationId);

	/**
	 * 
	 * @param sqlId SQL的ID
	 * @param entityList LinkmanInvestigationList
	 */
	void batchInsert(String sqlId, List<LinkmanInvestigation> entityList);

	/**
	 * 
	 * @param sqlId SQL的ID
	 * @param entityList LinkmanInvestigationList
	 * @return 更新的条数
	 */
	int batchUpdate(String sqlId, List<LinkmanInvestigation> entityList);

	boolean deleteBycreditInvestigatioId(Long creditInvestigatioId);
}