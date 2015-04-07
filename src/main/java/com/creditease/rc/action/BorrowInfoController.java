package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.domain.BorrowInfo;
import com.creditease.rc.service.IBorrowInfoService;
import com.creditease.rc.vo.BorrowInfoVo;
	/**
	 * 
	 * @author zhangman
	 *
	 */
@Controller
@RequestMapping(value="borrowInfo")
public class BorrowInfoController {
	/**
	 * 
	 * @param binder WebDataBinder对象
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	/**
	 * 添加或修改借款情况
	 * @param borrowInfos
	 */
	@Resource
	private IBorrowInfoService borrowInfoService;
	
	/**
	 * 
	 * @param borrowInfos 借款情况对象
	 * @return 借款请框vo对象
	 */
	@RequestMapping(value = "addOrUpdate")
	public @ResponseBody
	BorrowInfoVo addOrUpdateBorrowInfo(BorrowInfoVo borrowInfos){
		BorrowInfoVo borrowInfoVoReturn = new BorrowInfoVo();
		List<BorrowInfo> borrowInfoTemp = borrowInfoService.addOrUpdateBorrowInfo(borrowInfos.getBorrowInfos());
		borrowInfoVoReturn.setBorrowInfos(borrowInfoTemp );
		return borrowInfoVoReturn;
	};
	
	/**
	 * @param borrowerServiceAppId 借款服务申请id
	 * @return 借款情况list
	 * @throws ParseException  转换异常
	 */
	public List<BorrowInfo> selectBorrowInfo(int borrowerServiceAppId)  throws ParseException{
		return null;
	};
	/**
	 * 按 id 删除
	 * @param borrowerServiceAppId 小组身亲id
	 * @return 借款服务申请id
	 */
	public int deleteBorrowInfo(int borrowerServiceAppId){
		return borrowerServiceAppId;
		
	};
	/**
	 * 
	 * @param borrowerServiceAppId  借款服务申请id
	 * @return 借款情况列表 list
	 */
	@RequestMapping(value= "select")
	public @ResponseBody
	BorrowInfoVo selectById(String borrowerServiceAppId){
		BorrowInfoVo borrowInfoVo = new BorrowInfoVo();
		int borrowInfoIdInt= 0;
		if(borrowerServiceAppId != null &&!"".equals(borrowerServiceAppId)){
			borrowInfoIdInt = Integer.valueOf(borrowerServiceAppId);
			borrowInfoVo =borrowInfoService.selectById(borrowInfoIdInt);
			return borrowInfoVo;
		}else{
			return null;
		}
	
	}
}
