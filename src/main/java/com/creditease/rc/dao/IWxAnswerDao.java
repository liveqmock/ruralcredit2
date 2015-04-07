package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.WxAnswer;


public interface IWxAnswerDao {

	boolean batchInsertAnswerList(List<WxAnswer> insertAList);

	List<WxAnswer> queryAll();
}