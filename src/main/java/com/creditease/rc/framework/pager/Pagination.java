package com.creditease.rc.framework.pager;
/**
 * <p>Title:分页类 </p>
 * <p>Description:</p>
 * <p>Copyright:Copyright (c) 2011</p>
 * <p>Company:普信恒业科技发展(北京)有限公司</p>
 * <p>Date:2011-10-12</p>
 * <p>Modify:</p>
 * <p>Bug:</p>
 * 
 * @author wangxinqiang
 * @version 1.0
 */
import java.io.Serializable;
import java.util.List;

import com.creditease.rc.framework.common.Contants;

/**
 * 分页对象，与分页功能配套使用
 * Title: 分页对象
 * Description: 农村商贷系统研发
 * Copyright: Copyright (c) 2012
 * Company: 普信恒业科技发展（北京）有限公司
 * Date: 2012-12-17
 * @author: 解兵丰
 * @version: v1.0
 */
public class Pagination implements Serializable {
	
	private static final long serialVersionUID = 30161819074846596L;

	/**
	 * 默认每页显示数
	 */
	public final static int DEFAULT_PAGE_SIZE = Contants.DEFAULT_PAGE_SIZE;

	/**
	 * 每页显示数
	 */
	private int pageSize = DEFAULT_PAGE_SIZE;

	/**
	 * 总记录数
	 */
	private int total;

	/**
	 * 当前第几页，从0开始编码
	 */
	private int currentPage;
	
	/**
	 * 显示项集合
	 */
	@SuppressWarnings("unchecked")
	private List items;	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static int getDefaultPageSize() {
		return DEFAULT_PAGE_SIZE;
	}

	/**
	 * 构造函数
	 */
	public Pagination() {
		super();
	}
	
	/**
	 * 
	 * @param items
	 *            显示项集合
	 * @param total
	 *            总记录数
	 */
	@SuppressWarnings("unchecked")
	public Pagination(List items, int total) {
		setPageSize(DEFAULT_PAGE_SIZE);
		setTotal(total);
		setItems(items);
	}

	/**
	 * 
	 * @param items
	 *            显示项集合
	 * @param total
	 *            总记录数
	 * @param pageSize
	 *            每页显示数
	 */
	@SuppressWarnings("unchecked")
	public Pagination(List items, int total, int pageSize) {
		setPageSize(pageSize);
		setTotal(total);
		setItems(items);
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}
	
	/**
	 * 设置总页数indexes.length，总记录数totalResult，每页开始索引数(保存在数组每一维中)
	 * 
	 * @param total 总记录数
	 */
	public void setTotal(int total) {
		if(total < 0) {
			total = 0;
		}
		else {
			this.total = total;
		}
	}

	/**
	 * 开始索引数
	 * @return int
	 */
	public int getStartResult() {
		return this.pageSize * currentPage;
	}

	/**
	 * 获得分页数据
	 * 
	 * @return 分页集合
	 */
//	@SuppressWarnings("unchecked")
//	public List getItems() {
//		return items;
//	}

	// 得到所有记录的别名（为与easyUI 默认分页参数一致）
	public List getRows() {
		return items;
	}
	
	@SuppressWarnings("unchecked")
	public void setItems(List items) {
		this.items = items;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	/**
	 * 将easyUI 默认的当前页page装换为当前对象的currentPage
	 * page 从1开始编码
	 * currentPage 从0开始编码
	 * @param page 页数
	 */
	public void setPage(int page) {
		if(page > 0){
			this.currentPage = page - 1;
		} else {
			this.currentPage = 0;
		}
	}
}
