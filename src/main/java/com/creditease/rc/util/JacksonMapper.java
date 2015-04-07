/**
 * Title:JacksonMapper.java  
 * Description:  
 */
package com.creditease.rc.util;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Title:JacksonMapper.java  
 * Description:  
 * Copyright:Copyright (c) 2011 
 * Company:普信恒业科技发展（北京）有限公司 
 * @author 韩大年  
 * @version v1.0 
 * 2013-3-29 
 */
public class JacksonMapper {

	/**
	 * @Description 默认构造器 
	 */
	private JacksonMapper() {
		// TODO Auto-generated constructor stub
	}
	/** 
     *  
     */  
    private static  ObjectMapper mapper = null;  
  
  
    /**
     * 
    * checkstyle
    * @author wyf   
    * @return    
    * ObjectMapper
     */
    public static ObjectMapper getInstance() {  
    	if(mapper ==null ){
    		mapper = new ObjectMapper();
    	}
  
        return mapper;  
    }  

}
