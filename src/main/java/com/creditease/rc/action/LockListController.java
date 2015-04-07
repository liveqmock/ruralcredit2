package com.creditease.rc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.Message;
import com.creditease.rc.util.BankCardNOLock;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.LockUtil;

@Controller
@RequestMapping("LockListController")
public class LockListController {

	/**
	 * 
	 * @author 韩大年
	 * @Description: 单个银行卡解锁
	 * @return
	 * @version v1.0
	 *          2013-8-5
	 */
	@RequestMapping(value = "unLock")
	public @ResponseBody
	Message unLock(AccountInfo accountInfo) {
		Message message = new Message();
		message.setSuccess(false);
		if (null != accountInfo) {
			String bankNO = accountInfo.getAccount();
			if (CommonUtil.isNotEmpty(bankNO)) {
				boolean s = BankCardNOLock.unLockBankCardNO(bankNO);
				message.setSuccess(s);
			}
		}
		return message;
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 全部解锁
	 * @return
	 * @version v1.0
	 *          2013-8-5
	 */
	@RequestMapping(value = "unLockAll")
	public @ResponseBody
	Message unLockAll() {
		Message message = new Message();
		message.setSuccess(false);
		LockUtil.getInstance().getLockMap().clear();
		int l = LockUtil.getInstance().getLockMap().size();
		if (l != 0) {
			message.setSuccess(false);
		} else {
			message.setSuccess(true);
		}
		return message;
	}

	/**
	 * 
	 * @author 韩大年
	 * @Description: 查询被加锁的银行卡
	 * @return
	 * @version v1.0
	 *          2013-8-5
	 */
	@RequestMapping(value = "lockStateQuery")
	public @ResponseBody
	List<AccountInfo> lockStateQuery() {
		//Boolean b = BankCardNOLock.lockBankCardNO("9999999999");
		Map bankMap = new HashMap<String, String>();
		bankMap.putAll(LockUtil.getInstance().getLockMap());
		List<String> lockList = new ArrayList<String>();
		lockList.addAll(bankMap.keySet());
		List<AccountInfo> accountInfos = new ArrayList<AccountInfo>();
		for (String l : lockList) {
			AccountInfo accountInfo = new AccountInfo();
			accountInfo.setAccount(l);
			accountInfos.add(accountInfo);
		}
		return accountInfos;
	}
}
