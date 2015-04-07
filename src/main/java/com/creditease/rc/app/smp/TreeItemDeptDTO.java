package com.creditease.rc.app.smp;

import javax.xml.bind.annotation.XmlType;

import com.creditease.core.ws.WsConstants;

@XmlType(name = "orgEmpTreeDeptDTO", namespace = WsConstants.NS)
public class TreeItemDeptDTO extends TreeItemDTO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8859219523028825704L;

	private Integer parentDepartmentId;
	
	private String isChildDepart;

	private String tel;

	private String fax;

	private String adress;

	private String emailAddr;

	private String departmentDesc;

	private String remark;

	private String systemSign;
	
	private String departType;

	public Integer getParentDepartmentId()
	{
		return parentDepartmentId;
	}

	public void setParentDepartmentId(Integer parentDepartmentId)
	{
		this.parentDepartmentId = parentDepartmentId;
	}

	public String getIsChildDepart()
	{
		return isChildDepart;
	}

	public void setIsChildDepart(String isChildDepart)
	{
		this.isChildDepart = isChildDepart;
	}

	public String getTel()
	{
		return tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}

	public String getFax()
	{
		return fax;
	}

	public void setFax(String fax)
	{
		this.fax = fax;
	}

	public String getAdress()
	{
		return adress;
	}

	public void setAdress(String adress)
	{
		this.adress = adress;
	}

	public String getEmailAddr()
	{
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr)
	{
		this.emailAddr = emailAddr;
	}

	public String getDepartmentDesc()
	{
		return departmentDesc;
	}

	public void setDepartmentDesc(String departmentDesc)
	{
		this.departmentDesc = departmentDesc;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getSystemSign()
	{
		return systemSign;
	}

	public void setSystemSign(String systemSign)
	{
		this.systemSign = systemSign;
	}

	public String getDepartType()
	{
		return departType;
	}

	public void setDepartType(String departType)
	{
		this.departType = departType;
	}
}
