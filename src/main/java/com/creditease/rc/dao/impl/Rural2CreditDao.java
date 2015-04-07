package com.creditease.rc.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.creditease.rc.dao.*;
import com.creditease.rc.service.*;
import com.creditease.rc.service.impl.OverDueDataService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.creditease.rc.common.Constants;
import com.creditease.rc.domain.FinanceMoney;
import com.creditease.rc.domain.LocalReturnSchemeResponse;
import com.creditease.rc.domain.Message;
import com.creditease.rc.domain.OverDueObj;
import com.creditease.rc.framework.dao.IBaseDao;
import com.creditease.rc.util.CommonUtil;
import com.creditease.rc.util.DicUtil;
import com.creditease.rc.vo.CreditApplicationVo;
import com.creditease.rc.vo.OperateLogBusinessStruct;
import com.creditease.rc.vo.ReceiveCreditObj;

/**
 * 
 * @author haoqiang
 * 
 */
@Repository
public class Rural2CreditDao implements IRural2CreditDao {

	@Resource
	private IBaseDao baseDao;
	private static Logger logger = Logger.getLogger(OverDueDataService.class);
	@Resource
	private IMessageInfoService messageInfoService;
	@Resource
	private IOperateLogService operateLogService;
	@Resource
	private ICreditApplicationDAO creditApplicationDAO;
	@Resource
	private IReturnPlanService returnPlanService;
	@Resource
	private IRural2CreditService iRural2CreditService;
	@Resource
	private IorgamsService iorgamsService;
	@Resource
	private IAmountConfirmDAO amountConfirmDAO;
	@Resource
	private IGroupLoanRegistDAO groupLoanRegistDAO;
	@Resource
	private IFinanceMoneyDao financeMoneyDao;
	@Resource
	private IMessageInfoDao messageInfoDao;
	@Override
	public List<Map<String, String>> queryBizIdAndSysguId(List<String> bizIdList) {
		// TODO Auto-generated method stub
		return (List<Map<String, String>>) baseDao.queryList("RURAL2CREDIT.queryBizIdAndSysguId", bizIdList);
	}

	@Override
	public List<Map<String, String>> queryBizIdAndSysguIdByBusinessNumber(String businessNumber) {
		// TODO Auto-generated method stub
		return (List<Map<String, String>>) baseDao.queryList("RURAL2CREDIT.queryBizIdAndSysguIdByBusinessNumber",
				businessNumber);
	}

	@Override
	public String querySysGuidByBusinessNumber(String businessNumber) {
		// TODO Auto-generated method stub
		return (String) baseDao.queryUnique("RURAL2CREDIT.querySysGuidByBusinessNumber", businessNumber);
	}

	@Override
	public List<String> querySysGuidListByAccountInfoId(Integer accountInfoId) {
		// TODO Auto-generated method stub
		return (List<String>) baseDao.queryList("RURAL2CREDIT.querySysGuidListByAccountInfoId", accountInfoId);
	}

	@Override
	public Message updateStatusByReceiveCreditMsg(List<ReceiveCreditObj> receiveCreditObjs) throws ParseException {
		// TODO Auto-generated method stub
		Message message = new Message();
		List<CreditApplicationVo> creditApplicationVos = new ArrayList<CreditApplicationVo>();
		//此列表存储还款完成或者提前还款完成的sys_uuid
		List<String> sysIdList = new ArrayList<String>();
		for (ReceiveCreditObj receiveCreditObj : receiveCreditObjs) {
			CreditApplicationVo creditApplicationVo = new CreditApplicationVo();
			String getApplyId = receiveCreditObj.getApplyId();
			String getType = receiveCreditObj.getType();
			List<String> UUIdList = new ArrayList<String>();
			UUIdList.add(getApplyId);
			List<OverDueObj> dueObjs = creditApplicationDAO.queryCreditApplicationVoListByUUIdList(UUIdList);
			Long creditapplicationId = null;
			if (CommonUtil.isNotEmpty(dueObjs)) {
				OverDueObj dueObj = dueObjs.get(0);
				creditapplicationId = dueObj.getCreditApplicationId();
			}
			if ("1".equals(getType)) {// 正常还款完成
				creditApplicationVo.setSysGuid(getApplyId);
				creditApplicationVo.setAuditStatus(Constants.STATE_16);
				creditApplicationVos.add(creditApplicationVo);
				OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
				operateLogBusinessStruct.setCreditapplicationId(creditapplicationId);
				operateLogBusinessStruct.setFunctionCode("400010");
				String value = DicUtil.convertCodeKeyToCodeValue("auditStatus", Constants.STATE_16);
				/*operateLogBusinessStruct.setRemark("uuid：" + getApplyId + "申请状态变为：" + value + "参数：" + getApplyId + ","
						+ getType);*/
				operateLogBusinessStruct.setRemark("处理结果=正常还款完成时 申请状态变为：" + value);
				operateLogService.insert(operateLogBusinessStruct);
				sysIdList.add(getApplyId);
			} else if ("2".equals(getType)) {// 提前还款完成
				creditApplicationVo.setSysGuid(getApplyId);
				creditApplicationVo.setAuditStatus(Constants.STATE_20);
				creditApplicationVos.add(creditApplicationVo);
				OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
				operateLogBusinessStruct.setCreditapplicationId(creditapplicationId);
				operateLogBusinessStruct.setFunctionCode("400020");
				String value = DicUtil.convertCodeKeyToCodeValue("auditStatus", Constants.STATE_20);
				/*operateLogBusinessStruct.setRemark("uuid：" + getApplyId + "申请状态变为：" + value + "参数：" + getApplyId + ","
						+ getType);*/
				operateLogBusinessStruct.setRemark("处理结果=提前还款完成时 申请状态变为：" + value);
				operateLogService.insert(operateLogBusinessStruct);
				sysIdList.add(getApplyId);
			}else if("0".equals(getType)){//还款中（信托）
				//查询是否是信托还款（1：信托，0：债权转让）
				String lendingChannelByApplyId = amountConfirmDAO.selectLendingChannelByApplyId(getApplyId);
				// TODO 添加打款时间
				String groupLoanRegistId = groupLoanRegistDAO.selectGroupLoanRegistId(getApplyId);
				FinanceMoney financeMoney = new FinanceMoney();
				//判断是手动处理还是贷后自动处理
				//记得加上判断是否为空
				if(receiveCreditObjs.get(0).getTime()!=""||!receiveCreditObjs.get(0).getTime().equals(null)){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date dateTime;
					dateTime = sdf.parse(receiveCreditObjs.get(0).getTime());
					financeMoney.setTradeTime(dateTime);
				}else{
					financeMoney.setTradeTime(new Date());
				}
				financeMoney.setHistoryFlag("F");
				financeMoney.setType("F");
				financeMoney.setFinanceStatus("2");
				financeMoney.setAssociationId(Integer.parseInt(groupLoanRegistId));
				financeMoneyDao.insertFinanceMoney(financeMoney);
				if(CommonUtil.isNotEmpty(lendingChannelByApplyId)){
					if("1".equals(lendingChannelByApplyId)){
						creditApplicationVo.setSysGuid(getApplyId);
						creditApplicationVo.setAuditStatus(Constants.STATE_15);
						creditApplicationVos.add(creditApplicationVo);
						OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
						operateLogBusinessStruct.setCreditapplicationId(creditapplicationId);
						operateLogBusinessStruct.setFunctionCode("400030");
						String value = DicUtil.convertCodeKeyToCodeValue("auditStatus", Constants.STATE_15);
						/*operateLogBusinessStruct.setRemark("uuid：" + getApplyId + "申请状态变为：" + value + "参数：" + getApplyId + ","
								+ getType);*/
						operateLogBusinessStruct.setRemark("处理结果=还款中时 申请状态变为：" + value);
						List<LocalReturnSchemeResponse> localReturnSchemeResponses = iRural2CreditService.returnScheme(creditapplicationId);
						returnPlanService.insertReturnPlanFromIocalReturnSchemeResponses(localReturnSchemeResponses, creditapplicationId);
						operateLogService.insert(operateLogBusinessStruct);
						//通知机构资产
						iorgamsService.synBorrowContractState(creditapplicationId,"3");//放款状态同步(3:表示成功)
						iorgamsService.matchConfirm(creditapplicationId);
					}else if("0".equals(lendingChannelByApplyId)){
						creditApplicationVo.setSysGuid(getApplyId);
						creditApplicationVo.setAuditStatus(Constants.STATE_15);
						creditApplicationVos.add(creditApplicationVo);
						OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
						operateLogBusinessStruct.setCreditapplicationId(creditapplicationId);
						operateLogBusinessStruct.setFunctionCode("400030");
						String value = DicUtil.convertCodeKeyToCodeValue("auditStatus", Constants.STATE_15);
						/*operateLogBusinessStruct.setRemark("uuid：" + getApplyId + "申请状态变为：" + value + "参数：" + getApplyId + ","
								+ getType);*/
						operateLogBusinessStruct.setRemark("处理结果=还款中时 申请状态变为：" + value);
						List<LocalReturnSchemeResponse> localReturnSchemeResponses = iRural2CreditService.returnScheme(creditapplicationId);
						returnPlanService.insertReturnPlanFromIocalReturnSchemeResponses(localReturnSchemeResponses, creditapplicationId);
						operateLogService.insert(operateLogBusinessStruct);
					}
				}
			}else if("3".equals(getType)){//等待重新合同复核
				//更新财务放款日期为历史(财务退票的时候)
				financeMoneyDao.updateHistory(creditapplicationId);
				creditApplicationVo.setSysGuid(getApplyId);
				creditApplicationVo.setAuditStatus(Constants.STATE_37);
				creditApplicationVos.add(creditApplicationVo);
				OperateLogBusinessStruct operateLogBusinessStruct = new OperateLogBusinessStruct();
				operateLogBusinessStruct.setCreditapplicationId(creditapplicationId);
				operateLogBusinessStruct.setFunctionCode("400040");
				String value = DicUtil.convertCodeKeyToCodeValue("auditStatus", Constants.STATE_37);
				/*operateLogBusinessStruct.setRemark("uuid：" + getApplyId + "申请状态变为：" + value + "参数：" + getApplyId + ","
						+ getType);*/
				operateLogBusinessStruct.setRemark("处理结果=等待重新合同复核时  申请状态变为：" + value);
				List<LocalReturnSchemeResponse> localReturnSchemeResponses = iRural2CreditService.returnScheme(creditapplicationId);
				returnPlanService.insertReturnPlanFromIocalReturnSchemeResponses(localReturnSchemeResponses, creditapplicationId);
				operateLogService.insert(operateLogBusinessStruct);
			}
		}
		baseDao.batchUpdate("RURAL2CREDIT.updateStatusByReceiveCreditMsg", creditApplicationVos);
		message.setSuccess(true);
		message.setCode("0");
		message.setMsg("成功！");
		try{
			if(sysIdList.size()>0){
				List<String> canInsertLoanCloseList = new ArrayList<String>();
				//删除已经发送短信成功的记录
				messageInfoService.deleteSuccessedLoanCloseList();
				//插入需要（还款完成、提前还款完成）发送短信的记录 然后等待定时任务发送短信
				messageInfoService.insertCreditMessageHisList(sysIdList);
				logger.info("插入贷款结清记录成功....");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return message;
	}
}