package com.creditease.rc.action;

import com.creditease.core.security.SpringSecurityUtils;
import com.creditease.rc.domain.*;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.service.IBusinessStatusChangeService;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.IDataDictionaryService;
import com.creditease.rc.service.ISaleInquireService;
import com.creditease.rc.util.DESPlus;
import com.creditease.rc.util.DateUtil;
import com.creditease.rc.util.DicUtil;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.vo.DistributeDepartmentVO;
import com.creditease.rc.vo.LegalProceedingsVo;
import com.creditease.rc.vo.SaleInquireSearchInfoVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("businessStatusChangeController")
public class BusinessStatusChangeController {

    @Resource
    private ICreditApplicationService creditApplicationService;
    @Resource
    private IBusinessStatusChangeService businessStatusChangeService;
    @Resource
    private IDataDictionaryService dataDictionaryService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, "operateDate", new CustomDateEditor(dateFormat, true));
    }

    /**
     * 查询预变更信贷申请
     */
    @RequestMapping(value = "searchBusinessStatusChange", method = RequestMethod.POST)
    public
    @ResponseBody
    Pagination searchBusinessStatusChange(HttpServletRequest request, BusinessStatusChangeVo vo) {
        Pagination pagination = new Pagination();
        pagination = businessStatusChangeService.selectPrepareCreditApplication(pagination, vo);
        return pagination;
    }

    /**
     * 保存变更
     */
    @RequestMapping(value = "saveChange", method = RequestMethod.POST)
    public
    @ResponseBody
    Message saveChange(HttpServletRequest request, BusinessStatusChangeVo vo) {

        Message message = new Message();
        long flag;
        boolean retFlag = true;
        //保存变更
        BusinessStatusChange change = new BusinessStatusChange();
        BusinessStatusChange _change;
        //更新数据历史标记 0 旧数据 1 新数据
        change.setHistoryFlag("1");
        //信贷申请ID
        change.setCreditapplicationId(vo.getCreditapplicationId());
        String name_zh = SpringSecurityUtils.getCurrentUser().getName_zh();
        //操作人
        change.setOperator(name_zh);
        change.setOperateDate(new Date());
        //变更原因
        change.setChangeReason(vo.getChangeReason());
        //备注
        change.setRemark(vo.getRemark());
        //前后状态变更
        List<CodeTable> tables = dataDictionaryService.getSpecifiedDic("", "auditStatus");
        Map<String, String> codeMap = new HashMap<String, String>();
        for (CodeTable table : tables) {
            codeMap.put(table.getCodeVlue(), table.getCodeKey());
        }
        change.setBeforeStatus(codeMap.get(vo.getBeforeStatus()));
//        _change = businessStatusChangeService.selectLatestStatusByCreditApplicationId(vo.getCreditapplicationId());
        if (vo.getType().equals("不良贷款")) {
            change.setOperateType("0");
            if (vo.getBeforeStatus().equals("还款中") || vo.getBeforeStatus().equals("法律诉讼")) {
                change.setAfterStatus(codeMap.get("不良贷款"));
                //更新催收及回访记录
                businessStatusChangeService.affectROANDRV(vo.getCreditapplicationId(), "0", change.getOperateDate());
            } else {
                /**
                 * 返回操作前状态修改为返回还款中
                 */
               /* //返回操作前状态
                if (null != _change) {
                    change.setAfterStatus(_change.getBeforeStatus());
                } else {
                    retFlag = false;
                }*/
                change.setAfterStatus(codeMap.get("还款中"));
                //更新催收及回访记录
                businessStatusChangeService.affectROANDRV(vo.getCreditapplicationId(), "1", change.getOperateDate());
            }
        } else if (vo.getType().equals("法律诉讼")) {
            change.setOperateType("1");
            if (vo.getBeforeStatus().equals("还款中") || vo.getBeforeStatus().equals("不良贷款")) {
                change.setAfterStatus(codeMap.get("法律诉讼"));
            } else {
                /**
                 * 返回操作前状态修改为返回还款中
                 */
               /* //返回操作前状态
                if (null != _change) {
                    change.setAfterStatus(_change.getBeforeStatus());
                } else {
                    retFlag = false;
                }*/
                change.setAfterStatus(codeMap.get("还款中"));
            }
        }

        //更新变更记录历史标记
        if (vo.getChangeId() > 0) {
            _change = businessStatusChangeService.selectChangeById(vo.getChangeId());
            if (null != _change) {
                _change.setHistoryFlag("0");
                businessStatusChangeService.updateChangeHistoryFlag(_change);
            }
        }
        //保存附件ID
        if (StringUtils.isNotEmpty(request.getParameter("clientId"))) {
            change.setAttachmentId(request.getParameter("clientId"));
        }
        //保存
        flag = businessStatusChangeService.saveChange(change);
        if (flag > 0 && retFlag) {
            message.setSuccess(true);
        } else {
            message.setSuccess(false);
            message.setMsg("保存失败");
        }
        return message;
    }

    /**
     * 查询变更记录
     */
    @RequestMapping(value = "selectChangeLog", method = RequestMethod.POST)
    public
    @ResponseBody
    Pagination selectChangeLog(HttpServletRequest request, BusinessStatusChangeVo vo, String page, String rows, String sort, String order) {
        Pagination pagination = new Pagination();
        if (!StringUtils.isBlank(page)) {
            pagination.setPage(Integer.valueOf(page));
        }
        if (!StringUtils.isBlank(rows)) {
            pagination.setPageSize(Integer.valueOf(rows));
        }
        if(StringUtils.isEmpty(vo.getType())){
            vo.setType("nonPerformingLoans");
        }
        pagination = businessStatusChangeService.selectChangeLog(pagination, vo, sort, order);
        return pagination;
    }

    /**
     * 初始化变更页面
     */
    @RequestMapping(value = "initChange")
    ModelAndView initChange(BusinessStatusChangeVo vo) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/jsp/rc/businessStatusChange/businessStatusChangeReason.jsp");
        modelAndView.addObject("vo", vo);
        return modelAndView;
    }

    /**
     * 检查附件
     * @param request
     * @return
     */
    @RequestMapping(value = "checkAttachment", method = RequestMethod.POST)
    @ResponseBody
    public Map checkAttachment(HttpServletRequest request) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            String clientId = request.getParameter("clientId");
            if (StringUtils.isNotEmpty(clientId)) {
                Integer count = businessStatusChangeService.checkAttachment(new DESPlus().decrypt(clientId));
                map.put("count", count.intValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return map;
        }
    }

    /**
     * 查询变更记录详情
     */
    @RequestMapping(value = "selectChangeLogByNumber", method = RequestMethod.POST)
    public
    @ResponseBody
    Pagination selectChangeLogByNumber(HttpServletRequest request, String page, String rows, String sort, String order) {
        Pagination pagination = new Pagination();
        if (!StringUtils.isBlank(page)) {
            pagination.setPage(Integer.valueOf(page));
        }
        if (!StringUtils.isBlank(rows)) {
            pagination.setPageSize(Integer.valueOf(rows));
        }
        pagination = businessStatusChangeService.selectChangeLogByNumber(pagination, sort, order, request.getParameter("tabTitle"),request.getParameter("number"));
        return pagination;
    }

    /**
     * 查询诉讼
     */
    @RequestMapping(value = "viewLegalProceedings", method = RequestMethod.POST)
    public
    @ResponseBody
    Pagination viewLegalProceedings(HttpServletRequest request, LegalProceedingsVo vo, String page, String rows, String sort, String order) {
        Pagination pagination = new Pagination();
        if (!StringUtils.isBlank(page)) {
            pagination.setPage(Integer.valueOf(page));
        }
        if (!StringUtils.isBlank(rows)) {
            pagination.setPageSize(Integer.valueOf(rows));
        }
        // 添加权限查询
        List<String> authList = SpringSecurityUtils.getAuthList(request.getSession());
        String sqlsid = "";
        if (authList.size() > 0) {
            for (String e : authList) {
                if (sqlsid != null) {
                    sqlsid = sqlsid + "'" + e + "'" + ",";
                } else {
                    sqlsid = "'" + e + "'" + ",";
                }
            }
            sqlsid = sqlsid.substring(0, sqlsid.length() - 1);
        }
        pagination = businessStatusChangeService.viewLegalProceedings(pagination,vo,sort,order,sqlsid);
        return pagination;
    }

    /**
     * 查询诉讼跟进记录
     */
    @RequestMapping(value = "viewLegalProceedingsDetail", method = RequestMethod.POST)
    public
    @ResponseBody
    Pagination viewLegalProceedingsDetail(HttpServletRequest request, LegalProceedings legalProceedings, String page, String rows, String sort, String order) {
        Pagination pagination = new Pagination();
        if (!StringUtils.isBlank(page)) {
            pagination.setPage(Integer.valueOf(page));
        }
        if (!StringUtils.isBlank(rows)) {
            pagination.setPageSize(Integer.valueOf(rows));
        }
        pagination = businessStatusChangeService.viewLegalProceedingsDetail(pagination, legalProceedings, sort, order);
        return pagination;
    }

    /**
     * 保存跟进记录
     * @param request
     * @param legalProceedings
     * @return
     */
    @RequestMapping(value = "saveLegalProceedings", method = RequestMethod.POST)
    public @ResponseBody
    Message saveLegalProceedings(HttpServletRequest request, LegalProceedings legalProceedings) {

        Message message = new Message();
        long flag = businessStatusChangeService.saveLegalProceedings(legalProceedings);
        if (flag > 0) {
            message.setSuccess(true);
        } else {
            message.setSuccess(false);
            message.setMsg("保存失败");
        }
        return message;
    }


}
