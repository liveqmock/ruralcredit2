package com.creditease.rc.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.creditease.rc.common.Constants;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.OtherFileUpload;
import com.creditease.rc.domain.Town;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.ITownService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.vo.Area;
import com.creditease.rc.vo.QueryCheckTownsVo;
import com.creditease.rc.vo.TownVo;

/**
 * 
 * @author zhangman
 * 
 */
@Controller
@RequestMapping("town")
public class TownController {
	@Resource
	private ITownService townService;

	/**
	 * 分页 ，模糊查询
	 * 
	 * @param page
	 *            第几页
	 * @param rows
	 *            每页显示行数
	 * @param townlist
	 *            乡镇信息vo类
	 * @param fuzzy
	 *            模糊查询条件
	 * @return 返回乡镇信息
	 */
	@RequestMapping(value = "listTown")
	public @ResponseBody
	Pagination selectAllTown(Area townlist, String fuzzy, String page, String rows) {
		Pagination pagination = new Pagination();
		if (!(StringUtils.isBlank(page))) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (!(StringUtils.isBlank(rows))) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		Pagination towns = new Pagination();
		if (fuzzy != null) {
			towns = townService.selectFuzzy(fuzzy, pagination);
		} else {
			towns = townService.selectAllTown(townlist, pagination);
		}

		return towns;
	}

	/**
	 * 按照名称,父ID模糊查询,
	 * 
	 * @param q
	 *            乡镇名称 easyui-combobox传值
	 * @param parentId
	 *            父id（镇id 或 县city-code）
	 * @return 乡镇列表
	 */
	@RequestMapping(value = "list")
	public @ResponseBody
	List<Town> selectTown(String q, @RequestParam("parentId") Long parentId) {
		return townService.selectTown(q, parentId);
	}

	/**
	 * 
	 * @param type
	 *            向上查找 向下先找类型 判断
	 * @param townshipinfoid
	 *            乡镇信息id
	 * @return 乡镇信息List
	 */
	@RequestMapping(value = "lisByVillage")
	public @ResponseBody
	List<Town> selectTownVillage(String type, @RequestParam("parentId") Long townshipinfoid) {
		return townService.queryTownshipInfo(type, townshipinfoid);
	}

	/**
	 * 新增 乡镇信息
	 * 
	 * @param towns
	 *            县镇信息封装类
	 * @return message 对象
	 * @throws Exception
	 *             抛出异常
	 */
	@RequestMapping(value = "addTown")
	public @ResponseBody
	Message addTown(TownVo towns) throws Exception {
		Message message = new Message();
		// if(uploadFile != null && uploadFile !=""){
		// FileInputStream inputStream = new FileInputStream(uploadFile);
		//
		// }else{
		if (towns != null) {
			Town[] townsArray = towns.getListTown();
			String townKey = townsArray[0].getKey().toUpperCase();
			String townName = townsArray[0].getTownshipinfName();
			Long parentId = townsArray[0].getParentId();
			String villageKey = townsArray[1].getKey().toUpperCase();
			String villageName = townsArray[1].getTownshipinfName();
			townsArray[0].setKey(townKey);
			townsArray[1].setKey(villageKey);
			List<QueryCheckTownsVo> queryCheckTownsVos = townService.queryCheckTowns(parentId);
			Map<String, String> townMapKeyIsKey = townService.getTownMapKeyIsKey(queryCheckTownsVos);
			Map<String, String> townMapKeyIsName = townService.getTownMapKeyIsName(queryCheckTownsVos);
			Map<String, String> villageMapKeyIsKey = townService.getVillageMapKeyIsKey(queryCheckTownsVos);
			Map<String, String> villageMapKeyIsName = townService.getVillageMapKeyIsName(queryCheckTownsVos);

			// 判断镇是否与数据库中的相符
			String dbtownName = townMapKeyIsKey.get(townKey);
			String dbtownKey = townMapKeyIsName.get(townName);

			if (CommonUtil.isEmpty(dbtownName) && CommonUtil.isEmpty(dbtownKey)) {
				// 验证有没有这个镇
				// 不用验证村 直接插入
			} else if (CommonUtil.isNotEmpty(dbtownName) && CommonUtil.isNotEmpty(dbtownKey)
					&& townName.equals(dbtownName) && townKey.equals(dbtownKey)) {
				// 如果 镇 没有问题 再来验证村
				String dbvillageName = villageMapKeyIsKey.get(townKey + "_" + townName + "_" + villageKey);
				String dbvillageKey = villageMapKeyIsName.get(townKey + "_" + townName + "_" + villageName);
				if (CommonUtil.isEmpty(dbvillageName) && CommonUtil.isEmpty(dbvillageKey)) {
					// 验证有没有这个村
					// 没有村直接插入
				} else if (CommonUtil.isNotEmpty(dbvillageName) && CommonUtil.isNotEmpty(dbvillageKey)
						&& villageName.equals(dbvillageName) && villageKey.equals(dbvillageKey)) {
					// 判断是否与数据库中村相同
					message.setSuccess(false);
					message.setMsg("该镇下的村在数据库中已存在！");
					return message;
				} else {
					message.setSuccess(false);
					message.setMsg("该镇下的村名称或编码在数据库中已存在！");
					return message;
				}
			} else {
				message.setSuccess(false);
				message.setMsg("镇编号或名称与数据库中现有编号名称不符！");
				return message;
			}

		}

		message = townService.addTown(towns);
		return message;
	}

	/**
	 * @param towns
	 *            县镇信息list对象
	 * @return message对象
	 */
	@RequestMapping(value = "update")
	public @ResponseBody
	Message updateTown(Area towns) {
		Message message = new Message();
		boolean flag = false;

		if (towns != null) {
			String townKey = towns.getTownCode().toUpperCase();
			String townName = towns.getTown();
			Long parentId = Long.parseLong(towns.getDistrictCode());
			String villageKey = towns.getCode().toUpperCase();
			String villageName = towns.getVillage();
			List<QueryCheckTownsVo> queryCheckTownsVos = townService.queryCheckTowns(parentId);
			Map<String, String> townMapKeyIsKey = townService.getTownMapKeyIsKey(queryCheckTownsVos);
			Map<String, String> townMapKeyIsName = townService.getTownMapKeyIsName(queryCheckTownsVos);
			Map<String, String> villageMapKeyIsKey = townService.getVillageMapKeyIsKey(queryCheckTownsVos);
			Map<String, String> villageMapKeyIsName = townService.getVillageMapKeyIsName(queryCheckTownsVos);

			// 判断镇是否与数据库中的相符
			String dbtownName = townMapKeyIsKey.get(townKey);
			townMapKeyIsName.remove(dbtownName);
			String dbtownKey = townMapKeyIsName.get(townName);

			if (CommonUtil.isEmpty(dbtownKey)) {
				// 如果 镇 没有问题 再来验证村
				String dbvillageName = villageMapKeyIsKey.get(townKey + "_" + dbtownName + "_" + villageKey);
				villageMapKeyIsName.remove(townKey + "_" + dbtownName + "_" + dbvillageName);
				String dbvillageKey = villageMapKeyIsName.get(townKey + "_" + dbtownName + "_" + villageName);
				if (CommonUtil.isEmpty(dbvillageKey)) {
					// 没有重复的村
				} else {
					message.setSuccess(false);
					message.setMsg("该村名称已存在！");
					return message;
				}
			} else {
				message.setSuccess(false);
				message.setMsg("该镇名称已存在！");
				return message;
			}

		}

		flag = townService.updateTown(towns);
		message.setSuccess(flag);
		if (flag) {
			message.setMsg("修改成功！");
		} else {
			message.setMsg("修改失败！");
		}
		return message;
	}

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
	@RequestMapping(value = "varifyTownshipId")
	public @ResponseBody
	Message varifyTownshipId(Long parentId, String key, Long townShipInfoId) {
		return townService.searchTownshipId(parentId, key, townShipInfoId);
	}

	/**
	 * 
	 * @param parentId
	 *            父id(镇id，县id)
	 * @param key
	 *            手录编号
	 * @return message对象
	 */
	@RequestMapping(value = "varifyVillage")
	public @ResponseBody
	Message varifyVillage(Long parentId, String key) {
		Message message = new Message();
		List<Town> towns = townService.selectTown("", parentId);
		message.setSuccess(true);
		for (Town town : towns) {
			if (town.getKey().equals(key)) {
				message.setSuccess(false);
			}
		}
		return message;
	}

	/**
	 * 
	 * @param caId
	 *            sd
	 * @param uploadFileList
	 *            fd
	 * @param uploadFileTypeList
	 *            df
	 * @param originalFileList
	 *            ser
	 * @return s
	 */

	public String groupAllSave(Integer caId, String[] uploadFileList, String[] uploadFileTypeList,
			String[] originalFileList) {

		List<OtherFileUpload> listTemp = new ArrayList<OtherFileUpload>();
		if (null != uploadFileList && null != uploadFileTypeList) {
			String[] nameArr = uploadFileList;// UUID之后的名字
			String[] originalName = originalFileList;// 原来的名字
			// TODO 根据名字划分小组内各自的文件及共同文件
			int len = nameArr.length;
			for (int i = 0; i < len; i++) {
				System.out.println(nameArr[i] + "---------" + originalName[i]);
				// TODO originalName中取出标识、身份证
				if (CommonUtil.isNotEmpty(originalName[i])) {
					String[] s = StringUtils.split(originalName[i], ".");
					String type = s[0];
					if (CommonUtil.isNotEmpty(type)) {
						String[] filename = StringUtils.split(type, "_");
						String identifier = filename[0];
						String myType = filename[filename.length - 1];
						if (CommonUtil.isNotEmpty(myType) && "G".equals(myType)) {
							// TODO 如果为小组，与小组关联
							OtherFileUpload otherFileUpload = new OtherFileUpload();
							otherFileUpload.setUploadFileName(nameArr[i]);
							otherFileUpload.setFileType(Constants.FILE_TYPE_GROUP_FILES);
							otherFileUpload.setUploadDependClass(Constants.CREDIT_APPLICATION_CLASS);
							otherFileUpload.setFileSubType(uploadFileTypeList[i]);
							otherFileUpload.setUploadDependId(caId);
							// 将新数据添加到原来的set集合里,存储数据用
							listTemp.add(otherFileUpload);
						} else if (CommonUtil.isNotEmpty(identifier)) {
							Map searchMap = new HashMap();
							searchMap.put("credentialsNumber", identifier);
							searchMap.put("creditapplicationId", caId);
							// TODO 根据身份证查出个人，与个人资料关联

						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * （郝强）要改
	 * 上传文件
	 * 
	 * @param file 文件
	 * @param parentId 父id
	 * @param response 回应
	 * @return Message 消息
	 * @throws Exception 异常
	 */
	@RequestMapping(value = "uploadfile")
	public @ResponseBody
	Message uploadfile(@RequestParam("file") MultipartFile file, String parentId, HttpServletResponse response)
			throws Exception {
		Message message = new Message();
		InputStream is = file.getInputStream();
// PrintWriter out = response.getWriter();
// String results;
// response.setContentType("text/html;charset=UTF-8");
// response.setCharacterEncoding("UTF-8");
		int count = 0;
		Workbook workbook = WorkbookFactory.create(is);
		if (workbook == null) {
			message.setMsg("您选择的不是excel文件，请重新选择！");
			message.setSuccess(false);
//
// results = "false-您选择的不是excel文件，请重新选择！";
		} else {

			Sheet sheet = workbook.getSheetAt(0);
			// 判断空数据
			Map result = validateNull(sheet);
			boolean flag = (Boolean) result.get("flag");
			if (flag == false) {
// message = (Message) result.get("message");
				message.setSuccess(flag);
				message.setMsg(result.get("message").toString());
// results = "false-您选择的不是excel文件，请重新选择！";
			} else {
				// 添加 判断空数据 （郝强验证）
				Map resultOnly = townService.addTowns(sheet, parentId);
				flag = (Boolean) resultOnly.get("flag");
				message.setSuccess(flag);
				message.setMsg(resultOnly.get("message").toString());
				if (flag) {
					count = (Integer) resultOnly.get("count");
				}
			}
		}
// out.print(results);
// out.close();

		if (message.isSuccess()) {
			message.setMsg("导入成功！共导入" + count + "条数据。");
		}

		return message;
	}

	/**
	 * 
	 * @param is
	 *            数据流
	 * @param filename
	 *            文件名
	 * @return 工作簿
	 * @throws IOException
	 *             抛出异常
	 */

	private Workbook createWorkBook(String filename, InputStream is) throws IOException {
		if (filename.toLowerCase().endsWith("xls")) {
			return new HSSFWorkbook(is);
		}
		if (filename.toLowerCase().endsWith("xlsx")) {
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			return workbook;
		}
		return null;
	}

	/**
	 * 
	 * @param sheet
	 *            excel页面
	 * @return true：不存在空数据，false：存在空数据
	 */
	private Map validateNull(Sheet sheet) {
		Map result = new HashMap();
		boolean flag = true;
		Message message = new Message();
		StringBuffer sb = new StringBuffer();

		// 这里有空指针
		// 判断空数据 以及具体位置
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if ((row.getCell(0) == null || "".equals(row.getCell(0).toString().trim()))
					&& (row.getCell(1) == null || "".equals(row.getCell(1).toString().trim()))
					&& (row.getCell(2) == null || "".equals(row.getCell(2).toString().trim()))
					&& (row.getCell(3) == null || "".equals(row.getCell(3).toString().trim()))
					&& (row.getCell(4) == null || "".equals(row.getCell(4).toString().trim()))) {
				continue;
			}
			for (int j = 0; j < 5; j++) {
				Cell cell = row.getCell(j);
				if (cell == null || "".equals(cell.toString().trim())) {
					sb.append("第" + (i + 1) + "行,第" + (j + 1) + "列,");
					flag = false;
				}
			}
		}
		if (!("").equals(sb.toString())) {
			sb.append("数据为空，请填写完整！");
			message.setMsg(sb.toString());
			message.setSuccess(false);
		}
		if (sb.length() > 30) {
			sb.append("数据为空，请填写完整！");
			message.setMsg(sb.toString().substring(0, 30) + "......" + "数据为空，请填写完整！(可能excel不符合格式要求)");
			message.setSuccess(false);
		}

		result.put("flag", flag);
		result.put("message", message.getMsg());
		return result;
	}

	/**
	 * 
	 * @param townshipinfoid d
	 * @return d
	 */
	@RequestMapping(value = "searchParentId")
	public @ResponseBody
	Town searchParentId(Long townshipinfoid) {
		return townService.searchParentId(townshipinfoid);
	}

	/**
	 * 查询村所在的省市区县镇编码
	 * 
	 * @author wyf
	 * @param: townshipinfoid townshipinfoid
	 * @return: Map<String,Integer>
	 */
	@RequestMapping(value = "searchNscByVillageId")
	@ResponseBody
	public Map searchNscByVillageId(Long townshipinfoid) {
		Map cityCodes = new HashMap<String, Integer>();
		cityCodes = townService.searchNscByVillageId(townshipinfoid);
		return cityCodes;
	}
}
