package com.creditease.rc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IRlAuditDetailDao;
import com.creditease.rc.domain.RlAuditDetail;
import com.creditease.rc.service.IRlAuditDetailService;
/**
 * 
 * @author xubingzhao
 *
 */
@Service
public class RlAuditDetailService implements IRlAuditDetailService {
	@Resource
	private IRlAuditDetailDao iRlAuditDetailDao;
	@Override
	public List<RlAuditDetail> selectChgMount(Integer id) {
		List<RlAuditDetail>list=iRlAuditDetailDao.selectChgMount(id);
		return list;
	}

}
