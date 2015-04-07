package com.creditease.rc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.creditease.rc.dao.IRlApplyAuditTableDao;
import com.creditease.rc.domain.RlApplyAuditTable;
import com.creditease.rc.domain.RlAuditDetail;
import com.creditease.rc.framework.dao.impl.BaseDao;
/**
 * 小组实现类
 * @author xubingzhao
 *
 */
@Repository
public class RlApplyAuditTableDao extends BaseDao implements IRlApplyAuditTableDao {

	@Override
	public void insert(RlApplyAuditTable rlApplyAuditTable) {
		insertObject("RLAPPLYAUDITTABLE.insertRlApplyAuditTable", rlApplyAuditTable);
	}

	@Override
	public List<RlAuditDetail> selectById(RlApplyAuditTable rlApplyAuditTable) {
		List<RlAuditDetail>list=(List<RlAuditDetail>) queryList("RLAPPLYAUDITTABLE.selectById",rlApplyAuditTable);
		return list;
	}
	
	@Override
	public RlApplyAuditTable selectExamAmount(Integer creditId){
		RlApplyAuditTable rlApplyAuditTable=(RlApplyAuditTable) queryUnique("RLAPPLYAUDITTABLE.selectExamAmount", creditId);
		return rlApplyAuditTable;
	}
	
	@Override
	public List<RlApplyAuditTable> getRemarks(Integer id) {
		List<RlApplyAuditTable> list=(List<RlApplyAuditTable>) this.queryList("RLAPPLYAUDITTABLE.selectRemarks", id);
		return list;
	}

	@Override
	public RlApplyAuditTable selectLastApplyAudit(int creditapplicationId) {
		// TODO Auto-generated method stub
		return (RlApplyAuditTable) queryUnique("RLAPPLYAUDITTABLE.selectLastExamAmount", creditapplicationId);
	}

	@Override
	public List<RlApplyAuditTable> selectAuditRecord(RlApplyAuditTable  rlApplyAuditTable) {
		return (List<RlApplyAuditTable>) queryList("RLAPPLYAUDITTABLE.selectAuditRecord", rlApplyAuditTable);
	}

	@Override
	public void updateRlApplyAuditTable(RlApplyAuditTable r) {
		this.update("RLAPPLYAUDITTABLE.updateRlApplyAuditTable", r);
		
	}
	
	@Override
	public int updateApplyAuditByCreditapplicationId(int creditapplicationId) {
		int i=update("RLAPPLYAUDITTABLE.updateApplyAuditByCreditapplicationId", creditapplicationId);
		return i;
	}

	@Override
	public List<RlApplyAuditTable> selectApplyAuditTable(int creditapplicationId) {
		return (List<RlApplyAuditTable>) this.queryList("RLAPPLYAUDITTABLE.selectApplyAuditTable", creditapplicationId);
	}

	/**
	 * 根据id置为历史记录
	 * @param applyAuditTableId
	 * @return
	 */
	@Override
	public int setToHistoryDataById(int applyAuditTableId) {
		int i = update("RLAPPLYAUDITTABLE.updateApplyAuditById", applyAuditTableId);
		return i;
	}

    @Override
    public Integer getMinExamineAmount(int creditApplicationId){
        return (Integer)queryUnique("RLAPPLYAUDITTABLE.getMinExamineAmount",creditApplicationId);
    }
}
