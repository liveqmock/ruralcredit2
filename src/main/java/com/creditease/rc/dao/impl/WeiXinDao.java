package com.creditease.rc.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IWeiXinDao;
import com.creditease.rc.domain.CustomerBasicInfo;
import com.creditease.rc.domain.NationalStandardCode;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.vo.ConvertPrizeHistory;
import com.creditease.rc.vo.Prize;
import com.creditease.rc.vo.PrizeHasFlag;
import com.creditease.rc.vo.QandA;
import com.creditease.rc.vo.QyScoreResponseParam;

@Repository
public class WeiXinDao implements IWeiXinDao {

	@Resource
	private IBaseDao baseDao;

	@Override
	public List<NationalStandardCode> selectByParentId(Map map) {
		return (List<NationalStandardCode>) baseDao.queryList("WEIXIN.selectByParentId", map);
	}

	@Override
	public List<CustomerBasicInfo> checkWeiXinVIP(Map<String, String> queryMap) {
		// TODO Auto-generated method stub
		return (List<CustomerBasicInfo>) baseDao.queryList("WEIXIN.checkWeiXinVIP", queryMap);
	}

	@Override
	public List<Long> checkAnswered(Map<String, String> queryMap) {
		// TODO Auto-generated method stub
		return (List<Long>) baseDao.queryList("WEIXIN.checkAnswered", queryMap);
	}

	@Override
	public QyScoreResponseParam queryScore(Map<String, String> queryMap) {
		// TODO Auto-generated method stub
		return (QyScoreResponseParam) baseDao.queryUnique("WEIXIN.queryScore", queryMap);
	}

	@Override
	public List<QandA> queryHistoryQuestionsWrong(String getUserCode) {
		return (List<QandA>) baseDao.queryList("WEIXIN.queryHistoryQuestionsWrong", getUserCode);
	}

	@Override
	public List<PrizeHasFlag> qyCanGetPrizeList(String getUserCode) {
		// TODO Auto-generated method stub
		return (List<PrizeHasFlag>) baseDao.queryList("WEIXIN.qyCanGetPrizeList", getUserCode);
	}

	@Override
	public List<ConvertPrizeHistory> queryConvertPrizeHistoryList(Map<String, String> queryMap) {
		// TODO Auto-generated method stub
		return (List<ConvertPrizeHistory>) baseDao.queryList("WEIXIN.queryConvertPrizeHistoryList", queryMap);
	}

	@Override
	public List<Prize> queryPrizeList(Map<String, String> queryMap) {
		return (List<Prize>) baseDao.queryList("WEIXIN.queryPrizeList", queryMap);
	}

	@Override
	public List<PrizeHasFlag> qyCanGetFirstPrizeList(String getUserCode) {
		
		return (List<PrizeHasFlag>) baseDao.queryList("WEIXIN.qyCanGetFirstPrizeList", getUserCode);
	}

}
