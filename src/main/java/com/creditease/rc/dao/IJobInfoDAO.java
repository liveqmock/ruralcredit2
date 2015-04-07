package com.creditease.rc.dao;

import java.text.ParseException;
import java.util.List;

import com.creditease.rc.domain.JobInfo;
import com.creditease.rc.framework.dao.IBaseDao;
/**
 * 客户工作信息
 * @author zhangman
 *
 */
public interface IJobInfoDAO  extends IBaseDao{
	/**
	 * 添加工作信息
	 * @param jobInfo 工作信息对象
	 * @return 工作信息id
	 */
	public int addJobInfo(JobInfo jobInfo);
	/**
	 * 
	 * @param jobInfos 
	 * @return 
	 */
	public void bathInsertJobInfo(List<JobInfo> jobInfos);
	/**
	 * 修改工作信息
	 * @param jobInfo 工作信息对象
	 */
	public void updateJobInfo(JobInfo jobInfo);
	/**
	 * 查询工作信息
	 * @param borrowerServiceAppId 借款服务申请id
	 * @return 工作信息列表
	 * @throws ParseException 转换异常
	 */
	public List<JobInfo> selectJobInfo(int borrowerServiceAppId)throws ParseException;
	/**
	 * 按 id删除
	 * @param borrowerServiceAppId 借款服务申请id
	 * @return 删除了几行
	 */
	public int deleteJobInfo(int borrowerServiceAppId);
	/**
	 *  按照 借款服务申请 id查询
	 * @param borrowerServiceAppId 借款服务申请id
	 * @return 工作信息对象
	 */
	public JobInfo selectByBorrowerServiceAppId(int borrowerServiceAppId);
}
