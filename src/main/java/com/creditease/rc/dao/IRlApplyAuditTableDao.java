package com.creditease.rc.dao;

import java.util.List;

import com.creditease.rc.domain.RlApplyAuditTable;
import com.creditease.rc.domain.RlAuditDetail;

/**
 * 小组Dao层
 * @author xubingzhao
 *
 */
public interface IRlApplyAuditTableDao {
	/**
	 * 保存审批记录到数据库
	 * @param rlApplyAuditTable 小组类VO
	 */
	public void insert(RlApplyAuditTable rlApplyAuditTable);
	/**
	 * 根据ID查审批记录
	 * @param rlApplyAuditTable 小组对象 
	 * @return 组员列表
	 */
	public List<RlAuditDetail> selectById(RlApplyAuditTable rlApplyAuditTable);
	/**
	 * 查询审批金额
	 * @param creditId 信贷申请ID
	 * @return 小组审批的金额
	 */
	public RlApplyAuditTable selectExamAmount(Integer creditId);
	/**
	 * 获得历史备注信息
	 * @param id 主键
	 * @return 历史备注信息
	 */
	public List<RlApplyAuditTable> getRemarks(Integer id);
	/**
	 * 查询最新的审批记录
	 * @param creditapplicationId 信贷id
	 * @return 审批记录
	 */
	public  RlApplyAuditTable selectLastApplyAudit(int  creditapplicationId);
	/**
	 * @author zhangman
	 * 查询最新的审批记录
	 * @param rlApplyAuditTable 信贷id
	 * @return 审批记录
	 */
	public List<RlApplyAuditTable> selectAuditRecord(RlApplyAuditTable  rlApplyAuditTable);
	/**
	 * 修改历史记录
	 * @param r 表对象 
	 */
	public void updateRlApplyAuditTable(RlApplyAuditTable r);
	/**
	 * 根据信贷申请单修改审批记录表
	* @author wyf   
	* @param creditapplicationId 
	* @return    
	* int
	 */
	public int updateApplyAuditByCreditapplicationId(int creditapplicationId);
	
	/**
	 * 查询审批记录
	* checkstyle
	* @author wyf   
	* @param creditapplicationId 
	* @return    
	* List<RlApplyAuditTable>
	 */
	public List<RlApplyAuditTable> selectApplyAuditTable(int creditapplicationId);
	
	/**
	 * 根据id置为历史记录
	* checkstyle
	* @author wyf   
	* @param applyAuditTableId 
	* @return    
	* int
	 */
	public int setToHistoryDataById(int applyAuditTableId);

    /*
    获取营业部经理、风险经理前两级审批金额最小值
     */
    public Integer getMinExamineAmount(int creditApplicationId);
}
