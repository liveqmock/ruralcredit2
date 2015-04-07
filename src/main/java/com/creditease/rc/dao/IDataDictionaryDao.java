	/**
 *
	*/
package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.CodeTable;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.DataDictionaryVo;

/**
 * 
 * cs
 * Title:IDataDictionaryDao.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2013
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-6-6上午10:18:21
 * @author wyf
 * @version v2.0
 */
public interface IDataDictionaryDao{
	/**
	 * 依据section获取码表
	* @author wyf  
	* @Description: 
	* @param dictionaryVo 码表所属分类
	* @return List<CodeTable> 
	* @throws
	 */
	public List<CodeTable> getCodeTableBySection(DataDictionaryVo dictionaryVo);
	
	/**
	 * 分页查询codeTable
	 *@author yifengwang
	 *@param dataDictionaryVo 查询条件
	 *@param pagination 分页对象
	 *@return 2012-12-24下午02:44:29
	 */
	public Pagination getCodeTableByPagination(DataDictionaryVo dataDictionaryVo,Pagination pagination);
	/**
	 * 获取所有码表
	 *@author yifengwang
	 *@return 2012-12-24下午02:45:03
	 */
	public List<CodeTable> getCodeTableByAll();
	/**
	 * 添加codeTable 
	 *@author yifengwang
	 *@param codeTable 2012-12-24下午02:45:56
	 */
	
	public void saveCodeTable(CodeTable codeTable);
	/**
	 * 修改codeTable
	 *@author yifengwang
	 *@param codeTable 2012-12-24下午02:46:21
	 */
	public void updateCodeTable(CodeTable codeTable);
	
	/**
	 * 批量修改codeTable
	 *@author yifengwang
	 *@param codeTables 2012-12-24下午02:46:36
	 */
	public void batchUpdateCodeValue(List<CodeTable> codeTables);
	
	/**
	 * 批量删除
	 *@author yifengwang
	 *@param codeTableIds 2012-12-24下午03:25:42
	 */
	public void batchDeleteCodeTable(List<Integer> codeTableIds);
	/**
	 * 获取所有section
	 *@author yifengwang
	 *@return 2012-12-24下午03:25:59
	 */
	public List getSection();
	
	/**
	 * 少数民族专用
	* @author wyf   
	* @param value 
	* @return    
	* List<CodeTable>
	 */
	public List<CodeTable> nationalCombobox(String value);
	/**
	 * 批量增加
	 *@author yifengwang
	 *@param codeTableIds 2012-12-24下午03:25:42
	 */
	public void batchAddCodeTable(List<CodeTable> codeTableIds);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param codeTable    
	* void
	 */
	public void deleteCodeTable(CodeTable codeTable);
	/**
	 * 分页查询codeTable用于业务编码设置
	 *@author yifengwang
	 *@param pagination 分页对象
	 *@return 2012-12-24下午02:44:29
	 */
	public Pagination getCodeTableBySerialNum(Pagination pagination);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param dataDictionaryVo 
	* @return    
	* String
	 */
	public String getCodeValueByKey(DataDictionaryVo dataDictionaryVo);

	/**
	 * 
	 * @param parentId
	 * @return
	 */
	public List<CodeTable> selectByParentId(Integer parentId);

	public List<Map<String, Object>> selectSection(String section);

	public int updateParentId(Integer parentId, String codeIds);

	public int deleteParentId(String codeIds);

    public List<CodeTable> selectBySectionAndParentId(Map map);

    }
