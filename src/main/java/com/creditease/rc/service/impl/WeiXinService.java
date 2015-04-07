package com.creditease.rc.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IWeiXinDao;
import com.creditease.rc.domain.CodeTable;
import com.creditease.rc.domain.CustomerBasicInfo;
import com.creditease.rc.domain.CustomerConsultPool;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.NationalStandardCode;
import com.creditease.rc.domain.WxAnswer;
import com.creditease.rc.domain.WxAnswersRecord;
import com.creditease.rc.domain.WxQuestion;
import com.creditease.rc.domain.WxUser;
import com.creditease.rc.domain.WxupriRecord;
import com.creditease.rc.service.IDataDictionaryService;
import com.creditease.rc.service.INationalStandardCodeService;
import com.creditease.rc.service.ISaleInquireService;
import com.creditease.rc.service.IWebsiteService;
import com.creditease.rc.service.IWeiXinService;
import com.creditease.rc.service.IWxAnswerService;
import com.creditease.rc.service.IWxAnswersRecordService;
import com.creditease.rc.service.IWxPrizeService;
import com.creditease.rc.service.IWxQuestionService;
import com.creditease.rc.service.IWxUserService;
import com.creditease.rc.service.IWxupriRecordService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.vo.AddScoreRequestParam;
import com.creditease.rc.vo.AddScoreResponseParam;
import com.creditease.rc.vo.Answer;
import com.creditease.rc.vo.AnswerCompleteRequestParam;
import com.creditease.rc.vo.AnswerCompleteResponseParam;
import com.creditease.rc.vo.ConsultPoolRequestParam;
import com.creditease.rc.vo.ConvertPrizeHistory;
import com.creditease.rc.vo.ConvertPrizeRequestParam;
import com.creditease.rc.vo.ConvertPrizeResponseParam;
import com.creditease.rc.vo.DataDictionaryResponseParam;
import com.creditease.rc.vo.DataDictionaryVo;
import com.creditease.rc.vo.GainQuestionsRequestParam;
import com.creditease.rc.vo.GainQuestionsResponseParam;
import com.creditease.rc.vo.PCAResponseParam;
import com.creditease.rc.vo.Prize;
import com.creditease.rc.vo.PrizeHasFlag;
import com.creditease.rc.vo.QandA;
import com.creditease.rc.vo.Question;
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
import com.creditease.rc.vo.WebsiteData;
import com.creditease.rc.vo.WebsiteInfoRequestParam;
import com.creditease.rc.vo.WebsiteInfoResponseParam;

@Service
public class WeiXinService implements IWeiXinService {
	@Resource
	private IWeiXinDao weiXinDao;
	@Resource
	private IDataDictionaryService dataDictionaryService;
	@Resource
	private ISaleInquireService saleInquireService;
	@Resource
	private INationalStandardCodeService NationalStandardCodeService;

	@Resource
	private IWxQuestionService wxQuestionService;
	@Resource
	private IWxAnswerService wxAnswerService;
	@Resource
	private IWxUserService wxUserService;
	@Resource
	private IWxAnswersRecordService wxAnswersRecordService;
	@Resource
	private IWebsiteService websiteService;
	@Resource
	private IWxPrizeService wxPrizeService;
	@Resource
	private IWxupriRecordService wxupriRecordService;

	@Override
	public DataDictionaryResponseParam qyBorrowUse() {

		return this.getDataDicBySection("marketConsultBorrowing");
	}

	@Override
	public DataDictionaryResponseParam qyInfoSource() {
		return this.getDataDicBySection("marketConsultInfoSource");
	}

	@Override
	public PCAResponseParam qyProvinceCityArea(String parentId) {
		PCAResponseParam param = new PCAResponseParam();
		param.setCodeinfo("0");
		param.setMessgae("查询失败");
		Map map = new HashMap();
		if (StringUtils.isNotEmpty(parentId)) {
			Pattern pattern = Pattern.compile("^\\d*$");
			Matcher matcher = pattern.matcher(parentId);
			if (matcher.matches()) {
				map.put("filter", "ca");
				map.put("parentId", parentId);
			} else {
				param.setCodeinfo("3");
				param.setMessgae("请输入正确的国标码");
			}
		} else {
			map.put("filter", "p");
		}

		if (!map.isEmpty()) {
			List<NationalStandardCode> codeList = weiXinDao.selectByParentId(map);
			if (!codeList.isEmpty()) {
				param.setCodeList(codeList);
				param.setCodeinfo("1");
				param.setMessgae("查询成功");
			} else {
				param.setCodeinfo("2");
				param.setMessgae("当前地无营业网点");
			}
		}
		return param;
	}

	@Override
	public WebServiceMessage saveReservation(ConsultPoolRequestParam param) {
		WebServiceMessage message = new WebServiceMessage();
		boolean retFlag = true;
		// 保存咨询记录
		if (param != null) {
			CustomerConsultPool pool = param.getPool();
			// 验证所有属性非空、验证省市区、验证联系方式
			Pattern pattern = Pattern.compile("^[1][3-8]\\d{9}$|^((\\d{4}|\\d{3})-(\\d{7,8}))$");
			Matcher matcher = pattern.matcher(param.getPool().getMobilePhone());
			if (StringUtils.isNotEmpty(pool.getProvince()) && StringUtils.isNotEmpty(pool.getCity()) && StringUtils.isNotEmpty(pool.getArea()) && StringUtils.isNotEmpty(pool.getCustomerName())
					&& StringUtils.isNotEmpty(pool.getMobilePhone()) && StringUtils.isNotEmpty(pool.getConSex()) && StringUtils.isNotEmpty(pool.getConsultAmount())
					&& StringUtils.isNotEmpty(pool.getBorrowing()) && StringUtils.isNotEmpty(pool.getChannel()) && (pool.getConSex().equals("0") || pool.getConSex().equals("1")) && matcher.matches()
					&& isPCAValid(pool)) {

				pool.setMarketConsultRegistType("0");
				pool.setRegistDate(new Date());
				pool.setConsultWay("1");
				pool.setMarketConsultState("0");
				Long flag = saleInquireService.saveInquire(pool);
				if (flag < 0) {
					retFlag = false;
				}
			} else {
				retFlag = false;
			}
		} else {
			retFlag = false;
		}
		if (retFlag) {
			message.setCodeinfo("1");
			message.setMessgae("保存成功");
		} else {
			message.setCodeinfo("0");
			message.setMessgae("保存失败");
		}
		return message;
	}

	private DataDictionaryResponseParam getDataDicBySection(String section) {
		DataDictionaryResponseParam param = new DataDictionaryResponseParam();
		param.setCodeinfo("0");
		param.setMessgae("查询失败");
		List<CodeTable> tables = dataDictionaryService.getSpecifiedDic("", section);
		DataDictionaryVo dictionaryVo;
		if (!tables.isEmpty()) {
			List<DataDictionaryVo> voList = new ArrayList<DataDictionaryVo>();
			for (CodeTable table : tables) {
				dictionaryVo = new DataDictionaryVo();
				dictionaryVo.setCodeKey(table.getCodeKey());
				dictionaryVo.setCodeValue(table.getCodeVlue());
				voList.add(dictionaryVo);
			}
			param.setVoList(voList);
			param.setCodeinfo("1");
			param.setMessgae("查询成功");
		}
		return param;
	}

	// 根据省市区查询营业部信息
	public WebsiteInfoResponseParam qyWebsiteInfoParam(WebsiteInfoRequestParam websiteInfoRequestParam) {
		WebsiteInfoResponseParam param = new WebsiteInfoResponseParam();
		param.setCodeinfo("0");
		param.setMessgae("查询失败！");
		// 调用内部查询方法
		try {
			WebsiteData websiteData = websiteService.qyWebsiteByMap(websiteInfoRequestParam);
			param.setWebsiteData(websiteData);
		} catch (Exception e) {
			param.setCodeinfo("1");
			param.setMessgae("查询失败！");
		}
		return param;
	}

	// 判断省、市、区三列是否匹配，以省为准来判断匹配性
	private boolean isPCAValid(CustomerConsultPool pool) {

		boolean flag = true;
		// 获取省市区
		NationalStandardCode standardCodeP = NationalStandardCodeService.selectByCode(Integer.valueOf(pool.getProvince()));
		NationalStandardCode standardCodeC = NationalStandardCodeService.selectByCode(Integer.valueOf(pool.getCity()));
		NationalStandardCode standardCodeA = NationalStandardCodeService.selectByCode(Integer.valueOf(pool.getArea()));
		// 判断省-市对应
		List<NationalStandardCode> c1;
		List<NationalStandardCode> c2;
		if (null != standardCodeP && null != standardCodeC && null != standardCodeA) {
			c1 = NationalStandardCodeService.selectCity(standardCodeC.getCityName(), standardCodeP.getCityCode());
			if (null != c1 && !c1.isEmpty()) {
				// 判断市-区对应
				c2 = NationalStandardCodeService.selectCity(standardCodeA.getCityName(), c1.get(0).getCityCode());
				if (null != c2 && !c2.isEmpty()) {
					pool.setResidenceAddress(standardCodeP.getCityName() + "-" + standardCodeC.getCityName() + "-" + standardCodeA.getCityName());
				}
			} else {
				// 省-市不对应
				flag = false;
			}
		} else {
			flag = false;
		}
		return flag;
	}

	@Override
	public UserLoginResponseParam userLogin(UserLoginRequestParam userLoginRequestParam) {
		// TODO Auto-generated method stub
		UserLoginResponseParam loginResponseParam = new UserLoginResponseParam();
		Message message = new Message();
		List<Question> questions = new ArrayList<Question>();
		String getLoginType = userLoginRequestParam.getLoginType();
		String getName = userLoginRequestParam.getName();
		String getPhone = userLoginRequestParam.getPhone();
		String getUserCode = userLoginRequestParam.getUserCode();
		String getIdentityCardSub = userLoginRequestParam.getIdentityCardSub();
		if (getLoginType.equals("0")) {
			// 如果是vip则验证身份证号和姓名
			Map<String, String> queryMap = new HashMap<String, String>();
			queryMap.put("name", getName.substring(0, 1));
			queryMap.put("identityCardSub", getIdentityCardSub);
			message = this.checkWeiXinVIP(queryMap);
			if (!message.isSuccess()) {
				loginResponseParam.setCodeinfo(message.getCode());
				loginResponseParam.setMessgae(message.getMsg());
				return loginResponseParam;
			} else {
				getPhone = message.getCode();
			}
		}
		// 判断今天是否答过题
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("userCode", getUserCode);
		message = this.checkAnswered(queryMap);
		if (!message.isSuccess()) {
			loginResponseParam.setCodeinfo(message.getCode());
			loginResponseParam.setMessgae(message.getMsg());
			return loginResponseParam;
		}
		// 根据类型出题
		questions = this.setQuestions(getLoginType);

		// 讲登陆信息保存到数据库中
		WxUser wxUser = new WxUser();
		wxUser.setLoginDate(new Timestamp(new Date().getTime()));
		wxUser.setIdentityCardSub(getIdentityCardSub);
		wxUser.setName(getName);
		wxUser.setPhone(getPhone);
		wxUser.setUserCode(getUserCode);
		message = wxUserService.saveWxUserByUserCode(wxUser);
		loginResponseParam.setCodeinfo("0");
		loginResponseParam.setMessgae("登陆成功！");
		loginResponseParam.setQuestions(questions);
		return loginResponseParam;
	}

	private Message checkWeiXinVIP(Map<String, String> queryMap) {
		Message message = new Message();
		List<CustomerBasicInfo> customerIdList = weiXinDao.checkWeiXinVIP(queryMap);
		if (CommonUtil.isNotEmpty(customerIdList)) {
			message.setSuccess(true);
			message.setCode(customerIdList.get(0).getMobilephone());
		} else {
			message.setSuccess(false);
			message.setMsg("非宜信客户！");
			message.setCode("9");

		}
		return message;
	}

	private Message checkAnswered(Map<String, String> queryMap) {
		Message message = new Message();
		List<Long> wxUserIds = weiXinDao.checkAnswered(queryMap);
		if (CommonUtil.isEmpty(wxUserIds)) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
			message.setMsg("今日已答过题！");
			message.setCode("10");
		}
		return message;
	}

	private List<Question> setQuestions(String getLoginType) {
		List<Question> questions = new ArrayList<Question>();
		int y = 0;
		int r = 0;
		int n = 0;

		if (getLoginType.equals("0")) {
			r = 2;
			n = 1;
			y = 3;
		} else if (getLoginType.equals("1")) {
			r = 3;
			n = 1;
			y = 1;
		} else if (getLoginType.equals("2")) {
			r = 3;
			n = 3;
			y = 1;
		}
		Map<Long, WxQuestion> wxQuestionMap = wxQuestionService.queryAllToMap();
		Map<Long, List<WxAnswer>> WxAnswerMap = wxAnswerService.queryAllToMap();
		Map<String, List<Long>> qLevelMap = getQLevelMap(wxQuestionMap);
		List<Long> wxQuestionIds = this.getQuestions(r, n, y, qLevelMap);
		questions = constructQuestions(wxQuestionIds, wxQuestionMap, WxAnswerMap);
		return questions;
	}

	private List<Question> constructQuestions(List<Long> wxQuestionIds, Map<Long, WxQuestion> wxQuestionMap, Map<Long, List<WxAnswer>> wxAnswerMap) {
		List<Question> questions = new ArrayList<Question>();
		for (Long wxQuestionId : wxQuestionIds) {
			Question question = new Question();
			WxQuestion wxQuestion = wxQuestionMap.get(wxQuestionId);
			String getQuestion = wxQuestion.getQuestion();
			String getType = wxQuestion.getType();
			Long getWxQuestionId = wxQuestion.getWxQuestionId();
			question.setWxQuestionId(getWxQuestionId);
			question.setType(getType);
			question.setQuestion(getQuestion);
			List<Answer> answers = new ArrayList<Answer>();
			List<WxAnswer> wxAnswers = wxAnswerMap.get(wxQuestionId);
			for (WxAnswer wxAnswer : wxAnswers) {
				String getAnswer = wxAnswer.getAnswer();
				String getTrueFlag = wxAnswer.getTrueFlag();
				Long getWxAnswerId = wxAnswer.getWxAnswerId();
				Long getWxQuestionIda = wxAnswer.getWxQuestionId();
				Answer answer = new Answer();
				answer.setAnswer(getAnswer);
				answer.setTrueFlag(getTrueFlag);
				answer.setWxAnswerId(getWxAnswerId);
				answer.setWxQuestionId(getWxQuestionIda);
				answers.add(answer);
			}
			question.setAnswers(answers);
			questions.add(question);
		}
		return questions;
	}

	private List<Long> getQuestions(int r, int n, int y, Map<String, List<Long>> qLevelMap) {
		Random ran = new Random();
		List<Long> wxQuestionIds = new ArrayList<Long>();
		// 1找容易题型
		Set<Long> rset = new HashSet<Long>();
		List<Long> rIds = qLevelMap.get("R");
		int rIdMax = rIds.size();
		while (rset.size() < r) {
			int i = ran.nextInt(rIdMax);
			Long wxQuestionId = rIds.get(i);
			rset.add(wxQuestionId);
		}
		// 2找困难题型
		Set<Long> nset = new HashSet<Long>();
		List<Long> nIds = qLevelMap.get("N");
		int nIdMax = nIds.size();
		while (nset.size() < n) {
			int i = ran.nextInt(nIdMax);
			Long wxQuestionId = nIds.get(i);
			nset.add(wxQuestionId);
		}
		// 2找宜信题型
		Set<Long> yset = new HashSet<Long>();
		List<Long> yIds = qLevelMap.get("Y");
		int yIdMax = yIds.size();
		while (yset.size() < y) {
			int i = ran.nextInt(yIdMax);
			Long wxQuestionId = yIds.get(i);
			yset.add(wxQuestionId);
		}
		// 将题放入试卷
		wxQuestionIds.addAll(rset);
		wxQuestionIds.addAll(nset);
		wxQuestionIds.addAll(yset);
		return wxQuestionIds;
	}

	private Map<String, List<Long>> getQLevelMap(Map<Long, WxQuestion> wxQuestionMap) {
		Set<Entry<Long, WxQuestion>> set = wxQuestionMap.entrySet();
		Map<String, List<Long>> qLevelMap = new HashMap<String, List<Long>>();
		for (Entry<Long, WxQuestion> entry : set) {
			WxQuestion wxQuestion = entry.getValue();
			Long getWxQuestionId = wxQuestion.getWxQuestionId();
			String getType = wxQuestion.getType();
			if (CommonUtil.isNotEmpty(getType)) {
				List<Long> longs = qLevelMap.get(getType);
				if (CommonUtil.isEmpty(longs)) {
					longs = new ArrayList<Long>();
				}
				longs.add(getWxQuestionId);
				qLevelMap.put(getType, longs);
			}
		}
		return qLevelMap;
	}

	@Override
	public GainQuestionsResponseParam gainQuestions(GainQuestionsRequestParam gainQuestionsRequestParam) {
		GainQuestionsResponseParam questionsResponseParam = new GainQuestionsResponseParam();

		Message message = new Message();
		String getUserCode = gainQuestionsRequestParam.getUserCode();
		// 判断今天是否答过题
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("userCode", getUserCode);
		WxUser wxUser = wxUserService.queryWxUserByUserCode(getUserCode);
		if (wxUser == null) {
			questionsResponseParam.setCodeinfo("4");
			questionsResponseParam.setMessgae("未找到该客户！");
			return questionsResponseParam;
		}
		message = this.checkAnswered(queryMap);
		if (!message.isSuccess()) {
			questionsResponseParam.setCodeinfo("5");
			questionsResponseParam.setMessgae(message.getMsg());
			return questionsResponseParam;
		}

		String getLevelType = gainQuestionsRequestParam.getLevelType();
		List<Question> questions = setQuestions(getLevelType);
		questionsResponseParam.setCodeinfo("0");
		questionsResponseParam.setMessgae("获得题目成功！");
		questionsResponseParam.setQuestions(questions);
		return questionsResponseParam;
	}

	@Override
	public AnswerCompleteResponseParam answerComplete(AnswerCompleteRequestParam answerCompleteRequestParam) {
		AnswerCompleteResponseParam answerCompleteResponseParam = new AnswerCompleteResponseParam();
		Message message = new Message();
		int score = 0;
		int aggregateScore = 0;
		Timestamp timestamp = new Timestamp(new Date().getTime());// 答题 日期
		List<WxAnswersRecord> answersRecords = new ArrayList<WxAnswersRecord>();
		String getUserCode = answerCompleteRequestParam.getUserCode();
		String getLoginType = answerCompleteRequestParam.getLoginType();
		List<QandA> getQandAs = answerCompleteRequestParam.getQandAs();
		WxUser wxUser = wxUserService.queryWxUserByUserCode(getUserCode);
		if (wxUser == null) {
			answerCompleteResponseParam.setCodeinfo("7");
			answerCompleteResponseParam.setMessgae("未找到该客户！");
			return answerCompleteResponseParam;
		}
		// 判断今天是否答过题
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("userCode", getUserCode);
		message = this.checkAnswered(queryMap);
		if (!message.isSuccess()) {
			answerCompleteResponseParam.setCodeinfo("8");
			answerCompleteResponseParam.setMessgae(message.getMsg());
			return answerCompleteResponseParam;
		}
		Long getWxUserId = wxUser.getWxUserId();
		Long getScore = wxUser.getScore();
		Long getAggregateScore = wxUser.getAggregateScore();
		if (getLoginType.equals("0")) {
			// VIP客户：答对一题+2分(Y)，答错一题0分
			for (QandA qandA : getQandAs) {
				Long getQuestionId = qandA.getQuestionId();
				String getTrueFlag = qandA.getTrueFlag();
				String getType = qandA.getType();

				WxAnswersRecord wxAnswersRecord = new WxAnswersRecord();
				wxAnswersRecord.setWxUserId(getWxUserId);
				wxAnswersRecord.setWxQuestionId(getQuestionId);
				wxAnswersRecord.setAnswerDate(timestamp);
				if (CommonUtil.isNotEmpty(getTrueFlag) && "0".equals(getTrueFlag)) {
					if (getType.equals("Y")) {
						score = score + 2;
						aggregateScore = aggregateScore + 2;
						wxAnswersRecord.setScore(1l);
						wxAnswersRecord.setVipScore(1l);
						wxAnswersRecord.setIsCorrect("0");
					} else {
						score = score + 1;
						aggregateScore = aggregateScore + 1;
						wxAnswersRecord.setScore(1l);
						wxAnswersRecord.setVipScore(0l);
						wxAnswersRecord.setIsCorrect("0");
					}

				} else {
					wxAnswersRecord.setScore(0l);
					wxAnswersRecord.setVipScore(0l);
					wxAnswersRecord.setIsCorrect("1");
				}
				answersRecords.add(wxAnswersRecord);
			}
		} else {
			// 普通客户：答对一题+1分，答错一题0分
			for (QandA qandA : getQandAs) {
				Long getQuestionId = qandA.getQuestionId();
				String getTrueFlag = qandA.getTrueFlag();

				WxAnswersRecord wxAnswersRecord = new WxAnswersRecord();
				wxAnswersRecord.setWxUserId(getWxUserId);
				wxAnswersRecord.setWxQuestionId(getQuestionId);
				wxAnswersRecord.setAnswerDate(timestamp);
				if (getTrueFlag.equals("0")) {
					score = score + 1;
					aggregateScore = aggregateScore + 1;
					wxAnswersRecord.setScore(1l);
					wxAnswersRecord.setVipScore(0l);
					wxAnswersRecord.setIsCorrect("0");
				} else {
					wxAnswersRecord.setScore(0l);
					wxAnswersRecord.setVipScore(0l);
					wxAnswersRecord.setIsCorrect("1");
				}
				answersRecords.add(wxAnswersRecord);
			}
		}
		BigDecimal sumScore = new BigDecimal(score).add(new BigDecimal(getScore == null ? 0l : getScore));
		BigDecimal sumAggregateScore = new BigDecimal(aggregateScore).add(new BigDecimal(getAggregateScore == null ? 0l : getAggregateScore));
		Long endScore = sumScore.longValue();
		Long endAggregateScore = sumAggregateScore.longValue();
		wxUser.setAnswerDate(timestamp);
		wxUser.setScore(endScore);
		wxUser.setAggregateScore(endAggregateScore);
		wxUserService.saveWxUserByUserCode(wxUser);
		wxAnswersRecordService.batchInsertAnswersRecords(answersRecords);
		answerCompleteResponseParam.setCodeinfo("0");
		answerCompleteResponseParam.setMessgae("答题成功！");
		return answerCompleteResponseParam;
	}

	@Override
	public QyScoreResponseParam qyScore(QyScoreRequestParam qyScoreRequestParam) {
		QyScoreResponseParam qyScoreResponseParam = new QyScoreResponseParam();
		String userCode = qyScoreRequestParam.getUserCode();
		String answerDate = qyScoreRequestParam.getAnswerDate();
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("userCode", userCode);
		queryMap.put("answerDate", answerDate);
		
		qyScoreResponseParam = this.queryScore(queryMap);
		
		//计算答题的正确率
		BigDecimal num1=new BigDecimal(qyScoreResponseParam.getaRight());
		BigDecimal num2=new BigDecimal(qyScoreResponseParam.getaComplete());
		BigDecimal num3=new BigDecimal("100");
		qyScoreResponseParam.setRightRate((num1.divide(num2,4,RoundingMode.HALF_UP)).multiply(num3));
		System.out.println(qyScoreResponseParam.getRightRate());
		if (qyScoreResponseParam == null) {
			qyScoreResponseParam = new QyScoreResponseParam();
			qyScoreResponseParam.setCodeinfo("5");
			qyScoreResponseParam.setMessgae("无查询结果！");
			return qyScoreResponseParam;
		}
		qyScoreResponseParam.setCodeinfo("0");
		qyScoreResponseParam.setMessgae("查询成功！");
		return qyScoreResponseParam;
	}

	private QyScoreResponseParam queryScore(Map<String, String> queryMap) {

		return weiXinDao.queryScore(queryMap);
	}

	@Override
	public QyHistoryQuestionsWrongResponseParam qyHistoryQuestionsWrong(QyHistoryQuestionsWrongRequestParam qyHistoryQuestionsWrongRequestParam) {
		QyHistoryQuestionsWrongResponseParam qyHistoryQuestionsWrongResponseParam = new QyHistoryQuestionsWrongResponseParam();
		String getUserCode = qyHistoryQuestionsWrongRequestParam.getUserCode();
		WxUser wxUser = wxUserService.queryWxUserByUserCode(getUserCode);
		if (wxUser == null) {
			qyHistoryQuestionsWrongResponseParam.setCodeinfo("4");
			qyHistoryQuestionsWrongResponseParam.setMessgae("未找到该客户！");
			return qyHistoryQuestionsWrongResponseParam;
		}
		List<QandA> qandAs = this.queryHistoryQuestionsWrong(getUserCode);
		qyHistoryQuestionsWrongResponseParam.setQandAs(qandAs);
		qyHistoryQuestionsWrongResponseParam.setCodeinfo("0");
		qyHistoryQuestionsWrongResponseParam.setMessgae("查询成功！");
		return qyHistoryQuestionsWrongResponseParam;
	}

	private List<QandA> queryHistoryQuestionsWrong(String getUserCode) {
		// TODO Auto-generated method stub
		return weiXinDao.queryHistoryQuestionsWrong(getUserCode);
	}

	@Override
	public AddScoreResponseParam addScore(AddScoreRequestParam addScoreRequestParam) {
		AddScoreResponseParam addScoreResponseParam = new AddScoreResponseParam();
		String getUserCode = addScoreRequestParam.getUserCode();
		int addScore = addScoreRequestParam.getScore();
		WxUser wxUser = wxUserService.queryWxUserByUserCode(getUserCode);
		if (wxUser == null) {
			addScoreResponseParam.setCodeinfo("4");
			addScoreResponseParam.setMessgae("未找到该客户！");
			return addScoreResponseParam;
		}
		Long getScore = wxUser.getScore();
		Long getAggregateScore = wxUser.getAggregateScore();
		BigDecimal score = new BigDecimal(getScore == null ? 0l : getScore).add(new BigDecimal(addScore));
		BigDecimal aggregate = new BigDecimal(getAggregateScore == null ? 0l : getAggregateScore).add(new BigDecimal(addScore));
		wxUser.setScore(score.longValue());
		wxUser.setAggregateScore(aggregate.longValue());
		wxUserService.saveWxUserByUserCode(wxUser);
		addScoreResponseParam.setCodeinfo("0");
		addScoreResponseParam.setMessgae("加分成功！");
		return addScoreResponseParam;
	}

	@Override
	public QyPrizeListResponseParam qyPrizeList(QyPrizeListRequestParam qyPrizeListRequestParam) {
		QyPrizeListResponseParam qyPrizeListResponseParam = new QyPrizeListResponseParam();
		Map<String, String> queryMap = new HashMap<String, String>();
		List<Prize> prizes = weiXinDao.queryPrizeList(queryMap);
		qyPrizeListResponseParam.setCodeinfo("0");
		qyPrizeListResponseParam.setMessgae("查询成功！");
		qyPrizeListResponseParam.setPrizes(prizes);
		return qyPrizeListResponseParam;
	}

	@Override
	public QyCanGetPrizeListResponseParam qyCanGetPrizeList(QyCanGetPrizeListRequestParam qyCanGetPrizeListRequestParam) {
		QyCanGetPrizeListResponseParam canGetPrizeListResponseParam = new QyCanGetPrizeListResponseParam();
		String getUserCode = qyCanGetPrizeListRequestParam.getUserCode();
		WxUser wxUser = wxUserService.queryWxUserByUserCode(getUserCode);
		if (wxUser == null) {
			canGetPrizeListResponseParam.setCodeinfo("4");
			canGetPrizeListResponseParam.setMessgae("未找到该客户！");
			return canGetPrizeListResponseParam;
		}
		List<PrizeHasFlag> prizeHasFlags = weiXinDao.qyCanGetPrizeList(getUserCode);
		canGetPrizeListResponseParam.setCodeinfo("0");
		canGetPrizeListResponseParam.setMessgae("查询成功！");
		canGetPrizeListResponseParam.setScore(wxUser.getScore());
		canGetPrizeListResponseParam.setPhone(wxUser.getPhone());
		canGetPrizeListResponseParam.setPrizeHasFlags(prizeHasFlags);
		//判断是否兑换过金靴奖
		WxUser wxUser2=wxUserService.qyChangedGetPrizeUser(getUserCode);
		if(wxUser2!=null){
			canGetPrizeListResponseParam.getPrizeHasFlags().get(0).setConvertFlag("0");
			canGetPrizeListResponseParam.getPrizeHasFlags().get(0).setFirstFlag("0");
		}
		return canGetPrizeListResponseParam;
	}

	@Override
	public ConvertPrizeResponseParam convertPrize(ConvertPrizeRequestParam convertPrizeRequestParam) {
		ConvertPrizeResponseParam convertPrizeResponseParam = new ConvertPrizeResponseParam();
		Message message = new Message();
		Date recpriDate = new Date();
		String getUserCode = convertPrizeRequestParam.getUserCode();
		Long getWxPrizeId = convertPrizeRequestParam.getWxPrizeId();
		String getProvincehome = convertPrizeRequestParam.getProvincehome();
		String getCityhome = convertPrizeRequestParam.getCityhome();
		String getCountyhome = convertPrizeRequestParam.getCountyhome();
		String getBranchofficeName = convertPrizeRequestParam.getBranchofficeName();
		String getBranchofficeId = convertPrizeRequestParam.getBranchofficeId();
		String getRecpriPhone = convertPrizeRequestParam.getRecpriPhone();
		WxUser wxUser = wxUserService.queryWxUserByUserCode(getUserCode);
		if (wxUser == null) {
			convertPrizeResponseParam.setCodeinfo("9");
			convertPrizeResponseParam.setMessgae("未找到该客户！");
			return convertPrizeResponseParam;
		}
		
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("wxPrizeId", getWxPrizeId.toString());
		queryMap.put("validFlag", "0");
		List<Prize> prizes = weiXinDao.queryPrizeList(queryMap);
		if (CommonUtil.isEmpty(prizes)) {
			convertPrizeResponseParam.setCodeinfo("10");
			convertPrizeResponseParam.setMessgae("未找到对应的奖品！");
			return convertPrizeResponseParam;
		}
		Prize prize = prizes.get(0);
		Long getWxUserId = wxUser.getWxUserId();
		String getName = wxUser.getName();
		String getPhone = wxUser.getPhone();
		Long getScore = wxUser.getScore();
		Long getUsedScore = wxUser.getUsedScore();
		String pictureAddress = prize.getPictureAddress();
		Long getPrizeScore = prize.getPrizeScore();

		if (CommonUtil.isEmpty(getRecpriPhone)) {
			getRecpriPhone = getPhone;
		}

		BigDecimal score = new BigDecimal(getScore == null ? 0l : getScore).add(new BigDecimal(-(getPrizeScore == null ? 0l : getPrizeScore)));
		BigDecimal userdScore = new BigDecimal(getUsedScore == null ? 0l : getUsedScore).add(new BigDecimal(getPrizeScore == null ? 0l : getPrizeScore));
		if (score.compareTo(new BigDecimal(0)) < 0) {
			convertPrizeResponseParam.setCodeinfo("11");
			convertPrizeResponseParam.setMessgae("积分不够！");
			return convertPrizeResponseParam;
		}
		wxUser.setScore(score.longValue());
		wxUser.setUsedScore(userdScore.longValue());
		WxupriRecord wxupriRecord = new WxupriRecord();

		String upriRecordCode = encrypt(getUserCode, getWxPrizeId, recpriDate);

		wxupriRecord.setWxUserId(getWxUserId);
		wxupriRecord.setWxPrizeId(getWxPrizeId);
		wxupriRecord.setUpriRecordCode(upriRecordCode);
		if(StringUtils.isEmpty(getProvincehome)||getProvincehome.equals("null")){
			wxupriRecord.setProvincehome("");
		}else{
		wxupriRecord.setProvincehome(getProvincehome);
		}
		if(StringUtils.isEmpty(getCityhome)||getCityhome.equals("null")){
			wxupriRecord.setCityhome("");
		}else{
		wxupriRecord.setCityhome(getCityhome);
		}
		if(StringUtils.isEmpty(getCountyhome) ||getCountyhome.equals("null")){
			wxupriRecord.setCountyhome("");
		}else{
		wxupriRecord.setCountyhome(getCountyhome);
		}
		if(StringUtils.isEmpty(getBranchofficeName)||getBranchofficeName.equals("null")){
			wxupriRecord.setBranchofficeName("");
		}else{
		wxupriRecord.setBranchofficeName(getBranchofficeName);
		}
		if(StringUtils.isEmpty(getBranchofficeId)||getBranchofficeId.equals("null")){
			wxupriRecord.setBranchofficeId("");
		}else{
			wxupriRecord.setBranchofficeId(getBranchofficeId);
		}
		wxupriRecord.setIsReceive("0");
		wxupriRecord.setRecpriName(getName);
		wxupriRecord.setRecpriPhone(getRecpriPhone);
		wxupriRecord.setRecpriDate(new Timestamp(recpriDate.getTime()));
		//判断该用户兑换的什么奖项
		if((getWxPrizeId.toString()).equals("248217")){
		//判断是否兑换过金靴奖
		WxUser wxUser2=wxUserService.qyChangedGetPrizeUser(getUserCode);
		if(wxUser2!=null){
			convertPrizeResponseParam.setCodeinfo("15");
			convertPrizeResponseParam.setMessgae("您已经兑换过金靴奖了！");
			return convertPrizeResponseParam;
		}
		}
		wxupriRecordService.insertWxupriRecord(wxupriRecord);
		wxUserService.saveWxUserByUserCode(wxUser);
		
		Long getWxUpriRecordId = wxupriRecord.getWxUpriRecordId();
		queryMap.clear();
		queryMap.put("wxUpriRecordId", getWxUpriRecordId.toString());
		List<ConvertPrizeHistory> convertPrizeHistories = weiXinDao.queryConvertPrizeHistoryList(queryMap);
		ConvertPrizeHistory convertPrizeHistory = convertPrizeHistories.get(0);
		convertPrizeHistory.getUpriRecordCode();
		convertPrizeHistory.getStatus();
		convertPrizeHistory.getLocation();
		convertPrizeHistory.getPictureAddress();
		convertPrizeHistory.getRecpriDate();
		convertPrizeResponseParam.setCodeinfo("0");
		convertPrizeResponseParam.setMessgae("兑奖成功！");
		convertPrizeResponseParam.setUpriRecordCode(convertPrizeHistory.getUpriRecordCode());
		convertPrizeResponseParam.setStatus(convertPrizeHistory.getStatus());
		convertPrizeResponseParam.setLocation(convertPrizeHistory.getLocation());
		convertPrizeResponseParam.setPictureAddress(convertPrizeHistory.getPictureAddress());
		convertPrizeResponseParam.setRecpriDate(convertPrizeHistory.getRecpriDate());
		
		return convertPrizeResponseParam;
	}

	/**
	 * 传入信贷系统销售编号加密properties为signInfo两次
	 * 
	 * @author 郝强
	 * @param paramDate
	 *            日期
	 * @return signInfoEncrypt 加密后的结果
	 */
	private String encrypt(String userCode, Long wxPrizeId, Date paramDate) {

		String date = DateUtil.dateConvertStringTime(paramDate);

		// MD5加密

		System.out.println("原始==========================" + userCode + wxPrizeId + date);
		String encrypt = new Md5PasswordEncoder().encodePassword(userCode + wxPrizeId + date, null);

		encrypt = encrypt.substring(0, 8);

		System.out.println("取前8位的结果============" + encrypt);

		// 返回加密后的结果
		return encrypt;
	}

	@Override
	public QyConvertPrizeHistoryResponseParam qyConvertPrizeHistory(QyConvertPrizeHistoryRequestParam qyConvertPrizeHistoryRequestParam) {
		QyConvertPrizeHistoryResponseParam qyConvertPrizeHistoryResponseParam = new QyConvertPrizeHistoryResponseParam();
		String getUserCode = qyConvertPrizeHistoryRequestParam.getUserCode();
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("userCode", getUserCode);
		List<ConvertPrizeHistory> convertPrizeHistories = weiXinDao.queryConvertPrizeHistoryList(queryMap);
		qyConvertPrizeHistoryResponseParam.setCodeinfo("0");
		qyConvertPrizeHistoryResponseParam.setMessgae("查询成功！");
		qyConvertPrizeHistoryResponseParam.setConvertPrizeHistories(convertPrizeHistories);
		return qyConvertPrizeHistoryResponseParam;
	}

	/**
	 * 用户登陆时，查询此用户是否登陆过，显示相对应的用户信息
	 * 
	 * @author 罗红杰
	 * @param userQyRequestParam
	 * @return
	 */
	@Override
	public UserQyResponseParam userSelect(UserQyRequestParam userQyRequestParam) {
		UserQyResponseParam userQyResponseParam=new UserQyResponseParam();
		Message message = new Message();
		String getUserCode = userQyRequestParam.getUserCode();
		WxUser wXUser = wxUserService.queryWxUserByUserCode(getUserCode);
		userQyResponseParam.setwXUser(wXUser);
		//什么时候给出响应
		
		return userQyResponseParam;
	}

	@Override
	public QyCanGetPrizeListResponseParam qyCanGetFirstPrizeList(QyCanGetPrizeListRequestParam qyCanGetPrizeListRequestParam) {
		QyCanGetPrizeListResponseParam canGetPrizeListResponseParam = new QyCanGetPrizeListResponseParam();
		String getUserCode = qyCanGetPrizeListRequestParam.getUserCode();
		WxUser wxUser = wxUserService.queryWxUserByUserCode(getUserCode);
		if (wxUser == null) {
			canGetPrizeListResponseParam.setCodeinfo("4");
			canGetPrizeListResponseParam.setMessgae("未找到该客户！");
			return canGetPrizeListResponseParam;
		}
		
		List<PrizeHasFlag> prizeHasFlags = weiXinDao.qyCanGetFirstPrizeList(getUserCode);
		
		canGetPrizeListResponseParam.setCodeinfo("0");
		canGetPrizeListResponseParam.setMessgae("查询成功！");
		canGetPrizeListResponseParam.setScore(wxUser.getScore());
		canGetPrizeListResponseParam.setPhone(wxUser.getPhone());
		canGetPrizeListResponseParam.setPrizeHasFlags(prizeHasFlags);
		
		return canGetPrizeListResponseParam;
	}
	/**
	 * 查询该用户是否兑过金靴奖
	 * 
	 * @author 罗红杰
	 * @param 
	 * @return
	 */
//	@Override
//	public QyChangedGetPrizeUserResponseParam qyChangedGetPrizeUser(QyChangedGetPrizeUserRequestParam qyChangedGetPrizeUserRequestParam){
//		QyChangedGetPrizeUserResponseParam changedGetPrizeUserResponseParam=new QyChangedGetPrizeUserResponseParam();
//		String getUserCode = qyChangedGetPrizeUserRequestParam.getUserCode();
//		WxUser wxUser = wxUserService.qyChangedGetPrizeUser(getUserCode);
//		changedGetPrizeUserResponseParam.setwXUser(wxUser);
//		changedGetPrizeUserResponseParam.setCodeinfo("0");
//		changedGetPrizeUserResponseParam.setMessgae("查询成功！");
//		return changedGetPrizeUserResponseParam;
//	}
}
