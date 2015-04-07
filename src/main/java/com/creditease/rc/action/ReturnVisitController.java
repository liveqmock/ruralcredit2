package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.ReturnVisit;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IReturnVisitService;
import com.creditease.rc.vo.ReturnVisitVo;

/**
 * 
 * @author zhangman
 * Copyright: Copyright (c) 2012
 * Company: 普信恒业科技发展（北京）有限公司
 */
@Controller
@RequestMapping(value ="ReturnVisit")
public class ReturnVisitController {
	/**
	 * 
	 * @param binder
	 *            WebDataBinder对象
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	@Resource
	private IReturnVisitService returnVisitService;

	/**
	 * 
	 * @param returnVisit 客户回访对象
	 * @return Message 消息
	 */
	@RequestMapping(value = "addReturnVisit")
	public @ResponseBody
	Message addReturnVisit(ReturnVisit returnVisit){
		Message message = new Message();
		try {
			returnVisitService.addReturnVisit(returnVisit);
			message.setSuccess(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	} 
	
	/**
	 * 
	 * @param returnVisit 客户回访对象
	 * @return Message 消息
	 */
	@RequestMapping(value = "updateReturnVisit")
	public Message updateReturnVisit(ReturnVisit returnVisit){
		return null;
	}
	
	/**
	 * 
	 * @param returnVisit 客户回访对象
	 * @return ReturnVisitVo 回访对象
	 */
	@RequestMapping(value = "selectReturnVisit")
	public ReturnVisitVo  selectReturnVisit(ReturnVisit returnVisit){
		return null;
	}
	
	/**
	 * 
	 * @param page 起始页
	 * @param rows 行
	 * @param fuzzyValue 模糊条件
	 * @param returnVisit 客户回访对象
	 * @return Pagination 分页列表
	 */
	@RequestMapping(value = "selectReturnVisitList")
	public @ResponseBody
	Pagination  selectReturnVisitList(ReturnVisitVo returnVisit,String page, String rows,String fuzzyValue){
		Pagination pagination = new Pagination();
		if(!StringUtils.isBlank(page)){
			pagination.setPage(Integer.valueOf(page));
		}
		if(!StringUtils.isBlank(rows)){
			pagination.setPageSize(Integer.valueOf(rows));
		}
		return returnVisitService.selectReturnVisitList(returnVisit, pagination, fuzzyValue);
	}
}
