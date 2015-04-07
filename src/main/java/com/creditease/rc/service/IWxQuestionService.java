package com.creditease.rc.service;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.WxQuestion;

public interface IWxQuestionService {

	Message inserQuestionMap(Map<String, WxQuestion> questionMap);

	Message batchInsertQuestionList(List<WxQuestion> insertQList);

	Map<Long, WxQuestion> queryAllToMap();

}
