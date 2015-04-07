package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.WxQuestion;

public interface IWxQuestionDao {

	boolean batchInsertQuestionList(List<WxQuestion> insertQList);

	List<WxQuestion> queryAll();
}