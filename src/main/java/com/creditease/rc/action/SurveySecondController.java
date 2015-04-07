package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.domain.BorrowerServiceApp;
import com.creditease.rc.domain.Borrowersurvey;
import com.creditease.rc.domain.Familydepositdebt;
import com.creditease.rc.domain.Familymember;
import com.creditease.rc.domain.Familyotherincome;
import com.creditease.rc.domain.Familytotalcost;
import com.creditease.rc.domain.Householdasserts;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.Surveybusinessinfo;
import com.creditease.rc.service.ISurveySecondService;
import com.creditease.rc.service.IApplicationService;
import com.creditease.rc.service.IBorrowerServiceAppService;
import com.creditease.rc.service.IBorrowersurveyService;
import com.creditease.rc.service.IFamilydepositdebtService;
import com.creditease.rc.service.IFamilymemberService;
import com.creditease.rc.service.IFamilyotherincomeService;
import com.creditease.rc.service.IFamilytotalcostService;
import com.creditease.rc.service.IHouseholdassertsService;
import com.creditease.rc.service.ISurveybusinessinfoService;
import com.creditease.rc.vo.SurveySecondVo;
import com.creditease.rc.vo.BorrowersurveyVo;
import com.creditease.rc.vo.FamilydepositdebtVo;
import com.creditease.rc.vo.FamilymemberVo;
import com.creditease.rc.vo.FamilyotherincomeVo;
import com.creditease.rc.vo.FamilytotalcostVo;
import com.creditease.rc.vo.HouseholdassertsVo;
import com.creditease.rc.vo.SurveybusinessinfoVo;

/**
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("surveySecond")
public class SurveySecondController {
	/**
	 * 
	 * @param binder 传入的所有参数
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@Resource
	private IBorrowerServiceAppService borrowerServiceAppService;

	@Resource
	private IApplicationService applicationSrevice;

	@Resource
	private IFamilymemberService familymemberService;

	@Resource
	private IFamilyotherincomeService familyotherincomeService;

	@Resource
	private ISurveybusinessinfoService surveybusinessinfoService;

	@Resource
	private IFamilytotalcostService familytotalcostService;

	@Resource
	private IFamilydepositdebtService familydepositdebtService;

	@Resource
	private IHouseholdassertsService householdassertsService;

	@Resource
	private ISurveySecondService surveySecondService;

	@Resource
	private IBorrowersurveyService borrowersurveyService;

	/**
	 * ★━━━━━━━━━━━━━━━━━━━━━━━━━━━现用到的方法━━━━━━━━━━━━━━━━━━━━━━━━━━━━━★
	 */
	/**
	 * 回显入户2的所有信息
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 入户2大对象
	 */
	@RequestMapping(value = "doSearchSurveySecondVo", method = RequestMethod.POST)
	public @ResponseBody
	SurveySecondVo doSearchSurveySecondVo(Integer borrowerServiceAppId) {
		SurveySecondVo returnSurveySecondVo = new SurveySecondVo();
// // 家庭成员
// List<Familymember> returnFamilymembers = familymemberService.searchFamilymembers(borrowerServiceAppId);
// // returnFamilymemberVo.setFamilymembers(returnFamilymembers);
// returnSurveySecondVo.setFamilymembers(returnFamilymembers);
// // 其他收入
// List<Familyotherincome> returnFamilyotherincomes = familyotherincomeService
// .searchFamilyotherincomes(borrowerServiceAppId);
// returnSurveySecondVo.setFamilyotherincomes(returnFamilyotherincomes);
// // 经营情况
// Surveybusinessinfo returnSurveybusinessinfo = surveybusinessinfoService
// .searchSurveybusinessinfo(borrowerServiceAppId);
// returnSurveySecondVo.setSurveybusinessinfo(returnSurveybusinessinfo);
// // 日常总开支
// List<Familytotalcost> returnFamilytotalcosts = familytotalcostService
// .searchFamilytotalcosts(borrowerServiceAppId);
// returnSurveySecondVo.setFamilytotalcosts(returnFamilytotalcosts);
// // 储蓄债权债务
// List<Familydepositdebt> returnFamilydepositdebts = familydepositdebtService
// .searchFamilydepositdebts(borrowerServiceAppId);
// returnSurveySecondVo.setFamilydepositdebts(returnFamilydepositdebts);
// // 家庭资产
// List<Householdasserts> returnHouseholdassertss = householdassertsService
// .searchHouseholdassertss(borrowerServiceAppId);
// returnSurveySecondVo.setHouseholdassertss(returnHouseholdassertss);
// // 上述合计
// Borrowersurvey returnBorrowersurvey = borrowersurveyService.searchBorrowersurvey(borrowerServiceAppId);
// returnSurveySecondVo.setBorrowersurvey(returnBorrowersurvey);

		returnSurveySecondVo = surveySecondService.searchSurveySecondVo(borrowerServiceAppId);

		return returnSurveySecondVo;
	}

	/**
	 * 保存全部入户调查表2信息
	 * 
	 * @param surveySecondVo 入户调查表2大对象
	 * @return Message
	 */
	@RequestMapping(value = "saveSurveySecondVo", method = RequestMethod.POST)
	public @ResponseBody
	Message saveSurveySecondVo(SurveySecondVo surveySecondVo) {

		boolean isSuccess = surveySecondService.saveSurveySecondVo(surveySecondVo);

		Message message = new Message();
		if (isSuccess) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
		}

		return message;
	}

	/**
	 * 保存Familydepositdebts储蓄/债权债务，其中包括增加删除修改操作。自动判断！
	 * 
	 * @param passfamilydepositdebtVo 储蓄/债权债务
	 * @return 储蓄/债权债务
	 */
	@RequestMapping(value = "saveFamilydepositdebts", method = RequestMethod.POST)
	public @ResponseBody
	FamilydepositdebtVo saveFamilydepositdebts(FamilydepositdebtVo passfamilydepositdebtVo) {
		List<Familydepositdebt> familydepositdebtList = passfamilydepositdebtVo.getFamilydepositdebts();
		List<Familydepositdebt> returnFamilydepositdebts = familydepositdebtService
				.saveFamilydepositdebts(familydepositdebtList);
		FamilydepositdebtVo returnFamilydepositdebtVo = new FamilydepositdebtVo();
		returnFamilydepositdebtVo.setFamilydepositdebts(returnFamilydepositdebts);
		return returnFamilydepositdebtVo;
	}

	/**
	 * 回显Familydepositdebts储蓄/债权债务
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 储蓄/债权债务
	 */
	@RequestMapping(value = "searchFamilydepositdebts", method = RequestMethod.POST)
	public @ResponseBody
	FamilydepositdebtVo searchFamilydepositdebts(Integer borrowerServiceAppId) {
		List<Familydepositdebt> returnFamilydepositdebts = familydepositdebtService
				.searchFamilydepositdebts(borrowerServiceAppId);
		FamilydepositdebtVo returnFamilydepositdebtVo = new FamilydepositdebtVo();
		returnFamilydepositdebtVo.setFamilydepositdebts(returnFamilydepositdebts);
		return returnFamilydepositdebtVo;
	}

	/**
	 * 保存Householdassertss家庭资产，其中包括增加删除修改操作。自动判断！
	 * 
	 * @param passhouseholdassertsVo 家庭资产
	 * @return 家庭资产
	 */
	@RequestMapping(value = "saveHouseholdassertss", method = RequestMethod.POST)
	public @ResponseBody
	HouseholdassertsVo saveHouseholdassertss(HouseholdassertsVo passhouseholdassertsVo) {
		List<Householdasserts> householdassertsList = passhouseholdassertsVo.getHouseholdassertss();
		List<Householdasserts> returnHouseholdassertss = householdassertsService
				.saveHouseholdassertss(householdassertsList);
		HouseholdassertsVo returnHouseholdassertsVo = new HouseholdassertsVo();
		returnHouseholdassertsVo.setHouseholdassertss(returnHouseholdassertss);
		return returnHouseholdassertsVo;
	}

	/**
	 * 回显Householdassertss家庭资产
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 家庭资产
	 */
	@RequestMapping(value = "searchHouseholdassertss", method = RequestMethod.POST)
	public @ResponseBody
	HouseholdassertsVo searchHouseholdassertss(Integer borrowerServiceAppId) {
		List<Householdasserts> returnHouseholdassertss = householdassertsService
				.searchHouseholdassertss(borrowerServiceAppId);
		HouseholdassertsVo returnHouseholdassertsVo = new HouseholdassertsVo();
		returnHouseholdassertsVo.setHouseholdassertss(returnHouseholdassertss);
		return returnHouseholdassertsVo;
	}

	/**
	 * 保存Familytotalcosts日常总开支，其中包括增加删除修改操作。自动判断！
	 * 
	 * @param passfamilytotalcostVo 日常总开支
	 * @return 日常总开支
	 */
	@RequestMapping(value = "saveFamilytotalcosts", method = RequestMethod.POST)
	public @ResponseBody
	FamilytotalcostVo saveFamilytotalcosts(FamilytotalcostVo passfamilytotalcostVo) {
		List<Familytotalcost> familytotalcostList = passfamilytotalcostVo.getFamilytotalcosts();
		List<Familytotalcost> returnFamilytotalcosts = familytotalcostService.saveFamilytotalcosts(familytotalcostList);
		FamilytotalcostVo returnFamilytotalcostVo = new FamilytotalcostVo();
		returnFamilytotalcostVo.setFamilytotalcosts(returnFamilytotalcosts);
		return returnFamilytotalcostVo;
	}

	/**
	 * 回显Familytotalcosts日常总开支
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 日常总开支
	 */
	@RequestMapping(value = "searchFamilytotalcosts", method = RequestMethod.POST)
	public @ResponseBody
	FamilytotalcostVo searchFamilytotalcosts(Integer borrowerServiceAppId) {
		List<Familytotalcost> returnFamilytotalcosts = familytotalcostService
				.searchFamilytotalcosts(borrowerServiceAppId);
		FamilytotalcostVo returnFamilytotalcostVo = new FamilytotalcostVo();
		returnFamilytotalcostVo.setFamilytotalcosts(returnFamilytotalcosts);
		return returnFamilytotalcostVo;
	}

	/**
	 * 保存Familyotherincomes其他收入，其中包括增加删除修改操作。自动判断！
	 * 
	 * @param passfamilyotherincomeVo 其他收入
	 * @return 其他收入
	 */
	@RequestMapping(value = "saveFamilyotherincomes", method = RequestMethod.POST)
	public @ResponseBody
	FamilyotherincomeVo saveFamilyotherincomes(FamilyotherincomeVo passfamilyotherincomeVo) {
		List<Familyotherincome> familyotherincomeList = passfamilyotherincomeVo.getFamilyotherincomes();
		List<Familyotherincome> returnFamilyotherincomes = familyotherincomeService
				.saveFamilyotherincomes(familyotherincomeList);
		FamilyotherincomeVo returnFamilyotherincomeVo = new FamilyotherincomeVo();
		returnFamilyotherincomeVo.setFamilyotherincomes(returnFamilyotherincomes);
		return returnFamilyotherincomeVo;
	}

	/**
	 * 回显Familyotherincomes其他收入
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 其他收入
	 */
	@RequestMapping(value = "searchFamilyotherincomes", method = RequestMethod.POST)
	public @ResponseBody
	FamilyotherincomeVo searchFamilyotherincomes(Integer borrowerServiceAppId) {
		List<Familyotherincome> returnFamilyotherincomes = familyotherincomeService
				.searchFamilyotherincomes(borrowerServiceAppId);
		FamilyotherincomeVo returnFamilyotherincomeVo = new FamilyotherincomeVo();
		returnFamilyotherincomeVo.setFamilyotherincomes(returnFamilyotherincomes);
		return returnFamilyotherincomeVo;
	}

	/**
	 * 保存Borrowersurvey上述总计，其中包括增加修改操作。自动判断！
	 * 
	 * @param passborrowersurvey 上述总计
	 * @return 上述总计
	 */
	@RequestMapping(value = "saveBorrowersurvey", method = RequestMethod.POST)
	public @ResponseBody
	Borrowersurvey saveBorrowersurvey(Borrowersurvey passborrowersurvey) {
		Borrowersurvey returnBorrowersurvey = new Borrowersurvey();
		returnBorrowersurvey = borrowersurveyService.saveBorrowersurvey(passborrowersurvey);
		return returnBorrowersurvey;
	}

	/**
	 * 保存Borrowersurvey上述总计，其中包括增加修改操作。自动判断！（为了最后保存全体的方便）
	 * 
	 * @param passborrowersurveyVo 上述总计
	 * @return 上述总计
	 */
	@RequestMapping(value = "saveBorrowersurveyVo", method = RequestMethod.POST)
	public @ResponseBody
	BorrowersurveyVo saveBorrowersurveyVo(BorrowersurveyVo passborrowersurveyVo) {
		Borrowersurvey passborrowersurvey = passborrowersurveyVo.getBorrowersurvey();
		Borrowersurvey returnBorrowersurvey = borrowersurveyService.saveBorrowersurvey(passborrowersurvey);
		BorrowersurveyVo returnBorrowersurveyVo = new BorrowersurveyVo();
		returnBorrowersurveyVo.setBorrowersurvey(returnBorrowersurvey);
		return returnBorrowersurveyVo;
	}

	/**
	 * 回显Borrowersurvey上述合计
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 上述合计
	 */
	@RequestMapping(value = "searchBorrowersurvey", method = RequestMethod.POST)
	public @ResponseBody
	Borrowersurvey searchBorrowersurvey(Integer borrowerServiceAppId) {
		Borrowersurvey returnBorrowersurvey = borrowersurveyService.searchBorrowersurvey(borrowerServiceAppId);
		return returnBorrowersurvey;
	}

	/**
	 * 回显Borrowersurvey上述合计（为了最后保存的方便）
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 上述合计
	 */
	@RequestMapping(value = "searchBorrowersurveyVo", method = RequestMethod.POST)
	public @ResponseBody
	BorrowersurveyVo searchBorrowersurveyVo(Integer borrowerServiceAppId) {

		Borrowersurvey returnBorrowersurvey = borrowersurveyService.searchBorrowersurvey(borrowerServiceAppId);
		BorrowersurveyVo returnBorrowersurveyVo = new BorrowersurveyVo();

		returnBorrowersurveyVo.setBorrowersurvey(returnBorrowersurvey);
		return returnBorrowersurveyVo;
	}

	/**
	 * 保存Surveybusinessinfo经营情况，其中包括增加修改操作。自动判断！
	 * 
	 * @param passsurveybusinessinfo 经营情况
	 * @return 经营情况
	 */
	@RequestMapping(value = "saveSurveybusinessinfo", method = RequestMethod.POST)
	public @ResponseBody
	Surveybusinessinfo saveSurveybusinessinfo(Surveybusinessinfo passsurveybusinessinfo) {
		Surveybusinessinfo returnSurveybusinessinfo = new Surveybusinessinfo();
		returnSurveybusinessinfo = surveybusinessinfoService.saveSurveybusinessinfo(passsurveybusinessinfo);
		return returnSurveybusinessinfo;
	}

	/**
	 * 保存Surveybusinessinfo经营情况，其中包括增加修改操作。自动判断！（为了最后全体保存方便）
	 * 
	 * @param passsurveybusinessinfoVo 经营情况
	 * @return 经营情况
	 */
	@RequestMapping(value = "saveSurveybusinessinfoVo", method = RequestMethod.POST)
	public @ResponseBody
	SurveybusinessinfoVo saveSurveybusinessinfoVo(SurveybusinessinfoVo passsurveybusinessinfoVo) {
		return passsurveybusinessinfoVo;
//		Surveybusinessinfo passsurveybusinessinfo = passsurveybusinessinfoVo.getSurveybusinessinfo();
//		Surveybusinessinfo returnSurveybusinessinfo = surveybusinessinfoService
//				.saveSurveybusinessinfo(passsurveybusinessinfo);
//		SurveybusinessinfoVo returnSurveybusinessinfoVo = new SurveybusinessinfoVo();
//		returnSurveybusinessinfoVo.setSurveybusinessinfo(returnSurveybusinessinfo);
//		return returnSurveybusinessinfoVo;
	}

	/**
	 * 回显Surveybusinessinfo经营情况
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 经营情况
	 */
	@RequestMapping(value = "searchSurveybusinessinfo", method = RequestMethod.POST)
	public @ResponseBody
	Surveybusinessinfo searchSurveybusinessinfo(Integer borrowerServiceAppId) {
		Surveybusinessinfo returnSurveybusinessinfo = surveybusinessinfoService
				.searchSurveybusinessinfo(Long.valueOf(borrowerServiceAppId));
		return returnSurveybusinessinfo;
	}

	/**
	 * 回显Surveybusinessinfo经营情况（为了最后保存方便）
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 经营情况
	 */
	@RequestMapping(value = "searchSurveybusinessinfoVo", method = RequestMethod.POST)
	public @ResponseBody
	SurveybusinessinfoVo searchSurveybusinessinfoVo(Integer borrowerServiceAppId) {
		return null;
//		Surveybusinessinfo returnSurveybusinessinfo = surveybusinessinfoService
//				.searchSurveybusinessinfo(borrowerServiceAppId);
//		SurveybusinessinfoVo returnSurveybusinessinfoVo = new SurveybusinessinfoVo();
//		returnSurveybusinessinfoVo.setSurveybusinessinfo(returnSurveybusinessinfo);
//		return returnSurveybusinessinfoVo;
	}

	/**
	 * 保存Familymembers家庭成员，其中包括增加删除修改操作。自动判断！
	 * 
	 * @param passFamilymemberVo 家庭成员
	 * @return 家庭成员
	 */
	@RequestMapping(value = "saveFamilymembers", method = RequestMethod.POST)
	public @ResponseBody
	FamilymemberVo saveFamilymembers(FamilymemberVo passFamilymemberVo) {
		List<Familymember> familymemberList = passFamilymemberVo.getFamilymembers();
		List<Familymember> returnFamilymembers = familymemberService.saveFamilymembers(familymemberList);
		FamilymemberVo returnFamilymemberVo = new FamilymemberVo();
		returnFamilymemberVo.setFamilymembers(returnFamilymembers);
		return returnFamilymemberVo;
	}

	/**
	 * 回显Familymember家庭成员
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 家庭成员
	 */
	@RequestMapping(value = "searchFamilymembers", method = RequestMethod.POST)
	public @ResponseBody
	FamilymemberVo searchFamilymembers(Integer borrowerServiceAppId) {
		List<Familymember> returnFamilymembers = familymemberService.searchFamilymembers(Long.valueOf(borrowerServiceAppId));
		FamilymemberVo returnFamilymemberVo = new FamilymemberVo();
		returnFamilymemberVo.setFamilymembers(returnFamilymembers);
		return returnFamilymemberVo;
	}

	/**
	 * ★━━━━━━━━━━━━━━━━━━━━━━━━━━━现用到的方法END━━━━━━━━━━━━━━━━━━━━━━━━━━━━━★
	 */

	/**
	 * ★━━━━━━━━━━━━━━━━━━━━━━━━━━━备用的方法━━━━━━━━━━━━━━━━━━━━━━━━━━━━━★
	 */
	/**
	 * 回显小组信息
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 信贷申请
	 */
	@RequestMapping(value = "getNameAndCode", method = RequestMethod.POST)
	public @ResponseBody
	BorrowerServiceApp getNameAndCode(Integer borrowerServiceAppId) {
		BorrowerServiceApp borrowerServiceApp = new BorrowerServiceApp();
		borrowerServiceApp = borrowerServiceAppService.selectByBorrowerServiceAppId(borrowerServiceAppId);
		return borrowerServiceApp;
	}

	/**
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @param request 页面接受
	 * @return 页面
	 */
	@RequestMapping(value = "returnSurveySecondJSP")
	public String returnSurveySecondJSP(Integer borrowerServiceAppId, HttpServletRequest request) {
		request.setAttribute("borrowerServiceAppId", borrowerServiceAppId);
		return "";
	}

	/**
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @param creditApplicationId 小组申请主键
	 * @param request 页面接受
	 * @return 页面
	 */
	@RequestMapping(value = "returnContactSurveyJSP")
	public String returnContactSurveyJSP(Integer borrowerServiceAppId, Integer creditApplicationId,
			HttpServletRequest request) {
		request.setAttribute("borrowerServiceAppId", borrowerServiceAppId);
		request.setAttribute("creditApplicationId", creditApplicationId);
		return "/jsp/rc/business/contactSurvey.jsp";
	}

	/**
	 * 
	 * @param familymemberVo 家庭成员拓展对象
	 * @return Message
	 */
	@RequestMapping(value = "insertIntofamilymenberList", method = RequestMethod.POST)
	public @ResponseBody
	Message insertIntofamilymenberList(FamilymemberVo familymemberVo) {

		List<Familymember> familymembers = familymemberVo.getFamilymembers();
		List<Familymember> addFamilymembers = new ArrayList<Familymember>();
		for (int i = 0; i < familymembers.size(); i++) {
			Familymember temp = familymembers.get(i);
			String name = temp.getName();
			if (name != null && !"".equals(name.trim())) {
				addFamilymembers.add(temp);
			}
		}
		boolean isSuccess = familymemberService.batchInsert(addFamilymembers);

		Message message = new Message();
		if (isSuccess) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
		}
		return message;
	}

	/**
	 * 
	 * @param familyotherincomeVo 其他收入拓展对象
	 * @return Message
	 */
	@RequestMapping(value = "insertIntofamilyotherincomeList", method = RequestMethod.POST)
	public @ResponseBody
	Message insertIntofamilyotherincomeList(FamilyotherincomeVo familyotherincomeVo) {
		List<Familyotherincome> familyotherincomes = familyotherincomeVo.getFamilyotherincomes();
		List<Familyotherincome> addfamilyotherincomes = new ArrayList<Familyotherincome>();
		for (int i = 0; i < familyotherincomes.size(); i++) {
			Familyotherincome temp = familyotherincomes.get(i);
			String otherIncomeType = temp.getOtherIncomeType();
			if (otherIncomeType != null && !"".equals(otherIncomeType.trim())) {
				addfamilyotherincomes.add(temp);
			}
		}
		boolean isSuccess = familyotherincomeService.batchInsert(addfamilyotherincomes);
		Message message = new Message();
		if (isSuccess) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
		}
		return message;
	}

	/**
	 * @param familytotalcostVo 日常总开支拓展对象
	 * @return Message
	 */
	@RequestMapping(value = "insertIntofamilytotalcostList", method = RequestMethod.POST)
	public @ResponseBody
	Message insertIntofamilytotalcostList(FamilytotalcostVo familytotalcostVo) {
		List<Familytotalcost> familytotalcosts = familytotalcostVo.getFamilytotalcosts();
		List<Familytotalcost> addfamilytotalcosts = new ArrayList<Familytotalcost>();
		for (int i = 0; i < familytotalcosts.size(); i++) {
			Familytotalcost temp = familytotalcosts.get(i);
			double amount = temp.getAmount();
			if (!"".equals(amount)) {
				addfamilytotalcosts.add(temp);
			}
		}
		boolean isSuccess = familytotalcostService.batchInsert(addfamilytotalcosts);
		Message message = new Message();
		if (isSuccess) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
		}
		return message;
	}

	/**
	 * 
	 * @param familydepositdebtVo 储蓄债权债务拓展对象
	 * @return Message
	 */
	@RequestMapping(value = "insertIntofamilydepositdebtList", method = RequestMethod.POST)
	public @ResponseBody
	Message insertIntofamilydepositdebtList(FamilydepositdebtVo familydepositdebtVo) {
		List<Familydepositdebt> familydepositdebts = familydepositdebtVo.getFamilydepositdebts();
		List<Familydepositdebt> addfamilydepositdebts = new ArrayList<Familydepositdebt>();
		for (int i = 0; i < familydepositdebts.size(); i++) {
			Familydepositdebt temp = familydepositdebts.get(i);
			String bank = temp.getBank();
			if (bank != null && !"".equals(bank.trim())) {
				addfamilydepositdebts.add(temp);
			}
		}
		boolean isSuccess = familydepositdebtService.batchInsert(addfamilydepositdebts);
		Message message = new Message();
		if (isSuccess) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
		}
		return message;
	}

	/**
	 * 
	 * @param surveybusinessinfo 经营情况
	 * @return Message
	 */
	@RequestMapping(value = "insertIntosurveybusinessinfo", method = RequestMethod.POST)
	public @ResponseBody
	Message insertIntosurveybusinessinfo(Surveybusinessinfo surveybusinessinfo) {

		boolean isSuccess = surveybusinessinfoService.insersurveybusinessinfo(surveybusinessinfo);
		Message message = new Message();
		if (isSuccess) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
		}
		return message;
	}

	/**
	 * 
	 * @param surveySecondVo 入户2整个对象
	 * @return Message
	 */
	@RequestMapping(value = "insertSurveySecondVo", method = RequestMethod.POST)
	public @ResponseBody
	Message insertSurveySecondVo(SurveySecondVo surveySecondVo) {

		boolean isSuccess = surveySecondService.insertSurveySecondVo(surveySecondVo);

		Message message = new Message();
		if (isSuccess) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
		}

		return message;
	}

	/**
	 * 
	 * @param borrowerSurveyId 上述合计主键
	 * @return 入户2大对象
	 */
	@RequestMapping(value = "searchSurveySecondVo", method = RequestMethod.POST)
	public @ResponseBody
	SurveySecondVo searchSurveySecondVo(int borrowerSurveyId) {
		SurveySecondVo surveySecondVo = surveySecondService.searchSurveySecondTable(borrowerSurveyId);

		// int rbsid = borrowerSurveyId;
		return surveySecondVo;

	}

	/**
	 * 
	 * @param surveySecondVo 入户2大对象
	 * @return Message
	 */
	@RequestMapping(value = "updateSurveySecondVo", method = RequestMethod.POST)
	public @ResponseBody
	Message updateSurveySecondVo(SurveySecondVo surveySecondVo) {

		boolean isSuccess = surveySecondService.updateSurveySecondTable(surveySecondVo);
		Message message = new Message();
		if (isSuccess) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
		}
		return message;
	}

	/**
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return 信贷申请对象
	 */
	@RequestMapping(value = "getApplictionData", method = RequestMethod.POST)
	public @ResponseBody
	BorrowerServiceApp getApplictionData(int borrowerServiceAppId) {
		BorrowerServiceApp borrowerServiceApp = applicationSrevice.searchBorrowerServiceApp(borrowerServiceAppId);
		return borrowerServiceApp;

	}

	/**
	 * 
	 * @param borrowerServiceAppId 信贷申请主键
	 * @return int
	 */
	@RequestMapping(value = "getBorrowerSurveyId", method = RequestMethod.POST)
	public @ResponseBody
	int getBorrowerSurveyId(int borrowerServiceAppId) {
		int borrowerSurveyId = borrowersurveyService.searchBorrowerSurveyId(borrowerServiceAppId);
		return borrowerSurveyId;

	}

	/**
	 * 
	 * @param familymemberVo 家庭成员对象
	 * @return Message
	 */
	@RequestMapping(value = "insertFamilymemberVoTest", method = RequestMethod.POST)
	public @ResponseBody
	Message insertFamilymemberVoTest(FamilymemberVo familymemberVo) {
		boolean isSuccess = true;
		Message message = new Message();
		if (isSuccess) {
			message.setSuccess(true);
		} else {
			message.setSuccess(false);
		}

		return message;
	}
	/**
	 * ★━━━━━━━━━━━━━━━━━━━━━━━━━━━备用的方法END━━━━━━━━━━━━━━━━━━━━━━━━━━━━━★
	 */
}
