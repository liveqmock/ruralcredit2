/**
 * Title:OperateLogController.java  
 * Description:  
 */
package com.creditease.rc.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.common.DepartmentEntity;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IOperateLogService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.SmpWSUtil;
import com.creditease.rc.vo.OperateLogVO;

/**
 * Title:OperateLogController.java Description: Copyright:Copyright (c) 2011
 * Company:普信恒业科技发展（北京）有限公司
 * 
 * @author 韩大年
 * @version v1.0 2013-3-12
 */
@Controller
@RequestMapping("operateLogController")
public class OperateLogController {

	/**
	 * @Description 默认构造器
	 */
	public OperateLogController() {
		// TODO Auto-generated constructor stub
	}

	@Resource
	private IOperateLogService operateLogService;
	@Resource
	private SmpWSUtil smpWSUtil;
	
	/**
	 * 
	 * @author 韩大年
	 * @Description: 日志分页查询
	 * @param operateLogVO
	 * @param page
	 * @param rows
	 * @param request
	 * @return
	 * @version v1.0 2013-3-12
	 */
	@RequestMapping("queryOperateLogList")
	@ResponseBody
	public Pagination queryOperateLogList(OperateLogVO operateLogVO,
			@RequestParam(required = false) String page,
			@RequestParam(required = false) String rows,
			HttpServletRequest request) {
		Pagination pagination = new Pagination();
		if (CommonUtil.isNotEmpty(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (CommonUtil.isNotEmpty(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		pagination = operateLogService.selectOperateLogByPagination(operateLogVO, pagination);
		return pagination;
	};
	
	/**
	 * 
	 * @author 韩大年  
	 * @Description:  获取分公司名称
	 * @return 
	 * @version v1.0 
	 * 2013-3-18
	 */
	@RequestMapping("getDepartmentList")
	@ResponseBody
	public List<DepartmentEntity> getDepartmentList(){
		
		return this.smpWSUtil.getDepartmentList();
	}
	/**
	 * 某申请单日志查询
	* @author wyf  
	* @Description: 
	* @param: page
	* @param: rows
	* @param: creditAppId
	* @return: Pagination 
	 */
	@RequestMapping("queryOperateLogByCreditId")
	@ResponseBody
	public Pagination queryOperateLogByCreditId(String page,String rows,String creditAppId){
		Pagination pagination = new Pagination();
		if (CommonUtil.isNotEmpty(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (CommonUtil.isNotEmpty(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		pagination = operateLogService.queryOperateLogByCreditId(creditAppId, pagination);
		return pagination;
	}
}
