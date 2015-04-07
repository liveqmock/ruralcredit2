	/**
 *
	*/
package com.creditease.rc.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.CodeTable;
import com.creditease.rc.domain.Message;
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
public interface IDataDictionaryService {

	/**
	 *
	 *@author 韩大年
	 *@function：   获取所有字典用于初始化下拉
	 *2012-6-26
	 */
	public void initGetAllDic();
	/**
	 * 保存字典
	* @author wyf
	* @param codeTable codeTable
	* @throws Exception s
	 */
	public void saveCodeTable(CodeTable codeTable)throws Exception;
	/**
	 * 批量删除字典
	* @author wyf
	* @param codeTableIds codeTableIds
	* @throws Exception s
	 */
	public void deleteCodeTables(List<Integer> codeTableIds)throws Exception;
	/**
	 * 修改字典
	* @author wyf
	* @param codeTable codeTable
	* @throws Exception s
	 */
	public void updateCodeTable(CodeTable codeTable)throws Exception;
	/**
	 * 批量修改
	* @author wyf
	* @param codeTables codeTables
	* @throws Exception s
	 */
	public void batchUpdate(List<CodeTable> codeTables)throws Exception;
	/**
	 * 获取所有字典用于分页
	* @author wyf
	* @param dataDictionaryVo dataDictionaryVo
	* @param pagination pagination
	* @return Pagination
	 */
	public Pagination getCodeTableByPagination(DataDictionaryVo dataDictionaryVo,Pagination pagination);
	/**
	 * 获取所有字典用于初始化
	* @author wyf
	* @return List<CodeTable>
	 */
	public List<CodeTable> getCodeTableByAll();
	/**
	 *
	* checkstyle
	* @author wyf
	* @param codekey
	* @param type
	* @param dictionaryVo
	* @return
	* boolean
	 */
	public boolean getCodeTableBySection(String codekey,boolean type,DataDictionaryVo dictionaryVo);
	/**
	 *  获取所有section
	* @author wyf
	* @return List
	 */
	public List getSection();
	/**
	 * 初始化指定section的数据字典
	* @author wyf
	* @param dicArray dicArray
	 */
	public void initGetAllDic(String  dicArray);
	/**
	 *
	 *@author yifengwang
	 *@param q q
	 *@param section section
	 *@return 2012-12-24下午04:02:29
	 */
	public List getSpecifiedDic(String q,String section);
	/**
	 * 修改指定section的数据字典-增删改
	* checkstyle
	* @author wyf
	* @param delete
	* @param update
	* @param insert
	* void
	 */
	public void modifyGetAllDic(CodeTable[] delete,CodeTable[] update,CodeTable insert);
	/**
	 * 查询字典方法
	 * @param dictionaryVo
	 * @return List
	 */
	public List<CodeTable> getCodeTableBySection(DataDictionaryVo dictionaryVo);
	/**
	 * 导入excel
	* checkstyle
	* @author wyf
	* @param is
	* @throws Exception
	* void
	 */
	public void impExcelTemplate(InputStream is) throws Exception ;
	/**
	 * 少数民族专用
	* checkstyle
	* @author wyf
	* @param value
	* @return
	* List<CodeTable>
	 */
	public	List<CodeTable> nationalCombobox(String value);
	/**
	 *
	* checkstyle
	* @author wyf
	* @param codeTable
	* @throws Exception
	* void
	 */
	public void deleteCodeTable(CodeTable codeTable)throws Exception;

	/**
	 * 查询value
	* checkstyle
	* @author wyf
	* @param section
	* @param key
	* @return
	* String
	 */
	public String getCodeValueByKey(String section,String key);
	/**
	 * 查询业务编码
	* @author wyf
	* @param pagination
	* @return
	* Pagination
	 */
	public Pagination getCodeTableBySerialNum(Pagination pagination);
	/**
	 *
	 * @param parentId
	 * @return
	 */
	public List<CodeTable> selectByParentId(Integer parentId);
	public List<Map<String, Object>> selectSection(String q);
	public Message updateParentId(Integer parentId, String codeIds);
	public Message deleteParentId(String codeIds);

    /*
    根据section、parentId获取数据字典
     */
    public List<CodeTable> selectBySectionAndParentId(String section,String parentId);
}
