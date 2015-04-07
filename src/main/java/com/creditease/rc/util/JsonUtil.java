package com.creditease.rc.util;
 
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
 /**
  * 
  *  JsonUtil.java
  * @author yifengwang
  *  2012-12-24 下午05:47:19
  * @version V2.0
  */
 public class JsonUtil {
     /**
      * 从普通的Bean转换为字符串
      *@author yifengwang
      *@param o o
      *@return 2012-12-24下午05:45:30
      */
     public static String getJson(Object o){
         JSONObject jo=JSONObject.fromObject(o);
         return jo.toString();
     }
     /**
      * 从列表转换为字符串
      *@author yifengwang
      *@param list list
      *@return 2012-12-24下午05:45:48
      */
	public static String getJson(List list){
         JSONArray ja=JSONArray.fromObject(list);
         return ja.toString();
     }
     /**
      * 从Java对象数组转换为字符串
      *@author yifengwang
      *@param arry arr
      *@return 2012-12-24下午05:45:59
      */
     public static String getJson(Object[] arry){
         JSONArray ja=JSONArray.fromObject(arry);
         return ja.toString();
     }
     /**
      * 从json格式的字符串转换为Map对象
      *@author yifengwang
      *@param s s
      *@return 2012-12-24下午05:46:11
      */
     public static Map getObject(String s){
         return     JSONObject.fromObject(s);
     }
     /**
      * 从json格式的字符串转换为List
      *@author yifengwang
      *@param s s
      *@return 2012-12-24下午05:46:20
      */
     public static List getArray(String s){
         return JSONArray.fromObject(s);
     }
     /**
      * 从json格式的字符串转换为某个Bean
      *@author yifengwang
      *@param s s
      *@param cls cls
      *@return 2012-12-24下午05:46:31
      */
     public static Object getObject(String s,Class cls){
         JSONObject jo=JSONObject.fromObject(s);
         return JSONObject.toBean(jo, cls);
     }
     /**
      * 从json格式的字符串转换为某类对象的数组
      *@author yifengwang
      *@param s s
      *@param cls cls
      *@return 2012-12-24下午05:46:40
      */
     public static Object getArray(String s,Class cls){
    	 JSONArray ja = new JSONArray();
    	 if(StringUtils.isNotBlank(s)){
    		 ja = JSONArray.fromObject(s);
    	 }
    	 return JSONArray.toArray(ja, cls);
     }
     /**
      * 把一个json数组串转换成集合，且集合里存放的为实例Bean
     * checkstyle
     * @author wyf   
     * @param jsonArrStr 
     * @param clazz 
     * @return    
     * List
      */
     public static List getListFromJsonArrStr(String jsonArrStr, Class clazz) {   
         JSONArray jsonArr = JSONArray.fromObject(jsonArrStr);   
         List list = new ArrayList();   
         for (int i = 0; i < jsonArr.size(); i++) {   
             list.add(JSONObject.toBean(jsonArr.getJSONObject(i), clazz));   
         }   
         return list;   
     }
     
     /**  
      * 把一个json数组串转换成集合，且集合里的对象的属性含有另外实例Bean  
      * @param jsonArrStr e.g. [{'data':[{'name':'get'}]},{'data':[{'name':'set'}]}]  
      * @param clazz e.g. MyBean.class  
      * @param classMap e.g. classMap.put("data", Person.class)  
      * @return List  
      */  
      public static List getListFromJsonArrStr(String jsonArrStr, Class clazz, Map classMap) {   
          JSONArray jsonArr = JSONArray.fromObject(jsonArrStr);   
          List list = new ArrayList();   
          for (int i = 0; i < jsonArr.size(); i++) {   
              list.add(JSONObject.toBean(jsonArr.getJSONObject(i), clazz, classMap));   
          }   
          return list;   
      }
      /**
       * 
      * checkstyle
      * @author wyf   
      * @param object 
      * @return    
      * String
       */
      public static String getJackson(Object object){
    	  ObjectMapper mapper=JacksonMapper.getInstance();
          JsonGenerator gen=null;
          String json=null;
          try {
              StringWriter sw = new StringWriter();  
              gen = new JsonFactory().createJsonGenerator(sw);  
              mapper.writeValue(gen, object);  
              gen.close();  
              json = sw.toString();  

          } catch (IOException e) {

              e.printStackTrace();

          }
          return json;
          
      }
 }