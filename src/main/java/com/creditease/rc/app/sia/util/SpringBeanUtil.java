/**
 * Copyright (C) 2006-2014 普信恒业科技发展（北京）有限公司.
 * 本系统是商用软件,未经授权擅自复制或传播本程序的部分或全部将是非法的.
============================================================
 * FileName: SpringBeanUtil.java 
 * Created: 2014年6月16日 by yw 
 * @version 1.0
 * @Revision
 * @Author yw
 * @Date 2014年6月16日
============================================================ 
 * ProjectName: ICPlatform 
 * Description: 
==========================================================*/
package com.creditease.rc.app.sia.util;
/**
 * 
 * Description: 从spring 容器中获取bean
 * @author yw
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
------------------------------------------------------------------
 * 2014年6月13日    yw       1.0        1.0 Version 
 * </pre>
 */
public final class SpringBeanUtil {
    /**
     * 私有构造函数
     */
    private SpringBeanUtil() { } 
    /**
     * 
     * Description: 从spring 容器中获取bean
     * @param beanName bean名称
     * @return Object
     * @throws
     * @Author yw
     * Create Date: 2014年6月13日 上午9:48:23
     */
    public static Object getBean(final String beanName) { 
        return SpringContextTool.getApplicationContext().getBean(beanName);
    }
    /**
     * 
     * Description: 通过接口类名获取spring容器中的bean
     * @param <T> 泛型
     * @param clazz 类名
     * @return Object
     * @throws
     * @Author yw
     * Create Date: 2014年6月13日 上午11:28:27
     */
    public static <T> Object getBean(final Class<T> clazz) { 
        return SpringContextTool.getApplicationContext().getBean(clazz);
    }
}
