package com.creditease.rc.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;

import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.Town;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.Area;
import com.creditease.rc.vo.QueryCheckTownsVo;
import com.creditease.rc.vo.TownVo;

/**
 * 
 * @author zhangman
 * 
 */
public interface ITownService {
	/**
	 * 
	 * @param town 县镇信息对象
	 * @return 信息列表
	 */
	public Town select(Town town);

	/**
	 * 
	 * @param townname 乡镇名称
	 * @param parentId 父id（镇id，县city-code）
	 * @return 乡镇列表
	 */
	public List<Town> selectTown(String townname, long parentId);

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
	 * @param towns 县镇信息列表
	 * @return true:成功，false：失败
	 */
	public Message addTown(TownVo towns);

	/**
	 * 添加乡镇信息
	 * 
	 * @param town 县镇信息列表
	 * @return 编码
	 */
	public long addTownOne(Town town);

	/**
	 * @param townList 村镇列表
	 *            添加乡镇信息
	 */
	public void addTownList(List<Town> townList);

	/**
	 * 修改乡镇信息
	 * 
	 * @param towns 乡镇信息列表
	 * @return true:成功，false：失败
	 */
	public boolean updateTown(Area towns);

	/**
	 * 查询县，镇，村列表.如果是镇列表，当该镇下没上线的村时镇列表不显示该镇
	 * 
	 * @author yifengwang
	 * @param type type
	 * @param townshipinfoid townshipinfoid
	 * @return 2012-12-24下午04:05:14
	 */
	public List<Town> queryTownshipInfo(String type, Long townshipinfoid);

	/**
	 * 查询村所在镇及县
	 * 
	 * @author yifengwang
	 * @param villageId villageId
	 * @return 2012-12-24下午04:05:37
	 */
	public Map<String, Integer> queryCountryTownByVillageId(Long villageId);

	/**
	 * 
	 * @param fuzzy 模糊条件
	 * @param pagination 分页条件
	 * @return 分页列表
	 */
	public Pagination selectFuzzy(String fuzzy, Pagination pagination);

	/**
	 * 
	 * @param sheet
	 *            excel页面
	 * @param parentId 父id
	 * @return true：不存在空数据，false：存在空数据
	 */
	public Map addTowns(Sheet sheet, String parentId);

	/**
	 * 
	 * @param townshipinfoid er
	 * @return df
	 */
	public Town searchParentId(long townshipinfoid);

	/**
	 * 查询村所在的省市区县镇编码
	 * 
	 * @author wyf
	 * @param townshipinfoid
	 * @return Map<String,Integer>
	 */
	public Map searchNscByVillageId(Long townshipinfoid);

	/**
	 * 
	 * @param parentId
	 *            父id(镇id，县id)
	 * @param key
	 *            手录编号
	 * @param townShipInfoId
	 *            乡镇信息id
	 * @return message对象
	 */
	public Message searchTownshipId(Long parentId, String key, Long townShipInfoId);

	/**
	 * 取村map KEY 是 name
	 * 
	 * @param queryCheckTownsVos
	 * @return
	 */
	public Map<String, String> getVillageMapKeyIsName(List<QueryCheckTownsVo> queryCheckTownsVos);

	/**
	 * 取村map KEY 是 key
	 * 
	 * @param queryCheckTownsVos
	 * @return
	 */
	public Map<String, String> getVillageMapKeyIsKey(List<QueryCheckTownsVo> queryCheckTownsVos);

	/**
	 * 取镇map KEY 是 key
	 * 
	 * @param queryCheckTownsVos
	 * @return
	 */
	public Map<String, String> getTownMapKeyIsName(List<QueryCheckTownsVo> queryCheckTownsVos);

	/**
	 * 取镇map KEY 是 key
	 * 
	 * @param queryCheckTownsVos
	 * @return
	 */
	public Map<String, String> getTownMapKeyIsKey(List<QueryCheckTownsVo> queryCheckTownsVos);

	/**
	 * 取到区下的镇和村
	 * 
	 * @param parentId 区ID
	 * @return
	 */
	public List<QueryCheckTownsVo> queryCheckTowns(long parentId);
}
