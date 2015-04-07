package com.creditease.rc.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;

import com.creditease.rc.domain.Contact;
import com.creditease.rc.domain.CustomerConsult;
import com.creditease.rc.domain.SystemAnnouncement;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.ContactVo;
/**
 * 
 * @author liuli
 * 2013-05-14
 * 系统信息服务类
 *
 */
public interface ISystemInfoService {
	/**
	 * 分页查询系统公告列表
	 * @param sAnnouncement 系统信息对象
	 * @param pagination 分页条件
	 * @param fuzzyValue 模糊查询条件
	 * @return 分页列表
	 */
	public Pagination searchSystemAnnouncements(SystemAnnouncement sAnnouncement,Pagination pagination,String fuzzyValue, String sort,String order,HttpSession session);
	
	/**
	 * 添加公告
	 * @param systemAnnouncement
	 * @return
	 */
	public int addSystemAnnouncement(SystemAnnouncement systemAnnouncement);
	
	/**
	 * 根据id获取公告信息
	 * @param id
	 * @return
	 */
	public SystemAnnouncement getSystemAnnouncementById(int id);
	
	/**
	 * 更新系统公告
	 * @param systemAnnouncement
	 * @return
	 */
	public int updateSystemAnnouncement(SystemAnnouncement systemAnnouncement);
	
	/**
	 * 删除系统公告
	 * @param systemAnnouncement
	 * @return
	 */
	public int deleteSystemAnnouncement(int saId);
	
	/**
	 * 更新rl_credit_application表中的 AUDIT_STATUS字段值
	 * 用于在线上系统出现异常时进行应急操作
	 * liuli 2013-05-17
	 */
	public int updateCreditAppAuditStatus(String businessNumber,String auditStatus);
	
}
