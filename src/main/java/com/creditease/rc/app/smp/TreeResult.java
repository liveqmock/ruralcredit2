package com.creditease.rc.app.smp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.creditease.core.ws.WSResult;
import com.creditease.core.ws.WsConstants;

@XmlType(name = "orgEmpTreeResult", namespace = WsConstants.NS)
public class TreeResult extends WSResult
{
	private static final long serialVersionUID = 1L;

	private TreeItemDTO treeItemDTO;

	@XmlElement(name = "treeItemDTO")
	public TreeItemDTO getTreeItemDTO()
	{
		return treeItemDTO;
	}

	public void setTreeItemDTO(TreeItemDTO treeItemDTO)
	{
		this.treeItemDTO = treeItemDTO;
	}
}
