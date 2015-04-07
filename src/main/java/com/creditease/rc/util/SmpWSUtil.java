/**
 * Title:WebServiceUtil2SMP.java
 * Description:
 */
package com.creditease.rc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.security.User;
import com.creditease.rc.app.smp.EmployeeDTO;
import com.creditease.rc.app.smp.EmployeeResult;
import com.creditease.rc.app.smp.EmployeeWebService;
import com.creditease.rc.app.smp.OrganizeWebService;
import com.creditease.rc.common.Constants;
import com.creditease.rc.common.DepartmentEntity;
import com.creditease.rc.framework.exception.AppBusinessException;

/**
 * Title:WebServiceUtil2SMP.java
 * Description:
 * Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 
 * @author 韩大年
 * @version v1.0
 *          2013-3-18
 */
@Service
public class SmpWSUtil {

	/**
	 * @Description 默认构造器
	 */
	public SmpWSUtil() {
		// TODO Auto-generated constructor stub
	}

	@Resource
	private EmployeeWebService employeeWebService;
	@Resource
	private OrganizeWebService organizeService; // 机构服务
	private Map<String, String> areaDepartMap = new HashMap<String, String>(0); // 区域部门Map<部门id,部门名称>
	private Integer areaGroupId;// UC中(部门结构)部门id对应(角色结构)中区域ID
	// 分公司名称(区域部门)-总部大部门名称
	private String areaDepartmentName;
	// 区域部门id-总部大部门ID
	private String areaDepartmentId;
	// 信贷员id
	private String loanOfficerId;

	private String departmentId;// 员工所属直接部门ID
	private String departmentName;// 员工所属直接部门名
	
	/**
	 * 根据系统标识获取员工
	 * @param systemSign
	 * @return
	 */
	public EmployeeResult getEmployeeBySystemSign(){
		
		return this.employeeWebService.getEmployeeBySystemSign(Constants.SYSTEM_SIGN);
	}

	/**
	 * 获取smp中客户经理信息
	 * checkstyle
	 * 
	 * @author wyf
	 * @param loanOfficerId
	 * @return
	 *         EmployeeDTO
	 */
	public EmployeeDTO getEmployeeDTO(String loanOfficerId) {
		Integer comEmpId = 0;
		EmployeeDTO employeeDTO = null;
		if (CommonUtil.isNotEmpty(loanOfficerId)) {
			comEmpId = Integer.valueOf(loanOfficerId);
			EmployeeResult employeeResult = employeeWebService.getEmployee(comEmpId,
					Integer.parseInt(Constants.SYSTEM_SIGN));
			if (null != employeeResult) {
				employeeDTO = employeeResult.getEmployee();
			}
		}
		if (employeeDTO == null) {
			employeeDTO = new EmployeeDTO();
		}
		return employeeDTO;
	}

	/**
	 * 根据信贷员id查询分公司名称
	 * checkstyle
	 * 
	 * @author wyf
	 * @param loanOfficerId
	 * @return
	 *         String
	 */
	public String getAreaDepartmentNameByLoanOfficerId(String loanOfficerId) {

		return this.getEmployeeDTO(loanOfficerId).getAreaDepartmentName();
	}

	/**
	 * 根据信贷员id查询分公司名称:map<id,名称>
	 * checkstyle
	 * 
	 * @author wyf
	 * @param loanOfficerId
	 * @return
	 *         Map<String,String>
	 */
	public Map<String, String> getAreaDepartmentNameMapByLoanOfficerId(String loanOfficerId) {
		Map<String, String> areaDepartmentNameMap = new HashMap<String, String>();
		EmployeeDTO employeeDTO = this.getEmployeeDTO(loanOfficerId);
		if (null != employeeDTO) {
			areaDepartmentName = employeeDTO.getAreaDepartmentName();
			if (employeeDTO.getAreaDepartmentId() != null) {
				areaDepartmentId = employeeDTO.getAreaDepartmentId().toString();
			}
		}
		if (null == areaDepartmentName) {
			areaDepartmentName = "";
		}
		if (null == areaDepartmentId) {
			areaDepartmentId = "";
		}
		areaDepartmentNameMap.put(areaDepartmentId, areaDepartmentName);
		return areaDepartmentNameMap;
	}

	/**
	 * 根据客户经理id获取员工所属直接部门名
	 * checkstyle
	 * 
	 * @author wyf
	 * @param loanOfficerId
	 * @return
	 *         String
	 */
	public String getDepartmentNameByLoanOfficerId(String loanOfficerId) {

		departmentName = this.getEmployeeDTO(loanOfficerId).getDepartmentName();
		return departmentName;
	}

	/**
	 * 
	 * @author 韩大年
	 * @function： 获取UC中所有分公司名称map<AreaDepartmentId,AreaDepartmentName>
	 * @return
	 *         2012-3-29
	 */
	public Map<String, String> getAllAreaDepartmentNameMap() {
		areaDepartMap = this.organizeService.getAllAreaDepart(Integer.parseInt(Constants.SYSTEM_SIGN));
		return areaDepartMap;
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 查询部门列表
	 * @return List<DepartmentEntity>
	 * @version v1.0
	 *          2013-3-18
	 */
	public List<DepartmentEntity> getDepartmentList() {
		List<DepartmentEntity> departmentList = new ArrayList<DepartmentEntity>(0);
		Integer areaDepartmentId = -1;
		areaDepartmentId = SpringSecurityUtils.getCurrentUser().getAreaDepartmentId();
		String areaDepartmentName = SpringSecurityUtils.getCurrentUser().getAreaDepartmentName();
		if (areaDepartmentId == null) {
			areaDepartmentId = -1;
		}
		if (areaDepartmentId.intValue() > 0) {
			DepartmentEntity departmentEntity = new DepartmentEntity();
			departmentEntity.setDepartmentId(areaDepartmentId.toString());
			departmentEntity.setDepartmentName(areaDepartmentName);
			departmentList.add(departmentEntity);
		} else {
			areaDepartMap = this.organizeService.getAllAreaDepart(Integer.parseInt(Constants.SYSTEM_SIGN));
			Set<Map.Entry<String, String>> set = areaDepartMap.entrySet();
			if (set.size() > 0) {
				for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
					Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
					DepartmentEntity departmentEntity = new DepartmentEntity();
					departmentEntity.setDepartmentId(entry.getKey());
					departmentEntity.setDepartmentName(entry.getValue());
					departmentList.add(departmentEntity);
				}
			}

		}

		return departmentList;
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 根据depId=AreaDepartmentId查询分公司下所有在职员工
	 *               根据depId=DepartmentId查询当前部门下（信贷员部门）所有在职员工
	 * @param areaDepartmentId
	 * @version v1.0
	 *          2013-6-4
	 */
	public List<EmployeeDTO> getIterUsersByDepId(String depId) {
		List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
		EmployeeResult employeeResult = employeeWebService.getIterUsersByDepId(Integer.parseInt(depId),
				Integer.parseInt(Constants.SYSTEM_SIGN));
		if (null != employeeResult) {
			employeeDTOList = employeeResult.getEmployeeList();
		}
		return employeeDTOList;
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 根据客户经理id和系统标示查询客户经理信息。
	 * @param loanOfficerId
	 * @param system_sign
	 * @return
	 * @version v1.0
	 *          2013-6-8
	 */
	public EmployeeDTO getEmployee(String loanOfficerId, String system_sign) {
		EmployeeDTO employeeDTO = null;
		if (CommonUtil.isEmpty(system_sign)) {
			return this.getEmployee(loanOfficerId);
		} else {
			EmployeeResult employeeResult = this.employeeWebService.getEmployee(Integer.valueOf(loanOfficerId),
					Integer.parseInt(system_sign));
			if (null != employeeResult) {
				employeeDTO = employeeResult.getEmployee();
			}
		}
		return employeeDTO;
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 根据客户经理id查询smp中客户信息
	 * @param loanOfficerId
	 * @return
	 * @version v1.0
	 *          2013-6-8
	 */
	public EmployeeDTO getEmployee(String loanOfficerId) {
		EmployeeResult employeeResult = this.employeeWebService.getEmployee(Integer.valueOf(loanOfficerId),
				Integer.parseInt(Constants.SYSTEM_SIGN));
		EmployeeDTO employeeDTO = null;
		if (null != employeeResult) {
			employeeDTO = employeeResult.getEmployee();
		}

		return employeeDTO;
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 获取登录用户的角色
	 * @return
	 * @version v1.0
	 *          2013-7-25
	 */
	public Set<String> getUserAuthorities() {
		Set<String> set = new HashSet<String>();
		User user = SpringSecurityUtils.getCurrentUser();
		Set<GrantedAuthority> GrantedAuthoritySet = (Set<GrantedAuthority>) user.getAuthorities();
		if (null != GrantedAuthoritySet && GrantedAuthoritySet.size() > 0) {
			for (GrantedAuthority grantedAuthority : GrantedAuthoritySet) {
				set.add(grantedAuthority.getAuthority());
			}
		}

		return set;

	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 根据部门id(可是departmentId,teamDepartmentId,cityDepartmentID,arealDepartmentId),查询直属下级部门Map<id,name>
	 * @return
	 * @version v1.0
	 *          2013-7-23
	 */
	public Map<String, String> getSubordinateDepartMapByDepartId(String departmentId) {
		if (CommonUtil.isEmpty(departmentId)) {
			throw new AppBusinessException("departmentId is null");
		}
		return this.organizeService.getAllDepartByDepartId(Integer.valueOf(departmentId));

	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 根据部门id(可是departmentId,teamDepartmentId,cityDepartmentID,arealDepartmentId),查询直属下级部门List
	 * @return
	 * @version v1.0
	 *          2013-7-23
	 */
	public List<DepartmentEntity> getSubordinateDepartListByDepartId(String departmentId) {
		List<DepartmentEntity> departmentList = new ArrayList<DepartmentEntity>(0);
		Map<String, String> map = getSubordinateDepartMapByDepartId(departmentId);

		Set<Map.Entry<String, String>> set = map.entrySet();
		if (set.size() > 0) {
			for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
				DepartmentEntity departmentEntity = new DepartmentEntity();
				departmentEntity.setDepartmentId(entry.getKey());
				departmentEntity.setDepartmentName(entry.getValue());
				departmentList.add(departmentEntity);
			}
		}
		return departmentList;
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 根据部门id和角色。获取员工列表,可传多个角色
	 * @param departmentId
	 * @param roleName
	 * @return
	 * @version v1.0
	 *          2013-8-8
	 */
	public List<EmployeeDTO> getEmployeesListByRole_DepartmentId(String departmentId, String... roleNames) {
		if (CommonUtil.isEmpty(departmentId) || null == roleNames || roleNames.length == 0) {
			throw new AppBusinessException("departmentId is null or roleNames is null or roleNames.length=0");
		}
		// 部门下所有员工list
		List<EmployeeDTO> employeeList = null;
		// 所有角色的员工map
		Map<String, String> allEmployeeMap = new HashMap<String, String>(0);
		// 部门下角色的员工list
		List<EmployeeDTO> descEmployeeList = new ArrayList<EmployeeDTO>(0);

		// 循环角色入参
		for (String roleName : roleNames) {
			Map<String, String> employeeMap = null;
			// 获取角色下所有人（userid,name_zh）
			employeeMap = this.getEmployeesByRole(roleName);

			if (null != employeeMap && employeeMap.size() > 0) {
				allEmployeeMap.putAll(employeeMap);
			}
		}

		// 获取部门下所有员工
		employeeList = this.getIterUsersByDepId(departmentId);
		if (CommonUtil.isNotEmpty(employeeList)) {
			for (EmployeeDTO employeeDTO : employeeList) {
				String userId = employeeDTO.getComEmpId().toString();
				if (allEmployeeMap.containsKey(userId)) {
					descEmployeeList.add(employeeDTO);
				}
			}
		}
		return descEmployeeList;
	}
	/**
	 * 
	 * @author 韩大年
	 * @Description: 根据角色代码,获取对应的用户map(只有account(也就是userName)可用)
	 * @param roleName
	 * @return userMap:userMap<userID,name_zh>
	 * @version v1.0
	 *          2013-8-8
	 */

	public Map<String, String> getEmployeesByRole(String roleName) {
		Map<String, String> userMap = new HashMap<String, String>();
		List<EmployeeDTO> employeeList = null;
		if (CommonUtil.isEmpty(roleName)) {
			throw new AppBusinessException("roleName is null");
		}
		EmployeeResult employeeResult = this.employeeWebService.getEmployeesByRole(roleName, Constants.SYSTEM_SIGN);
		if (null != employeeResult) {
			employeeList = employeeResult.getEmployeeList();
			if (CommonUtil.isNotEmpty(employeeList)) {
				for (EmployeeDTO employeeDTO : employeeList) {
					userMap.put(employeeDTO.getComEmpId().toString(), employeeDTO.getName());
				}

			}
		}

		return userMap;

	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 获取所有城市一级部门列表
	 * @return
	 * @version v1.0
	 *          2013-7-23
	 */
	public Map<String, String> getAllCityDepart() {
		return this.organizeService.getAllCityDepart(Integer.valueOf(Constants.SYSTEM_SIGN));
	}

	public Integer getAreaGroupId() {
		return areaGroupId;
	}

	public void setAreaGroupId(Integer areaGroupId) {
		this.areaGroupId = areaGroupId;
	}

	public Map<String, String> getAreaDepartMap() {
		return areaDepartMap;
	}

	public void setAreaDepartMap(Map<String, String> areaDepartMap) {
		this.areaDepartMap = areaDepartMap;
	}

	public String getAreaDepartmentName() {
		return areaDepartmentName;
	}

	public void setAreaDepartmentName(String areaDepartmentName) {
		this.areaDepartmentName = areaDepartmentName;
	}

	public String getAreaDepartmentId() {
		return areaDepartmentId;
	}

	public void setAreaDepartmentId(String areaDepartmentId) {
		this.areaDepartmentId = areaDepartmentId;
	}

	public String getLoanOfficerId() {
		return loanOfficerId;
	}

	public void setLoanOfficerId(String loanOfficerId) {
		this.loanOfficerId = loanOfficerId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
