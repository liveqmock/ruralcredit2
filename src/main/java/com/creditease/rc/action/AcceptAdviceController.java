package com.creditease.rc.action;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.security.User;
import com.creditease.rc.domain.AcceptConsultRollback;
import com.creditease.rc.domain.Application;
import com.creditease.rc.domain.CodeTable;
import com.creditease.rc.domain.CustomerConsultPool;
import com.creditease.rc.domain.Message;
import com.creditease.rc.service.IAcceptConsultRollbackService;
import com.creditease.rc.service.IApplicationService;
import com.creditease.rc.service.IConsultPoolAndApplyPersonService;
import com.creditease.rc.service.IConsultPoolAndRollbackService;
import com.creditease.rc.service.ICustomerConsultPoolService;
import com.creditease.rc.service.ICustomerConsultService;
import com.creditease.rc.service.IDataDictionaryService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DicUtil;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.util.LockAndUnlockByLockUtil;
import com.creditease.rc.vo.CustomerConsultPoolVo;
import com.creditease.rc.vo.DataDictionaryVo;
import com.creditease.rc.vo.FamilyInfo;
import com.creditease.rc.vo.MobileInfo;
import com.creditease.rc.vo.NewApplicationInfo;
import com.creditease.rc.vo.NewCustomerConsult;
import com.creditease.rc.vo.PartnerInfo;
import com.creditease.rc.vo.PersonArrested;

/**
 * 受理咨询操作controller
 * 
 * @author yangll
 * @since 2014-02-14
 * 
 */
@Controller
@RequestMapping(value = "acceptAdviceController")
public class AcceptAdviceController {

	@Resource
	private ICustomerConsultPoolService customerConsultPoolService;

	@Resource
	private IAcceptConsultRollbackService acceptConsultRollbackService;

	@Resource
	private IConsultPoolAndRollbackService consultPoolAndRollbackService;

	@Resource
	private IDataDictionaryService iDataDictionaryService;

	@Resource
	private ICustomerConsultService customerConsultService;

	@Resource
	private IConsultPoolAndApplyPersonService consultPoolAndApplyPersonService;

	@Resource
	private IApplicationService applicationService;

	/**
	 * 
	 * @param binder
	 *            WebDataBinder对象
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	//申请退回原因
	@RequestMapping(value = "updateRollbackReason")
	public @ResponseBody
	Message updateRollbackReason(CustomerConsultPoolVo customerConsultPoolVo,AcceptConsultRollback acceptConsultRollback) {
		Message message = new Message();
		// 将上一条的退回原因置为历史（历史标识为1），同时将客户咨询池的受理咨询状态改为“待退回确认”
		// 新插入一条退回原因（当前标识为0）,为了控制事务，将该操作放在一个service方法中
		boolean isSuccess = false;
		isSuccess = consultPoolAndRollbackService.updateRollBackAndConsultStatus(customerConsultPoolVo,acceptConsultRollback);
		message.setSuccess(isSuccess);
		return message;
	}

	/**
	 * @author luohongjie
	 * 确认退回
	 * @param acceptConsultRollback
	 * @return
	 */
	@RequestMapping(value = "updateRollbackReasonConfirm")
	public @ResponseBody
	Message updateRollbackReasonConfirm(CustomerConsultPoolVo customerConsultPoolVo,AcceptConsultRollback acceptConsultRollback) {
		Message message = new Message();
		// 同时将客户咨询池的营销咨询状态改为“已退回”
		// 新插入一条退回原因（当前标识为0）,为了控制事务，将该操作放在一个service方法中
		boolean isSuccess = false;
		isSuccess = consultPoolAndRollbackService.updateMarkingConsultStatusConfirm(customerConsultPoolVo,acceptConsultRollback);
		message.setSuccess(isSuccess);
		return message;
	}
	
	/**
	 * 取消退回确认方法
	 * 
	 */
	@RequestMapping(value = "updateRollbackReasonCancel")
	public @ResponseBody
	Message updateRollBackReasonCancel(CustomerConsultPoolVo customerConsultPoolVo,AcceptConsultRollback acceptConsultRollback){
		Message message=new Message();
		boolean isSuccess=false;
		isSuccess=consultPoolAndRollbackService.updateRollbackReasonCancel(customerConsultPoolVo,acceptConsultRollback);
		message.setSuccess(isSuccess);
		return message;
		
	}
	
	/**
	 * 确认退回操作
	 */
	@RequestMapping(value = "backInquirePools")
	public @ResponseBody
	ModelAndView backInquirePools(Long consultPoolId) {
		ModelAndView modelAndView = new ModelAndView();
		// 查询退回原因对象信息（当前标识为0）
		AcceptConsultRollback acceptConsultRollback = acceptConsultRollbackService
				.getAcceptConsultRollbackByForeignKey(consultPoolId);
		// 设置返回值到Message对象中
		modelAndView.addObject("acceptConsultRollback", acceptConsultRollback);
		modelAndView.addObject("consultPoolId", consultPoolId);
		modelAndView.setViewName("/jsp/rc/CustomerConsultPool/back_pool_reason.jsp");
		return modelAndView;
	}
	/**
	 * 申请退回操作
	 */
	@RequestMapping(value = "applyBackInquirePools")
	public @ResponseBody
	ModelAndView applyBackInquirePools(Long consultPoolId,String pool) {
		System.out.println(pool);
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("consultPoolId", consultPoolId);
		modelAndView.addObject("pool",pool);
		modelAndView.setViewName("/jsp/rc/CustomerConsultPool/back_pool_reason.jsp");
		return modelAndView;
	}
	

	/**
	 * 受理咨询
	 * 
	 * @return
	 */
	@RequestMapping(value = "acceptAdvice")
	public @ResponseBody
	ModelAndView acceptAdvice(Long consultPoolId) {
		// 取出受理咨询的对象信息
		CustomerConsultPool customerConsultPool = customerConsultPoolService.queryCustomerConsultPool(consultPoolId);
		// 填充到命令对象中
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/CustomerConsultPool/accept_inquire.jsp");
		//获取数据字典客户标签（processCausesPool）：code_value，用于前台数据显示，
        if (StringUtils.isNotEmpty(customerConsultPool.getCustomerTag())) {
            customerConsultPool.setCustomerTagName(getCodeValueStrs("processCausesPool", customerConsultPool.getCustomerTag().split(",")));
            customerConsultPool.setCustomerTag(customerConsultPool.getCustomerTag());
        }
        modelAndView.addObject("customerConsultPool", customerConsultPool);
		return modelAndView;
	}

	/**
	 * 更新受理咨询信息
	 */
	@RequestMapping(value = "updateAcceptAdvice")
	public @ResponseBody
	Message updateAcceptAdvice(CustomerConsultPool customerConsultPool, String frontTraceStatus) {
		Message message = new Message();
		try {
			// 对当前操作进行加锁
			boolean hasLock = LockAndUnlockByLockUtil.lockOperate("RL_MARKET_CONSULT_POOL#" + customerConsultPool.getConsultPoolId(), "咨询池表");
			// 取出受理咨询的对象信息
			CustomerConsultPool customerConsultPoolDB = customerConsultPoolService.queryCustomerConsultPool(customerConsultPool.getConsultPoolId());
			String TraceStatus=customerConsultPoolDB.getTraceStatus();
			if(TraceStatus == null){
				TraceStatus="";
			}
			boolean f=TraceStatus.equals(frontTraceStatus);
			if (hasLock && customerConsultPoolDB != null && !f) {
				// 加锁不成功并且数据库状态和前台页面（数据列表中而不是受理咨询编辑页面）状态不一致
				message.setMsg("该客户状态已变更，不允许提交！");
			} else if (hasLock) {// 加锁成功
				//customerConsultPool.setFeedbackDate(new Date());
				boolean isSuccess = false;
				isSuccess = customerConsultPoolService.updateCustomerConsultPool(customerConsultPool, "customer");
				message.setSuccess(isSuccess);
			} else if (!hasLock) {
				message.setMsg("该数据正在被操作，请确认！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			message.setMsg("数据库操作异常，请联系系统管理员！");
		} finally {
			LockAndUnlockByLockUtil.unlockOperate("RL_MARKET_CONSULT_POOL#" + customerConsultPool.getConsultPoolId());
		}
		return message;
	}

	/**
	 * 查看受理咨询信息
	 */
	@RequestMapping(value = "getAcceptAdviceInfo")
	public @ResponseBody
	ModelAndView getAcceptAdviceInfo(Long consultPoolId) {
		ModelAndView modelAndView = new ModelAndView();
		CustomerConsultPool customerConsultPool = customerConsultPoolService
				.getCustomerConsultPoolByPrimaryKey(consultPoolId);
		// 填充到命令对象中
		modelAndView.addObject("customerConsultPool", customerConsultPool);
		modelAndView.setViewName("/jsp/acceptadvice/acceptInquireView.jsp");
		return modelAndView;
	}

	/**
	 * 申请借款页面
	 */
	@RequestMapping(value = "showAddNewClint")
	public @ResponseBody
	ModelAndView showAddNewClint(Long consultPoolId) {
		ModelAndView modelAndView = new ModelAndView();
		// 预先插入一条咨询记录生成咨询id，用于和申请进行外键关联
		NewCustomerConsult newCustomerConsult = new NewCustomerConsult();
		Long customerConsultId = (long) 1;
		// Long customerConsultId = (long) customerConsultService.returnPKInsertCustomer(newCustomerConsult);
		modelAndView.addObject("consultPoolId", consultPoolId);
		modelAndView.addObject("customerConsultId", customerConsultId);
		modelAndView.setViewName("/jsp/acceptadvice/add_new_client.jsp");
		return modelAndView;
	}

	/**
	 * 提交申请信息
	 */
	@RequestMapping(value = "saveApplyPerson")
	public @ResponseBody
	String saveApplyPerson(String customerConsultId, String consultPoolId, String strInfo, String strArrested) {
		Message message = new Message();
		// 获取user（登录人）信息方法
		User user = SpringSecurityUtils.getCurrentUser();
		HashSet roleSet = (HashSet) user.getAuthorities();
		List roleList = new ArrayList();
		for (Iterator it = roleSet.iterator(); it.hasNext();) {
			roleList.add(it.next());
		}
		CustomerConsultPool customerConsultPool = customerConsultPoolService.queryCustomerConsultPool(Long
				.valueOf(consultPoolId));
		// 获取咨询借款金额
		BigDecimal borrBigDecimal = customerConsultPool.getBorrowAmount().multiply(new BigDecimal(10000));
		// 是否需要双人调查
		int surveyFlag = isDoublePersonSurvey(String.valueOf(borrBigDecimal), roleList);

		// 构造主表数据
		Application application = new Application();
// application.setCustomerConsultId(Long.valueOf(customerConsultId));
// application.setApplicationStatus("0");

		// application.setSysGuid(sysGuid);
		// application.setApplicationNumber(applicationNumber);

// application.setOptDate(new Date());

		// application.setCreateBy(createBy);
// application.setCreateTime(new Date());
		// application.setLastUpdateBy(lastUpdateBy);
		// application.setLastUpdateTime(new Date());

// application.setAreaDepartmentId(String.valueOf(user.getAreaDepartmentId()));
// application.setAreaDepartmentName(user.getAreaDepartmentName());
// application.setCityDepartmentId(String.valueOf(user.getCityDepartmentId()));
// application.setCityDepartmentName(user.getCityDepartmentName());
// application.setTeamDepartmentId(String.valueOf(user.getTeamDepartmentId()));
// application.setTeamDepartmentName(user.getTeamDepartmentName());
// application.setDepartmentId(String.valueOf(user.getDepartmentId()));
// application.setDepartmentName(user.getDepartmentName());

		// application.setIsDoubleSurvey(String.valueOf(surveyFlag));// 是否需要双人调查

		// 表单数据
		// ApplicationInfo info = (ApplicationInfo)
		// JsonUtil.getObject(strInfo,ApplicationInfo.class);
		NewApplicationInfo info = (NewApplicationInfo) JsonUtil.getObject(strInfo, NewApplicationInfo.class);
		List<PersonArrested> arresteds = null;
		if (strArrested != null && strArrested != "") {
			arresteds = JsonUtil.getListFromJsonArrStr(strArrested, PersonArrested.class);
		}

		// 根据身份证号，查询历史申请信息
		// 1.查询历史申请基本信息(取到历史申请id)
		List<NewApplicationInfo> hisAppInfoList = consultPoolAndApplyPersonService.getNewHistoryBasicInfoBycode(info
				.getIdentityCard());
		NewApplicationInfo hisAppInfo = new NewApplicationInfo();
		if (hisAppInfoList != null && hisAppInfoList.size() > 0) {
			hisAppInfo = hisAppInfoList.get(0);
		}
		// 查询历史申请手机联系方式
		// 组装数据
		List<MobileInfo> hisMobileInfoList = null;
		if (CommonUtil.isNotEmpty(String.valueOf(hisAppInfo.getApplicationId()))) {// 判断是否存在历史申请id
			Map map = new HashMap();
			map.put("applyId", hisAppInfo.getApplicationId());
			map.put("status", "1");
			hisMobileInfoList = consultPoolAndApplyPersonService.getHistoryMobileInfo(map);
		}

		// 2.查询历史申请配偶信息
		PartnerInfo partnerInfo = null;
		if (CommonUtil.isNotEmpty(String.valueOf(hisAppInfo.getApplicationInfoId()))) {
			partnerInfo = consultPoolAndApplyPersonService.getHistoryPartnerInfo(hisAppInfo.getApplicationInfoId());
		}

		// 3.查询历史申请其他家庭成员信息
		List<FamilyInfo> hisFamilyInfoList = null;
		if (CommonUtil.isNotEmpty(String.valueOf(hisAppInfo.getApplicationInfoId()))) {
			hisFamilyInfoList = consultPoolAndApplyPersonService.getHistoryFamilyInfoList(hisAppInfo
					.getApplicationInfoId());
		}

		// 更新名字和年龄，把原来存在的申请id和申请基本信息id还有历史标示置为null，否则保存到数据库里存在问题
		hisAppInfo.setAppInfoName(info.getAppInfoName());
		hisAppInfo.setAppInfoAge(info.getAppInfoAge());
		hisAppInfo.setAppInfoSex(info.getAppInfoSex());
		hisAppInfo.setIdentityCard(info.getIdentityCard());
		hisAppInfo.setBorrowerType((short) 1);// 主申请人
		hisAppInfo.setHistoryflag((short) 0);// 表示新增
		hisAppInfo.setCourtQueryResultFlag(info.getCourtQueryResultFlag());// 人法网结果查询状态

		// 更新咨询中客户姓名为最新的申请客户姓名
		NewCustomerConsult customerConsult = new NewCustomerConsult();
		customerConsult.setCustomerConsultId(Long.valueOf(customerConsultId));

		// 保存历史信息和人法网查询结果以及更新咨询客户姓名
		consultPoolAndApplyPersonService.insert(application, hisAppInfo, hisAppInfoList, hisMobileInfoList,
				partnerInfo, hisFamilyInfoList, arresteds, customerConsult, customerConsultPool);

		return String.valueOf("1");
	}

	// 双人调查接口方法(0:不需要，1：需要)
	public int isDoublePersonSurvey(String borrowAccout, List<GrantedAuthority> roleList) {
		Set<String> roleSet = new HashSet<String>();
		for (GrantedAuthority gAuthority : roleList) {
			roleSet.add(gAuthority.getAuthority());
		}

		if (roleSet.contains("ROLE_SINGLE")) {// 单人调查权限
			return 0;
		}

		if (roleSet.contains("ROLE_LOAN_OFFICER_3")) {// 高级信贷员>20
			BigDecimal boBigDecimal = new BigDecimal(borrowAccout);
			BigDecimal twoBigDecimal = new BigDecimal("200000.0000000");
			if (boBigDecimal.subtract(twoBigDecimal).intValue() > 0) {
				return 1;
			}
		}

		if (roleSet.contains("ROLE_LOAN_OFFICER_2")) {// 中级信贷员>10
			BigDecimal boBigDecimal = new BigDecimal(borrowAccout);
			BigDecimal tenBigDecimal = new BigDecimal("100000.0000000");
			if (boBigDecimal.subtract(tenBigDecimal).intValue() > 0) {
				return 1;
			}
		}

		if (roleSet.contains("ROLE_LOAN_OFFICER_1")) {// 低级信贷员>5
			BigDecimal boBigDecimal = new BigDecimal(borrowAccout);
			BigDecimal fiveBigDecimal = new BigDecimal("50000.0000000");
			if (boBigDecimal.subtract(fiveBigDecimal).intValue() > 0) {
				return 1;
			}
		}

		return 0;
	}

	// 根据咨询id，查询咨询池信息
	@RequestMapping(value = "getConsultPoolId")
	public @ResponseBody
	Message getConsultPoolId(Long customerConsultId) {
		Message message = new Message();
		boolean isSuccess = false;
		CustomerConsultPool customerConsultPool = customerConsultPoolService
				.getConsultPoolByForeignKey(customerConsultId);
		if (customerConsultPool != null) {
			isSuccess = true;
		} else {
			isSuccess = false;
		}
		message.setSuccess(isSuccess);
		return message;
	}

	/**
	 * 根据咨询池id,查询咨询池信息
	 */
	@RequestMapping(value = "getConsultPoolInfo")
	public @ResponseBody
	CustomerConsultPool getConsultPoolInfo(Long consultPoolId) {
		CustomerConsultPool customerConsultPool = customerConsultPoolService.queryCustomerConsultPool(consultPoolId);
		return customerConsultPool;
	}

	// 获得数据字典
	@RequestMapping(value = "/getDataDictionaryList")
	public @ResponseBody
	List<CodeTable> getDataDictionaryList(DataDictionaryVo dictionaryVo) {
		List<CodeTable> lists = new ArrayList<CodeTable>();
		// List<CodeTable> lists = iDataDictionaryService.getSectionList(dictionaryVo);
		List<CodeTable> list = new ArrayList<CodeTable>();
		CodeTable dic = new CodeTable();
		dic.setCodeKey("");
		dic.setCodeVlue("请选择");
		list.add(dic);
		list.addAll(lists);
		return list;
	}
	
	
	/**
     * 获取数据字典中多个 code_value 组成的字符串
     * @param section
     * @param keys
     * @return
     */
    public String getCodeValueStrs(String section, String[] keys) {
        StringBuffer s = new StringBuffer();
        for (String key : keys) {
            if (s.length() > 0) {
                s.append(",");
            }
            s.append(DicUtil.convertCodeKeyToCodeValue(section, key));
        }
        return s.length() == 0 ? null : s.toString();
    }
}
