package com.creditease.rc.service;

import java.text.ParseException;
import java.util.List;

import com.creditease.rc.domain.JobInfo;
/**
 * 
 * @author zhangman
 *
 */
public interface IJobInfoService {
	/**
	 * 添加或修改工作信息
	 * @param jobInfo 工作信息对象
	 * @return 工作信息对象
	 */
	public JobInfo addOrUpdateJobInfo(JobInfo jobInfo);
	/**
	 * 查询工作信息
	 * @param borrowerServiceAppId 借款申请服务id
	 * @return 工作信息列表
	 * @throws ParseException 转换异常
	 */ 
	public List<JobInfo> selectJobInfo(int borrowerServiceAppId)throws ParseException;
	/**
	 * 按 id 删除
	 * @param borrowerServiceAppId 借款申请服务id 
	 * @return 删除的行数
	 */
	public int deleteJobInfo(int borrowerServiceAppId);
	/**
	 * 按照 借款服务申请 id查询
	 * @param borrowerServiceAppId 借款申请服务id 
	 * @return 工作信息对象
	 */
	public JobInfo selectByBorrowerServiceAppId(int borrowerServiceAppId);
}
