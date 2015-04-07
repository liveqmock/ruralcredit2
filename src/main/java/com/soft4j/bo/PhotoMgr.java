/*
 * Created on 2005-6-12
 * Author stephen
 * Email zhoujianqiang AT gamil DOT com
 * CopyRight(C)2005-2008 , All rights reserved.
 */
package com.soft4j.bo;

import java.io.File;
import java.util.UUID;

import javax.servlet.jsp.PageContext;

import com.creditease.rc.common.Constants;
import com.creditease.rc.util.PropertiesUtil;
import com.creditease.rc.util.UploadUtil;
import com.soft4j.httpupload4j.SmartUpload;

/**
 * 照片的管理类.
 * 
 * @author stephen
 * @version 1.0.0
 */
public final class PhotoMgr {
	
	public static String PIC_STORAGE_DIR = null;
	
	static{
		String classesPath = PhotoMgr.class.getClassLoader().getResource(File.separator).getPath();
		PIC_STORAGE_DIR = PropertiesUtil.getVlaue(classesPath + File.separator + "config" + File.separator + "picture.properties",
				Constants.PICTURESTORAGEDIRECTORY);
	}
	
	/**
	 * 文件上传方法.
	 *@author yifengwang
	 *@param su s
	 *@param pageContext p
	 *@return String s
	 *@throws Exception 2012-12-24下午05:51:26
	 */
	public static String fileUpload(SmartUpload su,PageContext pageContext) throws Exception {
	    com.soft4j.httpupload4j.File suFile = null;
	    int fileCount = 0;
	    try {
	    	//获取传递过来的参数
	    	String userId = su.getRequest().getParameter("user_id");
	    	String passId = su.getRequest().getParameter("pass_id");

	        String fileExt = "";
	        int fileSize = 0;
	        String AllowedExtensions = ",jpg,jpeg,gif,";//允许上传的文件类型
	        double maxFileSize = 2*1024;//单文件最大大小，单位KB
	        //校验文件类型和大小
	        for (int i=0; i<su.getFiles().getCount();i++) {
	            suFile = su.getFiles().getFile(i);
	            if (suFile.isMissing()){
	            	continue;
	            }
	            //校验文件大小
	            fileSize = suFile.getSize()/1024;//字节转换成KB
	            if(fileSize==0) {
	            	fileSize=1;
	            }
	            if(maxFileSize<fileSize){
	            	throw new Exception("单个上传相片的容量不能超过["+maxFileSize+"KB]");
	            }
	
	           /* //校验文件类型
	            if (suFile.getFileExt() == null
	                    || "".equals(suFile.getFileExt())) {
	                fileExt = ",,";
	            } else {
	                fileExt = "," + suFile.getFileExt().toLowerCase() + ",";
	            }
	            if (!"".equals(AllowedExtensions)
	                    && AllowedExtensions.indexOf(fileExt) == -1) {
	                throw new Exception("您上传的文件[" + suFile.getFileName()
	                        + "]的类型为系统禁止上传的文件类型，不能上传！");
	            }*/
	            fileCount++;
	        }
	        if (fileCount==0) {
	        	throw new Exception("请选择上传的文件");
	        }
	        //准备保存文件
	        //String filePath="D:\\tomcat\\webapps\\test\\photo\\";//这里填写项目中存放上传文件的物理路径
	        // String filePath="D:\\temp\\";
	        //创建文件夹
	        Boolean flag= UploadUtil.createFileDirectory(PIC_STORAGE_DIR);
	        String filePath = PIC_STORAGE_DIR + File.separator;
	        String realyName = UploadUtil.rename(suFile.getFileName());
	        for (int i=0; i<su.getFiles().getCount();i++) {
	        	suFile = su.getFiles().getFile(i);
	        	suFile.saveAs(filePath+realyName,SmartUpload.SAVE_PHYSICAL);//保存文件
	        }
	        pageContext.setAttribute("upLoadFileName", realyName);
	        //成功返回null
	        return null;
	    } finally {
	    	//
	    }
	}

}
