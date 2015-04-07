package com.creditease.rc.util;

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
	 * 读属性文件
	 * @param path 文件路径
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
	 * 获取属性值
	 * @param properties 配置对象
	 * @param key 获取key
	 * @return String
	 */
	public static String getVlaue(Properties properties, String key){
		return (String) properties.get(key);
	}
	
	/**
	 * 获取属性值
	 * @param path 配置文件路径
	 * @param key 获取key
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
	 *@return  
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
	
	/**
	 * 测试方法
	 * @param args 参数
	 */
	public static void  main(String []args){
		PropertiesUtil.loadProperties("picture.properties");
		
	}
	
}
