package com.creditease.rc.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.CodeTable;

/**
 * Title:
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2012-6-20 
 * @author 韩大年
 * @version v2.0
 */
public class DicUtil {

	/**
	 * 
	 */
	private DicUtil() {
		// TODO Auto-generated constructor stub
	}
	//map(key=section,value=list<CodeTable>)
	public static Map<String, List<CodeTable>> map=new HashMap<String, List<CodeTable>>();
	//sectionMap(key=section,value=Map<codeKey,codeValue>)
	public static Map<String, Map<String,String>> sectionMap= new HashMap<String, Map<String,String>>();
	private static DicUtil  instance =null;
	/**
	 * 
	 *@author yifengwang
	 *@return 2012-12-24下午03:32:40
	 */
	public  static DicUtil getInstance(){
		if(null==instance){
			synchronized(DicUtil.class){
				if(null==instance){
					instance = new DicUtil();
				}
			}
		}
		return instance;
	}
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description: 根据字典类型,将codeKey转义成codeValue
	 * @param section section
	 * @param codeKey codeKey
	 * @return 2012-11-5
	 * @version v1.0
	 * 
	 */
	public static String convertCodeKeyToCodeValue(String section,String codeKey){
		if(DicUtil.sectionMap.containsKey(section)){
			Map<String,String> codeKeyMap= DicUtil.sectionMap.get(section);
			if(codeKeyMap.containsKey(codeKey)){
				return codeKeyMap.get(codeKey);
			}
		}
		return "";
	}
	
}
