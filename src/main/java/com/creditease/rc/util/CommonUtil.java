package com.creditease.rc.util;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Title:工具类
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2011-9-8
 * @author handanian
 * @version v2.0
 */
public class CommonUtil implements Serializable {

	/**
	 * 
	 */
	public CommonUtil() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 判断字符串为空
	 * @param str s
	 * @return boolean boolean
	 */
	  public static boolean isEmpty(String str){
	        if(str == null || "".equalsIgnoreCase(str.trim())){
	            
	        	return true;
	        }
	        return false;
	    }
	  /**
	   * 判断字符串非空
	   * @param str s
	   * @return boolean boolean
	   */
	  public static boolean isNotEmpty(String str){
		  
		  return !isEmpty(str);
	  }

	  /**
	   * 判断list为空
	   * @param collection list
	   * @return boolean boolean
	   */
	    public static boolean isEmpty(Collection collection){
	        if(collection == null || collection.size() == 0){
	        	
	        	return true;
	        }
	        return false;
	    }
	    /**
	     * 判断list非空
	     * @param collection list
	     * @return boolean boolean
	     */
		public static boolean isNotEmpty(Collection collection)  {
	    	return !isEmpty( collection);
	    }
	    
	    /**
	     * 
	     *@author 韩大年
	     *@function： 解码成utf-8
	     *@param str 解码字符串对象
	     *@return  2011-10-27
	     */
	    public static String decode(String str){
	    	try {
				str= URLDecoder.decode(str, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	return str;
	    }
	    
	    /**
	     * 
	     *@author 韩大年
	     *@function： 解码成utf-8
	     *@param  strArray 解码数字对象
	     *@return  2011-10-27
	     */
	    public static String [] decode(String strArray[]){
	    	for(String str:strArray){
	    		try {
	    			str=URLDecoder.decode(str, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
	    	}
	    	
	    	return strArray;
	    }
	    
	    /**
	     * 
	     *@author 韩大年
	     *@function： 字符串转化成UTF-8个格式
	     *@param s s
	     *@return 2012-3-31
	     */
		public static String toUtf8String(String s) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (c >= 0 && c <= 255) {
					sb.append(c);
				} else {
					byte[] b;
					try {
						b = Character.toString(c).getBytes("utf-8");
					} catch (Exception ex) {
						System.out.println(ex);
						b = new byte[0];
					}
					for (int j = 0; j < b.length; j++) {
						int k = b[j];
						if (k < 0){
							k += 256;
						}
						sb.append("%" + Integer.toHexString(k).toUpperCase());
					}
				}
			}
			return sb.toString();
		}
		
		/**
		 * 
		 * @author 韩大年  
		 * @Description: 
		 * @param bean 克隆
		 * @return 2012-10-31
		 */
		public static Object cloneBean(Object bean){
			try {
				BeanUtils.cloneBean(bean);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bean;
			
		}
		
		/**
		 * 
		 * @author 韩大年  
		 * @Description: 将src中所有属性拷贝到desc中 .注:java.util.Date是不被支持的
		 * @param src 源对象
		 * @param desc 目标对象
		 * @version v1.0
		 * 2012-10-31
		 */
		public static void copyProperties(Object desc, Object src){
			try {
				BeanUtils.copyProperties(desc, src);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/**
		 * 将字符串的指定位置替换成新的字符 
		* checkstyle
		* @author wyf   
		* @param str 
		* @param newChar 
		* @return    
		* String
		 */
		public static String replace(String str,String newChar){ 
			String result = "";
			result = str.replaceAll("Z[0-9]{1,2}", newChar);
	        return result;
		} 

}
