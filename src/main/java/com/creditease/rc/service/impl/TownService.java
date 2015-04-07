package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

import com.creditease.rc.dao.ITownDAO;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.NationalStandardCode;
import com.creditease.rc.domain.Town;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.INationalStandardCodeService;
import com.creditease.rc.service.ITownService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.vo.Area;
import com.creditease.rc.vo.QueryCheckTownsVo;
import com.creditease.rc.vo.TownVo;

/**
 * 对小组信息的操作
 * 
 * @author zhangman
 * 
 */
@Service
public class TownService implements ITownService {

	@Resource
	private ITownDAO townDAO;
	@Resource
	private INationalStandardCodeService nationalStandardCodeService;

	@Override
	public Town select(Town town) {
		return townDAO.select(town);
	}

	@Override
	public List<Town> selectTown(String townname, long parentId) {
		Town town = new Town();
		town.setTownshipinfName(townname);
		if (!"".equals(parentId)) {
			town.setParentId(parentId);
		}
		NationalStandardCode nationalStandardCode = nationalStandardCodeService.selectByCode(Long.valueOf(parentId)
				.intValue());
		if (nationalStandardCode != null) {
			town.setOnLine("1");
		}
		return townDAO.selectTown(town);
	}

	@Override
	public Pagination selectAllTown(Area townlist, Pagination pagination) {
		return townDAO.selectAllTown(townlist, pagination);
	};

	@Override
	public Message addTown(TownVo towns) {
		Message message = new Message();
		Town town = towns.getListTown()[0];
		message = searchTownshipId(town.getParentId(), town.getKey(), town.getTownshipinfoid());
		if (message.isSuccess()) {
			long key = 0;
			if (!(towns.getListTown()[0].getTownshipinfoid() == null || "".equals(towns.getListTown()[0]
					.getTownshipinfoid()))) {
				townDAO.addTown(towns.getListTown()[1]);
				message.setSuccess(true);
			} else {
				towns.getListTown()[0].setOnLine("1");
				key = townDAO.addTown(towns.getListTown()[0]);
				towns.getListTown()[1].setParentId(key);
				townDAO.addTown(towns.getListTown()[1]);
				message.setSuccess(true);
			}
		} else {
			message.setSuccess(false);
		}
		return message;
	}

	@Override
	public long addTownOne(Town town) {
		return townDAO.addTown(town);
	}

	@Override
	public void addTownList(List<Town> townList) {
		townDAO.addTownList(townList);
	}

	@Override
	public boolean updateTown(Area towns) {
		Town town = new Town();
		Town village = new Town();
		town.setKey(towns.getTownCode());
		town.setTownshipinfName(towns.getTown());
		town.setTownshipinfoid(towns.getParentId());
		village.setKey(towns.getCode());
		village.setTownshipinfName(towns.getVillage());
		village.setTownshipinfoid(towns.getTownshipinfoid());
		village.setOnLine(towns.getOnLine());
		town.setOnLine("1");
		townDAO.updateTown(town);
		townDAO.updateTown(village);
		return true;
	}

	@Override
	public List<Town> queryTownshipInfo(String type, Long townshipinfoid) {
		return townDAO.queryTownshipInfo(type, townshipinfoid);
	}

	@Override
	public Map<String, Integer> queryCountryTownByVillageId(Long villageId) {
		return townDAO.queryCountryTownByVillageId(villageId);
	}

	@Override
	public Pagination selectFuzzy(String fuzzy, Pagination pagination) {
		return townDAO.selectFuzzy(fuzzy, pagination);
	}

	@Override
	public Map addTowns(Sheet sheet, String parentId) {
		Map result = new HashMap();
		// 验证 文件本身的重复数据
		Message message = this.validateOnly(sheet);
		boolean flag = true;
		// 验证 重复数据
		if (message.isSuccess()) {
			Message message1 = this.validateDataBaseNew(sheet, Long.valueOf(parentId));
			// 验证数据库重复数据
			if (message1.isSuccess()) {
				int count = 0;
				long townId = 0;
				List<Town> townList = new ArrayList<Town>();
				String cellNameNew1 = sheet.getRow(0).getCell(0).getStringCellValue().trim();
				String cellKeyNew1 = sheet.getRow(0).getCell(1).getStringCellValue().trim().toUpperCase();
				String villageNameNew1 = sheet.getRow(0).getCell(2).getStringCellValue().trim();
				String villageKeyNew1 = sheet.getRow(0).getCell(3).getStringCellValue().trim().toUpperCase();
				long online1 = (long) sheet.getRow(0).getCell(4).getNumericCellValue();
				Town town1 = new Town();
				Town valige1 = new Town();
				town1 = this.setTown(cellNameNew1, cellKeyNew1, online1);
				town1.setOnLine("1");
				town1.setParentId(Long.valueOf(parentId));
				valige1 = this.setTown(villageNameNew1, villageKeyNew1, online1);
				// 如果 数据库中存在 该镇
				if (townDAO.select(town1) != null) {
					if (townDAO.select(valige1) == null) {
						valige1.setParentId(townDAO.select(town1).getTownshipinfoid());
						townList.add(valige1);
						count++;
					}
				} else {
					townId = townDAO.addTown(town1);
					valige1.setParentId(townId);
					townList.add(valige1);
					count++;
				}
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					if ((sheet.getRow(i).getCell(0) == null || "".equals(sheet.getRow(i).getCell(0)
							.getStringCellValue().trim()))
							&& (sheet.getRow(i).getCell(0) == null || "".equals(sheet.getRow(i).getCell(0)
									.getStringCellValue().trim()))
							&& (sheet.getRow(i).getCell(0) == null || "".equals(sheet.getRow(i).getCell(0)
									.getStringCellValue().trim()))
							&& (sheet.getRow(i).getCell(0) == null || "".equals(sheet.getRow(i).getCell(0)
									.getStringCellValue().trim()))
							&& (sheet.getRow(i).getCell(0) == null || "".equals(sheet.getRow(i).getCell(0)
									.getStringCellValue().trim()))) {
						continue;
					}
					String cellNameNew = sheet.getRow(i).getCell(0).getStringCellValue().trim();
					String cellKeyNew = sheet.getRow(i).getCell(1).getStringCellValue().trim().toUpperCase();
					String villageNameNew = sheet.getRow(i).getCell(2).getStringCellValue().trim();
					String villageKeyNew = sheet.getRow(i).getCell(3).getStringCellValue().trim().toUpperCase();
					long online = (long) sheet.getRow(i).getCell(4).getNumericCellValue();
					Town town = new Town();
					Town valige = new Town();
					town = this.setTown(cellNameNew, cellKeyNew, online);
					town.setOnLine("1");
					town.setParentId(Long.valueOf(parentId));
					valige = this.setTown(villageNameNew, villageKeyNew, online);
					// 如果 数据库中存在 该镇
					if (townDAO.select(town) != null) {
						valige.setParentId(townDAO.select(town).getParentId());
						if (townDAO.select(valige) == null) {
							valige.setParentId(townDAO.select(town).getTownshipinfoid());
							townList.add(valige);
							count++;
						}
					} else {
						townId = townDAO.addTown(town);
						valige.setParentId(townId);
						townList.add(valige);
						count++;
					}
				}
				this.addTownList(townList);
				result.put("flag", flag);
				result.put("message", message.getMsg());
				result.put("count", count);
				return result;
			} else {
				result.put("flag", false);
				result.put("message", message1.getMsg());
				return result;
			}

		} else {
			result.put("flag", false);
			result.put("message", message.getMsg());
			return result;
		}

	}

	/**
	 * 
	 * @param cellName
	 *            名称
	 * @param cellKey
	 *            编码
	 * @param online
	 *            上线标记
	 * @return 县镇对象
	 */
	private Town setTown(String cellName, String cellKey, long online) {
		Town town = new Town();
		town.setTownshipinfName(cellName);
		town.setKey(cellKey);
		town.setOnLine(String.valueOf(online));
		return town;
	}

	/**
	 * 验证 数据重复
	 * 
	 * @param sheet
	 *            工作簿
	 * @return map
	 */
	private Message validateOnly(Sheet sheet) {
		List<Area> townList = new ArrayList<Area>();
		StringBuffer zhenName = new StringBuffer();
		StringBuffer zhengCode = new StringBuffer();
		StringBuffer cunName = new StringBuffer();
		StringBuffer cunCode = new StringBuffer();
		StringBuffer cun = new StringBuffer();
		Message message = new Message();
		message.setSuccess(true);
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			if ((sheet.getRow(i).getCell(0) == null || ""
					.equals(sheet.getRow(i).getCell(0).getStringCellValue().trim()))
					&& (sheet.getRow(i).getCell(0) == null || "".equals(sheet.getRow(i).getCell(0).getStringCellValue()
							.trim()))
					&& (sheet.getRow(i).getCell(0) == null || "".equals(sheet.getRow(i).getCell(0).getStringCellValue()
							.trim()))
					&& (sheet.getRow(i).getCell(0) == null || "".equals(sheet.getRow(i).getCell(0).getStringCellValue()
							.trim()))
					&& (sheet.getRow(i).getCell(0) == null || "".equals(sheet.getRow(i).getCell(0).getStringCellValue()
							.trim()))) {
				continue;
			}
			String townName = sheet.getRow(i).getCell(0).getStringCellValue().trim();
			String townKey = sheet.getRow(i).getCell(1).getStringCellValue().trim().toUpperCase();
			String villageName = sheet.getRow(i).getCell(2).getStringCellValue().trim();
			String villageKey = sheet.getRow(i).getCell(3).getStringCellValue().trim().toUpperCase();

			for (int j = i + 1; j <= sheet.getLastRowNum(); j++) {
				if ((sheet.getRow(j).getCell(0) == null || "".equals(sheet.getRow(j).getCell(0).getStringCellValue()
						.trim()))
						&& (sheet.getRow(j).getCell(0) == null || "".equals(sheet.getRow(j).getCell(0)
								.getStringCellValue().trim()))
						&& (sheet.getRow(j).getCell(0) == null || "".equals(sheet.getRow(j).getCell(0)
								.getStringCellValue().trim()))
						&& (sheet.getRow(j).getCell(0) == null || "".equals(sheet.getRow(j).getCell(0)
								.getStringCellValue().trim()))
						&& (sheet.getRow(j).getCell(0) == null || "".equals(sheet.getRow(j).getCell(0)
								.getStringCellValue().trim()))) {
					continue;
				}
				String townNameNew = sheet.getRow(j).getCell(0).getStringCellValue().trim();
				String townKeyNew = sheet.getRow(j).getCell(1).getStringCellValue().trim().toUpperCase();
				String villageNameNew = sheet.getRow(j).getCell(2).getStringCellValue().trim();
				String villageKeyNew = sheet.getRow(j).getCell(3).getStringCellValue().trim().toUpperCase();

				if (townNameNew.equals(townName) && townKeyNew.equals(townKey)) {
					if (villageName.equals(villageNameNew) && !(villageKey.equals(villageKeyNew))) {
						cunName.append(townName + "中第" + (i + 1) + "行与第" + (j + 1) + "行，");
						message.setSuccess(false);
					}
					if (villageKey.equals(villageKeyNew) && !(villageName.equals(villageNameNew))) {
						cunCode.append(townName + "中第" + (i + 1) + "行与第" + (j + 1) + "行，");
						message.setSuccess(false);
					}
					if (villageKey.equals(villageKeyNew) && villageName.equals(villageNameNew)) {
						cun.append(townName + "中第" + (i + 1) + "行与第" + (j + 1) + "行，");
						message.setSuccess(false);
					}
				}
				if (townNameNew.equals(townName) && !(townKeyNew.equals(townKey))) {
					zhenName.append("第" + (i + 1) + "行与第" + (j + 1) + "行，");
					message.setSuccess(false);
				}
				if (townKeyNew.equals(townKey) && !(townNameNew.equals(townName))) {
					zhengCode.append("第" + (i + 1) + "行与第" + (j + 1) + "行，");
					message.setSuccess(false);
				}

			}

		}
		StringBuffer returnString = new StringBuffer();
		if (zhenName.toString() != null && !("".equals(zhenName.toString()))) {
			if (zhenName.length() > 30) {
				zhenName = new StringBuffer(zhenName.toString().substring(0, 30) + "......");
			}
			zhenName.append("镇名称相同！");
			returnString.append(zhenName);
		}
		if (zhengCode.toString() != null && !("".equals(zhengCode.toString()))) {
			if (zhengCode.length() > 30) {
				zhengCode = new StringBuffer(zhengCode.toString().substring(0, 30) + "......");
			}
			zhengCode.append("镇编码相同！");
			returnString.append("," + zhengCode);
		}
		if (cunName.toString() != null && !("".equals(cunName.toString()))) {
			if (cunName.length() > 30) {
				cunName = new StringBuffer(cunName.toString().substring(0, 30) + "......");
			}
			cunName.append("村名称相同！");
			returnString.append("," + cunName);
		}
		if (cunCode.toString() != null && !("".equals(cunCode.toString()))) {
			if (cunCode.length() > 30) {
				cunCode = new StringBuffer(cunCode.toString().substring(0, 30) + "......");
			}
			cunCode.append("村编码相同！");
			returnString.append("," + cunCode);
		}
		if (cun.toString() != null && !("".equals(cun.toString()))) {
			if (cun.length() > 30) {
				cun = new StringBuffer(cun.toString().substring(0, 30) + "......");
			}
			cun.append("村相同！");
			returnString.append("," + cun);
		}
		message.setMsg(returnString.toString());
		return message;
	}

	/**
	 * 验证 编号与数据库 重复
	 * 
	 * @author 郝强
	 * @param sheet
	 * @param parentId
	 * @return Message
	 */
	public Message validateDataBaseNew(Sheet sheet, long parentId) {
		StringBuffer sb5 = new StringBuffer();// 镇已存在
		StringBuffer sb6 = new StringBuffer();// 村已存在
		StringBuffer sb7 = new StringBuffer();// 镇与数据库中的key或Name不符
		StringBuffer sb8 = new StringBuffer();// 村与数据库中的key或Name不符
		Message message = new Message();
		message.setSuccess(true);
		long townId = 0;
		List<QueryCheckTownsVo> queryCheckTownsVos = this.queryCheckTowns(parentId);
		Map<String, String> townMapKeyIsKey = this.getTownMapKeyIsKey(queryCheckTownsVos);
		Map<String, String> townMapKeyIsName = this.getTownMapKeyIsName(queryCheckTownsVos);
		Map<String, String> villageMapKeyIsKey = this.getVillageMapKeyIsKey(queryCheckTownsVos);
		Map<String, String> villageMapKeyIsName = this.getVillageMapKeyIsName(queryCheckTownsVos);
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			if ((sheet.getRow(i).getCell(0) == null || ""
					.equals(sheet.getRow(i).getCell(0).getStringCellValue().trim()))
					&& (sheet.getRow(i).getCell(0) == null || "".equals(sheet.getRow(i).getCell(0).getStringCellValue()
							.trim()))
					&& (sheet.getRow(i).getCell(0) == null || "".equals(sheet.getRow(i).getCell(0).getStringCellValue()
							.trim()))
					&& (sheet.getRow(i).getCell(0) == null || "".equals(sheet.getRow(i).getCell(0).getStringCellValue()
							.trim()))
					&& (sheet.getRow(i).getCell(0) == null || "".equals(sheet.getRow(i).getCell(0).getStringCellValue()
							.trim()))) {
				continue;
			}
			String cellName = sheet.getRow(i).getCell(0).getStringCellValue().trim();// 镇名称
			String cellKey = sheet.getRow(i).getCell(1).getStringCellValue().trim().toUpperCase();// 镇Key
			String villageNameNew = sheet.getRow(i).getCell(2).getStringCellValue().trim();// 村名称
			String villageKeyNew = sheet.getRow(i).getCell(3).getStringCellValue().trim().toUpperCase();// 村Key
// // 判断镇 与数据库数据重复
// List<Town> towns = this.selectTown("", parentId);
// for (Town town2 : towns) {
// if (town2.getKey().equals(cellKey)) {
// sb5.append("第" + (i + 1) + "行，");
// message.setSuccess(false);
// }
// }
			// 判断镇是否与数据库中的相符
			String dbtownName = townMapKeyIsKey.get(cellKey);
			String dbtownKey = townMapKeyIsName.get(cellName);

			if (CommonUtil.isEmpty(dbtownName) && CommonUtil.isEmpty(dbtownKey)) {
				// 验证有没有这个镇
				// 不用验证村 直接插入
			} else if (CommonUtil.isNotEmpty(dbtownName) && CommonUtil.isNotEmpty(dbtownKey)
					&& cellName.equals(dbtownName) && cellKey.equals(dbtownKey)) {
				// 如果 镇 没有问题 再来验证村
				String dbvillageName = villageMapKeyIsKey.get(cellKey + "_" + cellName + "_" + villageKeyNew);
				String dbvillageKey = villageMapKeyIsName.get(cellKey + "_" + cellName + "_" + villageNameNew);
				if (CommonUtil.isEmpty(dbvillageName) && CommonUtil.isEmpty(dbvillageKey)) {
					// 验证有没有这个村
					// 没有村直接插入
				} else if (CommonUtil.isNotEmpty(dbvillageName) && CommonUtil.isNotEmpty(dbvillageKey)
						&& villageNameNew.equals(dbvillageName) && villageKeyNew.equals(dbvillageKey)) {
					// 判断是否与数据库中村相同
					sb6.append("第" + (i + 1) + "行，");
					if (message.isSuccess()) {
						message.setSuccess(false);
					}
					continue;
				} else {
					sb8.append("第" + (i + 1) + "行，");
					if (message.isSuccess()) {
						message.setSuccess(false);
					}
					continue;
				}
			} else {
				sb7.append("第" + (i + 1) + "行，");
				if (message.isSuccess()) {
					message.setSuccess(false);
				}
				continue;
			}

			// 如果真已经 存在 判断 村 是否 与数据库重复
// Town town = new Town();
// town.setTownshipinfName(cellName);
// town.setKey(cellKey);
// town.setParentId(Long.valueOf(parentId));
// if (townDAO.select(town) != null) {
// townId = townDAO.select(town).getTownshipinfoid();
// List<Town> townValige = this.selectTown("", townId);
// for (Town town2 : townValige) {
// // 同一个镇下的 村编号重复
// if (town2.getKey().equals(villageKeyNew) && town2.getTownshipinfName().equals(villageNameNew)) {
// } else if (town2.getKey().equals(villageKeyNew)) {
// sb6.append("第" + (i + 1) + "行，");
// message.setSuccess(false);
// }
// }
// }
		}
		StringBuffer returnString = new StringBuffer();
		if (!"".equals(sb5.toString())) {
			sb5.append("镇已经存在");
			returnString.append("," + sb5);
		}
		if (!"".equals(sb6.toString())) {
			sb6.append("村已经存在");
			returnString.append("," + sb6);
		}
		if (!"".equals(sb7.toString())) {
			sb7.append("镇编号或名称与数据库中现有编号名称不符");
			returnString.append("," + sb7);
		}
		if (!"".equals(sb8.toString())) {
			sb8.append("村编号或名称在数据库中已存在");
			returnString.append("," + sb8);
		}
		// message.setMsg(sb5.toString());
		message.setMsg(returnString.toString());
		return message;
	}

	/**
	 * 验证 编号与数据库 重复
	 * 
	 * @param sheet
	 * @param parentId
	 * @return Message
	 */
	public Message validateDataBase(Sheet sheet, long parentId) {
		StringBuffer sb5 = new StringBuffer();
		StringBuffer sb6 = new StringBuffer();
		Message message = new Message();
		message.setSuccess(true);
		long townId = 0;
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			if ((sheet.getRow(i).getCell(0) == null || ""
					.equals(sheet.getRow(i).getCell(0).getStringCellValue().trim()))
					&& (sheet.getRow(i).getCell(0) == null || "".equals(sheet.getRow(i).getCell(0).getStringCellValue()
							.trim()))
					&& (sheet.getRow(i).getCell(0) == null || "".equals(sheet.getRow(i).getCell(0).getStringCellValue()
							.trim()))
					&& (sheet.getRow(i).getCell(0) == null || "".equals(sheet.getRow(i).getCell(0).getStringCellValue()
							.trim()))
					&& (sheet.getRow(i).getCell(0) == null || "".equals(sheet.getRow(i).getCell(0).getStringCellValue()
							.trim()))) {
				continue;
			}
			String cellName = sheet.getRow(i).getCell(0).getStringCellValue().trim();
			String cellKey = sheet.getRow(i).getCell(1).getStringCellValue().trim();
			String villageNameNew = sheet.getRow(i).getCell(2).getStringCellValue().trim();
			String villageKeyNew = sheet.getRow(i).getCell(3).getStringCellValue().trim();
			// 判断镇 与数据库数据重复
			List<Town> towns = this.selectTown("", parentId);
			for (Town town2 : towns) {
				if (town2.getKey().equals(cellKey)) {
					sb5.append("第" + (i + 1) + "行，");
					message.setSuccess(false);
				}
			}
			// 如果真已经 存在 判断 村 是否 与数据库重复
			Town town = new Town();
			town.setTownshipinfName(cellName);
			town.setKey(cellKey);
			town.setParentId(Long.valueOf(parentId));
			if (townDAO.select(town) != null) {
				townId = townDAO.select(town).getTownshipinfoid();
				List<Town> townValige = this.selectTown("", townId);
				for (Town town2 : townValige) {
					// 同一个镇下的 村编号重复
					if (town2.getKey().equals(villageKeyNew) && town2.getTownshipinfName().equals(villageNameNew)) {
					} else if (town2.getKey().equals(villageKeyNew)) {
						sb6.append("第" + (i + 1) + "行，");
						message.setSuccess(false);
					}
				}
			}
		}
		StringBuffer returnString = new StringBuffer();
		if (!"".equals(sb5.toString())) {
			sb5.append("镇编号已经存在");
			returnString.append("," + sb5);
		}
		if (!"".equals(sb6.toString())) {
			sb6.append("村编号已经存在");
			returnString.append("," + sb6);
		}
		message.setMsg(sb5.toString());
		message.setMsg(returnString.toString());
		return message;
	}

	@Override
	public Town searchParentId(long townshipinfoid) {
		// TODO Auto-generated method stub
		return (Town) townDAO.queryUnique("TOWNINFO.searchById", townshipinfoid);
	}

	@Override
	public Map searchNscByVillageId(Long townshipinfoid) {
		return townDAO.searchNscByVillageId(townshipinfoid);
	}

	@Override
	public Message searchTownshipId(Long parentId, String key, Long townShipInfoId) {
		Message message = new Message();
		message.setSuccess(true);
		if (parentId != null && !"".equals(parentId) && key != null && !"".equals(key)) {
			List<Town> towns = this.selectTown("", parentId);
			for (Town town : towns) {
				if (townShipInfoId == null) {
					if (town.getKey() != null) {
						if (town.getKey().equals(key)) {
							message.setSuccess(false);
							message.setMsg("镇编号已经存在");
						}
					}
				} else {
					if (town.getKey() != null) {
						if (town.getKey().equals(key) && Long.valueOf(town.getTownshipinfoid()).equals(townShipInfoId)) {
							message.setSuccess(true);
						} else if (town.getKey().equals(key)
								&& !(Long.valueOf(town.getTownshipinfoid()).equals(townShipInfoId))) {
							message.setSuccess(false);
							message.setMsg("镇编号已经存在");
						}
					} else {

					}
				}
			}
		}
		return message;
	}

	@Override
	public Map<String, String> getVillageMapKeyIsName(List<QueryCheckTownsVo> queryCheckTownsVos) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		for (QueryCheckTownsVo queryCheckTownsVo : queryCheckTownsVos) {
			String getTownKey = queryCheckTownsVo.getTownKey();
			String getTownName = queryCheckTownsVo.getTownName();
			String getVillageKey = queryCheckTownsVo.getVillageKey();
			String getVillageName = queryCheckTownsVo.getVillageName();
			String mapKey = getTownKey + "_" + getTownName + "_" + getVillageName;
			map.put(mapKey, getVillageKey);
		}
		return map;
	}

	@Override
	public Map<String, String> getVillageMapKeyIsKey(List<QueryCheckTownsVo> queryCheckTownsVos) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		for (QueryCheckTownsVo queryCheckTownsVo : queryCheckTownsVos) {
			String getTownKey = queryCheckTownsVo.getTownKey();
			String getTownName = queryCheckTownsVo.getTownName();
			String getVillageKey = queryCheckTownsVo.getVillageKey();
			String getVillageName = queryCheckTownsVo.getVillageName();
			String mapKey = getTownKey + "_" + getTownName + "_" + getVillageKey;
			map.put(mapKey, getVillageName);
		}
		return map;
	}

	@Override
	public Map<String, String> getTownMapKeyIsName(List<QueryCheckTownsVo> queryCheckTownsVos) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		for (QueryCheckTownsVo queryCheckTownsVo : queryCheckTownsVos) {
			map.put(queryCheckTownsVo.getTownName(), queryCheckTownsVo.getTownKey());
		}
		return map;
	}

	@Override
	public Map<String, String> getTownMapKeyIsKey(List<QueryCheckTownsVo> queryCheckTownsVos) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		for (QueryCheckTownsVo queryCheckTownsVo : queryCheckTownsVos) {
			map.put(queryCheckTownsVo.getTownKey(), queryCheckTownsVo.getTownName());
		}
		return map;
	}

	@Override
	public List<QueryCheckTownsVo> queryCheckTowns(long parentId) {
		// TODO Auto-generated method stub
		return townDAO.queryCheckTowns(parentId);
	}
}
