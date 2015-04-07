package com.creditease.rc.action;

import java.io.File;
import java.text.NumberFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.common.Constants;
import com.creditease.rc.domain.Message;
import com.creditease.rc.service.IRepairSysDataService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.PropertiesUtil;
import com.creditease.rc.util.UploadUtil;

@Controller
@RequestMapping("repairSysData")
public class RepairSysDataController {

	@Resource
	private IRepairSysDataService repairSysDataService;
	/**
	 * 更改业务编号
	* @author wyf  
	* @param: caId
	* @param: busNum
	* @param: repairType
	* @return: Message 
	 */
	@RequestMapping(value="repairBusNum")
	@ResponseBody
	public Message repairBusNum(String caId,String busNum,String repairType){
		Message msg = new Message();
		if(CommonUtil.isNotEmpty(repairType) && CommonUtil.isNotEmpty(caId) && CommonUtil.isNotEmpty(busNum)){
			try {
				//1\校验新编号是否大于下一编号
				int  maxNum = readSerialNum();
				String s = busNum.substring(busNum.length()-6);
				if(Integer.valueOf(s)>=maxNum){
					msg.setMsg("新编号后六位超出预期，请重新输入新编号");
					msg.setSuccess(false);
					return msg;
				}
				boolean result = true;
				int checkNew = repairSysDataService.queryCountByOld(busNum);
				if(checkNew>0){
					msg.setMsg("新编号已存在,请重新输入新编号");
					msg.setSuccess(false);
					return msg;
				}
				if("0".equals(repairType)){//ById
					result = repairSysDataService.repairBusId(caId, busNum);
				}else if("1".equals(repairType)) {//ByNum
					int checkOld = repairSysDataService.queryCountByOld(caId);
					if(checkOld!=1){
						msg.setMsg("原编号不存在或重复");
						msg.setSuccess(false);
						return msg;
					}
					result = repairSysDataService.repairBusNum(caId, busNum);
				}
				if(result){
					msg.setMsg("修改成功");
				}else{
					msg.setMsg("修改失败");
				}
				msg.setSuccess(result);
			} catch (Exception e) {
				msg.setMsg("系统异常,请稍后重试或联系管理员");
				msg.setSuccess(false);
				e.printStackTrace();
			}
		}
		return msg;
	}
	/**
	 * 读取文件中存放的下一个编号
	* @author wyf  
	* @return: int 
	 */
	private int readSerialNum(){
		int num = 0;
		String classesPath = getClass().getClassLoader().getResource(File.separator).getPath();
    	System.out.println(classesPath);
    	String filePath = PropertiesUtil.getVlaue(classesPath + File.separator + "config" + File.separator + "picture.properties",
				Constants.PICTURESTORAGEDIRECTORY);
    	System.out.println(filePath);
    	File file = new File(filePath+File.separator+Constants.SERIAL_NUMBER);
        if(file.exists()) {
        	List<String> list = UploadUtil.readList(file);
        	String[] data = list.get(0).split(",");
            num = Integer.parseInt(data[1]);
        }
        return num;
	}
}
