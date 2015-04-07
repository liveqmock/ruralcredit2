package com.creditease.rc.service;

import java.util.Date;

import org.apache.log4j.Logger;

import com.creditease.rc.domain.BusinessStatusChange;
import com.creditease.rc.domain.BusinessStatusChangeVo;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.LegalProceedings;
import com.creditease.rc.framework.pager.Pagination;
import com.creditease.rc.vo.LegalProceedingsVo;


public interface IBusinessStatusChangeService {

    static Logger logger = Logger.getLogger(IBusinessStatusChangeService.class);
    //保存变更
    long saveChange(BusinessStatusChange change);

    //查询变更记录
    public Pagination selectChangeLog(Pagination pagination,BusinessStatusChangeVo vo,String sort, String order);

    //查询预变更信贷申请
    public Pagination selectPrepareCreditApplication(Pagination pagination,BusinessStatusChangeVo vo);

    //以主键ID查询变更记录
    public BusinessStatusChange selectChangeById(Long changeId);

    //更新记录
    public Integer updateChangeHistoryFlag(BusinessStatusChange change);

    //系统自动任务:判断逾期天数 > 180 天,更新记录数据状态为:不良贷款
    public void updateOverDueGt180();

    //依据信贷申请 ID 获取最新变更记录
    public BusinessStatusChange selectLatestStatusByCreditApplicationId(Long creditApplicationId);

    //检查附件数目，以确定是否上传附件
    public Integer checkAttachment(String clientId);

    //查询变更记录详情
    public Pagination selectChangeLogByNumber(Pagination pagination, String sort, String order,String tabTitle, String number);

    //查询诉讼
    public Pagination viewLegalProceedings(Pagination pagination, LegalProceedingsVo vo, String sort, String order,String sqlSid);

    //查询诉讼跟进记录
    public Pagination viewLegalProceedingsDetail(Pagination pagination, LegalProceedings legalProceedings, String sort, String order);
    
    //保存跟进记录
    public Long saveLegalProceedings(LegalProceedings legalProceedings);

   /**
    * 诉讼或者不良贷款影响催收和回访
    * @param creditapplicationId
    * @param type:0由还款中变为不良贷款货诉讼；1由诉讼或不良贷款变为还款中
    * @param date
    * @return
    */
    public Message affectROANDRV(Long creditapplicationId, String type,Date date);
}
