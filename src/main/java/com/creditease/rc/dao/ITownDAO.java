package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.Town;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.Area;
import com.creditease.rc.vo.QueryCheckTownsVo;

/**
 * 
 * @author zhangman
 * 
 */

public interface ITownDAO extends IBaseDao {
	/**
	 * 
	 * @param town 县镇信息对象
	 * @return 信息列表
	 */
	public Town select(Town town);

	/**
	 * 
	 * @param town 乡镇名称
	 * @return 乡镇列表
	 */
	public List<Town> selectTown(Town town);

	/**
	 * 分页，模糊查询
	 * 
	 * @param townlist 县镇信息list对象
	 * @param pagination 分页条件
	 * @return 乡镇信息分页列表
	 */
	public Pagination selectAllTown(Area townlist, Pagination pagination);

	/**
	 * 添加乡镇信息
	 * 
	 * @param town 县镇信息对象
	 * @return 乡镇信息id
	 */
	public Long addTown(Town town);

	/**
	 * @param townList 村镇列表
	 *            添加乡镇信息
	 */
	public void addTownList(List<Town> townList);

	/**
	 * 修改乡镇信息
	 * 
	 * @param town 乡镇信息对象
	 */
	public void updateTown(Town town);

	/**
	 * 
	 * @param type 查询类型（向上查询，向下查询）
	 * @param townshipinfoid 乡镇信息id
	 * @return 乡镇信息list
	 */
	public List<Town> queryTownshipInfo(String type, Long townshipinfoid);

	/**
	 * 
	 * @author yifengwang
	 * @param townshipinfoid villageId
	 * @return 2012-12-24下午03:26:32
	 */
	public Map<String, Integer> queryCountryTownByVillageId(Long townshipinfoid);

	/**
	 * 模糊查询
	 * 
	 * @param fuzzy 模糊条件
	 * @param pagination 分页条件
	 * @return 分页列表
	 */
	public Pagination selectFuzzy(String fuzzy, Pagination pagination);

	/**
	 * * 查询村所在的省市区县镇编码
	 * 
	 * @author wyf
	 * @param townshipinfoid
	 * @return Map
	 */
	public Map searchNscByVillageId(Long townshipinfoid);

	public List<QueryCheckTownsVo> queryCheckTowns(long parentId);

}
