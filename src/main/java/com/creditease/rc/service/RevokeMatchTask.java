package com.creditease.rc.service;
import com.creditease.rc.domain.Message;

public interface RevokeMatchTask {

	/**
	 * 定时撤销撮合请求
	 * @return
	 */
	public Message revokeMatch();
}
