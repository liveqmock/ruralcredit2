package com.creditease.webservice.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.creditease.rc.domain.ComplaintProposalRequestParam;
import com.creditease.rc.service.IComplaintProposalService;
import com.creditease.rc.service.IWebsiteService;
import com.creditease.rc.service.IWeiXinService;
import com.creditease.rc.service.impl.Rural2CreditService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.vo.*;
import com.creditease.webservice.IWeiXinWS;

public class WeiXinWS implements IWeiXinWS {
	Logger log = Logger.getLogger(Rural2CreditService.class);
	@Resource
	private IWeiXinService weiXinService;
	@Resource
	private IWebsiteService websiteService;

	@Resource
	private IComplaintProposalService complaintProposalService;

	@Override
	public DataDictionaryResponseParam qyBorrowUse() {
		return weiXinService.qyBorrowUse();
	}

	@Override
	public DataDictionaryResponseParam qyInfoSource() {
		return weiXinService.qyInfoSource();
	}

	@Override
	public PCAResponseParam qyProvinceCityArea(PCARequestParam pcaRequestParam) {
		return weiXinService.qyProvinceCityArea(pcaRequestParam.getParentId());
	}

	@Override
	public WebServiceMessage saveReservation(ConsultPoolRequestParam poolRequestParam) {
		return weiXinService.saveReservation(poolRequestParam);
	}

	//网点查询
	@Override
	public WebsiteInfoResponseParam qyWebsiteInfo(WebsiteInfoRequestParam websiteInfoRequestParam) {
		WebsiteInfoResponseParam ResponseParam = new WebsiteInfoResponseParam();
		WebsiteData websiteData = websiteService.qyWebsiteByMap(websiteInfoRequestParam);
		ResponseParam.setWebsiteData(websiteData);
		return ResponseParam;
	}

	/** 用户登陆时，查询此用户是否登陆过，显示相对应的用户信息**/
	@Override
	public UserQyResponseParam qyUserInfo(UserQyRequestParam userQyRequestParam) {
		UserQyResponseParam userQyResponseParam=new UserQyResponseParam();
		userQyResponseParam=weiXinService.userSelect(userQyRequestParam);
		return userQyResponseParam;
	}
	
	/** 以下爲活動類接口bigin **/
	@Override
	public UserLoginResponseParam userLogin(UserLoginRequestParam userLoginRequestParam) {
		log.info("**************WeiXinWS.userLogin(userLoginRequestParam) requestParam******************");
		log.info(JsonUtil.getJackson(userLoginRequestParam));
		UserLoginResponseParam userLoginResponseParam = new UserLoginResponseParam();
		String codeinfo = "";
		String message = "";
		// 1.校验
		if (userLoginRequestParam == null) {
			codeinfo = "2";
			message = "userLoginRequestParam为空！";
			userLoginResponseParam.setCodeinfo(codeinfo);
			userLoginResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.userLogin(userLoginResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(userLoginResponseParam));
			return userLoginResponseParam;
		}
		String getLoginType = userLoginRequestParam.getLoginType();
		String getName = userLoginRequestParam.getName();
		String getPhone = userLoginRequestParam.getPhone();
		String getUserCode = userLoginRequestParam.getUserCode();
		String getIdentityCardSub = userLoginRequestParam.getIdentityCardSub();
		if (CommonUtil.isEmpty(getUserCode)) {
			codeinfo = "3";
			message = "getUserCode为空！";
			userLoginResponseParam.setCodeinfo(codeinfo);
			userLoginResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.userLogin(userLoginResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(userLoginResponseParam));
			return userLoginResponseParam;
		}

		if (CommonUtil.isEmpty(getName)) {
			codeinfo = "4";
			message = "getName为空！";
			userLoginResponseParam.setCodeinfo(codeinfo);
			userLoginResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.userLogin(userLoginResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(userLoginResponseParam));
			return userLoginResponseParam;
		}
		if (CommonUtil.isEmpty(getLoginType)) {
			codeinfo = "5";
			message = "getLoginType为空！";
			userLoginResponseParam.setCodeinfo(codeinfo);
			userLoginResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.userLogin(userLoginResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(userLoginResponseParam));
			return userLoginResponseParam;
		}
		if (!getLoginType.equals("0") && !getLoginType.equals("1") && !getLoginType.equals("2")) {
			codeinfo = "6";
			message = "getLoginType不在范围内！";
			userLoginResponseParam.setCodeinfo(codeinfo);
			userLoginResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.userLogin(userLoginResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(userLoginResponseParam));
			return userLoginResponseParam;
		} else if (getLoginType.equals("0")) {
			if (CommonUtil.isEmpty(getIdentityCardSub)) {
				codeinfo = "7";
				message = "getIdentityCardSub为空！";
				userLoginResponseParam.setCodeinfo(codeinfo);
				userLoginResponseParam.setMessgae(message);
				log.info("**************WeiXinWS.userLogin(userLoginResponseParam) responseParam******************");
				log.info(JsonUtil.getJackson(userLoginResponseParam));
				return userLoginResponseParam;
			}
		} else {
			if (CommonUtil.isEmpty(getPhone)) {
				codeinfo = "8";
				message = "getPhone为空！";
				userLoginResponseParam.setCodeinfo(codeinfo);
				userLoginResponseParam.setMessgae(message);
				log.info("**************WeiXinWS.userLogin(userLoginResponseParam) responseParam******************");
				log.info(JsonUtil.getJackson(userLoginResponseParam));
				return userLoginResponseParam;
			}
		}

		// 2.判断并出题
		try {
			userLoginResponseParam = weiXinService.userLogin(userLoginRequestParam);
		} catch (Exception e) {
			e.printStackTrace();
			codeinfo = "1";
			message = "农贷系统内部错误！";
			userLoginResponseParam.setCodeinfo(codeinfo);
			userLoginResponseParam.setMessgae(message);
		}
		log.info("**************WeiXinWS.userLogin(userLoginResponseParam) responseParam******************");
		log.info(JsonUtil.getJackson(userLoginResponseParam));
		return userLoginResponseParam;
	}

	@Override
	public GainQuestionsResponseParam gainQuestions(GainQuestionsRequestParam gainQuestionsRequestParam) {
		log.info("**************WeiXinWS.gainQuestions(gainQuestionsRequestParam) requestParam******************");
		log.info(JsonUtil.getJackson(gainQuestionsRequestParam));
		GainQuestionsResponseParam gainQuestionsResponseParam = new GainQuestionsResponseParam();
		String codeinfo = "";
		String message = "";
		if (gainQuestionsRequestParam == null) {
			codeinfo = "2";
			message = "gainQuestionsRequestParam为空！";
			gainQuestionsResponseParam.setCodeinfo(codeinfo);
			gainQuestionsResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.gainQuestions(gainQuestionsResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(gainQuestionsResponseParam));
			return gainQuestionsResponseParam;
		}
		String getLevelType = gainQuestionsRequestParam.getLevelType();
		if (CommonUtil.isEmpty(getLevelType)) {
			codeinfo = "3";
			message = "getLevelType为空！";
			gainQuestionsResponseParam.setCodeinfo(codeinfo);
			gainQuestionsResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.gainQuestions(gainQuestionsResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(gainQuestionsResponseParam));
			return gainQuestionsResponseParam;
		}
		try {

			gainQuestionsResponseParam = weiXinService.gainQuestions(gainQuestionsRequestParam);
		} catch (Exception e) {
			e.printStackTrace();
			codeinfo = "1";
			message = "农贷系统内部错误！";
			gainQuestionsResponseParam.setCodeinfo(codeinfo);
			gainQuestionsResponseParam.setMessgae(message);
		}
		log.info("**************WeiXinWS.gainQuestions(gainQuestionsResponseParam) responseParam******************");
		log.info(JsonUtil.getJackson(gainQuestionsResponseParam));
		return gainQuestionsResponseParam;
	}

	@Override
	public AnswerCompleteResponseParam answerComplete(AnswerCompleteRequestParam answerCompleteRequestParam) {
		log.info("**************WeiXinWS.answerComplete(answerCompleteRequestParam) requestParam******************");
		log.info(JsonUtil.getJackson(answerCompleteRequestParam));
		AnswerCompleteResponseParam answerCompleteResponseParam = new AnswerCompleteResponseParam();
		String codeinfo = "";
		String message = "";
		if (answerCompleteRequestParam == null) {
			codeinfo = "2";
			message = "answerCompleteRequestParam为空！";
			answerCompleteResponseParam.setCodeinfo(codeinfo);
			answerCompleteResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.answerComplete(answerCompleteResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(answerCompleteResponseParam));
			return answerCompleteResponseParam;
		}
		String getUserCode = answerCompleteRequestParam.getUserCode();
		String getLoginType = answerCompleteRequestParam.getLoginType();
		List<QandA> getQandAs = answerCompleteRequestParam.getQandAs();
		if (CommonUtil.isEmpty(getUserCode)) {
			codeinfo = "3";
			message = "getUserCode为空！";
			answerCompleteResponseParam.setCodeinfo(codeinfo);
			answerCompleteResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.answerComplete(answerCompleteResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(answerCompleteResponseParam));
			return answerCompleteResponseParam;
		}
		if (CommonUtil.isEmpty(getLoginType)) {
			codeinfo = "4";
			message = "getLoginType为空！";
			answerCompleteResponseParam.setCodeinfo(codeinfo);
			answerCompleteResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.answerComplete(answerCompleteResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(answerCompleteResponseParam));
			return answerCompleteResponseParam;
		}
		if (!getLoginType.equals("0") && !getLoginType.equals("1") && !getLoginType.equals("2")) {
			codeinfo = "5";
			message = "getLoginType为空！";
			answerCompleteResponseParam.setCodeinfo(codeinfo);
			answerCompleteResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.answerComplete(answerCompleteResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(answerCompleteResponseParam));
			return answerCompleteResponseParam;
		}
		if (CommonUtil.isEmpty(getQandAs)) {
			codeinfo = "6";
			message = "getQandAs为空！";
			answerCompleteResponseParam.setCodeinfo(codeinfo);
			answerCompleteResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.answerComplete(answerCompleteResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(answerCompleteResponseParam));
			return answerCompleteResponseParam;
		}
		try {

			answerCompleteResponseParam = weiXinService.answerComplete(answerCompleteRequestParam);
		} catch (Exception e) {
			e.printStackTrace();
			codeinfo = "1";
			message = "农贷系统内部错误！";
			answerCompleteResponseParam.setCodeinfo(codeinfo);
			answerCompleteResponseParam.setMessgae(message);
		}
		log.info("**************WeiXinWS.answerComplete(answerCompleteResponseParam) responseParam******************");
		log.info(JsonUtil.getJackson(answerCompleteResponseParam));
		return answerCompleteResponseParam;
	}

	@Override
	public QyScoreResponseParam qyScore(QyScoreRequestParam qyScoreRequestParam) {
		log.info("**************WeiXinWS.qyScore(qyScoreRequestParam) requestParam******************");
		log.info(JsonUtil.getJackson(qyScoreRequestParam));
		QyScoreResponseParam qyScoreResponseParam = new QyScoreResponseParam();
		String codeinfo = "";
		String message = "";
		if (qyScoreRequestParam == null) {
			codeinfo = "2";
			message = "qyScoreRequestParam为空！";
			qyScoreResponseParam.setCodeinfo(codeinfo);
			qyScoreResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.qyScore(qyScoreResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(qyScoreResponseParam));
			return qyScoreResponseParam;
		}
		String getUserCode = qyScoreRequestParam.getUserCode();
		String getLoginType = qyScoreRequestParam.getAnswerDate();
		if (CommonUtil.isEmpty(getUserCode)) {
			codeinfo = "3";
			message = "getUserCode为空！";
			qyScoreResponseParam.setCodeinfo(codeinfo);
			qyScoreResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.qyScore(qyScoreResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(qyScoreResponseParam));
			return qyScoreResponseParam;
		}
		if (CommonUtil.isEmpty(getLoginType)) {
			codeinfo = "4";
			message = "getLoginType为空！";
			qyScoreResponseParam.setCodeinfo(codeinfo);
			qyScoreResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.qyScore(qyScoreResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(qyScoreResponseParam));
			return qyScoreResponseParam;
		}
		try {

			qyScoreResponseParam = weiXinService.qyScore(qyScoreRequestParam);
		} catch (Exception e) {
			e.printStackTrace();
			codeinfo = "1";
			message = "农贷系统内部错误！";
			qyScoreResponseParam.setCodeinfo(codeinfo);
			qyScoreResponseParam.setMessgae(message);
		}
		log.info("**************WeiXinWS.qyScore(qyScoreResponseParam) responseParam******************");
		log.info(JsonUtil.getJackson(qyScoreResponseParam));
		return qyScoreResponseParam;
	}

	@Override
	public QyHistoryQuestionsWrongResponseParam qyHistoryQuestionsWrong(QyHistoryQuestionsWrongRequestParam qyHistoryQuestionsWrongRequestParam) {
		log.info("**************WeiXinWS.qyHistoryQuestionsWrong(qyHistoryQuestionsWrongRequestParam) requestParam******************");
		log.info(JsonUtil.getJackson(qyHistoryQuestionsWrongRequestParam));
		QyHistoryQuestionsWrongResponseParam qyHistoryQuestionsWrongResponseParam = new QyHistoryQuestionsWrongResponseParam();
		String codeinfo = "";
		String message = "";
		if (qyHistoryQuestionsWrongRequestParam == null) {
			codeinfo = "2";
			message = "answerCompleteRequestParam为空！";
			qyHistoryQuestionsWrongResponseParam.setCodeinfo(codeinfo);
			qyHistoryQuestionsWrongResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.qyHistoryQuestionsWrong(qyHistoryQuestionsWrongResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(qyHistoryQuestionsWrongResponseParam));
			return qyHistoryQuestionsWrongResponseParam;
		}
		String getUserCode = qyHistoryQuestionsWrongRequestParam.getUserCode();
		if (CommonUtil.isEmpty(getUserCode)) {
			codeinfo = "3";
			message = "getUserCode为空！";
			qyHistoryQuestionsWrongResponseParam.setCodeinfo(codeinfo);
			qyHistoryQuestionsWrongResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.qyHistoryQuestionsWrong(qyHistoryQuestionsWrongResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(qyHistoryQuestionsWrongResponseParam));
			return qyHistoryQuestionsWrongResponseParam;
		}
		try {
			qyHistoryQuestionsWrongResponseParam = weiXinService.qyHistoryQuestionsWrong(qyHistoryQuestionsWrongRequestParam);
		} catch (Exception e) {
			e.printStackTrace();
			codeinfo = "1";
			message = "农贷系统内部错误！";
			qyHistoryQuestionsWrongResponseParam.setCodeinfo(codeinfo);
			qyHistoryQuestionsWrongResponseParam.setMessgae(message);
		}
		log.info("**************WeiXinWS.qyHistoryQuestionsWrong(qyHistoryQuestionsWrongResponseParam) responseParam******************");
		log.info(JsonUtil.getJackson(qyHistoryQuestionsWrongResponseParam));
		return qyHistoryQuestionsWrongResponseParam;
	}

	@Override
	public AddScoreResponseParam addScore(AddScoreRequestParam addScoreRequestParam) {
		log.info("**************WeiXinWS.addScore(addScoreRequestParam) requestParam******************");
		log.info(JsonUtil.getJackson(addScoreRequestParam));
		AddScoreResponseParam addScoreResponseParam = new AddScoreResponseParam();
		String codeinfo = "";
		String message = "";
		if (addScoreRequestParam == null) {
			codeinfo = "2";
			message = "addScoreRequestParam为空！";
			addScoreResponseParam.setCodeinfo(codeinfo);
			addScoreResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.addScore(addScoreRequestParam) responseParam******************");
			log.info(JsonUtil.getJackson(addScoreRequestParam));
			return addScoreResponseParam;
		}
		String getUserCode = addScoreRequestParam.getUserCode();
		int getScore = addScoreRequestParam.getScore();
		if (CommonUtil.isEmpty(getUserCode)) {
			codeinfo = "3";
			message = "getUserCode为空！";
			addScoreResponseParam.setCodeinfo(codeinfo);
			addScoreResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.addScore(addScoreRequestParam) responseParam******************");
			log.info(JsonUtil.getJackson(addScoreRequestParam));
			return addScoreResponseParam;
		}
		try {

			addScoreResponseParam = weiXinService.addScore(addScoreRequestParam);
		} catch (Exception e) {
			e.printStackTrace();
			codeinfo = "1";
			message = "农贷系统内部错误！";
			addScoreResponseParam.setCodeinfo(codeinfo);
			addScoreResponseParam.setMessgae(message);
		}
		log.info("**************WeiXinWS.addScore(addScoreRequestParam) responseParam******************");
		log.info(JsonUtil.getJackson(addScoreRequestParam));
		return addScoreResponseParam;
	}

	@Override
	public QyPrizeListResponseParam qyPrizeList(QyPrizeListRequestParam qyPrizeListRequestParam) {
		log.info("**************WeiXinWS.qyPrizeList(qyPrizeListRequestParam) requestParam******************");
		log.info(JsonUtil.getJackson(qyPrizeListRequestParam));
		QyPrizeListResponseParam qyPrizeListResponseParam = new QyPrizeListResponseParam();
		String codeinfo = "";
		String message = "";
		if (qyPrizeListRequestParam == null) {
			codeinfo = "2";
			message = "qyPrizeListRequestParam为空！";
			qyPrizeListResponseParam.setCodeinfo(codeinfo);
			qyPrizeListResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.qyPrizeList(qyPrizeListResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(qyPrizeListResponseParam));
			return qyPrizeListResponseParam;
		}
		try {

			qyPrizeListResponseParam = weiXinService.qyPrizeList(qyPrizeListRequestParam);
		} catch (Exception e) {
			e.printStackTrace();
			codeinfo = "1";
			message = "农贷系统内部错误！";
			qyPrizeListResponseParam.setCodeinfo(codeinfo);
			qyPrizeListResponseParam.setMessgae(message);
		}
		log.info("**************WeiXinWS.qyPrizeList(qyPrizeListResponseParam) responseParam******************");
		log.info(JsonUtil.getJackson(qyPrizeListResponseParam));
		return qyPrizeListResponseParam;
	}

	@Override
	public QyCanGetPrizeListResponseParam qyCanGetPrizeList(QyCanGetPrizeListRequestParam qyCanGetPrizeListRequestParam) {
		log.info("**************WeiXinWS.qyCanGetPrizeList(qyCanGetPrizeListRequestParam) requestParam******************");
		log.info(JsonUtil.getJackson(qyCanGetPrizeListRequestParam));
		QyCanGetPrizeListResponseParam qyCanGetPrizeListResponseParam = new QyCanGetPrizeListResponseParam();
		String codeinfo = "";
		String message = "";
		if (qyCanGetPrizeListRequestParam == null) {
			codeinfo = "2";
			message = "qyCanGetPrizeListRequestParam为空！";
			qyCanGetPrizeListResponseParam.setCodeinfo(codeinfo);
			qyCanGetPrizeListResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.qyCanGetPrizeList(qyCanGetPrizeListResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(qyCanGetPrizeListResponseParam));
			return qyCanGetPrizeListResponseParam;
		}
		String getUserCode = qyCanGetPrizeListRequestParam.getUserCode();
		if (CommonUtil.isEmpty(getUserCode)) {
			codeinfo = "3";
			message = "getUserCode为空！";
			qyCanGetPrizeListResponseParam.setCodeinfo(codeinfo);
			qyCanGetPrizeListResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.qyCanGetPrizeList(qyCanGetPrizeListResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(qyCanGetPrizeListResponseParam));
			return qyCanGetPrizeListResponseParam;
		}
		try {

			qyCanGetPrizeListResponseParam = weiXinService.qyCanGetPrizeList(qyCanGetPrizeListRequestParam);
		} catch (Exception e) {
			e.printStackTrace();
			codeinfo = "1";
			message = "农贷系统内部错误！";
			qyCanGetPrizeListResponseParam.setCodeinfo(codeinfo);
			qyCanGetPrizeListResponseParam.setMessgae(message);
		}
		log.info("**************WeiXinWS.qyCanGetPrizeList(qyCanGetPrizeListResponseParam) responseParam******************");
		log.info(JsonUtil.getJackson(qyCanGetPrizeListResponseParam));
		return qyCanGetPrizeListResponseParam;
	}

	@Override
	public ConvertPrizeResponseParam convertPrize(ConvertPrizeRequestParam convertPrizeRequestParam) {
		log.info("**************WeiXinWS.convertPrize(convertPrizeRequestParam) requestParam******************");
		log.info(JsonUtil.getJackson(convertPrizeRequestParam));
		ConvertPrizeResponseParam convertPrizeResponseParam = new ConvertPrizeResponseParam();
		String codeinfo = "";
		String message = "";
		if (convertPrizeRequestParam == null) {
			codeinfo = "2";
			message = "convertPrizeRequestParam为空！";
			convertPrizeResponseParam.setCodeinfo(codeinfo);
			convertPrizeResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.convertPrize(convertPrizeResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(convertPrizeResponseParam));
			return convertPrizeResponseParam;
		}
		String getUserCode = convertPrizeRequestParam.getUserCode();
		Long getWxPrizeId = convertPrizeRequestParam.getWxPrizeId();
		String getProvincehome = convertPrizeRequestParam.getProvincehome();
		String getCityhome = convertPrizeRequestParam.getCityhome();
		String getCountyhome = convertPrizeRequestParam.getCountyhome();
		String getBranchofficeName = convertPrizeRequestParam.getBranchofficeName();
		String getBranchofficeId = convertPrizeRequestParam.getBranchofficeId();

		if (CommonUtil.isEmpty(getUserCode)) {
			codeinfo = "3";
			message = "getUserCode为空！";
			convertPrizeResponseParam.setCodeinfo(codeinfo);
			convertPrizeResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.convertPrize(convertPrizeResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(convertPrizeResponseParam));
			return convertPrizeResponseParam;
		}
		if (getWxPrizeId == null) {
			codeinfo = "4";
			message = "getWxPrizeId为空！";
			convertPrizeResponseParam.setCodeinfo(codeinfo);
			convertPrizeResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.convertPrize(convertPrizeResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(convertPrizeResponseParam));
			return convertPrizeResponseParam;
		}
//		if (CommonUtil.isEmpty(getProvincehome)) {
//			codeinfo = "5";
//			message = "getProvincehome为空！";
//			convertPrizeResponseParam.setCodeinfo(codeinfo);
//			convertPrizeResponseParam.setMessgae(message);
//			log.info("**************WeiXinWS.convertPrize(convertPrizeResponseParam) responseParam******************");
//			log.info(JsonUtil.getJackson(convertPrizeResponseParam));
//			return convertPrizeResponseParam;
//		}
//		if (CommonUtil.isEmpty(getCityhome)) {
//			codeinfo = "6";
//			message = "getCityhome为空！";
//			convertPrizeResponseParam.setCodeinfo(codeinfo);
//			convertPrizeResponseParam.setMessgae(message);
//			log.info("**************WeiXinWS.convertPrize(convertPrizeResponseParam) responseParam******************");
//			log.info(JsonUtil.getJackson(convertPrizeResponseParam));
//			return convertPrizeResponseParam;
//		}
//		if (CommonUtil.isEmpty(getCountyhome)) {
//			codeinfo = "7";
//			message = "getCountyhome为空！";
//			convertPrizeResponseParam.setCodeinfo(codeinfo);
//			convertPrizeResponseParam.setMessgae(message);
//			log.info("**************WeiXinWS.convertPrize(convertPrizeResponseParam) responseParam******************");
//			log.info(JsonUtil.getJackson(convertPrizeResponseParam));
//			return convertPrizeResponseParam;
//		}
//		if (CommonUtil.isEmpty(getBranchofficeId)) {
//			codeinfo = "8";
//			message = "getBranchofficeId为空！";
//			convertPrizeResponseParam.setCodeinfo(codeinfo);
//			convertPrizeResponseParam.setMessgae(message);
//			log.info("**************WeiXinWS.convertPrize(convertPrizeResponseParam) responseParam******************");
//			log.info(JsonUtil.getJackson(convertPrizeResponseParam));
//			return convertPrizeResponseParam;
//		}
		try {

			convertPrizeResponseParam = weiXinService.convertPrize(convertPrizeRequestParam);
		} catch (Exception e) {
			e.printStackTrace();
			codeinfo = "1";
			message = "农贷系统内部错误！";
			convertPrizeResponseParam.setCodeinfo(codeinfo);
			convertPrizeResponseParam.setMessgae(message);
		}
		log.info("**************WeiXinWS.convertPrize(convertPrizeResponseParam) responseParam******************");
		log.info(JsonUtil.getJackson(convertPrizeResponseParam));
		return convertPrizeResponseParam;
	}

	@Override
	public QyConvertPrizeHistoryResponseParam qyConvertPrizeHistory(QyConvertPrizeHistoryRequestParam qyConvertPrizeHistoryRequestParam) {
		log.info("**************WeiXinWS.qyConvertPrizeHistory(qyConvertPrizeHistoryRequestParam) requestParam******************");
		log.info(JsonUtil.getJackson(qyConvertPrizeHistoryRequestParam));
		QyConvertPrizeHistoryResponseParam qyConvertPrizeHistoryResponseParam = new QyConvertPrizeHistoryResponseParam();
		String codeinfo = "";
		String message = "";
		if (qyConvertPrizeHistoryRequestParam == null) {
			codeinfo = "2";
			message = "qyConvertPrizeHistoryRequestParam为空！";
			qyConvertPrizeHistoryResponseParam.setCodeinfo(codeinfo);
			qyConvertPrizeHistoryResponseParam.setMessgae(message);
			return qyConvertPrizeHistoryResponseParam;
		}
		String getUserCode = qyConvertPrizeHistoryRequestParam.getUserCode();
		if (CommonUtil.isEmpty(getUserCode)) {
			codeinfo = "3";
			message = "getUserCode为空！";
			qyConvertPrizeHistoryResponseParam.setCodeinfo(codeinfo);
			qyConvertPrizeHistoryResponseParam.setMessgae(message);
			return qyConvertPrizeHistoryResponseParam;
		}
		try {

			qyConvertPrizeHistoryResponseParam = weiXinService.qyConvertPrizeHistory(qyConvertPrizeHistoryRequestParam);
		} catch (Exception e) {
			e.printStackTrace();
			codeinfo = "1";
			message = "农贷系统内部错误！";
			qyConvertPrizeHistoryResponseParam.setCodeinfo(codeinfo);
			qyConvertPrizeHistoryResponseParam.setMessgae(message);
		}
		log.info("**************WeiXinWS.qyConvertPrizeHistory(qyConvertPrizeHistoryResponseParam) responseParam******************");
		log.info(JsonUtil.getJackson(qyConvertPrizeHistoryResponseParam));
		return qyConvertPrizeHistoryResponseParam;
	}

	/** 以上爲活動類接口end **/
	@Override
	public WebServiceMessage saveComplaintProposal(ComplaintProposalRequestParam complaintProposalRequestParam) {
		// TODO Auto-generated method stub
		WebServiceMessage webServiceMessage = new WebServiceMessage();
		webServiceMessage = complaintProposalService.saveComplaintProposal(complaintProposalRequestParam);
		return webServiceMessage;
	}

	@Override
	public QyCanGetPrizeListResponseParam qyCanGetFirstPrizeList(QyCanGetPrizeListRequestParam qyCanGetPrizeListRequestParam) {
		log.info("**************WeiXinWS.qyCanGetPrizeList(qyCanGetPrizeListRequestParam) requestParam******************");
		log.info(JsonUtil.getJackson(qyCanGetPrizeListRequestParam));
		QyCanGetPrizeListResponseParam qyCanGetPrizeListResponseParam = new QyCanGetPrizeListResponseParam();
		String codeinfo = "";
		String message = "";
		if (qyCanGetPrizeListRequestParam == null) {
			codeinfo = "2";
			message = "qyCanGetPrizeListRequestParam为空！";
			qyCanGetPrizeListResponseParam.setCodeinfo(codeinfo);
			qyCanGetPrizeListResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.qyCanGetPrizeList(qyCanGetPrizeListResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(qyCanGetPrizeListResponseParam));
			return qyCanGetPrizeListResponseParam;
		}
		String getUserCode = qyCanGetPrizeListRequestParam.getUserCode();
		if (CommonUtil.isEmpty(getUserCode)) {
			codeinfo = "3";
			message = "getUserCode为空！";
			qyCanGetPrizeListResponseParam.setCodeinfo(codeinfo);
			qyCanGetPrizeListResponseParam.setMessgae(message);
			log.info("**************WeiXinWS.qyCanGetPrizeList(qyCanGetPrizeListResponseParam) responseParam******************");
			log.info(JsonUtil.getJackson(qyCanGetPrizeListResponseParam));
			return qyCanGetPrizeListResponseParam;
		}
		try {

			qyCanGetPrizeListResponseParam = weiXinService.qyCanGetFirstPrizeList(qyCanGetPrizeListRequestParam);
		} catch (Exception e) {
			e.printStackTrace();
			codeinfo = "1";
			message = "农贷系统内部错误！";
			qyCanGetPrizeListResponseParam.setCodeinfo(codeinfo);
			qyCanGetPrizeListResponseParam.setMessgae(message);
		}
		log.info("**************WeiXinWS.qyCanGetPrizeList(qyCanGetPrizeListResponseParam) responseParam******************");
		log.info(JsonUtil.getJackson(qyCanGetPrizeListResponseParam));
		return qyCanGetPrizeListResponseParam;
	}

//	@Override
//	public QyChangedGetPrizeUserResponseParam qyChangedGetPrizeUser(QyChangedGetPrizeUserRequestParam qyChangedGetPrizeUserRequestParam) {
//		QyChangedGetPrizeUserResponseParam changedGetPrizeUserResponseParam=new QyChangedGetPrizeUserResponseParam();
//		changedGetPrizeUserResponseParam=weiXinService.qyChangedGetPrizeUser(qyChangedGetPrizeUserRequestParam);
//		return changedGetPrizeUserResponseParam;
//	}
}
