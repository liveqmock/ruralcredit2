package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.Space;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.report.excel.ExcelUtils;
import com.creditease.rc.report.excel.TableData;
import com.creditease.rc.service.IJsGridReportBaseXLSXService;
import com.creditease.rc.service.SpaceService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DESPlus;

@Controller
@RequestMapping("spaceController")
public class SpaceController {

	@Resource
	private SpaceService spaceService;
	@Resource
	private IJsGridReportBaseXLSXService jsGridReportBaseXLSXService;
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		dateFormat2.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
		binder.registerCustomEditor(Date.class,"spacedate",new CustomDateEditor(
				dateFormat2, true));
		binder.registerCustomEditor(Date.class,"spacedateReality",new CustomDateEditor(
				dateFormat2, true));
		binder.registerCustomEditor(Date.class,"spacedate_begin",new CustomDateEditor(
				dateFormat2, true));
		binder.registerCustomEditor(Date.class,"spacedate_end",new CustomDateEditor(
				dateFormat2, true));
	}
	/**
	 * 展业计划列表
	 * @param space
	 * @param page
	 * @param rows
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("spaceList")
	public @ResponseBody Pagination spaceList(Space space,String page,String rows,HttpServletRequest request,
			HttpSession session){
		Pagination pagination = new Pagination();
		if(!StringUtils.isBlank(page)){
			pagination.setPage(Integer.valueOf(page));
		}
		if(!StringUtils.isBlank(rows)){
			pagination.setPageSize(Integer.valueOf(rows));
		}
		return spaceService.spaceList(pagination, space, page, rows, request, session);
	}
	/**
	 * 弹出登记展业计划
	 * @return
	 */
	@RequestMapping(value = "/addFrame")
	public ModelAndView addFrame() {
	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/sales/register_space.jsp");
		return modelAndView;
	}
	/**
	 * 保存登记展业计划
	 * @param space
	 * @return
	 */
	@RequestMapping("saveSpace")
	public @ResponseBody Message saveSpace(Space space){
		Message message = new Message();
		Long saveSpace = spaceService.saveSpace(space);
		if(saveSpace!=null && saveSpace>0){
			message.setSuccess(true);
			message.setMsg("保存成功");
		}else{
			message.setSuccess(false);
			message.setMsg("保存失败");
		}
		return message;
	}
	/**
	 * 弹出执行展业计划窗口
	 * @param value
	 * @return
	 */
	@RequestMapping("registerSpaceExecuteFrame")
	public ModelAndView registerSpaceExecuteFrame(String value){
		ModelAndView modelAndView = new ModelAndView();
		String value2="";
		try {
			DESPlus desPlus = new DESPlus();
			value2=desPlus.encrypt(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		modelAndView.addObject("value", value);
		modelAndView.addObject("value2", value2);
		modelAndView.setViewName("/jsp/rc/sales/execute_space.jsp");
		return modelAndView;
	}
	/**
	 * 保存展业计划
	 * @param space
	 * @return
	 */
	@RequestMapping("saveExecuteSpace")
	public @ResponseBody Message saveExecuteSpace(Space space){
		return spaceService.saveExecuteSpace(space);
	}
	/**
	 * 延期
	 * @param value
	 * @return
	 */
	@RequestMapping(value="updateSpaceStud")
	public @ResponseBody Message updateSpaceStud(String value){
		return spaceService.updateSpaceStud(value);
	}
	/**
	 * 为下载照片提供主键加密
	 * @param value
	 * @return
	 */
	@RequestMapping("encodingSpaceId")
	public @ResponseBody Message encodingSpaceId(String value){
		Message message = new Message();
		try {
			DESPlus desPlus = new DESPlus();
			value=desPlus.encrypt(value);
			message.setSuccess(true);
			message.setMsg(value);
		} catch (Exception e) {
			message.setSuccess(false);
			message.setMsg("主键id加密失败");
			e.printStackTrace();
		}
		return message;
	}
	
	@RequestMapping("downloadExcel")
	public void downloadExcel (HttpServletResponse response,Pagination pagination,Space space, String page, String rows,
			HttpServletRequest request, HttpSession session) throws Exception{
		 //response.setContentType("application/msexcel;charset=UTF-8");
	        pagination.setPageSize(0);
	        pagination = spaceService.downloadExcel(pagination, space, page, rows, request, session);
	        List list = pagination.getRows();//获取数据
	        if(CommonUtil.isNotEmpty(list)){
	        	String title = "执行计划列表";
	            String[] hearders = new String[]{"大区名字","分中心","所属营业部","计划展业日期",
	            								 "计划展业时间","计划展业地点","人员规划","预计客户咨询量","展业状态",
	            								 "实际展业日期","实际展业时间","实际展业地点","实际参与人员",
	            								 "实际客户量","提供照片数量"};//表头数组
	            String[] fields = new String[]{"REGIONNAME","CITYDEPARTMENTNAME","AREADEPARTMENTNAME","SPACEDATE",
	            							   "SPACETIME","SPACEPLACE","PERSONPLANNING","CUSTOMERCONSULTCOUNT","SAPCETEXT",
	            							   "SPACEDATEREALITY","SPACETIMEREALITY","SPACEPLACEREALITY","PARTICIPANTSREALITY",
	            							   "CUSTOMERCONSULTCOUNTREALITY","PHOTOCOUNT"};//对象属性数组
	            TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders),fields);
	            //JsGridReportBase report = new JsGridReportBase(request, response);
	            jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
	            jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);
	        }else{
	        	String title = "执行计划列表";
	            String[] hearders = new String[]{"大区名字","分中心","所属营业部","计划展业日期","计划展业日期",
												 "计划展业时间","计划展业地点","人员规划","预计客户咨询量","展业状态",
												 "实际展业日期","实际展业时间","实际展业地点","实际参与人员",
												 "实际客户量","提供照片数量"};//表头数组
	            Space cu=new Space();
	            list.add(cu);
	            String[] fields = new String[] {};//对象属性数组
	            TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders),fields);
	           /* JsGridReportBase report = new JsGridReportBase(request, response);
	            report.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);*/
	            jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
	            jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);
	        }
		
	}
}
