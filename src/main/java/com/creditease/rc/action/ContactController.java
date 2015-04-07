package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.creditease.rc.service.IContactService;
import com.creditease.rc.vo.ContactVo;
/**
 * 
 * @author zhangman
 *
 */
@Controller
@RequestMapping(value="contact")
public class ContactController {
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
	private IContactService  contactService;
	
	/**
	 * 
	 * @param contacts 联系人对象
	 * @return 联系人vo对象
	 */
	@RequestMapping(value="addOrUpdate")
	public @ResponseBody ContactVo addOrUpdateContact(ContactVo contacts){
		return contactService.addOrUpdateContact(contacts);
	};
	/**
	 * 
	 * @param borrowerServiceAppId 借款服务申请id
	 * @return 联系人列列表
	 */
	public List<Contact> selectContact(int borrowerServiceAppId) {
		return null;
	}


	/**
	 * 
	 * @param borrowerServiceAppId 借款服务申请id
	 * @return 联系人vo类
	 */
	@RequestMapping(value = "select")
	public @ResponseBody
	ContactVo selectByBorrowerServiceAppId(String borrowerServiceAppId){
		ContactVo contactVo = new ContactVo();
		int borrowerServiceAppIdInt = 0 ;
		if(borrowerServiceAppId != null && !"".equals(borrowerServiceAppId)){
		 borrowerServiceAppIdInt = Integer.valueOf(borrowerServiceAppId);
		}
			List<Contact> contacts = new ArrayList<Contact>();
			contacts = contactService.selectContact(borrowerServiceAppIdInt);
			contactVo.setContacts(contacts);
			return contactVo;
	}
}
