package com.creditease.rc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IContactDAO;
import com.creditease.rc.domain.Contact;
import com.creditease.rc.framework.dao.impl.BaseDao;
/**
 * 
 * @author zhangman
 *
 */
@Repository
public class ContactDAO implements IContactDAO {
	@Resource
	private BaseDao baseDao;

	
	@Override
	public void addContact(List<Contact> contacts) {
		baseDao.batchInsert("CONTACT.insert", contacts);
	}
	@Override
	public int addContact(Contact contact) {
		return (Integer) baseDao.insertObject("CONTACT.insert", contact);
	}
	@Override
	public List<Contact> selectContact(int borrowerServiceAppId) {
		return (List<Contact>) baseDao.queryList("CONTACT.selectAll", borrowerServiceAppId);
	}
	@Override
	public void updateContact(Contact contact) {
		baseDao.update("CONTACT.update", contact);
	}
	@Override
	public void updateContact(List<Contact> contacts) {
		baseDao.batchUpdate("CONTACT.update", contacts);
	}
	@Override
	public int deleteContact(int borrowerServiceAppId) {
		// TODO Auto-generated method stub
		return baseDao.update("CONTACT.deleteByBSAId", borrowerServiceAppId);
	}
	@Override
	public List<Contact> selectByContact(Contact contact) {
		// TODO Auto-generated method stub
		return (List<Contact>) baseDao.queryList("CONTACT.selectByContact", contact);
	}
	@Override
	public Contact selectOneContact(Contact contact) {
		// TODO Auto-generated method stub
		return (Contact) baseDao.queryUnique("CONTACT.selectByContact", contact);
	}
}
