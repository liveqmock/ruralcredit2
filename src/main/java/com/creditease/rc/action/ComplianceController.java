package com.creditease.rc.action;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.core.security.User;
import com.creditease.rc.report.excel.*;
import com.creditease.rc.service.IJsGridReportBaseXLSXService;

import com.creditease.rc.domain.*;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.report.excel.ExcelUtils;
import com.creditease.rc.report.excel.JsGridReportBase;
import com.creditease.rc.report.excel.TableData;
import com.creditease.rc.service.IBorrowerServiceAppService;
import com.creditease.rc.service.IComplianceCheckService;
import com.creditease.rc.service.IDataDictionaryService;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.vo.ComplianceCheckVo;
import com.creditease.rc.vo.DataDictionaryVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: zhangwei
 * Date: 2014-8-6
 * Time: 16:22
 */
@Controller
@RequestMapping("ComplianceController")
public class ComplianceController {

    @Resource
    private IComplianceCheckService complianceCheckService;
    @Resource
    private IBorrowerServiceAppService borrowerServiceAppService;
    @Resource
    private IDataDictionaryService dataDictionaryService;
    @Resource
    private IJsGridReportBaseXLSXService jsGridReportBaseXLSXService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, "operateDate", new CustomDateEditor(dateFormat, true));
    }

    /*
    合规检查-查询列表
     */
    @RequestMapping(value = "searchComplianceCheck", method = RequestMethod.POST)
    public
    @ResponseBody
    Pagination searchComplianceCheck(ComplianceCheckVo checkVo, String page, String rows, String sort, String order, HttpSession session) {
        Pagination pagination = new Pagination();
        if (!StringUtils.isBlank(page)) {
            pagination.setPage(Integer.valueOf(page));
        }
        if (!StringUtils.isBlank(rows)) {
            pagination.setPageSize(Integer.valueOf(rows));
        }
        // 添加权限查询
        List<String> authList = SpringSecurityUtils.getAuthList(session);
        String sqlsid = "";
        if (authList.size() > 0) {
            for (String e : authList) {
                sqlsid = sqlsid + "'" + e + "'" + ",";
            }
            sqlsid = sqlsid.substring(0, sqlsid.length() - 1);
            checkVo.setAuthList(sqlsid);
        }
        pagination = complianceCheckService.searchComplianceCheck(pagination, sort, order, checkVo);
        return pagination;
    }

    /*
    合规检查-初始化数据（业务资料标签页）
     */
    @RequestMapping(value = "initData", method = RequestMethod.POST)
    public
    @ResponseBody
    Object initData(String creditApplicationId) {
        /*借款人相关信息*/
        List<BorrowerServiceApp> borrowerServiceApps = borrowerServiceAppService.selectBorrowerServiceAppList(Integer.valueOf(creditApplicationId));
        BorrowerServiceApp borrowerServiceApp = null;
        if (!borrowerServiceApps.isEmpty()) {
            borrowerServiceApp = borrowerServiceApps.get(0);
        }

        return borrowerServiceApp;
    }

    /*
    合规检查-查询检查记录（营业部自查、合规检查、客服检查）
     */
    @RequestMapping(value = "searchComplianceCheckRecord", method = RequestMethod.POST)
    public
    @ResponseBody
    Object searchComplianceCheckRecord(ComplianceCheckVo vo) {

        Map<String, Object> retMap = new HashMap<String, Object>();
        /*营业部自查记录及备注*/
        vo.setCheckType("0");
        retMap.put("self", complianceCheckService.searchComplianceCheckRecord(vo));
        retMap.put("remark_self", complianceCheckService.searchComplianceCheckByCreditApplicationId(vo));
        /*客服检查记录及备注*/
        vo.setCheckType("2");
        retMap.put("customer", complianceCheckService.searchComplianceCheckRecord(vo));
        retMap.put("remark_customer", complianceCheckService.searchComplianceCheckByCreditApplicationId(vo));
        /*合规检查记录及备注*/
        vo.setCheckType("1");
        retMap.put("commissioner", complianceCheckService.searchComplianceCheckRecord(vo));
        retMap.put("remark_commissioner", complianceCheckService.searchComplianceCheckByCreditApplicationId(vo));
        /*放款列表-查看-最新一次客服检查记录*/
        vo.setCheckType("2");
        vo.setIsNew("Y");
        retMap.put("customer_loan", complianceCheckService.searchComplianceCheckItemByFlag(vo));
        return retMap;
    }

    /*
    获取数据字典
     */
    @RequestMapping(value = "selectDataDictionaryBySections", method = RequestMethod.POST)
    public
    @ResponseBody
    Object selectDataDictionaryBySections(HttpServletRequest request) {
        String checkType = request.getParameter("sections");
        if (StringUtils.isNotEmpty(checkType)) {
            Map<String, String> retMap = new HashMap<String, String>();
            DataDictionaryVo vo = new DataDictionaryVo();
            if (checkType.equals("commissionerCheck") || checkType.equals("selfCheck")) {
                /*数据字典：合规检查*/
                vo.setSelectType("E");
                vo.setSection("object_compliance");
                retMap.put("object_compliance", JsonUtil.getJson(dataDictionaryService.getCodeTableBySection(vo)));
                vo.setSection("systemMaterial_compliance_v1");
                retMap.put("systemMaterial_compliance", JsonUtil.getJson(dataDictionaryService.getCodeTableBySection(vo)));
                vo.setSection("checkpoint_compliance_v1");
                retMap.put("checkpoint_compliance", JsonUtil.getJson(dataDictionaryService.getCodeTableBySection(vo)));
            } else if (checkType.equals("customerCheck")) {
                /*数据字典：客服检查*/
                vo.setSelectType("E");
                vo.setSection("errorFile_compliance");
                retMap.put("errorFile_compliance", JsonUtil.getJson(dataDictionaryService.getCodeTableBySection(vo)));
                vo.setSection("checkpoint_loan");
                retMap.put("checkpoint_loan", JsonUtil.getJson(dataDictionaryService.getCodeTableBySection(vo)));
            }
             /*当前操作人*/
            retMap.put("operator", SpringSecurityUtils.getCurrentUser().getName_zh());
            return retMap;
        }
        return null;
    }

    /*
    合规检查-保存检查
     */
    @RequestMapping(value = "saveComplianceCheck", method = RequestMethod.POST)
    public
    @ResponseBody
    Message saveComplianceCheck(HttpServletRequest request) {
        Message message = new Message(true);
        String inserted = request.getParameter("inserted");
        String deleted = request.getParameter("deleted");
        String checkRemark = request.getParameter("checkRemark");
        String creditApplicationId = request.getParameter("creditApplicationId");
        String checkType = request.getParameter("checkType");
        String complianceCheckId = request.getParameter("complianceCheckId");

        ComplianceCheckVo _checkVo = new ComplianceCheckVo();
        _checkVo.setCreditApplicationId(Long.valueOf(creditApplicationId));
        _checkVo.setCheckType(checkType);
        _checkVo.setCheckRemark(checkRemark);
        /*合规检查备注表(rl_compliance_check)：增加操作人、操作时间*/
        User user = SpringSecurityUtils.getCurrentUser();
        _checkVo.setOperatorId(user.getUserId());
        _checkVo.setOperatorName(user.getName_zh());
        _checkVo.setOperateDate(new Date());
        if (!checkType.equals("2")) {
            _checkVo.setIsNew("Y");
            if (StringUtils.isNotEmpty(complianceCheckId)) {
                _checkVo.setComplianceCheckId(Long.valueOf(complianceCheckId));
                complianceCheckService.updateComplianceCheckRemarkById(_checkVo);
            } else {
                complianceCheckService.saveComplianceCheck(_checkVo);
            }
        } else {
            _checkVo.setIsNew("N");
            complianceCheckService.updateComplianceCheckFlag(_checkVo);
            _checkVo.setIsNew("Y");
            complianceCheckService.saveComplianceCheck(_checkVo);
        }
        if (StringUtils.isNotEmpty(inserted)) {
            List<ComplianceCheckVo> checks = com.creditease.core.utils.JsonUtil.getListFromJsonArrStr(inserted, ComplianceCheckVo.class);
            Long _complianceCheckId = null;
            if (checkType.equals("2")) {
                 /*仅当客服检查时，保存检查项关联检查ID*/
                ComplianceCheckVo complianceCheckVo = new ComplianceCheckVo();
                complianceCheckVo.setCreditApplicationId(_checkVo.getCreditApplicationId());
                complianceCheckVo.setCheckType(checkType);
                complianceCheckVo.setIsNew("Y");
                List<ComplianceCheckVo> complianceCheckVoList = complianceCheckService.selectComplianceCheckByIdAndTypeAndFlag(complianceCheckVo);
                if (!complianceCheckVoList.isEmpty()) {
                    complianceCheckVo = complianceCheckVoList.listIterator().next();
                    _complianceCheckId = complianceCheckVo.getComplianceCheckId();
                }
            }
            for (ComplianceCheckVo checkVo : checks) {
                checkVo.setCreditApplicationId(Long.valueOf(creditApplicationId));
                checkVo.setOperatorId(user.getUserId());
                checkVo.setOperatorName(user.getName_zh());
                checkVo.setOperateDate(new Date());
                checkVo.setCheckType(checkType);
                checkVo.setCheckPoint(StringUtils.isNotEmpty(checkVo.getCheckPointLoan()) == true ? checkVo.getCheckPointLoan() : checkVo.getCheckPoint());
                checkVo.setIsNew("Y");
                checkVo.setComplianceCheckId(_complianceCheckId != null ? _complianceCheckId : null);
            }
            ComplianceCheckVo vo;
            /*仅当客服检查时，允许保存重复检查项*/
                /*2014-10-31 客户需求变更：三种类型检查（放款列表-客服检查、合规检查、合规自查）同一次可添加重复检查点记录*/
            /*if (!checkType.equals("2")) {
                for (Iterator<ComplianceCheckVo> voIterator = checks.iterator(); voIterator.hasNext(); ) {
                    vo = voIterator.next();
                    if (complianceCheckService.selectExistenceOfComplianceCheckItem(vo)) {
                        voIterator.remove();
                    }
                }
            }*/
            /*for (int i = 0; i < checks.size() - 1; i++) {
                for (int j = checks.size() - 1; j > i; j--) {
                    if (checks.get(j).getComplianceObject().equals(checks.get(i).getComplianceObject())
                            && checks.get(j).getSystemMaterial().equals(checks.get(i).getSystemMaterial())
                            && checks.get(j).getCheckPoint().equals(checks.get(i).getCheckPoint())) {
                        checks.remove(j);
                    }
                }
            }*/
            /*添加新检查项数据前，更新之前检查项数据为旧数据，即 isNew: Y or N*/
            _checkVo.setIsNew("N");
            complianceCheckService.updateComplianceCheckItemFlag(_checkVo);
            complianceCheckService.saveComplianceCheckItem(checks);
        }
        if (StringUtils.isNotEmpty(deleted)) {
            List<ComplianceCheckVo> checkVos = com.creditease.core.utils.JsonUtil.getListFromJsonArrStr(deleted, ComplianceCheckVo.class);
            complianceCheckService.deleteComplianceCheck(checkVos);
        }
        /*保存或更新某类型检查得分信息*/
        if (StringUtils.isNotEmpty(inserted) || StringUtils.isNotEmpty(deleted)) {
            ComplianceCheckVo checkVo_search = new ComplianceCheckVo();
            checkVo_search.setCreditApplicationId(Long.valueOf(creditApplicationId));
            checkVo_search.setCheckType(checkType);
            if (checkType.equals("0") || checkType.equals("1")) {
                checkVo_search.setComplianceCheckPointType("1");
            } else {
                checkVo_search.setComplianceCheckPointType("0");
            }
            Double score = complianceCheckService.selectComplianceCheckScoreByCheckPoint(checkVo_search);
            String complianceCheckScoreType;
            if (checkType.equals("1")) {
                complianceCheckScoreType = "2";
            } else if (checkType.equals("2")) {
                complianceCheckScoreType = "1";
            } else {
                complianceCheckScoreType = checkType;
            }
            checkVo_search.setComplianceCheckScoreType(complianceCheckScoreType);
            ComplianceCheckVo checkVo_score = complianceCheckService.selectComplianceCheckScoreByIdAndType(checkVo_search);
            if (null != checkVo_score) {
                checkVo_score.setComplianceCheckPointScore(score);
                checkVo_score.setCreditApplicationId(Long.valueOf(creditApplicationId));
                checkVo_score.setComplianceCheckScoreType(complianceCheckScoreType);
                complianceCheckService.updateComplianceCheckScoreByIdAndType(checkVo_score);
            } else {
                ComplianceCheckVo checkVo_insert = new ComplianceCheckVo();
                checkVo_insert.setComplianceCheckPointScore(score);
                checkVo_insert.setCreditApplicationId(Long.valueOf(creditApplicationId));
                checkVo_insert.setComplianceCheckScoreType(complianceCheckScoreType);
                complianceCheckService.saveComplianceCheckScore(checkVo_insert);
            }
        }
        return message;
    }

    /*
    根据section、parentId获取数据字典
     */
    @RequestMapping(value = "getDataDictionaryBySectionAndParentId", method = RequestMethod.POST)
    public
    @ResponseBody
    List<CodeTable> getDataDictionaryBySectionAndParentId(HttpServletRequest request) {
        String section = request.getParameter("section");
        String parentId = request.getParameter("parentId");
        List<CodeTable> codeTables = new ArrayList<CodeTable>();
        if (StringUtils.isNotEmpty(section) && StringUtils.isNotEmpty(parentId)) {
            codeTables = dataDictionaryService.selectBySectionAndParentId(section, parentId);
        } else {
            System.out.println("==========>合规检查：获取数据字典发生错误，{字典标识：" + section + "}或{字典父Id：" + parentId + "}为空" + "<==========");
        }
        return codeTables;
    }

    /*
    合规检查-查询检查点分数配置信息
     */
    @RequestMapping(value = "selectComplianceCheckPointConfigByType", method = RequestMethod.POST)
    public
    @ResponseBody
    List<ComplianceCheckVo> selectComplianceCheckPointConfigByType(ComplianceCheckVo vo) {
        return complianceCheckService.selectComplianceCheckPointConfigByType(vo);
    }

    /*
    合规检查-保存检查点分数配置信息
     */
    @RequestMapping(value = "saveComplianceCheckConfig", method = RequestMethod.POST)
    public
    @ResponseBody
    Message saveComplianceCheckConfig(ComplianceCheckVo vo) {
        Message message = new Message(false);
        boolean retDB = complianceCheckService.selectComplianceConfigByPointAndType(vo);
        if (!retDB) {
            boolean ret = complianceCheckService.saveComplianceCheckPointConfigByType(vo);
            if (ret) {
                message = new Message(true, "保存成功！");
            }
        } else {
            message.setMsg("该检查项已配置分数！");
        }
        return message;
    }

    /*
    合规检查-更新检查点分数配置信息
     */
    @RequestMapping(value = "updateComplianceCheckConfig", method = RequestMethod.POST)
    public
    @ResponseBody
    Message updateComplianceCheckConfig(ComplianceCheckVo vo) {
        Message message = new Message(false);
        boolean retDB = complianceCheckService.selectComplianceConfigByPointAndType(vo);
        if (retDB) {
            boolean ret = complianceCheckService.updateComplianceCheckPointConfigByType(vo);
            if (ret) {
                message = new Message(true, "保存成功！");
            }
        }
        return message;
    }

    /*
    合规检查-更新检查点分数配置信息
     */
    @RequestMapping(value = "deleteComplianceCheckConfigById", method = RequestMethod.POST)
    public
    @ResponseBody
    Message deleteComplianceCheckConfigById(ComplianceCheckVo vo) {
        Message message = new Message(false, "删除失败！");
        boolean ret = complianceCheckService.deleteComplianceCheckConfigById(vo);
        if (ret) {
            message = new Message(true, "删除成功！");
        }
        return message;
    }

    /*
    检查已添加检查项信息
     */
    @RequestMapping(value = "selectExistenceOfComplianceCheckItem", method = RequestMethod.POST)
    public
    @ResponseBody
    Boolean selectExistenceOfComplianceCheckItem(ComplianceCheckVo vo) {
        return complianceCheckService.selectExistenceOfComplianceCheckItem(vo);
    }

    /*
    导出统计数据
     */
    @RequestMapping(value = "exportStatistics")
    public void exportStatistics(ComplianceCheckVo vo, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/msexcel;charset=UTF-8");
        Pagination pagination = new Pagination();
        pagination.setPageSize(0);
        // 添加权限查询
        List<String> authList = SpringSecurityUtils.getAuthList(request.getSession());
        String sqlsid = "";
        if (authList.size() > 0) {
            for (String e : authList) {
                sqlsid = sqlsid + "'" + e + "'" + ",";
            }
            sqlsid = sqlsid.substring(0, sqlsid.length() - 1);
            vo.setAuthList(sqlsid);
        }
        pagination = complianceCheckService.exportStatistics(pagination, null, null, vo);
        List<ComplianceCheckVo> list = pagination.getRows();
        String title = "合规检查-统计数据";
        String[] hearders = new String[]{"营业部", "资料提交人", "合规检查笔数", "合规检查扣分", "客服检查笔数", "客服检查扣分", "总得分"};
        String[] fields = new String[]{"COMPANY_NAME", "CREATE_LOAN_OFFICER_NAME", "CHECK_COUNT_COM_MODIFY", "SCORE_OTHER", "CHECK_COUNT_CUS_MODIFY", "SCORE_CUSTOMER", "TOTAL_SCORE"};
        if (list.isEmpty()) {
            list.add(new ComplianceCheckVo());
            fields = new String[]{"", "", "", "", "", "", ""};
        }
        TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders), fields);
        JsGridReportBase report;
        try {
           /* report = new JsGridReportBase(request, response);
            report.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);*/
            jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
            jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    导出明细检查数据
     */
    @RequestMapping(value = "exportCheckDetail")
    public void exportCheckDetail(ComplianceCheckVo vo, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/msexcel;charset=UTF-8");
        Pagination pagination = new Pagination();
        pagination.setPageSize(0);
        // 添加权限查询
        List<String> authList = SpringSecurityUtils.getAuthList(request.getSession());
        String sqlsid = "";
        if (authList.size() > 0) {
            for (String e : authList) {
                sqlsid = sqlsid + "'" + e + "'" + ",";
            }
            sqlsid = sqlsid.substring(0, sqlsid.length() - 1);
            vo.setAuthList(sqlsid);
        }
        pagination = complianceCheckService.exportCheckDetail(pagination, null, null, vo);
        List<ComplianceCheckVo> list = pagination.getRows();
        /*合规明细数据*/
        String title = "合规检查-导出合规明细数据";
        String[] hearders = new String[]{"营业部", "业务单号", "客户经理", "资料提交人", "放款日期 ", "对象", "业务资料", "检查点", "备注", "扣分", "检查人", "检查时间"};
        String[] fields = new String[]{"COMPANY_NAME", "BUSINESS_NUMBER", "LOAN_OFFICER_NAME", "CREATE_LOAN_OFFICER_NAME", "SIGNAGREEMENT_DATE", "COMPLIANCE_OBJECT", "SYSTEM_MATERIAL", "COMPLIANCE_CHECK_POINT", "COMPLIANCE_CHECK_ADVICE", "COMPLIANCE_CHECK_SCORE", "OPERATOR_NAME", "OPERATE_DATE"};
        /*客服明细数据*/
        if (vo.getCheckType().equals("2")) {
            title = "合规检查-导出客服明细数据";
            hearders = new String[]{"营业部", "业务单号", "客户经理", "资料提交人", "放款日期 ", "错误文件", "检查点", "备注与意见", "扣分", "检查人", "检查时间"};
            fields = new String[]{"COMPANY_NAME", "BUSINESS_NUMBER", "LOAN_OFFICER_NAME", "CREATE_LOAN_OFFICER_NAME", "SIGNAGREEMENT_DATE", "ERROR_FILE", "COMPLIANCE_CP_LOAN", "COMPLIANCE_CHECK_ADVICE", "COMPLIANCE_CHECK_SCORE", "OPERATOR_NAME", "OPERATE_DATE"};
        }
        if (list.isEmpty()) {
            list.add(new ComplianceCheckVo());
            fields = new String[]{"", "", "", "", "", "", "", "", "", "", "", ""};
            if (vo.getCheckType().equals("2")) {
                fields = new String[]{"", "", "", "", "", "", "", "", "", "", ""};
            }
        }
        TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders), fields);
        JsGridReportBase report;
        try {
           /* report = new JsGridReportBase(request, response);
            report.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);*/
            jsGridReportBaseXLSXService.jsGridReportBaseXLSXService(request,response);
            jsGridReportBaseXLSXService.exportToExcel(title, SpringSecurityUtils.getCurrentUserName(), td);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
   查询检查项记录
    */
    @RequestMapping(value = "selectComplianceCheckItemByIdAndType", method = RequestMethod.POST)
    public
    @ResponseBody
    List<ComplianceCheckVo> selectComplianceCheckItemByIdAndType(ComplianceCheckVo vo) {
        return complianceCheckService.selectComplianceCheckItemByIdAndType(vo);
    }

    /*
    检查某笔申请单是否被做过某类型检查
     */
    @RequestMapping(value = "checkIfCheckByType", method = RequestMethod.POST)
    public
    @ResponseBody
    Boolean checkIfCheckByType(ComplianceCheckVo vo) {
        return complianceCheckService.checkIfCheckByType(vo);
    }

    /*
    保存申诉、申诉审批 [0-未申诉 1-待申诉审批 2-申诉通过 3-申诉驳回]
     */
    @RequestMapping(value = "saveComplianceComplaint", method = RequestMethod.POST)
    public
    @ResponseBody
    Boolean saveComplianceComplaint(ComplianceCheckVo vo) {
        User currentUser = SpringSecurityUtils.getCurrentUser();
        vo.setOperatorId(currentUser.getUserId());
        vo.setOperatorName(currentUser.getName_zh());
        vo.setOperateDate(new Date());
        return vo.getComplainingType().equals("complaining") ? complianceCheckService.saveComplianceComplaint(vo) : complianceCheckService.saveComplianceApprove(vo);
    }
}
