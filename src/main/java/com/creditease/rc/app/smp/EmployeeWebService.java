package com.creditease.rc.app.smp;
import java.util.HashMap;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.creditease.core.ws.MapAdapter;
import com.creditease.core.ws.WsConstants;
import com.creditease.core.ws.dto.AuthoritiesResult;

@WebService(name = "employeeService", targetNamespace = WsConstants.NS)
public interface EmployeeWebService {

	 /**
	 * 在职员工
	 * @return
	 */
	EmployeeResult getOnOfficeEmployee(@WebParam(name = "employeeTemp" )EmployeeDTO employeeTemp);

	 /**
	 * 今日入职员工
	 * @return
	 */
	EmployeeResult getOnBoardEmployee(@WebParam(name = "employeeTemp" )EmployeeDTO employeeTemp);

	 /**
	 * 今日离职员工
	 * @return
	 */
	EmployeeResult getTurnoverEmployee(@WebParam(name = "employeeTemp" )EmployeeDTO employeeTemp);

	/**
	 * 根据员工ID获取下级所有员工信息
	 */
	EmployeeResult getSubordinateEmployee(@WebParam(name = "comEmpId" )Integer comEmpId,@WebParam(name = "systemSign") Integer systemSign);

	/**
	 * 根据员工ID获取上级所有员工信息
	 */
	EmployeeResult getSuperiorEmployee(@WebParam(name = "comEmpId" )Integer comEmpId,@WebParam(name = "systemSign") Integer systemSign);

	/**
	 * 根据员工ID获取员工信息
	 */
	EmployeeResult getEmployee(@WebParam(name = "comEmpId" )Integer comEmpId,@WebParam(name = "systemSign") Integer systemSign);

	/**
	 * 根据员工id，递归获取部门下级部门中所有的员工。
	 *
	 * @param empId
	 * @return
	 */
	EmployeeResult getIterUsersByUserId(@WebParam(name = "empId") Integer empId,@WebParam(name = "systemSign") Integer systemSign);

	/**
	 * 根据区域id获取所有下面所有员工信息
	 * @param areaid
	 * @param systemSign
	 * @return
	 */
	EmployeeResult getIterUsersByAreaid(@WebParam(name = "areaid") Integer areaid,@WebParam(name = "systemSign") Integer systemSign);
	/**
	 * 根据部门id获取所有下面所有员工信息
	 * @param depId
	 * @param systemSign
	 * @return
	 */
	EmployeeResult getIterUsersByDepId(@WebParam(name = "depId") Integer depId,@WebParam(name = "systemSign") Integer systemSign);
	/**
	 * 根据姓名、区域ID、城市id、城市部门
	 * @param systemSign
	 * @param empName
	 * @param area_depId
	 * @param cityId
	 * @param city_depId
	 * @return
	 */
	EmployeeResult getEmployeeByNameId(@WebParam(name = "systemSign")Integer systemSign,@WebParam(name = "empName")String empName,@WebParam(name = "area_depId")Integer area_depId,@WebParam(name = "cityId")Integer cityId,@WebParam(name = "city_depId")Integer city_depId,@WebParam(name = "emp_type")Integer emp_type);

	/**
	 * 根据员工id、系统标识，查询员工tree。
	 *
	 * @param empId
	 * @param systemSign
	 * @param empType 1:admin*，2：普通员工
	 * @param hasChild 是否查询子节点 1:查询子节点；0：不查询
	 * @param hasEmp 是否查询员工  y:查询;n:不查询
	 * @param hasGroup 是否查询员工所在的组1:查询deptTree；2：查询groupTree；3：查询全部
	 * @return
	 */
	@XmlJavaTypeAdapter(MapAdapter.class)
	HashMap<String, String> getEmpTreeByEmpId(@WebParam(name = "empId") Integer empId, @WebParam(name = "systemSign") String systemSign, @WebParam(name = "empType") Integer empType, @WebParam(name = "hasChild") Integer hasChild, @WebParam(name = "hasEmp") String hasEmp, @WebParam(name = "hasGroup") Integer hasGroup);

	/**
	 * 根据两个员工的id，判断员工是否是同一个城市，是，返回城市id，否，返回-1；
	 *
	 * @param fromEmpId
	 * @param toEmpId
	 * @param systemSign
	 * @return
	 */
	String getCityByEmp(@WebParam(name = "fComEmpId") Integer fComEmpId, @WebParam(name="sComEmpId") Integer sComEmpId, @WebParam(name = "systemSign")Integer systemSign);

	/**
	 * 员工部门转移，需要一起将组进行调整。
	 *
	 * @param empId
	 * @param toDeptId
	 * @param systemSign
	 * @return
	 */
	String transEmp(@WebParam(name = "empId") Integer empId, @WebParam(name = "toDeptId") Integer toDeptId, @WebParam(name = "toGroupId") String toGroupIds, @WebParam(name = "systemSign")Integer systemSign);

	/**
	 * 根据员工id或者名字 获取员工信息
	 * @param systemSign
	 * @param idOrName
	 * @return
	 */
	EmployeeResult getEmployeeByIDOrName(@WebParam(name = "systemSign")Integer systemSign,List<String> idOrName);
	
	/**
	 * 员工部门转移，需要一起将组进行调整。
	 * @param empId
	 * @param toDeptId
	 * @param systemSign
	 * @return
	 */
	String checkEmpTrans(@WebParam(name = "empId") Integer empId, @WebParam(name = "toDeptId") Integer toDeptId, @WebParam(name = "systemSign")Integer systemSign);
	
	/**
	 * 根据员工id获取员工信息
	 * @param systemSign
	 * @param idOrName
	 * @param date
	 * @return
	 */
	EmployeeResult getEmployeeByIDOrNameForLender(@WebParam(name = "systemSign")Integer systemSign,List<String> idOrName);
	/**
	 * 从“借款人服务部”开始，递归查询下级部门，不包括具体的部门节点，
	 * 如"客服部门"，"晨曦团队"等。
	 * 
	 * @param systemSign
	 * @return
	 */
	String getIterDeptsWithoutLeaf(@WebParam(name = "systemSign") Integer systemSign);

	/**
	 * 根据组织机构id、、系统标识，递归获取员工所在的部门的上级部门。
	 *
	 * @param param
	 * @return
	 */
	String getCityDeptIdByDeptSys(@WebParam(name = "deptId") Integer deptId, @WebParam(name = "systemSign") Integer systemSign);
	
	/***
	 * 咨询页面增加员工
	 * @param emplyee
	 * @return
	 */
	String addSimpleEmp(@WebParam(name = "employee") EmployeeDTO employee);
	
	/**
	 * 根据员工id，系统标识获取本组和所有下级组的员工
	 * @param systemSign
	 * @param empId
	 * @return
	 */
	EmployeeResult getEmpInfoByGroup(@WebParam(name = "empId") String empId,@WebParam(name = "systemSign")String systemSign);
	
	/***
	 * 根据身份证验证员工是否存在
	 * @param identityNo
	 * @param systemSign
	 * @return
	 */
	String countEmployeeByIdentityNo(@WebParam(name = "identityNo") String identityNo,@WebParam(name = "systemSign")String systemSign);
	
	/**构建全部部门、所有部门下的员工，返回员工、组织架构集合。
	 * 
	 * @param systemSign 系统标识
	 * @param groupOrOrg 组下面的还是组织架构的员工，1：组织架构，2：组.
	 * @return
	 */
	TreeResult buildAllOrgEmpTree(@WebParam(name = "systemSign")Integer systemSign, @WebParam(name = "groupOrOrg")Integer groupOrOrg);
	/**
	 * 根据员工id,系统标志判断 是否 是咨询人
	 * @param empId
	 * @param systemSign
	 * return boolean
	 */
	boolean isZiXunRen(@WebParam(name = "empId") String empId,@WebParam(name = "systemSign")String systemSign);
	/**
	 * 根据员工id,系统标志判断 是否 是团队经理
	 * @param empId
	 * @param systemSign
	 * return boolean
	 */
	boolean isJingLi(@WebParam(name = "empId") String empId,@WebParam(name = "systemSign")String systemSign);

	/**
	 * 通过操作人id、系统标识获取团队长列表
	 * 
	 * @param systemSign
	 * @param empId
	 * @param empType 员工类型:1为团队经理,2为销售人员。
	 * @return
	 */
	EmployeeResult getLeaderOrSalesByEmpId(@WebParam(name = "systemSign")Integer systemSign, @WebParam(name = "empId")Integer empId, @WebParam(name = "empType")Integer empType);
	
	/**
	 * 根据员工id查询所在组的名称
	 * @param comEmpId
	 * @return
	 */
	String getAreaNamesByComEmId(@WebParam(name = "comEmpId")Integer comEmpId);
	//==========================车贷工作流接口==============================================
	/**
	 * 根据账号获取员工信息
	 * @param account
	 * @return
	 */
	EmployeeResult getEmployeeByAccount(@WebParam(name = "account")String account);
	/**
	 * 根据员工账号获取对应的角色列表
	 * @param empId
	 * @return
	 */
	RolesResult getRolesByAccount(@WebParam(name = "account")String account);
	/**
	 * 根据角色代码、系统标识获取对应的用户列表
	 * @param roleId
	 * @return
	 */
	EmployeeResult getEmployeesByRole(@WebParam(name = "roleName")String roleName,@WebParam(name = "systemSign")String systemSign);
	/**
	 * 根据用户名和密码获取员工信息，即用户校验
	 * @param account
	 * @param password
	 * @return
	 */
	Boolean getEmployeeByAccountPassword(@WebParam(name = "account")String account,@WebParam(name = "password")String password);
	/**
	 * 根据角色名称、系统标识查询角色信息
	 * @param roleName 角色名称，支持多个角色查询，角色之间用逗号分隔
	 * @param systemSign 系统标识
	 * @return RolesResult
	 * @throws 
	 * @Author qj
	 */
	RolesResult getRolesInfomation(@WebParam(name = "roleName")String roleName,@WebParam(name = "systemSign")String systemSign);
	/**
	 * 
	 * Description: 查询某个角色下所有的用户，并且需要根据当前登录用户的数据权限（当前组）进行过滤
	 *
	 * @param 
	 * @return EmployeeResult
	 * @throws 
	 * @Author xcl
	 * Create Date: 2012-8-22 上午10:12:28
	 */
	EmployeeResult getEmployeesByRoleAndGroupId(List<String> roleName,@WebParam(name = "systemSign")String systemSign,@WebParam(name = "groupId") Integer groupId);
	
	/**
	 *	根据在职状态、创建时间段、员工姓名、区域部门ID以及角色ID查询员工
	 * @param activeStatus在职状态
	 * @param beginCreateTime员工创建开始日期
	 * @param endCreateTime 员工创建结束日期
	 * @param userName 员工姓名
	 * @param areaDepartId 区域部门ID
	 * @param roleId 角色ID
	 * @return EmployeeResultForRural
	 * @Author qj
	 */
	EmployeeForRuralResult getEmployeeInfoByStatusTimeNameDeptIdAndRoleId(@WebParam(name = "active_status" )String activeStatus,@WebParam(name = "beginCreateTime") String beginCreateTime,
				@WebParam(name = "endCreateTime") String endCreateTime,@WebParam(name = "userName") String userName,@WebParam(name = "areaDepartId") Integer areaDepartId,
				@WebParam(name = "roleId") Integer roleId);
	
	/**
	 * 根据系统标识获取系统下所有角色
	 * @param systemSign 系统标识
	 * @return EmployeeForRuralResult
	 * @Author qj
	 */
	@XmlJavaTypeAdapter(MapAdapter.class)
	HashMap<String, String> getAllRolesBySystemSign(@WebParam(name = "systemSign") String systemSign);
	
	/**
	 * 根据用户名和密码(密文)获取员工信息
	 * @param account
	 * @param password（密文）
	 * @return
	 */
	EmployeeResult getEmployeeForYCD(@WebParam(name = "account")String account,@WebParam(name = "password")String password);
	
	/**
	 * 根据用户名和密码(密文)获取员工信息
	 * @param account
	 * @param password（密文）
	 * @return
	 */
	String getManagerMenuJson(@WebParam(name = "systemSign")Integer systemSign,@WebParam(name = "account")String account);
	/***
	 * 其他系统增加员工
	 * @param emplyee
	 * @return
	 */
	String addEmployee(@WebParam(name = "employee") EmployeeDTO employee);
	
	/***
	 * 其他系统修改员工
	 * @param emplyee
	 * @return
	 */
	String updateEmployee(@WebParam(name = "employee") EmployeeDTO employee);
	
	/***
	 * 其他系统删除员工（在职状态置为离职，停用账户）
	 * @param emplyee
	 * @return
	 */
	String deleteEmployee(@WebParam(name = "employee") EmployeeDTO employee);
	
	/***
	 * 其他系统操作员工
	 * @param emplyee
	 * @return
	 */
//	Message operateEmployeeList(@WebParam(name = "message") Message message);
	
	/***
	 * 其他系统删除员工（在职状态置为离职，停用账户）
	 * @param emplyee
	 * @return
	 */
	String deleteEmployeeList(@WebParam(name = "employeeList") List<EmployeeDTO> employeeList);
	
	/**
	 * 获取部门下城市负责人
	 * @param depId
	 * @return
	 */
	EmployeeResult getEmpOfCityByDeptId(@WebParam(name = "depId") Integer depId);
	
	/**
	 * 获取该部门的城市下所有人员信息
	 * @param depId
	 * @return
	 */
	EmployeeResult getEmpByDeptIdAndCityId(@WebParam(name = "depId") Integer depId, @WebParam(name = "cityId") Integer cityId);
	
	/**
	 * 根据系统标识获取员工
	 * @param systemSign
	 * @return
	 */
	EmployeeResult getEmployeeBySystemSign(@WebParam(name = "systemSign") String systemSign);
	
	/**
	 * 根据在职状态或者创建时间查询员工
	 */
	EmployeeResult getEmployeeInfoByActiveStatusOrCreateTime(@WebParam(name = "active_status" )String activeStatus,@WebParam(name = "createTime") String createTime);
	
	/**
	 * 根据user的组ID取得上级或下级所挂员工ID
	 * @param type up 查询上级组
	 *             down 查询下级组
	 * @return
	 */
	String getUserGroupTree(@WebParam(name = "systemSign")String systemSign, @WebParam(name = "comEmpId")Integer comEmpId, @WebParam(name = "type")String type);
	
	/**
	 * 根据user的账号密码判断此员工是否属于投一二三
	 * @param account  账号
	 * @param password 密码
	 * @return
	 */
	AuthoritiesResult getEmployeeOfAreaDepart(@WebParam(name = "account")String account,@WebParam(name = "password")String password,@WebParam(name = "dept")String dept);
	
}
