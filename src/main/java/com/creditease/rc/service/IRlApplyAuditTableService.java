package com.creditease.rc.service;

import java.util.List;

import com.creditease.rc.domain.RlApplyAuditTable;

/**
 * 小组表Service接口
 * @author xubingzhao
 *
 */
public interface IRlApplyAuditTableService {
	/**
	 * 获得历史备注信息
	 * @param id 主键
	 * @return 历史备注信息
	 */
	public List<RlApplyAuditTable> getRemarks(Integer id);
	/**
	 * 查询审批金额
	 * @param creditId 信贷申请ID
	 * @return 小组审批的金额
	 */
	public RlApplyAuditTable selectExamAmount(Integer creditId);
	/**
	 * @author zhangman
	 * 查询最新的审批记录
	 * @param creditapplicationId 信贷id
	 * @return 审批记录
	 */
	public  RlApplyAuditTable selectLastApplyAudit(Integer creditapplicationId);
	/**
	 * 查询最新的审批记录
	 * @param rlApplyAuditTable 信贷id
	 * @return 审批记录
	 */
	public List<RlApplyAuditTable> selectAuditRecord(RlApplyAuditTable  rlApplyAuditTable);
	/**
	 * 根据信贷申请单ID修改审批单状态
	 * @param creditapplicationId 
	 * @return  int
	 */
	public int updateApplyAuditByCreditapplicationId(int creditapplicationId);
	
	/**
	 * 查询审批记录
	 * @param creditapplicationId 
	 * @return List<RlApplyAuditTable>
	 */
	public List<RlApplyAuditTable> selectApplyAuditTable(int creditapplicationId);
	
	/**
	 * 置为历史数据，将状态位置为T
	 * @param applyAuditTableId  
	 * @return  int
	 */
	public int setToHistoryDataById(int applyAuditTableId);

    /*
    获取营业部经理、风险经理前两级审批金额最小值
     */
    public Integer getMinExamineAmount(int creditApplicationId);
}
