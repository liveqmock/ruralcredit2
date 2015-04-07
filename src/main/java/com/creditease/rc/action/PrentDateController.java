package com.creditease.rc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.domain.CustomerReturnVisit;
import com.creditease.rc.service.ICustomerReturnVisitService;
import com.creditease.rc.vo.CustomerReturnVisitVo;


@Controller
@RequestMapping(value = "PresentDateController")
public class PrentDateController {

	@Resource
	private ICustomerReturnVisitService customerReturnVisitService;
	/**
	 * 返回当前时间
	 * @return
	 */
	@RequestMapping(value = "returnNowDate" ,produces = "application/text ; charset=utf-8")
	public @ResponseBody String returnNowDate(){
		String dateString =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		return dateString;
	}
	
	/**
	 * 返回当前时间
	 * @return
	 */
	@RequestMapping(value = "returnDateList" )
	public @ResponseBody List<Map<String, String>> returnDateList(){
		List<Map<String, String>> maps = new ArrayList<Map<String,String>>();
		String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String lastday =new SimpleDateFormat("yyyy-MM-dd").format(new Date(new Date().getTime()-24*60*60*1000));
		Map<String, String> map = new HashMap<String, String>();
		map.put("dateString", today);
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("dateString", lastday);
		maps.add(map);
		maps.add(map2);
		return maps;
	}
	
	/**
	 * 返回当前时间
	 * 并且判断当前日期是不是还款日(回访计划里面的)
	 * manzhang
	 * @return
	 */
	@RequestMapping(value = "returnDateListSelectChange" )
	public @ResponseBody Map<String, Object> returnDateList(Long creditapplicationId){
		 Map<String, Object>  mapRe=new HashMap<String, Object>();
		List<Map<String, String>> lists = new ArrayList<Map<String,String>>();
		String today=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String lastday =new SimpleDateFormat("yyyy-MM-dd").format(new Date(new Date().getTime()-24*60*60*1000));
		String beforeLastday =new SimpleDateFormat("yyyy-MM-dd").format(new Date(new Date().getTime()-48*60*60*1000));
		Map<String, String> map = new HashMap<String, String>();
		map.put("dateString", today);
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("dateString", lastday);
		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("dateString", beforeLastday);
		lists.add(map);
		lists.add(map2);
		lists.add(map3);
		//查询当前时间是否是还款日
		CustomerReturnVisitVo customerReturnVisitVo = new CustomerReturnVisitVo();
		customerReturnVisitVo.setCreditapplicationId(creditapplicationId);
		customerReturnVisitVo.setRepayMentDateString(today);
		List<CustomerReturnVisit> customerReturnVisits = customerReturnVisitService.selectToday(customerReturnVisitVo);
		if(customerReturnVisits.size() > 0){
			mapRe.put("flag", true); //true就执行前台select 选择前一天的时候
			mapRe.put("lastRepayDate", today);  //返回当前日期（即上一个还款日）
			mapRe.put("lastcustomerReturnVisitId", customerReturnVisits.get(0).getCustomerReturnVisitId());  //返回当前日期（即上一个还款日）
		}else{
			mapRe.put("flag", false);
		}
		mapRe.put("list", lists);
		mapRe.put("todayNow", today);
		return mapRe;
	}
}
