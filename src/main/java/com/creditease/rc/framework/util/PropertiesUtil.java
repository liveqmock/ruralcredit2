package com.creditease.rc.framework.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;


/**
 * Title: 操作属性文件的工具类
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2011-7-19
 * @author xiebingfeng
 * @version v1.0
 */
public class PropertiesUtil {
	/**
	 * 根据路径读取属性对象
	 * @param path 路径
	 * @return Properties
	 */
	public static Properties readPropertiesFile(String path){
		if(StringUtils.isEmpty(path)) { return null; }
		
		Properties pro = new Properties();
		try {
			pro.load(new FileInputStream(path.trim()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			pro = null;
		} catch (IOException e) {
			e.printStackTrace();
			pro = null;
		}
		return pro;
	}
	
	/**
	 * 根据key从属性对象中获取值
	 * @param properties 属性对象
	 * @param key key
	 * @return String
	 */
	public static String getVlaue(Properties properties, String key){
		return (String) properties.get(key);
	}
	
	/**
	 * 根据key从属性对象中获取值
	 * @param path 属性文件路径
	 * @param key key
	 * @return String
	 */
	public static String getVlaue(String path, String key){
		Properties pro = readPropertiesFile(path);
		if(pro == null) { return null; }
		return getVlaue(pro, key);
	}
	
	/**
	 * 
	 *@author 韩大年
	 *@function： 根据资源文件名获取properties对象
	 *@param propertiesName 资源文件名
	 *@return Properties
	 *2012-6-27
	 */
	public static Properties loadProperties(String propertiesName){
		InputStream inputStream = PropertiesUtil.class.getResourceAsStream("/config/"+propertiesName);
		Properties pro = new Properties();
		try {
			pro.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(pro.get("zipPicStorageDir"));
		return pro;
	}
	
}
