package com.creditease.rc.app.sia.util;

import java.util.Map;

public class CMQMessage {
    private String cmd;
    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }
    /**
     * 
     * Description: 输出成xml
     * @param dateFormat 日期格式
     * @return String 
     * @throws
     * @Author wangping
     * Create Date: 2014年5月27日 下午1:10:29
     */
    public String toXML(String dateFormat) throws Exception{
        if(dateFormat == null || "".equals(dateFormat)){
            return toXML();
        }
        return CMQUtil.getXML(this,dateFormat);
    }
    /**
     * 
     * Description: 输出成xml。使用默认的日期格式CMQUtil.DATEFORMAT_DEFAULT
     * @param
     * @return String
     * @throws
     * @Author wangping
     * Create Date: 2014年5月27日 下午1:11:18
     */
    public String toXML() throws Exception{
        return toXML(CMQUtil.DATEFORMAT_DEFAULT);
    }
    /**
     * 
     * Description: 读取xml生成继承自CMQMessageAns的子类
     * @param xml xml格式的字符串
     * @param fieldTypes 集合属性的属性名和对应的java类型
     * @return CMQMessageAns
     * @throws
     * @Author wangping
     * Create Date: 2014年5月27日 下午4:50:06
     */
    public CMQMessage toBean(String xml ,Map<String,Class<?>> fieldTypes) throws Exception{
        return CMQUtil.getBean(xml, this.getClass(),CMQUtil.DATEFORMAT_DEFAULT ,fieldTypes);
    }/**
     * 
     * Description: 读取xml生成继承自CMQMessageAns的子类
     * @param xml xml格式的字符串
     * @param dateFormat 日期格式
     * @param fieldTypes 集合属性的属性名和对应的java类型
     * @return CMQMessageAns
     * @throws
     * @Author wangping
     * Create Date: 2014年5月27日 下午4:50:06
     */
    public CMQMessage toBean(String xml ,String dateFormat ,Map<String,Class<?>> fieldTypes) throws Exception{
        return CMQUtil.getBean(xml, this.getClass(),dateFormat ,fieldTypes);
    }
    /**
     * 
     * Description: 读取xml生成继承自CMQMessageAns的子类。
     * @param xml xml格式的字符串
     * @param dateFormat 日期格式
     * @return CMQMessageAns
     * @throws
     * @Author wangping
     * Create Date: 2014年5月27日 下午1:11:31
     */
    public CMQMessage toBean(String xml ,String dateFormat) throws Exception{
        return toBean(xml, dateFormat ,null);
    }
    /**
     * 
     * Description: 读取xml生成继承自CMQMessageAns的子类。
     * </br>使用默认的日期格式CMQUtil.DATEFORMAT_DEFAULT
     * @param xml xml格式的字符串
     * @return CMQMessageAns
     * @throws
     * @Author wangping
     * Create Date: 2014年5月27日 下午1:13:06
     */
    public CMQMessage toBean(String xml) throws Exception{
        return toBean(xml,CMQUtil.DATEFORMAT_DEFAULT);
    }
}
