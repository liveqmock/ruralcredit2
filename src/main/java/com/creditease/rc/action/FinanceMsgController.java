package com.creditease.rc.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.domain.FinanceMoney;
import com.creditease.rc.domain.PayplatformNotice;
import com.creditease.rc.domain.ProcessMessage;
import com.creditease.rc.service.IFinanceMoneyService;
import com.creditease.rc.service.IPayplatformNoticeService;
import com.creditease.rc.service.impl.ProtocolManagementService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.JsonUtil;


/**
 * 财务消息处理
 * @author xubingzhao
 *
 */
@Controller
@RequestMapping("financeMsgController")
public class FinanceMsgController {
	@Resource
	private IFinanceMoneyService financeMoneyService; 
	@Resource
	private IPayplatformNoticeService payplatformNoticeService; 
	private Logger log = Logger.getLogger(ProtocolManagementService.class);
	/**
	 * 
	 * @param msg 
	 * @return  String
	 */
	@RequestMapping(value="processMsg")
	public @ResponseBody
	String processMsg(String msg){
		try {
			msg = new String(msg.getBytes("ISO-8859-1"), "UTF-8");
			log.info(msg);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		List<ProcessMessage>list= string2ProcessMsgList(msg);
		String result=financeMoneyService.updateFinanceMoney(list);
		return result;
	} 
	/**
	 * 
	 * @param msg  
	 * @return  list
	 */
	private List<ProcessMessage> string2ProcessMsgList(String msg){
		if(StringUtils.isBlank(msg)){
			return null;
		}
		JSONArray jsonArray = JSONArray.fromObject(msg);
		List<ProcessMessage> list=new ArrayList<ProcessMessage>();
		for(int i=0;i<jsonArray.size();i++){
			JSONObject j=(JSONObject) jsonArray.get(i);
			PayplatformNotice payplatformNotice=new PayplatformNotice();
			ProcessMessage p=(ProcessMessage) JSONObject.toBean(j, ProcessMessage.class);
			payplatformNotice.setBizid(p.getBizId());
			payplatformNotice.setNoticeType(p.getNoticeType());
			payplatformNotice.setOperateDate(new Date());
			payplatformNotice.setResponseMsg(j.toString());
			payplatformNotice.setTradeTime(DateUtil.stringConvertDate(p.getTradeTime()));
			payplatformNoticeService.insertPayplatformNotice(payplatformNotice);
			list.add(p);
		}
		return list;
	}
	
	/**
	 * 手动收付款 
	 * @param orderNum  
	 * @param financeType  
	 * @param financeResult  
	 * @return  String
	 */
	@RequestMapping(value="manualFinance")
	@ResponseBody
	public String manualFinance(String orderNum,String financeType,String financeResult){
		Map[] maps = new HashMap[1];
		Map<String,String> map = new HashMap<String, String>();
		FinanceMoney financeMoney = new FinanceMoney();
		financeMoney.setBizId(orderNum);
		financeMoney.setType(financeType);
		FinanceMoney fm = financeMoneyService.selectFinanceMoneyByBizId(financeMoney);
		if(fm!=null){//S收款/F付款
			map.put("noticeType", fm.getType());
			if("F".equals(fm.getType())){
				map.put("retInfo", "结算平台消息手动付款");
				map.put("tradeTime", DateUtil.dateConvertString(new Date()));
				if(CommonUtil.isNotEmpty(financeResult)){//0成功/-1失败
					map.put("state", financeResult);
				}
				if(CommonUtil.isNotEmpty(orderNum)){
					map.put("bizId", orderNum);
				}
			}else if("S".equals(fm.getType())||"U".equals(fm.getType())){
				map.put("retInfo", "结算平台消息手动收款");
				map.put("tradeTime", DateUtil.dateConvertString(new Date()));
				if(CommonUtil.isNotEmpty(financeResult)){//0成功/-1失败
					map.put("state", financeResult);
				}
				if(CommonUtil.isNotEmpty(orderNum)){
					map.put("bizId", orderNum);
				}
				map.put("successAmount", fm.getAmount());
			}
			
		}else{
			return "ERROR";
		}
		maps[0] = map;
		String msg = JsonUtil.getJson(maps);
		List<ProcessMessage> list = string2ProcessMsgList(msg);
		String result=financeMoneyService.updateFinanceMoney(list);
		return result;
	}
}
