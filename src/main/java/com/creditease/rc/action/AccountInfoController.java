package com.creditease.rc.action;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.common.DepartmentEntity;
import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.ModifyCatdApp;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IAccountInfoService;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.IModifyCatdAppService;
import com.creditease.rc.util.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 分公司账号
 * 
 * @author zhangman
 * 
 */
@Controller
@RequestMapping("accountInfo")
public class AccountInfoController {

	@Resource
	private IAccountInfoService accountInfoService;
	@Resource
	private SmpWSUtil smpWSUtil;

	@Resource
	private ICreditApplicationService creditApplicationService;

	@Resource
	private IModifyCatdAppService modifyCatdAppService;

	/**
	 * 
	 * @param accountInfo 分公司财务账号信息
	 * @return Message
	 */
	@RequestMapping(value = "addAccountInfo")
	public @ResponseBody
	Message addAccountInfo(AccountInfo accountInfo) {
		Message message = new Message();
		boolean flag = accountInfoService.addAccountInfo(accountInfo);
		message.setSuccess(flag);
		return message;
	}

	/**
	 * 
	 * @param accountInfo 分公司财务账号信息
	 * @return Message
	 */
	@RequestMapping(value = "addOrUpdate")
	public @ResponseBody
	Message addOrUpdateAccountInfo(AccountInfo accountInfo) {
		Message message = new Message();
		boolean flag;
		if (accountInfo.getAccountInfoId() == null || "".equals(accountInfo.getAccountInfoId())) {
			flag = accountInfoService.addAccountInfo(accountInfo);
		} else {
			flag = accountInfoService.updateAccountInfo(accountInfo);
		}
		message.setSuccess(flag);
		return message;
	}

	/**
	 * 
	 * @param accountInfo 分公司财务账号信息
	 * @return AccountInfo
	 */
	@RequestMapping(value = "addOrUpdateReturn")
	public @ResponseBody
	AccountInfo addOrUpdate(AccountInfo accountInfo) {
		return accountInfoService.addOrUpdate(accountInfo);
	}

	/**
	 * 
	 * @param accountInfo 分公司财务账号信息
	 * @param fuzzyValue 模糊查询条件
	 * @param page 第几页
	 * @param rows 每页显示行数
	 * @param accountType 类型
	 * @return 带分页的列表
	 */
	@RequestMapping(value = "selectByPage")
	public @ResponseBody
	Pagination selectAccountInfos(AccountInfo accountInfo, String page, String rows, String fuzzyValue,
			String accountType) {

		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (fuzzyValue != null) {
			return accountInfoService.selectFuzzy(fuzzyValue, pagination, accountType);
		} else {
			return accountInfoService.selectAccountInfos(accountInfo, pagination, accountType);
		}
	}

	/**
	 * smp取部门列表
	 * 
	 * @return 返回部门列表
	 */
	@RequestMapping(value = "getDepartmentList")
	public @ResponseBody
	List departmentList() {
		List<DepartmentEntity> departmentList = smpWSUtil.getDepartmentList();
		return departmentList;
	}

	/**
	 * 模糊查询
	 * 
	 * @param fuzzyValue 模糊查询条件
	 * @param page 第几页
	 * @param rows 每页显示行数
	 * @param accountType 账户类型
	 * @return 分公司财务信息分页列表
	 */
	@RequestMapping(value = "searchFuzzy")
	public @ResponseBody
	Pagination searchFuzzy(String fuzzyValue, String page, String rows, String accountType) {
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		return accountInfoService.selectFuzzy(fuzzyValue, pagination, accountType);
	}

	/**
	 * 按照 id 查找
	 * 
	 * @param accountInfoId 账户ID
	 * @return 账户对象
	 */
	@RequestMapping(value = "selectById")
	public @ResponseBody
	AccountInfo selectById(Integer accountInfoId) {
		return accountInfoService.selectByAccountID(accountInfoId);
	}

	/**
	 * 查询分公司账户
	 * 
	 * @param creditapplicationId id
	 * @return list
	 */
	@RequestMapping(value = "selectCompanyAccounts")
	public @ResponseBody
	List<AccountInfo> selectCompanyAccounts(Integer creditapplicationId) {
		List<AccountInfo> accountInfoList = accountInfoService.selectCompanyAccounts(creditapplicationId);
		return accountInfoList;
	};

	/** 以下为修改银行卡新增方法 **/
	@RequestMapping(value = "selectByCrediApplicationId")
	public @ResponseBody
	AccountInfo selectByCrediApplicationId(Long creditapplicationId) {
		System.out.println(creditapplicationId);
		System.out.println(creditapplicationId);
		System.out.println(creditapplicationId);
		System.out.println(creditapplicationId);
		System.out.println(creditapplicationId);
		System.out.println(creditapplicationId);
		return accountInfoService.selectByCrediApplicationId(creditapplicationId);
	}

	/** 以下为修改银行卡新增方法 **/
	@RequestMapping(value = "returnBankCardJSP")
	public ModelAndView returnBankCardJSP(Long creditapplicationId) {
		System.out.println(creditapplicationId);
		AccountInfo accountInfo = accountInfoService.selectByCrediApplicationId(creditapplicationId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/basicInfo/bankCardNew.jsp");
		modelAndView.addObject("creditapplicationId", creditapplicationId);

		Map map = new HashMap();
		DESPlus desPlus;
		try {
			desPlus = new DESPlus();
			map.put("clientid", desPlus.encrypt(creditapplicationId + "AccountCard"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String clientid = (String) map.get("clientid");

		System.out.println(clientid);
		System.out.println(clientid);
		System.out.println(clientid);
		System.out.println(clientid);
		System.out.println(clientid);

		long nowTime = System.currentTimeMillis();
		String sMillis[] = new String[2];
		try {
			Properties properties = PropertiesUtil.loadProperties("spring/cm/cm.properties");
			DESPlus desPlus2 = new DESPlus();
			sMillis[0] = desPlus2.encrypt(nowTime + "");
			String cmIp = properties.getProperty("cm.hostip");
			String DESIp = desPlus2.encrypt(cmIp);
			sMillis[1] = DESIp;
		} catch (Exception e) {
			e.printStackTrace();
		}

		String signTime = sMillis[0];
		String signIp = sMillis[1];

		modelAndView.addObject("clientid", clientid);
		modelAndView.addObject("signTime", signTime);
		modelAndView.addObject("signIp", signIp);
		modelAndView.addObject("accountInfo", accountInfo);
		return modelAndView;
	}

	/**
	 * 验证修改卡信息文件是否上传
	 * 
	 * @author hq
	 * @Description:
	 * @param creditAppId
	 * @return Map
	 */
	@RequestMapping(value = "getImgAmountByCreditAccountCard")
	@ResponseBody
	public Map getImgAmountByGuaranor(Long creditAppId) {
		Map map = new HashMap();
		DESPlus desPlus;
		try {
			desPlus = new DESPlus();
			System.out.println(creditAppId + "AccountCard");
			System.out.println(creditAppId + "AccountCard");
			System.out.println(creditAppId + "AccountCard");
			System.out.println(creditAppId + "AccountCard");
			int imgAmount = creditApplicationService.getImgAmount(creditAppId + "AccountCard");
			map.put("imgAmount", imgAmount);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "saveModifyCatdApp")
	public @ResponseBody
	Message saveModifyCatdApp(ModifyCatdApp modifyCatdApp) {
		Message message = new Message();
		message = modifyCatdAppService.saveModifyCatdApp(modifyCatdApp);
		return message;
	}

	@RequestMapping(value = "queryAccountInformationChangeList")
	public @ResponseBody
	Pagination queryAccountInformationChangeList(@RequestParam(required = false) String page,
			@RequestParam(required = false) String rows, HttpSession session, String paramJsonMap, String sort,
			String order) {
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		// 添加权限查询
		List<String> authList = SpringSecurityUtils.getAuthList(session);
		String sqlsid = "";
		if (authList.size() > 0) {
			for (String e : authList) {
				if (sqlsid != null) {
					sqlsid = sqlsid + "'" + e + "'" + ",";
				} else {
					sqlsid = "'" + e + "'" + ",";
				}
			}
			sqlsid = sqlsid.substring(0, sqlsid.length() - 1);
		}

		System.out.println(sqlsid);
		System.out.println(sqlsid);
		System.out.println(sqlsid);
		System.out.println(sqlsid);
		Map<String, String> pramMap = new HashMap<String, String>();

		pramMap.put("authList", sqlsid);
		pramMap.put("sort", sort);
		pramMap.put("order", order);

		if (!"".equals(paramJsonMap) && paramJsonMap != null) {
			Map<String, String> temp = JsonUtil.getObject(paramJsonMap);
			String businessNumber = temp.get("businessNumber");
			String name = temp.get("name");
			pramMap.put("businessNumber", businessNumber);
			pramMap.put("name", name);
		}

		pagination = modifyCatdAppService.queryAccountInformationChangeList(pagination, pramMap);

		return pagination;
	}

	@RequestMapping(value = "returnBankCardJSPforApproval")
	public ModelAndView returnBankCardJSPforApproval(Long modifyCatdAppId, Long creditapplicationId, String type) {
		System.out.println(creditapplicationId);

		ModifyCatdApp modifyCatdApp = modifyCatdAppService.queryModifyCatdAppByPrimarKey(modifyCatdAppId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/basicInfo/bankCardVeiwNew.jsp");
		modelAndView.addObject("creditapplicationId", creditapplicationId);
		modelAndView.addObject("modifyCatdAppId", modifyCatdAppId);

		Map map = new HashMap();
		DESPlus desPlus;
		try {
			desPlus = new DESPlus();
			map.put("clientid", desPlus.encrypt(creditapplicationId + "AccountCard"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String clientid = (String) map.get("clientid");

		System.out.println(clientid);

		long nowTime = System.currentTimeMillis();
		String sMillis[] = new String[2];
		try {
			Properties properties = PropertiesUtil.loadProperties("spring/cm/cm.properties");
			DESPlus desPlus2 = new DESPlus();
			sMillis[0] = desPlus2.encrypt(nowTime + "");
			String cmIp = properties.getProperty("cm.hostip");
			String DESIp = desPlus2.encrypt(cmIp);
			sMillis[1] = DESIp;
		} catch (Exception e) {
			e.printStackTrace();
		}

		String signTime = sMillis[0];
		String signIp = sMillis[1];

		modelAndView.addObject("clientid", clientid);
		modelAndView.addObject("signTime", signTime);
		modelAndView.addObject("signIp", signIp);
		modelAndView.addObject("modifyCatdApp", modifyCatdApp);

		return modelAndView;
	}

	@RequestMapping(value = "queryModifyCatdAppByPrimarKey")
	public @ResponseBody
	ModifyCatdApp queryModifyCatdAppByPrimarKey(Long modifyCatdAppId) {

		return modifyCatdAppService.queryModifyCatdAppByPrimarKey(modifyCatdAppId);
	}

	@RequestMapping(value = "approvalModifyCatdApp")
	public @ResponseBody
	Message approvalModifyCatdApp(Long modifyCatdAppId, Long accountInfoId, String type, Long creditapplicationId, ModifyCatdApp app) {
		app.setModifyCatdAppId(modifyCatdAppId);
		app.setAccountInfoId(accountInfoId);
		app.setStatus(type);
		app.setCreditapplicationId(creditapplicationId);
		Message message = modifyCatdAppService.approvalModifyCatdAppBasedPre(app);
		return message;
	}

	// 验证是否有申请中的变更数据
	@RequestMapping(value = "checkApplicating")
	@ResponseBody
	public Message checkApplicating(Integer creditApplicationId) {
		Message message = new Message();
		message.setSuccess(false);
		message.setMsg("修改银行卡申请已提交，待审批结束后，方能提交！");
		List<Long> ModifyCatdAppIds = modifyCatdAppService.queryModifyCatdAppInSHENQINGZHONG(creditApplicationId);
		if (CommonUtil.isEmpty(ModifyCatdAppIds)) {
			message.setSuccess(true);
			message.setMsg("允许申请！");
		}
		return message;
	}
	/** 以上为修改银行卡新增方法 **/

}
