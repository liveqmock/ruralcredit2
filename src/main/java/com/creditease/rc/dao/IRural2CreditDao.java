package com.creditease.rc.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.Message;
import com.creditease.rc.vo.ReceiveCreditObj;

/**
 * 
 * @author haoqiang
 * 
 */
public interface IRural2CreditDao {

	public List<Map<String, String>> queryBizIdAndSysguId(List<String> bizIdList);

	public List<Map<String, String>> queryBizIdAndSysguIdByBusinessNumber(String businessNumber);

	public String querySysGuidByBusinessNumber(String businessNumber);

	public List<String> querySysGuidListByAccountInfoId(Integer accountInfoId);

	/**
	 * 更改结清状态
	 * 
	 * @param receiveCreditObjs 贷后发送的消息List
	 * @return message
	 */
	public Message updateStatusByReceiveCreditMsg(List<ReceiveCreditObj> receiveCreditObjs) throws ParseException ;
}
