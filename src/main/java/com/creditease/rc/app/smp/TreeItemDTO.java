package com.creditease.rc.app.smp;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import com.creditease.core.ws.WSResult;
import com.creditease.core.ws.WsConstants;

@XmlType(name = "orgEmpTreeDTO", namespace = WsConstants.NS)
public class TreeItemDTO extends WSResult
{
	private static final long serialVersionUID = -2805903446404854620L;

	private Integer id;

	private String name;
	
	private LinkedList treeItemDTOs = new LinkedList();

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	@XmlElementWrapper(name = "treeItemDTOs")
	@XmlElement(name = "treeItemDTO")
	public List getTreeItemDTO()
	{
		return treeItemDTOs;
	}
	
	public void add(TreeItemDTO treeItemDTO)
	{
		this.treeItemDTOs.add(treeItemDTO);
	}
}
