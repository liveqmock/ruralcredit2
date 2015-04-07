package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.CompactRulePrefix;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.CompactRulePrefixVO;

public interface ICompactRulePrefixDao {
	Long insert(CompactRulePrefix record);

	int updateByPrimaryKey(CompactRulePrefix record);

	int updateByPrimaryKeySelective(CompactRulePrefix record);

	CompactRulePrefix selectByPrimaryKey(Long compactRulePrefixId);

	int deleteByPrimaryKey(Long compactRulePrefixId);

	/**
	 * 
	 * @author 韩大年
	 * @Description: 查询。条件可选
	 * @param record
	 * @return
	 * @version v1.0
	 *          2013-9-18
	 */
	public List<CompactRulePrefix> selectBySelective(CompactRulePrefix record);

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
	public Pagination queryCompactRulePrefixByPagination(CompactRulePrefixVO compactRulePrefixVO, Pagination pagination);

	/**
	 * 
	 * @author 韩大年
	 * @Description: “区/县”下分组
	 * @return
	 * @version v1.0
	 *          2014-2-20
	 */
	public List<String> selectGroupByArea();

	/**
	 * 
	 * @author 韩大年
	 * @Description: 城市分组
	 * @return
	 * @version v1.0
	 *          2014-2-20
	 */
	public List<String> selectGroupByCity();

	/**
	 * 
	 * @author 韩大年
	 * @Description: 查询区县下营业部
	 * @return
	 * @version v1.0
	 *          2014-2-20
	 */
	public List<CompactRulePrefix> selectCompactRulePrefixByArea(String area);

	/**
	 * 
	 * @author 韩大年
	 * @Description: 查询城市下营业部
	 * @return
	 * @version v1.0
	 *          2014-2-20
	 */
	public List<CompactRulePrefix> selectCompactRulePrefixByCity(String city);
}
