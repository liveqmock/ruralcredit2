package com.creditease.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.creditease.rc.domain.ComplaintProposalRequestParam;
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
import com.creditease.rc.vo.PCARequestParam;
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
import com.creditease.rc.vo.WebsiteInfoRequestParam;
import com.creditease.rc.vo.WebsiteInfoResponseParam;

@WebService
public interface IWeiXinWS {

	/**
	 * 查询借款用途
	 * 
	 * @return
	 */
	public DataDictionaryResponseParam qyBorrowUse();

	/**
	 * 查询信息来源
	 * 
	 * @return
	 */
	public DataDictionaryResponseParam qyInfoSource();

	/**
	 * 查询省市区
	 * 
	 * @return
	 */
	public PCAResponseParam qyProvinceCityArea(@WebParam(name = "pcaRequestParam") PCARequestParam pcaRequestParam);

	/**
	 * 保存预约记录
	 * 
	 * @param poolRequestParam
	 * @return
	 */
	public WebServiceMessage saveReservation(@WebParam(name = "poolRequestParam") ConsultPoolRequestParam poolRequestParam);

	/**
	 * 查询网点接口
	 * 
	 * @author 罗红杰
	 * @return List<Website>
	 */
	public WebsiteInfoResponseParam qyWebsiteInfo(@WebParam(name = "websiteInfoRequestParam") WebsiteInfoRequestParam websiteInfoRequestParam);

	/**用户登陆时，查询此用户是否登陆过，显示相对应的用户信息
	 * 
	 *  @author 罗红杰
	 *  @param UserQyRequestParam
	 *  @return
	 **/
	public UserQyResponseParam qyUserInfo(@WebParam(name="userQyRequestParam") UserQyRequestParam userQyRequestParam);
	
	/** 以下爲活動類接口bigin **/

	/**
	 * 用戶登錄驗證並給出題型
	 * 
	 * @author 郝強
	 * @param userLoginRequestParam
	 * @return
	 */
	public UserLoginResponseParam userLogin(@WebParam(name = "userLoginRequestParam") UserLoginRequestParam userLoginRequestParam);

	/**
	 * 获得题目
	 * 
	 * @author 郝强
	 * @param gainQuestionsRequestParam
	 * @return
	 */
	public GainQuestionsResponseParam gainQuestions(@WebParam(name = "gainQuestionsRequestParam") GainQuestionsRequestParam gainQuestionsRequestParam);

	/**
	 * 答題完成
	 * 
	 * @author 郝強
	 * @param answerCompleteRequestParam
	 * @return
	 */
	public AnswerCompleteResponseParam answerComplete(@WebParam(name = "answerCompleteRequestParam") AnswerCompleteRequestParam answerCompleteRequestParam);

	/**
	 * 查看成績
	 * 
	 * @author 郝強
	 * @param qyScoreRequestParam
	 * @return
	 */
	public QyScoreResponseParam qyScore(@WebParam(name = "qyScoreRequestParam") QyScoreRequestParam qyScoreRequestParam);

	/**
	 * 查看錯誤歷史
	 * 
	 * @author 郝強
	 * @param qyHistoryQuestionsWrongRequestParam
	 * @return
	 */
	public QyHistoryQuestionsWrongResponseParam qyHistoryQuestionsWrong(@WebParam(name = "qyHistoryQuestionsWrongRequestParam") QyHistoryQuestionsWrongRequestParam qyHistoryQuestionsWrongRequestParam);

	/**
	 * 給指定用戶加減分
	 * 
	 * @author 郝強
	 * @param addScoreRequestParam
	 * @return
	 */
	public AddScoreResponseParam addScore(@WebParam(name = "addScoreRequestParam") AddScoreRequestParam addScoreRequestParam);

	/**
	 * 查詢獎品列表
	 * 
	 * @author 郝強
	 * @param qyPrizeListRequestParam
	 * @return
	 */
	public QyPrizeListResponseParam qyPrizeList(@WebParam(name = "qyPrizeListRequestParam") QyPrizeListRequestParam qyPrizeListRequestParam);

	/**
	 * 查询可兑换奖品
	 * 
	 * @author 罗红杰
	 * @param qyCanGetPrizeListRequestParam
	 * @return
	 */
	public QyCanGetPrizeListResponseParam qyCanGetFirstPrizeList(@WebParam(name = "qyCanGetPrizeListRequestParam") QyCanGetPrizeListRequestParam qyCanGetPrizeListRequestParam);
	/**
	 * 查詢可兌換獎品
	 * 
	 * @author 郝強
	 * @param qyCanGetPrizeListRequestParam
	 * @return
	 */
	public QyCanGetPrizeListResponseParam qyCanGetPrizeList(@WebParam(name = "qyCanGetPrizeListRequestParam") QyCanGetPrizeListRequestParam qyCanGetPrizeListRequestParam);

	/**
	 * 兌換獎品
	 * 
	 * @author 郝強
	 * @param convertPrizeRequestParam
	 * @return
	 */
	public ConvertPrizeResponseParam convertPrize(@WebParam(name = "convertPrizeRequestParam") ConvertPrizeRequestParam convertPrizeRequestParam);

	/**
	 * 查看對講歷史
	 * 
	 * @author 郝強
	 * @param qyConvertPrizeHistoryRequestParam
	 * @return
	 */
	public QyConvertPrizeHistoryResponseParam qyConvertPrizeHistory(@WebParam(name = "qyConvertPrizeHistoryRequestParam") QyConvertPrizeHistoryRequestParam qyConvertPrizeHistoryRequestParam);

	/** 以下爲活動類接口end **/

	// 保存投诉意见
	public WebServiceMessage saveComplaintProposal(@WebParam(name = "complaintProposalRequestParam") ComplaintProposalRequestParam complaintProposalRequestParam);
	/**
	 * 
	 * @author 罗红杰
	 * @param qyChangedGetPrizeUserRequestParam
	 * @return
	 */
	//public QyChangedGetPrizeUserResponseParam qyChangedGetPrizeUser(@WebParam(name="qyChangedGetPrizeUserRequestParam") QyChangedGetPrizeUserRequestParam qyChangedGetPrizeUserRequestParam);
}
