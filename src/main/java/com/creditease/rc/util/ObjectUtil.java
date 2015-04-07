/*
 * Copyright (C) 2006-2012 普信恒业科技发展（北京）有限公司.
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
 * ============================================================
 * FileName: ObjectUtil.java
 * Created: [2012-11-8 下午01:13:05] by 姚鑫
 * $Id$
 * $Revision$
 * $Author$
 * $Date$
 * ============================================================
 * ProjectName: ruralcredit2
 * Description:
 * ==========================================================
 */
package com.creditease.rc.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Description: 对象工具类
 * 
 * @author 姚鑫
 * @version 2.0
 * 
 *          <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------
 * 2012-11-8      姚鑫                      2.0         2.0 Version
 * </pre>
 */

public class ObjectUtil {
	/**
	 * 验证对象是否为空
	 * 
	 * @param obj 对象
	 * @param filtedFieldNames 被过滤掉的对象属性名称集合
	 * @return true-对象不为空;flase-对象为空
	 */
	public static boolean checkObjectIsNotNull(Object obj, List<String> filtedFieldNames) {
		boolean result = false;
		if (obj != null && filtedFieldNames != null) {
			Class<?> objClass = obj.getClass();
			for (Field f : objClass.getDeclaredFields()) {
				String filtedFieldName = f.getName();
				if (!filtedFieldNames.contains(filtedFieldName)) {
					f.setAccessible(true);
					String filtedFieldType = f.getType().getName();
					try {
						if ("java.lang.String".equals(filtedFieldType)) {
							String objString = (String) f.get(obj);
							if (objString != null && !"".equals(objString.trim())) {
								result = true;
							}
						} else {
							Object objOther = f.get(obj);
							if (objOther != null) {
								result = true;
							}
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}

	/**
	 * 验证对象的每一个属性是否都不为空
	 * 
	 * @author 郝强
	 * @param obj 对象
	 * @return true-对象的每一个属性都不为空;flase-对象有的属性为空或者对象为空
	 */
	public static boolean checkObjectIsPerfect(Object obj) {

		if (obj == null) {
			return false;
		}

		boolean result = false;

		Class clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		try {
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].getName();
				String firstLetter = fieldName.substring(0, 1).toUpperCase();
				String getMethodName = "get" + firstLetter + fieldName.substring(1);
				Method getMethod;
				getMethod = clazz.getMethod(getMethodName, new Class[] {});
				Object o = getMethod.invoke(obj, new Object[] {});
				if (o == null) {
					return false;
				}
			}
			return true;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 验证对象的所有属性都为空
	 * 
	 * @author 郝强
	 * @param obj 对象
	 * @return true-对象的所有属性都为空;flase-对象有一个不为空
	 */
	public static boolean checkObjectIsNull(Object obj) {

		if (obj == null) {
			return false;
		}

		boolean result = false;

		Class clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		try {
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].getName();
				String firstLetter = fieldName.substring(0, 1).toUpperCase();
				String getMethodName = "get" + firstLetter + fieldName.substring(1);
				Method getMethod;
				getMethod = clazz.getMethod(getMethodName, new Class[] {});
				Object o = getMethod.invoke(obj, new Object[] {});
				if (o != null) {
					return false;
				}
			}
			return true;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	
	
	/**
	 * 验证对象filtedFieldNames属性中 至少有一个不为空
	 * zhangman
	 * 
	 * @param obj 对象
	 * @param filtedFieldNames 被过滤掉的对象属性名称集合
	 * @return true-对象不为空;flase-对象为空
	 */
	public static boolean checkObjectOneNeed(Object obj, List<String> filtedFieldNames) {
		boolean result = false;
		if (obj != null && filtedFieldNames != null) {
			Class<?> objClass = obj.getClass();
			for (Field f : objClass.getDeclaredFields()) {
				String filtedFieldName = f.getName();
				if (filtedFieldNames.contains(filtedFieldName)) {
					f.setAccessible(true);
					String filtedFieldType = f.getType().getName();
					try {
						if ("java.lang.String".equals(filtedFieldType)) {
							String objString = (String) f.get(obj);
							if (objString != null && !"".equals(objString.trim())) {
								result = true;
							}
						} else {
							Object objOther = f.get(obj);
							if (objOther != null) {
								result = true;
							}
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}

}
