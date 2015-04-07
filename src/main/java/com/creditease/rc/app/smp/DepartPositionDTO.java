package com.creditease.rc.app.smp;

import java.io.Serializable;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.creditease.core.ws.MapAdapter;
import com.creditease.core.ws.WsConstants;

@XmlType(name = "departPosition", namespace = WsConstants.NS)
public class DepartPositionDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer departId;
	private String departName;
	private HashMap<String, String> positions = new HashMap<String, String>();
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public Integer getDepartId() {
		return departId;
	}
	public void setDepartId(Integer departId) {
		this.departId = departId;
	}
	@XmlJavaTypeAdapter(MapAdapter.class)
	public HashMap<String, String> getPositions() {
		return positions;
	}
	public void setPositions(HashMap<String, String> positions) {
		this.positions = positions;
	}


}
