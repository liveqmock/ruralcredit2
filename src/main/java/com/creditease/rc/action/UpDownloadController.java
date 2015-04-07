package com.creditease.rc.action;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.creditease.rc.common.Constants;
import com.creditease.rc.domain.Message;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.IDataDictionaryService;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.util.PropertiesUtil;
import com.creditease.rc.util.UploadUtil;

/**
 * 
 * UpDownloadController.java
 * @author yifengwang
 * 2012-12-24 下午01:31:50
 * @version V2.0
 */
@Controller
@RequestMapping("upDownload")
public class UpDownloadController {
	@Resource
	private ICreditApplicationService creditApplicationService;
	@Resource
	private IDataDictionaryService dataDictionaryService;
	@Resource
	private DataDictionaryController dataDictionaryController;
	/**
	 * 上传文件
	* @author wyf  
	* @param: request
	* @param: response
	* @return: String 
	 */
	@RequestMapping(value="transferFile",method = RequestMethod.POST)
	@ResponseBody
	public String transferFile(HttpServletRequest request, HttpServletResponse response){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;    
        //获取前台传值  
//        String[] contents = multipartRequest.getParameterValues("content");  
        Message message = new Message();
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();    
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile mf = entity.getValue();    
            try {  
                //解析EXCEL
            	InputStream is = mf.getInputStream();
                dataDictionaryService.impExcelTemplate(is);
                dataDictionaryController.reloadInitGetAllDic();
                message.setSuccess(true);
	        } catch (Exception e) {  
	        	message.setSuccess(false);
	        	message.setMsg(e.getMessage());
	        	e.printStackTrace();  
	        	break; 
	        }    
        }   
        return JsonUtil.getJson(message);
	}
	/**
	 * 
	 *@author yifengwang
	 *@param request s
	 *@param response s
	 *@param creditAppId s 
	 *@return 2012-12-24下午01:32:56
	 */
	@RequestMapping(value="zipFile")
	public String download(HttpServletRequest request,HttpServletResponse response,Integer creditAppId){
		String contentType = "application/octet-stream";
		try {
			//1、找出小组所有上传文件名
			List<String> list = creditApplicationService.queryUploadFileList(creditAppId);
			//2、将上传文件打包
			String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/config");
			String ctxPath = PropertiesUtil.getVlaue(realPath + "/" + Constants.PICTURECONFIG, Constants.PICTURESTORAGEDIRECTORY);
			//TODO 文件命名
			String zipName = ctxPath+"/"+"zipTest.zip";
			UploadUtil.compress(list, ctxPath, zipName);
			//3、下载打包好的文件
			UploadUtil.download(request, response, "zipTest.zip", contentType, "zipTest.zip");
			//4、删除压缩包
			boolean del = UploadUtil.delFile(zipName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
