package com.creditease.rc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IDepositDao;
import com.creditease.rc.service.IDepositService;
/**
 * 存款 和应收现金
 * @author zhangman
 *
 */
@Service
public class DepositService implements IDepositService {

	@Resource
	private IDepositDao depositDao;
	
	
}
