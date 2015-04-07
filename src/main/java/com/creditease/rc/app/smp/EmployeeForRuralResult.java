package com.creditease.rc.app.smp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import com.creditease.core.ws.WSResult;
import com.creditease.core.ws.WsConstants;

@XmlType(name = "employeeForRuralResult", namespace = WsConstants.NS)
public class EmployeeForRuralResult extends WSResult {

	/**
	 * webservice 返回封装
	 */
	private static final long serialVersionUID = 1L;

	private Integer comEmpId;
	private EmployeeForRuralDTO employeeForRural;
	
	private List<EmployeeForRuralDTO> employeeRuralList = new ArrayList<EmployeeForRuralDTO>();
	
	public Integer getComEmpId() {
		return comEmpId;
	}
	
	public void setComEmpId(Integer comEmpId){
		this.comEmpId = comEmpId;
	}
	
	@XmlElement(name = "employeeForRural")
	public EmployeeForRuralDTO getEmployeeForRural() {
		return employeeForRural;
	}
	
	public void setEmployeeForRural(EmployeeForRuralDTO employeeForRural) {
		this.employeeForRural = employeeForRural;
	}
	
	@XmlElementWrapper(name = "employeeRuralList")
	@XmlElement(name = "employeeForRural")
	public List<EmployeeForRuralDTO> getEmployeeRuralList() {
		return employeeRuralList;
	}
	
	public void setEmployeeRuralList(List<EmployeeForRuralDTO> employeeRuralList) {
		this.employeeRuralList = employeeRuralList;
	}
	
}
