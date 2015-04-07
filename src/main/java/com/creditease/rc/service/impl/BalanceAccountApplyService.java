package com.creditease.rc.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.security.User;
import com.creditease.rc.common.Constants;
import com.creditease.rc.dao.IBalanceAccountApplyDAO;
import com.creditease.rc.domain.BalanceAccountApply;
import com.creditease.rc.domain.BalanceAccountApplyVo;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.util.DESPlus;

@Service
public class BalanceAccountApplyService implements IBalanceAccountApplyService {

	@Resource
	private IBalanceAccountApplyDAO balanceAccountApplyDao;
	@Override
	public boolean insertAccountApply(BalanceAccountApply balanceAccountApply) {
		//获取当前登陆人
		User user = SpringSecurityUtils.getCurrentUser();
		balanceAccountApply.setApplyId(Long.valueOf(user.getUserId()));
		balanceAccountApply.setApplyName(user.getName_zh());
		balanceAccountApply.setApplyTime(new Timestamp(new Date().getTime()));
		//给历史标识赋值
		balanceAccountApply.setHistoryFlag("F");
		//审批状态默认值 0 待审批
		balanceAccountApply.setAuditResult("0");
		Long rows = balanceAccountApplyDao.insert(balanceAccountApply);
		return rows> 0 ? true : false;
	}
	@Override
	public Pagination accountApplyHistoryDateGrid(Map<String, String> queryMap, Pagination pagination) {
		// TODO Auto-generated method stub
		Pagination paginationResult = new Pagination();
		paginationResult =balanceAccountApplyDao.accountApplyHistoryDateGrid(queryMap, pagination);
		return paginationResult;
	}
	/**
	 * 根据主键ID查询数据
	 */
	@Override
	public BalanceAccountApplyVo queryBalanceAccountApplyByPrimaryKey(Long balanceAccountApplyId){
		return balanceAccountApplyDao.queryBalanceAccountApplyByPrimaryKey(balanceAccountApplyId);
	}
	/**
	 * 查询要申请的数据
	 */
	@Override
	public Pagination accountApplyDateGrid(Map<String, String> queryMap, Pagination pagination) {
		
		return balanceAccountApplyDao.queryBalanceAccountApply(queryMap,pagination);
	}
	@Override
	public boolean dynamicUpdate(BalanceAccountApplyVo balanceAccountApplyVo) {
		// TODO Auto-generated method stub
		User user = SpringSecurityUtils.getCurrentUser();
		//审批人ID
		balanceAccountApplyVo.setAuditorId(Long.parseLong(user.getUserId()));
		//审批人姓名
		balanceAccountApplyVo.setAuditorName(user.getName_zh());
		//审批人时间
		balanceAccountApplyVo.setAuditTime(new Date());
		int rows=balanceAccountApplyDao.dynamicUpdate(balanceAccountApplyVo);
		if (rows > 0) {
			return true;
		} else {
			return false;
		}
	}

}
