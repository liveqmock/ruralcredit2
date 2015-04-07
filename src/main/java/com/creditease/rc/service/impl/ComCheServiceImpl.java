package com.creditease.rc.service.impl;

import com.creditease.rc.domain.CodeTable;
import com.creditease.rc.service.IComCheService;
import com.creditease.rc.service.IComplianceCheckService;
import com.creditease.rc.service.IDataDictionaryService;
import com.creditease.rc.vo.ComplianceCheckReq;
import com.creditease.rc.vo.ComplianceCheckRes;
import com.creditease.rc.vo.ComplianceCheckVo;
import com.creditease.rc.vo.DataDictionaryVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by v-weizhang on 2014-12-17.
 */
@Service
public class ComCheServiceImpl implements IComCheService {
    @Resource
    private IComplianceCheckService checkService;

    @Resource
    private IDataDictionaryService dataDictionaryService;

    @Override
    public ComplianceCheckRes searchComCheByType(ComplianceCheckReq req) {
        ComplianceCheckRes res = new ComplianceCheckRes();
        res.setCode("0");
        DataDictionaryVo vo = new DataDictionaryVo();
        vo.setSelectType("E");
        if (org.apache.commons.lang.StringUtils.isEmpty(req.getCheckType())) {
            vo.setSection("checkpoint_loan");
            List<CodeTable> tablesCPL = dataDictionaryService.getCodeTableBySection(vo);
            vo.setSection("errorFile_compliance");
            List<CodeTable> tablesErrorFile = dataDictionaryService.getCodeTableBySection(vo);

            if (!tablesCPL.isEmpty() && !tablesErrorFile.isEmpty()) {
                res.setList(this.codeTable2Dictionary(tablesCPL));
                res.setListErrorFile(this.codeTable2Dictionary(tablesErrorFile));
                res.setCode("1");
                res.setMessage("查询成功");
            } else {
                res.setMessage("查询客服检查信息时发生错误");
            }
        } else {
            /*apply for commissioner*/
        }
        return res;
    }

    @Override
    public ComplianceCheckRes saveComCheByType(ComplianceCheckReq req) {
        ComplianceCheckRes res = new ComplianceCheckRes();
        res.setCode("0");
        if (org.apache.commons.lang.StringUtils.isEmpty(req.getCheckType())) {
            if (null == req.getList() || req.getList().isEmpty()) {
                res.setMessage("保存失败，未传入有效数据!");
            } else {
                if (null != req.getList().get(0).getCreditApplicationId()
                        && StringUtils.isNotEmpty(req.getList().get(0).getErrorFile())
                        && StringUtils.isNotEmpty(req.getList().get(0).getCheckPoint())) {
                    for (ComplianceCheckVo checkVo : req.getList()) {
                        checkVo.setOperatorId("999999");
                        checkVo.setOperatorName("icp");
                        checkVo.setOperateDate(new Date());
                        checkVo.setCheckType("2");
                        checkVo.setIsNew("Y");
                    }
                    ComplianceCheckVo _checkVo = new ComplianceCheckVo();
                    _checkVo.setCreditApplicationId(req.getList().get(0).getCreditApplicationId());
                    _checkVo.setCheckType("2");
                    _checkVo.setIsNew("N");
                    /*保存检查点*/
                    checkService.updateComplianceCheckItemFlag(_checkVo);
                    checkService.saveComplianceCheckItem(req.getList());

                    _checkVo.setComplianceCheckPointType("0");
                    Double score = checkService.selectComplianceCheckScoreByCheckPoint(_checkVo);
                    _checkVo.setComplianceCheckScoreType("1");
                    ComplianceCheckVo checkVo_score = checkService.selectComplianceCheckScoreByIdAndType(_checkVo);
                    /*保存得分*/
                    if (null != checkVo_score) {
                        _checkVo.setComplianceCheckPointScore(score);
                        checkService.updateComplianceCheckScoreByIdAndType(_checkVo);
                    } else {
                        _checkVo.setComplianceCheckPointScore(score);
                        checkService.saveComplianceCheckScore(_checkVo);
                    }
                    res.setCode("1");
                    res.setMessage("保存成功");
                } else {
                    res.setMessage("保存失败，传入数据不完整!");
                }
            }
        } else {
            /*apply for commissioner*/
        }
        return res;
    }

    private List<DataDictionaryVo> codeTable2Dictionary(List<CodeTable> tables) {
        List<DataDictionaryVo> voList = new ArrayList<DataDictionaryVo>();
        DataDictionaryVo vo;
        for (CodeTable table : tables) {
            vo = new DataDictionaryVo();
            vo.setCodeKey(table.getCodeKey());
            vo.setCodeValue(table.getCodeVlue());
            voList.add(vo);
        }
        return voList;
    }
}
