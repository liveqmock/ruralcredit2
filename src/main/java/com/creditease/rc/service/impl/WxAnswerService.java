package com.creditease.rc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IWxAnswerDao;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.WxAnswer;
import com.creditease.rc.domain.WxQuestion;
import com.creditease.rc.service.IWxAnswerService;
import com.creditease.rc.util.CommonUtil;

@Service
public class WxAnswerService implements IWxAnswerService {
	@Resource
	private IWxAnswerDao answerDao;

	@Override
	public Message inserAnswerListMap(Map<String, WxQuestion> questionMap, Map<String, List<WxAnswer>> answerListMap) {
		// TODO Auto-generated method stub
		Message message = new Message();
		List<WxAnswer> insertAList = new ArrayList<WxAnswer>();
		Set<Entry<String, WxQuestion>> qEntrySet = questionMap.entrySet();
		for (Entry<String, WxQuestion> qEntry : qEntrySet) {
			String getKey = qEntry.getKey();
			WxQuestion wxQuestion = qEntry.getValue();
			Long getWxQuestionId = wxQuestion.getWxQuestionId();
			List<WxAnswer> answers = answerListMap.get(getKey);
			for (WxAnswer wxAnswer : answers) {
				wxAnswer.setWxQuestionId(getWxQuestionId);
				insertAList.add(wxAnswer);
			}
		}
		message = this.batchInsertAnswerList(insertAList);
		return message;
	}

	@Override
	public Message batchInsertAnswerList(List<WxAnswer> insertAList) {
		// TODO Auto-generated method stub
		Message message = new Message();
		boolean s = answerDao.batchInsertAnswerList(insertAList);
		message.setSuccess(s);
		return message;
	}

	@Override
	public Map<Long, List<WxAnswer>> queryAllToMap() {
		Map<Long, List<WxAnswer>> map = new HashMap<Long, List<WxAnswer>>();
		List<WxAnswer> answers = answerDao.queryAll();
		for (WxAnswer wxAnswer : answers) {
			Long getWxQuestionId = wxAnswer.getWxQuestionId();
			List<WxAnswer> wxAnswers = map.get(getWxQuestionId);
			if (CommonUtil.isEmpty(wxAnswers)) {
				wxAnswers = new ArrayList<WxAnswer>();
			}
			wxAnswers.add(wxAnswer);
			map.put(getWxQuestionId, wxAnswers);
		}

		return map;
	}

}
