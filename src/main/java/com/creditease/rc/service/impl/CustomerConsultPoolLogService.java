package com.creditease.rc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.security.User;
import com.creditease.rc.dao.ICustomerConsultPoolLogDao;
import com.creditease.rc.domain.CustomerConsultPoolLog;
import com.creditease.rc.framework.exception.AppBusinessException;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.ICustomerConsultPoolLogService;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.vo.CustomerConsultPoolLogVo;

@Service
public class CustomerConsultPoolLogService implements ICustomerConsultPoolLogService {

	/**
	 * @Description 默认构造器
	 */
	public CustomerConsultPoolLogService() {
		// TODO Auto-generated constructor stub
	}

	@Resource
	private ICustomerConsultPoolLogDao customerConsultPoolLogDAO;

	@Override
	public Long insert(CustomerConsultPoolLog log, Map<String, String> contentMap) {
		if (null == log) {
			throw new AppBusinessException(" log is null ");
		}
		if (CommonUtil.isEmpty(log.getOptModule())) {
			throw new AppBusinessException(" log.optModule is null ");
		}
		if (CommonUtil.isEmpty(log.getCurrStatus())) {
			throw new AppBusinessException(" log.getCurrStatus() is null ");
		}
		if (CommonUtil.isEmpty(log.getNextStatus())) {
			throw new AppBusinessException(" log.getNextStatus is null ");
		}
		if (log.getConnectionId() == null) {
			throw new AppBusinessException("关联id is null ");
		}
		if (CommonUtil.isEmpty(log.getConnectionCetegory())) {
			throw new AppBusinessException("关联id类型 is null ");
		}
		if (CommonUtil.isEmpty(log.getCurrConnectionDicSection())) {
			throw new AppBusinessException("变更前关联表状态数据字典section is null ");
		}
		if (CommonUtil.isEmpty(log.getNextConnectionDicSection())) {
			throw new AppBusinessException("变更后关联表状态数据字典section is null ");
		}
		try {
			User user = SpringSecurityUtils.getCurrentUser();
			if (null != user && CommonUtil.isNotEmpty(user.getUserId())) {
				log.setOperatorId(Long.valueOf(user.getUserId()));
			}
			if (null != user && CommonUtil.isNotEmpty(user.getName_zh())) {
				log.setOperatorName(user.getName_zh());
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("用户未登录");
			System.err.println(e.getStackTrace());
		}
		if (null != contentMap && contentMap.size() > 0) {
			String msg = JsonUtil.getJackson(contentMap);
			log.setOptBussinessContent(msg);
		}
		customerConsultPoolLogDAO.insert(log);
		return log.getOperatorId();
	}

	@Override
	public CustomerConsultPoolLog insertOpt(CustomerConsultPoolLog optLog, Map<String, String> contentMap) {
		if (null == optLog) {
			throw new AppBusinessException(" optLog is null ");
		}
		if (CommonUtil.isEmpty(optLog.getOptModule())) {
			throw new AppBusinessException(" optLog.optModule is null ");
		}
		if (CommonUtil.isEmpty(optLog.getCurrStatus())) {
			throw new AppBusinessException(" optLog.getCurrStatus() is null ");
		}
		if (CommonUtil.isEmpty(optLog.getNextStatus())) {
			throw new AppBusinessException(" optLog.getNextStatus is null ");
		}
		if (optLog.getConnectionId() == null) {
			throw new AppBusinessException("关联id is null ");
		}
		if (CommonUtil.isEmpty(optLog.getConnectionCetegory())) {
			throw new AppBusinessException("关联id类型 is null ");
		}
		if (CommonUtil.isEmpty(optLog.getCurrConnectionDicSection())) {
			throw new AppBusinessException("变更前关联表状态数据字典section is null ");
		}
		if (CommonUtil.isEmpty(optLog.getNextConnectionDicSection())) {
			throw new AppBusinessException("变更后关联表状态数据字典section is null ");
		}
		try {
			User user = SpringSecurityUtils.getCurrentUser();
			if (null != user && CommonUtil.isNotEmpty(user.getUserId())) {
				optLog.setOperatorId(Long.valueOf(user.getUserId()));
			}
			if (null != user && CommonUtil.isNotEmpty(user.getName_zh())) {
				optLog.setOperatorName(user.getName_zh());
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("用户未登录");
			System.err.println(e.getStackTrace());
		}
		if (null != contentMap && contentMap.size() > 0) {
			String msg = JsonUtil.getJackson(contentMap);
			optLog.setOptBussinessContent(msg);
		}
		Long operatorId = customerConsultPoolLogDAO.insertOpt(optLog);
		if (operatorId != null) {
			optLog.setOperatorId(operatorId);
			return optLog;
		} else {
			return null;
		}
	}

	@Override
	public List<CustomerConsultPoolLogVo> selectOpt(CustomerConsultPoolLogVo operateLogVO) {
		return customerConsultPoolLogDAO.selectOpt(operateLogVO);
	}

	@Override
	public Pagination selectPagination(CustomerConsultPoolLogVo operateLogVO, Pagination pagination) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("operateLogVO", operateLogVO);
		return customerConsultPoolLogDAO.selectPagination(map, pagination);
	}

	@Override
	public int updateOpt(CustomerConsultPoolLog record) {
		return customerConsultPoolLogDAO.updateOpt(record);
	}

	@Override
	public List<CustomerConsultPoolLogVo> selectById(Long connectionId) {
		CustomerConsultPoolLogVo operateLogVO = new CustomerConsultPoolLogVo();
		operateLogVO.setConnectionId(connectionId);
		return customerConsultPoolLogDAO.selectOpt(operateLogVO);
	}

	@Override
	public int updateApplicationId(Long connectionId, Long applicationId) {
		CustomerConsultPoolLog operateLogVO = new CustomerConsultPoolLog();
		operateLogVO.setConnectionId(connectionId);
		return customerConsultPoolLogDAO.updateOpt(operateLogVO);
	}

	@Override
	public Pagination queryCunsult(CustomerConsultPoolLogVo cunsultLogVo, Pagination pagination) {
		return customerConsultPoolLogDAO.queryCunsult(cunsultLogVo, pagination);
	}
}
