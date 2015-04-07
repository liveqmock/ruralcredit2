package com.creditease.rc.service;

import com.creditease.rc.vo.AddScoreRequestParam;
import com.creditease.rc.vo.AddScoreResponseParam;
import com.creditease.rc.vo.AnswerCompleteRequestParam;
import com.creditease.rc.vo.AnswerCompleteResponseParam;
import com.creditease.rc.vo.ConsultPoolRequestParam;
import com.creditease.rc.vo.ConvertPrizeRequestParam;
import com.creditease.rc.vo.ConvertPrizeResponseParam;
import com.creditease.rc.vo.DataDictionaryResponseParam;
import com.creditease.rc.vo.GainQuestionsRequestParam;
import com.creditease.rc.vo.GainQuestionsResponseParam;
import com.creditease.rc.vo.PCAResponseParam;
import com.creditease.rc.vo.QyCanGetPrizeListRequestParam;
import com.creditease.rc.vo.QyCanGetPrizeListResponseParam;
import com.creditease.rc.vo.QyChangedGetPrizeUserRequestParam;
import com.creditease.rc.vo.QyChangedGetPrizeUserResponseParam;
import com.creditease.rc.vo.QyConvertPrizeHistoryRequestParam;
import com.creditease.rc.vo.QyConvertPrizeHistoryResponseParam;
import com.creditease.rc.vo.QyHistoryQuestionsWrongRequestParam;
import com.creditease.rc.vo.QyHistoryQuestionsWrongResponseParam;
import com.creditease.rc.vo.QyPrizeListRequestParam;
import com.creditease.rc.vo.QyPrizeListResponseParam;
import com.creditease.rc.vo.QyScoreRequestParam;
import com.creditease.rc.vo.QyScoreResponseParam;
import com.creditease.rc.vo.UserLoginRequestParam;
import com.creditease.rc.vo.UserLoginResponseParam;
import com.creditease.rc.vo.UserQyRequestParam;
import com.creditease.rc.vo.UserQyResponseParam;
import com.creditease.rc.vo.WebServiceMessage;

public interface IWeiXinService {

	/**
	 * 查询借款用途
	 * 
	 * @return
	 */
	DataDictionaryResponseParam qyBorrowUse();

	/**
	 * 查询信息来源
	 * 
	 * @return
	 */
	DataDictionaryResponseParam qyInfoSource();

	/**
	 * 查询省市区
	 * 
	 * @return
	 */
	PCAResponseParam qyProvinceCityArea(String parentId);

	/**
	 * 保存预约（咨询记录）
	 * 
	 * @return
	 */
	public WebServiceMessage saveReservation(ConsultPoolRequestParam param);

	/**
	 * 用户登陆时，查询此用户是否登陆过，显示相对应的用户信息
	 * 
	 * @author 罗红杰
	 * @param userQyRequestParam
	 * @return
	 */
	UserQyResponseParam userSelect(UserQyRequestParam userQyRequestParam);
	
	/**
	 * 用户登录并给出题型
	 * 
	 * @author 郝强
	 * @param userLoginRequestParam
	 * @return
	 */
	UserLoginResponseParam userLogin(UserLoginRequestParam userLoginRequestParam);

	/**
	 * 获得题目
	 * 
	 * @author 郝强
	 * @param gainQuestionsRequestParam
	 * @return
	 */
	GainQuestionsResponseParam gainQuestions(GainQuestionsRequestParam gainQuestionsRequestParam);

	/**
	 * 答题完成
	 * 
	 * @author 郝强
	 * @param answerCompleteRequestParam
	 * @return
	 */
	AnswerCompleteResponseParam answerComplete(AnswerCompleteRequestParam answerCompleteRequestParam);

	/**
	 * 查看成绩
	 * 
	 * @author 郝强
	 * @param qyScoreRequestParam
	 * @return
	 */
	QyScoreResponseParam qyScore(QyScoreRequestParam qyScoreRequestParam);

	/**
	 * 查看错误历史
	 * 
	 * @author 郝强
	 * @param qyHistoryQuestionsWrongRequestParam
	 * @return
	 */
	QyHistoryQuestionsWrongResponseParam qyHistoryQuestionsWrong(QyHistoryQuestionsWrongRequestParam qyHistoryQuestionsWrongRequestParam);

	/**
	 * 给指定用户加减分
	 * 
	 * @author 郝强
	 * @param addScoreRequestParam
	 * @return
	 */
	AddScoreResponseParam addScore(AddScoreRequestParam addScoreRequestParam);

	/**
	 * 查询奖品列表
	 * 
	 * @author 郝强
	 * @param qyPrizeListRequestParam
	 * @return
	 */
	QyPrizeListResponseParam qyPrizeList(QyPrizeListRequestParam qyPrizeListRequestParam);

	/**
	 * 查看可兑换奖品
	 * 
	 * @author 郝强
	 * @param qyCanGetPrizeListRequestParam
	 * @return
	 */
	QyCanGetPrizeListResponseParam qyCanGetPrizeList(QyCanGetPrizeListRequestParam qyCanGetPrizeListRequestParam);

	/**
	 * 查看可兑换一等奖 奖品
	 * 
	 * @author  罗红杰
	 * @param qyCanGetPrizeListRequestParam
	 * @return
	 */
	QyCanGetPrizeListResponseParam qyCanGetFirstPrizeList(QyCanGetPrizeListRequestParam qyCanGetPrizeListRequestParam);
	
	
	/**
	 * 兑换奖品
	 * 
	 * @author 郝强
	 * @param convertPrizeRequestParam
	 * @return
	 */
	ConvertPrizeResponseParam convertPrize(ConvertPrizeRequestParam convertPrizeRequestParam);

	/**
	 * 查看兑奖历史
	 * 
	 * @author 郝强
	 * @param qyConvertPrizeHistoryRequestParam
	 * @return
	 */
	QyConvertPrizeHistoryResponseParam qyConvertPrizeHistory(QyConvertPrizeHistoryRequestParam qyConvertPrizeHistoryRequestParam);

	/**
	 * @author 罗红杰
	 * @param changedGetPrizeUserRequestParam
	 * @return
	 */
	//QyChangedGetPrizeUserResponseParam qyChangedGetPrizeUser(QyChangedGetPrizeUserRequestParam changedGetPrizeUserRequestParam);

}
