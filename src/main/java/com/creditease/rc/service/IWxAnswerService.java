package com.creditease.rc.service;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.WxAnswer;
import com.creditease.rc.domain.WxQuestion;

public interface IWxAnswerService {

	Message inserAnswerListMap(Map<String, WxQuestion> questionMap, Map<String, List<WxAnswer>> answerListMap);

	Message batchInsertAnswerList(List<WxAnswer> insertAList);

	Map<Long, List<WxAnswer>> queryAllToMap();

}
