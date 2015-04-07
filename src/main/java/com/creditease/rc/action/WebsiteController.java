package com.creditease.rc.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.creditease.rc.domain.Website;
import com.creditease.rc.service.ICustomerConsultPoolService;
import com.creditease.rc.service.IWebsiteService;

@Controller
@RequestMapping("websiteController")
public class WebsiteController {
	 @Resource
	 private ICustomerConsultPoolService icustomerConsultPoolService;
	 
	 @Resource
	 private IWebsiteService iwebsiteService;
	 
	 @RequestMapping(value = "uploadExcel")
	 public  @ResponseBody Map<String, String> uploadExcel(
	            @RequestParam("UpLoadFile") MultipartFile UpLoadFile) {
		 
		 Map<String, String> map = new HashMap<String, String>();
	        InputStream is = null;
	        try {
	            is = UpLoadFile.getInputStream();
	            Workbook workbook = WorkbookFactory.create(is);
	            List<String[]> list = icustomerConsultPoolService.getAllData(
	                    workbook, 0);
	            //if (map.containsKey("成功上传")) {
	            	 List<Website> websitList = new ArrayList<Website>();
	            	 Website website;
	            	 for (int i = 0; i < list.size(); i++) {
	                     String[] value = list.get(i);
	                     website=new Website();
	                     website.setBranchofficeId(value[0]);
	                     website.setBranchofficeName(value[1]);
	                     website.setWorkAddress(value[2]);
	                     website.setBusinessTime(value[3]);
	                     website.setConsultingTelephone(value[4]);
	                     website.setSearchAddress(value[5]);
	                     website.setProvincehome(value[6]);
	                     website.setCityhome(value[7]);
	                     website.setCountyhome(value[8]);
	                     websitList.add(website);
	            	 }
	            	 //调用导入方法
	            	 iwebsiteService.uploadExcel(websitList);
	                 System.out.println("导入成功");
	     				map.put("成功上传", list.size() + "条");
	          //  }else {
	                // 导入失败
	              //  System.out.println("导入失败");
	                return map;
	           // }
	         } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("导入失败");
	        } finally {
	            if (is != null) {
	                try {
	                    is.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	            is = null;
	        }
	 }
}
