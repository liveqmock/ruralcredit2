package com.creditease.rc.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.rc.dao.IRlApplyAuditTableDao;
import com.creditease.rc.domain.RlApplyAuditTable;
import com.creditease.rc.service.IOperateLogService;
import com.creditease.rc.service.IRlApplyAuditTableService;
import com.creditease.rc.vo.OperateLogBusinessStruct;
/**
 * 小组表Service接口实现类
 * @author xubingzhao
 *
 */
@Service
public class RlApplyAduitTableService implements IRlApplyAuditTableService {
	@Resource
	private IRlApplyAuditTableDao iRlApplyAduitTableDao;
	@Resource
	private IOperateLogService operateLogService;
	@Override
	public List<RlApplyAuditTable> getRemarks(Integer id) {
		List<RlApplyAuditTable> list=iRlApplyAduitTableDao.getRemarks(id);
		return list;
	}
	
	@Override
	public RlApplyAuditTable selectExamAmount(Integer id){
		RlApplyAuditTable rlApplyAuditTable=iRlApplyAduitTableDao.selectExamAmount(id);
		return rlApplyAuditTable;
	}

	@Override
	public RlApplyAuditTable selectLastApplyAudit(Integer creditapplicationId) {
		// TODO Auto-generated method stub
		return iRlApplyAduitTableDao.selectLastApplyAudit(creditapplicationId);
	}

	@Override
	public List<RlApplyAuditTable> selectAuditRecord(RlApplyAuditTable  rlApplyAuditTable) {
		return iRlApplyAduitTableDao.selectAuditRecord(rlApplyAuditTable);
	}
	
	@Override
	public int updateApplyAuditByCreditapplicationId(int creditapplicationId) {
		int i = iRlApplyAduitTableDao.updateApplyAuditByCreditapplicationId(creditapplicationId);
		if(0<i){
			OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
			operateLogBusinessStruct.setResult("撤销审批记录成功");
			operateLogBusinessStruct.setCreditapplicationId((long) creditapplicationId);
			operateLogBusinessStruct.setFunctionCode("20003*");
			operateLogBusinessStruct.setFunctionBussiness("审批撤回");
			operateLogService.insert(operateLogBusinessStruct);
		}
		return i;
	}
	
	@Override
	public List<RlApplyAuditTable> selectApplyAuditTable(int creditapplicationId){
		return iRlApplyAduitTableDao.selectApplyAuditTable(creditapplicationId);
	}

	/* 
	 * 置为历史数据
	 * liuli 2013-05-20
	 */
	@Override
	public int setToHistoryDataById(int applyAuditTableId) {
		int i = iRlApplyAduitTableDao.setToHistoryDataById(applyAuditTableId);
		System.out.println(new Date() +"将applyAuditTableId："+applyAuditTableId+"设置为历史");
		return i ;
	}

    @Override
    public Integer getMinExamineAmount(int creditApplicationId){
        return iRlApplyAduitTableDao.getMinExamineAmount(creditApplicationId);
    }
}
