package com.creditease.rc.dao;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.CustomerBasicInfo;
import com.creditease.rc.domain.NationalStandardCode;
import com.creditease.rc.vo.ConvertPrizeHistory;
import com.creditease.rc.vo.Prize;
import com.creditease.rc.vo.PrizeHasFlag;
import com.creditease.rc.vo.QandA;
import com.creditease.rc.vo.QyScoreResponseParam;

public interface IWeiXinDao {

	/*
	 * 根据父ID获取省市区名称、国标码
	 */
	public List<NationalStandardCode> selectByParentId(Map map);

	public List<CustomerBasicInfo> checkWeiXinVIP(Map<String, String> queryMap);

	public List<Long> checkAnswered(Map<String, String> queryMap);

	public QyScoreResponseParam queryScore(Map<String, String> queryMap);

	public List<QandA> queryHistoryQuestionsWrong(String getUserCode);

	public List<PrizeHasFlag> qyCanGetPrizeList(String getUserCode);

	public List<ConvertPrizeHistory> queryConvertPrizeHistoryList(Map<String, String> queryMap);

	public List<Prize> queryPrizeList(Map<String, String> queryMap);
	
	public List<PrizeHasFlag> qyCanGetFirstPrizeList(String getUserCode);

}
