package com.creditease.rc.action;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.SpecialApply;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.ISpecialApplyService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DESPlus;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.util.PropertiesUtil;
import com.creditease.rc.vo.SpecialApplyVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Controller
@RequestMapping("specialApplyController")
public class SpecialApplyController {

	@Resource
	private ISpecialApplyService specialApplyService;

	@Resource
	private ICreditApplicationService creditApplicationService;

	@RequestMapping(value = "returnSpecialApplyJsp")
	public ModelAndView returnSpecialApplyJsp(SpecialApplyVo specialApplyVo, Long specialApplyId, String add,
			String audit) {
		DESPlus desPlus;
		Map map = new HashMap();
		try {
			desPlus = new DESPlus();
			map.put("clientid", desPlus.encrypt(specialApplyVo.getCreditapplicationId() + "SpecialApply"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String clientid = (String) map.get("clientid");

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

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/jsp/rc/business/specialApply.jsp");

		// 判断specialApplyId是否为空
		if (specialApplyVo.getSpecialApplyId() == null) {
		} else {
			specialApplyVo = specialApplyService.queryApplyVoByPrimaryKey(specialApplyId);
		}
		modelAndView.addObject("clientid", clientid);
		modelAndView.addObject("signTime", signTime);
		modelAndView.addObject("signIp", signIp);
		modelAndView.addObject("add", add);
		modelAndView.addObject("audit", audit);
		modelAndView.addObject("specialApplyVo", specialApplyVo);

		return modelAndView;
	}

	/**
	 * 判断添加特殊申请的时候 特殊申请状态是否为：待审批
	 * creditapplicationId 申请Id
	 * return specialApplyAuditState
	 */
	@RequestMapping(value = "getSpecialApplyAuditState")
	@ResponseBody
	public String getSpecialApplyAuditState(Long creditapplicationId) {
		List<SpecialApplyVo> specialApplyVos = specialApplyService.selectSpecialState(creditapplicationId);
		String specialApplyAuditState = "1";
		if (CommonUtil.isNotEmpty(specialApplyVos)) {
			specialApplyAuditState = "0";
		}
		return specialApplyAuditState;
	}

	/**
	 * 特殊情况申请时是否上传文件
	 * 
	 * @author luohongjie
	 * 
	 */
	@RequestMapping(value = "getImgAmountBySpeciaApply")
	@ResponseBody
	public Map getImgAmountBySpeciaApply(Long creditAppId) {
		Map map = new HashMap();
		DESPlus desPlus;
		try {
			desPlus = new DESPlus();
			int imgAmount = creditApplicationService.getImgAmount(creditAppId + "SpecialApply");
			map.put("imgAmount", imgAmount);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 添加特殊申请信息
	 * 
	 * @author luohongjie
	 * 
	 */
	@RequestMapping(value = "saveSpecialApply")
	public @ResponseBody
	Message saveSpecialApply(SpecialApply specialApply) {
		Message message = new Message();
		boolean success = specialApplyService.insert(specialApply);
		System.out.println(success);
		message.setSuccess(success);
		if (success) {
			message.setMsg("保存信息成功！");
		} else {
			message.setMsg("保存信息失败！");
		}
		return message;
	}

	/**
	 * 审批特殊情况申请信息
	 * 
	 * @author luohongjie
	 * 
	 */
	@RequestMapping(value = "auditSpecialApply")
	public @ResponseBody
	Message auditSpecialApply(SpecialApplyVo specialApplyVo) {
		Message message = new Message();

		boolean success = specialApplyService.dynamicUpdate(specialApplyVo);
		message.setSuccess(success);
		if (success) {
			message.setMsg("保存成功！");
		} else {
			message.setMsg("保存失败！");
		}
		return message;
	}

	/**
	 * 查看特殊申请信息列表
	 * 
	 * @author luohongjie
	 * 
	 */
	@RequestMapping(value = "querySpecialApplyList")
	public @ResponseBody
	Pagination querySpecialApplyList(@RequestParam(required = false) String page,
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
		Map<String, String> pramMap = new HashMap<String, String>();

		pramMap.put("authList", sqlsid);
		pramMap.put("sort", sort);
		pramMap.put("order", order);

		if (!"".equals(paramJsonMap) && paramJsonMap != null) {
			Map<String, String> temp = JsonUtil.getObject(paramJsonMap);
			String businessNumber = temp.get("businessNumber");
			String name = temp.get("name");
			String loanOfficerName = temp.get("loanOfficerName");
			String companyId = temp.get("companyId");
			String auditSpecialApplyState = temp.get("auditSpecialApplyState");
			pramMap.put("businessNumber", businessNumber);
			pramMap.put("name", name);
			pramMap.put("loanOfficerName", loanOfficerName);
			pramMap.put("companyId", companyId);
			pramMap.put("auditSpecialApplyState", auditSpecialApplyState);

		}
		String s = pramMap.get("auditSpecialApplyState");

		pagination = specialApplyService.querySpecialApplyList(pagination, pramMap);
		return pagination;
	}

	/*
	根据信贷申请单ID查询特殊情况申请
     */
	@RequestMapping(value = "searchApplyByCreditId", method = RequestMethod.POST)
	public
	@ResponseBody
	Pagination searchApplyByCreditId(Long id, String page, String rows) {
		Pagination pagination = new Pagination();
		if (!StringUtils.isBlank(page)) {
			pagination.setPage(Integer.valueOf(page));
		}
		if (!StringUtils.isBlank(rows)) {
			pagination.setPageSize(Integer.valueOf(rows));
		}
		pagination = specialApplyService.searchApplyByCreditId(pagination, id);
		return pagination;
	}
}
