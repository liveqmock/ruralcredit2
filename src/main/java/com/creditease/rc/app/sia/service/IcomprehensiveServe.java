package com.creditease.rc.app.sia.service;

import java.text.ParseException;

import com.creditease.rc.domain.Message;

public interface IcomprehensiveServe {
	
	/**
	 * 将数据推送到综合信贷
	 * @param creditApplicationId
	 * @return message
	 * @throws ParseException
	 */
	public Message send2Comprehensive(Long creditApplicationId) throws ParseException;

}
