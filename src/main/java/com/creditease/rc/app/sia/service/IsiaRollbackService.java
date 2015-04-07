package com.creditease.rc.app.sia.service;

public interface IsiaRollbackService {

	/**
	 * 接收综合信贷同步状态数据
	 * @param message
	 */
	 void sendMessageBySia(String message);
}
