package com.creditease.rc.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.creditease.rc.dao.IDataDictionaryDao;
import com.creditease.rc.domain.CodeTable;
import com.creditease.rc.framework.dao.impl.BaseDao;
import com.creditease.rc.framework.pager.Pagination;
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
@Repository
public class DataDictionaryDao extends BaseDao implements IDataDictionaryDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<CodeTable> getCodeTableBySection(DataDictionaryVo dictionaryVo) {
		List<CodeTable> codeTables = new ArrayList<CodeTable>();
		if(dictionaryVo!=null){
			if("A".equals(dictionaryVo.getSelectType())){//查询所有
				codeTables = (List<CodeTable>)queryList("codeTable.selectBySection",dictionaryVo);
			}else if("E".equals(dictionaryVo.getSelectType())){//查询启用
				codeTables = (List<CodeTable>)queryList("codeTable.selectByEnabled",dictionaryVo);
			}else if("V".equals(dictionaryVo.getSelectType())){//查询校验
				codeTables = (List<CodeTable>)queryList("codeTable.selectByValidate",dictionaryVo);
			}
		}
		return codeTables;
	}

	@Override
	public void saveCodeTable(CodeTable codeTable) {
		insert("codeTable.insertCodeTable",codeTable);
	}

	@Override
	public Pagination getCodeTableByPagination(DataDictionaryVo dataDictionaryVo,Pagination pagination) {
		return queryForPaginatedList("codeTable.selectByPagination", "codeTable.countByPage",
				dataDictionaryVo, pagination.getStartResult(), pagination.getPageSize());
	}

	@Override
	public void updateCodeTable(CodeTable codeTable) {
		update("codeTable.updateCodeVal", codeTable);
	}

	@Override
	public List<CodeTable> getCodeTableByAll() {
		return (List<CodeTable>) queryList("codeTable.selectByAll");
	}

	@Override
	public void batchUpdateCodeValue(List<CodeTable> codeTables) {
		batchUpdate("codeTable.updateCodeVal", codeTables);
	}

	@Override
	public void batchDeleteCodeTable(List<Integer> codeTableIds) {
		batchDelete("codeTable.batchDelete", codeTableIds);
	}

	@Override
	public List getSection() {
		return queryList("codeTable.getSection");
	}

	@Override
	public void batchAddCodeTable(List<CodeTable> codeTableIds) {
		batchInsert("codeTable.insertCodeTable", codeTableIds);
	}

	@Override
	public List<CodeTable> nationalCombobox(String value) {
		// TODO Auto-generated method stub
		return (List<CodeTable>) queryList("codeTable.nationalCombobox",value);
	}

	@Override
	public void deleteCodeTable(CodeTable codeTable) {
		this.delete("codeTable.deleteObject", codeTable);
	}

	@Override
	public Pagination getCodeTableBySerialNum(Pagination pagination) {
		return queryForPaginatedList("codeTable.selectBySerialNum", "codeTable.countBySerialNum",
				null, pagination.getStartResult(), pagination.getPageSize());
	}
	@Override
	public String getCodeValueByKey(DataDictionaryVo dataDictionaryVo){
		return (String) queryUnique("codeTable.selectCodeValueByKey", dataDictionaryVo);
	}
	@Override
	public List<CodeTable> selectByParentId(Integer parentId) {
		// TODO Auto-generated method stub
		return (List<CodeTable>) queryList("codeTable.selectByParentId",parentId);
	}
	@Override
	public List<Map<String, Object>> selectSection(String section) {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>) queryList("codeTable.selectSection",section);
	}
	
	@Override
	public int updateParentId(Integer parentId, String codeIds) {
		// TODO Auto-generated method stub
		Map conditons = new HashMap();
		conditons.put("parentId", parentId);
		conditons.put("codeIds", codeIds);
		return update("codeTable.updateParentId", conditons);
	}
	
	@Override
	public int deleteParentId(String codeIds) {
		Map conditons = new HashMap();
		conditons.put("codeIds", codeIds);
		return update("codeTable.deleteParentId", conditons);
	}

    public List<CodeTable> selectBySectionAndParentId(Map map) {
        return (List<CodeTable>) queryList("codeTable.selectBySectionAndParentId", map);
    }
}
