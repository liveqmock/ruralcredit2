package com.creditease.rc.framework.dao;

import java.util.List;

import com.creditease.rc.framework.pager.Pagination;

/**
 * <p>
 * Title:基类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright:Copyright (c) 2011
 * </p>
 * <p>
 * Company:普信恒业科技发展(北京)有限公司
 * </p>
 * <p>
 * Date:2011-10-12
 * </p>
 * <p>
 * Modify:
 * </p>
 * <p>
 * Bug:
 * </p>
 * 
 * @author wangxinqiang
 * @version 1.0
 */
public interface IBaseDao {

	/**
	 * 插入一条数据返回自身的对象
	 * 
	 * @param sqlId id
	 * @param entity 实体对象
	 * @return Object
	 */
	Object insertObject(String sqlId, Object entity);

	/**
	 * 添加一条新纪录
	 * 
	 * @param sqlId
	 *            sqlmap名称
	 * @param entity
	 *            实体对象
	 */
	void insert(String sqlId, Object entity);

	/**
	 * 批量添加
	 * 
	 * @param sqlId
	 *            sqlmap名称
	 * @param entityList
	 *            实体对象列表
	 */
	@SuppressWarnings("unchecked")
	void batchInsert(String sqlId, List entityList);

	/**
	 * 批量添加
	 * 
	 * @param sqlId
	 *            sqlmap名称
	 * @param entityList
	 *            实体对象列表
	 * @param commitNum
	 *            阶段提交数默认为20次一提交
	 */
	@SuppressWarnings("unchecked")
	void batchInsert(String sqlId, List entityList, int commitNum);

	/**
	 * 删除一条纪录
	 * 
	 * @param sqlId
	 *            sqlmap名称
	 * @param pk
	 *            主键
	 * @return 影响几行数据
	 */
	int delete(String sqlId, Object pk);

	/**
	 * 批量删除
	 * 
	 * @param sqlId sqlmap名称
	 * @param pkList 主键列表
	 */
	void batchDelete(String sqlId, List<Integer> pkList);

	/**
	 * 批量删除
	 * 影响几行数据默认为20次一提交
	 * @param sqlId
	 *            sqlmap名称
	 * @param pkList
	 *            主键列表
	 * @param commitNum
	 *            阶段提交数
	 */
	void batchDelete(String sqlId, List<Integer> pkList, int commitNum);

	/**
	 * 修改一条记录
	 * 
	 * @param sqlId
	 *            sqlmap名称
	 * @param entity
	 *            实体对象
	 * @return 影响几行数据
	 */
	int update(String sqlId, Object entity);

	/**
	 * 批量修改
	 * 
	 * @param sqlId
	 *            sqlmap名称
	 * @param entityList
	 *            实体对象列表
	 * @return 影响几行数据默认为20次一提交
	 */
	@SuppressWarnings("unchecked")
	int batchUpdate(String sqlId, List entityList);

	/**
	 * 批量修改
	 * 
	 * @param sqlId
	 *            sqlmap名称
	 * @param entityList
	 *            实体对象列表
	 * @param commitNum
	 *            阶段提交数
	 * @return 影响几行数据默认为20次一提交
	 */
	@SuppressWarnings("unchecked")
	int batchUpdate(String sqlId, List entityList, int commitNum);

	/**
	 * 唯一查询
	 * 
	 * @param sqlId
	 *            sqlmap名称
	 * @return 对应的实体对象
	 */
	Object queryUnique(String sqlId);

	/**
	 * 唯一查询
	 * 
	 * @param sqlId
	 *            sqlmap名称
	 * @param parameter
	 *            查询条件
	 * @return 对应的实体对象
	 */
	Object queryUnique(String sqlId, Object parameter);

	/**
	 * 多行查询
	 * 
	 * @param sqlId
	 *            sqlmap名称
	 * @return 对应的多个实体对象
	 */
	List<?> queryList(String sqlId);

	/**
	 * 多行查询
	 * 
	 * @param sqlId
	 *            sqlmap名称
	 * @param parameter
	 *            查询条件
	 * @return 对应的实体对象
	 */
	List<?> queryList(String sqlId, Object parameter);

	/**
	 * 分页查询
	 * 
	 * @param itemSqlId
	 *            数据项sqlmap名称
	 * @param countSqlId
	 *            总页数sqlmap名称
	 * @param parameter
	 *            查询条件
	 * @param startIndex
	 *            显示第几页数据
	 * @return 分页对象
	 */
	Pagination queryForPaginatedList(String itemSqlId, String countSqlId,
			Object parameter, int startIndex);

	/**
	 * 分页查询
	 * 
	 * @param itemSqlId
	 *            数据项sqlmap名称
	 * @param countSqlId
	 *            总页数sqlmap名称
	 * @param parameter
	 *            查询条件
	 * @param startIndex
	 *            显示第几页数据
	 * @param max
	 *            每页显示记录数
	 * @return Pagination
	 */
	Pagination queryForPaginatedList(String itemSqlId, String countSqlId,
			Object parameter, int startIndex, int max);

}
