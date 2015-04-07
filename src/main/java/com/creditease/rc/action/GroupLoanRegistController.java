package com.creditease.rc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.creditease.core.exception.BusinessException;
import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.GroupLoanRegist;
import com.creditease.rc.domain.LoanRegist;
import com.creditease.rc.domain.Message;
import com.creditease.rc.service.IAccountInfoService;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.IGroupLoanRegistService;
import com.creditease.rc.util.BankCardNOLock;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.vo.GroupLoanRegistVo;

/**
 * 放款控制层
 * 
 * @author zhangman
 * 
 */
@Controller
@RequestMapping("GroupLoanRegist")
public class GroupLoanRegistController {
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

	// 放款登记服务
	@Resource
	private IGroupLoanRegistService groupLoanRegistService;
	@Resource
	private IAccountInfoService accountInfoService;
	@Resource
	private ICreditApplicationService creditApplicationService;

	/**
	 * 查询放款明细
	 * 
	 * @param creditApplicationId
	 *            信贷申请单id
	 * @return 审批结果的放款明细
	 */
	@RequestMapping(value = "searchDetail")
	public @ResponseBody
	List<LoanRegist> selectLoanDetail(Integer creditApplicationId) {
		return groupLoanRegistService.selectLoanDetail(creditApplicationId);
	};

	/**
	 * 查询放款登记中的放款明细
	 * 
	 * @param creditApplicationId
	 *            信贷申请单id
	 * @return 放款明细
	 */
	@RequestMapping(value = "searchLoanRegistList")
	public @ResponseBody
	List<LoanRegist> searchLoanRegistList(Integer creditApplicationId) {
		return groupLoanRegistService.selectLoanRegist(creditApplicationId);
	}

	/**
	 * * 放款登记
	 * 
	 * @param groupLoanRegist 放款登记对象字符串
	 * @param loanRegistList 放款登记明细字符串
	 * 
	 * @param uploadFileList 上传文件
	 * @param uploadFileTypeList 上传文件类型
	 * @param originalFileList 文件
	 * @param time 时间
	 * @return true ， false
	 */
	@RequestMapping(value = "loanRegist")
	public @ResponseBody
	boolean loanRegist(String loanRegistList, String groupLoanRegist, String[] uploadFileList,
			String[] uploadFileTypeList, String[] originalFileList, Date time) {

		GroupLoanRegist groupLoadRegistTemp = new GroupLoanRegist();
		CreditApplication creditApplication = new CreditApplication();
		groupLoadRegistTemp = (GroupLoanRegist) JsonUtil.getObject(groupLoanRegist, GroupLoanRegist.class);
		groupLoadRegistTemp.setLoanTime(time);
		creditApplication = (CreditApplication) JsonUtil.getObject(groupLoanRegist, CreditApplication.class);
		List<LoanRegist> loanRegistListTemp = new ArrayList<LoanRegist>();
		LoanRegist[] loanListArray = (LoanRegist[]) JsonUtil.getArray(loanRegistList, LoanRegist.class);
		for (int i = 0; i < loanListArray.length; i++) {
			loanRegistListTemp.add(loanListArray[i]);
		}
		return groupLoanRegistService.addGroupLoanRegist(groupLoadRegistTemp, creditApplication, loanRegistListTemp,
				uploadFileList, uploadFileTypeList, originalFileList);
	}

	/**
	 * 放款确认（回退）
	 * 
	 * @param groupLoanRegist 放款登记对象
	 * @param registStatus 放款确认状态
	 * @param creditApplication 借款申请
	 * @return 成功失败
	 */
	@RequestMapping(value = "loanConfirm")
	public @ResponseBody
	Message loanConfirm(GroupLoanRegist groupLoanRegist, CreditApplication creditApplication, String registStatus) {

		Message message = new Message();
		Integer creditapplicationId = creditApplication.getCreditapplicationId();
		CreditApplication creditApplication2 = creditApplicationService.selectById(creditapplicationId);
		Integer accountInfoId = creditApplication2.getAccountInfoId();

		AccountInfo accountInfoSelect = accountInfoService.selectByAccountID(accountInfoId);
		String account = accountInfoSelect.getAccount();
		if (CommonUtil.isNotEmpty(account)) {
			// 判断是否存在锁
			Boolean b = BankCardNOLock.lockBankCardNO(account);
			if (b) {
				// 获取银行卡号。加锁
				try {
					message = groupLoanRegistService.addOrUpdateGroupLoanRegist(groupLoanRegist, creditApplication,
							registStatus, null);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					throw new BusinessException(e.getMessage());
				} finally {
					// 解锁
					BankCardNOLock.unLockBankCardNO(account);
				}
			} else {
				throw new BusinessException("卡号：" + account + "已锁住，请等待或解锁");
			}

		}
		return message;
	}

	/**
	 * 查询历史备注
	 * 
	 * @param creditApplicationId 信贷申请id
	 * @return 回退的登记列表
	 */
	@RequestMapping(value = "searchHistory")
	public @ResponseBody
	List<GroupLoanRegist> searchHistory(Integer creditApplicationId) {
		GroupLoanRegist groupLoanRegist = new GroupLoanRegist();
		groupLoanRegist.setCreditapplicationId(creditApplicationId);
		groupLoanRegist.setRegistStatus("2");
		return groupLoanRegistService.searchHistory(groupLoanRegist);
	}

	/**
	 * 查询历史备注
	 * 
	 * @param creditApplicationId 信贷申请id
	 * @return 回退的登记列表
	 */
	@RequestMapping(value = "searchConfirm")
	public @ResponseBody
	List<GroupLoanRegist> searchConfirm(Integer creditApplicationId) {
		GroupLoanRegist groupLoanRegist = new GroupLoanRegist();
		groupLoanRegist.setCreditapplicationId(creditApplicationId);
		return groupLoanRegistService.searchConfirm(groupLoanRegist);
	}

	/**
	 * 查询一条放款登记
	 * 
	 * @param creditApplicationId 信贷申请id
	 * @return 放款登记
	 */
	@RequestMapping(value = "searchLoanRegist")
	public @ResponseBody
	GroupLoanRegist searchLoanRegist(Integer creditApplicationId) {
		GroupLoanRegist groupLoanRegist = new GroupLoanRegist();
		groupLoanRegist.setCreditapplicationId(creditApplicationId);
		groupLoanRegist.setRegistStatus("0");
		List<GroupLoanRegist> groupLoanRegistList = groupLoanRegistService.searchHistory(groupLoanRegist);
		if (CommonUtil.isNotEmpty(groupLoanRegistList)) {
			return groupLoanRegistList.get(0);
		} else {
			return null;
		}

	}

	/**
	 * 查询放款登记
	 * 
	 * @param creditApplicationId 信贷申请id
	 * @param registStatus 标记
	 * @return 放款登记
	 */
	@RequestMapping(value = "searchForLook")
	public @ResponseBody
	GroupLoanRegist searchForLook(Integer creditApplicationId, String registStatus) {
		GroupLoanRegist groupLoanRegist = new GroupLoanRegist();
		groupLoanRegist.setCreditapplicationId(creditApplicationId);
		groupLoanRegist.setRegistStatus(registStatus);
		return groupLoanRegistService.selectRegist(groupLoanRegist);
	}

	/**
	 * 放款登记查看
	 * 
	 * @param creditapplicationId 信贷申请id
	 * @return 放款登记
	 */
	@RequestMapping(value = "searchGLR4View")
	public @ResponseBody
	ModelAndView searchGLR4View(Integer creditapplicationId) {
		Map map = groupLoanRegistService.selectForLook(creditapplicationId);
		return new ModelAndView("/jsp/rc/business/groupLoanRegistView.jsp", map);
	}

	/**
	 * 查询放款登记 用于放款确认的查看
	 * zhangman
	 * 
	 * @param groupLoanRegist
	 * @return List<GroupLoanRegistVo>
	 */
	@RequestMapping(value = "searchForConfirm")
	public @ResponseBody
	GroupLoanRegistVo searchForConfirm(GroupLoanRegist groupLoanRegist) {
		// TODO Auto-generated method stub
		return groupLoanRegistService.searchForConfirm(groupLoanRegist);
	}
}
