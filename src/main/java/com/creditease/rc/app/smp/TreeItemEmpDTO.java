package com.creditease.rc.app.smp;

import java.util.Date;

import javax.xml.bind.annotation.XmlType;

import com.creditease.core.ws.WsConstants;

@XmlType(name = "orgEmpTreeEmpDTO", namespace = WsConstants.NS)
public class TreeItemEmpDTO extends TreeItemDTO
{
	private static final long serialVersionUID = 8327849992140064761L;

	private String account;// 账号

	private String activeStatus;// 员工在职状态

	private Integer areaDepartmentId;// 总部大部门ID

	private String areaDepartmentName;// 总部大部门名称

	private Integer areaId;// 城市ID
	
	private String areaName;// 城市名字

	private String cityCode;//城市code

	private Integer cityDepartmentId;// 城市一级部门ID

	private String cityDepartmentName;// 城市一级部门名字

	private Date createDate;// 创建时间

	private Integer departmentId;// 员工所属直接部门ID

	private String departmentName;// 员工所属直接部门名

	private String districtCode;//区code

	private String emailAddr;
	
	private Integer empLevel;// 员工级别

	private Integer empParentId;// 上级领导

	private Integer empType;// 员工类型

	private String identityNo;// 身份证

	private String position;// 职位

	private String positionId;// 职位id

	private String provinceCode;//省code

	private String sex;// 性别

	private Integer teamDepartmentId;// 员工所属团队部门ID

	private String teamDepartmentName;// 员工所属团队部门名称

	private String tel;

	public String getAccount()
	{
		return account;
	}

	public String getActiveStatus()
	{
		return activeStatus;
	}
	
	public Integer getAreaDepartmentId()
	{
		return areaDepartmentId;
	}
	
	public String getAreaDepartmentName()
	{
		return areaDepartmentName;
	}

	public Integer getAreaId()
	{
		return areaId;
	}

	public String getAreaName()
	{
		return areaName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public Integer getCityDepartmentId()
	{
		return cityDepartmentId;
	}

	public String getCityDepartmentName()
	{
		return cityDepartmentName;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public String getEmailAddr()
	{
		return emailAddr;
	}

	public Integer getEmpLevel()
	{
		return empLevel;
	}

	public Integer getEmpParentId()
	{
		return empParentId;
	}

	public Integer getEmpType()
	{
		return empType;
	}

	public String getIdentityNo()
	{
		return identityNo;
	}

	public String getPosition()
	{
		return position;
	}

	public String getPositionId()
	{
		return positionId;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public String getSex()
	{
		return sex;
	}

	public Integer getTeamDepartmentId()
	{
		return teamDepartmentId;
	}

	public String getTeamDepartmentName()
	{
		return teamDepartmentName;
	}

	public String getTel()
	{
		return tel;
	}

	public void setAccount(String account)
	{
		this.account = account;
	}

	public void setActiveStatus(String activeStatus)
	{
		this.activeStatus = activeStatus;
	}

	public void setAreaDepartmentId(Integer areaDepartmentId)
	{
		this.areaDepartmentId = areaDepartmentId;
	}

	public void setAreaDepartmentName(String areaDepartmentName)
	{
		this.areaDepartmentName = areaDepartmentName;
	}

	public void setAreaId(Integer areaId)
	{
		this.areaId = areaId;
	}

	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public void setCityDepartmentId(Integer cityDepartmentId)
	{
		this.cityDepartmentId = cityDepartmentId;
	}

	public void setCityDepartmentName(String cityDepartmentName)
	{
		this.cityDepartmentName = cityDepartmentName;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public void setEmailAddr(String emailAddr)
	{
		this.emailAddr = emailAddr;
	}

	public void setEmpLevel(Integer empLevel)
	{
		this.empLevel = empLevel;
	}

	public void setEmpParentId(Integer empParentId)
	{
		this.empParentId = empParentId;
	}

	public void setEmpType(Integer empType)
	{
		this.empType = empType;
	}

	public void setIdentityNo(String identityNo)
	{
		this.identityNo = identityNo;
	}

	public void setPosition(String position)
	{
		this.position = position;
	}

	public void setPositionId(String positionId)
	{
		this.positionId = positionId;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public void setTeamDepartmentId(Integer teamDepartmentId)
	{
		this.teamDepartmentId = teamDepartmentId;
	}

	public void setTeamDepartmentName(String teamDepartmentName)
	{
		this.teamDepartmentName = teamDepartmentName;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}
	
}
