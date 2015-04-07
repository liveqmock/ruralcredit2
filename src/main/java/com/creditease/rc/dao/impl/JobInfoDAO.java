package com.creditease.rc.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IJobInfoDAO;
import com.creditease.rc.domain.JobInfo;
import com.creditease.rc.framework.dao.impl.BaseDao;
/**
 * 
 * @author zhangman
 *
 */
@Repository
public class JobInfoDAO extends BaseDao implements IJobInfoDAO {

	@Resource
	private BaseDao baseDao;


	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	@Override
	public int addJobInfo(JobInfo jobInfo) {
		return (Integer) baseDao.insertObject("JOB.insert", jobInfo);
	}
	
	@Override
	public void bathInsertJobInfo(List<JobInfo> jobInfos) {
		// TODO Auto-generated method stub
		 baseDao.batchInsert("JOB.insert", jobInfos);
	}
	
	@Override
	public void updateJobInfo(JobInfo jobInfo) {
		baseDao.update("JOB.update", jobInfo);		
	}
	@Override
	public List<JobInfo> selectJobInfo(int borrowerServiceAppId) throws ParseException {
		// TODO Auto-generated method stub
		List<JobInfo> jobInfoList = new ArrayList<JobInfo>();
		jobInfoList=(List<JobInfo>) baseDao.queryList("JOB.selectAll", borrowerServiceAppId);
		return jobInfoList;
	}
	
	@Override
	public int deleteJobInfo(int borrowerServiceAppId) {
		return baseDao.delete("JOB.deleteByBSAId", borrowerServiceAppId);
	}
	@Override
	public JobInfo selectByBorrowerServiceAppId(int borrowerServiceAppId) {
		return (JobInfo) baseDao.queryUnique("JOB.selectAll", borrowerServiceAppId);
	}
}
