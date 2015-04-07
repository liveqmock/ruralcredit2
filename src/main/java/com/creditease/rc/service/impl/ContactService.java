package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IContactDAO;
import com.creditease.rc.domain.Contact;
import com.creditease.rc.service.IContactService;
import com.creditease.rc.vo.ContactVo;
/**
 * 
 * @author zhangman
 *
 */
@Service
public class ContactService implements IContactService {

	@Resource
	private IContactDAO contactDAO;
	
	@Override
	public ContactVo addOrUpdateContact(ContactVo contacts) {
		ContactVo contactVoTamp = new ContactVo();
		List<Contact> contactList = contacts.getContacts();
		 if(contacts.getContacts().get(0).getContactId()!= null && !"".equals(contacts.getContacts().get(0).getContactId())){
			 contactDAO.updateContact(contactList);
			 return contacts;
		 }else{
			 List<Contact> contactListTemp = new ArrayList<Contact>();
			 for (Contact contact : contactList) {
				int contactId = contactDAO.addContact(contact);
				contact.setContactId(contactId);
				contactListTemp.add(contact);
			}
			 contactVoTamp.setContacts(contactListTemp);
			 return contactVoTamp;
		 }
	}

	@Override
	public List<Contact> selectContact(int borrowerServiceAppId) {
		// TODO Auto-generated method stub
		return contactDAO.selectContact(borrowerServiceAppId);
	}

	@Override
	public int deleteContact(int borrowerServiceAppId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Contact> selectByContact(Contact contact) {
		// TODO Auto-generated method stub
		return contactDAO.selectByContact(contact);
	}
	
	@Override
	public Contact selectOneContact(Contact contact) {
		// TODO Auto-generated method stub
		return contactDAO.selectOneContact(contact);
	}
}
