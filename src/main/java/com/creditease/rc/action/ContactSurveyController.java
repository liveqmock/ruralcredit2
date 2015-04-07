package com.creditease.rc.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.domain.Borrowersurvey;
import com.creditease.rc.domain.Message;
import com.creditease.rc.service.IBorrowersurveyService;

/**
 * 
 * @author haoqiang
 *
 */
@Controller
@RequestMapping("contactSurvey")
public class ContactSurveyController {

	@Resource
	private IBorrowersurveyService borrowersurveyService;

	/**
	 * 
	 * @param borrowerSurveyId 上述合计主键
	 * @return 上述合计对象
	 */
	@RequestMapping(value = "searchContactSurveyAlt", method = RequestMethod.POST)
	public @ResponseBody
	Borrowersurvey searchContactSurveyAlt(int borrowerSurveyId) {

		Borrowersurvey borrowersurvey = borrowersurveyService.searchBorrowersurvey(borrowerSurveyId);
		return borrowersurvey;

	}

	/**
	 * 
	 * @param borrowersurvey 上述合计
	 * @return 消息
	 */
	@RequestMapping(value = "updateContactSurveyAlt", method = RequestMethod.POST)
	public @ResponseBody
	Message updateContactSurveyAlt(Borrowersurvey borrowersurvey) {

		boolean isSuccess = borrowersurveyService.updateContactSurveyAlt(borrowersurvey);
		Message message = new Message();
		if (isSuccess) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
		}
		return message;
	}

	/**
	 * 查询联系人调查表
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 上述合计对象
	 */
	@RequestMapping(value = "searchContactSurvey", method = RequestMethod.POST)
	public @ResponseBody
	Borrowersurvey searchContactSurvey(Integer borrowerServiceAppId) {

		Borrowersurvey returnBorrowersurvey = borrowersurveyService.searchBorrowersurvey(borrowerServiceAppId);
		return returnBorrowersurvey;

	}

	/**
	 * 保存对borrowersurvey的修改操作
	 * 
	 * @param passborrowersurvey 上述合计对象
	 * @return 上述合计对象
	 */
	@RequestMapping(value = "saveContactSurvey", method = RequestMethod.POST)
	public @ResponseBody
	Borrowersurvey saveContactSurvey(Borrowersurvey passborrowersurvey) {

		Borrowersurvey returnBorrowersurvey = new Borrowersurvey();
		returnBorrowersurvey = borrowersurveyService.saveBorrowersurvey(passborrowersurvey);
		borrowersurveyService.updateFlag3th(passborrowersurvey.getBorrowerServiceAppId());
		return returnBorrowersurvey;
	}

}
