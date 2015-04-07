package com.creditease.rc.app.smp;

import java.util.HashMap;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.creditease.core.ws.MapAdapter;
import com.creditease.core.ws.WsConstants;

@WebService(name = "organizeService", targetNamespace = WsConstants.NS)
public interface OrganizeWebService {

	/**
	 * 获取所有区域级部门列表
	 * @param systemSign
	 * @return
	 */
	@XmlJavaTypeAdapter(MapAdapter.class)
	HashMap<String,String> getAllAreaDepart(@WebParam(name = "systemSign")Integer systemSign);
	/**
	 * 获取所有城市列表
	 * @param systemSign
	 * @return
	 */
	@XmlJavaTypeAdapter(MapAdapter.class)
	HashMap<String,String> getAllCity(@WebParam(name = "systemSign")Integer systemSign);
	/**
	 * 获取所有城市一级部门列表
	 * @param systemSign
	 * @return
	 */
	@XmlJavaTypeAdapter(MapAdapter.class)
	HashMap<String,String> getAllCityDepart(@WebParam(name = "systemSign")Integer systemSign);
	/**
	 * 根据区域部门ID获取城市列表，返回city_code, area_name两个字段
	 * @param areaDepartId
	 * @param systemSign
	 * @return
	 */
	@XmlJavaTypeAdapter(MapAdapter.class)
	HashMap<String,String> getCityByAreaDepartId(@WebParam(name = "areaDepartId")Integer areaDepartId,
			@WebParam(name = "systemSign")Integer systemSign);
	/**
	 * 根据区域部门ID获取城市列表，返回cityDTO的集合。
	 * @param areaDepartId
	 * @param systemSign
	 * @return
	 */
	CityResult getCityListByAreaDepartId(@WebParam(name = "areaDepartId")Integer areaDepartId,
			@WebParam(name = "systemSign")Integer systemSign);
	/**
	 * 根据区域部门ID和城市ID获取城市一级部门列表
	 * @param areaDepartId，cityId
	 * @param systemSign
	 * @return
	 */
	@XmlJavaTypeAdapter(MapAdapter.class)
	HashMap<String,String> getCityDepartById(@WebParam(name = "areaDepartId")Integer areaDepartId,
			@WebParam(name = "cityId")Integer cityId,@WebParam(name = "systemSign")Integer systemSign);
	
	/**
	 * 根据区域部门ID和城市ID获取城市一级部门列表(包括营业部门的地址)
	 * @param areaDepartId，cityId
	 * @param systemSign
	 * @return
	 */
	@XmlJavaTypeAdapter(MapAdapter.class)
	HashMap<String,String> getCityDepartByIdForMIC(@WebParam(name = "areaDepartId")Integer areaDepartId,
			@WebParam(name = "cityId")Integer cityId,@WebParam(name = "systemSign")Integer systemSign);

	/**
	 * 获取组织机构
	 * @param id 部门id或城市id
	 * @param type id类型（1_区域级部门；2_城市；3_城市一级部门）
	 * @param systemSign 系统标识
	 * @return 组织机构列表
	 */
	@XmlJavaTypeAdapter(MapAdapter.class)
	HashMap<String,String> getOrganizeById(@WebParam(name = "Id")Integer id,
			@WebParam(name = "type")String type,@WebParam(name = "systemSign")Integer systemSign);
	/**
	 * 获取部门职位信息
	 * @param systemSign
	 * @return
	 */
	DepartPositionResult getDepartPositions(@WebParam(name = "systemSign") Integer systemSign);
	/**
	 * 通过组关系id，获取关联上下级的组。
	 * 
	 * @param relationId
	 * @param systemSign
	 * @return
	 */
	String getIterGroupByRelationId(@WebParam(name = "relationId")Integer relationId, @WebParam(name = "systemSign") Integer systemSign);
	
	/**
	 * 根据部门ID，查询该部门的直属所有部门的ID
	 * @param deptId
	 * @return
	 */
	List<Integer> getAllDepartIdByDepartId(@WebParam(name = "departId")Integer departId);
	/**
	 * 根据部门ID，查询该部门的直属所有部门的ID和名称
	 * @param deptId
	 * @return
	 */
	@XmlJavaTypeAdapter(MapAdapter.class)
	HashMap<String,String> getAllDepartByDepartId(@WebParam(name = "departId")Integer departId);
	/**
	 * 根据部门ID，查询该部门下级为城市一级部门的所有部门的ID
	 * @param deptId
	 * @param dept_type
	 * @return
	 */
	List<Integer> getCityDepartIdByDepartId(@WebParam(name = "departId")Integer departId);
	
	/**
	 * 
	 * Description: 根据用户编号(com_emp_id)查询该用户在组内设置负责的办公室，
	 * 				返回HashMap，包括办公室编号、办公室名称；(从组开始查询组织机构)
	 * @param com_emp_id 员工ID
	 * @param systemSign 系统标识
	 * @return HashMap<String,String>
	 * @throws 
	 * @Author xcl
	 * Create Date: 2012-3-22 下午03:47:14
	 */
	@XmlJavaTypeAdapter(MapAdapter.class)
	HashMap<String, String> getOfficeByEmpId(@WebParam(name = "com_emp_id") Integer com_emp_id);

	/**
	 * 
	 * Description: 根据办公室编号查询该办公室下所有团队名称
	 *				如某某小组形式，返回HashMap，包括团队的编号、团队名称。(直接查询组织机构)
	 * @param departId 办公室编号
	 * @return HashMap<String,String>
	 * @throws 
	 * @Author xcl
	 * Create Date: 2012-3-22 下午03:48:07
	 */
	@XmlJavaTypeAdapter(MapAdapter.class)
	HashMap<String, String> getTeamByDepartId(@WebParam(name = "departId") Integer departId);
	
	/**
	 * 根据部门编号查询对应组的ID
	 * @param department_id
	 * @return Integer
	 */
	Integer getGroupIdByDepartmentId(@WebParam(name = "departmentId") Integer departmentId);
	
	/**
	 * 根据系统标识查询该系统下的区域部门，然后查询出区域部门对应的组信息
	 * @param 
	 * @return HashMap<String,String>
	 * @throws 
	 * @Author qj
	 * Create Date: 2012-7-19 上午05:55:33
	 */
	@XmlJavaTypeAdapter(MapAdapter.class)
	HashMap<String,String> getGroupInfoByAreaDepartmentIdAndSystem(@WebParam(name = "systemSign")Integer systemSign);
}
