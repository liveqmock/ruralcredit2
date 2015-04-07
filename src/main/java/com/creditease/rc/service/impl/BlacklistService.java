package com.creditease.rc.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;

import com.creditease.rc.dao.impl.BlacklistDao;
import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IBlacklistDao;
import com.creditease.rc.domain.Blacklist;
import com.creditease.rc.domain.CustomerBasicInfo;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IBlacklistService;
import com.creditease.rc.service.ICustomerBasicInfoService;

@Service
public class BlacklistService implements IBlacklistService {

	@Resource
	private IBlacklistDao blacklistDao;
	@Resource
	private ICustomerBasicInfoService customerBasicInfoService;

	@Override
	public boolean dynamicInsert(Blacklist blacklist) {
		// TODO Auto-generated method stub
		return blacklistDao.dynamicInsert(blacklist);
	}

	@Override
	public int dynamicUpdate(Blacklist blacklist) {
		// TODO Auto-generated method stub
		return blacklistDao.dynamicUpdate(blacklist);
	}

	@Override
	public Pagination queryBlicklistList(Map<String, String> queryMap, Pagination pagination) {
		// TODO Auto-generated method stub
		return blacklistDao.queryBlicklistList(queryMap, pagination);
	}

	@Override
	public boolean insertBlacklist(Blacklist blacklist) {
		// TODO Auto-generated method stub
		updateOrInsertCustomer(blacklist);
		return blacklistDao.dynamicInsert(blacklist);
	}

	@Override
	public boolean updateBlacklist(Blacklist blacklist) {
		// TODO Auto-generated method stub
		blacklistDao.dynamicUpdate(blacklist);
		return true;
	}

	@Override
	public int checkIdNumber(String credentialsNumber) {
		// TODO Auto-generated method stub
		return blacklistDao.checkIdNumber(credentialsNumber);
	}

	@Override
	public boolean updateOrInsertCustomer(Blacklist blacklist) {
		// TODO Auto-generated method stub
		CustomerBasicInfo customerBasicInfo = customerBasicInfoService.selectByNumber(blacklist.getCredentialsNumber());
		if (customerBasicInfo == null) {
			customerBasicInfo = new CustomerBasicInfo();
			customerBasicInfo.setName(blacklist.getName());
			customerBasicInfo.setCredentialsNumber(blacklist.getCredentialsNumber());
			customerBasicInfoService.addCustomerBasicInfo(customerBasicInfo);
		} else {
			customerBasicInfo.setName(blacklist.getName());
			customerBasicInfo.setCredentialsNumber(blacklist.getCredentialsNumber());
			customerBasicInfoService.updateCustomerBasicInfo(customerBasicInfo);
		}
		return true;
	}

    /**
     *    定时跑批
     插入逾期信息到数据库
     */
    @Override
    public  void insertBlackListJob() {
        System.out.println("逾期开始时间..."+new Date());
        logger.info("定时任务（逾期天数超180天检查）开始运行...");
        List<Blacklist> blacklists = new ArrayList<Blacklist>();
        //step1 获取需要插入黑名单的客户
        List<CustomerBasicInfo> blackListCustomerInfoList = customerBasicInfoService.getBlackListCustomerInfoList();
        //step2 把客户插入到黑名单
        if(blackListCustomerInfoList.size()>0) {
            for(CustomerBasicInfo customerBasicInfo:blackListCustomerInfoList){
                Blacklist blacklist = new Blacklist();
                blacklist.setBlacklistedOperatorName("sysProcedure");
                blacklist.setBlacklistedReason("逾期大于180天...");
                blacklist.setCredentialsNumber(customerBasicInfo.getCredentialsNumber().toUpperCase());
                blacklist.setBlacklistedTime(new Timestamp(new Date().getTime()));
                blacklist.setName(customerBasicInfo.getName());
                blacklists.add(blacklist);
                if(blacklists.size()>100){
                    blacklistDao.insertNewBlackListList(blacklists);
                    blacklists.clear();
                }
            }
            if(blacklists.size()>0){
                blacklistDao.insertNewBlackListList(blacklists);
            }
        }
        logger.info("定时任务（逾期180天，此借款人变为黑名单用户）结束，"+"共产生"+blackListCustomerInfoList.size()+"条黑名单记录......");
        System.out.println("逾期结束时间..."+new Date());

    }
}