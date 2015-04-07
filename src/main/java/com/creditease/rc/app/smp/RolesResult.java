package com.creditease.rc.app.smp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import com.creditease.core.ws.WSResult;
import com.creditease.core.ws.WsConstants;

@XmlType(name = "rolesResult", namespace = WsConstants.NS)
public class RolesResult extends WSResult{
	private static final long serialVersionUID = 1L;

	private int roleId;
	private RolesDTO roles;
	
	private List<RolesDTO> rolesList = new ArrayList<RolesDTO>();

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@XmlElement(name = "roles")
	public RolesDTO getRolesDTO() {
		return roles;
	}

	public void setRolesDTO(RolesDTO roles) {
		this.roles = roles;
	}

	@XmlElementWrapper(name = "rolesList")
	@XmlElement(name = "roles")
	public List<RolesDTO> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<RolesDTO> rolesList) {
		this.rolesList = rolesList;
	}
	
}
