package com.creditease.rc.dao.impl;

import com.creditease.rc.dao.IComplianceCheckDao;
import com.creditease.rc.framework.dao.impl.BaseDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.ComplianceCheckVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhangwei
 * Date: 2014-8-6
 * Time: 16:29
 */
@Repository
public class ComplianceCheckDao extends BaseDao implements IComplianceCheckDao {

    @Override
    public Pagination searchComplianceCheck(Map m, Pagination pagination) {
        /*99-合规检查记录*/
        if (m.get("checkType").equals("99")) {
            return queryForPaginatedList("compliance_check.selectComplianceCheckRecord", "compliance_check.selectComplianceCheckRecordCount", m, pagination.getStartResult(), pagination.getPageSize());
        }
        return queryForPaginatedList("compliance_check.searchComplianceCheck", "compliance_check.searchComplianceCheckCount", m, pagination.getStartResult(), pagination.getPageSize());
    }

    @Override
    public boolean saveComplianceCheck(ComplianceCheckVo vo) {
        return insertObject("compliance_check.saveComplianceCheck", vo) != null ? true : false;
    }

    @Override
    public boolean saveComplianceCheckItem(List<ComplianceCheckVo> checkVos) {
        batchInsert("compliance_check.saveComplianceCheckItem", checkVos);
        return true;
    }

    @Override
    public Integer updateComplianceCheckItemFlag(ComplianceCheckVo checkVo) {
        return update("compliance_check.updateComplianceCheckItemFlag", checkVo);
    }

    @Override
    public Integer updateComplianceCheckFlag(ComplianceCheckVo checkVo) {
        return update("compliance_check.updateComplianceCheckFlag", checkVo);
    }

    @Override
    public List<ComplianceCheckVo> searchComplianceCheckRecord(ComplianceCheckVo vo) {
        return (List<ComplianceCheckVo>) queryList("compliance_check.searchComplianceCheckRecord", vo);
    }

    @Override
    public List<ComplianceCheckVo> searchComplianceCheckItemByFlag(ComplianceCheckVo vo) {
        return (List<ComplianceCheckVo>) queryList("compliance_check.searchComplianceCheckItemByFlag", vo);
    }

    @Override
    public void deleteComplianceCheck(List<Integer> complianceCheckItemIds) {
        batchDelete("compliance_check.deleteComplianceCheck", complianceCheckItemIds);
    }

    @Override
    public ComplianceCheckVo searchComplianceCheckById(ComplianceCheckVo vo) {
        return (ComplianceCheckVo) queryUnique("compliance_check.searchComplianceCheckById", vo);
    }

    @Override
    public List<ComplianceCheckVo> searchComplianceCheckByCreditApplicationId(ComplianceCheckVo vo) {
        return (List<ComplianceCheckVo>) queryList("compliance_check.searchComplianceCheckByCreditApplicationId", vo);
    }

    @Override
    public Integer updateComplianceCheckRemarkById(ComplianceCheckVo vo) {
        return update("compliance_check.updateComplianceCheckRemarkById", vo);
    }

    @Override
    public List<ComplianceCheckVo> selectComplianceCheckPointConfigByType(ComplianceCheckVo vo) {
        return (List<ComplianceCheckVo>) queryList("compliance_check.selectComplianceCheckPointConfigByType", vo);
    }

    @Override
    public List<ComplianceCheckVo> selectComplianceCheckItemByIdAndType(ComplianceCheckVo vo) {
        return (List<ComplianceCheckVo>) queryList("compliance_check.selectComplianceCheckItemByIdAndType", vo);
    }

    @Override
    public List<ComplianceCheckVo> selectComplianceCheckByIdAndTypeAndFlag(ComplianceCheckVo vo) {
        return (List<ComplianceCheckVo>) queryList("compliance_check.selectComplianceCheckByIdAndTypeAndFlag", vo);
    }

    @Override
    public List<ComplianceCheckVo> selectComplianceConfigByPointAndType(ComplianceCheckVo vo) {
        return (List<ComplianceCheckVo>) queryList("compliance_check.selectComplianceConfigByPointAndType", vo);
    }

    @Override
    public Object saveComplianceCheckPointConfigByType(ComplianceCheckVo vo) {
        return insertObject("compliance_check.saveComplianceCheckPointConfigByType", vo);
    }

    @Override
    public Object updateComplianceCheckPointConfigByType(ComplianceCheckVo vo) {
        return update("compliance_check.updateComplianceCheckConfig", vo);
    }

    @Override
    public Object deleteComplianceCheckConfigById(ComplianceCheckVo vo) {
        return delete("compliance_check.deleteComplianceCheckConfigById", vo);
    }

    @Override
    public Object selectExistenceOfComplianceCheckItem(ComplianceCheckVo vo) {
        return queryUnique("compliance_check.selectExistenceOfComplianceCheckItem", vo);
    }

    @Override
    public Object checkIfCheckByType(ComplianceCheckVo vo) {
        return queryUnique("compliance_check.checkIfCheckByType", vo);
    }

    @Override
    public Object saveComplianceComplaint(ComplianceCheckVo vo) {
        return insertObject("compliance_check.saveComplianceComplaint", vo);
    }

    @Override
    public Object saveComplianceApprove(ComplianceCheckVo vo) {
        return insertObject("compliance_check.saveComplianceApprove", vo);
    }

    @Override
    public Object updateComplianceComplaint(ComplianceCheckVo vo) {
        return update("compliance_check.updateComplianceComplaint", vo);
    }

    @Override
    public ComplianceCheckVo selectComplianceCheckScoreByIdAndType(ComplianceCheckVo vo) {
        return (ComplianceCheckVo) queryUnique("compliance_check.selectComplianceCheckScoreByIdAndType", vo);
    }

    @Override
    public Object saveComplianceCheckScore(ComplianceCheckVo vo) {
        return insertObject("compliance_check.saveComplianceCheckScore", vo);
    }

    @Override
    public Object updateComplianceCheckScoreByIdAndType(ComplianceCheckVo vo) {
        return update("compliance_check.updateComplianceCheckScoreByIdAndType", vo);
    }

    @Override
    public Double selectComplianceCheckScoreByCheckPoint(ComplianceCheckVo vo) {
        return (Double) queryUnique("compliance_check.selectComplianceCheckScoreByCheckPoint", vo);
    }

    @Override
    public Pagination exportStatistics(Map m, Pagination pagination) {
        return queryForPaginatedList("compliance_check.exportStatistics", "compliance_check.exportStatisticsCount", m, pagination.getStartResult(), pagination.getPageSize());
    }

    @Override
    public Pagination exportCheckDetail(Map m, Pagination pagination) {
        if (m.get("checkType").equals("1")) {
            return queryForPaginatedList("compliance_check.exportCheckDetail_commissioner", "compliance_check.exportCheckDetailCount_commissioner", m, pagination.getStartResult(), pagination.getPageSize());
        }
        return queryForPaginatedList("compliance_check.exportCheckDetail_customer", "compliance_check.exportCheckDetailCount_customer", m, pagination.getStartResult(), pagination.getPageSize());
    }

}
