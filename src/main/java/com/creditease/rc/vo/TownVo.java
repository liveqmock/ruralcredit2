package com.creditease.rc.vo;

import java.io.Serializable;
import java.util.List;

import com.creditease.rc.domain.Town;
/**
 * 
 * @author zhangman
 *
 */
public class TownVo implements Serializable{

	private Town[] listTown;

	public Town[] getListTown() {
		return listTown;
	}

	public void setListTown(Town[] listTown) {
		this.listTown = listTown;
	}

	
}
