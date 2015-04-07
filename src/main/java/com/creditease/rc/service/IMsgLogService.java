package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.domain.MsgLog;

public interface IMsgLogService {
	/**
	 * 保存现金流入流出错误的最大借款额度的相关信息
	 * CreditApplicationDAO 实现方法：
	 * @param MsgLog
	 */
    void insert(MsgLog msgLog);

    /**
     * 根据creditapplicationId查询数据
     */
    public List<MsgLog> selectMsgLogList(int creditapplicationId);
    
    /**
     * 根据creditapplicationId更新数据
     */
    int updateMsgLogDataByCreditapplicationId(MsgLog msgLog);
    
    int updateByPrimaryKeySelective(MsgLog record);

    MsgLog selectByPrimaryKey(Short msgLogId);
    
    /**
     * 根据creditapplicationId删除数据
     */
	int deleteBycreditapllicationId(Long creditapplicationId);

	void insertOldData(MsgLog msgLog);
}