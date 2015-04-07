package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IWxQuestionDao;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.WxQuestion;
import com.creditease.rc.service.IWxQuestionService;

@Service
public class WxQuestionService implements IWxQuestionService {

	@Resource
	private IWxQuestionDao wxQuestionDao;

	@Override
	public Message inserQuestionMap(Map<String, WxQuestion> questionMap) {
		// TODO Auto-generated method stub
		Message message = new Message();
		List<WxQuestion> insertQList = new ArrayList<WxQuestion>();
		Set<Entry<String, WxQuestion>> qEntrySet = questionMap.entrySet();
		for (Entry<String, WxQuestion> entry : qEntrySet) {
			insertQList.add(entry.getValue());
		}
		message = this.batchInsertQuestionList(insertQList);
		return message;
	}

	@Override
	public Message batchInsertQuestionList(List<WxQuestion> insertQList) {
		// TODO Auto-generated method stub
		Message message = new Message();
		boolean s = wxQuestionDao.batchInsertQuestionList(insertQList);
		message.setSuccess(s);
		return message;
	}

	@Override
	public Map<Long, WxQuestion> queryAllToMap() {
		Map<Long, WxQuestion> map = new HashMap<Long, WxQuestion>();
		List<WxQuestion> wxQuestions = wxQuestionDao.queryAll();
		for (WxQuestion wxQuestion : wxQuestions) {
			Long getWxQuestionId = wxQuestion.getWxQuestionId();
			map.put(getWxQuestionId, wxQuestion);
		}
		return map;
	}

}
