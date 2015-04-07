package com.creditease.rc.app.sia.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.creditease.rc.app.sia.service.IsiaRollbackService;
import com.creditease.rc.app.sia.vo.ICPStatusSynchronizationVo;
import com.creditease.rc.dao.IAccountInfoDAO;
import com.creditease.rc.dao.IAmountConfirmDAO;
import com.creditease.rc.dao.IBorrowerServiceAppDAO;
import com.creditease.rc.dao.ICreditApplicationDAO;
import com.creditease.rc.dao.IDataDictionaryDao;
import com.creditease.rc.dao.IFinanceMoneyDao;
import com.creditease.rc.dao.IGroupLoanRegistDAO;
import com.creditease.rc.dao.IProtocolMappingDAO;
import com.creditease.rc.domain.AccountInfo;
import com.creditease.rc.domain.AmountConfirm;
import com.creditease.rc.domain.BorrowerServiceApp;
import com.creditease.rc.domain.CreditApplication;
import com.creditease.rc.domain.FinanceMoney;
import com.creditease.rc.domain.GroupLoanRegist;
import com.creditease.rc.domain.LocalReturnSchemeResponse;
import com.creditease.rc.domain.ProtocolMapping;
import com.creditease.rc.service.IAccountInfoService;
import com.creditease.rc.service.ICreditApplicationService;
import com.creditease.rc.service.IOperateLogService;
import com.creditease.rc.service.IReturnPlanService;
import com.creditease.rc.service.IRural2CreditService;
import com.creditease.rc.util.JsonUtil;
import com.creditease.rc.vo.AccountInfoVo2ICP;
import com.creditease.rc.vo.DataDictionaryVo;
import com.creditease.rc.vo.OperateLogBusinessStruct;

/**
 * Created by Administrator on 2014-11-25.
 */
@Service
public class SiaRollbackService implements IsiaRollbackService{
    @Resource
    private IDataDictionaryDao iDataDictionaryDao;
    @Resource
    private IOperateLogService operateLogService;
    @Resource
    private ICreditApplicationDAO creditApplicationDAO;
    private static Logger log = Logger.getLogger(SiaRollbackService.class);
    @Resource
    private IAccountInfoDAO accountInfoDAO;
    @Resource
    private IAmountConfirmDAO amountConfirmDAO;
    @Resource
    private IProtocolMappingDAO protocolMappingDAO;
    @Resource
    private IGroupLoanRegistDAO groupLoanRegistDAO;
    @Resource
	private IBorrowerServiceAppDAO borrowerServiceAppDAO;
    @Resource
	private IAccountInfoService accountInfoService;
    @Resource
	private ICreditApplicationService creditApplicationService;
    @Resource
	private IFinanceMoneyDao financeMoneyDao;
    @Resource
	private IReturnPlanService returnPlanService;
    @Resource
	private IRural2CreditService iRural2CreditService;

    public SiaRollbackService(){}
    @Override
    public void sendMessageBySia(String message){
        
        int result;
        String nextStatus = "";
        try {
            System.out.println(message);
            ICPStatusSynchronizationVo icpStatus = (ICPStatusSynchronizationVo) new ICPStatusSynchronizationVo().toBean(message);
            log.info("状态同步，接收综合信贷入参" + JsonUtil.getJackson(icpStatus));
            if (icpStatus != null) {
                //获取农贷当前申请状态
                CreditApplication creditApplication = new CreditApplication();
                creditApplication.setCreditapplicationId(Integer.valueOf(icpStatus.getApplicationId()));
                creditApplication=creditApplicationDAO.selectCreditApplicationById(creditApplication);
                //同步综合信贷状态
                CreditApplication app = new CreditApplication();
                app.setCreditapplicationId(Integer.valueOf(icpStatus.getApplicationId()));
                nextStatus=icpStatus.getNextApplicationStatus();
                app.setAuditStatus(nextStatus);
                result = creditApplicationDAO.submitAuditing(app);//更新状态
                if("39".equals(nextStatus) || "41".equals(nextStatus)){//等待ICP合同签订
                	
                	//更新本地数据库数据
                	AccountInfoVo2ICP accountInfo = icpStatus.getAccountInfo();
                	if(accountInfo!=null){
                		accountInfo.setCreditapplicationId(icpStatus.getApplicationId());
                		accountInfoDAO.insertOrUpdate(accountInfo);
                		
                		//更新主表信息
                        BorrowerServiceApp borrowerServiceApp = borrowerServiceAppDAO
        				.selectBorrowerLeaderByCreditApplicationId(Integer.parseInt(icpStatus.getApplicationId()));
                        AccountInfo accountInfoParam = new AccountInfo();
                        accountInfoParam.setBorrowerName(borrowerServiceApp.getName());
        				accountInfoParam
        						.setBorrowerCredentialsNumber(borrowerServiceApp
        								.getCredentialsNumber());

        				AccountInfo accountInfoExist = accountInfoService
        						.selctAccountInfo(accountInfoParam);
        				if(accountInfoExist!=null){
        					
        					creditApplication.setAccountInfoId(accountInfoExist.getAccountInfoId());
        					creditApplication.setReturnAccountInfoId(accountInfoExist.getAccountInfoId());
        					creditApplication.setBusinessType("1");
        					creditApplication.setDefaultReturnmentWay("0");
        					creditApplicationService
        					.updateCreditApplicationForAccount(creditApplication);
        				}
                	}
                	AmountConfirm amountConfirm = icpStatus.getAmountConfirm();
                	if(amountConfirm!=null){
                		amountConfirm.setHistoryFlag("0");
                		amountConfirmDAO.addAmountConfirm(amountConfirm);
                		CreditApplication application = new CreditApplication();
                		application.setCreditapplicationId(Integer.parseInt(icpStatus.getApplicationId().toString()));
                		application.setSignagreementDate(amountConfirm.getBeginInterestTime());
                		creditApplicationDAO.updataSignagreementDate(application);
                	}
                	ProtocolMapping protocolMapping = icpStatus.getProtocolMapping();
                	if(protocolMapping!=null){
                		protocolMappingDAO.insert(protocolMapping);
                	}
                }
                if("15".equals(nextStatus)){//还款中
                	GroupLoanRegist groupLoanRegist = icpStatus.getGroupLoanRegist();
                	if(groupLoanRegist!=null){
                		int addGroupLoanRegist = groupLoanRegistDAO.addGroupLoanRegist(groupLoanRegist);
                		FinanceMoney financeMoney = new FinanceMoney();
                		financeMoney.setTradeTime(new Date());
                		financeMoney.setHistoryFlag("F");
                		financeMoney.setType("F");
                		financeMoney.setFinanceStatus("2");
                		financeMoney.setAssociationId(addGroupLoanRegist);
                		financeMoneyDao.insertFinanceMoney(financeMoney);
                		List<LocalReturnSchemeResponse> localReturnSchemeResponses = iRural2CreditService.returnScheme(Long.parseLong(icpStatus.getApplicationId()));
						returnPlanService.insertReturnPlanFromIocalReturnSchemeResponses(localReturnSchemeResponses, Long.parseLong(icpStatus.getApplicationId()));
                	}
                }
                if("40".equals(nextStatus)){
                	//更新本地数据库数据
                	AccountInfoVo2ICP accountInfo = icpStatus.getAccountInfo();
                	if(accountInfo!=null){
                		accountInfo.setCreditapplicationId(icpStatus.getApplicationId());
                		accountInfoDAO.insertOrUpdate(accountInfo);
                	}
                	//金额确认(变成历史)
                	amountConfirmDAO.updateAmountConfirm(Integer.parseInt(icpStatus.getApplicationId()));
                	groupLoanRegistDAO.updateIcp(icpStatus.getApplicationId());
                	financeMoneyDao.updateIcp(icpStatus.getApplicationId());
                }
              //记录日志
                DataDictionaryVo dictionaryVo = new DataDictionaryVo();
                dictionaryVo.setSection("auditStatus");
                dictionaryVo.setCodeKey(nextStatus);
                String codeValueByKey = iDataDictionaryDao.getCodeValueByKey(dictionaryVo);
                if (StringUtils.isNotEmpty(codeValueByKey)) {
                    nextStatus = codeValueByKey;
                }
               
                //记录日志
                OperateLogBusinessStruct operateLog = new OperateLogBusinessStruct();
                operateLog.setCreditapplicationId((Long.parseLong(icpStatus.getApplicationId())));
                operateLog.setFunctionCode("999999");
                dictionaryVo.setCodeKey(creditApplication.getAuditStatus());
                String codeValueUpdateBefore = iDataDictionaryDao.getCodeValueByKey(dictionaryVo);
                if (StringUtils.isNotEmpty(codeValueUpdateBefore)) {
                	operateLog.setRemark("【综合信贷】合同状态同步：" +
                            "同步前状态：" + codeValueUpdateBefore + "," +
                            "同步后状态：" + nextStatus);
                	log.info("【综合信贷】合同状态同步出参，更改申请状态条数" + result + ",更新后状态" + nextStatus);
                }
                
                if (icpStatus.getOperatorId() != null) {
                    operateLog.setOperatorId(icpStatus.getOperatorId());
                }
                operateLog.setOperatorName(icpStatus.getOperator());
                operateLogService.insert(operateLog);//插入日志
                
            }
        } catch (Exception e) {
            log.info("合同状态同步" + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
