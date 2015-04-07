package com.creditease.rc.action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.domain.CodeTable;
import com.creditease.rc.domain.Message;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IDataDictionaryService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DicUtil;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.vo.DataDictionaryVo;

/**
 * 
 *  DataDictionaryController.java
 * @author yifengwang
 *  2012-12-24 下午01:42:34
 * @version V2.0
 */
@Controller
@RequestMapping("dicRequest")
public class DataDictionaryController {

	/**
	 * 
	 */
	public DataDictionaryController() {
		
	}
	@Resource
	private IDataDictionaryService dataDictionaryService;
	
	/**
	 * 
	 *@author yifengwang
	 *@param dicArray key集合
	 *@return 字典map
	 *@throws Exception 2012-12-24下午02:30:42
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="getAllDic",method=RequestMethod.POST)
	@ResponseBody
	public  Map  getAllDic(String dicArray) throws Exception{
		Map<String, List> retDicMap = new HashMap<String, List>();
		String arr[]=dicArray.split(",");
		//重新加载字典
		if(null==DicUtil.map){
			dataDictionaryService.initGetAllDic();
		}
		if(arr.length>0){
			for(String key:arr){
			    if(DicUtil.map.containsKey(key)){
			    	retDicMap.put(key, (List) DicUtil.map.get(key));
			    }else{
			    	retDicMap.put(key,new ArrayList());
			    }
			}
		}
		return retDicMap;
	}
	/**
	 * 
	 *@author yifengWang
	 *@param q codeValue
	 *@param section 查询某种字典
	 *@return 2012-12-24下午01:59:35
	 */
	@RequestMapping(value="getSpecifiedDic",method=RequestMethod.POST)
	@ResponseBody
	public List getSpecifiedDic(String q,String section){
		 String res = null;
		 List list = dataDictionaryService.getSpecifiedDic(q, section);
		 if(CommonUtil.isNotEmpty(list)){
			 res = JsonUtil.getJson(list);
		 }
		return list;
	}
	/**
	 * 
	 *@author yifengwang
	 *@param page s
	 *@param rows s
	 *@param dataDictionaryVo s
	 *@return 2012-12-24下午01:57:53
	 */
	@RequestMapping(value="listPage",method=RequestMethod.POST)
	public @ResponseBody Pagination getCodeTables(@RequestParam(required=false) String page, 
			@RequestParam(required=false) String rows,DataDictionaryVo dataDictionaryVo){
		Pagination pagination = new Pagination();
		if(!StringUtils.isBlank(page)){ pagination.setPage(Integer.valueOf(page));}
		if(!StringUtils.isBlank(rows)){ pagination.setPageSize(Integer.valueOf(rows));}
		return dataDictionaryService.getCodeTableByPagination(dataDictionaryVo,pagination);
	}
	
	/**
	 * 
	 *@author yifengwang
	 *@param page s
	 *@param rows s
	 *@param dataDictionaryVo s
	 *@return 2012-12-24下午01:57:53
	 */
	@RequestMapping(value="listPageByserialNum",method=RequestMethod.POST)
	public @ResponseBody Pagination getCodeTablesByserialNum(@RequestParam(required=false) String page, 
			@RequestParam(required=false) String rows){
		Pagination pagination = new Pagination();
		if(!StringUtils.isBlank(page)){ pagination.setPage(Integer.valueOf(page));}
		if(!StringUtils.isBlank(rows)){ pagination.setPageSize(Integer.valueOf(rows));}
		return dataDictionaryService.getCodeTableBySerialNum(pagination);
	}
	/**
	 * 
	 *@author yifengwang
	 *@param deleted 被删除集合
	 *@param updated 被修改的集合
	 *@return 2012-12-24下午01:58:12
	 */
	@RequestMapping(value="saveAllData",method=RequestMethod.POST)
	@ResponseBody
	public Message saveAll(String deleted,String updated){
		Message message = new Message();
		CodeTable[] codeTableDeleted = (CodeTable[]) JsonUtil.getArray(deleted, CodeTable.class);
		CodeTable[] codeTableUpdated = (CodeTable[]) JsonUtil.getArray(updated, CodeTable.class);
		try {
			List<Integer> codeTablesDelete = new ArrayList<Integer>();
			if(codeTableDeleted.length>0){
				for(CodeTable ct:codeTableDeleted){
					codeTablesDelete.add(ct.getCodaTableId());
				}
				dataDictionaryService.deleteCodeTables(codeTablesDelete);
			}
			if(codeTableUpdated.length>0){
				for(CodeTable ct:codeTableUpdated){
					dataDictionaryService.updateCodeTable(ct);
				}
			}
			dataDictionaryService.modifyGetAllDic(codeTableDeleted,codeTableUpdated,null);
			message.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			message.setSuccess(false);
		}
		return message;
	}
	/**
	 * 
	 *@author yifengwang
	 *@param inserted 新增
	 *@return 2012-12-24下午01:58:22
	 */
	@RequestMapping(value="saveInsert",method=RequestMethod.POST)
	@ResponseBody
	public Message saveInsert(CodeTable inserted){
		Message message = new Message();
		DataDictionaryVo dictionaryVo = new DataDictionaryVo();
		dictionaryVo.setSection(inserted.getSection());
		dictionaryVo.setCodeKey(inserted.getCodeKey());
		dictionaryVo.setCodeValue(inserted.getCodeVlue());
		dictionaryVo.setSelectType("V");
		try {
			List<CodeTable> codes = dataDictionaryService.getCodeTableBySection(dictionaryVo);
			//校验时查出所有启用和停用的
			if(CommonUtil.isNotEmpty(codes)){
				message.setSuccess(false);
				message.setMsg("数据已存在,请修改后再保存");
			}else{
				dataDictionaryService.saveCodeTable(inserted);
				dataDictionaryService.modifyGetAllDic(null, null, inserted);
				message.setSuccess(true);
				message.setMsg("保存成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			message.setSuccess(false);
			message.setMsg("保存失败,请稍后重试");
		}
		return message;
	}
	/**
	 * 
	 *@author yifengwang
	 *@param codeKey 是
	 *@param mysection 的
	 *@return 2012-12-24下午01:58:26
	 */
	@RequestMapping(value="validateCodeKey",method=RequestMethod.POST)
	@ResponseBody
	public boolean validateCodeKey(@RequestParam(required=false)String codeKey,@RequestParam(required=false)String mysection){
		DataDictionaryVo dictionaryVo = new DataDictionaryVo();
		dictionaryVo.setSection(mysection);
		dictionaryVo.setSelectType("V");
		boolean result = dataDictionaryService.getCodeTableBySection(codeKey,true, dictionaryVo);
		return result;
	}
	/**
	 * 
	 *@author yifengwang
	 *@param codeKey 是
	 *@param mysection 的
	 *@return 2012-12-24下午01:58:26
	 */
	@RequestMapping(value="validateCodeValue",method=RequestMethod.POST)
	@ResponseBody
	public boolean validateCodeValue(@RequestParam(required=false)String codeKey,@RequestParam(required=false)String mysection){
		DataDictionaryVo dictionaryVo = new DataDictionaryVo();
		dictionaryVo.setSection(mysection);
		dictionaryVo.setSelectType("V");
		boolean result = dataDictionaryService.getCodeTableBySection(codeKey, false,dictionaryVo);
		return result;
	}
	/**
	 * 
	* @author wyf  
	* @Description: 
	* @param: deleted
	* @return: Message 
	 */
	@RequestMapping(value="deleteData",method=RequestMethod.POST)
	@ResponseBody
	public Message deleteData(String deleted){
		Message message = new Message();
		CodeTable codeTable = (CodeTable) JsonUtil.getObject(deleted, CodeTable.class);
		try {
			if(codeTable!=null){
				dataDictionaryService.deleteCodeTable(codeTable);
				CodeTable[] codeTableUpdated = new CodeTable[1];
				codeTableUpdated[0] = codeTable;
				dataDictionaryService.modifyGetAllDic(null,codeTableUpdated,null);
			}
			message.setSuccess(true);
			message.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			message.setSuccess(false);
			message.setMsg("删除失败,请重试");
		}
		return message;
	}
	/**
	 * 
	* @author wyf  
	* @Description: 
	* @param: updated
	* @return: Message 
	 */
	@RequestMapping(value="updateData",method=RequestMethod.POST)
	@ResponseBody
	public Message updateData(String updated){
		Message message = new Message();
//		CodeTable[] codeTableUpdated = (CodeTable[]) JsonUtil.getArray(updated, CodeTable.class);
		/**
		 * alter table RL_CODETABLE add(available varchar2(20));
comment on column RL_CODETABLE.available is '是否启用:0是-1否';
		 */
		CodeTable codeTable = (CodeTable) JsonUtil.getObject(updated, CodeTable.class);
		try {
			if(codeTable!=null){
				List<CodeTable> codes = null;
				if("0".equals(codeTable.getAvailable())){//启用时校验
					DataDictionaryVo dictionaryVo = new DataDictionaryVo();
					dictionaryVo.setSection(codeTable.getSection());
					dictionaryVo.setCodeKey(codeTable.getCodeKey());
					dictionaryVo.setCodeValue(codeTable.getCodeVlue());
					dictionaryVo.setSelectType("E");
					codes = dataDictionaryService.getCodeTableBySection(dictionaryVo);
				}
				if(CommonUtil.isNotEmpty(codes)){
					message.setSuccess(false);
					message.setMsg("已有重复数据启用,操作失败");
				}else{
					dataDictionaryService.updateCodeTable(codeTable);
					CodeTable[] codeTableUpdated = new CodeTable[1];
					codeTableUpdated[0] = codeTable;
					dataDictionaryService.modifyGetAllDic(null,codeTableUpdated,null);
					message.setSuccess(true);
					message.setMsg("操作成功");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			message.setSuccess(false);
			message.setMsg("操作失败，请重试");
		}
		return message;
	}
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  重新加载字典
	 * @return 
	 * @version v1.0 
	 * 2013-7-8
	 */
	@RequestMapping(value="reloadInitGetAllDic",method=RequestMethod.POST)
	@ResponseBody
	public Message reloadInitGetAllDic(){
		Message message = new Message();
		try{
			message.setSuccess(true);
			DicUtil.map.clear();
			DicUtil.sectionMap.clear();
			this.dataDictionaryService.initGetAllDic();
		}catch (Exception e) {
			// TODO: handle exception
			message.setSuccess(false);
			message.setMsg(e.getLocalizedMessage());
			e.printStackTrace();
		}
		
		return message;
		
	}
	
	@RequestMapping(value="selectSection")
	public @ResponseBody List<Map<String, Object>> selectSection(String q){
			return dataDictionaryService.selectSection(q);
	}
	
	
	@RequestMapping(value="forManage")
	public @ResponseBody Pagination forManage(@RequestParam(required=false) String page, String querayFlag,
			@RequestParam(required=false) String rows,DataDictionaryVo dataDictionaryVo){
		Pagination pagination = new Pagination();
		if("T".equals(querayFlag)){
			pagination.setItems(new ArrayList());
			return pagination;
		}else{
			if(!StringUtils.isBlank(page)){ pagination.setPage(Integer.valueOf(page));}
			if(!StringUtils.isBlank(rows)){ pagination.setPageSize(Integer.valueOf(rows));}
			pagination = dataDictionaryService.getCodeTableByPagination(dataDictionaryVo,pagination);
			List<CodeTable> codeTables = pagination.getRows();
			for (int i = codeTables.size()-1;i>=0;i--) {
				if("-1".equals(codeTables.get(i).getAvailable())){
					codeTables.remove(i);
				}
			}
			return pagination;
		}
	}
	/**
	 * 
	 * @param parentId
	 * @param codeIds
	 * @return
	 */
	@RequestMapping(value = "updateParentId")
	public @ResponseBody Message updateParentId(Integer parentId,String codeIds){
		return dataDictionaryService.updateParentId(parentId, codeIds);
	}
	
	@RequestMapping(value = "deleteParentId")
	public @ResponseBody Message deleteParentId(String codeIds){
		return dataDictionaryService.deleteParentId( codeIds);
	}
}
