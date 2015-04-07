package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.Contact;
/**
 * 
 * @author zhangman
 *
 */
public interface IContactDAO {
	/**
	 * 批量添加联系人信息
	 * @param contacts 联系人对象列表
	 */
	public void addContact(List<Contact> contacts);
	
	/**
	 *  单个添加联系人信息
	 * @param contact 联系人对象
	 * @return 联系人对象id
	 */
	public int addContact(Contact contact);
	/**
	 *  查询联系人信息
	 * @param borrowerServiceAppId 借款服务申请id
	 * @return 联系人列表
	 */
	public List<Contact> selectContact(int borrowerServiceAppId);

	/**
	 * 单个修改联系人信息
	 * @param contact 联系人对象
	 */
	public void updateContact(Contact contact);
	
	/**
	 * 批量修改联系人信息
	 * @param contacts 联系人对象list
	 */
	public void updateContact(List<Contact> contacts);
	/**
	 * 按 id 删除
	 * @param borrowerServiceAppId 借款服务申请id
	 * @return 删除的行数
	 */
	public int deleteContact(int borrowerServiceAppId);
	/**
	 * 按照各种条件查询
	 * @param contact 联系人类
	 * @return 联系人列表
	 */
	public List<Contact> selectByContact(Contact contact);
	/**
	 * 查询一条
	 * @param contact 联系人类
	 * @return 一条联系人
	 */
	public Contact selectOneContact(Contact contact);
}
