package com.creditease.rc.common;


import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import com.creditease.rc.util.PropertiesUtil;
import com.creditease.rc.util.UploadUtil;

/**
 * 
 * Title:SerialNumber.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2013-4-7下午04:03:00
 * @author 王毅峰
 * @version v2.0
 */
@Service
public class BusinessSerialNumber{
	
	/**
     * 存储的分隔符
    */
	private final static String FIELD_SEPARATOR = ",";
	/**
	 * 
	 */
	public BusinessSerialNumber(){
		
	}

	/**
	 * 生成编号
	* @author wyf   
	* @return String 
	* @throws Exception    
	 */
    public synchronized String getSerialNumber() throws Exception{
    	Date date = new Date();
        int n = getOrUpdateNumber(date, 1);
        if(n==0){
        	return null;
        }else{
        	return format(date) + format(n);
        }
    }
    /**
     * 更新文件编号并返回
    * @author wyf   
    * @param current 
    * @param start 
    * @return int 
    * @throws Exception    
    *  
     */
    private int getOrUpdateNumber(Date current, int start) throws Exception {
    	String classesPath = getClass().getClassLoader().getResource(File.separator).getPath();
    	System.out.println(classesPath);
    	String filePath = PropertiesUtil.getVlaue(classesPath + File.separator + "config" + File.separator + "picture.properties",
				Constants.PICTURESTORAGEDIRECTORY);
    	System.out.println(filePath);
    	File file = new File(filePath+File.separator+Constants.SERIAL_NUMBER);
        String date = format(current);
        String year = date.substring(0,2);
        int num = start;
        boolean createFlag = true;
        if(file.exists()) {
            List<String> list = UploadUtil.readList(file);
            String[] data = list.get(0).split(FIELD_SEPARATOR);
            if(year.equals(data[0])) {
                num = Integer.parseInt(data[1]);
            }
        }else{
        	createFlag = UploadUtil.createFileDirectory(filePath);
        }
        if(createFlag){
        	UploadUtil.rewrite(file, year + FIELD_SEPARATOR + (num + 1));
        }else{
        	num = 0;
        }
        return num;
    } 
    /**
     * 格式化日期
    * @author wyf   
    * @param date 
    * @return    
    * String
     */
    private String format(Date date) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    	String ymd = sdf.format(date);
    	String year = ymd.substring(2);
        return year;
    }
    /**
     * 格式化编号
    * @author wyf   
    * @param num 
    * @return    
    * String
     */
    private String format(int num) {
    	DecimalFormat df = null;
    	char[] chs = new char[6];
        for(int i = 0; i < chs.length; i++) {
            chs[i] = '0';
        }
        df = new DecimalFormat(new String(chs));
        return df.format(num);
    }
    /**
     * 
    * checkstyle
    * @author wyf   
    * @return String 
    * @throws IllegalAccessException    
     */
    private String getWebRoot() throws IllegalAccessException{
    	String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();;
    	if (path.indexOf("WEB-INF") > 0) {
    		path = path.substring(0, path.indexOf("WEB-INF/classes"));
    	}else{
    		throw new IllegalAccessException("路径获取错误");
    	}
    	return path;
    }
    /**
    * checkstyle
    * @author wyf   
    * @param args     
    * void
     */
    public static void main(String[] args) {
		BusinessSerialNumber bsn = new BusinessSerialNumber();
		try {
			String num = bsn.getSerialNumber();
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
