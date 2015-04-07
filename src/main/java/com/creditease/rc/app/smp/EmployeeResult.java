package com.creditease.rc.app.smp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import com.creditease.core.ws.WSResult;
import com.creditease.core.ws.WsConstants;

@XmlType(name = "employeeResult", namespace = WsConstants.NS)
public class EmployeeResult extends WSResult {

	/**
	 * webservice 返回封装
	 */
	private static final long serialVersionUID = 1L;

	private Integer comEmpId;
	private EmployeeDTO employee;
	
	private List<EmployeeDTO> employeeList = new ArrayList<EmployeeDTO>();
	
	@XmlElementWrapper(name = "employeeList")
	@XmlElement(name = "employee")
	public List<EmployeeDTO> getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(List<EmployeeDTO> employeeList) {
		this.employeeList = employeeList;
	}
	
	@XmlElement(name = "employee")
	public EmployeeDTO getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}
	public Integer getComEmpId() {
		return comEmpId;
	}
	public void setComEmpId(Integer comEmpId){
		this.comEmpId = comEmpId;
	}
}
