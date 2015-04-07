package com.creditease.rc.app.smp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import com.creditease.core.ws.WSResult;
import com.creditease.core.ws.WsConstants;

@XmlType(name = "departPositionResult", namespace = WsConstants.NS)
public class DepartPositionResult extends WSResult {

	/**
	 * webservice 返回封装
	 */
	private static final long serialVersionUID = 1L;
	private Integer departId;
	private DepartPositionDTO departPosition; 
	private List<DepartPositionDTO> departPositions = new ArrayList<DepartPositionDTO>();
	
	@XmlElementWrapper(name = "departPositions")
	@XmlElement(name = "departPosition")
	public List<DepartPositionDTO> getDepartPositions() {
		return departPositions;
	}
	public void setDepartPositions(List<DepartPositionDTO> departPositions) {
		this.departPositions = departPositions;
	}
	@XmlElement(name = "departPosition")
	public DepartPositionDTO getDepartPosition() {
		return departPosition;
	}
	public void setDepartPosition(DepartPositionDTO departPosition) {
		this.departPosition = departPosition;
	}
	public Integer getDepartId() {
		return departId;
	}
	public void setDepartId(Integer departId){
		this.departId = departId;
	}
}
