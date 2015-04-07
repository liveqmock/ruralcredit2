package com.creditease.rc.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.Space;
import com.creditease.rc.framework.pager.Pagination;

public interface SpaceService {

	/**
	 * 展业列表
	 * @param pagination
	 * @param space
	 * @param page
	 * @param rows
	 * @param request
	 * @param session
	 * @return
	 */
	Pagination spaceList(Pagination pagination,Space space,String page,String rows,HttpServletRequest request,
			HttpSession session);
	/**
	 * 保存登记展业计划
	 * @param space
	 * @return
	 */
	Long saveSpace(Space space);
	/**
	 * 延期
	 */
	Message updateSpaceStud(String value);
	/**
	 * 保存执行展期后的结果
	 * @param space
	 * @return
	 */
	Message saveExecuteSpace(Space space);
	
	/**
	 * 导出excel表格
	 * @param pagination
	 * @param space
	 * @param page
	 * @param rows
	 * @param request
	 * @param session
	 * @return
	 */
	Pagination downloadExcel(Pagination pagination,Space space,String page,String rows,HttpServletRequest request,
			HttpSession session);
	
}
