package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.domain.Contact;
import com.creditease.rc.vo.ContactVo;
/**
 * 
 * @author zhangman
 *
 */
public interface IContactService {
	/**
	 * 添加联系人信息
	 * @param contacts 联系人vo对象
	 * @return 联系人vo对象
	 */
	public ContactVo addOrUpdateContact(ContactVo contacts);
	/**
	 *  查询联系人信息
	 * @param borrowerServiceAppId 借款服务申请id
	 * @return 联系人列表
	 */
	public List<Contact> selectContact(int borrowerServiceAppId);

	/**
	 *  按 id 删除
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
