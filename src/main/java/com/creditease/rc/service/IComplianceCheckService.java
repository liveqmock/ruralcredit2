package com.creditease.rc.service;

import com.creditease.rc.domain.ComplianceCheck;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.ComplianceCheckVo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhangwei
 * Date: 2014-8-6
 * Time: 16:25
 */
public interface IComplianceCheckService {

    /*列表查询*/
    Pagination searchComplianceCheck(Pagination pagination, String sort, String order, ComplianceCheckVo checkVo);

    /*查询检查*/
    ComplianceCheckVo searchComplianceCheckById(ComplianceCheckVo vo);

    List<ComplianceCheckVo> searchComplianceCheckByCreditApplicationId(ComplianceCheckVo vo);

    /*更新检查备注*/
    boolean updateComplianceCheckRemarkById(ComplianceCheckVo vo);

    /*保存检查*/
    boolean saveComplianceCheck(ComplianceCheckVo vo);

    /*保存检查项*/
    boolean saveComplianceCheckItem(List<ComplianceCheckVo> checkVos);

    /*更新检查项表新旧数据标识*/
    boolean updateComplianceCheckItemFlag(ComplianceCheckVo checkVo);

    /*更新检查表新旧数据标识*/
    boolean updateComplianceCheckFlag(ComplianceCheckVo checkVo);

    /*合规检查-查询检查记录（营业部自查、合规检查、客服检查）*/
    List<ComplianceCheckVo> searchComplianceCheckRecord(ComplianceCheckVo vo);

    /*合规检查-查询检查记录（营业部自查、合规检查、客服检查），传递新旧数据标识*/
    List<ComplianceCheckVo> searchComplianceCheckItemByFlag(ComplianceCheckVo vo);

    /*删除检查记录*/
    boolean deleteComplianceCheck(List<ComplianceCheckVo> checkVos);

    /*查询检查点分数配置记录数据*/
    List<ComplianceCheckVo> selectComplianceCheckPointConfigByType(ComplianceCheckVo vo);

    /*查询检查项*/
    List<ComplianceCheckVo> selectComplianceCheckItemByIdAndType(ComplianceCheckVo vo);

    /*查询检查项*/
    List<ComplianceCheckVo> selectComplianceCheckByIdAndTypeAndFlag(ComplianceCheckVo vo);

    /*查询检查点分数配置记录数据*/
    boolean selectComplianceConfigByPointAndType(ComplianceCheckVo vo);

    /*保存检查点分数配置*/
    boolean saveComplianceCheckPointConfigByType(ComplianceCheckVo vo);

    /*更新检查点分数配置信息*/
    boolean updateComplianceCheckPointConfigByType(ComplianceCheckVo vo);

    /*删除检查点分数配置记录数据*/
    boolean deleteComplianceCheckConfigById(ComplianceCheckVo vo);

    /*检查已添加检查项信息*/
    boolean selectExistenceOfComplianceCheckItem(ComplianceCheckVo vo);

    /*检查某笔申请单是否被做过某类型检查*/
    boolean checkIfCheckByType(ComplianceCheckVo vo);

    /*保存申诉*/
    boolean saveComplianceComplaint(ComplianceCheckVo vo);

    /*保存申诉审批*/
    boolean saveComplianceApprove(ComplianceCheckVo vo);

    /*查询某类型检查得分 0-自查扣分 1-客服扣分 2-合规扣分*/
    ComplianceCheckVo selectComplianceCheckScoreByIdAndType(ComplianceCheckVo vo);

    /*保存某类型检查得分*/
    boolean saveComplianceCheckScore(ComplianceCheckVo vo);

    /*更新某类型检查得分*/
    boolean updateComplianceCheckScoreByIdAndType(ComplianceCheckVo vo);

    /*计算某信贷申请某类型检查扣分*/
    Double selectComplianceCheckScoreByCheckPoint(ComplianceCheckVo vo);

    /*导出统计数据*/
    Pagination exportStatistics(Pagination pagination, String sort, String order, ComplianceCheckVo checkVo);

    /*导出明细数据*/
    Pagination exportCheckDetail(Pagination pagination, String sort, String order, ComplianceCheckVo checkVo);

}
