package com.creditease.rc.dao;

import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.ComplianceCheckVo;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhangwei
 * Date: 2014-8-6
 * Time: 16:27
 */
public interface IComplianceCheckDao extends IBaseDao {

    Pagination searchComplianceCheck(Map m, Pagination pagination);

    boolean saveComplianceCheck(ComplianceCheckVo vo);

    boolean saveComplianceCheckItem(List<ComplianceCheckVo> checkVos);

    Integer updateComplianceCheckItemFlag(ComplianceCheckVo checkVo);

    Integer updateComplianceCheckFlag(ComplianceCheckVo checkVo);

    List<ComplianceCheckVo> searchComplianceCheckRecord(ComplianceCheckVo vo);

    List<ComplianceCheckVo> searchComplianceCheckItemByFlag(ComplianceCheckVo vo);

    void deleteComplianceCheck(List<Integer> complianceCheckItemIds);

    public ComplianceCheckVo searchComplianceCheckById(ComplianceCheckVo vo);

    public List<ComplianceCheckVo> searchComplianceCheckByCreditApplicationId(ComplianceCheckVo vo);

    public Integer updateComplianceCheckRemarkById(ComplianceCheckVo vo);

    public List<ComplianceCheckVo> selectComplianceCheckPointConfigByType(ComplianceCheckVo vo);

    public List<ComplianceCheckVo> selectComplianceCheckItemByIdAndType(ComplianceCheckVo vo);

    public List<ComplianceCheckVo> selectComplianceCheckByIdAndTypeAndFlag(ComplianceCheckVo vo);

    public List<ComplianceCheckVo> selectComplianceConfigByPointAndType(ComplianceCheckVo vo);

    public Object saveComplianceCheckPointConfigByType(ComplianceCheckVo vo);

    public Object updateComplianceCheckPointConfigByType(ComplianceCheckVo vo);

    public Object deleteComplianceCheckConfigById(ComplianceCheckVo vo);

    public Object selectExistenceOfComplianceCheckItem(ComplianceCheckVo vo);

    public Object checkIfCheckByType(ComplianceCheckVo vo);

    public Object saveComplianceComplaint(ComplianceCheckVo vo);

    public Object saveComplianceApprove(ComplianceCheckVo vo);

    public Object updateComplianceComplaint(ComplianceCheckVo vo);

    public ComplianceCheckVo selectComplianceCheckScoreByIdAndType(ComplianceCheckVo vo);

    public Object saveComplianceCheckScore(ComplianceCheckVo vo);

    public Object updateComplianceCheckScoreByIdAndType(ComplianceCheckVo vo);

    public Double selectComplianceCheckScoreByCheckPoint(ComplianceCheckVo vo);

    Pagination exportStatistics(Map m, Pagination pagination);

    Pagination exportCheckDetail(Map m, Pagination pagination);

}
