package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IContactDAO;
import com.creditease.rc.dao.ICreditApplicationDAO;
import com.creditease.rc.dao.IFamilymemberDao;
import com.creditease.rc.dao.ISystemInfoDAO;
import com.creditease.rc.domain.Contact;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.SystemAnnouncement;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IContactService;
import com.creditease.rc.service.ISystemInfoService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.vo.ContactVo;
/**
 * 
 * @author liuli
 * 2013-05-14
 *
 */
@Service
public class SystemInfoService implements ISystemInfoService {
	
	@Resource
	private ISystemInfoDAO systemInfoDAO;
	
	@Resource
	private ICreditApplicationDAO creditApplicationDAO;

	@Override
	public Pagination searchSystemAnnouncements(
			SystemAnnouncement sAnnouncement, Pagination pagination,
			String fuzzyValue, String sort, String order, HttpSession session) {
		
		if("createTime".equals(sort)){
			sort = "CREATE_TIME";
		}else if("title".equals(sort)){
			sort = "TITLE";
		}else if("systemAnnouncementId".equals(sort)){
			sort = "SYSTEM_ANNOUNCEMENT_ID";
		}
		Map searchValue = new HashMap();
		searchValue.put("sort", sort);
		searchValue.put("order", order);
		
		return systemInfoDAO.searchByFuzzyValueMap(searchValue, pagination);
	}

	@Override
	public int addSystemAnnouncement(SystemAnnouncement systemAnnouncement) {
		Date time = new Date();
		systemAnnouncement.setCreateTime(time);
		systemAnnouncement.setUpdateTime(time);
		return systemInfoDAO.addSystemAnnouncement(systemAnnouncement);
	}

	@Override
	public SystemAnnouncement getSystemAnnouncementById(int id) {
		List l = systemInfoDAO.getSystemAnnouncementById(id);
		if(null!=l&&l.size()>0){
			return (SystemAnnouncement)l.get(0);
		}else{
			return null;
		}
	}

	@Override
	public int updateSystemAnnouncement(SystemAnnouncement systemAnnouncement) {
		systemAnnouncement.setUpdateTime(new Date());
		int i = systemInfoDAO.updateSystemAnnouncement(systemAnnouncement);
		return 0;
	}

	@Override
	public int deleteSystemAnnouncement(int saId) {
		int i = systemInfoDAO.deleteSystemAnnouncement(saId);
		return i;
	}

	/**
	 * 更新rl_credit_application表中的 AUDIT_STATUS字段值
	 * 用于在线上系统出现异常时进行应急操作
	 * liuli 2013-05-17
	 */
	@Override
	public int updateCreditAppAuditStatus(String businessNumber,String auditStatus) {
		CreditApplication creditApplication = new CreditApplication();
		int returnValue = 0;
		if(null!=businessNumber){
			creditApplication = creditApplicationDAO.selectByGroupNumber(businessNumber.trim());
			//如果有对应的数据则更新
			if(null!=creditApplication){
				creditApplication.setGroupNumber(businessNumber);
				creditApplication.setAuditStatus(auditStatus); 
				returnValue = creditApplicationDAO.submitAuditing(creditApplication);
				System.out.println("returnValue-----------"+returnValue);
			}
		}
		return returnValue;
	}

	
}
