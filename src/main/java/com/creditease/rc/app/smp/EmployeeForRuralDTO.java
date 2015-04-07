package com.creditease.rc.app.smp;

import java.io.Serializable;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlType;

import com.creditease.core.ws.WsConstants;

@XmlType(name = "employeeForRural", namespace = WsConstants.NS)
public class EmployeeForRuralDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer comEmpId;//员工ID
	private String account;//账号
	private String name;//名字
	private String sex;//性别
	private String emailAddr;
	private String tel;
	private String identityNo;//身份证
	private Integer empParentId;//上级领导
	private String activeStatus;//员工在职状态
	private Integer empLevel;//员工级别
	private Integer empType;//员工类型
	private String createTime;//创建时间
	private String systemSign;//系统标志	
	private Integer areaDepartmentId;//总部大部门ID	
	private String areaDepartmentName;//总部大部门名称	
	private Integer areaId;//城市ID
	private String areaName;//城市名字
	private Integer cityDepartmentId;//城市一级部门ID
	private String cityDepartmentName;//城市一级部门名字
	private Integer departmentId;//员工所属直接部门ID
	private String departmentName;//员工所属直接部门名
	private Integer teamDepartmentId;//员工所属团队部门ID
	private String teamDepartmentName;//员工所属团队部门名称
	private String positionId;//职位id
	private String position;//职位
	
	private String code;//城市code
	//员工编号
	private Integer comEmpNo;
	
	/**新增加属性*/
	private HashMap<String,String> roleMap=new HashMap<String, String>();//key为角色ID,value为角色名称
	
	/**新增加属性*/
	private HashMap<String, String> deptMap=new HashMap<String, String>();//key为部门ID,value为部门名称
	
	/**新增加属性*/
	private String flag;//判断上级城市是否为山东,是返回：true;不是返回：false
	
	public String getAccount() {
		return account;
	}
	public String getActiveStatus() {
		return activeStatus;
	}
	public Integer getAreaDepartmentId() {
		return areaDepartmentId;
	}
	public String getAreaDepartmentName() {
		return areaDepartmentName;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public Integer getCityDepartmentId() {
		return cityDepartmentId;
	}
	public String getCityDepartmentName() {
		return cityDepartmentName;
	}
	public String getCode()
	{
		return code;
	}
	public Integer getComEmpId() {
		return comEmpId;
	}
	public Integer getComEmpNo() {
		return comEmpNo;
	}
	public String getCreateTime() {
		return createTime;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public String getEmailAddr() {
		return emailAddr;
	}
	public Integer getEmpLevel() {
		return empLevel;
	}
	public Integer getEmpParentId() {
		return empParentId;
	}
	public Integer getEmpType() {
		return empType;
	}
	public String getIdentityNo() {
		return identityNo;
	}
	public String getName() {
		return name;
	}
	public String getPosition() {
		return position;
	}
	public String getPositionId() {
		return positionId;
	}
	public String getSex() {
		return sex;
	}
	public String getSystemSign() {
		return systemSign;
	}
	public Integer getTeamDepartmentId() {
		return teamDepartmentId;
	}
	public String getTeamDepartmentName() {
		return teamDepartmentName;
	}
	public String getTel() {
		return tel;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	public void setAreaDepartmentId(Integer areaDepartmentId) {
		this.areaDepartmentId = areaDepartmentId;
	}
	public void setAreaDepartmentName(String areaDepartmentName) {
		this.areaDepartmentName = areaDepartmentName;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public void setCityDepartmentId(Integer cityDepartmentId) {
		this.cityDepartmentId = cityDepartmentId;
	}
	public void setCityDepartmentName(String cityDepartmentName) {
		this.cityDepartmentName = cityDepartmentName;
	}
	public void setCode(String code)
	{
		this.code = code;
	}
	public void setComEmpId(Integer comEmpId) {
		this.comEmpId = comEmpId;
	}
	public void setComEmpNo(Integer comEmpNo) {
		this.comEmpNo = comEmpNo;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}
	public void setEmpLevel(Integer empLevel) {
		this.empLevel = empLevel;
	}
	public void setEmpParentId(Integer empParentId) {
		this.empParentId = empParentId;
	}
	public void setEmpType(Integer empType) {
		this.empType = empType;
	}
	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setSystemSign(String systemSign) {
		this.systemSign = systemSign;
	}
	public void setTeamDepartmentId(Integer teamDepartmentId) {
		this.teamDepartmentId = teamDepartmentId;
	}
	public void setTeamDepartmentName(String teamDepartmentName) {
		this.teamDepartmentName = teamDepartmentName;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public HashMap<String, String> getRoleMap() {
		return roleMap;
	}
	public void setRoleMap(HashMap<String, String> roleMap) {
		this.roleMap = roleMap;
	}
	public HashMap<String, String> getDeptMap() {
		return deptMap;
	}
	public void setDeptMap(HashMap<String, String> deptMap) {
		this.deptMap = deptMap;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
