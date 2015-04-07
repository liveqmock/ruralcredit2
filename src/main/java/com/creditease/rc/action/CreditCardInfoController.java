package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.service.ICreditCardInfoService;
import com.creditease.rc.vo.CreditCardInfoVo;
/**
 * 
 * @author zhangman
 *
 */
@Controller
@RequestMapping(value="creditCardInfo")
public class CreditCardInfoController {
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
	@Resource
	private ICreditCardInfoService creditCardInfoService;
	/**
	 * 
	 * @param creditCardInfoVo 信用卡vo对象
	 * @return 信用卡vo對象
	 */
	@RequestMapping(value="addOrUpdate")
	public @ResponseBody 
	CreditCardInfoVo addOrUpdateCreditCardInfo(CreditCardInfoVo creditCardInfoVo){
		return creditCardInfoService.addOrUpdateCreditCardInfo(creditCardInfoVo);
	};
	/**
	 * 
	 * @param borrowerServiceAppId 借款服务申请id
	 * @return 信用卡vo类
	 */
	@RequestMapping(value="select")
	public @ResponseBody
	CreditCardInfoVo selectByBorrowerServiceAppId(String borrowerServiceAppId){
		CreditCardInfoVo creditCardInfoVo = new CreditCardInfoVo();
		int borrowerServiceAppIdInt = 0 ;
		if(borrowerServiceAppId != null && !"".equals(borrowerServiceAppId)){
			 borrowerServiceAppIdInt = Integer.valueOf(borrowerServiceAppId);
			 creditCardInfoVo = creditCardInfoService.selectByBorrowerServiceAppId(borrowerServiceAppIdInt);
			 return creditCardInfoVo;
		}else{
			return null;
		}
		
	}
}
