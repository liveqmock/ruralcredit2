package com.creditease.rc.app.smp;

import com.creditease.core.orm.Entity;

public class RolesDTO extends Entity{
	
	private static final long serialVersionUID = 1L;
	/** 角色ID */
	private Integer roleId;
	/** 角色代码 */
	private String roleName;
	/** 角色描述 */
	private String roleDesc;
	/** 是否启用 0否1是*/
	private String isActive;
	/** 系统标识代码 */
	private String systemSign;
	/** 备注 */
	private String remark;
	
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getSystemSign() {
		return systemSign;
	}
	public void setSystemSign(String systemSign) {
		this.systemSign = systemSign;
	}
}