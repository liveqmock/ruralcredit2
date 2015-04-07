package com.creditease.rc.service.impl;

import com.creditease.rc.dao.IComplianceCheckDao;
import com.creditease.rc.domain.ComplianceCheck;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IComplianceCheckService;
import com.creditease.rc.vo.ComplianceCheckVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zhangwei
 * Date: 2014-8-6
 * Time: 16:25
 */
@Service
public class ComplianceCheckService implements IComplianceCheckService {

    @Resource
    private IComplianceCheckDao complianceCheckDao;

    @Override
    public Pagination searchComplianceCheck(Pagination pagination, String sort, String order, ComplianceCheckVo checkVo) {
        Map m = new HashMap();
        m.put("sort", sort);
        m.put("order", order);
        m.put("checkType", checkVo.getCheckType());
        m.put("fuzzyValue", checkVo.getFuzzyValue());
        m.put("branchName", checkVo.getBranchName());
        m.put("businessNumber", checkVo.getBusinessNumber());
        m.put("materialMan", checkVo.getMaterialMan());
        m.put("loanBegin", checkVo.getLoanBegin());
        m.put("loanEnd", checkVo.getLoanEnd());
        m.put("borrowerMan", checkVo.getBorrowerMan());
        m.put("accountManager", checkVo.getAccountManager());
        m.put("businessStatus", checkVo.getBusinessStatus());
        m.put("authList", checkVo.getAuthList());
        m.put("complaintStatus", checkVo.getComplaintStatus());
        return complianceCheckDao.searchComplianceCheck(m, pagination);
    }

    @Override
    public ComplianceCheckVo searchComplianceCheckById(ComplianceCheckVo vo) {
        return complianceCheckDao.searchComplianceCheckById(vo);
    }

    @Override
    public List<ComplianceCheckVo> searchComplianceCheckByCreditApplicationId(ComplianceCheckVo vo) {
        return complianceCheckDao.searchComplianceCheckByCreditApplicationId(vo);
    }

    @Override
    public boolean updateComplianceCheckRemarkById(ComplianceCheckVo vo) {
        Integer ret = complianceCheckDao.updateComplianceCheckRemarkById(vo);
        return ret != null ? true : false;
    }

    @Override
    public boolean saveComplianceCheck(ComplianceCheckVo vo) {
        return complianceCheckDao.saveComplianceCheck(vo);
    }

    @Override
    public boolean saveComplianceCheckItem(List<ComplianceCheckVo> checkVos) {
        return complianceCheckDao.saveComplianceCheckItem(checkVos);
    }

    @Override
    public boolean updateComplianceCheckItemFlag(ComplianceCheckVo checkVo) {
        return complianceCheckDao.updateComplianceCheckItemFlag(checkVo).intValue() > 0 ? true : false;
    }

    @Override
    public boolean updateComplianceCheckFlag(ComplianceCheckVo checkVo) {
        return complianceCheckDao.updateComplianceCheckFlag(checkVo).intValue() > 0 ? true : false;
    }

    @Override
    public List<ComplianceCheckVo> searchComplianceCheckRecord(ComplianceCheckVo vo) {
        /*查询检查项*/
        return complianceCheckDao.searchComplianceCheckRecord(vo);
    }

    @Override
    public List<ComplianceCheckVo> searchComplianceCheckItemByFlag(ComplianceCheckVo vo) {
        /*查询检查项，传递新旧数据标识*/
        return complianceCheckDao.searchComplianceCheckItemByFlag(vo);
    }

    @Override
    public boolean deleteComplianceCheck(List<ComplianceCheckVo> checkVos) {
        List<Integer> complianceCheckItemIds = new ArrayList<Integer>();
        for (ComplianceCheckVo vo : checkVos) {
            complianceCheckItemIds.add(vo.getComplianceCheckItemId().intValue());
        }
        if (!complianceCheckItemIds.isEmpty()) {
            complianceCheckDao.deleteComplianceCheck(complianceCheckItemIds);
            return true;
        }
        return false;
    }

    @Override
    public List<ComplianceCheckVo> selectComplianceCheckPointConfigByType(ComplianceCheckVo vo) {
        if (vo.getComplianceCheckPointType().equals("0")) {
            vo.setSection("checkpoint_loan");
        } else if (vo.getComplianceCheckPointType().equals("1")) {
            vo.setSection("checkpoint_compliance_v1");
        }
        return complianceCheckDao.selectComplianceCheckPointConfigByType(vo);
    }

    @Override
    public List<ComplianceCheckVo> selectComplianceCheckItemByIdAndType(ComplianceCheckVo vo) {
        return complianceCheckDao.selectComplianceCheckItemByIdAndType(vo);
    }

    @Override
    public List<ComplianceCheckVo> selectComplianceCheckByIdAndTypeAndFlag(ComplianceCheckVo vo) {
        return complianceCheckDao.selectComplianceCheckByIdAndTypeAndFlag(vo);
    }

    @Override
    public boolean selectComplianceConfigByPointAndType(ComplianceCheckVo vo) {
        List<ComplianceCheckVo> vos = complianceCheckDao.selectComplianceConfigByPointAndType(vo);
        return !vos.isEmpty() ? true : false;
    }

    @Override
    public boolean saveComplianceCheckPointConfigByType(ComplianceCheckVo vo) {
        Object ret = complianceCheckDao.saveComplianceCheckPointConfigByType(vo);
        return null != ret ? true : false;
    }

    @Override
    public boolean updateComplianceCheckPointConfigByType(ComplianceCheckVo vo) {
        Object ret = complianceCheckDao.updateComplianceCheckPointConfigByType(vo);
        return null != ret ? true : false;
    }

    @Override
    public boolean deleteComplianceCheckConfigById(ComplianceCheckVo vo) {
        Object ret = complianceCheckDao.deleteComplianceCheckConfigById(vo);
        return null != ret ? true : false;
    }

    @Override
    public boolean selectExistenceOfComplianceCheckItem(ComplianceCheckVo vo) {
        Object ret = complianceCheckDao.selectExistenceOfComplianceCheckItem(vo);
        return null != ret ? true : false;
    }

    @Override
    public boolean checkIfCheckByType(ComplianceCheckVo vo) {
        Object ret = complianceCheckDao.checkIfCheckByType(vo);
        return null != ret ? true : false;
    }

    @Override
    public boolean saveComplianceComplaint(ComplianceCheckVo vo) {
        vo.setComplaintStatus("1");/*待申诉审批*/
        vo.setIsNew("Y");
        Object ret = complianceCheckDao.saveComplianceComplaint(vo);
        return null != ret ? true : false;
    }

    @Override
    public boolean saveComplianceApprove(ComplianceCheckVo vo) {
        vo.setComplaintStatus("2");//审批通过
        if (vo.getApproveResult().equals("1")) {
            vo.setComplaintStatus("3");//审批驳回
        }
        /*修改申诉状态*/
        Object ret_update = complianceCheckDao.updateComplianceComplaint(vo);
        /*保存审批记录*/
        vo.setIsNew("Y");
        Object ret_insert = complianceCheckDao.saveComplianceApprove(vo);
        return null != ret_update && null != ret_insert ? true : false;
    }

    @Override
    public ComplianceCheckVo selectComplianceCheckScoreByIdAndType(ComplianceCheckVo vo) {
        return complianceCheckDao.selectComplianceCheckScoreByIdAndType(vo);
    }

    @Override
    public boolean saveComplianceCheckScore(ComplianceCheckVo vo) {
        Object ret = complianceCheckDao.saveComplianceCheckScore(vo);
        return null != ret ? true : false;
    }

    @Override
    public boolean updateComplianceCheckScoreByIdAndType(ComplianceCheckVo vo) {
        Object ret = complianceCheckDao.updateComplianceCheckScoreByIdAndType(vo);
        return null != ret ? true : false;
    }

    @Override
    public Double selectComplianceCheckScoreByCheckPoint(ComplianceCheckVo vo) {
        return complianceCheckDao.selectComplianceCheckScoreByCheckPoint(vo);
    }

    @Override
    public Pagination exportStatistics(Pagination pagination, String sort, String order, ComplianceCheckVo checkVo) {
        Map m = new HashMap();
        m.put("branchName", checkVo.getBranchName());
        m.put("businessNumber", checkVo.getBusinessNumber());
        m.put("materialMan", checkVo.getMaterialMan());
        m.put("loanBegin", checkVo.getLoanBegin());
        m.put("loanEnd", checkVo.getLoanEnd());
        m.put("authList", checkVo.getAuthList());
        return complianceCheckDao.exportStatistics(m, pagination);
    }

    @Override
    public Pagination exportCheckDetail(Pagination pagination, String sort, String order, ComplianceCheckVo checkVo) {
        Map m = new HashMap();
        m.put("sort", sort);
        m.put("order", order);
        m.put("checkType", checkVo.getCheckType());
        if (checkVo.getCheckType().equals("0") || checkVo.getCheckType().equals("1")) {
            m.put("configType", "1");
        } else if (checkVo.getCheckType().equals("2")) {
            m.put("configType", "0");
        }
        m.put("fuzzyValue", checkVo.getFuzzyValue());
        m.put("branchName", checkVo.getBranchName());
        m.put("businessNumber", checkVo.getBusinessNumber());
        m.put("materialMan", checkVo.getMaterialMan());
        m.put("loanBegin", checkVo.getLoanBegin());
        m.put("loanEnd", checkVo.getLoanEnd());
        m.put("authList", checkVo.getAuthList());
        return complianceCheckDao.exportCheckDetail(m, pagination);
    }

}
