package com.creditease.rc.service;

import java.util.Date;
import java.util.List;

import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.ReceivedRecord;

/**
 * 调用结算平台的服务
 * 还有调用贷后收款接口服务
 * 
 * @author xubingzhao
 * 
 */
public interface IFinanceReceiveService {
	/**
	 * 财务收款预约服务
	 * 
	 * @param receivedRecordIdList 收款登记表ID
	 * @param parameter 页面传的备注日期等信息
	 * @return Message
	 */
	public Message financialReceive(List<Integer> receivedRecordIdList, ReceivedRecord parameter);

	/**
	 * 贷后收款预约服务
	 * After the credit （贷后系统）缩写为：ATC
	 * 
	 * @author 郝强
	 * @param receivedRecordIdList 收款登记表ID
	 * @param paramDate 预约日期
	 * @param remark 预约备注
	 * @return Message
	 */
	public Message financialReceiveATC(List<Integer> receivedRecordIdList, Date paramDate, String remark);

	/**
	 * 调用贷后系统查询预约结果
	 * 
	 * @author 郝强
	 * @param bizIdList 订单号
	 * @return 是否调用成功的消息
	 */
	public Message updateQueryReserveResult(List<String> bizIdList);

	/**
	 * 通过收款登记IDLIST查询对应的BizIdList
	 * 
	 * @author 郝强
	 * @param receivedRecordIdList
	 * @return
	 */
	public List<String> queryBizIdListByReceivedRecordIdList(List<Integer> receivedRecordIdList);

}
