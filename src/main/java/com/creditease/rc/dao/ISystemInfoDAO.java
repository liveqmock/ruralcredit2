package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.SystemAnnouncement;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;

/**
 * 
 * @author liuli
 * 2013-05-14
 *
 */
public interface ISystemInfoDAO extends IBaseDao{
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param searchValue 
	* @param pagination 
	* @return    
	* Pagination
	 */
	public Pagination searchByFuzzyValueMap(Map searchValue, Pagination pagination);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param systemAnnouncement 
	* @return    
	* int
	 */
	public int addSystemAnnouncement(SystemAnnouncement systemAnnouncement);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param id 
	* @return    
	* List<SystemAnnouncement>
	 */
	public List<SystemAnnouncement> getSystemAnnouncementById(int id);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param systemAnnouncement 
	* @return    
	* int
	 */
	public int updateSystemAnnouncement(SystemAnnouncement systemAnnouncement);
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param saId 
	* @return    
	* int
	 */
	public int deleteSystemAnnouncement(int saId);
	
}
