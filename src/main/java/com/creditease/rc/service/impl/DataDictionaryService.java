	/**
 *
	*/
package com.creditease.rc.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IDataDictionaryDao;
import com.creditease.rc.domain.CodeTable;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.exception.AppBusinessException;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IDataDictionaryService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DicComparator;
import com.creditease.rc.util.DicUtil;
import com.creditease.rc.vo.DataDictionaryVo;

/**
 * Title:
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2012-6-26 
 * @author 韩大年
 * @version v2.0
 */
@Service
public class DataDictionaryService implements IDataDictionaryService{

	/**
	 * 
	 */
	public DataDictionaryService() {
		// TODO Auto-generated constructor stub
	}
	@Resource
	private IDataDictionaryDao dataDictionaryDao;
	/**
	 * 
	 *@author 韩大年
	 *@function：   获取所有字典
	 *2012-6-26
	 */
	@PostConstruct
	public void initGetAllDic(){
		/*List<String> sections = getSection();
		if(CommonUtil.isNotEmpty(sections)){
			for(String key:sections){
				initDicSection(key);
			}
		}*/
		//查询所有字典的SECTION
		List<String> sectionList = this.getSection();
		//查询所有字典对象
		List<CodeTable> codeTableList=this.getCodeTableByAll();
		if(CommonUtil.isNotEmpty(sectionList) && CommonUtil.isNotEmpty(codeTableList)){
			for(String section:sectionList){
				if(CommonUtil.isEmpty(section)){
					continue;
				}
				List<CodeTable> setionCodeTableList= new ArrayList<CodeTable>();
				CodeTable element = new CodeTable();
				element.setCodeKey("");
				element.setCodeVlue("请选择");
				setionCodeTableList.add(0, element);
				Map<String, String> keyValueMap = new HashMap<String, String>();
				keyValueMap.put("", "请选择");
				for(CodeTable codeTable:codeTableList){
					if(section.equalsIgnoreCase(codeTable.getSection())){
						setionCodeTableList.add(codeTable);
						keyValueMap.put(codeTable.getCodeKey(), codeTable.getCodeVlue());
					}
				}
				Collections.sort(setionCodeTableList,new DicComparator());
				DicUtil.map.put(section,setionCodeTableList);
				DicUtil.sectionMap.put(section,keyValueMap);
				
			}
		}
		
	}

	@Override
	public void saveCodeTable(CodeTable codeTable)throws Exception {
		dataDictionaryDao.saveCodeTable(codeTable);
	}

	@Override
	public Pagination getCodeTableByPagination(DataDictionaryVo dataDictionaryVo,Pagination pagination) {
		return dataDictionaryDao.getCodeTableByPagination(dataDictionaryVo,pagination);
	}

	@Override
	public List<CodeTable> getCodeTableByAll() {
		return dataDictionaryDao.getCodeTableByAll();
	}

	@Override
	public void deleteCodeTables(List<Integer> codeTableIds)throws Exception {
		dataDictionaryDao.batchDeleteCodeTable(codeTableIds);
	}

	@Override
	public void updateCodeTable(CodeTable codeTable) throws Exception{
		dataDictionaryDao.updateCodeTable(codeTable);
	}

	@Override
	public boolean getCodeTableBySection(String codekey,boolean type,DataDictionaryVo dictionaryVo) {
		dictionaryVo.setSelectType("V");
		List<CodeTable> codeTables = dataDictionaryDao.getCodeTableBySection(dictionaryVo);
		for(CodeTable codeTable:codeTables){
			if(type){
				if(codeTable.getCodeKey().equals(codekey)){
					return false;
				}
			}else{
				if(codeTable.getCodeVlue().equals(codekey)){
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void batchUpdate(List<CodeTable> codeTables) throws Exception {
		dataDictionaryDao.batchUpdateCodeValue(codeTables);
	}

	@Override
	public List getSection() {
		return dataDictionaryDao.getSection();
	}
	@Override
	public void initGetAllDic(String  dicArray){
		DicUtil.map = new HashMap<String, List<CodeTable>>();
		String[] arr=dicArray.split(",");
		for(String key:arr){
			DataDictionaryVo dictionaryVo = new DataDictionaryVo();
			dictionaryVo.setSection(key);
			dictionaryVo.setSelectType("E");
			List<CodeTable> list = dataDictionaryDao.getCodeTableBySection(dictionaryVo);
			Collections.sort(list,new DicComparator());
			CodeTable element = new CodeTable();
			element.setCodeKey("");
			element.setCodeVlue("请选择");
			list.add(0, element);
			DicUtil.map.put(key, list);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getSpecifiedDic(String q, String section) {
		List<CodeTable> list = new ArrayList<CodeTable>();
		DataDictionaryVo dictionaryVo = new DataDictionaryVo();
		dictionaryVo.setSection(section);
		dictionaryVo.setCodeValue(q);
		dictionaryVo.setSelectType("E");
		list = dataDictionaryDao.getCodeTableBySection(dictionaryVo);
		return list;
	}
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param section     
	* void
	 */
	private void initDicSection(String section){
		DataDictionaryVo dictionaryVo = new DataDictionaryVo();
		dictionaryVo.setSection(section);
		dictionaryVo.setSelectType("E");
		List<CodeTable> bankList = dataDictionaryDao.getCodeTableBySection(dictionaryVo);
		Collections.sort(bankList,new DicComparator());
		CodeTable element = new CodeTable();
		element.setCodeKey("");
		element.setCodeVlue("请选择");
		bankList.add(0, element);
		DicUtil.map.put(section, bankList);
		if(CommonUtil.isNotEmpty(bankList)){
			Map<String, String>  codeKeyMap = new HashMap<String, String>();
			codeKeyMap.put("", "请选择");
			for(CodeTable codeTable :bankList){
				codeKeyMap.put(codeTable.getCodeKey(), codeTable.getCodeVlue());
			}
			DicUtil.sectionMap.put(section, codeKeyMap);
		}
	}
	@Override
	public void modifyGetAllDic(CodeTable[] delete,CodeTable[] update,CodeTable insert) {
		if(delete!=null && delete.length>0){
			initDicSection(delete[0].getSection());
		}
		if(update!=null && update.length>0){
			initDicSection(update[0].getSection());
		}
		if(insert!=null){
			initDicSection(insert.getSection());
		}
	}
	
	@Override
	public List<CodeTable> getCodeTableBySection(
			DataDictionaryVo dictionaryVo) {
		return dataDictionaryDao.getCodeTableBySection(dictionaryVo);
	}

	@Override
	public void impExcelTemplate(InputStream is) throws Exception {
		Workbook workbook = WorkbookFactory.create(is);
		Sheet sheet = workbook.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();
		List<CodeTable> codeTables = new ArrayList<CodeTable>();
		Set<String> sections = new HashSet<String>();
		List<String[]> list = getAllData(workbook, 0);
		for(int i=1;i<list.size();i++){
			String[] val = list.get(i);
//			System.err.println("xxxxxxxxxxxx=============="+i);
			if(val.length>=5){
				CodeTable codeTable = new CodeTable();
				codeTable.setSection(val[0]);
				codeTable.setCodeKey(val[1]);
				codeTable.setCodeVlue(val[2]);
				codeTable.setCodeNote(val[3]);
				codeTable.setSequence(val[4]);
				codeTable.setAvailable(val[5]);
				//判断是否重复
				Boolean b=DicUtil.sectionMap.containsKey(codeTable.getSection());
				if(b){
					Map<String, String>map =DicUtil.sectionMap.get(codeTable.getSection());
					if(map.containsKey(codeTable.getCodeKey())){
						throw new AppBusinessException(i+1+" row data is duplicate record");
					}
				}
				
				codeTables.add(codeTable);
				sections.add(codeTable.getSection());
			}
		
		}
		if(CommonUtil.isNotEmpty(codeTables)){
			dataDictionaryDao.batchAddCodeTable(codeTables);
			if(!sections.isEmpty()){
				Iterator<String> section = sections.iterator();
				while(section.hasNext()){
					initDicSection(section.next());
				}
			}
		}
	}
	
	/**
	 * 取Excel所有数据，包含header
	* checkstyle
	* @author wyf   
	* @param workbook 
	* @param sheetIndex 
	* @return    
	* List<String[]>
	 */
	private List<String[]> getAllData(Workbook workbook,int sheetIndex) {
		List<String[]> dataList = new ArrayList<String[]>(100);
		int columnNum = 0;
		Sheet sheet = workbook.getSheetAt(sheetIndex);
		if (sheet.getRow(0) != null) {
			columnNum = sheet.getRow(0).getLastCellNum()- sheet.getRow(0).getFirstCellNum();
		}
		if (columnNum > 0) {
			for (Row row : sheet) {
				String[] singleRow = new String[columnNum];
				int n = 0;
				for (int i = 0; i < columnNum; i++) {
					Cell cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						singleRow[n] = "";
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						singleRow[n] = Boolean.toString(cell
								.getBooleanCellValue());
						break;
					// 数值
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							singleRow[n] = String.valueOf(cell
									.getDateCellValue());
						} else {
							cell.setCellType(Cell.CELL_TYPE_STRING);
							String temp = cell.getStringCellValue();
							// 判断是否包含小数点，如果不含小数点，则以字符串读取，如果含小数点，则转换为Double类型的字符串
							if (temp.indexOf(".") > -1) {
								singleRow[n] = String.valueOf(new Double(temp))
										.trim();
							} else {
								singleRow[n] = temp.trim();
							}
						}
						break;
					case Cell.CELL_TYPE_STRING:
						singleRow[n] = cell.getStringCellValue().trim();
						break;
					case Cell.CELL_TYPE_ERROR:
						singleRow[n] = "";
						break;
					case Cell.CELL_TYPE_FORMULA:
						cell.setCellType(Cell.CELL_TYPE_STRING);
						singleRow[n] = cell.getStringCellValue();
						if (singleRow[n] != null) {
							singleRow[n] = singleRow[n].replaceAll("#N/A", "")
									.trim();
						}
						break;
					default:
						singleRow[n] = "";
						break;
					}
					n++;
				}
				if ("".equals(singleRow[0])) {
					continue;
				}// 如果第一行为空，跳过
				dataList.add(singleRow);
			}
		}
		return dataList;
	}


	@Override
	public List<CodeTable> nationalCombobox(String value) {
		// TODO Auto-generated method stub
		return dataDictionaryDao.nationalCombobox(value);
	}

	@Override
	public void deleteCodeTable(CodeTable codeTable) throws Exception {
		dataDictionaryDao.deleteCodeTable(codeTable);
	}

	@Override
	public Pagination getCodeTableBySerialNum(Pagination pagination) {
		return dataDictionaryDao.getCodeTableBySerialNum(pagination);
	}
	@Override
	public String getCodeValueByKey(String section,String key){
		DataDictionaryVo dataDictionaryVo = new DataDictionaryVo();
		dataDictionaryVo.setCodeKey(key);
		dataDictionaryVo.setSection(section);
		return dataDictionaryDao.getCodeValueByKey(dataDictionaryVo);
	}
	@Override
	public List<CodeTable> selectByParentId(Integer parentId) {
		// TODO Auto-generated method stub
		return dataDictionaryDao.selectByParentId(parentId);
	}
	@Override
	public List<Map<String, Object>> selectSection(String q) {
		// TODO Auto-generated method stub
		 List<Map<String, Object>> list = dataDictionaryDao.selectSection(q);
		 return list;
	}
	
	@Override
	public Message updateParentId(Integer parentId, String codeIds) {
		Message message = new Message();
		codeIds = codeIds.substring(1, codeIds.length()-1);
		int result = dataDictionaryDao.updateParentId(parentId, codeIds);
		if(result > 0){
			message.setSuccess(true);
		}else{
			message.setMsg("没有数据更新");
		}
		return message;
	}
	
	@Override
	public Message deleteParentId(String codeIds) {
		Message message = new Message();
		codeIds = codeIds.substring(1, codeIds.length()-1);
		int result = dataDictionaryDao.deleteParentId(codeIds);
		if(result > 0){
			message.setSuccess(true);
		}else{
			message.setMsg("没有数据更新");
		}
		return message;
	}

    @Override
    public List<CodeTable> selectBySectionAndParentId(String section, String parentId) {
        Map map = new HashMap();
        map.put("section",section);
        map.put("parentId",parentId);
        return dataDictionaryDao.selectBySectionAndParentId(map);
    }

}
