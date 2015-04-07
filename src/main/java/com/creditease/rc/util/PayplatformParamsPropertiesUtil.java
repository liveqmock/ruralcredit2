	/**
 *
	*/
package com.creditease.rc.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Title:
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2011-12-26 
 * @author 韩大年
 * @version v2.0
 */
public class PayplatformParamsPropertiesUtil {
	
	//资源文件路径
	public static String propertiesPath = null;
	public static Properties propertiesInstance= new Properties();
	static {
		String projectPath=PayplatformParamsPropertiesUtil.class.getClassLoader().getResource(File.separator).getPath();
		propertiesPath =projectPath + File.separator + "config" + 
		File.separator+"spring"+ File.separator+"settle"+ File.separator + "settle.properties";
	    try {
			propertiesInstance.load(new FileInputStream(propertiesPath));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param key 
	* @return    
	* String
	 */
	public static String getProperty(String key){
		return propertiesInstance.getProperty(key);
	}


	/**
	 *@author 韩大年
	 *@function： 
	 *@param args  
	 *2011-12-26 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
