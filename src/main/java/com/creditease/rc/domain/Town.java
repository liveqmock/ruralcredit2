package com.creditease.rc.domain;

import java.io.Serializable;
/**
 * 
 * @author zhangman
 *
 */
public class Town implements Serializable{
	private Long townshipinfoid; //乡镇信息id
	private Long parentId;		//父id
	private String townshipinfName;	//乡镇信息名称
	private String key;				//乡镇信息编号
	private String onLine;			//是否上线
	
	/**
	 * 
	 */
	public Town(){
		super();
	}
	
	/**
	 * 
	 * @param townshipinfoid 乡镇信息id
	 * @param parentId 父id
	 * @param townshipinfName 乡镇信息名称
	 * @param key 乡镇信息编号
	 * @param onLine 是否上线
	 */

	public Town(Long townshipinfoid, Long parentId, String townshipinfName,
			String key, String onLine) {
		super();
		this.townshipinfoid = townshipinfoid;
		this.parentId = parentId;
		this.townshipinfName = townshipinfName;
		this.key = key;
		this.onLine = onLine;
	}

	public String getTownshipinfName() {
		return townshipinfName;
	}
	public void setTownshipinfName(String townshipinfName) {
		this.townshipinfName = townshipinfName;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getOnLine() {
		return onLine;
	}
	public void setOnLine(String onLine) {
		this.onLine = onLine;
	}

	public Long getTownshipinfoid() {
		return townshipinfoid;
	}

	public void setTownshipinfoid(Long townshipinfoid) {
		this.townshipinfoid = townshipinfoid;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
