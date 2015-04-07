package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.domain.Contact;
import com.creditease.rc.domain.JobInfo;
import com.creditease.rc.domain.Message;
import com.creditease.rc.service.IApplicationService;
import com.creditease.rc.service.IContactService;
import com.creditease.rc.service.IJobInfoService;
import com.creditease.rc.vo.ApplicationTableVo1;
import com.creditease.rc.vo.ContactVo;
import com.creditease.rc.vo.JobContentVo;

/**
 * 
 * @author zhangman (已不用)
 *
 */
@Controller
@RequestMapping("surveyFirst")
public class SurveyFirstController {

	/**
	 * 
	 * @param binder 
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}
 
	@Resource
	private IApplicationService applicationSrevice;
	
	@Resource
	private IJobInfoService jobInfoService;
	
	@Resource
	private IContactService  contactService;

//	/**
//	 * 入户调查表一 数据的添加
//	 * 
//	 * @param applicationList 
//	 * @return message
//	 */
//	@RequestMapping(value = "add")
//	public @ResponseBody
//	Message addApplacation(ApplicationTableVo1 applicationList) {
//		boolean flag = false;
//		flag = applicationSrevice.addApplication(applicationList);
//		Message message = new Message();
//		message.setSuccess(flag);
//		return message;
//	}
	/**
	 * 按 借款服务申请id 查询入户申请表一
	 * @param borrowerServiceAppId   借款服务申请id
	 * @return 入户申请表一 全部内容
	 */
	@RequestMapping(value = "showApplication1")
	public @ResponseBody
	ApplicationTableVo1 showApplicarion(String borrowerServiceAppId) {
		int borrowerServiceAppIdInteger = Integer.valueOf(borrowerServiceAppId);
		ApplicationTableVo1 applicationList = new ApplicationTableVo1();
		applicationList = applicationSrevice
				.selectApplication(borrowerServiceAppIdInteger);
		return applicationList;
	}


	/**
	 * 删除（已不用）
	 * @param borrowerServiceAppId 借款服务申请id
	 * @return message 
	 */
	@RequestMapping(value="deleteById")
	public @ResponseBody
	Message deleteBorrowerServiceAppById(String borrowerServiceAppId){
		boolean flag = false;
		int borrowerServiceAppIdInteger = Integer.valueOf(borrowerServiceAppId);
		flag = applicationSrevice.deleteBorrowerServiceApp(borrowerServiceAppIdInteger);
		Message message = new Message();
		message.setSuccess(flag);
		return message;
	}
	
	/**
	 * 同时添加工作联系人
	 * @param jobContentVo 工作联系人的VO类
	 * @return  工作联系人的VO类
	 */
	@RequestMapping(value = "jobContentAddOrUpdate")
	public @ResponseBody 
	JobContentVo jobContentAddOrUpdate(JobContentVo jobContentVo){
		ContactVo contactVo = new ContactVo(); 
		contactVo.setContacts(jobContentVo.getContacts());
		JobInfo jobInfo =  jobInfoService.addOrUpdateJobInfo(jobContentVo.getJobInfo());
		ContactVo contactVoResult = contactService.addOrUpdateContact(contactVo);
		JobContentVo jobContentVoResult  = new JobContentVo();
		jobContentVoResult.setJobInfo(jobInfo);
		jobContentVoResult.setContacts(contactVoResult.getContacts());
		return jobContentVoResult;
	}
	/**
	 * 工作 联系人 查询
	 * @param borrowerServiceAppId 借款服务申请列表
	 * @return 工作 联系人Vo对象
	 */
	@RequestMapping(value="jobContentSelect")
	public @ResponseBody JobContentVo jobContentSelect(Integer borrowerServiceAppId){
		JobContentVo jobContentVo = new JobContentVo();
		
		if(borrowerServiceAppId != null){
			JobInfo jobInfo = jobInfoService.selectByBorrowerServiceAppId(borrowerServiceAppId);
			List<Contact> contactList  = contactService.selectContact(borrowerServiceAppId);
			jobContentVo.setJobInfo(jobInfo);
			jobContentVo.setContacts(contactList);
		}
		return jobContentVo;
	} 
}
	
