package com.creditease.rc.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

import com.creditease.rc.common.Constants;
/**
 * 
 * Title:UploadUtil.java
 * Description: 宜农贷2.0系统开发
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 2012-11-30下午01:34:39
 * @author 王毅峰
 * @version v2.0
 */
public class UploadUtil {
	
	/**
	 * 创建文件夹
	 *@author yifengwang
	 *@param path path
	 *@return 2012-12-24下午05:48:15
	 */
	public static Boolean createFileDirectory(String path){
		Boolean flag=true;
		try {
			File dirFile  =   new  File(path);
             if ( ! (dirFile.exists())  &&   ! (dirFile.isDirectory())) {
                 boolean  creadok  =  dirFile.mkdirs();
                 if (creadok) {
                    System.out.println( " ok:创建文件夹成功！ " );
                } else {
                    System.out.println( " err:创建文件夹失败！ " );    
                    flag=false;
                }
            }
        } catch (Exception e) {
        	flag=false ;
            e.printStackTrace();
            System.out.println(e);
        } 
        return   flag ;
	}
	/**
	 * 将上传的文件进行重命名 
	* @author wyf  
	* @@param name name
	* @return String s
	 */
	public static String rename(String name){
		String realName = UUID.randomUUID().toString()+"_"+name;
		return realName;
	}
	/**
	 * 删除文件
	 *@author yifengwang
	 *@param path p
	 *@return 2012-12-7下午04:29:35
	 */
	public static boolean delFile(String path) {
		boolean result = false;
		File file = new File(path);
		if (file.exists() && file.isFile()) {
			file.delete();
			result = true;
		}
		return result;
	}
	/**
	 * 下载
	 *@author yifengwang
	 *@param request r
	 *@param response r
	 *@param storeName s
	 *@param contentType c
	 *@param realName 2012-12-24下午05:48:58
	 */
	public static void download(HttpServletRequest request,HttpServletResponse response,
			String storeName, String contentType,String realName){
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			response.setContentType("text/html;charset=UTF-8");  
			request.setCharacterEncoding("UTF-8");  
			String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/config");
			String ctxPath = PropertiesUtil.getVlaue(realPath + "/" + Constants.PICTURECONFIG, Constants.PICTURESTORAGEDIRECTORY);
			String downLoadPath = ctxPath +"/"+ storeName;
			long fileLength = new File(downLoadPath).length();  
			response.setContentType(contentType);  
			response.setHeader("Content-disposition", "attachment; filename=" + new String(realName.getBytes("GBK"), "ISO8859-1"));  
			response.setHeader("Content-Length", String.valueOf(fileLength));  
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());  
			byte[] buff = new byte[2048];  
			int bytesRead;
			while(-1!=(bytesRead = bis.read(buff, 0, buff.length))){
				 bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				bis.close();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
			
		}
	}
	/**
	 * 压缩文件
	 *@author yifengwang
	 *@param includes i
	 *@param srcPathname s
	 *@param destPathName 2012-12-3上午11:49:13
	 */
	public static void compress(List<String> includes,String srcPathname,String destPathName){
		File srcFile = new File(srcPathname);
		File zipFile = new File(destPathName);
		if(!srcFile.exists()){
			throw new RuntimeException("src File or Dir "+srcPathname+" does not exist.");
		}
		
		Project project = new Project();
		FileSet fileSet = new FileSet();
		if(srcFile.isDirectory()){
			fileSet.setDir(srcFile);
			for(String inc:includes){
				fileSet.setIncludes(inc);
			}
		}else{
			fileSet.setFile(srcFile);
		}
		Zip zip = new Zip();
		zip.setProject(project);
		zip.setDestFile(zipFile);
		zip.addFileset(fileSet);
		zip.setEncoding("GBK");
		zip.execute();
	}
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param file 
	* @param data     
	* void
	 */
	public static void rewrite(File file, String data) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {        
            if(bw != null) {
               try { 
                   bw.close();
               } catch(IOException e) {
                   e.printStackTrace();
               }
            }            
        }
    }
    /**
     * 
    * checkstyle
    * @author wyf   
    * @param file 
    * @return    
    * List<String>
     */
    public static List<String> readList(File file) {
        BufferedReader br = null;
        List<String> data = new ArrayList<String>();
        try {
            br = new BufferedReader(new FileReader(file));
            for(String str = null; (str = br.readLine()) != null; ) {
                data.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null) {
               try { 
                   br.close();
               } catch(IOException e) {
                   e.printStackTrace();
               }
            }
        }
        return data;
    }
}
