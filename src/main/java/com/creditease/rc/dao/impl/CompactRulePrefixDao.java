package com.creditease.rc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.ICompactRulePrefixDao;
import com.creditease.rc.domain.CompactRulePrefix;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.CompactRulePrefixVO;
@Repository
public class CompactRulePrefixDao implements ICompactRulePrefixDao {
	public CompactRulePrefixDao() {
		super();
	}

	@Resource
	private IBaseDao baseDao;

	@Override
	public Long insert(CompactRulePrefix record) {
		Object newKey = baseDao.insertObject("COMPACTRULEPREFIX.abatorgenerated_insert", record);
		return (Long) newKey;
	}

	@Override
	public int updateByPrimaryKey(CompactRulePrefix record) {
		int rows = baseDao.update("COMPACTRULEPREFIX.abatorgenerated_updateByPrimaryKey", record);
		return rows;
	}

	@Override
	public int updateByPrimaryKeySelective(CompactRulePrefix record) {
		int rows = baseDao.update("COMPACTRULEPREFIX.abatorgenerated_updateByPrimaryKeySelective", record);
		return rows;
	}

	@Override
	public CompactRulePrefix selectByPrimaryKey(Long compactRulePrefixId) {
		CompactRulePrefix key = new CompactRulePrefix();
		key.setCompactRulePrefixId(compactRulePrefixId);
		CompactRulePrefix record = (CompactRulePrefix) baseDao.queryUnique(
				"COMPACTRULEPREFIX.abatorgenerated_selectByPrimaryKey", key);
		return record;
	}

	@Override
	public int deleteByPrimaryKey(Long compactRulePrefixId) {
		CompactRulePrefix key = new CompactRulePrefix();
		key.setCompactRulePrefixId(compactRulePrefixId);
		int rows = baseDao.delete("COMPACTRULEPREFIX.abatorgenerated_deleteByPrimaryKey", key);
		return rows;
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 按条件查询
	 * @param record
	 * @return
	 * @version v1.0
	 *          2013-9-18
	 */
	@Override
	public List<CompactRulePrefix> selectBySelective(CompactRulePrefix record) {
		return (List<CompactRulePrefix>) this.baseDao.queryList("COMPACTRULEPREFIX.selectBySelective", record);
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description:
	 * @param compactRulePrefixVO
	 * @param pagination
	 * @return 分页查询
	 * @version v1.0
	 *          2013-9-22
	 */
	@Override
	public Pagination queryCompactRulePrefixByPagination(CompactRulePrefixVO compactRulePrefixVO, Pagination pagination) {
		return baseDao.queryForPaginatedList("COMPACTRULEPREFIX.selectListByPage",
				"COMPACTRULEPREFIX.selectCountByPage", compactRulePrefixVO, pagination.getStartResult(),
				pagination.getPageSize());
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: “区/县”下分组
	 * @return
	 * @version v1.0
	 *          2014-2-20
	 */
	public List<String> selectGroupByArea() {
		return (List<String>) this.baseDao.queryList("COMPACTRULEPREFIX.selectGroupByArea");
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 城市分组
	 * @return
	 * @version v1.0
	 *          2014-2-20
	 */
	public List<String> selectGroupByCity() {
		return (List<String>) this.baseDao.queryList("COMPACTRULEPREFIX.selectGroupByCity");
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 查询区县下营业部
	 * @return
	 * @version v1.0
	 *          2014-2-20
	 */
	public List<CompactRulePrefix> selectCompactRulePrefixByArea(String area) {
		return (List<CompactRulePrefix>) this.baseDao.queryList(
				"COMPACTRULEPREFIX.selectCompactRulePrefixByArea", area);
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 查询城市下营业部
	 * @return
	 * @version v1.0
	 *          2014-2-20
	 */
	public List<CompactRulePrefix> selectCompactRulePrefixByCity(String city) {
		return (List<CompactRulePrefix>) this.baseDao.queryList(
				"COMPACTRULEPREFIX.selectCompactRulePrefixByCity", city);
	}

}
