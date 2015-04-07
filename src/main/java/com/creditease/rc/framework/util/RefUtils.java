package com.creditease.rc.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 反射工具类
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright:Copyright (c) 2011</p>
 * <p>Company:普信恒业科技发展(北京)有限公司</p>
 * <p>Date:2011-10-17</p>
 * <p>Modify:</p>
 * <p>Bug:</p>
 * 
 * @author wangxinqiang
 * @version 1.0
 */
public class RefUtils {
	
	private static final Log logger = LogFactory.getLog(RefUtils.class);

	/**
	 * 给成员变量赋值
	 * @param target 
	 * @param fname 
	 * @param ftype 
	 * @param fvalue 
	 */
	@SuppressWarnings("unchecked")
	public static void setFieldValue(Object target, String fname, Class ftype,
			Object fvalue) {
		if (target == null
				|| fname == null
				|| "".equals(fname)
				|| (fvalue != null && !ftype
						.isAssignableFrom(fvalue.getClass()))) {
			return;
		}
		Class clazz = target.getClass();
		try {
			Method method = clazz.getDeclaredMethod("set"
					+ Character.toUpperCase(fname.charAt(0))
					+ fname.substring(1), ftype);
			if (!Modifier.isPublic(method.getModifiers())) {
				method.setAccessible(true);
			}
			method.invoke(target, fvalue);

		} catch (Exception me) {

			try {
				Field field = clazz.getDeclaredField(fname);
				if (!Modifier.isPublic(field.getModifiers())) {
					field.setAccessible(true);
				}
				field.set(target, fvalue);
			} catch (Exception fe) {

			}
		}
	}

	/**
	 * 实例化指定类
	 * @param className 类名称
	 * @return Object
	 */
	public static Object instance(String className) {
		Object obj = null;
		try {
			obj = Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			logger.error(className + "类实例化错误 ！");
			e.printStackTrace();

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}
	

	
	
	
	
	/**
	 * 返回某个对象的静态属性值 
	 * @param clazz 需要查找的对象
	 * @param property String 属性名称
	 * @return 属性值 
	 */
	@SuppressWarnings("unchecked")
	public static Object getStaticProperty(Class clazz,String property)
	{
		try {
			Field f = clazz.getDeclaredField(property);
			return f.get(null);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 返回某个对象的属性值 
	 * @param obj Object 需要查找的对象
	 * @param property String 属性名称
	 * @return 属性值 
	 */
	public static Object getPropertyValue(Object obj,String property)
	{
		try {
			Field f = obj.getClass().getDeclaredField(property);
			return f.get(obj);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
