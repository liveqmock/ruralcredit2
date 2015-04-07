package com.creditease.rc.app.smp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import com.creditease.core.ws.WSResult;
import com.creditease.core.ws.WsConstants;

@XmlType(name = "cityResult", namespace = WsConstants.NS)
public class CityResult extends WSResult
{
	/**
	 * webservice 返回封装
	 */
	private static final long serialVersionUID = 6574577188317871345L;

	private List<CityDTO> cityList = new ArrayList<CityDTO>();

	@XmlElementWrapper(name = "cityList")
	public List<CityDTO> getCityList()
	{
		return cityList;
	}

	public void setCityList(List<CityDTO> cityList)
	{
		this.cityList = cityList;
	}
}
