package com.creditease.rc.app.sia.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mw
 *
 */
public class Entity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String orderBy;
	private String order;
	private List<String> autList;

	public Entity() {
		this.orderBy = null;

		this.order = null;

		this.autList = new ArrayList<String>();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<String> getAutList() {
		return this.autList;
	}

	public void setAutList(List<String> autList) {
		this.autList = autList;
	}

	public String getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrder() {
		return this.order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}