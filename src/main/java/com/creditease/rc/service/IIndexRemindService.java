package com.creditease.rc.service;

import java.util.List;
import java.util.Map;

import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.vo.CreditapplicationView;
import com.creditease.rc.vo.DepartmentCountInfo;
import com.creditease.rc.vo.IndexRemindVo;

/**
 * 
 * @author Administrator
 * 
 */
public interface IIndexRemindService {
	/**
	 * 查询首页提醒 无用
	 * 
	 * @param creditApplication
	 * @return IndexRemindVo
	 */
	public IndexRemindVo selectRemind(CreditApplication creditApplication);

	/**
	 * 查询 营业部业绩统计 无用
	 * 
	 * @return List<DepartmentCountInfo>
	 */
	public List<DepartmentCountInfo> selectDepartmentCountInfo();

	/**
	 * 从视图中查提醒
	 * 
	 * @param creditApplication 信贷申请单对象
	 * @return List<CreditapplicationView>
	 */
	public List<CreditapplicationView> selectRemindFromView(CreditApplication creditApplication);

	/**
	 * 从库中查询次权限下有多少条申请
	 * 
	 * @param querySultMap 用于查询申请的map
	 * @return 返回申请条数
	 */
	public int conSultCount(Map<String, String> querySultMap);
}
