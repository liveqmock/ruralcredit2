package com.creditease.rc.service.impl;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IJobInfoDAO;
import com.creditease.rc.domain.JobInfo;
import com.creditease.rc.service.IJobInfoService;
/**
 * 
 * @author zhangman
 *
 */
@Service
public class JobInfoService implements IJobInfoService {

	@Resource
	private IJobInfoDAO jobInfoDAO;
	@Override
	public JobInfo addOrUpdateJobInfo(JobInfo jobInfo) {
		if(jobInfo != null){
			if(jobInfo.getJobInfoId()!= null && !("".equals(jobInfo.getJobInfoId()))){
				jobInfoDAO.updateJobInfo(jobInfo);
			}else{
				long jobInfoId = jobInfoDAO.addJobInfo(jobInfo);
				jobInfo.setJobInfoId(jobInfoId);
			}
		}
		return jobInfo;
	}

	@Override
	public List<JobInfo> selectJobInfo(int borrowerServiceAppId)
			throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteJobInfo(int borrowerServiceAppId) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public JobInfo selectByBorrowerServiceAppId(int borrowerServiceAppId) {
		return jobInfoDAO.selectByBorrowerServiceAppId(borrowerServiceAppId);
	}
}
