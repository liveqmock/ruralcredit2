package com.creditease.rc.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.creditease.rc.domain.CustomerConsult;
import com.creditease.rc.domain.CustomerConsultPool;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.CustomerConsultPoolVo;

public interface  IInquirePoolOfficeService {

		/**
		 * 查询分页列表
		 * @param pagination
		 * @param sort
		 * @param order
		 * @param request
		 * @return
		 */
		Pagination selectInquirePoolOfficeList(Pagination pagination, Map<String, String> pramMap);

		void updateBusinessStatus();
		
		/**
		 * 导出数据
		 * @param pagination
		 * @param customerConsultPoolVo
		 * @param sort
		 * @param order
		 * @param request
		 * @return
		 */
		public Pagination downloadExcel(Pagination pagination, CustomerConsultPoolVo customerConsultPoolVo,String sort, String order, HttpServletRequest request);

		/**
		 * 查看详情
		 * @param telphone
		 * @return
		 */
		List<CustomerConsultPoolVo> selectHistory(String telphone);

		//查看咨询池
		CustomerConsultPool selectCustomerConsultPool(CustomerConsultPool customerConsultPool); 
	}


