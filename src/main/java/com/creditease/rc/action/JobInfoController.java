package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.domain.JobInfo;
import com.creditease.rc.service.IJobInfoService;
/**
 * 
 * @author zhangman
 *
 */
@Controller
@RequestMapping(value="jobInfo")
public class JobInfoController {
	/**
	 * 
	 * @param binder WebDataBinder对象
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
	@Resource
	private IJobInfoService jobInfoService;
	
	/**
	 * 添加或修改工作信息
	 * @param jobInfo 工作信息对象
	 * @return 工作信息对象
	 */
	@RequestMapping(value = "addOrUpdate")
	public @ResponseBody JobInfo addOrUpdateJobInfo(JobInfo jobInfo){
		return jobInfoService.addOrUpdateJobInfo(jobInfo);
		
	};
	/**
	 * 
	 * @param borrowerServiceAppId 借款服务申请id
	 * @return 工作信息对象
	 */
	@RequestMapping(value= "select")
	public @ResponseBody
	JobInfo selectByBorrowerServiceAppId(String borrowerServiceAppId){
		JobInfo jobInfo = new JobInfo();
		int borrowerServiceAppIdInt = 0;
		if(borrowerServiceAppId != null && !"".equals(borrowerServiceAppId)){
			 borrowerServiceAppIdInt = Integer.valueOf(borrowerServiceAppId);
			 jobInfo = jobInfoService.selectByBorrowerServiceAppId(borrowerServiceAppIdInt);
			 return jobInfo;
		}else{
			return null;
		}
	}
}
