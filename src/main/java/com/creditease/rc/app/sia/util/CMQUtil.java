package com.creditease.rc.app.sia.util;

import java.io.IOException;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * 采用CMQ通信的客户端和服务端都可以使用这个类，把javaBean对象输出成xml格式的文本。或者把xml格式的文本封装成指定类型的javaBean。 <br/>
 * 为了保证最大程度的灵活性，这里不限制输出的javaBean与输入的javaBean必须在同一个包下，也不限制必须同名。 <br/>
 * 客户端与服务端可以自行决定相关javaBean的定义。但是要求相同的参数名必须相同。 Description:
 * @author wangping
 * @version 1.0
 * 
 *          <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------
 * 2014年5月21日    wangping       1.0        1.0 Version
 * </pre>
 */
public class CMQUtil {
    private static Logger log = Logger.getLogger(CMQUtil.class);

    private CMQUtil() {
    }
    /**
     * 默认的日期格式化格式："yyyy-MM-dd HH:mm:ss"
     */
    public static final String DATEFORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 把bean对象转换成xml格式，只保留数据结构不记录java类型。 Description:
     * @param bean 要转换成xml数据的java对象
     * @param dateFormat 时间类型的格式；默认是"yyyy-MM-dd HH:mm:ss"
     * @return String 经过CMQUtil.encodeUTF8(xml)处理的xml格式的字符串
     * @throws
     * @Author wangping Create Date: 2014年5月23日 下午3:15:51
     */
    public static final String getXML(Object bean, String dateFormat) throws JAXBException,
            IOException, IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        if (dateFormat == null)
            return getXML(bean);
        return encodeUTF8(xml.append(getFieldXML(bean, bean.getClass().getSimpleName(), dateFormat))
                .toString());
    }
    /**
     * 
     * Description: URLEncoder.encode(xml, "UTF-8");
     * @param
     * @return String
     * @throws
     * @Author wangping
     * Create Date: 2014年5月26日 下午5:30:15
     */
    public static final String encodeUTF8(String xml) throws UnsupportedEncodingException{
        return URLEncoder.encode(xml, "UTF-8");
    }
    /**
     * 
     * Description: URLDecoder.decode(xml, "UTF-8");
     * @param
     * @return String
     * @throws
     * @Author wangping
     * Create Date: 2014年5月26日 下午5:30:25
     */
    public static final String decodeUTF8(String xml) throws UnsupportedEncodingException{
        return URLDecoder.decode(xml, "UTF-8");
    }
    /**
     * 把bean对象转换成xml格式，只保留数据结构不记录java类型。时间类型的数据保存格式："yyyy-MM-dd HH:mm:ss" Description:
     * @param bean 要转换成xml数据的java对象
     * @return String 经过CMQUtil.encodeUTF8(xml)处理的xml格式的字符串
     * @throws
     * @Author wangping Create Date: 2014年5月23日 下午3:15:51
     */
    public static final String getXML(Object bean) throws JAXBException, IOException,
            IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        return getXML(bean, DATEFORMAT_DEFAULT);
    }
    /**
     * 拼接属性对象的xml格式(返回结果不包括xml头信息)
     * Description: 
     * @param
     * @return StringBuffer
     * @throws
     * @Author wangping
     * Create Date: 2014年5月23日 下午7:26:40
     */
    private static StringBuffer getFieldXML(Object bean, String name, String dateFormat)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        StringBuffer xml = new StringBuffer();
        xml.append("<").append(name).append(">");
        if (bean == null) {
            xml.append("");
        } else if ((bean instanceof String)) {
            xml.append((String) bean);
        } else if ((bean instanceof Double)) {
            xml.append(((Double) bean).doubleValue());
        } else if ((bean instanceof Float)) {
            xml.append(((Float) bean).floatValue());
        } else if ((bean instanceof Boolean)) {
            xml.append(((Boolean) bean).booleanValue());
        } else if ((bean instanceof Character)) {
            xml.append(((Character) bean).charValue());
        } else if ((bean instanceof Byte)) {
            xml.append(((Byte) bean).intValue());
        } else if ((bean instanceof Short)) {
            xml.append(((Short) bean).intValue());
        } else if ((bean instanceof Integer)) {
            xml.append(((Integer) bean).intValue());
        } else if ((bean instanceof Long)) {
            xml.append(((Long) bean).longValue());
        } else if ((bean instanceof java.math.BigDecimal)) {
            xml.append(((java.math.BigDecimal) bean).toString());
        } else if ((bean instanceof java.math.BigInteger)) {
            xml.append(((java.math.BigInteger) bean).toString());
        } else if ((bean instanceof java.util.Date)) {
            xml.append(new SimpleDateFormat(dateFormat).format((java.util.Date) bean));
        } else if((bean instanceof javax.xml.datatype.XMLGregorianCalendar)){
            xml.append(new SimpleDateFormat(dateFormat).format(((javax.xml.datatype.XMLGregorianCalendar)bean).toGregorianCalendar().getTime()));
        } else if (bean.getClass().isArray()) { 
            int length = Array.getLength(bean);
            for (int i = 0; i < length; i++) {
                xml.append(getFieldXML(Array.get(bean, i), "object", dateFormat));
            }
        } else if ((bean instanceof Collection)) { 
            Collection<?> collection = (Collection<?>) bean;
            for (Iterator<?> i = collection.iterator(); i.hasNext();) {
                xml.append(getFieldXML(i.next(), "object", dateFormat));
            }
        } else if ((bean instanceof Map)) {
            Map<?, ?> map = (Map<?, ?>) bean;
            for (Iterator<?> i = map.keySet().iterator(); i.hasNext();) {
                Object key = i.next();
                Object value = map.get(key);
                xml.append(getFieldXML(value, String.valueOf(key), dateFormat));
            }
        } else if(bean.getClass().isEnum()){
            xml.append(bean.toString());
        } else {
            Method[] methods = bean.getClass().getMethods();
            Method m;
            String mName;
            for (Method method : methods) {
                m = null;
                mName = null;
                if (method.getName().startsWith("get")) {
                    try {
                        if (bean.getClass().getMethod(method.getName().replaceFirst("get", "set"),
                                method.getReturnType()) != null) {
                            m = method;
                            mName = m.getName().replaceFirst("get", "");
                        }
                    } catch (Exception e) {
                    }
                } else if (method.getName().startsWith("is")) {
                    try {
                        if (bean.getClass().getMethod(method.getName().replaceFirst("is", "set"),
                                method.getReturnType()) != null) {
                            m = method;
                            mName = m.getName().replaceFirst("is", "");
                        }
                    } catch (Exception e) {
                    }
                }
                if (m != null) {
                    Object object = m.invoke(bean);
                    xml.append(getFieldXML(object, toLowerCaseFirst(mName), dateFormat));
                }
            }
        }
        return xml.append("</").append(name).append(">");
    }

    /**
     * 第一个字符转小写 Description:
     * @param
     * @return String
     * @throws
     * @Author wangping Create Date: 2014年5月23日 下午4:00:06
     */
    private static final String toLowerCaseFirst(String str) {
        if (str == null)
            return null;
        return str.replaceFirst(String.valueOf(str.charAt(0)), String.valueOf(str.charAt(0))
                .toLowerCase());
    }
    /**
     * 从xml文件生成JavaBean对象 ，日期类型使用默认的格式，这个方法不支持带有集合类属性的对象。
     * Description: 
     * @param xml 经过CMQUtil.encodeUTF8(xml)处理的xml格式的字符串
     * @param claz 表明目标对象的类型
     * @return T 返回claz类型的JavaBean实例
     * @throws
     * @Author wangping
     * Create Date: 2014年5月23日 下午6:23:06
     */
    public static final <T> T getBean(String xml, Class<T> claz)throws Exception {
        return getBean(xml, claz, DATEFORMAT_DEFAULT,null);
    }
    /**
     * 从xml文件生成JavaBean对象 ，日期类型使用默认的格式。
     * Description: 
     * @param xml 经过CMQUtil.encodeUTF8(xml)处理的xml格式的字符串
     * @param claz 表明目标对象的类型
     * @param fieldTypes 集合属性的元素类型，这个参数非常重要，应该提供带有默认构造函数的实现类，避免传入interface或abstract类，如果接收到的类型是interface会尝试初始化.impl.类名Impl实现类，找不到就报错。
     * @return T 返回claz类型的JavaBean实例
     * @throws
     * @Author wangping
     * Create Date: 2014年5月23日 下午6:30:40
     */
    public static final <T> T getBean(String xml, Class<T> claz,Map<String,Class<?>> fieldTypes) throws Exception {
        return getBean(xml, claz, DATEFORMAT_DEFAULT,fieldTypes);
    }
    /**
     * 从xml文件生成JavaBean对象。
     * Description: 
     * @param xml 经过CMQUtil.encodeUTF8(xml)处理的xml格式的字符串
     * @param claz 表明目标对象的类型
     * @param dateFormat 日期格式
     * @return T 返回claz类型的JavaBean实例
     * @throws
     * @Author wangping
     * Create Date: 2014年5月23日 下午6:24:37
     */
    public static final <T> T getBean(String xml, Class<T> claz, String dateFormat) throws Exception {
        return getBean(xml,claz,dateFormat ,null);
    }
    /**
     * 从xml文件生成JavaBean对象。
     * Description: 
     * @param xml 经过CMQUtil.encodeUTF8(xml)处理的xml格式的字符串
     * @param claz 表明目标对象的类型
     * @param dateFormat 日期格式
     * @param fieldTypes 集合属性的元素类型，应该提供带有默认构造函数的实现类，避免传入interface或abstract类，如果接收到的类型是interface会尝试初始化.impl.类名Impl实现类，找不到就报错。
     * @return T 返回claz类型的JavaBean实例
     * @throws
     * @Author wangping
     * Create Date: 2014年5月23日 下午6:25:38
     */
    public static final <T> T getBean(String xml, Class<T> claz, String dateFormat ,Map<String,Class<?>> fieldTypes) throws Exception {
        log.debug("收到的消息："+xml);
        String xmlLocal = decodeUTF8(xml);
        log.debug("收到的消息-解码："+xmlLocal);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(xmlLocal)));
        Element root = document.getDocumentElement(); // root
        return getBean(root,claz,dateFormat ,fieldTypes);
    }
    /**
     * 从Node对象生成JavaBean对象。
     * Description: 
     * @param node xml文档对象
     * @param claz 表明目标对象的类型
     * @param dateFormat 日期格式
     * @param fieldTypes 集合属性的元素类型，应该提供带有默认构造函数的实现类，避免传入interface或abstract类，如果接收到的类型是interface会尝试初始化.impl.类名Impl实现类，找不到就报错。
     * @return T 返回claz类型的JavaBean实例
     * @throws
     * @Author wangping
     * Create Date: 2014年5月23日 下午6:27:06
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T>T getBean(Node node, Class<T> claz, String dateFormat ,Map<String,Class<?>> fieldTypes)throws Exception{
        if (node == null)
            return null;
        String value = node.getTextContent();
        if (value == null || "".equals(value)) {
            return null;
        }
        if (claz.getName().equals("byte")) {
            return (T)Byte.valueOf(value);
        } else if (claz.getName().equals("char")) {
            return (T)Character.valueOf(value.charAt(0));
        } else if (claz.getName().equals("short")) {
            return (T)Short.valueOf(value);
        } else if (claz.getName().equals("int")) {
            return (T)Integer.valueOf(value);
        } else if (claz.getName().equals("long")) {
            return (T)Long.valueOf(value);
        } else if (claz.getName().equals("float")) {
            return (T)Float.valueOf(value);
        } else if (claz.getName().equals("double")) {
            return (T)Double.valueOf(value);
        } else if (claz.getName().equals("boolean")) {
            return (T)Boolean.valueOf(value);
        } else if (java.lang.Byte.class.isAssignableFrom(claz)) {
            return (T)Byte.valueOf(value);
        } else if (java.lang.Character.class.isAssignableFrom(claz)) {
            return (T)Character.valueOf(value.charAt(0));
        } else if (java.lang.Short.class.isAssignableFrom(claz)) {
            return (T)Short.valueOf(value);
        } else if (java.lang.Integer.class.isAssignableFrom(claz)) {
            return (T)Integer.valueOf(value);
        } else if (java.lang.Long.class.isAssignableFrom(claz)) {
            return (T)Long.valueOf(value);
        } else if (java.lang.Float.class.isAssignableFrom(claz)) {
            return (T)Float.valueOf(value);
        } else if (java.lang.Double.class.isAssignableFrom(claz)) {
            return (T)Double.valueOf(value);
        } else if (java.lang.Boolean.class.isAssignableFrom(claz)) {
            return (T)Boolean.valueOf(value);
        }  else if (java.math.BigDecimal.class.isAssignableFrom(claz)) {
            return (T)new java.math.BigDecimal(value);
        }  else if (java.math.BigInteger.class.isAssignableFrom(claz)) {
            return (T)new java.math.BigInteger(value);
        } else if (java.util.Date.class.isAssignableFrom(claz)) {
            java.util.Date nDate = new SimpleDateFormat(dateFormat).parse(value);
            if(java.sql.Date.class.isAssignableFrom(claz)){
                return (T)new java.sql.Date(nDate.getTime());
            } else if(java.sql.Timestamp.class.isAssignableFrom(claz)){
                return (T)new java.sql.Timestamp(nDate.getTime());
            } else {
                return (T)nDate;
            }
        } else if(claz == String.class){
            return (T)value;
        } else if (javax.xml.datatype.XMLGregorianCalendar.class.isAssignableFrom(claz)){
            java.util.Date nDate = new SimpleDateFormat(dateFormat).parse(value);
            java.util.GregorianCalendar cal = new java.util.GregorianCalendar();
            cal.setTime(nDate);
            return (T)javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        }else if (claz.isArray()) { 
            Class<?> type = claz.getComponentType();
            NodeList nodes = node.getChildNodes();
            Object newArray = Array.newInstance(type, nodes.getLength());
            for(int i=0;i<nodes.getLength();i++){
                Array.set(newArray, i, getBean(nodes.item(i),type,dateFormat ,fieldTypes));
            }
            return (T)newArray;
        } else if (Collection.class.isAssignableFrom(claz)) { 
            if(fieldTypes == null || fieldTypes.get(node.getNodeName()) == null)
                throw new CMQUtilParseXMLMissTypeException(node.getNodeName());
            Collection newCollection;
            if(claz.getConstructors().length > 0){ // 如果是自定义的集合类，需要用自定义的类型赋值。
                newCollection = (Collection) claz.newInstance();
            }else if(List.class == claz){
                newCollection = new ArrayList();
            } else if(Set.class == claz) { 
                newCollection = new HashSet();
            } else {
                newCollection = (Collection) claz.newInstance();
            }
            NodeList nodes = node.getChildNodes();
            for(int i=0;i<nodes.getLength();i++){
                newCollection.add(getBean(nodes.item(i),fieldTypes.get(node.getNodeName()),dateFormat ,fieldTypes));
            }
            return (T)newCollection;
        } else if (Map.class.isAssignableFrom(claz)) {
            if(fieldTypes == null)
                throw new CMQUtilParseXMLMissTypeException(node.getNodeName());
            Map newMap;
            if(claz.getConstructors().length > 0){
                newMap = (Map) claz.newInstance();
            }else {
                newMap = new HashMap();
            }
            NodeList nodes = node.getChildNodes();
            for(int i=0;i<nodes.getLength();i++){
                newMap.put(nodes.item(i).getNodeName(),getBean(nodes.item(i),fieldTypes.get(node.getNodeName()),dateFormat,fieldTypes));
            }
            return (T)newMap;
        } else if(claz.isEnum()){
            return (T)Enum.valueOf((Class)claz, value);
        }  else {
            T bean;
            if(claz.isInterface()){ // 如果属性对象是一个未知的接口，尝试调用默认规则的实现类
                bean = (T) Class.forName(claz.getPackage().getName()+".impl."+claz.getSimpleName()+"Impl").newInstance();
            }else{
                bean = claz.newInstance();
            }
            NodeList childNodes = node.getChildNodes();
            Map<String, Node> nodeMap = new HashMap<String, Node>();
            for (int i = 0; i < childNodes.getLength(); i++) {
                if (childNodes.item(i) == null)
                    continue;
                nodeMap.put(childNodes.item(i).getNodeName(), childNodes.item(i));
            }
            Method[] methods = bean.getClass().getMethods();
            Method m;
            String nName;
            for (Method method : methods) {
                m = null;
                nName = null;
                if (method.getName().startsWith("get")) {
                    try {
                        m = bean.getClass().getMethod(method.getName().replaceFirst("get", "set"),
                                method.getReturnType());
                    } catch (Exception e) {
                    }
                } else if (method.getName().startsWith("is")) {
                    try {
                        m = bean.getClass().getMethod(method.getName().replaceFirst("is", "set"),
                                method.getReturnType());
                    } catch (Exception e) {
                    }
                }
                if (m != null) {
                    nName = toLowerCaseFirst(m.getName().replaceFirst("set", ""));
                    Object field = getBean(nodeMap.get(nName), m.getParameterTypes()[0], dateFormat,fieldTypes);
                    m.invoke(bean, field);
                }
            }
            return bean;
        }
    }
    /**
     * 
     * Description: 当目标类的属性有集合类型时，必须指定集合里元素的类型。没有指定时，抛出这个异常。
     * @author wangping
     * @version 1.0
     * <pre>
     * Modification History: 
     * Date         Author      Version     Description 
    ------------------------------------------------------------------
     * 2014年5月26日    wangping       1.0        1.0 Version 
     * </pre>
     */
    private static class CMQUtilParseXMLMissTypeException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        CMQUtilParseXMLMissTypeException(String fieldName){
            super("必须指明这个集合里的元素类型："+fieldName+" 。可以通过增加调用参数 fieldTypes解决这个异常");
        }
    }
}
