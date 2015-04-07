package com.creditease.rc.util;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.security.User;

/**
 * Title: 
 * Description: 农村商贷系统研发
 * Copyright: Copyright (c) 2012
 * Company: 普信恒业科技发展（北京）有限公司
 * Date: 2013-3-7
 * @author: 解兵丰
 * @version: v1.0
 */
public class RoleUtil {
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @return    
	* String
	 */
	public static String getRolePathOfCurrentUser(){
		User user = SpringSecurityUtils.getCurrentUser();
		String strAuthorities = user.getAuthorities().toString();
		return getRolePath(strAuthorities);
	}
	/**
	 * 
	* checkstyle
	* @author wyf   
	* @param strAuthorities 
	* @return    
	* String
	 */
	public static String getRolePath(String strAuthorities){
		String path = "";
		if(strAuthorities.contains("ROLE_sysadmin")){
			path = "ROLE_sysadmin";
			return path;
		} else if(strAuthorities.contains("ROLE_LOAN_MGR")){
			path = "ROLE_LOAN_MGR";
			return path;
		} else if(strAuthorities.contains("ROLE_LOAN_OFFICER")){
			path = "ROLE_LOAN_OFFICER";
			return path;
		} else if(strAuthorities.contains("ROLE_RISK_MGR")){
			path = "ROLE_RISK_MGR";
			return path;
		}
		return path;
	}
	
}
